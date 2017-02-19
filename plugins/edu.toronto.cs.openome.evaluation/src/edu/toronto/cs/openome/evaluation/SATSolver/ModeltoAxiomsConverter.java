package edu.toronto.cs.openome.evaluation.SATSolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Vector;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.sat4j.core.VecInt;
import org.sat4j.specs.IteratorInt;

import edu.toronto.cs.openome.evaluation.commands.BacktrackReverseJudgmentCommand;
import edu.toronto.cs.openome.evaluation.commands.EnableHumanJudgmentCommand;
import edu.toronto.cs.openome_model.AndDecomposition;
import edu.toronto.cs.openome_model.BreakContribution;
import edu.toronto.cs.openome_model.Container;
import edu.toronto.cs.openome_model.Contribution;
import edu.toronto.cs.openome_model.Decomposition;
import edu.toronto.cs.openome_model.Dependable;
import edu.toronto.cs.openome_model.Dependency;
import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.HelpContribution;
import edu.toronto.cs.openome_model.HumanJudgment;
import edu.toronto.cs.openome_model.HurtContribution;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Link;
import edu.toronto.cs.openome_model.MakeContribution;
import edu.toronto.cs.openome_model.OrDecomposition;
import edu.toronto.cs.openome_model.SomeMinusContribution;
import edu.toronto.cs.openome_model.SomePlusContribution;
import edu.toronto.cs.openome_model.UnknownContribution;
import edu.toronto.cs.openome_model.impl.ModelImpl;
import edu.toronto.cs.openome_model.impl.SoftgoalImpl;
import edu.toronto.cs.openome_model.Intention;
import java.lang.Math;

public class ModeltoAxiomsConverter {
	ModelImpl model;
	Dimacs cnf;
	Vector<Link> done;
	AxiomsFactory axiomsFactory;
	DualHashMap<Integer, Intention> intentionIndex;
	CommandStack cs;
	
	public ModeltoAxiomsConverter(ModelImpl m, CommandStack c) {
		model = m;
		intentionIndex = new DualHashMap<Integer, Intention>();
		createIntentionIndex();
		done = new Vector<Link>();
		axiomsFactory = new AxiomsFactory();
		cs = c;
	}
	
	private void createIntentionIndex() {
		int sixCount = 1;
		//System.out.println("creating intentionIndex");
		for (Intention i : model.getAllIntentions()) {
			//System.out.println("put: " + sixCount + ", " + i.getName());
			intentionIndex.put(new Integer(sixCount), i);
			sixCount += 6;
		}		
	}
	
	private void reset(String filename) {
		done = new Vector<Link>();
		cnf = new Dimacs(filename);
	}
	
	public DualHashMap<Integer, Intention> getIntentionIndex() {
		return intentionIndex;
	}

	public Dimacs convertBothDirections(String filename)  {
		reset(filename);
		
		convertContributions(1);
		//System.out.println("done contributions");
		convertDependencies(1);
		//System.out.println("done dependencies");
		convertDecompositions(1);
		//System.out.println("done decompositions");
		createTargets(1);
		//System.out.println("done targets");
		createInvariants();
		//System.out.println("done invariants");
		createConstraints(1);
		//System.out.println("done constraints");
		
		return cnf;
	}
	
	public Dimacs convertForward(String filename) {
		reset(filename);
		convertContributions(2);
		convertDependencies(2);
		convertDecompositions(2);
		createTargets(2);
		createInvariants();
		createConstraints(2);

		return cnf;
	}
	
	public Dimacs convertBackward(String filename) {
		reset(filename);
		convertContributions(3);
		convertDependencies(3);
		convertDecompositions(3);
		createTargets(3);
		createInvariants();
		createConstraints(3);

		return cnf;
	}

	private void convertDecompositions(int dir) {
		//System.out.println(model.getDecompositions().size());
		for (Decomposition dec : model.getDecompositions()) {
			//System.out.println("decomposition: " + dec.toString());
			if (!done.contains(dec)) {
				//System.out.println("not done: " + dec.toString());
				Intention target = dec.getTarget();
				//System.out.println("decomposition target: " + target.getName());
				
				if (target != null) {
									
					Vector<Link> links = new Vector<Link>();
					Vector<Intention> sources = new Vector<Intention>();
					for (Decomposition sibling: target.getDecompositionsTo()){
						//System.out.println("done " + sibling.toString());
						done.add(sibling);
						links.add(sibling);
						Intention source = sibling.getSource();
						if (source != null) {	
							//System.out.println("decomposition source is not null for: " + dec.toString());
							sources.add(source);
						} else {
							//System.out.println("decomposition source is null for: " + dec.toString());
						}
						//System.out.println("decomposition source: " + sibling.getSource().getName());
					}
					//System.out.println(sources.size());
					if (sources.size() > 0) {
						//System.out.println("decomposition source and target not null for: " + target.getName());
						String description = "Decomposition to: " + target.getName();
						LinkAxioms la = null;
						if (dec instanceof AndDecomposition)
							la = axiomsFactory.createLinkAxiom(sources, target, links, "Decomposition", intentionIndex, description);
						if (dec instanceof OrDecomposition) {
							//System.out.println("Ordecomp");
							la = axiomsFactory.createLinkAxiom(sources, target, links, "Means Ends", intentionIndex, description);
						}
							
						
						if (la != null) {
							switch (dir) {
								case 1: la.createAllClauses(); break;
								case 2: la.createForwardClauses(); break;
								case 3: la.createBackwardClauses(); break;
								default: la.createAllClauses();  break;
							}	
						}
						
						cnf.addAxioms(la);
					}
				}
			}			
			else { 
				//System.out.println("already done " + dec.toString());
				}
			
			//System.out.println("done " + dec.toString());
			done.add(dec);
						
		}		
	}	

	private void convertDependencies(int dir) {
		for (Dependency dep : model.getDependencies()) {
						
			if (!done.contains(dep)) {
				Intention sourceInt = null;
				Intention targetInt = null;	
				
				//this is the source in forward evaluation, the dependee
				Dependable source = dep.getDependencyFrom();
					
				//if it's not an actor
				if (!(source instanceof Container))  {
					//This is the target?
					sourceInt = (Intention) source;
					//System.out.println("dependency to " + sourceInt.getName());
				}
				
				//this is the target in forward evaluation, the depender
				Dependable target = dep.getDependencyTo();
				
				//if it's not an actor
				if (!(target instanceof Container))  {
					targetInt = (Intention) target;
					//System.out.println("dependency from " + targetInt.getName());
				}
				
				//it's a dependency from an intention to an intention
				if (targetInt != null && sourceInt != null) {
					Vector<Link> link = new Vector<Link>();
					link.add(dep);
					Vector<Intention> sources = new Vector<Intention>();
					sources.add(sourceInt);
					//intentionIndex.print();
					String description = "Dependency: " + targetInt.getName();
					LinkAxioms la = axiomsFactory.createLinkAxiom(sources, targetInt, link, "Dependency", intentionIndex, description);
					
					switch (dir) {
						case 1: la.createAllClauses(); break;
						case 2: la.createForwardClauses(); break;
						case 3: la.createBackwardClauses(); break;
						default: la.createAllClauses();  break;
					}
					//la.createAllClauses();
					cnf.addAxioms(la);
				}
			}
			else {
				//System.out.println("already done " + dep.toString());
				}
			
			//System.out.println("done " + dep.toString());
			done.add(dep);						
		}		
	}	

	private void convertContributions(int dir) {
		//System.out.println("converting contributions");	
		
		for (Contribution cont : model.getContributions()) {
			if (!done.contains(cont)) {
							
				//this is the source in forward evaluation
				Intention source = cont.getSource();					
				
				//System.out.println("contribuion from " + source.getName());
								
				//this is the target in forward evaluation
				Intention target = cont.getTarget();
					
				//System.out.println("contribuion to " + target.getName());
				
				if (source != null & target != null) {
					Vector<Link> links = new Vector<Link>();
					Vector<Intention> sources = new Vector<Intention>();
					for (Contribution sibling: target.getContributesFrom()){
						//System.out.println("done " + sibling.toString());
						done.add(sibling);
						links.add(sibling);
						sources.add(sibling.getSource());
						//System.out.println("contribution source: " + sibling.getSource().getName());
						/*if (sibling instanceof MakeContribution) 
							System.out.println("Make");
						if (sibling instanceof HelpContribution)
							System.out.println("Help");
						if (sibling instanceof SomePlusContribution)
							System.out.println("SomePlus");
						if (sibling instanceof UnknownContribution)
							System.out.println("Unknown");
						if (sibling instanceof SomeMinusContribution)
							System.out.println("SomeMinus");
						if (sibling instanceof HurtContribution)
							System.out.println("Hurt");
						if (sibling instanceof BreakContribution)
							System.out.println("Break");*/
					}				
								
					//intentionIndex.print();
					
					LinkAxioms la = null;
					
					String description = "Contriubtion to: " + target.getName();
					
					la = axiomsFactory.createLinkAxiom(sources, target, links, "Contribution", intentionIndex, description);
						
					switch (dir) {
						case 1: la.createAllClauses(); break;
						case 2: la.createForwardClauses(); break;
						case 3: la.createBackwardClauses(); break;
						default: la.createAllClauses();  break;
					}
					//la.createAllClauses();
					cnf.addAxioms(la);
				}
				
			}
			else {
				//System.out.println("already done " + cont.toString());
				}
			
			//System.out.println("done " + cont.toString());
			done.add(cont);
						
		}
		
	}
	
	private void createConstraints(int dir) {
		for (Intention intention : model.getAllIntentions()) {
			Vector<Intention> sources = new Vector<Intention>();
			sources.add(intention);
			String description = "Constraints for: " + intention.getName();
			ConstraintAxioms ia = new ConstraintAxioms(intention,intentionIndex, description);
			if (intention.isLeaf()) {	
				
				if (!(intention instanceof SoftgoalImpl)) {
					ia.createLeafClauses(); 
					cnf.addAxioms(ia);	
				}
				
			} 
			//if (dir == 3) {
			//else if (intention.getContributesFrom().size() < 2 ) { //& !(intention instanceof SoftgoalImpl)) {
			//		ia.createAllClauses();
			//		cnf.addAxioms(ia);	
			//}
			//}
				
			
		}
		
	}

	private void createInvariants() {
		
		for (Intention intention : model.getAllIntentions()) {
			Vector<Intention> sources = new Vector<Intention>();
			sources.add(intention);
			String description = "Invariants for: " + intention.getName();
			IntentionAxioms ia = axiomsFactory.createIntentionAxiom(intention, "Invariant", intentionIndex, description);
			
			ia.createAllClauses(); 
						
			cnf.addAxioms(ia);			
		}
					
	}

	private void createTargets(int dir) {
		for (Intention intention : model.getAllIntentions()) {
			if (intention.getQualitativeReasoningCombinedLabel() != EvaluationLabel.NONE) {
				Vector<Intention> sources = new Vector<Intention>();
				sources.add(intention);
				String description = "Target for: " + intention.getName();
				TargetAxioms ia = new TargetAxioms(intention, intentionIndex, description);
			
				/*switch (dir) {
					case 1: ia.createAllClauses(); break;
					case 2: ia.createAllClauses(); break;
					case 3: ia.createBackwardClauses(); break;
					default: ia.createAllClauses();  break;
				}*/
				
				ia.createAllClauses();
						
				cnf.addAxioms(ia);		
			}
		}
		
	}

	public HashMap<Intention, int[]> convertResults(Vector<Integer> intResults) {
		HashMap<Intention, int[]> map = new HashMap<Intention, int[]>();
		int[] list;
		/*for (int i : intResults) {
			Intention intention = (Intention) intentionIndex.getForward(new Integer(i));
			if (intention != null) {
				
			}
		}*/
		//System.out.println("Converting results");
		for (Object obj : intentionIndex.keySetForward()) {
			if (obj == null)
				System.out.println("ojbect is null in convert Results");
				
			Integer integer = (Integer) obj;
			//System.out.println("index: " + integer.intValue());
			
			int index = intResults.indexOf(integer);
			//System.out.println(index);
			if (index < 0) {
				index = intResults.indexOf(new Integer(integer.intValue() * -1));
				//System.out.println(index);
			}
			if (index >= 0) {
				list = new int[6];
				for (int i=0; i<6; i++) {
					list[i] = intResults.get(index + i).intValue();
					//System.out.println("i: " + i + "result: " + list[i]);
				}
				
				map.put((Intention) intentionIndex.getForward(integer), list);
				//System.out.println(((Intention) intentionIndex.getForward(integer)).getName());
			}				
		}
		
		return map;
	}
	
	public Vector<String> convertMinResultstoStringClause(Vector<Integer> intResults, Dimacs cnf) {
		//HashMap<Intention, int[]> map = new HashMap<Intention, int[]>();
		
		Vector<String> values = new Vector<String>();
		//intentionIndex.print();
		for (int i =0; i< cnf.getNumClauses();i++) {
			int index = intResults.indexOf(new Integer(i));
			
			if (index < 0) {
				//System.out.println("index: " + index + " for " + i);
				VecInt vi = cnf.getClauseByIndex(i);
				if (vi != null)
					values.add(convertToStringClause(vi));
				else
					System.out.println("couldn't find clause by index in conversion");
			}
			
		}
		
		return values;	
	
	}
	
	public Vector<String> convertMinResultstoString(Vector<Integer> intResults, Dimacs cnf) {
		//HashMap<Intention, int[]> map = new HashMap<Intention, int[]>();
		
		Vector<String> values = new Vector<String>();
		//intentionIndex.print();
		for (int i =0; i< cnf.getNumClauses();i++) {
			int index = intResults.indexOf(new Integer(i));
			
			if (index < 0) {
				//System.out.println("index: " + index + " for " + i);
				VecInt vi = cnf.getClauseByIndex(i);
				if (vi != null)
					values.addAll(convertToString(vi));
				else
					System.out.println("couldn't find clause by index in conversion");
			}
			
		}
		
		return values;	
	
	}
	
	public Vector<Intention> convertMinResultstoIntentions(Vector<Integer> intResults, Dimacs cnf) {
		Vector<Intention> conflicts = new Vector<Intention>();
		//intentionIndex.print();
		for (int i =0; i< cnf.getNumClauses();i++) {
			int index = intResults.indexOf(new Integer(i));
			
			if (index < 0) {
				//System.out.println("index: " + index + " for " + i);
				VecInt vi = cnf.getClauseByIndex(i);
				if (vi != null)
					conflicts.addAll(convertToIntentions(vi));
				else
					System.out.println("couldn't find clause by index in conversion to Intention");
			}
			
		}
		conflicts = unique(conflicts);
		return conflicts;
	}
	
	public Vector<Intention> convertMinResultstoSourceIntentions(Vector<Integer> intResults, Dimacs cnf, HashMap<Intention, String> minResults) {
		Vector<Intention> conflicts = new Vector<Intention>();
		Vector<VecInt> conflictClauses = new Vector<VecInt>();
		Vector<VecInt> roots = new Vector<VecInt>();
		//intentionIndex.print();
		for (int i =0; i< cnf.getNumClauses();i++) {
			int index = intResults.indexOf(new Integer(i));
			
			if (index < 0) {
				//System.out.println("index: " + index + " for " + i);
				VecInt vi = cnf.getClauseByIndex(i);
				if (vi != null)  {
					conflictClauses.add(vi);
					if (vi.size() == 2)
						roots.add(vi);
				}
				else
					System.out.println("couldn't find clause by index in conversion to Source Intention");
			}
			
		}
		Vector<Integer> intRoots = new Vector<Integer>();
		for (VecInt root : roots) {
			convertToIntentionAndLabel(root.get(0), minResults);
			intRoots.add(root.get(0));
		}		
		
		VecInt sources = new VecInt();
		findSources(sources, intRoots, conflictClauses, minResults);
		
		conflicts = convertToIntentions(sources);
		
		conflicts = unique(conflicts);
		return conflicts;
	}
	
	private void findSources(VecInt sources, Vector<Integer> roots, Vector<VecInt> clauses, HashMap<Intention, String> minResults) {
		//System.out.println("FindSources");
		Vector<Integer> newRoots = new Vector<Integer>();
		boolean foundRoot = false;
		Vector<VecInt> clauses2 = new Vector<VecInt>(clauses);
		String rootString = "";
		for (Integer root: roots) {
		    System.out.println("Root:" + root.intValue());
			rootString += convertToIntention(root).getName() + "\t";
			foundRoot = false;
			for (VecInt clause : clauses) {
				IteratorInt itr = clause.iterator();
				while(itr.hasNext()) {
					Integer i = itr.next();		
					if (i.intValue() != 0) {
						if ((i.intValue() == (-1 * root.intValue())) || ((-1 * i.intValue()) == root.intValue())) {
							foundRoot = true;
							Integer found = i;
							itr = clause.iterator();
						//	System.out.println("Found root: " + root.intValue());
							while (itr.hasNext()) {
								Integer other = itr.next();
								if (other.intValue() != 0) {
									if (Math.abs(other.intValue()) != Math.abs(found.intValue())) {
										if (!newRoots.contains(other)) {
											newRoots.add(other);
											//System.out.println("adding: " + other.intValue());
										}
									}
									if ((other.intValue() != (-1 * root.intValue())) && ((-1 * other.intValue()) != root.intValue()))
										convertToIntentionAndLabel(other, minResults);
								}
							}	
							clauses2.remove(clause);						
						}	
					}
				}
			}
			if (!foundRoot) {
				sources.push(root);
			}
		}
		System.out.println(rootString);
		
		if (newRoots.size() == 0)
			return;
		
		findSources(sources, newRoots, clauses2, minResults);
	}
	

	private void convertToIntentionAndLabel(Integer other, HashMap<Intention, String> minResults) {
		//System.out.println("converting: " + other);
		String str = "";
		boolean neg = false;
		for (int i = 0; i< 6; i++) {				
				
			if (other != 0) {
				if (other < 0) {
					other = other * -1;
					neg = true;
				}
				Intention intention = (Intention) intentionIndex.getForward(new Integer(other - i));
				if (intention != null) {						
					//System.out.println("Got intention: " + intention.getName());
					switch (i) {
						case(0): if (neg) {str += "not ";} str += "S"; break; 
						case(1): if (neg) {str += "not ";} str += "PS"; break;
						case(2): if (neg) {str += "not ";} str += "U"; break;
						case(3): if (neg) {str += "not ";} str += "C"; break;
						case(4): if (neg) {str += "not ";} str += "PD"; break;
						case(5): if (neg) {str += "not ";} str += "D"; break;
					}
												
					neg = false;
					if (minResults.containsKey(intention)) {
						String tmp = minResults.get(intention) + ", " + str;
						minResults.put(intention, tmp);
						return;
					}
					else {
						minResults.put(intention, str);
						return;
					}
					
				}
			}
		}					
	}

	public HashMap<Intention, String> convertMinResultstoIntentionsAndLabels(Vector<Integer> intResults, Dimacs cnf) {
		HashMap<Intention, String> minResults = new HashMap<Intention, String>();
		Vector<VecInt> conflictClauses = new Vector<VecInt>();
		//intentionIndex.print();
		for (int i =0; i< cnf.getNumClauses();i++) {
			int index = intResults.indexOf(new Integer(i));
			
			if (index < 0) {
				VecInt vi = cnf.getClauseByIndex(i);
				if (vi != null)
					conflictClauses.add(vi);
				else
					System.out.println("couldn't find clause by index in conversion to Hashmap");
			}			
		}	
		
		minResults = convertToIntentionAndLabels(conflictClauses);
		
		for (Intention i : minResults.keySet()) {
			System.out.println(i.getName() + " " + minResults.get(i));
		}
		
		return minResults;	
	}

	private Intention convertToIntention(Integer intNum) {
		if (intNum < 0) {
			intNum = intNum * -1;
		}
		for (int i = 0; i< 6; i++) {					
			if (intNum != 0) {				
				Intention intention = (Intention) intentionIndex.getForward(new Integer(intNum - i));
				if (intention != null) {
					return intention;
				}				
			}
			
		}
		return null;
	}

	public Vector<Intention> unique  (  Vector<Intention> v  )   {  
         Vector<Intention> tmpVector=new Vector<Intention> (  ) ;  
         Intention tmpValue; 
        
         if  ( v.isEmpty (  )  )  return ( v ) ;         
         for  ( int j = 0; j  <  v.size (  ) ; j++ )   {  
           tmpValue =   v.elementAt ( j ) ; 
           if  ( tmpValue!=null )   {     
        	   if  (  tmpVector.isEmpty (  )   )   
        		   tmpVector.addElement ( tmpValue ) ; 
               if  ( tmpVector.indexOf ( tmpValue ) ==-1 )  {  
            	   tmpVector.addElement ( tmpValue ) ; 
               }  
           }                   
         } 
       return  tmpVector; 
    } 

	
	private Vector<Intention> convertToIntentions(VecInt vi) {
		IteratorInt it = vi.iterator();
		Vector<Intention> conflicts = new Vector<Intention>();
		
		while(it.hasNext()) {
			int var = it.next();
			for (int i = 0; i< 6; i++) {				
				
				if (var != 0) {
					if (var < 0) {
						var = var * -1;
					}
					Intention intention = (Intention) intentionIndex.getForward(new Integer(var - i));
					if (intention != null) {
						if (!conflicts.contains(intention))
							conflicts.add(intention);
					}
				}
			}
			
		}
		return conflicts;
	}
	
	
	private String convertToStringClause(VecInt vi) {
		IteratorInt it = vi.iterator();
		//System.out.println("Converting to string: " + vi.toString());
		//Vector<String> strings = new Vector<String>();
		String str = "";
		boolean neg = false;
		int count = 0;
		while(it.hasNext()) {
			int var = it.next();
			for (int i = 0; i< 6; i++) {				
				//System.out.println(var);
				if (var != 0) {
					if (var < 0) {
						var = var * -1;
						neg = true;
					}
					Intention intention = (Intention) intentionIndex.getForward(new Integer(var - i));
					if (intention != null) {
						//System.out.println("Got intention: " + intention.getName());
						switch (i) {
							case(0): if (neg) {str += "not ";} str += "S(" + intention.getName() + ")"; break; 
							case(1): if (neg) {str += "not ";} str += "PS(" + intention.getName() + ")"; break;
							case(2): if (neg) {str += "not ";} str += "U(" + intention.getName() + ")"; break;
							case(3): if (neg) {str += "not ";} str += "C(" + intention.getName() + ")"; break;
							case(4): if (neg) {str += "not ";} str += "PD(" + intention.getName() + ")"; break;
							case(5): if (neg) {str += "not ";} str += "D(" + intention.getName() + ")"; break;
						}
						if (count < (vi.size() - 2))
							str += " OR ";
						//System.out.println(str);
						//break out of the for loop
						i = 6;
						neg = false;
					}
				}
			}
			count++;
			
		}
		return str;
	}
	
	private HashMap<Intention, String> convertToIntentionAndLabels(Vector<VecInt> clauses) {
		HashMap<Intention, String> map = new HashMap<Intention, String>();
		
		for (VecInt clause : clauses) {
			IteratorInt it = clause.iterator();
			//System.out.println("Converting to string: " + vi.toString());
			//Vector<String> strings = new Vector<String>();
			String str = "";
			boolean neg = false;
			while(it.hasNext()) {
				int var = it.next();
				for (int i = 0; i< 6; i++) {				
					//System.out.println(var);
					if (var != 0) {
						if (var < 0) {
							var = var * -1;
							neg = true;
						}
						Intention intention = (Intention) intentionIndex.getForward(new Integer(var - i));
						if (intention != null) {
							
							//System.out.println("Got intention: " + intention.getName());
							switch (i) {
								case(0): if (neg) {str += "not ";} str += "S"; break; 
								case(1): if (neg) {str += "not ";} str += "PS"; break;
								case(2): if (neg) {str += "not ";} str += "U"; break;
								case(3): if (neg) {str += "not ";} str += "C"; break;
								case(4): if (neg) {str += "not ";} str += "PD"; break;
								case(5): if (neg) {str += "not ";} str += "D"; break;
							}
							
							//System.out.println(str);
							//break out of the for loop
							i = 6;
							neg = false;
							if (map.containsKey(intention)) {
								String tmp = map.get(intention) + ", " + str;
								map.put(intention, tmp);
							}
							map.put(intention, str);
							str = "";
						}
					}
				}
				
				
			}
		}
		return map;
	}
	
	private Vector<String> convertToString(VecInt vi) {
		IteratorInt it = vi.iterator();
		//System.out.println("Converting to string: " + vi.toString());
		//Vector<String> strings = new Vector<String>();
		Vector<String> strs = new Vector<String>();
		boolean neg = false;
		
		while(it.hasNext()) {
			int var = it.next();
			for (int i = 0; i< 6; i++) {				
				//System.out.println(var);
				if (var != 0) {
					if (var < 0) {
						var = var * -1;
						neg = true;
					}
					Intention intention = (Intention) intentionIndex.getForward(new Integer(var - i));
					if (intention != null) {
						//System.out.println("Got intention: " + intention.getName());
						String str = "";
						switch (i) {
							case(0): if (neg) {str += "not ";} str += "S(" + intention.getName() + ")"; break; 
							case(1): if (neg) {str += "not ";} str += "PS(" + intention.getName() + ")"; break;
							case(2): if (neg) {str += "not ";} str += "U(" + intention.getName() + ")"; break;
							case(3): if (neg) {str += "not ";} str += "C(" + intention.getName() + ")"; break;
							case(4): if (neg) {str += "not ";} str += "PD(" + intention.getName() + ")"; break;
							case(5): if (neg) {str += "not ";} str += "D(" + intention.getName() + ")"; break;
						}
						strs.add(str);
						//System.out.println(str);
						//break out of the for loop
						i = 6;
						neg = false;
					}
				}
			}
			
			
		}
		return strs;
	}

	public Dimacs addHumanJudgment(Dimacs cnf, Intention i, HumanJudgment hj, int dir) {
		//Disable old clauses
		//cnf.disableAxioms(links);		
		
		String description = "Human judgment for intention " + i.getName();
		
		Vector<Intention>  sources = new Vector<Intention>();
		Vector<Link> links = new Vector<Link>();
		
		ListIterator<Intention> it = hj.getLabelBag().getLabelBagIntentions().listIterator();
		while (it.hasNext()) {
			Intention intn =  it.next();
			sources.add(intn);
			//System.out.println("source: " + ilp.getIntention().getName());
			for (Contribution cont : intn.getContributesTo()) {
				if (cont.getTarget().equals(i)) {
					links.add(cont);
					//System.out.println("link: " + cont.getContributionType());
				}				
			}
		}
		
		Vector<Axioms> axs = cnf.getAxioms(links);
		
		for (Axioms ax : axs) {
			//removing all previous human judgments to this intention
			if (ax instanceof HumanJudgmentLinkAxioms) {
				cnf.removeAxiom(ax);
				System.out.println("shouldn't happen?");
			}
			//disable non human judgment axioms to this intention
			else {
				cnf.disableAxiom(ax);
			}
		}
		
		HumanJudgmentLinkAxioms hja = new HumanJudgmentLinkAxioms(sources, i, links, intentionIndex, description);
		
		hja.addLabelBag(hj.getLabelBag());
		
		//both directions
		if (dir == 0) {			
			hja.createAllClauses();			
		}
		//forwards
		else if (dir == 1) {
			hja.createForwardClauses();
		} 
		//backwards
		else if (dir == -1) {
			hja.createBackwardClauses();
		}
		
		cnf.addAxioms(hja);	
		
		return cnf;
		
	}
	
	public Dimacs backtrackHumanJudgment(Dimacs cnf, Intention i, HumanJudgment hj) {
		//System.out.println("Converter backtracking for " + w.getIntention().getName());
		int index = i.getHumanJudgments().indexOf(hj);
		
		if (index < 0) {
			System.out.println("Problem in backtracking over human judgments, judgment and intention don't match");
			return null;
		}
		
		Command setEnabled = new EnableHumanJudgmentCommand(hj, false);
		cs.execute(setEnabled);
		
		Vector<Link> links = new Vector<Link>();
		
		ListIterator<Intention> it = hj.getLabelBag().getLabelBagIntentions().listIterator();
		while (it.hasNext()) {
			Intention intn =  it.next();
			//System.out.println("source: " + ilp.getIntention().getName());
			for (Contribution cont : intn.getContributesTo()) {
				if (cont.getTarget().equals(i)) {
					links.add(cont);
					//System.out.println("link: " + cont.getContributionType());
				}				
			}
		}
		
		Vector<Axioms> axs = cnf.getAxioms(links);
		//System.out.println(axs.size());
		
		for (Axioms ax : axs) {
			//System.out.println(ax.getDescription());
			if (ax instanceof HumanJudgmentLinkAxioms) {
				cnf.removeAxiom(ax);
			}
			else {
				//System.out.println("trying to enable axiom.");
				//re-enable old non-judgment axioms for this intention
				cnf.enableAxiom(ax);
			}
		}	
		Command backtrackReverse = new BacktrackReverseJudgmentCommand(i, hj);
		cs.execute(backtrackReverse);
				
		//System.out.println("Converter backtracked for " + w.getIntention().getName());
		return cnf;
	}

	public Vector<Intention> findTargets() {
		Vector<Intention> targets = new Vector<Intention>();
		for (Intention intention : model.getAllIntentions()) {
			if (intention.getQualitativeReasoningCombinedLabel() != EvaluationLabel.NONE) {
				
				targets.add(intention);
				
			}
		}
		return targets;
	}
	
}

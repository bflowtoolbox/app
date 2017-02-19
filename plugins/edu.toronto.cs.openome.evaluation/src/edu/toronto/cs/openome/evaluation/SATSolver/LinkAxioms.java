package edu.toronto.cs.openome.evaluation.SATSolver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import org.sat4j.core.VecInt;
import org.sat4j.specs.IteratorInt;

import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Link;
import edu.toronto.cs.openome_model.UnknownContribution;

public abstract class LinkAxioms extends Axioms {
	
	protected Vector<Intention> sourceInts;
	protected Intention target;
	protected Vector<Link> links;
	protected Vector<VecInt> forwardClauses;
	protected Vector<VecInt> backwardClauses;
	protected int tIndex;
	protected VecInt sourceIndexes;

	
	public LinkAxioms(Vector<Intention> sources, Intention targ, Vector<Link> l, DualHashMap<Integer, Intention> dhm, String desc) {
		super(dhm, desc);
		sourceInts = sources;
		target = targ;
		links = l;
		forwardClauses = new Vector<VecInt>();	
		backwardClauses = new Vector<VecInt>();		
		sourceIndexes = null;
		tIndex = 0;
		
	}
	
	protected void findIndexes() {
		//System.out.println("finding indexes");
		if (sourceIndexes == null) {
			//System.out.println("sourceIndexes was null");
			sourceIndexes = new VecInt();
			
			if (intentionMap != null) {
			//	System.out.println("intentionMap was not null");
				Integer intTIndex = (Integer) intentionMap.getInverse(target);
				tIndex = intTIndex.intValue();
				//System.out.println("target index is: " + tIndex);
		
				if (sourceInts != null) {
					for (Intention sInt: sourceInts) {
						Integer sourceIndex = (Integer) intentionMap.getInverse(sInt);
						sourceIndexes.push(sourceIndex.intValue());	
						//.out.println("source index is: " + sourceIndex.intValue());
					}	
				}
				else {
					//System.out.println("sourceInts is null");
				}
			}
			else {
				//System.out.println("intentionMap is  null");
			}
		}
		else {
			//System.out.println("sourceIndexes is not null " + sourceIndexes.toString());			
		}
	}
	
	public boolean containsLinks(Vector<Link> l) {
		//for (Link link : links) {
	//		System.out.println("link: " + link.toString());
		//}
		boolean equals = false;
		for (Link link : l) {
			
			equals = false;
			for (Link link2 : links) {
				if (link.equals(link2)) {
					//System.out.println("links equal: " + link.toString() + link2.toString());
					equals = true;
				}
				else {
					//System.out.println("links not equal: " + link.toString() + link2.toString());
				}
			}
			if (!equals)
				return false;
		}
		return true;
	}
	
	public void createAllClauses() {
		//System.out.println("Creating Clauses");
		
		createForwardClauses();
		//System.out.println(getNumClauses());
		createBackwardClauses();	
	}
	
	abstract public void createForwardClauses(); 
	
	abstract public void createBackwardClauses();
	
	public int getNumClauses() {
		return forwardClauses.size() + backwardClauses.size();
	}
	
	public int getNumVars() {
		return intentionMap.size() * 6;
	}
	
	public Vector<String> getClauses() {
		Vector<String> strClauses = new Vector<String>();
		
		for (VecInt vi : forwardClauses)  {
			String str = "";
			IteratorInt it = vi.iterator();
			while (it.hasNext()) {			
				str += String.valueOf(it.next()) + " ";				
			}
			
			strClauses.add(str);
		}
		
		for (VecInt vi : backwardClauses)  {
			String str = "";
			IteratorInt it = vi.iterator();
			while (it.hasNext()) {			
				str += String.valueOf(it.next()) + " ";				
			}
			
			strClauses.add(str);
		}
		
		return strClauses;
	}
	
	public Vector<String> getForwardClauses() {
		Vector<String> strForClauses = new Vector<String>();
		
		for (VecInt vi : forwardClauses)  {
			String str = "";
			IteratorInt it = vi.iterator();
			while (it.hasNext()) {			
				str += String.valueOf(it.next()) + " ";				
			}
			
			strForClauses.add(str);
		}
		
		return strForClauses;
	}
	
	public Vector<String> getBackwardClauses() {
		Vector<String> strBackClauses = new Vector<String>();
		
		for (VecInt vi : backwardClauses)  {
			String str = "";
			IteratorInt it = vi.iterator();
			while (it.hasNext()) {			
				str += String.valueOf(it.next()) + " ";				
			}
			
			strBackClauses.add(str);
		}
		
		return strBackClauses;
	}
	
	
	protected Vector<VecInt> allButImplicationForward(int special, int specmult, int normal, int normalmult) {
		
		Vector<VecInt> v = generateForwardCombinations(0, new Vector<VecInt>(), special, specmult, normal, normalmult);
		
		// adding ~a to all combinations
		for (int i = 0; i < v.size(); i++){
			v.get(i).push(tIndex + special);
			v.get(i).push(0);
		}
		
		return simplify(v);
	}
	
	protected Vector<VecInt> generateForwardCombinations(int index, Vector<VecInt> v, int special, int specmult, int normal, int normalmult) {
		
		if (index == sourceIndexes.size()) {
			return v;
		}
		
		Vector<VecInt> vTemp = new Vector<VecInt>();
		
		if (index == 0) {
			
			VecInt vi = new VecInt();
			vi.push((sourceIndexes.get(index) + special) * specmult * -1);
			vTemp.add(vi);
		}
		else {
			for (VecInt vi : v) {
				VecInt tmp = new VecInt();
				tmp.pushAll(vi);
				tmp.push((sourceIndexes.get(index) + special) * specmult * -1);
				vTemp.add(tmp);
				tmp = new VecInt();
				tmp.pushAll(vi);
				tmp.push((sourceIndexes.get(index) + normal) * normalmult * -1);
				vTemp.add(tmp);
			}
			VecInt tmp = new VecInt();
			for (int i = 0; i< index; i++) {				
				tmp.push((sourceIndexes.get(i) + normal) * normalmult * -1);
			}
			tmp.push((sourceIndexes.get(index) + special) * specmult * -1);
			vTemp.add(tmp);
		}
		
		return generateForwardCombinations(index+1, vTemp, special, specmult, normal, normalmult);
		
	}
	
	protected Vector<VecInt> allButImplicationBackward(int special, int specmult, int normal, int normalmult) {
		//System.out.println("allButImplicationBackward: " + special + " " + normal);
		ArrayList<ArrayList<Integer>> vTemp = new ArrayList<ArrayList<Integer>>();
				
		Vector<VecInt> v = generateForwardCombinations(0, new Vector<VecInt>(), special, -1 * specmult, normal, -1 * normalmult);
		
		for (VecInt vi : v) {
			//System.out.println(vi.toString());
			ArrayList<Integer> a = new ArrayList<Integer>();
			IteratorInt itr = vi.iterator();
			while (itr.hasNext()) {
				a.add(new Integer(itr.next()));
			}
			vTemp.add(a);
		}
		
		ArrayList<ArrayList<Integer>> comb = generateBackwardCombinations(vTemp, 0, new ArrayList<ArrayList<Integer>>());
		
		Vector<VecInt> vComb = new Vector<VecInt>();
		//System.out.println(comb.size());
		// adding ~a to all combinations
		for (int i = 0; i < comb.size(); i++){
			
			comb.get(i).add((tIndex + special) * -1);
			comb.get(i).add(0);
			VecInt t = new VecInt();
			for (Integer in : comb.get(i)) {
				t.push(in.intValue());
			}
			//System.out.println(t.toString());
			vComb.add(t);
		}
		
		return simplify(vComb);
	}
	
	public static ArrayList<ArrayList<Integer>> generateBackwardCombinations(ArrayList<ArrayList<Integer>> subclauses, int index, ArrayList<ArrayList<Integer>> comb){

		// base case, no more subclauses
		if (index == subclauses.size())
			return comb;

		// get current subclause
		ArrayList<Integer> clause = subclauses.get(index);

		ArrayList<ArrayList<Integer>> newComb = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> tmp;
		if (index > 0){
			// add all possible combinations to the set of clauses we've already built up
			for (int i = 0; i < clause.size(); i++){
				for (int j = 0; j < comb.size(); j++){
					// get combination j, add literal i from clause to it and store it in the combinations arraylist
					tmp = new ArrayList<Integer>(comb.get(j));
					if (!tmp.contains(clause.get(i))) {
						tmp.add(clause.get(i));
					}
					newComb = containsSuperSetOrEqual(tmp, newComb);
					
				}
			}
		}
		else{
			// need to setup comb
			for (int i = 0; i < clause.size(); i++){
				tmp = new ArrayList<Integer>();
				tmp.add(clause.get(i));
				newComb.add(tmp);
			}
		}
		// newComb becomes comb, increment index
		return generateBackwardCombinations(subclauses, index+1, newComb);
	}

	private static ArrayList<ArrayList<Integer>> containsSuperSetOrEqual(ArrayList<Integer> tmp, ArrayList<ArrayList<Integer>> newComb) {

		for (ArrayList<Integer> al : newComb) {
			if (subsetOrEqual(al, tmp))
				return newComb;
			if (subsetOrEqual(tmp, al)) {
				newComb.remove(al);
				newComb.add(tmp);
				return newComb;
			}					
		}
		
		newComb.add(tmp);
		return newComb;
	}

	private static boolean subsetOrEqual(ArrayList<Integer> al, ArrayList<Integer> tmp) {
		for (Integer i: al) {
			if (!tmp.contains(i))
				return false;
		}
		return true;
	}

	public boolean containsBackward(VecInt vi) {
		
		for (VecInt vi2 : backwardClauses) {
			//System.out.println(vi.toString());
			//System.out.println(vi2.toString());
			if(equalsVecInt(vi, vi2))
				return true;
		}
		return false;
	}
	
	public boolean containsForward(VecInt vi) {
		for (VecInt vi2 : forwardClauses) {
			if(equalsVecInt(vi, vi2))
				return true;
		}
		return false;
	}
	
	public boolean contains(VecInt vi) {
		if (containsBackward(vi))
			return true;
		if (containsForward(vi))
			return true;
		return false;
	}
	
	public VecInt getClauseByIndex(int index) {
		int numClause = getNumClauses();
		//System.out.println(description + " " + clauseIndex);
		for (int i = 0; i< numClause; i++) {
			 if ((clauseIndex + i) == index) {
				 //System.out.println("found");
				 
				 if (i < forwardClauses.size()) {
					 //System.out.println(i);
					 return forwardClauses.get(i);
				 }
				 else {
					 //System.out.println(i- forwardClauses.size() + " " + backwardClauses.size());
					 return backwardClauses.get(i - forwardClauses.size());
				 }
				 
			 }
		}
		
		return null;
	}
}

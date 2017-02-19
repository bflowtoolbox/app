package edu.toronto.cs.openome.conversion.service;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.reader.InstanceReader;
import org.sat4j.reader.Reader;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;
import org.sat4j.tools.ModelIterator;

import util.Computing;
import edu.toronto.cs.openome.conversion.parser.GoalModelReader;
import edu.toronto.cs.openome_model.BreakContribution;
import edu.toronto.cs.openome_model.Container;
import edu.toronto.cs.openome_model.Contribution;
import edu.toronto.cs.openome_model.Decomposition;
import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.HelpContribution;
import edu.toronto.cs.openome_model.HurtContribution;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.MakeContribution;
import edu.toronto.cs.openome_model.Model;
import edu.toronto.cs.openome_model.OrDecomposition;
import edu.toronto.cs.openome_model.Property;
import edu.toronto.cs.openome_model.Softgoal;
import edu.toronto.cs.openome_model.openome_modelFactory;
import edu.toronto.cs.openome_model.openome_modelPackage;

@SuppressWarnings("restriction")
public class QueryVariability2 implements IConfigurator {

	/**
	 * we assume there are 1/INTERVAL levels of satisfaction/denial 
	 */
//	public final int LEVELS = 4;
	public final int LEVELS = 10;
	public final float INTERVAL = 1f/LEVELS;
	
	public openome_modelPackage e = null;
	public openome_modelFactory f = null;
	public Resource resource;
	public int max_rank;

	public QueryVariability2() {
		e = openome_modelPackage.eINSTANCE;
		f = e.getopenome_modelFactory();		
		current_rank = 0;
		max_rank = -1;
	}
	public QueryVariability2(Resource r, Map<String, Integer> labels, Map<String, Integer> ranks) {		
		e = openome_modelPackage.eINSTANCE;
		f = e.getopenome_modelFactory();
		current_rank = 0;
		setModel(r, labels, ranks);		
	}
	private void setModel(Resource r, Map<String, Integer> labels, Map<String, Integer> ranks) {
		resource = r;
		FS_goals = new HashSet<Intention>();
		FD_goals = new HashSet<Intention>();
		PS_goals = new HashSet<Intention>();
		PD_goals = new HashSet<Intention>();
		CF_goals = new HashSet<Intention>();
		UN_goals = new HashSet<Intention>();
		VAR_goals = new HashSet<Intention>();
		configurations = new Hashtable<Intention, HashSet<Intention> >();  
		
		goal_ids = new Hashtable<Intention, Integer>();
		Intentions = new HashSet<Intention>();
		Contributions = new HashSet<Contribution>();
		Decompositions= new HashSet<Decomposition>();
		init(resource, labels, ranks);		
	}
	HashSet<Intention> FS_goals = null;
	HashSet<Intention> FD_goals = null;
	HashSet<Intention> PS_goals = null;
	HashSet<Intention> PD_goals = null;
	HashSet<Intention> CF_goals = null;
	HashSet<Intention> UN_goals = null;
	HashSet<Intention> VAR_goals = null;
	Hashtable<Intention, HashSet<Intention>> configurations = null;  
	
	Hashtable<Intention, Integer> goal_ids = null;
	HashSet<Intention> Intentions = null;
	HashSet<Contribution> Contributions = null;
	HashSet<Decomposition> Decompositions= null;

	private void collect_goals(Model m) {
		EList<Intention> l = m.getIntentions();
		Contributions.addAll(m.getContributions());
		Decompositions.addAll(m.getDecompositions());
		for (int j =0; j < l.size(); j++) {
			Intention root = (Intention) l.get(j);
			collect_goals(root);
		}	    		
	}

	public HashSet< HashMap <Intention, HashSet<Intention> > > reports
	 = new HashSet< HashMap <Intention, HashSet<Intention> > >();
	
	public int size = 0;
	public void init(Model m, Map<String, Integer> labels, Map<String, Integer> ranks) {
		max_rank = -1;
		for (Integer r: ranks.values()) {
			if (max_rank == -1 || max_rank < r.intValue())
				max_rank = r.intValue();
		}
		Intentions = new HashSet<Intention>();
		goal_ids = new Hashtable<Intention, Integer>();
		collect_goals(m);
		size = 0;
		for (Intention i: Intentions) {
			if (i instanceof Softgoal) {
				size++;
			}
		}
		StringBuffer graphStr = encode(labels, ranks);
		ISolver solver = SolverFactory.newDefault();
		solver.setTimeout(3600); // 1 hour timeout
		ModelIterator mi = new ModelIterator(solver);
		Reader reader = new InstanceReader(mi);

		PrintWriter p;
		try {
			p = new PrintWriter(new FileOutputStream(Computing.getTempFilename("file.txt")), true);
			String gs = graphStr.toString(); 
			if (gs.endsWith("\n")) {
				gs = gs.substring(0, gs.length()-1);
			}
			p.println(gs);			
			IProblem problem = reader.parseInstance(Computing.getTempFilename("file.txt"));
			boolean unsat = true;
			while (solver.isSatisfiable()) {
				unsat = false;
				int [] model = problem.model();
				String result = reader.decode(solver.model());
				decode(result);
				configuring_variability_goals();
				HashMap <Intention, HashSet<Intention> > r = report();
				if (r.size()>0)
					reports.add(r);
			}
			if (!unsat) {
				System.out.println("satisfiable: there are " + reports.size() + " solutions." );
//				for (HashMap <Intention, HashSet<Intention> > r: reports) {
//					System.out.println(r);
//				}
			} else if (unsat && max_rank>0 && current_rank < max_rank){
				current_rank++;
				System.out.println("unsatisfiable, ignore the goals with the rank: " + current_rank);
				init(m, labels, ranks);
			} else {
				System.out.println("unsatisfiable, even after ignoring all softgoals!");				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public void init(Resource resource, Map<String, Integer> labels, Map<String, Integer> ranks) {
		for (Resource o: resource.getResourceSet().getResources()){ 
	    	if (o instanceof Intention) {
	    		Intention p = (Intention) o;
	    		collect_goals(p);
	    	} else if (o instanceof Container) {
	    		Container a = (Container) o;
    			EList<Intention> l = a.getIntentions();
	    		for (int j =0; j < l.size(); j++) {
	    			Intention root = (Intention) l.get(j);
	    			collect_goals(root);
	    		}	    		
	    	} else if (o instanceof Resource){
	    		Model m = (Model) o.getContents().get(0);
	    		init(m, labels, ranks);
	    	}
		}
	}
	
	private HashMap<Intention, HashSet<Intention>> report() {
		HashMap<Intention, HashSet<Intention>> r = new 
		 HashMap<Intention, HashSet<Intention>>();
		for (Enumeration<Intention> e = configurations.keys(); e.hasMoreElements(); )
		{
			Intention g = e.nextElement();
			HashSet<Intention> config = configurations.get(g);
			if (config.size()>0) {
//				System.out.print(g.getName() + ":");				
				HashSet<Intention> selected = r.get(g);
				if (selected == null)
					selected = new HashSet<Intention> ();
				for (Intention c: config) {
					selected.add(c);
//					System.out.print(" " + c.getName());
				}
				r.put(g, selected);
//				System.out.println();
			}
		}
		return r;
	}

	private void configuring_variability_goals() {
		VAR_goals = new HashSet<Intention>();
		configurations = new Hashtable<Intention, HashSet<Intention> >();  
		for (Intention g: Intentions) {
			for (Decomposition d: Decompositions) {
				if (d instanceof OrDecomposition && g == d.getTarget()) {					
					VAR_goals.add(g);
					HashSet<Intention> subgoals = configurations.get(g);
					if (subgoals == null)
						subgoals = new HashSet<Intention>();
					Intention s = d.getSource();
					if (FS_goals.contains(s)|| PS_goals.contains(s)) {
						subgoals.add(s);
					}
					configurations.put(g, subgoals);
				}
			}
		}
	}
	
	private void collect_goals(Intention root) {
		add_a_goal(root); 
		EList<Decomposition> subgoals = root.getDecompositions();
		for (int j=0; j < subgoals.size(); j++) {
			Intention s = ((Decomposition) subgoals.get(j)).getTarget();
			collect_goals(s);
		}
	}

	private void add_a_goal(Intention root) {
		if (goal_ids.get(root) == null) {
			goal_ids.put(root, Intentions.size());
			Intentions.add(root);
			if (root.getQualitativeReasoningCombinedLabel() == EvaluationLabel.SATISFIED ) {
				FS_goals.add(root);
			} else if (root.getQualitativeReasoningCombinedLabel() == EvaluationLabel.DENIED ) {
				FD_goals.add(root);
			} else if (root.getQualitativeReasoningCombinedLabel() == EvaluationLabel.PARTIALLY_SATISFIED ) {
				PS_goals.add(root);
			} else if (root.getQualitativeReasoningCombinedLabel() == EvaluationLabel.PARTIALLY_DENIED ) {
				PD_goals.add(root);
			} else if (root.getQualitativeReasoningCombinedLabel() == EvaluationLabel.CONFLICT ) {
				CF_goals.add(root);
			} else {
				UN_goals.add(root);
			}
		}
	}
	private boolean is_or(Decomposition to) {
		return to instanceof OrDecomposition;
	}
	private boolean is_runtime_or(Decomposition to) {
		return false;
	}
	private Vector<Intention> getDecompositions(Decomposition to) {
		Vector<Intention> children = new Vector<Intention>();
		return children;
	}
	public static void main(String[] args) {
		QueryVariability2 q;
		Map<String, Integer> labels;
		Map<String, Integer> ranks;

		q = new QueryVariability2();
		labels = new HashMap<String, Integer>();
		ranks = new HashMap<String, Integer>();
// This trade-off is in favour of b1
//		labels.put("d1", new Integer(10));
//		labels.put("d2", new Integer(5));
// This trade-off is b2
//		labels.put("d1", new Integer(0));
//		labels.put("d2", new Integer(10));
// This trade-off has two solutions, b1 or b2
//		labels.put("d1", new Integer(10));
//		labels.put("d2", new Integer(10));
//		ranks.put("d1", new Integer(1));
//		ranks.put("d2", new Integer(1));
// even if the two softgoals have the same expectations
		labels.put("d1", new Integer(10));
		labels.put("d2", new Integer(10));
// let's say d1 is preferred over d2. In this case it is b1		
		ranks.put("d1", new Integer(7));
		ranks.put("d2", new Integer(3));
// change the ranking preference, in this case it is b2
//		ranks.put("d1", new Integer(1));
//		ranks.put("d2", new Integer(2));
		q.setModel("test/ex3.oom", labels, ranks);
		q.query("b");		

// More test cases need to be constructed
		q = new QueryVariability2();
		labels = new HashMap<String, Integer>();
		ranks = new HashMap<String, Integer>();
  		q.setModel("test/ex1.oom", labels, ranks);
  		q.query("a");
  		q.query("b");

		q = new QueryVariability2();
		labels = new HashMap<String, Integer>();
		ranks = new HashMap<String, Integer>();
  		q.setModel("test/ex2.oom", labels, ranks);
  		q.query("c");

		q = new QueryVariability2();
		labels = new HashMap<String, Integer>();
		ranks = new HashMap<String, Integer>();
		labels.put("f", new Integer(10));
		labels.put("g", new Integer(10));
		ranks.put("f", new Integer(2));
		ranks.put("g", new Integer(1));
		q.setModel("test/example.oom", labels, ranks);
		q.query("c");		

		q = new QueryVariability2();
		labels = new HashMap<String, Integer>();
		ranks = new HashMap<String, Integer>();
		labels.put("Customer Satisfaction", new Integer(8));
		labels.put("Minimize Risk", new Integer(10));
		ranks.put("Customer Satisfaction", new Integer(2));
		ranks.put("Minimize Risk", new Integer(1));
  		q.setModel("test/bpm07.oom", labels, ranks);
  		// System.out.println(q.query("Apply Process To Customer"));
  		System.out.println(q.query("Process Customer Order"));
	}
	/**
	 * 
	 * @param name -- the name of an OR-Intention
	 * @return -- the alternative subgoal selected to fulfil the OR-Intention
	 */
	public String query(String name) {
		System.out.println(name + ": "); 
		for (HashMap<Intention, HashSet<Intention>> r: reports) {
			System.out.println(answer(r, name));
		}
		return "";
	}
	public void setModel(String name, 
			Map<String, Integer> labels, Map<String, Integer> ranks) {
		GoalModelReader rdr = new GoalModelReader();
		Resource r = rdr.read(name);
		setModel(r, labels, ranks);
	}

	private String answer(HashMap<Intention, HashSet<Intention>> report, String name) {
		String found = "";
		for (Enumeration<Intention> e = configurations.keys(); e.hasMoreElements(); )
		{
			Intention g = e.nextElement();
			if (!g.getName().equalsIgnoreCase(name))
				continue;
			HashSet<Intention> config = report.get(g);
			if (config != null && config.size()>0) {
				for (Intention c: config) {
					found = found + " " + c.getName();
				}
			}
		}
		if (found.equals(""))
			return "not found";
		return found;
	}
	
	int PropositionID(int g, float l) {
		return (int) (LEVELS * (g + l) + 1);
	}

	int FS(Intention p) {		
		return PS(p, 0.5f);
	}
	int FD(Intention p) {
		return PD(p, 0.5f);
	}
	/**
	 * return 0 if no such literal for hard goal
	 */
	int PD(Intention p, float f) {
		if (p instanceof Softgoal)
			return PropositionID(goal_ids.get(p).intValue(), 1.0f - f);
		if (f==0.5f) // FS are allowd for hard goals
			return PropositionID(goal_ids.get(p).intValue(), 1.0f - f);
		return 0;
	}

	/**
	 * return 0 if no such literal for hard goal
	 * 
	 * @param p
	 * @return
	 */
	int PS(Intention p, float f) {
		if (p instanceof Softgoal)
			return PropositionID(goal_ids.get(p).intValue(), 0.5f - f);
		if (f==0.5f) // FD are allowd for hard goals
			return PropositionID(goal_ids.get(p).intValue(), 0.5f - f);
		return 0;
	}

	int numClauses = 0;

	/**
	 * Create the suitabe input in the DiMacs format that SAT4J solver can read
	 * 
	 * A hard goal is encoded into FS for true, and FD for false A softgoal is
	 * encoded into FS, PS, PD, FD
	 * 
	 * Here the goal model is converted in 5 steps: 1. The rules to avoid
	 * conflicts: FS or PS => not PD and not FD 2. The axioms of the label
	 * lattice, i.e., FS => PS, FD => PD 3. The correlation rules, i.e., HELP,
	 * MAKE, BREAK, HURT 4. The AND/OR rules 5. The facts, i.e., the existing
	 * labels of the goals
	 * 
	 * @return a string in the DiMacs format
	 */
	private StringBuffer encode(Map<String, Integer> labels, Map<String, Integer> ranks) {
		numClauses = 0;
		StringBuffer graphStr = new StringBuffer();
		StringBuffer step1 = new StringBuffer();
		StringBuffer step2 = new StringBuffer();
		StringBuffer step3 = new StringBuffer();
		StringBuffer step4 = new StringBuffer();
		StringBuffer step5 = new StringBuffer();
		int numVariables = 0;
		for (Intention p: Intentions) {
			step1.append(encode_1(p));
			step2.append(encode_2(p));
			step3.append(encode_3(p));
			step4.append(encode_4(p));
//			step5.append(encode_5(p));			
			step5.append(encode_6(p, labels, ranks));			
			// Yijun: to make sure enough literals are created
			numVariables = Math.max(numVariables, 4 * goal_ids.get(p).intValue() + 4);
		}
		graphStr.append(step1.toString());
		graphStr.append(step2.toString());
		graphStr.append(step3.toString());
		graphStr.append(step4.toString());
		graphStr.append(step5.toString());
		System.gc();
		StringBuffer gStr = new StringBuffer();
		gStr.append("p cnf ");
		gStr.append(numVariables);
		gStr.append(" ");
		numClauses = check_clause_count(graphStr);
		gStr.append(numClauses);
		gStr.append("\n");
		gStr.append(graphStr);
		return gStr;
	}

	/**
	 * @param graphStr
	 */
	private int check_clause_count(StringBuffer graphStr) {
		String a[] = graphStr.toString().split("\n");
		return a.length;
	}

	private String implies(int i, int j) {
		if (i == 0 || j == 0) // ignore these rules
			return "";
		numClauses++;
		return (i > 0 ? "-" + i : "" + (-i)) + " " + j + " 0\n";		
	}

	private String implies(int i, String j) {
		if (i == 0) // ignore these rules
			return "";
		numClauses++;
		return (i > 0 ? "-" + i : "" + (-i)) + " " + j + " 0\n";
	}

	/**
	 * encode mutual exclusions to avoid conflicts
	 * 
	 * @param p
	 * @return
	 */
	private String encode_1(Intention p) {
//		System.out.println(p.getName());
		StringBuffer b = new StringBuffer();
		// if (System.getProperty("Avoid Conflicts") != null || System.getProperty("Avoid Conflicts Strictly") != null)
		for (int i=1; i<= LEVELS/2; i++) {
			b.append(implies(PS(p, (float) i/LEVELS), -PD(p,(float) i/LEVELS)));
			if (i>1) {
				b.append(implies(PS(p, (float) i/LEVELS), PS(p,(float) (i-1)/LEVELS)));
				b.append(implies(PD(p, (float) i/LEVELS), PD(p,(float) (i-1)/LEVELS)));
			}
		}
//		System.out.println(b);
		return b.toString();
	}

	/**
	 * encode axiom FS => PS, FD => PD
	 */
	private String encode_2(Intention p) {
		StringBuffer b = new StringBuffer();
		for (int i=2; i<= LEVELS/2; i++) {
			b.append(implies(PS(p, (float) i/LEVELS), 
				 PS(p,(float) (i-1)/LEVELS)));
			b.append(implies(PD(p, (float) i/LEVELS), 
				 PD(p,(float) (i-1)/LEVELS)));
		}
		return b.toString();
	}

	/**
	 * encode correlation links
	 * 
	 * @param p
	 * @return
	 */
	private String encode_3(Intention p) {
//		System.out.println(p.getName());
		StringBuffer b = new StringBuffer();
		for (Contribution l: Contributions) {
			Intention from = l.getSource(), to = l.getTarget();
			if (goal_ids.get(from).intValue() == goal_ids.get(p).intValue()) {
				if (l instanceof MakeContribution ) { // make
					// System.out.println(p.getName() + " -(make)-> " + to.getName());
					for (float i=1; i<=(float)LEVELS/2; i++) {
						b.append(implies(PS(p, i/LEVELS), PS(to, i/LEVELS)));						
						b.append(implies(PS(to, i/LEVELS), PS(p, i/LEVELS)));		
						// if (System.getProperty("Balanced contributions") != null) {
						b.append(implies(PD(p, i/LEVELS), PD(to, i/LEVELS)));						
						b.append(implies(PD(to, i/LEVELS), PD(p, i/LEVELS)));					
						// }
					}
				} else if (l instanceof BreakContribution) { // break
					// System.out.println(p.getName() + " -(break)-> " + to.getName());
					for (float i=1; i<=(float)LEVELS/2; i++) {
						b.append(implies(PS(p, i/LEVELS), -PS(to, i/LEVELS)));						
						b.append(implies(PS(to, i/LEVELS), -PS(p, i/LEVELS)));
						// if (System.getProperty("Balanced contributions") != null) {
						b.append(implies(PD(p, i/LEVELS), -PD(to, i/LEVELS)));						
						b.append(implies(PD(to, i/LEVELS), -PD(p, i/LEVELS)));
						// }
					}
				} else if (l instanceof HelpContribution) { // help
					for (float i=1; i<=(float)LEVELS/2; i++) {
						for (float j=1; j<i; j++) {
							b.append(implies(PS(p, i/LEVELS), PS(to, j/LEVELS)));
//							if (System.getProperty("Balanced contributions") != null) {
								b.append(implies(PD(p, i/LEVELS), PD(to, j/LEVELS)));
//							}
						}
						String helpS = "";
						String helpD = "";
						for (float j=i; j<=LEVELS/2; j++) {
							helpD += " " + PD(p, j/LEVELS);
//							if (System.getProperty("Balanced contributions") != null) {
								helpS += " " + PS(p, j/LEVELS);
//							}
						}
						if (p instanceof Softgoal) {
							b.append(implies(PD(to, i/LEVELS), helpD));
//							if (System.getProperty("Balanced contributions") != null) {
								b.append(implies(PS(to, i/LEVELS), helpS));
//							}
						}
					}
				} else if (l instanceof HurtContribution) { // hurt
					for (float i=1; i<=LEVELS/2; i++) {
						for (float j=1; j<i; j++) {
							b.append(implies(PS(p, i/LEVELS), PD(to, j/LEVELS)));
//							if (System.getProperty("Balanced contributions") != null) {
								b.append(implies(PD(p, i/LEVELS), PS(to, j/LEVELS)));
//							}
						}
						String helpS = "";
						String helpD = "";
						for (float j=i; j<=LEVELS/2; j++) {
							helpD += " " + PD(p, j/LEVELS);
//							if (System.getProperty("Balanced contributions") != null) {
								helpS += " " + PS(p, j/LEVELS);
//							}
						}
						if (p instanceof Softgoal) {
							b.append(implies(PS(to, i/LEVELS), helpD));
//							if (System.getProperty("Balanced contributions") != null) {
								b.append(implies(PD(to, i/LEVELS), helpS));
//							}
						}
					}
				}
			}
		}
		//System.out.println(b);
		return b.toString();
	}

	/**
	 * encode AND/OR decompositions
	 * 
	 * Exception: when all OR-decomposed children do not contribute to any softgoal, 
	 * they are regarded as AND-decomposed children for the reasoning.
	 * This exception can be turned on through a property 
	 *     "ome.reasoning.topdown.runtime_or".
	 *  
	 * @param p
	 * @return
	 */
	private String encode_4(Intention p) {
//		System.out.println(p.getName());
		StringBuffer b = new StringBuffer();
		StringBuffer step4i = new StringBuffer();
		boolean is_and = false;
		boolean is_or = false;
		StringBuffer step4iii = new StringBuffer();
		StringBuffer step4iv = new StringBuffer();
		StringBuffer step4v = new StringBuffer();
		StringBuffer step4vi = new StringBuffer();
		for (Decomposition l: Decompositions) {
			Intention from = l.getSource(), to = l.getTarget();
			if (goal_ids.get(to).intValue() == goal_ids.get(p).intValue()) {
				// contributions				
				if (! (is_or(l) || is_runtime_or(l))) {
					if (p instanceof Softgoal) {
						for (float i=1; i<LEVELS/2.0f; i++)
							step4i.append(implies(PS(p, i/LEVELS), PS(from, i/LEVELS)));
					}
					step4i.append(implies(FS(p), FS(from)));
//					System.out.println(p.getName() + "->" + from.getName());
//					if (System.getProperty("Balanced contributions") != null) {
						step4i.append(implies(FD(from), FD(p)));
//					}
					step4iii.append(-FS(from) + " ");
//					if (System.getProperty("Balanced contributions") != null) {
						step4vi.append(FD(from) + " ");
//					}
					if (p instanceof Softgoal) {
						for (float i=1; i<LEVELS/2; i++) {
//							if (System.getProperty("Balanced contributions") != null) {
								step4i.append(implies(PD(from, i/LEVELS), PD(p, i/LEVELS)));
//							}
							step4iv.append(" " + (-PS(from, i/LEVELS)));
							step4v.append(" "+PD(from, i/LEVELS));
						}
					}
					is_and = true;
				} else {
					step4i.append(implies(FS(from), FS(p)));
//					System.out.println(from.getName() + "->" + p.getName());
					if (p instanceof Softgoal) {
						for (float i=1; i<LEVELS/2; i++)
							step4i.append(implies(PS(from, i/LEVELS), PS(p, i/LEVELS)));
					}
//					if (System.getProperty("Balanced contributions") != null) {
						step4i.append(implies(FD(p), FD(from)));
//					}
					step4v.append(" " + FS(from));
					if (p instanceof Softgoal && from instanceof Softgoal) {
						for (float i=1; i<LEVELS/2; i++) {
//							if (System.getProperty("Balanced contributions") != null) {
								step4i.append(implies(PD(p, i/LEVELS), PD(from, i/LEVELS)));
//							}
							step4iii.append(" " + (-PD(from, i/LEVELS)));
							step4vi.append(PS(from, i/LEVELS) + " ");
						}
					}
					if (p instanceof Softgoal)
						step4iv.append(" " + (-FD(from)));
					is_or = true;
				}
			}
		}
		if (is_and && is_or) {
			System.out.println("warning: the model has both AND and OR decompositions");
			return "";
		}
		if (is_and) {
			step4iii.append(" " + FS(p) + " 0\n");
			numClauses++;
			if (p instanceof Softgoal) {
				for (float i=1; i<LEVELS/2; i++) {
					step4iv.append(" " + PS(p, i/LEVELS) + " 0\n");
					numClauses++;
					step4v.append(" " + (-PD(p, i/LEVELS)) + " 0\n");
					numClauses ++;
				}
				step4vi.append(" " + (-FD(p)) + " 0\n");
				numClauses ++;
			}
		} else if (is_or) {
			step4v.append(" " + -FS(p) + " 0\n");
			numClauses++;
			if (p instanceof Softgoal) {
				for (float i=1; i<LEVELS/2.0f; i++) {
					step4vi.append("" + (-PS(p, i/LEVELS)) + " 0\n");
					numClauses++;
					step4iii.append(PD(p, i/LEVELS) + " 0\n");
					numClauses ++;
				}
				step4iv.append(" " + FD(p) + " 0\n");
				numClauses ++;
			}
		}
		b.append(step4i.toString());
		b.append(step4iii.toString());
		b.append(step4iv.toString());
		b.append(step4v.toString());
		b.append(step4vi.toString());
//		System.out.println(step4v);
		return b.toString();
	}

	
	
	private String encode_5(Intention p) {
//		System.out.println(p.getName());
		StringBuffer b = new StringBuffer();
		if (p instanceof Softgoal) {
			if (p.getQualitativeReasoningCombinedLabel() == EvaluationLabel.SATISFIED) { // FS
				b.append(FS(p) + " " + "0\n");
				numClauses++;
			} else if (p.getQualitativeReasoningCombinedLabel() == EvaluationLabel.DENIED) { // FD
				b.append(FD(p) + " " + "0\n");
				numClauses++;
			} else if (p.getQualitativeReasoningCombinedLabel() == EvaluationLabel.CONFLICT) { // CF
				b.append(FS(p) + " 0\n");
				b.append(FD(p) + " 0\n");
				numClauses += 2;
			} else if (p.getQualitativeReasoningCombinedLabel() == EvaluationLabel.PARTIALLY_SATISFIED) { // PS
				for (float i=1; i<LEVELS/2; i++) {
					b.append(PS(p, i/LEVELS) + " " + "0\n");
					numClauses ++;
				}
				b.append(-FS(p) + " " + "0\n");
				numClauses += 2;
			} else if (p.getQualitativeReasoningCombinedLabel() == EvaluationLabel.PARTIALLY_DENIED) { // PD
				for (float i=1; i<LEVELS/2; i++) {
					b.append(PD(p, i/LEVELS) + " " + "0\n");
					numClauses ++;
				}
				b.append(-FD(p) + " " + "0\n");
				numClauses ++;
			} else { // either unknown or conflict
				// nothing
			}
		} else {
			if (p.getQualitativeReasoningCombinedLabel() == EvaluationLabel.SATISFIED) {
				b.append(FS(p) + " " + "0\n");
				numClauses++;
			} else if (p.getQualitativeReasoningCombinedLabel() == EvaluationLabel.DENIED) {
				b.append(FD(p) + " " + "0\n");
				numClauses++;
			} else if (p.getQualitativeReasoningCombinedLabel() == EvaluationLabel.CONFLICT) {
				b.append(FS(p) + " " + "0\n");
				b.append(FD(p) + " " + "0\n");
				numClauses+=2;
			}
		}
//		System.out.println(b);
		return b.toString();
	}

	public int current_rank;
	public String encode_6(Intention p, 
			Map<String, Integer> labels,
			Map<String, Integer> ranks) {
		// System.out.println(current_rank);
		StringBuffer b = new StringBuffer();
		if (! (p instanceof Softgoal)) {
			if (p.getQualitativeReasoningCombinedLabel() == EvaluationLabel.SATISFIED) {
				b.append(FS(p) + " " + "0\n");
				numClauses++;
			} else if (p.getQualitativeReasoningCombinedLabel() == EvaluationLabel.DENIED) {
				b.append(FD(p) + " " + "0\n");
				numClauses++;
			} else if (p.getQualitativeReasoningCombinedLabel() == EvaluationLabel.CONFLICT) {
				b.append(FS(p) + " " + "0\n");
				b.append(FD(p) + " " + "0\n");
				numClauses+=2;
			}
		} else {
			for (String key: labels.keySet()) {
				if (p.getName().equals(key) 
						&& current_rank < ranks.get(key).intValue()) {
					System.out.println(p.getName());
					for (float i=1; i<= LEVELS; i++) {
						if (i == (float) labels.get(key).intValue())
							b.append(PS(p, i/LEVELS) + " " + "0\n");
					}
					numClauses ++;
				}
			}
		}
		// System.out.println(b);
		return b.toString();
	}
	
	
	private void decode(String result) {
		FS_goals = new HashSet<Intention>();
		FD_goals = new HashSet<Intention>();
		PS_goals = new HashSet<Intention>();
		PD_goals = new HashSet<Intention>();
		CF_goals = new HashSet<Intention>();
		UN_goals = new HashSet<Intention>();		
//		System.out.println(result);
		List values = Arrays.asList(result.split(" "));
		for (Intention p: Intentions) {
			for (float i=1; i<LEVELS/2; i++)
				if (p instanceof Softgoal && values.contains(String.valueOf(PS(p, i/LEVELS)))) 
				{
//					System.out.println(p.getName() + " +" + i);
					p.setQualitativeReasoningCombinedLabel(EvaluationLabel.PARTIALLY_SATISFIED);
					PS_goals.add(p);
				}
			for (float i=1; i<LEVELS/2; i++)
				if (p instanceof Softgoal && values.contains(String.valueOf(PD(p, i/LEVELS))))
				{
//					System.out.println(p.getName() + " -" + i);
					p.setQualitativeReasoningCombinedLabel(EvaluationLabel.PARTIALLY_DENIED);
					PD_goals.add(p);
				}
			if (values.contains(String.valueOf(FS(p))))
			{
				p.setQualitativeReasoningCombinedLabel(EvaluationLabel.SATISFIED);
				FS_goals.add(p);
			}
			if (values.contains(String.valueOf(FD(p))))
			{
				p.setQualitativeReasoningCombinedLabel(EvaluationLabel.DENIED);
				FD_goals.add(p);
			}				
		}
/*
		for (Intention i: FS_goals) {
			System.out.println(i.getName());
		}
*/
	}	
}

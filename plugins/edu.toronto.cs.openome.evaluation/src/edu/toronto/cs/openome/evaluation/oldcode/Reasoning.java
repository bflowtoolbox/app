package edu.toronto.cs.openome.evaluation.oldcode;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.reader.DimacsReader;
import org.sat4j.reader.ParseFormatException;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;

import util.Computing;

import edu.toronto.cs.openome_model.*;


/**
 * <!-- begin-user-doc -->
 * Of course there are no comments here either.
 * 
 * I believe this is the code used to implement the Tropos style of reasoning.  It sort of worked for a while on 
 * goal models, but didn't work completely.
 * <!-- end-user-doc -->
 */
public class Reasoning {

	public Openome_modelPackage e = null;
	public Openome_modelFactory f = null;
	public Resource resource;

	public Reasoning(Resource r) {
		e = Openome_modelPackage.eINSTANCE;
		f = e.getOpenome_modelFactory();
		resource = r;
	}

	Vector<Intention> fullySatisfied = new Vector<Intention>();
	Vector<Intention> fullyDenied = new Vector<Intention>();
	Vector<Intention> partiallySatisifed = new Vector<Intention>();
	Vector<Intention> partiallyDenied = new Vector<Intention>();
	Vector<Intention> conflicted = new Vector<Intention>();
	Vector<Intention> unknown = new Vector<Intention>();
	Vector<Intention> VAR_goals = new Vector<Intention>();
	Hashtable<Intention, HashSet<Intention>> configurations = new Hashtable<Intention, HashSet<Intention>>();

	public boolean reasoning() {
		boolean satisfied = false;
//ISolver line didn't compile with new Eclipse.  I've commented it out.  This code isn't used at the moment anyway 
		
		
//		StringBuffer graphStr = encode();
//		ISolver solver = SolverFactory.newMiniLearning();
//		solver.setTimeout(3600); // 1 hour timeout
//		DimacsReader reader = new DimacsReader(solver);
//		PrintWriter out;
//		try {
//			String filename = "file.txt";
//			out = new PrintWriter(new FileOutputStream(filename), true);
//			out.println(graphStr.toString());
//			// Debug.DEBUG_LOADER = true;
//			reader.parseInstance(filename);
//			// Debug.DEBUG_LOADER = false;
//			if (solver.isSatisfiable()) {
//				System.out.println("satisfiable");
//				String result = reader.decode(solver.model());
//				decode(result);
//				satisfied = true;
//			} else
//				satisfied = false;
//			configuring_variability_goals();
//			update();
//			report();
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		} catch (ParseFormatException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ContradictionException e) {
//			e.printStackTrace();
//		} catch (TimeoutException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return satisfied;
	}

	private void update() {
		for (Intention root : Intentions) {
			if (fullySatisfied.contains(root)) {
				root
						.setQualitativeReasoningCombinedLabel(EvaluationLabel.SATISFIED);
			} else if (fullyDenied.contains(root)) {
				root
						.setQualitativeReasoningCombinedLabel(EvaluationLabel.DENIED);
			} else if (partiallySatisifed.contains(root)) {
				root
						.setQualitativeReasoningCombinedLabel(EvaluationLabel.PARTIALLY_SATISFIED);
			} else if (partiallyDenied.contains(root)) {
				root
						.setQualitativeReasoningCombinedLabel(EvaluationLabel.PARTIALLY_DENIED);
			} else if (conflicted.contains(root)) {
				root
						.setQualitativeReasoningCombinedLabel(EvaluationLabel.CONFLICT);
			} else {
				root
						.setQualitativeReasoningCombinedLabel(EvaluationLabel.UNKNOWN);
			}
		}
	}

	int numClauses = 0;
	Hashtable<Intention, Integer> goal_ids = new Hashtable<Intention, Integer>();
	Vector<Intention> Intentions = new Vector<Intention>();

	/**
	 * Create the suitable input in the DiMacs format that SAT4J solver can read
	 * 
	 * A hard Intention is encoded into FS for true, and FD for false A softgoal
	 * is encoded into FS, PS, PD, FD
	 * 
	 * Here the Intention model is converted in 5 steps: 1. The rules to avoid
	 * conflicts: FS or PS => not PD and not FD 2. The axioms of the label
	 * lattice, i.e., FS => PS, FD => FD 3. The correlation rules, i.e., HELP,
	 * MAKE, BREAK, HURT 4. The AND/OR rules 5. The facts, i.e., the existing
	 * labels of the Intentions
	 * 
	 * @return a string in the DiMacs format
	 */
	private StringBuffer encode() {
		numClauses = 0;
		StringBuffer graphStr = new StringBuffer();
		StringBuffer step1 = new StringBuffer();
		StringBuffer step2 = new StringBuffer();
		StringBuffer step3 = new StringBuffer();
		StringBuffer step4 = new StringBuffer();
		StringBuffer step5 = new StringBuffer();
		int numVariables = 0;
		collect_goals();
		System.out.println("collected Intentions: n = " + Intentions.size());
		for (Intention p : Intentions) {
			step1.append(encodeMutualExclusions(p));
			step2.append(encodeAxiom(p));
			step3.append(encodeContributionLinks(p));
			step4.append(encodeDecompositions(p));
			// Yijun: to make sure enough literals are created
		}
		numVariables = Math.max(numVariables, 4 * Intentions.size() + 4);
		step5.append(encode_5());
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

	private void collect_goals() {
		init();
		for (Object o : resource.getResourceSet().getResources()) {
			if (o instanceof Intention) {
				Intention p = (Intention) o;
				collect_goals(p);
			} else if (o instanceof Container) {
				Container a = (Container) o;
				EList<Intention> l = a.getIntentions();
				for (int j = 0; j < l.size(); j++) {
					Intention root = l.get(j);
					collect_goals(root);
				}
			}
		}
		report();
	}

	private void init() {
		Intentions = new Vector<Intention>();
		goal_ids = new Hashtable<Intention, Integer>();
		fullySatisfied = new Vector<Intention>();
		fullyDenied = new Vector<Intention>();
		partiallySatisifed = new Vector<Intention>();
		partiallyDenied = new Vector<Intention>();
		conflicted = new Vector<Intention>();
		unknown = new Vector<Intention>();
	}

	private void report() {
		// System.out.println("no. Intentions = " + Intentions.size());
		// System.out.println("no. FS Intentions = " + FS_goals.size());
		// System.out.println("no. FD Intentions = " + FD_goals.size());
		// System.out.println("no. PS Intentions = " + PS_goals.size());
		// System.out.println("no. PD Intentions = " + PD_goals.size());
		// System.out.println("no. CF Intentions = " + CF_goals.size());
		// System.out.println("no. UN Intentions = " + UN_goals.size());
		for (Enumeration<Intention> e = configurations.keys(); e
				.hasMoreElements();) {
			Intention g = e.nextElement();
			HashSet<Intention> config = configurations.get(g);
			System.out.println(g.getName() + ":");
			for (Intention c : config) {
				System.out.println(" " + c.getName());
			}
			System.out.println();
		}
	}

	private void configuring_variability_goals() {
		VAR_goals = new Vector<Intention>();
		configurations = new Hashtable<Intention, HashSet<Intention>>();
		for (Intention g : Intentions) {
			if (is_or(g) && !is_runtime_or(g)) {
				VAR_goals.add(g);
				EList<Decomposition> subgoals = g.getDecompositions();
				HashSet<Intention> config = new HashSet<Intention>();
				for (int i = 0; i < subgoals.size(); i++) {
					Decomposition d = subgoals.get(i);
					Intention s = d.getTarget();
					if (fullySatisfied.contains(s)
							|| partiallySatisifed.contains(s)) {
						config.add(s);
					}
				}
				configurations.put(g, config);
			}
		}
	}

	private void collect_goals(Intention root) {
		add_a_goal(root);
		EList<Decomposition> subgoals = root.getDecompositions();
		for (int j = 0; j < subgoals.size(); j++) {
			Decomposition d = subgoals.get(j);
			Intention s = d.getTarget();
			collect_goals(s);
		}
	}

	private void add_a_goal(Intention root) {
		goal_ids.put(root, Intentions.size());
		Intentions.add(root);
		if (root.getQualitativeReasoningCombinedLabel() == EvaluationLabel.SATISFIED) {
			fullySatisfied.add(root);
		} else if (root.getQualitativeReasoningCombinedLabel() == EvaluationLabel.DENIED) {
			fullyDenied.add(root);
		} else if (root.getQualitativeReasoningCombinedLabel() == EvaluationLabel.PARTIALLY_SATISFIED) {
			partiallySatisifed.add(root);
		} else if (root.getQualitativeReasoningCombinedLabel() == EvaluationLabel.PARTIALLY_DENIED) {
			partiallyDenied.add(root);
		} else if (root.getQualitativeReasoningCombinedLabel() == EvaluationLabel.CONFLICT) {
			conflicted.add(root);
		} else {
			unknown.add(root);
		}
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
		return (i > 0 ? "-" + i : "" + (-i)) + " " + j + " 0\n";
	}

	private String implies(int i, String j) {
		if (i == 0) // ignore these rules
			return "";
		return (i > 0 ? "-" + i : "" + (-i)) + " " + j + " 0\n";
	}

	int FSID(int g) {
		return 4 * g + 1;
	}

	int PSID(int g) {
		return 4 * g + 2;
	}

	int FDID(int g) {
		return 4 * g + 3;
	}

	int PDID(int g) {
		return 4 * g + 4;
	}

	int FS(Intention intention) {
		return FSID(goal_ids.get(intention).intValue());
	}

	/**
	 * return 0 if no such literal for hard Intention
	 * 
	 * @param intention
	 * @return
	 */
	int PS(Intention intention) {
		if (is_soft_goal(intention))
			return PSID(goal_ids.get(intention).intValue());
		return 0;
	}

	/**
	 * return -FS for hard Intention
	 * 
	 * @param intention
	 * @return
	 */
	int FD(Intention intention) {
		return FDID(goal_ids.get(intention).intValue());
	}

	/**
	 * return 0 if no such literal for hard Intention
	 * 
	 * @param intention
	 * @return
	 */
	int PD(Intention intention) {
		if (is_soft_goal(intention))
			return PDID(goal_ids.get(intention).intValue());
		return 0;
	}

	/**
	 * encode mutual exclusions to avoid conflicts
	 * 
	 * @param intention
	 * @return
	 */
	private String encodeMutualExclusions(Intention intention) {
		StringBuffer buf = new StringBuffer();
		if (System.getProperty("Avoid Conflicts") != null
				|| System.getProperty("Avoid Conflicts Strictly") != null) {
			buf.append(implies(FS(intention), -FD(intention)));
			if (System.getProperty("Avoid Conflicts Strictly") != null) {
				buf.append(implies(FS(intention), -PD(intention)));
				buf.append(implies(PS(intention), -FD(intention)));
				buf.append(implies(PS(intention), -PD(intention)));
			}
		}
		return buf.toString();
	}

	/**
	 * encode axiom FS => PS, FD => PD
	 */
	private String encodeAxiom(Intention intention) {
		StringBuffer b = new StringBuffer();
		b.append(implies(FS(intention), PS(intention)));
		b.append(implies(FD(intention), PD(intention)));
		return b.toString();
	}

	/**
	 * encode correlation links
	 * 
	 * @param from
	 * @return
	 */
	private String encodeContributionLinks(Intention from) {
		StringBuffer buf = new StringBuffer();
		EList<Contribution> list = from.getContributesFrom(); // getRule now
																// getContributesFrom?
																// hope so
		for (int j = 0; j < list.size(); j++) {
			Contribution c = list.get(j);
			Intention to = c.getTarget();
			if (is_make_contribution(c)) {
				buf.append(implies(FS(from), FS(to)));
				buf.append(implies(PS(from), PS(to)));
				buf.append(implies(FS(to), FS(from)));
				buf.append(implies(PS(to), PS(from)));
				if (System.getProperty("Balanced contributions") != null) {
					buf.append(implies(FD(from), FD(to)));
					buf.append(implies(PD(from), PD(to)));
					buf.append(implies(FD(to), FD(from)));
					buf.append(implies(PD(to), PD(from)));
				}
			} else if (is_break_contribution(c)) {
				buf.append(implies(FS(from), FD(to)));
				buf.append(implies(PS(from), PD(to)));
				buf.append(implies(FS(to), FD(from)));
				buf.append(implies(PS(to), PD(from)));
				if (System.getProperty("Balanced contributions") != null) {
					buf.append(implies(PD(from), PS(to)));
					buf.append(implies(FD(from), FS(to)));
					buf.append(implies(PD(to), PS(from)));
					buf.append(implies(FD(to), FS(from)));
				}
			} else if (is_help_contribution(c)) {
				buf.append(implies(FS(from), PS(to)));
				if (is_soft_goal(from)) {
					buf.append(implies(PS(to), "" + FS(from) + " " + PS(from)));
					buf.append(implies(PS(from), PS(to)));
				}
				if (System.getProperty("Balanced contributions") != null) {
					buf.append(implies(FD(from), PD(to)));
					if (is_soft_goal(from)) {
						buf.append(implies(PD(to), "" + FD(from) + " "
								+ PD(from)));
						buf.append(implies(PD(from), PD(to)));
					}
				}
			} else if (is_hurt_contribution(c)) {
				buf.append(implies(FS(from), PD(to)));
				buf.append(implies(PS(from), PD(to)));
				if (is_soft_goal(from)) {
					buf.append(implies(PS(to), "" + FD(from) + " " + PD(from)));
				}
				if (System.getProperty("Balanced contributions") != null) {
					buf.append(implies(FD(from), PS(to)));
					buf.append(implies(PD(from), PS(to)));
					if (is_soft_goal(from)) {
						buf.append(implies(PD(to), "" + FS(from) + " "
								+ PS(from)));
					}
				}
			}
		}
		return buf.toString();
	}

	private boolean is_hurt_contribution(Contribution c) {
		return c instanceof HurtContribution;
	}

	private boolean is_make_contribution(Contribution c) {
		return c instanceof MakeContribution;
	}

	private boolean is_help_contribution(Contribution c) {
		return c instanceof HelpContribution;
	}

	private boolean is_break_contribution(Contribution c) {
		return c instanceof BreakContribution;
	}

	private boolean is_soft_goal(Intention from) {
		return from instanceof Softgoal;
	}

	/**
	 * encode AND/OR decompositions
	 * 
	 * Exception: when all OR-decomposed children do not contribute to any
	 * softgoal, they are regarded as AND-decomposed children for the reasoning.
	 * This exception can be turned on through a Property
	 * "ome.reasoning.topdown.runtime_or".
	 * 
	 * @param to
	 * @return
	 */
	private String encodeDecompositions(Intention to) {
		StringBuffer b = new StringBuffer();
		StringBuffer step4i = new StringBuffer();
		boolean is_and = false;
		boolean is_or = false;
		StringBuffer step4iii = new StringBuffer();
		StringBuffer step4iv = new StringBuffer();
		StringBuffer step4v = new StringBuffer();
		StringBuffer step4vi = new StringBuffer();
		EList<Decomposition> list = to.getDecompositions();
		for (int j = 0; j < list.size(); j++) {
			Decomposition d = list.get(j);
			Intention from = d.getTarget();
			if (is_and(to)
					|| Computing
							.propertyHolds("ome.reasoning.topdown.runtime_or")
					&& is_runtime_or(to)) {
				if (is_soft_goal(to)) {
					step4i.append(implies(PS(to), PS(from)));
				}
				step4i.append(implies(FS(to), FS(from)));
				step4iii.append(-FS(from) + " ");
				if (is_soft_goal(to)) {
					step4iv.append(-PS(from) + " ");
				}
				if (System.getProperty("Balanced contributions") != null) {
					if (is_soft_goal(to)) {
						step4i.append(implies(PD(from), PD(to)));
						step4i.append(implies(FD(from), FD(to)));
						step4v.append(PD(from) + " ");
						step4vi.append(FD(from) + " ");
					}
				}
				is_and = true;
			} else if (is_or(to)) {
				step4i.append(implies(FS(from), FS(to)));
				if (is_soft_goal(to)) {
					step4i.append(implies(PS(from), PS(to)));
				}
				step4v.append(FS(from) + " ");
				if (is_soft_goal(to) && is_soft_goal(from)) {
					step4vi.append(PS(from) + " ");
				}
				if (System.getProperty("Balanced contributions") != null) {
					if (is_soft_goal(to)) {
						step4i.append(implies(PD(to), PD(from)));
						step4i.append(implies(FD(to), FD(from)));
					}
					if (is_soft_goal(to)) {
						if (is_soft_goal(from))
							step4iii.append(-PD(from) + " ");
						step4iv.append(-FD(from) + " ");
					}
				}
				is_or = true;
			}
		}
		EList<Dependency> dlist = to.getDependencyTo();
		for (int j = 0; j < dlist.size(); j++) {
			if (dlist.get(j).getDependencyTo() instanceof Container)
				break;
			Intention from = (Intention) (dlist.get(j)).getDependencyTo(); // TODO:
																			// dangerous
																			// cast
			if (is_soft_goal(to)) {
				step4i.append(implies(PS(to), PS(from)));
			}
			step4i.append(implies(FS(to), FS(from)));
			step4iii.append(-FS(from) + " ");
			if (is_soft_goal(to) && is_soft_goal(from)) {
				step4iv.append(-PS(from) + " ");
			}
			if (System.getProperty("Balanced contributions") != null) {
				if (is_soft_goal(to)) {
					step4i.append(implies(PD(from), PD(to)));
					step4i.append(implies(FD(from), FD(to)));
					if (is_soft_goal(from))
						step4v.append(PD(from) + " ");
					step4vi.append(FD(from) + " ");
				}
			}
			is_and = true;
		}

		if (is_and) {
			step4iii.append(FS(to) + " 0\n");
			if (is_soft_goal(to)) {
				step4iv.append(PS(to) + " 0\n");
			}
			if (System.getProperty("Balanced contributions") != null) {
				if (is_soft_goal(to)) {
					step4v.append("" + -PD(to) + " 0\n");
					step4vi.append("" + -FD(to) + " 0\n");
				}
			}
		} else if (is_or) {
			step4v.append("" + -FS(to) + " 0\n");
			if (is_soft_goal(to)) {
				step4vi.append("" + -PS(to) + " 0\n");
			}
			if (System.getProperty("Balanced contributions") != null) {
				if (is_soft_goal(to)) {
					step4iii.append(PD(to) + " 0\n");
					step4iv.append(FD(to) + " 0\n");
				}
			}
		}
		b.append(step4i.toString());
		b.append(step4iii.toString());
		b.append(step4iv.toString());
		b.append(step4v.toString());
		b.append(step4vi.toString());
		return b.toString();
	}

	private boolean is_and(Intention to) {
		return to instanceof AndDecomposition;
	}

	private boolean is_or(Intention to) {
		return to instanceof OrDecomposition;
	}

	private boolean is_runtime_or(Intention to) {
		if (is_or(to))
			return false;
		if (System.getProperty("ome.reasoning.topdown.runtime_or") == null)
			return false;
		boolean isRuntimeOR = true;
		EList<Decomposition> list = to.getDecompositions();
		for (int j = 0; j < list.size(); j++) {
			Decomposition decomp = (Decomposition) list.get(j);
			Intention from = decomp.getTarget();
			EList<Contribution> contributions = from.getContributesFrom();
			if (contributions.size() > 0) {
				isRuntimeOR = false;
				break;
			}
		}
		return isRuntimeOR;
	}

	private String encode_5() {
		StringBuffer buf = new StringBuffer();
		for (Intention intention : fullySatisfied) {
			buf.append(FS(intention) + " " + "0\n");
		}
		for (Intention intention: fullyDenied) {
			buf.append(FD(intention) + " " + "0\n");
		}
		for (Intention intention: conflicted) {
			buf.append(FS(intention) + " 0\n");
			buf.append(FD(intention) + " 0\n");
		}
		for (Intention intention: partiallySatisifed) {
			buf.append(PS(intention) + " " + "0\n");
			buf.append(-FS(intention) + " " + "0\n");
		}
		for (Intention intention: partiallyDenied) {
			buf.append(PD(intention) + " " + "0\n");
			buf.append(-FD(intention) + " " + "0\n");
		}
		return buf.toString();
	}

	/**
	 * Determines which intentions are fully satisfied, fully denied, partially
	 * satisfied, partially denied, conflicted or unknown.
	 * 
	 * @param result
	 */
	private void decode(String result) {
		List<String> values = Arrays.asList(result.split(" "));
		fullySatisfied = new Vector<Intention>();
		fullyDenied = new Vector<Intention>();
		partiallySatisifed = new Vector<Intention>();
		partiallyDenied = new Vector<Intention>();
		conflicted = new Vector<Intention>();
		unknown = new Vector<Intention>();
		float satisfied = 0, denied = 0;
		for (Intention intention : Intentions) {
			satisfied = 0;
			denied = 0;
			if (is_soft_goal(intention)
					&& values.contains(String.valueOf(PS(intention))))
				satisfied = 0.5f;
			if (is_soft_goal(intention)
					&& values.contains(String.valueOf(PD(intention))))
				denied = 0.5f;
			if (values.contains(String.valueOf(FS(intention))))
				satisfied = 1;
			if (values.contains(String.valueOf(FD(intention))))
				denied = 1;
			if (satisfied == 1 && denied == 0) {
				fullySatisfied.add(intention);
			} else if (satisfied == 0 && denied == 1) {
				fullyDenied.add(intention);
			} else if (satisfied > denied) {
				partiallySatisifed.add(intention);
			} else if (denied > satisfied) {
				partiallyDenied.add(intention);
			} else if (satisfied == denied && satisfied >= 0.5) {
				conflicted.add(intention);
			} else if (satisfied == denied && satisfied < 0.5) {
				unknown.add(intention);
			}
		}
	}
}

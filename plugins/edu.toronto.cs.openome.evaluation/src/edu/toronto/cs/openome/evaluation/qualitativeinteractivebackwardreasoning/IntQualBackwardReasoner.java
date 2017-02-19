package edu.toronto.cs.openome.evaluation.qualitativeinteractivebackwardreasoning;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;
import java.util.Vector;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.sat4j.core.VecInt;

import edu.toronto.cs.openome.evaluation.SATSolver.Dimacs;
import edu.toronto.cs.openome.evaluation.SATSolver.ModeltoAxiomsConverter;
import edu.toronto.cs.openome.evaluation.SATSolver.zChaffSolver;
import edu.toronto.cs.openome.evaluation.SATSolver.zMinimalSolver;
import edu.toronto.cs.openome.evaluation.commands.BackwardHJWindowCommand;
import edu.toronto.cs.openome.evaluation.commands.ClearLabelBagCommand;
import edu.toronto.cs.openome.evaluation.commands.ForwardHJWindowCommand;
import edu.toronto.cs.openome.evaluation.commands.HighlightIntentionOutlinesCommand;
import edu.toronto.cs.openome.evaluation.commands.SetInitialEvaluationLabelCommand;
import edu.toronto.cs.openome.evaluation.reasoning.Reasoner;
import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.HumanJudgment;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.LabelBag;
import edu.toronto.cs.openome_model.diagram.edit.commands.HighlightIntentionsCommand;
import edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart;

import edu.toronto.cs.openome_model.impl.HumanJudgmentImpl;
import edu.toronto.cs.openome_model.impl.LabelBagImpl;
import edu.toronto.cs.openome_model.impl.ModelImpl;

public class IntQualBackwardReasoner extends Reasoner {
	//Some extra info needs to be stored with softgoals, so we have a wrapper class, and we have another class
	//which stores a list of these wrappers, this is that class
	//private Vector<Intention> softgoalWrappers;
	private HashMap <Intention, Integer> minDistances;
	private Dimacs cnf;
	private Dimacs cnfBack;
	zChaffSolver solver;
	zMinimalSolver minSolver;
	ModeltoAxiomsConverter converter;
	Stack<HashMap<Intention, HumanJudgment>> hjStack;
	Vector<Intention> targets;
	Shell shell;
	
	//timing structure
	protected Vector <Integer> messageTimes;
	
	/**
	 * @author jenhork
	 * Constructor, takes in a ModelImpl (how the model is stored) a CommandStack, to execute commands, also a diagram Command stack
	 */
	public IntQualBackwardReasoner(ModelImpl m, CommandStack com, List editParts) {
		super(m, com, editParts);
		
		//softgoalWrappers = new Vector<Intention>();
		minDistances = new HashMap<Intention, Integer>();
		solver = new zChaffSolver();
		minSolver = new zMinimalSolver();
		converter = new ModeltoAxiomsConverter(model, cs);
		hjStack = new Stack<HashMap<Intention, HumanJudgment>>();
		targets = converter.findTargets();
		initializeMinDistances();
		Shell [] ar = PlatformUI.getWorkbench().getDisplay().getShells();
		shell = ar[0];
		//System.out.println("finished constructor");
		
		//timing structure
		messageTimes = new Vector<Integer>();
	}
	
	/**
	 * @author jenhork
	 * The major method for qualitative, interactive reasoning.  This is like the "main" method.  Where most of the
	 * stuff starts from.
	 */
	public void reason() {
		
		internalReason();
		
		//process hj times
		processHJTimes();
		
		processMessageTimes();
				
	}


	private void internalReason() {
		//DEBUGGING stuff, delete eventually
		
		System.out.println("Qualitative Interactive Backward Reasoning - !yohA");
		System.out.println(System.getProperty("os.name"));
		
		//First, translate the model into a form that the SAT solver can read
		
		init();
						
		cnf = converter.convertBothDirections("Dimacs.cnf");
				
		cnfBack = converter.convertBackward("backDimacs.cnf");
		
		System.out.println("Done conversion");		
		
		while( true )  {
			int result = solver.solve(cnf);		
			System.out.println("Solved cnf, result: " + result);
			if (result ==1) {
				
				Vector<Integer> intResults = solver.getResults();
				
				HashMap<Intention, int[]> results = converter.convertResults(intResults);
			
				/*System.out.println("Results HashMap");
				//System.out.println(intResults.size());
				for (Intention intention : results.keySet()) {
					System.out.println(intention.getName());
					int [] ints = results.get(intention);
					for (int i : ints) {
						System.out.print(i + " ");
					}
					System.out.println("");				
				}*/
				
				/*Find the intentions that need human judgment, also display the intermediate
				 * results of the SAT Solver
				 */
				Vector<Intention> needHJ = processAndDisplayResults(results);	
				
				if (needHJ.size() == 0) {
					//we are done, problem is SAT, HJ not needed
					System.out.println("no intentions need hj, complete");
					showMessage("Success: backward evaluation complete!", shell);
					return;
				}
				
				/*System.out.println("The following intentions need human judgment: ");
				for (Intention i : needHJ) {
					System.out.println(i.getName());
				}*/
				
				//find the intentions needing human judgment nearest to the targets
				Vector<Intention> topMostConflict = findTopMostConflict(needHJ);
				
				/*System.out.println("The following intentions are the topmost conflicts: ");
				for (Intention i : topMostConflict) {
					System.out.println(i.getName());
				}*/
				
				if (topMostConflict.size() == 0) {
					System.out.println("no topmost intentions need hj, complete");
					showMessage("Success: backward evaluation complete!", shell);
					return;
				}

				
				//System.out.println(cnf.getNumClauses());
				
				HashMap<Intention, HumanJudgment> topMostJudgments = new HashMap<Intention, HumanJudgment>();
				
				//Use the topmost intentions needing human judgment to get judgments and add
				//the judgment to the SAT encodings
				//the int results shows the result of the user interaction process
				//We also want to know which judgments were added, to help us backtrack later,
				//so we pass in a new HashMap to keep track
				int hjresult = addHumanJudgement(topMostConflict, topMostJudgments); //, results);
				
				//user has cancelled
				if (hjresult == -1)
					return;
				//user has no more hj to add
				if (hjresult == 0) {
					System.out.println("HJ result 0");
					int bresult = backtrack(result);
					
					if (bresult == -1) {
						return;
					}
				}
				if (hjresult == 1) {
					hjStack.push(topMostJudgments);
				}
				
				//System.out.println(cnf.getNumClauses());
				
				//remove me later
				//result = solver.solve(cnf);
				//result = solver.solve(cnfBack);	
				
				
			}
			else if (result == 0) {
				//if (hjStack.size() > 0) {
					//temporarily disable unsat results
					//processUnSATCore();
					
				//}
				
				int bresult = backtrack(result);
				
				if (bresult == -1) {
					return;
				}		
			}
			else {
				System.out.println("zChaff failed");
				
				return;
			}
		}
		
	}
	
	

	private void init() {
		for (Intention i: model.getAllIntentions()) {
			if (i.getLabelBag() != null) {
				Command clearLB = new ClearLabelBagCommand(i);
				cs.execute(clearLB);
			}
			Command setInitValue = new SetInitialEvaluationLabelCommand(i, EvaluationLabel.NONE);
			cs.execute(setInitValue);
		}		
	}

	private int backtrack(int result) {
		Shell [] ar = PlatformUI.getWorkbench().getDisplay().getShells();
		
		Shell shell = ar[0];	
		Vector<Intention> conflictIntentions;
		//Vector<Intention> conflictSourceIntentions;
		//HashMap<Intention, int[]> unSatResults;
		if (result == 0) {
			
			conflictIntentions = processUnSATCore(); 
		}
		else {
			conflictIntentions = new Vector<Intention>();
			//conflictSourceIntentions = new Vector<Intention>();
			//unSatResults = new HashMap<Intention, int[]>();
		}
		
		
		boolean conflictingNeedHJ = false;
		
		if (hjStack.size() > 0) {				
					
			HashMap<Intention, HumanJudgment> needHJ = hjStack.pop();
			
			for (Intention i: needHJ.keySet()) {
				if (result == 1 || conflictIntentions.contains(i))  {
					conflictingNeedHJ = true;
					System.out.println(i.getName() + " needs human judgment in backtrack");
												
					cnf = converter.backtrackHumanJudgment(cnf, i, needHJ.get(i));
					cnfBack = converter.backtrackHumanJudgment(cnfBack, i, needHJ.get(i));				
					
				}
			}
			
			/*result = solver.solve(cnf);		
			System.out.println("Solved cnf, result: " + result);
			if (result == 0) {
				for (Intention i: needHJ) {
					System.out.println(i.getName() + " backtracking over");
					
					cnf = converter.backtrackHumanJudgment(cnf, i, 0);
					cnfBack = converter.backtrackHumanJudgment(cnfBack, i, -1);
					
				}
			}
			
			if (!conflictingNeedHJ) {
				return -1;
			}*/
			
			return 1;
		}
		//when you're backintrakcing over human judgment but there is no more judgment to back track over	
		else {
			//temporarily disable unsat results
			/*String unsatMessage = getUnsatCoreString(cnf);
			System.out.println(unsatMessage);
			showMessage("Target(s) unsatisfiable, no more judgments to backtrack over.\n" + unsatMessage + "Ending.", shell);
			//showMessage("Target(s) unsatisfiable, no more judgments to backtrack over.\n" + "Ending.", shell);
			*/
			showMessage("No more judgments to backtrack over.  Target(s) unsatisfiable\n", shell);
						
			return -1;
		}
		
	}
	
	private Vector<Intention> processUnSATCore() {
		int minResult = minSolver.solve(cnf);	
		
		Vector<Integer> intResults = minSolver.getResults();
		String unsatMessage2 = getUnsatCoreString(intResults);
		System.out.println(unsatMessage2);
		Vector<Intention> conflictIntentions = getUnSatCoreIntentions(intResults);

		HashMap<Intention, String> unSatResults = new HashMap<Intention, String>();
		Vector<Intention> conflictSourceIntentions = getUnSatCoreSourceIntentions(intResults, unSatResults);
		System.out.println("conflicting Intentions:");
		for (Intention k: conflictIntentions) {
			System.out.println(k.getName());
		}	
		
		//HashMap<Intention, String> unSatResults = getUnSatCoreIntentionsAndLabels(intResults);
		
		showBackTrackMessage(conflictIntentions, conflictSourceIntentions, unSatResults);
		
		return conflictIntentions;
	}
	
	private void showBackTrackMessage(Vector<Intention> conflictIntentions, Vector<Intention> conflictSourceIntentions, HashMap<Intention, String> unSatResults) {
			
		
		conflictIntentions.removeAll(conflictSourceIntentions);
		highlightIntentions(conflictIntentions, "orange");
		highlightIntentions(conflictSourceIntentions, "red");
		
		
		String message = "Target(s) unsatisfiable\n";
		if (conflictIntentions.size() >0 ){
			message += "The following intentions are involved in the conflict:\n";
		}
		for (Intention k: conflictIntentions) {
			String str = unSatResults.get(k);			
			message += k.getName() + "\t\t" + str + "\n";
		}
		if (conflictSourceIntentions.size() >0 ){
			message += "\nThe following intentions are the sources of the conflict:\n";
		}
		for (Intention k: conflictSourceIntentions) {
			String str = unSatResults.get(k);	
			message += k.getName() + "\t\t" +  str + "\n";
		}
		message+= "\nbacktracking...";
		showMessage(message, shell);
		conflictIntentions.addAll(conflictSourceIntentions);
		unHighlightIntentions(conflictIntentions);
		}

	private void highlightIntentions(Vector<Intention> conflictIntentions, String color) {
		System.out.println("highlighting conflict intentions");
		HighlightIntentionsCommand highlight = new HighlightIntentionsCommand(editParts, conflictIntentions, color);
		
		cs.execute(highlight);		
	}
	
	private void unHighlightIntentions(Vector<Intention> conflictIntentions) {
		System.out.println("unhighlighting intentions");
		HighlightIntentionsCommand highlight = new HighlightIntentionsCommand(editParts, conflictIntentions, "");
		
		cs.execute(highlight);		
	}

	private Vector<Intention> getUnSatCoreIntentions(Vector<Integer> intResults) {
		Vector<Intention> conflicts = new Vector<Intention>();
				
			//for (Integer in : intResults) {
			//	System.out.println(in.toString());
			//}		
		
			conflicts = converter.convertMinResultstoIntentions(intResults, cnf);
						
		
		return conflicts;
	}
	
	private HashMap<Intention, String> getUnSatCoreIntentionsAndLabels(Vector<Integer> intResults) {
		HashMap<Intention, String>  conflicts = new HashMap<Intention, String>();
				
			//for (Integer in : intResults) {
			//	System.out.println(in.toString());
			//}		
		
			conflicts = converter.convertMinResultstoIntentionsAndLabels(intResults, cnf);
						
		
		return conflicts;
	}
	
	private Vector<Intention> getUnSatCoreSourceIntentions(Vector<Integer> intResults, HashMap<Intention, String> unSatResults) {
		Vector<Intention> conflicts = new Vector<Intention>();
		
				
			//for (Integer in : intResults) {
			//	System.out.println(in.toString());
			//}		
		
			conflicts = converter.convertMinResultstoSourceIntentions(intResults, cnf, unSatResults);
						
		
		System.out.println("Source conflicts: ");
		for (Intention in : conflicts) {
			System.out.println(in.getName());
		}
		return conflicts;
	}

	private String getUnsatCoreString(Vector<Integer> intResults) {
					
			Vector<String> results = converter.convertMinResultstoStringClause(intResults, cnf);
			
			/*System.out.println("The following intention labels are conflicting:");
			System.out.println(results.size());
			for (String str: results) {
				System.out.println(str);			
			}*/
			
			String message = "The following intention clauses are conflicting:\n";
			for (String str: results) {
				message += str + "\n";			
			}
			
			return message;
				
	}

	private int addHumanJudgement(Vector<Intention> topMostConflict, HashMap<Intention, HumanJudgment> topMostJudgments) { //, HashMap<Intention, int[]> results) {
		int hjresult = 0;
		int result = solver.solve(cnfBack);					
		
		if (result == 1) {
			
			Vector<Integer> intBackResults = solver.getResults();
		
			HashMap<Intention, int[]> backResults = converter.convertResults(intBackResults);
			
			for (Intention i: topMostConflict)  {
				int[] results = backResults.get(i);
				if (results == null) {
					System.out.println("error in back results");
				}
				//for (int j : results) {
				//	System.out.print(j + " ");
				//}
				if (!none(results)) {
					//System.out.println("The following intentions are getting human judgment: " + i.getName());			
					
					/*System.out.println("Backward Results HashMap");
					//System.out.println(intResults.size());
					for (Intention intention : backResults.keySet()) {
						System.out.println(intention.getName());
						int [] ints = backResults.get(intention);
						for (int j : ints) {
							System.out.print(j + " ");
						}
						System.out.println("");				
					}*/
				
					//backwards eval to find targets?
					//softgoalWrappers.add(i);								
					
					HumanJudgment judgmentResult = promptForHumanJudgment(i, backResults.get(i));
					System.out.println(judgmentResult.toUIString());
					//user has pressed cancel, quit everything
					if (judgmentResult == null) {
						return -1;
					//no combinations 
					} else if (judgmentResult.getLabelBag() == null) {
						return 0;
					} else if (judgmentResult.getLabelBag().size() > 0) {
						//System.out.println("r is 1");
						//lb.printBag();
						//do something with the bag
						
						displayIntermediateHJResults(i, judgmentResult);
						
						/*System.out.println("human judgements:");
						for (HumanJudgement hj : w.getHumanJudgements()) {
							hj.getLabelBag().printBag();
							System.out.println("Target: " + hj.getJudgement().toString());
						}	*/
						
						cnf = converter.addHumanJudgment(cnf, i, judgmentResult, 0);
						cnfBack = converter.addHumanJudgment(cnfBack, i, judgmentResult, -1);
						
						hjresult = 1;
						
						topMostJudgments.put(i, judgmentResult);
					}
				}
			}
		} else {
			System.out.println("Couldn't find backward target");
			return 0;
		}
		return hjresult;		
	}

	private void displayIntermediateHJResults(Intention i, HumanJudgment hj) {
		//System.out.println("Displaying results");	
		setQualCombinedLabel(i, hj.getResultLabel());
		if (hj.getLabelBag() == null)
			System.out.println("label bag null in display intermediate results");
		if (hj.getLabelBag().getLabelBagEvalLabels() == null)
			System.out.println("Eval labels null in display intermediate results");
		if (hj.getLabelBag().getLabelBagIntentions() == null)
			System.out.println("Intentions null in display intermediate results");
		ListIterator<Intention> it = hj.getLabelBag().getLabelBagIntentions().listIterator();
		while(it.hasNext()) {
			Intention intnt = it.next();
			//ugly:  the label bag has two vectors, one for intentions, one for labels, we are iterating through the intentions, so get the 
			//label at the index of the intention.  The two vectors should have equal size and correspondence between intentions and labels
			setQualCombinedLabel(intnt, hj.getLabelBag().getLabelBagEvalLabels().get(hj.getLabelBag().getLabelBagIntentions().indexOf(intnt)));
			//System.out.println(ilp.getIntention().getName() + " " + ilp.getEvaluationLabel().toString());	
		}
		
		
	}

	private boolean none(int[] is) {
		for (int i : is) {
			if (i > 0)
				return false;
		}
		return true;
	}

	private HumanJudgment promptForHumanJudgment(Intention i, int[] js) {
		System.out.println(i.getName() + " needs human judgment.  Target is: ");
		for (int j: js)
			System.out.print(j + " ");
		System.out.println();
		
		Shell [] ar = PlatformUI.getWorkbench().getDisplay().getShells();
		
		Command setInitLabel;
		if (js[0] > 0 ) { //& (js[2] < 0 & js[3] < 0 & js[4] < 0 & js[5] < 0)) {
			setInitLabel = new SetInitialEvaluationLabelCommand(i, EvaluationLabel.SATISFIED);
		}		
		else if (js[1] > 0 ) { //& (js[2] < 0 & js[3] < 0 & js[4] < 0 & js[5] < 0)) {
			setInitLabel = new SetInitialEvaluationLabelCommand(i, EvaluationLabel.PARTIALLY_SATISFIED);
		}	
		else if (js[2] > 0 ) { //& (js[0] < 0 & js[1] < 0 & js[3] < 0 & js[4] < 0 & js[5] < 0)) {
			setInitLabel = new SetInitialEvaluationLabelCommand(i, EvaluationLabel.UNKNOWN);
		}		
		else if (js[3] > 0 ) { //& (js[0] < 0 & js[1] < 0 & js[2] < 0 & js[4] < 0 & js[5] < 0)) {
			setInitLabel = new SetInitialEvaluationLabelCommand(i, EvaluationLabel.CONFLICT);
		}
		else if (js[4] > 0 ) { //& (js[0] < 0 & js[1] < 0 & js[2] < 0 & js[3] < 0)) {
			setInitLabel = new SetInitialEvaluationLabelCommand(i, EvaluationLabel.PARTIALLY_DENIED);
		}			
		else if (js[5] > 0 ) { //& (js[0] < 0 & js[1] < 0 & js[2] < 0 & js[3] < 0 & js[4]<0)) {
			setInitLabel = new SetInitialEvaluationLabelCommand(i, EvaluationLabel.DENIED);
		}
		else {
			//System.out.println("Backward target not clear for " + w.getIntention().getName());
			//return new LabelBag();
			setInitLabel = new SetInitialEvaluationLabelCommand(i, EvaluationLabel.NONE);
		}
		cs.execute(setInitLabel);
		
		// highlighting
		
		/**
		 * highlights target body.
		 * however, if it highlighted an intention that was a non-default colour
		 * that would be lost when eval window target moves on.
		 * colour is reset to default, not previous.
		 */
		Vector<Intention> tohighlight = new Vector<Intention>();
		tohighlight.add(i);
		tohighlight.addAll(i.getChildren());
		highlightIntentions(tohighlight, "yellow");
		
		// highlights the target intention and its children
		/**List<Intention> target = new Vector<Intention>();
		target.add(i);
		List<Intention> children = i.getChildren();
		try {
		HighlightIntentionOutlinesCommand highlightTarget = new HighlightIntentionOutlinesCommand (
				editParts, target, new RGB(255,0,0)); // 255 0 0 is rgb for red for target
		HighlightIntentionOutlinesCommand highlightChildren = new HighlightIntentionOutlinesCommand (
				editParts, children, new RGB(0,0,255)); // 0 0 255 is rgb for blue for children
		cs.execute(highlightTarget);
		cs.execute(highlightChildren);
		} catch (Exception highlightFailedException) {			
		}*/
		
		long start = System.currentTimeMillis();
		
		
		// bring up backward judgement window
		System.out.println("Opening window");
		BackwardHJWindowCommand wincom = new BackwardHJWindowCommand(ar[0], cs, i/*, softgoalWrappers*/);
				
		cs.execute(wincom);
		
		long end = System.currentTimeMillis();
		
		Integer time = new Integer((int) (end-start));
		
		System.out.println("Human judgment time for analysis was "+(end-start)+" ms.");		
		
		hjTimes.add(time);
		
		if (!judgedIntentions.contains(i)) 
			judgedIntentions.add(i);
		
		// window moves on. unhighlight: 
		
		/** unhighlight the intentions' body, 
		 * not just the outline. caveat: restores it to default color,
		 * not previous color
		 */
		unHighlightIntentions(tohighlight);
		
		/** unhighlight outlines
		try {
		// unhighlight target when evaluation target moves on
		HighlightIntentionOutlinesCommand unhighlightTarget = new HighlightIntentionOutlinesCommand(
				editParts, target, new RGB(0,0,0));		
		// unhighlight children when evaluation target moves on
		HighlightIntentionOutlinesCommand unhighlightChildren = new HighlightIntentionOutlinesCommand(
				editParts, children, new RGB(0,0,0));		
		cs.execute(unhighlightTarget);
		cs.execute(unhighlightChildren);
		} catch(Exception unhighlightOutlinesFailedException) {			
		}*/
		
		if (wincom.noCombinations()) {
			System.out.println("Window had no combinations");
			return new HumanJudgmentImpl();
		}
		
		if (wincom.done()) {
			System.out.println("Window was done");
			return wincom.getJudgmentResult();								
		}
		
		// was cancelled
		return null;
	}

	private Vector<Intention> findTopMostConflict(Vector<Intention> needHJ) {
		// find distance from top goals for each intention
		
		Vector<Intention> topMost = new Vector<Intention>();
		
		int smallest = model.getAllIntentions().size();
		
		for (Intention i: needHJ) {
			int distance = minDistances.get(i).intValue();
			if (distance < smallest) {
				smallest = distance;
			}
		}
		if (smallest != model.getAllIntentions().size()+1) {
			for (Intention i: needHJ) {
				if (smallest == minDistances.get(i).intValue()) 
					topMost.add(i);
			}
		}
		
		return topMost;
	}
	
	private void initializeMinDistances() {
		if (minDistances.size() == 0)
		{
			//System.out.println("initialize min distances");
			//initialize data structure
			for (Intention i : model.getAllIntentions()) {
				//second argument is the largest possible value for distance + 1
				minDistances.put(i, new Integer(model.getAllIntentions().size()+1));
			}
			//find top intentions
			for (Intention i : targets) {
				//if (i.isRoot()) {
					//dusted off my algorithms book (seriously)
					//I guess this is a breadth-first search for all intentions,
					//keeping track of the distance from root
					int distance = 0;
					findMinDistance(distance, i, minDistances);
					
					//System.out.println("target: " + i.getName());
				//}
			}
			/*for (Intention i: minDistances.keySet()) {
				System.out.println(i.getName() + " distance: " + minDistances.get(i).intValue());
			}*/
		}
		
	}

	private void findMinDistance(int distance, Intention root, HashMap<Intention, Integer> hashMap) {
		
		if (hashMap.get(root).intValue() > distance)
				hashMap.put(root, new Integer(distance));
		
		distance++;
				
		//base case, root is leaf
		if (root.isLeaf())   {	
			
			return;
		}
		else {			
			for (Intention child : root.getChildren()) {
				
				findMinDistance(distance, child, hashMap);
			}
		}
		
		return;
	}
	
	private String findLabelsFromInts(int [] ints) {
		String labels = "";
		if (ints != null) {
			if (ints[0] > 0)
				labels += "Satisfied ";
			else if (ints[1] > 0)
				labels += "Partially Satisfied ";
			if (ints[2] > 0)
				labels += "Unknown ";
			if (ints[3] > 0)
				labels += "Conflict ";
			if (ints[5] > 0)
				labels += "Denied ";
			else if (ints[4] > 0)
				labels += "Partially Denied ";
		}
		
		return labels;
	}

	private Vector<Intention> processAndDisplayResults(HashMap<Intention, int[]> results) {
		Vector<Intention> needHJ = new Vector<Intention>();
		
		for (Intention intention : results.keySet()) {
			//System.out.println("Displaying for: " + intention.getName());
			int [] ints = results.get(intention);
			
			if (ints[0] > 0 & (ints[2] < 0 & ints[3] < 0 & ints[4] < 0 & ints[5] < 0)) {
				setQualCombinedLabel(intention, EvaluationLabel.SATISFIED);
			}
			
			else if (ints[1] > 0 & (ints[0] < 0 & ints[2] < 0 & ints[3] < 0 & ints[4] < 0 & ints[5] < 0)) {
				//if (intention.getContributesFrom().size() < 2)
					setQualCombinedLabel(intention, EvaluationLabel.PARTIALLY_SATISFIED);
			}
			
			else if (ints[2] > 0 & (ints[0] < 0 & ints[1] < 0 & ints[3] < 0 & ints[4] < 0 & ints[5] < 0)) {
				setQualCombinedLabel(intention, EvaluationLabel.UNKNOWN);
			}
			
			else if (ints[3] > 0 & (ints[0] < 0 & ints[1] < 0 & ints[2] < 0 & ints[4] < 0 & ints[5] < 0)) {
				setQualCombinedLabel(intention, EvaluationLabel.CONFLICT);
			}
			
			else if (ints[4] > 0 & (ints[0] < 0 & ints[1] < 0 & ints[2] < 0 & ints[3] < 0 & ints[5] < 0)) {
				//if (intention.getContributesFrom().size() < 2)
					setQualCombinedLabel(intention, EvaluationLabel.PARTIALLY_DENIED);
			}
			
			else if (ints[5] > 0 & (ints[0] < 0 & ints[1] < 0 & ints[2] < 0 & ints[3] < 0)) {
				setQualCombinedLabel(intention, EvaluationLabel.DENIED);
			}
			else if (ints[4] < 0 & ints[5] < 0 & ints[0] < 0 & ints[1] < 0 & ints[2] < 0 & ints[3] < 0) {
				setQualCombinedLabel(intention, EvaluationLabel.NONE);
			}
			/*!!
			 * I'm missing a case here where there is more than one PS or more than one PD value.  
			 * This isn't actually possible, but if the value is only PS or only PD and there is more than one incoming
			 * contribution link then...  maybe this is it?
			 */
			else {
				if (intention.getContributesFrom().size() > 1) {
					setQualCombinedLabel(intention, EvaluationLabel.UNKNOWN);
					needHJ.add(intention);
				}
				else {
					setQualCombinedLabel(intention, EvaluationLabel.CONFLICT);
				}					
			}
		}
		
		return needHJ;
	}
	
	/*public SoftgoalWrappers getSoftgoalWrappers(){
		return softgoalWrappers;
	}*/
	
	/**
	 * Shows a message in a dialog box with an OK button 
	 * @param message
	 */
	private void showMessage(String message, Shell shell) {			
		//timing
		long start = System.currentTimeMillis();
		
		MessageDialog.openInformation(
			shell,
			"Interactive Qualitative Backward Reasoning",
			message);
		
		long end = System.currentTimeMillis();
		
		Integer time = new Integer((int) (end-start));
		
		messageTimes.add(time);				
	}
	
	
	private void processMessageTimes() {
		int sum = 0;
		
		for (Integer i:  messageTimes) {
			
			sum+= i.intValue();
		}
		
		System.out.println("Num messages given: " + messageTimes.size());
		System.out.println("Total time for other messages: " + sum);
		
	}

}

/**
 * 
 */
package edu.toronto.cs.openome.evaluation.SATSolver;

import java.util.HashMap;
import java.util.Vector;

import org.eclipse.emf.common.command.CommandStack;

import edu.toronto.cs.openome.evaluation.commands.SetQualitativeEvaluationLabelCommand;
import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.impl.ModelImpl;
/**
 * @author jenhork
 * The parent reasoner class, defines the possible actions that all reasoners must have.
 * This class also holds the ModelImplementation and CommandStack that all reasoners will need to communicate with the model
 * 
 *
 */
public abstract class SATSolver {
	Dimacs cnf;
	static String homedir = new SolverFileHandler().folderPath();
	//static String filename = "dimacs.cnf";
	protected Vector<Integer> results;
		//"C:\\zChaffWorkspace\\zChaff\\";
	
	/**
	 * @author jenhork
	 * Default constructor, does nothing
	 */
	public SATSolver() {
		results = new Vector<Integer>();		
	}
				
	/**
	 * @author jenhork
	 * This is where the reasoning actually occurs; however, it should be overridden by it's childen, as this is a general type of reasoner. 
	 * It's not clear what the parent reasoner should do.
	 */
	public abstract int solve(Dimacs c) ;
	
	protected Vector<Integer> convertToInts(String[] vars, int num) {
		//System.out.println("ConvertToInts");
		Vector<Integer> ints = new Vector<Integer>();
		
		int min = 0;
		
		if (vars.length < num)
			min = vars.length;
		else
			min = num;
		
		for (int index = 0; index < min; index++) {
			//System.out.println(index + ", " + vars[index]);
			ints.add(Integer.parseInt(vars[index]));
		}
		return ints;
	}
	
	public Vector<Integer> getResults() {
		return results;
	}
	
		
}

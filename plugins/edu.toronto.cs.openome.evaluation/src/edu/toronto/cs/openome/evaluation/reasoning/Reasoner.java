/**
 * 
 */
package edu.toronto.cs.openome.evaluation.reasoning;

import java.sql.Time;
import java.util.Collection;
import java.util.List;
import java.util.Timer;
import java.util.Vector;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;

import edu.toronto.cs.openome.evaluation.commands.SetQualitativeEvaluationLabelCommand;
import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.diagram.edit.parts.ActorActorCompartmentEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.CompartmentEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart;
import edu.toronto.cs.openome_model.impl.GoalImpl;
import edu.toronto.cs.openome_model.impl.IntentionImpl;
import edu.toronto.cs.openome_model.impl.ModelImpl;
import edu.toronto.cs.openome_model.impl.ResourceImpl;
import edu.toronto.cs.openome_model.impl.SoftgoalImpl;
import edu.toronto.cs.openome_model.impl.TaskImpl;

/**
 * @author jenhork
 * The parent reasoner class, defines the possible actions that all reasoners must have.
 * This class also holds the ModelImplementation and CommandStack that all reasoners will need to communicate with the model
 * 
 *
 */
public class Reasoner {
	protected ModelImpl model;
	protected CommandStack cs;
	protected List editParts;
		
	//timing structure
	protected Vector <Integer> hjTimes;
	
	//judged intentions for timing
	protected Vector <Intention> judgedIntentions;
	
	/**
	 * @author jenhork
	 * Default constructor, does nothing
	 */
	public Reasoner() {
		model = null;
		cs = null;
		editParts = null;

		hjTimes = new Vector<Integer>();
		judgedIntentions = new Vector <Intention>();
		
	}
	
	/**
	 * @author jenhork
	 * Default constructor, sets the model implementation and the command stack
	 */
	public Reasoner(ModelImpl mod, CommandStack com, List ep) {
		model = mod;
		cs = com;	
		editParts = ep;

		hjTimes = new Vector<Integer>();
		judgedIntentions = new Vector <Intention>();
	}
		
	/**
	 * @author jenhork
	 * This is where the reasoning actually occurs; however, it should be overridden by it's childen, as this is a general type of reasoner. 
	 * It's not clear what the parent reasoner should do.
	 */
	public void reason() {
		//This one does nothing, should be overridden
	}
	
	/**
	 * @author jenhork
	 * This method sets the QualitativeCombinedLabel attribute of a model element.
	 * It has to do this using a command.  It creates a new command then executes the command.
	 * The parameters passed in are the intention for which the label is changing and the new label to change it to.
	 */
	public void setQualCombinedLabel(Intention i, EvaluationLabel l) {
		//make a new command with the intention and label.
		SetQualitativeEvaluationLabelCommand setLabel = new SetQualitativeEvaluationLabelCommand(i, l);
		
		//put this command on the command stack (cs).  The application will execute the command for us.
		//We define the execute method for this type of command in SetQualitativeEvaluationLabelCommand
		cs.execute(setLabel);
		
		//put something here to slow it down?
	}
	
protected void processHJTimes() {
		
		if(hjTimes.size() != 0) { 
			int max = -1;
			int min = 999999999;
			int sum = 0;
			
			for (Integer i:  hjTimes) {
				max = Math.max(i.intValue(), max);
				min = Math.min(i.intValue(), min);
				sum+= i.intValue();
			}
			
			int average = sum/hjTimes.size();
			
			System.out.println("Num human judgments in model: " + hjTimes.size());
			System.out.println("Num intentions which recieved judgment: " + judgedIntentions.size());
			System.out.println("Max human judgment time: " + max);
			System.out.println("Min human judgment time: " + min);
			System.out.println("Total human judgment time: " + sum);
			System.out.println("Average human judgment time: " + average);
		}
		
	}
	
		
}

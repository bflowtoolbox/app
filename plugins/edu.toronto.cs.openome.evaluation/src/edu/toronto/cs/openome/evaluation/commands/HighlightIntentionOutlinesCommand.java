package edu.toronto.cs.openome.evaluation.commands;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.common.command.Command;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.swt.graphics.RGB;

import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.diagram.edit.parts.CompartmentEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart;
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
import edu.toronto.cs.openome_model.impl.IntentionImpl;

public class HighlightIntentionOutlinesCommand implements Command {
	private List<Intention> intentionList;
	private RGB oColor;
	protected List editParts;
	
	public HighlightIntentionOutlinesCommand(List editP, List<Intention> list, RGB c) {
		intentionList = list;
		oColor = c;
		editParts = editP;
	}

	public boolean canExecute() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean canUndo() {
		// TODO Auto-generated method stub
		return false;
	}

	public Command chain(Command arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void execute() {
		
		highlightIntentions();
	}

	public Collection<?> getAffectedObjects() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLabel() {
		// TODO Auto-generated method stub
		return "a label";
	}

	public Collection<?> getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	public void redo() {
		// TODO Auto-generated method stub

	}

	public void undo() {
		// TODO Auto-generated method stub

	}
	
	/**
	 * @author jenhork
	 
	 */
	public void highlightIntentions() {
		//get a list of editparts for all of the intentions in the model
		Vector<GraphicalEditPart> processedEditParts = processEditParts();
		
		for (GraphicalEditPart ep : processedEditParts) {
			AbstractBorderedShapeEditPart aSEp = (AbstractBorderedShapeEditPart) ep;
			IntentionImpl partIntention = (IntentionImpl) aSEp.resolveSemanticElement();
			//if this intention is in the list of things to highlight
			if (intentionList.contains(partIntention)) {
				System.out.println("highlighting outline for" + partIntention.getName());
				if (aSEp instanceof SoftgoalEditPart) {
					SoftgoalEditPart sep = (SoftgoalEditPart) aSEp;					
					sep.setOutlineColor(oColor);
					sep.refresh();
				}
				if (aSEp instanceof Softgoal2EditPart) {
					Softgoal2EditPart sep = (Softgoal2EditPart) aSEp;				
					sep.setOutlineColor(oColor);
					sep.refresh();
				}
				if (aSEp instanceof Softgoal3EditPart) {
					Softgoal3EditPart sep = (Softgoal3EditPart) aSEp;			
					sep.setOutlineColor(oColor);
					sep.refresh();
				}
				if (aSEp instanceof Softgoal4EditPart) {
					Softgoal4EditPart sep = (Softgoal4EditPart) aSEp;				
					sep.setOutlineColor(oColor);
					sep.refresh();
				}
				if (aSEp instanceof Softgoal5EditPart) {
					Softgoal5EditPart sep = (Softgoal5EditPart) aSEp;				
					sep.setOutlineColor(oColor);
					sep.refresh();
				}
				if (aSEp instanceof GoalEditPart) {
					GoalEditPart sep = (GoalEditPart) aSEp;				
					sep.setOutlineColor(oColor);
					sep.refresh();
				}
				if (aSEp instanceof Goal2EditPart) {
					Goal2EditPart sep = (Goal2EditPart) aSEp;				
					sep.setOutlineColor(oColor);
					sep.refresh();
				}
				if (aSEp instanceof Goal3EditPart) {
					Goal3EditPart sep = (Goal3EditPart) aSEp;				
					sep.setOutlineColor(oColor);
					sep.refresh();
				}
				if (aSEp instanceof Goal4EditPart) {
					Goal4EditPart sep = (Goal4EditPart) aSEp;				
					sep.setOutlineColor(oColor);
					sep.refresh();
				}
				if (aSEp instanceof Goal5EditPart) {
					Goal5EditPart sep = (Goal5EditPart) aSEp;				
					sep.setOutlineColor(oColor);
					sep.refresh();
				}
				if (aSEp instanceof TaskEditPart) {
					TaskEditPart sep = (TaskEditPart) aSEp;				
					sep.setOutlineColor(oColor);
					sep.refresh();
				}
				if (aSEp instanceof Task2EditPart) {
					Task2EditPart sep = (Task2EditPart) aSEp;				
					sep.setOutlineColor(oColor);
					sep.refresh();
				}
				if (aSEp instanceof Task3EditPart) {
					Task3EditPart sep = (Task3EditPart) aSEp;					
					sep.setOutlineColor(oColor);
					sep.refresh();
				}
				if (aSEp instanceof Task4EditPart) {
					Task4EditPart sep = (Task4EditPart) aSEp;					
					sep.setOutlineColor(oColor);
					sep.refresh();
				}
				if (aSEp instanceof Task5EditPart) {
					Task5EditPart sep = (Task5EditPart) aSEp;				
					sep.setOutlineColor(oColor);
					sep.refresh();
				}
				if (aSEp instanceof ResourceEditPart) {
					ResourceEditPart sep = (ResourceEditPart) aSEp;					
					sep.setOutlineColor(oColor);
					sep.refresh();
				}
				if (aSEp instanceof Resource2EditPart) {
					Resource2EditPart sep = (Resource2EditPart) aSEp;					
					sep.setOutlineColor(oColor);
					sep.refresh();
				}
				if (aSEp instanceof Resource3EditPart) {
					Resource3EditPart sep = (Resource3EditPart) aSEp;				
					sep.setOutlineColor(oColor);
					sep.refresh();
				}
				if (aSEp instanceof Resource4EditPart) {
					Resource4EditPart sep = (Resource4EditPart) aSEp;					
					sep.setOutlineColor(oColor);
					sep.refresh();
				}
				if (aSEp instanceof Resource5EditPart) {
					Resource5EditPart sep = (Resource5EditPart) aSEp;					
					sep.setOutlineColor(oColor);
					sep.refresh();
				}
			}
		}
	}
	
	private Vector<GraphicalEditPart> processEditParts() {
		Vector<GraphicalEditPart> processed = new Vector<GraphicalEditPart>();
		
		for (Object ob: editParts) {
			EditPart ep = (EditPart) ob;
			//if it's an actor
			if (ep instanceof ShapeNodeEditPart) {
			
				for (Object obj : ep.getChildren()) {
					//get the part that has the intentions
					if (obj instanceof CompartmentEditPart) {
						CompartmentEditPart cEP = (CompartmentEditPart) obj;
						//get the intentions
						processed.addAll(cEP.getChildren());
					}						
				}
			}			
			//if it's an intention, a dependum in this case
			if (ep instanceof AbstractBorderedShapeEditPart) {
				AbstractBorderedShapeEditPart sep = (AbstractBorderedShapeEditPart) ep;
				//add it to the list
				processed.add(sep);				
			}
		}	
		return processed;
	}

}

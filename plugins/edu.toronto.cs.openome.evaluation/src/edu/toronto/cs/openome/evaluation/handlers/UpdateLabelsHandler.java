/**
 * 
 */
package edu.toronto.cs.openome.evaluation.handlers;

import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.emf.common.command.CommandStack;

import edu.toronto.cs.openome.evaluation.commands.SetQualitativeEvaluationLabelCommand;
import edu.toronto.cs.openome_model.Alternative;
import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.impl.ModelImpl;

/**
 * @author aftabs
 * Updates the given intention in the model with the given label
 *
 */
public class UpdateLabelsHandler extends ReasonerHandler{
	
	ModelImpl model;
	CommandStack cs;
	Alternative alt;

	public UpdateLabelsHandler(Alternative a) {
		super();
		alt = a;
	}
	
	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	public Object execute() {
	
		model = getModelImpl();
		cs = getCommandStack();
		
		updateLabels();
		
		return null;
				
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isHandled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Redraw the given intention's label to the given label. 
	 */
	private void updateLabels() {
		for (Intention i : model.getAllIntentions()) {
			//if (alt.findIntention(i))
				
				/*
				 * Clear the labels for now
				 * TODO: Set the appropriate label
				 */
				{SetQualitativeEvaluationLabelCommand setLabel = new SetQualitativeEvaluationLabelCommand(i, EvaluationLabel.NONE);
			
				cs.execute(setLabel);}
		}
	}
}

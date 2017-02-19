package edu.toronto.cs.openome.evaluation.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;

import edu.toronto.cs.openome.evaluation.commands.SetQualitativeEvaluationLabelCommand;
import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.impl.IntentionImpl;
import edu.toronto.cs.openome_model.impl.ModelImpl;

/**
 * Handler for applying a label to intention(s). For use with a matching command in
 * plugin.xml.
 *  
 * @author arupghose
 */
public class ApplyWeaklySatisfiedHandler extends ReasonerHandler{
	
	ModelImpl model;
	CommandStack cs;

	public ApplyWeaklySatisfiedHandler() {
		super();
	}
	
	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
	
		model = getModelImpl();
		cs = getCommandStack();
		applyWeaklySatisfied();
		
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
	
	private void applyWeaklySatisfied() {
		try {
			ISelection selection =  
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
			if (selection instanceof IStructuredSelection) {
				// the array is called intentions, but of course it may contain
				// other things like links and containers
				Object[] intentions = ((IStructuredSelection) selection).toArray();				
				int selectionSize = intentions.length;
				
				// try to cast the selection into an intention, and then apply
				// the label to it
				for (int i = 0; i < selectionSize; i++) {
					IGraphicalEditPart ep = (IGraphicalEditPart) intentions[i];
					// using this try-catch is essential for mass-application of label
					// because even if a cast fails (for instance if you're trying to 
					// label a link), the for-loop does not hang.
					try {
						AbstractBorderedShapeEditPart aSEp = (AbstractBorderedShapeEditPart) ep;
						IntentionImpl partIntention = (IntentionImpl) aSEp.resolveSemanticElement();
						SetQualitativeEvaluationLabelCommand setLabel = new SetQualitativeEvaluationLabelCommand(partIntention, EvaluationLabel.PARTIALLY_SATISFIED);
						cs.execute(setLabel);
					} catch (Exception CastFail) {						
					}
				}
			}
		} catch (Exception ApplyLabelFail) {			
		}
	}
}
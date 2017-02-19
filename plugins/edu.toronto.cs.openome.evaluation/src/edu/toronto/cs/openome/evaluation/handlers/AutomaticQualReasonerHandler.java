/**
 * 
 */
package edu.toronto.cs.openome.evaluation.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;

import edu.toronto.cs.openome.evaluation.qualitativeautomaticreasoning.AutomaticQualReasoner;
import edu.toronto.cs.openome.evaluation.reasoning.Reasoning;
import edu.toronto.cs.openome_model.impl.ModelImpl;

/**
 * @author jenhork
 *
 */
public class AutomaticQualReasonerHandler extends ReasonerHandler {
	
	public AutomaticQualReasonerHandler() {
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
	
		ModelImpl mi = getModelImpl();
		CommandStack cs = getCommandStack();
		
		DiagramCommandStack dcs = getDiagramCommandStack();
		
		Reasoning reasoning = new Reasoning(new AutomaticQualReasoner(mi, cs, dcs));
		
		reasoning.reason();
		
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

}




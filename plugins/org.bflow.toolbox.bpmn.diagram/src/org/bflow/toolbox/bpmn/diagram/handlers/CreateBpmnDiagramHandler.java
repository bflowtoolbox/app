package org.bflow.toolbox.bpmn.diagram.handlers;

import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * Implements {@link IHandler} to create a new BPMN diagram when button 
 * in the toolbar is pressed.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2018-10-04
 *
 */
public class CreateBpmnDiagramHandler extends AbstractHandler {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO add some logic here
		return null;
	}

}

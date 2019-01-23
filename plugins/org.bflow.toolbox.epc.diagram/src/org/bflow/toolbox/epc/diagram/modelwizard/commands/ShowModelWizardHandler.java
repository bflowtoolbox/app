package org.bflow.toolbox.epc.diagram.modelwizard.commands;

import org.bflow.toolbox.epc.diagram.actions.ElementGeneratorAction;
import org.bflow.toolbox.epc.diagram.wizards.ModelWizard;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * Provides a handler to run the {@link ModelWizard}.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 31/07/13
 */
public class ShowModelWizardHandler extends AbstractHandler {
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPage workbenchPage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IWorkbenchPart activeWorkbenchPart = workbenchPage.getActivePart();
		
		ElementGeneratorAction action = new ElementGeneratorAction();
		action.setActivePart(null, activeWorkbenchPart);
		action.run(null);
		
		return null;
	}

}

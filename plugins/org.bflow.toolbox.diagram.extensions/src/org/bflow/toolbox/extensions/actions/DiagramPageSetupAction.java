package org.bflow.toolbox.extensions.actions;

import org.bflow.toolbox.extensions.wizards.DiagramPageSetupWizard;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;


/**
 * An <code>IActionDelegate</code> to provide an wizard to change
 * the size of the diagram.
 * @author Joerg Hartmann
 * @since 0.0.7
 *
 */
public class DiagramPageSetupAction implements IActionDelegate{

	/**
	 * Runs the action.
	 * @param action
	 */
	public void run(IAction action) {
		DiagramPageSetupWizard w = new DiagramPageSetupWizard();
		w.init(null, null);
		
		IWorkbenchPart page =  PlatformUI.getWorkbench()
			.getActiveWorkbenchWindow().getActivePage().getActivePart();

		WizardDialog dialog = new WizardDialog(page.getSite().getShell(), w);
		dialog.create();
		dialog.open();
	}

	
	/**
	 * Selection has changed.
	 * @param action
	 * @param selection 
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		
	}
}

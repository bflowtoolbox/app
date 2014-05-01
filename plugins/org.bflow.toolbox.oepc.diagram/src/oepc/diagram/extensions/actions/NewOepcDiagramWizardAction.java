package oepc.diagram.extensions.actions;

import oepc.diagram.part.OepcCreationWizard;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * Implements the IWorkbenchWindowActionDelegate to call the new oepc diagram
 * wizard
 * 
 * @author Arian Storch
 * @since 21/11/09
 * @version 04/01/10 bug fixed
 */
public class NewOepcDiagramWizardAction implements
		IWorkbenchWindowActionDelegate {

	private IWorkbenchWindow workbenchWindow;

	@Override
	public void dispose() {
	}

	@Override
	public void init(IWorkbenchWindow window) {
		this.workbenchWindow = window;
	}

	@Override
	public void run(IAction action) {
		try {
			OepcCreationWizard wizard = new OepcCreationWizard();
			
			ISelection iSel = workbenchWindow.getActivePage().getSelection();	
			
			StructuredSelection sSel = new StructuredSelection(iSel);

			wizard.init(workbenchWindow.getWorkbench(), sSel);

			WizardDialog wd = new WizardDialog(workbenchWindow.getShell(),
					wizard);

			wd.open();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}

}

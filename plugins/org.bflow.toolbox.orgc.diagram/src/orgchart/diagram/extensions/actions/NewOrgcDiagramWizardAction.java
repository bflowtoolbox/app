package orgchart.diagram.extensions.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import orgchart.diagram.part.OrgcCreationWizard;

/**
 * Implements the IWorkbenchWindowActionDelegate to call the new orgc diagram
 * wizard
 * 
 * @author Markus Schnädelbach
 * 
 */
public class NewOrgcDiagramWizardAction implements
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
			OrgcCreationWizard wizard = new OrgcCreationWizard();
				
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

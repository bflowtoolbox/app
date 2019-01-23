package org.bflow.toolbox.epc.diagram.actions;

import org.bflow.toolbox.epc.diagram.Messages;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

/**
 * Implements the {@link IWorkbenchWindowActionDelegate} to call the new project
 * wizard.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-04-01
 * @version 2013-12-14
 * 
 */
public class NewProjectAction implements IWorkbenchWindowActionDelegate {

	private IWorkbenchWindow workbenchWnd;
	
	private WizardNewProjectCreationPage page;
	
	@Override
	public void dispose() {
	}

	@Override
	public void init(IWorkbenchWindow window) {
		this.workbenchWnd = window;
	}

	@Override
	public void run(IAction action) {
		
		DummyWizard dumWiz = new DummyWizard();
		dumWiz.setWindowTitle(Messages.NewProjectAction_WindowTitle);
				
		page = new WizardNewProjectCreationPage("new wizard creation page"); //$NON-NLS-1$
		page.setTitle(Messages.NewProjectAction_PageTitle);
		page.setDescription(Messages.NewProjectAction_PageDescription);
		dumWiz.addPage(page);
		
		WizardDialog wd = new WizardDialog(workbenchWnd.getShell(), dumWiz);
		
		wd.open();		
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {

	}
	
	/**
	 * Dummy wizard that handles the output of the wizard page.
	 * @author Arian Storch
	 * @since 11/01/11
	 *
	 */
	private class DummyWizard extends Wizard {

		@Override
		public boolean performFinish() {
			
			IProject proj = page.getProjectHandle();
			
			try {
				proj.create(null);
				proj.open(null);
				return true;
			} catch (CoreException e1) {
				
				e1.printStackTrace();
				return false;
			}
			
			
		}
		
	}

}

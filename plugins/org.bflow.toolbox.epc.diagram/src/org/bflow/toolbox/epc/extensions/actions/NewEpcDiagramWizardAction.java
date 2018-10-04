package org.bflow.toolbox.epc.extensions.actions;

import org.bflow.toolbox.epc.diagram.part.EpcCreationWizard;
import org.bflow.toolbox.epc.diagram.part.EpcDiagramEditorPlugin;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * Implements the IWorkbenchWindowActionDelegate to call the new epc diagram
 * wizard
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2009-11-21
 * @version 2010-01-04
 * 
 */
public class NewEpcDiagramWizardAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow _workbenchWindow;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#dispose()
	 */
	@Override
	public void dispose() {
		// Nothing to do here
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(org.eclipse.ui.IWorkbenchWindow)
	 */
	@Override
	public void init(IWorkbenchWindow window) {
		_workbenchWindow = window;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		try {
			EpcCreationWizard wizard = new EpcCreationWizard();
				
			ISelection selection = _workbenchWindow.getActivePage().getSelection();	
			StructuredSelection structSelection = new StructuredSelection(selection);
			
			wizard.init(_workbenchWindow.getWorkbench(), structSelection);
			
			WizardDialog wizardDialog = new WizardDialog(_workbenchWindow.getShell(), wizard);
			wizardDialog.open();
		} catch (Exception ex) {
			EpcDiagramEditorPlugin.getInstance().logError("Error on running EpcCreationWizard", ex);			
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}

}

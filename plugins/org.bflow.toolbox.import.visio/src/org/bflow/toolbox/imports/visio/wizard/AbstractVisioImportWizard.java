package org.bflow.toolbox.imports.visio.wizard;


import org.bflow.toolbox.imports.visio.runner.AbstractVisioWorkflowRunner;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

/**
 * @author Christian Boehme
 * 
 */
public abstract class AbstractVisioImportWizard extends Wizard implements IImportWizard {

	protected WizardPageVisioSelect sourcePage;
	protected WizardPageWorkflowFolder targetPage;
	protected AbstractVisioWorkflowRunner wf;

	public AbstractVisioImportWizard() {
		super();
	}

	public boolean performFinish() {

		wf.setTargetPath(targetPage.getTargetPath());
		boolean success = wf.runWorkflow(sourcePage.getVisioDocumentFile());
		showDialog(success);
		try {
			ResourcesPlugin.getWorkspace().getRoot().refreshLocal(
					IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return success;
	}

	private void showDialog(boolean success) {
		MessageBox box;
		String message;
		if (success) {
			box = new MessageBox(new Shell(), SWT.ICON_WORKING);
			message = "Import succeeded.";
		} else {
			box = new MessageBox(new Shell(), SWT.ICON_ERROR);
			message = "Import aborted.";
		}
		box.setText("visio2bflow");
		box.setMessage(message);
		box.open();
	}

	public abstract void init(IWorkbench workbench, IStructuredSelection selection);

	public void addPages() {
		addPage(sourcePage);
		addPage(targetPage);
	}

}

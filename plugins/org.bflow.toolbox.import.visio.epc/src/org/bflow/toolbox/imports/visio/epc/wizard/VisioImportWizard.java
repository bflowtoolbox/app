package org.bflow.toolbox.imports.visio.epc.wizard;

import org.bflow.toolbox.imports.visio.epc.Activator;
import org.bflow.toolbox.imports.visio.epc.runner.VisioWorkflowRunner;
import org.bflow.toolbox.imports.visio.wizard.AbstractVisioImportWizard;
import org.bflow.toolbox.imports.visio.wizard.WizardPageVisioSelect;
import org.bflow.toolbox.imports.visio.wizard.WizardPageWorkflowFolder;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;

/**
 * @author Christian Boehme
 * 
 */
public class VisioImportWizard extends AbstractVisioImportWizard {


	public VisioImportWizard() {
		super();
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle("Visio Import Wizard");
		setNeedsProgressMonitor(true);
		wf = new VisioWorkflowRunner();
		sourcePage = new WizardPageVisioSelect("Select Visio File", wf, "/src/model/EPC_M.VSS", "Import a Visio EPC", "EPC_M.VSS", Activator.getDefault());
		targetPage = new WizardPageWorkflowFolder("Select target Folder",
				selection);
	}
}

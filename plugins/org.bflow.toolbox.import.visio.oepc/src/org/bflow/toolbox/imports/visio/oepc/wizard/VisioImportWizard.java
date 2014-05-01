package org.bflow.toolbox.imports.visio.oepc.wizard;

import org.bflow.toolbox.imports.visio.oepc.Activator;
import org.bflow.toolbox.imports.visio.oepc.runner.VisioWorkflowRunner;
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
		sourcePage = new WizardPageVisioSelect("Select Visio File", wf, "/src/model/oepk.vss", "Import a Visio oEPC", "oEPK.vss", Activator.getDefault());
		targetPage = new WizardPageWorkflowFolder("Select target Folder",
				selection);
	}

}

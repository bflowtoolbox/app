/**
 * 
 */
package org.bflow.toolbox.export.visio.epc.wizard;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.bflow.toolbox.export.visio.epc.Activator;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.mwe.core.WorkflowRunner;
import org.eclipse.emf.mwe.core.monitor.NullProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

/**
 * @author Christian Boehme
 * 
 */
public class EpcExportWizard extends Wizard implements IExportWizard {

	private EpcModelSelectionWizardPage epcModelSelectionWizardPage;
	private OutputFileSelectionWizardPage outputFileSelectionWizardPage;

	public EpcExportWizard() {
		super();
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle("Epc Export Wizard");
		epcModelSelectionWizardPage = new EpcModelSelectionWizardPage(
				"Epc Export Wizard", selection);
		outputFileSelectionWizardPage = new OutputFileSelectionWizardPage(
				"Epc Export Wizard");

		this.addPage(epcModelSelectionWizardPage);
		this.addPage(outputFileSelectionWizardPage);

	}

	public boolean performFinish() {
		boolean success = false;

		String workflowFile = "workflow/BflowEpc2Visio.oaw";

		Map<String, String> properties = new HashMap<String, String>();
		Map<String, Object> slotContents = new HashMap<String, Object>();

		properties.put("epcMetamodelPackage", "org.bflow.toolbox.epc.EpcPackage");
		properties.put("bflowMetamodelPackage",
				"org.bflow.toolbox.bflow.BflowPackage");
		properties.put("notationMetamodelPackage",
				"org.eclipse.gmf.runtime.notation.NotationPackage");

		properties.put("epcModelFile", epcModelSelectionWizardPage
				.getEpcModelFile().getAbsolutePath().replace("\\", "/"));
		properties
				.put(
						"visioEmfMetamodelFile",
						"platform:/plugin/org.bflow.toolbox.export.visio.epc/src/metamodel/visio_epk_metamodel.ecore");
		
		String visioStencilPath = "";
		try {
			URL url = FileLocator.find(Activator.getDefault().getBundle(),
					new Path("src/metamodel/epk.vss"), null);
			URL metaModelURL;
			metaModelURL = FileLocator.toFileURL(url);
			File metamodel = new File(metaModelURL.getFile());
			visioStencilPath = metamodel.getAbsolutePath().replace("\\", "/");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		properties.put("visioStencilFile",visioStencilPath);
		properties.put("visioModelTargetFile", outputFileSelectionWizardPage.getTargetPath().replace("\\", "/"));

		success = new WorkflowRunner().run(workflowFile, new NullProgressMonitor(), properties, slotContents);

		showDialog(success);
		return success;
	}

	private void showDialog(boolean success) {
		MessageBox box;
		String message;
		if (success) {
			box = new MessageBox(new Shell(), SWT.ICON_WORKING);
			message = "Export succeeded.";
		} else {
			box = new MessageBox(new Shell(), SWT.ICON_ERROR);
			message = "Export aborted.";
		}
		box.setText("epc2visio");
		box.setMessage(message);
		box.open();
	}

}

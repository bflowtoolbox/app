package org.infai.m3b.visio.emf.oaw;

import java.io.File;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.infai.m3b.visio.emf.exporter.VisioEmfDocumentExporter;
import org.infai.m3b.visio.emf.visiostub.IVApplication;
import org.infai.m3b.visio.emf.visiostub.IVDocument;
import org.infai.m3b.visio.emf.visioutil.VisioUtil;

/**
 * 
 * @author
 * @version modified by Arian Storch 20/01/11
 */
public class VisioModelWriter extends AbstractWorkflowComponent {

	protected String visioModelFilePath;
	protected HashSet<File> visioStencilFiles;
	protected String modelInputSlot = "modelInputSlot";

	public VisioModelWriter() {
		super();
		this.visioStencilFiles = new HashSet<File>();
	}

	public void setVisioModelFilePath(final String value) {
		this.visioModelFilePath = value;
	}

	public void setVisioStencilFilePath(final String value) {

		String[] stencils = value.split(";");
		for (String stencil : stencils) {
			this.visioStencilFiles.add(new File(stencil));
		}
	}

	public void setModelInputSlot(String value) {
		this.modelInputSlot = value;
	}

	@Override
	protected void invokeInternal(WorkflowContext ctx, ProgressMonitor monitor,
			Issues issues) {
		IVApplication visioApp = VisioUtil.openVisioApplication();

		try {
			EObject emfModel = (EObject) ctx.get(modelInputSlot);

			VisioEmfDocumentExporter exporter = new VisioEmfDocumentExporter(
					visioApp);
			IVDocument visioDocument = exporter.exportVisioEmfDocument(
					emfModel, visioStencilFiles);
			VisioUtil.saveVisioDocument(visioApp, visioDocument, new File(
					visioModelFilePath));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			VisioUtil.closeVisioApplication(visioApp);
		}

	}

	@Override
	public void checkConfiguration(Issues issues) {

	}

}

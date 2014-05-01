package org.infai.m3b.visio.emf.oaw;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.infai.m3b.visio.emf.importer.VisioDocumentImporter;
import org.infai.m3b.visio.emf.visiostub.IVApplication;
import org.infai.m3b.visio.emf.visioutil.VisioUtil;

/**
 * @author Christian Boehme
 * @version modified by Arian Storch 20/01/11
 */

public class VisioModelReader extends AbstractWorkflowComponent {

	protected String visioModelFilePath;
	protected boolean searchRegisteredStencils = false;
	protected HashSet<File> visioStencilFiles;
	protected String modelOutputSlot = "modelOutputSlot";
	protected String metamodelOutputSlot = "metamodelOutputSlot";
	protected File visioFile;

	public VisioModelReader() {
		super();
		this.visioStencilFiles = new HashSet<File>();
	}

	public void setVisioModelFilePath(final String value) {
		this.visioModelFilePath = value;

	}

	public void setSearchRegisteredStencils(final boolean value) {
		this.searchRegisteredStencils = value;
	}

	public void setVisioStencilFilePath(final String value) {

		String[] stencils = value.split(";");
		for (String stencil : stencils) {
			this.visioStencilFiles.add(new File(stencil));
		}
	}

	public void setVisioStencilFile(final String path) {
		this.visioStencilFiles.add(new File(path));
	}

	public void setModelOutputSlot(String value) {
		this.modelOutputSlot = value;
	}

	public void setMetamodelOutputSlot(String value) {
		this.metamodelOutputSlot = value;
	}

	@Override
	protected void invokeInternal(WorkflowContext ctx, ProgressMonitor monitor,
			Issues issues) {
		IVApplication visioApp = VisioUtil.openVisioApplication();
		VisioDocumentImporter importer = new VisioDocumentImporter(visioApp);

		try {
			EObject document = importer.importVisioDocument(visioFile,
					visioStencilFiles, searchRegisteredStencils);
			ctx.set(modelOutputSlot, document);
			ctx.set(metamodelOutputSlot, importer.getEmfVisioMetamodel());
		} catch (Exception e1) {

			e1.printStackTrace();
		} finally {
			VisioUtil.closeVisioApplication(visioApp);
		}

	}

	@Override
	public void checkConfiguration(Issues issues) {
		visioFile = new File(visioModelFilePath);
		if (!visioFile.exists()) {
			issues.addError("Visio File does not exist!");
		}

		if (!visioStencilFiles.isEmpty()) {
			for (Iterator<File> it = visioStencilFiles.iterator(); it.hasNext();) {
				File f = ((File) it.next());
				if (!f.exists()) {
					issues.addError("Error: Visio Stencil " + f
							+ " does not exist!");
				}
			}
		}

	}

}

package org.infai.m3b.visio.emf.oaw;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.infai.m3b.visio.emf.emfutil.EmfVisioMetamodel;
import org.infai.m3b.visio.emf.importer.VisioStencilImporter;
import org.infai.m3b.visio.emf.visiostub.IVApplication;
import org.infai.m3b.visio.emf.visioutil.VisioUtil;

/**
 * 
 * @author
 * @version modified by Arian Storch 20/01/11
 * 
 */
public class VisioStencilReader extends AbstractWorkflowComponent {

	protected HashSet<File> visioStencilFiles;
	protected String metamodelOutputSlot = "metamodelOutputSlot";

	public VisioStencilReader() {
		super();

		this.visioStencilFiles = new HashSet<File>();
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

	public void setMetamodelOutputSlot(String value) {
		this.metamodelOutputSlot = value;
	}

	@Override
	protected void invokeInternal(WorkflowContext ctx, ProgressMonitor monitor,
			Issues issues) {
		IVApplication visioApp = VisioUtil.openVisioApplication();
		VisioStencilImporter importer = new VisioStencilImporter(visioApp);

		try {
			EmfVisioMetamodel emfMetamodel = importer
					.importVisioStencil(this.visioStencilFiles);
			ctx.set(metamodelOutputSlot, emfMetamodel.getEVisioMetamodel());
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			VisioUtil.closeVisioApplication(visioApp);
		}

	}

	@Override
	public void checkConfiguration(Issues issues) {
		if (visioStencilFiles.isEmpty()) {
			issues.addError("Error: No Visio stencil added");
		} else {
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

package org.infai.m3b.visio.emf.importer.job;

import java.io.File;
import java.util.HashSet;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.infai.m3b.visio.emf.emfutil.EmfUtil;
import org.infai.m3b.visio.emf.importer.Activator;
import org.infai.m3b.visio.emf.importer.VisioDocumentImporter;
import org.infai.m3b.visio.emf.visiostub.IVApplication;
import org.infai.m3b.visio.emf.visioutil.VisioUtil;


public class VisioDocumentImportJob extends Job{
	
	private IPath emfModelPath;
	private IPath emfMetamodelPath;
	private File visioModelPath;
	private HashSet<File> visioStencilPath;
	private boolean search;

	public VisioDocumentImportJob(File visioModelPath, HashSet<File> visioStencilPath, IPath emfModelPath, 
			IPath emfMetamodelPath, boolean search) {
		
		super("Importing a Visio document and stencil to EMF.");
		this.emfMetamodelPath = emfMetamodelPath;
		this.emfModelPath = emfModelPath;
		this.visioModelPath = visioModelPath;
		this.visioStencilPath = visioStencilPath;
		this.search = search;
	}
		
	
	protected IStatus run(IProgressMonitor monitor) {
		
		Status status = null;
		
		VisioDocumentImporter importer = null;
		monitor.beginTask("Transforming document", 100);
		IVApplication visioApplication = VisioUtil.openVisioApplication();
		
		try {
			monitor.worked(10);
			importer = new VisioDocumentImporter(visioApplication);
			EObject emfDocument = importer.importVisioDocument(visioModelPath, visioStencilPath, search);
			monitor.worked(80);
			EmfUtil.saveAsEcore(emfMetamodelPath.toFile().toString(), importer.getEmfVisioMetamodel());
			monitor.worked(90);
			EmfUtil.saveAsXmi(emfModelPath.toFile().toString(), emfDocument, true);
			monitor.worked(100);
			status = new Status(IStatus.OK, Activator.PLUGIN_ID, IStatus.OK, "Job has finished.", null);
		}
		catch(Exception e) {
			
			
			Activator plugin = Activator.getDefault();
			ILog log = plugin.getLog();
			Status stats = new Status(IStatus.ERROR, plugin.getBundle().getSymbolicName(), 
					IStatus.ERROR, "The program for the Visio-import could not be executed properly.", e);
			log.log(stats);
			status = stats;
			
		}
		finally {
			VisioUtil.closeVisioApplication(visioApplication);
		}
		
		return status;
		
	}
}

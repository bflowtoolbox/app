package org.infai.m3b.visio.emf.importer.job;

import java.io.File;
import java.util.HashSet;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EPackage;
import org.infai.m3b.visio.emf.emfutil.EmfUtil;
import org.infai.m3b.visio.emf.importer.Activator;
import org.infai.m3b.visio.emf.importer.VisioStencilImporter;
import org.infai.m3b.visio.emf.visiostub.IVApplication;
import org.infai.m3b.visio.emf.visioutil.VisioUtil;



public class VisioStencilImportJob extends Job{
	
	private IPath emfMetamodelPath;
	private HashSet<File> visioStencilPath;

	public VisioStencilImportJob(HashSet<File> visioStencilPath, IPath emfMetamodel) {
		
		super("Importing a Visio stencil to EMF.");
		this.emfMetamodelPath = emfMetamodel;
		this.visioStencilPath = visioStencilPath;
	}
	
	protected IStatus run(IProgressMonitor monitor) {
			
		Status status = null;
		VisioStencilImporter importer = null;
		IVApplication visioApplication = VisioUtil.openVisioApplication();
		
		monitor.beginTask("Transforming stencil", 100);
		
		try{
			importer = new VisioStencilImporter(visioApplication);
			monitor.worked(10);
			EPackage emfMetamodel = importer.importVisioStencil(visioStencilPath).getEVisioMetamodel();
			monitor.worked(90);
			EmfUtil.saveAsEcore(emfMetamodelPath.toFile().toString(), emfMetamodel);
			monitor.worked(100);
			status = new Status(IStatus.OK, Activator.PLUGIN_ID, IStatus.OK, "Job has finished.", null);
		}
		catch(Exception e) {
			
			Activator plugin = Activator.getDefault();
			ILog log = plugin.getLog();
			status = new Status(IStatus.ERROR, plugin.getBundle().getSymbolicName(), 
					IStatus.ERROR, "The Visio stencil import could not be executed properly.", e);
			log.log(status);
		}
		finally {
			VisioUtil.closeVisioApplication(visioApplication);
		}
		
		return status;
	}
}

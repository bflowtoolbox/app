package org.infai.m3b.visio.emf.exporter;

import java.io.File;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.infai.m3b.visio.emf.emfutil.EmfUtil;
import org.infai.m3b.visio.emf.emfutil.EmfVisioMetamodel;
import org.infai.m3b.visio.emf.exporter.transformation.EmfModel2VisioModel;
import org.infai.m3b.visio.emf.visiostub.IVApplication;
import org.infai.m3b.visio.emf.visiostub.IVDocument;
import org.jawin.COMException;

public class VisioEmfDocumentExporter {
	
	private IVApplication visioApp;	
	
	public VisioEmfDocumentExporter(IVApplication visioApp) {
		
		this.visioApp = visioApp;
	}
	
	public IVDocument exportVisioEmfDocument(File emfModel, HashSet<File> visioStencils) {
		
		Resource modelResource = EmfUtil.loadXmi(emfModel.toString());
		EmfVisioMetamodel evm = new EmfVisioMetamodel();
		
		EObject firstObject = modelResource.getContents().get(0);
		IVDocument visioDocument = null;
		
		if(firstObject != null)
			if(firstObject.eClass().getName().equals(evm.getEVisioDocument().getName()))
				visioDocument = exportVisioEmfDocument(firstObject, visioStencils);
		
		return visioDocument;
	}
	
	public IVDocument exportVisioEmfDocument(EObject emfModel, HashSet<File> visioStencilPaths) {
		
		IVDocument visioDocument = null;
		try {
			visioDocument = this.visioApp.getDocuments().Add("");	
			HashSet<IVDocument> visioStencils = new HashSet<IVDocument>();
			
			//load all Visio stencils
			for(File visioStencilPath : visioStencilPaths) {
				try {
					IVDocument visioStencil = this.visioApp.getDocuments().Open(visioStencilPath.toString());
					visioStencils.add(visioStencil);
					
				} catch (COMException e) {
					
					e.printStackTrace();
				}
			}
			EmfModel2VisioModel emf2visio = new EmfModel2VisioModel(visioApp);
			visioDocument = emf2visio.createVisioModel(emfModel, visioDocument, visioStencils);
			
		} catch (COMException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return visioDocument;
	}
	
	public IVApplication getVisioApp() {
		return visioApp;
	}
}

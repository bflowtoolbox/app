/*******************************************************************************
 * Copyright (c) 2009 Heiko Kern.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Heiko Kern - initial API and implementation
 *******************************************************************************/
package org.infai.m3b.visio.emf.importer;

import java.io.File;
import java.util.HashSet;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.infai.m3b.visio.emf.emfutil.EmfVisioMetamodel;
import org.infai.m3b.visio.emf.importer.transformation.VisioModel2EmfModel;
import org.infai.m3b.visio.emf.visiostub.IVApplication;
import org.infai.m3b.visio.emf.visiostub.IVDocument;
import org.infai.m3b.visio.emf.visioutil.VisioModelUtil;
import org.infai.m3b.visio.emf.visioutil.VisioStencilUtil;

/**
 * @author Heiko Kern
 *
 */
public class VisioDocumentImporter {

	private IVApplication visioApp = null;
	private EmfVisioMetamodel emfVisioMetamodel;
	
	public VisioDocumentImporter(IVApplication visioApp) {
		this.visioApp = visioApp;
	}	
	
	public EObject importVisioDocument(File visioModelPath, HashSet<File> visioStencilPaths) throws Exception {
		
		VisioModelUtil mr = new VisioModelUtil(this.visioApp);
		IVDocument visioModel = mr.openVisioDocument(visioModelPath);
		
		//System.out.println("begin");
		VisioStencilUtil sr = new VisioStencilUtil(this.visioApp);
		HashSet<File> usedStencils = (HashSet<File>)sr.getUsedStencils(visioModelPath, visioStencilPaths);
	
		VisioStencilImporter stencilImporter = new VisioStencilImporter(visioApp);
		emfVisioMetamodel = stencilImporter.importVisioStencil(usedStencils);
		
		VisioModel2EmfModel model2emf = new VisioModel2EmfModel(visioApp, emfVisioMetamodel);
		EObject emfDocument = model2emf.createEmfVisioModel(visioModel);
		
		if(model2emf.hasDocumentPackage()) {
			
			EPackage documentPackage = model2emf.getDocumentPackage();
			this.emfVisioMetamodel.addSubPackage(documentPackage);
		}
		
		//System.out.println("end complete");
		
		return emfDocument;	
	}
	
	public EObject importVisioDocument(File visioModelPath, HashSet<File> visioStencilPath, boolean searchForStencils) throws Exception {
		
		if(searchForStencils) {
			
			VisioStencilUtil sr = new VisioStencilUtil(this.visioApp);
			visioStencilPath.addAll(sr.getMyStencils());	
			visioStencilPath.addAll(sr.getDefaultStencils());
		}
				
		return importVisioDocument(visioModelPath, visioStencilPath); 
	}
	
	public IVApplication getVisioApplication() {
		return visioApp;
	}	
	
	public EPackage getEmfVisioMetamodel() {
		return this.emfVisioMetamodel.getEVisioMetamodel();
	}
}

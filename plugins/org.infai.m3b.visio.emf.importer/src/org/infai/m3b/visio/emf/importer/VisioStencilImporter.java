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

import org.eclipse.emf.ecore.EPackage;
import org.infai.m3b.visio.emf.emfutil.EmfVisioMetamodel;
import org.infai.m3b.visio.emf.importer.transformation.VisioStencil2EmfMetamodel;
import org.infai.m3b.visio.emf.visiostub.IVApplication;
import org.infai.m3b.visio.emf.visiostub.IVDocument;
import org.infai.m3b.visio.emf.visioutil.VisioStencilUtil;
/**
 * @author Heiko Kern
 *
 */
public class VisioStencilImporter {

	private IVApplication visioApp = null;
	
	public VisioStencilImporter(IVApplication visioApp) {
		
		this.visioApp = visioApp;
	}	
	
	public EmfVisioMetamodel importVisioStencil(HashSet<File> visioStencilPaths, EmfVisioMetamodel evm) throws Exception {
		
		VisioStencilUtil sr = new VisioStencilUtil(this.visioApp);
		VisioStencil2EmfMetamodel stencil2emf = new VisioStencil2EmfMetamodel(visioApp, evm);
		
		for(File visioStencilFile : visioStencilPaths) {
			IVDocument visioStencilDocument = sr.openStencil(visioStencilFile);
			EPackage emfStencilPackage = stencil2emf.createEmfMetamodel(visioStencilDocument);
			
			evm.addSubPackage(emfStencilPackage);
		}
		
		return evm;
	}
	
	public EmfVisioMetamodel importVisioStencil(HashSet<File> visioStencilPaths) throws Exception {
		
		EmfVisioMetamodel evm = new EmfVisioMetamodel();
		importVisioStencil(visioStencilPaths, evm);
		return evm;
	}
		
	public IVApplication getVisioApplication() {
		return visioApp;
	}	
	
	
	
}

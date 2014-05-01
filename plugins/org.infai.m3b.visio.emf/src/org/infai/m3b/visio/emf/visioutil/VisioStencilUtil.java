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
package org.infai.m3b.visio.emf.visioutil;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.infai.m3b.visio.emf.visiostub.IVApplication;
import org.infai.m3b.visio.emf.visiostub.IVCell;
import org.infai.m3b.visio.emf.visiostub.IVDocument;
import org.infai.m3b.visio.emf.visiostub.IVMaster;
import org.infai.m3b.visio.emf.visiostub.IVShape;
import org.infai.m3b.visio.emf.visiostub.VisCellIndices;
import org.infai.m3b.visio.emf.visiostub.VisDocumentTypes;
import org.infai.m3b.visio.emf.visiostub.VisSectionIndices;
import org.infai.m3b.visio.emf.visiostub.VisUnitCodes;
import org.infai.m3b.visio.emf.visiostub.tagVisOpenSaveArgs;
import org.jawin.COMException;


public class VisioStencilUtil {
	
	
	private IVApplication visioApp;
	
	public VisioStencilUtil(IVApplication visioApp) {
		
		this.visioApp = visioApp;
	}
	
	public boolean isStencilUsedInModel(IVDocument stencil, IVDocument visioModel)
    {
        VisioModelUtil modelReader = new VisioModelUtil(this.visioApp);
        HashSet<String> mastersInModel = new HashSet<String>();
        HashSet<String> mastersInStencil = new HashSet<String>();
        
		try {
			for(IVMaster master : modelReader.getMastersInDocument(visioModel))
	        	mastersInModel.add(master.getUniqueID());	        	
	        
			for(IVMaster master : new VisioList<IVMaster>(stencil.getMasters(), IVMaster.class)) {
				mastersInStencil.add(master.getUniqueID());
			}
			
			for(String masterInStencil : mastersInStencil) {
	        	if(mastersInModel.contains(masterInStencil))
	        		return true;
	        }
			
		} catch (COMException e) {
			
			VisioUtil.closeVisioApplication(this.visioApp);
			e.printStackTrace();
		}
        
        return false;
    }
	
	public Collection<File> getUsedStencils(File visioModel, Collection<File> stencils)
    {
		HashSet<File> usedStencils = new HashSet<File>();
		VisioStencilUtil sr = new VisioStencilUtil(this.visioApp);
		
		VisioModelUtil mr = new VisioModelUtil(this.visioApp);
		IVDocument visioDocument = mr.openVisioDocument(visioModel);
		
		for(File stencil : stencils) {
			IVDocument stencilDocument = sr.openStencil(stencil);
			
			if(this.isStencilUsedInModel(stencilDocument, visioDocument))
				usedStencils.add(stencil);
		}
        return usedStencils;
    }
	
	public ArrayList<File> getMyStencils() {
		
		ArrayList<File> myStencils = new ArrayList<File>();
		
		try {
			File myShapesPath = new File(visioApp.getMyShapesPath());
			File[] stencilsInDirectory = myShapesPath.listFiles(new VisioFileFilter("vss"));
			for(File stencilInDirectory : stencilsInDirectory)
					myStencils.add(stencilInDirectory);
		
		} catch (COMException e) {
			
			VisioUtil.closeVisioApplication(this.visioApp);
			e.printStackTrace();
		}
		return myStencils;
	}
	
	
	public ArrayList<IVDocument> getOpenStencils() {
		
		ArrayList<IVDocument> stencils = new ArrayList<IVDocument>();
		try {
			for(IVDocument document : new VisioList<IVDocument>(visioApp.getDocuments(), IVDocument.class))
			{
				if(document.getType() == VisDocumentTypes.visTypeStencil)
				{
					stencils.add(document);
				}
			}
		} catch (COMException e) {
			VisioUtil.closeVisioApplication(this.visioApp);
			e.printStackTrace();
		}
		return stencils;
	}
	
	
	public IVDocument openStencil(File visioStencilPath) {
		
		IVDocument visioStencil = new IVDocument();
		
		try {
			visioStencil = this.visioApp.getDocuments().OpenEx(visioStencilPath.toString(), (short)tagVisOpenSaveArgs.visOpenRO);
			
		} catch (COMException e) {

			VisioUtil.closeVisioApplication(this.visioApp);
			e.printStackTrace();
		}
		return visioStencil;
	}
	
	
	public ArrayList<VisioProperty> getMasterProperty(IVMaster visioMaster)
    {
        ArrayList<VisioProperty> visioPropertyList = new ArrayList<VisioProperty>();
        try {
        	
	        VisioList<IVShape> visioMasterShape = new VisioList<IVShape>(visioMaster.getShapes(), IVShape.class);
		
	        for(IVShape masterShape : visioMasterShape)
	        {
	            short propertySection = (short)VisSectionIndices.visSectionProp;
	            if (masterShape.getSectionExists(propertySection, (short)0) != 0)
	            {
	                for (short i = 0; i < masterShape.getRowCount(propertySection); i++)
	                {
	                    IVCell cellSrcP = masterShape.getCellsSRC(propertySection, i, (short)VisCellIndices.visCustPropsLabel);
	                    String propertyName = (String)cellSrcP.get("ResultStr", VisUnitCodes.visUnitsString);
	                    
	                    if(!propertyName.trim().isEmpty())
                        {
	                    	VisioProperty property = new VisioProperty(propertyName, new Short(i).toString(), null);
                            IVCell cellSrcV = masterShape.getCellsSRC(propertySection, i, (short)VisCellIndices.visCustPropsValue);
                            String propertyValue = (String)cellSrcV.get("ResultStr", VisUnitCodes.visNoCast);

                            if (!propertyValue.equals("0,0000"))
                                property.setValue(propertyValue);
                          

                            visioPropertyList.add(property);
                        }
	                }
	            }
	        }
        
        } catch (COMException e) {

        	VisioUtil.closeVisioApplication(this.visioApp);
			e.printStackTrace();
		}
        return visioPropertyList;
    }
	
	
	public HashSet<File> getDefaultStencils() {
		
		HashSet<File> stencils = new HashSet<File>();
		
		for(File stencilFile : VisioUtil.getOfficePath().listFiles(new VisioFileFilter("vss"))) {
			stencils.add(stencilFile);
		}
		
		for(File file : VisioUtil.getOfficePath().listFiles()) {
			if(file.isDirectory()) {
				for(File stencilFile : file.listFiles(new VisioFileFilter("vss"))) {
					stencils.add(stencilFile);
				}
			}
		}
		
		return stencils;
	}
	
	public IVMaster getMaster(IVDocument stencil, String masterId) {
		
		try {
			VisioList<IVMaster> visioMasters = new VisioList<IVMaster>(stencil.getMasters(),
					IVMaster.class);
			
			for(IVMaster master : visioMasters) {
				if(master.getUniqueID().equals(masterId))
					return master;
			}
			
		} catch (COMException e) {
			
			VisioUtil.closeVisioApplication(this.visioApp);
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean isConnectionMaster(IVMaster master) {
		
		try {
			if ((short)master.getOneD() == -1)
				return true;
		} catch (COMException e) {
			
			VisioUtil.closeVisioApplication(this.visioApp);
			e.printStackTrace();
		}
		return false;
	}

}

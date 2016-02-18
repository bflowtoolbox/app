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
import java.util.HashSet;

import org.infai.m3b.visio.emf.visiostub.IVApplication;
import org.infai.m3b.visio.emf.visiostub.IVCell;
import org.infai.m3b.visio.emf.visiostub.IVDocument;
import org.infai.m3b.visio.emf.visiostub.IVMaster;
import org.infai.m3b.visio.emf.visiostub.IVPage;
import org.infai.m3b.visio.emf.visiostub.IVRow;
import org.infai.m3b.visio.emf.visiostub.IVSelection;
import org.infai.m3b.visio.emf.visiostub.IVShape;
import org.infai.m3b.visio.emf.visiostub.VisCellIndices;
import org.infai.m3b.visio.emf.visiostub.VisRowIndices;
import org.infai.m3b.visio.emf.visiostub.VisSectionIndices;
import org.infai.m3b.visio.emf.visiostub.VisSpatialRelationCodes;
import org.infai.m3b.visio.emf.visiostub.tagVisOpenSaveArgs;
import org.jawin.COMException;
import org.jawin.DispatchPtr;

/**
 * This class allows access on Visio models.
 * @author Heiko Kern
 *
 */
public class VisioModelUtil {
	
	private IVApplication visioApp;
	
	public VisioModelUtil(IVApplication visioApp) {
		
		this.visioApp = visioApp;
	}
	
	/**
	 * Returns shapes contained in another shape.
	 * @param visioShape
	 * @param tolerance
	 * @returnList of shapes contained in another shape
	 */
	public ArrayList<IVShape> getSpatialContainedShapes(IVShape visioShape, double tolerance)
    {
        short containedRelation = (short)VisSpatialRelationCodes.visSpatialContain;
        ArrayList<IVShape> allContainedShapes = new ArrayList<IVShape>();
       
        //alle enthaltenen Shapes eines Shapes
		try {
				
			IVSelection res = new IVSelection();
	        DispatchPtr dispPtr = (DispatchPtr)visioShape.getN("SpatialNeighbors", new Object[] {new Short(containedRelation),new Double(tolerance),new Short((short)0)});
	        res.stealUnknown(dispPtr);
					
	        VisioList<IVShape> containedShapes = new VisioList<IVShape>(res, IVShape.class);
	                
	        //Wenn Shapes verdeckt sind, dann nicht enthalten
	        for(IVShape s : containedShapes)
	        {
	            boolean greaterIndex = s.getIndex() > visioShape.getIndex();
	            boolean noFillPattern = visioShape.getCells("FillPattern").getResultIU() == 0;
	            boolean transparent = visioShape.getCells("FillForegndTrans").getResultIU() >= 0.8 || 
	                visioShape.getCells("FillBkgndTrans").getResultIU() >= 0.8;
	
	            if (greaterIndex || noFillPattern || transparent)
	                allContainedShapes.add(s);
	        }
		} catch (COMException e) {
			
			VisioUtil.closeVisioApplication(this.visioApp);
			e.printStackTrace();
		}
        
        return allContainedShapes;
    }
	
	
	public boolean isConnectionShape(IVShape visioShape) {
		
		try {
			return visioShape.getConnects().getCount()>0;
		} catch (COMException e) {
			
			VisioUtil.closeVisioApplication(this.visioApp);
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<IVShape> getShapesFromDocument(IVDocument document)
    {
        ArrayList<IVShape> allShapes = new ArrayList<IVShape>();

        try {
			for(IVPage page : new VisioList<IVPage>(document.getPages(), IVPage.class)) {
				VisioList<IVShape> shapes = new VisioList<IVShape>(page.getShapes(), IVShape.class);
				allShapes.addAll(shapes);
			}
		} catch (COMException e) {
			
			VisioUtil.closeVisioApplication(this.visioApp);
			e.printStackTrace();
		}            
        return allShapes;
    }

    public HashSet<IVMaster> getMastersInDocument(IVDocument document)
    {
        HashSet<IVMaster> allMasters = new HashSet<IVMaster>();

        for(IVShape shape : getShapesFromDocument(document)) {
			try {
				if(!shape.getMaster().toString().matches(".*\\[0,0\\]"))
					allMasters.add(shape.getMaster());
			} catch (COMException e) {

				VisioUtil.closeVisioApplication(this.visioApp);
				e.printStackTrace();
			}
        }
        return allMasters;
    }
    
    public IVDocument openVisioDocument(File visioDocumentPath) {
    	
    	IVDocument visioModel = new IVDocument();
		
    	try {
			visioModel = visioApp.getDocuments().OpenEx(visioDocumentPath.toString(), (short)tagVisOpenSaveArgs.visOpenCopy);
		} catch (COMException e) {
			VisioUtil.closeVisioApplication(this.visioApp);
			e.printStackTrace();
		}
		
		return visioModel;
    }
    
    public boolean hasMaster(IVShape shape) {
		
		boolean hasMaster = false;
		
		try {
			hasMaster = !shape.getMaster().toString().matches(".*\\[0,0\\]");
		} catch (COMException e) {
			VisioUtil.closeVisioApplication(this.visioApp);
			e.printStackTrace();
		}
		return hasMaster;
	}
    
   
    
    public boolean isDoubleUnit(Integer unit) {
    	
    	final HashSet<Integer> doubleUnits = new HashSet<Integer>();
    	doubleUnits.add(80);
    	doubleUnits.add(36);
    	doubleUnits.add(69);
    	doubleUnits.add(52);
    	doubleUnits.add(54);
    	doubleUnits.add(111);
    	doubleUnits.add(81);
    	doubleUnits.add(53);
    	doubleUnits.add(64);
    	doubleUnits.add(42);
    	doubleUnits.add(44);
    	doubleUnits.add(45);
    	doubleUnits.add(46);
    	doubleUnits.add(47);
    	doubleUnits.add(43);
    	doubleUnits.add(67);
    	doubleUnits.add(66);
    	doubleUnits.add(37);
    	doubleUnits.add(65);
    	doubleUnits.add(73);
    	doubleUnits.add(72);
    	doubleUnits.add(71);
    	doubleUnits.add(74);
    	doubleUnits.add(68);
    	doubleUnits.add(70);
    	doubleUnits.add(84);
    	doubleUnits.add(76);
    	doubleUnits.add(32);
    	doubleUnits.add(63);
    	doubleUnits.add(33);
    	doubleUnits.add(49);
    	doubleUnits.add(51);
    	doubleUnits.add(50);
    	doubleUnits.add(83);
    	doubleUnits.add(85);
    	doubleUnits.add(84);
    	doubleUnits.add(75);
    	
    	
    	if(doubleUnits.contains(unit)) 
    		return true;
    	
    	return false;
    }
    
    
    public void connectShapes(IVShape srcShape, IVShape trgShape, IVShape conShape) {
    	
    	short section = VisSectionIndices.visSectionObject;
    	short xForm1D = VisRowIndices.visRowXForm1D;
    	short beginX = VisCellIndices.vis1DBeginX;
    	short xFormOut = VisRowIndices.visRowXFormOut;
    	short pinX = VisCellIndices.visXFormPinX;
    	short endX = VisCellIndices.vis1DEndX;
    	
    	try {
			IVCell beginXCell = conShape.getCellsSRC(section, xForm1D, beginX);
			IVCell endXCell = conShape.getCellsSRC(section, xForm1D, endX);
			IVCell pinXCellSrc = srcShape.getCellsSRC(section, xFormOut, pinX);
			IVCell pinXCellTrg = trgShape.getCellsSRC(section, xFormOut, pinX);
			
			beginXCell.invokeN("GlueTo", new Object[] {pinXCellSrc});
			endXCell.invokeN("GlueTo", new Object[] {pinXCellTrg});
    	
    	} catch (COMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public ArrayList<VisioProperty> getShapeProperty(IVShape visioShape)
    {
        ArrayList<VisioProperty> visioPropertyList = new ArrayList<VisioProperty>();
        try {
		
            short propertySection = (short)VisSectionIndices.visSectionProp;
            if (visioShape.getSectionExists(propertySection, (short)0) != 0)
            {
                for (short rowIndex = 0; rowIndex < visioShape.getRowCount(propertySection); rowIndex++)
                {
                	IVRow currentRow = visioShape.getSection(propertySection).getRow(rowIndex);
                	
                	//property name
                	IVCell propertyCell = new IVCell();
					DispatchPtr dispPtr = (DispatchPtr)currentRow.get("Cell", 2);
					propertyCell.stealUnknown(dispPtr);	
					String propertyName = (String)propertyCell.get("ResultStr", new Integer(propertyCell.getUnits()));
					dispPtr.close();
					propertyCell.close();
					
					//property type
					dispPtr = (DispatchPtr)currentRow.get("Cell", 0);
					propertyCell.stealUnknown(dispPtr);	
					String propertyType = Integer.toString(propertyCell.getUnits());
					
					//property value
					String propertyValue = (String)propertyCell.get("ResultStr", new Integer(propertyCell.getUnits()));
										
					VisioProperty vp = new VisioProperty(propertyName, Short.toString(rowIndex),  propertyType);
					vp.setValue(propertyValue);
					visioPropertyList.add(vp);
					dispPtr.close();
					propertyCell.close();
                }
            }
        
        } catch (COMException e) {

        	VisioUtil.closeVisioApplication(this.visioApp);
			e.printStackTrace();
		}
        return visioPropertyList;
    }

}

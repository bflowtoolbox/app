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
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.infai.m3b.visio.emf.visiostub.IVApplication;
import org.infai.m3b.visio.emf.visiostub.IVCell;
import org.infai.m3b.visio.emf.visiostub.IVDocument;
import org.infai.m3b.visio.emf.visiostub.IVShape;
import org.infai.m3b.visio.emf.visiostub.VisSectionIndices;
import org.jawin.COMException;
import org.jawin.donated.win32.Registry;
import org.jawin.donated.win32.RegistryConstants;
import org.jawin.win32.Ole32;

public class VisioUtil {
	
	public static File getOfficePath() {
		
		File officePath = null;
		
		try {
			int officeKey = Registry.OpenKey(RegistryConstants.HKEY_LOCAL_MACHINE, 
			"SOFTWARE\\Microsoft\\Office\\12.0\\Common\\InstallRoot");
			
			String officeValue = Registry.QueryStringValue(officeKey, "Path");
			officePath = new File(officeValue); 	
			Registry.CloseKey(officeKey);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (COMException e) {
			
			e.printStackTrace();
		}

		return officePath;
	}
	
	public static IVApplication openVisioApplication() {

		IVApplication visioApp = null;
		
		try {
			Ole32.CoInitialize();
			visioApp = new IVApplication("Visio.Application");
			visioApp.getWindow().setVisible(false);
			
		} catch (COMException e) {
			
			e.printStackTrace();
		}
		
		return visioApp;
	}
	
	public static void closeVisioApplication(IVApplication visioApp) {
		
		if(visioApp == null)
			return;
		
		try {
			visioApp.setAlertResponse((short)5);
			visioApp.Quit();
			Ole32.CoUninitialize();
		} catch (COMException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static void connectShapes(EObject emfConnectionShape, HashMap<EObject, IVShape> allShapes) {
		
		EList<EObject> emfConList = ((EList)emfConnectionShape.eGet(
				emfConnectionShape.eClass().getEStructuralFeature("visio_connectedShapes")));
		
		EObject emfSourceShape = emfConList.get(0);
		EObject emfTargetShape = emfConList.get(1);
		IVShape visioConnectionShape = allShapes.get(emfConnectionShape);
		IVShape visioSourceShape = allShapes.get(emfSourceShape);
		IVShape visioTargetShape = allShapes.get(emfTargetShape);
		
		try {
			IVCell conBeginCell = visioConnectionShape.getCells("BeginX");
			IVCell conEndCell = visioConnectionShape.getCells("EndX");
			IVCell beginCell = visioSourceShape.getCells("AlignBottom");
			IVCell endCell = visioTargetShape.getCells("AlignBottom");
			
	        conBeginCell.invoke("GlueTo", beginCell);   
	        conEndCell.invoke("GlueTo", endCell);
	        
		} catch (COMException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public static IVShape getVisioChildShape(EObject childEmfObject, IVShape visioParentShape) {
			
		try {
			VisioList<IVShape> visioShapes = new VisioList<IVShape>(visioParentShape.getShapes(), IVShape.class);
			
			
			String emfId = childEmfObject.eGet(childEmfObject.eClass().getEStructuralFeature("visioMasterShapeName")).toString();
			
			for(IVShape visioShape : visioShapes) {
				
				String visioId = visioShape.getMasterShape().getName();
				if(visioId.equals(emfId))
					return visioShape;				
			}	
		} catch (COMException e) {
		
			e.printStackTrace();
		}
		return null;
	}

	final static Field[] sectionFields = VisSectionIndices.class.getFields();
	
	public static HashSet<VisioShapeSheetSection> getShapeSheetSections(IVShape shape) throws COMException {
		
		HashSet<VisioShapeSheetSection> sections = new HashSet<VisioShapeSheetSection>();
		
		for(Field field : sectionFields) {
			
			String sectionName = field.getName();
			short sectionId = -1; 
			try {
				sectionId = (short)field.getInt(VisSectionIndices.class);
				if(shape.getSectionExists(sectionId, (short)0)!=0)
					sections.add(new VisioShapeSheetSection(sectionName, sectionId));
				
			} catch (IllegalArgumentException e) {
				
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				
				e.printStackTrace();
			}
		}
		return sections;
	}
	
	public static void saveVisioDocument(IVApplication visioApp, IVDocument document, File visioFilePath) {
		
		try {
			
			visioApp.setAlertResponse((short)5);
			document.SaveAs(visioFilePath.toString().replace("/", "\\"));
			
		} catch (COMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
}

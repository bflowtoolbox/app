package org.infai.m3b.visio.emf.exporter.transformation;

import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.infai.m3b.visio.emf.emfutil.EmfShapeSheetUtil;
import org.infai.m3b.visio.emf.emfutil.EmfUtil;
import org.infai.m3b.visio.emf.emfutil.EmfVisioMetamodel;
import org.infai.m3b.visio.emf.visiostub.IVApplication;
import org.infai.m3b.visio.emf.visiostub.IVDocument;
import org.infai.m3b.visio.emf.visiostub.IVMaster;
import org.infai.m3b.visio.emf.visiostub.IVPage;
import org.infai.m3b.visio.emf.visiostub.IVShape;
import org.infai.m3b.visio.emf.visioutil.VisioList;
import org.infai.m3b.visio.emf.visioutil.VisioModelUtil;
import org.infai.m3b.visio.emf.visioutil.VisioShapeSheetUtil;
import org.infai.m3b.visio.emf.visioutil.VisioUtil;
import org.jawin.COMException;
import org.jawin.DispatchPtr;

public class EmfModel2VisioModel {
	
	private IVApplication visioApp;
	
	private HashMap<EObject, IVShape> allVisioShapes;
	
	public EmfModel2VisioModel(IVApplication visioApp) {
		
			this.visioApp = visioApp;
			this.allVisioShapes = new HashMap<EObject, IVShape>();
	}

	
	public IVDocument createVisioModel(EObject emfModel, IVDocument visioDocument, HashSet<IVDocument> visioStencils) {
		
		//all stencil masters
		HashMap<String, IVMaster> allVisioMasters = registerVisioMasters(visioStencils);
		
		//all emf pages		
		EList<EObject> emfPages = (EList<EObject>)emfModel.eGet(emfModel.eClass().getEStructuralFeature("visioPages"));
		
		try {
			//creates a Visio document
			visioDocument = visioApp.getDocuments().Add("");
			
			//for each page
			for(int i=0 ; i < emfPages.size() ; i++) {
				
				//create Visio page
				EObject emfPage = emfPages.get(i);			
				IVPage visioPage = null;
				
				if(i==0) {
					//Visio has always a first page that cannot be deleted
					IVPage firstVisioPage = new IVPage();
			        DispatchPtr firstVisioPagePtr = (DispatchPtr)visioDocument.getPages().invoke("ItemU", new Short((short)1));
			        firstVisioPage.stealUnknown(firstVisioPagePtr);
			        visioPage = firstVisioPage;
				}
				else {
					visioPage = visioDocument.getPages().Add();
				}
				
				Object name = emfPage.eGet(emfPage.eClass().getEStructuralFeature("visioName"));
				if(name != null)
					visioPage.setName(name.toString());	
				
				//for each contained Visio shape
				EList<EObject> emfContainedShapes = (EList<EObject>)emfPage.eGet(emfPage.eClass().
						getEStructuralFeature("visioContainedShapes"));
				
				for(EObject emfShape : emfContainedShapes) {
					
					String masterId = EmfUtil.getVisioMasterId(emfShape.eClass());
					IVMaster visioMaster = allVisioMasters.get(masterId);
					createVisioShape(emfShape, visioPage, visioMaster);	
				}
				
				//alle Verbindungen zeichnen
				for(EObject emfShape : emfContainedShapes) {
					EClass conClazz = (EClass)emfShape.eClass().getEPackage().
						getESuperPackage().getEClassifier("EVisioConnectionShape");
					EClass shapeClazz = emfShape.eClass();
					
					for(EClass type : shapeClazz.getESuperTypes()) {
						if(type.getName().equals("EVisioConnectionShape"))
							createVisioConnection(emfShape);
					}
														
					//if( conClazz.isSuperTypeOf(shapeClazz)) {
					//	createVisioConnection(emfShape);
					//}	
				}
			}
		} catch (COMException e) {
			VisioUtil.closeVisioApplication(this.visioApp);
			e.printStackTrace();
		}
		
		return visioDocument;
	}

	private HashMap<String, IVMaster> registerVisioMasters(HashSet<IVDocument> visioStencils) {
		
		HashMap<String, IVMaster> allVisioMasters = new HashMap<String, IVMaster>();
		
		try {
			for(IVDocument visioStencil : visioStencils) {
				for(IVMaster master : new VisioList<IVMaster>(visioStencil.getMasters(), IVMaster.class)) {
					allVisioMasters.put(master.getUniqueID(), master);
				}
			}
		} catch (COMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return allVisioMasters;
	}


	private IVShape createVisioShape(EObject emfShape, IVPage visioPage, IVMaster visioMaster) throws COMException {
		
		if(allVisioShapes.containsKey(emfShape)) {
			return allVisioShapes.get(emfShape);
		}
		
		IVShape visioShape = visioPage.Drop(visioMaster, 0, 0);
		EmfShapeSheetUtil sr = new EmfShapeSheetUtil(emfShape);
		VisioShapeSheetUtil sw = new VisioShapeSheetUtil(visioShape);
		
		Object text = emfShape.eGet(emfShape.eClass().getEStructuralFeature("visioText"));
		if(text!=null)
			sw.setShapeText(text.toString());
		
		double width = 0, height = 0, x = 0, y = 0;
		
		EObject xCell = sr.getCell(emfShape, "visSectionObject", "1", "PinX");
		if(xCell!=null) {
			EStructuralFeature valueAttribute = xCell.eClass().getEStructuralFeature("visioValue");
			x = Double.valueOf(xCell.eGet(valueAttribute).toString());
		}
		
		EObject yCell = sr.getCell(emfShape, "visSectionObject", "1", "PinY");
		if(yCell!=null) {
			EStructuralFeature valueAttribute = yCell.eClass().getEStructuralFeature("visioValue");
			y = Double.valueOf(yCell.eGet(valueAttribute).toString());
		}
		
		VisioList<IVShape> masterShapes = new VisioList<IVShape>(visioShape.getMaster().getShapes(), IVShape.class);
		width = masterShapes.get(0).getCells("Width").getResultIU();
		
		EObject widthCell = sr.getCell(emfShape, "visSectionObject", "1", "Width");
		if(widthCell!=null) {
			EStructuralFeature valueAttribute = widthCell.eClass().getEStructuralFeature("visioValue");
			if(widthCell.eGet(valueAttribute) != null) {
				width = Double.valueOf(widthCell.eGet(valueAttribute).toString());
			}
		}
		
		masterShapes = new VisioList<IVShape>(visioShape.getMaster().getShapes(), IVShape.class);
		height = masterShapes.get(0).getCells("Height").getResultIU();
		
		EObject heightCell = sr.getCell(emfShape, "visSectionObject", "1", "Height");
		if(heightCell!=null) {
			EStructuralFeature valueAttribute = heightCell.eClass().getEStructuralFeature("visioValue");
			if(heightCell.eGet(valueAttribute) != null) {
				height = Double.valueOf(heightCell.eGet(valueAttribute).toString());
			}
		}
		
		sw.setShapeLocationX(x);
		sw.setShapeLocationY(y);
		sw.setShapeHeight(height);
		sw.setShapeWidth(width);
		
		allVisioShapes.put(emfShape, visioShape);
		createSubVisioShapes(emfShape, visioShape);
		
		return visioShape;
	}
	
	private void createSubVisioShapes(EObject parentShape, IVShape parentVisioShape) throws COMException {
		
		EList<EObject> emfCShapes = (EList<EObject>)parentShape.eGet(parentShape.eClass().getEStructuralFeature("visioContainedShapes"));
		
		for(EObject emfCShape : emfCShapes) {
			
			
			IVShape cVisioShape = VisioUtil.getVisioChildShape(emfCShape, parentVisioShape);
			if(cVisioShape != null){
				
				allVisioShapes.put(emfCShape, cVisioShape);
				
				EmfShapeSheetUtil sr = new EmfShapeSheetUtil(emfCShape);
				VisioShapeSheetUtil sw = new VisioShapeSheetUtil(cVisioShape);
				
				Object text = emfCShape.eGet(emfCShape.eClass().getEStructuralFeature("visioText"));
				if(text!=null)
					sw.setShapeText(text.toString());
				
				double width = 0, height = 0, x = 0, y = 0;
				
				EObject xCell = sr.getCell(emfCShape, "visSectionObject", "1", "PinX");
				if(xCell!=null) {
					EStructuralFeature valueAttribute = xCell.eClass().getEStructuralFeature("visioValue");
					x = Double.valueOf(xCell.eGet(valueAttribute).toString());
				}
				else
					x = parentVisioShape.getCells("PinX").getResultIU();
					
				
				EObject yCell = sr.getCell(emfCShape, "visSectionObject", "1", "PinY");
				if(yCell!=null) {
					EStructuralFeature valueAttribute = yCell.eClass().getEStructuralFeature("visioValue");
					y = Double.valueOf(yCell.eGet(valueAttribute).toString());
				}
				else
					y = parentVisioShape.getCells("PinY").getResultIU();
				
				
				VisioList<IVShape> masterShapes = new VisioList<IVShape>(cVisioShape.getMaster().getShapes(), IVShape.class);
				width = masterShapes.get(0).getCells("Width").getResultIU();
				
				EObject widthCell = sr.getCell(emfCShape, "visSectionObject", "1", "Width");
				if(widthCell!=null) {
					EStructuralFeature valueAttribute = widthCell.eClass().getEStructuralFeature("visioValue");
					if(widthCell.eGet(valueAttribute) != null) {
						width = Double.valueOf(widthCell.eGet(valueAttribute).toString());
					}
				}
				
				
				EObject heightCell = sr.getCell(emfCShape, "visSectionObject", "1", "Height");
				if(heightCell!=null) {
					EStructuralFeature valueAttribute = heightCell.eClass().getEStructuralFeature("visioValue");
					if(heightCell.eGet(valueAttribute) != null) {
						height = Double.valueOf(heightCell.eGet(valueAttribute).toString());
					}
				}
				
				sw.setShapeLocationX(x);
				sw.setShapeLocationY(y);
				sw.setShapeHeight(height);
				sw.setShapeWidth(width);
								
				createSubVisioShapes(emfCShape, cVisioShape);
			}
		}
	}
	
	private void createVisioConnection(EObject emfShape) {
		
		IVShape conShape = allVisioShapes.get(emfShape);
		
		IVShape visioSourceShape = allVisioShapes.get(emfShape.eGet(emfShape.eClass().getEStructuralFeature("visioSourceShape")));
		IVShape visioTargetShape = allVisioShapes.get(emfShape.eGet(emfShape.eClass().getEStructuralFeature("visioTargetShape")));
		
		VisioModelUtil vsm = new VisioModelUtil(visioApp);
		vsm.connectShapes(visioSourceShape, visioTargetShape, conShape);	
	}
}

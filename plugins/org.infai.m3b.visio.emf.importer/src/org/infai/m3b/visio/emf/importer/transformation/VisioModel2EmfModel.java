package org.infai.m3b.visio.emf.importer.transformation;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.infai.m3b.visio.emf.emfutil.EmfUtil;
import org.infai.m3b.visio.emf.emfutil.EmfVisioMetamodel;
import org.infai.m3b.visio.emf.visiostub.IVApplication;
import org.infai.m3b.visio.emf.visiostub.IVCell;
import org.infai.m3b.visio.emf.visiostub.IVConnect;
import org.infai.m3b.visio.emf.visiostub.IVDocument;
import org.infai.m3b.visio.emf.visiostub.IVMaster;
import org.infai.m3b.visio.emf.visiostub.IVPage;
import org.infai.m3b.visio.emf.visiostub.IVRow;
import org.infai.m3b.visio.emf.visiostub.IVShape;
import org.infai.m3b.visio.emf.visiostub.VisFromParts;
import org.infai.m3b.visio.emf.visioutil.VisioList;
import org.infai.m3b.visio.emf.visioutil.VisioModelUtil;
import org.infai.m3b.visio.emf.visioutil.VisioProperty;
import org.infai.m3b.visio.emf.visioutil.VisioShapeSheetSection;
import org.infai.m3b.visio.emf.visioutil.VisioUtil;
import org.jawin.COMException;
import org.jawin.DispatchPtr;


public class VisioModel2EmfModel {
	
	private VisioModelUtil visioModelReader;
	
	//abstract EMF metamodel 
	private EPackage documentPackage;
	private boolean hasDocumentPackage;
	
	private IVApplication visioApp;
	
	EmfVisioMetamodel emfVisioMetamodel;
	
	//all model elements
	private HashMap<String, EObject> allEmfShapes;
	
	public VisioModel2EmfModel(IVApplication visioApp, EmfVisioMetamodel emfVisioMetamodel) {
		
		this.visioApp = visioApp;
		visioModelReader = new VisioModelUtil(visioApp);
		this.documentPackage = null;
		this.hasDocumentPackage = false;
		
		//all model elements
		allEmfShapes = new HashMap<String, EObject>();
		this.emfVisioMetamodel = emfVisioMetamodel;
	}
	

	private HashMap<String, EClassifier> registerEmfClassifier(EmfVisioMetamodel emfVisioMetamodel) {
		
		HashMap<String, EClassifier> allEmfClassifiers = new HashMap<String, EClassifier>();
		
		//put all EClassifier in a registry
		for(EPackage subpackage : emfVisioMetamodel.getEVisioMetamodel().getESubpackages()) {
			for(EClassifier classifier : subpackage.getEClassifiers()) {
				String id = classifier.getEAnnotation("visio").getDetails().get("UID");
				allEmfClassifiers.put(id, classifier);
			}
		}		
		return allEmfClassifiers;
	}
	
	
	public EObject createEmfVisioModel(IVDocument visioModel) {
	
		//all metamodel classifier
		HashMap<String, EClassifier> allEmfClassifier = registerEmfClassifier(emfVisioMetamodel);
		//EFactory of the abstract EMF metamodel
		EFactory visioFactory = emfVisioMetamodel.getEVisioMetamodel().getEFactoryInstance();
		
		EmfVisioMetamodel evm = this.emfVisioMetamodel;
		
		//creates the Document EObject
		EObject emfVisioDocument = visioFactory.create(evm.getEVisioDocument());
		
		try {	
			
			emfVisioDocument.eSet(evm.getEVisioDocument().getEStructuralFeature("visioName"), 
					visioModel.getFullName());
			
			//foreach Visio page in a Visio model a corresponding EMF object is created
			VisioList<IVPage> visioPages = new VisioList<IVPage>(visioModel.getPages(), IVPage.class);
			for(IVPage visioPage : visioPages) {
				
				//create Visio page
				EObject emfPage = visioFactory.create(evm.getEVisioPage());
				emfPage.eSet(evm.getEVisioPage().getEStructuralFeature("visioName"), visioPage.getName());
				@SuppressWarnings("unchecked")
				EList<EObject> pages = (EList<EObject>)emfVisioDocument.eGet(evm.getEVisioDocument().getEStructuralFeature("visioPages"));
				pages.add(emfPage);
				
				//foreach Visio shape in a Visio page a corresponding EMF object is created
				VisioList<IVShape> visioShapes = new VisioList<IVShape>(visioPage.getShapes(), IVShape.class);
				@SuppressWarnings("unchecked")
				EList<EObject> pageShapes = (EList<EObject>)emfPage.eGet(evm.getEVisioPage().getEStructuralFeature("visioContainedShapes"));
				
				for(IVShape visioShape : visioShapes) {
					EObject emfVisioShape = createEmfElement(visioShape, allEmfClassifier);	
					pageShapes.add(emfVisioShape);
				}
				
				for(IVShape visioShape : visioShapes) {
					//foreach Visio shape that is graphical contained in a parent shape an EObject is created  
					ArrayList<IVShape> visioContainedShapes = visioModelReader.getSpatialContainedShapes(visioShape, 0.0);
					EObject emfShape = createEmfShape(visioShape, allEmfClassifier);
					
					@SuppressWarnings("unchecked")
					EList<EObject> conShapes = (EList<EObject>)emfShape.eGet(evm.getEVisioConnectinShape().getEStructuralFeature("visioContainedShapes"));
					for(IVShape visioContainedShape : visioContainedShapes) {	
						EObject emfContainedShape = createEmfShape(visioContainedShape, allEmfClassifier);
						conShapes.add(emfContainedShape);
					}
				}
				
				for(IVShape visioShape : visioShapes) {
					
					//foreach Visio shape that is connected to another Visio shape an Eobject is created 
					if(visioModelReader.isConnectionShape(visioShape)) {
						
						EObject emfShape = createEmfShape(visioShape, allEmfClassifier);
						VisioList<IVConnect> connections = new VisioList<IVConnect>(visioShape.getConnects(), IVConnect.class);
						for(IVConnect connection : connections) {
							
							if (connection.getFromPart() == VisFromParts.visBegin) {
								IVShape connectedVisioShape = connection.getToCell().getShape();
								EObject connectedEmfShape = createEmfShape(connectedVisioShape, allEmfClassifier);
								emfShape.eSet(evm.getEVisioConnectinShape().getEStructuralFeature("visioSourceShape"), connectedEmfShape);
							}
							
							
							if (connection.getFromPart() == VisFromParts.visEnd) {		
								IVShape connectedVisioShape = connection.getToCell().getShape();
								EObject connectedEmfShape = createEmfShape(connectedVisioShape, allEmfClassifier);
								emfShape.eSet(evm.getEVisioConnectinShape().getEStructuralFeature("visioTargetShape"), connectedEmfShape);
							}
						}	
					}
				}
				
			}
		} catch (COMException e) {

			VisioUtil.closeVisioApplication(this.visioApp);
			e.printStackTrace();
		}
		
		return emfVisioDocument;
	}
	
	
	
	//iterates the model and creates EMF objects
	private EObject createEmfElement(IVShape visioShape, HashMap<String, EClassifier> allEmfClassifier) {
		
		EmfVisioMetamodel evm = this.emfVisioMetamodel;
		//creates an EObject
		EObject emfShape = createEmfShape(visioShape, allEmfClassifier);
		
		try {
			//foreach Visio shape that is structural contained in a parent shape the createShape-method is called recursively
			VisioList<IVShape> containedShapes = new VisioList<IVShape>(visioShape.getShapes(), IVShape.class);
			
			@SuppressWarnings("unchecked")
			EList<EObject> shapes = (EList<EObject>)emfShape.eGet(evm.getEVisioShape().getEStructuralFeature("visioContainedShapes"));
			
			for(IVShape containedShape : containedShapes)
	        {
				EObject emfContainedShape = createEmfElement(containedShape, allEmfClassifier);
				shapes.add(emfContainedShape);
	        }	
		} catch (COMException e) {
			
			VisioUtil.closeVisioApplication(this.visioApp);
			e.printStackTrace();
		}
		
		return emfShape;
	}
	
	
	//creates an EObject or returns an existing EObject  
	private EObject createEmfShape(IVShape visioShape, HashMap<String, EClassifier> allEmfClassifier) {
		
		//EFactory of the abstract EMF metamodel
		EFactory visioFactory = this.emfVisioMetamodel.getEVisioMetamodel().getEFactoryInstance();
		
		EObject emfShape = null;
		
		try {
			String shapeId = visioShape.getUniqueID((short)1);
			
			//if the EObject was already created then the method returns this EObject
			if(allEmfShapes.containsKey(shapeId)) 
				return allEmfShapes.get(shapeId);
			
			String shapeName = visioShape.getName();
			String shapeText = visioShape.getText();
			
			String masterShapeId = new String();
			String masterShapeName = new String();
				
			//if a visio shape has a master
			if(visioModelReader.hasMaster(visioShape)) {
				
				IVMaster visioMaster = visioShape.getMaster();
				String masterId = visioMaster.getUniqueID();
				masterShapeId = visioShape.getMasterShape().getUniqueID((short)1);
				masterShapeName = visioShape.getMasterShape().getName();
				
				//if visio master is contained in a given stencil
				if(allEmfClassifier.containsKey(masterId)) {
					//selects the EClass corresponding to the Visio master of the Visio shape
					EClass eClazz = (EClass)allEmfClassifier.get(masterId);
					//create a emfVisioShape
					emfShape = eClazz.getEPackage().getEFactoryInstance().create(eClazz);
					emfShape.eSet(emfShape.eClass().getEStructuralFeature("visioMasterShapeID"),masterShapeId);
					emfShape.eSet(emfShape.eClass().getEStructuralFeature("visioMasterShapeName"),masterShapeName);
				}
				//if the visio master is not contained in a given stencil
				else {
					
					VisioStencil2EmfMetamodel stencil2emf = new VisioStencil2EmfMetamodel(visioApp, this.emfVisioMetamodel);
					
					//create an epackage, if there is no one
					if(!hasDocumentPackage()) {
						this.documentPackage = stencil2emf.createEmfStencil(visioShape.getDocument());
						this.hasDocumentPackage = true;
					}
					
					EClass eClazz = stencil2emf.createEmfMaster(visioMaster);
					this.documentPackage.getEClassifiers().add(eClazz);
					allEmfClassifier.put(masterId, eClazz);
					
					
					emfShape = eClazz.getEPackage().getEFactoryInstance().create(eClazz);
					emfShape.eSet(emfShape.eClass().getEStructuralFeature("visioMasterShapeID"),masterShapeId);
					emfShape.eSet(emfShape.eClass().getEStructuralFeature("visioMasterShapeName"),masterShapeName);
					
				}

			}
			//if a visioshape has no master
			else {
				emfShape = visioFactory.create(this.emfVisioMetamodel.getEVisioShapeWithoutMaster());		
			}
			
			emfShape.eSet(emfShape.eClass().getEStructuralFeature("visioName"), shapeName);
			emfShape.eSet(emfShape.eClass().getEStructuralFeature("visioID"), shapeId);
			emfShape.eSet(emfShape.eClass().getEStructuralFeature("visioText"), shapeText);
			
			//System.out.println("begin shape sheet");
			EObject emfSheet = createShapeSheet(visioShape);
			//set shape sheet of this emfVisioShape
			emfShape.eSet(emfShape.eClass().getEStructuralFeature("visioShapeSheet"), emfSheet);
			//System.out.println("end shape sheet");
			
			
			setAttributeValues(visioShape, emfShape);
			allEmfShapes.put(shapeId, emfShape);
			
			
			
		} catch (COMException e) {
			
			VisioUtil.closeVisioApplication(this.visioApp);
			e.printStackTrace();
		}
		
		return emfShape;		
	}
	
	
	private void setAttributeValues(IVShape visioShape, EObject emfShape) {
		
		VisioModelUtil vmu = new VisioModelUtil(this.visioApp);
		
		HashMap<String, EAttribute> emfAttributes = new HashMap<String, EAttribute>();
		for(EAttribute emfAttribute : emfShape.eClass().getEAttributes()) 
			emfAttributes.put(emfAttribute.getName(), emfAttribute);		
		
		for(VisioProperty vp : vmu.getShapeProperty(visioShape)) {
			
			if(emfAttributes.containsKey(EmfUtil.correctIdentifier(vp.getName()))) {
				EAttribute emfAttribute = emfAttributes.get(EmfUtil.correctIdentifier(vp.getName()));
				emfShape.eSet(emfAttribute, vp.getValue());
			}
			else {
				EFactory visioFactory = this.emfVisioMetamodel.getEVisioMetamodel().getEFactoryInstance();
				EClass visioAttribute = this.emfVisioMetamodel.getEVisioAttribute();
				EObject emfAttribute = visioFactory.create(visioAttribute);
				emfAttribute.eSet(visioAttribute.getEStructuralFeature("visioName"), vp.getName());
				emfAttribute.eSet(visioAttribute.getEStructuralFeature("visioValue"), vp.getValue());
				
				@SuppressWarnings("unchecked")
				EList<EObject> structuralFeatures = ((EList<EObject>)emfShape.eGet(this.emfVisioMetamodel.getEVisioShape().getEStructuralFeature("visioAttributes")));
				structuralFeatures.add(emfAttribute);
			}
		}
	}
	
	
	private EObject createShapeSheet(IVShape visioShape)
	{
		
		EFactory visioFactory = this.emfVisioMetamodel.getEVisioMetamodel().getEFactoryInstance();
		EObject emfSheet =  visioFactory.create(this.emfVisioMetamodel.getEVisioShapeSheet());
		
		try {
			//System.out.println("--->"+ visioShape.getName());
			
			@SuppressWarnings("unchecked")
			EList<EObject> sections = (EList<EObject>)emfSheet.eGet(emfSheet.eClass().getEStructuralFeature("visioSections"));
			
			//for each section
			for(VisioShapeSheetSection section : VisioUtil.getShapeSheetSections(visioShape)) {
			
				EObject emfSection = createSection(section.getId(), section.getName());
				//System.out.println(section.getName());
				sections.add(emfSection);
					
				//for each row
				short rowCount = visioShape.getRowCount(section.getId());
				
				@SuppressWarnings("unchecked")
				EList<EObject> rows = (EList<EObject>)emfSection.eGet(emfSection.eClass().getEStructuralFeature("visioRows"));
				for (short rowIndex = 0; rowIndex < rowCount; rowIndex++) {
					
					if(visioShape.getRowExists(section.getId(), rowIndex, (short)0)!=0) {
					
						IVRow currentRow = visioShape.getSection(section.getId()).getRow(rowIndex);
						EObject emfRow = createRow(currentRow);
						rows.add(emfRow);
												
						//for each cell
						short cellCount = visioShape.getRowsCellCount(section.getId(), rowIndex);
						
						@SuppressWarnings("unchecked")
						EList<EObject> cells = (EList<EObject>)emfRow.eGet(emfRow.eClass().getEStructuralFeature("visioCells"));						
						for (short cellIndex = 0; cellIndex < cellCount; cellIndex++) {

							IVCell currentCell = new IVCell();
							DispatchPtr dispPtr = (DispatchPtr)currentRow.get("Cell", new Integer(cellIndex));
							currentCell.stealUnknown(dispPtr);	
							dispPtr.close();
							//System.out.println(currentCell.getName());
							EObject emfCell = createCell(currentCell);
							currentCell.close();
							cells.add(emfCell);							
						}
						currentRow.close();
					}
				}
			}
		} catch (COMException e) {
			
			VisioUtil.closeVisioApplication(this.visioApp);
			e.printStackTrace();
		}
		return emfSheet;
	}
	
	private EObject createSection(short sectionId, String sectionName) {
		
		EmfVisioMetamodel evm = this.emfVisioMetamodel;
		EFactory visioFactory = evm.getEVisioMetamodel().getEFactoryInstance();
		
		EObject emfSection = visioFactory.create(evm.getEVisioSection());
		emfSection.eSet(evm.getEVisioSection().getEStructuralFeature("visioName"), sectionName);
		
		return emfSection;
	}
	
	private EObject createRow(IVRow row) {
		
		EmfVisioMetamodel evm = this.emfVisioMetamodel;
		EFactory visioFactory = evm.getEVisioMetamodel().getEFactoryInstance();
		
		EObject emfRow = visioFactory.create(evm.getEVisioRow());
		
		try {
			String rowName = String.valueOf(row.getIndex());
			emfRow.eSet(evm.getEVisioRow().getEStructuralFeature("visioName"), rowName);
			
		} catch (COMException e) {
			
			VisioUtil.closeVisioApplication(this.visioApp);
			e.printStackTrace();
		}
		return emfRow;	
	}
	
	private EObject createCell(IVCell cell) {
		
		EmfVisioMetamodel evm = this.emfVisioMetamodel;
		EFactory visioFactory = evm.getEVisioMetamodel().getEFactoryInstance();
		
		EObject emfCell = visioFactory.create(evm.getEVisioCell());
		
		try {	
			short cellUnit = cell.getUnits();
			emfCell.eSet(evm.getEVisioCell().getEStructuralFeature("visioName"), cell.getName());
			
			if(cell.getName().equals("PinX") || cell.getName().equals("PinY") ||
					cell.getName().equals("Width") || cell.getName().equals("Height")) {
				
				String value = Double.toString(cell.getResultIU());
				emfCell.eSet(emfCell.eClass().getEStructuralFeature("visioValue"), value);
			}
			else if(visioModelReader.isDoubleUnit(new Integer(cellUnit))) {
				
				String value = cell.get("Result", cellUnit).toString();
				emfCell.eSet(emfCell.eClass().getEStructuralFeature("visioValue"), value);
				String formula = cell.getFormulaU();
				emfCell.eSet(emfCell.eClass().getEStructuralFeature("visioFormula"), formula);
			}
			else {
				
				String value = cell.get("ResultStr", cellUnit).toString();
				emfCell.eSet(emfCell.eClass().getEStructuralFeature("visioValue"), value);
				String formula = cell.getFormulaU();
				emfCell.eSet(emfCell.eClass().getEStructuralFeature("visioFormula"), formula);
			}
		} catch (COMException e) {

			VisioUtil.closeVisioApplication(this.visioApp);
			e.printStackTrace();
		}	
		return emfCell;
	}
	
	public boolean hasDocumentPackage() {
		
		return hasDocumentPackage;
	}
	
	public EPackage getDocumentPackage() {
		return documentPackage; 
	}
 }

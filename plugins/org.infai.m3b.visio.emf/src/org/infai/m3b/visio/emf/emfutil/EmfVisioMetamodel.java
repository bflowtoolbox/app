package org.infai.m3b.visio.emf.emfutil;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;

public class EmfVisioMetamodel {
	
	private EcoreFactory fac;
	private EcorePackage pac;
	
	private EPackage emfVisioMetamodel;
	
	private EClass eVisioIdElement;
	private EClass eVisioNamedElement;
	private EClass eVisioDocument;
	private EClass eVisioPage;
	private EClass eVisioShape;
	private EClass eVisioAttribute;
	private EClass eVisioConnectinShape;
	private EClass eVisioShapeSheet;
	private EClass eVisioShapeWithoutMaster;
	private EClass eVisioSection;
	private EClass eVisioRow;
	private EClass eVisioCell;
	private EEnum eVisioUnit;
	
	public EmfVisioMetamodel() {
		
		this.fac = EcoreFactory.eINSTANCE;
		this.pac = EcorePackage.eINSTANCE;
		
		eVisioIdElement = fac.createEClass();
		eVisioNamedElement = fac.createEClass();
		eVisioDocument = fac.createEClass();
		eVisioPage = fac.createEClass();
		eVisioShape = fac.createEClass();
		eVisioAttribute = fac.createEClass();
		eVisioConnectinShape = fac.createEClass();
		eVisioShapeSheet = fac.createEClass();
		eVisioShapeWithoutMaster = fac.createEClass();
		eVisioSection = fac.createEClass();
		eVisioRow = fac.createEClass();
		eVisioCell = fac.createEClass();
		eVisioUnit = fac.createEEnum();
		emfVisioMetamodel = fac.createEPackage();
		 
		createEVisioModel(); 
	}
	
	private EPackage createEVisioModel() {
		
		emfVisioMetamodel.setName("visiomodel");
		emfVisioMetamodel.setNsPrefix("visio");
		emfVisioMetamodel.setNsURI("http://infai.org/visio2emf/");
		
		emfVisioMetamodel.getEClassifiers().add(createEVisioIdElement());
		emfVisioMetamodel.getEClassifiers().add(createEVisioNamedElement());
		emfVisioMetamodel.getEClassifiers().add(createEVisioDocument());
		emfVisioMetamodel.getEClassifiers().add(createEVisioPage());
		emfVisioMetamodel.getEClassifiers().add(createEVisioShape());
		emfVisioMetamodel.getEClassifiers().add(createEVisioShapeWithoutMaster());
		emfVisioMetamodel.getEClassifiers().add(createEVisioAttribute());
		emfVisioMetamodel.getEClassifiers().add(createEVisioConnectionShape());
		emfVisioMetamodel.getEClassifiers().add(createEVisioShapeSheet());
		emfVisioMetamodel.getEClassifiers().add(createEVisioSection());
		emfVisioMetamodel.getEClassifiers().add(createEVisioRow());
		emfVisioMetamodel.getEClassifiers().add(createEVisioCell());
		emfVisioMetamodel.getEClassifiers().add(createEVisioUnits());
		
		return emfVisioMetamodel;
	}
	
	
	private EClass createEVisioIdElement() {
		eVisioIdElement.setName("EVisioIdElement");
		eVisioIdElement.setAbstract(true);
		EAttribute visioID = fac.createEAttribute();
		visioID.setName("visioID");
		visioID.setEType(pac.getEString());
		eVisioIdElement.getEStructuralFeatures().add(visioID);
		return eVisioIdElement;
	}
	
	private EClass createEVisioNamedElement() {
		eVisioNamedElement.setName("EVisioNamedElement");
		//eVisioNamedElement.getESuperTypes().add(eVisioIdElement);
		eVisioNamedElement.setAbstract(true);
		EAttribute visioName = fac.createEAttribute();
		visioName.setName("visioName");
		visioName.setEType(pac.getEString());
		eVisioNamedElement.getEStructuralFeatures().add(visioName);
		return eVisioNamedElement;
	}
	
	private EClass createEVisioDocument() {
		eVisioDocument.setName("EVisioDocument");
		eVisioDocument.getESuperTypes().add(eVisioNamedElement);
		EReference visioPages = fac.createEReference();
		visioPages.setName("visioPages");
		visioPages.setEType(eVisioPage);
		visioPages.setLowerBound(0);
		visioPages.setUpperBound(-1);
		visioPages.setContainment(true);
		eVisioDocument.getEStructuralFeatures().add(visioPages);
		return eVisioDocument;
	}
	

	private EClass createEVisioPage() {
		eVisioPage.setName("EVisioPage");
		eVisioPage.getESuperTypes().add(eVisioShape);
		return eVisioPage;
	}
	
	private EClass createEVisioShape() {
		eVisioShape.setName("EVisioShape");
		eVisioShape.setAbstract(true);
		eVisioShape.getESuperTypes().add(eVisioIdElement);
		eVisioShape.getESuperTypes().add(eVisioNamedElement);
		EReference visioSpatial = fac.createEReference();
		visioSpatial.setName("visioSpatialContainedShapes");
		visioSpatial.setEType(eVisioShape);
		visioSpatial.setLowerBound(0);
		visioSpatial.setUpperBound(-1);
		visioSpatial.setContainment(false);
		eVisioShape.getEStructuralFeatures().add(visioSpatial);
		EReference visioContain = fac.createEReference();
		visioContain.setName("visioContainedShapes");
		visioContain.setEType(eVisioShape);
		visioContain.setLowerBound(0);
		visioContain.setUpperBound(-1);
		visioContain.setContainment(true);
		eVisioShape.getEStructuralFeatures().add(visioContain);
		EReference visioAttribute = fac.createEReference();
		visioAttribute.setName("visioAttributes");
		visioAttribute.setEType(eVisioAttribute);
		visioAttribute.setLowerBound(0);
		visioAttribute.setUpperBound(-1);
		visioAttribute.setContainment(true);
		eVisioShape.getEStructuralFeatures().add(visioAttribute);
		EAttribute visioText = fac.createEAttribute();
		visioText.setName("visioText");
		visioText.setEType(pac.getEString());
		eVisioShape.getEStructuralFeatures().add(visioText);
		EReference visioShapeSheet = fac.createEReference();
		visioShapeSheet.setName("visioShapeSheet");
		visioShapeSheet.setEType(eVisioShapeSheet);
		visioShapeSheet.setLowerBound(1);
		visioShapeSheet.setUpperBound(1);
		visioShapeSheet.setContainment(true);
		eVisioShape.getEStructuralFeatures().add(visioShapeSheet);
		EAttribute visioMasterShapeID = fac.createEAttribute();
		visioMasterShapeID.setName("visioMasterShapeID");
		visioMasterShapeID.setEType(pac.getEString());
		eVisioShape.getEStructuralFeatures().add(visioMasterShapeID);
		EAttribute visioMasterShapeName = fac.createEAttribute();
		visioMasterShapeName.setName("visioMasterShapeName");
		visioMasterShapeName.setEType(pac.getEString());
		eVisioShape.getEStructuralFeatures().add(visioMasterShapeName);
		return eVisioShape;
	}
	
	private EClass createEVisioShapeWithoutMaster() {
		eVisioShapeWithoutMaster.setName("EVisioShapeWithoutMaster");
		eVisioShapeWithoutMaster.getESuperTypes().add(eVisioShape);
		return eVisioShapeWithoutMaster;
	}
	
	

	private EClass createEVisioAttribute() {
		eVisioAttribute.setName("EVisioAttribute");
		eVisioAttribute.getESuperTypes().add(eVisioNamedElement);
		EAttribute visioValue = fac.createEAttribute();
		visioValue.setName("visioValue");
		visioValue.setEType(pac.getEString());
		eVisioAttribute.getEStructuralFeatures().add(visioValue);
		return eVisioAttribute;
	}
	
	private EClass createEVisioConnectionShape() {
		eVisioConnectinShape.setName("EVisioConnectionShape");
		eVisioConnectinShape.getESuperTypes().add(eVisioShape);
		EReference visioSourceShape = fac.createEReference();
		visioSourceShape.setName("visioSourceShape");
		visioSourceShape.setEType(eVisioShape);
		visioSourceShape.setLowerBound(0);
		visioSourceShape.setUpperBound(1);
		visioSourceShape.setContainment(false);
		eVisioConnectinShape.getEStructuralFeatures().add(visioSourceShape);
		EReference visioTargetShape = fac.createEReference();
		visioTargetShape.setName("visioTargetShape");
		visioTargetShape.setEType(eVisioShape);
		visioTargetShape.setLowerBound(0);
		visioTargetShape.setUpperBound(1);
		visioTargetShape.setContainment(false);
		eVisioConnectinShape.getEStructuralFeatures().add(visioTargetShape);
		return eVisioConnectinShape;
	}
	
	private EClass createEVisioShapeSheet() {
		eVisioShapeSheet.setName("EVisioShapeSheet");
		EReference visioSections = fac.createEReference();
		visioSections.setName("visioSections");
		visioSections.setEType(eVisioSection);
		visioSections.setLowerBound(0);
		visioSections.setUpperBound(-1);
		visioSections.setContainment(true);
		eVisioShapeSheet.getEStructuralFeatures().add(visioSections);
		return eVisioShapeSheet;
	}
	
	private EClass createEVisioSection() {
		eVisioSection.setName("EVisioSection");
		eVisioSection.getESuperTypes().add(eVisioNamedElement);
		EReference visioRows = fac.createEReference();
		visioRows.setName("visioRows");
		visioRows.setEType(eVisioRow);
		visioRows.setLowerBound(0);
		visioRows.setUpperBound(-1);
		visioRows.setContainment(true);
		eVisioSection.getEStructuralFeatures().add(visioRows);
		//EReference visioCells = fac.createEReference();
		//visioCells.setName("visioCells");
		//visioCells.setEType(eVisioCell);
		//visioCells.setLowerBound(0);
		//visioCells.setUpperBound(-1);
		//visioCells.setContainment(false);
		//eVisioSection.getEStructuralFeatures().add(visioCells);
		return eVisioSection;
	}
	
	private EClass createEVisioRow() {
		eVisioRow.setName("EVisioRow");
		eVisioRow.getESuperTypes().add(eVisioNamedElement);
		EReference visioCells = fac.createEReference();
		visioCells.setName("visioCells");
		visioCells.setEType(eVisioCell);
		visioCells.setLowerBound(0);
		visioCells.setUpperBound(-1);
		visioCells.setContainment(true);
		eVisioRow.getEStructuralFeatures().add(visioCells);
		return eVisioRow;
	}
	
	private EClass createEVisioCell() {
		eVisioCell.setName("EVisioCell");
		eVisioCell.getESuperTypes().add(eVisioNamedElement);
		EAttribute visioValue = fac.createEAttribute();
		visioValue.setName("visioValue");
		visioValue.setEType(pac.getEString());
		eVisioCell.getEStructuralFeatures().add(visioValue);
		EAttribute visioFormula = fac.createEAttribute();
		visioFormula.setName("visioFormula");
		visioFormula.setEType(pac.getEString());
		eVisioCell.getEStructuralFeatures().add(visioFormula);
		EAttribute visioUnit = fac.createEAttribute();
		visioUnit.setName("visioUnit");
		visioUnit.setEType(eVisioUnit);
		eVisioCell.getEStructuralFeatures().add(visioUnit);
		return eVisioCell;
	}
	
	private EEnum createEVisioUnits() {
		
		eVisioUnit.setName("EVisioeVisioUnit");
		eVisioUnit.getELiterals().add(createLiteral("viseVisioUnitString", 231));
	    eVisioUnit.getELiterals().add(createLiteral("visAngleUnit", 80));
	    eVisioUnit.getELiterals().add(createLiteral("visAcre", 36));
	    eVisioUnit.getELiterals().add(createLiteral("visCentimeters", 69));
	    eVisioUnit.getELiterals().add(createLiteral("visCicerosAndDidots", 52));
	    eVisioUnit.getELiterals().add(createLiteral("visCiceros", 54));
	    eVisioUnit.getELiterals().add(createLiteral("visCurrency", 111));
	    eVisioUnit.getELiterals().add(createLiteral("visDate", 40));
	    eVisioUnit.getELiterals().add(createLiteral("visDegreeMinSec", 82));
	    eVisioUnit.getELiterals().add(createLiteral("visDegrees", 81));
	    eVisioUnit.getELiterals().add(createLiteral("visDidots", 53));
	    eVisioUnit.getELiterals().add(createLiteral("visDrawingeVisioUnit", 64));
	    eVisioUnit.getELiterals().add(createLiteral("visDurationeVisioUnit", 42));
	    eVisioUnit.getELiterals().add(createLiteral("visElapsedDay", 44));
	    eVisioUnit.getELiterals().add(createLiteral("visElapsedHour", 45));
	    eVisioUnit.getELiterals().add(createLiteral("visElapsedMin", 46));
	    eVisioUnit.getELiterals().add(createLiteral("visElapsedSec", 47));
	    eVisioUnit.getELiterals().add(createLiteral("visElapsedWeek", 43));
	    eVisioUnit.getELiterals().add(createLiteral("visFeetAndInches", 67));
	    eVisioUnit.getELiterals().add(createLiteral("visFeet", 66));
	    eVisioUnit.getELiterals().add(createLiteral("visHectare", 37));
	    eVisioUnit.getELiterals().add(createLiteral("visInches", 65));
	    eVisioUnit.getELiterals().add(createLiteral("visInchFrac", 73));
	    eVisioUnit.getELiterals().add(createLiteral("visKilometers", 72));
	    eVisioUnit.getELiterals().add(createLiteral("visMeters", 71));
	    eVisioUnit.getELiterals().add(createLiteral("visMileFrac", 74));
	    eVisioUnit.getELiterals().add(createLiteral("visMiles", 68));
	    eVisioUnit.getELiterals().add(createLiteral("visMillimeters", 70));
	    eVisioUnit.getELiterals().add(createLiteral("visMin", 84));
	    eVisioUnit.getELiterals().add(createLiteral("visNautMiles", 76));
	    eVisioUnit.getELiterals().add(createLiteral("visNoCast", 252));
	    eVisioUnit.getELiterals().add(createLiteral("visNumber", 32));
	    eVisioUnit.getELiterals().add(createLiteral("visPageUnit", 63));
	    eVisioUnit.getELiterals().add(createLiteral("visPercent", 33));
	    eVisioUnit.getELiterals().add(createLiteral("visPicasAndPoints", 49));
	    eVisioUnit.getELiterals().add(createLiteral("visPicas", 51));
	    eVisioUnit.getELiterals().add(createLiteral("visPoints", 50));
	    eVisioUnit.getELiterals().add(createLiteral("visRadians", 83));
	    eVisioUnit.getELiterals().add(createLiteral("visSec", 85));
	    eVisioUnit.getELiterals().add(createLiteral("visTypeUnit", 48));
	    eVisioUnit.getELiterals().add(createLiteral("viseVisioUnitColor", 251));
	    eVisioUnit.getELiterals().add(createLiteral("viseVisioUnitGUID", 95));
	    eVisioUnit.getELiterals().add(createLiteral("viseVisioUnitInval", 255));
	    eVisioUnit.getELiterals().add(createLiteral("viseVisioUnitNURBS", 138));
	    eVisioUnit.getELiterals().add(createLiteral("viseVisioUnitPoint", 225));
	    eVisioUnit.getELiterals().add(createLiteral("viseVisioUnitPolyline", 139));
	    eVisioUnit.getELiterals().add(createLiteral("visYards", 75));
		return eVisioUnit;
	}
	

	private EEnumLiteral createLiteral(String name, int value) {
		
		EEnumLiteral literal = fac.createEEnumLiteral();
		literal.setName(name);
		literal.setValue(value);	
		return literal;
	}

	public EClass getEVisioIdElement() {
		return eVisioIdElement;
	}

	public EClass getEVisioNamedElement() {
		return eVisioNamedElement;
	}

	public EClass getEVisioDocument() {
		return eVisioDocument;
	}

	public EClass getEVisioPage() {
		return eVisioPage;
	}

	public EClass getEVisioShape() {
		return eVisioShape;
	}

	public EClass getEVisioAttribute() {
		return eVisioAttribute;
	}

	public EClass getEVisioConnectinShape() {
		return eVisioConnectinShape;
	}

	public EClass getEVisioShapeSheet() {
		return eVisioShapeSheet;
	}

	public EClass getEVisioSection() {
		return eVisioSection;
	}

	public EClass getEVisioRow() {
		return eVisioRow;
	}

	public EClass getEVisioCell() {
		return eVisioCell;
	}

	public EEnum getEVisioUnit() {
		return eVisioUnit;
	}

	public EClass getEVisioShapeWithoutMaster() {
		return eVisioShapeWithoutMaster;
	}
	
	public EPackage getEVisioMetamodel() {
		return emfVisioMetamodel;
	}
	
	
	public void addSubPackage(EPackage emfStencilPackage) {
		
		if(!isContainedVisioPackage(emfStencilPackage))
			emfVisioMetamodel.getESubpackages().add(emfStencilPackage);
		
	}
	
	private boolean isContainedVisioPackage(EPackage pack) {
		
		for(EPackage visioPackage : this.emfVisioMetamodel.getESubpackages()) {
			if(isEqualVisioPackage(visioPackage, pack))
				return true;
		}
		
		return false;
	}
	
	private boolean isEqualVisioPackage(EPackage package1, EPackage package2) {
		
		String package1Path = package1.getEAnnotation("visio").getDetails().get("path");
		String package2Path = package2.getEAnnotation("visio").getDetails().get("path");
		
		if(package1Path.equals(package2Path) || package1.getName().equals(package2.getName()) ||
				package1.getNsURI().equals(package2.getNsURI()))
			return true;
		
		return false;
	}
	
}

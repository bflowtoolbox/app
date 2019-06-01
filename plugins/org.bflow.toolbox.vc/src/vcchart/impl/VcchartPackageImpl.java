/**
 */
package vcchart.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import vcchart.Activity1;
import vcchart.Activity2;
import vcchart.All;
import vcchart.All_Rel_1;
import vcchart.All_Rel_1_EndPoint;
import vcchart.All_Rel_3;
import vcchart.Application;
import vcchart.Cluster;
import vcchart.Document;
import vcchart.Model;
import vcchart.NamedElement;
import vcchart.Objective;
import vcchart.Participant;
import vcchart.Product;
import vcchart.Relation1;
import vcchart.Relation2;
import vcchart.Relation3;
import vcchart.RelationsObject;
import vcchart.TechnicalTerm;
import vcchart.VcchartFactory;
import vcchart.VcchartPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class VcchartPackageImpl extends EPackageImpl implements VcchartPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass allEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass all_Rel_1EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass all_Rel_1_EndPointEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass all_Rel_3EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass relationsObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activity1EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass activity2EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass productEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass objectiveEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass clusterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass technicalTermEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass participantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass applicationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass relation1EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass relation2EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass relation3EClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see vcchart.VcchartPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private VcchartPackageImpl() {
		super(eNS_URI, VcchartFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link VcchartPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static VcchartPackage init() {
		if (isInited) return (VcchartPackage)EPackage.Registry.INSTANCE.getEPackage(VcchartPackage.eNS_URI);

		// Obtain or create and register package
		VcchartPackageImpl theVcchartPackage = (VcchartPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof VcchartPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new VcchartPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theVcchartPackage.createPackageContents();

		// Initialize created meta-data
		theVcchartPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theVcchartPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(VcchartPackage.eNS_URI, theVcchartPackage);
		return theVcchartPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModel() {
		return modelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Activitys1() {
		return (EReference)modelEClass.getEStructuralFeatures().get(0);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Activitys2() {
		return (EReference)modelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Clusters() {
		return (EReference)modelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Products() {
		return (EReference)modelEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_TechnicalTerms() {
		return (EReference)modelEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Objectives() {
		return (EReference)modelEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Participants() {
		return (EReference)modelEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Applications() {
		return (EReference)modelEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Documents() {
		return (EReference)modelEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Relations1() {
		return (EReference)modelEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Relations2() {
		return (EReference)modelEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Relations3() {
		return (EReference)modelEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamedElement() {
		return namedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedElement_Name() {
		return (EAttribute)namedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAll() {
		return allEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAll_Rel_1() {
		return all_Rel_1EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAll_Rel_1_EndPoint() {
		return all_Rel_1_EndPointEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAll_Rel_3() {
		return all_Rel_3EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRelationsObject() {
		return relationsObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActivity1() {
		return activity1EClass;
	}
	
	/**
	 * @generated NOT
	 */
	public EAttribute getActivity1_Subdiagram() {
		return (EAttribute)activity1EClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActivity2() {
		return activity2EClass;
	}
	
	/**
	 * @generated NOT
	 */
	public EAttribute getActivity2_Subdiagram() {
		return (EAttribute)activity2EClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProduct() {
		return productEClass;
	}
	
	/**
	 * @generated NOT
	 */
	public EAttribute getProduct_Subdiagram() {
		return (EAttribute)productEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getObjective() {
		return objectiveEClass;
	}
	
	/**
	 * @generated NOT
	 */
	public EAttribute getObjective_Subdiagram() {
		return (EAttribute) objectiveEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCluster() {
		return clusterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTechnicalTerm() {
		return technicalTermEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParticipant() {
		return participantEClass;
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public EAttribute getParticipant_Subdiagram() {
		return (EAttribute) participantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getApplication() {
		return applicationEClass;
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public EAttribute getApplication_Subdiagram() {
		return (EAttribute) applicationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDocument() {
		return documentEClass;
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public EAttribute getDocument_Subdiagram() {
		return (EAttribute) documentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRelation1() {
		return relation1EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRelation1_Source() {
		return (EReference)relation1EClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRelation1_Target() {
		return (EReference)relation1EClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRelation2() {
		return relation2EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRelation2_Source() {
		return (EReference)relation2EClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRelation2_Target() {
		return (EReference)relation2EClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRelation3() {
		return relation3EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRelation3_Source() {
		return (EReference)relation3EClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRelation3_Target() {
		return (EReference)relation3EClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VcchartFactory getVcchartFactory() {
		return (VcchartFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		modelEClass = createEClass(MODEL);
		createEReference(modelEClass, MODEL__ACTIVITYS1);
		createEReference(modelEClass, MODEL__ACTIVITYS2);
		createEReference(modelEClass, MODEL__CLUSTERS);
		createEReference(modelEClass, MODEL__PRODUCTS);
		createEReference(modelEClass, MODEL__TECHNICAL_TERMS);
		createEReference(modelEClass, MODEL__OBJECTIVES);
		createEReference(modelEClass, MODEL__PARTICIPANTS);
		createEReference(modelEClass, MODEL__APPLICATIONS);
		createEReference(modelEClass, MODEL__DOCUMENTS);
		createEReference(modelEClass, MODEL__RELATIONS1);
		createEReference(modelEClass, MODEL__RELATIONS2);
		createEReference(modelEClass, MODEL__RELATIONS3);

		namedElementEClass = createEClass(NAMED_ELEMENT);
		createEAttribute(namedElementEClass, NAMED_ELEMENT__NAME);

		allEClass = createEClass(ALL);

		all_Rel_1EClass = createEClass(ALL_REL_1);

		all_Rel_1_EndPointEClass = createEClass(ALL_REL_1END_POINT);

		all_Rel_3EClass = createEClass(ALL_REL_3);

		relationsObjectEClass = createEClass(RELATIONS_OBJECT);

		activity1EClass = createEClass(ACTIVITY1);
		createEAttribute(activity1EClass, ACTIVITY1__SUBDIAGRAM);

		activity2EClass = createEClass(ACTIVITY2);
		createEAttribute(activity2EClass, ACTIVITY2__SUBDIAGRAM);

		productEClass = createEClass(PRODUCT);
		createEAttribute(productEClass, PRODUCT__SUBDIAGRAM);

		objectiveEClass = createEClass(OBJECTIVE);
		createEAttribute(objectiveEClass, OBJECTIVE__SUBDIAGRAM);

		clusterEClass = createEClass(CLUSTER);

		technicalTermEClass = createEClass(TECHNICAL_TERM);

		participantEClass = createEClass(PARTICIPANT);
		createEAttribute(participantEClass, PARTICIPANT__SUBDIAGRAM);

		applicationEClass = createEClass(APPLICATION);
		createEAttribute(applicationEClass, APPLICATION__SUBDIAGRAM);

		documentEClass = createEClass(DOCUMENT);
		createEAttribute(documentEClass, DOCUMENT__SUBDIAGRAM);

		relation1EClass = createEClass(RELATION1);
		createEReference(relation1EClass, RELATION1__SOURCE);
		createEReference(relation1EClass, RELATION1__TARGET);

		relation2EClass = createEClass(RELATION2);
		createEReference(relation2EClass, RELATION2__SOURCE);
		createEReference(relation2EClass, RELATION2__TARGET);

		relation3EClass = createEClass(RELATION3);
		createEReference(relation3EClass, RELATION3__SOURCE);
		createEReference(relation3EClass, RELATION3__TARGET);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated Not
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		all_Rel_1EClass.getESuperTypes().add(this.getAll());
		all_Rel_1_EndPointEClass.getESuperTypes().add(this.getAll_Rel_1());
		all_Rel_3EClass.getESuperTypes().add(this.getAll());
		relationsObjectEClass.getESuperTypes().add(this.getAll());
		activity1EClass.getESuperTypes().add(this.getNamedElement());
		activity1EClass.getESuperTypes().add(this.getAll_Rel_1());
		activity1EClass.getESuperTypes().add(this.getAll_Rel_3());
		activity2EClass.getESuperTypes().add(this.getNamedElement());
		activity2EClass.getESuperTypes().add(this.getAll_Rel_1());
		activity2EClass.getESuperTypes().add(this.getAll_Rel_3());
		productEClass.getESuperTypes().add(this.getNamedElement());
		productEClass.getESuperTypes().add(this.getAll_Rel_1_EndPoint());
		productEClass.getESuperTypes().add(this.getAll_Rel_3());
		objectiveEClass.getESuperTypes().add(this.getNamedElement());
		objectiveEClass.getESuperTypes().add(this.getAll_Rel_1_EndPoint());
		objectiveEClass.getESuperTypes().add(this.getAll_Rel_3());
		clusterEClass.getESuperTypes().add(this.getNamedElement());
		clusterEClass.getESuperTypes().add(this.getAll_Rel_3());
		clusterEClass.getESuperTypes().add(this.getRelationsObject());
		technicalTermEClass.getESuperTypes().add(this.getNamedElement());
		technicalTermEClass.getESuperTypes().add(this.getAll_Rel_3());
		technicalTermEClass.getESuperTypes().add(this.getRelationsObject());
		participantEClass.getESuperTypes().add(this.getNamedElement());
		participantEClass.getESuperTypes().add(this.getAll_Rel_3());
		participantEClass.getESuperTypes().add(this.getRelationsObject());
		applicationEClass.getESuperTypes().add(this.getNamedElement());
		applicationEClass.getESuperTypes().add(this.getAll_Rel_3());
		applicationEClass.getESuperTypes().add(this.getRelationsObject());
		documentEClass.getESuperTypes().add(this.getNamedElement());
		documentEClass.getESuperTypes().add(this.getAll_Rel_3());
		documentEClass.getESuperTypes().add(this.getRelationsObject());

		// Initialize classes, features, and operations; add parameters
		initEClass(modelEClass, Model.class, "Model", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getModel_Activitys1(), this.getActivity1(), null, "Activitys1", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivity1_Subdiagram(), ecorePackage.getEString(), "Subdiagram", null, 0, 1, Activity1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEReference(getModel_Activitys2(), this.getActivity2(), null, "Activitys2", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActivity2_Subdiagram(), ecorePackage.getEString(), "Subdiagram", null, 0, 1, Activity2.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		
		initEReference(getModel_Clusters(), this.getCluster(), null, "Clusters", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		
		initEReference(getModel_Products(), this.getProduct(), null, "Products", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProduct_Subdiagram(), ecorePackage.getEString(), "Subdiagram", null, 0, 1, Product.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		
		initEReference(getModel_TechnicalTerms(), this.getTechnicalTerm(), null, "TechnicalTerms", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		
		initEReference(getModel_Objectives(), this.getObjective(), null, "Objectives", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getObjective_Subdiagram(), ecorePackage.getEString(), "Subdiagram", null, 0, 1, Objective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		
		initEReference(getModel_Participants(), this.getParticipant(), null, "Participants", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getParticipant_Subdiagram(), ecorePackage.getEString(), "Subdiagram", null, 0, 1, Participant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		
		initEReference(getModel_Applications(), this.getApplication(), null, "Applications", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getApplication_Subdiagram(), ecorePackage.getEString(), "Subdiagram", null, 0, 1, Application.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		
		initEReference(getModel_Documents(), this.getDocument(), null, "Documents", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocument_Subdiagram(), ecorePackage.getEString(), "Subdiagram", null, 0, 1, Document.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		
		initEReference(getModel_Relations1(), this.getRelation1(), null, "Relations1", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Relations2(), this.getRelation2(), null, "Relations2", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Relations3(), this.getRelation3(), null, "Relations3", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(namedElementEClass, NamedElement.class, "NamedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamedElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(allEClass, All.class, "All", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(all_Rel_1EClass, All_Rel_1.class, "All_Rel_1", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(all_Rel_1_EndPointEClass, All_Rel_1_EndPoint.class, "All_Rel_1_EndPoint", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(all_Rel_3EClass, All_Rel_3.class, "All_Rel_3", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(relationsObjectEClass, RelationsObject.class, "RelationsObject", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(activity1EClass, Activity1.class, "Activity1", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(activity2EClass, Activity2.class, "Activity2", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(productEClass, Product.class, "Product", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(objectiveEClass, Objective.class, "Objective", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(clusterEClass, Cluster.class, "Cluster", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(technicalTermEClass, TechnicalTerm.class, "TechnicalTerm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(participantEClass, Participant.class, "Participant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(applicationEClass, Application.class, "Application", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(documentEClass, Document.class, "Document", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(relation1EClass, Relation1.class, "Relation1", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRelation1_Source(), this.getAll_Rel_1(), null, "source", null, 0, 1, Relation1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRelation1_Target(), this.getAll_Rel_1(), null, "target", null, 0, 1, Relation1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(relation2EClass, Relation2.class, "Relation2", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRelation2_Source(), this.getAll_Rel_1(), null, "source", null, 0, 1, Relation2.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRelation2_Target(), this.getAll_Rel_1(), null, "target", null, 0, 1, Relation2.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(relation3EClass, Relation3.class, "Relation3", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRelation3_Source(), this.getAll_Rel_3(), null, "source", null, 0, 1, Relation3.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRelation3_Target(), this.getAll_Rel_3(), null, "target", null, 0, 1, Relation3.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// gmf.diagram
		createGmfAnnotations();
		// gmf.node
		createGmf_1Annotations();
		// gmf.link
		createGmf_2Annotations();
	}

	/**
	 * Initializes the annotations for <b>gmf.diagram</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createGmfAnnotations() {
		String source = "gmf.diagram";	
		addAnnotation
		  (modelEClass, 
		   source, 
		   new String[] {
			 "onefile", "true",
			 "diagram.extension", "vc"
		   });
	}

	/**
	 * Initializes the annotations for <b>gmf.node</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createGmf_1Annotations() {
		String source = "gmf.node";	
		addAnnotation
		  (activity1EClass, 
		   source, 
		   new String[] {
			 "label", "name",
			 "label.icon", "false",
			 "figure", "polygon",
			 "polygon.x", "0 0 85 100 85 0",
			 "polygon.y", "0 50 50 25 0 0",
			 "color", "0,248,0",
			 "size", "100,50",
			 "border.color", "0,0,0"
		   });	
		addAnnotation
		  (activity2EClass, 
		   source, 
		   new String[] {
			 "label", "name",
			 "label.icon", "false",
			 "figure", "polygon",
			 "polygon.x", "0 15 0 85 100 85 0",
			 "polygon.y", "0 25 50 50 25 0 0",
			 "color", "0,248,0",
			 "size", "100,50",
			 "border.color", "0,0,0"
		   });	
		addAnnotation
		  (productEClass, 
		   source, 
		   new String[] {
			 "label", "name",
			 "label.icon", "false",
			 "figure", "polygon",
			 "polygon.x", "0 50 100 100 0 0 100 0",
			 "polygon.y", "10 0 10 50 50 42 42 42",
			 "color", "0,248,0",
			 "size", "100,50",
			 "border.color", "0,0,0"
		   });	
		addAnnotation
		  (objectiveEClass, 
		   source, 
		   new String[] {
			 "label", "name",
			 "label.icon", "false",
			 "figure", "polygon",
			 "polygon.x", "0 50 100 100 0",
			 "polygon.y", "10 0 10 50 50",
			 "color", "0,248,0",
			 "size", "100,50",
			 "border.color", "0,0,0"
		   });	
		addAnnotation
		  (clusterEClass, 
		   source, 
		   new String[] {
			 "label", "name",
			 "label.icon", "false",
			 "figure", "polygon",
			 "polygon.x", "0 10 10 10 90 90 90 100 100 0",
			 "polygon.y", "0 0 50 0 0 50 0 0 50 50",
			 "color", "223,22,22",
			 "size", "100,50",
			 "border.color", "0,0,0"
		   });	
		addAnnotation
		  (technicalTermEClass, 
		   source, 
		   new String[] {
			 "label", "name",
			 "label.icon", "false",
			 "figure", "rectangle",
			 "size", "100,50",
			 "border.color", "0,0,0"
		   });	
		addAnnotation
		  (participantEClass, 
		   source, 
		   new String[] {
			 "label", "name",
			 "label.icon", "false",
			 "figure", "ellipse",
			 "size", "100,50",
			 "border.color", "0,0,0",
			 "color", "22,22,22"
		   });	
		addAnnotation
		  (applicationEClass, 
		   source, 
		   new String[] {
			 "label", "name",
			 "label.icon", "false",
			 "figure", "rectangle",
			 "size", "100,50",
			 "border.color", "0,0,0",
			 "color", "22,22,22"
		   });	
		addAnnotation
		  (documentEClass, 
		   source, 
		   new String[] {
			 "label", "name",
			 "label.icon", "false",
			 "figure", "rectangle",
			 "size", "100,50",
			 "border.color", "0,0,0",
			 "color", "22,22,22"
		   });
	}

	/**
	 * Initializes the annotations for <b>gmf.link</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createGmf_2Annotations() {
		String source = "gmf.link";	
		addAnnotation
		  (relation1EClass, 
		   source, 
		   new String[] {
			 "source", "source",
			 "target", "target",
			 "style", "dash",
			 "target.decoration", "arrow",
			 "color", "0,0,0",
			 "source.constraint", "self.oclIsKindOf(All_Rel_1) and self <> oppositeEnd",
			 "target.constraint", "(self.oclIsKindOf(All_Rel_1) and oppositeEnd.oclIsKindOf(All_Rel_1)) and not (self.oclIsTypeOf(Activity1)) and not (oppositeEnd.oclIsKindOf(All_Rel_1_EndPoint))"
		   });	
		addAnnotation
		  (relation2EClass, 
		   source, 
		   new String[] {
			 "source", "source",
			 "target", "target",
			 "color", "0,0,0",
			 "target.decoration", "arrow",
			 "source.constraint", "self.oclIsKindOf(All_Rel_1) and self <> oppositeEnd",
			 "target.constraint", "(self.oclIsTypeOf(Activity1) and oppositeEnd.oclIsTypeOf(Activity1)) or (self.oclIsTypeOf(Activity2) and oppositeEnd.oclIsTypeOf(Activity2)) or (self.oclIsTypeOf(Objective) and oppositeEnd.oclIsTypeOf(Objective)) or (self.oclIsTypeOf(Product) and oppositeEnd.oclIsTypeOf(Product))"
		   });	
		addAnnotation
		  (relation3EClass, 
		   source, 
		   new String[] {
			 "source", "source",
			 "target", "target",
			 "color", "0,0,0",
			 "source.constraint", "self <> oppositeEnd",
			 "target.constraint", "(self.oclIsKindOf(All_Rel_1) and oppositeEnd.oclIsKindOf(RelationsObject)) or (self.oclIsKindOf(RelationsObject) and oppositeEnd.oclIsKindOf(All_Rel_1))"
		   });
	}

} //VcchartPackageImpl

/**
 */
package orgchart.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import orgchart.All;
import orgchart.All_Rel_1;
import orgchart.All_Rel_2;
import orgchart.ExternalPerson;
import orgchart.Group;
import orgchart.InternalPerson;
import orgchart.Location;
import orgchart.Model;
import orgchart.NamedElement;
import orgchart.OrgchartFactory;
import orgchart.OrgchartPackage;
import orgchart.Participant;
import orgchart.Person;
import orgchart.PersonType;
import orgchart.Position;
import orgchart.Relation1;
import orgchart.Relation2;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OrgchartPackageImpl extends EPackageImpl implements OrgchartPackage {
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
	private EClass all_Rel_2EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass personEClass = null;

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
	private EClass locationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass groupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass positionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass internalPersonEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass externalPersonEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass personTypeEClass = null;

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
	 * @see orgchart.OrgchartPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private OrgchartPackageImpl() {
		super(eNS_URI, OrgchartFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link OrgchartPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static OrgchartPackage init() {
		if (isInited) return (OrgchartPackage)EPackage.Registry.INSTANCE.getEPackage(OrgchartPackage.eNS_URI);

		// Obtain or create and register package
		OrgchartPackageImpl theOrgchartPackage = (OrgchartPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof OrgchartPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new OrgchartPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theOrgchartPackage.createPackageContents();

		// Initialize created meta-data
		theOrgchartPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theOrgchartPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(OrgchartPackage.eNS_URI, theOrgchartPackage);
		return theOrgchartPackage;
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
	public EReference getModel_Participants() {
		return (EReference)modelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Positions() {
		return (EReference)modelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_InternalPersons() {
		return (EReference)modelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_ExternalPersons() {
		return (EReference)modelEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_PersonTypes() {
		return (EReference)modelEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Locations() {
		return (EReference)modelEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Groups() {
		return (EReference)modelEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Relations1() {
		return (EReference)modelEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Relations2() {
		return (EReference)modelEClass.getEStructuralFeatures().get(8);
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
	public EClass getAll_Rel_2() {
		return all_Rel_2EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPerson() {
		return personEClass;
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
	public EClass getLocation() {
		return locationEClass;
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public EAttribute getLocation_Subdiagram() {
		return (EAttribute) locationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGroup() {
		return groupEClass;
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public EAttribute getGroup_Subdiagram() {
		return (EAttribute) groupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPosition() {
		return positionEClass;
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public EAttribute getPosition_Subdiagram() {
		return (EAttribute) positionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInternalPerson() {
		return internalPersonEClass;
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public EAttribute getInternalPerson_Subdiagram() {
		return (EAttribute) internalPersonEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExternalPerson() {
		return externalPersonEClass;
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public EAttribute getExternalPerson_Subdiagram() {
		return (EAttribute) externalPersonEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPersonType() {
		return personTypeEClass;
	}
	
	/**
	 * @generated NOT
	 */
	@Override
	public EAttribute getPersonType_Subdiagram() {
		return (EAttribute) personTypeEClass.getEStructuralFeatures().get(0);
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
	public OrgchartFactory getOrgchartFactory() {
		return (OrgchartFactory)getEFactoryInstance();
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
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		modelEClass = createEClass(MODEL);
		createEReference(modelEClass, MODEL__PARTICIPANTS);
		createEReference(modelEClass, MODEL__POSITIONS);
		createEReference(modelEClass, MODEL__INTERNAL_PERSONS);
		createEReference(modelEClass, MODEL__EXTERNAL_PERSONS);
		createEReference(modelEClass, MODEL__PERSON_TYPES);
		createEReference(modelEClass, MODEL__LOCATIONS);
		createEReference(modelEClass, MODEL__GROUPS);
		createEReference(modelEClass, MODEL__RELATIONS1);
		createEReference(modelEClass, MODEL__RELATIONS2);

		namedElementEClass = createEClass(NAMED_ELEMENT);
		createEAttribute(namedElementEClass, NAMED_ELEMENT__NAME);

		allEClass = createEClass(ALL);

		all_Rel_1EClass = createEClass(ALL_REL_1);

		all_Rel_2EClass = createEClass(ALL_REL_2);

		personEClass = createEClass(PERSON);

		participantEClass = createEClass(PARTICIPANT);
		createEAttribute(participantEClass, PARTICIPANT__SUBDIAGRAM);

		locationEClass = createEClass(LOCATION);
		createEAttribute(locationEClass, LOCATION__SUBDIAGRAM);

		groupEClass = createEClass(GROUP);
		createEAttribute(groupEClass, GROUP__SUBDIAGRAM);

		positionEClass = createEClass(POSITION);
		createEAttribute(positionEClass, POSITION__SUBDIAGRAM);

		internalPersonEClass = createEClass(INTERNAL_PERSON);
		createEAttribute(internalPersonEClass, INTERNAL_PERSON__SUBDIAGRAM);

		externalPersonEClass = createEClass(EXTERNAL_PERSON);
		createEAttribute(externalPersonEClass, EXTERNAL_PERSON__SUBDIAGRAM);
		
		personTypeEClass = createEClass(PERSON_TYPE);
		createEAttribute(personTypeEClass, PERSON_TYPE__SUBDIAGRAM);

		relation1EClass = createEClass(RELATION1);
		createEReference(relation1EClass, RELATION1__SOURCE);
		createEReference(relation1EClass, RELATION1__TARGET);

		relation2EClass = createEClass(RELATION2);
		createEReference(relation2EClass, RELATION2__SOURCE);
		createEReference(relation2EClass, RELATION2__TARGET);
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
	 * @generated
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
		all_Rel_2EClass.getESuperTypes().add(this.getAll());
		personEClass.getESuperTypes().add(this.getAll_Rel_2());
		participantEClass.getESuperTypes().add(this.getNamedElement());
		participantEClass.getESuperTypes().add(this.getAll_Rel_1());
		locationEClass.getESuperTypes().add(this.getNamedElement());
		locationEClass.getESuperTypes().add(this.getAll_Rel_1());
		groupEClass.getESuperTypes().add(this.getNamedElement());
		groupEClass.getESuperTypes().add(this.getAll_Rel_1());
		groupEClass.getESuperTypes().add(this.getAll_Rel_2());
		positionEClass.getESuperTypes().add(this.getNamedElement());
		positionEClass.getESuperTypes().add(this.getAll_Rel_1());
		positionEClass.getESuperTypes().add(this.getAll_Rel_2());
		internalPersonEClass.getESuperTypes().add(this.getNamedElement());
		internalPersonEClass.getESuperTypes().add(this.getPerson());
		externalPersonEClass.getESuperTypes().add(this.getNamedElement());
		externalPersonEClass.getESuperTypes().add(this.getPerson());
		personTypeEClass.getESuperTypes().add(this.getNamedElement());
		personTypeEClass.getESuperTypes().add(this.getAll_Rel_2());

		// Initialize classes, features, and operations; add parameters
		initEClass(modelEClass, Model.class, "Model", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getModel_Participants(), this.getParticipant(), null, "Participants", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Positions(), this.getPosition(), null, "Positions", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_InternalPersons(), this.getInternalPerson(), null, "InternalPersons", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_ExternalPersons(), this.getExternalPerson(), null, "ExternalPersons", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_PersonTypes(), this.getPersonType(), null, "PersonTypes", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Locations(), this.getLocation(), null, "Locations", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Groups(), this.getGroup(), null, "Groups", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Relations1(), this.getRelation1(), null, "Relations1", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Relations2(), this.getRelation2(), null, "Relations2", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(namedElementEClass, NamedElement.class, "NamedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamedElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(allEClass, All.class, "All", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(all_Rel_1EClass, All_Rel_1.class, "All_Rel_1", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(all_Rel_2EClass, All_Rel_2.class, "All_Rel_2", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(personEClass, Person.class, "Person", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(participantEClass, Participant.class, "Participant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getParticipant_Subdiagram(), ecorePackage.getEString(), "Subdiagram", null, 0, 1, Participant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(locationEClass, Location.class, "Location", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLocation_Subdiagram(), ecorePackage.getEString(), "Subdiagram", null, 0, 1, Location.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		
		initEClass(groupEClass, Group.class, "Group", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGroup_Subdiagram(), ecorePackage.getEString(), "Subdiagram", null, 0, 1, Group.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		
		initEClass(positionEClass, Position.class, "Position", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPosition_Subdiagram(), ecorePackage.getEString(), "Subdiagram", null, 0, 1, Position.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		
		initEClass(internalPersonEClass, InternalPerson.class, "InternalPerson", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInternalPerson_Subdiagram(), ecorePackage.getEString(), "Subdiagram", null, 0, 1, InternalPerson.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		
		initEClass(externalPersonEClass, ExternalPerson.class, "ExternalPerson", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExternalPerson_Subdiagram(), ecorePackage.getEString(), "Subdiagram", null, 0, 1, ExternalPerson.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		
		initEClass(personTypeEClass, PersonType.class, "PersonType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPersonType_Subdiagram(), ecorePackage.getEString(), "Subdiagram", null, 0, 1, PersonType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		
		initEClass(relation1EClass, Relation1.class, "Relation1", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRelation1_Source(), this.getAll_Rel_1(), null, "source", null, 0, 1, Relation1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRelation1_Target(), this.getAll_Rel_1(), null, "target", null, 0, 1, Relation1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(relation2EClass, Relation2.class, "Relation2", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRelation2_Source(), this.getAll_Rel_2(), null, "source", null, 0, 1, Relation2.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRelation2_Target(), this.getAll_Rel_2(), null, "target", null, 0, 1, Relation2.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
			 "diagram.extension", "orgc"
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
		  (participantEClass, 
		   source, 
		   new String[] {
			 "label", "name",
			 "label.icon", "false",
			 "figure", "ellipse",
			 "color", "255,255,0",
			 "size", "100,50",
			 "border.color", "0,0,0"
		   });	
		addAnnotation
		  (locationEClass, 
		   source, 
		   new String[] {
			 "label", "name",
			 "label.icon", "false",
			 "figure", "ellipse",
			 "color", "255,255,0",
			 "size", "100,50",
			 "border.color", "0,0,0"
		   });	
		addAnnotation
		  (groupEClass, 
		   source, 
		   new String[] {
			 "label", "name",
			 "label.icon", "false",
			 "figure", "ellipse",
			 "color", "255,255,0",
			 "size", "100,50",
			 "border.color", "0,0,0"
		   });	
		addAnnotation
		  (positionEClass, 
		   source, 
		   new String[] {
			 "label", "name",
			 "label.icon", "false",
			 "figure", "rectangle",
			 "color", "255,255,0",
			 "size", "100,50",
			 "border.color", "0,0,0"
		   });	
		addAnnotation
		  (internalPersonEClass, 
		   source, 
		   new String[] {
			 "label", "name",
			 "label.icon", "false",
			 "figure", "rectangle",
			 "color", "255,255,0",
			 "size", "100,50",
			 "border.color", "0,0,0"
		   });	
		addAnnotation
		  (externalPersonEClass, 
		   source, 
		   new String[] {
			 "label", "name",
			 "label.icon", "false",
			 "figure", "rectangle",
			 "size", "100,50",
			 "border.color", "0,0,0"
		   });	
		addAnnotation
		  (personTypeEClass, 
		   source, 
		   new String[] {
			 "label", "name",
			 "label.icon", "false",
			 "figure", "rectangle",
			 "color", "255,255,0",
			 "size", "100,50",
			 "border.color", "0,0,0"
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
			 "target.decoration", "arrow",
			 "color", "0,0,0",
			 "source.constraint", "self.oclIsKindOf(All_Rel_1) and self <> oppositeEnd",
			 "target.constraint", "(self.oclIsTypeOf(Participant) and oppositeEnd.oclIsKindOf(All_Rel_1) and not oppositeEnd.oclIsKindOf(Position)) or (self.oclIsTypeOf(Group) and oppositeEnd.oclIsKindOf(All_Rel_1) and not oppositeEnd.oclIsKindOf(Position)) or (self.oclIsTypeOf(Location) and oppositeEnd.oclIsKindOf(All_Rel_1) and not oppositeEnd.oclIsKindOf(Position)) or (self.oclIsTypeOf(Position) and (oppositeEnd.oclIsTypeOf(Position) or oppositeEnd.oclIsTypeOf(Participant)))"
		   });	
		addAnnotation
		  (relation2EClass, 
		   source, 
		   new String[] {
			 "source", "source",
			 "target", "target",
			 "color", "0,0,0",
			 "source.constraint", "self.oclIsKindOf(All_Rel_2) and self <> oppositeEnd",
			 "target.constraint", "(self.oclIsTypeOf(Position) and oppositeEnd.oclIsKindOf(Person)) or (self.oclIsTypeOf(PersonType) and (oppositeEnd.oclIsTypeOf(PersonType) or oppositeEnd.oclIsKindOf(Person))) or (self.oclIsKindOf(Person) and (oppositeEnd.oclIsTypeOf(PersonType) or oppositeEnd.oclIsTypeOf(Position) or oppositeEnd.oclIsTypeOf(Group))) or (self.oclIsKindOf(Group) and (oppositeEnd.oclIsKindOf(Person)))"
		   });
	}

} //OrgchartPackageImpl

/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bflow.toolbox.epc.impl;

import org.bflow.toolbox.bflow.BflowPackage;
import org.bflow.toolbox.epc.Application;
import org.bflow.toolbox.epc.Arc;
import org.bflow.toolbox.epc.CardFile;
import org.bflow.toolbox.epc.Cluster;
import org.bflow.toolbox.epc.Document;
import org.bflow.toolbox.epc.Epc;
import org.bflow.toolbox.epc.EpcFactory;
import org.bflow.toolbox.epc.EpcPackage;
import org.bflow.toolbox.epc.Event;
import org.bflow.toolbox.epc.ExternalPerson;
import org.bflow.toolbox.epc.File;
import org.bflow.toolbox.epc.Function;
import org.bflow.toolbox.epc.Group;
import org.bflow.toolbox.epc.InformationArc;
import org.bflow.toolbox.epc.InternalPerson;
import org.bflow.toolbox.epc.Location;
import org.bflow.toolbox.epc.Objective;
import org.bflow.toolbox.epc.Participant;
import org.bflow.toolbox.epc.PersonType;
import org.bflow.toolbox.epc.Position;
import org.bflow.toolbox.epc.ProcessInterface;
import org.bflow.toolbox.epc.Product;
import org.bflow.toolbox.epc.Relation;
import org.bflow.toolbox.epc.TechnicalTerm;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EpcPackageImpl extends EPackageImpl implements EpcPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass functionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processInterfaceEClass = null;

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
	private EClass participantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass andEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass orEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass xorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arcEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass relationEClass = null;

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
	private EClass locationEClass = null;

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
	private EClass fileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cardFileEClass = null;

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
	private EClass technicalTermEClass = null;

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
	private EClass objectiveEClass = null;

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
	private EClass informationArcEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass epcEClass = null;

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
	 * @see org.bflow.toolbox.epc.EpcPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private EpcPackageImpl() {
		super(eNS_URI, EpcFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static EpcPackage init() {
		if (isInited) return (EpcPackage)EPackage.Registry.INSTANCE.getEPackage(EpcPackage.eNS_URI);

		// Obtain or create and register package
		EpcPackageImpl theEpcPackage = (EpcPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof EpcPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new EpcPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		BflowPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theEpcPackage.createPackageContents();

		// Initialize created meta-data
		theEpcPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theEpcPackage.freeze();

		return theEpcPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEvent() {
		return eventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFunction() {
		return functionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFunction_Subdiagram() {
		return (EAttribute)functionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessInterface() {
		return processInterfaceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcessInterface_Subdiagram() {
		return (EAttribute)processInterfaceEClass.getEStructuralFeatures().get(0);
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParticipant() {
		return participantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAND() {
		return andEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOR() {
		return orEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getXOR() {
		return xorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArc() {
		return arcEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRelation() {
		return relationEClass;
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLocation() {
		return locationEClass;
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFile() {
		return fileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCardFile() {
		return cardFileEClass;
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
	public EClass getInternalPerson() {
		return internalPersonEClass;
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPersonType() {
		return personTypeEClass;
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
	public EClass getDocument() {
		return documentEClass;
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProduct() {
		return productEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInformationArc() {
		return informationArcEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEpc() {
		return epcEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEpc_Elements() {
		return (EReference)epcEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEpc_Connections() {
		return (EReference)epcEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EpcFactory getEpcFactory() {
		return (EpcFactory)getEFactoryInstance();
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
		eventEClass = createEClass(EVENT);

		functionEClass = createEClass(FUNCTION);
		createEAttribute(functionEClass, FUNCTION__SUBDIAGRAM);

		processInterfaceEClass = createEClass(PROCESS_INTERFACE);
		createEAttribute(processInterfaceEClass, PROCESS_INTERFACE__SUBDIAGRAM);

		applicationEClass = createEClass(APPLICATION);

		participantEClass = createEClass(PARTICIPANT);

		andEClass = createEClass(AND);

		orEClass = createEClass(OR);

		xorEClass = createEClass(XOR);

		arcEClass = createEClass(ARC);

		relationEClass = createEClass(RELATION);

		groupEClass = createEClass(GROUP);

		locationEClass = createEClass(LOCATION);

		positionEClass = createEClass(POSITION);

		fileEClass = createEClass(FILE);

		cardFileEClass = createEClass(CARD_FILE);

		clusterEClass = createEClass(CLUSTER);

		internalPersonEClass = createEClass(INTERNAL_PERSON);

		externalPersonEClass = createEClass(EXTERNAL_PERSON);

		personTypeEClass = createEClass(PERSON_TYPE);

		technicalTermEClass = createEClass(TECHNICAL_TERM);

		documentEClass = createEClass(DOCUMENT);

		objectiveEClass = createEClass(OBJECTIVE);

		productEClass = createEClass(PRODUCT);

		informationArcEClass = createEClass(INFORMATION_ARC);

		epcEClass = createEClass(EPC);
		createEReference(epcEClass, EPC__ELEMENTS);
		createEReference(epcEClass, EPC__CONNECTIONS);
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

		// Obtain other dependent packages
		BflowPackage theBflowPackage = (BflowPackage)EPackage.Registry.INSTANCE.getEPackage(BflowPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		eventEClass.getESuperTypes().add(theBflowPackage.getElement());
		eventEClass.getESuperTypes().add(theBflowPackage.getIBflowElement());
		functionEClass.getESuperTypes().add(theBflowPackage.getElement());
		functionEClass.getESuperTypes().add(theBflowPackage.getIBflowElement());
		processInterfaceEClass.getESuperTypes().add(theBflowPackage.getElement());
		processInterfaceEClass.getESuperTypes().add(theBflowPackage.getIBflowElement());
		applicationEClass.getESuperTypes().add(theBflowPackage.getElement());
		applicationEClass.getESuperTypes().add(theBflowPackage.getIEBflowElement());
		participantEClass.getESuperTypes().add(theBflowPackage.getElement());
		participantEClass.getESuperTypes().add(theBflowPackage.getIEBflowElement());
		andEClass.getESuperTypes().add(theBflowPackage.getElement());
		andEClass.getESuperTypes().add(theBflowPackage.getIConnector());
		orEClass.getESuperTypes().add(theBflowPackage.getElement());
		orEClass.getESuperTypes().add(theBflowPackage.getIConnector());
		xorEClass.getESuperTypes().add(theBflowPackage.getElement());
		xorEClass.getESuperTypes().add(theBflowPackage.getIConnector());
		arcEClass.getESuperTypes().add(theBflowPackage.getConnection());
		relationEClass.getESuperTypes().add(theBflowPackage.getConnection());
		groupEClass.getESuperTypes().add(theBflowPackage.getElement());
		groupEClass.getESuperTypes().add(theBflowPackage.getIBflowElement());
		locationEClass.getESuperTypes().add(theBflowPackage.getElement());
		locationEClass.getESuperTypes().add(theBflowPackage.getIBflowElement());
		positionEClass.getESuperTypes().add(theBflowPackage.getElement());
		positionEClass.getESuperTypes().add(theBflowPackage.getIBflowElement());
		fileEClass.getESuperTypes().add(theBflowPackage.getElement());
		fileEClass.getESuperTypes().add(theBflowPackage.getIBflowElement());
		cardFileEClass.getESuperTypes().add(theBflowPackage.getElement());
		cardFileEClass.getESuperTypes().add(theBflowPackage.getIBflowElement());
		clusterEClass.getESuperTypes().add(theBflowPackage.getElement());
		clusterEClass.getESuperTypes().add(theBflowPackage.getIBflowElement());
		internalPersonEClass.getESuperTypes().add(theBflowPackage.getElement());
		internalPersonEClass.getESuperTypes().add(theBflowPackage.getIBflowElement());
		externalPersonEClass.getESuperTypes().add(theBflowPackage.getElement());
		externalPersonEClass.getESuperTypes().add(theBflowPackage.getIBflowElement());
		personTypeEClass.getESuperTypes().add(theBflowPackage.getElement());
		personTypeEClass.getESuperTypes().add(theBflowPackage.getIBflowElement());
		technicalTermEClass.getESuperTypes().add(theBflowPackage.getElement());
		technicalTermEClass.getESuperTypes().add(theBflowPackage.getIBflowElement());
		documentEClass.getESuperTypes().add(theBflowPackage.getElement());
		documentEClass.getESuperTypes().add(theBflowPackage.getIBflowElement());
		objectiveEClass.getESuperTypes().add(theBflowPackage.getElement());
		objectiveEClass.getESuperTypes().add(theBflowPackage.getIBflowElement());
		productEClass.getESuperTypes().add(theBflowPackage.getElement());
		productEClass.getESuperTypes().add(theBflowPackage.getIBflowElement());
		informationArcEClass.getESuperTypes().add(theBflowPackage.getConnection());

		// Initialize classes and features; add operations and parameters
		initEClass(eventEClass, Event.class, "Event", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(functionEClass, Function.class, "Function", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFunction_Subdiagram(), ecorePackage.getEString(), "Subdiagram", null, 0, -1, Function.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(processInterfaceEClass, ProcessInterface.class, "ProcessInterface", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProcessInterface_Subdiagram(), ecorePackage.getEString(), "Subdiagram", null, 0, 1, ProcessInterface.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(applicationEClass, Application.class, "Application", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(participantEClass, Participant.class, "Participant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(andEClass, org.bflow.toolbox.epc.AND.class, "AND", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(orEClass, org.bflow.toolbox.epc.OR.class, "OR", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(xorEClass, org.bflow.toolbox.epc.XOR.class, "XOR", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(arcEClass, Arc.class, "Arc", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(relationEClass, Relation.class, "Relation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(groupEClass, Group.class, "Group", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(locationEClass, Location.class, "Location", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(positionEClass, Position.class, "Position", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(fileEClass, File.class, "File", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cardFileEClass, CardFile.class, "CardFile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(clusterEClass, Cluster.class, "Cluster", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(internalPersonEClass, InternalPerson.class, "InternalPerson", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(externalPersonEClass, ExternalPerson.class, "ExternalPerson", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(personTypeEClass, PersonType.class, "PersonType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(technicalTermEClass, TechnicalTerm.class, "TechnicalTerm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(documentEClass, Document.class, "Document", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(objectiveEClass, Objective.class, "Objective", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(productEClass, Product.class, "Product", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(informationArcEClass, InformationArc.class, "InformationArc", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(epcEClass, Epc.class, "Epc", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEpc_Elements(), theBflowPackage.getElement(), null, "elements", null, 0, -1, Epc.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEpc_Connections(), theBflowPackage.getConnection(), null, "connections", null, 0, -1, Epc.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //EpcPackageImpl

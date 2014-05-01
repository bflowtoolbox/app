/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package oepc.impl;

import oepc.ANDConnector;
import oepc.BusinessAttribute;
import oepc.BusinessMethod;
import oepc.BusinessObject;
import oepc.BusinessObjectElement;
import oepc.ControlFlowEdge;
import oepc.Document;
import oepc.Event;
import oepc.ITSystem;
import oepc.InformationEdge;
import oepc.ORConnector;
import oepc.OepcFactory;
import oepc.OepcPackage;
import oepc.OrganisationUnit;
import oepc.XORConnector;

import org.bflow.toolbox.bflow.BflowPackage;

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
public class OepcPackageImpl extends EPackageImpl implements OepcPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oepcEClass = null;

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
	private EClass businessObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass itSystemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass organisationUnitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass xorConnectorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass andConnectorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass orConnectorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass controlFlowEdgeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass informationEdgeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass businessObjectElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass businessAttributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass businessMethodEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentEClass = null;

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
	 * @see oepc.OepcPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private OepcPackageImpl() {
		super(eNS_URI, OepcFactory.eINSTANCE);
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
	public static OepcPackage init() {
		if (isInited) return (OepcPackage)EPackage.Registry.INSTANCE.getEPackage(OepcPackage.eNS_URI);

		// Obtain or create and register package
		OepcPackageImpl theOepcPackage = (OepcPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof OepcPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new OepcPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		BflowPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theOepcPackage.createPackageContents();

		// Initialize created meta-data
		theOepcPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theOepcPackage.freeze();

		return theOepcPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOEPC() {
		return oepcEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOEPC_Elements() {
		return (EReference)oepcEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOEPC_Connections() {
		return (EReference)oepcEClass.getEStructuralFeatures().get(1);
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
	public EClass getBusinessObject() {
		return businessObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBusinessObject_Attributes() {
		return (EReference)businessObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBusinessObject_Methods() {
		return (EReference)businessObjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getITSystem() {
		return itSystemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOrganisationUnit() {
		return organisationUnitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getXORConnector() {
		return xorConnectorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getANDConnector() {
		return andConnectorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getORConnector() {
		return orConnectorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getControlFlowEdge() {
		return controlFlowEdgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInformationEdge() {
		return informationEdgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBusinessObjectElement() {
		return businessObjectElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBusinessObjectElement_Name() {
		return (EAttribute)businessObjectElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBusinessAttribute() {
		return businessAttributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBusinessMethod() {
		return businessMethodEClass;
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
	public OepcFactory getOepcFactory() {
		return (OepcFactory)getEFactoryInstance();
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
		oepcEClass = createEClass(OEPC);
		createEReference(oepcEClass, OEPC__ELEMENTS);
		createEReference(oepcEClass, OEPC__CONNECTIONS);

		eventEClass = createEClass(EVENT);

		businessObjectEClass = createEClass(BUSINESS_OBJECT);
		createEReference(businessObjectEClass, BUSINESS_OBJECT__ATTRIBUTES);
		createEReference(businessObjectEClass, BUSINESS_OBJECT__METHODS);

		itSystemEClass = createEClass(IT_SYSTEM);

		organisationUnitEClass = createEClass(ORGANISATION_UNIT);

		xorConnectorEClass = createEClass(XOR_CONNECTOR);

		andConnectorEClass = createEClass(AND_CONNECTOR);

		orConnectorEClass = createEClass(OR_CONNECTOR);

		controlFlowEdgeEClass = createEClass(CONTROL_FLOW_EDGE);

		informationEdgeEClass = createEClass(INFORMATION_EDGE);

		businessObjectElementEClass = createEClass(BUSINESS_OBJECT_ELEMENT);
		createEAttribute(businessObjectElementEClass, BUSINESS_OBJECT_ELEMENT__NAME);

		businessAttributeEClass = createEClass(BUSINESS_ATTRIBUTE);

		businessMethodEClass = createEClass(BUSINESS_METHOD);

		documentEClass = createEClass(DOCUMENT);
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
		businessObjectEClass.getESuperTypes().add(theBflowPackage.getElement());
		businessObjectEClass.getESuperTypes().add(theBflowPackage.getIBflowElement());
		itSystemEClass.getESuperTypes().add(theBflowPackage.getElement());
		itSystemEClass.getESuperTypes().add(theBflowPackage.getIBflowElement());
		organisationUnitEClass.getESuperTypes().add(theBflowPackage.getElement());
		organisationUnitEClass.getESuperTypes().add(theBflowPackage.getIBflowElement());
		xorConnectorEClass.getESuperTypes().add(theBflowPackage.getElement());
		xorConnectorEClass.getESuperTypes().add(theBflowPackage.getIConnector());
		andConnectorEClass.getESuperTypes().add(theBflowPackage.getElement());
		andConnectorEClass.getESuperTypes().add(theBflowPackage.getIConnector());
		orConnectorEClass.getESuperTypes().add(theBflowPackage.getElement());
		orConnectorEClass.getESuperTypes().add(theBflowPackage.getIConnector());
		controlFlowEdgeEClass.getESuperTypes().add(theBflowPackage.getConnection());
		informationEdgeEClass.getESuperTypes().add(theBflowPackage.getConnection());
		businessAttributeEClass.getESuperTypes().add(this.getBusinessObjectElement());
		businessMethodEClass.getESuperTypes().add(this.getBusinessObjectElement());
		documentEClass.getESuperTypes().add(theBflowPackage.getElement());
		documentEClass.getESuperTypes().add(theBflowPackage.getIBflowElement());

		// Initialize classes and features; add operations and parameters
		initEClass(oepcEClass, oepc.OEPC.class, "OEPC", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOEPC_Elements(), theBflowPackage.getElement(), null, "elements", null, 0, -1, oepc.OEPC.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOEPC_Connections(), theBflowPackage.getConnection(), null, "connections", null, 0, -1, oepc.OEPC.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(eventEClass, Event.class, "Event", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(businessObjectEClass, BusinessObject.class, "BusinessObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBusinessObject_Attributes(), this.getBusinessAttribute(), null, "attributes", null, 0, -1, BusinessObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBusinessObject_Methods(), this.getBusinessMethod(), null, "methods", null, 0, -1, BusinessObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(itSystemEClass, ITSystem.class, "ITSystem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(organisationUnitEClass, OrganisationUnit.class, "OrganisationUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(xorConnectorEClass, XORConnector.class, "XORConnector", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(andConnectorEClass, ANDConnector.class, "ANDConnector", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(orConnectorEClass, ORConnector.class, "ORConnector", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(controlFlowEdgeEClass, ControlFlowEdge.class, "ControlFlowEdge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(informationEdgeEClass, InformationEdge.class, "InformationEdge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(businessObjectElementEClass, BusinessObjectElement.class, "BusinessObjectElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBusinessObjectElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, BusinessObjectElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(businessAttributeEClass, BusinessAttribute.class, "BusinessAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(businessMethodEClass, BusinessMethod.class, "BusinessMethod", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(documentEClass, Document.class, "Document", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //OepcPackageImpl

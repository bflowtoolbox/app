/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bflow.toolbox.vc.impl;

import org.bflow.toolbox.bflow.BflowPackage;
import org.bflow.toolbox.vc.Cluster;
import org.bflow.toolbox.vc.Objective;
import org.bflow.toolbox.vc.PredecessorConnection;
import org.bflow.toolbox.vc.ProcessSuperiority;
import org.bflow.toolbox.vc.Product;
import org.bflow.toolbox.vc.Relation;
import org.bflow.toolbox.vc.TechnicalTerm;
import org.bflow.toolbox.vc.ValueChain;
import org.bflow.toolbox.vc.ValueChain2;
import org.bflow.toolbox.vc.Vc;
import org.bflow.toolbox.vc.VcFactory;
import org.bflow.toolbox.vc.VcPackage;
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
public class VcPackageImpl extends EPackageImpl implements VcPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass valueChainEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass valueChain2EClass = null;

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
	private EClass clusterEClass = null;

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
	private EClass predecessorConnectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass processSuperiorityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vcEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass relationEClass = null;

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
	 * @see org.bflow.toolbox.vc.VcPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private VcPackageImpl() {
		super(eNS_URI, VcFactory.eINSTANCE);
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
	public static VcPackage init() {
		if (isInited) return (VcPackage)EPackage.Registry.INSTANCE.getEPackage(VcPackage.eNS_URI);

		// Obtain or create and register package
		VcPackageImpl theVcPackage = (VcPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof VcPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new VcPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		BflowPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theVcPackage.createPackageContents();

		// Initialize created meta-data
		theVcPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theVcPackage.freeze();

		return theVcPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getValueChain() {
		return valueChainEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getValueChain_Subdiagram() {
		return (EAttribute)valueChainEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getValueChain2() {
		return valueChain2EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getValueChain2_Subdiagram() {
		return (EAttribute)valueChain2EClass.getEStructuralFeatures().get(0);
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
	public EClass getCluster() {
		return clusterEClass;
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
	public EClass getPredecessorConnection() {
		return predecessorConnectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcessSuperiority() {
		return processSuperiorityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVc() {
		return vcEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVc_Elements() {
		return (EReference)vcEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVc_Connections() {
		return (EReference)vcEClass.getEStructuralFeatures().get(1);
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
	public VcFactory getVcFactory() {
		return (VcFactory)getEFactoryInstance();
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
		valueChainEClass = createEClass(VALUE_CHAIN);
		createEAttribute(valueChainEClass, VALUE_CHAIN__SUBDIAGRAM);

		valueChain2EClass = createEClass(VALUE_CHAIN2);
		createEAttribute(valueChain2EClass, VALUE_CHAIN2__SUBDIAGRAM);

		technicalTermEClass = createEClass(TECHNICAL_TERM);

		clusterEClass = createEClass(CLUSTER);

		objectiveEClass = createEClass(OBJECTIVE);

		productEClass = createEClass(PRODUCT);

		predecessorConnectionEClass = createEClass(PREDECESSOR_CONNECTION);

		processSuperiorityEClass = createEClass(PROCESS_SUPERIORITY);

		vcEClass = createEClass(VC);
		createEReference(vcEClass, VC__ELEMENTS);
		createEReference(vcEClass, VC__CONNECTIONS);

		relationEClass = createEClass(RELATION);
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
		valueChainEClass.getESuperTypes().add(theBflowPackage.getElement());
		valueChainEClass.getESuperTypes().add(theBflowPackage.getIBflowElement());
		valueChain2EClass.getESuperTypes().add(theBflowPackage.getElement());
		valueChain2EClass.getESuperTypes().add(theBflowPackage.getIBflowElement());
		technicalTermEClass.getESuperTypes().add(theBflowPackage.getElement());
		technicalTermEClass.getESuperTypes().add(theBflowPackage.getIBflowElement());
		clusterEClass.getESuperTypes().add(theBflowPackage.getElement());
		clusterEClass.getESuperTypes().add(theBflowPackage.getIBflowElement());
		objectiveEClass.getESuperTypes().add(theBflowPackage.getElement());
		objectiveEClass.getESuperTypes().add(theBflowPackage.getIBflowElement());
		productEClass.getESuperTypes().add(theBflowPackage.getElement());
		productEClass.getESuperTypes().add(theBflowPackage.getIBflowElement());
		predecessorConnectionEClass.getESuperTypes().add(theBflowPackage.getConnection());
		processSuperiorityEClass.getESuperTypes().add(theBflowPackage.getConnection());
		relationEClass.getESuperTypes().add(theBflowPackage.getConnection());

		// Initialize classes and features; add operations and parameters
		initEClass(valueChainEClass, ValueChain.class, "ValueChain", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getValueChain_Subdiagram(), ecorePackage.getEString(), "Subdiagram", null, 0, 1, ValueChain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(valueChain2EClass, ValueChain2.class, "ValueChain2", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getValueChain2_Subdiagram(), ecorePackage.getEString(), "Subdiagram", null, 0, 1, ValueChain2.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(technicalTermEClass, TechnicalTerm.class, "TechnicalTerm", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(clusterEClass, Cluster.class, "Cluster", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(objectiveEClass, Objective.class, "Objective", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(productEClass, Product.class, "Product", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(predecessorConnectionEClass, PredecessorConnection.class, "PredecessorConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(processSuperiorityEClass, ProcessSuperiority.class, "ProcessSuperiority", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(vcEClass, Vc.class, "Vc", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getVc_Elements(), theBflowPackage.getElement(), null, "elements", null, 0, -1, Vc.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVc_Connections(), theBflowPackage.getConnection(), null, "connections", null, 0, -1, Vc.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(relationEClass, Relation.class, "Relation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //VcPackageImpl

/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bflow.toolbox.vc;

import org.bflow.toolbox.bflow.BflowPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.bflow.toolbox.vc.VcFactory
 * @model kind="package"
 * @generated
 */
public interface VcPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "vc";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "org.bflow.toolbox.vc";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "vc";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	VcPackage eINSTANCE = org.bflow.toolbox.vc.impl.VcPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.vc.impl.ValueChainImpl <em>Value Chain</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.vc.impl.ValueChainImpl
	 * @see org.bflow.toolbox.vc.impl.VcPackageImpl#getValueChain()
	 * @generated
	 */
	int VALUE_CHAIN = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_CHAIN__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_CHAIN__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_CHAIN__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_CHAIN__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_CHAIN__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The feature id for the '<em><b>Subdiagram</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_CHAIN__SUBDIAGRAM = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Value Chain</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_CHAIN_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.vc.impl.ValueChain2Impl <em>Value Chain2</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.vc.impl.ValueChain2Impl
	 * @see org.bflow.toolbox.vc.impl.VcPackageImpl#getValueChain2()
	 * @generated
	 */
	int VALUE_CHAIN2 = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_CHAIN2__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_CHAIN2__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_CHAIN2__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_CHAIN2__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_CHAIN2__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The feature id for the '<em><b>Subdiagram</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_CHAIN2__SUBDIAGRAM = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Value Chain2</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_CHAIN2_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.vc.impl.TechnicalTermImpl <em>Technical Term</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.vc.impl.TechnicalTermImpl
	 * @see org.bflow.toolbox.vc.impl.VcPackageImpl#getTechnicalTerm()
	 * @generated
	 */
	int TECHNICAL_TERM = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_TERM__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_TERM__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_TERM__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_TERM__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_TERM__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>Technical Term</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_TERM_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.vc.impl.ClusterImpl <em>Cluster</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.vc.impl.ClusterImpl
	 * @see org.bflow.toolbox.vc.impl.VcPackageImpl#getCluster()
	 * @generated
	 */
	int CLUSTER = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTER__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTER__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTER__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTER__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTER__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>Cluster</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTER_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.vc.impl.ObjectiveImpl <em>Objective</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.vc.impl.ObjectiveImpl
	 * @see org.bflow.toolbox.vc.impl.VcPackageImpl#getObjective()
	 * @generated
	 */
	int OBJECTIVE = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTIVE__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTIVE__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTIVE__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTIVE__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTIVE__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>Objective</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTIVE_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.vc.impl.ProductImpl <em>Product</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.vc.impl.ProductImpl
	 * @see org.bflow.toolbox.vc.impl.VcPackageImpl#getProduct()
	 * @generated
	 */
	int PRODUCT = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>Product</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.vc.impl.PredecessorConnectionImpl <em>Predecessor Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.vc.impl.PredecessorConnectionImpl
	 * @see org.bflow.toolbox.vc.impl.VcPackageImpl#getPredecessorConnection()
	 * @generated
	 */
	int PREDECESSOR_CONNECTION = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDECESSOR_CONNECTION__NAME = BflowPackage.CONNECTION__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDECESSOR_CONNECTION__ID = BflowPackage.CONNECTION__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDECESSOR_CONNECTION__DESCRIPTION = BflowPackage.CONNECTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDECESSOR_CONNECTION__FROM = BflowPackage.CONNECTION__FROM;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDECESSOR_CONNECTION__TO = BflowPackage.CONNECTION__TO;

	/**
	 * The number of structural features of the '<em>Predecessor Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREDECESSOR_CONNECTION_FEATURE_COUNT = BflowPackage.CONNECTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.bflow.toolbox.vc.impl.ProcessSuperiorityImpl <em>Process Superiority</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.vc.impl.ProcessSuperiorityImpl
	 * @see org.bflow.toolbox.vc.impl.VcPackageImpl#getProcessSuperiority()
	 * @generated
	 */
	int PROCESS_SUPERIORITY = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_SUPERIORITY__NAME = BflowPackage.CONNECTION__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_SUPERIORITY__ID = BflowPackage.CONNECTION__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_SUPERIORITY__DESCRIPTION = BflowPackage.CONNECTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_SUPERIORITY__FROM = BflowPackage.CONNECTION__FROM;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_SUPERIORITY__TO = BflowPackage.CONNECTION__TO;

	/**
	 * The number of structural features of the '<em>Process Superiority</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_SUPERIORITY_FEATURE_COUNT = BflowPackage.CONNECTION_FEATURE_COUNT + 0;


	/**
	 * The meta object id for the '{@link org.bflow.toolbox.vc.impl.VcImpl <em>Vc</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.vc.impl.VcImpl
	 * @see org.bflow.toolbox.vc.impl.VcPackageImpl#getVc()
	 * @generated
	 */
	int VC = 8;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VC__ELEMENTS = 0;

	/**
	 * The feature id for the '<em><b>Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VC__CONNECTIONS = 1;

	/**
	 * The number of structural features of the '<em>Vc</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VC_FEATURE_COUNT = 2;


	/**
	 * The meta object id for the '{@link org.bflow.toolbox.vc.impl.RelationImpl <em>Relation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bflow.toolbox.vc.impl.RelationImpl
	 * @see org.bflow.toolbox.vc.impl.VcPackageImpl#getRelation()
	 * @generated
	 */
	int RELATION = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION__NAME = BflowPackage.CONNECTION__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION__ID = BflowPackage.CONNECTION__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION__DESCRIPTION = BflowPackage.CONNECTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION__FROM = BflowPackage.CONNECTION__FROM;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION__TO = BflowPackage.CONNECTION__TO;

	/**
	 * The number of structural features of the '<em>Relation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_FEATURE_COUNT = BflowPackage.CONNECTION_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.vc.ValueChain <em>Value Chain</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Chain</em>'.
	 * @see org.bflow.toolbox.vc.ValueChain
	 * @generated
	 */
	EClass getValueChain();

	/**
	 * Returns the meta object for the attribute '{@link org.bflow.toolbox.vc.ValueChain#getSubdiagram <em>Subdiagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Subdiagram</em>'.
	 * @see org.bflow.toolbox.vc.ValueChain#getSubdiagram()
	 * @see #getValueChain()
	 * @generated
	 */
	EAttribute getValueChain_Subdiagram();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.vc.ValueChain2 <em>Value Chain2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Chain2</em>'.
	 * @see org.bflow.toolbox.vc.ValueChain2
	 * @generated
	 */
	EClass getValueChain2();

	/**
	 * Returns the meta object for the attribute '{@link org.bflow.toolbox.vc.ValueChain2#getSubdiagram <em>Subdiagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Subdiagram</em>'.
	 * @see org.bflow.toolbox.vc.ValueChain2#getSubdiagram()
	 * @see #getValueChain2()
	 * @generated
	 */
	EAttribute getValueChain2_Subdiagram();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.vc.TechnicalTerm <em>Technical Term</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Technical Term</em>'.
	 * @see org.bflow.toolbox.vc.TechnicalTerm
	 * @generated
	 */
	EClass getTechnicalTerm();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.vc.Cluster <em>Cluster</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cluster</em>'.
	 * @see org.bflow.toolbox.vc.Cluster
	 * @generated
	 */
	EClass getCluster();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.vc.Objective <em>Objective</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Objective</em>'.
	 * @see org.bflow.toolbox.vc.Objective
	 * @generated
	 */
	EClass getObjective();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.vc.Product <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product</em>'.
	 * @see org.bflow.toolbox.vc.Product
	 * @generated
	 */
	EClass getProduct();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.vc.PredecessorConnection <em>Predecessor Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Predecessor Connection</em>'.
	 * @see org.bflow.toolbox.vc.PredecessorConnection
	 * @generated
	 */
	EClass getPredecessorConnection();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.vc.ProcessSuperiority <em>Process Superiority</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Superiority</em>'.
	 * @see org.bflow.toolbox.vc.ProcessSuperiority
	 * @generated
	 */
	EClass getProcessSuperiority();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.vc.Vc <em>Vc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Vc</em>'.
	 * @see org.bflow.toolbox.vc.Vc
	 * @generated
	 */
	EClass getVc();

	/**
	 * Returns the meta object for the containment reference list '{@link org.bflow.toolbox.vc.Vc#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see org.bflow.toolbox.vc.Vc#getElements()
	 * @see #getVc()
	 * @generated
	 */
	EReference getVc_Elements();

	/**
	 * Returns the meta object for the containment reference list '{@link org.bflow.toolbox.vc.Vc#getConnections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connections</em>'.
	 * @see org.bflow.toolbox.vc.Vc#getConnections()
	 * @see #getVc()
	 * @generated
	 */
	EReference getVc_Connections();

	/**
	 * Returns the meta object for class '{@link org.bflow.toolbox.vc.Relation <em>Relation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relation</em>'.
	 * @see org.bflow.toolbox.vc.Relation
	 * @generated
	 */
	EClass getRelation();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	VcFactory getVcFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.vc.impl.ValueChainImpl <em>Value Chain</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.vc.impl.ValueChainImpl
		 * @see org.bflow.toolbox.vc.impl.VcPackageImpl#getValueChain()
		 * @generated
		 */
		EClass VALUE_CHAIN = eINSTANCE.getValueChain();

		/**
		 * The meta object literal for the '<em><b>Subdiagram</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALUE_CHAIN__SUBDIAGRAM = eINSTANCE.getValueChain_Subdiagram();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.vc.impl.ValueChain2Impl <em>Value Chain2</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.vc.impl.ValueChain2Impl
		 * @see org.bflow.toolbox.vc.impl.VcPackageImpl#getValueChain2()
		 * @generated
		 */
		EClass VALUE_CHAIN2 = eINSTANCE.getValueChain2();

		/**
		 * The meta object literal for the '<em><b>Subdiagram</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALUE_CHAIN2__SUBDIAGRAM = eINSTANCE.getValueChain2_Subdiagram();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.vc.impl.TechnicalTermImpl <em>Technical Term</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.vc.impl.TechnicalTermImpl
		 * @see org.bflow.toolbox.vc.impl.VcPackageImpl#getTechnicalTerm()
		 * @generated
		 */
		EClass TECHNICAL_TERM = eINSTANCE.getTechnicalTerm();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.vc.impl.ClusterImpl <em>Cluster</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.vc.impl.ClusterImpl
		 * @see org.bflow.toolbox.vc.impl.VcPackageImpl#getCluster()
		 * @generated
		 */
		EClass CLUSTER = eINSTANCE.getCluster();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.vc.impl.ObjectiveImpl <em>Objective</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.vc.impl.ObjectiveImpl
		 * @see org.bflow.toolbox.vc.impl.VcPackageImpl#getObjective()
		 * @generated
		 */
		EClass OBJECTIVE = eINSTANCE.getObjective();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.vc.impl.ProductImpl <em>Product</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.vc.impl.ProductImpl
		 * @see org.bflow.toolbox.vc.impl.VcPackageImpl#getProduct()
		 * @generated
		 */
		EClass PRODUCT = eINSTANCE.getProduct();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.vc.impl.PredecessorConnectionImpl <em>Predecessor Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.vc.impl.PredecessorConnectionImpl
		 * @see org.bflow.toolbox.vc.impl.VcPackageImpl#getPredecessorConnection()
		 * @generated
		 */
		EClass PREDECESSOR_CONNECTION = eINSTANCE.getPredecessorConnection();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.vc.impl.ProcessSuperiorityImpl <em>Process Superiority</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.vc.impl.ProcessSuperiorityImpl
		 * @see org.bflow.toolbox.vc.impl.VcPackageImpl#getProcessSuperiority()
		 * @generated
		 */
		EClass PROCESS_SUPERIORITY = eINSTANCE.getProcessSuperiority();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.vc.impl.VcImpl <em>Vc</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.vc.impl.VcImpl
		 * @see org.bflow.toolbox.vc.impl.VcPackageImpl#getVc()
		 * @generated
		 */
		EClass VC = eINSTANCE.getVc();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VC__ELEMENTS = eINSTANCE.getVc_Elements();

		/**
		 * The meta object literal for the '<em><b>Connections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VC__CONNECTIONS = eINSTANCE.getVc_Connections();

		/**
		 * The meta object literal for the '{@link org.bflow.toolbox.vc.impl.RelationImpl <em>Relation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bflow.toolbox.vc.impl.RelationImpl
		 * @see org.bflow.toolbox.vc.impl.VcPackageImpl#getRelation()
		 * @generated
		 */
		EClass RELATION = eINSTANCE.getRelation();

	}

} //VcPackage

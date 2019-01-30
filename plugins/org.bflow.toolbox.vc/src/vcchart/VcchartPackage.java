/**
 */
package vcchart;

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
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see vcchart.VcchartFactory
 * @model kind="package"
 * @generated
 */
public interface VcchartPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "vcchart";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "vc";

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
	VcchartPackage eINSTANCE = vcchart.impl.VcchartPackageImpl.init();

	/**
	 * The meta object id for the '{@link vcchart.impl.ModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see vcchart.impl.ModelImpl
	 * @see vcchart.impl.VcchartPackageImpl#getModel()
	 * @generated
	 */
	int MODEL = 0;

	/**
	 * The feature id for the '<em><b>Activitys1</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__ACTIVITYS1 = 0;

	/**
	 * The feature id for the '<em><b>Activitys2</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__ACTIVITYS2 = 1;

	/**
	 * The feature id for the '<em><b>Clusters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__CLUSTERS = 2;

	/**
	 * The feature id for the '<em><b>Products</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__PRODUCTS = 3;

	/**
	 * The feature id for the '<em><b>Technical Terms</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__TECHNICAL_TERMS = 4;

	/**
	 * The feature id for the '<em><b>Objectives</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__OBJECTIVES = 5;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__PARTICIPANTS = 6;

	/**
	 * The feature id for the '<em><b>Applications</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__APPLICATIONS = 7;

	/**
	 * The feature id for the '<em><b>Documents</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__DOCUMENTS = 8;

	/**
	 * The feature id for the '<em><b>Relations1</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__RELATIONS1 = 9;

	/**
	 * The feature id for the '<em><b>Relations2</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__RELATIONS2 = 10;

	/**
	 * The feature id for the '<em><b>Relations3</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__RELATIONS3 = 11;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_FEATURE_COUNT = 12;

	/**
	 * The number of operations of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link vcchart.impl.NamedElementImpl <em>Named Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see vcchart.impl.NamedElementImpl
	 * @see vcchart.impl.VcchartPackageImpl#getNamedElement()
	 * @generated
	 */
	int NAMED_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__NAME = 0;

	/**
	 * The number of structural features of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link vcchart.impl.AllImpl <em>All</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see vcchart.impl.AllImpl
	 * @see vcchart.impl.VcchartPackageImpl#getAll()
	 * @generated
	 */
	int ALL = 2;

	/**
	 * The number of structural features of the '<em>All</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>All</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link vcchart.impl.All_Rel_1Impl <em>All Rel 1</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see vcchart.impl.All_Rel_1Impl
	 * @see vcchart.impl.VcchartPackageImpl#getAll_Rel_1()
	 * @generated
	 */
	int ALL_REL_1 = 3;

	/**
	 * The number of structural features of the '<em>All Rel 1</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_REL_1_FEATURE_COUNT = ALL_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>All Rel 1</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_REL_1_OPERATION_COUNT = ALL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link vcchart.impl.All_Rel_1_EndPointImpl <em>All Rel 1End Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see vcchart.impl.All_Rel_1_EndPointImpl
	 * @see vcchart.impl.VcchartPackageImpl#getAll_Rel_1_EndPoint()
	 * @generated
	 */
	int ALL_REL_1END_POINT = 4;

	/**
	 * The number of structural features of the '<em>All Rel 1End Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_REL_1END_POINT_FEATURE_COUNT = ALL_REL_1_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>All Rel 1End Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_REL_1END_POINT_OPERATION_COUNT = ALL_REL_1_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link vcchart.impl.All_Rel_3Impl <em>All Rel 3</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see vcchart.impl.All_Rel_3Impl
	 * @see vcchart.impl.VcchartPackageImpl#getAll_Rel_3()
	 * @generated
	 */
	int ALL_REL_3 = 5;

	/**
	 * The number of structural features of the '<em>All Rel 3</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_REL_3_FEATURE_COUNT = ALL_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>All Rel 3</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_REL_3_OPERATION_COUNT = ALL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link vcchart.impl.RelationsObjectImpl <em>Relations Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see vcchart.impl.RelationsObjectImpl
	 * @see vcchart.impl.VcchartPackageImpl#getRelationsObject()
	 * @generated
	 */
	int RELATIONS_OBJECT = 6;

	/**
	 * The number of structural features of the '<em>Relations Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONS_OBJECT_FEATURE_COUNT = ALL_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Relations Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONS_OBJECT_OPERATION_COUNT = ALL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link vcchart.impl.Activity1Impl <em>Activity1</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see vcchart.impl.Activity1Impl
	 * @see vcchart.impl.VcchartPackageImpl#getActivity1()
	 * @generated
	 */
	int ACTIVITY1 = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY1__NAME = NAMED_ELEMENT__NAME;

	/**
	 * @generated NOT
	 */
	int ACTIVITY1__SUBDIAGRAM = NAMED_ELEMENT_FEATURE_COUNT + 0;
	
	/**
	 * The number of structural features of the '<em>Activity1</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY1_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Activity1</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY1_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;
	
	/**
	 * The meta object id for the '{@link vcchart.impl.Activity2Impl <em>Activity2</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see vcchart.impl.Activity2Impl
	 * @see vcchart.impl.VcchartPackageImpl#getActivity2()
	 * @generated
	 */
	int ACTIVITY2 = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY2__NAME = NAMED_ELEMENT__NAME;

	/**
	 * @generated NOT
	 */
	int ACTIVITY2__SUBDIAGRAM = NAMED_ELEMENT_FEATURE_COUNT + 0;
	
	/**
	 * The number of structural features of the '<em>Activity2</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY2_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Activity2</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY2_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;	

	/**
	 * The meta object id for the '{@link vcchart.impl.ProductImpl <em>Product</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see vcchart.impl.ProductImpl
	 * @see vcchart.impl.VcchartPackageImpl#getProduct()
	 * @generated
	 */
	int PRODUCT = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__NAME = NAMED_ELEMENT__NAME;
	
	/**
	 * @generated NOT
	 */
	int PRODUCT__SUBDIAGRAM = NAMED_ELEMENT_FEATURE_COUNT + 0;
	
	/**
	 * The number of structural features of the '<em>Product</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Product</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link vcchart.impl.ObjectiveImpl <em>Objective</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see vcchart.impl.ObjectiveImpl
	 * @see vcchart.impl.VcchartPackageImpl#getObjective()
	 * @generated
	 */
	int OBJECTIVE = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTIVE__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The number of structural features of the '<em>Objective</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTIVE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Objective</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECTIVE_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link vcchart.impl.ClusterImpl <em>Cluster</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see vcchart.impl.ClusterImpl
	 * @see vcchart.impl.VcchartPackageImpl#getCluster()
	 * @generated
	 */
	int CLUSTER = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTER__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The number of structural features of the '<em>Cluster</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTER_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Cluster</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTER_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link vcchart.impl.TechnicalTermImpl <em>Technical Term</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see vcchart.impl.TechnicalTermImpl
	 * @see vcchart.impl.VcchartPackageImpl#getTechnicalTerm()
	 * @generated
	 */
	int TECHNICAL_TERM = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_TERM__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The number of structural features of the '<em>Technical Term</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_TERM_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Technical Term</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TECHNICAL_TERM_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link vcchart.impl.ParticipantImpl <em>Participant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see vcchart.impl.ParticipantImpl
	 * @see vcchart.impl.VcchartPackageImpl#getParticipant()
	 * @generated
	 */
	int PARTICIPANT = 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The number of structural features of the '<em>Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link vcchart.impl.ApplicationImpl <em>Application</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see vcchart.impl.ApplicationImpl
	 * @see vcchart.impl.VcchartPackageImpl#getApplication()
	 * @generated
	 */
	int APPLICATION = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The number of structural features of the '<em>Application</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Application</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link vcchart.impl.DocumentImpl <em>Document</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see vcchart.impl.DocumentImpl
	 * @see vcchart.impl.VcchartPackageImpl#getDocument()
	 * @generated
	 */
	int DOCUMENT = 15;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The number of structural features of the '<em>Document</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Document</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link vcchart.impl.Relation1Impl <em>Relation1</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see vcchart.impl.Relation1Impl
	 * @see vcchart.impl.VcchartPackageImpl#getRelation1()
	 * @generated
	 */
	int RELATION1 = 16;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION1__SOURCE = 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION1__TARGET = 1;

	/**
	 * The number of structural features of the '<em>Relation1</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION1_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Relation1</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION1_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link vcchart.impl.Relation2Impl <em>Relation2</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see vcchart.impl.Relation2Impl
	 * @see vcchart.impl.VcchartPackageImpl#getRelation2()
	 * @generated
	 */
	int RELATION2 = 17;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION2__SOURCE = 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION2__TARGET = 1;

	/**
	 * The number of structural features of the '<em>Relation2</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION2_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Relation2</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION2_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link vcchart.impl.Relation3Impl <em>Relation3</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see vcchart.impl.Relation3Impl
	 * @see vcchart.impl.VcchartPackageImpl#getRelation3()
	 * @generated
	 */
	int RELATION3 = 18;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION3__SOURCE = 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION3__TARGET = 1;

	/**
	 * The number of structural features of the '<em>Relation3</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION3_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Relation3</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION3_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link vcchart.Model <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see vcchart.Model
	 * @generated
	 */
	EClass getModel();

	/**
	 * Returns the meta object for the containment reference list '{@link vcchart.Model#getActivitys1 <em>Activitys1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Activitys1</em>'.
	 * @see vcchart.Model#getActivitys1()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Activitys1();

	/**
	 * Returns the meta object for the containment reference list '{@link vcchart.Model#getActivitys2 <em>Activitys2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Activitys2</em>'.
	 * @see vcchart.Model#getActivitys2()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Activitys2();

	/**
	 * Returns the meta object for the containment reference list '{@link vcchart.Model#getClusters <em>Clusters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Clusters</em>'.
	 * @see vcchart.Model#getClusters()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Clusters();

	/**
	 * Returns the meta object for the containment reference list '{@link vcchart.Model#getProducts <em>Products</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Products</em>'.
	 * @see vcchart.Model#getProducts()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Products();

	/**
	 * Returns the meta object for the containment reference list '{@link vcchart.Model#getTechnicalTerms <em>Technical Terms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Technical Terms</em>'.
	 * @see vcchart.Model#getTechnicalTerms()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_TechnicalTerms();

	/**
	 * Returns the meta object for the containment reference list '{@link vcchart.Model#getObjectives <em>Objectives</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Objectives</em>'.
	 * @see vcchart.Model#getObjectives()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Objectives();

	/**
	 * Returns the meta object for the containment reference list '{@link vcchart.Model#getParticipants <em>Participants</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Participants</em>'.
	 * @see vcchart.Model#getParticipants()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Participants();

	/**
	 * Returns the meta object for the containment reference list '{@link vcchart.Model#getApplications <em>Applications</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Applications</em>'.
	 * @see vcchart.Model#getApplications()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Applications();

	/**
	 * Returns the meta object for the containment reference list '{@link vcchart.Model#getDocuments <em>Documents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Documents</em>'.
	 * @see vcchart.Model#getDocuments()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Documents();

	/**
	 * Returns the meta object for the containment reference list '{@link vcchart.Model#getRelations1 <em>Relations1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Relations1</em>'.
	 * @see vcchart.Model#getRelations1()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Relations1();

	/**
	 * Returns the meta object for the containment reference list '{@link vcchart.Model#getRelations2 <em>Relations2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Relations2</em>'.
	 * @see vcchart.Model#getRelations2()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Relations2();

	/**
	 * Returns the meta object for the containment reference list '{@link vcchart.Model#getRelations3 <em>Relations3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Relations3</em>'.
	 * @see vcchart.Model#getRelations3()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Relations3();

	/**
	 * Returns the meta object for class '{@link vcchart.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Element</em>'.
	 * @see vcchart.NamedElement
	 * @generated
	 */
	EClass getNamedElement();

	/**
	 * Returns the meta object for the attribute '{@link vcchart.NamedElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see vcchart.NamedElement#getName()
	 * @see #getNamedElement()
	 * @generated
	 */
	EAttribute getNamedElement_Name();

	/**
	 * Returns the meta object for class '{@link vcchart.All <em>All</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>All</em>'.
	 * @see vcchart.All
	 * @generated
	 */
	EClass getAll();

	/**
	 * Returns the meta object for class '{@link vcchart.All_Rel_1 <em>All Rel 1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>All Rel 1</em>'.
	 * @see vcchart.All_Rel_1
	 * @generated
	 */
	EClass getAll_Rel_1();

	/**
	 * Returns the meta object for class '{@link vcchart.All_Rel_1_EndPoint <em>All Rel 1End Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>All Rel 1End Point</em>'.
	 * @see vcchart.All_Rel_1_EndPoint
	 * @generated
	 */
	EClass getAll_Rel_1_EndPoint();

	/**
	 * Returns the meta object for class '{@link vcchart.All_Rel_3 <em>All Rel 3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>All Rel 3</em>'.
	 * @see vcchart.All_Rel_3
	 * @generated
	 */
	EClass getAll_Rel_3();

	/**
	 * Returns the meta object for class '{@link vcchart.RelationsObject <em>Relations Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relations Object</em>'.
	 * @see vcchart.RelationsObject
	 * @generated
	 */
	EClass getRelationsObject();

	/**
	 * Returns the meta object for class '{@link vcchart.Activity1 <em>Activity1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity1</em>'.
	 * @see vcchart.Activity1
	 * @generated
	 */
	EClass getActivity1();
	
	/**
	 * @generated NOT
	 */
	EAttribute getActivity1_Subdiagram();

	/**
	 * Returns the meta object for class '{@link vcchart.Activity2 <em>Activity2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity2</em>'.
	 * @see vcchart.Activity2
	 * @generated
	 */
	EClass getActivity2();
	
	/**
	 * @generated NOT
	 */
	EAttribute getActivity2_Subdiagram();

	/**
	 * Returns the meta object for class '{@link vcchart.Product <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product</em>'.
	 * @see vcchart.Product
	 * @generated
	 */
	EClass getProduct();
	
	/**
	 * @generated NOT
	 */
	EAttribute getProduct_Subdiagram();

	/**
	 * Returns the meta object for class '{@link vcchart.Objective <em>Objective</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Objective</em>'.
	 * @see vcchart.Objective
	 * @generated
	 */
	EClass getObjective();

	/**
	 * Returns the meta object for class '{@link vcchart.Cluster <em>Cluster</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cluster</em>'.
	 * @see vcchart.Cluster
	 * @generated
	 */
	EClass getCluster();

	/**
	 * Returns the meta object for class '{@link vcchart.TechnicalTerm <em>Technical Term</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Technical Term</em>'.
	 * @see vcchart.TechnicalTerm
	 * @generated
	 */
	EClass getTechnicalTerm();

	/**
	 * Returns the meta object for class '{@link vcchart.Participant <em>Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Participant</em>'.
	 * @see vcchart.Participant
	 * @generated
	 */
	EClass getParticipant();

	/**
	 * Returns the meta object for class '{@link vcchart.Application <em>Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Application</em>'.
	 * @see vcchart.Application
	 * @generated
	 */
	EClass getApplication();

	/**
	 * Returns the meta object for class '{@link vcchart.Document <em>Document</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document</em>'.
	 * @see vcchart.Document
	 * @generated
	 */
	EClass getDocument();

	/**
	 * Returns the meta object for class '{@link vcchart.Relation1 <em>Relation1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relation1</em>'.
	 * @see vcchart.Relation1
	 * @generated
	 */
	EClass getRelation1();

	/**
	 * Returns the meta object for the reference '{@link vcchart.Relation1#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see vcchart.Relation1#getSource()
	 * @see #getRelation1()
	 * @generated
	 */
	EReference getRelation1_Source();

	/**
	 * Returns the meta object for the reference '{@link vcchart.Relation1#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see vcchart.Relation1#getTarget()
	 * @see #getRelation1()
	 * @generated
	 */
	EReference getRelation1_Target();

	/**
	 * Returns the meta object for class '{@link vcchart.Relation2 <em>Relation2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relation2</em>'.
	 * @see vcchart.Relation2
	 * @generated
	 */
	EClass getRelation2();

	/**
	 * Returns the meta object for the reference '{@link vcchart.Relation2#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see vcchart.Relation2#getSource()
	 * @see #getRelation2()
	 * @generated
	 */
	EReference getRelation2_Source();

	/**
	 * Returns the meta object for the reference '{@link vcchart.Relation2#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see vcchart.Relation2#getTarget()
	 * @see #getRelation2()
	 * @generated
	 */
	EReference getRelation2_Target();

	/**
	 * Returns the meta object for class '{@link vcchart.Relation3 <em>Relation3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relation3</em>'.
	 * @see vcchart.Relation3
	 * @generated
	 */
	EClass getRelation3();

	/**
	 * Returns the meta object for the reference '{@link vcchart.Relation3#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see vcchart.Relation3#getSource()
	 * @see #getRelation3()
	 * @generated
	 */
	EReference getRelation3_Source();

	/**
	 * Returns the meta object for the reference '{@link vcchart.Relation3#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see vcchart.Relation3#getTarget()
	 * @see #getRelation3()
	 * @generated
	 */
	EReference getRelation3_Target();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	VcchartFactory getVcchartFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link vcchart.impl.ModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see vcchart.impl.ModelImpl
		 * @see vcchart.impl.VcchartPackageImpl#getModel()
		 * @generated
		 */
		EClass MODEL = eINSTANCE.getModel();

		/**
		 * The meta object literal for the '<em><b>Activitys1</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__ACTIVITYS1 = eINSTANCE.getModel_Activitys1();

		/**
		 * The meta object literal for the '<em><b>Activitys2</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__ACTIVITYS2 = eINSTANCE.getModel_Activitys2();

		/**
		 * The meta object literal for the '<em><b>Clusters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__CLUSTERS = eINSTANCE.getModel_Clusters();

		/**
		 * The meta object literal for the '<em><b>Products</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__PRODUCTS = eINSTANCE.getModel_Products();

		/**
		 * The meta object literal for the '<em><b>Technical Terms</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__TECHNICAL_TERMS = eINSTANCE.getModel_TechnicalTerms();

		/**
		 * The meta object literal for the '<em><b>Objectives</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__OBJECTIVES = eINSTANCE.getModel_Objectives();

		/**
		 * The meta object literal for the '<em><b>Participants</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__PARTICIPANTS = eINSTANCE.getModel_Participants();

		/**
		 * The meta object literal for the '<em><b>Applications</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__APPLICATIONS = eINSTANCE.getModel_Applications();

		/**
		 * The meta object literal for the '<em><b>Documents</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__DOCUMENTS = eINSTANCE.getModel_Documents();

		/**
		 * The meta object literal for the '<em><b>Relations1</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__RELATIONS1 = eINSTANCE.getModel_Relations1();

		/**
		 * The meta object literal for the '<em><b>Relations2</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__RELATIONS2 = eINSTANCE.getModel_Relations2();

		/**
		 * The meta object literal for the '<em><b>Relations3</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__RELATIONS3 = eINSTANCE.getModel_Relations3();

		/**
		 * The meta object literal for the '{@link vcchart.impl.NamedElementImpl <em>Named Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see vcchart.impl.NamedElementImpl
		 * @see vcchart.impl.VcchartPackageImpl#getNamedElement()
		 * @generated
		 */
		EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ELEMENT__NAME = eINSTANCE.getNamedElement_Name();

		/**
		 * The meta object literal for the '{@link vcchart.impl.AllImpl <em>All</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see vcchart.impl.AllImpl
		 * @see vcchart.impl.VcchartPackageImpl#getAll()
		 * @generated
		 */
		EClass ALL = eINSTANCE.getAll();

		/**
		 * The meta object literal for the '{@link vcchart.impl.All_Rel_1Impl <em>All Rel 1</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see vcchart.impl.All_Rel_1Impl
		 * @see vcchart.impl.VcchartPackageImpl#getAll_Rel_1()
		 * @generated
		 */
		EClass ALL_REL_1 = eINSTANCE.getAll_Rel_1();

		/**
		 * The meta object literal for the '{@link vcchart.impl.All_Rel_1_EndPointImpl <em>All Rel 1End Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see vcchart.impl.All_Rel_1_EndPointImpl
		 * @see vcchart.impl.VcchartPackageImpl#getAll_Rel_1_EndPoint()
		 * @generated
		 */
		EClass ALL_REL_1END_POINT = eINSTANCE.getAll_Rel_1_EndPoint();

		/**
		 * The meta object literal for the '{@link vcchart.impl.All_Rel_3Impl <em>All Rel 3</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see vcchart.impl.All_Rel_3Impl
		 * @see vcchart.impl.VcchartPackageImpl#getAll_Rel_3()
		 * @generated
		 */
		EClass ALL_REL_3 = eINSTANCE.getAll_Rel_3();

		/**
		 * The meta object literal for the '{@link vcchart.impl.RelationsObjectImpl <em>Relations Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see vcchart.impl.RelationsObjectImpl
		 * @see vcchart.impl.VcchartPackageImpl#getRelationsObject()
		 * @generated
		 */
		EClass RELATIONS_OBJECT = eINSTANCE.getRelationsObject();

		/**
		 * The meta object literal for the '{@link vcchart.impl.Activity1Impl <em>Activity1</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see vcchart.impl.Activity1Impl
		 * @see vcchart.impl.VcchartPackageImpl#getActivity1()
		 * @generated
		 */
		EClass ACTIVITY1 = eINSTANCE.getActivity1();
		
		/**
		 * @generated NOT
		 */
		EAttribute ACTIVITY1__SUBDIAGRAM = eINSTANCE.getActivity1_Subdiagram();

		/**
		 * The meta object literal for the '{@link vcchart.impl.Activity2Impl <em>Activity2</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see vcchart.impl.Activity2Impl
		 * @see vcchart.impl.VcchartPackageImpl#getActivity2()
		 * @generated
		 */
		EClass ACTIVITY2 = eINSTANCE.getActivity2();
		
		/**
		 * @generated NOT
		 */
		EAttribute ACTIVITY2__SUBDIAGRAM = eINSTANCE.getActivity2_Subdiagram();

		/**
		 * The meta object literal for the '{@link vcchart.impl.ProductImpl <em>Product</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see vcchart.impl.ProductImpl
		 * @see vcchart.impl.VcchartPackageImpl#getProduct()
		 * @generated
		 */
		EClass PRODUCT = eINSTANCE.getProduct();
		
		/**
		 * @generated NOT
		 */
		EAttribute PRODUCT__SUBDIAGRAM = eINSTANCE.getProduct_Subdiagram();

		/**
		 * The meta object literal for the '{@link vcchart.impl.ObjectiveImpl <em>Objective</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see vcchart.impl.ObjectiveImpl
		 * @see vcchart.impl.VcchartPackageImpl#getObjective()
		 * @generated
		 */
		EClass OBJECTIVE = eINSTANCE.getObjective();

		/**
		 * The meta object literal for the '{@link vcchart.impl.ClusterImpl <em>Cluster</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see vcchart.impl.ClusterImpl
		 * @see vcchart.impl.VcchartPackageImpl#getCluster()
		 * @generated
		 */
		EClass CLUSTER = eINSTANCE.getCluster();

		/**
		 * The meta object literal for the '{@link vcchart.impl.TechnicalTermImpl <em>Technical Term</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see vcchart.impl.TechnicalTermImpl
		 * @see vcchart.impl.VcchartPackageImpl#getTechnicalTerm()
		 * @generated
		 */
		EClass TECHNICAL_TERM = eINSTANCE.getTechnicalTerm();

		/**
		 * The meta object literal for the '{@link vcchart.impl.ParticipantImpl <em>Participant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see vcchart.impl.ParticipantImpl
		 * @see vcchart.impl.VcchartPackageImpl#getParticipant()
		 * @generated
		 */
		EClass PARTICIPANT = eINSTANCE.getParticipant();

		/**
		 * The meta object literal for the '{@link vcchart.impl.ApplicationImpl <em>Application</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see vcchart.impl.ApplicationImpl
		 * @see vcchart.impl.VcchartPackageImpl#getApplication()
		 * @generated
		 */
		EClass APPLICATION = eINSTANCE.getApplication();

		/**
		 * The meta object literal for the '{@link vcchart.impl.DocumentImpl <em>Document</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see vcchart.impl.DocumentImpl
		 * @see vcchart.impl.VcchartPackageImpl#getDocument()
		 * @generated
		 */
		EClass DOCUMENT = eINSTANCE.getDocument();

		/**
		 * The meta object literal for the '{@link vcchart.impl.Relation1Impl <em>Relation1</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see vcchart.impl.Relation1Impl
		 * @see vcchart.impl.VcchartPackageImpl#getRelation1()
		 * @generated
		 */
		EClass RELATION1 = eINSTANCE.getRelation1();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATION1__SOURCE = eINSTANCE.getRelation1_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATION1__TARGET = eINSTANCE.getRelation1_Target();

		/**
		 * The meta object literal for the '{@link vcchart.impl.Relation2Impl <em>Relation2</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see vcchart.impl.Relation2Impl
		 * @see vcchart.impl.VcchartPackageImpl#getRelation2()
		 * @generated
		 */
		EClass RELATION2 = eINSTANCE.getRelation2();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATION2__SOURCE = eINSTANCE.getRelation2_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATION2__TARGET = eINSTANCE.getRelation2_Target();

		/**
		 * The meta object literal for the '{@link vcchart.impl.Relation3Impl <em>Relation3</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see vcchart.impl.Relation3Impl
		 * @see vcchart.impl.VcchartPackageImpl#getRelation3()
		 * @generated
		 */
		EClass RELATION3 = eINSTANCE.getRelation3();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATION3__SOURCE = eINSTANCE.getRelation3_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATION3__TARGET = eINSTANCE.getRelation3_Target();

	}

} //VcchartPackage

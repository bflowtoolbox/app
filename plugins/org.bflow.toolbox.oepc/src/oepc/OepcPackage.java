/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package oepc;

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
 * @see oepc.OepcFactory
 * @model kind="package"
 * @generated
 */
public interface OepcPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "oepc";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "org.bflow.toolbox.oepc";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "oepc";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OepcPackage eINSTANCE = oepc.impl.OepcPackageImpl.init();

	/**
	 * The meta object id for the '{@link oepc.impl.OEPCImpl <em>OEPC</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oepc.impl.OEPCImpl
	 * @see oepc.impl.OepcPackageImpl#getOEPC()
	 * @generated
	 */
	int OEPC = 0;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OEPC__ELEMENTS = 0;

	/**
	 * The feature id for the '<em><b>Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OEPC__CONNECTIONS = 1;

	/**
	 * The number of structural features of the '<em>OEPC</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OEPC_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link oepc.impl.EventImpl <em>Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oepc.impl.EventImpl
	 * @see oepc.impl.OepcPackageImpl#getEvent()
	 * @generated
	 */
	int EVENT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link oepc.impl.BusinessObjectImpl <em>Business Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oepc.impl.BusinessObjectImpl
	 * @see oepc.impl.OepcPackageImpl#getBusinessObject()
	 * @generated
	 */
	int BUSINESS_OBJECT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_OBJECT__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_OBJECT__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_OBJECT__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_OBJECT__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_OBJECT__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_OBJECT__ATTRIBUTES = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Methods</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_OBJECT__METHODS = BflowPackage.ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Business Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_OBJECT_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link oepc.impl.ITSystemImpl <em>IT System</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oepc.impl.ITSystemImpl
	 * @see oepc.impl.OepcPackageImpl#getITSystem()
	 * @generated
	 */
	int IT_SYSTEM = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IT_SYSTEM__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IT_SYSTEM__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IT_SYSTEM__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IT_SYSTEM__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IT_SYSTEM__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>IT System</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IT_SYSTEM_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link oepc.impl.OrganisationUnitImpl <em>Organisation Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oepc.impl.OrganisationUnitImpl
	 * @see oepc.impl.OepcPackageImpl#getOrganisationUnit()
	 * @generated
	 */
	int ORGANISATION_UNIT = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANISATION_UNIT__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANISATION_UNIT__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANISATION_UNIT__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANISATION_UNIT__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANISATION_UNIT__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>Organisation Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORGANISATION_UNIT_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link oepc.impl.XORConnectorImpl <em>XOR Connector</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oepc.impl.XORConnectorImpl
	 * @see oepc.impl.OepcPackageImpl#getXORConnector()
	 * @generated
	 */
	int XOR_CONNECTOR = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XOR_CONNECTOR__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XOR_CONNECTOR__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XOR_CONNECTOR__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XOR_CONNECTOR__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XOR_CONNECTOR__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>XOR Connector</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XOR_CONNECTOR_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link oepc.impl.ANDConnectorImpl <em>AND Connector</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oepc.impl.ANDConnectorImpl
	 * @see oepc.impl.OepcPackageImpl#getANDConnector()
	 * @generated
	 */
	int AND_CONNECTOR = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_CONNECTOR__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_CONNECTOR__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_CONNECTOR__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_CONNECTOR__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_CONNECTOR__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>AND Connector</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_CONNECTOR_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link oepc.impl.ORConnectorImpl <em>OR Connector</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oepc.impl.ORConnectorImpl
	 * @see oepc.impl.OepcPackageImpl#getORConnector()
	 * @generated
	 */
	int OR_CONNECTOR = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_CONNECTOR__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_CONNECTOR__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_CONNECTOR__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_CONNECTOR__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_CONNECTOR__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>OR Connector</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_CONNECTOR_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link oepc.impl.ControlFlowEdgeImpl <em>Control Flow Edge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oepc.impl.ControlFlowEdgeImpl
	 * @see oepc.impl.OepcPackageImpl#getControlFlowEdge()
	 * @generated
	 */
	int CONTROL_FLOW_EDGE = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_FLOW_EDGE__NAME = BflowPackage.CONNECTION__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_FLOW_EDGE__ID = BflowPackage.CONNECTION__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_FLOW_EDGE__DESCRIPTION = BflowPackage.CONNECTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_FLOW_EDGE__FROM = BflowPackage.CONNECTION__FROM;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_FLOW_EDGE__TO = BflowPackage.CONNECTION__TO;

	/**
	 * The number of structural features of the '<em>Control Flow Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_FLOW_EDGE_FEATURE_COUNT = BflowPackage.CONNECTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link oepc.impl.InformationEdgeImpl <em>Information Edge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oepc.impl.InformationEdgeImpl
	 * @see oepc.impl.OepcPackageImpl#getInformationEdge()
	 * @generated
	 */
	int INFORMATION_EDGE = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION_EDGE__NAME = BflowPackage.CONNECTION__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION_EDGE__ID = BflowPackage.CONNECTION__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION_EDGE__DESCRIPTION = BflowPackage.CONNECTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION_EDGE__FROM = BflowPackage.CONNECTION__FROM;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION_EDGE__TO = BflowPackage.CONNECTION__TO;

	/**
	 * The number of structural features of the '<em>Information Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFORMATION_EDGE_FEATURE_COUNT = BflowPackage.CONNECTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link oepc.impl.BusinessObjectElementImpl <em>Business Object Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oepc.impl.BusinessObjectElementImpl
	 * @see oepc.impl.OepcPackageImpl#getBusinessObjectElement()
	 * @generated
	 */
	int BUSINESS_OBJECT_ELEMENT = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_OBJECT_ELEMENT__NAME = 0;

	/**
	 * The number of structural features of the '<em>Business Object Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_OBJECT_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link oepc.impl.BusinessAttributeImpl <em>Business Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oepc.impl.BusinessAttributeImpl
	 * @see oepc.impl.OepcPackageImpl#getBusinessAttribute()
	 * @generated
	 */
	int BUSINESS_ATTRIBUTE = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_ATTRIBUTE__NAME = BUSINESS_OBJECT_ELEMENT__NAME;

	/**
	 * The number of structural features of the '<em>Business Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_ATTRIBUTE_FEATURE_COUNT = BUSINESS_OBJECT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link oepc.impl.BusinessMethodImpl <em>Business Method</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oepc.impl.BusinessMethodImpl
	 * @see oepc.impl.OepcPackageImpl#getBusinessMethod()
	 * @generated
	 */
	int BUSINESS_METHOD = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_METHOD__NAME = BUSINESS_OBJECT_ELEMENT__NAME;

	/**
	 * The number of structural features of the '<em>Business Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUSINESS_METHOD_FEATURE_COUNT = BUSINESS_OBJECT_ELEMENT_FEATURE_COUNT + 0;


	/**
	 * The meta object id for the '{@link oepc.impl.DocumentImpl <em>Document</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see oepc.impl.DocumentImpl
	 * @see oepc.impl.OepcPackageImpl#getDocument()
	 * @generated
	 */
	int DOCUMENT = 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__NAME = BflowPackage.ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__ID = BflowPackage.ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__DESCRIPTION = BflowPackage.ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__IN = BflowPackage.ELEMENT__IN;

	/**
	 * The feature id for the '<em><b>Out</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__OUT = BflowPackage.ELEMENT__OUT;

	/**
	 * The number of structural features of the '<em>Document</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_FEATURE_COUNT = BflowPackage.ELEMENT_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link oepc.OEPC <em>OEPC</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>OEPC</em>'.
	 * @see oepc.OEPC
	 * @generated
	 */
	EClass getOEPC();

	/**
	 * Returns the meta object for the containment reference list '{@link oepc.OEPC#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see oepc.OEPC#getElements()
	 * @see #getOEPC()
	 * @generated
	 */
	EReference getOEPC_Elements();

	/**
	 * Returns the meta object for the containment reference list '{@link oepc.OEPC#getConnections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connections</em>'.
	 * @see oepc.OEPC#getConnections()
	 * @see #getOEPC()
	 * @generated
	 */
	EReference getOEPC_Connections();

	/**
	 * Returns the meta object for class '{@link oepc.Event <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event</em>'.
	 * @see oepc.Event
	 * @generated
	 */
	EClass getEvent();

	/**
	 * Returns the meta object for class '{@link oepc.BusinessObject <em>Business Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Business Object</em>'.
	 * @see oepc.BusinessObject
	 * @generated
	 */
	EClass getBusinessObject();

	/**
	 * Returns the meta object for the containment reference list '{@link oepc.BusinessObject#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see oepc.BusinessObject#getAttributes()
	 * @see #getBusinessObject()
	 * @generated
	 */
	EReference getBusinessObject_Attributes();

	/**
	 * Returns the meta object for the containment reference list '{@link oepc.BusinessObject#getMethods <em>Methods</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Methods</em>'.
	 * @see oepc.BusinessObject#getMethods()
	 * @see #getBusinessObject()
	 * @generated
	 */
	EReference getBusinessObject_Methods();

	/**
	 * Returns the meta object for class '{@link oepc.ITSystem <em>IT System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IT System</em>'.
	 * @see oepc.ITSystem
	 * @generated
	 */
	EClass getITSystem();

	/**
	 * Returns the meta object for class '{@link oepc.OrganisationUnit <em>Organisation Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Organisation Unit</em>'.
	 * @see oepc.OrganisationUnit
	 * @generated
	 */
	EClass getOrganisationUnit();

	/**
	 * Returns the meta object for class '{@link oepc.XORConnector <em>XOR Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>XOR Connector</em>'.
	 * @see oepc.XORConnector
	 * @generated
	 */
	EClass getXORConnector();

	/**
	 * Returns the meta object for class '{@link oepc.ANDConnector <em>AND Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AND Connector</em>'.
	 * @see oepc.ANDConnector
	 * @generated
	 */
	EClass getANDConnector();

	/**
	 * Returns the meta object for class '{@link oepc.ORConnector <em>OR Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>OR Connector</em>'.
	 * @see oepc.ORConnector
	 * @generated
	 */
	EClass getORConnector();

	/**
	 * Returns the meta object for class '{@link oepc.ControlFlowEdge <em>Control Flow Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Control Flow Edge</em>'.
	 * @see oepc.ControlFlowEdge
	 * @generated
	 */
	EClass getControlFlowEdge();

	/**
	 * Returns the meta object for class '{@link oepc.InformationEdge <em>Information Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Information Edge</em>'.
	 * @see oepc.InformationEdge
	 * @generated
	 */
	EClass getInformationEdge();

	/**
	 * Returns the meta object for class '{@link oepc.BusinessObjectElement <em>Business Object Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Business Object Element</em>'.
	 * @see oepc.BusinessObjectElement
	 * @generated
	 */
	EClass getBusinessObjectElement();

	/**
	 * Returns the meta object for the attribute '{@link oepc.BusinessObjectElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see oepc.BusinessObjectElement#getName()
	 * @see #getBusinessObjectElement()
	 * @generated
	 */
	EAttribute getBusinessObjectElement_Name();

	/**
	 * Returns the meta object for class '{@link oepc.BusinessAttribute <em>Business Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Business Attribute</em>'.
	 * @see oepc.BusinessAttribute
	 * @generated
	 */
	EClass getBusinessAttribute();

	/**
	 * Returns the meta object for class '{@link oepc.BusinessMethod <em>Business Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Business Method</em>'.
	 * @see oepc.BusinessMethod
	 * @generated
	 */
	EClass getBusinessMethod();

	/**
	 * Returns the meta object for class '{@link oepc.Document <em>Document</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document</em>'.
	 * @see oepc.Document
	 * @generated
	 */
	EClass getDocument();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	OepcFactory getOepcFactory();

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
		 * The meta object literal for the '{@link oepc.impl.OEPCImpl <em>OEPC</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oepc.impl.OEPCImpl
		 * @see oepc.impl.OepcPackageImpl#getOEPC()
		 * @generated
		 */
		EClass OEPC = eINSTANCE.getOEPC();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OEPC__ELEMENTS = eINSTANCE.getOEPC_Elements();

		/**
		 * The meta object literal for the '<em><b>Connections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OEPC__CONNECTIONS = eINSTANCE.getOEPC_Connections();

		/**
		 * The meta object literal for the '{@link oepc.impl.EventImpl <em>Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oepc.impl.EventImpl
		 * @see oepc.impl.OepcPackageImpl#getEvent()
		 * @generated
		 */
		EClass EVENT = eINSTANCE.getEvent();

		/**
		 * The meta object literal for the '{@link oepc.impl.BusinessObjectImpl <em>Business Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oepc.impl.BusinessObjectImpl
		 * @see oepc.impl.OepcPackageImpl#getBusinessObject()
		 * @generated
		 */
		EClass BUSINESS_OBJECT = eINSTANCE.getBusinessObject();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BUSINESS_OBJECT__ATTRIBUTES = eINSTANCE.getBusinessObject_Attributes();

		/**
		 * The meta object literal for the '<em><b>Methods</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BUSINESS_OBJECT__METHODS = eINSTANCE.getBusinessObject_Methods();

		/**
		 * The meta object literal for the '{@link oepc.impl.ITSystemImpl <em>IT System</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oepc.impl.ITSystemImpl
		 * @see oepc.impl.OepcPackageImpl#getITSystem()
		 * @generated
		 */
		EClass IT_SYSTEM = eINSTANCE.getITSystem();

		/**
		 * The meta object literal for the '{@link oepc.impl.OrganisationUnitImpl <em>Organisation Unit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oepc.impl.OrganisationUnitImpl
		 * @see oepc.impl.OepcPackageImpl#getOrganisationUnit()
		 * @generated
		 */
		EClass ORGANISATION_UNIT = eINSTANCE.getOrganisationUnit();

		/**
		 * The meta object literal for the '{@link oepc.impl.XORConnectorImpl <em>XOR Connector</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oepc.impl.XORConnectorImpl
		 * @see oepc.impl.OepcPackageImpl#getXORConnector()
		 * @generated
		 */
		EClass XOR_CONNECTOR = eINSTANCE.getXORConnector();

		/**
		 * The meta object literal for the '{@link oepc.impl.ANDConnectorImpl <em>AND Connector</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oepc.impl.ANDConnectorImpl
		 * @see oepc.impl.OepcPackageImpl#getANDConnector()
		 * @generated
		 */
		EClass AND_CONNECTOR = eINSTANCE.getANDConnector();

		/**
		 * The meta object literal for the '{@link oepc.impl.ORConnectorImpl <em>OR Connector</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oepc.impl.ORConnectorImpl
		 * @see oepc.impl.OepcPackageImpl#getORConnector()
		 * @generated
		 */
		EClass OR_CONNECTOR = eINSTANCE.getORConnector();

		/**
		 * The meta object literal for the '{@link oepc.impl.ControlFlowEdgeImpl <em>Control Flow Edge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oepc.impl.ControlFlowEdgeImpl
		 * @see oepc.impl.OepcPackageImpl#getControlFlowEdge()
		 * @generated
		 */
		EClass CONTROL_FLOW_EDGE = eINSTANCE.getControlFlowEdge();

		/**
		 * The meta object literal for the '{@link oepc.impl.InformationEdgeImpl <em>Information Edge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oepc.impl.InformationEdgeImpl
		 * @see oepc.impl.OepcPackageImpl#getInformationEdge()
		 * @generated
		 */
		EClass INFORMATION_EDGE = eINSTANCE.getInformationEdge();

		/**
		 * The meta object literal for the '{@link oepc.impl.BusinessObjectElementImpl <em>Business Object Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oepc.impl.BusinessObjectElementImpl
		 * @see oepc.impl.OepcPackageImpl#getBusinessObjectElement()
		 * @generated
		 */
		EClass BUSINESS_OBJECT_ELEMENT = eINSTANCE.getBusinessObjectElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUSINESS_OBJECT_ELEMENT__NAME = eINSTANCE.getBusinessObjectElement_Name();

		/**
		 * The meta object literal for the '{@link oepc.impl.BusinessAttributeImpl <em>Business Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oepc.impl.BusinessAttributeImpl
		 * @see oepc.impl.OepcPackageImpl#getBusinessAttribute()
		 * @generated
		 */
		EClass BUSINESS_ATTRIBUTE = eINSTANCE.getBusinessAttribute();

		/**
		 * The meta object literal for the '{@link oepc.impl.BusinessMethodImpl <em>Business Method</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oepc.impl.BusinessMethodImpl
		 * @see oepc.impl.OepcPackageImpl#getBusinessMethod()
		 * @generated
		 */
		EClass BUSINESS_METHOD = eINSTANCE.getBusinessMethod();

		/**
		 * The meta object literal for the '{@link oepc.impl.DocumentImpl <em>Document</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see oepc.impl.DocumentImpl
		 * @see oepc.impl.OepcPackageImpl#getDocument()
		 * @generated
		 */
		EClass DOCUMENT = eINSTANCE.getDocument();

	}

} //OepcPackage

/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package oepc.impl;

import oepc.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OepcFactoryImpl extends EFactoryImpl implements OepcFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OepcFactory init() {
		try {
			OepcFactory theOepcFactory = (OepcFactory)EPackage.Registry.INSTANCE.getEFactory("org.bflow.toolbox.oepc"); 
			if (theOepcFactory != null) {
				return theOepcFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new OepcFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OepcFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case OepcPackage.OEPC: return createOEPC();
			case OepcPackage.EVENT: return createEvent();
			case OepcPackage.BUSINESS_OBJECT: return createBusinessObject();
			case OepcPackage.IT_SYSTEM: return createITSystem();
			case OepcPackage.ORGANISATION_UNIT: return createOrganisationUnit();
			case OepcPackage.XOR_CONNECTOR: return createXORConnector();
			case OepcPackage.AND_CONNECTOR: return createANDConnector();
			case OepcPackage.OR_CONNECTOR: return createORConnector();
			case OepcPackage.CONTROL_FLOW_EDGE: return createControlFlowEdge();
			case OepcPackage.INFORMATION_EDGE: return createInformationEdge();
			case OepcPackage.BUSINESS_ATTRIBUTE: return createBusinessAttribute();
			case OepcPackage.BUSINESS_METHOD: return createBusinessMethod();
			case OepcPackage.DOCUMENT: return createDocument();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OEPC createOEPC() {
		OEPCImpl oepc = new OEPCImpl();
		return oepc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Event createEvent() {
		EventImpl event = new EventImpl();
		return event;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BusinessObject createBusinessObject() {
		BusinessObjectImpl businessObject = new BusinessObjectImpl();
		return businessObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ITSystem createITSystem() {
		ITSystemImpl itSystem = new ITSystemImpl();
		return itSystem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrganisationUnit createOrganisationUnit() {
		OrganisationUnitImpl organisationUnit = new OrganisationUnitImpl();
		return organisationUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XORConnector createXORConnector() {
		XORConnectorImpl xorConnector = new XORConnectorImpl();
		return xorConnector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ANDConnector createANDConnector() {
		ANDConnectorImpl andConnector = new ANDConnectorImpl();
		return andConnector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ORConnector createORConnector() {
		ORConnectorImpl orConnector = new ORConnectorImpl();
		return orConnector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ControlFlowEdge createControlFlowEdge() {
		ControlFlowEdgeImpl controlFlowEdge = new ControlFlowEdgeImpl();
		return controlFlowEdge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InformationEdge createInformationEdge() {
		InformationEdgeImpl informationEdge = new InformationEdgeImpl();
		return informationEdge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BusinessAttribute createBusinessAttribute() {
		BusinessAttributeImpl businessAttribute = new BusinessAttributeImpl();
		return businessAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BusinessMethod createBusinessMethod() {
		BusinessMethodImpl businessMethod = new BusinessMethodImpl();
		return businessMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Document createDocument() {
		DocumentImpl document = new DocumentImpl();
		return document;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OepcPackage getOepcPackage() {
		return (OepcPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static OepcPackage getPackage() {
		return OepcPackage.eINSTANCE;
	}

} //OepcFactoryImpl

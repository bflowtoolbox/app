/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package oepc.util;

import oepc.*;

import org.bflow.toolbox.bflow.BflowSymbol;
import org.bflow.toolbox.bflow.Connection;
import org.bflow.toolbox.bflow.Element;
import org.bflow.toolbox.bflow.IBflowElement;
import org.bflow.toolbox.bflow.IConnector;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see oepc.OepcPackage
 * @generated
 */
public class OepcAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static OepcPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OepcAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = OepcPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OepcSwitch<Adapter> modelSwitch =
		new OepcSwitch<Adapter>() {
			@Override
			public Adapter caseOEPC(OEPC object) {
				return createOEPCAdapter();
			}
			@Override
			public Adapter caseEvent(Event object) {
				return createEventAdapter();
			}
			@Override
			public Adapter caseBusinessObject(BusinessObject object) {
				return createBusinessObjectAdapter();
			}
			@Override
			public Adapter caseITSystem(ITSystem object) {
				return createITSystemAdapter();
			}
			@Override
			public Adapter caseOrganisationUnit(OrganisationUnit object) {
				return createOrganisationUnitAdapter();
			}
			@Override
			public Adapter caseXORConnector(XORConnector object) {
				return createXORConnectorAdapter();
			}
			@Override
			public Adapter caseANDConnector(ANDConnector object) {
				return createANDConnectorAdapter();
			}
			@Override
			public Adapter caseORConnector(ORConnector object) {
				return createORConnectorAdapter();
			}
			@Override
			public Adapter caseControlFlowEdge(ControlFlowEdge object) {
				return createControlFlowEdgeAdapter();
			}
			@Override
			public Adapter caseInformationEdge(InformationEdge object) {
				return createInformationEdgeAdapter();
			}
			@Override
			public Adapter caseBusinessObjectElement(BusinessObjectElement object) {
				return createBusinessObjectElementAdapter();
			}
			@Override
			public Adapter caseBusinessAttribute(BusinessAttribute object) {
				return createBusinessAttributeAdapter();
			}
			@Override
			public Adapter caseBusinessMethod(BusinessMethod object) {
				return createBusinessMethodAdapter();
			}
			@Override
			public Adapter caseDocument(Document object) {
				return createDocumentAdapter();
			}
			@Override
			public Adapter caseBflowSymbol(BflowSymbol object) {
				return createBflowSymbolAdapter();
			}
			@Override
			public Adapter caseElement(Element object) {
				return createElementAdapter();
			}
			@Override
			public Adapter caseIBflowElement(IBflowElement object) {
				return createIBflowElementAdapter();
			}
			@Override
			public Adapter caseIConnector(IConnector object) {
				return createIConnectorAdapter();
			}
			@Override
			public Adapter caseConnection(Connection object) {
				return createConnectionAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link oepc.OEPC <em>OEPC</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see oepc.OEPC
	 * @generated
	 */
	public Adapter createOEPCAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link oepc.Event <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see oepc.Event
	 * @generated
	 */
	public Adapter createEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link oepc.BusinessObject <em>Business Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see oepc.BusinessObject
	 * @generated
	 */
	public Adapter createBusinessObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link oepc.ITSystem <em>IT System</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see oepc.ITSystem
	 * @generated
	 */
	public Adapter createITSystemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link oepc.OrganisationUnit <em>Organisation Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see oepc.OrganisationUnit
	 * @generated
	 */
	public Adapter createOrganisationUnitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link oepc.XORConnector <em>XOR Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see oepc.XORConnector
	 * @generated
	 */
	public Adapter createXORConnectorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link oepc.ANDConnector <em>AND Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see oepc.ANDConnector
	 * @generated
	 */
	public Adapter createANDConnectorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link oepc.ORConnector <em>OR Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see oepc.ORConnector
	 * @generated
	 */
	public Adapter createORConnectorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link oepc.ControlFlowEdge <em>Control Flow Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see oepc.ControlFlowEdge
	 * @generated
	 */
	public Adapter createControlFlowEdgeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link oepc.InformationEdge <em>Information Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see oepc.InformationEdge
	 * @generated
	 */
	public Adapter createInformationEdgeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link oepc.BusinessObjectElement <em>Business Object Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see oepc.BusinessObjectElement
	 * @generated
	 */
	public Adapter createBusinessObjectElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link oepc.BusinessAttribute <em>Business Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see oepc.BusinessAttribute
	 * @generated
	 */
	public Adapter createBusinessAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link oepc.BusinessMethod <em>Business Method</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see oepc.BusinessMethod
	 * @generated
	 */
	public Adapter createBusinessMethodAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link oepc.Document <em>Document</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see oepc.Document
	 * @generated
	 */
	public Adapter createDocumentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.bflow.toolbox.bflow.BflowSymbol <em>Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bflow.toolbox.bflow.BflowSymbol
	 * @generated
	 */
	public Adapter createBflowSymbolAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.bflow.toolbox.bflow.Element <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bflow.toolbox.bflow.Element
	 * @generated
	 */
	public Adapter createElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.bflow.toolbox.bflow.IBflowElement <em>IBflow Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bflow.toolbox.bflow.IBflowElement
	 * @generated
	 */
	public Adapter createIBflowElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.bflow.toolbox.bflow.IConnector <em>IConnector</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bflow.toolbox.bflow.IConnector
	 * @generated
	 */
	public Adapter createIConnectorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.bflow.toolbox.bflow.Connection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bflow.toolbox.bflow.Connection
	 * @generated
	 */
	public Adapter createConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //OepcAdapterFactory

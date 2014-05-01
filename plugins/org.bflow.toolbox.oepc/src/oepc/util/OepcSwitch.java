/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package oepc.util;

import java.util.List;

import oepc.*;

import org.bflow.toolbox.bflow.BflowSymbol;
import org.bflow.toolbox.bflow.Connection;
import org.bflow.toolbox.bflow.Element;
import org.bflow.toolbox.bflow.IBflowElement;
import org.bflow.toolbox.bflow.IConnector;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see oepc.OepcPackage
 * @generated
 */
public class OepcSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static OepcPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OepcSwitch() {
		if (modelPackage == null) {
			modelPackage = OepcPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case OepcPackage.OEPC: {
				OEPC oepc = (OEPC)theEObject;
				T result = caseOEPC(oepc);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OepcPackage.EVENT: {
				Event event = (Event)theEObject;
				T result = caseEvent(event);
				if (result == null) result = caseElement(event);
				if (result == null) result = caseIBflowElement(event);
				if (result == null) result = caseBflowSymbol(event);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OepcPackage.BUSINESS_OBJECT: {
				BusinessObject businessObject = (BusinessObject)theEObject;
				T result = caseBusinessObject(businessObject);
				if (result == null) result = caseElement(businessObject);
				if (result == null) result = caseIBflowElement(businessObject);
				if (result == null) result = caseBflowSymbol(businessObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OepcPackage.IT_SYSTEM: {
				ITSystem itSystem = (ITSystem)theEObject;
				T result = caseITSystem(itSystem);
				if (result == null) result = caseElement(itSystem);
				if (result == null) result = caseIBflowElement(itSystem);
				if (result == null) result = caseBflowSymbol(itSystem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OepcPackage.ORGANISATION_UNIT: {
				OrganisationUnit organisationUnit = (OrganisationUnit)theEObject;
				T result = caseOrganisationUnit(organisationUnit);
				if (result == null) result = caseElement(organisationUnit);
				if (result == null) result = caseIBflowElement(organisationUnit);
				if (result == null) result = caseBflowSymbol(organisationUnit);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OepcPackage.XOR_CONNECTOR: {
				XORConnector xorConnector = (XORConnector)theEObject;
				T result = caseXORConnector(xorConnector);
				if (result == null) result = caseElement(xorConnector);
				if (result == null) result = caseIConnector(xorConnector);
				if (result == null) result = caseBflowSymbol(xorConnector);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OepcPackage.AND_CONNECTOR: {
				ANDConnector andConnector = (ANDConnector)theEObject;
				T result = caseANDConnector(andConnector);
				if (result == null) result = caseElement(andConnector);
				if (result == null) result = caseIConnector(andConnector);
				if (result == null) result = caseBflowSymbol(andConnector);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OepcPackage.OR_CONNECTOR: {
				ORConnector orConnector = (ORConnector)theEObject;
				T result = caseORConnector(orConnector);
				if (result == null) result = caseElement(orConnector);
				if (result == null) result = caseIConnector(orConnector);
				if (result == null) result = caseBflowSymbol(orConnector);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OepcPackage.CONTROL_FLOW_EDGE: {
				ControlFlowEdge controlFlowEdge = (ControlFlowEdge)theEObject;
				T result = caseControlFlowEdge(controlFlowEdge);
				if (result == null) result = caseConnection(controlFlowEdge);
				if (result == null) result = caseBflowSymbol(controlFlowEdge);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OepcPackage.INFORMATION_EDGE: {
				InformationEdge informationEdge = (InformationEdge)theEObject;
				T result = caseInformationEdge(informationEdge);
				if (result == null) result = caseConnection(informationEdge);
				if (result == null) result = caseBflowSymbol(informationEdge);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OepcPackage.BUSINESS_OBJECT_ELEMENT: {
				BusinessObjectElement businessObjectElement = (BusinessObjectElement)theEObject;
				T result = caseBusinessObjectElement(businessObjectElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OepcPackage.BUSINESS_ATTRIBUTE: {
				BusinessAttribute businessAttribute = (BusinessAttribute)theEObject;
				T result = caseBusinessAttribute(businessAttribute);
				if (result == null) result = caseBusinessObjectElement(businessAttribute);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OepcPackage.BUSINESS_METHOD: {
				BusinessMethod businessMethod = (BusinessMethod)theEObject;
				T result = caseBusinessMethod(businessMethod);
				if (result == null) result = caseBusinessObjectElement(businessMethod);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OepcPackage.DOCUMENT: {
				Document document = (Document)theEObject;
				T result = caseDocument(document);
				if (result == null) result = caseElement(document);
				if (result == null) result = caseIBflowElement(document);
				if (result == null) result = caseBflowSymbol(document);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>OEPC</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>OEPC</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOEPC(OEPC object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEvent(Event object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Business Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Business Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBusinessObject(BusinessObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IT System</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IT System</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseITSystem(ITSystem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Organisation Unit</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Organisation Unit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOrganisationUnit(OrganisationUnit object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>XOR Connector</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>XOR Connector</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseXORConnector(XORConnector object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AND Connector</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AND Connector</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseANDConnector(ANDConnector object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>OR Connector</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>OR Connector</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseORConnector(ORConnector object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Control Flow Edge</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Control Flow Edge</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseControlFlowEdge(ControlFlowEdge object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Information Edge</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Information Edge</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInformationEdge(InformationEdge object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Business Object Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Business Object Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBusinessObjectElement(BusinessObjectElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Business Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Business Attribute</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBusinessAttribute(BusinessAttribute object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Business Method</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Business Method</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBusinessMethod(BusinessMethod object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Document</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Document</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDocument(Document object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Symbol</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Symbol</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBflowSymbol(BflowSymbol object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseElement(Element object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IBflow Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IBflow Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIBflowElement(IBflowElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IConnector</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IConnector</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIConnector(IConnector object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConnection(Connection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //OepcSwitch

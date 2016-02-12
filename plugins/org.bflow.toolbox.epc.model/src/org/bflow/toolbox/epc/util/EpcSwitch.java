/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bflow.toolbox.epc.util;

import java.util.List;

import org.bflow.toolbox.bflow.BflowSymbol;
import org.bflow.toolbox.bflow.Connection;
import org.bflow.toolbox.bflow.Element;
import org.bflow.toolbox.bflow.IBflowElement;
import org.bflow.toolbox.bflow.IConnector;
import org.bflow.toolbox.bflow.IEBflowElement;
import org.bflow.toolbox.epc.AND;
import org.bflow.toolbox.epc.Application;
import org.bflow.toolbox.epc.Arc;
import org.bflow.toolbox.epc.CardFile;
import org.bflow.toolbox.epc.Cluster;
import org.bflow.toolbox.epc.Document;
import org.bflow.toolbox.epc.Epc;
import org.bflow.toolbox.epc.EpcNode;
import org.bflow.toolbox.epc.EpcPackage;
import org.bflow.toolbox.epc.Event;
import org.bflow.toolbox.epc.ExternalPerson;
import org.bflow.toolbox.epc.File;
import org.bflow.toolbox.epc.Function;
import org.bflow.toolbox.epc.Group;
import org.bflow.toolbox.epc.InformationArc;
import org.bflow.toolbox.epc.InternalPerson;
import org.bflow.toolbox.epc.Location;
import org.bflow.toolbox.epc.OR;
import org.bflow.toolbox.epc.Objective;
import org.bflow.toolbox.epc.Participant;
import org.bflow.toolbox.epc.PersonType;
import org.bflow.toolbox.epc.Position;
import org.bflow.toolbox.epc.ProcessInterface;
import org.bflow.toolbox.epc.Product;
import org.bflow.toolbox.epc.Relation;
import org.bflow.toolbox.epc.TechnicalTerm;
import org.bflow.toolbox.epc.XOR;
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
 * @see org.bflow.toolbox.epc.EpcPackage
 * @generated
 */
public class EpcSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static EpcPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EpcSwitch() {
		if (modelPackage == null) {
			modelPackage = EpcPackage.eINSTANCE;
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
			case EpcPackage.EVENT: {
				Event event = (Event)theEObject;
				T result = caseEvent(event);
				if (result == null) result = caseElement(event);
				if (result == null) result = caseIBflowElement(event);
				if (result == null) result = caseBflowSymbol(event);
				if (result == null) result = defaultCase(theEObject);
				if (result == null) result = caseEpcNode(event);
				return result;
			}
			case EpcPackage.FUNCTION: {
				Function function = (Function)theEObject;
				T result = caseFunction(function);
				if (result == null) result = caseElement(function);
				if (result == null) result = caseIBflowElement(function);
				if (result == null) result = caseBflowSymbol(function);
				if (result == null) result = defaultCase(theEObject);
				if (result == null) result = caseEpcNode(function);
				return result;
			}
			case EpcPackage.PROCESS_INTERFACE: {
				ProcessInterface processInterface = (ProcessInterface)theEObject;
				T result = caseProcessInterface(processInterface);
				if (result == null) result = caseElement(processInterface);
				if (result == null) result = caseIBflowElement(processInterface);
				if (result == null) result = caseBflowSymbol(processInterface);
				if (result == null) result = defaultCase(theEObject);
				if (result == null) result = caseEpcNode(processInterface);
				return result;
			}
			case EpcPackage.APPLICATION: {
				Application application = (Application)theEObject;
				T result = caseApplication(application);
				if (result == null) result = caseElement(application);
				if (result == null) result = caseIEBflowElement(application);
				if (result == null) result = caseBflowSymbol(application);
				if (result == null) result = defaultCase(theEObject);
				if (result == null) result = caseEpcNode(application);
				return result;
			}
			case EpcPackage.PARTICIPANT: {
				Participant participant = (Participant)theEObject;
				T result = caseParticipant(participant);
				if (result == null) result = caseElement(participant);
				if (result == null) result = caseIEBflowElement(participant);
				if (result == null) result = caseBflowSymbol(participant);
				if (result == null) result = defaultCase(theEObject);
				if (result == null) result = caseEpcNode(participant);
				return result;
			}
			case EpcPackage.AND: {
				AND and = (AND)theEObject;
				T result = caseAND(and);
				if (result == null) result = caseElement(and);
				if (result == null) result = caseIConnector(and);
				if (result == null) result = caseBflowSymbol(and);
				if (result == null) result = defaultCase(theEObject);
				if (result == null) result = caseEpcNode(and);
				return result;
			}
			case EpcPackage.OR: {
				OR or = (OR)theEObject;
				T result = caseOR(or);
				if (result == null) result = caseElement(or);
				if (result == null) result = caseIConnector(or);
				if (result == null) result = caseBflowSymbol(or);
				if (result == null) result = defaultCase(theEObject);
				if (result == null) result = caseEpcNode(or);
				return result;
			}
			case EpcPackage.XOR: {
				XOR xor = (XOR)theEObject;
				T result = caseXOR(xor);
				if (result == null) result = caseElement(xor);
				if (result == null) result = caseIConnector(xor);
				if (result == null) result = caseBflowSymbol(xor);
				if (result == null) result = defaultCase(theEObject);
				if (result == null) result = caseEpcNode(xor);
				return result;
			}
			case EpcPackage.ARC: {
				Arc arc = (Arc)theEObject;
				T result = caseArc(arc);
				if (result == null) result = caseConnection(arc);
				if (result == null) result = caseBflowSymbol(arc);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EpcPackage.RELATION: {
				Relation relation = (Relation)theEObject;
				T result = caseRelation(relation);
				if (result == null) result = caseConnection(relation);
				if (result == null) result = caseBflowSymbol(relation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EpcPackage.GROUP: {
				Group group = (Group)theEObject;
				T result = caseGroup(group);
				if (result == null) result = caseElement(group);
				if (result == null) result = caseIBflowElement(group);
				if (result == null) result = caseBflowSymbol(group);
				if (result == null) result = defaultCase(theEObject);
				if (result == null) result = caseEpcNode(group);
				return result;
			}
			case EpcPackage.LOCATION: {
				Location location = (Location)theEObject;
				T result = caseLocation(location);
				if (result == null) result = caseElement(location);
				if (result == null) result = caseIBflowElement(location);
				if (result == null) result = caseBflowSymbol(location);
				if (result == null) result = defaultCase(theEObject);
				if (result == null) result = caseEpcNode(location);
				return result;
			}
			case EpcPackage.POSITION: {
				Position position = (Position)theEObject;
				T result = casePosition(position);
				if (result == null) result = caseElement(position);
				if (result == null) result = caseIBflowElement(position);
				if (result == null) result = caseBflowSymbol(position);
				if (result == null) result = defaultCase(theEObject);
				if (result == null) result = caseEpcNode(position);
				return result;
			}
			case EpcPackage.FILE: {
				File file = (File)theEObject;
				T result = caseFile(file);
				if (result == null) result = caseElement(file);
				if (result == null) result = caseIBflowElement(file);
				if (result == null) result = caseBflowSymbol(file);
				if (result == null) result = defaultCase(theEObject);
				if (result == null) result = caseEpcNode(file);
				return result;
			}
			case EpcPackage.CARD_FILE: {
				CardFile cardFile = (CardFile)theEObject;
				T result = caseCardFile(cardFile);
				if (result == null) result = caseElement(cardFile);
				if (result == null) result = caseIBflowElement(cardFile);
				if (result == null) result = caseBflowSymbol(cardFile);
				if (result == null) result = defaultCase(theEObject);
				if (result == null) result = caseEpcNode(cardFile);
				return result;
			}
			case EpcPackage.CLUSTER: {
				Cluster cluster = (Cluster)theEObject;
				T result = caseCluster(cluster);
				if (result == null) result = caseElement(cluster);
				if (result == null) result = caseIBflowElement(cluster);
				if (result == null) result = caseBflowSymbol(cluster);
				if (result == null) result = defaultCase(theEObject);
				if (result == null) result = caseEpcNode(cluster);
				return result;
			}
			case EpcPackage.INTERNAL_PERSON: {
				InternalPerson internalPerson = (InternalPerson)theEObject;
				T result = caseInternalPerson(internalPerson);
				if (result == null) result = caseElement(internalPerson);
				if (result == null) result = caseIBflowElement(internalPerson);
				if (result == null) result = caseBflowSymbol(internalPerson);
				if (result == null) result = defaultCase(theEObject);
				if (result == null) result = caseEpcNode(internalPerson);
				return result;
			}
			case EpcPackage.EXTERNAL_PERSON: {
				ExternalPerson externalPerson = (ExternalPerson)theEObject;
				T result = caseExternalPerson(externalPerson);
				if (result == null) result = caseElement(externalPerson);
				if (result == null) result = caseIBflowElement(externalPerson);
				if (result == null) result = caseBflowSymbol(externalPerson);
				if (result == null) result = defaultCase(theEObject);
				if (result == null) result = caseEpcNode(externalPerson);
				return result;
			}
			case EpcPackage.PERSON_TYPE: {
				PersonType personType = (PersonType)theEObject;
				T result = casePersonType(personType);
				if (result == null) result = caseElement(personType);
				if (result == null) result = caseIBflowElement(personType);
				if (result == null) result = caseBflowSymbol(personType);
				if (result == null) result = defaultCase(theEObject);
				if (result == null) result = caseEpcNode(personType);
				return result;
			}
			case EpcPackage.TECHNICAL_TERM: {
				TechnicalTerm technicalTerm = (TechnicalTerm)theEObject;
				T result = caseTechnicalTerm(technicalTerm);
				if (result == null) result = caseElement(technicalTerm);
				if (result == null) result = caseIBflowElement(technicalTerm);
				if (result == null) result = caseBflowSymbol(technicalTerm);
				if (result == null) result = defaultCase(theEObject);
				if (result == null) result = caseEpcNode(technicalTerm);
				return result;
			}
			case EpcPackage.DOCUMENT: {
				Document document = (Document)theEObject;
				T result = caseDocument(document);
				if (result == null) result = caseElement(document);
				if (result == null) result = caseIBflowElement(document);
				if (result == null) result = caseBflowSymbol(document);
				if (result == null) result = defaultCase(theEObject);
				if (result == null) result = caseEpcNode(document);
				return result;
			}
			case EpcPackage.OBJECTIVE: {
				Objective objective = (Objective)theEObject;
				T result = caseObjective(objective);
				if (result == null) result = caseElement(objective);
				if (result == null) result = caseIBflowElement(objective);
				if (result == null) result = caseBflowSymbol(objective);
				if (result == null) result = defaultCase(theEObject);
				if (result == null) result = caseEpcNode(objective);
				return result;
			}
			case EpcPackage.PRODUCT: {
				Product product = (Product)theEObject;
				T result = caseProduct(product);
				if (result == null) result = caseElement(product);
				if (result == null) result = caseIBflowElement(product);
				if (result == null) result = caseBflowSymbol(product);
				if (result == null) result = defaultCase(theEObject);
				if (result == null) result = caseEpcNode(product);
				return result;
			}
			case EpcPackage.INFORMATION_ARC: {
				InformationArc informationArc = (InformationArc)theEObject;
				T result = caseInformationArc(informationArc);
				if (result == null) result = caseConnection(informationArc);
				if (result == null) result = caseBflowSymbol(informationArc);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EpcPackage.EpcNode: {
				EpcNode epcNode = (EpcNode)theEObject;
				T result = caseEpcNode(epcNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EpcPackage.EPC: {
				Epc epc = (Epc)theEObject;
				T result = caseEpc(epc);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
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
	 * Returns the result of interpreting the object as an instance of '<em>Function</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFunction(Function object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Process Interface</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Process Interface</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProcessInterface(ProcessInterface object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Application</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Application</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseApplication(Application object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Participant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Participant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParticipant(Participant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AND</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AND</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAND(AND object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>OR</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>OR</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOR(OR object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>XOR</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>XOR</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseXOR(XOR object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Arc</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Arc</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArc(Arc object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelation(Relation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGroup(Group object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Location</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Location</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLocation(Location object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Position</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Position</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePosition(Position object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>File</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>File</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFile(File object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Card File</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Card File</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCardFile(CardFile object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cluster</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cluster</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCluster(Cluster object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Internal Person</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Internal Person</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInternalPerson(InternalPerson object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>External Person</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>External Person</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExternalPerson(ExternalPerson object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Person Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Person Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePersonType(PersonType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Technical Term</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Technical Term</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTechnicalTerm(TechnicalTerm object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Objective</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Objective</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseObjective(Objective object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Product</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Product</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProduct(Product object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Information Arc</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Information Arc</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInformationArc(InformationArc object) {
		return null;
	}
	
	public T caseEpcNode(EpcNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Epc</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Epc</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEpc(Epc object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>IE Bflow Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IE Bflow Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIEBflowElement(IEBflowElement object) {
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

} //EpcSwitch

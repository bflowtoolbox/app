/**
 */
package vcchart.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import vcchart.*;

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
 * @see vcchart.VcchartPackage
 * @generated
 */
public class VcchartSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static VcchartPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VcchartSwitch() {
		if (modelPackage == null) {
			modelPackage = VcchartPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case VcchartPackage.MODEL: {
				Model model = (Model)theEObject;
				T result = caseModel(model);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VcchartPackage.NAMED_ELEMENT: {
				NamedElement namedElement = (NamedElement)theEObject;
				T result = caseNamedElement(namedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VcchartPackage.ALL: {
				All all = (All)theEObject;
				T result = caseAll(all);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VcchartPackage.ALL_REL_1: {
				All_Rel_1 all_Rel_1 = (All_Rel_1)theEObject;
				T result = caseAll_Rel_1(all_Rel_1);
				if (result == null) result = caseAll(all_Rel_1);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VcchartPackage.ALL_REL_1END_POINT: {
				All_Rel_1_EndPoint all_Rel_1_EndPoint = (All_Rel_1_EndPoint)theEObject;
				T result = caseAll_Rel_1_EndPoint(all_Rel_1_EndPoint);
				if (result == null) result = caseAll_Rel_1(all_Rel_1_EndPoint);
				if (result == null) result = caseAll(all_Rel_1_EndPoint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VcchartPackage.ALL_REL_3: {
				All_Rel_3 all_Rel_3 = (All_Rel_3)theEObject;
				T result = caseAll_Rel_3(all_Rel_3);
				if (result == null) result = caseAll(all_Rel_3);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VcchartPackage.RELATIONS_OBJECT: {
				RelationsObject relationsObject = (RelationsObject)theEObject;
				T result = caseRelationsObject(relationsObject);
				if (result == null) result = caseAll(relationsObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VcchartPackage.ACTIVITY1: {
				Activity1 activity1 = (Activity1)theEObject;
				T result = caseActivity1(activity1);
				if (result == null) result = caseNamedElement(activity1);
				if (result == null) result = caseAll_Rel_1(activity1);
				if (result == null) result = caseAll_Rel_3(activity1);
				if (result == null) result = caseAll(activity1);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VcchartPackage.ACTIVITY2: {
				Activity2 activity2 = (Activity2)theEObject;
				T result = caseActivity2(activity2);
				if (result == null) result = caseNamedElement(activity2);
				if (result == null) result = caseAll_Rel_1(activity2);
				if (result == null) result = caseAll_Rel_3(activity2);
				if (result == null) result = caseAll(activity2);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VcchartPackage.PRODUCT: {
				Product product = (Product)theEObject;
				T result = caseProduct(product);
				if (result == null) result = caseNamedElement(product);
				if (result == null) result = caseAll_Rel_1_EndPoint(product);
				if (result == null) result = caseAll_Rel_3(product);
				if (result == null) result = caseAll_Rel_1(product);
				if (result == null) result = caseAll(product);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VcchartPackage.OBJECTIVE: {
				Objective objective = (Objective)theEObject;
				T result = caseObjective(objective);
				if (result == null) result = caseNamedElement(objective);
				if (result == null) result = caseAll_Rel_1_EndPoint(objective);
				if (result == null) result = caseAll_Rel_3(objective);
				if (result == null) result = caseAll_Rel_1(objective);
				if (result == null) result = caseAll(objective);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VcchartPackage.CLUSTER: {
				Cluster cluster = (Cluster)theEObject;
				T result = caseCluster(cluster);
				if (result == null) result = caseNamedElement(cluster);
				if (result == null) result = caseAll_Rel_3(cluster);
				if (result == null) result = caseRelationsObject(cluster);
				if (result == null) result = caseAll(cluster);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VcchartPackage.TECHNICAL_TERM: {
				TechnicalTerm technicalTerm = (TechnicalTerm)theEObject;
				T result = caseTechnicalTerm(technicalTerm);
				if (result == null) result = caseNamedElement(technicalTerm);
				if (result == null) result = caseAll_Rel_3(technicalTerm);
				if (result == null) result = caseRelationsObject(technicalTerm);
				if (result == null) result = caseAll(technicalTerm);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VcchartPackage.PARTICIPANT: {
				Participant participant = (Participant)theEObject;
				T result = caseParticipant(participant);
				if (result == null) result = caseNamedElement(participant);
				if (result == null) result = caseAll_Rel_3(participant);
				if (result == null) result = caseRelationsObject(participant);
				if (result == null) result = caseAll(participant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VcchartPackage.APPLICATION: {
				Application application = (Application)theEObject;
				T result = caseApplication(application);
				if (result == null) result = caseNamedElement(application);
				if (result == null) result = caseAll_Rel_3(application);
				if (result == null) result = caseRelationsObject(application);
				if (result == null) result = caseAll(application);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VcchartPackage.DOCUMENT: {
				Document document = (Document)theEObject;
				T result = caseDocument(document);
				if (result == null) result = caseNamedElement(document);
				if (result == null) result = caseAll_Rel_3(document);
				if (result == null) result = caseRelationsObject(document);
				if (result == null) result = caseAll(document);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VcchartPackage.RELATION1: {
				Relation1 relation1 = (Relation1)theEObject;
				T result = caseRelation1(relation1);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VcchartPackage.RELATION2: {
				Relation2 relation2 = (Relation2)theEObject;
				T result = caseRelation2(relation2);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case VcchartPackage.RELATION3: {
				Relation3 relation3 = (Relation3)theEObject;
				T result = caseRelation3(relation3);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModel(Model object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedElement(NamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>All</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>All</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAll(All object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>All Rel 1</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>All Rel 1</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAll_Rel_1(All_Rel_1 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>All Rel 1End Point</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>All Rel 1End Point</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAll_Rel_1_EndPoint(All_Rel_1_EndPoint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>All Rel 3</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>All Rel 3</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAll_Rel_3(All_Rel_3 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relations Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relations Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelationsObject(RelationsObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activity1</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activity1</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActivity1(Activity1 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Activity2</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Activity2</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActivity2(Activity2 object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Relation1</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relation1</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelation1(Relation1 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relation2</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relation2</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelation2(Relation2 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relation3</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relation3</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelation3(Relation3 object) {
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
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //VcchartSwitch

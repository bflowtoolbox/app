/**
 * <copyright>
 * </copyright>
 *
 * $Id: Q7dslSwitch.java 532 2007-09-03 23:04:02Z nernst $
 */
package q7dsl.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import q7dsl.Actor;
import q7dsl.Advice;
import q7dsl.FLOAT;
import q7dsl.How;
import q7dsl.HowMuch;
import q7dsl.HowMuchRules;
import q7dsl.Label;
import q7dsl.Model;
import q7dsl.Q7dslPackage;
import q7dsl.Topic;
import q7dsl.What;
import q7dsl.When;
import q7dsl.Where;
import q7dsl.Who;
import q7dsl.Why;

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
 * @see q7dsl.Q7dslPackage
 * @generated
 */
public class Q7dslSwitch {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static Q7dslPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Q7dslSwitch() {
		if (modelPackage == null) {
			modelPackage = Q7dslPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public Object doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected Object doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch((EClass)eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected Object doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case Q7dslPackage.MODEL: {
				Model model = (Model)theEObject;
				Object result = caseModel(model);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Q7dslPackage.ADVICE: {
				Advice advice = (Advice)theEObject;
				Object result = caseAdvice(advice);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Q7dslPackage.WHO: {
				Who who = (Who)theEObject;
				Object result = caseWho(who);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Q7dslPackage.ACTOR: {
				Actor actor = (Actor)theEObject;
				Object result = caseActor(actor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Q7dslPackage.WHEN: {
				When when = (When)theEObject;
				Object result = caseWhen(when);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Q7dslPackage.WHY: {
				Why why = (Why)theEObject;
				Object result = caseWhy(why);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Q7dslPackage.WHAT: {
				What what = (What)theEObject;
				Object result = caseWhat(what);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Q7dslPackage.TOPIC: {
				Topic topic = (Topic)theEObject;
				Object result = caseTopic(topic);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Q7dslPackage.WHERE: {
				Where where = (Where)theEObject;
				Object result = caseWhere(where);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Q7dslPackage.HOW: {
				How how = (How)theEObject;
				Object result = caseHow(how);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Q7dslPackage.HOW_MUCH_RULES: {
				HowMuchRules howMuchRules = (HowMuchRules)theEObject;
				Object result = caseHowMuchRules(howMuchRules);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Q7dslPackage.HOW_MUCH: {
				HowMuch howMuch = (HowMuch)theEObject;
				Object result = caseHowMuch(howMuch);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Q7dslPackage.LABEL: {
				Label label = (Label)theEObject;
				Object result = caseLabel(label);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case Q7dslPackage.FLOAT: {
				FLOAT float_ = (FLOAT)theEObject;
				Object result = caseFLOAT(float_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseModel(Model object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Advice</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Advice</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseAdvice(Advice object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Who</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Who</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseWho(Who object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Actor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Actor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseActor(Actor object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>When</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>When</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseWhen(When object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Why</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Why</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseWhy(Why object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>What</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>What</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseWhat(What object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Topic</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Topic</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTopic(Topic object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Where</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Where</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseWhere(Where object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>How</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>How</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseHow(How object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>How Much Rules</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>How Much Rules</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseHowMuchRules(HowMuchRules object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>How Much</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>How Much</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseHowMuch(HowMuch object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Label</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Label</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLabel(Label object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>FLOAT</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>FLOAT</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseFLOAT(FLOAT object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public Object defaultCase(EObject object) {
		return null;
	}

} //Q7dslSwitch

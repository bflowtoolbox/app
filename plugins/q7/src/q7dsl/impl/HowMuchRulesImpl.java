/**
 * <copyright>
 * </copyright>
 *
 * $Id: HowMuchRulesImpl.java 532 2007-09-03 23:04:02Z nernst $
 */
package q7dsl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import q7dsl.HowMuch;
import q7dsl.HowMuchRules;
import q7dsl.Q7dslPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>How Much Rules</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link q7dsl.impl.HowMuchRulesImpl#getHowmuch <em>Howmuch</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HowMuchRulesImpl extends EObjectImpl implements HowMuchRules {
	/**
	 * The cached value of the '{@link #getHowmuch() <em>Howmuch</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHowmuch()
	 * @generated
	 * @ordered
	 */
	protected EList howmuch = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HowMuchRulesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return Q7dslPackage.Literals.HOW_MUCH_RULES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getHowmuch() {
		if (howmuch == null) {
			howmuch = new EObjectContainmentEList(HowMuch.class, this, Q7dslPackage.HOW_MUCH_RULES__HOWMUCH);
		}
		return howmuch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Q7dslPackage.HOW_MUCH_RULES__HOWMUCH:
				return ((InternalEList)getHowmuch()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Q7dslPackage.HOW_MUCH_RULES__HOWMUCH:
				return getHowmuch();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Q7dslPackage.HOW_MUCH_RULES__HOWMUCH:
				getHowmuch().clear();
				getHowmuch().addAll((Collection)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case Q7dslPackage.HOW_MUCH_RULES__HOWMUCH:
				getHowmuch().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case Q7dslPackage.HOW_MUCH_RULES__HOWMUCH:
				return howmuch != null && !howmuch.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //HowMuchRulesImpl
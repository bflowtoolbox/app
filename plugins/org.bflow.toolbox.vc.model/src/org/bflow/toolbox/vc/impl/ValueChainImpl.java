/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bflow.toolbox.vc.impl;

import org.bflow.toolbox.bflow.impl.ElementImpl;
import org.bflow.toolbox.vc.ValueChain;
import org.bflow.toolbox.vc.VcPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Value Chain</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.bflow.toolbox.vc.impl.ValueChainImpl#getSubdiagram <em>Subdiagram</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ValueChainImpl extends ElementImpl implements ValueChain {
	/**
	 * The default value of the '{@link #getSubdiagram() <em>Subdiagram</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubdiagram()
	 * @generated
	 * @ordered
	 */
	protected static final String SUBDIAGRAM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSubdiagram() <em>Subdiagram</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubdiagram()
	 * @generated
	 * @ordered
	 */
	protected String subdiagram = SUBDIAGRAM_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ValueChainImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return VcPackage.Literals.VALUE_CHAIN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSubdiagram() {
		return subdiagram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubdiagram(String newSubdiagram) {
		String oldSubdiagram = subdiagram;
		subdiagram = newSubdiagram;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, VcPackage.VALUE_CHAIN__SUBDIAGRAM, oldSubdiagram, subdiagram));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case VcPackage.VALUE_CHAIN__SUBDIAGRAM:
				return getSubdiagram();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case VcPackage.VALUE_CHAIN__SUBDIAGRAM:
				setSubdiagram((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case VcPackage.VALUE_CHAIN__SUBDIAGRAM:
				setSubdiagram(SUBDIAGRAM_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case VcPackage.VALUE_CHAIN__SUBDIAGRAM:
				return SUBDIAGRAM_EDEFAULT == null ? subdiagram != null : !SUBDIAGRAM_EDEFAULT.equals(subdiagram);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (Subdiagram: ");
		result.append(subdiagram);
		result.append(')');
		return result.toString();
	}

} //ValueChainImpl

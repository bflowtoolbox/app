/**
 */
package vcchart.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import vcchart.Activity1;
import vcchart.VcchartPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity1</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class Activity1Impl extends NamedElementImpl implements Activity1 {
	
	/**
	 * @generated NOT
	 */
	protected static final String SUBDIAGRAM_EDEFAULT = null;
	
	/**
	 * @generated NOT
	 */
	protected String subdiagram = SUBDIAGRAM_EDEFAULT;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Activity1Impl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return VcchartPackage.Literals.ACTIVITY1;
	}
	
	/**
	 * @generated NOT
	 */
	public String getSubdiagram() {
		return subdiagram;
	}
	
	/**
	 * @generated NOT
	 */
	public void setSubdiagram(String newSubdiagram) {
		String oldSubdiagram = subdiagram;
		subdiagram = newSubdiagram;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, VcchartPackage.ACTIVITY1__SUBDIAGRAM, oldSubdiagram, subdiagram));
	}
	
	/**
	 * @generated NOT
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
	
	/**
	 * @generated NOT
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case VcchartPackage.ACTIVITY1__SUBDIAGRAM:
				return getSubdiagram();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * @generated NOT
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case VcchartPackage.ACTIVITY1__SUBDIAGRAM:
				setSubdiagram((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * @generated NOT
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case VcchartPackage.ACTIVITY1__SUBDIAGRAM:
				setSubdiagram(SUBDIAGRAM_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * @generated NOT
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case VcchartPackage.ACTIVITY1__SUBDIAGRAM:
				return SUBDIAGRAM_EDEFAULT == null ? subdiagram != null : !SUBDIAGRAM_EDEFAULT.equals(subdiagram);
		}
		return super.eIsSet(featureID);
	}

} //Activity1Impl

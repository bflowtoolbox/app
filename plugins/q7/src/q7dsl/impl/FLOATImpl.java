/**
 * <copyright>
 * </copyright>
 *
 * $Id: FLOATImpl.java 532 2007-09-03 23:04:02Z nernst $
 */
package q7dsl.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import q7dsl.FLOAT;
import q7dsl.Q7dslPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>FLOAT</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link q7dsl.impl.FLOATImpl#getDecimal <em>Decimal</em>}</li>
 *   <li>{@link q7dsl.impl.FLOATImpl#getIntegral <em>Integral</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FLOATImpl extends EObjectImpl implements FLOAT {
	/**
	 * The default value of the '{@link #getDecimal() <em>Decimal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDecimal()
	 * @generated
	 * @ordered
	 */
	protected static final String DECIMAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDecimal() <em>Decimal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDecimal()
	 * @generated
	 * @ordered
	 */
	protected String decimal = DECIMAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getIntegral() <em>Integral</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntegral()
	 * @generated
	 * @ordered
	 */
	protected static final String INTEGRAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIntegral() <em>Integral</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntegral()
	 * @generated
	 * @ordered
	 */
	protected String integral = INTEGRAL_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FLOATImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return Q7dslPackage.Literals.FLOAT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDecimal() {
		return decimal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDecimal(String newDecimal) {
		String oldDecimal = decimal;
		decimal = newDecimal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7dslPackage.FLOAT__DECIMAL, oldDecimal, decimal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIntegral() {
		return integral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIntegral(String newIntegral) {
		String oldIntegral = integral;
		integral = newIntegral;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7dslPackage.FLOAT__INTEGRAL, oldIntegral, integral));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Q7dslPackage.FLOAT__DECIMAL:
				return getDecimal();
			case Q7dslPackage.FLOAT__INTEGRAL:
				return getIntegral();
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
			case Q7dslPackage.FLOAT__DECIMAL:
				setDecimal((String)newValue);
				return;
			case Q7dslPackage.FLOAT__INTEGRAL:
				setIntegral((String)newValue);
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
			case Q7dslPackage.FLOAT__DECIMAL:
				setDecimal(DECIMAL_EDEFAULT);
				return;
			case Q7dslPackage.FLOAT__INTEGRAL:
				setIntegral(INTEGRAL_EDEFAULT);
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
			case Q7dslPackage.FLOAT__DECIMAL:
				return DECIMAL_EDEFAULT == null ? decimal != null : !DECIMAL_EDEFAULT.equals(decimal);
			case Q7dslPackage.FLOAT__INTEGRAL:
				return INTEGRAL_EDEFAULT == null ? integral != null : !INTEGRAL_EDEFAULT.equals(integral);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (decimal: ");
		result.append(decimal);
		result.append(", integral: ");
		result.append(integral);
		result.append(')');
		return result.toString();
	}

} //FLOATImpl
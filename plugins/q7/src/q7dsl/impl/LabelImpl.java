/**
 * <copyright>
 * </copyright>
 *
 * $Id: LabelImpl.java 532 2007-09-03 23:04:02Z nernst $
 */
package q7dsl.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import q7dsl.FLOAT;
import q7dsl.Label;
import q7dsl.LabelEnumerator;
import q7dsl.Q7dslPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Label</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link q7dsl.impl.LabelImpl#getSat <em>Sat</em>}</li>
 *   <li>{@link q7dsl.impl.LabelImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link q7dsl.impl.LabelImpl#getDen <em>Den</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LabelImpl extends EObjectImpl implements Label {
	/**
	 * The cached value of the '{@link #getSat() <em>Sat</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSat()
	 * @generated
	 * @ordered
	 */
	protected FLOAT sat = null;

	/**
	 * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected static final LabelEnumerator LABEL_EDEFAULT = LabelEnumerator.FULLY_SATISFIED_LITERAL;

	/**
	 * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected LabelEnumerator label = LABEL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDen() <em>Den</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDen()
	 * @generated
	 * @ordered
	 */
	protected FLOAT den = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LabelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return Q7dslPackage.Literals.LABEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FLOAT getSat() {
		return sat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSat(FLOAT newSat, NotificationChain msgs) {
		FLOAT oldSat = sat;
		sat = newSat;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Q7dslPackage.LABEL__SAT, oldSat, newSat);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSat(FLOAT newSat) {
		if (newSat != sat) {
			NotificationChain msgs = null;
			if (sat != null)
				msgs = ((InternalEObject)sat).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.LABEL__SAT, null, msgs);
			if (newSat != null)
				msgs = ((InternalEObject)newSat).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.LABEL__SAT, null, msgs);
			msgs = basicSetSat(newSat, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7dslPackage.LABEL__SAT, newSat, newSat));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LabelEnumerator getLabel() {
		return label;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabel(LabelEnumerator newLabel) {
		LabelEnumerator oldLabel = label;
		label = newLabel == null ? LABEL_EDEFAULT : newLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7dslPackage.LABEL__LABEL, oldLabel, label));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FLOAT getDen() {
		return den;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDen(FLOAT newDen, NotificationChain msgs) {
		FLOAT oldDen = den;
		den = newDen;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Q7dslPackage.LABEL__DEN, oldDen, newDen);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDen(FLOAT newDen) {
		if (newDen != den) {
			NotificationChain msgs = null;
			if (den != null)
				msgs = ((InternalEObject)den).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.LABEL__DEN, null, msgs);
			if (newDen != null)
				msgs = ((InternalEObject)newDen).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.LABEL__DEN, null, msgs);
			msgs = basicSetDen(newDen, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7dslPackage.LABEL__DEN, newDen, newDen));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Q7dslPackage.LABEL__SAT:
				return basicSetSat(null, msgs);
			case Q7dslPackage.LABEL__DEN:
				return basicSetDen(null, msgs);
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
			case Q7dslPackage.LABEL__SAT:
				return getSat();
			case Q7dslPackage.LABEL__LABEL:
				return getLabel();
			case Q7dslPackage.LABEL__DEN:
				return getDen();
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
			case Q7dslPackage.LABEL__SAT:
				setSat((FLOAT)newValue);
				return;
			case Q7dslPackage.LABEL__LABEL:
				setLabel((LabelEnumerator)newValue);
				return;
			case Q7dslPackage.LABEL__DEN:
				setDen((FLOAT)newValue);
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
			case Q7dslPackage.LABEL__SAT:
				setSat((FLOAT)null);
				return;
			case Q7dslPackage.LABEL__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case Q7dslPackage.LABEL__DEN:
				setDen((FLOAT)null);
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
			case Q7dslPackage.LABEL__SAT:
				return sat != null;
			case Q7dslPackage.LABEL__LABEL:
				return label != LABEL_EDEFAULT;
			case Q7dslPackage.LABEL__DEN:
				return den != null;
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
		result.append(" (label: ");
		result.append(label);
		result.append(')');
		return result.toString();
	}

} //LabelImpl
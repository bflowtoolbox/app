/**
 * <copyright>
 * </copyright>
 *
 * $Id: HowMuchImpl.java 532 2007-09-03 23:04:02Z nernst $
 */
package q7dsl.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import q7dsl.FLOAT;
import q7dsl.HowMuch;
import q7dsl.Op;
import q7dsl.Q7dslPackage;
import q7dsl.What;
import q7dsl.Who;
import q7dsl.Why;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>How Much</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link q7dsl.impl.HowMuchImpl#getWhy <em>Why</em>}</li>
 *   <li>{@link q7dsl.impl.HowMuchImpl#getWho <em>Who</em>}</li>
 *   <li>{@link q7dsl.impl.HowMuchImpl#getOp <em>Op</em>}</li>
 *   <li>{@link q7dsl.impl.HowMuchImpl#getWhat <em>What</em>}</li>
 *   <li>{@link q7dsl.impl.HowMuchImpl#getTrust <em>Trust</em>}</li>
 *   <li>{@link q7dsl.impl.HowMuchImpl#getStrength <em>Strength</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HowMuchImpl extends EObjectImpl implements HowMuch {
	/**
	 * The cached value of the '{@link #getWhy() <em>Why</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWhy()
	 * @generated
	 * @ordered
	 */
	protected Why why = null;

	/**
	 * The cached value of the '{@link #getWho() <em>Who</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWho()
	 * @generated
	 * @ordered
	 */
	protected Who who = null;

	/**
	 * The default value of the '{@link #getOp() <em>Op</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOp()
	 * @generated
	 * @ordered
	 */
	protected static final Op OP_EDEFAULT = Op.HELP_LITERAL;

	/**
	 * The cached value of the '{@link #getOp() <em>Op</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOp()
	 * @generated
	 * @ordered
	 */
	protected Op op = OP_EDEFAULT;

	/**
	 * The cached value of the '{@link #getWhat() <em>What</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWhat()
	 * @generated
	 * @ordered
	 */
	protected What what = null;

	/**
	 * The cached value of the '{@link #getTrust() <em>Trust</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrust()
	 * @generated
	 * @ordered
	 */
	protected FLOAT trust = null;

	/**
	 * The cached value of the '{@link #getStrength() <em>Strength</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStrength()
	 * @generated
	 * @ordered
	 */
	protected FLOAT strength = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HowMuchImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return Q7dslPackage.Literals.HOW_MUCH;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Why getWhy() {
		return why;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWhy(Why newWhy, NotificationChain msgs) {
		Why oldWhy = why;
		why = newWhy;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Q7dslPackage.HOW_MUCH__WHY, oldWhy, newWhy);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWhy(Why newWhy) {
		if (newWhy != why) {
			NotificationChain msgs = null;
			if (why != null)
				msgs = ((InternalEObject)why).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.HOW_MUCH__WHY, null, msgs);
			if (newWhy != null)
				msgs = ((InternalEObject)newWhy).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.HOW_MUCH__WHY, null, msgs);
			msgs = basicSetWhy(newWhy, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7dslPackage.HOW_MUCH__WHY, newWhy, newWhy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Who getWho() {
		return who;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWho(Who newWho, NotificationChain msgs) {
		Who oldWho = who;
		who = newWho;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Q7dslPackage.HOW_MUCH__WHO, oldWho, newWho);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWho(Who newWho) {
		if (newWho != who) {
			NotificationChain msgs = null;
			if (who != null)
				msgs = ((InternalEObject)who).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.HOW_MUCH__WHO, null, msgs);
			if (newWho != null)
				msgs = ((InternalEObject)newWho).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.HOW_MUCH__WHO, null, msgs);
			msgs = basicSetWho(newWho, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7dslPackage.HOW_MUCH__WHO, newWho, newWho));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Op getOp() {
		return op;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOp(Op newOp) {
		Op oldOp = op;
		op = newOp == null ? OP_EDEFAULT : newOp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7dslPackage.HOW_MUCH__OP, oldOp, op));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public What getWhat() {
		return what;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWhat(What newWhat, NotificationChain msgs) {
		What oldWhat = what;
		what = newWhat;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Q7dslPackage.HOW_MUCH__WHAT, oldWhat, newWhat);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWhat(What newWhat) {
		if (newWhat != what) {
			NotificationChain msgs = null;
			if (what != null)
				msgs = ((InternalEObject)what).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.HOW_MUCH__WHAT, null, msgs);
			if (newWhat != null)
				msgs = ((InternalEObject)newWhat).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.HOW_MUCH__WHAT, null, msgs);
			msgs = basicSetWhat(newWhat, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7dslPackage.HOW_MUCH__WHAT, newWhat, newWhat));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FLOAT getTrust() {
		return trust;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTrust(FLOAT newTrust, NotificationChain msgs) {
		FLOAT oldTrust = trust;
		trust = newTrust;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Q7dslPackage.HOW_MUCH__TRUST, oldTrust, newTrust);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTrust(FLOAT newTrust) {
		if (newTrust != trust) {
			NotificationChain msgs = null;
			if (trust != null)
				msgs = ((InternalEObject)trust).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.HOW_MUCH__TRUST, null, msgs);
			if (newTrust != null)
				msgs = ((InternalEObject)newTrust).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.HOW_MUCH__TRUST, null, msgs);
			msgs = basicSetTrust(newTrust, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7dslPackage.HOW_MUCH__TRUST, newTrust, newTrust));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FLOAT getStrength() {
		return strength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStrength(FLOAT newStrength, NotificationChain msgs) {
		FLOAT oldStrength = strength;
		strength = newStrength;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Q7dslPackage.HOW_MUCH__STRENGTH, oldStrength, newStrength);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStrength(FLOAT newStrength) {
		if (newStrength != strength) {
			NotificationChain msgs = null;
			if (strength != null)
				msgs = ((InternalEObject)strength).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.HOW_MUCH__STRENGTH, null, msgs);
			if (newStrength != null)
				msgs = ((InternalEObject)newStrength).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.HOW_MUCH__STRENGTH, null, msgs);
			msgs = basicSetStrength(newStrength, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7dslPackage.HOW_MUCH__STRENGTH, newStrength, newStrength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Q7dslPackage.HOW_MUCH__WHY:
				return basicSetWhy(null, msgs);
			case Q7dslPackage.HOW_MUCH__WHO:
				return basicSetWho(null, msgs);
			case Q7dslPackage.HOW_MUCH__WHAT:
				return basicSetWhat(null, msgs);
			case Q7dslPackage.HOW_MUCH__TRUST:
				return basicSetTrust(null, msgs);
			case Q7dslPackage.HOW_MUCH__STRENGTH:
				return basicSetStrength(null, msgs);
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
			case Q7dslPackage.HOW_MUCH__WHY:
				return getWhy();
			case Q7dslPackage.HOW_MUCH__WHO:
				return getWho();
			case Q7dslPackage.HOW_MUCH__OP:
				return getOp();
			case Q7dslPackage.HOW_MUCH__WHAT:
				return getWhat();
			case Q7dslPackage.HOW_MUCH__TRUST:
				return getTrust();
			case Q7dslPackage.HOW_MUCH__STRENGTH:
				return getStrength();
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
			case Q7dslPackage.HOW_MUCH__WHY:
				setWhy((Why)newValue);
				return;
			case Q7dslPackage.HOW_MUCH__WHO:
				setWho((Who)newValue);
				return;
			case Q7dslPackage.HOW_MUCH__OP:
				setOp((Op)newValue);
				return;
			case Q7dslPackage.HOW_MUCH__WHAT:
				setWhat((What)newValue);
				return;
			case Q7dslPackage.HOW_MUCH__TRUST:
				setTrust((FLOAT)newValue);
				return;
			case Q7dslPackage.HOW_MUCH__STRENGTH:
				setStrength((FLOAT)newValue);
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
			case Q7dslPackage.HOW_MUCH__WHY:
				setWhy((Why)null);
				return;
			case Q7dslPackage.HOW_MUCH__WHO:
				setWho((Who)null);
				return;
			case Q7dslPackage.HOW_MUCH__OP:
				setOp(OP_EDEFAULT);
				return;
			case Q7dslPackage.HOW_MUCH__WHAT:
				setWhat((What)null);
				return;
			case Q7dslPackage.HOW_MUCH__TRUST:
				setTrust((FLOAT)null);
				return;
			case Q7dslPackage.HOW_MUCH__STRENGTH:
				setStrength((FLOAT)null);
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
			case Q7dslPackage.HOW_MUCH__WHY:
				return why != null;
			case Q7dslPackage.HOW_MUCH__WHO:
				return who != null;
			case Q7dslPackage.HOW_MUCH__OP:
				return op != OP_EDEFAULT;
			case Q7dslPackage.HOW_MUCH__WHAT:
				return what != null;
			case Q7dslPackage.HOW_MUCH__TRUST:
				return trust != null;
			case Q7dslPackage.HOW_MUCH__STRENGTH:
				return strength != null;
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
		result.append(" (op: ");
		result.append(op);
		result.append(')');
		return result.toString();
	}

} //HowMuchImpl
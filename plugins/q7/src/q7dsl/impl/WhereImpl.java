/**
 * <copyright>
 * </copyright>
 *
 * $Id: WhereImpl.java 532 2007-09-03 23:04:02Z nernst $
 */
package q7dsl.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import q7dsl.Op;
import q7dsl.Q7dslPackage;
import q7dsl.What;
import q7dsl.Where;
import q7dsl.Why;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Where</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link q7dsl.impl.WhereImpl#getName <em>Name</em>}</li>
 *   <li>{@link q7dsl.impl.WhereImpl#getOp <em>Op</em>}</li>
 *   <li>{@link q7dsl.impl.WhereImpl#getTopics <em>Topics</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WhereImpl extends EObjectImpl implements Where {
	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected Why name = null;

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
	 * The cached value of the '{@link #getTopics() <em>Topics</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTopics()
	 * @generated
	 * @ordered
	 */
	protected What topics = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WhereImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return Q7dslPackage.Literals.WHERE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Why getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetName(Why newName, NotificationChain msgs) {
		Why oldName = name;
		name = newName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Q7dslPackage.WHERE__NAME, oldName, newName);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(Why newName) {
		if (newName != name) {
			NotificationChain msgs = null;
			if (name != null)
				msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.WHERE__NAME, null, msgs);
			if (newName != null)
				msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.WHERE__NAME, null, msgs);
			msgs = basicSetName(newName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7dslPackage.WHERE__NAME, newName, newName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, Q7dslPackage.WHERE__OP, oldOp, op));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public What getTopics() {
		return topics;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTopics(What newTopics, NotificationChain msgs) {
		What oldTopics = topics;
		topics = newTopics;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Q7dslPackage.WHERE__TOPICS, oldTopics, newTopics);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTopics(What newTopics) {
		if (newTopics != topics) {
			NotificationChain msgs = null;
			if (topics != null)
				msgs = ((InternalEObject)topics).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.WHERE__TOPICS, null, msgs);
			if (newTopics != null)
				msgs = ((InternalEObject)newTopics).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Q7dslPackage.WHERE__TOPICS, null, msgs);
			msgs = basicSetTopics(newTopics, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7dslPackage.WHERE__TOPICS, newTopics, newTopics));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Q7dslPackage.WHERE__NAME:
				return basicSetName(null, msgs);
			case Q7dslPackage.WHERE__TOPICS:
				return basicSetTopics(null, msgs);
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
			case Q7dslPackage.WHERE__NAME:
				return getName();
			case Q7dslPackage.WHERE__OP:
				return getOp();
			case Q7dslPackage.WHERE__TOPICS:
				return getTopics();
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
			case Q7dslPackage.WHERE__NAME:
				setName((Why)newValue);
				return;
			case Q7dslPackage.WHERE__OP:
				setOp((Op)newValue);
				return;
			case Q7dslPackage.WHERE__TOPICS:
				setTopics((What)newValue);
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
			case Q7dslPackage.WHERE__NAME:
				setName((Why)null);
				return;
			case Q7dslPackage.WHERE__OP:
				setOp(OP_EDEFAULT);
				return;
			case Q7dslPackage.WHERE__TOPICS:
				setTopics((What)null);
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
			case Q7dslPackage.WHERE__NAME:
				return name != null;
			case Q7dslPackage.WHERE__OP:
				return op != OP_EDEFAULT;
			case Q7dslPackage.WHERE__TOPICS:
				return topics != null;
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

} //WhereImpl
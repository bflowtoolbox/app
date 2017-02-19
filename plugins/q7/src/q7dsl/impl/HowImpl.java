/**
 * <copyright>
 * </copyright>
 *
 * $Id: HowImpl.java 532 2007-09-03 23:04:02Z nernst $
 */
package q7dsl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import q7dsl.Advice;
import q7dsl.DecompositionType;
import q7dsl.Enrichment;
import q7dsl.How;
import q7dsl.Q7dslPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>How</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link q7dsl.impl.HowImpl#getAdvices <em>Advices</em>}</li>
 *   <li>{@link q7dsl.impl.HowImpl#getEnrich <em>Enrich</em>}</li>
 *   <li>{@link q7dsl.impl.HowImpl#getOp <em>Op</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HowImpl extends EObjectImpl implements How {
	/**
	 * The cached value of the '{@link #getAdvices() <em>Advices</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdvices()
	 * @generated
	 * @ordered
	 */
	protected EList advices = null;

	/**
	 * The default value of the '{@link #getEnrich() <em>Enrich</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnrich()
	 * @generated
	 * @ordered
	 */
	protected static final Enrichment ENRICH_EDEFAULT = Enrichment.SEQUENTIAL_LITERAL;

	/**
	 * The cached value of the '{@link #getEnrich() <em>Enrich</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnrich()
	 * @generated
	 * @ordered
	 */
	protected Enrichment enrich = ENRICH_EDEFAULT;

	/**
	 * The default value of the '{@link #getOp() <em>Op</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOp()
	 * @generated
	 * @ordered
	 */
	protected static final DecompositionType OP_EDEFAULT = DecompositionType.AND_LITERAL;

	/**
	 * The cached value of the '{@link #getOp() <em>Op</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOp()
	 * @generated
	 * @ordered
	 */
	protected DecompositionType op = OP_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HowImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return Q7dslPackage.Literals.HOW;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getAdvices() {
		if (advices == null) {
			advices = new EObjectContainmentEList(Advice.class, this, Q7dslPackage.HOW__ADVICES);
		}
		return advices;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Enrichment getEnrich() {
		return enrich;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnrich(Enrichment newEnrich) {
		Enrichment oldEnrich = enrich;
		enrich = newEnrich == null ? ENRICH_EDEFAULT : newEnrich;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7dslPackage.HOW__ENRICH, oldEnrich, enrich));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DecompositionType getOp() {
		return op;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOp(DecompositionType newOp) {
		DecompositionType oldOp = op;
		op = newOp == null ? OP_EDEFAULT : newOp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Q7dslPackage.HOW__OP, oldOp, op));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Q7dslPackage.HOW__ADVICES:
				return ((InternalEList)getAdvices()).basicRemove(otherEnd, msgs);
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
			case Q7dslPackage.HOW__ADVICES:
				return getAdvices();
			case Q7dslPackage.HOW__ENRICH:
				return getEnrich();
			case Q7dslPackage.HOW__OP:
				return getOp();
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
			case Q7dslPackage.HOW__ADVICES:
				getAdvices().clear();
				getAdvices().addAll((Collection)newValue);
				return;
			case Q7dslPackage.HOW__ENRICH:
				setEnrich((Enrichment)newValue);
				return;
			case Q7dslPackage.HOW__OP:
				setOp((DecompositionType)newValue);
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
			case Q7dslPackage.HOW__ADVICES:
				getAdvices().clear();
				return;
			case Q7dslPackage.HOW__ENRICH:
				setEnrich(ENRICH_EDEFAULT);
				return;
			case Q7dslPackage.HOW__OP:
				setOp(OP_EDEFAULT);
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
			case Q7dslPackage.HOW__ADVICES:
				return advices != null && !advices.isEmpty();
			case Q7dslPackage.HOW__ENRICH:
				return enrich != ENRICH_EDEFAULT;
			case Q7dslPackage.HOW__OP:
				return op != OP_EDEFAULT;
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
		result.append(" (enrich: ");
		result.append(enrich);
		result.append(", op: ");
		result.append(op);
		result.append(')');
		return result.toString();
	}

} //HowImpl
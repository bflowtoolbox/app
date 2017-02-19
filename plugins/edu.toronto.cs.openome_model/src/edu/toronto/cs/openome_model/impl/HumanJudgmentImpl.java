/**
 * Copyright 2001-2008 University of Toronto
 *
 * $Id$
 */
package edu.toronto.cs.openome_model.impl;

import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.HumanJudgment;
import edu.toronto.cs.openome_model.LabelBag;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.openome_modelPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Human Judgment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.toronto.cs.openome_model.impl.HumanJudgmentImpl#getResultLabel <em>Result Label</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.HumanJudgmentImpl#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.HumanJudgmentImpl#getLabelBag <em>Label Bag</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HumanJudgmentImpl extends EObjectImpl implements HumanJudgment {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright 2001-2008 University of Toronto";

	/**
	 * The status of this Alternative - utilized for <code>AlternativesView</code> purposes 
	 */
	protected boolean status = false; 
	
	/**
	 * The default value of the '{@link #getResultLabel() <em>Result Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResultLabel()
	 * @generated
	 * @ordered
	 */
	protected static final EvaluationLabel RESULT_LABEL_EDEFAULT = EvaluationLabel.NONE;

	/**
	 * The cached value of the '{@link #getResultLabel() <em>Result Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResultLabel()
	 * @generated
	 * @ordered
	 */
	protected EvaluationLabel resultLabel = RESULT_LABEL_EDEFAULT;

	/**
	 * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * Changed from false to true
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated NOT
	 * @ordered
	 */
	protected static final boolean ENABLED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected boolean enabled = ENABLED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLabelBag() <em>Label Bag</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabelBag()
	 * @generated
	 * @ordered
	 */
	protected LabelBag labelBag;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public HumanJudgmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public HumanJudgmentImpl(LabelBag lb, EvaluationLabel label) {
		super();
		setLabelBag(lb);
		setResultLabel(label);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return openome_modelPackage.Literals.HUMAN_JUDGMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EvaluationLabel getResultLabel() {
		return resultLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResultLabel(EvaluationLabel newResultLabel) {
		EvaluationLabel oldResultLabel = resultLabel;
		resultLabel = newResultLabel == null ? RESULT_LABEL_EDEFAULT : newResultLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.HUMAN_JUDGMENT__RESULT_LABEL, oldResultLabel, resultLabel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnabled(boolean newEnabled) {
		boolean oldEnabled = enabled;
		enabled = newEnabled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.HUMAN_JUDGMENT__ENABLED, oldEnabled, enabled));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LabelBag getLabelBag() {
		return labelBag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLabelBag(LabelBag newLabelBag, NotificationChain msgs) {
		LabelBag oldLabelBag = labelBag;
		labelBag = newLabelBag;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, openome_modelPackage.HUMAN_JUDGMENT__LABEL_BAG, oldLabelBag, newLabelBag);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabelBag(LabelBag newLabelBag) {
		if (newLabelBag != labelBag) {
			NotificationChain msgs = null;
			if (labelBag != null)
				msgs = ((InternalEObject)labelBag).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - openome_modelPackage.HUMAN_JUDGMENT__LABEL_BAG, null, msgs);
			if (newLabelBag != null)
				msgs = ((InternalEObject)newLabelBag).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - openome_modelPackage.HUMAN_JUDGMENT__LABEL_BAG, null, msgs);
			msgs = basicSetLabelBag(newLabelBag, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.HUMAN_JUDGMENT__LABEL_BAG, newLabelBag, newLabelBag));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case openome_modelPackage.HUMAN_JUDGMENT__LABEL_BAG:
				return basicSetLabelBag(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case openome_modelPackage.HUMAN_JUDGMENT__RESULT_LABEL:
				return getResultLabel();
			case openome_modelPackage.HUMAN_JUDGMENT__ENABLED:
				return isEnabled();
			case openome_modelPackage.HUMAN_JUDGMENT__LABEL_BAG:
				return getLabelBag();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case openome_modelPackage.HUMAN_JUDGMENT__RESULT_LABEL:
				setResultLabel((EvaluationLabel)newValue);
				return;
			case openome_modelPackage.HUMAN_JUDGMENT__ENABLED:
				setEnabled((Boolean)newValue);
				return;
			case openome_modelPackage.HUMAN_JUDGMENT__LABEL_BAG:
				setLabelBag((LabelBag)newValue);
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
			case openome_modelPackage.HUMAN_JUDGMENT__RESULT_LABEL:
				setResultLabel(RESULT_LABEL_EDEFAULT);
				return;
			case openome_modelPackage.HUMAN_JUDGMENT__ENABLED:
				setEnabled(ENABLED_EDEFAULT);
				return;
			case openome_modelPackage.HUMAN_JUDGMENT__LABEL_BAG:
				setLabelBag((LabelBag)null);
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
			case openome_modelPackage.HUMAN_JUDGMENT__RESULT_LABEL:
				return resultLabel != RESULT_LABEL_EDEFAULT;
			case openome_modelPackage.HUMAN_JUDGMENT__ENABLED:
				return enabled != ENABLED_EDEFAULT;
			case openome_modelPackage.HUMAN_JUDGMENT__LABEL_BAG:
				return labelBag != null;
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
		result.append(" (resultLabel: ");
		result.append(resultLabel);
		result.append(", enabled: ");
		result.append(enabled);
		result.append(')');
		return result.toString();
	}
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String toUIString() {
		String str = "";
		str += "Human Judgment\nResult Label: " + getResultLabel().toString();
		str += "\nLabel Bag: ";
		if (getLabelBag() != null)
			str += getLabelBag().toUIString();
		else
			str += "null";
		return str;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public HumanJudgment findOrImplies(LabelBag bag) {
		if (bag.equals(getLabelBag())) {
			//System.out.println("Equals");
			return this;
		}
		else if (bag.size() < getLabelBag().size())
			return null;
		else {
			LabelBag diff = getLabelBag().bagDiff(bag);
			//System.out.println("Diff");
			//diff.printBag();
			//System.out.println("//Diff");
			if (diff == null) {
				System.out.println("Problem with findOrImplies");
				return null;
			}
			if (getResultLabel() == EvaluationLabel.SATISFIED && diff.isAllPositive())
				return this;
			if (getResultLabel() == EvaluationLabel.DENIED && diff.isAllNegative())
				return this;
		}
		return null;
	}

	@Override
	public boolean getAffectedStatus() {
		return status;
	}

	@Override
	public void setAffectedStatus(boolean b) {
		status = b; 
		
	}

} //HumanJudgmentImpl

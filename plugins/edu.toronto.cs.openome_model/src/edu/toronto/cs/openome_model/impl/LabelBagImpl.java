/**
 * Copyright 2001-2008 University of Toronto
 *
 * $Id$
 */
package edu.toronto.cs.openome_model.impl;

import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.LabelBag;
import edu.toronto.cs.openome_model.openome_modelPackage;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Vector;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;



/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Label Bag</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.toronto.cs.openome_model.impl.LabelBagImpl#getLabelBagIntentions <em>Label Bag Intentions</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.LabelBagImpl#getLabelBagEvalLabels <em>Label Bag Eval Labels</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.LabelBagImpl#isToResolve <em>To Resolve</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.LabelBagImpl#isAllPositive <em>All Positive</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.LabelBagImpl#isAllNegative <em>All Negative</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.LabelBagImpl#isHasFullPositive <em>Has Full Positive</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.LabelBagImpl#isHasFullNegative <em>Has Full Negative</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.LabelBagImpl#isHasUnknown <em>Has Unknown</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.LabelBagImpl#isHasConflict <em>Has Conflict</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.LabelBagImpl#isAllUnknown <em>All Unknown</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.LabelBagImpl#isAllConflict <em>All Conflict</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LabelBagImpl extends EObjectImpl implements LabelBag {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright 2001-2008 University of Toronto";

	/**
	 * The cached value of the '{@link #getLabelBagIntentions() <em>Label Bag Intentions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabelBagIntentions()
	 * @generated
	 * @ordered
	 */
	protected EList<Intention> labelBagIntentions;

	/**
	 * The cached value of the '{@link #getLabelBagEvalLabels() <em>Label Bag Eval Labels</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabelBagEvalLabels()
	 * @generated
	 * @ordered
	 */
	protected EList<EvaluationLabel> labelBagEvalLabels;

	/**
	 * The default value of the '{@link #isToResolve() <em>To Resolve</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isToResolve()
	 * @generated NOT
	 * @ordered
	 */
	protected static final boolean TO_RESOLVE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isToResolve() <em>To Resolve</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isToResolve()
	 * @generated
	 * @ordered
	 */
	protected boolean toResolve = TO_RESOLVE_EDEFAULT;

	/**
	 * The default value of the '{@link #isAllPositive() <em>All Positive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllPositive()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ALL_POSITIVE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAllPositive() <em>All Positive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllPositive()
	 * @generated
	 * @ordered
	 */
	protected boolean allPositive = ALL_POSITIVE_EDEFAULT;

	/**
	 * The default value of the '{@link #isAllNegative() <em>All Negative</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllNegative()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ALL_NEGATIVE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAllNegative() <em>All Negative</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllNegative()
	 * @generated
	 * @ordered
	 */
	protected boolean allNegative = ALL_NEGATIVE_EDEFAULT;

	/**
	 * The default value of the '{@link #isHasFullPositive() <em>Has Full Positive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasFullPositive()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HAS_FULL_POSITIVE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHasFullPositive() <em>Has Full Positive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasFullPositive()
	 * @generated
	 * @ordered
	 */
	protected boolean hasFullPositive = HAS_FULL_POSITIVE_EDEFAULT;

	/**
	 * The default value of the '{@link #isHasFullNegative() <em>Has Full Negative</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasFullNegative()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HAS_FULL_NEGATIVE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHasFullNegative() <em>Has Full Negative</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasFullNegative()
	 * @generated
	 * @ordered
	 */
	protected boolean hasFullNegative = HAS_FULL_NEGATIVE_EDEFAULT;

	/**
	 * The default value of the '{@link #isHasUnknown() <em>Has Unknown</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasUnknown()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HAS_UNKNOWN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHasUnknown() <em>Has Unknown</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasUnknown()
	 * @generated
	 * @ordered
	 */
	protected boolean hasUnknown = HAS_UNKNOWN_EDEFAULT;

	/**
	 * The default value of the '{@link #isHasConflict() <em>Has Conflict</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasConflict()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HAS_CONFLICT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHasConflict() <em>Has Conflict</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHasConflict()
	 * @generated
	 * @ordered
	 */
	protected boolean hasConflict = HAS_CONFLICT_EDEFAULT;

	/**
	 * The default value of the '{@link #isAllUnknown() <em>All Unknown</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllUnknown()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ALL_UNKNOWN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAllUnknown() <em>All Unknown</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllUnknown()
	 * @generated
	 * @ordered
	 */
	protected boolean allUnknown = ALL_UNKNOWN_EDEFAULT;

	/**
	 * The default value of the '{@link #isAllConflict() <em>All Conflict</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllConflict()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ALL_CONFLICT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAllConflict() <em>All Conflict</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllConflict()
	 * @generated
	 * @ordered
	 */
	protected boolean allConflict = ALL_CONFLICT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * changed from protected to public for testing purposes
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public LabelBagImpl() {
		super();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * changed from protected to public for testing purposes
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public LabelBagImpl(LabelBag copy) {
		super();
		getLabelBagEvalLabels().removeAll(getLabelBagEvalLabels());
		getLabelBagIntentions().removeAll(getLabelBagIntentions());
		getLabelBagEvalLabels().addAll(copy.getLabelBagEvalLabels());
		getLabelBagIntentions().addAll(copy.getLabelBagIntentions());
		setToResolve(copy.needResolve());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return openome_modelPackage.Literals.LABEL_BAG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<Intention> getLabelBagIntentions() {
		if (labelBagIntentions == null) {
			labelBagIntentions = new EObjectResolvingEList<Intention>(Intention.class, this, openome_modelPackage.LABEL_BAG__LABEL_BAG_INTENTIONS);
		}
		return labelBagIntentions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<EvaluationLabel> getLabelBagEvalLabels() {
		if (labelBagEvalLabels == null) {
			labelBagEvalLabels = new EDataTypeEList<EvaluationLabel>(EvaluationLabel.class, this, openome_modelPackage.LABEL_BAG__LABEL_BAG_EVAL_LABELS);
		}
		return labelBagEvalLabels;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isToResolve() {
		return toResolve;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setToResolve(boolean newToResolve) {
		boolean oldToResolve = toResolve;
		toResolve = newToResolve;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.LABEL_BAG__TO_RESOLVE, oldToResolve, toResolve));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setToResolved() {
		boolean oldToResolve = toResolve;
		toResolve = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.LABEL_BAG__TO_RESOLVE, oldToResolve, toResolve));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean needResolve() {
		return toResolve;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isAllPositive() {
		//assessBag();
		return allPositive;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAllPositive(boolean newAllPositive) {
		boolean oldAllPositive = allPositive;
		allPositive = newAllPositive;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.LABEL_BAG__ALL_POSITIVE, oldAllPositive, allPositive));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isAllNegative() {
		//assessBag();
		return allNegative;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAllNegative(boolean newAllNegative) {
		boolean oldAllNegative = allNegative;
		allNegative = newAllNegative;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.LABEL_BAG__ALL_NEGATIVE, oldAllNegative, allNegative));
	}
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHasFullPositive() {
		return hasFullPositive;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean hasFullPositive() {
		//assessBag();
		return hasFullPositive;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasFullPositive(boolean newHasFullPositive) {
		boolean oldHasFullPositive = hasFullPositive;
		hasFullPositive = newHasFullPositive;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.LABEL_BAG__HAS_FULL_POSITIVE, oldHasFullPositive, hasFullPositive));
	}
		
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHasFullNegative() {
		return hasFullNegative;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean hasFullNegative() {
		//assessBag();
		return hasFullNegative;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasFullNegative(boolean newHasFullNegative) {
		boolean oldHasFullNegative = hasFullNegative;
		hasFullNegative = newHasFullNegative;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.LABEL_BAG__HAS_FULL_NEGATIVE, oldHasFullNegative, hasFullNegative));
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHasUnknown() {
		return hasUnknown;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean hasUnknown() {
		///assessBag();
		return hasUnknown;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasUnknown(boolean newHasUnknown) {
		boolean oldHasUnknown = hasUnknown;
		hasUnknown = newHasUnknown;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.LABEL_BAG__HAS_UNKNOWN, oldHasUnknown, hasUnknown));
	}	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHasConflict() {
		return hasConflict;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHasConflict(boolean newHasConflict) {
		boolean oldHasConflict = hasConflict;
		hasConflict = newHasConflict;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.LABEL_BAG__HAS_CONFLICT, oldHasConflict, hasConflict));
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean hasConflict() {
		//assessBag();
		return hasConflict;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isAllUnknown() {
		//assessBag();
		return allUnknown;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAllUnknown(boolean newAllUnknown) {
		boolean oldAllUnknown = allUnknown;
		allUnknown = newAllUnknown;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.LABEL_BAG__ALL_UNKNOWN, oldAllUnknown, allUnknown));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isAllConflict() {
		//assessBag();
		return allConflict;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAllConflict(boolean newAllConflict) {
		boolean oldAllConflict = allConflict;
		allConflict = newAllConflict;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.LABEL_BAG__ALL_CONFLICT, oldAllConflict, allConflict));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case openome_modelPackage.LABEL_BAG__LABEL_BAG_INTENTIONS:
				return getLabelBagIntentions();
			case openome_modelPackage.LABEL_BAG__LABEL_BAG_EVAL_LABELS:
				return getLabelBagEvalLabels();
			case openome_modelPackage.LABEL_BAG__TO_RESOLVE:
				return isToResolve();
			case openome_modelPackage.LABEL_BAG__ALL_POSITIVE:
				return isAllPositive();
			case openome_modelPackage.LABEL_BAG__ALL_NEGATIVE:
				return isAllNegative();
			case openome_modelPackage.LABEL_BAG__HAS_FULL_POSITIVE:
				return hasFullPositive();
			case openome_modelPackage.LABEL_BAG__HAS_FULL_NEGATIVE:
				return hasFullNegative();
			case openome_modelPackage.LABEL_BAG__HAS_UNKNOWN:
				return hasUnknown();
			case openome_modelPackage.LABEL_BAG__HAS_CONFLICT:
				return hasConflict();
			case openome_modelPackage.LABEL_BAG__ALL_UNKNOWN:
				return isAllUnknown();
			case openome_modelPackage.LABEL_BAG__ALL_CONFLICT:
				return isAllConflict();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case openome_modelPackage.LABEL_BAG__LABEL_BAG_INTENTIONS:
				getLabelBagIntentions().clear();
				getLabelBagIntentions().addAll((Collection<? extends Intention>)newValue);
				return;
			case openome_modelPackage.LABEL_BAG__LABEL_BAG_EVAL_LABELS:
				getLabelBagEvalLabels().clear();
				getLabelBagEvalLabels().addAll((Collection<? extends EvaluationLabel>)newValue);
				return;
			case openome_modelPackage.LABEL_BAG__TO_RESOLVE:
				setToResolved();
				return;
			case openome_modelPackage.LABEL_BAG__ALL_POSITIVE:
				setAllPositive((Boolean)newValue);
				return;
			case openome_modelPackage.LABEL_BAG__ALL_NEGATIVE:
				setAllNegative((Boolean)newValue);
				return;
			case openome_modelPackage.LABEL_BAG__HAS_FULL_POSITIVE:
				setHasFullPositive((Boolean)newValue);
				return;
			case openome_modelPackage.LABEL_BAG__HAS_FULL_NEGATIVE:
				setHasFullNegative((Boolean)newValue);
				return;
			case openome_modelPackage.LABEL_BAG__HAS_UNKNOWN:
				setHasUnknown((Boolean)newValue);
				return;
			case openome_modelPackage.LABEL_BAG__HAS_CONFLICT:
				setHasConflict((Boolean)newValue);
				return;
			case openome_modelPackage.LABEL_BAG__ALL_UNKNOWN:
				setAllUnknown((Boolean)newValue);
				return;
			case openome_modelPackage.LABEL_BAG__ALL_CONFLICT:
				setAllConflict((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case openome_modelPackage.LABEL_BAG__LABEL_BAG_INTENTIONS:
				getLabelBagIntentions().clear();
				return;
			case openome_modelPackage.LABEL_BAG__LABEL_BAG_EVAL_LABELS:
				getLabelBagEvalLabels().clear();
				return;
			case openome_modelPackage.LABEL_BAG__TO_RESOLVE:
				setToResolved();
				return;
			case openome_modelPackage.LABEL_BAG__ALL_POSITIVE:
				setAllPositive(ALL_POSITIVE_EDEFAULT);
				return;
			case openome_modelPackage.LABEL_BAG__ALL_NEGATIVE:
				setAllNegative(ALL_NEGATIVE_EDEFAULT);
				return;
			case openome_modelPackage.LABEL_BAG__HAS_FULL_POSITIVE:
				setHasFullPositive(HAS_FULL_POSITIVE_EDEFAULT);
				return;
			case openome_modelPackage.LABEL_BAG__HAS_FULL_NEGATIVE:
				setHasFullNegative(HAS_FULL_NEGATIVE_EDEFAULT);
				return;
			case openome_modelPackage.LABEL_BAG__HAS_UNKNOWN:
				setHasUnknown(HAS_UNKNOWN_EDEFAULT);
				return;
			case openome_modelPackage.LABEL_BAG__HAS_CONFLICT:
				setHasConflict(HAS_CONFLICT_EDEFAULT);
				return;
			case openome_modelPackage.LABEL_BAG__ALL_UNKNOWN:
				setAllUnknown(ALL_UNKNOWN_EDEFAULT);
				return;
			case openome_modelPackage.LABEL_BAG__ALL_CONFLICT:
				setAllConflict(ALL_CONFLICT_EDEFAULT);
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
			case openome_modelPackage.LABEL_BAG__LABEL_BAG_INTENTIONS:
				return labelBagIntentions != null && !labelBagIntentions.isEmpty();
			case openome_modelPackage.LABEL_BAG__LABEL_BAG_EVAL_LABELS:
				return labelBagEvalLabels != null && !labelBagEvalLabels.isEmpty();
			case openome_modelPackage.LABEL_BAG__TO_RESOLVE:
				return toResolve != TO_RESOLVE_EDEFAULT;
			case openome_modelPackage.LABEL_BAG__ALL_POSITIVE:
				return allPositive != ALL_POSITIVE_EDEFAULT;
			case openome_modelPackage.LABEL_BAG__ALL_NEGATIVE:
				return allNegative != ALL_NEGATIVE_EDEFAULT;
			case openome_modelPackage.LABEL_BAG__HAS_FULL_POSITIVE:
				return hasFullPositive != HAS_FULL_POSITIVE_EDEFAULT;
			case openome_modelPackage.LABEL_BAG__HAS_FULL_NEGATIVE:
				return hasFullNegative != HAS_FULL_NEGATIVE_EDEFAULT;
			case openome_modelPackage.LABEL_BAG__HAS_UNKNOWN:
				return hasUnknown != HAS_UNKNOWN_EDEFAULT;
			case openome_modelPackage.LABEL_BAG__HAS_CONFLICT:
				return hasConflict != HAS_CONFLICT_EDEFAULT;
			case openome_modelPackage.LABEL_BAG__ALL_UNKNOWN:
				return allUnknown != ALL_UNKNOWN_EDEFAULT;
			case openome_modelPackage.LABEL_BAG__ALL_CONFLICT:
				return allConflict != ALL_CONFLICT_EDEFAULT;
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
		result.append(" (labelBagEvalLabels: ");
		result.append(labelBagEvalLabels);
		result.append(", toResolve: ");
		result.append(toResolve);
		result.append(", allPositive: ");
		result.append(allPositive);
		result.append(", allNegative: ");
		result.append(allNegative);
		result.append(", hasFullPositive: ");
		result.append(hasFullPositive);
		result.append(", hasFullNegative: ");
		result.append(hasFullNegative);
		result.append(", hasUnknown: ");
		result.append(hasUnknown);
		result.append(", hasConflict: ");
		result.append(hasConflict);
		result.append(", allUnknown: ");
		result.append(allUnknown);
		result.append(", allConflict: ");
		result.append(allConflict);
		result.append(')');
		return result.toString();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String toUIString() {
		String str = "";		
		int i = 0;
		for (Intention intn : getLabelBagIntentions()) {
			str += "(" + intn.getName() + ", " + getLabelBagEvalLabels().get(i).getName() + ") ";
			i++;
		}
		return str;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void printBag() {
		if (getLabelBagIntentions().size() != getLabelBagEvalLabels().size()) {
			System.out.println("Size error when printing label bag");
			return;
		}
		int i = 0;
		for (Intention intn : getLabelBagIntentions()) {
			System.out.print("(" + intn.getName() + ", " + getLabelBagEvalLabels().get(i).getName() + "), ");
			System.out.println("");
			i++;
		}
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int addToLabelBag(Intention i, EvaluationLabel label) {
		int intentionsSize = getLabelBagIntentions().size();
		int labelsSize = getLabelBagEvalLabels().size();
		if (intentionsSize != labelsSize) {
			System.out.println("Problem with list sizes in addToLabelBag.  intentions: " + intentionsSize + " labels: " + labelsSize);
			return -1;
		}
		
		//currently the intentions EList doesn't support duplicates.  I've tried playing with the unique setting for
		//the list and it doesn't make a difference, annoying.
		int index = getLabelBagIntentions().indexOf(i);
		if (index >= 0) {
			//replace existing label
			getLabelBagEvalLabels().remove(index);
			getLabelBagEvalLabels().add(index, label);
		}
		else {
			getLabelBagIntentions().add(i);
			getLabelBagEvalLabels().add(label);
		}
			
		
		setToResolve(true);
		assessBag();
		
		return 1;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int getBagSize() {
		if (getLabelBagIntentions().size() != getLabelBagEvalLabels().size()) {
			System.out.println("Size error when getting bag size");
			return -1;
		}
		return getLabelBagIntentions().size();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * Test for equality without caring about order.
	 * I can't use the default vector equals or index of because it only checks for object equality 
	 * and doesn't seem to call my overridden equals function for IntentionLabelPair.
	 * So I'm doing this the ugly way, annoying
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean equals(LabelBag other) {
		if (getLabelBagIntentions().size() != other.getLabelBagIntentions().size())
			return false;
		
		boolean found = false;
		int i = 0;
		int j = 0;
		for (Intention intn: getLabelBagIntentions())	{
			found = false;
			j = 0;
			for (Intention intn2: other.getLabelBagIntentions()) {
				//Eval label equal comparison can be done by ==, as it's an enumerable, represented by a #
				if (intn.equals(intn2) && getLabelBagEvalLabels().get(i) == other.getLabelBagEvalLabels().get(j))
					found = true;
				j++;
			}
			if (!found)
				return false;					
			i++;
		}
		
		return true;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public LabelBag bagDiff(LabelBag bag) {
		
		boolean found = false;
		LabelBagImpl newlb = new LabelBagImpl();
		int i = 0;
		int j = 0;
		
		for (Intention intn: bag.getLabelBagIntentions())	{
			found = false;
			j = 0;
			for (Intention intn2: getLabelBagIntentions()) {
				if (intn.equals(intn2) && bag.getLabelBagEvalLabels().get(i).equals(getLabelBagEvalLabels().get(j)))
					found = true;
				j++;
			}
			if (!found) {
				newlb.addToLabelBag(intn, bag.getLabelBagEvalLabels().get(i));				
			}
			i++;
		}
		//and again...
		i = 0;
		j = 0;
		for (Intention intn: getLabelBagIntentions())	{
			found = false;
			j = 0;
			for (Intention intn2: bag.getLabelBagIntentions()) {
				if (intn.equals(intn2) && bag.getLabelBagEvalLabels().get(j).equals(getLabelBagEvalLabels().get(i)))
					found = true;
				j++;
			}
			if (!found) {
				newlb.addToLabelBag(intn, getLabelBagEvalLabels().get(i));
			}
			i++;
		}
		
		return newlb;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Object[] toArray() {
		if (getLabelBagIntentions().size() != getLabelBagIntentions().size()) {
			System.out.println("Error in LabelBag toArray, lists not equal size");
			return null;
		}
		Object [] array = new Object[getLabelBagIntentions().size()];
		for (int i = 0; i < getLabelBagIntentions().size(); i++) {
			Vector<Object> v = new Vector<Object>();
			v.add(getLabelBagIntentions().get(i));
			v.add(getLabelBagEvalLabels().get(i));
			array[i] = (Object) v;
		}
		
		return array;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int size() {
		if (getLabelBagIntentions().size() != getLabelBagIntentions().size()) {
			System.out.println("Error in LabelBag size(), lists not equal size");
			return -1;
		}
		return getLabelBagIntentions().size();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean removeFromLabelBag(Intention intn, EvaluationLabel label) {
		boolean a = getLabelBagIntentions().remove(intn);
		boolean b = getLabelBagEvalLabels().remove(label);
		
		if (a && b)
			return true;
		
		return false;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	private void assessBag() {
						
		setAllPositive(true);
		setAllNegative(true);
		setHasFullPositive(false);
		setHasFullNegative(false);
		boolean hasConflict = false;
		boolean hasUnknown = false;
		//setHasConflict(false);
		//setHasUnknown(false);
		//setAllConflict(true);
		//setAllUnknown(true);
		boolean allConflict = true;
		boolean allUnknown = true;
			
		for (EvaluationLabel label: getLabelBagEvalLabels()) {
						
			if (label == EvaluationLabel.SATISFIED) {
				setHasFullPositive(true);
				setAllNegative(false);
				allConflict = false;
				allUnknown = false;			
			}
			if (label == EvaluationLabel.PARTIALLY_SATISFIED)  {
				setAllNegative(false);
				allConflict = false;
				allUnknown = false;	
			}
			if (label == EvaluationLabel.CONFLICT) {
				hasConflict = true;
				setHasConflict(true);
				setAllNegative(false);
				setAllPositive(false);
				allUnknown = false;	
			}				
		    if (label == EvaluationLabel.UNKNOWN) {
		    	hasUnknown = true;
		    	setHasUnknown(true);
				setAllNegative(false);
				setAllPositive(false);
				allConflict = false;
			}
			if (label == EvaluationLabel.PARTIALLY_DENIED) {
				setAllPositive(false);
				allConflict = false;
				allUnknown = false;	
			}
			if (label == EvaluationLabel.DENIED) {
				setHasFullNegative(true);
				setAllPositive(false);
				allConflict = false;
				allUnknown = false;	
			}
		}
		if (hasConflict && allConflict)
			setAllConflict(true);
		else setAllConflict(false);
		if (hasUnknown && allUnknown)
			setAllUnknown(true);
		else setAllUnknown(false);
		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void clearLabelBag() {
		if (getLabelBagEvalLabels() != null)
			getLabelBagEvalLabels().removeAll(getLabelBagEvalLabels());
		if (getLabelBagIntentions() != null)
			getLabelBagIntentions().removeAll(getLabelBagIntentions());
		needResolve();
	}
	
	
	

} //LabelBagImpl

/**
 * Copyright 2001-2008 University of Toronto
 *
 * $Id$
 */
package edu.toronto.cs.openome_model.impl;

import edu.toronto.cs.openome_model.Contribution;
import edu.toronto.cs.openome_model.GoalModelingContributionSymmetry;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Model;
import edu.toronto.cs.openome_model.openome_modelPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Contribution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.toronto.cs.openome_model.impl.ContributionImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.ContributionImpl#getModel <em>Model</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.ContributionImpl#getSource <em>Source</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.ContributionImpl#getGoal_model_symmetry <em>Goal model symmetry</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContributionImpl extends LinkImpl implements Contribution {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright 2001-2008 University of Toronto";

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected Intention target;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected Intention source;

	/**
	 * The default value of the '{@link #getGoal_model_symmetry() <em>Goal model symmetry</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGoal_model_symmetry()
	 * @generated
	 * @ordered
	 */
	protected static final GoalModelingContributionSymmetry GOAL_MODEL_SYMMETRY_EDEFAULT = GoalModelingContributionSymmetry.BOTH;

	/**
	 * The cached value of the '{@link #getGoal_model_symmetry() <em>Goal model symmetry</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGoal_model_symmetry()
	 * @generated
	 * @ordered
	 */
	protected GoalModelingContributionSymmetry goal_model_symmetry = GOAL_MODEL_SYMMETRY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContributionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return openome_modelPackage.Literals.CONTRIBUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Intention getTarget() {
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject)target;
			target = (Intention)eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, openome_modelPackage.CONTRIBUTION__TARGET, oldTarget, target));
			}
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Intention basicGetTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTarget(Intention newTarget, NotificationChain msgs) {
		Intention oldTarget = target;
		target = newTarget;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, openome_modelPackage.CONTRIBUTION__TARGET, oldTarget, newTarget);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(Intention newTarget) {
		if (newTarget != target) {
			NotificationChain msgs = null;
			if (target != null)
				msgs = ((InternalEObject)target).eInverseRemove(this, openome_modelPackage.INTENTION__CONTRIBUTES_FROM, Intention.class, msgs);
			if (newTarget != null)
				msgs = ((InternalEObject)newTarget).eInverseAdd(this, openome_modelPackage.INTENTION__CONTRIBUTES_FROM, Intention.class, msgs);
			msgs = basicSetTarget(newTarget, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.CONTRIBUTION__TARGET, newTarget, newTarget));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model getModel() {
		if (eContainerFeatureID() != openome_modelPackage.CONTRIBUTION__MODEL) return null;
		return (Model)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModel(Model newModel, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newModel, openome_modelPackage.CONTRIBUTION__MODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModel(Model newModel) {
		if (newModel != eInternalContainer() || (eContainerFeatureID() != openome_modelPackage.CONTRIBUTION__MODEL && newModel != null)) {
			if (EcoreUtil.isAncestor(this, newModel))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newModel != null)
				msgs = ((InternalEObject)newModel).eInverseAdd(this, openome_modelPackage.MODEL__CONTRIBUTIONS, Model.class, msgs);
			msgs = basicSetModel(newModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.CONTRIBUTION__MODEL, newModel, newModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Intention getSource() {
		if (source != null && source.eIsProxy()) {
			InternalEObject oldSource = (InternalEObject)source;
			source = (Intention)eResolveProxy(oldSource);
			if (source != oldSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, openome_modelPackage.CONTRIBUTION__SOURCE, oldSource, source));
			}
		}
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Intention basicGetSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSource(Intention newSource, NotificationChain msgs) {
		Intention oldSource = source;
		source = newSource;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, openome_modelPackage.CONTRIBUTION__SOURCE, oldSource, newSource);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(Intention newSource) {
		if (newSource != source) {
			NotificationChain msgs = null;
			if (source != null)
				msgs = ((InternalEObject)source).eInverseRemove(this, openome_modelPackage.INTENTION__CONTRIBUTES_TO, Intention.class, msgs);
			if (newSource != null)
				msgs = ((InternalEObject)newSource).eInverseAdd(this, openome_modelPackage.INTENTION__CONTRIBUTES_TO, Intention.class, msgs);
			msgs = basicSetSource(newSource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.CONTRIBUTION__SOURCE, newSource, newSource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GoalModelingContributionSymmetry getGoal_model_symmetry() {
		return goal_model_symmetry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGoal_model_symmetry(GoalModelingContributionSymmetry newGoal_model_symmetry) {
		GoalModelingContributionSymmetry oldGoal_model_symmetry = goal_model_symmetry;
		goal_model_symmetry = newGoal_model_symmetry == null ? GOAL_MODEL_SYMMETRY_EDEFAULT : newGoal_model_symmetry;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.CONTRIBUTION__GOAL_MODEL_SYMMETRY, oldGoal_model_symmetry, goal_model_symmetry));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case openome_modelPackage.CONTRIBUTION__TARGET:
				if (target != null)
					msgs = ((InternalEObject)target).eInverseRemove(this, openome_modelPackage.INTENTION__CONTRIBUTES_FROM, Intention.class, msgs);
				return basicSetTarget((Intention)otherEnd, msgs);
			case openome_modelPackage.CONTRIBUTION__MODEL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetModel((Model)otherEnd, msgs);
			case openome_modelPackage.CONTRIBUTION__SOURCE:
				if (source != null)
					msgs = ((InternalEObject)source).eInverseRemove(this, openome_modelPackage.INTENTION__CONTRIBUTES_TO, Intention.class, msgs);
				return basicSetSource((Intention)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case openome_modelPackage.CONTRIBUTION__TARGET:
				return basicSetTarget(null, msgs);
			case openome_modelPackage.CONTRIBUTION__MODEL:
				return basicSetModel(null, msgs);
			case openome_modelPackage.CONTRIBUTION__SOURCE:
				return basicSetSource(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case openome_modelPackage.CONTRIBUTION__MODEL:
				return eInternalContainer().eInverseRemove(this, openome_modelPackage.MODEL__CONTRIBUTIONS, Model.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case openome_modelPackage.CONTRIBUTION__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
			case openome_modelPackage.CONTRIBUTION__MODEL:
				return getModel();
			case openome_modelPackage.CONTRIBUTION__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
			case openome_modelPackage.CONTRIBUTION__GOAL_MODEL_SYMMETRY:
				return getGoal_model_symmetry();
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
			case openome_modelPackage.CONTRIBUTION__TARGET:
				setTarget((Intention)newValue);
				return;
			case openome_modelPackage.CONTRIBUTION__MODEL:
				setModel((Model)newValue);
				return;
			case openome_modelPackage.CONTRIBUTION__SOURCE:
				setSource((Intention)newValue);
				return;
			case openome_modelPackage.CONTRIBUTION__GOAL_MODEL_SYMMETRY:
				setGoal_model_symmetry((GoalModelingContributionSymmetry)newValue);
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
			case openome_modelPackage.CONTRIBUTION__TARGET:
				setTarget((Intention)null);
				return;
			case openome_modelPackage.CONTRIBUTION__MODEL:
				setModel((Model)null);
				return;
			case openome_modelPackage.CONTRIBUTION__SOURCE:
				setSource((Intention)null);
				return;
			case openome_modelPackage.CONTRIBUTION__GOAL_MODEL_SYMMETRY:
				setGoal_model_symmetry(GOAL_MODEL_SYMMETRY_EDEFAULT);
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
			case openome_modelPackage.CONTRIBUTION__TARGET:
				return target != null;
			case openome_modelPackage.CONTRIBUTION__MODEL:
				return getModel() != null;
			case openome_modelPackage.CONTRIBUTION__SOURCE:
				return source != null;
			case openome_modelPackage.CONTRIBUTION__GOAL_MODEL_SYMMETRY:
				return goal_model_symmetry != GOAL_MODEL_SYMMETRY_EDEFAULT;
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
		result.append(" (goal_model_symmetry: ");
		result.append(goal_model_symmetry);
		result.append(')');
		return result.toString();
	}

	/**
	 * @generated NOT
	 */
	public String getContributionType() {
	
		return "Contribution";
	}

} //ContributionImpl

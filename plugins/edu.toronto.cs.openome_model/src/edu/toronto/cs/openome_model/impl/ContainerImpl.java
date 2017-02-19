/**
 * Copyright 2001-2008 University of Toronto
 *
 * $Id$
 */
package edu.toronto.cs.openome_model.impl;

import edu.toronto.cs.openome_model.Actor;
import edu.toronto.cs.openome_model.Association;
import edu.toronto.cs.openome_model.Container;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Model;
import edu.toronto.cs.openome_model.openome_modelPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.toronto.cs.openome_model.impl.ContainerImpl#getName <em>Name</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.ContainerImpl#getSub <em>Sub</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.ContainerImpl#getIntentions <em>Intentions</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.ContainerImpl#getModel <em>Model</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.ContainerImpl#getAssociationTo <em>Association To</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.ContainerImpl#getAssociationFrom <em>Association From</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ContainerImpl extends DependableImpl implements Container {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright 2001-2008 University of Toronto";

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSub() <em>Sub</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSub()
	 * @generated
	 * @ordered
	 */
	protected EList<Actor> sub;

	/**
	 * The cached value of the '{@link #getIntentions() <em>Intentions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntentions()
	 * @generated
	 * @ordered
	 */
	protected EList<Intention> intentions;

	/**
	 * The cached value of the '{@link #getAssociationTo() <em>Association To</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociationTo()
	 * @generated
	 * @ordered
	 */
	protected EList<Association> associationTo;

	/**
	 * The cached value of the '{@link #getAssociationFrom() <em>Association From</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociationFrom()
	 * @generated
	 * @ordered
	 */
	protected EList<Association> associationFrom;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return openome_modelPackage.Literals.CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.CONTAINER__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Actor> getSub() {
		if (sub == null) {
			sub = new EObjectContainmentEList<Actor>(Actor.class, this, openome_modelPackage.CONTAINER__SUB);
		}
		return sub;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Intention> getIntentions() {
		if (intentions == null) {
			intentions = new EObjectContainmentWithInverseEList<Intention>(Intention.class, this, openome_modelPackage.CONTAINER__INTENTIONS, openome_modelPackage.INTENTION__CONTAINER);
		}
		return intentions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model getModel() {
		if (eContainerFeatureID() != openome_modelPackage.CONTAINER__MODEL) return null;
		return (Model)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModel(Model newModel, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newModel, openome_modelPackage.CONTAINER__MODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModel(Model newModel) {
		if (newModel != eInternalContainer() || (eContainerFeatureID() != openome_modelPackage.CONTAINER__MODEL && newModel != null)) {
			if (EcoreUtil.isAncestor(this, newModel))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newModel != null)
				msgs = ((InternalEObject)newModel).eInverseAdd(this, openome_modelPackage.MODEL__CONTAINERS, Model.class, msgs);
			msgs = basicSetModel(newModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.CONTAINER__MODEL, newModel, newModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Association> getAssociationTo() {
		if (associationTo == null) {
			associationTo = new EObjectResolvingEList<Association>(Association.class, this, openome_modelPackage.CONTAINER__ASSOCIATION_TO);
		}
		return associationTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Association> getAssociationFrom() {
		if (associationFrom == null) {
			associationFrom = new EObjectResolvingEList<Association>(Association.class, this, openome_modelPackage.CONTAINER__ASSOCIATION_FROM);
		}
		return associationFrom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case openome_modelPackage.CONTAINER__INTENTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIntentions()).basicAdd(otherEnd, msgs);
			case openome_modelPackage.CONTAINER__MODEL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetModel((Model)otherEnd, msgs);
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
			case openome_modelPackage.CONTAINER__SUB:
				return ((InternalEList<?>)getSub()).basicRemove(otherEnd, msgs);
			case openome_modelPackage.CONTAINER__INTENTIONS:
				return ((InternalEList<?>)getIntentions()).basicRemove(otherEnd, msgs);
			case openome_modelPackage.CONTAINER__MODEL:
				return basicSetModel(null, msgs);
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
			case openome_modelPackage.CONTAINER__MODEL:
				return eInternalContainer().eInverseRemove(this, openome_modelPackage.MODEL__CONTAINERS, Model.class, msgs);
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
			case openome_modelPackage.CONTAINER__NAME:
				return getName();
			case openome_modelPackage.CONTAINER__SUB:
				return getSub();
			case openome_modelPackage.CONTAINER__INTENTIONS:
				return getIntentions();
			case openome_modelPackage.CONTAINER__MODEL:
				return getModel();
			case openome_modelPackage.CONTAINER__ASSOCIATION_TO:
				return getAssociationTo();
			case openome_modelPackage.CONTAINER__ASSOCIATION_FROM:
				return getAssociationFrom();
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
			case openome_modelPackage.CONTAINER__NAME:
				setName((String)newValue);
				return;
			case openome_modelPackage.CONTAINER__SUB:
				getSub().clear();
				getSub().addAll((Collection<? extends Actor>)newValue);
				return;
			case openome_modelPackage.CONTAINER__INTENTIONS:
				getIntentions().clear();
				getIntentions().addAll((Collection<? extends Intention>)newValue);
				return;
			case openome_modelPackage.CONTAINER__MODEL:
				setModel((Model)newValue);
				return;
			case openome_modelPackage.CONTAINER__ASSOCIATION_TO:
				getAssociationTo().clear();
				getAssociationTo().addAll((Collection<? extends Association>)newValue);
				return;
			case openome_modelPackage.CONTAINER__ASSOCIATION_FROM:
				getAssociationFrom().clear();
				getAssociationFrom().addAll((Collection<? extends Association>)newValue);
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
			case openome_modelPackage.CONTAINER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case openome_modelPackage.CONTAINER__SUB:
				getSub().clear();
				return;
			case openome_modelPackage.CONTAINER__INTENTIONS:
				getIntentions().clear();
				return;
			case openome_modelPackage.CONTAINER__MODEL:
				setModel((Model)null);
				return;
			case openome_modelPackage.CONTAINER__ASSOCIATION_TO:
				getAssociationTo().clear();
				return;
			case openome_modelPackage.CONTAINER__ASSOCIATION_FROM:
				getAssociationFrom().clear();
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
			case openome_modelPackage.CONTAINER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case openome_modelPackage.CONTAINER__SUB:
				return sub != null && !sub.isEmpty();
			case openome_modelPackage.CONTAINER__INTENTIONS:
				return intentions != null && !intentions.isEmpty();
			case openome_modelPackage.CONTAINER__MODEL:
				return getModel() != null;
			case openome_modelPackage.CONTAINER__ASSOCIATION_TO:
				return associationTo != null && !associationTo.isEmpty();
			case openome_modelPackage.CONTAINER__ASSOCIATION_FROM:
				return associationFrom != null && !associationFrom.isEmpty();
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
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //ContainerImpl

/**
 * Copyright 2001-2008 University of Toronto
 *
 * $Id$
 */
package edu.toronto.cs.openome_model.impl;

import edu.toronto.cs.openome_model.Agent;
import edu.toronto.cs.openome_model.Position;
import edu.toronto.cs.openome_model.Role;
import edu.toronto.cs.openome_model.openome_modelPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Agent</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.toronto.cs.openome_model.impl.AgentImpl#getPlays <em>Plays</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.AgentImpl#getOccupies <em>Occupies</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.AgentImpl#getIns <em>Ins</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AgentImpl extends ContainerImpl implements Agent {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright 2001-2008 University of Toronto";

	/**
	 * The cached value of the '{@link #getPlays() <em>Plays</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPlays()
	 * @generated
	 * @ordered
	 */
	protected EList<Role> plays;

	/**
	 * The cached value of the '{@link #getOccupies() <em>Occupies</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOccupies()
	 * @generated
	 * @ordered
	 */
	protected EList<Position> occupies;

	/**
	 * The cached value of the '{@link #getIns() <em>Ins</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIns()
	 * @generated
	 * @ordered
	 */
	protected Agent ins;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AgentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return openome_modelPackage.Literals.AGENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Role> getPlays() {
		if (plays == null) {
			plays = new EObjectResolvingEList<Role>(Role.class, this, openome_modelPackage.AGENT__PLAYS);
		}
		return plays;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Position> getOccupies() {
		if (occupies == null) {
			occupies = new EObjectResolvingEList<Position>(Position.class, this, openome_modelPackage.AGENT__OCCUPIES);
		}
		return occupies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Agent getIns() {
		if (ins != null && ins.eIsProxy()) {
			InternalEObject oldIns = (InternalEObject)ins;
			ins = (Agent)eResolveProxy(oldIns);
			if (ins != oldIns) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, openome_modelPackage.AGENT__INS, oldIns, ins));
			}
		}
		return ins;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Agent basicGetIns() {
		return ins;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIns(Agent newIns) {
		Agent oldIns = ins;
		ins = newIns;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.AGENT__INS, oldIns, ins));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case openome_modelPackage.AGENT__PLAYS:
				return getPlays();
			case openome_modelPackage.AGENT__OCCUPIES:
				return getOccupies();
			case openome_modelPackage.AGENT__INS:
				if (resolve) return getIns();
				return basicGetIns();
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
			case openome_modelPackage.AGENT__PLAYS:
				getPlays().clear();
				getPlays().addAll((Collection<? extends Role>)newValue);
				return;
			case openome_modelPackage.AGENT__OCCUPIES:
				getOccupies().clear();
				getOccupies().addAll((Collection<? extends Position>)newValue);
				return;
			case openome_modelPackage.AGENT__INS:
				setIns((Agent)newValue);
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
			case openome_modelPackage.AGENT__PLAYS:
				getPlays().clear();
				return;
			case openome_modelPackage.AGENT__OCCUPIES:
				getOccupies().clear();
				return;
			case openome_modelPackage.AGENT__INS:
				setIns((Agent)null);
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
			case openome_modelPackage.AGENT__PLAYS:
				return plays != null && !plays.isEmpty();
			case openome_modelPackage.AGENT__OCCUPIES:
				return occupies != null && !occupies.isEmpty();
			case openome_modelPackage.AGENT__INS:
				return ins != null;
		}
		return super.eIsSet(featureID);
	}

} //AgentImpl

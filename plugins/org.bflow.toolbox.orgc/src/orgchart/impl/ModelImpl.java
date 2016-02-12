/**
 */
package orgchart.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgchart.ExternalPerson;
import orgchart.Group;
import orgchart.InternalPerson;
import orgchart.Location;
import orgchart.Model;
import orgchart.OrgchartPackage;
import orgchart.Participant;
import orgchart.PersonType;
import orgchart.Position;
import orgchart.Relation1;
import orgchart.Relation2;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgchart.impl.ModelImpl#getParticipants <em>Participants</em>}</li>
 *   <li>{@link orgchart.impl.ModelImpl#getPositions <em>Positions</em>}</li>
 *   <li>{@link orgchart.impl.ModelImpl#getInternalPersons <em>Internal Persons</em>}</li>
 *   <li>{@link orgchart.impl.ModelImpl#getExternalPersons <em>External Persons</em>}</li>
 *   <li>{@link orgchart.impl.ModelImpl#getPersonTypes <em>Person Types</em>}</li>
 *   <li>{@link orgchart.impl.ModelImpl#getLocations <em>Locations</em>}</li>
 *   <li>{@link orgchart.impl.ModelImpl#getGroups <em>Groups</em>}</li>
 *   <li>{@link orgchart.impl.ModelImpl#getRelations1 <em>Relations1</em>}</li>
 *   <li>{@link orgchart.impl.ModelImpl#getRelations2 <em>Relations2</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelImpl extends MinimalEObjectImpl.Container implements Model {
	/**
	 * The cached value of the '{@link #getParticipants() <em>Participants</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParticipants()
	 * @generated
	 * @ordered
	 */
	protected EList<Participant> participants;

	/**
	 * The cached value of the '{@link #getPositions() <em>Positions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPositions()
	 * @generated
	 * @ordered
	 */
	protected EList<Position> positions;

	/**
	 * The cached value of the '{@link #getInternalPersons() <em>Internal Persons</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInternalPersons()
	 * @generated
	 * @ordered
	 */
	protected EList<InternalPerson> internalPersons;

	/**
	 * The cached value of the '{@link #getExternalPersons() <em>External Persons</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalPersons()
	 * @generated
	 * @ordered
	 */
	protected EList<ExternalPerson> externalPersons;

	/**
	 * The cached value of the '{@link #getPersonTypes() <em>Person Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersonTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<PersonType> personTypes;

	/**
	 * The cached value of the '{@link #getLocations() <em>Locations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocations()
	 * @generated
	 * @ordered
	 */
	protected EList<Location> locations;

	/**
	 * The cached value of the '{@link #getGroups() <em>Groups</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroups()
	 * @generated
	 * @ordered
	 */
	protected EList<Group> groups;

	/**
	 * The cached value of the '{@link #getRelations1() <em>Relations1</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelations1()
	 * @generated
	 * @ordered
	 */
	protected EList<Relation1> relations1;

	/**
	 * The cached value of the '{@link #getRelations2() <em>Relations2</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelations2()
	 * @generated
	 * @ordered
	 */
	protected EList<Relation2> relations2;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OrgchartPackage.Literals.MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Participant> getParticipants() {
		if (participants == null) {
			participants = new EObjectContainmentEList<Participant>(Participant.class, this, OrgchartPackage.MODEL__PARTICIPANTS);
		}
		return participants;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Position> getPositions() {
		if (positions == null) {
			positions = new EObjectContainmentEList<Position>(Position.class, this, OrgchartPackage.MODEL__POSITIONS);
		}
		return positions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InternalPerson> getInternalPersons() {
		if (internalPersons == null) {
			internalPersons = new EObjectContainmentEList<InternalPerson>(InternalPerson.class, this, OrgchartPackage.MODEL__INTERNAL_PERSONS);
		}
		return internalPersons;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExternalPerson> getExternalPersons() {
		if (externalPersons == null) {
			externalPersons = new EObjectContainmentEList<ExternalPerson>(ExternalPerson.class, this, OrgchartPackage.MODEL__EXTERNAL_PERSONS);
		}
		return externalPersons;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PersonType> getPersonTypes() {
		if (personTypes == null) {
			personTypes = new EObjectContainmentEList<PersonType>(PersonType.class, this, OrgchartPackage.MODEL__PERSON_TYPES);
		}
		return personTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Location> getLocations() {
		if (locations == null) {
			locations = new EObjectContainmentEList<Location>(Location.class, this, OrgchartPackage.MODEL__LOCATIONS);
		}
		return locations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Group> getGroups() {
		if (groups == null) {
			groups = new EObjectContainmentEList<Group>(Group.class, this, OrgchartPackage.MODEL__GROUPS);
		}
		return groups;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Relation1> getRelations1() {
		if (relations1 == null) {
			relations1 = new EObjectContainmentEList<Relation1>(Relation1.class, this, OrgchartPackage.MODEL__RELATIONS1);
		}
		return relations1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Relation2> getRelations2() {
		if (relations2 == null) {
			relations2 = new EObjectContainmentEList<Relation2>(Relation2.class, this, OrgchartPackage.MODEL__RELATIONS2);
		}
		return relations2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OrgchartPackage.MODEL__PARTICIPANTS:
				return ((InternalEList<?>)getParticipants()).basicRemove(otherEnd, msgs);
			case OrgchartPackage.MODEL__POSITIONS:
				return ((InternalEList<?>)getPositions()).basicRemove(otherEnd, msgs);
			case OrgchartPackage.MODEL__INTERNAL_PERSONS:
				return ((InternalEList<?>)getInternalPersons()).basicRemove(otherEnd, msgs);
			case OrgchartPackage.MODEL__EXTERNAL_PERSONS:
				return ((InternalEList<?>)getExternalPersons()).basicRemove(otherEnd, msgs);
			case OrgchartPackage.MODEL__PERSON_TYPES:
				return ((InternalEList<?>)getPersonTypes()).basicRemove(otherEnd, msgs);
			case OrgchartPackage.MODEL__LOCATIONS:
				return ((InternalEList<?>)getLocations()).basicRemove(otherEnd, msgs);
			case OrgchartPackage.MODEL__GROUPS:
				return ((InternalEList<?>)getGroups()).basicRemove(otherEnd, msgs);
			case OrgchartPackage.MODEL__RELATIONS1:
				return ((InternalEList<?>)getRelations1()).basicRemove(otherEnd, msgs);
			case OrgchartPackage.MODEL__RELATIONS2:
				return ((InternalEList<?>)getRelations2()).basicRemove(otherEnd, msgs);
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
			case OrgchartPackage.MODEL__PARTICIPANTS:
				return getParticipants();
			case OrgchartPackage.MODEL__POSITIONS:
				return getPositions();
			case OrgchartPackage.MODEL__INTERNAL_PERSONS:
				return getInternalPersons();
			case OrgchartPackage.MODEL__EXTERNAL_PERSONS:
				return getExternalPersons();
			case OrgchartPackage.MODEL__PERSON_TYPES:
				return getPersonTypes();
			case OrgchartPackage.MODEL__LOCATIONS:
				return getLocations();
			case OrgchartPackage.MODEL__GROUPS:
				return getGroups();
			case OrgchartPackage.MODEL__RELATIONS1:
				return getRelations1();
			case OrgchartPackage.MODEL__RELATIONS2:
				return getRelations2();
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
			case OrgchartPackage.MODEL__PARTICIPANTS:
				getParticipants().clear();
				getParticipants().addAll((Collection<? extends Participant>)newValue);
				return;
			case OrgchartPackage.MODEL__POSITIONS:
				getPositions().clear();
				getPositions().addAll((Collection<? extends Position>)newValue);
				return;
			case OrgchartPackage.MODEL__INTERNAL_PERSONS:
				getInternalPersons().clear();
				getInternalPersons().addAll((Collection<? extends InternalPerson>)newValue);
				return;
			case OrgchartPackage.MODEL__EXTERNAL_PERSONS:
				getExternalPersons().clear();
				getExternalPersons().addAll((Collection<? extends ExternalPerson>)newValue);
				return;
			case OrgchartPackage.MODEL__PERSON_TYPES:
				getPersonTypes().clear();
				getPersonTypes().addAll((Collection<? extends PersonType>)newValue);
				return;
			case OrgchartPackage.MODEL__LOCATIONS:
				getLocations().clear();
				getLocations().addAll((Collection<? extends Location>)newValue);
				return;
			case OrgchartPackage.MODEL__GROUPS:
				getGroups().clear();
				getGroups().addAll((Collection<? extends Group>)newValue);
				return;
			case OrgchartPackage.MODEL__RELATIONS1:
				getRelations1().clear();
				getRelations1().addAll((Collection<? extends Relation1>)newValue);
				return;
			case OrgchartPackage.MODEL__RELATIONS2:
				getRelations2().clear();
				getRelations2().addAll((Collection<? extends Relation2>)newValue);
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
			case OrgchartPackage.MODEL__PARTICIPANTS:
				getParticipants().clear();
				return;
			case OrgchartPackage.MODEL__POSITIONS:
				getPositions().clear();
				return;
			case OrgchartPackage.MODEL__INTERNAL_PERSONS:
				getInternalPersons().clear();
				return;
			case OrgchartPackage.MODEL__EXTERNAL_PERSONS:
				getExternalPersons().clear();
				return;
			case OrgchartPackage.MODEL__PERSON_TYPES:
				getPersonTypes().clear();
				return;
			case OrgchartPackage.MODEL__LOCATIONS:
				getLocations().clear();
				return;
			case OrgchartPackage.MODEL__GROUPS:
				getGroups().clear();
				return;
			case OrgchartPackage.MODEL__RELATIONS1:
				getRelations1().clear();
				return;
			case OrgchartPackage.MODEL__RELATIONS2:
				getRelations2().clear();
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
			case OrgchartPackage.MODEL__PARTICIPANTS:
				return participants != null && !participants.isEmpty();
			case OrgchartPackage.MODEL__POSITIONS:
				return positions != null && !positions.isEmpty();
			case OrgchartPackage.MODEL__INTERNAL_PERSONS:
				return internalPersons != null && !internalPersons.isEmpty();
			case OrgchartPackage.MODEL__EXTERNAL_PERSONS:
				return externalPersons != null && !externalPersons.isEmpty();
			case OrgchartPackage.MODEL__PERSON_TYPES:
				return personTypes != null && !personTypes.isEmpty();
			case OrgchartPackage.MODEL__LOCATIONS:
				return locations != null && !locations.isEmpty();
			case OrgchartPackage.MODEL__GROUPS:
				return groups != null && !groups.isEmpty();
			case OrgchartPackage.MODEL__RELATIONS1:
				return relations1 != null && !relations1.isEmpty();
			case OrgchartPackage.MODEL__RELATIONS2:
				return relations2 != null && !relations2.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ModelImpl

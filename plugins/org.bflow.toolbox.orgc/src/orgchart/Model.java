/**
 */
package orgchart;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgchart.Model#getParticipants <em>Participants</em>}</li>
 *   <li>{@link orgchart.Model#getPositions <em>Positions</em>}</li>
 *   <li>{@link orgchart.Model#getInternalPersons <em>Internal Persons</em>}</li>
 *   <li>{@link orgchart.Model#getExternalPersons <em>External Persons</em>}</li>
 *   <li>{@link orgchart.Model#getPersonTypes <em>Person Types</em>}</li>
 *   <li>{@link orgchart.Model#getLocations <em>Locations</em>}</li>
 *   <li>{@link orgchart.Model#getGroups <em>Groups</em>}</li>
 *   <li>{@link orgchart.Model#getRelations1 <em>Relations1</em>}</li>
 *   <li>{@link orgchart.Model#getRelations2 <em>Relations2</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgchart.OrgchartPackage#getModel()
 * @model annotation="gmf.diagram onefile='true' diagram.extension='orgc'"
 * @generated
 */
public interface Model extends EObject {
	/**
	 * Returns the value of the '<em><b>Participants</b></em>' containment reference list.
	 * The list contents are of type {@link orgchart.Participant}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participants</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participants</em>' containment reference list.
	 * @see orgchart.OrgchartPackage#getModel_Participants()
	 * @model containment="true"
	 * @generated
	 */
	EList<Participant> getParticipants();

	/**
	 * Returns the value of the '<em><b>Positions</b></em>' containment reference list.
	 * The list contents are of type {@link orgchart.Position}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Positions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Positions</em>' containment reference list.
	 * @see orgchart.OrgchartPackage#getModel_Positions()
	 * @model containment="true"
	 * @generated
	 */
	EList<Position> getPositions();

	/**
	 * Returns the value of the '<em><b>Internal Persons</b></em>' containment reference list.
	 * The list contents are of type {@link orgchart.InternalPerson}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Internal Persons</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Internal Persons</em>' containment reference list.
	 * @see orgchart.OrgchartPackage#getModel_InternalPersons()
	 * @model containment="true"
	 * @generated
	 */
	EList<InternalPerson> getInternalPersons();

	/**
	 * Returns the value of the '<em><b>External Persons</b></em>' containment reference list.
	 * The list contents are of type {@link orgchart.ExternalPerson}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External Persons</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External Persons</em>' containment reference list.
	 * @see orgchart.OrgchartPackage#getModel_ExternalPersons()
	 * @model containment="true"
	 * @generated
	 */
	EList<ExternalPerson> getExternalPersons();

	/**
	 * Returns the value of the '<em><b>Person Types</b></em>' containment reference list.
	 * The list contents are of type {@link orgchart.PersonType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Person Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Person Types</em>' containment reference list.
	 * @see orgchart.OrgchartPackage#getModel_PersonTypes()
	 * @model containment="true"
	 * @generated
	 */
	EList<PersonType> getPersonTypes();

	/**
	 * Returns the value of the '<em><b>Locations</b></em>' containment reference list.
	 * The list contents are of type {@link orgchart.Location}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Locations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Locations</em>' containment reference list.
	 * @see orgchart.OrgchartPackage#getModel_Locations()
	 * @model containment="true"
	 * @generated
	 */
	EList<Location> getLocations();

	/**
	 * Returns the value of the '<em><b>Groups</b></em>' containment reference list.
	 * The list contents are of type {@link orgchart.Group}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Groups</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Groups</em>' containment reference list.
	 * @see orgchart.OrgchartPackage#getModel_Groups()
	 * @model containment="true"
	 * @generated
	 */
	EList<Group> getGroups();

	/**
	 * Returns the value of the '<em><b>Relations1</b></em>' containment reference list.
	 * The list contents are of type {@link orgchart.Relation1}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relations1</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relations1</em>' containment reference list.
	 * @see orgchart.OrgchartPackage#getModel_Relations1()
	 * @model containment="true"
	 * @generated
	 */
	EList<Relation1> getRelations1();

	/**
	 * Returns the value of the '<em><b>Relations2</b></em>' containment reference list.
	 * The list contents are of type {@link orgchart.Relation2}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relations2</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relations2</em>' containment reference list.
	 * @see orgchart.OrgchartPackage#getModel_Relations2()
	 * @model containment="true"
	 * @generated
	 */
	EList<Relation2> getRelations2();

} // Model

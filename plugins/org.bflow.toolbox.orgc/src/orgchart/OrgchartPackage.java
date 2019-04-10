/**
 */
package orgchart;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see orgchart.OrgchartFactory
 * @model kind="package"
 * @generated
 */
public interface OrgchartPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "orgchart";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "orgc";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "orgc";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OrgchartPackage eINSTANCE = orgchart.impl.OrgchartPackageImpl.init();

	/**
	 * The meta object id for the '{@link orgchart.impl.ModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgchart.impl.ModelImpl
	 * @see orgchart.impl.OrgchartPackageImpl#getModel()
	 * @generated
	 */
	int MODEL = 0;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__PARTICIPANTS = 0;

	/**
	 * The feature id for the '<em><b>Positions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__POSITIONS = 1;

	/**
	 * The feature id for the '<em><b>Internal Persons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__INTERNAL_PERSONS = 2;

	/**
	 * The feature id for the '<em><b>External Persons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__EXTERNAL_PERSONS = 3;

	/**
	 * The feature id for the '<em><b>Person Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__PERSON_TYPES = 4;

	/**
	 * The feature id for the '<em><b>Locations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__LOCATIONS = 5;

	/**
	 * The feature id for the '<em><b>Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__GROUPS = 6;

	/**
	 * The feature id for the '<em><b>Relations1</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__RELATIONS1 = 7;

	/**
	 * The feature id for the '<em><b>Relations2</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__RELATIONS2 = 8;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_FEATURE_COUNT = 9;

	/**
	 * The number of operations of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link orgchart.impl.NamedElementImpl <em>Named Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgchart.impl.NamedElementImpl
	 * @see orgchart.impl.OrgchartPackageImpl#getNamedElement()
	 * @generated
	 */
	int NAMED_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__NAME = 0;

	/**
	 * The number of structural features of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link orgchart.impl.AllImpl <em>All</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgchart.impl.AllImpl
	 * @see orgchart.impl.OrgchartPackageImpl#getAll()
	 * @generated
	 */
	int ALL = 2;

	/**
	 * The number of structural features of the '<em>All</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>All</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link orgchart.impl.All_Rel_1Impl <em>All Rel 1</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgchart.impl.All_Rel_1Impl
	 * @see orgchart.impl.OrgchartPackageImpl#getAll_Rel_1()
	 * @generated
	 */
	int ALL_REL_1 = 3;

	/**
	 * The number of structural features of the '<em>All Rel 1</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_REL_1_FEATURE_COUNT = ALL_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>All Rel 1</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_REL_1_OPERATION_COUNT = ALL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link orgchart.impl.All_Rel_2Impl <em>All Rel 2</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgchart.impl.All_Rel_2Impl
	 * @see orgchart.impl.OrgchartPackageImpl#getAll_Rel_2()
	 * @generated
	 */
	int ALL_REL_2 = 4;

	/**
	 * The number of structural features of the '<em>All Rel 2</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_REL_2_FEATURE_COUNT = ALL_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>All Rel 2</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALL_REL_2_OPERATION_COUNT = ALL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link orgchart.impl.PersonImpl <em>Person</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgchart.impl.PersonImpl
	 * @see orgchart.impl.OrgchartPackageImpl#getPerson()
	 * @generated
	 */
	int PERSON = 5;

	/**
	 * The number of structural features of the '<em>Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_FEATURE_COUNT = ALL_REL_2_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_OPERATION_COUNT = ALL_REL_2_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link orgchart.impl.ParticipantImpl <em>Participant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgchart.impl.ParticipantImpl
	 * @see orgchart.impl.OrgchartPackageImpl#getParticipant()
	 * @generated
	 */
	int PARTICIPANT = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__NAME = NAMED_ELEMENT__NAME;
	
	/**
	 * @generated NOT
	 */
	int PARTICIPANT__SUBDIAGRAM = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link orgchart.impl.LocationImpl <em>Location</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgchart.impl.LocationImpl
	 * @see orgchart.impl.OrgchartPackageImpl#getLocation()
	 * @generated
	 */
	int LOCATION = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__NAME = NAMED_ELEMENT__NAME;

	/**
	 * @generated NOT
	 */
	int LOCATION__SUBDIAGRAM = NAMED_ELEMENT_FEATURE_COUNT + 0;
	
	/**
	 * The number of structural features of the '<em>Location</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Location</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link orgchart.impl.GroupImpl <em>Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgchart.impl.GroupImpl
	 * @see orgchart.impl.OrgchartPackageImpl#getGroup()
	 * @generated
	 */
	int GROUP = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP__NAME = NAMED_ELEMENT__NAME;
	
	/** 
	 * @generated NOT 
	 */
	int GROUP__SUBDIAGRAM = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GROUP_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link orgchart.impl.PositionImpl <em>Position</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgchart.impl.PositionImpl
	 * @see orgchart.impl.OrgchartPackageImpl#getPosition()
	 * @generated
	 */
	int POSITION = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSITION__NAME = NAMED_ELEMENT__NAME;

	/**
	 * @generated NOT
	 */
	int POSITION__SUBDIAGRAM = NAMED_ELEMENT_FEATURE_COUNT + 0;
	
	/**
	 * The number of structural features of the '<em>Position</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSITION_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Position</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSITION_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link orgchart.impl.InternalPersonImpl <em>Internal Person</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgchart.impl.InternalPersonImpl
	 * @see orgchart.impl.OrgchartPackageImpl#getInternalPerson()
	 * @generated
	 */
	int INTERNAL_PERSON = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_PERSON__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The number of structural features of the '<em>Internal Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_PERSON_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Internal Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_PERSON_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link orgchart.impl.ExternalPersonImpl <em>External Person</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgchart.impl.ExternalPersonImpl
	 * @see orgchart.impl.OrgchartPackageImpl#getExternalPerson()
	 * @generated
	 */
	int EXTERNAL_PERSON = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PERSON__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The number of structural features of the '<em>External Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PERSON_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>External Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_PERSON_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link orgchart.impl.PersonTypeImpl <em>Person Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgchart.impl.PersonTypeImpl
	 * @see orgchart.impl.OrgchartPackageImpl#getPersonType()
	 * @generated
	 */
	int PERSON_TYPE = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_TYPE__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The number of structural features of the '<em>Person Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_TYPE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Person Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_TYPE_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link orgchart.impl.Relation1Impl <em>Relation1</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgchart.impl.Relation1Impl
	 * @see orgchart.impl.OrgchartPackageImpl#getRelation1()
	 * @generated
	 */
	int RELATION1 = 13;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION1__SOURCE = 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION1__TARGET = 1;

	/**
	 * The number of structural features of the '<em>Relation1</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION1_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Relation1</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION1_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link orgchart.impl.Relation2Impl <em>Relation2</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgchart.impl.Relation2Impl
	 * @see orgchart.impl.OrgchartPackageImpl#getRelation2()
	 * @generated
	 */
	int RELATION2 = 14;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION2__SOURCE = 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION2__TARGET = 1;

	/**
	 * The number of structural features of the '<em>Relation2</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION2_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Relation2</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION2_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link orgchart.Model <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see orgchart.Model
	 * @generated
	 */
	EClass getModel();

	/**
	 * Returns the meta object for the containment reference list '{@link orgchart.Model#getParticipants <em>Participants</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Participants</em>'.
	 * @see orgchart.Model#getParticipants()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Participants();

	/**
	 * Returns the meta object for the containment reference list '{@link orgchart.Model#getPositions <em>Positions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Positions</em>'.
	 * @see orgchart.Model#getPositions()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Positions();

	/**
	 * Returns the meta object for the containment reference list '{@link orgchart.Model#getInternalPersons <em>Internal Persons</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Internal Persons</em>'.
	 * @see orgchart.Model#getInternalPersons()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_InternalPersons();

	/**
	 * Returns the meta object for the containment reference list '{@link orgchart.Model#getExternalPersons <em>External Persons</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>External Persons</em>'.
	 * @see orgchart.Model#getExternalPersons()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_ExternalPersons();

	/**
	 * Returns the meta object for the containment reference list '{@link orgchart.Model#getPersonTypes <em>Person Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Person Types</em>'.
	 * @see orgchart.Model#getPersonTypes()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_PersonTypes();

	/**
	 * Returns the meta object for the containment reference list '{@link orgchart.Model#getLocations <em>Locations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Locations</em>'.
	 * @see orgchart.Model#getLocations()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Locations();

	/**
	 * Returns the meta object for the containment reference list '{@link orgchart.Model#getGroups <em>Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Groups</em>'.
	 * @see orgchart.Model#getGroups()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Groups();

	/**
	 * Returns the meta object for the containment reference list '{@link orgchart.Model#getRelations1 <em>Relations1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Relations1</em>'.
	 * @see orgchart.Model#getRelations1()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Relations1();

	/**
	 * Returns the meta object for the containment reference list '{@link orgchart.Model#getRelations2 <em>Relations2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Relations2</em>'.
	 * @see orgchart.Model#getRelations2()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Relations2();

	/**
	 * Returns the meta object for class '{@link orgchart.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Element</em>'.
	 * @see orgchart.NamedElement
	 * @generated
	 */
	EClass getNamedElement();

	/**
	 * Returns the meta object for the attribute '{@link orgchart.NamedElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see orgchart.NamedElement#getName()
	 * @see #getNamedElement()
	 * @generated
	 */
	EAttribute getNamedElement_Name();

	/**
	 * Returns the meta object for class '{@link orgchart.All <em>All</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>All</em>'.
	 * @see orgchart.All
	 * @generated
	 */
	EClass getAll();

	/**
	 * Returns the meta object for class '{@link orgchart.All_Rel_1 <em>All Rel 1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>All Rel 1</em>'.
	 * @see orgchart.All_Rel_1
	 * @generated
	 */
	EClass getAll_Rel_1();

	/**
	 * Returns the meta object for class '{@link orgchart.All_Rel_2 <em>All Rel 2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>All Rel 2</em>'.
	 * @see orgchart.All_Rel_2
	 * @generated
	 */
	EClass getAll_Rel_2();

	/**
	 * Returns the meta object for class '{@link orgchart.Person <em>Person</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Person</em>'.
	 * @see orgchart.Person
	 * @generated
	 */
	EClass getPerson();

	/**
	 * Returns the meta object for class '{@link orgchart.Participant <em>Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Participant</em>'.
	 * @see orgchart.Participant
	 * @generated
	 */
	EClass getParticipant();
	
	/**
	 * @generated NOT
	 */
	EAttribute getParticipant_Subdiagram();

	/**
	 * Returns the meta object for class '{@link orgchart.Location <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Location</em>'.
	 * @see orgchart.Location
	 * @generated
	 */
	EClass getLocation();
	
	/**
	 * @generated NOT
	 */
	EAttribute getLocation_Subdiagram();

	/**
	 * Returns the meta object for class '{@link orgchart.Group <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Group</em>'.
	 * @see orgchart.Group
	 * @generated
	 */
	EClass getGroup();
	
	/**
	 * @generated NOT
	 */
	EAttribute getGroup_Subdiagram();

	/**
	 * Returns the meta object for class '{@link orgchart.Position <em>Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Position</em>'.
	 * @see orgchart.Position
	 * @generated
	 */
	EClass getPosition();
	
	/**
	 * @generated NOT
	 */
	EAttribute getPosition_Subdiagram();

	/**
	 * Returns the meta object for class '{@link orgchart.InternalPerson <em>Internal Person</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Internal Person</em>'.
	 * @see orgchart.InternalPerson
	 * @generated
	 */
	EClass getInternalPerson();

	/**
	 * Returns the meta object for class '{@link orgchart.ExternalPerson <em>External Person</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>External Person</em>'.
	 * @see orgchart.ExternalPerson
	 * @generated
	 */
	EClass getExternalPerson();

	/**
	 * Returns the meta object for class '{@link orgchart.PersonType <em>Person Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Person Type</em>'.
	 * @see orgchart.PersonType
	 * @generated
	 */
	EClass getPersonType();

	/**
	 * Returns the meta object for class '{@link orgchart.Relation1 <em>Relation1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relation1</em>'.
	 * @see orgchart.Relation1
	 * @generated
	 */
	EClass getRelation1();

	/**
	 * Returns the meta object for the reference '{@link orgchart.Relation1#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see orgchart.Relation1#getSource()
	 * @see #getRelation1()
	 * @generated
	 */
	EReference getRelation1_Source();

	/**
	 * Returns the meta object for the reference '{@link orgchart.Relation1#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see orgchart.Relation1#getTarget()
	 * @see #getRelation1()
	 * @generated
	 */
	EReference getRelation1_Target();

	/**
	 * Returns the meta object for class '{@link orgchart.Relation2 <em>Relation2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relation2</em>'.
	 * @see orgchart.Relation2
	 * @generated
	 */
	EClass getRelation2();

	/**
	 * Returns the meta object for the reference '{@link orgchart.Relation2#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see orgchart.Relation2#getSource()
	 * @see #getRelation2()
	 * @generated
	 */
	EReference getRelation2_Source();

	/**
	 * Returns the meta object for the reference '{@link orgchart.Relation2#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see orgchart.Relation2#getTarget()
	 * @see #getRelation2()
	 * @generated
	 */
	EReference getRelation2_Target();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	OrgchartFactory getOrgchartFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link orgchart.impl.ModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgchart.impl.ModelImpl
		 * @see orgchart.impl.OrgchartPackageImpl#getModel()
		 * @generated
		 */
		EClass MODEL = eINSTANCE.getModel();

		/**
		 * The meta object literal for the '<em><b>Participants</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__PARTICIPANTS = eINSTANCE.getModel_Participants();

		/**
		 * The meta object literal for the '<em><b>Positions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__POSITIONS = eINSTANCE.getModel_Positions();

		/**
		 * The meta object literal for the '<em><b>Internal Persons</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__INTERNAL_PERSONS = eINSTANCE.getModel_InternalPersons();

		/**
		 * The meta object literal for the '<em><b>External Persons</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__EXTERNAL_PERSONS = eINSTANCE.getModel_ExternalPersons();

		/**
		 * The meta object literal for the '<em><b>Person Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__PERSON_TYPES = eINSTANCE.getModel_PersonTypes();

		/**
		 * The meta object literal for the '<em><b>Locations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__LOCATIONS = eINSTANCE.getModel_Locations();

		/**
		 * The meta object literal for the '<em><b>Groups</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__GROUPS = eINSTANCE.getModel_Groups();

		/**
		 * The meta object literal for the '<em><b>Relations1</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__RELATIONS1 = eINSTANCE.getModel_Relations1();

		/**
		 * The meta object literal for the '<em><b>Relations2</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__RELATIONS2 = eINSTANCE.getModel_Relations2();

		/**
		 * The meta object literal for the '{@link orgchart.impl.NamedElementImpl <em>Named Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgchart.impl.NamedElementImpl
		 * @see orgchart.impl.OrgchartPackageImpl#getNamedElement()
		 * @generated
		 */
		EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ELEMENT__NAME = eINSTANCE.getNamedElement_Name();

		/**
		 * The meta object literal for the '{@link orgchart.impl.AllImpl <em>All</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgchart.impl.AllImpl
		 * @see orgchart.impl.OrgchartPackageImpl#getAll()
		 * @generated
		 */
		EClass ALL = eINSTANCE.getAll();

		/**
		 * The meta object literal for the '{@link orgchart.impl.All_Rel_1Impl <em>All Rel 1</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgchart.impl.All_Rel_1Impl
		 * @see orgchart.impl.OrgchartPackageImpl#getAll_Rel_1()
		 * @generated
		 */
		EClass ALL_REL_1 = eINSTANCE.getAll_Rel_1();

		/**
		 * The meta object literal for the '{@link orgchart.impl.All_Rel_2Impl <em>All Rel 2</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgchart.impl.All_Rel_2Impl
		 * @see orgchart.impl.OrgchartPackageImpl#getAll_Rel_2()
		 * @generated
		 */
		EClass ALL_REL_2 = eINSTANCE.getAll_Rel_2();

		/**
		 * The meta object literal for the '{@link orgchart.impl.PersonImpl <em>Person</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgchart.impl.PersonImpl
		 * @see orgchart.impl.OrgchartPackageImpl#getPerson()
		 * @generated
		 */
		EClass PERSON = eINSTANCE.getPerson();

		/**
		 * The meta object literal for the '{@link orgchart.impl.ParticipantImpl <em>Participant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgchart.impl.ParticipantImpl
		 * @see orgchart.impl.OrgchartPackageImpl#getParticipant()
		 * @generated
		 */
		EClass PARTICIPANT = eINSTANCE.getParticipant();
		
		/**
		 * @generated NOT
		 */
		EAttribute PARTICIPANT__SUBDIAGRAM = eINSTANCE.getParticipant_Subdiagram();

		/**
		 * The meta object literal for the '{@link orgchart.impl.LocationImpl <em>Location</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgchart.impl.LocationImpl
		 * @see orgchart.impl.OrgchartPackageImpl#getLocation()
		 * @generated
		 */
		EClass LOCATION = eINSTANCE.getLocation();
		
		/**
		 * @generated NOT
		 */
		EAttribute LOCATION__SUBDIAGRAM = eINSTANCE.getLocation_Subdiagram();

		/**
		 * The meta object literal for the '{@link orgchart.impl.GroupImpl <em>Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgchart.impl.GroupImpl
		 * @see orgchart.impl.OrgchartPackageImpl#getGroup()
		 * @generated
		 */
		EClass GROUP = eINSTANCE.getGroup();
		
		/**
		 * @generated NOT
		 */
		EAttribute GROUP__SUBDIAGRAM = eINSTANCE.getGroup_Subdiagram();

		/**
		 * The meta object literal for the '{@link orgchart.impl.PositionImpl <em>Position</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgchart.impl.PositionImpl
		 * @see orgchart.impl.OrgchartPackageImpl#getPosition()
		 * @generated
		 */
		EClass POSITION = eINSTANCE.getPosition();
		
		/**
		 * @generated NOT
		 */
		EAttribute POSITION__SUBDIAGRAM = eINSTANCE.getPosition_Subdiagram();

		/**
		 * The meta object literal for the '{@link orgchart.impl.InternalPersonImpl <em>Internal Person</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgchart.impl.InternalPersonImpl
		 * @see orgchart.impl.OrgchartPackageImpl#getInternalPerson()
		 * @generated
		 */
		EClass INTERNAL_PERSON = eINSTANCE.getInternalPerson();

		/**
		 * The meta object literal for the '{@link orgchart.impl.ExternalPersonImpl <em>External Person</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgchart.impl.ExternalPersonImpl
		 * @see orgchart.impl.OrgchartPackageImpl#getExternalPerson()
		 * @generated
		 */
		EClass EXTERNAL_PERSON = eINSTANCE.getExternalPerson();

		/**
		 * The meta object literal for the '{@link orgchart.impl.PersonTypeImpl <em>Person Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgchart.impl.PersonTypeImpl
		 * @see orgchart.impl.OrgchartPackageImpl#getPersonType()
		 * @generated
		 */
		EClass PERSON_TYPE = eINSTANCE.getPersonType();

		/**
		 * The meta object literal for the '{@link orgchart.impl.Relation1Impl <em>Relation1</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgchart.impl.Relation1Impl
		 * @see orgchart.impl.OrgchartPackageImpl#getRelation1()
		 * @generated
		 */
		EClass RELATION1 = eINSTANCE.getRelation1();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATION1__SOURCE = eINSTANCE.getRelation1_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATION1__TARGET = eINSTANCE.getRelation1_Target();

		/**
		 * The meta object literal for the '{@link orgchart.impl.Relation2Impl <em>Relation2</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgchart.impl.Relation2Impl
		 * @see orgchart.impl.OrgchartPackageImpl#getRelation2()
		 * @generated
		 */
		EClass RELATION2 = eINSTANCE.getRelation2();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATION2__SOURCE = eINSTANCE.getRelation2_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATION2__TARGET = eINSTANCE.getRelation2_Target();

	}

} //OrgchartPackage

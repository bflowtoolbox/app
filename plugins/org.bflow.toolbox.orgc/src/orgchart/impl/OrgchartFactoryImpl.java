/**
 */
package orgchart.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import orgchart.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OrgchartFactoryImpl extends EFactoryImpl implements OrgchartFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OrgchartFactory init() {
		try {
			OrgchartFactory theOrgchartFactory = (OrgchartFactory)EPackage.Registry.INSTANCE.getEFactory(OrgchartPackage.eNS_URI);
			if (theOrgchartFactory != null) {
				return theOrgchartFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new OrgchartFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrgchartFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case OrgchartPackage.MODEL: return createModel();
			case OrgchartPackage.PARTICIPANT: return createParticipant();
			case OrgchartPackage.LOCATION: return createLocation();
			case OrgchartPackage.GROUP: return createGroup();
			case OrgchartPackage.POSITION: return createPosition();
			case OrgchartPackage.INTERNAL_PERSON: return createInternalPerson();
			case OrgchartPackage.EXTERNAL_PERSON: return createExternalPerson();
			case OrgchartPackage.PERSON_TYPE: return createPersonType();
			case OrgchartPackage.RELATION1: return createRelation1();
			case OrgchartPackage.RELATION2: return createRelation2();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model createModel() {
		ModelImpl model = new ModelImpl();
		return model;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Participant createParticipant() {
		ParticipantImpl participant = new ParticipantImpl();
		return participant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Location createLocation() {
		LocationImpl location = new LocationImpl();
		return location;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Group createGroup() {
		GroupImpl group = new GroupImpl();
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Position createPosition() {
		PositionImpl position = new PositionImpl();
		return position;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InternalPerson createInternalPerson() {
		InternalPersonImpl internalPerson = new InternalPersonImpl();
		return internalPerson;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalPerson createExternalPerson() {
		ExternalPersonImpl externalPerson = new ExternalPersonImpl();
		return externalPerson;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PersonType createPersonType() {
		PersonTypeImpl personType = new PersonTypeImpl();
		return personType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Relation1 createRelation1() {
		Relation1Impl relation1 = new Relation1Impl();
		return relation1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Relation2 createRelation2() {
		Relation2Impl relation2 = new Relation2Impl();
		return relation2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrgchartPackage getOrgchartPackage() {
		return (OrgchartPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static OrgchartPackage getPackage() {
		return OrgchartPackage.eINSTANCE;
	}

} //OrgchartFactoryImpl

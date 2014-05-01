/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bflow.toolbox.epc.impl;

import org.bflow.toolbox.epc.AND;
import org.bflow.toolbox.epc.Application;
import org.bflow.toolbox.epc.Arc;
import org.bflow.toolbox.epc.CardFile;
import org.bflow.toolbox.epc.Cluster;
import org.bflow.toolbox.epc.Document;
import org.bflow.toolbox.epc.Epc;
import org.bflow.toolbox.epc.EpcFactory;
import org.bflow.toolbox.epc.EpcPackage;
import org.bflow.toolbox.epc.Event;
import org.bflow.toolbox.epc.ExternalPerson;
import org.bflow.toolbox.epc.File;
import org.bflow.toolbox.epc.Function;
import org.bflow.toolbox.epc.Group;
import org.bflow.toolbox.epc.InformationArc;
import org.bflow.toolbox.epc.InternalPerson;
import org.bflow.toolbox.epc.Location;
import org.bflow.toolbox.epc.OR;
import org.bflow.toolbox.epc.Objective;
import org.bflow.toolbox.epc.Participant;
import org.bflow.toolbox.epc.PersonType;
import org.bflow.toolbox.epc.Position;
import org.bflow.toolbox.epc.ProcessInterface;
import org.bflow.toolbox.epc.Product;
import org.bflow.toolbox.epc.Relation;
import org.bflow.toolbox.epc.TechnicalTerm;
import org.bflow.toolbox.epc.XOR;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EpcFactoryImpl extends EFactoryImpl implements EpcFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EpcFactory init() {
		try {
			EpcFactory theEpcFactory = (EpcFactory)EPackage.Registry.INSTANCE.getEFactory("org.bflow.toolbox.epc"); 
			if (theEpcFactory != null) {
				return theEpcFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new EpcFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EpcFactoryImpl() {
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
			case EpcPackage.EVENT: return createEvent();
			case EpcPackage.FUNCTION: return createFunction();
			case EpcPackage.PROCESS_INTERFACE: return createProcessInterface();
			case EpcPackage.APPLICATION: return createApplication();
			case EpcPackage.PARTICIPANT: return createParticipant();
			case EpcPackage.AND: return createAND();
			case EpcPackage.OR: return createOR();
			case EpcPackage.XOR: return createXOR();
			case EpcPackage.ARC: return createArc();
			case EpcPackage.RELATION: return createRelation();
			case EpcPackage.GROUP: return createGroup();
			case EpcPackage.LOCATION: return createLocation();
			case EpcPackage.POSITION: return createPosition();
			case EpcPackage.FILE: return createFile();
			case EpcPackage.CARD_FILE: return createCardFile();
			case EpcPackage.CLUSTER: return createCluster();
			case EpcPackage.INTERNAL_PERSON: return createInternalPerson();
			case EpcPackage.EXTERNAL_PERSON: return createExternalPerson();
			case EpcPackage.PERSON_TYPE: return createPersonType();
			case EpcPackage.TECHNICAL_TERM: return createTechnicalTerm();
			case EpcPackage.DOCUMENT: return createDocument();
			case EpcPackage.OBJECTIVE: return createObjective();
			case EpcPackage.PRODUCT: return createProduct();
			case EpcPackage.INFORMATION_ARC: return createInformationArc();
			case EpcPackage.EPC: return createEpc();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Event createEvent() {
		EventImpl event = new EventImpl();
		return event;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Function createFunction() {
		FunctionImpl function = new FunctionImpl();
		return function;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessInterface createProcessInterface() {
		ProcessInterfaceImpl processInterface = new ProcessInterfaceImpl();
		return processInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Application createApplication() {
		ApplicationImpl application = new ApplicationImpl();
		return application;
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
	public AND createAND() {
		ANDImpl and = new ANDImpl();
		return and;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OR createOR() {
		ORImpl or = new ORImpl();
		return or;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XOR createXOR() {
		XORImpl xor = new XORImpl();
		return xor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Arc createArc() {
		ArcImpl arc = new ArcImpl();
		return arc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Relation createRelation() {
		RelationImpl relation = new RelationImpl();
		return relation;
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
	public Location createLocation() {
		LocationImpl location = new LocationImpl();
		return location;
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
	public File createFile() {
		FileImpl file = new FileImpl();
		return file;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CardFile createCardFile() {
		CardFileImpl cardFile = new CardFileImpl();
		return cardFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Cluster createCluster() {
		ClusterImpl cluster = new ClusterImpl();
		return cluster;
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
	public TechnicalTerm createTechnicalTerm() {
		TechnicalTermImpl technicalTerm = new TechnicalTermImpl();
		return technicalTerm;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Document createDocument() {
		DocumentImpl document = new DocumentImpl();
		return document;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Objective createObjective() {
		ObjectiveImpl objective = new ObjectiveImpl();
		return objective;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Product createProduct() {
		ProductImpl product = new ProductImpl();
		return product;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InformationArc createInformationArc() {
		InformationArcImpl informationArc = new InformationArcImpl();
		return informationArc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Epc createEpc() {
		EpcImpl epc = new EpcImpl();
		return epc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EpcPackage getEpcPackage() {
		return (EpcPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static EpcPackage getPackage() {
		return EpcPackage.eINSTANCE;
	}

} //EpcFactoryImpl

/**
 */
package vcchart.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import vcchart.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class VcchartFactoryImpl extends EFactoryImpl implements VcchartFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static VcchartFactory init() {
		try {
			VcchartFactory theVcchartFactory = (VcchartFactory)EPackage.Registry.INSTANCE.getEFactory(VcchartPackage.eNS_URI);
			if (theVcchartFactory != null) {
				return theVcchartFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new VcchartFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VcchartFactoryImpl() {
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
			case VcchartPackage.MODEL: return createModel();
			case VcchartPackage.ACTIVITY1: return createActivity1();
			case VcchartPackage.ACTIVITY2: return createActivity2();
			case VcchartPackage.PRODUCT: return createProduct();
			case VcchartPackage.OBJECTIVE: return createObjective();
			case VcchartPackage.CLUSTER: return createCluster();
			case VcchartPackage.TECHNICAL_TERM: return createTechnicalTerm();
			case VcchartPackage.PARTICIPANT: return createParticipant();
			case VcchartPackage.APPLICATION: return createApplication();
			case VcchartPackage.DOCUMENT: return createDocument();
			case VcchartPackage.RELATION1: return createRelation1();
			case VcchartPackage.RELATION2: return createRelation2();
			case VcchartPackage.RELATION3: return createRelation3();
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
	public Activity1 createActivity1() {
		Activity1Impl activity1 = new Activity1Impl();
		return activity1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Activity2 createActivity2() {
		Activity2Impl activity2 = new Activity2Impl();
		return activity2;
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
	public Objective createObjective() {
		ObjectiveImpl objective = new ObjectiveImpl();
		return objective;
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
	public TechnicalTerm createTechnicalTerm() {
		TechnicalTermImpl technicalTerm = new TechnicalTermImpl();
		return technicalTerm;
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
	public Application createApplication() {
		ApplicationImpl application = new ApplicationImpl();
		return application;
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
	public Relation3 createRelation3() {
		Relation3Impl relation3 = new Relation3Impl();
		return relation3;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VcchartPackage getVcchartPackage() {
		return (VcchartPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static VcchartPackage getPackage() {
		return VcchartPackage.eINSTANCE;
	}

} //VcchartFactoryImpl

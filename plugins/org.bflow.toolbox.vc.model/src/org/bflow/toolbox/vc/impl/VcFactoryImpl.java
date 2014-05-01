/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bflow.toolbox.vc.impl;

import org.bflow.toolbox.vc.Cluster;
import org.bflow.toolbox.vc.Objective;
import org.bflow.toolbox.vc.PredecessorConnection;
import org.bflow.toolbox.vc.ProcessSuperiority;
import org.bflow.toolbox.vc.Product;
import org.bflow.toolbox.vc.Relation;
import org.bflow.toolbox.vc.TechnicalTerm;
import org.bflow.toolbox.vc.ValueChain;
import org.bflow.toolbox.vc.ValueChain2;
import org.bflow.toolbox.vc.Vc;
import org.bflow.toolbox.vc.VcFactory;
import org.bflow.toolbox.vc.VcPackage;
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
public class VcFactoryImpl extends EFactoryImpl implements VcFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static VcFactory init() {
		try {
			VcFactory theVcFactory = (VcFactory)EPackage.Registry.INSTANCE.getEFactory("org.bflow.toolbox.vc"); 
			if (theVcFactory != null) {
				return theVcFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new VcFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VcFactoryImpl() {
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
			case VcPackage.VALUE_CHAIN: return createValueChain();
			case VcPackage.VALUE_CHAIN2: return createValueChain2();
			case VcPackage.TECHNICAL_TERM: return createTechnicalTerm();
			case VcPackage.CLUSTER: return createCluster();
			case VcPackage.OBJECTIVE: return createObjective();
			case VcPackage.PRODUCT: return createProduct();
			case VcPackage.PREDECESSOR_CONNECTION: return createPredecessorConnection();
			case VcPackage.PROCESS_SUPERIORITY: return createProcessSuperiority();
			case VcPackage.VC: return createVc();
			case VcPackage.RELATION: return createRelation();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueChain createValueChain() {
		ValueChainImpl valueChain = new ValueChainImpl();
		return valueChain;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueChain2 createValueChain2() {
		ValueChain2Impl valueChain2 = new ValueChain2Impl();
		return valueChain2;
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
	public Cluster createCluster() {
		ClusterImpl cluster = new ClusterImpl();
		return cluster;
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
	public PredecessorConnection createPredecessorConnection() {
		PredecessorConnectionImpl predecessorConnection = new PredecessorConnectionImpl();
		return predecessorConnection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessSuperiority createProcessSuperiority() {
		ProcessSuperiorityImpl processSuperiority = new ProcessSuperiorityImpl();
		return processSuperiority;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Vc createVc() {
		VcImpl vc = new VcImpl();
		return vc;
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
	public VcPackage getVcPackage() {
		return (VcPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static VcPackage getPackage() {
		return VcPackage.eINSTANCE;
	}

} //VcFactoryImpl

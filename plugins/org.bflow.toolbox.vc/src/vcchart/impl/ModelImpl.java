/**
 */
package vcchart.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import vcchart.Activity1;
import vcchart.Activity2;
import vcchart.Application;
import vcchart.Cluster;
import vcchart.Document;
import vcchart.Model;
import vcchart.Objective;
import vcchart.Participant;
import vcchart.Product;
import vcchart.Relation1;
import vcchart.Relation2;
import vcchart.Relation3;
import vcchart.TechnicalTerm;
import vcchart.VcchartPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link vcchart.impl.ModelImpl#getActivitys1 <em>Activitys1</em>}</li>
 *   <li>{@link vcchart.impl.ModelImpl#getActivitys2 <em>Activitys2</em>}</li>
 *   <li>{@link vcchart.impl.ModelImpl#getClusters <em>Clusters</em>}</li>
 *   <li>{@link vcchart.impl.ModelImpl#getProducts <em>Products</em>}</li>
 *   <li>{@link vcchart.impl.ModelImpl#getTechnicalTerms <em>Technical Terms</em>}</li>
 *   <li>{@link vcchart.impl.ModelImpl#getObjectives <em>Objectives</em>}</li>
 *   <li>{@link vcchart.impl.ModelImpl#getParticipants <em>Participants</em>}</li>
 *   <li>{@link vcchart.impl.ModelImpl#getApplications <em>Applications</em>}</li>
 *   <li>{@link vcchart.impl.ModelImpl#getDocuments <em>Documents</em>}</li>
 *   <li>{@link vcchart.impl.ModelImpl#getRelations1 <em>Relations1</em>}</li>
 *   <li>{@link vcchart.impl.ModelImpl#getRelations2 <em>Relations2</em>}</li>
 *   <li>{@link vcchart.impl.ModelImpl#getRelations3 <em>Relations3</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelImpl extends MinimalEObjectImpl.Container implements Model {
	/**
	 * The cached value of the '{@link #getActivitys1() <em>Activitys1</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivitys1()
	 * @generated
	 * @ordered
	 */
	protected EList<Activity1> activitys1;

	/**
	 * The cached value of the '{@link #getActivitys2() <em>Activitys2</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivitys2()
	 * @generated
	 * @ordered
	 */
	protected EList<Activity2> activitys2;

	/**
	 * The cached value of the '{@link #getClusters() <em>Clusters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClusters()
	 * @generated
	 * @ordered
	 */
	protected EList<Cluster> clusters;

	/**
	 * The cached value of the '{@link #getProducts() <em>Products</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProducts()
	 * @generated
	 * @ordered
	 */
	protected EList<Product> products;

	/**
	 * The cached value of the '{@link #getTechnicalTerms() <em>Technical Terms</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTechnicalTerms()
	 * @generated
	 * @ordered
	 */
	protected EList<TechnicalTerm> technicalTerms;

	/**
	 * The cached value of the '{@link #getObjectives() <em>Objectives</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObjectives()
	 * @generated
	 * @ordered
	 */
	protected EList<Objective> objectives;

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
	 * The cached value of the '{@link #getApplications() <em>Applications</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getApplications()
	 * @generated
	 * @ordered
	 */
	protected EList<Application> applications;

	/**
	 * The cached value of the '{@link #getDocuments() <em>Documents</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocuments()
	 * @generated
	 * @ordered
	 */
	protected EList<Document> documents;

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
	 * The cached value of the '{@link #getRelations3() <em>Relations3</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelations3()
	 * @generated
	 * @ordered
	 */
	protected EList<Relation3> relations3;

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
		return VcchartPackage.Literals.MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Activity1> getActivitys1() {
		if (activitys1 == null) {
			activitys1 = new EObjectContainmentEList<Activity1>(Activity1.class, this, VcchartPackage.MODEL__ACTIVITYS1);
		}
		return activitys1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Activity2> getActivitys2() {
		if (activitys2 == null) {
			activitys2 = new EObjectContainmentEList<Activity2>(Activity2.class, this, VcchartPackage.MODEL__ACTIVITYS2);
		}
		return activitys2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Cluster> getClusters() {
		if (clusters == null) {
			clusters = new EObjectContainmentEList<Cluster>(Cluster.class, this, VcchartPackage.MODEL__CLUSTERS);
		}
		return clusters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Product> getProducts() {
		if (products == null) {
			products = new EObjectContainmentEList<Product>(Product.class, this, VcchartPackage.MODEL__PRODUCTS);
		}
		return products;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TechnicalTerm> getTechnicalTerms() {
		if (technicalTerms == null) {
			technicalTerms = new EObjectContainmentEList<TechnicalTerm>(TechnicalTerm.class, this, VcchartPackage.MODEL__TECHNICAL_TERMS);
		}
		return technicalTerms;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Objective> getObjectives() {
		if (objectives == null) {
			objectives = new EObjectContainmentEList<Objective>(Objective.class, this, VcchartPackage.MODEL__OBJECTIVES);
		}
		return objectives;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Participant> getParticipants() {
		if (participants == null) {
			participants = new EObjectContainmentEList<Participant>(Participant.class, this, VcchartPackage.MODEL__PARTICIPANTS);
		}
		return participants;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Application> getApplications() {
		if (applications == null) {
			applications = new EObjectContainmentEList<Application>(Application.class, this, VcchartPackage.MODEL__APPLICATIONS);
		}
		return applications;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Document> getDocuments() {
		if (documents == null) {
			documents = new EObjectContainmentEList<Document>(Document.class, this, VcchartPackage.MODEL__DOCUMENTS);
		}
		return documents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Relation1> getRelations1() {
		if (relations1 == null) {
			relations1 = new EObjectContainmentEList<Relation1>(Relation1.class, this, VcchartPackage.MODEL__RELATIONS1);
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
			relations2 = new EObjectContainmentEList<Relation2>(Relation2.class, this, VcchartPackage.MODEL__RELATIONS2);
		}
		return relations2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Relation3> getRelations3() {
		if (relations3 == null) {
			relations3 = new EObjectContainmentEList<Relation3>(Relation3.class, this, VcchartPackage.MODEL__RELATIONS3);
		}
		return relations3;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case VcchartPackage.MODEL__ACTIVITYS1:
				return ((InternalEList<?>)getActivitys1()).basicRemove(otherEnd, msgs);
			case VcchartPackage.MODEL__ACTIVITYS2:
				return ((InternalEList<?>)getActivitys2()).basicRemove(otherEnd, msgs);
			case VcchartPackage.MODEL__CLUSTERS:
				return ((InternalEList<?>)getClusters()).basicRemove(otherEnd, msgs);
			case VcchartPackage.MODEL__PRODUCTS:
				return ((InternalEList<?>)getProducts()).basicRemove(otherEnd, msgs);
			case VcchartPackage.MODEL__TECHNICAL_TERMS:
				return ((InternalEList<?>)getTechnicalTerms()).basicRemove(otherEnd, msgs);
			case VcchartPackage.MODEL__OBJECTIVES:
				return ((InternalEList<?>)getObjectives()).basicRemove(otherEnd, msgs);
			case VcchartPackage.MODEL__PARTICIPANTS:
				return ((InternalEList<?>)getParticipants()).basicRemove(otherEnd, msgs);
			case VcchartPackage.MODEL__APPLICATIONS:
				return ((InternalEList<?>)getApplications()).basicRemove(otherEnd, msgs);
			case VcchartPackage.MODEL__DOCUMENTS:
				return ((InternalEList<?>)getDocuments()).basicRemove(otherEnd, msgs);
			case VcchartPackage.MODEL__RELATIONS1:
				return ((InternalEList<?>)getRelations1()).basicRemove(otherEnd, msgs);
			case VcchartPackage.MODEL__RELATIONS2:
				return ((InternalEList<?>)getRelations2()).basicRemove(otherEnd, msgs);
			case VcchartPackage.MODEL__RELATIONS3:
				return ((InternalEList<?>)getRelations3()).basicRemove(otherEnd, msgs);
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
			case VcchartPackage.MODEL__ACTIVITYS1:
				return getActivitys1();
			case VcchartPackage.MODEL__ACTIVITYS2:
				return getActivitys2();
			case VcchartPackage.MODEL__CLUSTERS:
				return getClusters();
			case VcchartPackage.MODEL__PRODUCTS:
				return getProducts();
			case VcchartPackage.MODEL__TECHNICAL_TERMS:
				return getTechnicalTerms();
			case VcchartPackage.MODEL__OBJECTIVES:
				return getObjectives();
			case VcchartPackage.MODEL__PARTICIPANTS:
				return getParticipants();
			case VcchartPackage.MODEL__APPLICATIONS:
				return getApplications();
			case VcchartPackage.MODEL__DOCUMENTS:
				return getDocuments();
			case VcchartPackage.MODEL__RELATIONS1:
				return getRelations1();
			case VcchartPackage.MODEL__RELATIONS2:
				return getRelations2();
			case VcchartPackage.MODEL__RELATIONS3:
				return getRelations3();
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
			case VcchartPackage.MODEL__ACTIVITYS1:
				getActivitys1().clear();
				getActivitys1().addAll((Collection<? extends Activity1>)newValue);
				return;
			case VcchartPackage.MODEL__ACTIVITYS2:
				getActivitys2().clear();
				getActivitys2().addAll((Collection<? extends Activity2>)newValue);
				return;
			case VcchartPackage.MODEL__CLUSTERS:
				getClusters().clear();
				getClusters().addAll((Collection<? extends Cluster>)newValue);
				return;
			case VcchartPackage.MODEL__PRODUCTS:
				getProducts().clear();
				getProducts().addAll((Collection<? extends Product>)newValue);
				return;
			case VcchartPackage.MODEL__TECHNICAL_TERMS:
				getTechnicalTerms().clear();
				getTechnicalTerms().addAll((Collection<? extends TechnicalTerm>)newValue);
				return;
			case VcchartPackage.MODEL__OBJECTIVES:
				getObjectives().clear();
				getObjectives().addAll((Collection<? extends Objective>)newValue);
				return;
			case VcchartPackage.MODEL__PARTICIPANTS:
				getParticipants().clear();
				getParticipants().addAll((Collection<? extends Participant>)newValue);
				return;
			case VcchartPackage.MODEL__APPLICATIONS:
				getApplications().clear();
				getApplications().addAll((Collection<? extends Application>)newValue);
				return;
			case VcchartPackage.MODEL__DOCUMENTS:
				getDocuments().clear();
				getDocuments().addAll((Collection<? extends Document>)newValue);
				return;
			case VcchartPackage.MODEL__RELATIONS1:
				getRelations1().clear();
				getRelations1().addAll((Collection<? extends Relation1>)newValue);
				return;
			case VcchartPackage.MODEL__RELATIONS2:
				getRelations2().clear();
				getRelations2().addAll((Collection<? extends Relation2>)newValue);
				return;
			case VcchartPackage.MODEL__RELATIONS3:
				getRelations3().clear();
				getRelations3().addAll((Collection<? extends Relation3>)newValue);
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
			case VcchartPackage.MODEL__ACTIVITYS1:
				getActivitys1().clear();
				return;
			case VcchartPackage.MODEL__ACTIVITYS2:
				getActivitys2().clear();
				return;
			case VcchartPackage.MODEL__CLUSTERS:
				getClusters().clear();
				return;
			case VcchartPackage.MODEL__PRODUCTS:
				getProducts().clear();
				return;
			case VcchartPackage.MODEL__TECHNICAL_TERMS:
				getTechnicalTerms().clear();
				return;
			case VcchartPackage.MODEL__OBJECTIVES:
				getObjectives().clear();
				return;
			case VcchartPackage.MODEL__PARTICIPANTS:
				getParticipants().clear();
				return;
			case VcchartPackage.MODEL__APPLICATIONS:
				getApplications().clear();
				return;
			case VcchartPackage.MODEL__DOCUMENTS:
				getDocuments().clear();
				return;
			case VcchartPackage.MODEL__RELATIONS1:
				getRelations1().clear();
				return;
			case VcchartPackage.MODEL__RELATIONS2:
				getRelations2().clear();
				return;
			case VcchartPackage.MODEL__RELATIONS3:
				getRelations3().clear();
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
			case VcchartPackage.MODEL__ACTIVITYS1:
				return activitys1 != null && !activitys1.isEmpty();
			case VcchartPackage.MODEL__ACTIVITYS2:
				return activitys2 != null && !activitys2.isEmpty();
			case VcchartPackage.MODEL__CLUSTERS:
				return clusters != null && !clusters.isEmpty();
			case VcchartPackage.MODEL__PRODUCTS:
				return products != null && !products.isEmpty();
			case VcchartPackage.MODEL__TECHNICAL_TERMS:
				return technicalTerms != null && !technicalTerms.isEmpty();
			case VcchartPackage.MODEL__OBJECTIVES:
				return objectives != null && !objectives.isEmpty();
			case VcchartPackage.MODEL__PARTICIPANTS:
				return participants != null && !participants.isEmpty();
			case VcchartPackage.MODEL__APPLICATIONS:
				return applications != null && !applications.isEmpty();
			case VcchartPackage.MODEL__DOCUMENTS:
				return documents != null && !documents.isEmpty();
			case VcchartPackage.MODEL__RELATIONS1:
				return relations1 != null && !relations1.isEmpty();
			case VcchartPackage.MODEL__RELATIONS2:
				return relations2 != null && !relations2.isEmpty();
			case VcchartPackage.MODEL__RELATIONS3:
				return relations3 != null && !relations3.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ModelImpl

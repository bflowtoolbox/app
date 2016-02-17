/**
 */
package vcchart;

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
 *   <li>{@link vcchart.Model#getActivitys1 <em>Activitys1</em>}</li>
 *   <li>{@link vcchart.Model#getActivitys2 <em>Activitys2</em>}</li>
 *   <li>{@link vcchart.Model#getClusters <em>Clusters</em>}</li>
 *   <li>{@link vcchart.Model#getProducts <em>Products</em>}</li>
 *   <li>{@link vcchart.Model#getTechnicalTerms <em>Technical Terms</em>}</li>
 *   <li>{@link vcchart.Model#getObjectives <em>Objectives</em>}</li>
 *   <li>{@link vcchart.Model#getParticipants <em>Participants</em>}</li>
 *   <li>{@link vcchart.Model#getApplications <em>Applications</em>}</li>
 *   <li>{@link vcchart.Model#getDocuments <em>Documents</em>}</li>
 *   <li>{@link vcchart.Model#getRelations1 <em>Relations1</em>}</li>
 *   <li>{@link vcchart.Model#getRelations2 <em>Relations2</em>}</li>
 *   <li>{@link vcchart.Model#getRelations3 <em>Relations3</em>}</li>
 * </ul>
 * </p>
 *
 * @see vcchart.VcchartPackage#getModel()
 * @model annotation="gmf.diagram onefile='true' diagram.extension='vc'"
 * @generated
 */
public interface Model extends EObject {
	/**
	 * Returns the value of the '<em><b>Activitys1</b></em>' containment reference list.
	 * The list contents are of type {@link vcchart.Activity1}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activitys1</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activitys1</em>' containment reference list.
	 * @see vcchart.VcchartPackage#getModel_Activitys1()
	 * @model containment="true"
	 * @generated
	 */
	EList<Activity1> getActivitys1();

	/**
	 * Returns the value of the '<em><b>Activitys2</b></em>' containment reference list.
	 * The list contents are of type {@link vcchart.Activity2}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activitys2</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activitys2</em>' containment reference list.
	 * @see vcchart.VcchartPackage#getModel_Activitys2()
	 * @model containment="true"
	 * @generated
	 */
	EList<Activity2> getActivitys2();

	/**
	 * Returns the value of the '<em><b>Clusters</b></em>' containment reference list.
	 * The list contents are of type {@link vcchart.Cluster}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Clusters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Clusters</em>' containment reference list.
	 * @see vcchart.VcchartPackage#getModel_Clusters()
	 * @model containment="true"
	 * @generated
	 */
	EList<Cluster> getClusters();

	/**
	 * Returns the value of the '<em><b>Products</b></em>' containment reference list.
	 * The list contents are of type {@link vcchart.Product}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Products</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Products</em>' containment reference list.
	 * @see vcchart.VcchartPackage#getModel_Products()
	 * @model containment="true"
	 * @generated
	 */
	EList<Product> getProducts();

	/**
	 * Returns the value of the '<em><b>Technical Terms</b></em>' containment reference list.
	 * The list contents are of type {@link vcchart.TechnicalTerm}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Technical Terms</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Technical Terms</em>' containment reference list.
	 * @see vcchart.VcchartPackage#getModel_TechnicalTerms()
	 * @model containment="true"
	 * @generated
	 */
	EList<TechnicalTerm> getTechnicalTerms();

	/**
	 * Returns the value of the '<em><b>Objectives</b></em>' containment reference list.
	 * The list contents are of type {@link vcchart.Objective}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Objectives</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Objectives</em>' containment reference list.
	 * @see vcchart.VcchartPackage#getModel_Objectives()
	 * @model containment="true"
	 * @generated
	 */
	EList<Objective> getObjectives();

	/**
	 * Returns the value of the '<em><b>Participants</b></em>' containment reference list.
	 * The list contents are of type {@link vcchart.Participant}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participants</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participants</em>' containment reference list.
	 * @see vcchart.VcchartPackage#getModel_Participants()
	 * @model containment="true"
	 * @generated
	 */
	EList<Participant> getParticipants();

	/**
	 * Returns the value of the '<em><b>Applications</b></em>' containment reference list.
	 * The list contents are of type {@link vcchart.Application}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Applications</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Applications</em>' containment reference list.
	 * @see vcchart.VcchartPackage#getModel_Applications()
	 * @model containment="true"
	 * @generated
	 */
	EList<Application> getApplications();

	/**
	 * Returns the value of the '<em><b>Documents</b></em>' containment reference list.
	 * The list contents are of type {@link vcchart.Document}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Documents</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Documents</em>' containment reference list.
	 * @see vcchart.VcchartPackage#getModel_Documents()
	 * @model containment="true"
	 * @generated
	 */
	EList<Document> getDocuments();

	/**
	 * Returns the value of the '<em><b>Relations1</b></em>' containment reference list.
	 * The list contents are of type {@link vcchart.Relation1}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relations1</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relations1</em>' containment reference list.
	 * @see vcchart.VcchartPackage#getModel_Relations1()
	 * @model containment="true"
	 * @generated
	 */
	EList<Relation1> getRelations1();

	/**
	 * Returns the value of the '<em><b>Relations2</b></em>' containment reference list.
	 * The list contents are of type {@link vcchart.Relation2}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relations2</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relations2</em>' containment reference list.
	 * @see vcchart.VcchartPackage#getModel_Relations2()
	 * @model containment="true"
	 * @generated
	 */
	EList<Relation2> getRelations2();

	/**
	 * Returns the value of the '<em><b>Relations3</b></em>' containment reference list.
	 * The list contents are of type {@link vcchart.Relation3}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relations3</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relations3</em>' containment reference list.
	 * @see vcchart.VcchartPackage#getModel_Relations3()
	 * @model containment="true"
	 * @generated
	 */
	EList<Relation3> getRelations3();

} // Model

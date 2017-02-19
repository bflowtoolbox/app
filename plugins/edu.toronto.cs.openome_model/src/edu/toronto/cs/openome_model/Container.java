/**
 * Copyright 2001-2008 University of Toronto
 *
 * $Id$
 */
package edu.toronto.cs.openome_model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.toronto.cs.openome_model.Container#getName <em>Name</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Container#getSub <em>Sub</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Container#getIntentions <em>Intentions</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Container#getModel <em>Model</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Container#getAssociationTo <em>Association To</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Container#getAssociationFrom <em>Association From</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.toronto.cs.openome_model.openome_modelPackage#getContainer()
 * @model abstract="true"
 * @generated
 */
public interface Container extends Dependable {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright 2001-2008 University of Toronto";

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getContainer_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Container#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Sub</b></em>' containment reference list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.Actor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub</em>' containment reference list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getContainer_Sub()
	 * @model containment="true"
	 * @generated
	 */
	EList<Actor> getSub();

	/**
	 * Returns the value of the '<em><b>Intentions</b></em>' containment reference list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.Intention}.
	 * It is bidirectional and its opposite is '{@link edu.toronto.cs.openome_model.Intention#getContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Intentions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Intentions</em>' containment reference list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getContainer_Intentions()
	 * @see edu.toronto.cs.openome_model.Intention#getContainer
	 * @model opposite="container" containment="true"
	 * @generated
	 */
	EList<Intention> getIntentions();

	/**
	 * Returns the value of the '<em><b>Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.toronto.cs.openome_model.Model#getContainers <em>Containers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model</em>' container reference.
	 * @see #setModel(Model)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getContainer_Model()
	 * @see edu.toronto.cs.openome_model.Model#getContainers
	 * @model opposite="containers"
	 * @generated
	 */
	Model getModel();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Container#getModel <em>Model</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' container reference.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(Model value);

	/**
	 * Returns the value of the '<em><b>Association To</b></em>' reference list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.Association}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Association To</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Association To</em>' reference list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getContainer_AssociationTo()
	 * @model
	 * @generated
	 */
	EList<Association> getAssociationTo();

	/**
	 * Returns the value of the '<em><b>Association From</b></em>' reference list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.Association}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Association From</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Association From</em>' reference list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getContainer_AssociationFrom()
	 * @model
	 * @generated
	 */
	EList<Association> getAssociationFrom();

} // Container

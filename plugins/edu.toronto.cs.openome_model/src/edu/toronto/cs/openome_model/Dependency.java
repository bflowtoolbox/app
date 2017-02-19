/**
 * Copyright 2001-2008 University of Toronto
 *
 * $Id$
 */
package edu.toronto.cs.openome_model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dependency</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.toronto.cs.openome_model.Dependency#getDependencyFrom <em>Dependency From</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Dependency#getDependencyTo <em>Dependency To</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Dependency#getTrust <em>Trust</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Dependency#getLabel <em>Label</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Dependency#getModel <em>Model</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.toronto.cs.openome_model.openome_modelPackage#getDependency()
 * @model
 * @generated
 */
public interface Dependency extends Link {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright 2001-2008 University of Toronto";

	/**
	 * Returns the value of the '<em><b>Dependency From</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link edu.toronto.cs.openome_model.Dependable#getDependencyTo <em>Dependency To</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dependency From</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dependency From</em>' reference.
	 * @see #setDependencyFrom(Dependable)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getDependency_DependencyFrom()
	 * @see edu.toronto.cs.openome_model.Dependable#getDependencyTo
	 * @model opposite="dependencyTo"
	 * @generated
	 */
	Dependable getDependencyFrom();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Dependency#getDependencyFrom <em>Dependency From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dependency From</em>' reference.
	 * @see #getDependencyFrom()
	 * @generated
	 */
	void setDependencyFrom(Dependable value);

	/**
	 * Returns the value of the '<em><b>Dependency To</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link edu.toronto.cs.openome_model.Dependable#getDependencyFrom <em>Dependency From</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dependency To</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dependency To</em>' reference.
	 * @see #setDependencyTo(Dependable)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getDependency_DependencyTo()
	 * @see edu.toronto.cs.openome_model.Dependable#getDependencyFrom
	 * @model opposite="dependencyFrom"
	 * @generated
	 */
	Dependable getDependencyTo();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Dependency#getDependencyTo <em>Dependency To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dependency To</em>' reference.
	 * @see #getDependencyTo()
	 * @generated
	 */
	void setDependencyTo(Dependable value);

	/**
	 * Returns the value of the '<em><b>Trust</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trust</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trust</em>' attribute.
	 * @see #setTrust(float)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getDependency_Trust()
	 * @model
	 * @generated
	 */
	float getTrust();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Dependency#getTrust <em>Trust</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trust</em>' attribute.
	 * @see #getTrust()
	 * @generated
	 */
	void setTrust(float value);

	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getDependency_Label()
	 * @model
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Dependency#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

	/**
	 * Returns the value of the '<em><b>Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.toronto.cs.openome_model.Model#getDependencies <em>Dependencies</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model</em>' container reference.
	 * @see #setModel(Model)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getDependency_Model()
	 * @see edu.toronto.cs.openome_model.Model#getDependencies
	 * @model opposite="dependencies"
	 * @generated
	 */
	Model getModel();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Dependency#getModel <em>Model</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' container reference.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(Model value);

} // Dependency

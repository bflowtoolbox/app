/**
 * Copyright 2001-2008 University of Toronto
 *
 * $Id$
 */
package edu.toronto.cs.openome_model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dependable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.toronto.cs.openome_model.Dependable#getDependencyFrom <em>Dependency From</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Dependable#getDependencyTo <em>Dependency To</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.toronto.cs.openome_model.openome_modelPackage#getDependable()
 * @model abstract="true"
 * @generated
 */
public interface Dependable extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright 2001-2008 University of Toronto";

	/**
	 * Returns the value of the '<em><b>Dependency From</b></em>' reference list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.Dependency}.
	 * It is bidirectional and its opposite is '{@link edu.toronto.cs.openome_model.Dependency#getDependencyTo <em>Dependency To</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dependency From</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dependency From</em>' reference list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getDependable_DependencyFrom()
	 * @see edu.toronto.cs.openome_model.Dependency#getDependencyTo
	 * @model opposite="dependencyTo"
	 * @generated
	 */
	EList<Dependency> getDependencyFrom();

	/**
	 * Returns the value of the '<em><b>Dependency To</b></em>' reference list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.Dependency}.
	 * It is bidirectional and its opposite is '{@link edu.toronto.cs.openome_model.Dependency#getDependencyFrom <em>Dependency From</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dependency To</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dependency To</em>' reference list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getDependable_DependencyTo()
	 * @see edu.toronto.cs.openome_model.Dependency#getDependencyFrom
	 * @model opposite="dependencyFrom"
	 * @generated
	 */
	EList<Dependency> getDependencyTo();

} // Dependable

/**
 * Copyright 2001-2008 University of Toronto
 *
 * $Id$
 */
package edu.toronto.cs.openome_model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Actor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.toronto.cs.openome_model.Actor#getIs_a <em>Is a</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Actor#getIs_part_of <em>Is part of</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Actor#getBelieves <em>Believes</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.toronto.cs.openome_model.openome_modelPackage#getActor()
 * @model
 * @generated
 */
public interface Actor extends Container {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright 2001-2008 University of Toronto";

	/**
	 * Returns the value of the '<em><b>Is a</b></em>' reference list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.Actor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is a</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is a</em>' reference list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getActor_Is_a()
	 * @model
	 * @generated
	 */
	EList<Actor> getIs_a();

	/**
	 * Returns the value of the '<em><b>Is part of</b></em>' reference list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.Actor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is part of</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is part of</em>' reference list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getActor_Is_part_of()
	 * @model
	 * @generated
	 */
	EList<Actor> getIs_part_of();

	/**
	 * Returns the value of the '<em><b>Believes</b></em>' reference list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.Belief}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Believes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Believes</em>' reference list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getActor_Believes()
	 * @model
	 * @generated
	 */
	EList<Belief> getBelieves();

} // Actor

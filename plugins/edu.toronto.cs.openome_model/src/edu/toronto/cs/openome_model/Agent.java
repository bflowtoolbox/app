/**
 * Copyright 2001-2008 University of Toronto
 *
 * $Id$
 */
package edu.toronto.cs.openome_model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Agent</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.toronto.cs.openome_model.Agent#getPlays <em>Plays</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Agent#getOccupies <em>Occupies</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Agent#getIns <em>Ins</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.toronto.cs.openome_model.openome_modelPackage#getAgent()
 * @model
 * @generated
 */
public interface Agent extends Container {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright 2001-2008 University of Toronto";

	/**
	 * Returns the value of the '<em><b>Plays</b></em>' reference list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.Role}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Plays</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Plays</em>' reference list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getAgent_Plays()
	 * @model
	 * @generated
	 */
	EList<Role> getPlays();

	/**
	 * Returns the value of the '<em><b>Occupies</b></em>' reference list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.Position}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Occupies</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Occupies</em>' reference list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getAgent_Occupies()
	 * @model
	 * @generated
	 */
	EList<Position> getOccupies();

	/**
	 * Returns the value of the '<em><b>Ins</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ins</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ins</em>' reference.
	 * @see #setIns(Agent)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getAgent_Ins()
	 * @model
	 * @generated
	 */
	Agent getIns();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Agent#getIns <em>Ins</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ins</em>' reference.
	 * @see #getIns()
	 * @generated
	 */
	void setIns(Agent value);

} // Agent

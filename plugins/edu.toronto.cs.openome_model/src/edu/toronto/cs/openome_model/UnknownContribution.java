/**
 * Copyright 2001-2008 University of Toronto
 *
 * $Id$
 */
package edu.toronto.cs.openome_model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unknown Contribution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.toronto.cs.openome_model.UnknownContribution#getContributionType <em>Contribution Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.toronto.cs.openome_model.openome_modelPackage#getUnknownContribution()
 * @model
 * @generated
 */
public interface UnknownContribution extends Contribution {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright 2001-2008 University of Toronto";

	/**
	 * Returns the value of the '<em><b>Contribution Type</b></em>' attribute.
	 * The default value is <code>"Unknown"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contribution Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contribution Type</em>' attribute.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getUnknownContribution_ContributionType()
	 * @model default="Unknown" required="true" changeable="false"
	 * @generated
	 */
	String getContributionType();

} // UnknownContribution

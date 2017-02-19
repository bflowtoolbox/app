/**
 * Copyright 2001-2008 University of Toronto
 *
 * $Id$
 */
package edu.toronto.cs.openome_model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Contribution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.toronto.cs.openome_model.Contribution#getTarget <em>Target</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Contribution#getModel <em>Model</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Contribution#getSource <em>Source</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Contribution#getGoal_model_symmetry <em>Goal model symmetry</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.toronto.cs.openome_model.openome_modelPackage#getContribution()
 * @model
 * @generated
 */
public interface Contribution extends Link {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright 2001-2008 University of Toronto";

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link edu.toronto.cs.openome_model.Intention#getContributesFrom <em>Contributes From</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Intention)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getContribution_Target()
	 * @see edu.toronto.cs.openome_model.Intention#getContributesFrom
	 * @model opposite="contributesFrom"
	 * @generated
	 */
	Intention getTarget();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Contribution#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Intention value);

	/**
	 * Returns the value of the '<em><b>Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.toronto.cs.openome_model.Model#getContributions <em>Contributions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model</em>' container reference.
	 * @see #setModel(Model)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getContribution_Model()
	 * @see edu.toronto.cs.openome_model.Model#getContributions
	 * @model opposite="contributions"
	 * @generated
	 */
	Model getModel();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Contribution#getModel <em>Model</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' container reference.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(Model value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link edu.toronto.cs.openome_model.Intention#getContributesTo <em>Contributes To</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(Intention)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getContribution_Source()
	 * @see edu.toronto.cs.openome_model.Intention#getContributesTo
	 * @model opposite="contributesTo"
	 * @generated
	 */
	Intention getSource();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Contribution#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Intention value);

	/**
	 * Returns the value of the '<em><b>Goal model symmetry</b></em>' attribute.
	 * The literals are from the enumeration {@link edu.toronto.cs.openome_model.GoalModelingContributionSymmetry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Goal model symmetry</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Goal model symmetry</em>' attribute.
	 * @see edu.toronto.cs.openome_model.GoalModelingContributionSymmetry
	 * @see #setGoal_model_symmetry(GoalModelingContributionSymmetry)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getContribution_Goal_model_symmetry()
	 * @model
	 * @generated
	 */
	GoalModelingContributionSymmetry getGoal_model_symmetry();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Contribution#getGoal_model_symmetry <em>Goal model symmetry</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Goal model symmetry</em>' attribute.
	 * @see edu.toronto.cs.openome_model.GoalModelingContributionSymmetry
	 * @see #getGoal_model_symmetry()
	 * @generated
	 */
	void setGoal_model_symmetry(GoalModelingContributionSymmetry value);

	/**
	 * @generated NOT
	 */
	String getContributionType();

} // Contribution

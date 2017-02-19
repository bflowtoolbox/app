/**
 * Copyright 2001-2008 University of Toronto
 *
 * $Id$
 */
package edu.toronto.cs.openome_model;

import org.eclipse.emf.common.util.BasicEList;
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
 *   <li>{@link edu.toronto.cs.openome_model.Model#getName <em>Name</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Model#getIntentions <em>Intentions</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Model#getContributions <em>Contributions</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Model#getDependencies <em>Dependencies</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Model#getDecompositions <em>Decompositions</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Model#getContainers <em>Containers</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Model#getCorrelations <em>Correlations</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Model#getAssociations <em>Associations</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Model#getAlternatives <em>Alternatives</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.toronto.cs.openome_model.openome_modelPackage#getModel()
 * @model
 * @generated
 */
public interface Model extends EObject {
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
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getModel_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Model#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Intentions</b></em>' containment reference list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.Intention}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Intentions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Intentions</em>' containment reference list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getModel_Intentions()
	 * @model containment="true"
	 * @generated
	 */
	EList<Intention> getIntentions();

	/**
	 * Returns the value of the '<em><b>Contributions</b></em>' containment reference list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.Contribution}.
	 * It is bidirectional and its opposite is '{@link edu.toronto.cs.openome_model.Contribution#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contributions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contributions</em>' containment reference list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getModel_Contributions()
	 * @see edu.toronto.cs.openome_model.Contribution#getModel
	 * @model opposite="model" containment="true"
	 * @generated
	 */
	EList<Contribution> getContributions();

	/**
	 * Returns the value of the '<em><b>Dependencies</b></em>' containment reference list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.Dependency}.
	 * It is bidirectional and its opposite is '{@link edu.toronto.cs.openome_model.Dependency#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dependencies</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dependencies</em>' containment reference list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getModel_Dependencies()
	 * @see edu.toronto.cs.openome_model.Dependency#getModel
	 * @model opposite="model" containment="true"
	 * @generated
	 */
	EList<Dependency> getDependencies();

	/**
	 * Returns the value of the '<em><b>Decompositions</b></em>' containment reference list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.Decomposition}.
	 * It is bidirectional and its opposite is '{@link edu.toronto.cs.openome_model.Decomposition#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Decompositions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Decompositions</em>' containment reference list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getModel_Decompositions()
	 * @see edu.toronto.cs.openome_model.Decomposition#getModel
	 * @model opposite="model" containment="true"
	 * @generated
	 */
	EList<Decomposition> getDecompositions();

	/**
	 * Returns the value of the '<em><b>Containers</b></em>' containment reference list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.Container}.
	 * It is bidirectional and its opposite is '{@link edu.toronto.cs.openome_model.Container#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Containers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containers</em>' containment reference list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getModel_Containers()
	 * @see edu.toronto.cs.openome_model.Container#getModel
	 * @model opposite="model" containment="true"
	 * @generated
	 */
	EList<Container> getContainers();

	/**
	 * Returns the value of the '<em><b>Correlations</b></em>' reference list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.Correlation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Correlations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Correlations</em>' reference list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getModel_Correlations()
	 * @model
	 * @generated
	 */
	EList<Correlation> getCorrelations();

	/**
	 * Returns the value of the '<em><b>Associations</b></em>' containment reference list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.Association}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Associations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Associations</em>' containment reference list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getModel_Associations()
	 * @model containment="true"
	 * @generated
	 */
	EList<Association> getAssociations();

	/**
	 * Returns the value of the '<em><b>Alternatives</b></em>' containment reference list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.Alternative}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Alternatives</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Alternatives</em>' containment reference list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getModel_Alternatives()
	 * @model containment="true"
	 * @generated
	 */
	EList<Alternative> getAlternatives();
	
	/** @author jenhork
	 * The default getIntentions method only returns dependums.  This one should return all intentions in the model.
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<Intention> getAllIntentions();
	
	/** @author jenhork
	 * Get leaves in the model
	 * @generated NOT
	 */
	public EList<Intention> getLeaves();
	
	/** @author jenhork
	 * Get roots in the model
	 * @generated NOT
	 */
	public EList<Intention> getRoots();
	
	/** @author jenhork
	 * Return number of intentions in the model 
	 * String type can be All, Softgoal, Goal, Resource, Task
	 * @generated NOT
	 */
	public int getNumIntentions(String type);
	
	/** @author denys
	 * Return the number of actors of specified type in the model
	 * @param type one of All, Actor, Role, Position, Agent
	 * @return the number of elements of specified type in the model
	 * @generated NOT
	 */
	public int getNumActors(String type);
	
	/** @author denys
	 * Return the number of link of specified type in the model
	 * @param type one of All, Contribution, Dependency, Means-Ends, Decomposition, Association
	 * @return the number of links of specified type in the model
	 */
	public int getNumLinks(String type);

} // Model

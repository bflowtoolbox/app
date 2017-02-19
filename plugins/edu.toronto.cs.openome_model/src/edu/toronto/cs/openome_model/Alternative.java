/**
 * Copyright 2001-2008 University of Toronto
 *
 * $Id$
 */
package edu.toronto.cs.openome_model;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.EMap;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import edu.toronto.cs.openome_model.impl.ModelImpl;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Alternative</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.toronto.cs.openome_model.Alternative#getName <em>Name</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Alternative#getDescription <em>Description</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Alternative#getIntentions <em>Intentions</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Alternative#getEvalLabels <em>Eval Labels</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Alternative#getDirection <em>Direction</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.toronto.cs.openome_model.openome_modelPackage#getAlternative()
 * @model
 * @generated
 */
public interface Alternative extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright 2001-2008 University of Toronto";
	
	/**
	 * 
	 * @return A <code>boolean</code> which denotes whether this <code>Alternative</code>
	 * has been affected by a change in <code>HumanJudgmentsView</code> 
	 */
	boolean getAffectedStatus(); 
	
	/**
	 * Sets the affected status of this <code>Alternative</code> 
	 */
	void setAffectedStatus(boolean b); 

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * The default value is <code>" "</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getAlternative_Name()
	 * @model default=" "
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Alternative#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * The default value is <code>" "</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getAlternative_Description()
	 * @model default=" "
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Alternative#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Intentions</b></em>' reference list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.Intention}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Intentions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Intentions</em>' reference list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getAlternative_Intentions()
	 * @model
	 * @generated
	 */
	EList<Intention> getIntentions();

	/**
	 * Returns the value of the '<em><b>Eval Labels</b></em>' attribute list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.EvaluationLabel}.
	 * The literals are from the enumeration {@link edu.toronto.cs.openome_model.EvaluationLabel}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Eval Labels</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Eval Labels</em>' attribute list.
	 * @see edu.toronto.cs.openome_model.EvaluationLabel
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getAlternative_EvalLabels()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	EList<EvaluationLabel> getEvalLabels();

	/**
	 * Returns the value of the '<em><b>Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Direction</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Direction</em>' attribute.
	 * @see #setDirection(String)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getAlternative_Direction()
	 * @model
	 * @generated
	 */
	String getDirection();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Alternative#getDirection <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Direction</em>' attribute.
	 * @see #getDirection()
	 * @generated
	 */
	void setDirection(String value);

	/**
	 * @generated NOT
	 */
	void addIntention(EList<Intention> i, ModelImpl mi);
	
	/**
	 * @generated NOT
	 */
	HashMap<Intention, EvaluationLabel> getIntentionLabels();

} // Alternative

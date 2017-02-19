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
 * A representation of the model object '<em><b>Human Judgment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.toronto.cs.openome_model.HumanJudgment#getResultLabel <em>Result Label</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.HumanJudgment#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.HumanJudgment#getLabelBag <em>Label Bag</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.toronto.cs.openome_model.openome_modelPackage#getHumanJudgment()
 * @model
 * @generated
 */
public interface HumanJudgment extends EObject {
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
	 * Returns the value of the '<em><b>Result Label</b></em>' attribute.
	 * The literals are from the enumeration {@link edu.toronto.cs.openome_model.EvaluationLabel}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result Label</em>' attribute.
	 * @see edu.toronto.cs.openome_model.EvaluationLabel
	 * @see #setResultLabel(EvaluationLabel)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getHumanJudgment_ResultLabel()
	 * @model
	 * @generated
	 */
	EvaluationLabel getResultLabel();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.HumanJudgment#getResultLabel <em>Result Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Result Label</em>' attribute.
	 * @see edu.toronto.cs.openome_model.EvaluationLabel
	 * @see #getResultLabel()
	 * @generated
	 */
	void setResultLabel(EvaluationLabel value);

	/**
	 * Returns the value of the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enabled</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enabled</em>' attribute.
	 * @see #setEnabled(boolean)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getHumanJudgment_Enabled()
	 * @model
	 * @generated
	 */
	boolean isEnabled();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.HumanJudgment#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #isEnabled()
	 * @generated
	 */
	void setEnabled(boolean value);

	/**
	 * Returns the value of the '<em><b>Label Bag</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label Bag</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label Bag</em>' containment reference.
	 * @see #setLabelBag(LabelBag)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getHumanJudgment_LabelBag()
	 * @model containment="true"
	 * @generated
	 */
	LabelBag getLabelBag();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.HumanJudgment#getLabelBag <em>Label Bag</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label Bag</em>' containment reference.
	 * @see #getLabelBag()
	 * @generated
	 */
	void setLabelBag(LabelBag value);
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	HumanJudgment findOrImplies(LabelBag value);
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String toUIString();

} // HumanJudgment

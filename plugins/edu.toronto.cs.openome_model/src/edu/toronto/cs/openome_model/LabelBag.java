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
 * A representation of the model object '<em><b>Label Bag</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.toronto.cs.openome_model.LabelBag#getLabelBagIntentions <em>Label Bag Intentions</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.LabelBag#getLabelBagEvalLabels <em>Label Bag Eval Labels</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.LabelBag#isToResolve <em>To Resolve</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.LabelBag#isAllPositive <em>All Positive</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.LabelBag#isAllNegative <em>All Negative</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.LabelBag#isHasFullPositive <em>Has Full Positive</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.LabelBag#isHasFullNegative <em>Has Full Negative</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.LabelBag#isHasUnknown <em>Has Unknown</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.LabelBag#isHasConflict <em>Has Conflict</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.LabelBag#isAllUnknown <em>All Unknown</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.LabelBag#isAllConflict <em>All Conflict</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.toronto.cs.openome_model.openome_modelPackage#getLabelBag()
 * @model
 * @generated
 */
public interface LabelBag extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * changed from protected to public for testing purposes
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	//public LabelBagImpl(LabelBag copy);
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright 2001-2008 University of Toronto";	

	/**
	 * Returns the value of the '<em><b>Label Bag Intentions</b></em>' reference list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.Intention}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label Bag Intentions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label Bag Intentions</em>' reference list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getLabelBag_LabelBagIntentions()
	 * @model
	 * @generated
	 */
	EList<Intention> getLabelBagIntentions();

	/**
	 * Returns the value of the '<em><b>Label Bag Eval Labels</b></em>' attribute list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.EvaluationLabel}.
	 * The literals are from the enumeration {@link edu.toronto.cs.openome_model.EvaluationLabel}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label Bag Eval Labels</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label Bag Eval Labels</em>' attribute list.
	 * @see edu.toronto.cs.openome_model.EvaluationLabel
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getLabelBag_LabelBagEvalLabels()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	EList<EvaluationLabel> getLabelBagEvalLabels();
	
	/**
	 * Returns the value of the '<em><b>To Resolve</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To Resolve</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To Resolve</em>' attribute.
	 * @see #setToResolve(boolean)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getLabelBag_ToResolve()
	 * @model
	 * @generated
	 */
	boolean isToResolve();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.LabelBag#isToResolve <em>To Resolve</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To Resolve</em>' attribute.
	 * @see #isToResolve()
	 * @generated
	 */
	void setToResolve(boolean value);

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.LabelBag#isToResolve <em>To Resolve</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To Resolve</em>' attribute.
	 * @see #isToResolve()
	 * @generated NOT
	 */
	void setToResolved();

	/**
	 * Returns the value of the '<em><b>To Resolve</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To Resolve</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To Resolve</em>' attribute.
	 * @see #setToResolve(boolean)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getLabelBag_ToResolve()
	 * @model
	 * @generated NOT
	 */
	boolean needResolve();

	/**
	 * Returns the value of the '<em><b>All Positive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All Positive</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Positive</em>' attribute.
	 * @see #setAllPositive(boolean)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getLabelBag_AllPositive()
	 * @model
	 * @generated
	 */
	boolean isAllPositive();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.LabelBag#isAllPositive <em>All Positive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>All Positive</em>' attribute.
	 * @see #isAllPositive()
	 * @generated
	 */
	void setAllPositive(boolean value);

	/**
	 * Returns the value of the '<em><b>All Negative</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All Negative</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Negative</em>' attribute.
	 * @see #setAllNegative(boolean)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getLabelBag_AllNegative()
	 * @model
	 * @generated
	 */
	boolean isAllNegative();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.LabelBag#isAllNegative <em>All Negative</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>All Negative</em>' attribute.
	 * @see #isAllNegative()
	 * @generated
	 */
	void setAllNegative(boolean value);

	/**
	 * Returns the value of the '<em><b>Has Full Positive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Full Positive</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Full Positive</em>' attribute.
	 * @see #setHasFullPositive(boolean)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getLabelBag_HasFullPositive()
	 * @model
	 * @generated
	 */
	boolean isHasFullPositive();

	/**
	 * Returns the value of the '<em><b>Has Full Postive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Full Postive</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Full Postive</em>' attribute.
	 * @see #setHasFullPostive(boolean)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getLabelBag_HasFullPostive()
	 * @model
	 * @generated NOT
	 */
	boolean hasFullPositive();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.LabelBag#isHasFullPositive <em>Has Full Positive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Full Positive</em>' attribute.
	 * @see #isHasFullPositive()
	 * @generated
	 */
	void setHasFullPositive(boolean value);

	
	/**
	 * Returns the value of the '<em><b>Has Full Negative</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Full Negative</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Full Negative</em>' attribute.
	 * @see #setHasFullNegative(boolean)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getLabelBag_HasFullNegative()
	 * @model
	 * @generated
	 */
	boolean isHasFullNegative();

	/**
	 * Returns the value of the '<em><b>Has Full Negative</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Full Negative</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Full Negative</em>' attribute.
	 * @see #setHasFullNegative(boolean)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getLabelBag_HasFullNegative()
	 * @model
	 * @generated NOT
	 */
	boolean hasFullNegative();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.LabelBag#isHasFullNegative <em>Has Full Negative</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Full Negative</em>' attribute.
	 * @see #isHasFullNegative()
	 * @generated
	 */
	void setHasFullNegative(boolean value);

	/**
	 * Returns the value of the '<em><b>Has Unknown</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Unknown</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Unknown</em>' attribute.
	 * @see #setHasUnknown(boolean)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getLabelBag_HasUnknown()
	 * @model
	 * @generated
	 */
	boolean isHasUnknown();

	/**
	 * Returns the value of the '<em><b>Has Unknown</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Unknown</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Unknown</em>' attribute.
	 * @see #setHasUnknown(boolean)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getLabelBag_HasUnknown()
	 * @model
	 * @generated NOT
	 */
	boolean hasUnknown();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.LabelBag#isHasUnknown <em>Has Unknown</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Unknown</em>' attribute.
	 * @see #isHasUnknown()
	 * @generated
	 */
	void setHasUnknown(boolean value);

	/**
	 * Returns the value of the '<em><b>Has Conflict</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Conflict</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Conflict</em>' attribute.
	 * @see #setHasConflict(boolean)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getLabelBag_HasConflict()
	 * @model
	 * @generated
	 */
	boolean isHasConflict();

	/**
	 * Returns the value of the '<em><b>Has Conflict</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Conflict</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Conflict</em>' attribute.
	 * @see #setHasConflict(boolean)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getLabelBag_HasConflict()
	 * @model
	 * @generated NOT
	 */
	boolean hasConflict();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.LabelBag#isHasConflict <em>Has Conflict</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Has Conflict</em>' attribute.
	 * @see #isHasConflict()
	 * @generated
	 */
	void setHasConflict(boolean value);

	/**
	 * Returns the value of the '<em><b>All Unknown</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All Unknown</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Unknown</em>' attribute.
	 * @see #setAllUnknown(boolean)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getLabelBag_AllUnknown()
	 * @model
	 * @generated
	 */
	boolean isAllUnknown();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.LabelBag#isAllUnknown <em>All Unknown</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>All Unknown</em>' attribute.
	 * @see #isAllUnknown()
	 * @generated
	 */
	void setAllUnknown(boolean value);

	/**
	 * Returns the value of the '<em><b>All Conflict</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All Conflict</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Conflict</em>' attribute.
	 * @see #setAllConflict(boolean)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getLabelBag_AllConflict()
	 * @model
	 * @generated
	 */
	boolean isAllConflict();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.LabelBag#isAllConflict <em>All Conflict</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>All Conflict</em>' attribute.
	 * @see #isAllConflict()
	 * @generated
	 */
	void setAllConflict(boolean value);
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void printBag();
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int addToLabelBag(Intention i, EvaluationLabel label);
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int getBagSize();
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean equals(LabelBag bag);
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public LabelBag bagDiff(LabelBag bag);
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Object[] toArray();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int size();
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean removeFromLabelBag(Intention intn, EvaluationLabel label);
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String toUIString();
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void clearLabelBag();
	

} // LabelBag

/**
 * Copyright 2001-2008 University of Toronto
 *
 * $Id$
 */
package edu.toronto.cs.openome_model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Intention</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.toronto.cs.openome_model.Intention#getName <em>Name</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Intention#getSystem <em>System</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Intention#getBoundary <em>Boundary</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Intention#getExclusive <em>Exclusive</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Intention#getSequential <em>Sequential</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Intention#getParallel <em>Parallel</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Intention#getProperty <em>Property</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Intention#getDecompositions <em>Decompositions</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Intention#getParentDecompositions <em>Parent Decompositions</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Intention#getContainer <em>Container</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Intention#getQualitativeReasoningCombinedLabel <em>Qualitative Reasoning Combined Label</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Intention#getQualitativeReasoningSatisfiedLabel <em>Qualitative Reasoning Satisfied Label</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Intention#getQualitativeReasoningDenialLabel <em>Qualitative Reasoning Denial Label</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Intention#getQuantitativeReasoningCombinedLabel <em>Quantitative Reasoning Combined Label</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Intention#getQuantitativeReasoningDeniedLabel <em>Quantitative Reasoning Denied Label</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Intention#getQuantitativeReasoningSatisfiedLabel <em>Quantitative Reasoning Satisfied Label</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Intention#getContributesTo <em>Contributes To</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Intention#getContributesFrom <em>Contributes From</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Intention#getLabelBag <em>Label Bag</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Intention#getInitialEvalLabel <em>Initial Eval Label</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Intention#getReverseLabelBag <em>Reverse Label Bag</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.Intention#getHumanJudgments <em>Human Judgments</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.toronto.cs.openome_model.openome_modelPackage#getIntention()
 * @model
 * @generated
 */
public interface Intention extends Dependable {
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
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getIntention_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Intention#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>System</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>System</em>' attribute.
	 * @see #setSystem(Boolean)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getIntention_System()
	 * @model default="true"
	 * @generated
	 */
	Boolean getSystem();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Intention#getSystem <em>System</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>System</em>' attribute.
	 * @see #getSystem()
	 * @generated
	 */
	void setSystem(Boolean value);

	/**
	 * Returns the value of the '<em><b>Boundary</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Boundary</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Boundary</em>' attribute.
	 * @see #setBoundary(Boolean)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getIntention_Boundary()
	 * @model default="false"
	 * @generated
	 */
	Boolean getBoundary();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Intention#getBoundary <em>Boundary</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Boundary</em>' attribute.
	 * @see #getBoundary()
	 * @generated
	 */
	void setBoundary(Boolean value);

	/**
	 * Returns the value of the '<em><b>Exclusive</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exclusive</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exclusive</em>' attribute.
	 * @see #setExclusive(Boolean)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getIntention_Exclusive()
	 * @model default="true"
	 * @generated
	 */
	Boolean getExclusive();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Intention#getExclusive <em>Exclusive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exclusive</em>' attribute.
	 * @see #getExclusive()
	 * @generated
	 */
	void setExclusive(Boolean value);

	/**
	 * Returns the value of the '<em><b>Sequential</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sequential</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sequential</em>' attribute.
	 * @see #setSequential(Boolean)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getIntention_Sequential()
	 * @model default="true"
	 * @generated
	 */
	Boolean getSequential();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Intention#getSequential <em>Sequential</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sequential</em>' attribute.
	 * @see #getSequential()
	 * @generated
	 */
	void setSequential(Boolean value);

	/**
	 * Returns the value of the '<em><b>Parallel</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parallel</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parallel</em>' attribute.
	 * @see #setParallel(Boolean)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getIntention_Parallel()
	 * @model default="false"
	 * @generated
	 */
	Boolean getParallel();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Intention#getParallel <em>Parallel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parallel</em>' attribute.
	 * @see #getParallel()
	 * @generated
	 */
	void setParallel(Boolean value);

	/**
	 * Returns the value of the '<em><b>Property</b></em>' containment reference list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.Property}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property</em>' containment reference list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getIntention_Property()
	 * @model containment="true"
	 * @generated
	 */
	EList<Property> getProperty();

	/**
	 * Returns the value of the '<em><b>Decompositions</b></em>' reference list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.Decomposition}.
	 * It is bidirectional and its opposite is '{@link edu.toronto.cs.openome_model.Decomposition#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Decompositions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * 
	 * This doesn't seem to work, it won't return child decompositions
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Decompositions</em>' reference list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getIntention_Decompositions()
	 * @see edu.toronto.cs.openome_model.Decomposition#getSource
	 * @model opposite="source"
	 * @generated
	 */
	EList<Decomposition> getDecompositions();

	/**
	 * Returns the value of the '<em><b>Parent Decompositions</b></em>' reference list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.Decomposition}.
	 * It is bidirectional and its opposite is '{@link edu.toronto.cs.openome_model.Decomposition#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Decompositions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * 
	 * This doesn't do what you think it will do, it seems to just return null.
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Decompositions</em>' reference list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getIntention_ParentDecompositions()
	 * @see edu.toronto.cs.openome_model.Decomposition#getTarget
	 * @model opposite="target"
	 * @generated
	 */
	EList<Decomposition> getParentDecompositions();

	/**
	 * Returns the value of the '<em><b>Container</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.toronto.cs.openome_model.Container#getIntentions <em>Intentions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Container</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Container</em>' container reference.
	 * @see #setContainer(Container)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getIntention_Container()
	 * @see edu.toronto.cs.openome_model.Container#getIntentions
	 * @model opposite="intentions"
	 * @generated
	 */
	Container getContainer();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Intention#getContainer <em>Container</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Container</em>' container reference.
	 * @see #getContainer()
	 * @generated
	 */
	void setContainer(Container value);

	/**
	 * Returns the value of the '<em><b>Qualitative Reasoning Combined Label</b></em>' attribute.
	 * The literals are from the enumeration {@link edu.toronto.cs.openome_model.EvaluationLabel}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qualitative Reasoning Combined Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qualitative Reasoning Combined Label</em>' attribute.
	 * @see edu.toronto.cs.openome_model.EvaluationLabel
	 * @see #setQualitativeReasoningCombinedLabel(EvaluationLabel)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getIntention_QualitativeReasoningCombinedLabel()
	 * @model
	 * @generated
	 */
	EvaluationLabel getQualitativeReasoningCombinedLabel();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Intention#getQualitativeReasoningCombinedLabel <em>Qualitative Reasoning Combined Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qualitative Reasoning Combined Label</em>' attribute.
	 * @see edu.toronto.cs.openome_model.EvaluationLabel
	 * @see #getQualitativeReasoningCombinedLabel()
	 * @generated
	 */
	void setQualitativeReasoningCombinedLabel(EvaluationLabel value);

	/**
	 * Returns the value of the '<em><b>Qualitative Reasoning Satisfied Label</b></em>' attribute.
	 * The literals are from the enumeration {@link edu.toronto.cs.openome_model.EvaluationLabel}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qualitative Reasoning Satisfied Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qualitative Reasoning Satisfied Label</em>' attribute.
	 * @see edu.toronto.cs.openome_model.EvaluationLabel
	 * @see #setQualitativeReasoningSatisfiedLabel(EvaluationLabel)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getIntention_QualitativeReasoningSatisfiedLabel()
	 * @model
	 * @generated
	 */
	EvaluationLabel getQualitativeReasoningSatisfiedLabel();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Intention#getQualitativeReasoningSatisfiedLabel <em>Qualitative Reasoning Satisfied Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qualitative Reasoning Satisfied Label</em>' attribute.
	 * @see edu.toronto.cs.openome_model.EvaluationLabel
	 * @see #getQualitativeReasoningSatisfiedLabel()
	 * @generated
	 */
	void setQualitativeReasoningSatisfiedLabel(EvaluationLabel value);

	/**
	 * Returns the value of the '<em><b>Qualitative Reasoning Denial Label</b></em>' attribute.
	 * The literals are from the enumeration {@link edu.toronto.cs.openome_model.EvaluationLabel}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qualitative Reasoning Denial Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qualitative Reasoning Denial Label</em>' attribute.
	 * @see edu.toronto.cs.openome_model.EvaluationLabel
	 * @see #setQualitativeReasoningDenialLabel(EvaluationLabel)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getIntention_QualitativeReasoningDenialLabel()
	 * @model
	 * @generated
	 */
	EvaluationLabel getQualitativeReasoningDenialLabel();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Intention#getQualitativeReasoningDenialLabel <em>Qualitative Reasoning Denial Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qualitative Reasoning Denial Label</em>' attribute.
	 * @see edu.toronto.cs.openome_model.EvaluationLabel
	 * @see #getQualitativeReasoningDenialLabel()
	 * @generated
	 */
	void setQualitativeReasoningDenialLabel(EvaluationLabel value);

	/**
	 * Returns the value of the '<em><b>Quantitative Reasoning Combined Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Quantitative Reasoning Combined Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quantitative Reasoning Combined Label</em>' attribute.
	 * @see #setQuantitativeReasoningCombinedLabel(double)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getIntention_QuantitativeReasoningCombinedLabel()
	 * @model
	 * @generated
	 */
	double getQuantitativeReasoningCombinedLabel();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Intention#getQuantitativeReasoningCombinedLabel <em>Quantitative Reasoning Combined Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quantitative Reasoning Combined Label</em>' attribute.
	 * @see #getQuantitativeReasoningCombinedLabel()
	 * @generated
	 */
	void setQuantitativeReasoningCombinedLabel(double value);

	/**
	 * Returns the value of the '<em><b>Quantitative Reasoning Denied Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Quantitative Reasoning Denied Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quantitative Reasoning Denied Label</em>' attribute.
	 * @see #setQuantitativeReasoningDeniedLabel(double)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getIntention_QuantitativeReasoningDeniedLabel()
	 * @model
	 * @generated
	 */
	double getQuantitativeReasoningDeniedLabel();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Intention#getQuantitativeReasoningDeniedLabel <em>Quantitative Reasoning Denied Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quantitative Reasoning Denied Label</em>' attribute.
	 * @see #getQuantitativeReasoningDeniedLabel()
	 * @generated
	 */
	void setQuantitativeReasoningDeniedLabel(double value);

	/**
	 * Returns the value of the '<em><b>Quantitative Reasoning Satisfied Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Quantitative Reasoning Satisfied Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quantitative Reasoning Satisfied Label</em>' attribute.
	 * @see #setQuantitativeReasoningSatisfiedLabel(double)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getIntention_QuantitativeReasoningSatisfiedLabel()
	 * @model
	 * @generated
	 */
	double getQuantitativeReasoningSatisfiedLabel();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Intention#getQuantitativeReasoningSatisfiedLabel <em>Quantitative Reasoning Satisfied Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quantitative Reasoning Satisfied Label</em>' attribute.
	 * @see #getQuantitativeReasoningSatisfiedLabel()
	 * @generated
	 */
	void setQuantitativeReasoningSatisfiedLabel(double value);

	/**
	 * Returns the value of the '<em><b>Contributes To</b></em>' reference list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.Contribution}.
	 * It is bidirectional and its opposite is '{@link edu.toronto.cs.openome_model.Contribution#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contributes To</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contributes To</em>' reference list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getIntention_ContributesTo()
	 * @see edu.toronto.cs.openome_model.Contribution#getSource
	 * @model opposite="source"
	 * @generated
	 */
	EList<Contribution> getContributesTo();

	/**
	 * Returns the value of the '<em><b>Contributes From</b></em>' reference list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.Contribution}.
	 * It is bidirectional and its opposite is '{@link edu.toronto.cs.openome_model.Contribution#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contributes From</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contributes From</em>' reference list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getIntention_ContributesFrom()
	 * @see edu.toronto.cs.openome_model.Contribution#getTarget
	 * @model opposite="target"
	 * @generated
	 */
	EList<Contribution> getContributesFrom();
	
	/**
	 * Returns the value of the '<em><b>Human Judgments</b></em>' containment reference list.
	 * The list contents are of type {@link edu.toronto.cs.openome_model.HumanJudgment}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Human Judgments</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Human Judgments</em>' containment reference list.
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getIntention_HumanJudgments()
	 * @model containment="true"
	 * @generated
	 */
	EList<HumanJudgment> getHumanJudgments();

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
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getIntention_LabelBag()
	 * @model containment="true"
	 * @generated
	 */
	LabelBag getLabelBag();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Intention#getLabelBag <em>Label Bag</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label Bag</em>' containment reference.
	 * @see #getLabelBag()
	 * @generated
	 */
	void setLabelBag(LabelBag value);

	/**
	 * Returns the value of the '<em><b>Initial Eval Label</b></em>' attribute.
	 * The literals are from the enumeration {@link edu.toronto.cs.openome_model.EvaluationLabel}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initial Eval Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initial Eval Label</em>' attribute.
	 * @see edu.toronto.cs.openome_model.EvaluationLabel
	 * @see #setInitialEvalLabel(EvaluationLabel)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getIntention_InitialEvalLabel()
	 * @model
	 * @generated
	 */
	EvaluationLabel getInitialEvalLabel();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Intention#getInitialEvalLabel <em>Initial Eval Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initial Eval Label</em>' attribute.
	 * @see edu.toronto.cs.openome_model.EvaluationLabel
	 * @see #getInitialEvalLabel()
	 * @generated
	 */
	void setInitialEvalLabel(EvaluationLabel value);

	/**
	 * Returns the value of the '<em><b>Reverse Label Bag</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reverse Label Bag</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reverse Label Bag</em>' containment reference.
	 * @see #setReverseLabelBag(LabelBag)
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#getIntention_ReverseLabelBag()
	 * @model containment="true"
	 * @generated
	 */
	LabelBag getReverseLabelBag();

	/**
	 * Sets the value of the '{@link edu.toronto.cs.openome_model.Intention#getReverseLabelBag <em>Reverse Label Bag</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reverse Label Bag</em>' containment reference.
	 * @see #getReverseLabelBag()
	 * @generated
	 */
	void setReverseLabelBag(LabelBag value);

	/**
	 * @generated NOT
	 */
	EList<Decomposition> getDecompositionsFrom();
	
	/**
	 * @generated NOT
	 */
	EList<Decomposition> getDecompositionsTo();
	
	/**
	 * @generated NOT
	 */
	EList<Decomposition> getAllDecompositions();

	/**
	 * @generated NOT
	 */
	boolean isLeaf();

	/**
	 * @generated NOT
	 */
	boolean isRoot();
	
	/**
	 * @generated NOT
	 */
	EList<Intention> getChildren();
	
	/**
	 * @generated NOT
	 */
	EList<Intention> getParents();
	
	/**
	 * @generated NOT
	 */
	EList<Intention> getForwardSlice();
	
	/**
	 * @generated NOT
	 */
	EList<Intention> getBackwardSlice();
	
	/**
	 * @generated NOT
	 */
	public EList<Intention> getAllConnected();
	
	/**
	 * * @generated NOT
	 */
	public EvaluationLabel findExistingHumanJudgment();
	
	/**
	 * * @generated NOT
	 */
	public HumanJudgment addHumanJudgment(EvaluationLabel result);
	
	/**
	 * * @generated NOT
	 */
	public void addReverseJudgment(Intention intn, EvaluationLabel result);
	
	/**
	 * * @generated NOT
	 */
	public boolean backtrackReverseJudgments(HumanJudgment hj);

	/**
	 * * @generated NOT
	 */
	public boolean removeHumanJudgment(HumanJudgment humanJudgment);
	
	
	 

} // Intention

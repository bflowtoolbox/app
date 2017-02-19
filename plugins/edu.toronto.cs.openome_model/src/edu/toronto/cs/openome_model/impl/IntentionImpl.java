/**
 * Copyright 2001-2008 University of Toronto
 *
 * $Id$
 */
package edu.toronto.cs.openome_model.impl;

import edu.toronto.cs.openome_model.Container;
import edu.toronto.cs.openome_model.Contribution;
import edu.toronto.cs.openome_model.Decomposition;
import edu.toronto.cs.openome_model.Dependable;
import edu.toronto.cs.openome_model.Dependency;
import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.HumanJudgment;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.LabelBag;
import edu.toronto.cs.openome_model.Model;
import edu.toronto.cs.openome_model.Property;
import edu.toronto.cs.openome_model.openome_modelPackage;

import java.util.Collection;
import java.util.ListIterator;
import java.util.Vector;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;



/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Intention</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.toronto.cs.openome_model.impl.IntentionImpl#getName <em>Name</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.IntentionImpl#getSystem <em>System</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.IntentionImpl#getBoundary <em>Boundary</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.IntentionImpl#getExclusive <em>Exclusive</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.IntentionImpl#getSequential <em>Sequential</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.IntentionImpl#getParallel <em>Parallel</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.IntentionImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.IntentionImpl#getDecompositions <em>Decompositions</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.IntentionImpl#getParentDecompositions <em>Parent Decompositions</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.IntentionImpl#getContainer <em>Container</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.IntentionImpl#getQualitativeReasoningCombinedLabel <em>Qualitative Reasoning Combined Label</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.IntentionImpl#getQualitativeReasoningSatisfiedLabel <em>Qualitative Reasoning Satisfied Label</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.IntentionImpl#getQualitativeReasoningDenialLabel <em>Qualitative Reasoning Denial Label</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.IntentionImpl#getQuantitativeReasoningCombinedLabel <em>Quantitative Reasoning Combined Label</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.IntentionImpl#getQuantitativeReasoningDeniedLabel <em>Quantitative Reasoning Denied Label</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.IntentionImpl#getQuantitativeReasoningSatisfiedLabel <em>Quantitative Reasoning Satisfied Label</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.IntentionImpl#getContributesTo <em>Contributes To</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.IntentionImpl#getContributesFrom <em>Contributes From</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.IntentionImpl#getLabelBag <em>Label Bag</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.IntentionImpl#getInitialEvalLabel <em>Initial Eval Label</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.IntentionImpl#getReverseLabelBag <em>Reverse Label Bag</em>}</li>
 *   <li>{@link edu.toronto.cs.openome_model.impl.IntentionImpl#getHumanJudgments <em>Human Judgments</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IntentionImpl extends DependableImpl implements Intention {
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright 2001-2008 University of Toronto";

	/**
	 * The status of this Alternative - utilized for <code>AlternativesView</code> purposes 
	 */
	protected boolean status = false; 
	
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getSystem() <em>System</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSystem()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean SYSTEM_EDEFAULT = Boolean.TRUE;

	/**
	 * The cached value of the '{@link #getSystem() <em>System</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSystem()
	 * @generated
	 * @ordered
	 */
	protected Boolean system = SYSTEM_EDEFAULT;

	/**
	 * The default value of the '{@link #getBoundary() <em>Boundary</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBoundary()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean BOUNDARY_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getBoundary() <em>Boundary</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBoundary()
	 * @generated
	 * @ordered
	 */
	protected Boolean boundary = BOUNDARY_EDEFAULT;

	/**
	 * The default value of the '{@link #getExclusive() <em>Exclusive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExclusive()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean EXCLUSIVE_EDEFAULT = Boolean.TRUE;

	/**
	 * The cached value of the '{@link #getExclusive() <em>Exclusive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExclusive()
	 * @generated
	 * @ordered
	 */
	protected Boolean exclusive = EXCLUSIVE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSequential() <em>Sequential</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSequential()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean SEQUENTIAL_EDEFAULT = Boolean.TRUE;

	/**
	 * The cached value of the '{@link #getSequential() <em>Sequential</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSequential()
	 * @generated
	 * @ordered
	 */
	protected Boolean sequential = SEQUENTIAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getParallel() <em>Parallel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParallel()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean PARALLEL_EDEFAULT = Boolean.FALSE;

	/**
	 * The cached value of the '{@link #getParallel() <em>Parallel</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParallel()
	 * @generated
	 * @ordered
	 */
	protected Boolean parallel = PARALLEL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getProperty() <em>Property</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperty()
	 * @generated
	 * @ordered
	 */
	protected EList<Property> property;

	/**
	 * The cached value of the '{@link #getDecompositions() <em>Decompositions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDecompositions()
	 * @generated
	 * @ordered
	 */
	protected EList<Decomposition> decompositions;

	/**
	 * The cached value of the '{@link #getParentDecompositions() <em>Parent Decompositions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentDecompositions()
	 * @generated
	 * @ordered
	 */
	protected EList<Decomposition> parentDecompositions;

	/**
	 * The default value of the '{@link #getQualitativeReasoningCombinedLabel() <em>Qualitative Reasoning Combined Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQualitativeReasoningCombinedLabel()
	 * @generated
	 * @ordered
	 */
	protected static final EvaluationLabel QUALITATIVE_REASONING_COMBINED_LABEL_EDEFAULT = EvaluationLabel.NONE;

	/**
	 * The cached value of the '{@link #getQualitativeReasoningCombinedLabel() <em>Qualitative Reasoning Combined Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQualitativeReasoningCombinedLabel()
	 * @generated
	 * @ordered
	 */
	protected EvaluationLabel qualitativeReasoningCombinedLabel = QUALITATIVE_REASONING_COMBINED_LABEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getQualitativeReasoningSatisfiedLabel() <em>Qualitative Reasoning Satisfied Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQualitativeReasoningSatisfiedLabel()
	 * @generated
	 * @ordered
	 */
	protected static final EvaluationLabel QUALITATIVE_REASONING_SATISFIED_LABEL_EDEFAULT = EvaluationLabel.NONE;

	/**
	 * The cached value of the '{@link #getQualitativeReasoningSatisfiedLabel() <em>Qualitative Reasoning Satisfied Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQualitativeReasoningSatisfiedLabel()
	 * @generated
	 * @ordered
	 */
	protected EvaluationLabel qualitativeReasoningSatisfiedLabel = QUALITATIVE_REASONING_SATISFIED_LABEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getQualitativeReasoningDenialLabel() <em>Qualitative Reasoning Denial Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQualitativeReasoningDenialLabel()
	 * @generated
	 * @ordered
	 */
	protected static final EvaluationLabel QUALITATIVE_REASONING_DENIAL_LABEL_EDEFAULT = EvaluationLabel.NONE;

	/**
	 * The cached value of the '{@link #getQualitativeReasoningDenialLabel() <em>Qualitative Reasoning Denial Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQualitativeReasoningDenialLabel()
	 * @generated
	 * @ordered
	 */
	protected EvaluationLabel qualitativeReasoningDenialLabel = QUALITATIVE_REASONING_DENIAL_LABEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getQuantitativeReasoningCombinedLabel() <em>Quantitative Reasoning Combined Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuantitativeReasoningCombinedLabel()
	 * @generated
	 * @ordered
	 */
	protected static final double QUANTITATIVE_REASONING_COMBINED_LABEL_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getQuantitativeReasoningCombinedLabel() <em>Quantitative Reasoning Combined Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuantitativeReasoningCombinedLabel()
	 * @generated
	 * @ordered
	 */
	protected double quantitativeReasoningCombinedLabel = QUANTITATIVE_REASONING_COMBINED_LABEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getQuantitativeReasoningDeniedLabel() <em>Quantitative Reasoning Denied Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuantitativeReasoningDeniedLabel()
	 * @generated
	 * @ordered
	 */
	protected static final double QUANTITATIVE_REASONING_DENIED_LABEL_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getQuantitativeReasoningDeniedLabel() <em>Quantitative Reasoning Denied Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuantitativeReasoningDeniedLabel()
	 * @generated
	 * @ordered
	 */
	protected double quantitativeReasoningDeniedLabel = QUANTITATIVE_REASONING_DENIED_LABEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getQuantitativeReasoningSatisfiedLabel() <em>Quantitative Reasoning Satisfied Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuantitativeReasoningSatisfiedLabel()
	 * @generated
	 * @ordered
	 */
	protected static final double QUANTITATIVE_REASONING_SATISFIED_LABEL_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getQuantitativeReasoningSatisfiedLabel() <em>Quantitative Reasoning Satisfied Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuantitativeReasoningSatisfiedLabel()
	 * @generated
	 * @ordered
	 */
	protected double quantitativeReasoningSatisfiedLabel = QUANTITATIVE_REASONING_SATISFIED_LABEL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getContributesTo() <em>Contributes To</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContributesTo()
	 * @generated
	 * @ordered
	 */
	protected EList<Contribution> contributesTo;

	/**
	 * The cached value of the '{@link #getContributesFrom() <em>Contributes From</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContributesFrom()
	 * @generated
	 * @ordered
	 */
	protected EList<Contribution> contributesFrom;

	/**
	 * The cached value of the '{@link #getLabelBag() <em>Label Bag</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabelBag()
	 * @generated
	 * @ordered
	 */
	protected LabelBag labelBag;

	/**
	 * The default value of the '{@link #getInitialEvalLabel() <em>Initial Eval Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitialEvalLabel()
	 * @generated
	 * @ordered
	 */
	protected static final EvaluationLabel INITIAL_EVAL_LABEL_EDEFAULT = EvaluationLabel.NONE;

	/**
	 * The cached value of the '{@link #getInitialEvalLabel() <em>Initial Eval Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitialEvalLabel()
	 * @generated
	 * @ordered
	 */
	protected EvaluationLabel initialEvalLabel = INITIAL_EVAL_LABEL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getReverseLabelBag() <em>Reverse Label Bag</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReverseLabelBag()
	 * @generated
	 * @ordered
	 */
	protected LabelBag reverseLabelBag;

	/**
	 * The cached value of the '{@link #getHumanJudgments() <em>Human Judgments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHumanJudgments()
	 * @generated
	 * @ordered
	 */
	protected EList<HumanJudgment> humanJudgments;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IntentionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return openome_modelPackage.Literals.INTENTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.INTENTION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getSystem() {
		return system;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSystem(Boolean newSystem) {
		Boolean oldSystem = system;
		system = newSystem;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.INTENTION__SYSTEM, oldSystem, system));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getBoundary() {
		return boundary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBoundary(Boolean newBoundary) {
		Boolean oldBoundary = boundary;
		boundary = newBoundary;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.INTENTION__BOUNDARY, oldBoundary, boundary));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getExclusive() {
		return exclusive;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExclusive(Boolean newExclusive) {
		Boolean oldExclusive = exclusive;
		exclusive = newExclusive;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.INTENTION__EXCLUSIVE, oldExclusive, exclusive));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getSequential() {
		return sequential;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSequential(Boolean newSequential) {
		Boolean oldSequential = sequential;
		sequential = newSequential;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.INTENTION__SEQUENTIAL, oldSequential, sequential));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getParallel() {
		return parallel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParallel(Boolean newParallel) {
		Boolean oldParallel = parallel;
		parallel = newParallel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.INTENTION__PARALLEL, oldParallel, parallel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Property> getProperty() {
		if (property == null) {
			property = new EObjectContainmentEList<Property>(Property.class, this, openome_modelPackage.INTENTION__PROPERTY);
		}
		return property;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * 
	 * 
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isLeaf() {
		
		EList<Dependency> depFrom = getDependencyFrom();
		EList<Decomposition> decFrom = getDecompositionsTo();
		EList<Contribution> contFrom = getContributesFrom();
		
		System.out.println(depFrom.size() > 0); 
		System.out.println(decFrom.size() > 0);
		System.out.println(decFrom.size() + " | " + decFrom.toString());
		System.out.println(contFrom.size() > 0);

		if (depFrom.size() > 0) 
			return false;
		
		if (decFrom.size() > 0) 
			return false;
		
		if (contFrom.size() > 0) 
			return false;
		
		return true;
	}
	

	/**
	 * @generated NOT
	 */
	public boolean isRoot() {
		EList<Dependency> depFrom = getDependencyTo();
		if (depFrom.size() > 0) 
			return false;
		EList<Decomposition> decFrom = getDecompositionsFrom();
		if (decFrom.size() > 0) 
			return false;
		EList<Contribution> contFrom = getContributesTo();
		if (contFrom.size() > 0) 
			return false;
		
		return true;
	}
	
	/**
	 * @generated NOT
	 */
	public EList<Intention> getChildren() {
		EList<Intention> children = new BasicEList<Intention>();
		
		
		for (Dependency d : getDependencyFrom()) {
			Dependable dependable = d.getDependencyFrom();
			
			//If the target is not an actor, like in an SD diagram
			if (!(dependable instanceof Container)) {
				if (dependable != null) {
					children.add((Intention) dependable);
				}
				
			}
				
		}
		
		for (Decomposition dec : getDecompositionsTo()) {
			children.add(dec.getSource());
		}
		for (Contribution cont : getContributesFrom()) {
			children.add(cont.getSource());
		}
		
		
		return children;
	}
	
	/**
	 * @generated NOT
	 */
	public EList<Intention> getParents() {
		EList<Intention> parents = new BasicEList<Intention>();
		
		
		for (Dependency d : getDependencyTo()) {
			Dependable dependable = d.getDependencyTo();
			
			//If the target is not an actor, like in an SD diagram
			if (!(dependable instanceof Container)) {
				if (dependable != null) {
					parents.add((Intention) dependable);
				}				
			}				
		}
		
		for (Decomposition dec : getDecompositionsFrom()) {
			parents.add(dec.getTarget());
		}
		for (Contribution cont : getContributesTo()) {
			parents.add(cont.getTarget());
		}
				
		return parents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * This will only get decompositions that the element is a source of, the opposite of what I thought it would do
	 * 
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<Decomposition> getDecompositions() {
		if (decompositions == null) {
			decompositions = new EObjectWithInverseResolvingEList<Decomposition>(Decomposition.class, this, openome_modelPackage.INTENTION__DECOMPOSITIONS, openome_modelPackage.DECOMPOSITION__SOURCE);
		}
		return decompositions;		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<Decomposition> getAllDecompositions() {
		EList<Decomposition> decomps = new BasicEList<Decomposition>(getDecompositionsTo());	
		decomps.addAll(getDecompositionsFrom());
		
		return decomps;		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<Decomposition> getDecompositionsTo() {				
		return getParentDecompositions();		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<Decomposition> getDecompositionsFrom() {		

		return getDecompositions();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<Decomposition> getParentDecompositions() {
		if (parentDecompositions == null) {
			parentDecompositions = new EObjectWithInverseResolvingEList<Decomposition>(Decomposition.class, this, openome_modelPackage.INTENTION__PARENT_DECOMPOSITIONS, openome_modelPackage.DECOMPOSITION__TARGET);
		}
		return parentDecompositions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Container getContainer() {
		if (eContainerFeatureID() != openome_modelPackage.INTENTION__CONTAINER) return null;
		return (Container)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContainer(Container newContainer, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newContainer, openome_modelPackage.INTENTION__CONTAINER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContainer(Container newContainer) {
		if (newContainer != eInternalContainer() || (eContainerFeatureID() != openome_modelPackage.INTENTION__CONTAINER && newContainer != null)) {
			if (EcoreUtil.isAncestor(this, newContainer))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newContainer != null)
				msgs = ((InternalEObject)newContainer).eInverseAdd(this, openome_modelPackage.CONTAINER__INTENTIONS, Container.class, msgs);
			msgs = basicSetContainer(newContainer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.INTENTION__CONTAINER, newContainer, newContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EvaluationLabel getQualitativeReasoningCombinedLabel() {
		return qualitativeReasoningCombinedLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQualitativeReasoningCombinedLabel(EvaluationLabel newQualitativeReasoningCombinedLabel) {
		EvaluationLabel oldQualitativeReasoningCombinedLabel = qualitativeReasoningCombinedLabel;
		qualitativeReasoningCombinedLabel = newQualitativeReasoningCombinedLabel == null ? QUALITATIVE_REASONING_COMBINED_LABEL_EDEFAULT : newQualitativeReasoningCombinedLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.INTENTION__QUALITATIVE_REASONING_COMBINED_LABEL, oldQualitativeReasoningCombinedLabel, qualitativeReasoningCombinedLabel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EvaluationLabel getQualitativeReasoningSatisfiedLabel() {
		return qualitativeReasoningSatisfiedLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQualitativeReasoningSatisfiedLabel(EvaluationLabel newQualitativeReasoningSatisfiedLabel) {
		EvaluationLabel oldQualitativeReasoningSatisfiedLabel = qualitativeReasoningSatisfiedLabel;
		qualitativeReasoningSatisfiedLabel = newQualitativeReasoningSatisfiedLabel == null ? QUALITATIVE_REASONING_SATISFIED_LABEL_EDEFAULT : newQualitativeReasoningSatisfiedLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.INTENTION__QUALITATIVE_REASONING_SATISFIED_LABEL, oldQualitativeReasoningSatisfiedLabel, qualitativeReasoningSatisfiedLabel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EvaluationLabel getQualitativeReasoningDenialLabel() {
		return qualitativeReasoningDenialLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQualitativeReasoningDenialLabel(EvaluationLabel newQualitativeReasoningDenialLabel) {
		EvaluationLabel oldQualitativeReasoningDenialLabel = qualitativeReasoningDenialLabel;
		qualitativeReasoningDenialLabel = newQualitativeReasoningDenialLabel == null ? QUALITATIVE_REASONING_DENIAL_LABEL_EDEFAULT : newQualitativeReasoningDenialLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.INTENTION__QUALITATIVE_REASONING_DENIAL_LABEL, oldQualitativeReasoningDenialLabel, qualitativeReasoningDenialLabel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getQuantitativeReasoningCombinedLabel() {
		return quantitativeReasoningCombinedLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQuantitativeReasoningCombinedLabel(double newQuantitativeReasoningCombinedLabel) {
		double oldQuantitativeReasoningCombinedLabel = quantitativeReasoningCombinedLabel;
		quantitativeReasoningCombinedLabel = newQuantitativeReasoningCombinedLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.INTENTION__QUANTITATIVE_REASONING_COMBINED_LABEL, oldQuantitativeReasoningCombinedLabel, quantitativeReasoningCombinedLabel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getQuantitativeReasoningDeniedLabel() {
		return quantitativeReasoningDeniedLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQuantitativeReasoningDeniedLabel(double newQuantitativeReasoningDeniedLabel) {
		double oldQuantitativeReasoningDeniedLabel = quantitativeReasoningDeniedLabel;
		quantitativeReasoningDeniedLabel = newQuantitativeReasoningDeniedLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.INTENTION__QUANTITATIVE_REASONING_DENIED_LABEL, oldQuantitativeReasoningDeniedLabel, quantitativeReasoningDeniedLabel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getQuantitativeReasoningSatisfiedLabel() {
		return quantitativeReasoningSatisfiedLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQuantitativeReasoningSatisfiedLabel(double newQuantitativeReasoningSatisfiedLabel) {
		double oldQuantitativeReasoningSatisfiedLabel = quantitativeReasoningSatisfiedLabel;
		quantitativeReasoningSatisfiedLabel = newQuantitativeReasoningSatisfiedLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.INTENTION__QUANTITATIVE_REASONING_SATISFIED_LABEL, oldQuantitativeReasoningSatisfiedLabel, quantitativeReasoningSatisfiedLabel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Contribution> getContributesTo() {
		if (contributesTo == null) {
			contributesTo = new EObjectWithInverseResolvingEList<Contribution>(Contribution.class, this, openome_modelPackage.INTENTION__CONTRIBUTES_TO, openome_modelPackage.CONTRIBUTION__SOURCE);
		}
		return contributesTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Contribution> getContributesFrom() {
		if (contributesFrom == null) {
			contributesFrom = new EObjectWithInverseResolvingEList<Contribution>(Contribution.class, this, openome_modelPackage.INTENTION__CONTRIBUTES_FROM, openome_modelPackage.CONTRIBUTION__TARGET);
		}
		return contributesFrom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<HumanJudgment> getHumanJudgments() {
		if (humanJudgments == null) {
			humanJudgments = new EObjectContainmentEList<HumanJudgment>(HumanJudgment.class, this, openome_modelPackage.INTENTION__HUMAN_JUDGMENTS);
		}
		return humanJudgments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LabelBag getLabelBag() {
		return labelBag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLabelBag(LabelBag newLabelBag, NotificationChain msgs) {
		LabelBag oldLabelBag = labelBag;
		labelBag = newLabelBag;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, openome_modelPackage.INTENTION__LABEL_BAG, oldLabelBag, newLabelBag);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabelBag(LabelBag newLabelBag) {
		if (newLabelBag != labelBag) {
			NotificationChain msgs = null;
			if (labelBag != null)
				msgs = ((InternalEObject)labelBag).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - openome_modelPackage.INTENTION__LABEL_BAG, null, msgs);
			if (newLabelBag != null)
				msgs = ((InternalEObject)newLabelBag).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - openome_modelPackage.INTENTION__LABEL_BAG, null, msgs);
			msgs = basicSetLabelBag(newLabelBag, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.INTENTION__LABEL_BAG, newLabelBag, newLabelBag));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EvaluationLabel getInitialEvalLabel() {
		return initialEvalLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitialEvalLabel(EvaluationLabel newInitialEvalLabel) {
		EvaluationLabel oldInitialEvalLabel = initialEvalLabel;
		initialEvalLabel = newInitialEvalLabel == null ? INITIAL_EVAL_LABEL_EDEFAULT : newInitialEvalLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.INTENTION__INITIAL_EVAL_LABEL, oldInitialEvalLabel, initialEvalLabel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LabelBag getReverseLabelBag() {
		return reverseLabelBag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReverseLabelBag(LabelBag newReverseLabelBag, NotificationChain msgs) {
		LabelBag oldReverseLabelBag = reverseLabelBag;
		reverseLabelBag = newReverseLabelBag;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, openome_modelPackage.INTENTION__REVERSE_LABEL_BAG, oldReverseLabelBag, newReverseLabelBag);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReverseLabelBag(LabelBag newReverseLabelBag) {
		if (newReverseLabelBag != reverseLabelBag) {
			NotificationChain msgs = null;
			if (reverseLabelBag != null)
				msgs = ((InternalEObject)reverseLabelBag).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - openome_modelPackage.INTENTION__REVERSE_LABEL_BAG, null, msgs);
			if (newReverseLabelBag != null)
				msgs = ((InternalEObject)newReverseLabelBag).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - openome_modelPackage.INTENTION__REVERSE_LABEL_BAG, null, msgs);
			msgs = basicSetReverseLabelBag(newReverseLabelBag, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, openome_modelPackage.INTENTION__REVERSE_LABEL_BAG, newReverseLabelBag, newReverseLabelBag));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case openome_modelPackage.INTENTION__DECOMPOSITIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDecompositions()).basicAdd(otherEnd, msgs);
			case openome_modelPackage.INTENTION__PARENT_DECOMPOSITIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getParentDecompositions()).basicAdd(otherEnd, msgs);
			case openome_modelPackage.INTENTION__CONTAINER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetContainer((Container)otherEnd, msgs);
			case openome_modelPackage.INTENTION__CONTRIBUTES_TO:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getContributesTo()).basicAdd(otherEnd, msgs);
			case openome_modelPackage.INTENTION__CONTRIBUTES_FROM:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getContributesFrom()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case openome_modelPackage.INTENTION__PROPERTY:
				return ((InternalEList<?>)getProperty()).basicRemove(otherEnd, msgs);
			case openome_modelPackage.INTENTION__DECOMPOSITIONS:
				return ((InternalEList<?>)getDecompositions()).basicRemove(otherEnd, msgs);
			case openome_modelPackage.INTENTION__PARENT_DECOMPOSITIONS:
				return ((InternalEList<?>)getParentDecompositions()).basicRemove(otherEnd, msgs);
			case openome_modelPackage.INTENTION__CONTAINER:
				return basicSetContainer(null, msgs);
			case openome_modelPackage.INTENTION__CONTRIBUTES_TO:
				return ((InternalEList<?>)getContributesTo()).basicRemove(otherEnd, msgs);
			case openome_modelPackage.INTENTION__CONTRIBUTES_FROM:
				return ((InternalEList<?>)getContributesFrom()).basicRemove(otherEnd, msgs);
			case openome_modelPackage.INTENTION__LABEL_BAG:
				return basicSetLabelBag(null, msgs);
			case openome_modelPackage.INTENTION__REVERSE_LABEL_BAG:
				return basicSetReverseLabelBag(null, msgs);
			case openome_modelPackage.INTENTION__HUMAN_JUDGMENTS:
				return ((InternalEList<?>)getHumanJudgments()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case openome_modelPackage.INTENTION__CONTAINER:
				return eInternalContainer().eInverseRemove(this, openome_modelPackage.CONTAINER__INTENTIONS, Container.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case openome_modelPackage.INTENTION__NAME:
				return getName();
			case openome_modelPackage.INTENTION__SYSTEM:
				return getSystem();
			case openome_modelPackage.INTENTION__BOUNDARY:
				return getBoundary();
			case openome_modelPackage.INTENTION__EXCLUSIVE:
				return getExclusive();
			case openome_modelPackage.INTENTION__SEQUENTIAL:
				return getSequential();
			case openome_modelPackage.INTENTION__PARALLEL:
				return getParallel();
			case openome_modelPackage.INTENTION__PROPERTY:
				return getProperty();
			case openome_modelPackage.INTENTION__DECOMPOSITIONS:
				return getDecompositions();
			case openome_modelPackage.INTENTION__PARENT_DECOMPOSITIONS:
				return getParentDecompositions();
			case openome_modelPackage.INTENTION__CONTAINER:
				return getContainer();
			case openome_modelPackage.INTENTION__QUALITATIVE_REASONING_COMBINED_LABEL:
				return getQualitativeReasoningCombinedLabel();
			case openome_modelPackage.INTENTION__QUALITATIVE_REASONING_SATISFIED_LABEL:
				return getQualitativeReasoningSatisfiedLabel();
			case openome_modelPackage.INTENTION__QUALITATIVE_REASONING_DENIAL_LABEL:
				return getQualitativeReasoningDenialLabel();
			case openome_modelPackage.INTENTION__QUANTITATIVE_REASONING_COMBINED_LABEL:
				return getQuantitativeReasoningCombinedLabel();
			case openome_modelPackage.INTENTION__QUANTITATIVE_REASONING_DENIED_LABEL:
				return getQuantitativeReasoningDeniedLabel();
			case openome_modelPackage.INTENTION__QUANTITATIVE_REASONING_SATISFIED_LABEL:
				return getQuantitativeReasoningSatisfiedLabel();
			case openome_modelPackage.INTENTION__CONTRIBUTES_TO:
				return getContributesTo();
			case openome_modelPackage.INTENTION__CONTRIBUTES_FROM:
				return getContributesFrom();
			case openome_modelPackage.INTENTION__LABEL_BAG:
				return getLabelBag();
			case openome_modelPackage.INTENTION__INITIAL_EVAL_LABEL:
				return getInitialEvalLabel();
			case openome_modelPackage.INTENTION__REVERSE_LABEL_BAG:
				return getReverseLabelBag();
			case openome_modelPackage.INTENTION__HUMAN_JUDGMENTS:
				return getHumanJudgments();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case openome_modelPackage.INTENTION__NAME:
				setName((String)newValue);
				return;
			case openome_modelPackage.INTENTION__SYSTEM:
				setSystem((Boolean)newValue);
				return;
			case openome_modelPackage.INTENTION__BOUNDARY:
				setBoundary((Boolean)newValue);
				return;
			case openome_modelPackage.INTENTION__EXCLUSIVE:
				setExclusive((Boolean)newValue);
				return;
			case openome_modelPackage.INTENTION__SEQUENTIAL:
				setSequential((Boolean)newValue);
				return;
			case openome_modelPackage.INTENTION__PARALLEL:
				setParallel((Boolean)newValue);
				return;
			case openome_modelPackage.INTENTION__PROPERTY:
				getProperty().clear();
				getProperty().addAll((Collection<? extends Property>)newValue);
				return;
			case openome_modelPackage.INTENTION__DECOMPOSITIONS:
				getDecompositions().clear();
				getDecompositions().addAll((Collection<? extends Decomposition>)newValue);
				return;
			case openome_modelPackage.INTENTION__PARENT_DECOMPOSITIONS:
				getParentDecompositions().clear();
				getParentDecompositions().addAll((Collection<? extends Decomposition>)newValue);
				return;
			case openome_modelPackage.INTENTION__CONTAINER:
				setContainer((Container)newValue);
				return;
			case openome_modelPackage.INTENTION__QUALITATIVE_REASONING_COMBINED_LABEL:
				setQualitativeReasoningCombinedLabel((EvaluationLabel)newValue);
				return;
			case openome_modelPackage.INTENTION__QUALITATIVE_REASONING_SATISFIED_LABEL:
				setQualitativeReasoningSatisfiedLabel((EvaluationLabel)newValue);
				return;
			case openome_modelPackage.INTENTION__QUALITATIVE_REASONING_DENIAL_LABEL:
				setQualitativeReasoningDenialLabel((EvaluationLabel)newValue);
				return;
			case openome_modelPackage.INTENTION__QUANTITATIVE_REASONING_COMBINED_LABEL:
				setQuantitativeReasoningCombinedLabel((Double)newValue);
				return;
			case openome_modelPackage.INTENTION__QUANTITATIVE_REASONING_DENIED_LABEL:
				setQuantitativeReasoningDeniedLabel((Double)newValue);
				return;
			case openome_modelPackage.INTENTION__QUANTITATIVE_REASONING_SATISFIED_LABEL:
				setQuantitativeReasoningSatisfiedLabel((Double)newValue);
				return;
			case openome_modelPackage.INTENTION__CONTRIBUTES_TO:
				getContributesTo().clear();
				getContributesTo().addAll((Collection<? extends Contribution>)newValue);
				return;
			case openome_modelPackage.INTENTION__CONTRIBUTES_FROM:
				getContributesFrom().clear();
				getContributesFrom().addAll((Collection<? extends Contribution>)newValue);
				return;
			case openome_modelPackage.INTENTION__LABEL_BAG:
				setLabelBag((LabelBag)newValue);
				return;
			case openome_modelPackage.INTENTION__INITIAL_EVAL_LABEL:
				setInitialEvalLabel((EvaluationLabel)newValue);
				return;
			case openome_modelPackage.INTENTION__REVERSE_LABEL_BAG:
				setReverseLabelBag((LabelBag)newValue);
				return;
			case openome_modelPackage.INTENTION__HUMAN_JUDGMENTS:
				getHumanJudgments().clear();
				getHumanJudgments().addAll((Collection<? extends HumanJudgment>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case openome_modelPackage.INTENTION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case openome_modelPackage.INTENTION__SYSTEM:
				setSystem(SYSTEM_EDEFAULT);
				return;
			case openome_modelPackage.INTENTION__BOUNDARY:
				setBoundary(BOUNDARY_EDEFAULT);
				return;
			case openome_modelPackage.INTENTION__EXCLUSIVE:
				setExclusive(EXCLUSIVE_EDEFAULT);
				return;
			case openome_modelPackage.INTENTION__SEQUENTIAL:
				setSequential(SEQUENTIAL_EDEFAULT);
				return;
			case openome_modelPackage.INTENTION__PARALLEL:
				setParallel(PARALLEL_EDEFAULT);
				return;
			case openome_modelPackage.INTENTION__PROPERTY:
				getProperty().clear();
				return;
			case openome_modelPackage.INTENTION__DECOMPOSITIONS:
				getDecompositions().clear();
				return;
			case openome_modelPackage.INTENTION__PARENT_DECOMPOSITIONS:
				getParentDecompositions().clear();
				return;
			case openome_modelPackage.INTENTION__CONTAINER:
				setContainer((Container)null);
				return;
			case openome_modelPackage.INTENTION__QUALITATIVE_REASONING_COMBINED_LABEL:
				setQualitativeReasoningCombinedLabel(QUALITATIVE_REASONING_COMBINED_LABEL_EDEFAULT);
				return;
			case openome_modelPackage.INTENTION__QUALITATIVE_REASONING_SATISFIED_LABEL:
				setQualitativeReasoningSatisfiedLabel(QUALITATIVE_REASONING_SATISFIED_LABEL_EDEFAULT);
				return;
			case openome_modelPackage.INTENTION__QUALITATIVE_REASONING_DENIAL_LABEL:
				setQualitativeReasoningDenialLabel(QUALITATIVE_REASONING_DENIAL_LABEL_EDEFAULT);
				return;
			case openome_modelPackage.INTENTION__QUANTITATIVE_REASONING_COMBINED_LABEL:
				setQuantitativeReasoningCombinedLabel(QUANTITATIVE_REASONING_COMBINED_LABEL_EDEFAULT);
				return;
			case openome_modelPackage.INTENTION__QUANTITATIVE_REASONING_DENIED_LABEL:
				setQuantitativeReasoningDeniedLabel(QUANTITATIVE_REASONING_DENIED_LABEL_EDEFAULT);
				return;
			case openome_modelPackage.INTENTION__QUANTITATIVE_REASONING_SATISFIED_LABEL:
				setQuantitativeReasoningSatisfiedLabel(QUANTITATIVE_REASONING_SATISFIED_LABEL_EDEFAULT);
				return;
			case openome_modelPackage.INTENTION__CONTRIBUTES_TO:
				getContributesTo().clear();
				return;
			case openome_modelPackage.INTENTION__CONTRIBUTES_FROM:
				getContributesFrom().clear();
				return;
			case openome_modelPackage.INTENTION__LABEL_BAG:
				setLabelBag((LabelBag)null);
				return;
			case openome_modelPackage.INTENTION__INITIAL_EVAL_LABEL:
				setInitialEvalLabel(INITIAL_EVAL_LABEL_EDEFAULT);
				return;
			case openome_modelPackage.INTENTION__REVERSE_LABEL_BAG:
				setReverseLabelBag((LabelBag)null);
				return;
			case openome_modelPackage.INTENTION__HUMAN_JUDGMENTS:
				getHumanJudgments().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case openome_modelPackage.INTENTION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case openome_modelPackage.INTENTION__SYSTEM:
				return SYSTEM_EDEFAULT == null ? system != null : !SYSTEM_EDEFAULT.equals(system);
			case openome_modelPackage.INTENTION__BOUNDARY:
				return BOUNDARY_EDEFAULT == null ? boundary != null : !BOUNDARY_EDEFAULT.equals(boundary);
			case openome_modelPackage.INTENTION__EXCLUSIVE:
				return EXCLUSIVE_EDEFAULT == null ? exclusive != null : !EXCLUSIVE_EDEFAULT.equals(exclusive);
			case openome_modelPackage.INTENTION__SEQUENTIAL:
				return SEQUENTIAL_EDEFAULT == null ? sequential != null : !SEQUENTIAL_EDEFAULT.equals(sequential);
			case openome_modelPackage.INTENTION__PARALLEL:
				return PARALLEL_EDEFAULT == null ? parallel != null : !PARALLEL_EDEFAULT.equals(parallel);
			case openome_modelPackage.INTENTION__PROPERTY:
				return property != null && !property.isEmpty();
			case openome_modelPackage.INTENTION__DECOMPOSITIONS:
				return decompositions != null && !decompositions.isEmpty();
			case openome_modelPackage.INTENTION__PARENT_DECOMPOSITIONS:
				return parentDecompositions != null && !parentDecompositions.isEmpty();
			case openome_modelPackage.INTENTION__CONTAINER:
				return getContainer() != null;
			case openome_modelPackage.INTENTION__QUALITATIVE_REASONING_COMBINED_LABEL:
				return qualitativeReasoningCombinedLabel != QUALITATIVE_REASONING_COMBINED_LABEL_EDEFAULT;
			case openome_modelPackage.INTENTION__QUALITATIVE_REASONING_SATISFIED_LABEL:
				return qualitativeReasoningSatisfiedLabel != QUALITATIVE_REASONING_SATISFIED_LABEL_EDEFAULT;
			case openome_modelPackage.INTENTION__QUALITATIVE_REASONING_DENIAL_LABEL:
				return qualitativeReasoningDenialLabel != QUALITATIVE_REASONING_DENIAL_LABEL_EDEFAULT;
			case openome_modelPackage.INTENTION__QUANTITATIVE_REASONING_COMBINED_LABEL:
				return quantitativeReasoningCombinedLabel != QUANTITATIVE_REASONING_COMBINED_LABEL_EDEFAULT;
			case openome_modelPackage.INTENTION__QUANTITATIVE_REASONING_DENIED_LABEL:
				return quantitativeReasoningDeniedLabel != QUANTITATIVE_REASONING_DENIED_LABEL_EDEFAULT;
			case openome_modelPackage.INTENTION__QUANTITATIVE_REASONING_SATISFIED_LABEL:
				return quantitativeReasoningSatisfiedLabel != QUANTITATIVE_REASONING_SATISFIED_LABEL_EDEFAULT;
			case openome_modelPackage.INTENTION__CONTRIBUTES_TO:
				return contributesTo != null && !contributesTo.isEmpty();
			case openome_modelPackage.INTENTION__CONTRIBUTES_FROM:
				return contributesFrom != null && !contributesFrom.isEmpty();
			case openome_modelPackage.INTENTION__LABEL_BAG:
				return labelBag != null;
			case openome_modelPackage.INTENTION__INITIAL_EVAL_LABEL:
				return initialEvalLabel != INITIAL_EVAL_LABEL_EDEFAULT;
			case openome_modelPackage.INTENTION__REVERSE_LABEL_BAG:
				return reverseLabelBag != null;
			case openome_modelPackage.INTENTION__HUMAN_JUDGMENTS:
				return humanJudgments != null && !humanJudgments.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", system: ");
		result.append(system);
		result.append(", boundary: ");
		result.append(boundary);
		result.append(", exclusive: ");
		result.append(exclusive);
		result.append(", sequential: ");
		result.append(sequential);
		result.append(", parallel: ");
		result.append(parallel);
		result.append(", QualitativeReasoningCombinedLabel: ");
		result.append(qualitativeReasoningCombinedLabel);
		result.append(", QualitativeReasoningSatisfiedLabel: ");
		result.append(qualitativeReasoningSatisfiedLabel);
		result.append(", QualitativeReasoningDenialLabel: ");
		result.append(qualitativeReasoningDenialLabel);
		result.append(", QuantitativeReasoningCombinedLabel: ");
		result.append(quantitativeReasoningCombinedLabel);
		result.append(", QuantitativeReasoningDeniedLabel: ");
		result.append(quantitativeReasoningDeniedLabel);
		result.append(", QuantitativeReasoningSatisfiedLabel: ");
		result.append(quantitativeReasoningSatisfiedLabel);
		result.append(", initialEvalLabel: ");
		result.append(initialEvalLabel);
		result.append(')');
		return result.toString();
	}
	
	/**
	 * @generated NOT
	 */
	public EList<Intention> getForwardSlice() {
		//can't instantiate an EList and don't want to constrain it's type, so have to set it equal to something
		EList<Intention> slice = this.getParents();
		//but I really want it to be empty
		slice.removeAll(this.getParents());
		
		return getForwardSliceRecursive(slice, this);
	}

	/**
	 * @generated NOT
	 */
	private EList<Intention> getForwardSliceRecursive(EList<Intention> slice, Intention current) {
		EList<Intention> parents = current.getParents();
			
		if (parents == null) {
			if (!slice.contains(current))
				slice.add(current);
			return slice;
		}
		
		for (Intention parent : parents) {
			if (!slice.contains(parent)) {
				slice.add(parent);
			
				EList<Intention> tmp = getForwardSliceRecursive(slice, parent);
				for (Intention i: tmp) {
					if (!slice.contains(i))
						slice.add(i);
				}	
			}
		}		
		return slice;
	}
	
	/**
	 * @generated NOT
	 */
	public EList<Intention> getBackwardSlice() {
		//can't instantiate an EList and don't want to constrain it's type, so have to set it equal to something
		EList<Intention> slice = this.getChildren();
		//but I really want it to be empty
		slice.removeAll(this.getChildren());
		
		return getBackwardSliceRecursive(slice, this);
	}
	
	/**
	 * @generated NOT
	 */
	private EList<Intention> getBackwardSliceRecursive(EList<Intention> slice, Intention current) {
		EList<Intention> children = current.getChildren();
			
		if (children == null) {
			if (!slice.contains(current))
				slice.add(current);
			return slice;
		}
		
		for (Intention child : children) {
			if (!slice.contains(child)) {
				slice.add(child);
			
				EList<Intention> tmp = getBackwardSliceRecursive(slice, child);
				for (Intention i: tmp) {
					if (!slice.contains(i))
						slice.add(i);
				}	
			}
		}		
		return slice;
	}

	/**
	 * @generated NOT
	 */
	public EList<Intention> getAllConnected() {
		//can't instantiate an EList and don't want to constrain it's type, so have to set it equal to something
		EList<Intention> slice = this.getChildren();
		//but I really want it to be empty
		slice.removeAll(this.getChildren());
		
		return getAllConnectedRecursive(slice, this);
	}
	
	/**
	 * @generated NOT
	 */
	private EList<Intention> getAllConnectedRecursive(EList<Intention> slice, Intention current) {
		EList<Intention> children = current.getChildren();
		EList<Intention> parents = current.getParents();
				
		for (Intention child : children) {
			if (!slice.contains(child)) {
				slice.add(child);
			
				EList<Intention> tmp = getAllConnectedRecursive(slice, child);
				for (Intention i: tmp) {
					if (!slice.contains(i))
						slice.add(i);
				}	
			}
		}	
		
		for (Intention parent : parents) {
			if (!slice.contains(parent)) {
				slice.add(parent);
			
				EList<Intention> tmp = getAllConnectedRecursive(slice, parent);
				for (Intention i: tmp) {
					if (!slice.contains(i))
						slice.add(i);
				}	
			}
		}
		
		return slice;
	}
	
	/**
	 * * @generated NOT
	 */
	public EvaluationLabel findExistingHumanJudgment() {
		System.out.println("finding existing judgment");
		if (getLabelBag() == null)
			return null;
		HumanJudgment result = null;
		for (HumanJudgment hj: getHumanJudgments())  {
			result = hj.findOrImplies(getLabelBag());
			if (result != null)
				return result.getResultLabel();
		}
		return null;
	}
	
	/**
	 * * @generated NOT
	 */
	public HumanJudgment addHumanJudgment(EvaluationLabel result) {
		//System.out.println("adding human judgment in intention");
		
		HumanJudgment newJudgment = new HumanJudgmentImpl();
		LabelBag copybag = new LabelBagImpl(getLabelBag());
		System.out.println(getLabelBag().toUIString());
		newJudgment.setLabelBag(copybag);
		newJudgment.setResultLabel(result);
		
		getHumanJudgments().add(newJudgment);
		
		//System.out.println("added human judgment in intention");				
		
		return newJudgment;
	}
	
	
	/**
	 * * @generated NOT
	 */
	public void addReverseJudgment(Intention intn, EvaluationLabel result) {
		if (getReverseLabelBag() != null) {
			//System.out.println("reverselabelbag not null");
			
		}
		else {
			//System.out.println("reverselabelbag null");
			setReverseLabelBag(new LabelBagImpl());
			//getReverseLabelBag().addToLabelBag(intn, result);
		}
		
		getReverseLabelBag().addToLabelBag(intn, result);
			
	}
	
	/**
	 * * @generated NOT
	 */
	public boolean backtrackReverseJudgments(HumanJudgment hj) {
		boolean success = true;
		for (Intention child: hj.getLabelBag().getLabelBagIntentions()) {
			int size = child.getReverseLabelBag().getLabelBagIntentions().size();
			int index = 0;
			for (int j = 0; j< size; j++) {
				Intention target = child.getReverseLabelBag().getLabelBagIntentions().get(index);
				if (target.equals(this)) {
					EvaluationLabel label = child.getReverseLabelBag().getLabelBagEvalLabels().get(child.getReverseLabelBag().getLabelBagIntentions().indexOf(target));
					if (hj.getResultLabel().equals(label))  {
						if (child.getReverseLabelBag().removeFromLabelBag(target, label) != true)
							success = false;
					}					
				}
				else { index++; }
			}
		}
		
		return success;
	}
	
	/**
	 * * @generated NOT
	 */
	public boolean removeHumanJudgment(HumanJudgment humanJudgment) {
		if (getHumanJudgments() == null)
			return false;
		if (getHumanJudgments().contains(humanJudgment)) {
			getHumanJudgments().remove(humanJudgment);
			return true;
		}
		return false;	
	}

	@Override
	public boolean getAffectedStatus() {
		return status;
	}

	@Override
	public void setAffectedStatus(boolean b) {
		status = b;
	}


} //IntentionImpl

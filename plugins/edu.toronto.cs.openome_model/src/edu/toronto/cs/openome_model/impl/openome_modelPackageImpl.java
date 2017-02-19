/**
 * Copyright 2001-2008 University of Toronto
 *
 * $Id$
 */
package edu.toronto.cs.openome_model.impl;

import edu.toronto.cs.openome_model.Actor;
import edu.toronto.cs.openome_model.Agent;
import edu.toronto.cs.openome_model.Alternative;
import edu.toronto.cs.openome_model.AndContribution;
import edu.toronto.cs.openome_model.AndDecomposition;
import edu.toronto.cs.openome_model.Association;
import edu.toronto.cs.openome_model.Belief;
import edu.toronto.cs.openome_model.BreakContribution;
import edu.toronto.cs.openome_model.Contribution;
import edu.toronto.cs.openome_model.Correlation;
import edu.toronto.cs.openome_model.CoversAssociation;
import edu.toronto.cs.openome_model.Decomposition;
import edu.toronto.cs.openome_model.Dependable;
import edu.toronto.cs.openome_model.Dependency;
import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.Goal;
import edu.toronto.cs.openome_model.GoalModelingContributionSymmetry;
import edu.toronto.cs.openome_model.GoalModelingContributionType;
import edu.toronto.cs.openome_model.HelpContribution;
import edu.toronto.cs.openome_model.HumanJudgment;
import edu.toronto.cs.openome_model.HurtContribution;
import edu.toronto.cs.openome_model.INSAssociation;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.IsAAssociation;
import edu.toronto.cs.openome_model.IsPartOfAssociation;
import edu.toronto.cs.openome_model.LabelBag;
import edu.toronto.cs.openome_model.Link;
import edu.toronto.cs.openome_model.MakeContribution;
import edu.toronto.cs.openome_model.Model;
import edu.toronto.cs.openome_model.OccupiesAssociation;
import edu.toronto.cs.openome_model.OrContribution;
import edu.toronto.cs.openome_model.OrDecomposition;
import edu.toronto.cs.openome_model.PlaysAssociation;
import edu.toronto.cs.openome_model.Position;
import edu.toronto.cs.openome_model.Property;
import edu.toronto.cs.openome_model.Resource;
import edu.toronto.cs.openome_model.Role;
import edu.toronto.cs.openome_model.Softgoal;
import edu.toronto.cs.openome_model.SomeMinusContribution;
import edu.toronto.cs.openome_model.SomePlusContribution;
import edu.toronto.cs.openome_model.Task;
import edu.toronto.cs.openome_model.UnknownContribution;
import edu.toronto.cs.openome_model.openome_modelFactory;
import edu.toronto.cs.openome_model.openome_modelPackage;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class openome_modelPackageImpl extends EPackageImpl implements openome_modelPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright 2001-2008 University of Toronto";

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass agentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass andDecompositionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contributionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass decompositionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dependencyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass goalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass intentionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass orDecompositionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass positionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass roleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass softgoalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass taskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass beliefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass linkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass correlationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dependableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass helpContributionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass hurtContributionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass makeContributionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass breakContributionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass somePlusContributionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass someMinusContributionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unknownContributionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass andContributionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass orContributionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass associationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass isAAssociationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass coversAssociationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass isPartOfAssociationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass occupiesAssociationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass playsAssociationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass insAssociationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass alternativeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass intentionToEvaluationLabelMapEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass humanJudgmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass labelBagEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum evaluationLabelEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum goalModelingContributionTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum goalModelingContributionSymmetryEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see edu.toronto.cs.openome_model.openome_modelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private openome_modelPackageImpl() {
		super(eNS_URI, openome_modelFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link openome_modelPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static openome_modelPackage init() {
		if (isInited) return (openome_modelPackage)EPackage.Registry.INSTANCE.getEPackage(openome_modelPackage.eNS_URI);

		// Obtain or create and register package
		openome_modelPackageImpl theopenome_modelPackage = (openome_modelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof openome_modelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new openome_modelPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theopenome_modelPackage.createPackageContents();

		// Initialize created meta-data
		theopenome_modelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theopenome_modelPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(openome_modelPackage.eNS_URI, theopenome_modelPackage);
		return theopenome_modelPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActor() {
		return actorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActor_Is_a() {
		return (EReference)actorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActor_Is_part_of() {
		return (EReference)actorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActor_Believes() {
		return (EReference)actorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAgent() {
		return agentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAgent_Plays() {
		return (EReference)agentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAgent_Occupies() {
		return (EReference)agentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAgent_Ins() {
		return (EReference)agentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAndDecomposition() {
		return andDecompositionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContribution() {
		return contributionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContribution_Target() {
		return (EReference)contributionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContribution_Model() {
		return (EReference)contributionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContribution_Source() {
		return (EReference)contributionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContribution_Goal_model_symmetry() {
		return (EAttribute)contributionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDecomposition() {
		return decompositionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDecomposition_Source() {
		return (EReference)decompositionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDecomposition_Target() {
		return (EReference)decompositionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDecomposition_Model() {
		return (EReference)decompositionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDependency() {
		return dependencyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDependency_DependencyFrom() {
		return (EReference)dependencyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDependency_DependencyTo() {
		return (EReference)dependencyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDependency_Trust() {
		return (EAttribute)dependencyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDependency_Label() {
		return (EAttribute)dependencyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDependency_Model() {
		return (EReference)dependencyEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGoal() {
		return goalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntention() {
		return intentionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntention_Name() {
		return (EAttribute)intentionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntention_System() {
		return (EAttribute)intentionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntention_Boundary() {
		return (EAttribute)intentionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntention_Exclusive() {
		return (EAttribute)intentionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntention_Sequential() {
		return (EAttribute)intentionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntention_Parallel() {
		return (EAttribute)intentionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIntention_Property() {
		return (EReference)intentionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIntention_Decompositions() {
		return (EReference)intentionEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIntention_ParentDecompositions() {
		return (EReference)intentionEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIntention_Container() {
		return (EReference)intentionEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntention_QualitativeReasoningCombinedLabel() {
		return (EAttribute)intentionEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntention_QualitativeReasoningSatisfiedLabel() {
		return (EAttribute)intentionEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntention_QualitativeReasoningDenialLabel() {
		return (EAttribute)intentionEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntention_QuantitativeReasoningCombinedLabel() {
		return (EAttribute)intentionEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntention_QuantitativeReasoningDeniedLabel() {
		return (EAttribute)intentionEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntention_QuantitativeReasoningSatisfiedLabel() {
		return (EAttribute)intentionEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIntention_ContributesTo() {
		return (EReference)intentionEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIntention_ContributesFrom() {
		return (EReference)intentionEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIntention_HumanJudgments() {
		return (EReference)intentionEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIntention_LabelBag() {
		return (EReference)intentionEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntention_InitialEvalLabel() {
		return (EAttribute)intentionEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIntention_ReverseLabelBag() {
		return (EReference)intentionEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModel() {
		return modelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModel_Name() {
		return (EAttribute)modelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Intentions() {
		return (EReference)modelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Contributions() {
		return (EReference)modelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Dependencies() {
		return (EReference)modelEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Decompositions() {
		return (EReference)modelEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Containers() {
		return (EReference)modelEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Correlations() {
		return (EReference)modelEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Associations() {
		return (EReference)modelEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Alternatives() {
		return (EReference)modelEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOrDecomposition() {
		return orDecompositionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPosition() {
		return positionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPosition_Covers() {
		return (EReference)positionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProperty() {
		return propertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProperty_Name() {
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProperty_Value() {
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResource() {
		return resourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRole() {
		return roleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSoftgoal() {
		return softgoalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSoftgoal_Topic() {
		return (EAttribute)softgoalEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTask() {
		return taskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContainer() {
		return containerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContainer_Name() {
		return (EAttribute)containerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainer_Sub() {
		return (EReference)containerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainer_Intentions() {
		return (EReference)containerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainer_Model() {
		return (EReference)containerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainer_AssociationTo() {
		return (EReference)containerEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContainer_AssociationFrom() {
		return (EReference)containerEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBelief() {
		return beliefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLink() {
		return linkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCorrelation() {
		return correlationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCorrelation_Target() {
		return (EReference)correlationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCorrelation_Source() {
		return (EReference)correlationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDependable() {
		return dependableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDependable_DependencyFrom() {
		return (EReference)dependableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDependable_DependencyTo() {
		return (EReference)dependableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHelpContribution() {
		return helpContributionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHelpContribution_ContributionType() {
		return (EAttribute)helpContributionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHurtContribution() {
		return hurtContributionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHurtContribution_ContributionType() {
		return (EAttribute)hurtContributionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMakeContribution() {
		return makeContributionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMakeContribution_ContributionType() {
		return (EAttribute)makeContributionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBreakContribution() {
		return breakContributionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBreakContribution_ContributionType() {
		return (EAttribute)breakContributionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSomePlusContribution() {
		return somePlusContributionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSomePlusContribution_ContributionType() {
		return (EAttribute)somePlusContributionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSomeMinusContribution() {
		return someMinusContributionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSomeMinusContribution_ContributionType() {
		return (EAttribute)someMinusContributionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnknownContribution() {
		return unknownContributionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnknownContribution_ContributionType() {
		return (EAttribute)unknownContributionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAndContribution() {
		return andContributionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAndContribution_ContributionType() {
		return (EAttribute)andContributionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOrContribution() {
		return orContributionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOrContribution_ContributionType() {
		return (EAttribute)orContributionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssociation() {
		return associationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssociation_Source() {
		return (EReference)associationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssociation_Target() {
		return (EReference)associationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIsAAssociation() {
		return isAAssociationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIsAAssociation_Label() {
		return (EAttribute)isAAssociationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCoversAssociation() {
		return coversAssociationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCoversAssociation_Label() {
		return (EAttribute)coversAssociationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIsPartOfAssociation() {
		return isPartOfAssociationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIsPartOfAssociation_Label() {
		return (EAttribute)isPartOfAssociationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOccupiesAssociation() {
		return occupiesAssociationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOccupiesAssociation_Label() {
		return (EAttribute)occupiesAssociationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPlaysAssociation() {
		return playsAssociationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPlaysAssociation_Label() {
		return (EAttribute)playsAssociationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getINSAssociation() {
		return insAssociationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getINSAssociation_Label() {
		return (EAttribute)insAssociationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAlternative() {
		return alternativeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAlternative_Name() {
		return (EAttribute)alternativeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAlternative_Description() {
		return (EAttribute)alternativeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAlternative_Intentions() {
		return (EReference)alternativeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAlternative_EvalLabels() {
		return (EAttribute)alternativeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAlternative_Direction() {
		return (EAttribute)alternativeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntentionToEvaluationLabelMap() {
		return intentionToEvaluationLabelMapEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIntentionToEvaluationLabelMap_Key() {
		return (EReference)intentionToEvaluationLabelMapEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntentionToEvaluationLabelMap_Value() {
		return (EAttribute)intentionToEvaluationLabelMapEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHumanJudgment() {
		return humanJudgmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHumanJudgment_ResultLabel() {
		return (EAttribute)humanJudgmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getHumanJudgment_Enabled() {
		return (EAttribute)humanJudgmentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHumanJudgment_LabelBag() {
		return (EReference)humanJudgmentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLabelBag() {
		return labelBagEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLabelBag_LabelBagIntentions() {
		return (EReference)labelBagEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelBag_LabelBagEvalLabels() {
		return (EAttribute)labelBagEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelBag_ToResolve() {
		return (EAttribute)labelBagEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelBag_AllPositive() {
		return (EAttribute)labelBagEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelBag_AllNegative() {
		return (EAttribute)labelBagEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelBag_HasFullPositive() {
		return (EAttribute)labelBagEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelBag_HasFullNegative() {
		return (EAttribute)labelBagEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelBag_HasUnknown() {
		return (EAttribute)labelBagEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelBag_HasConflict() {
		return (EAttribute)labelBagEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelBag_AllUnknown() {
		return (EAttribute)labelBagEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLabelBag_AllConflict() {
		return (EAttribute)labelBagEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getEvaluationLabel() {
		return evaluationLabelEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getGoalModelingContributionType() {
		return goalModelingContributionTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getGoalModelingContributionSymmetry() {
		return goalModelingContributionSymmetryEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public openome_modelFactory getopenome_modelFactory() {
		return (openome_modelFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		actorEClass = createEClass(ACTOR);
		createEReference(actorEClass, ACTOR__IS_A);
		createEReference(actorEClass, ACTOR__IS_PART_OF);
		createEReference(actorEClass, ACTOR__BELIEVES);

		agentEClass = createEClass(AGENT);
		createEReference(agentEClass, AGENT__PLAYS);
		createEReference(agentEClass, AGENT__OCCUPIES);
		createEReference(agentEClass, AGENT__INS);

		andDecompositionEClass = createEClass(AND_DECOMPOSITION);

		contributionEClass = createEClass(CONTRIBUTION);
		createEReference(contributionEClass, CONTRIBUTION__TARGET);
		createEReference(contributionEClass, CONTRIBUTION__MODEL);
		createEReference(contributionEClass, CONTRIBUTION__SOURCE);
		createEAttribute(contributionEClass, CONTRIBUTION__GOAL_MODEL_SYMMETRY);

		decompositionEClass = createEClass(DECOMPOSITION);
		createEReference(decompositionEClass, DECOMPOSITION__SOURCE);
		createEReference(decompositionEClass, DECOMPOSITION__TARGET);
		createEReference(decompositionEClass, DECOMPOSITION__MODEL);

		dependencyEClass = createEClass(DEPENDENCY);
		createEReference(dependencyEClass, DEPENDENCY__DEPENDENCY_FROM);
		createEReference(dependencyEClass, DEPENDENCY__DEPENDENCY_TO);
		createEAttribute(dependencyEClass, DEPENDENCY__TRUST);
		createEAttribute(dependencyEClass, DEPENDENCY__LABEL);
		createEReference(dependencyEClass, DEPENDENCY__MODEL);

		goalEClass = createEClass(GOAL);

		intentionEClass = createEClass(INTENTION);
		createEAttribute(intentionEClass, INTENTION__NAME);
		createEAttribute(intentionEClass, INTENTION__SYSTEM);
		createEAttribute(intentionEClass, INTENTION__BOUNDARY);
		createEAttribute(intentionEClass, INTENTION__EXCLUSIVE);
		createEAttribute(intentionEClass, INTENTION__SEQUENTIAL);
		createEAttribute(intentionEClass, INTENTION__PARALLEL);
		createEReference(intentionEClass, INTENTION__PROPERTY);
		createEReference(intentionEClass, INTENTION__DECOMPOSITIONS);
		createEReference(intentionEClass, INTENTION__PARENT_DECOMPOSITIONS);
		createEReference(intentionEClass, INTENTION__CONTAINER);
		createEAttribute(intentionEClass, INTENTION__QUALITATIVE_REASONING_COMBINED_LABEL);
		createEAttribute(intentionEClass, INTENTION__QUALITATIVE_REASONING_SATISFIED_LABEL);
		createEAttribute(intentionEClass, INTENTION__QUALITATIVE_REASONING_DENIAL_LABEL);
		createEAttribute(intentionEClass, INTENTION__QUANTITATIVE_REASONING_COMBINED_LABEL);
		createEAttribute(intentionEClass, INTENTION__QUANTITATIVE_REASONING_DENIED_LABEL);
		createEAttribute(intentionEClass, INTENTION__QUANTITATIVE_REASONING_SATISFIED_LABEL);
		createEReference(intentionEClass, INTENTION__CONTRIBUTES_TO);
		createEReference(intentionEClass, INTENTION__CONTRIBUTES_FROM);
		createEReference(intentionEClass, INTENTION__LABEL_BAG);
		createEAttribute(intentionEClass, INTENTION__INITIAL_EVAL_LABEL);
		createEReference(intentionEClass, INTENTION__REVERSE_LABEL_BAG);
		createEReference(intentionEClass, INTENTION__HUMAN_JUDGMENTS);

		modelEClass = createEClass(MODEL);
		createEAttribute(modelEClass, MODEL__NAME);
		createEReference(modelEClass, MODEL__INTENTIONS);
		createEReference(modelEClass, MODEL__CONTRIBUTIONS);
		createEReference(modelEClass, MODEL__DEPENDENCIES);
		createEReference(modelEClass, MODEL__DECOMPOSITIONS);
		createEReference(modelEClass, MODEL__CONTAINERS);
		createEReference(modelEClass, MODEL__CORRELATIONS);
		createEReference(modelEClass, MODEL__ASSOCIATIONS);
		createEReference(modelEClass, MODEL__ALTERNATIVES);

		orDecompositionEClass = createEClass(OR_DECOMPOSITION);

		positionEClass = createEClass(POSITION);
		createEReference(positionEClass, POSITION__COVERS);

		propertyEClass = createEClass(PROPERTY);
		createEAttribute(propertyEClass, PROPERTY__NAME);
		createEAttribute(propertyEClass, PROPERTY__VALUE);

		resourceEClass = createEClass(RESOURCE);

		roleEClass = createEClass(ROLE);

		softgoalEClass = createEClass(SOFTGOAL);
		createEAttribute(softgoalEClass, SOFTGOAL__TOPIC);

		taskEClass = createEClass(TASK);

		containerEClass = createEClass(CONTAINER);
		createEAttribute(containerEClass, CONTAINER__NAME);
		createEReference(containerEClass, CONTAINER__SUB);
		createEReference(containerEClass, CONTAINER__INTENTIONS);
		createEReference(containerEClass, CONTAINER__MODEL);
		createEReference(containerEClass, CONTAINER__ASSOCIATION_TO);
		createEReference(containerEClass, CONTAINER__ASSOCIATION_FROM);

		beliefEClass = createEClass(BELIEF);

		linkEClass = createEClass(LINK);

		correlationEClass = createEClass(CORRELATION);
		createEReference(correlationEClass, CORRELATION__TARGET);
		createEReference(correlationEClass, CORRELATION__SOURCE);

		dependableEClass = createEClass(DEPENDABLE);
		createEReference(dependableEClass, DEPENDABLE__DEPENDENCY_FROM);
		createEReference(dependableEClass, DEPENDABLE__DEPENDENCY_TO);

		helpContributionEClass = createEClass(HELP_CONTRIBUTION);
		createEAttribute(helpContributionEClass, HELP_CONTRIBUTION__CONTRIBUTION_TYPE);

		hurtContributionEClass = createEClass(HURT_CONTRIBUTION);
		createEAttribute(hurtContributionEClass, HURT_CONTRIBUTION__CONTRIBUTION_TYPE);

		makeContributionEClass = createEClass(MAKE_CONTRIBUTION);
		createEAttribute(makeContributionEClass, MAKE_CONTRIBUTION__CONTRIBUTION_TYPE);

		breakContributionEClass = createEClass(BREAK_CONTRIBUTION);
		createEAttribute(breakContributionEClass, BREAK_CONTRIBUTION__CONTRIBUTION_TYPE);

		somePlusContributionEClass = createEClass(SOME_PLUS_CONTRIBUTION);
		createEAttribute(somePlusContributionEClass, SOME_PLUS_CONTRIBUTION__CONTRIBUTION_TYPE);

		someMinusContributionEClass = createEClass(SOME_MINUS_CONTRIBUTION);
		createEAttribute(someMinusContributionEClass, SOME_MINUS_CONTRIBUTION__CONTRIBUTION_TYPE);

		unknownContributionEClass = createEClass(UNKNOWN_CONTRIBUTION);
		createEAttribute(unknownContributionEClass, UNKNOWN_CONTRIBUTION__CONTRIBUTION_TYPE);

		andContributionEClass = createEClass(AND_CONTRIBUTION);
		createEAttribute(andContributionEClass, AND_CONTRIBUTION__CONTRIBUTION_TYPE);

		orContributionEClass = createEClass(OR_CONTRIBUTION);
		createEAttribute(orContributionEClass, OR_CONTRIBUTION__CONTRIBUTION_TYPE);

		associationEClass = createEClass(ASSOCIATION);
		createEReference(associationEClass, ASSOCIATION__SOURCE);
		createEReference(associationEClass, ASSOCIATION__TARGET);

		isAAssociationEClass = createEClass(IS_AASSOCIATION);
		createEAttribute(isAAssociationEClass, IS_AASSOCIATION__LABEL);

		coversAssociationEClass = createEClass(COVERS_ASSOCIATION);
		createEAttribute(coversAssociationEClass, COVERS_ASSOCIATION__LABEL);

		isPartOfAssociationEClass = createEClass(IS_PART_OF_ASSOCIATION);
		createEAttribute(isPartOfAssociationEClass, IS_PART_OF_ASSOCIATION__LABEL);

		occupiesAssociationEClass = createEClass(OCCUPIES_ASSOCIATION);
		createEAttribute(occupiesAssociationEClass, OCCUPIES_ASSOCIATION__LABEL);

		playsAssociationEClass = createEClass(PLAYS_ASSOCIATION);
		createEAttribute(playsAssociationEClass, PLAYS_ASSOCIATION__LABEL);

		insAssociationEClass = createEClass(INS_ASSOCIATION);
		createEAttribute(insAssociationEClass, INS_ASSOCIATION__LABEL);

		alternativeEClass = createEClass(ALTERNATIVE);
		createEAttribute(alternativeEClass, ALTERNATIVE__NAME);
		createEAttribute(alternativeEClass, ALTERNATIVE__DESCRIPTION);
		createEReference(alternativeEClass, ALTERNATIVE__INTENTIONS);
		createEAttribute(alternativeEClass, ALTERNATIVE__EVAL_LABELS);
		createEAttribute(alternativeEClass, ALTERNATIVE__DIRECTION);

		intentionToEvaluationLabelMapEClass = createEClass(INTENTION_TO_EVALUATION_LABEL_MAP);
		createEReference(intentionToEvaluationLabelMapEClass, INTENTION_TO_EVALUATION_LABEL_MAP__KEY);
		createEAttribute(intentionToEvaluationLabelMapEClass, INTENTION_TO_EVALUATION_LABEL_MAP__VALUE);

		humanJudgmentEClass = createEClass(HUMAN_JUDGMENT);
		createEAttribute(humanJudgmentEClass, HUMAN_JUDGMENT__RESULT_LABEL);
		createEAttribute(humanJudgmentEClass, HUMAN_JUDGMENT__ENABLED);
		createEReference(humanJudgmentEClass, HUMAN_JUDGMENT__LABEL_BAG);

		labelBagEClass = createEClass(LABEL_BAG);
		createEReference(labelBagEClass, LABEL_BAG__LABEL_BAG_INTENTIONS);
		createEAttribute(labelBagEClass, LABEL_BAG__LABEL_BAG_EVAL_LABELS);
		createEAttribute(labelBagEClass, LABEL_BAG__TO_RESOLVE);
		createEAttribute(labelBagEClass, LABEL_BAG__ALL_POSITIVE);
		createEAttribute(labelBagEClass, LABEL_BAG__ALL_NEGATIVE);
		createEAttribute(labelBagEClass, LABEL_BAG__HAS_FULL_POSITIVE);
		createEAttribute(labelBagEClass, LABEL_BAG__HAS_FULL_NEGATIVE);
		createEAttribute(labelBagEClass, LABEL_BAG__HAS_UNKNOWN);
		createEAttribute(labelBagEClass, LABEL_BAG__HAS_CONFLICT);
		createEAttribute(labelBagEClass, LABEL_BAG__ALL_UNKNOWN);
		createEAttribute(labelBagEClass, LABEL_BAG__ALL_CONFLICT);

		// Create enums
		evaluationLabelEEnum = createEEnum(EVALUATION_LABEL);
		goalModelingContributionTypeEEnum = createEEnum(GOAL_MODELING_CONTRIBUTION_TYPE);
		goalModelingContributionSymmetryEEnum = createEEnum(GOAL_MODELING_CONTRIBUTION_SYMMETRY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		actorEClass.getESuperTypes().add(this.getContainer());
		agentEClass.getESuperTypes().add(this.getContainer());
		andDecompositionEClass.getESuperTypes().add(this.getDecomposition());
		contributionEClass.getESuperTypes().add(this.getLink());
		decompositionEClass.getESuperTypes().add(this.getLink());
		dependencyEClass.getESuperTypes().add(this.getLink());
		goalEClass.getESuperTypes().add(this.getIntention());
		intentionEClass.getESuperTypes().add(this.getDependable());
		orDecompositionEClass.getESuperTypes().add(this.getDecomposition());
		positionEClass.getESuperTypes().add(this.getContainer());
		resourceEClass.getESuperTypes().add(this.getIntention());
		roleEClass.getESuperTypes().add(this.getContainer());
		softgoalEClass.getESuperTypes().add(this.getIntention());
		taskEClass.getESuperTypes().add(this.getIntention());
		containerEClass.getESuperTypes().add(this.getDependable());
		beliefEClass.getESuperTypes().add(this.getIntention());
		correlationEClass.getESuperTypes().add(this.getLink());
		helpContributionEClass.getESuperTypes().add(this.getContribution());
		hurtContributionEClass.getESuperTypes().add(this.getContribution());
		makeContributionEClass.getESuperTypes().add(this.getContribution());
		breakContributionEClass.getESuperTypes().add(this.getContribution());
		somePlusContributionEClass.getESuperTypes().add(this.getContribution());
		someMinusContributionEClass.getESuperTypes().add(this.getContribution());
		unknownContributionEClass.getESuperTypes().add(this.getContribution());
		andContributionEClass.getESuperTypes().add(this.getContribution());
		orContributionEClass.getESuperTypes().add(this.getContribution());
		associationEClass.getESuperTypes().add(this.getLink());
		isAAssociationEClass.getESuperTypes().add(this.getAssociation());
		coversAssociationEClass.getESuperTypes().add(this.getAssociation());
		isPartOfAssociationEClass.getESuperTypes().add(this.getAssociation());
		occupiesAssociationEClass.getESuperTypes().add(this.getAssociation());
		playsAssociationEClass.getESuperTypes().add(this.getAssociation());
		insAssociationEClass.getESuperTypes().add(this.getAssociation());

		// Initialize classes and features; add operations and parameters
		initEClass(actorEClass, Actor.class, "Actor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActor_Is_a(), this.getActor(), null, "is_a", null, 0, -1, Actor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActor_Is_part_of(), this.getActor(), null, "is_part_of", null, 0, -1, Actor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActor_Believes(), this.getBelief(), null, "believes", null, 0, -1, Actor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(agentEClass, Agent.class, "Agent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAgent_Plays(), this.getRole(), null, "plays", null, 0, -1, Agent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAgent_Occupies(), this.getPosition(), null, "occupies", null, 0, -1, Agent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAgent_Ins(), this.getAgent(), null, "ins", null, 0, 1, Agent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(andDecompositionEClass, AndDecomposition.class, "AndDecomposition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(contributionEClass, Contribution.class, "Contribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContribution_Target(), this.getIntention(), this.getIntention_ContributesFrom(), "target", null, 0, 1, Contribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContribution_Model(), this.getModel(), this.getModel_Contributions(), "model", null, 0, 1, Contribution.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContribution_Source(), this.getIntention(), this.getIntention_ContributesTo(), "source", null, 0, 1, Contribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContribution_Goal_model_symmetry(), this.getGoalModelingContributionSymmetry(), "goal_model_symmetry", null, 0, 1, Contribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(decompositionEClass, Decomposition.class, "Decomposition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDecomposition_Source(), this.getIntention(), this.getIntention_Decompositions(), "source", null, 0, 1, Decomposition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDecomposition_Target(), this.getIntention(), this.getIntention_ParentDecompositions(), "target", null, 0, 1, Decomposition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDecomposition_Model(), this.getModel(), this.getModel_Decompositions(), "model", null, 0, 1, Decomposition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dependencyEClass, Dependency.class, "Dependency", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDependency_DependencyFrom(), this.getDependable(), this.getDependable_DependencyTo(), "dependencyFrom", null, 0, 1, Dependency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDependency_DependencyTo(), this.getDependable(), this.getDependable_DependencyFrom(), "dependencyTo", null, 0, 1, Dependency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDependency_Trust(), ecorePackage.getEFloat(), "trust", null, 0, 1, Dependency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDependency_Label(), ecorePackage.getEString(), "label", null, 0, 1, Dependency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDependency_Model(), this.getModel(), this.getModel_Dependencies(), "model", null, 0, 1, Dependency.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(goalEClass, Goal.class, "Goal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(intentionEClass, Intention.class, "Intention", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntention_Name(), ecorePackage.getEString(), "name", null, 0, 1, Intention.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIntention_System(), ecorePackage.getEBooleanObject(), "system", "true", 0, 1, Intention.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIntention_Boundary(), ecorePackage.getEBooleanObject(), "boundary", "false", 0, 1, Intention.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIntention_Exclusive(), ecorePackage.getEBooleanObject(), "exclusive", "true", 0, 1, Intention.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIntention_Sequential(), ecorePackage.getEBooleanObject(), "sequential", "true", 0, 1, Intention.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIntention_Parallel(), ecorePackage.getEBooleanObject(), "parallel", "false", 0, 1, Intention.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIntention_Property(), this.getProperty(), null, "property", null, 0, -1, Intention.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIntention_Decompositions(), this.getDecomposition(), this.getDecomposition_Source(), "decompositions", null, 0, -1, Intention.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIntention_ParentDecompositions(), this.getDecomposition(), this.getDecomposition_Target(), "parentDecompositions", null, 0, -1, Intention.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIntention_Container(), this.getContainer(), this.getContainer_Intentions(), "container", null, 0, 1, Intention.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIntention_QualitativeReasoningCombinedLabel(), this.getEvaluationLabel(), "QualitativeReasoningCombinedLabel", null, 0, 1, Intention.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIntention_QualitativeReasoningSatisfiedLabel(), this.getEvaluationLabel(), "QualitativeReasoningSatisfiedLabel", null, 0, 1, Intention.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIntention_QualitativeReasoningDenialLabel(), this.getEvaluationLabel(), "QualitativeReasoningDenialLabel", null, 0, 1, Intention.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIntention_QuantitativeReasoningCombinedLabel(), ecorePackage.getEDouble(), "QuantitativeReasoningCombinedLabel", null, 0, 1, Intention.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIntention_QuantitativeReasoningDeniedLabel(), ecorePackage.getEDouble(), "QuantitativeReasoningDeniedLabel", null, 0, 1, Intention.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIntention_QuantitativeReasoningSatisfiedLabel(), ecorePackage.getEDouble(), "QuantitativeReasoningSatisfiedLabel", null, 0, 1, Intention.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIntention_ContributesTo(), this.getContribution(), this.getContribution_Source(), "contributesTo", null, 0, -1, Intention.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIntention_ContributesFrom(), this.getContribution(), this.getContribution_Target(), "contributesFrom", null, 0, -1, Intention.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIntention_LabelBag(), this.getLabelBag(), null, "labelBag", null, 0, 1, Intention.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIntention_InitialEvalLabel(), this.getEvaluationLabel(), "initialEvalLabel", null, 0, 1, Intention.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIntention_ReverseLabelBag(), this.getLabelBag(), null, "reverseLabelBag", null, 0, 1, Intention.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIntention_HumanJudgments(), this.getHumanJudgment(), null, "humanJudgments", null, 0, -1, Intention.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelEClass, Model.class, "Model", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModel_Name(), ecorePackage.getEString(), "name", null, 0, 1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Intentions(), this.getIntention(), null, "intentions", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Contributions(), this.getContribution(), this.getContribution_Model(), "contributions", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Dependencies(), this.getDependency(), this.getDependency_Model(), "dependencies", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Decompositions(), this.getDecomposition(), this.getDecomposition_Model(), "decompositions", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Containers(), this.getContainer(), this.getContainer_Model(), "containers", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Correlations(), this.getCorrelation(), null, "correlations", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Associations(), this.getAssociation(), null, "associations", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModel_Alternatives(), this.getAlternative(), null, "alternatives", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(orDecompositionEClass, OrDecomposition.class, "OrDecomposition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(positionEClass, Position.class, "Position", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPosition_Covers(), this.getRole(), null, "covers", null, 0, -1, Position.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertyEClass, Property.class, "Property", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProperty_Name(), ecorePackage.getEString(), "name", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProperty_Value(), ecorePackage.getEString(), "value", null, 0, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourceEClass, Resource.class, "Resource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(roleEClass, Role.class, "Role", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(softgoalEClass, Softgoal.class, "Softgoal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSoftgoal_Topic(), ecorePackage.getEString(), "topic", null, 0, 1, Softgoal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(taskEClass, Task.class, "Task", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(containerEClass, edu.toronto.cs.openome_model.Container.class, "Container", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getContainer_Name(), ecorePackage.getEString(), "name", null, 0, 1, edu.toronto.cs.openome_model.Container.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainer_Sub(), this.getActor(), null, "sub", null, 0, -1, edu.toronto.cs.openome_model.Container.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainer_Intentions(), this.getIntention(), this.getIntention_Container(), "intentions", null, 0, -1, edu.toronto.cs.openome_model.Container.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainer_Model(), this.getModel(), this.getModel_Containers(), "model", null, 0, 1, edu.toronto.cs.openome_model.Container.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainer_AssociationTo(), this.getAssociation(), null, "associationTo", null, 0, -1, edu.toronto.cs.openome_model.Container.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContainer_AssociationFrom(), this.getAssociation(), null, "associationFrom", null, 0, -1, edu.toronto.cs.openome_model.Container.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(beliefEClass, Belief.class, "Belief", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(linkEClass, Link.class, "Link", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(correlationEClass, Correlation.class, "Correlation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCorrelation_Target(), this.getIntention(), null, "target", null, 0, 1, Correlation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCorrelation_Source(), this.getIntention(), null, "source", null, 0, 1, Correlation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dependableEClass, Dependable.class, "Dependable", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDependable_DependencyFrom(), this.getDependency(), this.getDependency_DependencyTo(), "dependencyFrom", null, 0, -1, Dependable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDependable_DependencyTo(), this.getDependency(), this.getDependency_DependencyFrom(), "dependencyTo", null, 0, -1, Dependable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(helpContributionEClass, HelpContribution.class, "HelpContribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getHelpContribution_ContributionType(), ecorePackage.getEString(), "contributionType", "Help", 1, 1, HelpContribution.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(hurtContributionEClass, HurtContribution.class, "HurtContribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getHurtContribution_ContributionType(), ecorePackage.getEString(), "contributionType", "Hurt", 1, 1, HurtContribution.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(makeContributionEClass, MakeContribution.class, "MakeContribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMakeContribution_ContributionType(), ecorePackage.getEString(), "contributionType", "Make", 1, 1, MakeContribution.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(breakContributionEClass, BreakContribution.class, "BreakContribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBreakContribution_ContributionType(), ecorePackage.getEString(), "contributionType", "Break", 1, 1, BreakContribution.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(somePlusContributionEClass, SomePlusContribution.class, "SomePlusContribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSomePlusContribution_ContributionType(), ecorePackage.getEString(), "contributionType", "Some+", 1, 1, SomePlusContribution.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(someMinusContributionEClass, SomeMinusContribution.class, "SomeMinusContribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSomeMinusContribution_ContributionType(), ecorePackage.getEString(), "contributionType", "Some-", 1, 1, SomeMinusContribution.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unknownContributionEClass, UnknownContribution.class, "UnknownContribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUnknownContribution_ContributionType(), ecorePackage.getEString(), "contributionType", "Unknown", 1, 1, UnknownContribution.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(andContributionEClass, AndContribution.class, "AndContribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAndContribution_ContributionType(), ecorePackage.getEString(), "contributionType", "AND", 1, 1, AndContribution.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(orContributionEClass, OrContribution.class, "OrContribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOrContribution_ContributionType(), ecorePackage.getEString(), "contributionType", "OR", 1, 1, OrContribution.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(associationEClass, Association.class, "Association", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAssociation_Source(), this.getContainer(), null, "source", null, 0, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAssociation_Target(), this.getContainer(), null, "target", null, 0, 1, Association.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(isAAssociationEClass, IsAAssociation.class, "IsAAssociation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIsAAssociation_Label(), ecorePackage.getEString(), "label", "ISA", 0, 1, IsAAssociation.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(coversAssociationEClass, CoversAssociation.class, "CoversAssociation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCoversAssociation_Label(), ecorePackage.getEString(), "label", "Covers", 0, 1, CoversAssociation.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(isPartOfAssociationEClass, IsPartOfAssociation.class, "IsPartOfAssociation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIsPartOfAssociation_Label(), ecorePackage.getEString(), "label", "Is part of", 0, 1, IsPartOfAssociation.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(occupiesAssociationEClass, OccupiesAssociation.class, "OccupiesAssociation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOccupiesAssociation_Label(), ecorePackage.getEString(), "label", "Occupies", 0, 1, OccupiesAssociation.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(playsAssociationEClass, PlaysAssociation.class, "PlaysAssociation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPlaysAssociation_Label(), ecorePackage.getEString(), "label", "Plays", 0, 1, PlaysAssociation.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(insAssociationEClass, INSAssociation.class, "INSAssociation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getINSAssociation_Label(), ecorePackage.getEString(), "label", "INS", 0, 1, INSAssociation.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(alternativeEClass, Alternative.class, "Alternative", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAlternative_Name(), ecorePackage.getEString(), "name", " ", 0, 1, Alternative.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAlternative_Description(), ecorePackage.getEString(), "description", " ", 0, 1, Alternative.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAlternative_Intentions(), this.getIntention(), null, "intentions", null, 0, -1, Alternative.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAlternative_EvalLabels(), this.getEvaluationLabel(), "evalLabels", null, 0, -1, Alternative.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAlternative_Direction(), ecorePackage.getEString(), "direction", null, 0, 1, Alternative.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(intentionToEvaluationLabelMapEClass, Map.Entry.class, "IntentionToEvaluationLabelMap", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIntentionToEvaluationLabelMap_Key(), this.getIntention(), null, "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIntentionToEvaluationLabelMap_Value(), this.getEvaluationLabel(), "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(humanJudgmentEClass, HumanJudgment.class, "HumanJudgment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getHumanJudgment_ResultLabel(), this.getEvaluationLabel(), "resultLabel", null, 0, 1, HumanJudgment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHumanJudgment_Enabled(), ecorePackage.getEBoolean(), "enabled", null, 0, 1, HumanJudgment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHumanJudgment_LabelBag(), this.getLabelBag(), null, "labelBag", null, 0, 1, HumanJudgment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(labelBagEClass, LabelBag.class, "LabelBag", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLabelBag_LabelBagIntentions(), this.getIntention(), null, "labelBagIntentions", null, 0, -1, LabelBag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabelBag_LabelBagEvalLabels(), this.getEvaluationLabel(), "labelBagEvalLabels", null, 0, -1, LabelBag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getLabelBag_ToResolve(), ecorePackage.getEBoolean(), "toResolve", null, 0, 1, LabelBag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabelBag_AllPositive(), ecorePackage.getEBoolean(), "allPositive", null, 0, 1, LabelBag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabelBag_AllNegative(), ecorePackage.getEBoolean(), "allNegative", null, 0, 1, LabelBag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabelBag_HasFullPositive(), ecorePackage.getEBoolean(), "hasFullPositive", null, 0, 1, LabelBag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabelBag_HasFullNegative(), ecorePackage.getEBoolean(), "hasFullNegative", null, 0, 1, LabelBag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabelBag_HasUnknown(), ecorePackage.getEBoolean(), "hasUnknown", null, 0, 1, LabelBag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabelBag_HasConflict(), ecorePackage.getEBoolean(), "hasConflict", null, 0, 1, LabelBag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabelBag_AllUnknown(), ecorePackage.getEBoolean(), "allUnknown", null, 0, 1, LabelBag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabelBag_AllConflict(), ecorePackage.getEBoolean(), "allConflict", null, 0, 1, LabelBag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(evaluationLabelEEnum, EvaluationLabel.class, "EvaluationLabel");
		addEEnumLiteral(evaluationLabelEEnum, EvaluationLabel.NONE);
		addEEnumLiteral(evaluationLabelEEnum, EvaluationLabel.UNKNOWN);
		addEEnumLiteral(evaluationLabelEEnum, EvaluationLabel.SATISFIED);
		addEEnumLiteral(evaluationLabelEEnum, EvaluationLabel.PARTIALLY_SATISFIED);
		addEEnumLiteral(evaluationLabelEEnum, EvaluationLabel.DENIED);
		addEEnumLiteral(evaluationLabelEEnum, EvaluationLabel.PARTIALLY_DENIED);
		addEEnumLiteral(evaluationLabelEEnum, EvaluationLabel.CONFLICT);

		initEEnum(goalModelingContributionTypeEEnum, GoalModelingContributionType.class, "GoalModelingContributionType");
		addEEnumLiteral(goalModelingContributionTypeEEnum, GoalModelingContributionType.NONE);
		addEEnumLiteral(goalModelingContributionTypeEEnum, GoalModelingContributionType.PLUS_PLUS);
		addEEnumLiteral(goalModelingContributionTypeEEnum, GoalModelingContributionType.PLUS);
		addEEnumLiteral(goalModelingContributionTypeEEnum, GoalModelingContributionType.MINUS);
		addEEnumLiteral(goalModelingContributionTypeEEnum, GoalModelingContributionType.MINUS_MINUS);

		initEEnum(goalModelingContributionSymmetryEEnum, GoalModelingContributionSymmetry.class, "GoalModelingContributionSymmetry");
		addEEnumLiteral(goalModelingContributionSymmetryEEnum, GoalModelingContributionSymmetry.BOTH);
		addEEnumLiteral(goalModelingContributionSymmetryEEnum, GoalModelingContributionSymmetry.SAT);
		addEEnumLiteral(goalModelingContributionSymmetryEEnum, GoalModelingContributionSymmetry.DEN);

		// Create resource
		createResource(eNS_URI);
	}

} //openome_modelPackageImpl

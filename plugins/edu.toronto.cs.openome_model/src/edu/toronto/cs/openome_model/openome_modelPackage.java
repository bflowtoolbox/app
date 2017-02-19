/**
 * Copyright 2001-2008 University of Toronto
 *
 * $Id$
 */
package edu.toronto.cs.openome_model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see edu.toronto.cs.openome_model.openome_modelFactory
 * @model kind="package"
 * @generated
 */
public interface openome_modelPackage extends EPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright 2001-2008 University of Toronto";

	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "openome_model";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///edu/toronto/cs/openome_model.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "edu.toronto.cs.openome_model";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	openome_modelPackage eINSTANCE = edu.toronto.cs.openome_model.impl.openome_modelPackageImpl.init();

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.DependableImpl <em>Dependable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.DependableImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getDependable()
	 * @generated
	 */
	int DEPENDABLE = 20;

	/**
	 * The feature id for the '<em><b>Dependency From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDABLE__DEPENDENCY_FROM = 0;

	/**
	 * The feature id for the '<em><b>Dependency To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDABLE__DEPENDENCY_TO = 1;

	/**
	 * The number of structural features of the '<em>Dependable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDABLE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.ContainerImpl <em>Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.ContainerImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getContainer()
	 * @generated
	 */
	int CONTAINER = 16;

	/**
	 * The feature id for the '<em><b>Dependency From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__DEPENDENCY_FROM = DEPENDABLE__DEPENDENCY_FROM;

	/**
	 * The feature id for the '<em><b>Dependency To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__DEPENDENCY_TO = DEPENDABLE__DEPENDENCY_TO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__NAME = DEPENDABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Sub</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__SUB = DEPENDABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Intentions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__INTENTIONS = DEPENDABLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__MODEL = DEPENDABLE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Association To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ASSOCIATION_TO = DEPENDABLE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Association From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ASSOCIATION_FROM = DEPENDABLE_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_FEATURE_COUNT = DEPENDABLE_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.ActorImpl <em>Actor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.ActorImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getActor()
	 * @generated
	 */
	int ACTOR = 0;

	/**
	 * The feature id for the '<em><b>Dependency From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__DEPENDENCY_FROM = CONTAINER__DEPENDENCY_FROM;

	/**
	 * The feature id for the '<em><b>Dependency To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__DEPENDENCY_TO = CONTAINER__DEPENDENCY_TO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__NAME = CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Sub</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__SUB = CONTAINER__SUB;

	/**
	 * The feature id for the '<em><b>Intentions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__INTENTIONS = CONTAINER__INTENTIONS;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__MODEL = CONTAINER__MODEL;

	/**
	 * The feature id for the '<em><b>Association To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__ASSOCIATION_TO = CONTAINER__ASSOCIATION_TO;

	/**
	 * The feature id for the '<em><b>Association From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__ASSOCIATION_FROM = CONTAINER__ASSOCIATION_FROM;

	/**
	 * The feature id for the '<em><b>Is a</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__IS_A = CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is part of</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__IS_PART_OF = CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Believes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__BELIEVES = CONTAINER_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Actor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_FEATURE_COUNT = CONTAINER_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.AgentImpl <em>Agent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.AgentImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getAgent()
	 * @generated
	 */
	int AGENT = 1;

	/**
	 * The feature id for the '<em><b>Dependency From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__DEPENDENCY_FROM = CONTAINER__DEPENDENCY_FROM;

	/**
	 * The feature id for the '<em><b>Dependency To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__DEPENDENCY_TO = CONTAINER__DEPENDENCY_TO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__NAME = CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Sub</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__SUB = CONTAINER__SUB;

	/**
	 * The feature id for the '<em><b>Intentions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__INTENTIONS = CONTAINER__INTENTIONS;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__MODEL = CONTAINER__MODEL;

	/**
	 * The feature id for the '<em><b>Association To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__ASSOCIATION_TO = CONTAINER__ASSOCIATION_TO;

	/**
	 * The feature id for the '<em><b>Association From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__ASSOCIATION_FROM = CONTAINER__ASSOCIATION_FROM;

	/**
	 * The feature id for the '<em><b>Plays</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__PLAYS = CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Occupies</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__OCCUPIES = CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Ins</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT__INS = CONTAINER_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Agent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AGENT_FEATURE_COUNT = CONTAINER_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.LinkImpl <em>Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.LinkImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getLink()
	 * @generated
	 */
	int LINK = 18;

	/**
	 * The number of structural features of the '<em>Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.DecompositionImpl <em>Decomposition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.DecompositionImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getDecomposition()
	 * @generated
	 */
	int DECOMPOSITION = 4;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECOMPOSITION__SOURCE = LINK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECOMPOSITION__TARGET = LINK_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECOMPOSITION__MODEL = LINK_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Decomposition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECOMPOSITION_FEATURE_COUNT = LINK_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.AndDecompositionImpl <em>And Decomposition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.AndDecompositionImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getAndDecomposition()
	 * @generated
	 */
	int AND_DECOMPOSITION = 2;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_DECOMPOSITION__SOURCE = DECOMPOSITION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_DECOMPOSITION__TARGET = DECOMPOSITION__TARGET;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_DECOMPOSITION__MODEL = DECOMPOSITION__MODEL;

	/**
	 * The number of structural features of the '<em>And Decomposition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_DECOMPOSITION_FEATURE_COUNT = DECOMPOSITION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.ContributionImpl <em>Contribution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.ContributionImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getContribution()
	 * @generated
	 */
	int CONTRIBUTION = 3;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__TARGET = LINK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__MODEL = LINK_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__SOURCE = LINK_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Goal model symmetry</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__GOAL_MODEL_SYMMETRY = LINK_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Contribution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_FEATURE_COUNT = LINK_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.DependencyImpl <em>Dependency</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.DependencyImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getDependency()
	 * @generated
	 */
	int DEPENDENCY = 5;

	/**
	 * The feature id for the '<em><b>Dependency From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY__DEPENDENCY_FROM = LINK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dependency To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY__DEPENDENCY_TO = LINK_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Trust</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY__TRUST = LINK_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY__LABEL = LINK_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY__MODEL = LINK_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Dependency</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY_FEATURE_COUNT = LINK_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.IntentionImpl <em>Intention</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.IntentionImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getIntention()
	 * @generated
	 */
	int INTENTION = 7;

	/**
	 * The feature id for the '<em><b>Dependency From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTION__DEPENDENCY_FROM = DEPENDABLE__DEPENDENCY_FROM;

	/**
	 * The feature id for the '<em><b>Dependency To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTION__DEPENDENCY_TO = DEPENDABLE__DEPENDENCY_TO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTION__NAME = DEPENDABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>System</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTION__SYSTEM = DEPENDABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Boundary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTION__BOUNDARY = DEPENDABLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Exclusive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTION__EXCLUSIVE = DEPENDABLE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Sequential</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTION__SEQUENTIAL = DEPENDABLE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Parallel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTION__PARALLEL = DEPENDABLE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTION__PROPERTY = DEPENDABLE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Decompositions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTION__DECOMPOSITIONS = DEPENDABLE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Parent Decompositions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTION__PARENT_DECOMPOSITIONS = DEPENDABLE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTION__CONTAINER = DEPENDABLE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Qualitative Reasoning Combined Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTION__QUALITATIVE_REASONING_COMBINED_LABEL = DEPENDABLE_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Qualitative Reasoning Satisfied Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTION__QUALITATIVE_REASONING_SATISFIED_LABEL = DEPENDABLE_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Qualitative Reasoning Denial Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTION__QUALITATIVE_REASONING_DENIAL_LABEL = DEPENDABLE_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Quantitative Reasoning Combined Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTION__QUANTITATIVE_REASONING_COMBINED_LABEL = DEPENDABLE_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Quantitative Reasoning Denied Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTION__QUANTITATIVE_REASONING_DENIED_LABEL = DEPENDABLE_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Quantitative Reasoning Satisfied Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTION__QUANTITATIVE_REASONING_SATISFIED_LABEL = DEPENDABLE_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Contributes To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTION__CONTRIBUTES_TO = DEPENDABLE_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Contributes From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTION__CONTRIBUTES_FROM = DEPENDABLE_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>Label Bag</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTION__LABEL_BAG = DEPENDABLE_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>Initial Eval Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTION__INITIAL_EVAL_LABEL = DEPENDABLE_FEATURE_COUNT + 19;

	/**
	 * The feature id for the '<em><b>Reverse Label Bag</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTION__REVERSE_LABEL_BAG = DEPENDABLE_FEATURE_COUNT + 20;

	/**
	 * The feature id for the '<em><b>Human Judgments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTION__HUMAN_JUDGMENTS = DEPENDABLE_FEATURE_COUNT + 21;

	/**
	 * The number of structural features of the '<em>Intention</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTION_FEATURE_COUNT = DEPENDABLE_FEATURE_COUNT + 22;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.GoalImpl <em>Goal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.GoalImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getGoal()
	 * @generated
	 */
	int GOAL = 6;

	/**
	 * The feature id for the '<em><b>Dependency From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__DEPENDENCY_FROM = INTENTION__DEPENDENCY_FROM;

	/**
	 * The feature id for the '<em><b>Dependency To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__DEPENDENCY_TO = INTENTION__DEPENDENCY_TO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__NAME = INTENTION__NAME;

	/**
	 * The feature id for the '<em><b>System</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__SYSTEM = INTENTION__SYSTEM;

	/**
	 * The feature id for the '<em><b>Boundary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__BOUNDARY = INTENTION__BOUNDARY;

	/**
	 * The feature id for the '<em><b>Exclusive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__EXCLUSIVE = INTENTION__EXCLUSIVE;

	/**
	 * The feature id for the '<em><b>Sequential</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__SEQUENTIAL = INTENTION__SEQUENTIAL;

	/**
	 * The feature id for the '<em><b>Parallel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__PARALLEL = INTENTION__PARALLEL;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__PROPERTY = INTENTION__PROPERTY;

	/**
	 * The feature id for the '<em><b>Decompositions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__DECOMPOSITIONS = INTENTION__DECOMPOSITIONS;

	/**
	 * The feature id for the '<em><b>Parent Decompositions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__PARENT_DECOMPOSITIONS = INTENTION__PARENT_DECOMPOSITIONS;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__CONTAINER = INTENTION__CONTAINER;

	/**
	 * The feature id for the '<em><b>Qualitative Reasoning Combined Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__QUALITATIVE_REASONING_COMBINED_LABEL = INTENTION__QUALITATIVE_REASONING_COMBINED_LABEL;

	/**
	 * The feature id for the '<em><b>Qualitative Reasoning Satisfied Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__QUALITATIVE_REASONING_SATISFIED_LABEL = INTENTION__QUALITATIVE_REASONING_SATISFIED_LABEL;

	/**
	 * The feature id for the '<em><b>Qualitative Reasoning Denial Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__QUALITATIVE_REASONING_DENIAL_LABEL = INTENTION__QUALITATIVE_REASONING_DENIAL_LABEL;

	/**
	 * The feature id for the '<em><b>Quantitative Reasoning Combined Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__QUANTITATIVE_REASONING_COMBINED_LABEL = INTENTION__QUANTITATIVE_REASONING_COMBINED_LABEL;

	/**
	 * The feature id for the '<em><b>Quantitative Reasoning Denied Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__QUANTITATIVE_REASONING_DENIED_LABEL = INTENTION__QUANTITATIVE_REASONING_DENIED_LABEL;

	/**
	 * The feature id for the '<em><b>Quantitative Reasoning Satisfied Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__QUANTITATIVE_REASONING_SATISFIED_LABEL = INTENTION__QUANTITATIVE_REASONING_SATISFIED_LABEL;

	/**
	 * The feature id for the '<em><b>Contributes To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__CONTRIBUTES_TO = INTENTION__CONTRIBUTES_TO;

	/**
	 * The feature id for the '<em><b>Contributes From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__CONTRIBUTES_FROM = INTENTION__CONTRIBUTES_FROM;

	/**
	 * The feature id for the '<em><b>Label Bag</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__LABEL_BAG = INTENTION__LABEL_BAG;

	/**
	 * The feature id for the '<em><b>Initial Eval Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__INITIAL_EVAL_LABEL = INTENTION__INITIAL_EVAL_LABEL;

	/**
	 * The feature id for the '<em><b>Reverse Label Bag</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__REVERSE_LABEL_BAG = INTENTION__REVERSE_LABEL_BAG;

	/**
	 * The feature id for the '<em><b>Human Judgments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL__HUMAN_JUDGMENTS = INTENTION__HUMAN_JUDGMENTS;

	/**
	 * The number of structural features of the '<em>Goal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOAL_FEATURE_COUNT = INTENTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.ModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.ModelImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getModel()
	 * @generated
	 */
	int MODEL = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__NAME = 0;

	/**
	 * The feature id for the '<em><b>Intentions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__INTENTIONS = 1;

	/**
	 * The feature id for the '<em><b>Contributions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__CONTRIBUTIONS = 2;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__DEPENDENCIES = 3;

	/**
	 * The feature id for the '<em><b>Decompositions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__DECOMPOSITIONS = 4;

	/**
	 * The feature id for the '<em><b>Containers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__CONTAINERS = 5;

	/**
	 * The feature id for the '<em><b>Correlations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__CORRELATIONS = 6;

	/**
	 * The feature id for the '<em><b>Associations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__ASSOCIATIONS = 7;

	/**
	 * The feature id for the '<em><b>Alternatives</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__ALTERNATIVES = 8;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.OrDecompositionImpl <em>Or Decomposition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.OrDecompositionImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getOrDecomposition()
	 * @generated
	 */
	int OR_DECOMPOSITION = 9;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_DECOMPOSITION__SOURCE = DECOMPOSITION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_DECOMPOSITION__TARGET = DECOMPOSITION__TARGET;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_DECOMPOSITION__MODEL = DECOMPOSITION__MODEL;

	/**
	 * The number of structural features of the '<em>Or Decomposition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_DECOMPOSITION_FEATURE_COUNT = DECOMPOSITION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.PositionImpl <em>Position</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.PositionImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getPosition()
	 * @generated
	 */
	int POSITION = 10;

	/**
	 * The feature id for the '<em><b>Dependency From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSITION__DEPENDENCY_FROM = CONTAINER__DEPENDENCY_FROM;

	/**
	 * The feature id for the '<em><b>Dependency To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSITION__DEPENDENCY_TO = CONTAINER__DEPENDENCY_TO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSITION__NAME = CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Sub</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSITION__SUB = CONTAINER__SUB;

	/**
	 * The feature id for the '<em><b>Intentions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSITION__INTENTIONS = CONTAINER__INTENTIONS;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSITION__MODEL = CONTAINER__MODEL;

	/**
	 * The feature id for the '<em><b>Association To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSITION__ASSOCIATION_TO = CONTAINER__ASSOCIATION_TO;

	/**
	 * The feature id for the '<em><b>Association From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSITION__ASSOCIATION_FROM = CONTAINER__ASSOCIATION_FROM;

	/**
	 * The feature id for the '<em><b>Covers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSITION__COVERS = CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Position</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSITION_FEATURE_COUNT = CONTAINER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.PropertyImpl <em>Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.PropertyImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getProperty()
	 * @generated
	 */
	int PROPERTY = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__NAME = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.ResourceImpl <em>Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.ResourceImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getResource()
	 * @generated
	 */
	int RESOURCE = 12;

	/**
	 * The feature id for the '<em><b>Dependency From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__DEPENDENCY_FROM = INTENTION__DEPENDENCY_FROM;

	/**
	 * The feature id for the '<em><b>Dependency To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__DEPENDENCY_TO = INTENTION__DEPENDENCY_TO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__NAME = INTENTION__NAME;

	/**
	 * The feature id for the '<em><b>System</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__SYSTEM = INTENTION__SYSTEM;

	/**
	 * The feature id for the '<em><b>Boundary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__BOUNDARY = INTENTION__BOUNDARY;

	/**
	 * The feature id for the '<em><b>Exclusive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__EXCLUSIVE = INTENTION__EXCLUSIVE;

	/**
	 * The feature id for the '<em><b>Sequential</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__SEQUENTIAL = INTENTION__SEQUENTIAL;

	/**
	 * The feature id for the '<em><b>Parallel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__PARALLEL = INTENTION__PARALLEL;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__PROPERTY = INTENTION__PROPERTY;

	/**
	 * The feature id for the '<em><b>Decompositions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__DECOMPOSITIONS = INTENTION__DECOMPOSITIONS;

	/**
	 * The feature id for the '<em><b>Parent Decompositions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__PARENT_DECOMPOSITIONS = INTENTION__PARENT_DECOMPOSITIONS;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__CONTAINER = INTENTION__CONTAINER;

	/**
	 * The feature id for the '<em><b>Qualitative Reasoning Combined Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__QUALITATIVE_REASONING_COMBINED_LABEL = INTENTION__QUALITATIVE_REASONING_COMBINED_LABEL;

	/**
	 * The feature id for the '<em><b>Qualitative Reasoning Satisfied Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__QUALITATIVE_REASONING_SATISFIED_LABEL = INTENTION__QUALITATIVE_REASONING_SATISFIED_LABEL;

	/**
	 * The feature id for the '<em><b>Qualitative Reasoning Denial Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__QUALITATIVE_REASONING_DENIAL_LABEL = INTENTION__QUALITATIVE_REASONING_DENIAL_LABEL;

	/**
	 * The feature id for the '<em><b>Quantitative Reasoning Combined Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__QUANTITATIVE_REASONING_COMBINED_LABEL = INTENTION__QUANTITATIVE_REASONING_COMBINED_LABEL;

	/**
	 * The feature id for the '<em><b>Quantitative Reasoning Denied Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__QUANTITATIVE_REASONING_DENIED_LABEL = INTENTION__QUANTITATIVE_REASONING_DENIED_LABEL;

	/**
	 * The feature id for the '<em><b>Quantitative Reasoning Satisfied Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__QUANTITATIVE_REASONING_SATISFIED_LABEL = INTENTION__QUANTITATIVE_REASONING_SATISFIED_LABEL;

	/**
	 * The feature id for the '<em><b>Contributes To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__CONTRIBUTES_TO = INTENTION__CONTRIBUTES_TO;

	/**
	 * The feature id for the '<em><b>Contributes From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__CONTRIBUTES_FROM = INTENTION__CONTRIBUTES_FROM;

	/**
	 * The feature id for the '<em><b>Label Bag</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__LABEL_BAG = INTENTION__LABEL_BAG;

	/**
	 * The feature id for the '<em><b>Initial Eval Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__INITIAL_EVAL_LABEL = INTENTION__INITIAL_EVAL_LABEL;

	/**
	 * The feature id for the '<em><b>Reverse Label Bag</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__REVERSE_LABEL_BAG = INTENTION__REVERSE_LABEL_BAG;

	/**
	 * The feature id for the '<em><b>Human Judgments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__HUMAN_JUDGMENTS = INTENTION__HUMAN_JUDGMENTS;

	/**
	 * The number of structural features of the '<em>Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_FEATURE_COUNT = INTENTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.RoleImpl <em>Role</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.RoleImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getRole()
	 * @generated
	 */
	int ROLE = 13;

	/**
	 * The feature id for the '<em><b>Dependency From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__DEPENDENCY_FROM = CONTAINER__DEPENDENCY_FROM;

	/**
	 * The feature id for the '<em><b>Dependency To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__DEPENDENCY_TO = CONTAINER__DEPENDENCY_TO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__NAME = CONTAINER__NAME;

	/**
	 * The feature id for the '<em><b>Sub</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__SUB = CONTAINER__SUB;

	/**
	 * The feature id for the '<em><b>Intentions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__INTENTIONS = CONTAINER__INTENTIONS;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__MODEL = CONTAINER__MODEL;

	/**
	 * The feature id for the '<em><b>Association To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__ASSOCIATION_TO = CONTAINER__ASSOCIATION_TO;

	/**
	 * The feature id for the '<em><b>Association From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__ASSOCIATION_FROM = CONTAINER__ASSOCIATION_FROM;

	/**
	 * The number of structural features of the '<em>Role</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_FEATURE_COUNT = CONTAINER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.SoftgoalImpl <em>Softgoal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.SoftgoalImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getSoftgoal()
	 * @generated
	 */
	int SOFTGOAL = 14;

	/**
	 * The feature id for the '<em><b>Dependency From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTGOAL__DEPENDENCY_FROM = INTENTION__DEPENDENCY_FROM;

	/**
	 * The feature id for the '<em><b>Dependency To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTGOAL__DEPENDENCY_TO = INTENTION__DEPENDENCY_TO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTGOAL__NAME = INTENTION__NAME;

	/**
	 * The feature id for the '<em><b>System</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTGOAL__SYSTEM = INTENTION__SYSTEM;

	/**
	 * The feature id for the '<em><b>Boundary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTGOAL__BOUNDARY = INTENTION__BOUNDARY;

	/**
	 * The feature id for the '<em><b>Exclusive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTGOAL__EXCLUSIVE = INTENTION__EXCLUSIVE;

	/**
	 * The feature id for the '<em><b>Sequential</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTGOAL__SEQUENTIAL = INTENTION__SEQUENTIAL;

	/**
	 * The feature id for the '<em><b>Parallel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTGOAL__PARALLEL = INTENTION__PARALLEL;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTGOAL__PROPERTY = INTENTION__PROPERTY;

	/**
	 * The feature id for the '<em><b>Decompositions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTGOAL__DECOMPOSITIONS = INTENTION__DECOMPOSITIONS;

	/**
	 * The feature id for the '<em><b>Parent Decompositions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTGOAL__PARENT_DECOMPOSITIONS = INTENTION__PARENT_DECOMPOSITIONS;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTGOAL__CONTAINER = INTENTION__CONTAINER;

	/**
	 * The feature id for the '<em><b>Qualitative Reasoning Combined Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTGOAL__QUALITATIVE_REASONING_COMBINED_LABEL = INTENTION__QUALITATIVE_REASONING_COMBINED_LABEL;

	/**
	 * The feature id for the '<em><b>Qualitative Reasoning Satisfied Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTGOAL__QUALITATIVE_REASONING_SATISFIED_LABEL = INTENTION__QUALITATIVE_REASONING_SATISFIED_LABEL;

	/**
	 * The feature id for the '<em><b>Qualitative Reasoning Denial Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTGOAL__QUALITATIVE_REASONING_DENIAL_LABEL = INTENTION__QUALITATIVE_REASONING_DENIAL_LABEL;

	/**
	 * The feature id for the '<em><b>Quantitative Reasoning Combined Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTGOAL__QUANTITATIVE_REASONING_COMBINED_LABEL = INTENTION__QUANTITATIVE_REASONING_COMBINED_LABEL;

	/**
	 * The feature id for the '<em><b>Quantitative Reasoning Denied Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTGOAL__QUANTITATIVE_REASONING_DENIED_LABEL = INTENTION__QUANTITATIVE_REASONING_DENIED_LABEL;

	/**
	 * The feature id for the '<em><b>Quantitative Reasoning Satisfied Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTGOAL__QUANTITATIVE_REASONING_SATISFIED_LABEL = INTENTION__QUANTITATIVE_REASONING_SATISFIED_LABEL;

	/**
	 * The feature id for the '<em><b>Contributes To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTGOAL__CONTRIBUTES_TO = INTENTION__CONTRIBUTES_TO;

	/**
	 * The feature id for the '<em><b>Contributes From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTGOAL__CONTRIBUTES_FROM = INTENTION__CONTRIBUTES_FROM;

	/**
	 * The feature id for the '<em><b>Label Bag</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTGOAL__LABEL_BAG = INTENTION__LABEL_BAG;

	/**
	 * The feature id for the '<em><b>Initial Eval Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTGOAL__INITIAL_EVAL_LABEL = INTENTION__INITIAL_EVAL_LABEL;

	/**
	 * The feature id for the '<em><b>Reverse Label Bag</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTGOAL__REVERSE_LABEL_BAG = INTENTION__REVERSE_LABEL_BAG;

	/**
	 * The feature id for the '<em><b>Human Judgments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTGOAL__HUMAN_JUDGMENTS = INTENTION__HUMAN_JUDGMENTS;

	/**
	 * The feature id for the '<em><b>Topic</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTGOAL__TOPIC = INTENTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Softgoal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTGOAL_FEATURE_COUNT = INTENTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.TaskImpl <em>Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.TaskImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getTask()
	 * @generated
	 */
	int TASK = 15;

	/**
	 * The feature id for the '<em><b>Dependency From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__DEPENDENCY_FROM = INTENTION__DEPENDENCY_FROM;

	/**
	 * The feature id for the '<em><b>Dependency To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__DEPENDENCY_TO = INTENTION__DEPENDENCY_TO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__NAME = INTENTION__NAME;

	/**
	 * The feature id for the '<em><b>System</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__SYSTEM = INTENTION__SYSTEM;

	/**
	 * The feature id for the '<em><b>Boundary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__BOUNDARY = INTENTION__BOUNDARY;

	/**
	 * The feature id for the '<em><b>Exclusive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__EXCLUSIVE = INTENTION__EXCLUSIVE;

	/**
	 * The feature id for the '<em><b>Sequential</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__SEQUENTIAL = INTENTION__SEQUENTIAL;

	/**
	 * The feature id for the '<em><b>Parallel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__PARALLEL = INTENTION__PARALLEL;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__PROPERTY = INTENTION__PROPERTY;

	/**
	 * The feature id for the '<em><b>Decompositions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__DECOMPOSITIONS = INTENTION__DECOMPOSITIONS;

	/**
	 * The feature id for the '<em><b>Parent Decompositions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__PARENT_DECOMPOSITIONS = INTENTION__PARENT_DECOMPOSITIONS;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__CONTAINER = INTENTION__CONTAINER;

	/**
	 * The feature id for the '<em><b>Qualitative Reasoning Combined Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__QUALITATIVE_REASONING_COMBINED_LABEL = INTENTION__QUALITATIVE_REASONING_COMBINED_LABEL;

	/**
	 * The feature id for the '<em><b>Qualitative Reasoning Satisfied Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__QUALITATIVE_REASONING_SATISFIED_LABEL = INTENTION__QUALITATIVE_REASONING_SATISFIED_LABEL;

	/**
	 * The feature id for the '<em><b>Qualitative Reasoning Denial Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__QUALITATIVE_REASONING_DENIAL_LABEL = INTENTION__QUALITATIVE_REASONING_DENIAL_LABEL;

	/**
	 * The feature id for the '<em><b>Quantitative Reasoning Combined Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__QUANTITATIVE_REASONING_COMBINED_LABEL = INTENTION__QUANTITATIVE_REASONING_COMBINED_LABEL;

	/**
	 * The feature id for the '<em><b>Quantitative Reasoning Denied Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__QUANTITATIVE_REASONING_DENIED_LABEL = INTENTION__QUANTITATIVE_REASONING_DENIED_LABEL;

	/**
	 * The feature id for the '<em><b>Quantitative Reasoning Satisfied Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__QUANTITATIVE_REASONING_SATISFIED_LABEL = INTENTION__QUANTITATIVE_REASONING_SATISFIED_LABEL;

	/**
	 * The feature id for the '<em><b>Contributes To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__CONTRIBUTES_TO = INTENTION__CONTRIBUTES_TO;

	/**
	 * The feature id for the '<em><b>Contributes From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__CONTRIBUTES_FROM = INTENTION__CONTRIBUTES_FROM;

	/**
	 * The feature id for the '<em><b>Label Bag</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__LABEL_BAG = INTENTION__LABEL_BAG;

	/**
	 * The feature id for the '<em><b>Initial Eval Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__INITIAL_EVAL_LABEL = INTENTION__INITIAL_EVAL_LABEL;

	/**
	 * The feature id for the '<em><b>Reverse Label Bag</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__REVERSE_LABEL_BAG = INTENTION__REVERSE_LABEL_BAG;

	/**
	 * The feature id for the '<em><b>Human Judgments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__HUMAN_JUDGMENTS = INTENTION__HUMAN_JUDGMENTS;

	/**
	 * The number of structural features of the '<em>Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_FEATURE_COUNT = INTENTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.BeliefImpl <em>Belief</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.BeliefImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getBelief()
	 * @generated
	 */
	int BELIEF = 17;

	/**
	 * The feature id for the '<em><b>Dependency From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BELIEF__DEPENDENCY_FROM = INTENTION__DEPENDENCY_FROM;

	/**
	 * The feature id for the '<em><b>Dependency To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BELIEF__DEPENDENCY_TO = INTENTION__DEPENDENCY_TO;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BELIEF__NAME = INTENTION__NAME;

	/**
	 * The feature id for the '<em><b>System</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BELIEF__SYSTEM = INTENTION__SYSTEM;

	/**
	 * The feature id for the '<em><b>Boundary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BELIEF__BOUNDARY = INTENTION__BOUNDARY;

	/**
	 * The feature id for the '<em><b>Exclusive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BELIEF__EXCLUSIVE = INTENTION__EXCLUSIVE;

	/**
	 * The feature id for the '<em><b>Sequential</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BELIEF__SEQUENTIAL = INTENTION__SEQUENTIAL;

	/**
	 * The feature id for the '<em><b>Parallel</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BELIEF__PARALLEL = INTENTION__PARALLEL;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BELIEF__PROPERTY = INTENTION__PROPERTY;

	/**
	 * The feature id for the '<em><b>Decompositions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BELIEF__DECOMPOSITIONS = INTENTION__DECOMPOSITIONS;

	/**
	 * The feature id for the '<em><b>Parent Decompositions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BELIEF__PARENT_DECOMPOSITIONS = INTENTION__PARENT_DECOMPOSITIONS;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BELIEF__CONTAINER = INTENTION__CONTAINER;

	/**
	 * The feature id for the '<em><b>Qualitative Reasoning Combined Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BELIEF__QUALITATIVE_REASONING_COMBINED_LABEL = INTENTION__QUALITATIVE_REASONING_COMBINED_LABEL;

	/**
	 * The feature id for the '<em><b>Qualitative Reasoning Satisfied Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BELIEF__QUALITATIVE_REASONING_SATISFIED_LABEL = INTENTION__QUALITATIVE_REASONING_SATISFIED_LABEL;

	/**
	 * The feature id for the '<em><b>Qualitative Reasoning Denial Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BELIEF__QUALITATIVE_REASONING_DENIAL_LABEL = INTENTION__QUALITATIVE_REASONING_DENIAL_LABEL;

	/**
	 * The feature id for the '<em><b>Quantitative Reasoning Combined Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BELIEF__QUANTITATIVE_REASONING_COMBINED_LABEL = INTENTION__QUANTITATIVE_REASONING_COMBINED_LABEL;

	/**
	 * The feature id for the '<em><b>Quantitative Reasoning Denied Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BELIEF__QUANTITATIVE_REASONING_DENIED_LABEL = INTENTION__QUANTITATIVE_REASONING_DENIED_LABEL;

	/**
	 * The feature id for the '<em><b>Quantitative Reasoning Satisfied Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BELIEF__QUANTITATIVE_REASONING_SATISFIED_LABEL = INTENTION__QUANTITATIVE_REASONING_SATISFIED_LABEL;

	/**
	 * The feature id for the '<em><b>Contributes To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BELIEF__CONTRIBUTES_TO = INTENTION__CONTRIBUTES_TO;

	/**
	 * The feature id for the '<em><b>Contributes From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BELIEF__CONTRIBUTES_FROM = INTENTION__CONTRIBUTES_FROM;

	/**
	 * The feature id for the '<em><b>Label Bag</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BELIEF__LABEL_BAG = INTENTION__LABEL_BAG;

	/**
	 * The feature id for the '<em><b>Initial Eval Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BELIEF__INITIAL_EVAL_LABEL = INTENTION__INITIAL_EVAL_LABEL;

	/**
	 * The feature id for the '<em><b>Reverse Label Bag</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BELIEF__REVERSE_LABEL_BAG = INTENTION__REVERSE_LABEL_BAG;

	/**
	 * The feature id for the '<em><b>Human Judgments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BELIEF__HUMAN_JUDGMENTS = INTENTION__HUMAN_JUDGMENTS;

	/**
	 * The number of structural features of the '<em>Belief</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BELIEF_FEATURE_COUNT = INTENTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.CorrelationImpl <em>Correlation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.CorrelationImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getCorrelation()
	 * @generated
	 */
	int CORRELATION = 19;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORRELATION__TARGET = LINK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORRELATION__SOURCE = LINK_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Correlation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORRELATION_FEATURE_COUNT = LINK_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.HelpContributionImpl <em>Help Contribution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.HelpContributionImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getHelpContribution()
	 * @generated
	 */
	int HELP_CONTRIBUTION = 21;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HELP_CONTRIBUTION__TARGET = CONTRIBUTION__TARGET;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HELP_CONTRIBUTION__MODEL = CONTRIBUTION__MODEL;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HELP_CONTRIBUTION__SOURCE = CONTRIBUTION__SOURCE;

	/**
	 * The feature id for the '<em><b>Goal model symmetry</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HELP_CONTRIBUTION__GOAL_MODEL_SYMMETRY = CONTRIBUTION__GOAL_MODEL_SYMMETRY;

	/**
	 * The feature id for the '<em><b>Contribution Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HELP_CONTRIBUTION__CONTRIBUTION_TYPE = CONTRIBUTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Help Contribution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HELP_CONTRIBUTION_FEATURE_COUNT = CONTRIBUTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.HurtContributionImpl <em>Hurt Contribution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.HurtContributionImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getHurtContribution()
	 * @generated
	 */
	int HURT_CONTRIBUTION = 22;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HURT_CONTRIBUTION__TARGET = CONTRIBUTION__TARGET;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HURT_CONTRIBUTION__MODEL = CONTRIBUTION__MODEL;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HURT_CONTRIBUTION__SOURCE = CONTRIBUTION__SOURCE;

	/**
	 * The feature id for the '<em><b>Goal model symmetry</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HURT_CONTRIBUTION__GOAL_MODEL_SYMMETRY = CONTRIBUTION__GOAL_MODEL_SYMMETRY;

	/**
	 * The feature id for the '<em><b>Contribution Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HURT_CONTRIBUTION__CONTRIBUTION_TYPE = CONTRIBUTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Hurt Contribution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HURT_CONTRIBUTION_FEATURE_COUNT = CONTRIBUTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.MakeContributionImpl <em>Make Contribution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.MakeContributionImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getMakeContribution()
	 * @generated
	 */
	int MAKE_CONTRIBUTION = 23;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAKE_CONTRIBUTION__TARGET = CONTRIBUTION__TARGET;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAKE_CONTRIBUTION__MODEL = CONTRIBUTION__MODEL;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAKE_CONTRIBUTION__SOURCE = CONTRIBUTION__SOURCE;

	/**
	 * The feature id for the '<em><b>Goal model symmetry</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAKE_CONTRIBUTION__GOAL_MODEL_SYMMETRY = CONTRIBUTION__GOAL_MODEL_SYMMETRY;

	/**
	 * The feature id for the '<em><b>Contribution Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAKE_CONTRIBUTION__CONTRIBUTION_TYPE = CONTRIBUTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Make Contribution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAKE_CONTRIBUTION_FEATURE_COUNT = CONTRIBUTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.BreakContributionImpl <em>Break Contribution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.BreakContributionImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getBreakContribution()
	 * @generated
	 */
	int BREAK_CONTRIBUTION = 24;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAK_CONTRIBUTION__TARGET = CONTRIBUTION__TARGET;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAK_CONTRIBUTION__MODEL = CONTRIBUTION__MODEL;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAK_CONTRIBUTION__SOURCE = CONTRIBUTION__SOURCE;

	/**
	 * The feature id for the '<em><b>Goal model symmetry</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAK_CONTRIBUTION__GOAL_MODEL_SYMMETRY = CONTRIBUTION__GOAL_MODEL_SYMMETRY;

	/**
	 * The feature id for the '<em><b>Contribution Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAK_CONTRIBUTION__CONTRIBUTION_TYPE = CONTRIBUTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Break Contribution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAK_CONTRIBUTION_FEATURE_COUNT = CONTRIBUTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.SomePlusContributionImpl <em>Some Plus Contribution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.SomePlusContributionImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getSomePlusContribution()
	 * @generated
	 */
	int SOME_PLUS_CONTRIBUTION = 25;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOME_PLUS_CONTRIBUTION__TARGET = CONTRIBUTION__TARGET;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOME_PLUS_CONTRIBUTION__MODEL = CONTRIBUTION__MODEL;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOME_PLUS_CONTRIBUTION__SOURCE = CONTRIBUTION__SOURCE;

	/**
	 * The feature id for the '<em><b>Goal model symmetry</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOME_PLUS_CONTRIBUTION__GOAL_MODEL_SYMMETRY = CONTRIBUTION__GOAL_MODEL_SYMMETRY;

	/**
	 * The feature id for the '<em><b>Contribution Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOME_PLUS_CONTRIBUTION__CONTRIBUTION_TYPE = CONTRIBUTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Some Plus Contribution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOME_PLUS_CONTRIBUTION_FEATURE_COUNT = CONTRIBUTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.SomeMinusContributionImpl <em>Some Minus Contribution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.SomeMinusContributionImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getSomeMinusContribution()
	 * @generated
	 */
	int SOME_MINUS_CONTRIBUTION = 26;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOME_MINUS_CONTRIBUTION__TARGET = CONTRIBUTION__TARGET;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOME_MINUS_CONTRIBUTION__MODEL = CONTRIBUTION__MODEL;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOME_MINUS_CONTRIBUTION__SOURCE = CONTRIBUTION__SOURCE;

	/**
	 * The feature id for the '<em><b>Goal model symmetry</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOME_MINUS_CONTRIBUTION__GOAL_MODEL_SYMMETRY = CONTRIBUTION__GOAL_MODEL_SYMMETRY;

	/**
	 * The feature id for the '<em><b>Contribution Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOME_MINUS_CONTRIBUTION__CONTRIBUTION_TYPE = CONTRIBUTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Some Minus Contribution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOME_MINUS_CONTRIBUTION_FEATURE_COUNT = CONTRIBUTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.UnknownContributionImpl <em>Unknown Contribution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.UnknownContributionImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getUnknownContribution()
	 * @generated
	 */
	int UNKNOWN_CONTRIBUTION = 27;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_CONTRIBUTION__TARGET = CONTRIBUTION__TARGET;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_CONTRIBUTION__MODEL = CONTRIBUTION__MODEL;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_CONTRIBUTION__SOURCE = CONTRIBUTION__SOURCE;

	/**
	 * The feature id for the '<em><b>Goal model symmetry</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_CONTRIBUTION__GOAL_MODEL_SYMMETRY = CONTRIBUTION__GOAL_MODEL_SYMMETRY;

	/**
	 * The feature id for the '<em><b>Contribution Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_CONTRIBUTION__CONTRIBUTION_TYPE = CONTRIBUTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Unknown Contribution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_CONTRIBUTION_FEATURE_COUNT = CONTRIBUTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.AndContributionImpl <em>And Contribution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.AndContributionImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getAndContribution()
	 * @generated
	 */
	int AND_CONTRIBUTION = 28;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_CONTRIBUTION__TARGET = CONTRIBUTION__TARGET;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_CONTRIBUTION__MODEL = CONTRIBUTION__MODEL;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_CONTRIBUTION__SOURCE = CONTRIBUTION__SOURCE;

	/**
	 * The feature id for the '<em><b>Goal model symmetry</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_CONTRIBUTION__GOAL_MODEL_SYMMETRY = CONTRIBUTION__GOAL_MODEL_SYMMETRY;

	/**
	 * The feature id for the '<em><b>Contribution Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_CONTRIBUTION__CONTRIBUTION_TYPE = CONTRIBUTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>And Contribution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_CONTRIBUTION_FEATURE_COUNT = CONTRIBUTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.OrContributionImpl <em>Or Contribution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.OrContributionImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getOrContribution()
	 * @generated
	 */
	int OR_CONTRIBUTION = 29;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_CONTRIBUTION__TARGET = CONTRIBUTION__TARGET;

	/**
	 * The feature id for the '<em><b>Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_CONTRIBUTION__MODEL = CONTRIBUTION__MODEL;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_CONTRIBUTION__SOURCE = CONTRIBUTION__SOURCE;

	/**
	 * The feature id for the '<em><b>Goal model symmetry</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_CONTRIBUTION__GOAL_MODEL_SYMMETRY = CONTRIBUTION__GOAL_MODEL_SYMMETRY;

	/**
	 * The feature id for the '<em><b>Contribution Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_CONTRIBUTION__CONTRIBUTION_TYPE = CONTRIBUTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Or Contribution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_CONTRIBUTION_FEATURE_COUNT = CONTRIBUTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.AssociationImpl <em>Association</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.AssociationImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getAssociation()
	 * @generated
	 */
	int ASSOCIATION = 30;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION__SOURCE = LINK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION__TARGET = LINK_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Association</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_FEATURE_COUNT = LINK_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.IsAAssociationImpl <em>Is AAssociation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.IsAAssociationImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getIsAAssociation()
	 * @generated
	 */
	int IS_AASSOCIATION = 31;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IS_AASSOCIATION__SOURCE = ASSOCIATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IS_AASSOCIATION__TARGET = ASSOCIATION__TARGET;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IS_AASSOCIATION__LABEL = ASSOCIATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Is AAssociation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IS_AASSOCIATION_FEATURE_COUNT = ASSOCIATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.CoversAssociationImpl <em>Covers Association</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.CoversAssociationImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getCoversAssociation()
	 * @generated
	 */
	int COVERS_ASSOCIATION = 32;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COVERS_ASSOCIATION__SOURCE = ASSOCIATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COVERS_ASSOCIATION__TARGET = ASSOCIATION__TARGET;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COVERS_ASSOCIATION__LABEL = ASSOCIATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Covers Association</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COVERS_ASSOCIATION_FEATURE_COUNT = ASSOCIATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.IsPartOfAssociationImpl <em>Is Part Of Association</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.IsPartOfAssociationImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getIsPartOfAssociation()
	 * @generated
	 */
	int IS_PART_OF_ASSOCIATION = 33;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IS_PART_OF_ASSOCIATION__SOURCE = ASSOCIATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IS_PART_OF_ASSOCIATION__TARGET = ASSOCIATION__TARGET;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IS_PART_OF_ASSOCIATION__LABEL = ASSOCIATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Is Part Of Association</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IS_PART_OF_ASSOCIATION_FEATURE_COUNT = ASSOCIATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.OccupiesAssociationImpl <em>Occupies Association</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.OccupiesAssociationImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getOccupiesAssociation()
	 * @generated
	 */
	int OCCUPIES_ASSOCIATION = 34;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCUPIES_ASSOCIATION__SOURCE = ASSOCIATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCUPIES_ASSOCIATION__TARGET = ASSOCIATION__TARGET;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCUPIES_ASSOCIATION__LABEL = ASSOCIATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Occupies Association</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCCUPIES_ASSOCIATION_FEATURE_COUNT = ASSOCIATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.PlaysAssociationImpl <em>Plays Association</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.PlaysAssociationImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getPlaysAssociation()
	 * @generated
	 */
	int PLAYS_ASSOCIATION = 35;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAYS_ASSOCIATION__SOURCE = ASSOCIATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAYS_ASSOCIATION__TARGET = ASSOCIATION__TARGET;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAYS_ASSOCIATION__LABEL = ASSOCIATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Plays Association</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLAYS_ASSOCIATION_FEATURE_COUNT = ASSOCIATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.INSAssociationImpl <em>INS Association</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.INSAssociationImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getINSAssociation()
	 * @generated
	 */
	int INS_ASSOCIATION = 36;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INS_ASSOCIATION__SOURCE = ASSOCIATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INS_ASSOCIATION__TARGET = ASSOCIATION__TARGET;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INS_ASSOCIATION__LABEL = ASSOCIATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>INS Association</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INS_ASSOCIATION_FEATURE_COUNT = ASSOCIATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.AlternativeImpl <em>Alternative</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.AlternativeImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getAlternative()
	 * @generated
	 */
	int ALTERNATIVE = 37;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATIVE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATIVE__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Intentions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATIVE__INTENTIONS = 2;

	/**
	 * The feature id for the '<em><b>Eval Labels</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATIVE__EVAL_LABELS = 3;

	/**
	 * The feature id for the '<em><b>Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATIVE__DIRECTION = 4;

	/**
	 * The number of structural features of the '<em>Alternative</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATIVE_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.IntentionToEvaluationLabelMapImpl <em>Intention To Evaluation Label Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.IntentionToEvaluationLabelMapImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getIntentionToEvaluationLabelMap()
	 * @generated
	 */
	int INTENTION_TO_EVALUATION_LABEL_MAP = 38;

	/**
	 * The feature id for the '<em><b>Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTION_TO_EVALUATION_LABEL_MAP__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTION_TO_EVALUATION_LABEL_MAP__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Intention To Evaluation Label Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTION_TO_EVALUATION_LABEL_MAP_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.HumanJudgmentImpl <em>Human Judgment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.HumanJudgmentImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getHumanJudgment()
	 * @generated
	 */
	int HUMAN_JUDGMENT = 39;

	/**
	 * The feature id for the '<em><b>Result Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HUMAN_JUDGMENT__RESULT_LABEL = 0;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HUMAN_JUDGMENT__ENABLED = 1;

	/**
	 * The feature id for the '<em><b>Label Bag</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HUMAN_JUDGMENT__LABEL_BAG = 2;

	/**
	 * The number of structural features of the '<em>Human Judgment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HUMAN_JUDGMENT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.impl.LabelBagImpl <em>Label Bag</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.impl.LabelBagImpl
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getLabelBag()
	 * @generated
	 */
	int LABEL_BAG = 40;

	/**
	 * The feature id for the '<em><b>Label Bag Intentions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_BAG__LABEL_BAG_INTENTIONS = 0;

	/**
	 * The feature id for the '<em><b>Label Bag Eval Labels</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_BAG__LABEL_BAG_EVAL_LABELS = 1;

	/**
	 * The feature id for the '<em><b>To Resolve</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_BAG__TO_RESOLVE = 2;

	/**
	 * The feature id for the '<em><b>All Positive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_BAG__ALL_POSITIVE = 3;

	/**
	 * The feature id for the '<em><b>All Negative</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_BAG__ALL_NEGATIVE = 4;

	/**
	 * The feature id for the '<em><b>Has Full Positive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_BAG__HAS_FULL_POSITIVE = 5;

	/**
	 * The feature id for the '<em><b>Has Full Negative</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_BAG__HAS_FULL_NEGATIVE = 6;

	/**
	 * The feature id for the '<em><b>Has Unknown</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_BAG__HAS_UNKNOWN = 7;

	/**
	 * The feature id for the '<em><b>Has Conflict</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_BAG__HAS_CONFLICT = 8;

	/**
	 * The feature id for the '<em><b>All Unknown</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_BAG__ALL_UNKNOWN = 9;

	/**
	 * The feature id for the '<em><b>All Conflict</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_BAG__ALL_CONFLICT = 10;

	/**
	 * The number of structural features of the '<em>Label Bag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_BAG_FEATURE_COUNT = 11;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.EvaluationLabel <em>Evaluation Label</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.EvaluationLabel
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getEvaluationLabel()
	 * @generated
	 */
	int EVALUATION_LABEL = 41;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.GoalModelingContributionType <em>Goal Modeling Contribution Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.GoalModelingContributionType
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getGoalModelingContributionType()
	 * @generated
	 */
	int GOAL_MODELING_CONTRIBUTION_TYPE = 42;

	/**
	 * The meta object id for the '{@link edu.toronto.cs.openome_model.GoalModelingContributionSymmetry <em>Goal Modeling Contribution Symmetry</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.toronto.cs.openome_model.GoalModelingContributionSymmetry
	 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getGoalModelingContributionSymmetry()
	 * @generated
	 */
	int GOAL_MODELING_CONTRIBUTION_SYMMETRY = 43;


	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.Actor <em>Actor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Actor</em>'.
	 * @see edu.toronto.cs.openome_model.Actor
	 * @generated
	 */
	EClass getActor();

	/**
	 * Returns the meta object for the reference list '{@link edu.toronto.cs.openome_model.Actor#getIs_a <em>Is a</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Is a</em>'.
	 * @see edu.toronto.cs.openome_model.Actor#getIs_a()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_Is_a();

	/**
	 * Returns the meta object for the reference list '{@link edu.toronto.cs.openome_model.Actor#getIs_part_of <em>Is part of</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Is part of</em>'.
	 * @see edu.toronto.cs.openome_model.Actor#getIs_part_of()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_Is_part_of();

	/**
	 * Returns the meta object for the reference list '{@link edu.toronto.cs.openome_model.Actor#getBelieves <em>Believes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Believes</em>'.
	 * @see edu.toronto.cs.openome_model.Actor#getBelieves()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_Believes();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.Agent <em>Agent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Agent</em>'.
	 * @see edu.toronto.cs.openome_model.Agent
	 * @generated
	 */
	EClass getAgent();

	/**
	 * Returns the meta object for the reference list '{@link edu.toronto.cs.openome_model.Agent#getPlays <em>Plays</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Plays</em>'.
	 * @see edu.toronto.cs.openome_model.Agent#getPlays()
	 * @see #getAgent()
	 * @generated
	 */
	EReference getAgent_Plays();

	/**
	 * Returns the meta object for the reference list '{@link edu.toronto.cs.openome_model.Agent#getOccupies <em>Occupies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Occupies</em>'.
	 * @see edu.toronto.cs.openome_model.Agent#getOccupies()
	 * @see #getAgent()
	 * @generated
	 */
	EReference getAgent_Occupies();

	/**
	 * Returns the meta object for the reference '{@link edu.toronto.cs.openome_model.Agent#getIns <em>Ins</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Ins</em>'.
	 * @see edu.toronto.cs.openome_model.Agent#getIns()
	 * @see #getAgent()
	 * @generated
	 */
	EReference getAgent_Ins();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.AndDecomposition <em>And Decomposition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>And Decomposition</em>'.
	 * @see edu.toronto.cs.openome_model.AndDecomposition
	 * @generated
	 */
	EClass getAndDecomposition();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.Contribution <em>Contribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Contribution</em>'.
	 * @see edu.toronto.cs.openome_model.Contribution
	 * @generated
	 */
	EClass getContribution();

	/**
	 * Returns the meta object for the reference '{@link edu.toronto.cs.openome_model.Contribution#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see edu.toronto.cs.openome_model.Contribution#getTarget()
	 * @see #getContribution()
	 * @generated
	 */
	EReference getContribution_Target();

	/**
	 * Returns the meta object for the container reference '{@link edu.toronto.cs.openome_model.Contribution#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Model</em>'.
	 * @see edu.toronto.cs.openome_model.Contribution#getModel()
	 * @see #getContribution()
	 * @generated
	 */
	EReference getContribution_Model();

	/**
	 * Returns the meta object for the reference '{@link edu.toronto.cs.openome_model.Contribution#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see edu.toronto.cs.openome_model.Contribution#getSource()
	 * @see #getContribution()
	 * @generated
	 */
	EReference getContribution_Source();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.Contribution#getGoal_model_symmetry <em>Goal model symmetry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Goal model symmetry</em>'.
	 * @see edu.toronto.cs.openome_model.Contribution#getGoal_model_symmetry()
	 * @see #getContribution()
	 * @generated
	 */
	EAttribute getContribution_Goal_model_symmetry();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.Decomposition <em>Decomposition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Decomposition</em>'.
	 * @see edu.toronto.cs.openome_model.Decomposition
	 * @generated
	 */
	EClass getDecomposition();

	/**
	 * Returns the meta object for the reference '{@link edu.toronto.cs.openome_model.Decomposition#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see edu.toronto.cs.openome_model.Decomposition#getSource()
	 * @see #getDecomposition()
	 * @generated
	 */
	EReference getDecomposition_Source();

	/**
	 * Returns the meta object for the reference '{@link edu.toronto.cs.openome_model.Decomposition#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see edu.toronto.cs.openome_model.Decomposition#getTarget()
	 * @see #getDecomposition()
	 * @generated
	 */
	EReference getDecomposition_Target();

	/**
	 * Returns the meta object for the container reference '{@link edu.toronto.cs.openome_model.Decomposition#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Model</em>'.
	 * @see edu.toronto.cs.openome_model.Decomposition#getModel()
	 * @see #getDecomposition()
	 * @generated
	 */
	EReference getDecomposition_Model();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.Dependency <em>Dependency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dependency</em>'.
	 * @see edu.toronto.cs.openome_model.Dependency
	 * @generated
	 */
	EClass getDependency();

	/**
	 * Returns the meta object for the reference '{@link edu.toronto.cs.openome_model.Dependency#getDependencyFrom <em>Dependency From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Dependency From</em>'.
	 * @see edu.toronto.cs.openome_model.Dependency#getDependencyFrom()
	 * @see #getDependency()
	 * @generated
	 */
	EReference getDependency_DependencyFrom();

	/**
	 * Returns the meta object for the reference '{@link edu.toronto.cs.openome_model.Dependency#getDependencyTo <em>Dependency To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Dependency To</em>'.
	 * @see edu.toronto.cs.openome_model.Dependency#getDependencyTo()
	 * @see #getDependency()
	 * @generated
	 */
	EReference getDependency_DependencyTo();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.Dependency#getTrust <em>Trust</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trust</em>'.
	 * @see edu.toronto.cs.openome_model.Dependency#getTrust()
	 * @see #getDependency()
	 * @generated
	 */
	EAttribute getDependency_Trust();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.Dependency#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see edu.toronto.cs.openome_model.Dependency#getLabel()
	 * @see #getDependency()
	 * @generated
	 */
	EAttribute getDependency_Label();

	/**
	 * Returns the meta object for the container reference '{@link edu.toronto.cs.openome_model.Dependency#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Model</em>'.
	 * @see edu.toronto.cs.openome_model.Dependency#getModel()
	 * @see #getDependency()
	 * @generated
	 */
	EReference getDependency_Model();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.Goal <em>Goal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Goal</em>'.
	 * @see edu.toronto.cs.openome_model.Goal
	 * @generated
	 */
	EClass getGoal();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.Intention <em>Intention</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Intention</em>'.
	 * @see edu.toronto.cs.openome_model.Intention
	 * @generated
	 */
	EClass getIntention();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.Intention#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see edu.toronto.cs.openome_model.Intention#getName()
	 * @see #getIntention()
	 * @generated
	 */
	EAttribute getIntention_Name();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.Intention#getSystem <em>System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>System</em>'.
	 * @see edu.toronto.cs.openome_model.Intention#getSystem()
	 * @see #getIntention()
	 * @generated
	 */
	EAttribute getIntention_System();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.Intention#getBoundary <em>Boundary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Boundary</em>'.
	 * @see edu.toronto.cs.openome_model.Intention#getBoundary()
	 * @see #getIntention()
	 * @generated
	 */
	EAttribute getIntention_Boundary();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.Intention#getExclusive <em>Exclusive</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Exclusive</em>'.
	 * @see edu.toronto.cs.openome_model.Intention#getExclusive()
	 * @see #getIntention()
	 * @generated
	 */
	EAttribute getIntention_Exclusive();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.Intention#getSequential <em>Sequential</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sequential</em>'.
	 * @see edu.toronto.cs.openome_model.Intention#getSequential()
	 * @see #getIntention()
	 * @generated
	 */
	EAttribute getIntention_Sequential();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.Intention#getParallel <em>Parallel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parallel</em>'.
	 * @see edu.toronto.cs.openome_model.Intention#getParallel()
	 * @see #getIntention()
	 * @generated
	 */
	EAttribute getIntention_Parallel();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.toronto.cs.openome_model.Intention#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Property</em>'.
	 * @see edu.toronto.cs.openome_model.Intention#getProperty()
	 * @see #getIntention()
	 * @generated
	 */
	EReference getIntention_Property();

	/**
	 * Returns the meta object for the reference list '{@link edu.toronto.cs.openome_model.Intention#getDecompositions <em>Decompositions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Decompositions</em>'.
	 * @see edu.toronto.cs.openome_model.Intention#getDecompositions()
	 * @see #getIntention()
	 * @generated
	 */
	EReference getIntention_Decompositions();

	/**
	 * Returns the meta object for the reference list '{@link edu.toronto.cs.openome_model.Intention#getParentDecompositions <em>Parent Decompositions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parent Decompositions</em>'.
	 * @see edu.toronto.cs.openome_model.Intention#getParentDecompositions()
	 * @see #getIntention()
	 * @generated
	 */
	EReference getIntention_ParentDecompositions();

	/**
	 * Returns the meta object for the container reference '{@link edu.toronto.cs.openome_model.Intention#getContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Container</em>'.
	 * @see edu.toronto.cs.openome_model.Intention#getContainer()
	 * @see #getIntention()
	 * @generated
	 */
	EReference getIntention_Container();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.Intention#getQualitativeReasoningCombinedLabel <em>Qualitative Reasoning Combined Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Qualitative Reasoning Combined Label</em>'.
	 * @see edu.toronto.cs.openome_model.Intention#getQualitativeReasoningCombinedLabel()
	 * @see #getIntention()
	 * @generated
	 */
	EAttribute getIntention_QualitativeReasoningCombinedLabel();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.Intention#getQualitativeReasoningSatisfiedLabel <em>Qualitative Reasoning Satisfied Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Qualitative Reasoning Satisfied Label</em>'.
	 * @see edu.toronto.cs.openome_model.Intention#getQualitativeReasoningSatisfiedLabel()
	 * @see #getIntention()
	 * @generated
	 */
	EAttribute getIntention_QualitativeReasoningSatisfiedLabel();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.Intention#getQualitativeReasoningDenialLabel <em>Qualitative Reasoning Denial Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Qualitative Reasoning Denial Label</em>'.
	 * @see edu.toronto.cs.openome_model.Intention#getQualitativeReasoningDenialLabel()
	 * @see #getIntention()
	 * @generated
	 */
	EAttribute getIntention_QualitativeReasoningDenialLabel();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.Intention#getQuantitativeReasoningCombinedLabel <em>Quantitative Reasoning Combined Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Quantitative Reasoning Combined Label</em>'.
	 * @see edu.toronto.cs.openome_model.Intention#getQuantitativeReasoningCombinedLabel()
	 * @see #getIntention()
	 * @generated
	 */
	EAttribute getIntention_QuantitativeReasoningCombinedLabel();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.Intention#getQuantitativeReasoningDeniedLabel <em>Quantitative Reasoning Denied Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Quantitative Reasoning Denied Label</em>'.
	 * @see edu.toronto.cs.openome_model.Intention#getQuantitativeReasoningDeniedLabel()
	 * @see #getIntention()
	 * @generated
	 */
	EAttribute getIntention_QuantitativeReasoningDeniedLabel();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.Intention#getQuantitativeReasoningSatisfiedLabel <em>Quantitative Reasoning Satisfied Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Quantitative Reasoning Satisfied Label</em>'.
	 * @see edu.toronto.cs.openome_model.Intention#getQuantitativeReasoningSatisfiedLabel()
	 * @see #getIntention()
	 * @generated
	 */
	EAttribute getIntention_QuantitativeReasoningSatisfiedLabel();

	/**
	 * Returns the meta object for the reference list '{@link edu.toronto.cs.openome_model.Intention#getContributesTo <em>Contributes To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Contributes To</em>'.
	 * @see edu.toronto.cs.openome_model.Intention#getContributesTo()
	 * @see #getIntention()
	 * @generated
	 */
	EReference getIntention_ContributesTo();

	/**
	 * Returns the meta object for the reference list '{@link edu.toronto.cs.openome_model.Intention#getContributesFrom <em>Contributes From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Contributes From</em>'.
	 * @see edu.toronto.cs.openome_model.Intention#getContributesFrom()
	 * @see #getIntention()
	 * @generated
	 */
	EReference getIntention_ContributesFrom();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.toronto.cs.openome_model.Intention#getHumanJudgments <em>Human Judgments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Human Judgments</em>'.
	 * @see edu.toronto.cs.openome_model.Intention#getHumanJudgments()
	 * @see #getIntention()
	 * @generated
	 */
	EReference getIntention_HumanJudgments();

	/**
	 * Returns the meta object for the containment reference '{@link edu.toronto.cs.openome_model.Intention#getLabelBag <em>Label Bag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Label Bag</em>'.
	 * @see edu.toronto.cs.openome_model.Intention#getLabelBag()
	 * @see #getIntention()
	 * @generated
	 */
	EReference getIntention_LabelBag();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.Intention#getInitialEvalLabel <em>Initial Eval Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Initial Eval Label</em>'.
	 * @see edu.toronto.cs.openome_model.Intention#getInitialEvalLabel()
	 * @see #getIntention()
	 * @generated
	 */
	EAttribute getIntention_InitialEvalLabel();

	/**
	 * Returns the meta object for the containment reference '{@link edu.toronto.cs.openome_model.Intention#getReverseLabelBag <em>Reverse Label Bag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Reverse Label Bag</em>'.
	 * @see edu.toronto.cs.openome_model.Intention#getReverseLabelBag()
	 * @see #getIntention()
	 * @generated
	 */
	EReference getIntention_ReverseLabelBag();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.Model <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see edu.toronto.cs.openome_model.Model
	 * @generated
	 */
	EClass getModel();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.Model#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see edu.toronto.cs.openome_model.Model#getName()
	 * @see #getModel()
	 * @generated
	 */
	EAttribute getModel_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.toronto.cs.openome_model.Model#getIntentions <em>Intentions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Intentions</em>'.
	 * @see edu.toronto.cs.openome_model.Model#getIntentions()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Intentions();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.toronto.cs.openome_model.Model#getContributions <em>Contributions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Contributions</em>'.
	 * @see edu.toronto.cs.openome_model.Model#getContributions()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Contributions();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.toronto.cs.openome_model.Model#getDependencies <em>Dependencies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Dependencies</em>'.
	 * @see edu.toronto.cs.openome_model.Model#getDependencies()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Dependencies();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.toronto.cs.openome_model.Model#getDecompositions <em>Decompositions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Decompositions</em>'.
	 * @see edu.toronto.cs.openome_model.Model#getDecompositions()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Decompositions();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.toronto.cs.openome_model.Model#getContainers <em>Containers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Containers</em>'.
	 * @see edu.toronto.cs.openome_model.Model#getContainers()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Containers();

	/**
	 * Returns the meta object for the reference list '{@link edu.toronto.cs.openome_model.Model#getCorrelations <em>Correlations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Correlations</em>'.
	 * @see edu.toronto.cs.openome_model.Model#getCorrelations()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Correlations();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.toronto.cs.openome_model.Model#getAssociations <em>Associations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Associations</em>'.
	 * @see edu.toronto.cs.openome_model.Model#getAssociations()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Associations();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.toronto.cs.openome_model.Model#getAlternatives <em>Alternatives</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Alternatives</em>'.
	 * @see edu.toronto.cs.openome_model.Model#getAlternatives()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Alternatives();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.OrDecomposition <em>Or Decomposition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Or Decomposition</em>'.
	 * @see edu.toronto.cs.openome_model.OrDecomposition
	 * @generated
	 */
	EClass getOrDecomposition();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.Position <em>Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Position</em>'.
	 * @see edu.toronto.cs.openome_model.Position
	 * @generated
	 */
	EClass getPosition();

	/**
	 * Returns the meta object for the reference list '{@link edu.toronto.cs.openome_model.Position#getCovers <em>Covers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Covers</em>'.
	 * @see edu.toronto.cs.openome_model.Position#getCovers()
	 * @see #getPosition()
	 * @generated
	 */
	EReference getPosition_Covers();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.Property <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property</em>'.
	 * @see edu.toronto.cs.openome_model.Property
	 * @generated
	 */
	EClass getProperty();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.Property#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see edu.toronto.cs.openome_model.Property#getName()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Name();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.Property#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see edu.toronto.cs.openome_model.Property#getValue()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Value();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.Resource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource</em>'.
	 * @see edu.toronto.cs.openome_model.Resource
	 * @generated
	 */
	EClass getResource();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.Role <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role</em>'.
	 * @see edu.toronto.cs.openome_model.Role
	 * @generated
	 */
	EClass getRole();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.Softgoal <em>Softgoal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Softgoal</em>'.
	 * @see edu.toronto.cs.openome_model.Softgoal
	 * @generated
	 */
	EClass getSoftgoal();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.Softgoal#getTopic <em>Topic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Topic</em>'.
	 * @see edu.toronto.cs.openome_model.Softgoal#getTopic()
	 * @see #getSoftgoal()
	 * @generated
	 */
	EAttribute getSoftgoal_Topic();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.Task <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task</em>'.
	 * @see edu.toronto.cs.openome_model.Task
	 * @generated
	 */
	EClass getTask();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.Container <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container</em>'.
	 * @see edu.toronto.cs.openome_model.Container
	 * @generated
	 */
	EClass getContainer();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.Container#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see edu.toronto.cs.openome_model.Container#getName()
	 * @see #getContainer()
	 * @generated
	 */
	EAttribute getContainer_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.toronto.cs.openome_model.Container#getSub <em>Sub</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sub</em>'.
	 * @see edu.toronto.cs.openome_model.Container#getSub()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_Sub();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.toronto.cs.openome_model.Container#getIntentions <em>Intentions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Intentions</em>'.
	 * @see edu.toronto.cs.openome_model.Container#getIntentions()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_Intentions();

	/**
	 * Returns the meta object for the container reference '{@link edu.toronto.cs.openome_model.Container#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Model</em>'.
	 * @see edu.toronto.cs.openome_model.Container#getModel()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_Model();

	/**
	 * Returns the meta object for the reference list '{@link edu.toronto.cs.openome_model.Container#getAssociationTo <em>Association To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Association To</em>'.
	 * @see edu.toronto.cs.openome_model.Container#getAssociationTo()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_AssociationTo();

	/**
	 * Returns the meta object for the reference list '{@link edu.toronto.cs.openome_model.Container#getAssociationFrom <em>Association From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Association From</em>'.
	 * @see edu.toronto.cs.openome_model.Container#getAssociationFrom()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_AssociationFrom();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.Belief <em>Belief</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Belief</em>'.
	 * @see edu.toronto.cs.openome_model.Belief
	 * @generated
	 */
	EClass getBelief();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.Link <em>Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Link</em>'.
	 * @see edu.toronto.cs.openome_model.Link
	 * @generated
	 */
	EClass getLink();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.Correlation <em>Correlation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Correlation</em>'.
	 * @see edu.toronto.cs.openome_model.Correlation
	 * @generated
	 */
	EClass getCorrelation();

	/**
	 * Returns the meta object for the reference '{@link edu.toronto.cs.openome_model.Correlation#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see edu.toronto.cs.openome_model.Correlation#getTarget()
	 * @see #getCorrelation()
	 * @generated
	 */
	EReference getCorrelation_Target();

	/**
	 * Returns the meta object for the reference '{@link edu.toronto.cs.openome_model.Correlation#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see edu.toronto.cs.openome_model.Correlation#getSource()
	 * @see #getCorrelation()
	 * @generated
	 */
	EReference getCorrelation_Source();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.Dependable <em>Dependable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dependable</em>'.
	 * @see edu.toronto.cs.openome_model.Dependable
	 * @generated
	 */
	EClass getDependable();

	/**
	 * Returns the meta object for the reference list '{@link edu.toronto.cs.openome_model.Dependable#getDependencyFrom <em>Dependency From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Dependency From</em>'.
	 * @see edu.toronto.cs.openome_model.Dependable#getDependencyFrom()
	 * @see #getDependable()
	 * @generated
	 */
	EReference getDependable_DependencyFrom();

	/**
	 * Returns the meta object for the reference list '{@link edu.toronto.cs.openome_model.Dependable#getDependencyTo <em>Dependency To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Dependency To</em>'.
	 * @see edu.toronto.cs.openome_model.Dependable#getDependencyTo()
	 * @see #getDependable()
	 * @generated
	 */
	EReference getDependable_DependencyTo();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.HelpContribution <em>Help Contribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Help Contribution</em>'.
	 * @see edu.toronto.cs.openome_model.HelpContribution
	 * @generated
	 */
	EClass getHelpContribution();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.HelpContribution#getContributionType <em>Contribution Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Contribution Type</em>'.
	 * @see edu.toronto.cs.openome_model.HelpContribution#getContributionType()
	 * @see #getHelpContribution()
	 * @generated
	 */
	EAttribute getHelpContribution_ContributionType();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.HurtContribution <em>Hurt Contribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Hurt Contribution</em>'.
	 * @see edu.toronto.cs.openome_model.HurtContribution
	 * @generated
	 */
	EClass getHurtContribution();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.HurtContribution#getContributionType <em>Contribution Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Contribution Type</em>'.
	 * @see edu.toronto.cs.openome_model.HurtContribution#getContributionType()
	 * @see #getHurtContribution()
	 * @generated
	 */
	EAttribute getHurtContribution_ContributionType();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.MakeContribution <em>Make Contribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Make Contribution</em>'.
	 * @see edu.toronto.cs.openome_model.MakeContribution
	 * @generated
	 */
	EClass getMakeContribution();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.MakeContribution#getContributionType <em>Contribution Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Contribution Type</em>'.
	 * @see edu.toronto.cs.openome_model.MakeContribution#getContributionType()
	 * @see #getMakeContribution()
	 * @generated
	 */
	EAttribute getMakeContribution_ContributionType();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.BreakContribution <em>Break Contribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Break Contribution</em>'.
	 * @see edu.toronto.cs.openome_model.BreakContribution
	 * @generated
	 */
	EClass getBreakContribution();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.BreakContribution#getContributionType <em>Contribution Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Contribution Type</em>'.
	 * @see edu.toronto.cs.openome_model.BreakContribution#getContributionType()
	 * @see #getBreakContribution()
	 * @generated
	 */
	EAttribute getBreakContribution_ContributionType();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.SomePlusContribution <em>Some Plus Contribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Some Plus Contribution</em>'.
	 * @see edu.toronto.cs.openome_model.SomePlusContribution
	 * @generated
	 */
	EClass getSomePlusContribution();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.SomePlusContribution#getContributionType <em>Contribution Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Contribution Type</em>'.
	 * @see edu.toronto.cs.openome_model.SomePlusContribution#getContributionType()
	 * @see #getSomePlusContribution()
	 * @generated
	 */
	EAttribute getSomePlusContribution_ContributionType();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.SomeMinusContribution <em>Some Minus Contribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Some Minus Contribution</em>'.
	 * @see edu.toronto.cs.openome_model.SomeMinusContribution
	 * @generated
	 */
	EClass getSomeMinusContribution();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.SomeMinusContribution#getContributionType <em>Contribution Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Contribution Type</em>'.
	 * @see edu.toronto.cs.openome_model.SomeMinusContribution#getContributionType()
	 * @see #getSomeMinusContribution()
	 * @generated
	 */
	EAttribute getSomeMinusContribution_ContributionType();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.UnknownContribution <em>Unknown Contribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unknown Contribution</em>'.
	 * @see edu.toronto.cs.openome_model.UnknownContribution
	 * @generated
	 */
	EClass getUnknownContribution();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.UnknownContribution#getContributionType <em>Contribution Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Contribution Type</em>'.
	 * @see edu.toronto.cs.openome_model.UnknownContribution#getContributionType()
	 * @see #getUnknownContribution()
	 * @generated
	 */
	EAttribute getUnknownContribution_ContributionType();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.AndContribution <em>And Contribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>And Contribution</em>'.
	 * @see edu.toronto.cs.openome_model.AndContribution
	 * @generated
	 */
	EClass getAndContribution();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.AndContribution#getContributionType <em>Contribution Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Contribution Type</em>'.
	 * @see edu.toronto.cs.openome_model.AndContribution#getContributionType()
	 * @see #getAndContribution()
	 * @generated
	 */
	EAttribute getAndContribution_ContributionType();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.OrContribution <em>Or Contribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Or Contribution</em>'.
	 * @see edu.toronto.cs.openome_model.OrContribution
	 * @generated
	 */
	EClass getOrContribution();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.OrContribution#getContributionType <em>Contribution Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Contribution Type</em>'.
	 * @see edu.toronto.cs.openome_model.OrContribution#getContributionType()
	 * @see #getOrContribution()
	 * @generated
	 */
	EAttribute getOrContribution_ContributionType();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.Association <em>Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Association</em>'.
	 * @see edu.toronto.cs.openome_model.Association
	 * @generated
	 */
	EClass getAssociation();

	/**
	 * Returns the meta object for the reference '{@link edu.toronto.cs.openome_model.Association#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see edu.toronto.cs.openome_model.Association#getSource()
	 * @see #getAssociation()
	 * @generated
	 */
	EReference getAssociation_Source();

	/**
	 * Returns the meta object for the reference '{@link edu.toronto.cs.openome_model.Association#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see edu.toronto.cs.openome_model.Association#getTarget()
	 * @see #getAssociation()
	 * @generated
	 */
	EReference getAssociation_Target();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.IsAAssociation <em>Is AAssociation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Is AAssociation</em>'.
	 * @see edu.toronto.cs.openome_model.IsAAssociation
	 * @generated
	 */
	EClass getIsAAssociation();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.IsAAssociation#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see edu.toronto.cs.openome_model.IsAAssociation#getLabel()
	 * @see #getIsAAssociation()
	 * @generated
	 */
	EAttribute getIsAAssociation_Label();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.CoversAssociation <em>Covers Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Covers Association</em>'.
	 * @see edu.toronto.cs.openome_model.CoversAssociation
	 * @generated
	 */
	EClass getCoversAssociation();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.CoversAssociation#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see edu.toronto.cs.openome_model.CoversAssociation#getLabel()
	 * @see #getCoversAssociation()
	 * @generated
	 */
	EAttribute getCoversAssociation_Label();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.IsPartOfAssociation <em>Is Part Of Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Is Part Of Association</em>'.
	 * @see edu.toronto.cs.openome_model.IsPartOfAssociation
	 * @generated
	 */
	EClass getIsPartOfAssociation();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.IsPartOfAssociation#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see edu.toronto.cs.openome_model.IsPartOfAssociation#getLabel()
	 * @see #getIsPartOfAssociation()
	 * @generated
	 */
	EAttribute getIsPartOfAssociation_Label();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.OccupiesAssociation <em>Occupies Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Occupies Association</em>'.
	 * @see edu.toronto.cs.openome_model.OccupiesAssociation
	 * @generated
	 */
	EClass getOccupiesAssociation();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.OccupiesAssociation#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see edu.toronto.cs.openome_model.OccupiesAssociation#getLabel()
	 * @see #getOccupiesAssociation()
	 * @generated
	 */
	EAttribute getOccupiesAssociation_Label();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.PlaysAssociation <em>Plays Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Plays Association</em>'.
	 * @see edu.toronto.cs.openome_model.PlaysAssociation
	 * @generated
	 */
	EClass getPlaysAssociation();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.PlaysAssociation#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see edu.toronto.cs.openome_model.PlaysAssociation#getLabel()
	 * @see #getPlaysAssociation()
	 * @generated
	 */
	EAttribute getPlaysAssociation_Label();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.INSAssociation <em>INS Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>INS Association</em>'.
	 * @see edu.toronto.cs.openome_model.INSAssociation
	 * @generated
	 */
	EClass getINSAssociation();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.INSAssociation#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see edu.toronto.cs.openome_model.INSAssociation#getLabel()
	 * @see #getINSAssociation()
	 * @generated
	 */
	EAttribute getINSAssociation_Label();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.Alternative <em>Alternative</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Alternative</em>'.
	 * @see edu.toronto.cs.openome_model.Alternative
	 * @generated
	 */
	EClass getAlternative();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.Alternative#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see edu.toronto.cs.openome_model.Alternative#getName()
	 * @see #getAlternative()
	 * @generated
	 */
	EAttribute getAlternative_Name();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.Alternative#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see edu.toronto.cs.openome_model.Alternative#getDescription()
	 * @see #getAlternative()
	 * @generated
	 */
	EAttribute getAlternative_Description();

	/**
	 * Returns the meta object for the reference list '{@link edu.toronto.cs.openome_model.Alternative#getIntentions <em>Intentions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Intentions</em>'.
	 * @see edu.toronto.cs.openome_model.Alternative#getIntentions()
	 * @see #getAlternative()
	 * @generated
	 */
	EReference getAlternative_Intentions();

	/**
	 * Returns the meta object for the attribute list '{@link edu.toronto.cs.openome_model.Alternative#getEvalLabels <em>Eval Labels</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Eval Labels</em>'.
	 * @see edu.toronto.cs.openome_model.Alternative#getEvalLabels()
	 * @see #getAlternative()
	 * @generated
	 */
	EAttribute getAlternative_EvalLabels();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.Alternative#getDirection <em>Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Direction</em>'.
	 * @see edu.toronto.cs.openome_model.Alternative#getDirection()
	 * @see #getAlternative()
	 * @generated
	 */
	EAttribute getAlternative_Direction();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Intention To Evaluation Label Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Intention To Evaluation Label Map</em>'.
	 * @see java.util.Map.Entry
	 * @model keyType="edu.toronto.cs.openome_model.Intention" keyContainment="true"
	 *        valueDataType="edu.toronto.cs.openome_model.EvaluationLabel"
	 * @generated
	 */
	EClass getIntentionToEvaluationLabelMap();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getIntentionToEvaluationLabelMap()
	 * @generated
	 */
	EReference getIntentionToEvaluationLabelMap_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getIntentionToEvaluationLabelMap()
	 * @generated
	 */
	EAttribute getIntentionToEvaluationLabelMap_Value();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.HumanJudgment <em>Human Judgment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Human Judgment</em>'.
	 * @see edu.toronto.cs.openome_model.HumanJudgment
	 * @generated
	 */
	EClass getHumanJudgment();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.HumanJudgment#getResultLabel <em>Result Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Result Label</em>'.
	 * @see edu.toronto.cs.openome_model.HumanJudgment#getResultLabel()
	 * @see #getHumanJudgment()
	 * @generated
	 */
	EAttribute getHumanJudgment_ResultLabel();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.HumanJudgment#isEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see edu.toronto.cs.openome_model.HumanJudgment#isEnabled()
	 * @see #getHumanJudgment()
	 * @generated
	 */
	EAttribute getHumanJudgment_Enabled();

	/**
	 * Returns the meta object for the containment reference '{@link edu.toronto.cs.openome_model.HumanJudgment#getLabelBag <em>Label Bag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Label Bag</em>'.
	 * @see edu.toronto.cs.openome_model.HumanJudgment#getLabelBag()
	 * @see #getHumanJudgment()
	 * @generated
	 */
	EReference getHumanJudgment_LabelBag();

	/**
	 * Returns the meta object for class '{@link edu.toronto.cs.openome_model.LabelBag <em>Label Bag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Label Bag</em>'.
	 * @see edu.toronto.cs.openome_model.LabelBag
	 * @generated
	 */
	EClass getLabelBag();

	/**
	 * Returns the meta object for the reference list '{@link edu.toronto.cs.openome_model.LabelBag#getLabelBagIntentions <em>Label Bag Intentions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Label Bag Intentions</em>'.
	 * @see edu.toronto.cs.openome_model.LabelBag#getLabelBagIntentions()
	 * @see #getLabelBag()
	 * @generated
	 */
	EReference getLabelBag_LabelBagIntentions();

	/**
	 * Returns the meta object for the attribute list '{@link edu.toronto.cs.openome_model.LabelBag#getLabelBagEvalLabels <em>Label Bag Eval Labels</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Label Bag Eval Labels</em>'.
	 * @see edu.toronto.cs.openome_model.LabelBag#getLabelBagEvalLabels()
	 * @see #getLabelBag()
	 * @generated
	 */
	EAttribute getLabelBag_LabelBagEvalLabels();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.LabelBag#isToResolve <em>To Resolve</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>To Resolve</em>'.
	 * @see edu.toronto.cs.openome_model.LabelBag#isToResolve()
	 * @see #getLabelBag()
	 * @generated
	 */
	EAttribute getLabelBag_ToResolve();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.LabelBag#isAllPositive <em>All Positive</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>All Positive</em>'.
	 * @see edu.toronto.cs.openome_model.LabelBag#isAllPositive()
	 * @see #getLabelBag()
	 * @generated
	 */
	EAttribute getLabelBag_AllPositive();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.LabelBag#isAllNegative <em>All Negative</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>All Negative</em>'.
	 * @see edu.toronto.cs.openome_model.LabelBag#isAllNegative()
	 * @see #getLabelBag()
	 * @generated
	 */
	EAttribute getLabelBag_AllNegative();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.LabelBag#isHasFullPositive <em>Has Full Positive</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Has Full Positive</em>'.
	 * @see edu.toronto.cs.openome_model.LabelBag#isHasFullPositive()
	 * @see #getLabelBag()
	 * @generated
	 */
	EAttribute getLabelBag_HasFullPositive();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.LabelBag#isHasFullNegative <em>Has Full Negative</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Has Full Negative</em>'.
	 * @see edu.toronto.cs.openome_model.LabelBag#isHasFullNegative()
	 * @see #getLabelBag()
	 * @generated
	 */
	EAttribute getLabelBag_HasFullNegative();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.LabelBag#isHasUnknown <em>Has Unknown</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Has Unknown</em>'.
	 * @see edu.toronto.cs.openome_model.LabelBag#isHasUnknown()
	 * @see #getLabelBag()
	 * @generated
	 */
	EAttribute getLabelBag_HasUnknown();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.LabelBag#isHasConflict <em>Has Conflict</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Has Conflict</em>'.
	 * @see edu.toronto.cs.openome_model.LabelBag#isHasConflict()
	 * @see #getLabelBag()
	 * @generated
	 */
	EAttribute getLabelBag_HasConflict();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.LabelBag#isAllUnknown <em>All Unknown</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>All Unknown</em>'.
	 * @see edu.toronto.cs.openome_model.LabelBag#isAllUnknown()
	 * @see #getLabelBag()
	 * @generated
	 */
	EAttribute getLabelBag_AllUnknown();

	/**
	 * Returns the meta object for the attribute '{@link edu.toronto.cs.openome_model.LabelBag#isAllConflict <em>All Conflict</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>All Conflict</em>'.
	 * @see edu.toronto.cs.openome_model.LabelBag#isAllConflict()
	 * @see #getLabelBag()
	 * @generated
	 */
	EAttribute getLabelBag_AllConflict();

	/**
	 * Returns the meta object for enum '{@link edu.toronto.cs.openome_model.EvaluationLabel <em>Evaluation Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Evaluation Label</em>'.
	 * @see edu.toronto.cs.openome_model.EvaluationLabel
	 * @generated
	 */
	EEnum getEvaluationLabel();

	/**
	 * Returns the meta object for enum '{@link edu.toronto.cs.openome_model.GoalModelingContributionType <em>Goal Modeling Contribution Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Goal Modeling Contribution Type</em>'.
	 * @see edu.toronto.cs.openome_model.GoalModelingContributionType
	 * @generated
	 */
	EEnum getGoalModelingContributionType();

	/**
	 * Returns the meta object for enum '{@link edu.toronto.cs.openome_model.GoalModelingContributionSymmetry <em>Goal Modeling Contribution Symmetry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Goal Modeling Contribution Symmetry</em>'.
	 * @see edu.toronto.cs.openome_model.GoalModelingContributionSymmetry
	 * @generated
	 */
	EEnum getGoalModelingContributionSymmetry();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	openome_modelFactory getopenome_modelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.ActorImpl <em>Actor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.ActorImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getActor()
		 * @generated
		 */
		EClass ACTOR = eINSTANCE.getActor();

		/**
		 * The meta object literal for the '<em><b>Is a</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR__IS_A = eINSTANCE.getActor_Is_a();

		/**
		 * The meta object literal for the '<em><b>Is part of</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR__IS_PART_OF = eINSTANCE.getActor_Is_part_of();

		/**
		 * The meta object literal for the '<em><b>Believes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR__BELIEVES = eINSTANCE.getActor_Believes();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.AgentImpl <em>Agent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.AgentImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getAgent()
		 * @generated
		 */
		EClass AGENT = eINSTANCE.getAgent();

		/**
		 * The meta object literal for the '<em><b>Plays</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AGENT__PLAYS = eINSTANCE.getAgent_Plays();

		/**
		 * The meta object literal for the '<em><b>Occupies</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AGENT__OCCUPIES = eINSTANCE.getAgent_Occupies();

		/**
		 * The meta object literal for the '<em><b>Ins</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AGENT__INS = eINSTANCE.getAgent_Ins();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.AndDecompositionImpl <em>And Decomposition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.AndDecompositionImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getAndDecomposition()
		 * @generated
		 */
		EClass AND_DECOMPOSITION = eINSTANCE.getAndDecomposition();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.ContributionImpl <em>Contribution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.ContributionImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getContribution()
		 * @generated
		 */
		EClass CONTRIBUTION = eINSTANCE.getContribution();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTRIBUTION__TARGET = eINSTANCE.getContribution_Target();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTRIBUTION__MODEL = eINSTANCE.getContribution_Model();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTRIBUTION__SOURCE = eINSTANCE.getContribution_Source();

		/**
		 * The meta object literal for the '<em><b>Goal model symmetry</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTRIBUTION__GOAL_MODEL_SYMMETRY = eINSTANCE.getContribution_Goal_model_symmetry();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.DecompositionImpl <em>Decomposition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.DecompositionImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getDecomposition()
		 * @generated
		 */
		EClass DECOMPOSITION = eINSTANCE.getDecomposition();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DECOMPOSITION__SOURCE = eINSTANCE.getDecomposition_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DECOMPOSITION__TARGET = eINSTANCE.getDecomposition_Target();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DECOMPOSITION__MODEL = eINSTANCE.getDecomposition_Model();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.DependencyImpl <em>Dependency</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.DependencyImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getDependency()
		 * @generated
		 */
		EClass DEPENDENCY = eINSTANCE.getDependency();

		/**
		 * The meta object literal for the '<em><b>Dependency From</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPENDENCY__DEPENDENCY_FROM = eINSTANCE.getDependency_DependencyFrom();

		/**
		 * The meta object literal for the '<em><b>Dependency To</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPENDENCY__DEPENDENCY_TO = eINSTANCE.getDependency_DependencyTo();

		/**
		 * The meta object literal for the '<em><b>Trust</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEPENDENCY__TRUST = eINSTANCE.getDependency_Trust();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEPENDENCY__LABEL = eINSTANCE.getDependency_Label();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPENDENCY__MODEL = eINSTANCE.getDependency_Model();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.GoalImpl <em>Goal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.GoalImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getGoal()
		 * @generated
		 */
		EClass GOAL = eINSTANCE.getGoal();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.IntentionImpl <em>Intention</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.IntentionImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getIntention()
		 * @generated
		 */
		EClass INTENTION = eINSTANCE.getIntention();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENTION__NAME = eINSTANCE.getIntention_Name();

		/**
		 * The meta object literal for the '<em><b>System</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENTION__SYSTEM = eINSTANCE.getIntention_System();

		/**
		 * The meta object literal for the '<em><b>Boundary</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENTION__BOUNDARY = eINSTANCE.getIntention_Boundary();

		/**
		 * The meta object literal for the '<em><b>Exclusive</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENTION__EXCLUSIVE = eINSTANCE.getIntention_Exclusive();

		/**
		 * The meta object literal for the '<em><b>Sequential</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENTION__SEQUENTIAL = eINSTANCE.getIntention_Sequential();

		/**
		 * The meta object literal for the '<em><b>Parallel</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENTION__PARALLEL = eINSTANCE.getIntention_Parallel();

		/**
		 * The meta object literal for the '<em><b>Property</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTENTION__PROPERTY = eINSTANCE.getIntention_Property();

		/**
		 * The meta object literal for the '<em><b>Decompositions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTENTION__DECOMPOSITIONS = eINSTANCE.getIntention_Decompositions();

		/**
		 * The meta object literal for the '<em><b>Parent Decompositions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTENTION__PARENT_DECOMPOSITIONS = eINSTANCE.getIntention_ParentDecompositions();

		/**
		 * The meta object literal for the '<em><b>Container</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTENTION__CONTAINER = eINSTANCE.getIntention_Container();

		/**
		 * The meta object literal for the '<em><b>Qualitative Reasoning Combined Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENTION__QUALITATIVE_REASONING_COMBINED_LABEL = eINSTANCE.getIntention_QualitativeReasoningCombinedLabel();

		/**
		 * The meta object literal for the '<em><b>Qualitative Reasoning Satisfied Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENTION__QUALITATIVE_REASONING_SATISFIED_LABEL = eINSTANCE.getIntention_QualitativeReasoningSatisfiedLabel();

		/**
		 * The meta object literal for the '<em><b>Qualitative Reasoning Denial Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENTION__QUALITATIVE_REASONING_DENIAL_LABEL = eINSTANCE.getIntention_QualitativeReasoningDenialLabel();

		/**
		 * The meta object literal for the '<em><b>Quantitative Reasoning Combined Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENTION__QUANTITATIVE_REASONING_COMBINED_LABEL = eINSTANCE.getIntention_QuantitativeReasoningCombinedLabel();

		/**
		 * The meta object literal for the '<em><b>Quantitative Reasoning Denied Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENTION__QUANTITATIVE_REASONING_DENIED_LABEL = eINSTANCE.getIntention_QuantitativeReasoningDeniedLabel();

		/**
		 * The meta object literal for the '<em><b>Quantitative Reasoning Satisfied Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENTION__QUANTITATIVE_REASONING_SATISFIED_LABEL = eINSTANCE.getIntention_QuantitativeReasoningSatisfiedLabel();

		/**
		 * The meta object literal for the '<em><b>Contributes To</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTENTION__CONTRIBUTES_TO = eINSTANCE.getIntention_ContributesTo();

		/**
		 * The meta object literal for the '<em><b>Contributes From</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTENTION__CONTRIBUTES_FROM = eINSTANCE.getIntention_ContributesFrom();

		/**
		 * The meta object literal for the '<em><b>Human Judgments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTENTION__HUMAN_JUDGMENTS = eINSTANCE.getIntention_HumanJudgments();

		/**
		 * The meta object literal for the '<em><b>Label Bag</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTENTION__LABEL_BAG = eINSTANCE.getIntention_LabelBag();

		/**
		 * The meta object literal for the '<em><b>Initial Eval Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENTION__INITIAL_EVAL_LABEL = eINSTANCE.getIntention_InitialEvalLabel();

		/**
		 * The meta object literal for the '<em><b>Reverse Label Bag</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTENTION__REVERSE_LABEL_BAG = eINSTANCE.getIntention_ReverseLabelBag();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.ModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.ModelImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getModel()
		 * @generated
		 */
		EClass MODEL = eINSTANCE.getModel();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL__NAME = eINSTANCE.getModel_Name();

		/**
		 * The meta object literal for the '<em><b>Intentions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__INTENTIONS = eINSTANCE.getModel_Intentions();

		/**
		 * The meta object literal for the '<em><b>Contributions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__CONTRIBUTIONS = eINSTANCE.getModel_Contributions();

		/**
		 * The meta object literal for the '<em><b>Dependencies</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__DEPENDENCIES = eINSTANCE.getModel_Dependencies();

		/**
		 * The meta object literal for the '<em><b>Decompositions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__DECOMPOSITIONS = eINSTANCE.getModel_Decompositions();

		/**
		 * The meta object literal for the '<em><b>Containers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__CONTAINERS = eINSTANCE.getModel_Containers();

		/**
		 * The meta object literal for the '<em><b>Correlations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__CORRELATIONS = eINSTANCE.getModel_Correlations();

		/**
		 * The meta object literal for the '<em><b>Associations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__ASSOCIATIONS = eINSTANCE.getModel_Associations();

		/**
		 * The meta object literal for the '<em><b>Alternatives</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__ALTERNATIVES = eINSTANCE.getModel_Alternatives();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.OrDecompositionImpl <em>Or Decomposition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.OrDecompositionImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getOrDecomposition()
		 * @generated
		 */
		EClass OR_DECOMPOSITION = eINSTANCE.getOrDecomposition();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.PositionImpl <em>Position</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.PositionImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getPosition()
		 * @generated
		 */
		EClass POSITION = eINSTANCE.getPosition();

		/**
		 * The meta object literal for the '<em><b>Covers</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POSITION__COVERS = eINSTANCE.getPosition_Covers();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.PropertyImpl <em>Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.PropertyImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getProperty()
		 * @generated
		 */
		EClass PROPERTY = eINSTANCE.getProperty();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__NAME = eINSTANCE.getProperty_Name();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__VALUE = eINSTANCE.getProperty_Value();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.ResourceImpl <em>Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.ResourceImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getResource()
		 * @generated
		 */
		EClass RESOURCE = eINSTANCE.getResource();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.RoleImpl <em>Role</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.RoleImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getRole()
		 * @generated
		 */
		EClass ROLE = eINSTANCE.getRole();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.SoftgoalImpl <em>Softgoal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.SoftgoalImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getSoftgoal()
		 * @generated
		 */
		EClass SOFTGOAL = eINSTANCE.getSoftgoal();

		/**
		 * The meta object literal for the '<em><b>Topic</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOFTGOAL__TOPIC = eINSTANCE.getSoftgoal_Topic();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.TaskImpl <em>Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.TaskImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getTask()
		 * @generated
		 */
		EClass TASK = eINSTANCE.getTask();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.ContainerImpl <em>Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.ContainerImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getContainer()
		 * @generated
		 */
		EClass CONTAINER = eINSTANCE.getContainer();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINER__NAME = eINSTANCE.getContainer_Name();

		/**
		 * The meta object literal for the '<em><b>Sub</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__SUB = eINSTANCE.getContainer_Sub();

		/**
		 * The meta object literal for the '<em><b>Intentions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__INTENTIONS = eINSTANCE.getContainer_Intentions();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__MODEL = eINSTANCE.getContainer_Model();

		/**
		 * The meta object literal for the '<em><b>Association To</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__ASSOCIATION_TO = eINSTANCE.getContainer_AssociationTo();

		/**
		 * The meta object literal for the '<em><b>Association From</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__ASSOCIATION_FROM = eINSTANCE.getContainer_AssociationFrom();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.BeliefImpl <em>Belief</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.BeliefImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getBelief()
		 * @generated
		 */
		EClass BELIEF = eINSTANCE.getBelief();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.LinkImpl <em>Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.LinkImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getLink()
		 * @generated
		 */
		EClass LINK = eINSTANCE.getLink();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.CorrelationImpl <em>Correlation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.CorrelationImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getCorrelation()
		 * @generated
		 */
		EClass CORRELATION = eINSTANCE.getCorrelation();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORRELATION__TARGET = eINSTANCE.getCorrelation_Target();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORRELATION__SOURCE = eINSTANCE.getCorrelation_Source();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.DependableImpl <em>Dependable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.DependableImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getDependable()
		 * @generated
		 */
		EClass DEPENDABLE = eINSTANCE.getDependable();

		/**
		 * The meta object literal for the '<em><b>Dependency From</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPENDABLE__DEPENDENCY_FROM = eINSTANCE.getDependable_DependencyFrom();

		/**
		 * The meta object literal for the '<em><b>Dependency To</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPENDABLE__DEPENDENCY_TO = eINSTANCE.getDependable_DependencyTo();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.HelpContributionImpl <em>Help Contribution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.HelpContributionImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getHelpContribution()
		 * @generated
		 */
		EClass HELP_CONTRIBUTION = eINSTANCE.getHelpContribution();

		/**
		 * The meta object literal for the '<em><b>Contribution Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HELP_CONTRIBUTION__CONTRIBUTION_TYPE = eINSTANCE.getHelpContribution_ContributionType();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.HurtContributionImpl <em>Hurt Contribution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.HurtContributionImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getHurtContribution()
		 * @generated
		 */
		EClass HURT_CONTRIBUTION = eINSTANCE.getHurtContribution();

		/**
		 * The meta object literal for the '<em><b>Contribution Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HURT_CONTRIBUTION__CONTRIBUTION_TYPE = eINSTANCE.getHurtContribution_ContributionType();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.MakeContributionImpl <em>Make Contribution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.MakeContributionImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getMakeContribution()
		 * @generated
		 */
		EClass MAKE_CONTRIBUTION = eINSTANCE.getMakeContribution();

		/**
		 * The meta object literal for the '<em><b>Contribution Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAKE_CONTRIBUTION__CONTRIBUTION_TYPE = eINSTANCE.getMakeContribution_ContributionType();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.BreakContributionImpl <em>Break Contribution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.BreakContributionImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getBreakContribution()
		 * @generated
		 */
		EClass BREAK_CONTRIBUTION = eINSTANCE.getBreakContribution();

		/**
		 * The meta object literal for the '<em><b>Contribution Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BREAK_CONTRIBUTION__CONTRIBUTION_TYPE = eINSTANCE.getBreakContribution_ContributionType();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.SomePlusContributionImpl <em>Some Plus Contribution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.SomePlusContributionImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getSomePlusContribution()
		 * @generated
		 */
		EClass SOME_PLUS_CONTRIBUTION = eINSTANCE.getSomePlusContribution();

		/**
		 * The meta object literal for the '<em><b>Contribution Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOME_PLUS_CONTRIBUTION__CONTRIBUTION_TYPE = eINSTANCE.getSomePlusContribution_ContributionType();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.SomeMinusContributionImpl <em>Some Minus Contribution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.SomeMinusContributionImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getSomeMinusContribution()
		 * @generated
		 */
		EClass SOME_MINUS_CONTRIBUTION = eINSTANCE.getSomeMinusContribution();

		/**
		 * The meta object literal for the '<em><b>Contribution Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOME_MINUS_CONTRIBUTION__CONTRIBUTION_TYPE = eINSTANCE.getSomeMinusContribution_ContributionType();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.UnknownContributionImpl <em>Unknown Contribution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.UnknownContributionImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getUnknownContribution()
		 * @generated
		 */
		EClass UNKNOWN_CONTRIBUTION = eINSTANCE.getUnknownContribution();

		/**
		 * The meta object literal for the '<em><b>Contribution Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNKNOWN_CONTRIBUTION__CONTRIBUTION_TYPE = eINSTANCE.getUnknownContribution_ContributionType();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.AndContributionImpl <em>And Contribution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.AndContributionImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getAndContribution()
		 * @generated
		 */
		EClass AND_CONTRIBUTION = eINSTANCE.getAndContribution();

		/**
		 * The meta object literal for the '<em><b>Contribution Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AND_CONTRIBUTION__CONTRIBUTION_TYPE = eINSTANCE.getAndContribution_ContributionType();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.OrContributionImpl <em>Or Contribution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.OrContributionImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getOrContribution()
		 * @generated
		 */
		EClass OR_CONTRIBUTION = eINSTANCE.getOrContribution();

		/**
		 * The meta object literal for the '<em><b>Contribution Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OR_CONTRIBUTION__CONTRIBUTION_TYPE = eINSTANCE.getOrContribution_ContributionType();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.AssociationImpl <em>Association</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.AssociationImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getAssociation()
		 * @generated
		 */
		EClass ASSOCIATION = eINSTANCE.getAssociation();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSOCIATION__SOURCE = eINSTANCE.getAssociation_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSOCIATION__TARGET = eINSTANCE.getAssociation_Target();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.IsAAssociationImpl <em>Is AAssociation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.IsAAssociationImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getIsAAssociation()
		 * @generated
		 */
		EClass IS_AASSOCIATION = eINSTANCE.getIsAAssociation();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IS_AASSOCIATION__LABEL = eINSTANCE.getIsAAssociation_Label();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.CoversAssociationImpl <em>Covers Association</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.CoversAssociationImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getCoversAssociation()
		 * @generated
		 */
		EClass COVERS_ASSOCIATION = eINSTANCE.getCoversAssociation();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COVERS_ASSOCIATION__LABEL = eINSTANCE.getCoversAssociation_Label();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.IsPartOfAssociationImpl <em>Is Part Of Association</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.IsPartOfAssociationImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getIsPartOfAssociation()
		 * @generated
		 */
		EClass IS_PART_OF_ASSOCIATION = eINSTANCE.getIsPartOfAssociation();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IS_PART_OF_ASSOCIATION__LABEL = eINSTANCE.getIsPartOfAssociation_Label();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.OccupiesAssociationImpl <em>Occupies Association</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.OccupiesAssociationImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getOccupiesAssociation()
		 * @generated
		 */
		EClass OCCUPIES_ASSOCIATION = eINSTANCE.getOccupiesAssociation();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OCCUPIES_ASSOCIATION__LABEL = eINSTANCE.getOccupiesAssociation_Label();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.PlaysAssociationImpl <em>Plays Association</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.PlaysAssociationImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getPlaysAssociation()
		 * @generated
		 */
		EClass PLAYS_ASSOCIATION = eINSTANCE.getPlaysAssociation();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLAYS_ASSOCIATION__LABEL = eINSTANCE.getPlaysAssociation_Label();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.INSAssociationImpl <em>INS Association</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.INSAssociationImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getINSAssociation()
		 * @generated
		 */
		EClass INS_ASSOCIATION = eINSTANCE.getINSAssociation();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INS_ASSOCIATION__LABEL = eINSTANCE.getINSAssociation_Label();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.AlternativeImpl <em>Alternative</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.AlternativeImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getAlternative()
		 * @generated
		 */
		EClass ALTERNATIVE = eINSTANCE.getAlternative();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ALTERNATIVE__NAME = eINSTANCE.getAlternative_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ALTERNATIVE__DESCRIPTION = eINSTANCE.getAlternative_Description();

		/**
		 * The meta object literal for the '<em><b>Intentions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ALTERNATIVE__INTENTIONS = eINSTANCE.getAlternative_Intentions();

		/**
		 * The meta object literal for the '<em><b>Eval Labels</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ALTERNATIVE__EVAL_LABELS = eINSTANCE.getAlternative_EvalLabels();

		/**
		 * The meta object literal for the '<em><b>Direction</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ALTERNATIVE__DIRECTION = eINSTANCE.getAlternative_Direction();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.IntentionToEvaluationLabelMapImpl <em>Intention To Evaluation Label Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.IntentionToEvaluationLabelMapImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getIntentionToEvaluationLabelMap()
		 * @generated
		 */
		EClass INTENTION_TO_EVALUATION_LABEL_MAP = eINSTANCE.getIntentionToEvaluationLabelMap();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTENTION_TO_EVALUATION_LABEL_MAP__KEY = eINSTANCE.getIntentionToEvaluationLabelMap_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENTION_TO_EVALUATION_LABEL_MAP__VALUE = eINSTANCE.getIntentionToEvaluationLabelMap_Value();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.HumanJudgmentImpl <em>Human Judgment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.HumanJudgmentImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getHumanJudgment()
		 * @generated
		 */
		EClass HUMAN_JUDGMENT = eINSTANCE.getHumanJudgment();

		/**
		 * The meta object literal for the '<em><b>Result Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HUMAN_JUDGMENT__RESULT_LABEL = eINSTANCE.getHumanJudgment_ResultLabel();

		/**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HUMAN_JUDGMENT__ENABLED = eINSTANCE.getHumanJudgment_Enabled();

		/**
		 * The meta object literal for the '<em><b>Label Bag</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HUMAN_JUDGMENT__LABEL_BAG = eINSTANCE.getHumanJudgment_LabelBag();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.impl.LabelBagImpl <em>Label Bag</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.impl.LabelBagImpl
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getLabelBag()
		 * @generated
		 */
		EClass LABEL_BAG = eINSTANCE.getLabelBag();

		/**
		 * The meta object literal for the '<em><b>Label Bag Intentions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LABEL_BAG__LABEL_BAG_INTENTIONS = eINSTANCE.getLabelBag_LabelBagIntentions();

		/**
		 * The meta object literal for the '<em><b>Label Bag Eval Labels</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_BAG__LABEL_BAG_EVAL_LABELS = eINSTANCE.getLabelBag_LabelBagEvalLabels();

		/**
		 * The meta object literal for the '<em><b>To Resolve</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_BAG__TO_RESOLVE = eINSTANCE.getLabelBag_ToResolve();

		/**
		 * The meta object literal for the '<em><b>All Positive</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_BAG__ALL_POSITIVE = eINSTANCE.getLabelBag_AllPositive();

		/**
		 * The meta object literal for the '<em><b>All Negative</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_BAG__ALL_NEGATIVE = eINSTANCE.getLabelBag_AllNegative();

		/**
		 * The meta object literal for the '<em><b>Has Full Positive</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_BAG__HAS_FULL_POSITIVE = eINSTANCE.getLabelBag_HasFullPositive();

		/**
		 * The meta object literal for the '<em><b>Has Full Negative</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_BAG__HAS_FULL_NEGATIVE = eINSTANCE.getLabelBag_HasFullNegative();

		/**
		 * The meta object literal for the '<em><b>Has Unknown</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_BAG__HAS_UNKNOWN = eINSTANCE.getLabelBag_HasUnknown();

		/**
		 * The meta object literal for the '<em><b>Has Conflict</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_BAG__HAS_CONFLICT = eINSTANCE.getLabelBag_HasConflict();

		/**
		 * The meta object literal for the '<em><b>All Unknown</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_BAG__ALL_UNKNOWN = eINSTANCE.getLabelBag_AllUnknown();

		/**
		 * The meta object literal for the '<em><b>All Conflict</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL_BAG__ALL_CONFLICT = eINSTANCE.getLabelBag_AllConflict();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.EvaluationLabel <em>Evaluation Label</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.EvaluationLabel
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getEvaluationLabel()
		 * @generated
		 */
		EEnum EVALUATION_LABEL = eINSTANCE.getEvaluationLabel();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.GoalModelingContributionType <em>Goal Modeling Contribution Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.GoalModelingContributionType
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getGoalModelingContributionType()
		 * @generated
		 */
		EEnum GOAL_MODELING_CONTRIBUTION_TYPE = eINSTANCE.getGoalModelingContributionType();

		/**
		 * The meta object literal for the '{@link edu.toronto.cs.openome_model.GoalModelingContributionSymmetry <em>Goal Modeling Contribution Symmetry</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.toronto.cs.openome_model.GoalModelingContributionSymmetry
		 * @see edu.toronto.cs.openome_model.impl.openome_modelPackageImpl#getGoalModelingContributionSymmetry()
		 * @generated
		 */
		EEnum GOAL_MODELING_CONTRIBUTION_SYMMETRY = eINSTANCE.getGoalModelingContributionSymmetry();

	}

} //openome_modelPackage

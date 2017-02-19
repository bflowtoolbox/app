/**
 * <copyright>
 * </copyright>
 *
 * $Id: Q7dslPackage.java 532 2007-09-03 23:04:02Z nernst $
 */
package q7dsl;

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
 * @see q7dsl.Q7dslFactory
 * @model kind="package"
 * @generated
 */
public interface Q7dslPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "q7dsl";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.example.org/my/dsl";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "q7dsl";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Q7dslPackage eINSTANCE = q7dsl.impl.Q7dslPackageImpl.init();

	/**
	 * The meta object id for the '{@link q7dsl.impl.ModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see q7dsl.impl.ModelImpl
	 * @see q7dsl.impl.Q7dslPackageImpl#getModel()
	 * @generated
	 */
	int MODEL = 0;

	/**
	 * The feature id for the '<em><b>Advices</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__ADVICES = 0;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link q7dsl.impl.AdviceImpl <em>Advice</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see q7dsl.impl.AdviceImpl
	 * @see q7dsl.impl.Q7dslPackageImpl#getAdvice()
	 * @generated
	 */
	int ADVICE = 1;

	/**
	 * The feature id for the '<em><b>How</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADVICE__HOW = 0;

	/**
	 * The feature id for the '<em><b>When</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADVICE__WHEN = 1;

	/**
	 * The feature id for the '<em><b>Why</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADVICE__WHY = 2;

	/**
	 * The feature id for the '<em><b>Label</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADVICE__LABEL = 3;

	/**
	 * The feature id for the '<em><b>Howmuch</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADVICE__HOWMUCH = 4;

	/**
	 * The feature id for the '<em><b>Who</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADVICE__WHO = 5;

	/**
	 * The feature id for the '<em><b>Whom</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADVICE__WHOM = 6;

	/**
	 * The feature id for the '<em><b>What</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADVICE__WHAT = 7;

	/**
	 * The feature id for the '<em><b>Where</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADVICE__WHERE = 8;

	/**
	 * The number of structural features of the '<em>Advice</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADVICE_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link q7dsl.impl.WhoImpl <em>Who</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see q7dsl.impl.WhoImpl
	 * @see q7dsl.impl.Q7dslPackageImpl#getWho()
	 * @generated
	 */
	int WHO = 2;

	/**
	 * The feature id for the '<em><b>Actor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHO__ACTOR = 0;

	/**
	 * The number of structural features of the '<em>Who</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHO_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link q7dsl.impl.ActorImpl <em>Actor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see q7dsl.impl.ActorImpl
	 * @see q7dsl.impl.Q7dslPackageImpl#getActor()
	 * @generated
	 */
	int ACTOR = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__NAME = 0;

	/**
	 * The number of structural features of the '<em>Actor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link q7dsl.impl.WhenImpl <em>When</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see q7dsl.impl.WhenImpl
	 * @see q7dsl.impl.Q7dslPackageImpl#getWhen()
	 * @generated
	 */
	int WHEN = 4;

	/**
	 * The feature id for the '<em><b>Expr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHEN__EXPR = 0;

	/**
	 * The number of structural features of the '<em>When</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHEN_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link q7dsl.impl.WhyImpl <em>Why</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see q7dsl.impl.WhyImpl
	 * @see q7dsl.impl.Q7dslPackageImpl#getWhy()
	 * @generated
	 */
	int WHY = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHY__NAME = 0;

	/**
	 * The number of structural features of the '<em>Why</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHY_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link q7dsl.impl.WhatImpl <em>What</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see q7dsl.impl.WhatImpl
	 * @see q7dsl.impl.Q7dslPackageImpl#getWhat()
	 * @generated
	 */
	int WHAT = 6;

	/**
	 * The feature id for the '<em><b>Topics</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHAT__TOPICS = 0;

	/**
	 * The number of structural features of the '<em>What</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHAT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link q7dsl.impl.TopicImpl <em>Topic</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see q7dsl.impl.TopicImpl
	 * @see q7dsl.impl.Q7dslPackageImpl#getTopic()
	 * @generated
	 */
	int TOPIC = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC__NAME = 0;

	/**
	 * The number of structural features of the '<em>Topic</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOPIC_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link q7dsl.impl.WhereImpl <em>Where</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see q7dsl.impl.WhereImpl
	 * @see q7dsl.impl.Q7dslPackageImpl#getWhere()
	 * @generated
	 */
	int WHERE = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHERE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Op</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHERE__OP = 1;

	/**
	 * The feature id for the '<em><b>Topics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHERE__TOPICS = 2;

	/**
	 * The number of structural features of the '<em>Where</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHERE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link q7dsl.impl.HowImpl <em>How</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see q7dsl.impl.HowImpl
	 * @see q7dsl.impl.Q7dslPackageImpl#getHow()
	 * @generated
	 */
	int HOW = 9;

	/**
	 * The feature id for the '<em><b>Advices</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOW__ADVICES = 0;

	/**
	 * The feature id for the '<em><b>Enrich</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOW__ENRICH = 1;

	/**
	 * The feature id for the '<em><b>Op</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOW__OP = 2;

	/**
	 * The number of structural features of the '<em>How</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOW_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link q7dsl.impl.HowMuchRulesImpl <em>How Much Rules</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see q7dsl.impl.HowMuchRulesImpl
	 * @see q7dsl.impl.Q7dslPackageImpl#getHowMuchRules()
	 * @generated
	 */
	int HOW_MUCH_RULES = 10;

	/**
	 * The feature id for the '<em><b>Howmuch</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOW_MUCH_RULES__HOWMUCH = 0;

	/**
	 * The number of structural features of the '<em>How Much Rules</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOW_MUCH_RULES_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link q7dsl.impl.HowMuchImpl <em>How Much</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see q7dsl.impl.HowMuchImpl
	 * @see q7dsl.impl.Q7dslPackageImpl#getHowMuch()
	 * @generated
	 */
	int HOW_MUCH = 11;

	/**
	 * The feature id for the '<em><b>Why</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOW_MUCH__WHY = 0;

	/**
	 * The feature id for the '<em><b>Who</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOW_MUCH__WHO = 1;

	/**
	 * The feature id for the '<em><b>Op</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOW_MUCH__OP = 2;

	/**
	 * The feature id for the '<em><b>What</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOW_MUCH__WHAT = 3;

	/**
	 * The feature id for the '<em><b>Trust</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOW_MUCH__TRUST = 4;

	/**
	 * The feature id for the '<em><b>Strength</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOW_MUCH__STRENGTH = 5;

	/**
	 * The number of structural features of the '<em>How Much</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOW_MUCH_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link q7dsl.impl.LabelImpl <em>Label</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see q7dsl.impl.LabelImpl
	 * @see q7dsl.impl.Q7dslPackageImpl#getLabel()
	 * @generated
	 */
	int LABEL = 12;

	/**
	 * The feature id for the '<em><b>Sat</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__SAT = 0;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__LABEL = 1;

	/**
	 * The feature id for the '<em><b>Den</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__DEN = 2;

	/**
	 * The number of structural features of the '<em>Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link q7dsl.impl.FLOATImpl <em>FLOAT</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see q7dsl.impl.FLOATImpl
	 * @see q7dsl.impl.Q7dslPackageImpl#getFLOAT()
	 * @generated
	 */
	int FLOAT = 13;

	/**
	 * The feature id for the '<em><b>Decimal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT__DECIMAL = 0;

	/**
	 * The feature id for the '<em><b>Integral</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT__INTEGRAL = 1;

	/**
	 * The number of structural features of the '<em>FLOAT</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link q7dsl.Op <em>Op</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see q7dsl.Op
	 * @see q7dsl.impl.Q7dslPackageImpl#getOp()
	 * @generated
	 */
	int OP = 14;

	/**
	 * The meta object id for the '{@link q7dsl.DecompositionType <em>Decomposition Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see q7dsl.DecompositionType
	 * @see q7dsl.impl.Q7dslPackageImpl#getDecompositionType()
	 * @generated
	 */
	int DECOMPOSITION_TYPE = 15;

	/**
	 * The meta object id for the '{@link q7dsl.Enrichment <em>Enrichment</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see q7dsl.Enrichment
	 * @see q7dsl.impl.Q7dslPackageImpl#getEnrichment()
	 * @generated
	 */
	int ENRICHMENT = 16;

	/**
	 * The meta object id for the '{@link q7dsl.LabelEnumerator <em>Label Enumerator</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see q7dsl.LabelEnumerator
	 * @see q7dsl.impl.Q7dslPackageImpl#getLabelEnumerator()
	 * @generated
	 */
	int LABEL_ENUMERATOR = 17;


	/**
	 * Returns the meta object for class '{@link q7dsl.Model <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see q7dsl.Model
	 * @generated
	 */
	EClass getModel();

	/**
	 * Returns the meta object for the containment reference list '{@link q7dsl.Model#getAdvices <em>Advices</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Advices</em>'.
	 * @see q7dsl.Model#getAdvices()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Advices();

	/**
	 * Returns the meta object for class '{@link q7dsl.Advice <em>Advice</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Advice</em>'.
	 * @see q7dsl.Advice
	 * @generated
	 */
	EClass getAdvice();

	/**
	 * Returns the meta object for the containment reference '{@link q7dsl.Advice#getHow <em>How</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>How</em>'.
	 * @see q7dsl.Advice#getHow()
	 * @see #getAdvice()
	 * @generated
	 */
	EReference getAdvice_How();

	/**
	 * Returns the meta object for the containment reference '{@link q7dsl.Advice#getWhen <em>When</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>When</em>'.
	 * @see q7dsl.Advice#getWhen()
	 * @see #getAdvice()
	 * @generated
	 */
	EReference getAdvice_When();

	/**
	 * Returns the meta object for the containment reference '{@link q7dsl.Advice#getWhy <em>Why</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Why</em>'.
	 * @see q7dsl.Advice#getWhy()
	 * @see #getAdvice()
	 * @generated
	 */
	EReference getAdvice_Why();

	/**
	 * Returns the meta object for the containment reference '{@link q7dsl.Advice#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Label</em>'.
	 * @see q7dsl.Advice#getLabel()
	 * @see #getAdvice()
	 * @generated
	 */
	EReference getAdvice_Label();

	/**
	 * Returns the meta object for the containment reference '{@link q7dsl.Advice#getHowmuch <em>Howmuch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Howmuch</em>'.
	 * @see q7dsl.Advice#getHowmuch()
	 * @see #getAdvice()
	 * @generated
	 */
	EReference getAdvice_Howmuch();

	/**
	 * Returns the meta object for the attribute '{@link q7dsl.Advice#getWho <em>Who</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Who</em>'.
	 * @see q7dsl.Advice#getWho()
	 * @see #getAdvice()
	 * @generated
	 */
	EAttribute getAdvice_Who();

	/**
	 * Returns the meta object for the containment reference '{@link q7dsl.Advice#getWhom <em>Whom</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Whom</em>'.
	 * @see q7dsl.Advice#getWhom()
	 * @see #getAdvice()
	 * @generated
	 */
	EReference getAdvice_Whom();

	/**
	 * Returns the meta object for the containment reference '{@link q7dsl.Advice#getWhat <em>What</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>What</em>'.
	 * @see q7dsl.Advice#getWhat()
	 * @see #getAdvice()
	 * @generated
	 */
	EReference getAdvice_What();

	/**
	 * Returns the meta object for the containment reference '{@link q7dsl.Advice#getWhere <em>Where</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Where</em>'.
	 * @see q7dsl.Advice#getWhere()
	 * @see #getAdvice()
	 * @generated
	 */
	EReference getAdvice_Where();

	/**
	 * Returns the meta object for class '{@link q7dsl.Who <em>Who</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Who</em>'.
	 * @see q7dsl.Who
	 * @generated
	 */
	EClass getWho();

	/**
	 * Returns the meta object for the containment reference '{@link q7dsl.Who#getActor <em>Actor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Actor</em>'.
	 * @see q7dsl.Who#getActor()
	 * @see #getWho()
	 * @generated
	 */
	EReference getWho_Actor();

	/**
	 * Returns the meta object for class '{@link q7dsl.Actor <em>Actor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Actor</em>'.
	 * @see q7dsl.Actor
	 * @generated
	 */
	EClass getActor();

	/**
	 * Returns the meta object for the attribute '{@link q7dsl.Actor#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see q7dsl.Actor#getName()
	 * @see #getActor()
	 * @generated
	 */
	EAttribute getActor_Name();

	/**
	 * Returns the meta object for class '{@link q7dsl.When <em>When</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>When</em>'.
	 * @see q7dsl.When
	 * @generated
	 */
	EClass getWhen();

	/**
	 * Returns the meta object for the attribute '{@link q7dsl.When#getExpr <em>Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expr</em>'.
	 * @see q7dsl.When#getExpr()
	 * @see #getWhen()
	 * @generated
	 */
	EAttribute getWhen_Expr();

	/**
	 * Returns the meta object for class '{@link q7dsl.Why <em>Why</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Why</em>'.
	 * @see q7dsl.Why
	 * @generated
	 */
	EClass getWhy();

	/**
	 * Returns the meta object for the attribute '{@link q7dsl.Why#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see q7dsl.Why#getName()
	 * @see #getWhy()
	 * @generated
	 */
	EAttribute getWhy_Name();

	/**
	 * Returns the meta object for class '{@link q7dsl.What <em>What</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>What</em>'.
	 * @see q7dsl.What
	 * @generated
	 */
	EClass getWhat();

	/**
	 * Returns the meta object for the containment reference list '{@link q7dsl.What#getTopics <em>Topics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Topics</em>'.
	 * @see q7dsl.What#getTopics()
	 * @see #getWhat()
	 * @generated
	 */
	EReference getWhat_Topics();

	/**
	 * Returns the meta object for class '{@link q7dsl.Topic <em>Topic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Topic</em>'.
	 * @see q7dsl.Topic
	 * @generated
	 */
	EClass getTopic();

	/**
	 * Returns the meta object for the attribute '{@link q7dsl.Topic#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see q7dsl.Topic#getName()
	 * @see #getTopic()
	 * @generated
	 */
	EAttribute getTopic_Name();

	/**
	 * Returns the meta object for class '{@link q7dsl.Where <em>Where</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Where</em>'.
	 * @see q7dsl.Where
	 * @generated
	 */
	EClass getWhere();

	/**
	 * Returns the meta object for the containment reference '{@link q7dsl.Where#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see q7dsl.Where#getName()
	 * @see #getWhere()
	 * @generated
	 */
	EReference getWhere_Name();

	/**
	 * Returns the meta object for the attribute '{@link q7dsl.Where#getOp <em>Op</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Op</em>'.
	 * @see q7dsl.Where#getOp()
	 * @see #getWhere()
	 * @generated
	 */
	EAttribute getWhere_Op();

	/**
	 * Returns the meta object for the containment reference '{@link q7dsl.Where#getTopics <em>Topics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Topics</em>'.
	 * @see q7dsl.Where#getTopics()
	 * @see #getWhere()
	 * @generated
	 */
	EReference getWhere_Topics();

	/**
	 * Returns the meta object for class '{@link q7dsl.How <em>How</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>How</em>'.
	 * @see q7dsl.How
	 * @generated
	 */
	EClass getHow();

	/**
	 * Returns the meta object for the containment reference list '{@link q7dsl.How#getAdvices <em>Advices</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Advices</em>'.
	 * @see q7dsl.How#getAdvices()
	 * @see #getHow()
	 * @generated
	 */
	EReference getHow_Advices();

	/**
	 * Returns the meta object for the attribute '{@link q7dsl.How#getEnrich <em>Enrich</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enrich</em>'.
	 * @see q7dsl.How#getEnrich()
	 * @see #getHow()
	 * @generated
	 */
	EAttribute getHow_Enrich();

	/**
	 * Returns the meta object for the attribute '{@link q7dsl.How#getOp <em>Op</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Op</em>'.
	 * @see q7dsl.How#getOp()
	 * @see #getHow()
	 * @generated
	 */
	EAttribute getHow_Op();

	/**
	 * Returns the meta object for class '{@link q7dsl.HowMuchRules <em>How Much Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>How Much Rules</em>'.
	 * @see q7dsl.HowMuchRules
	 * @generated
	 */
	EClass getHowMuchRules();

	/**
	 * Returns the meta object for the containment reference list '{@link q7dsl.HowMuchRules#getHowmuch <em>Howmuch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Howmuch</em>'.
	 * @see q7dsl.HowMuchRules#getHowmuch()
	 * @see #getHowMuchRules()
	 * @generated
	 */
	EReference getHowMuchRules_Howmuch();

	/**
	 * Returns the meta object for class '{@link q7dsl.HowMuch <em>How Much</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>How Much</em>'.
	 * @see q7dsl.HowMuch
	 * @generated
	 */
	EClass getHowMuch();

	/**
	 * Returns the meta object for the containment reference '{@link q7dsl.HowMuch#getWhy <em>Why</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Why</em>'.
	 * @see q7dsl.HowMuch#getWhy()
	 * @see #getHowMuch()
	 * @generated
	 */
	EReference getHowMuch_Why();

	/**
	 * Returns the meta object for the containment reference '{@link q7dsl.HowMuch#getWho <em>Who</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Who</em>'.
	 * @see q7dsl.HowMuch#getWho()
	 * @see #getHowMuch()
	 * @generated
	 */
	EReference getHowMuch_Who();

	/**
	 * Returns the meta object for the attribute '{@link q7dsl.HowMuch#getOp <em>Op</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Op</em>'.
	 * @see q7dsl.HowMuch#getOp()
	 * @see #getHowMuch()
	 * @generated
	 */
	EAttribute getHowMuch_Op();

	/**
	 * Returns the meta object for the containment reference '{@link q7dsl.HowMuch#getWhat <em>What</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>What</em>'.
	 * @see q7dsl.HowMuch#getWhat()
	 * @see #getHowMuch()
	 * @generated
	 */
	EReference getHowMuch_What();

	/**
	 * Returns the meta object for the containment reference '{@link q7dsl.HowMuch#getTrust <em>Trust</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Trust</em>'.
	 * @see q7dsl.HowMuch#getTrust()
	 * @see #getHowMuch()
	 * @generated
	 */
	EReference getHowMuch_Trust();

	/**
	 * Returns the meta object for the containment reference '{@link q7dsl.HowMuch#getStrength <em>Strength</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Strength</em>'.
	 * @see q7dsl.HowMuch#getStrength()
	 * @see #getHowMuch()
	 * @generated
	 */
	EReference getHowMuch_Strength();

	/**
	 * Returns the meta object for class '{@link q7dsl.Label <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Label</em>'.
	 * @see q7dsl.Label
	 * @generated
	 */
	EClass getLabel();

	/**
	 * Returns the meta object for the containment reference '{@link q7dsl.Label#getSat <em>Sat</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Sat</em>'.
	 * @see q7dsl.Label#getSat()
	 * @see #getLabel()
	 * @generated
	 */
	EReference getLabel_Sat();

	/**
	 * Returns the meta object for the attribute '{@link q7dsl.Label#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see q7dsl.Label#getLabel()
	 * @see #getLabel()
	 * @generated
	 */
	EAttribute getLabel_Label();

	/**
	 * Returns the meta object for the containment reference '{@link q7dsl.Label#getDen <em>Den</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Den</em>'.
	 * @see q7dsl.Label#getDen()
	 * @see #getLabel()
	 * @generated
	 */
	EReference getLabel_Den();

	/**
	 * Returns the meta object for class '{@link q7dsl.FLOAT <em>FLOAT</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>FLOAT</em>'.
	 * @see q7dsl.FLOAT
	 * @generated
	 */
	EClass getFLOAT();

	/**
	 * Returns the meta object for the attribute '{@link q7dsl.FLOAT#getDecimal <em>Decimal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Decimal</em>'.
	 * @see q7dsl.FLOAT#getDecimal()
	 * @see #getFLOAT()
	 * @generated
	 */
	EAttribute getFLOAT_Decimal();

	/**
	 * Returns the meta object for the attribute '{@link q7dsl.FLOAT#getIntegral <em>Integral</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Integral</em>'.
	 * @see q7dsl.FLOAT#getIntegral()
	 * @see #getFLOAT()
	 * @generated
	 */
	EAttribute getFLOAT_Integral();

	/**
	 * Returns the meta object for enum '{@link q7dsl.Op <em>Op</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Op</em>'.
	 * @see q7dsl.Op
	 * @generated
	 */
	EEnum getOp();

	/**
	 * Returns the meta object for enum '{@link q7dsl.DecompositionType <em>Decomposition Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Decomposition Type</em>'.
	 * @see q7dsl.DecompositionType
	 * @generated
	 */
	EEnum getDecompositionType();

	/**
	 * Returns the meta object for enum '{@link q7dsl.Enrichment <em>Enrichment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Enrichment</em>'.
	 * @see q7dsl.Enrichment
	 * @generated
	 */
	EEnum getEnrichment();

	/**
	 * Returns the meta object for enum '{@link q7dsl.LabelEnumerator <em>Label Enumerator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Label Enumerator</em>'.
	 * @see q7dsl.LabelEnumerator
	 * @generated
	 */
	EEnum getLabelEnumerator();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Q7dslFactory getQ7dslFactory();

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
		 * The meta object literal for the '{@link q7dsl.impl.ModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see q7dsl.impl.ModelImpl
		 * @see q7dsl.impl.Q7dslPackageImpl#getModel()
		 * @generated
		 */
		EClass MODEL = eINSTANCE.getModel();

		/**
		 * The meta object literal for the '<em><b>Advices</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__ADVICES = eINSTANCE.getModel_Advices();

		/**
		 * The meta object literal for the '{@link q7dsl.impl.AdviceImpl <em>Advice</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see q7dsl.impl.AdviceImpl
		 * @see q7dsl.impl.Q7dslPackageImpl#getAdvice()
		 * @generated
		 */
		EClass ADVICE = eINSTANCE.getAdvice();

		/**
		 * The meta object literal for the '<em><b>How</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADVICE__HOW = eINSTANCE.getAdvice_How();

		/**
		 * The meta object literal for the '<em><b>When</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADVICE__WHEN = eINSTANCE.getAdvice_When();

		/**
		 * The meta object literal for the '<em><b>Why</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADVICE__WHY = eINSTANCE.getAdvice_Why();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADVICE__LABEL = eINSTANCE.getAdvice_Label();

		/**
		 * The meta object literal for the '<em><b>Howmuch</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADVICE__HOWMUCH = eINSTANCE.getAdvice_Howmuch();

		/**
		 * The meta object literal for the '<em><b>Who</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADVICE__WHO = eINSTANCE.getAdvice_Who();

		/**
		 * The meta object literal for the '<em><b>Whom</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADVICE__WHOM = eINSTANCE.getAdvice_Whom();

		/**
		 * The meta object literal for the '<em><b>What</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADVICE__WHAT = eINSTANCE.getAdvice_What();

		/**
		 * The meta object literal for the '<em><b>Where</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADVICE__WHERE = eINSTANCE.getAdvice_Where();

		/**
		 * The meta object literal for the '{@link q7dsl.impl.WhoImpl <em>Who</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see q7dsl.impl.WhoImpl
		 * @see q7dsl.impl.Q7dslPackageImpl#getWho()
		 * @generated
		 */
		EClass WHO = eINSTANCE.getWho();

		/**
		 * The meta object literal for the '<em><b>Actor</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WHO__ACTOR = eINSTANCE.getWho_Actor();

		/**
		 * The meta object literal for the '{@link q7dsl.impl.ActorImpl <em>Actor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see q7dsl.impl.ActorImpl
		 * @see q7dsl.impl.Q7dslPackageImpl#getActor()
		 * @generated
		 */
		EClass ACTOR = eINSTANCE.getActor();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTOR__NAME = eINSTANCE.getActor_Name();

		/**
		 * The meta object literal for the '{@link q7dsl.impl.WhenImpl <em>When</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see q7dsl.impl.WhenImpl
		 * @see q7dsl.impl.Q7dslPackageImpl#getWhen()
		 * @generated
		 */
		EClass WHEN = eINSTANCE.getWhen();

		/**
		 * The meta object literal for the '<em><b>Expr</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WHEN__EXPR = eINSTANCE.getWhen_Expr();

		/**
		 * The meta object literal for the '{@link q7dsl.impl.WhyImpl <em>Why</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see q7dsl.impl.WhyImpl
		 * @see q7dsl.impl.Q7dslPackageImpl#getWhy()
		 * @generated
		 */
		EClass WHY = eINSTANCE.getWhy();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WHY__NAME = eINSTANCE.getWhy_Name();

		/**
		 * The meta object literal for the '{@link q7dsl.impl.WhatImpl <em>What</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see q7dsl.impl.WhatImpl
		 * @see q7dsl.impl.Q7dslPackageImpl#getWhat()
		 * @generated
		 */
		EClass WHAT = eINSTANCE.getWhat();

		/**
		 * The meta object literal for the '<em><b>Topics</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WHAT__TOPICS = eINSTANCE.getWhat_Topics();

		/**
		 * The meta object literal for the '{@link q7dsl.impl.TopicImpl <em>Topic</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see q7dsl.impl.TopicImpl
		 * @see q7dsl.impl.Q7dslPackageImpl#getTopic()
		 * @generated
		 */
		EClass TOPIC = eINSTANCE.getTopic();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOPIC__NAME = eINSTANCE.getTopic_Name();

		/**
		 * The meta object literal for the '{@link q7dsl.impl.WhereImpl <em>Where</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see q7dsl.impl.WhereImpl
		 * @see q7dsl.impl.Q7dslPackageImpl#getWhere()
		 * @generated
		 */
		EClass WHERE = eINSTANCE.getWhere();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WHERE__NAME = eINSTANCE.getWhere_Name();

		/**
		 * The meta object literal for the '<em><b>Op</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WHERE__OP = eINSTANCE.getWhere_Op();

		/**
		 * The meta object literal for the '<em><b>Topics</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WHERE__TOPICS = eINSTANCE.getWhere_Topics();

		/**
		 * The meta object literal for the '{@link q7dsl.impl.HowImpl <em>How</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see q7dsl.impl.HowImpl
		 * @see q7dsl.impl.Q7dslPackageImpl#getHow()
		 * @generated
		 */
		EClass HOW = eINSTANCE.getHow();

		/**
		 * The meta object literal for the '<em><b>Advices</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HOW__ADVICES = eINSTANCE.getHow_Advices();

		/**
		 * The meta object literal for the '<em><b>Enrich</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HOW__ENRICH = eINSTANCE.getHow_Enrich();

		/**
		 * The meta object literal for the '<em><b>Op</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HOW__OP = eINSTANCE.getHow_Op();

		/**
		 * The meta object literal for the '{@link q7dsl.impl.HowMuchRulesImpl <em>How Much Rules</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see q7dsl.impl.HowMuchRulesImpl
		 * @see q7dsl.impl.Q7dslPackageImpl#getHowMuchRules()
		 * @generated
		 */
		EClass HOW_MUCH_RULES = eINSTANCE.getHowMuchRules();

		/**
		 * The meta object literal for the '<em><b>Howmuch</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HOW_MUCH_RULES__HOWMUCH = eINSTANCE.getHowMuchRules_Howmuch();

		/**
		 * The meta object literal for the '{@link q7dsl.impl.HowMuchImpl <em>How Much</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see q7dsl.impl.HowMuchImpl
		 * @see q7dsl.impl.Q7dslPackageImpl#getHowMuch()
		 * @generated
		 */
		EClass HOW_MUCH = eINSTANCE.getHowMuch();

		/**
		 * The meta object literal for the '<em><b>Why</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HOW_MUCH__WHY = eINSTANCE.getHowMuch_Why();

		/**
		 * The meta object literal for the '<em><b>Who</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HOW_MUCH__WHO = eINSTANCE.getHowMuch_Who();

		/**
		 * The meta object literal for the '<em><b>Op</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HOW_MUCH__OP = eINSTANCE.getHowMuch_Op();

		/**
		 * The meta object literal for the '<em><b>What</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HOW_MUCH__WHAT = eINSTANCE.getHowMuch_What();

		/**
		 * The meta object literal for the '<em><b>Trust</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HOW_MUCH__TRUST = eINSTANCE.getHowMuch_Trust();

		/**
		 * The meta object literal for the '<em><b>Strength</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HOW_MUCH__STRENGTH = eINSTANCE.getHowMuch_Strength();

		/**
		 * The meta object literal for the '{@link q7dsl.impl.LabelImpl <em>Label</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see q7dsl.impl.LabelImpl
		 * @see q7dsl.impl.Q7dslPackageImpl#getLabel()
		 * @generated
		 */
		EClass LABEL = eINSTANCE.getLabel();

		/**
		 * The meta object literal for the '<em><b>Sat</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LABEL__SAT = eINSTANCE.getLabel_Sat();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL__LABEL = eINSTANCE.getLabel_Label();

		/**
		 * The meta object literal for the '<em><b>Den</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LABEL__DEN = eINSTANCE.getLabel_Den();

		/**
		 * The meta object literal for the '{@link q7dsl.impl.FLOATImpl <em>FLOAT</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see q7dsl.impl.FLOATImpl
		 * @see q7dsl.impl.Q7dslPackageImpl#getFLOAT()
		 * @generated
		 */
		EClass FLOAT = eINSTANCE.getFLOAT();

		/**
		 * The meta object literal for the '<em><b>Decimal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FLOAT__DECIMAL = eINSTANCE.getFLOAT_Decimal();

		/**
		 * The meta object literal for the '<em><b>Integral</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FLOAT__INTEGRAL = eINSTANCE.getFLOAT_Integral();

		/**
		 * The meta object literal for the '{@link q7dsl.Op <em>Op</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see q7dsl.Op
		 * @see q7dsl.impl.Q7dslPackageImpl#getOp()
		 * @generated
		 */
		EEnum OP = eINSTANCE.getOp();

		/**
		 * The meta object literal for the '{@link q7dsl.DecompositionType <em>Decomposition Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see q7dsl.DecompositionType
		 * @see q7dsl.impl.Q7dslPackageImpl#getDecompositionType()
		 * @generated
		 */
		EEnum DECOMPOSITION_TYPE = eINSTANCE.getDecompositionType();

		/**
		 * The meta object literal for the '{@link q7dsl.Enrichment <em>Enrichment</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see q7dsl.Enrichment
		 * @see q7dsl.impl.Q7dslPackageImpl#getEnrichment()
		 * @generated
		 */
		EEnum ENRICHMENT = eINSTANCE.getEnrichment();

		/**
		 * The meta object literal for the '{@link q7dsl.LabelEnumerator <em>Label Enumerator</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see q7dsl.LabelEnumerator
		 * @see q7dsl.impl.Q7dslPackageImpl#getLabelEnumerator()
		 * @generated
		 */
		EEnum LABEL_ENUMERATOR = eINSTANCE.getLabelEnumerator();

	}

} //Q7dslPackage

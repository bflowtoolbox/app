/**
 * <copyright>
 * </copyright>
 *
 * $Id: Q7dslFactoryImpl.java 532 2007-09-03 23:04:02Z nernst $
 */
package q7dsl.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import q7dsl.Actor;
import q7dsl.Advice;
import q7dsl.DecompositionType;
import q7dsl.Enrichment;
import q7dsl.FLOAT;
import q7dsl.How;
import q7dsl.HowMuch;
import q7dsl.HowMuchRules;
import q7dsl.Label;
import q7dsl.LabelEnumerator;
import q7dsl.Model;
import q7dsl.Op;
import q7dsl.Q7dslFactory;
import q7dsl.Q7dslPackage;
import q7dsl.Topic;
import q7dsl.What;
import q7dsl.When;
import q7dsl.Where;
import q7dsl.Who;
import q7dsl.Why;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Q7dslFactoryImpl extends EFactoryImpl implements Q7dslFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Q7dslFactory init() {
		try {
			Q7dslFactory theQ7dslFactory = (Q7dslFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.example.org/my/dsl"); 
			if (theQ7dslFactory != null) {
				return theQ7dslFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Q7dslFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Q7dslFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case Q7dslPackage.MODEL: return createModel();
			case Q7dslPackage.ADVICE: return createAdvice();
			case Q7dslPackage.WHO: return createWho();
			case Q7dslPackage.ACTOR: return createActor();
			case Q7dslPackage.WHEN: return createWhen();
			case Q7dslPackage.WHY: return createWhy();
			case Q7dslPackage.WHAT: return createWhat();
			case Q7dslPackage.TOPIC: return createTopic();
			case Q7dslPackage.WHERE: return createWhere();
			case Q7dslPackage.HOW: return createHow();
			case Q7dslPackage.HOW_MUCH_RULES: return createHowMuchRules();
			case Q7dslPackage.HOW_MUCH: return createHowMuch();
			case Q7dslPackage.LABEL: return createLabel();
			case Q7dslPackage.FLOAT: return createFLOAT();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case Q7dslPackage.OP:
				return createOpFromString(eDataType, initialValue);
			case Q7dslPackage.DECOMPOSITION_TYPE:
				return createDecompositionTypeFromString(eDataType, initialValue);
			case Q7dslPackage.ENRICHMENT:
				return createEnrichmentFromString(eDataType, initialValue);
			case Q7dslPackage.LABEL_ENUMERATOR:
				return createLabelEnumeratorFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case Q7dslPackage.OP:
				return convertOpToString(eDataType, instanceValue);
			case Q7dslPackage.DECOMPOSITION_TYPE:
				return convertDecompositionTypeToString(eDataType, instanceValue);
			case Q7dslPackage.ENRICHMENT:
				return convertEnrichmentToString(eDataType, instanceValue);
			case Q7dslPackage.LABEL_ENUMERATOR:
				return convertLabelEnumeratorToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model createModel() {
		ModelImpl model = new ModelImpl();
		return model;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Advice createAdvice() {
		AdviceImpl advice = new AdviceImpl();
		return advice;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Who createWho() {
		WhoImpl who = new WhoImpl();
		return who;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Actor createActor() {
		ActorImpl actor = new ActorImpl();
		return actor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public When createWhen() {
		WhenImpl when = new WhenImpl();
		return when;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Why createWhy() {
		WhyImpl why = new WhyImpl();
		return why;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public What createWhat() {
		WhatImpl what = new WhatImpl();
		return what;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Topic createTopic() {
		TopicImpl topic = new TopicImpl();
		return topic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Where createWhere() {
		WhereImpl where = new WhereImpl();
		return where;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public How createHow() {
		HowImpl how = new HowImpl();
		return how;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HowMuchRules createHowMuchRules() {
		HowMuchRulesImpl howMuchRules = new HowMuchRulesImpl();
		return howMuchRules;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HowMuch createHowMuch() {
		HowMuchImpl howMuch = new HowMuchImpl();
		return howMuch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Label createLabel() {
		LabelImpl label = new LabelImpl();
		return label;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FLOAT createFLOAT() {
		FLOATImpl float_ = new FLOATImpl();
		return float_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Op createOpFromString(EDataType eDataType, String initialValue) {
		Op result = Op.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOpToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DecompositionType createDecompositionTypeFromString(EDataType eDataType, String initialValue) {
		DecompositionType result = DecompositionType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDecompositionTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Enrichment createEnrichmentFromString(EDataType eDataType, String initialValue) {
		Enrichment result = Enrichment.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEnrichmentToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LabelEnumerator createLabelEnumeratorFromString(EDataType eDataType, String initialValue) {
		LabelEnumerator result = LabelEnumerator.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLabelEnumeratorToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Q7dslPackage getQ7dslPackage() {
		return (Q7dslPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static Q7dslPackage getPackage() {
		return Q7dslPackage.eINSTANCE;
	}

} //Q7dslFactoryImpl

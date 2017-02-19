/**
 * Copyright 2001-2008 University of Toronto
 *
 * $Id$
 */
package edu.toronto.cs.openome_model.util;

import edu.toronto.cs.openome_model.*;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see edu.toronto.cs.openome_model.openome_modelPackage
 * @generated
 */
public class openome_modelSwitch<T> {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright 2001-2008 University of Toronto";

	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static openome_modelPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public openome_modelSwitch() {
		if (modelPackage == null) {
			modelPackage = openome_modelPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case openome_modelPackage.ACTOR: {
				Actor actor = (Actor)theEObject;
				T result = caseActor(actor);
				if (result == null) result = caseContainer(actor);
				if (result == null) result = caseDependable(actor);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.AGENT: {
				Agent agent = (Agent)theEObject;
				T result = caseAgent(agent);
				if (result == null) result = caseContainer(agent);
				if (result == null) result = caseDependable(agent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.AND_DECOMPOSITION: {
				AndDecomposition andDecomposition = (AndDecomposition)theEObject;
				T result = caseAndDecomposition(andDecomposition);
				if (result == null) result = caseDecomposition(andDecomposition);
				if (result == null) result = caseLink(andDecomposition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.CONTRIBUTION: {
				Contribution contribution = (Contribution)theEObject;
				T result = caseContribution(contribution);
				if (result == null) result = caseLink(contribution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.DECOMPOSITION: {
				Decomposition decomposition = (Decomposition)theEObject;
				T result = caseDecomposition(decomposition);
				if (result == null) result = caseLink(decomposition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.DEPENDENCY: {
				Dependency dependency = (Dependency)theEObject;
				T result = caseDependency(dependency);
				if (result == null) result = caseLink(dependency);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.GOAL: {
				Goal goal = (Goal)theEObject;
				T result = caseGoal(goal);
				if (result == null) result = caseIntention(goal);
				if (result == null) result = caseDependable(goal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.INTENTION: {
				Intention intention = (Intention)theEObject;
				T result = caseIntention(intention);
				if (result == null) result = caseDependable(intention);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.MODEL: {
				Model model = (Model)theEObject;
				T result = caseModel(model);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.OR_DECOMPOSITION: {
				OrDecomposition orDecomposition = (OrDecomposition)theEObject;
				T result = caseOrDecomposition(orDecomposition);
				if (result == null) result = caseDecomposition(orDecomposition);
				if (result == null) result = caseLink(orDecomposition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.POSITION: {
				Position position = (Position)theEObject;
				T result = casePosition(position);
				if (result == null) result = caseContainer(position);
				if (result == null) result = caseDependable(position);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.PROPERTY: {
				Property property = (Property)theEObject;
				T result = caseProperty(property);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.RESOURCE: {
				Resource resource = (Resource)theEObject;
				T result = caseResource(resource);
				if (result == null) result = caseIntention(resource);
				if (result == null) result = caseDependable(resource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.ROLE: {
				Role role = (Role)theEObject;
				T result = caseRole(role);
				if (result == null) result = caseContainer(role);
				if (result == null) result = caseDependable(role);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.SOFTGOAL: {
				Softgoal softgoal = (Softgoal)theEObject;
				T result = caseSoftgoal(softgoal);
				if (result == null) result = caseIntention(softgoal);
				if (result == null) result = caseDependable(softgoal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.TASK: {
				Task task = (Task)theEObject;
				T result = caseTask(task);
				if (result == null) result = caseIntention(task);
				if (result == null) result = caseDependable(task);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.CONTAINER: {
				Container container = (Container)theEObject;
				T result = caseContainer(container);
				if (result == null) result = caseDependable(container);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.BELIEF: {
				Belief belief = (Belief)theEObject;
				T result = caseBelief(belief);
				if (result == null) result = caseIntention(belief);
				if (result == null) result = caseDependable(belief);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.LINK: {
				Link link = (Link)theEObject;
				T result = caseLink(link);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.CORRELATION: {
				Correlation correlation = (Correlation)theEObject;
				T result = caseCorrelation(correlation);
				if (result == null) result = caseLink(correlation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.DEPENDABLE: {
				Dependable dependable = (Dependable)theEObject;
				T result = caseDependable(dependable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.HELP_CONTRIBUTION: {
				HelpContribution helpContribution = (HelpContribution)theEObject;
				T result = caseHelpContribution(helpContribution);
				if (result == null) result = caseContribution(helpContribution);
				if (result == null) result = caseLink(helpContribution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.HURT_CONTRIBUTION: {
				HurtContribution hurtContribution = (HurtContribution)theEObject;
				T result = caseHurtContribution(hurtContribution);
				if (result == null) result = caseContribution(hurtContribution);
				if (result == null) result = caseLink(hurtContribution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.MAKE_CONTRIBUTION: {
				MakeContribution makeContribution = (MakeContribution)theEObject;
				T result = caseMakeContribution(makeContribution);
				if (result == null) result = caseContribution(makeContribution);
				if (result == null) result = caseLink(makeContribution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.BREAK_CONTRIBUTION: {
				BreakContribution breakContribution = (BreakContribution)theEObject;
				T result = caseBreakContribution(breakContribution);
				if (result == null) result = caseContribution(breakContribution);
				if (result == null) result = caseLink(breakContribution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.SOME_PLUS_CONTRIBUTION: {
				SomePlusContribution somePlusContribution = (SomePlusContribution)theEObject;
				T result = caseSomePlusContribution(somePlusContribution);
				if (result == null) result = caseContribution(somePlusContribution);
				if (result == null) result = caseLink(somePlusContribution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.SOME_MINUS_CONTRIBUTION: {
				SomeMinusContribution someMinusContribution = (SomeMinusContribution)theEObject;
				T result = caseSomeMinusContribution(someMinusContribution);
				if (result == null) result = caseContribution(someMinusContribution);
				if (result == null) result = caseLink(someMinusContribution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.UNKNOWN_CONTRIBUTION: {
				UnknownContribution unknownContribution = (UnknownContribution)theEObject;
				T result = caseUnknownContribution(unknownContribution);
				if (result == null) result = caseContribution(unknownContribution);
				if (result == null) result = caseLink(unknownContribution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.AND_CONTRIBUTION: {
				AndContribution andContribution = (AndContribution)theEObject;
				T result = caseAndContribution(andContribution);
				if (result == null) result = caseContribution(andContribution);
				if (result == null) result = caseLink(andContribution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.OR_CONTRIBUTION: {
				OrContribution orContribution = (OrContribution)theEObject;
				T result = caseOrContribution(orContribution);
				if (result == null) result = caseContribution(orContribution);
				if (result == null) result = caseLink(orContribution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.ASSOCIATION: {
				Association association = (Association)theEObject;
				T result = caseAssociation(association);
				if (result == null) result = caseLink(association);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.IS_AASSOCIATION: {
				IsAAssociation isAAssociation = (IsAAssociation)theEObject;
				T result = caseIsAAssociation(isAAssociation);
				if (result == null) result = caseAssociation(isAAssociation);
				if (result == null) result = caseLink(isAAssociation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.COVERS_ASSOCIATION: {
				CoversAssociation coversAssociation = (CoversAssociation)theEObject;
				T result = caseCoversAssociation(coversAssociation);
				if (result == null) result = caseAssociation(coversAssociation);
				if (result == null) result = caseLink(coversAssociation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.IS_PART_OF_ASSOCIATION: {
				IsPartOfAssociation isPartOfAssociation = (IsPartOfAssociation)theEObject;
				T result = caseIsPartOfAssociation(isPartOfAssociation);
				if (result == null) result = caseAssociation(isPartOfAssociation);
				if (result == null) result = caseLink(isPartOfAssociation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.OCCUPIES_ASSOCIATION: {
				OccupiesAssociation occupiesAssociation = (OccupiesAssociation)theEObject;
				T result = caseOccupiesAssociation(occupiesAssociation);
				if (result == null) result = caseAssociation(occupiesAssociation);
				if (result == null) result = caseLink(occupiesAssociation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.PLAYS_ASSOCIATION: {
				PlaysAssociation playsAssociation = (PlaysAssociation)theEObject;
				T result = casePlaysAssociation(playsAssociation);
				if (result == null) result = caseAssociation(playsAssociation);
				if (result == null) result = caseLink(playsAssociation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.INS_ASSOCIATION: {
				INSAssociation insAssociation = (INSAssociation)theEObject;
				T result = caseINSAssociation(insAssociation);
				if (result == null) result = caseAssociation(insAssociation);
				if (result == null) result = caseLink(insAssociation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.ALTERNATIVE: {
				Alternative alternative = (Alternative)theEObject;
				T result = caseAlternative(alternative);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.INTENTION_TO_EVALUATION_LABEL_MAP: {
				@SuppressWarnings("unchecked") Map.Entry<Intention, EvaluationLabel> intentionToEvaluationLabelMap = (Map.Entry<Intention, EvaluationLabel>)theEObject;
				T result = caseIntentionToEvaluationLabelMap(intentionToEvaluationLabelMap);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.HUMAN_JUDGMENT: {
				HumanJudgment humanJudgment = (HumanJudgment)theEObject;
				T result = caseHumanJudgment(humanJudgment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case openome_modelPackage.LABEL_BAG: {
				LabelBag labelBag = (LabelBag)theEObject;
				T result = caseLabelBag(labelBag);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Actor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Actor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActor(Actor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Agent</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Agent</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAgent(Agent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>And Decomposition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>And Decomposition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAndDecomposition(AndDecomposition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Contribution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Contribution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContribution(Contribution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Decomposition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Decomposition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDecomposition(Decomposition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dependency</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dependency</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDependency(Dependency object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Goal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Goal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGoal(Goal object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Intention</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Intention</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntention(Intention object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModel(Model object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Or Decomposition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Or Decomposition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOrDecomposition(OrDecomposition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Position</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Position</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePosition(Position object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProperty(Property object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResource(Resource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Role</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Role</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRole(Role object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Softgoal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Softgoal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSoftgoal(Softgoal object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTask(Task object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainer(Container object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Belief</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Belief</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBelief(Belief object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLink(Link object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Correlation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Correlation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCorrelation(Correlation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dependable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dependable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDependable(Dependable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Help Contribution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Help Contribution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHelpContribution(HelpContribution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Hurt Contribution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Hurt Contribution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHurtContribution(HurtContribution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Make Contribution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Make Contribution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMakeContribution(MakeContribution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Break Contribution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Break Contribution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBreakContribution(BreakContribution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Some Plus Contribution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Some Plus Contribution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSomePlusContribution(SomePlusContribution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Some Minus Contribution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Some Minus Contribution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSomeMinusContribution(SomeMinusContribution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unknown Contribution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unknown Contribution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnknownContribution(UnknownContribution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>And Contribution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>And Contribution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAndContribution(AndContribution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Or Contribution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Or Contribution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOrContribution(OrContribution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Association</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Association</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAssociation(Association object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Is AAssociation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Is AAssociation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIsAAssociation(IsAAssociation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Covers Association</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Covers Association</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCoversAssociation(CoversAssociation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Is Part Of Association</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Is Part Of Association</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIsPartOfAssociation(IsPartOfAssociation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Occupies Association</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Occupies Association</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOccupiesAssociation(OccupiesAssociation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Plays Association</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Plays Association</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePlaysAssociation(PlaysAssociation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>INS Association</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>INS Association</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseINSAssociation(INSAssociation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Alternative</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Alternative</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAlternative(Alternative object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Intention To Evaluation Label Map</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Intention To Evaluation Label Map</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntentionToEvaluationLabelMap(Map.Entry<Intention, EvaluationLabel> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Human Judgment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Human Judgment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHumanJudgment(HumanJudgment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Label Bag</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Label Bag</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLabelBag(LabelBag object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //openome_modelSwitch

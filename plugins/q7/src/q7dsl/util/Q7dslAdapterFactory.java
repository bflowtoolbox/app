/**
 * <copyright>
 * </copyright>
 *
 * $Id: Q7dslAdapterFactory.java 532 2007-09-03 23:04:02Z nernst $
 */
package q7dsl.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import q7dsl.Actor;
import q7dsl.Advice;
import q7dsl.FLOAT;
import q7dsl.How;
import q7dsl.HowMuch;
import q7dsl.HowMuchRules;
import q7dsl.Label;
import q7dsl.Model;
import q7dsl.Q7dslPackage;
import q7dsl.Topic;
import q7dsl.What;
import q7dsl.When;
import q7dsl.Where;
import q7dsl.Who;
import q7dsl.Why;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see q7dsl.Q7dslPackage
 * @generated
 */
public class Q7dslAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static Q7dslPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Q7dslAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = Q7dslPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch the delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Q7dslSwitch modelSwitch =
		new Q7dslSwitch() {
			public Object caseModel(Model object) {
				return createModelAdapter();
			}
			public Object caseAdvice(Advice object) {
				return createAdviceAdapter();
			}
			public Object caseWho(Who object) {
				return createWhoAdapter();
			}
			public Object caseActor(Actor object) {
				return createActorAdapter();
			}
			public Object caseWhen(When object) {
				return createWhenAdapter();
			}
			public Object caseWhy(Why object) {
				return createWhyAdapter();
			}
			public Object caseWhat(What object) {
				return createWhatAdapter();
			}
			public Object caseTopic(Topic object) {
				return createTopicAdapter();
			}
			public Object caseWhere(Where object) {
				return createWhereAdapter();
			}
			public Object caseHow(How object) {
				return createHowAdapter();
			}
			public Object caseHowMuchRules(HowMuchRules object) {
				return createHowMuchRulesAdapter();
			}
			public Object caseHowMuch(HowMuch object) {
				return createHowMuchAdapter();
			}
			public Object caseLabel(Label object) {
				return createLabelAdapter();
			}
			public Object caseFLOAT(FLOAT object) {
				return createFLOATAdapter();
			}
			public Object defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	public Adapter createAdapter(Notifier target) {
		return (Adapter)modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link q7dsl.Model <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see q7dsl.Model
	 * @generated
	 */
	public Adapter createModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link q7dsl.Advice <em>Advice</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see q7dsl.Advice
	 * @generated
	 */
	public Adapter createAdviceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link q7dsl.Who <em>Who</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see q7dsl.Who
	 * @generated
	 */
	public Adapter createWhoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link q7dsl.Actor <em>Actor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see q7dsl.Actor
	 * @generated
	 */
	public Adapter createActorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link q7dsl.When <em>When</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see q7dsl.When
	 * @generated
	 */
	public Adapter createWhenAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link q7dsl.Why <em>Why</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see q7dsl.Why
	 * @generated
	 */
	public Adapter createWhyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link q7dsl.What <em>What</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see q7dsl.What
	 * @generated
	 */
	public Adapter createWhatAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link q7dsl.Topic <em>Topic</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see q7dsl.Topic
	 * @generated
	 */
	public Adapter createTopicAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link q7dsl.Where <em>Where</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see q7dsl.Where
	 * @generated
	 */
	public Adapter createWhereAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link q7dsl.How <em>How</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see q7dsl.How
	 * @generated
	 */
	public Adapter createHowAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link q7dsl.HowMuchRules <em>How Much Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see q7dsl.HowMuchRules
	 * @generated
	 */
	public Adapter createHowMuchRulesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link q7dsl.HowMuch <em>How Much</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see q7dsl.HowMuch
	 * @generated
	 */
	public Adapter createHowMuchAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link q7dsl.Label <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see q7dsl.Label
	 * @generated
	 */
	public Adapter createLabelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link q7dsl.FLOAT <em>FLOAT</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see q7dsl.FLOAT
	 * @generated
	 */
	public Adapter createFLOATAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //Q7dslAdapterFactory

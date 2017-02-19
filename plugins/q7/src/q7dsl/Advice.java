/**
 * <copyright>
 * </copyright>
 *
 * $Id: Advice.java 532 2007-09-03 23:04:02Z nernst $
 */
package q7dsl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Advice</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link q7dsl.Advice#getHow <em>How</em>}</li>
 *   <li>{@link q7dsl.Advice#getWhen <em>When</em>}</li>
 *   <li>{@link q7dsl.Advice#getWhy <em>Why</em>}</li>
 *   <li>{@link q7dsl.Advice#getLabel <em>Label</em>}</li>
 *   <li>{@link q7dsl.Advice#getHowmuch <em>Howmuch</em>}</li>
 *   <li>{@link q7dsl.Advice#getWho <em>Who</em>}</li>
 *   <li>{@link q7dsl.Advice#getWhom <em>Whom</em>}</li>
 *   <li>{@link q7dsl.Advice#getWhat <em>What</em>}</li>
 *   <li>{@link q7dsl.Advice#getWhere <em>Where</em>}</li>
 * </ul>
 * </p>
 *
 * @see q7dsl.Q7dslPackage#getAdvice()
 * @model
 * @generated
 */
public interface Advice extends EObject {
	/**
	 * Returns the value of the '<em><b>How</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>How</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>How</em>' containment reference.
	 * @see #setHow(How)
	 * @see q7dsl.Q7dslPackage#getAdvice_How()
	 * @model containment="true"
	 * @generated
	 */
	How getHow();

	/**
	 * Sets the value of the '{@link q7dsl.Advice#getHow <em>How</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>How</em>' containment reference.
	 * @see #getHow()
	 * @generated
	 */
	void setHow(How value);

	/**
	 * Returns the value of the '<em><b>When</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>When</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>When</em>' containment reference.
	 * @see #setWhen(When)
	 * @see q7dsl.Q7dslPackage#getAdvice_When()
	 * @model containment="true"
	 * @generated
	 */
	When getWhen();

	/**
	 * Sets the value of the '{@link q7dsl.Advice#getWhen <em>When</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>When</em>' containment reference.
	 * @see #getWhen()
	 * @generated
	 */
	void setWhen(When value);

	/**
	 * Returns the value of the '<em><b>Why</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Why</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Why</em>' containment reference.
	 * @see #setWhy(Why)
	 * @see q7dsl.Q7dslPackage#getAdvice_Why()
	 * @model containment="true"
	 * @generated
	 */
	Why getWhy();

	/**
	 * Sets the value of the '{@link q7dsl.Advice#getWhy <em>Why</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Why</em>' containment reference.
	 * @see #getWhy()
	 * @generated
	 */
	void setWhy(Why value);

	/**
	 * Returns the value of the '<em><b>Label</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' containment reference.
	 * @see #setLabel(Label)
	 * @see q7dsl.Q7dslPackage#getAdvice_Label()
	 * @model containment="true"
	 * @generated
	 */
	Label getLabel();

	/**
	 * Sets the value of the '{@link q7dsl.Advice#getLabel <em>Label</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' containment reference.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(Label value);

	/**
	 * Returns the value of the '<em><b>Howmuch</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Howmuch</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Howmuch</em>' containment reference.
	 * @see #setHowmuch(HowMuchRules)
	 * @see q7dsl.Q7dslPackage#getAdvice_Howmuch()
	 * @model containment="true"
	 * @generated
	 */
	HowMuchRules getHowmuch();

	/**
	 * Sets the value of the '{@link q7dsl.Advice#getHowmuch <em>Howmuch</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Howmuch</em>' containment reference.
	 * @see #getHowmuch()
	 * @generated
	 */
	void setHowmuch(HowMuchRules value);

	/**
	 * Returns the value of the '<em><b>Who</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Who</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Who</em>' attribute.
	 * @see #setWho(String)
	 * @see q7dsl.Q7dslPackage#getAdvice_Who()
	 * @model
	 * @generated
	 */
	String getWho();

	/**
	 * Sets the value of the '{@link q7dsl.Advice#getWho <em>Who</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Who</em>' attribute.
	 * @see #getWho()
	 * @generated
	 */
	void setWho(String value);

	/**
	 * Returns the value of the '<em><b>Whom</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Whom</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Whom</em>' containment reference.
	 * @see #setWhom(Who)
	 * @see q7dsl.Q7dslPackage#getAdvice_Whom()
	 * @model containment="true"
	 * @generated
	 */
	Who getWhom();

	/**
	 * Sets the value of the '{@link q7dsl.Advice#getWhom <em>Whom</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Whom</em>' containment reference.
	 * @see #getWhom()
	 * @generated
	 */
	void setWhom(Who value);

	/**
	 * Returns the value of the '<em><b>What</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>What</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>What</em>' containment reference.
	 * @see #setWhat(What)
	 * @see q7dsl.Q7dslPackage#getAdvice_What()
	 * @model containment="true"
	 * @generated
	 */
	What getWhat();

	/**
	 * Sets the value of the '{@link q7dsl.Advice#getWhat <em>What</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>What</em>' containment reference.
	 * @see #getWhat()
	 * @generated
	 */
	void setWhat(What value);

	/**
	 * Returns the value of the '<em><b>Where</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Where</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Where</em>' containment reference.
	 * @see #setWhere(Where)
	 * @see q7dsl.Q7dslPackage#getAdvice_Where()
	 * @model containment="true"
	 * @generated
	 */
	Where getWhere();

	/**
	 * Sets the value of the '{@link q7dsl.Advice#getWhere <em>Where</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Where</em>' containment reference.
	 * @see #getWhere()
	 * @generated
	 */
	void setWhere(Where value);

} // Advice
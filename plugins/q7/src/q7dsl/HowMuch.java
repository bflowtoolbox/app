/**
 * <copyright>
 * </copyright>
 *
 * $Id: HowMuch.java 532 2007-09-03 23:04:02Z nernst $
 */
package q7dsl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>How Much</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link q7dsl.HowMuch#getWhy <em>Why</em>}</li>
 *   <li>{@link q7dsl.HowMuch#getWho <em>Who</em>}</li>
 *   <li>{@link q7dsl.HowMuch#getOp <em>Op</em>}</li>
 *   <li>{@link q7dsl.HowMuch#getWhat <em>What</em>}</li>
 *   <li>{@link q7dsl.HowMuch#getTrust <em>Trust</em>}</li>
 *   <li>{@link q7dsl.HowMuch#getStrength <em>Strength</em>}</li>
 * </ul>
 * </p>
 *
 * @see q7dsl.Q7dslPackage#getHowMuch()
 * @model
 * @generated
 */
public interface HowMuch extends EObject {
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
	 * @see q7dsl.Q7dslPackage#getHowMuch_Why()
	 * @model containment="true"
	 * @generated
	 */
	Why getWhy();

	/**
	 * Sets the value of the '{@link q7dsl.HowMuch#getWhy <em>Why</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Why</em>' containment reference.
	 * @see #getWhy()
	 * @generated
	 */
	void setWhy(Why value);

	/**
	 * Returns the value of the '<em><b>Who</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Who</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Who</em>' containment reference.
	 * @see #setWho(Who)
	 * @see q7dsl.Q7dslPackage#getHowMuch_Who()
	 * @model containment="true"
	 * @generated
	 */
	Who getWho();

	/**
	 * Sets the value of the '{@link q7dsl.HowMuch#getWho <em>Who</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Who</em>' containment reference.
	 * @see #getWho()
	 * @generated
	 */
	void setWho(Who value);

	/**
	 * Returns the value of the '<em><b>Op</b></em>' attribute.
	 * The literals are from the enumeration {@link q7dsl.Op}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Op</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Op</em>' attribute.
	 * @see q7dsl.Op
	 * @see #setOp(Op)
	 * @see q7dsl.Q7dslPackage#getHowMuch_Op()
	 * @model
	 * @generated
	 */
	Op getOp();

	/**
	 * Sets the value of the '{@link q7dsl.HowMuch#getOp <em>Op</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Op</em>' attribute.
	 * @see q7dsl.Op
	 * @see #getOp()
	 * @generated
	 */
	void setOp(Op value);

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
	 * @see q7dsl.Q7dslPackage#getHowMuch_What()
	 * @model containment="true"
	 * @generated
	 */
	What getWhat();

	/**
	 * Sets the value of the '{@link q7dsl.HowMuch#getWhat <em>What</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>What</em>' containment reference.
	 * @see #getWhat()
	 * @generated
	 */
	void setWhat(What value);

	/**
	 * Returns the value of the '<em><b>Trust</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trust</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trust</em>' containment reference.
	 * @see #setTrust(FLOAT)
	 * @see q7dsl.Q7dslPackage#getHowMuch_Trust()
	 * @model containment="true"
	 * @generated
	 */
	FLOAT getTrust();

	/**
	 * Sets the value of the '{@link q7dsl.HowMuch#getTrust <em>Trust</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trust</em>' containment reference.
	 * @see #getTrust()
	 * @generated
	 */
	void setTrust(FLOAT value);

	/**
	 * Returns the value of the '<em><b>Strength</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Strength</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Strength</em>' containment reference.
	 * @see #setStrength(FLOAT)
	 * @see q7dsl.Q7dslPackage#getHowMuch_Strength()
	 * @model containment="true"
	 * @generated
	 */
	FLOAT getStrength();

	/**
	 * Sets the value of the '{@link q7dsl.HowMuch#getStrength <em>Strength</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Strength</em>' containment reference.
	 * @see #getStrength()
	 * @generated
	 */
	void setStrength(FLOAT value);

} // HowMuch
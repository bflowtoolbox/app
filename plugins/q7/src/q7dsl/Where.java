/**
 * <copyright>
 * </copyright>
 *
 * $Id: Where.java 532 2007-09-03 23:04:02Z nernst $
 */
package q7dsl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Where</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link q7dsl.Where#getName <em>Name</em>}</li>
 *   <li>{@link q7dsl.Where#getOp <em>Op</em>}</li>
 *   <li>{@link q7dsl.Where#getTopics <em>Topics</em>}</li>
 * </ul>
 * </p>
 *
 * @see q7dsl.Q7dslPackage#getWhere()
 * @model
 * @generated
 */
public interface Where extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' containment reference.
	 * @see #setName(Why)
	 * @see q7dsl.Q7dslPackage#getWhere_Name()
	 * @model containment="true"
	 * @generated
	 */
	Why getName();

	/**
	 * Sets the value of the '{@link q7dsl.Where#getName <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' containment reference.
	 * @see #getName()
	 * @generated
	 */
	void setName(Why value);

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
	 * @see q7dsl.Q7dslPackage#getWhere_Op()
	 * @model
	 * @generated
	 */
	Op getOp();

	/**
	 * Sets the value of the '{@link q7dsl.Where#getOp <em>Op</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Op</em>' attribute.
	 * @see q7dsl.Op
	 * @see #getOp()
	 * @generated
	 */
	void setOp(Op value);

	/**
	 * Returns the value of the '<em><b>Topics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Topics</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Topics</em>' containment reference.
	 * @see #setTopics(What)
	 * @see q7dsl.Q7dslPackage#getWhere_Topics()
	 * @model containment="true"
	 * @generated
	 */
	What getTopics();

	/**
	 * Sets the value of the '{@link q7dsl.Where#getTopics <em>Topics</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Topics</em>' containment reference.
	 * @see #getTopics()
	 * @generated
	 */
	void setTopics(What value);

} // Where
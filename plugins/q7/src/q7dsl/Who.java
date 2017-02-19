/**
 * <copyright>
 * </copyright>
 *
 * $Id: Who.java 532 2007-09-03 23:04:02Z nernst $
 */
package q7dsl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Who</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link q7dsl.Who#getActor <em>Actor</em>}</li>
 * </ul>
 * </p>
 *
 * @see q7dsl.Q7dslPackage#getWho()
 * @model
 * @generated
 */
public interface Who extends EObject {
	/**
	 * Returns the value of the '<em><b>Actor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actor</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actor</em>' containment reference.
	 * @see #setActor(Actor)
	 * @see q7dsl.Q7dslPackage#getWho_Actor()
	 * @model containment="true"
	 * @generated
	 */
	Actor getActor();

	/**
	 * Sets the value of the '{@link q7dsl.Who#getActor <em>Actor</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actor</em>' containment reference.
	 * @see #getActor()
	 * @generated
	 */
	void setActor(Actor value);

} // Who
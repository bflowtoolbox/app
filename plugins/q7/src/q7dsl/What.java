/**
 * <copyright>
 * </copyright>
 *
 * $Id: What.java 532 2007-09-03 23:04:02Z nernst $
 */
package q7dsl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>What</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link q7dsl.What#getTopics <em>Topics</em>}</li>
 * </ul>
 * </p>
 *
 * @see q7dsl.Q7dslPackage#getWhat()
 * @model
 * @generated
 */
public interface What extends EObject {
	/**
	 * Returns the value of the '<em><b>Topics</b></em>' containment reference list.
	 * The list contents are of type {@link q7dsl.Topic}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Topics</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Topics</em>' containment reference list.
	 * @see q7dsl.Q7dslPackage#getWhat_Topics()
	 * @model type="q7dsl.Topic" containment="true"
	 * @generated
	 */
	EList getTopics();

} // What
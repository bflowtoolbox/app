/**
 * <copyright>
 * </copyright>
 *
 * $Id: HowMuchRules.java 532 2007-09-03 23:04:02Z nernst $
 */
package q7dsl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>How Much Rules</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link q7dsl.HowMuchRules#getHowmuch <em>Howmuch</em>}</li>
 * </ul>
 * </p>
 *
 * @see q7dsl.Q7dslPackage#getHowMuchRules()
 * @model
 * @generated
 */
public interface HowMuchRules extends EObject {
	/**
	 * Returns the value of the '<em><b>Howmuch</b></em>' containment reference list.
	 * The list contents are of type {@link q7dsl.HowMuch}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Howmuch</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Howmuch</em>' containment reference list.
	 * @see q7dsl.Q7dslPackage#getHowMuchRules_Howmuch()
	 * @model type="q7dsl.HowMuch" containment="true"
	 * @generated
	 */
	EList getHowmuch();

} // HowMuchRules
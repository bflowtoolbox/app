/**
 * <copyright>
 * </copyright>
 *
 * $Id: Model.java 532 2007-09-03 23:04:02Z nernst $
 */
package q7dsl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link q7dsl.Model#getAdvices <em>Advices</em>}</li>
 * </ul>
 * </p>
 *
 * @see q7dsl.Q7dslPackage#getModel()
 * @model
 * @generated
 */
public interface Model extends EObject {
	/**
	 * Returns the value of the '<em><b>Advices</b></em>' containment reference list.
	 * The list contents are of type {@link q7dsl.Advice}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Advices</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Advices</em>' containment reference list.
	 * @see q7dsl.Q7dslPackage#getModel_Advices()
	 * @model type="q7dsl.Advice" containment="true"
	 * @generated
	 */
	EList getAdvices();

} // Model
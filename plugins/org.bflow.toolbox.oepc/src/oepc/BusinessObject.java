/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package oepc;

import org.bflow.toolbox.bflow.Element;
import org.bflow.toolbox.bflow.IBflowElement;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Business Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link oepc.BusinessObject#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link oepc.BusinessObject#getMethods <em>Methods</em>}</li>
 * </ul>
 * </p>
 *
 * @see oepc.OepcPackage#getBusinessObject()
 * @model
 * @generated
 */
public interface BusinessObject extends Element, IBflowElement {
	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link oepc.BusinessAttribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see oepc.OepcPackage#getBusinessObject_Attributes()
	 * @model containment="true"
	 * @generated
	 */
	EList<BusinessAttribute> getAttributes();

	/**
	 * Returns the value of the '<em><b>Methods</b></em>' containment reference list.
	 * The list contents are of type {@link oepc.BusinessMethod}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Methods</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Methods</em>' containment reference list.
	 * @see oepc.OepcPackage#getBusinessObject_Methods()
	 * @model containment="true"
	 * @generated
	 */
	EList<BusinessMethod> getMethods();

} // BusinessObject

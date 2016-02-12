/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bflow.toolbox.epc;

import org.bflow.toolbox.bflow.Element;
import org.bflow.toolbox.bflow.IBflowElement;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.bflow.toolbox.epc.Function#getSubdiagram <em>Subdiagram</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.bflow.toolbox.epc.EpcPackage#getFunction()
 * @model
 * @generated
 */
public interface Function extends Element, IBflowElement, EpcNode {
	/**
	 * Returns the value of the '<em><b>Subdiagram</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subdiagram</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subdiagram</em>' attribute list.
	 * @see org.bflow.toolbox.epc.EpcPackage#getFunction_Subdiagram()
	 * @model
	 * @generated
	 */
	EList<String> getSubdiagram();

} // Function

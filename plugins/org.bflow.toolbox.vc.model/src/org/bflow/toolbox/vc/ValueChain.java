/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bflow.toolbox.vc;

import org.bflow.toolbox.bflow.Element;
import org.bflow.toolbox.bflow.IBflowElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Value Chain</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.bflow.toolbox.vc.ValueChain#getSubdiagram <em>Subdiagram</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.bflow.toolbox.vc.VcPackage#getValueChain()
 * @model
 * @generated
 */
public interface ValueChain extends Element, IBflowElement {
	/**
	 * Returns the value of the '<em><b>Subdiagram</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subdiagram</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subdiagram</em>' attribute.
	 * @see #setSubdiagram(String)
	 * @see org.bflow.toolbox.vc.VcPackage#getValueChain_Subdiagram()
	 * @model
	 * @generated
	 */
	String getSubdiagram();

	/**
	 * Sets the value of the '{@link org.bflow.toolbox.vc.ValueChain#getSubdiagram <em>Subdiagram</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subdiagram</em>' attribute.
	 * @see #getSubdiagram()
	 * @generated
	 */
	void setSubdiagram(String value);

} // ValueChain

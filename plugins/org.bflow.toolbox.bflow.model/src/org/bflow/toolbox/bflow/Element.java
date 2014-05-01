/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bflow.toolbox.bflow;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.bflow.toolbox.bflow.Element#getIn <em>In</em>}</li>
 *   <li>{@link org.bflow.toolbox.bflow.Element#getOut <em>Out</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.bflow.toolbox.bflow.BflowPackage#getElement()
 * @model abstract="true"
 * @generated
 */
public interface Element extends BflowSymbol {
	/**
	 * Returns the value of the '<em><b>In</b></em>' reference list.
	 * The list contents are of type {@link org.bflow.toolbox.bflow.Connection}.
	 * It is bidirectional and its opposite is '{@link org.bflow.toolbox.bflow.Connection#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In</em>' reference list.
	 * @see org.bflow.toolbox.bflow.BflowPackage#getElement_In()
	 * @see org.bflow.toolbox.bflow.Connection#getTo
	 * @model opposite="to"
	 * @generated
	 */
	EList<Connection> getIn();

	/**
	 * Returns the value of the '<em><b>Out</b></em>' reference list.
	 * The list contents are of type {@link org.bflow.toolbox.bflow.Connection}.
	 * It is bidirectional and its opposite is '{@link org.bflow.toolbox.bflow.Connection#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out</em>' reference list.
	 * @see org.bflow.toolbox.bflow.BflowPackage#getElement_Out()
	 * @see org.bflow.toolbox.bflow.Connection#getFrom
	 * @model opposite="from"
	 * @generated
	 */
	EList<Connection> getOut();

} // Element

/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bflow.toolbox.bflow;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.bflow.toolbox.bflow.Connection#getFrom <em>From</em>}</li>
 *   <li>{@link org.bflow.toolbox.bflow.Connection#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.bflow.toolbox.bflow.BflowPackage#getConnection()
 * @model abstract="true"
 * @generated
 */
public interface Connection extends BflowSymbol {
	/**
	 * Returns the value of the '<em><b>From</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.bflow.toolbox.bflow.Element#getOut <em>Out</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' reference.
	 * @see #setFrom(Element)
	 * @see org.bflow.toolbox.bflow.BflowPackage#getConnection_From()
	 * @see org.bflow.toolbox.bflow.Element#getOut
	 * @model opposite="out"
	 * @generated
	 */
	Element getFrom();

	/**
	 * Sets the value of the '{@link org.bflow.toolbox.bflow.Connection#getFrom <em>From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' reference.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(Element value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.bflow.toolbox.bflow.Element#getIn <em>In</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' reference.
	 * @see #setTo(Element)
	 * @see org.bflow.toolbox.bflow.BflowPackage#getConnection_To()
	 * @see org.bflow.toolbox.bflow.Element#getIn
	 * @model opposite="in"
	 * @generated
	 */
	Element getTo();

	/**
	 * Sets the value of the '{@link org.bflow.toolbox.bflow.Connection#getTo <em>To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' reference.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(Element value);

} // Connection

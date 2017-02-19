/**
 * <copyright>
 * </copyright>
 *
 * $Id: FLOAT.java 532 2007-09-03 23:04:02Z nernst $
 */
package q7dsl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>FLOAT</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link q7dsl.FLOAT#getDecimal <em>Decimal</em>}</li>
 *   <li>{@link q7dsl.FLOAT#getIntegral <em>Integral</em>}</li>
 * </ul>
 * </p>
 *
 * @see q7dsl.Q7dslPackage#getFLOAT()
 * @model
 * @generated
 */
public interface FLOAT extends EObject {
	/**
	 * Returns the value of the '<em><b>Decimal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Decimal</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Decimal</em>' attribute.
	 * @see #setDecimal(String)
	 * @see q7dsl.Q7dslPackage#getFLOAT_Decimal()
	 * @model
	 * @generated
	 */
	String getDecimal();

	/**
	 * Sets the value of the '{@link q7dsl.FLOAT#getDecimal <em>Decimal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Decimal</em>' attribute.
	 * @see #getDecimal()
	 * @generated
	 */
	void setDecimal(String value);

	/**
	 * Returns the value of the '<em><b>Integral</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Integral</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Integral</em>' attribute.
	 * @see #setIntegral(String)
	 * @see q7dsl.Q7dslPackage#getFLOAT_Integral()
	 * @model
	 * @generated
	 */
	String getIntegral();

	/**
	 * Sets the value of the '{@link q7dsl.FLOAT#getIntegral <em>Integral</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Integral</em>' attribute.
	 * @see #getIntegral()
	 * @generated
	 */
	void setIntegral(String value);

} // FLOAT
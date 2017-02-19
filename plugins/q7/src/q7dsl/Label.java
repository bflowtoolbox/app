/**
 * <copyright>
 * </copyright>
 *
 * $Id: Label.java 532 2007-09-03 23:04:02Z nernst $
 */
package q7dsl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Label</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link q7dsl.Label#getSat <em>Sat</em>}</li>
 *   <li>{@link q7dsl.Label#getLabel <em>Label</em>}</li>
 *   <li>{@link q7dsl.Label#getDen <em>Den</em>}</li>
 * </ul>
 * </p>
 *
 * @see q7dsl.Q7dslPackage#getLabel()
 * @model
 * @generated
 */
public interface Label extends EObject {
	/**
	 * Returns the value of the '<em><b>Sat</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sat</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sat</em>' containment reference.
	 * @see #setSat(FLOAT)
	 * @see q7dsl.Q7dslPackage#getLabel_Sat()
	 * @model containment="true"
	 * @generated
	 */
	FLOAT getSat();

	/**
	 * Sets the value of the '{@link q7dsl.Label#getSat <em>Sat</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sat</em>' containment reference.
	 * @see #getSat()
	 * @generated
	 */
	void setSat(FLOAT value);

	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * The literals are from the enumeration {@link q7dsl.LabelEnumerator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see q7dsl.LabelEnumerator
	 * @see #setLabel(LabelEnumerator)
	 * @see q7dsl.Q7dslPackage#getLabel_Label()
	 * @model
	 * @generated
	 */
	LabelEnumerator getLabel();

	/**
	 * Sets the value of the '{@link q7dsl.Label#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see q7dsl.LabelEnumerator
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(LabelEnumerator value);

	/**
	 * Returns the value of the '<em><b>Den</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Den</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Den</em>' containment reference.
	 * @see #setDen(FLOAT)
	 * @see q7dsl.Q7dslPackage#getLabel_Den()
	 * @model containment="true"
	 * @generated
	 */
	FLOAT getDen();

	/**
	 * Sets the value of the '{@link q7dsl.Label#getDen <em>Den</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Den</em>' containment reference.
	 * @see #getDen()
	 * @generated
	 */
	void setDen(FLOAT value);

} // Label
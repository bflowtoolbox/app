/**
 * <copyright>
 * </copyright>
 *
 * $Id: How.java 532 2007-09-03 23:04:02Z nernst $
 */
package q7dsl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>How</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link q7dsl.How#getAdvices <em>Advices</em>}</li>
 *   <li>{@link q7dsl.How#getEnrich <em>Enrich</em>}</li>
 *   <li>{@link q7dsl.How#getOp <em>Op</em>}</li>
 * </ul>
 * </p>
 *
 * @see q7dsl.Q7dslPackage#getHow()
 * @model
 * @generated
 */
public interface How extends EObject {
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
	 * @see q7dsl.Q7dslPackage#getHow_Advices()
	 * @model type="q7dsl.Advice" containment="true"
	 * @generated
	 */
	EList getAdvices();

	/**
	 * Returns the value of the '<em><b>Enrich</b></em>' attribute.
	 * The literals are from the enumeration {@link q7dsl.Enrichment}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enrich</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enrich</em>' attribute.
	 * @see q7dsl.Enrichment
	 * @see #setEnrich(Enrichment)
	 * @see q7dsl.Q7dslPackage#getHow_Enrich()
	 * @model
	 * @generated
	 */
	Enrichment getEnrich();

	/**
	 * Sets the value of the '{@link q7dsl.How#getEnrich <em>Enrich</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enrich</em>' attribute.
	 * @see q7dsl.Enrichment
	 * @see #getEnrich()
	 * @generated
	 */
	void setEnrich(Enrichment value);

	/**
	 * Returns the value of the '<em><b>Op</b></em>' attribute.
	 * The literals are from the enumeration {@link q7dsl.DecompositionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Op</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Op</em>' attribute.
	 * @see q7dsl.DecompositionType
	 * @see #setOp(DecompositionType)
	 * @see q7dsl.Q7dslPackage#getHow_Op()
	 * @model
	 * @generated
	 */
	DecompositionType getOp();

	/**
	 * Sets the value of the '{@link q7dsl.How#getOp <em>Op</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Op</em>' attribute.
	 * @see q7dsl.DecompositionType
	 * @see #getOp()
	 * @generated
	 */
	void setOp(DecompositionType value);

} // How
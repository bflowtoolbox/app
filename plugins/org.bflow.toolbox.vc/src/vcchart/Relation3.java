/**
 */
package vcchart;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relation3</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link vcchart.Relation3#getSource <em>Source</em>}</li>
 *   <li>{@link vcchart.Relation3#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see vcchart.VcchartPackage#getRelation3()
 * @model annotation="gmf.link source='source' target='target' color='0,0,0' source.constraint='self <> oppositeEnd' target.constraint='(self.oclIsKindOf(All_Rel_1) and oppositeEnd.oclIsKindOf(RelationsObject)) or (self.oclIsKindOf(RelationsObject) and oppositeEnd.oclIsKindOf(All_Rel_1))'"
 * @generated
 */
public interface Relation3 extends EObject {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(All_Rel_3)
	 * @see vcchart.VcchartPackage#getRelation3_Source()
	 * @model
	 * @generated
	 */
	All_Rel_3 getSource();

	/**
	 * Sets the value of the '{@link vcchart.Relation3#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(All_Rel_3 value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(All_Rel_3)
	 * @see vcchart.VcchartPackage#getRelation3_Target()
	 * @model
	 * @generated
	 */
	All_Rel_3 getTarget();

	/**
	 * Sets the value of the '{@link vcchart.Relation3#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(All_Rel_3 value);

} // Relation3

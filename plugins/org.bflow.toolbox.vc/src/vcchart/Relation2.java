/**
 */
package vcchart;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relation2</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link vcchart.Relation2#getSource <em>Source</em>}</li>
 *   <li>{@link vcchart.Relation2#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see vcchart.VcchartPackage#getRelation2()
 * @model annotation="gmf.link source='source' target='target' color='0,0,0' target.decoration='arrow' source.constraint='self.oclIsKindOf(All_Rel_1) and self <> oppositeEnd' target.constraint='(self.oclIsTypeOf(Activity1) and oppositeEnd.oclIsTypeOf(Activity1)) or (self.oclIsTypeOf(Activity2) and oppositeEnd.oclIsTypeOf(Activity2)) or (self.oclIsTypeOf(Objective) and oppositeEnd.oclIsTypeOf(Objective)) or (self.oclIsTypeOf(Product) and oppositeEnd.oclIsTypeOf(Product))'"
 * @generated
 */
public interface Relation2 extends EObject {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(All_Rel_1)
	 * @see vcchart.VcchartPackage#getRelation2_Source()
	 * @model
	 * @generated
	 */
	All_Rel_1 getSource();

	/**
	 * Sets the value of the '{@link vcchart.Relation2#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(All_Rel_1 value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(All_Rel_1)
	 * @see vcchart.VcchartPackage#getRelation2_Target()
	 * @model
	 * @generated
	 */
	All_Rel_1 getTarget();

	/**
	 * Sets the value of the '{@link vcchart.Relation2#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(All_Rel_1 value);

} // Relation2

/**
 */
package orgchart;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relation2</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgchart.Relation2#getSource <em>Source</em>}</li>
 *   <li>{@link orgchart.Relation2#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgchart.OrgchartPackage#getRelation2()
 * @model annotation="gmf.link source='source' target='target' color='0,0,0' source.constraint='self.oclIsKindOf(All_Rel_2) and self <> oppositeEnd' target.constraint='(self.oclIsTypeOf(Position) and oppositeEnd.oclIsKindOf(Person)) or (self.oclIsTypeOf(PersonType) and (oppositeEnd.oclIsTypeOf(PersonType) or oppositeEnd.oclIsKindOf(Person))) or (self.oclIsKindOf(Person) and (oppositeEnd.oclIsTypeOf(PersonType) or oppositeEnd.oclIsTypeOf(Position) or oppositeEnd.oclIsTypeOf(Group))) or (self.oclIsKindOf(Group) and (oppositeEnd.oclIsKindOf(Person)))'"
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
	 * @see #setSource(All_Rel_2)
	 * @see orgchart.OrgchartPackage#getRelation2_Source()
	 * @model
	 * @generated
	 */
	All_Rel_2 getSource();

	/**
	 * Sets the value of the '{@link orgchart.Relation2#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(All_Rel_2 value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(All_Rel_2)
	 * @see orgchart.OrgchartPackage#getRelation2_Target()
	 * @model
	 * @generated
	 */
	All_Rel_2 getTarget();

	/**
	 * Sets the value of the '{@link orgchart.Relation2#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(All_Rel_2 value);

} // Relation2

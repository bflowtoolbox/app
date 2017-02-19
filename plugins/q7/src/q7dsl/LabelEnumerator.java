/**
 * <copyright>
 * </copyright>
 *
 * $Id: LabelEnumerator.java 532 2007-09-03 23:04:02Z nernst $
 */
package q7dsl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Label Enumerator</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see q7dsl.Q7dslPackage#getLabelEnumerator()
 * @model
 * @generated
 */
public final class LabelEnumerator extends AbstractEnumerator {
	/**
	 * The '<em><b>FULLY SATISFIED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FULLY SATISFIED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FULLY_SATISFIED_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FULLY_SATISFIED = 1;

	/**
	 * The '<em><b>PARTIALLY SATISFIED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PARTIALLY SATISFIED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PARTIALLY_SATISFIED_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PARTIALLY_SATISFIED = 2;

	/**
	 * The '<em><b>FULLY DENIED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FULLY DENIED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FULLY_DENIED_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FULLY_DENIED = 3;

	/**
	 * The '<em><b>PARTIALLY DENIED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PARTIALLY DENIED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PARTIALLY_DENIED_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PARTIALLY_DENIED = 4;

	/**
	 * The '<em><b>CONFLICT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CONFLICT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONFLICT_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CONFLICT = 5;

	/**
	 * The '<em><b>UNKNOWN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UNKNOWN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNKNOWN_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int UNKNOWN = 6;

	/**
	 * The '<em><b>FULLY SATISFIED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FULLY_SATISFIED
	 * @generated
	 * @ordered
	 */
	public static final LabelEnumerator FULLY_SATISFIED_LITERAL = new LabelEnumerator(FULLY_SATISFIED, "FULLY_SATISFIED", "FULLY_SATISFIED");

	/**
	 * The '<em><b>PARTIALLY SATISFIED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PARTIALLY_SATISFIED
	 * @generated
	 * @ordered
	 */
	public static final LabelEnumerator PARTIALLY_SATISFIED_LITERAL = new LabelEnumerator(PARTIALLY_SATISFIED, "PARTIALLY_SATISFIED", "PARTIALLY_SATISFIED");

	/**
	 * The '<em><b>FULLY DENIED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FULLY_DENIED
	 * @generated
	 * @ordered
	 */
	public static final LabelEnumerator FULLY_DENIED_LITERAL = new LabelEnumerator(FULLY_DENIED, "FULLY_DENIED", "FULLY_DENIED");

	/**
	 * The '<em><b>PARTIALLY DENIED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PARTIALLY_DENIED
	 * @generated
	 * @ordered
	 */
	public static final LabelEnumerator PARTIALLY_DENIED_LITERAL = new LabelEnumerator(PARTIALLY_DENIED, "PARTIALLY_DENIED", "PARTIALLY_DENIED");

	/**
	 * The '<em><b>CONFLICT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONFLICT
	 * @generated
	 * @ordered
	 */
	public static final LabelEnumerator CONFLICT_LITERAL = new LabelEnumerator(CONFLICT, "CONFLICT", "CONFLICT");

	/**
	 * The '<em><b>UNKNOWN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNKNOWN
	 * @generated
	 * @ordered
	 */
	public static final LabelEnumerator UNKNOWN_LITERAL = new LabelEnumerator(UNKNOWN, "UNKNOWN", "UNKNOWN");

	/**
	 * An array of all the '<em><b>Label Enumerator</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final LabelEnumerator[] VALUES_ARRAY =
		new LabelEnumerator[] {
			FULLY_SATISFIED_LITERAL,
			PARTIALLY_SATISFIED_LITERAL,
			FULLY_DENIED_LITERAL,
			PARTIALLY_DENIED_LITERAL,
			CONFLICT_LITERAL,
			UNKNOWN_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Label Enumerator</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Label Enumerator</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LabelEnumerator get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LabelEnumerator result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Label Enumerator</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LabelEnumerator getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LabelEnumerator result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Label Enumerator</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LabelEnumerator get(int value) {
		switch (value) {
			case FULLY_SATISFIED: return FULLY_SATISFIED_LITERAL;
			case PARTIALLY_SATISFIED: return PARTIALLY_SATISFIED_LITERAL;
			case FULLY_DENIED: return FULLY_DENIED_LITERAL;
			case PARTIALLY_DENIED: return PARTIALLY_DENIED_LITERAL;
			case CONFLICT: return CONFLICT_LITERAL;
			case UNKNOWN: return UNKNOWN_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private LabelEnumerator(int value, String name, String literal) {
		super(value, name, literal);
	}

} //LabelEnumerator

/**
 * <copyright>
 * </copyright>
 *
 * $Id: DecompositionType.java 532 2007-09-03 23:04:02Z nernst $
 */
package q7dsl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Decomposition Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see q7dsl.Q7dslPackage#getDecompositionType()
 * @model
 * @generated
 */
public final class DecompositionType extends AbstractEnumerator {
	/**
	 * The '<em><b>AND</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>AND</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #AND_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int AND = 1;

	/**
	 * The '<em><b>AND1</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>AND1</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #AND1_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int AND1 = 2;

	/**
	 * The '<em><b>OR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>OR</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OR_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int OR = 3;

	/**
	 * The '<em><b>OR1</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>OR1</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OR1_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int OR1 = 4;

	/**
	 * The '<em><b>AND</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AND
	 * @generated
	 * @ordered
	 */
	public static final DecompositionType AND_LITERAL = new DecompositionType(AND, "AND", "AND");

	/**
	 * The '<em><b>AND1</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AND1
	 * @generated
	 * @ordered
	 */
	public static final DecompositionType AND1_LITERAL = new DecompositionType(AND1, "AND1", "AND1");

	/**
	 * The '<em><b>OR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OR
	 * @generated
	 * @ordered
	 */
	public static final DecompositionType OR_LITERAL = new DecompositionType(OR, "OR", "OR");

	/**
	 * The '<em><b>OR1</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OR1
	 * @generated
	 * @ordered
	 */
	public static final DecompositionType OR1_LITERAL = new DecompositionType(OR1, "OR1", "OR1");

	/**
	 * An array of all the '<em><b>Decomposition Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final DecompositionType[] VALUES_ARRAY =
		new DecompositionType[] {
			AND_LITERAL,
			AND1_LITERAL,
			OR_LITERAL,
			OR1_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Decomposition Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Decomposition Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DecompositionType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DecompositionType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Decomposition Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DecompositionType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DecompositionType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Decomposition Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DecompositionType get(int value) {
		switch (value) {
			case AND: return AND_LITERAL;
			case AND1: return AND1_LITERAL;
			case OR: return OR_LITERAL;
			case OR1: return OR1_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private DecompositionType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //DecompositionType

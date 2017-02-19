/**
 * <copyright>
 * </copyright>
 *
 * $Id: Op.java 532 2007-09-03 23:04:02Z nernst $
 */
package q7dsl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Op</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see q7dsl.Q7dslPackage#getOp()
 * @model
 * @generated
 */
public final class Op extends AbstractEnumerator {
	/**
	 * The '<em><b>HELP</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>HELP</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #HELP_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int HELP = 1;

	/**
	 * The '<em><b>HELP2</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>HELP2</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #HELP2_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int HELP2 = 2;

	/**
	 * The '<em><b>HURT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>HURT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #HURT_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int HURT = 3;

	/**
	 * The '<em><b>HURT2</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>HURT2</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #HURT2_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int HURT2 = 4;

	/**
	 * The '<em><b>MAKE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MAKE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MAKE_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int MAKE = 5;

	/**
	 * The '<em><b>MAKE2</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MAKE2</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MAKE2_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int MAKE2 = 6;

	/**
	 * The '<em><b>BREAK</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BREAK</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BREAK_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int BREAK = 7;

	/**
	 * The '<em><b>BREAK2</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BREAK2</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BREAK2_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int BREAK2 = 8;

	/**
	 * The '<em><b>HELP</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HELP
	 * @generated
	 * @ordered
	 */
	public static final Op HELP_LITERAL = new Op(HELP, "HELP", "HELP");

	/**
	 * The '<em><b>HELP2</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HELP2
	 * @generated
	 * @ordered
	 */
	public static final Op HELP2_LITERAL = new Op(HELP2, "HELP2", "HELP2");

	/**
	 * The '<em><b>HURT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HURT
	 * @generated
	 * @ordered
	 */
	public static final Op HURT_LITERAL = new Op(HURT, "HURT", "HURT");

	/**
	 * The '<em><b>HURT2</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HURT2
	 * @generated
	 * @ordered
	 */
	public static final Op HURT2_LITERAL = new Op(HURT2, "HURT2", "HURT2");

	/**
	 * The '<em><b>MAKE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MAKE
	 * @generated
	 * @ordered
	 */
	public static final Op MAKE_LITERAL = new Op(MAKE, "MAKE", "MAKE");

	/**
	 * The '<em><b>MAKE2</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MAKE2
	 * @generated
	 * @ordered
	 */
	public static final Op MAKE2_LITERAL = new Op(MAKE2, "MAKE2", "MAKE2");

	/**
	 * The '<em><b>BREAK</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BREAK
	 * @generated
	 * @ordered
	 */
	public static final Op BREAK_LITERAL = new Op(BREAK, "BREAK", "BREAK");

	/**
	 * The '<em><b>BREAK2</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BREAK2
	 * @generated
	 * @ordered
	 */
	public static final Op BREAK2_LITERAL = new Op(BREAK2, "BREAK2", "BREAK2");

	/**
	 * An array of all the '<em><b>Op</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final Op[] VALUES_ARRAY =
		new Op[] {
			HELP_LITERAL,
			HELP2_LITERAL,
			HURT_LITERAL,
			HURT2_LITERAL,
			MAKE_LITERAL,
			MAKE2_LITERAL,
			BREAK_LITERAL,
			BREAK2_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Op</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Op</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Op get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Op result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Op</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Op getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Op result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Op</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Op get(int value) {
		switch (value) {
			case HELP: return HELP_LITERAL;
			case HELP2: return HELP2_LITERAL;
			case HURT: return HURT_LITERAL;
			case HURT2: return HURT2_LITERAL;
			case MAKE: return MAKE_LITERAL;
			case MAKE2: return MAKE2_LITERAL;
			case BREAK: return BREAK_LITERAL;
			case BREAK2: return BREAK2_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private Op(int value, String name, String literal) {
		super(value, name, literal);
	}

} //Op

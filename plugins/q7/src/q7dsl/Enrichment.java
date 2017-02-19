/**
 * <copyright>
 * </copyright>
 *
 * $Id: Enrichment.java 532 2007-09-03 23:04:02Z nernst $
 */
package q7dsl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Enrichment</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see q7dsl.Q7dslPackage#getEnrichment()
 * @model
 * @generated
 */
public final class Enrichment extends AbstractEnumerator {
	/**
	 * The '<em><b>SEQUENTIAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SEQUENTIAL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SEQUENTIAL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SEQUENTIAL = 1;

	/**
	 * The '<em><b>SEQUENTIAL1</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SEQUENTIAL1</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SEQUENTIAL1_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SEQUENTIAL1 = 2;

	/**
	 * The '<em><b>PARALLEL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PARALLEL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PARALLEL_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PARALLEL = 3;

	/**
	 * The '<em><b>PARALLEL1</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PARALLEL1</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PARALLEL1_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PARALLEL1 = 4;

	/**
	 * The '<em><b>XOR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>XOR</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #XOR_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int XOR = 5;

	/**
	 * The '<em><b>XOR1</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>XOR1</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #XOR1_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int XOR1 = 6;

	/**
	 * The '<em><b>SEQUENTIAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEQUENTIAL
	 * @generated
	 * @ordered
	 */
	public static final Enrichment SEQUENTIAL_LITERAL = new Enrichment(SEQUENTIAL, "SEQUENTIAL", "SEQUENTIAL");

	/**
	 * The '<em><b>SEQUENTIAL1</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEQUENTIAL1
	 * @generated
	 * @ordered
	 */
	public static final Enrichment SEQUENTIAL1_LITERAL = new Enrichment(SEQUENTIAL1, "SEQUENTIAL1", "SEQUENTIAL1");

	/**
	 * The '<em><b>PARALLEL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PARALLEL
	 * @generated
	 * @ordered
	 */
	public static final Enrichment PARALLEL_LITERAL = new Enrichment(PARALLEL, "PARALLEL", "PARALLEL");

	/**
	 * The '<em><b>PARALLEL1</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PARALLEL1
	 * @generated
	 * @ordered
	 */
	public static final Enrichment PARALLEL1_LITERAL = new Enrichment(PARALLEL1, "PARALLEL1", "PARALLEL1");

	/**
	 * The '<em><b>XOR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #XOR
	 * @generated
	 * @ordered
	 */
	public static final Enrichment XOR_LITERAL = new Enrichment(XOR, "XOR", "XOR");

	/**
	 * The '<em><b>XOR1</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #XOR1
	 * @generated
	 * @ordered
	 */
	public static final Enrichment XOR1_LITERAL = new Enrichment(XOR1, "XOR1", "XOR1");

	/**
	 * An array of all the '<em><b>Enrichment</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final Enrichment[] VALUES_ARRAY =
		new Enrichment[] {
			SEQUENTIAL_LITERAL,
			SEQUENTIAL1_LITERAL,
			PARALLEL_LITERAL,
			PARALLEL1_LITERAL,
			XOR_LITERAL,
			XOR1_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Enrichment</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Enrichment</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Enrichment get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Enrichment result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Enrichment</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Enrichment getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Enrichment result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Enrichment</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Enrichment get(int value) {
		switch (value) {
			case SEQUENTIAL: return SEQUENTIAL_LITERAL;
			case SEQUENTIAL1: return SEQUENTIAL1_LITERAL;
			case PARALLEL: return PARALLEL_LITERAL;
			case PARALLEL1: return PARALLEL1_LITERAL;
			case XOR: return XOR_LITERAL;
			case XOR1: return XOR1_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private Enrichment(int value, String name, String literal) {
		super(value, name, literal);
	}

} //Enrichment

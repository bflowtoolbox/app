/**
 * Copyright 2001-2008 University of Toronto
 *
 * $Id$
 */
package edu.toronto.cs.openome_model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Evaluation Label</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see edu.toronto.cs.openome_model.openome_modelPackage#getEvaluationLabel()
 * @model
 * @generated
 */
public enum EvaluationLabel implements Enumerator {
	/**
	 * The '<em><b>None</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NONE_VALUE
	 * @generated
	 * @ordered
	 */
	NONE(0, "None", "None"), /**
	 * The '<em><b>Unknown</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNKNOWN_VALUE
	 * @generated
	 * @ordered
	 */
	UNKNOWN(0, "Unknown", "Unknown"), /**
	 * The '<em><b>Satisfied</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SATISFIED_VALUE
	 * @generated
	 * @ordered
	 */
	SATISFIED(0, "Satisfied", "Satisfied"), /**
	 * The '<em><b>Partially Satisfied</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PARTIALLY_SATISFIED_VALUE
	 * @generated
	 * @ordered
	 */
	PARTIALLY_SATISFIED(0, "PartiallySatisfied", "PartiallySatisfied"), /**
	 * The '<em><b>Denied</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DENIED_VALUE
	 * @generated
	 * @ordered
	 */
	DENIED(0, "Denied", "Denied"), /**
	 * The '<em><b>Partially Denied</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PARTIALLY_DENIED_VALUE
	 * @generated
	 * @ordered
	 */
	PARTIALLY_DENIED(0, "PartiallyDenied", "PartiallyDenied"), /**
	 * The '<em><b>Conflict</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONFLICT_VALUE
	 * @generated
	 * @ordered
	 */
	CONFLICT(0, "Conflict", "Conflict");

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright 2001-2008 University of Toronto";

	/**
	 * The '<em><b>None</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>None</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NONE
	 * @model name="None"
	 * @generated
	 * @ordered
	 */
	public static final int NONE_VALUE = 0;

	/**
	 * The '<em><b>Unknown</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Unknown</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNKNOWN
	 * @model name="Unknown"
	 * @generated
	 * @ordered
	 */
	public static final int UNKNOWN_VALUE = 0;

	/**
	 * The '<em><b>Satisfied</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Satisfied</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SATISFIED
	 * @model name="Satisfied"
	 * @generated
	 * @ordered
	 */
	public static final int SATISFIED_VALUE = 0;

	/**
	 * The '<em><b>Partially Satisfied</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Partially Satisfied</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PARTIALLY_SATISFIED
	 * @model name="PartiallySatisfied"
	 * @generated
	 * @ordered
	 */
	public static final int PARTIALLY_SATISFIED_VALUE = 0;

	/**
	 * The '<em><b>Denied</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Denied</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DENIED
	 * @model name="Denied"
	 * @generated
	 * @ordered
	 */
	public static final int DENIED_VALUE = 0;

	/**
	 * The '<em><b>Partially Denied</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Partially Denied</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PARTIALLY_DENIED
	 * @model name="PartiallyDenied"
	 * @generated
	 * @ordered
	 */
	public static final int PARTIALLY_DENIED_VALUE = 0;

	/**
	 * The '<em><b>Conflict</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Conflict</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONFLICT
	 * @model name="Conflict"
	 * @generated
	 * @ordered
	 */
	public static final int CONFLICT_VALUE = 0;

	/**
	 * An array of all the '<em><b>Evaluation Label</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final EvaluationLabel[] VALUES_ARRAY =
		new EvaluationLabel[] {
			NONE,
			UNKNOWN,
			SATISFIED,
			PARTIALLY_SATISFIED,
			DENIED,
			PARTIALLY_DENIED,
			CONFLICT,
		};

	/**
	 * A public read-only list of all the '<em><b>Evaluation Label</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<EvaluationLabel> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Evaluation Label</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EvaluationLabel get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EvaluationLabel result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Evaluation Label</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EvaluationLabel getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EvaluationLabel result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Evaluation Label</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EvaluationLabel get(int value) {
		switch (value) {
			case NONE_VALUE: return NONE;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	private Intention intention ;
	

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EvaluationLabel(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Intention getIntention() {
	  return intention;
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setIntention(Intention i) {
	  this.intention = i;
	}
	
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * Uses the following order
	 * Sat > WeaklySat > Conflict > Unknown > WeaklyDenied > Denied > None
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Boolean isGreaterThan(EvaluationLabel l) {
		if (this == EvaluationLabel.NONE)
			return false;
		
		if (this == EvaluationLabel.SATISFIED) {
			if (l == EvaluationLabel.SATISFIED)
				return false;
			else
				return true;
		}
		if (this == EvaluationLabel.PARTIALLY_SATISFIED) {
			if (l == EvaluationLabel.SATISFIED || l == EvaluationLabel.PARTIALLY_SATISFIED)
				return false;
			else
				return true;
		}
		
		if (this == EvaluationLabel.CONFLICT) {
			if (l == EvaluationLabel.SATISFIED || l == EvaluationLabel.PARTIALLY_SATISFIED 
					|| l == EvaluationLabel.CONFLICT)
				return false;
			else
				return true;
		}
		
		if (this == EvaluationLabel.UNKNOWN) {
			if (l == EvaluationLabel.DENIED || l == EvaluationLabel.PARTIALLY_DENIED)
				return true;
			else
				return false;
		}
		
		if (this == EvaluationLabel.PARTIALLY_DENIED) {
			if (l == EvaluationLabel.DENIED)
				return true;
			else
				return false;
		}
		
		if (this == EvaluationLabel.DENIED) {
			if (l == EvaluationLabel.NONE)
				return true;
			else
				return false;
		}
		
		return false;				
	}
	
	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Boolean isLessThan(EvaluationLabel l) {
		return !this.isGreaterThan(l);
	}
	
} //EvaluationLabel

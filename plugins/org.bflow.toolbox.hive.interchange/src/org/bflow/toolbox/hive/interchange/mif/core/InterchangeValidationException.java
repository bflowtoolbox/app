package org.bflow.toolbox.hive.interchange.mif.core;

/**
 * Defines an exception that can be occur while validating an interchange export descriptor.
 * 
 * @author Arian Storch
 * @since 05/10/12
 */
public class InterchangeValidationException extends Exception {

	/**
	 * Serial version id.
	 */
	private static final long serialVersionUID = 2784276626214448954L;

	/**
	 * Instantiates a new interchange validation exception.
	 */
	public InterchangeValidationException() {
	}

	/**
	 * Instantiates a new interchange validation exception.
	 * 
	 * @param message
	 *            the message
	 */
	public InterchangeValidationException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new interchange validation exception.
	 * 
	 * @param exception
	 *            the exception
	 */
	public InterchangeValidationException(Throwable exception) {
		super(exception);
	}

	/**
	 * Instantiates a new interchange validation exception.
	 * 
	 * @param message
	 *            the message
	 * @param exception
	 *            the exception
	 */
	public InterchangeValidationException(String message, Throwable exception) {
		super(message, exception);
	}

}

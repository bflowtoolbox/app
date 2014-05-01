package org.bflow.toolbox.hive.interchange.mif.core;


/**
 * Defines an exception that can be occur while processing an interchange.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 03/10/10
 */
public class InterchangeProcessingException extends Exception {

	/** Serial Version UID. */
	private static final long serialVersionUID = -3028039221924754485L;

	/**
	 * Instantiates a new interchange processing exception.
	 */
	public InterchangeProcessingException() {
	}

	/**
	 * Instantiates a new interchange processing exception.
	 * 
	 * @param message
	 *            the message
	 */
	public InterchangeProcessingException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new interchange processing exception.
	 * 
	 * @param exception
	 *            the exception
	 */
	public InterchangeProcessingException(Throwable exception) {
		super(exception);
	}

	/**
	 * Instantiates a new interchange processing exception.
	 * 
	 * @param message
	 *            the message
	 * @param exception
	 *            the exception
	 */
	public InterchangeProcessingException(String message, Throwable exception) {
		super(message, exception);
	}

}

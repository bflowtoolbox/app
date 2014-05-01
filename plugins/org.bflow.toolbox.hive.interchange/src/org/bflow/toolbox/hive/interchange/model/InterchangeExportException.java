package org.bflow.toolbox.hive.interchange.model;

/**
 * Defines a specific exception that can occur when handling with export scripts.
 * 
 * @author Arian Storch
 * @since 27/07/12
 * 
 */
public class InterchangeExportException extends Exception {

	/**
	 * Serial version id
	 */
	private static final long serialVersionUID = -2554256835760920008L;

	/**
	 * Constructor.
	 * @param message exception message text
	 * @param throwable instance of throwable
	 */
	public InterchangeExportException(String message, Throwable throwable) {
		super(message, throwable);
	}

	/**
	 * Constructor.
	 * @param message exception message text
	 */
	public InterchangeExportException(String message) {
		super(message);
	}

	/**
	 * Constructor.
	 * @param throwable instance of throwable
	 */
	public InterchangeExportException(Throwable throwable) {
		super(throwable);
	}

}

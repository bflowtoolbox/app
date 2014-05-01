package org.bflow.toolbox.hive.interchange.model;

/**
 * Defines a specific exception that occur when handling with import scripts.
 * 
 * @author Arian Storch
 * @since 27/07/12
 * 
 */
public class InterchangeImportException extends Exception {

	/**
	 * Serial version id
	 */
	private static final long serialVersionUID = -7111367064117217996L;

	/**
	 * Constructor.
	 * @param message exception message text
	 */
	public InterchangeImportException(String message) {
		super(message);
	}

	/**
	 * Constructor.
	 * @param cause instance of throwable
	 */
	public InterchangeImportException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor.
	 * @param message exception message text
	 * @param cause instance of throwable
	 */
	public InterchangeImportException(String message, Throwable cause) {
		super(message, cause);
	}

}

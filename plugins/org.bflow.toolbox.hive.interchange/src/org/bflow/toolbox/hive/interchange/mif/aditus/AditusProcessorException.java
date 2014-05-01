package org.bflow.toolbox.hive.interchange.mif.aditus;

/**
 * Defines an exception that can be occur while processing an aditus file.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 12/07/13
 */
public class AditusProcessorException extends Exception {

	/** Serial version UID. */
	private static final long serialVersionUID = -1052322069501147476L;

	/**
	 * Instantiates a new aditus processor exception.
	 *
	 * @param message the message
	 * @param ex the ex
	 */
	public AditusProcessorException(String message, Throwable ex) {
		super(message, ex);
	}

	/**
	 * Instantiates a new aditus processor exception.
	 *
	 * @param ex the ex
	 */
	public AditusProcessorException(String ex) {
		super(ex);
	}

	/**
	 * Instantiates a new aditus processor exception.
	 *
	 * @param ex the ex
	 */
	public AditusProcessorException(Throwable ex) {
		super(ex);
	}
}

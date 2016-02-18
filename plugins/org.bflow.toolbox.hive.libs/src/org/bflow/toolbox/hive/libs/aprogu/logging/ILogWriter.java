package org.bflow.toolbox.hive.libs.aprogu.logging;

/**
 * Defines a log writer.
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2016-02-18
 *
 */
public interface ILogWriter {
	
	/**
	 * Writes the given message at error level to the log.
	 * 
	 * @param message
	 *            Message to write
	 */
	void error(String message);
	
	/**
	 * Writes the a message using the given message format and arguments at
	 * error level to the log.
	 * 
	 * @param messageFormat
	 *            Format of the message
	 * @param args
	 *            Format arguments
	 */
	void errorFormat(String messageFormat, Object...args);

}

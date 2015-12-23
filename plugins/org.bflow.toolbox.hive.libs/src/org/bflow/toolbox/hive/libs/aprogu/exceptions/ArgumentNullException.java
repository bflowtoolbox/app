package org.bflow.toolbox.hive.libs.aprogu.exceptions;

public class ArgumentNullException extends RuntimeException {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -5119710539559968250L;
	
	public ArgumentNullException(String argumentName) {
		super(buildExceptionMessage(argumentName));
	}

	static String buildExceptionMessage(String argumentName) {
		String exceptionMessage = String.format("Given argument '%s' is null!", argumentName);
		return exceptionMessage;
	}
}

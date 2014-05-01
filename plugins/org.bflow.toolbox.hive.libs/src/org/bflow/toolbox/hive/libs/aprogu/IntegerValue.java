package org.bflow.toolbox.hive.libs.aprogu;

/**
 * Represents an integer object that has additional methods.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 07/07/13
 */
public class IntegerValue {
	
	/** Value */
	public int Value = 0;
	
	/**
	 * Private constructor.
	 */
	private IntegerValue() {
	}
	
	/**
	 * Creates an instance.
	 * @return New instance
	 */
	public static IntegerValue create() {
		return new IntegerValue();
	}
	
	/**
	 * Tries to parse the given string. If it succeeds the value will be 
	 * set within the given instance. 
	 * @param str String that will be parsed
	 * @param result Instance that has been set to the value
	 * @return True if the parsing has been succeeded
	 */
	public static boolean tryParse(String str, IntegerValue result) {
		if(result == null)
			throw new NullPointerException();
		
		try {
			result.Value = Integer.parseInt(str);
			return true;
		} catch(NumberFormatException ex) {
			return false;
		}
	}

	@Override
	public String toString() {
		return String.format("%d", Value);
	}
}

package org.bflow.toolbox.hive.attributefilter;

/**
 * A FilterEntry for attributes has a attribute key, value and a operator as a
 * String. A Operator could be "<", "=", "\u2260", "\u2264", "\u2265" (utf-8
 * signs for !=, >= and <=) or ">". Also it holds a boolean if the filter should
 * be active or not.
 * 
 * @author Felix Hoess <fhoess@users.sf.net>
 * @since 02.06.2015
 *
 */
public class FilterEntry{
	private String attributeName; private String operator; private String value; private boolean active;
	public static final int ColumnATTRIBUTE_NAME = 0;
	public static final int ColumnOPERATOR = 1;
	public static final int ColumnVALUE = 2;
	public static final int ColumnACTIVE = 3;
	public static final char SEPARATOR = ',';
	public static final int COLUMN_AMOUNT = 4;
	
	public FilterEntry(String attributeName, String operator, String value,
			boolean active) {
		this.attributeName = attributeName;
		this.value = value;
		this.operator = operator;
		this.active = active;
	}

	/**
	 * returns a String[] of the entry. Uses the given separator symbol as a
	 * split symbol (use the {@link #SEPARATOR} constant)
	 * 
	 * @param separator
	 * @return
	 */
	public String[] getEntrySeparated(char separator) {
		return (getAttributeName() + separator + getOperator() + separator + getValue()
				+ separator + isActive()).split(String.valueOf(separator));

	}

	public String getAttributeName() {
		return attributeName;
	}
	public String getOperator() {
		return operator;
	}
	public String getValue() {
		return value;
	}
	public boolean isActive() {
		return active;
	}
	
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	public boolean equals(Object entry){
		if (entry instanceof FilterEntry){
			FilterEntry input = (FilterEntry)entry;
			return input.getAttributeName().equals(attributeName) && input.getOperator().equals(operator) && input.getValue().equals(value);
		}
		return false;
		
	}
	
}

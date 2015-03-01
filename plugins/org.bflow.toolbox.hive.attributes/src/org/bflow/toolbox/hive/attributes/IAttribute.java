package org.bflow.toolbox.hive.attributes;

/**
 * Defines an attribute interface for handling attributes within the protocol.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 14.06.10
 *
 */
public interface IAttribute {
	/**
	 * Returns true if this attribute shall affect other attributes of the same type. 
	 * @return true or false
	 */
	public boolean isLoner();
	
	/**
	 * Returns the element id associated with this attribute.
	 * @return element id associated with this attribute
	 */
	public String getElementID();
	
	/**
	 * Returns the name of the attribute.
	 * @return name of the attribute
	 */
	public String getName();
	
	/**
	 * Returns the value of the attribute.
	 * @return value of the attribute
	 */
	public String getValue();
}

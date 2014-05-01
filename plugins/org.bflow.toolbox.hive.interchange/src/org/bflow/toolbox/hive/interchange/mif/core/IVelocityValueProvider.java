package org.bflow.toolbox.hive.interchange.mif.core;

/**
 * Defines an interface for model elements which can provide additional values
 * to velocity.
 * 
 * @author Arian Storch
 * @since 02/05/13
 *
 */
public interface IVelocityValueProvider {
	
	/**
	 * Returns the value for the given property or null.
	 * 
	 * @param propertyName Name of the property
	 * @return Value for the given property name or null
	 */
	public Object get(String propertyName);

}

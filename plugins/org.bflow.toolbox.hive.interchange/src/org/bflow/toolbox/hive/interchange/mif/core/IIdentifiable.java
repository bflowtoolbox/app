package org.bflow.toolbox.hive.interchange.mif.core;

/**
 * Defines an interface for model elements which own properties that make them identifiable.
 * 
 * @author Arian Storch
 * @since 08/10/12
 */
public interface IIdentifiable {

	/**
	 * Returns the id.
	 * 
	 * @return the id
	 */
	public String getId();
	
	/**
	 * Returns the type.
	 * 
	 * @return the type
	 */
	public Object getType();
	
}

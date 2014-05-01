package org.bflow.toolbox.hive.interchange.mif.core;

import java.util.Map;


/**
 * Defines an interface for a model element which contains attributes.
 * 
 * @author Arian Storch
 * @since 01/10/12
 */
public interface IAttributeContainer {
	
	/**
	 * Returns the attributes.
	 * 
	 * @return the attributes
	 */
	public Map<String, String> getAttributes();

}

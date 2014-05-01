package org.bflow.toolbox.hive.interchange.mif.core;

import java.util.Map;

/**
 * Defines the interface of a Script Descriptor.
 * 
 * @author Arian Storch
 * @since 03/10/12
 */
public interface IScriptDescriptor {
	
	/**
	 * Returns the path of the script file.
	 * 
	 * @return the path
	 */
	public String getPath();
	
	/**
	 * Returns the parameters that shall be applied to the script file.
	 * 
	 * @return the parameters
	 */
	public Map<String, Object> getParameters();

}

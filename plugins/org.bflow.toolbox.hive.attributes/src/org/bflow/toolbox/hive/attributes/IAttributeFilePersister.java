package org.bflow.toolbox.hive.attributes;

import java.util.HashMap;

/**
 * Defines an object that handles the persistence operations instead of the
 * attribute file.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 11.04.2015
 * 
 */
public interface IAttributeFilePersister {

	/**
	 * Tells the instance to save the given attributes.
	 * 
	 * @param attributes
	 *            Attributes to save
	 * @throws Exception
	 */
	void save(HashMap<String, HashMap<String, String>> attributes) throws Exception;
	
	/**
	 * Tells the instance to load and add all attributes to the given map.
	 * 
	 * @param attributes
	 *            Map the attributes shall be added to
	 * @throws Exception
	 */
	void load(HashMap<String, HashMap<String, String>> attributes) throws Exception;
}

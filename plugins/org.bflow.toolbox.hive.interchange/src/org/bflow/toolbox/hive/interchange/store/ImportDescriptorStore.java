package org.bflow.toolbox.hive.interchange.store;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeDescriptor;

/**
 * Provides a store for import scripts.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 13/07/11
 * @version 18/07/13
 * 
 */
public class ImportDescriptorStore {

	/**
	 * Static collection instances
	 */
	private static List<IInterchangeDescriptor> depository = new LinkedList<IInterchangeDescriptor>();
	private static Map<IInterchangeDescriptor, String> locationMap = new HashMap<IInterchangeDescriptor, String>();

	/**
	 * Registers the import descriptor to the store.
	 * 
	 * @param importDescriptor
	 *            import description
	 */
	public static void register(IInterchangeDescriptor importDescriptor) {
		depository.add(importDescriptor);
	}
	
	/**
	 * Registers the import descriptor that is provided by a local
	 * file.
	 * 
	 * @param importDescriptor
	 *            import descriptor
	 * @param path
	 *            absolute path to the import descriptor file
	 */
	public static void register(IInterchangeDescriptor importDescriptor, String path) {
		if (StringUtils.isBlank(path))
			throw new NullPointerException("Absolute path cannot be null or empty!");
		
		depository.add(importDescriptor);
		locationMap.put(importDescriptor, path);
	}

	/**
	 * Returns the depository.
	 * 
	 * @return depository
	 */
	public static List<IInterchangeDescriptor> getDepository() {
		return depository;
	}

	/**
	 * Returns the import descriptor matching for the given name.
	 * 
	 * @param name
	 *            name of the import descriptor
	 * @return import descriptor or null
	 */
	public static IInterchangeDescriptor getExportDescription(String name) {
		for (IInterchangeDescriptor exd : depository)
			if (exd.getName().equalsIgnoreCase(name))
				return exd;

		return null;
	}

}

package org.bflow.toolbox.hive.interchange.store;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeDescriptor;
import org.osgi.framework.Bundle;

/**
 * Provides a store for import scripts.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2011-07-13
 * @version 2014-06-17
 * 
 */
public class ImportDescriptorStore {

	private static List<IInterchangeDescriptor> depository = new LinkedList<IInterchangeDescriptor>();
	private static Map<IInterchangeDescriptor, Bundle> bundleMap = new HashMap<IInterchangeDescriptor, Bundle>();
	private static Map<IInterchangeDescriptor, String> locationMap = new HashMap<IInterchangeDescriptor, String>();

	/**
	 * Registers the import description to the store that is provided by an bundle.
	 * 
	 * @param desc   Import description
	 * @param bundle Bundle which holds the import descriptor and its files
	 */
	public static void register(IInterchangeDescriptor desc, Bundle bundle) {
		if (bundle == null) throw new NullPointerException("Bundle cannot be null!");

		depository.add(desc);
		bundleMap.put(desc, bundle);
	}
	
	/**
	 * Registers the import descriptor that is provided by a local file.
	 * 
	 * @param importDescriptor Import descriptor
	 * @param path             Absolute path to the import descriptor file
	 */
	public static void register(IInterchangeDescriptor importDescriptor, String path) {
		if (StringUtils.isBlank(path)) throw new NullPointerException("Absolute path cannot be null or empty!");
		
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
	 * @param name Name of the import descriptor
	 * @return import descriptor or null
	 */
	public static IInterchangeDescriptor getImportDescription(String name) {
		for (IInterchangeDescriptor exd : depository)
			if (exd.getName().equalsIgnoreCase(name))
				return exd;

		return null;
	}
	
	/**
	 * Returns the bundle for the given import descriptor.
	 * 
	 * @param desc Import descriptor
	 * @return Bundle for import descriptor or null
	 */
	public static Bundle getBundleFor(IInterchangeDescriptor desc) {
		return bundleMap.get(desc);
	}

	/**
	 * Returns the path for the given import descriptor.
	 * 
	 * @param importDescriptor Import descriptor
	 * @return Absolute path for the import descriptor or null
	 */
	public static String getPathFor(IInterchangeDescriptor importDescriptor) {
		return locationMap.get(importDescriptor);
	}
}
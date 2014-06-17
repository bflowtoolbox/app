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
 * @since 13/07/11
 * @version 17/06/14
 * 
 */
public class ImportDescriptorStore {

	/**
	 * Static collection instances
	 */
	private static List<IInterchangeDescriptor> depository = new LinkedList<IInterchangeDescriptor>();
	private static Map<IInterchangeDescriptor, Bundle> bundleMap = new HashMap<IInterchangeDescriptor, Bundle>();
	private static Map<IInterchangeDescriptor, String> locationMap = new HashMap<IInterchangeDescriptor, String>();

	/**
	 * Registers the import description to the store that is provided by an
	 * bundle.
	 * 
	 * @param exp
	 *            import description
	 * @param bundle
	 *            the bundle which holds the export descriptor and its files
	 */
	public static void register(IInterchangeDescriptor exp, Bundle bundle) {
		if (bundle == null) throw new NullPointerException("Bundle cannot be null!");

		depository.add(exp);
		bundleMap.put(exp, bundle);
	}
	
	/**
	 * Registers the import descriptor to the store.
	 * 
	 * @param importDescriptor
	 *            import description
	 * @deprecated Use register(exp, bundle) instead!
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
	
	/**
	 * Returns the bundle for the given export descriptor.
	 * 
	 * @param exportDescriptor
	 *            the export descriptor
	 * @return the bundle for the export descriptor or null
	 */
	public static Bundle getBundleFor(IInterchangeDescriptor exportDescriptor) {
		return bundleMap.get(exportDescriptor);
	}

	/**
	 * Returns the path for the given import descriptor.
	 * 
	 * @param importDescriptor
	 *            the import descriptor
	 * @return the absolute path for the export descriptor or null
	 */
	public static String getPathFor(IInterchangeDescriptor importDescriptor) {
		return locationMap.get(importDescriptor);
	}
}
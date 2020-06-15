package org.bflow.toolbox.hive.interchange.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeDescriptor;
import org.osgi.framework.Bundle;

/**
 * Provides a store for export scripts.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2011-06-07
 * @version 2013-07-18
 * 			2019-02-16 AST Added localization support via plugin.properties
 */
public class ExportDescriptorStore {
	private static List<IInterchangeDescriptor> depository = new ArrayList<>();
	private static Map<IInterchangeDescriptor, Bundle> bundleMap = new HashMap<>();
	private static Map<IInterchangeDescriptor, String> locationMap = new HashMap<>();
	
	/**
	 * Registers the export description to the store that is provided by an bundle.
	 * 
	 * @param desc   Export description
	 * @param bundle The bundle which holds the export descriptor and its files
	 */
	public static void register(IInterchangeDescriptor desc, Bundle bundle) {
		if (bundle == null) throw new NullPointerException("Bundle cannot be null!");
		
		// 2019-02-16 AST Add localization support
		LocalizationKit.localizeDescription(desc, bundle);
		
		depository.add(desc);
		bundleMap.put(desc, bundle);
	}

	/**
	 * Registers the export description to the store that is provided by a local
	 * file.
	 * 
	 * @param desc Export description
	 * @param path Absolute path to the export description file
	 */
	public static void register(IInterchangeDescriptor desc, String path) {
		if (path == null || path.isEmpty()) {
			throw new NullPointerException("Absolute path cannot be null or empty!");
		}

		depository.add(desc);
		locationMap.put(desc, path);
	}

	/**
	 * Returns the depository.
	 * 
	 * @return depository
	 */
	public static List<IInterchangeDescriptor> getDepository() {
		return getDepository(false);
	}

	/**
	 * Returns the depository. The result depends on the given parameter. Including
	 * non public will also return those export descriptions which has been marked
	 * as non public. The result set will contain all export descriptions
	 * independent of their diagram editor file extension.
	 * 
	 * @param includeNonPublic Set TRUE will return all installed export
	 *                         descriptions
	 * @return List of export descriptions depending on the given parameter
	 */
	public static List<IInterchangeDescriptor> getDepository(boolean includeNonPublic) {
		List<IInterchangeDescriptor> list = new ArrayList<IInterchangeDescriptor>();

		for (int i = 0; i < depository.size(); i++) {
			IInterchangeDescriptor exp = depository.get(i);

			if (exp.isPublic()) { // add if it is public
				list.add(exp);
			} else { // it isn't marked as public
				if (includeNonPublic) { // but is requested also
					list.add(exp);
				}
			}
		}

		return list;
	}

	/**
	 * Returns the depository that is matching the given parameters.
	 * 
	 * @param diagramEditorFileExtension Diagram editor file extension
	 * @param includeNonPublic           Include non public flag
	 * @return Collection of ExportDescriptions matching the parameters
	 */
	public static List<IInterchangeDescriptor> getDepository(
			String diagramEditorFileExtension, boolean includeNonPublic) {
    	// installed matching ones
		List<IInterchangeDescriptor> installed = getDepository(includeNonPublic);

		// Result list
		List<IInterchangeDescriptor> matching = new ArrayList<IInterchangeDescriptor>();

		// finding those who are matching the file extension or were it is null
		for (int i = 0; i < installed.size(); i++) {
			IInterchangeDescriptor exp = installed.get(i);
			if (exp.getApplicableDiagramEditorTypes() == null
					|| exp.getApplicableDiagramEditorTypes().length == 0
					|| exp.getApplicableDiagramEditorTypes()[0]
							.equalsIgnoreCase(diagramEditorFileExtension)) {
				matching.add(exp);
			}
		}

		return matching;
	}

	/**
	 * Returns the export description fitting to the given name.
	 * 
	 * @param name Name of the export description
	 * @return export description or null
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
	 * @param exportDescriptor Export descriptor
	 * @return the bundle for the export descriptor or null
	 */
	public static Bundle getBundleFor(IInterchangeDescriptor exportDescriptor) {
		return bundleMap.get(exportDescriptor);
	}

	/**
	 * Returns the path for the given export descriptor.
	 * 
	 * @param exportDescriptor Export descriptor
	 * @return Absolute path for the export descriptor or null
	 */
	public static String getPathFor(IInterchangeDescriptor exportDescriptor) {
		return locationMap.get(exportDescriptor);
	}
}

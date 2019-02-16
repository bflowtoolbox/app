package org.bflow.toolbox.hive.interchange.store;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.PropertyResourceBundle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
	private static final Log _log = LogFactory.getLog(ExportDescriptorStore.class);
	private static Map<Bundle, PropertyResourceBundle> _bundleResourceMap = new HashMap<>();	 
	private static List<IInterchangeDescriptor> depository = new ArrayList<>();
	private static Map<IInterchangeDescriptor, Bundle> bundleMap = new HashMap<>();
	private static Map<IInterchangeDescriptor, String> locationMap = new HashMap<>();
	
	/**
	 * Registers the export description to the store that is provided by an
	 * bundle.
	 * 
	 * @param exp
	 *            export description
	 * @param bundle
	 *            the bundle which holds the export descriptor and its files
	 */
	public static void register(IInterchangeDescriptor exp, Bundle bundle) {
		if (bundle == null) throw new NullPointerException("Bundle cannot be null!");
		
		// 2019-02-16 AST Add localization support
		String expDesc = exp.getDescription();
		if (expDesc != null && expDesc.startsWith("%")) {
			PropertyResourceBundle prb = getPropertyResourceBundle(bundle);
			if (prb != null) {
				String key = expDesc.substring(1);
				String localizedText = prb.getString(key);
				applyLocalizedText(exp, localizedText);
			}
		}
		
		depository.add(exp);
		bundleMap.put(exp, bundle);
	}

	/**
	 * Registers the export description to the store that is provided by a local
	 * file.
	 * 
	 * @param exp  Export description
	 * @param path Absolute path to the export description file
	 */
	public static void register(IInterchangeDescriptor exp, String path) {
		if (path == null || path.isEmpty()) {
			throw new NullPointerException("Absolute path cannot be null or empty!");
		}

		depository.add(exp);
		locationMap.put(exp, path);
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
	
	/**
	 * Applies the given {@code text} to the given {@code exp} by overriding the
	 * field via reflection.
	 * 
	 * @param exp  Descriptor to modify
	 * @param text Text to apply
	 */
	private static void applyLocalizedText(IInterchangeDescriptor exp, String text) {
		Class<?> clzz = exp.getClass();
		try {
			Field descriptionField = clzz.getDeclaredField("description");
			descriptionField.setAccessible(true);
			try {
				descriptionField.set(exp, text);
			} finally {
				descriptionField.setAccessible(false);				
			}
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
			_log.error("Error on applying localized text", ex);
		}
	}
	
	/**
	 * Tries to resolve the locale-specific {@link PropertyResourceBundle} of 
	 * the plugin.properties the given {@code bundle} may provide. If there is 
	 * no resource file, NULL is returned.
	 */
	private static PropertyResourceBundle getPropertyResourceBundle(Bundle bundle) {
		PropertyResourceBundle prb = _bundleResourceMap.get(bundle);
		if (prb != null) return prb;
		
		String[] variants = getVariants();
		for (int i = -1; ++i != variants.length;) {
			String variant = variants[i];
			String variantName = "/plugin".concat(variant).concat(".properties");
			URL entryUrl = bundle.getEntry(variantName);
			if (entryUrl == null) continue;
			
			try (InputStream stream = entryUrl.openStream()) {
				prb = new PropertyResourceBundle(stream);
				_bundleResourceMap.put(bundle, prb);
				return prb;
			} catch (IOException ex) {
				_log.error("Error on reading property resource bundle", ex);
				continue;
			}
		}
		
		return null;
	}
	
	/**
	 * Returns the language abbreviation variants of the current Java platform locale.
	 */
	private static String[] getVariants() {
		Locale locale = Locale.getDefault();
		String localeStr = locale.toString();
		return new String[] { 
				"_".concat(localeStr), 
				"_".concat(localeStr.substring(0, 2)), 
				"" 
				};
	}
}

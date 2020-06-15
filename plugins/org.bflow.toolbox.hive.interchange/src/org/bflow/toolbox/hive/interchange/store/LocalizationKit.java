package org.bflow.toolbox.hive.interchange.store;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.PropertyResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeDescriptor;
import org.osgi.framework.Bundle;

/**
 * Provides methods to localize instance of {@link IInterchangeDescriptor} via
 * plugin.properties of the providing bundle.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-02-17
 *
 */
public class LocalizationKit {
	private static final Log _log = LogFactory.getLog(LocalizationKit.class);
	private static Map<Bundle, PropertyResourceBundle> _bundleResourceMap = new HashMap<>();
	
	/**
	 * Localizes the description of the given {@code desc} provided by the given
	 * {@code bundle}.
	 */
	public static void localizeDescription(IInterchangeDescriptor desc, Bundle bundle) {
		String expDesc = desc.getDescription();
		if (expDesc == null) return;
		if (!expDesc.startsWith("%")) return;
		
		PropertyResourceBundle prb = getPropertyResourceBundle(bundle);
		if (prb == null) return;
		
		String key = expDesc.substring(1);
		String localizedText = prb.getString(key);
		applyLocalizedText(desc, localizedText);
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
}

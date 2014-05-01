package org.bflow.toolbox.extensions.properties;

import java.util.PropertyResourceBundle;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;

public class PluginProperties implements IPluginProperties {

	private PropertyResourceBundle properties;

	public PluginProperties(String propertiesFile, String bundleName) {
		loadFile(propertiesFile, bundleName);
	}

	protected void loadFile(String propertiesFile, String bundleName) {
		try {
			properties = new PropertyResourceBundle(FileLocator.openStream(
					Platform.getBundle(bundleName), new Path(propertiesFile),
					false));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String loadProperty(String property) {
		return properties.getString(property);
	}
}

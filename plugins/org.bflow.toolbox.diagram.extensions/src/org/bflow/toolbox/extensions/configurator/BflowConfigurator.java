package org.bflow.toolbox.extensions.configurator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.eclipse.core.resources.ResourcesPlugin;

/**
 * Defines a standard bflow configuration instance. All necessary configs of
 * bflow are stored and can be requested here. For requesting properties look at
 * the {@link Key} constants.
 * 
 * @author Arian Storch
 * @since 12/07/10
 * @version 22/07/12
 */
public class BflowConfigurator {

	private static BflowConfigurator instance;

	private HashMap<String, String> properties;

	/**
	 * Constructor. In normal case you needn't this. Look at the static
	 * instance.
	 */
	private BflowConfigurator() {
		loadConfiguration();
	}

	private void loadConfiguration() {

		properties = new HashMap<String, String>();

		SAXReader reader = new SAXReader();
		Document doc = null;

		try {
			doc = reader.read(Key.FILE);
		} catch (DocumentException e) {
			// e.printStackTrace();
		}

		if (doc == null) {
			properties.put(Key.IDELETE, "true");
			saveConfiguration();
			return;
		}

		Element root = doc.getRootElement();

		for (Iterator<?> it = root.elementIterator(); it.hasNext();) {
			Element el = (Element) it.next();
			properties.put(el.attributeValue("name"), el
					.attributeValue("value"));
		}
	}

	/**
	 * Stores the value of the key within the configurator.<br/>
	 * Look at the {@link Key} constants.
	 * 
	 * @param key
	 * @param value
	 */
	public void put(String key, String value) {
		properties.put(key, value);
		saveConfiguration();
	}

	/**
	 * Returns the value according to the key or null if none exists.<br/>
	 * Look at the {@link Key} constants.
	 * 
	 * @param key
	 * @return value or null
	 */
	public String get(String key) {
		return properties.get(key);
	}

	private void saveConfiguration() {
		Document xmlDocument = DocumentHelper.createDocument();

		Element root = xmlDocument.addElement("config");

		for (String key : properties.keySet()) {
			Element el = root.addElement("param");
			el.addAttribute("name", key);
			el.addAttribute("value", properties.get(key));
		}

		try {
			OutputFormat format = OutputFormat.createPrettyPrint();

			if (!Key.FILE.exists()) {
				Key.FILE.getParentFile().mkdirs();
				Key.FILE.createNewFile();
			}

			XMLWriter writer = new XMLWriter(new FileWriter(Key.FILE), format);

			writer.write(xmlDocument);
			writer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Returns the active instance of the configurator.
	 * 
	 * @return active instance of the configurator
	 */
	public static BflowConfigurator getInstance() {
		if (instance == null)
			instance = new BflowConfigurator();

		return instance;
	}

	/**
	 * Defines the keys for the configurator.
	 * 
	 * @author Arian Storch
	 * @since 12/07/10
	 * 
	 */
	public static class Key {
		private static final File FILE = ResourcesPlugin.getWorkspace()
				.getRoot().getLocation().append("/.config/bflowconfig.xml")
				.toFile();

		/**
		 * key flag for the state of the idelete mode
		 */
		public static final String IDELETE = "idelete.flag";
	}
}

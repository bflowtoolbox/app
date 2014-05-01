package org.bflow.toolbox.hive.addons.store;

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

/**
 * Implements a store for configuration elements.
 * @author Arian Storch
 * @since 24/05/11
 *
 */
public class ConfigurationStore {
	
	private static ConfigurationStore instance;
	
	private HashMap<String, String> config;
	
	/**
	 * Constructor
	 */
	private ConfigurationStore() {
		config = new HashMap<String, String>();
		load();
	}
	
	/**
	 * Puts the value associated with this id into the configuration. If a value with the same id already exists, 
	 * it will be overwritten.
	 * @param id id 
	 * @param value value
	 */
	public void put(String id, String value) {
		config.put(id, value);
	}
	
	/**
	 * Returns the value of the id or null if none exists.
	 * @param id id
	 * @return value associated with this id
	 */
	public String get(String id) {
		return config.get(id);
	}
	
	/**
	 * Loads the configuration out of its xml file.
	 */
	private void load() {
		SAXReader reader = new SAXReader();
		
		try
		{
			Document xmlDocument = reader.read(Key.KEY_CONFIG_FILE);
			Element root = xmlDocument.getRootElement();
			
			for(Iterator<?> it = root.elementIterator("key"); it.hasNext(); )
			{
				Element key = (Element)it.next();
				
				String id = key.attributeValue("id");
				String value = key.attributeValue("value");
				
				config.put(id, value);
			}
		}
		catch(DocumentException ex)
		{
			//ex.printStackTrace();
		}
	}
	
	/**
	 * Saves the configuration on local drive. Usually you needn't to call this on your own.
	 */
	public void save() {
		Document xmlDocument = DocumentHelper.createDocument();
		Element root = xmlDocument.addElement("config");
		
		for(String id:config.keySet())
		{
			Element tool = root.addElement("key");
			tool.addAttribute("id", id);
			tool.addAttribute("value", config.get(id));
		}
		
		try
		{
			OutputFormat format = OutputFormat.createPrettyPrint();
			
			if(!Key.KEY_CONFIG_FILE.exists())
			{
				Key.KEY_CONFIG_FILE.getParentFile().mkdirs();
				Key.KEY_CONFIG_FILE.createNewFile();
			}
			
			XMLWriter writer = new XMLWriter(new FileWriter(Key.KEY_CONFIG_FILE), format);
			
			writer.write(xmlDocument);
			writer.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	/**
	 * Returns the instance of the Configuration Store.
	 * @return instance
	 */
	public static ConfigurationStore getInstance() {
		if(instance == null)
			instance = new ConfigurationStore();
		
		return instance;
	}
	
	public static final String ID_SAVE_ALL_OPEN_DIAGRAMS = "org.bflow.toolbox.mitamm.ids.saveallopen";

}

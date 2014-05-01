package org.bflow.toolbox.hive.addons.store;

import java.io.File;

import org.eclipse.core.resources.ResourcesPlugin;

/**
 * Kernel class that holds key files for the mitamm plugin.
 * @author Arian Storch
 * @since 08/09/10
 */
public class Key 
{	
	/**
	 * key for the protocolstore path
	 */
	public static final File KEY_PROTOCOLSTORE_PATH = ResourcesPlugin.getWorkspace().getRoot().getLocation().append("/.protocols/").toFile(); 
	
	/**
	 * key for the toolstore.xml file location
	 */
	public static final File KEY_TOOLSTORE_FILE = ResourcesPlugin.getWorkspace().getRoot().getLocation().append("/.protocols/toolstore.xml").toFile();
	
	/**
	 * key for the protocolstore.xml file location
	 */
	public static final File KEY_PROTOCOLSTORE_FILE = ResourcesPlugin.getWorkspace().getRoot().getLocation().append("/.protocols/protocolstore.xml").toFile();
	
	/**
	 * key for the mitamm.log file location
	 */
	public static final File KEY_MITAMM_LOG_FILE = ResourcesPlugin.getWorkspace().getRoot().getLocation().append("/.protocols/addons.log").toFile();
	
	/**
	 * key for the configuration file location
	 */
	public static final File KEY_CONFIG_FILE = ResourcesPlugin.getWorkspace().getRoot().getLocation().append("/.config/config.xml").toFile();
	
}

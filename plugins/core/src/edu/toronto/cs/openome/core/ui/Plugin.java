package edu.toronto.cs.openome.core.ui;

import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.plugin.AbstractUIPlugin;


/**
 * The main plugin class to be used in the desktop.
 */
public class Plugin extends AbstractUIPlugin implements IStartup {
	private static Plugin plugin;
	private static boolean prefsInit = false;
	private ResourceBundle resourceBundle = null;
    private static ResourceBundle resources;
    static {
        try {
            resources = ResourceBundle.getBundle("core", 
            		Locale.getDefault(),
            		Plugin.class.getClassLoader());
            for (Enumeration i = resources.getKeys(); i.hasMoreElements(); ) {
            	String key = (String)i.nextElement();
            	String val = resources.getString(key);
            	System.setProperty(IConstants.PREFIX + key, val);
            }
        } catch (MissingResourceException mre) {
        	mre.printStackTrace();
            System.out.println("Info: plugins.properties not found");
        }
    }				
	
	public Plugin() {
		super();
		plugin = this;
		try {
			resourceBundle=ResourceBundle.getBundle("ui.pluginResources");
		} catch (MissingResourceException x) {
			x.printStackTrace();
		}
	}

	/**
	 * Returns the shared instance.
	 */
	public static Plugin getPlugin() {
		return plugin;
	}

	/**
	 * Returns preferences assigned to the singleton instance.
	 * @return Preferences
	 */
	public static Preferences getPreferences() {
		Preferences prefs = getPlugin().getPluginPreferences();
		if (!prefsInit)
			initPrefs(prefs);
		return prefs;
	}

	/**
	 * Returns the string from the plugin's resource bundle,
	 * or 'key' if not found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle= getPlugin().getResourceBundle();
		if (bundle != null)
			try {
				return bundle.getString(key);
			} catch (MissingResourceException e) {
			}
		return key;
	}

	/**
	 * Returns the workspace instance.
	 */
	public static IWorkspace getWorkspace() {
		return ResourcesPlugin.getWorkspace();
	}

	/**
	 * Ensures that defaults exist for preference settings.
	 * @param prefs Preferences
	 */
	protected static synchronized void initPrefs(Preferences prefs) {
		if (!prefsInit) {
			// Init defaults here ...
		}
	}

	/**
	 * Logs a message to the active log with the given IStatus.
	 * @param iStatus
	 * @param msg
	 */
	public static void log(int iStatus, String msg) {
		log(iStatus, msg, null);
	}

	/**
	 * Logs a message to the active log with the given IStatus and detail.
	 * @param iStatus
	 * @param msg
	 * @param t
	 */
	public static void log(int iStatus, String msg, Throwable t) {
		getPlugin().getLog().log(
			new Status(iStatus, "ui", IStatus.OK, msg, t));
	}

	/**
	 * Saves preferences assigned to the singleton instance.
	 */
	public static void savePreferences() {
		getPlugin().savePluginPreferences();
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public void earlyStartup() {
	}
}
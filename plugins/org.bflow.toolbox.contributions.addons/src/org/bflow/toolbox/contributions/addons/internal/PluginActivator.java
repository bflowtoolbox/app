package org.bflow.toolbox.contributions.addons.internal;

import org.bflow.toolbox.contributions.addons.ExportListener;
import org.bflow.toolbox.contributions.addons.ImportListener;
import org.bflow.toolbox.hive.interchange.events.ExportListenerRegistry;
import org.bflow.toolbox.hive.interchange.events.ImportListenerRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class PluginActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.bflow.toolbox.add_ons.contributions";

	// The shared instance
	private static PluginActivator plugin;
	
	// Export listener
	private ExportListener exportListener = new ExportListener();
	
	// Import listener
	private ImportListener importListener = new ImportListener();
	
	/**
	 * The constructor
	 */
	public PluginActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		ExportListenerRegistry.addXSLTExportListener(exportListener);
		ImportListenerRegistry.addXSLTImportListener(importListener);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		ExportListenerRegistry.removeXSLTExportListener(exportListener);
		ImportListenerRegistry.removeXSLTImportListener(importListener);
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static PluginActivator getDefault() {
		return plugin;
	}

}

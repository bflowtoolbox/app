package org.bflow.toolbox.hive.gmfbridge.graphiti.internal;

import org.bflow.toolbox.hive.libs.aprogu.logging.ILogWriter;
import org.bflow.toolbox.hive.libs.aprogu.logging.LogWriter;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class GraphitiGmfBridgePlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.bflow.toolbox.hive.gmfbridge.graphiti"; //$NON-NLS-1$

	// The shared instance
	private static GraphitiGmfBridgePlugin plugin;
	
	private ILogWriter fLogWriter = LogWriter.createInstance(GraphitiGmfBridgePlugin.class);
	
	/**
	 * The constructor
	 */
	public GraphitiGmfBridgePlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static GraphitiGmfBridgePlugin getDefault() {
		return plugin;
	}
	
	/**
	 * Returns the log writer of this plug-in.
	 * 
	 * @return The log writer of this plug-in
	 */
	public static ILogWriter LogWriter() {
		return getDefault().fLogWriter;
	}

}

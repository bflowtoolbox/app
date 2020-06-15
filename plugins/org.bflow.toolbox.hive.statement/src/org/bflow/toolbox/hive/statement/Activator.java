<<<<<<< HEAD:plugins/org.bflow.toolbox.hive.gmfbridge.graphiti/src/org/bflow/toolbox/hive/gmfbridge/graphiti/internal/GraphitiGmfBridgePlugin.java
package org.bflow.toolbox.hive.gmfbridge.graphiti.internal;

import org.bflow.toolbox.hive.libs.aprogu.logging.ILogWriter;
import org.bflow.toolbox.hive.libs.aprogu.logging.LogWriter;
=======
package org.bflow.toolbox.hive.statement;

import org.eclipse.jface.resource.ImageDescriptor;
>>>>>>> development:plugins/org.bflow.toolbox.hive.statement/src/org/bflow/toolbox/hive/statement/Activator.java
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
<<<<<<< HEAD:plugins/org.bflow.toolbox.hive.gmfbridge.graphiti/src/org/bflow/toolbox/hive/gmfbridge/graphiti/internal/GraphitiGmfBridgePlugin.java
public class GraphitiGmfBridgePlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.bflow.toolbox.hive.gmfbridge.graphiti"; //$NON-NLS-1$

	// The shared instance
	private static GraphitiGmfBridgePlugin plugin;
	
	private ILogWriter fLogWriter = LogWriter.createInstance(GraphitiGmfBridgePlugin.class);
=======
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.bflow.toolbox.hive.statement"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
>>>>>>> development:plugins/org.bflow.toolbox.hive.statement/src/org/bflow/toolbox/hive/statement/Activator.java
	
	/**
	 * The constructor
	 */
<<<<<<< HEAD:plugins/org.bflow.toolbox.hive.gmfbridge.graphiti/src/org/bflow/toolbox/hive/gmfbridge/graphiti/internal/GraphitiGmfBridgePlugin.java
	public GraphitiGmfBridgePlugin() {
=======
	public Activator() {
>>>>>>> development:plugins/org.bflow.toolbox.hive.statement/src/org/bflow/toolbox/hive/statement/Activator.java
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
<<<<<<< HEAD:plugins/org.bflow.toolbox.hive.gmfbridge.graphiti/src/org/bflow/toolbox/hive/gmfbridge/graphiti/internal/GraphitiGmfBridgePlugin.java
	public static GraphitiGmfBridgePlugin getDefault() {
=======
	public static Activator getDefault() {
>>>>>>> development:plugins/org.bflow.toolbox.hive.statement/src/org/bflow/toolbox/hive/statement/Activator.java
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

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}

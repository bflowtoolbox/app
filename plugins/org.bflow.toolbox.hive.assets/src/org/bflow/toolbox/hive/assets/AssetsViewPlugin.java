package org.bflow.toolbox.hive.assets;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/** The activator class controls the plug-in life cycle */
public class AssetsViewPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.bflow.toolbox.hive.assets"; //$NON-NLS-1$

	// The shared instance
	private static AssetsViewPlugin plugin;
	
	private final Logger _log = LogManager.getLogger(AssetsViewPart.class);
	
	/** The constructor */
	public AssetsViewPlugin() {
		// Nothing to do here
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

	/** Logs the given {@code message} with an error severity. */
	public void logError(String message) {
		logError(message, null);
	}
	
	/**
	 * Logs the given {@code message} and {@code throwable} with an error severity.
	 */
	public void logError(String message, Throwable throwable) {
		_log.error(message, throwable);
		getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, message, throwable));
	}
	
	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static AssetsViewPlugin getDefault() {
		return plugin;
	}

}

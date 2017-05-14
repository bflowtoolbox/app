package org.bflow.toolbox.hive.eclipse.integration.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * Implements the {@link AbstractUIPlugin} for the eclipse integration plug-in.
 * @author Arian Storch <arian.storch@bflow.org>
 * @since 26.07.2015
 *
 */
public class EclipseIntegrationPlugin extends AbstractUIPlugin {

	private static BundleContext context;
	private static EclipseIntegrationPlugin plugin;

	static BundleContext getContext() {
		return context;
	}
	
	/**
	 * Returns the instance of this class.
	 * 
	 * @return Instance of this class.
	 */
	public static EclipseIntegrationPlugin Instance() {
		return plugin;
	}
	
	private final Log logWriter = LogFactory.getLog(getClass());

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		EclipseIntegrationPlugin.context = bundleContext;
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		EclipseIntegrationPlugin.context = null;
	}

	/**
	 * Writes an error log message.
	 * 
	 * @param text
	 *            Text
	 */
	public void logError(String text) {
		if (!logWriter.isErrorEnabled()) return;
		logWriter.error(text);
	}
	
	/**
	 * Writes an error log message with the corresponding exception.
	 * 
	 * @param text
	 *            Text
	 * @param ex
	 *            Exception
	 */
	public void logError(String text, Exception ex) {
		if (!logWriter.isErrorEnabled()) return;
		logWriter.error(text, ex);
	}
}

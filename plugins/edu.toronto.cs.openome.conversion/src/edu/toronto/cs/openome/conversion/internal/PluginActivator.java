package edu.toronto.cs.openome.conversion.internal;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * @generated
 */
public class PluginActivator extends AbstractUIPlugin {

	/**
	 * @generated
	 */
	public static final String ID = "openome_model"; //\u0024NON-NLS-1\u0024 XXX FIXME DollarSign support

	/**
	 * @generated
	 */
	private static PluginActivator ourInstance;

	/**
	 * @generated
	 */
	public PluginActivator() {
	}

	/**
	 * @generated
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		ourInstance = this;
	}

	/**
	 * @generated
	 */
	public void stop(BundleContext context) throws Exception {
		ourInstance = null;
		super.stop(context);
	}

	/**
	 * @generated
	 */
	public static PluginActivator getDefault() {
		return ourInstance;
	}

}

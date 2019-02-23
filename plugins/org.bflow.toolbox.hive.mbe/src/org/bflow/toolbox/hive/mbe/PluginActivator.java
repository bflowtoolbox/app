package org.bflow.toolbox.hive.mbe;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Class to control/hook the lifecycle of the plug-in.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2015-03-07
 * @version 2019-02-23 AST Added licence checker call
 *
 */
public class PluginActivator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		PluginActivator.context = bundleContext;
		
		new LicenceChecker().run();
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		PluginActivator.context = null;
	}

}

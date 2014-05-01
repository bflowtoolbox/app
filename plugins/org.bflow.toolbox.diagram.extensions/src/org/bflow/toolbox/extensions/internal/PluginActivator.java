package org.bflow.toolbox.extensions.internal;

import org.bflow.toolbox.extensions.workbench.BflowApplicationWorkbenchListener;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * Extends {@link AbstractUIPlugin} to participate in the lifecycle of Plug-ins. 
 * This plug-in registers listeners that are needed for various contributed features.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 14/12/13
 */
public class PluginActivator extends AbstractUIPlugin {
	
	private BflowApplicationWorkbenchListener fAppWorkbenchListener = new BflowApplicationWorkbenchListener();
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		PlatformUI.getWorkbench().addWorkbenchListener(fAppWorkbenchListener);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		PlatformUI.getWorkbench().removeWorkbenchListener(fAppWorkbenchListener);
		super.stop(context);
	}

}

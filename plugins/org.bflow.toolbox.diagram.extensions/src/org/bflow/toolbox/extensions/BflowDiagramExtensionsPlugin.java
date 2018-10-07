package org.bflow.toolbox.extensions;

import org.bflow.toolbox.extensions.workbench.BflowApplicationWorkbenchListener;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * Extends {@link AbstractUIPlugin} to participate in the life-cycle of plug-ins. 
 * This plug-in registers listeners that are needed for various contributed features.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2013-12-14
 * @version 2018-10-07 Renamed. Former name was PluginActivator.
 */
public class BflowDiagramExtensionsPlugin extends AbstractUIPlugin {
	
	private BflowApplicationWorkbenchListener _appWorkbenchListener = new BflowApplicationWorkbenchListener();
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		PlatformUI.getWorkbench().addWorkbenchListener(_appWorkbenchListener);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		PlatformUI.getWorkbench().removeWorkbenchListener(_appWorkbenchListener);
		super.stop(context);
	}

}

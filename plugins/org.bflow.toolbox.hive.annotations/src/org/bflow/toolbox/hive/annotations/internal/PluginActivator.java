package org.bflow.toolbox.hive.annotations.internal;

import org.bflow.toolbox.hive.annotations.AnnotationLauncherConfigurator;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * Provides a plug-in activator for the plug-in.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 22.08.2015
 * 
 */
public class PluginActivator extends AbstractUIPlugin {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		
		try {
			String installPath = AnnotationLauncherConfigurator.getANNOTATIONLOGIC_FOLDER_PATH();
			new ResourcesInstaller().install(installPath);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
package org.bflow.toolbox.hive.libs;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Plugin;

/**
 * Extends {@link Plugin} to control/hook the life-cycle of this plug-in.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-02-17
 * @version 2019-02-23 AST Set env var `rws_loc` by querying the workspace root
 *
 */
public class PluginActivator extends Plugin {
	/** Initializes the new instance. */
	public PluginActivator() {
		setLogFilePath();		
	}
	
	/**
	 * Sets the log file path by setting the corresponding environment variable to
	 * the path of the workspace root.
	 */
	private void setLogFilePath() {
		String workspacePath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString();
		System.setProperty("rws_loc", workspacePath);
	}
}

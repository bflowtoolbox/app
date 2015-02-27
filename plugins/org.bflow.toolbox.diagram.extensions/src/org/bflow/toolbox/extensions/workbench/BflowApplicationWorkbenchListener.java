package org.bflow.toolbox.extensions.workbench;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;

/**
 * Implements {@link IWorkbenchListener} to close all open diagrams when the
 * workbench is closing.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 14.12.13
 * @version 27.02.15 Add feature: refresh workspace at start up
 */
public class BflowApplicationWorkbenchListener implements IWorkbenchListener {
	
	/**
	 * Creates a new instance. Each instance refreshes the whole workspace on
	 * initialization.
	 */
	public BflowApplicationWorkbenchListener() {
		// Refresh the whole workspace at start up
		try {
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			workspace.getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException ex) {
			ex.printStackTrace(); // TODO Use application log
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchListener#preShutdown(org.eclipse.ui.IWorkbench, boolean)
	 */
	@Override
	public boolean preShutdown(IWorkbench workbench, boolean forced) {
		return workbench.getActiveWorkbenchWindow().getActivePage().closeAllEditors(true);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchListener#postShutdown(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void postShutdown(IWorkbench workbench) {
	}

}

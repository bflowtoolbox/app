package org.bflow.toolbox.extensions.workbench;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;

/**
 * Implements {@link IWorkbenchListener} to close all open diagrams when 
 * the workbench is closing. 
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 14/12/13
 */
public class BflowApplicationWorkbenchListener implements IWorkbenchListener {

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

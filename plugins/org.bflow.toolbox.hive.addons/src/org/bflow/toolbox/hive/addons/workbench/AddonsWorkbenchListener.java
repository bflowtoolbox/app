package org.bflow.toolbox.hive.addons.workbench;

import org.bflow.toolbox.hive.addons.store.ToolStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;
import org.eclipse.ui.PlatformUI;

/**
 * Provides an implementation of {@link IWorkbenchListener} to ensure the lifecycle 
 * of add-on factories and stores.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 13/09/13
 */
public class AddonsWorkbenchListener implements IWorkbenchListener {
	
	/**
	 * Registers a new AddonsWorkbenchListener on the workbench.
	 */
	public static void register() {
		PlatformUI.getWorkbench().addWorkbenchListener(new AddonsWorkbenchListener());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchListener#preShutdown(org.eclipse.ui.IWorkbench, boolean)
	 */
	@Override
	public boolean preShutdown(IWorkbench workbench, boolean forced) {
		// Ensure saving the ToolStore
		ToolStore.save();
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchListener#postShutdown(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void postShutdown(IWorkbench workbench) {
		workbench.removeWorkbenchListener(this);
	}

}

package org.bflow.toolbox.hive.mbe;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.ui.IPageListener;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.osgi.framework.Bundle;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

/**
 * Implements {@link IStartup} to get notified if the workbench has been
 * started. Then various listener of the workbench lifecycle will be attached.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 07.03.2015
 * @version	27.20.2016 - AST Added a fix to open the default perspective when the application starts the first time
 *
 */
public class MbeStartupListener implements IStartup {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IStartup#earlyStartup()
	 */
	@Override
	public void earlyStartup() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
				
		// Register custom workbench window listener
		workbench.getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
				if (window != null) {
					WorkbenchWindowPageListener listener = new WorkbenchWindowPageListener();
					window.addPageListener(listener);

					// If there is already an opened page attach to it
					IWorkbenchPage page = window.getActivePage();
					if (page != null)
						listener.pageActivated(page);
				}
			}
		});
		
		// This is a fix due to the issue that the currently underlying eclipse platform ignores the -perspective argument
		// Try to open the default perspective the first time the application starts
		workbench.getDisplay().asyncExec(new Runnable() {
			@Override
			public void run() {
				Bundle bundle = PluginActivator.getContext().getBundle();
				String pluginId = bundle.getSymbolicName();
				String flagName = "firstRun";
				
				Preferences prefs = InstanceScope.INSTANCE.getNode("org.bflow.toolbox.hive.mbe");
				boolean firstRun = prefs.getBoolean(flagName, true);
				if (!firstRun) return;
				
				// Set flag and persist it
				try {
					prefs.putBoolean(flagName, false);
					prefs.flush();
				} catch(BackingStoreException ex) {
					Platform.getLog(bundle)
							.log(new Status(IStatus.ERROR, pluginId, "Error on persisting first run flag value", ex));
				}
				
				String[] args = Platform.getApplicationArgs();
				
				// Let's look up the perspective id
				String perspectiveId = null;
				for (int i = -1; ++i != args.length;) {
					if (!args[i].equalsIgnoreCase("-perspective")) continue;
					if (i + 1 < args.length) {
						perspectiveId = args[i+1];
					}
					break;
				}
				
				// No perspective has been set
				if (perspectiveId == null) return;
				
				// Let's try to open the perspective
				try {
					IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
					workbench.showPerspective(perspectiveId, window);
				} catch (WorkbenchException ex) {
					Platform.getLog(bundle)
							.log(new Status(IStatus.ERROR, pluginId, "Error on initialzing startup perspective", ex));
				}
			}
		});
	}
	
	/**
	 * Anonymous implementation of {@link IPageListener} to send notifications
	 * to {@link MbeWorkbenchPagePartListener}.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 07.03.2015
	 *
	 */
	class WorkbenchWindowPageListener implements IPageListener {

		private IWorkbenchPage currentActivePage;
		
		/* (non-Javadoc)
		 * @see org.eclipse.ui.IPageListener#pageActivated(org.eclipse.ui.IWorkbenchPage)
		 */
		@Override
		public void pageActivated(IWorkbenchPage page) {
			if (currentActivePage != null)
				currentActivePage.removePartListener(MbeWorkbenchPagePartListener.Default);
			
			currentActivePage = page;
			
			if (currentActivePage != null)
				currentActivePage.addPartListener(MbeWorkbenchPagePartListener.Default);
		}

		/* (non-Javadoc)
		 * @see org.eclipse.ui.IPageListener#pageClosed(org.eclipse.ui.IWorkbenchPage)
		 */
		@Override
		public void pageClosed(IWorkbenchPage page) { }

		/* (non-Javadoc)
		 * @see org.eclipse.ui.IPageListener#pageOpened(org.eclipse.ui.IWorkbenchPage)
		 */
		@Override
		public void pageOpened(IWorkbenchPage page) { }
	}
}

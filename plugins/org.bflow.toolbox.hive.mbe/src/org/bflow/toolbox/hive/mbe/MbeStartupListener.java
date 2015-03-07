package org.bflow.toolbox.hive.mbe;

import org.eclipse.ui.IPageListener;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * Implements {@link IStartup} to get notified if the workbench has been
 * started. Then various listener of the workbench lifecycle will be attached.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 07.03.2015
 *
 */
public class MbeStartupListener implements IStartup {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IStartup#earlyStartup()
	 */
	@Override
	public void earlyStartup() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
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

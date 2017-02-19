/*
 * Created on Jun 19, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package edu.toronto.cs.openome.core.ui;

import org.eclipse.core.runtime.IPlatformRunnable;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * @author Yijun
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class App implements IPlatformRunnable {

	public static void main(String[] args) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IPlatformRunnable#run(java.lang.Object)
	 */
	public Object run(Object args) throws Exception {
		Display display = PlatformUI.createDisplay();
		try {
			int code = PlatformUI.createAndRunWorkbench(display,
					new Advisor());
			// exit the application with an appropriate return code
			return code == PlatformUI.RETURN_RESTART
					? EXIT_RESTART
					: EXIT_OK;
		} finally {
			if (display != null)
				display.dispose();
		}
	}
}

package org.bflow.toolbox.hive.addons.utils;

import java.lang.reflect.InvocationTargetException;

import org.bflow.toolbox.hive.addons.core.model.Protocol;
import org.bflow.toolbox.hive.nls.NLSupport;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * This class offers the ability to run a protocol while the user only sees a
 * progress dialog.<br/>
 * This is useful if you expect that the chain will take some time.
 * 
 * @author Arian Storch
 * @since 27.10.09
 * @version 13.07.11
 * 			28.10.16 AST - Code clean up
 * 
 */
public class ProtocolProgressDialog implements IRunnableWithProgress {
	private Protocol protocol;

	/**
	 * Constructor
	 * 
	 * @param chain
	 *            chain that shall be executed.
	 */
	public ProtocolProgressDialog(Protocol protocol) {
		this.protocol = protocol;
	}

	@SuppressWarnings("static-access")
	@Override
	public void run(final IProgressMonitor monitor)
			throws InvocationTargetException, InterruptedException {

		monitor.beginTask(NLSupport.ProtocolProgressDialog_TaskHeader, IProgressMonitor.UNKNOWN);

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				try {
					protocol.start();

					while (!protocol.isFinished()) {
						if (monitor.isCanceled())
							return;
						else
							Thread.sleep(1000);
					}

				} catch (Exception ex) {
					protocol.getLogger().error("Error on executing protocol", ex); //$NON-NLS-1$
				}
			}

		};

		Display display = PlatformUI.getWorkbench().getDisplay();

		display.asyncExec(runnable);
	}

}

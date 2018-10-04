package org.bflow.toolbox.bpmn.diagram;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * Provides access to the life-cycle and methods of the BPMN diagram Eclipse plug-in.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2018-10-04
 *
 */
public class BpmnDiagramPlugin extends AbstractUIPlugin {
	public static final String ID = "org.bflow.toolbox.bpmn.diagram";
	private static BpmnDiagramPlugin _instance;
	
	/** Initializes the new instance. */
	public BpmnDiagramPlugin() {
		_instance = this;
	}
	
	/**
	 * Logs the given message with an error severity.
	 * 
	 * @param message Message to log
	 */
	public void logError(String message) {
		logError(message, null);
	}
	
	/**
	 * Logs the given message and throwable with an error severity.
	 * 
	 * @param message   Message to log
	 * @param throwable Throwable to log
	 */
	public void logError(String message, Exception throwable) {
		if (message == null && throwable != null) {
			message = throwable.getMessage();
		}
		
		getLog().log(new Status(IStatus.ERROR, ID, IStatus.OK, message, throwable));
		debug(message, throwable);
	}
	
	/**
	 * Writes the given arguments into the console out stream.
	 * 
	 * @param message   Message
	 * @param throwable Throwable
	 */
	private void debug(String message, Throwable throwable) {
		if (!isDebugging()) return;
		
		if (message != null) {
			System.err.println(message);
		}
		
		if (throwable != null) {
			throwable.printStackTrace();
		}
	}
	
	/**
	 * Returns the default instance.
	 * 
	 * @return The default instance
	 */
	public static BpmnDiagramPlugin getInstance() {
		return _instance;
	}
}

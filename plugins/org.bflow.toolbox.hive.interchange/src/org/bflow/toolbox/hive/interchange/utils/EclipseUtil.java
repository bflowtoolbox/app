package org.bflow.toolbox.hive.interchange.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.bflow.toolbox.hive.interchange.AddonsInterchangePlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;

/**
 * Provides some useful methods for interacting with the eclipse platform.
 * 
 * @author Arian Storch
 * @since 27/07/12
 */
public class EclipseUtil {

	/**
	 * Creates an error dialog which shows some details.
	 * @param message main error message
	 * @param ex exception that should be visible in detail
	 * @param printStackTrace set true, to show the stack trace in detail
	 */
	public static void createErrorDetailsDialog(String message, Exception ex, boolean printStackTrace) {
		String pluginId = AddonsInterchangePlugin.PLUGIN_ID;
		MultiStatus info = new MultiStatus(pluginId, 1, message, ex);
		
		String cause = String.format(" - Caused by: %s", ex.getCause().getMessage());
		
		info.add(new Status(IStatus.ERROR, pluginId, cause));
		
		if (printStackTrace) {
			info.add(new Status(IStatus.ERROR, pluginId, " - Stacktrace - "));

			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter;

			printWriter = new PrintWriter(stringWriter);
			ex.printStackTrace(printWriter);

			String stackTrace = stringWriter.toString();
			String stackTraceLines[] = stackTrace.split("\n");

			for (int i = 0; i < stackTraceLines.length; i++) {
				info
						.add(new Status(IStatus.ERROR, pluginId,
								stackTraceLines[i]));
			}
		}
		
		ErrorDialog.openError(null, null, null, info);
	}	
}

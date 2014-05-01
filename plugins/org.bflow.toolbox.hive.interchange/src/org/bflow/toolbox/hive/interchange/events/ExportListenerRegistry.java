package org.bflow.toolbox.hive.interchange.events;

import java.util.ArrayList;

/**
 * Provides an export listener registry to add listeners to export processes.
 * 
 * @author Arian Storch
 * @since 23/06/11
 * @version 14/12/13
 */
public class ExportListenerRegistry {

	/**
	 * Collection of listeners
	 */
	private static ArrayList<IXSLTInterchangeExportListener> fXSLTInterchangeExportlistener = new ArrayList<IXSLTInterchangeExportListener>();

	/**
	 * Adds the listener to the registry.
	 * 
	 * @param listener
	 *            listener
	 */
	public static void addXSLTExportListener(IXSLTInterchangeExportListener listener) {
		fXSLTInterchangeExportlistener.add(listener);
	}

	/**
	 * Removes the listener from the registry.
	 * 
	 * @param listener
	 *            listener
	 */
	public static void removeXSLTExportListener(IXSLTInterchangeExportListener listener) {
		fXSLTInterchangeExportlistener.remove(listener);
	}

	/**
	 * Dispatches an export event to the listeners.
	 * 
	 * @param event
	 *            event to dispatch
	 */
	public static void dispatchEvent(ExportEvent event) {
		for (IXSLTInterchangeExportListener l : fXSLTInterchangeExportlistener) {
			l.noticeExportEvent(event);
		}
	}

}

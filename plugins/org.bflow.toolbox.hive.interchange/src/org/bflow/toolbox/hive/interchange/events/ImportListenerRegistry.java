package org.bflow.toolbox.hive.interchange.events;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeProcessListener;
import org.bflow.toolbox.hive.interchange.mif.core.InterchangeProcessInfo;

/**
 * Provides an import listener registry to add listeners to import processes.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 13/07/11
 * @version 14/12/13
 */
public class ImportListenerRegistry {

	/**
	 * Collection of listeners
	 */
	private static List<IXSLTInterchangeImportListener> fXSLTProcessListener = new ArrayList<IXSLTInterchangeImportListener>();
	private static List<IInterchangeProcessListener> fInterchangeProcessListener = new ArrayList<IInterchangeProcessListener>();

	/**
	 * Adds the listener to the registry.
	 * 
	 * @param listener
	 *            listener
	 */
	public static void addXSLTImportListener(IXSLTInterchangeImportListener listener) {
		fXSLTProcessListener.add(listener);
	}

	/**
	 * Removes the listener from the registry.
	 * 
	 * @param listener
	 *            listener
	 */
	public static void removeXSLTImportListener(IXSLTInterchangeImportListener listener) {
		fXSLTProcessListener.remove(listener);
	}
	
	/**
	 * Adds the listener to the registry.
	 * 
	 * @param listener
	 *            listener to add
	 */
	public static void addInterchangeProcessImportListener(IInterchangeProcessListener listener) {
		fInterchangeProcessListener.add(listener);
	}
	
	/**
	 * Removes the listener from the registry.
	 * 
	 * @param listener
	 *            listener to remove
	 */
	public static void removeInterchangeProcessImportListener(IInterchangeProcessListener listener) {
		fInterchangeProcessListener.remove(listener);
	}

	/**
	 * Dispatches an import event to the listeners.
	 * 
	 * @param event
	 *            event to dispatch
	 */
	public static void dispatchEvent(ImportEvent event) {
		for (IXSLTInterchangeImportListener l : fXSLTProcessListener) {
			l.noticeImportEvent(event);
		}
	}

	/**
	 * Invokes 'onInitialized()' on all registered listeners.
	 * 
	 * @param interchangeProcessInfo
	 *            the interchange process info
	 */
	public static void dispatchOnInitialized(InterchangeProcessInfo interchangeProcessInfo) {
		for(int i = -1; ++i < fInterchangeProcessListener.size(); ) {
			try {
				fInterchangeProcessListener.get(i).onInitialize(interchangeProcessInfo);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * Invokes 'beforeProcessing()' on all registered listeners.
	 * 
	 * @param sourceFile
	 *            the source file
	 * @param targetFile
	 *            the target file
	 */
	public static void dispatchOnBeforeProcessing(File sourceFile, File targetFile) {
		for(int i = -1; ++i < fInterchangeProcessListener.size(); ) {
			try {
				fInterchangeProcessListener.get(i).beforeProcessing(sourceFile, targetFile);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * Invokes 'afterProcessing()' on all registered listeners.
	 * 
	 * @param sourceFile
	 *            the source file
	 * @param targetFile
	 *            the target file
	 */
	public static void dispatchOnAfterProcessing(File sourceFile, File targetFile) {
		for(int i = -1; ++i < fInterchangeProcessListener.size(); ) {
			try {
				fInterchangeProcessListener.get(i).afterProcessing(sourceFile, targetFile);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * Invokes 'onFinished()' on all registered listeners.
	 * 
	 * @param interchangeProcessInfo
	 *            the interchange process info
	 */
	public static void dispatchOnFinished(InterchangeProcessInfo interchangeProcessInfo) {
		for(int i = -1; ++i < fInterchangeProcessListener.size(); ) {
			try {
				fInterchangeProcessListener.get(i).onFinished(interchangeProcessInfo);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}

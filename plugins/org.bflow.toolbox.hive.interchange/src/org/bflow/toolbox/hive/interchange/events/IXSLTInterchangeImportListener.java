package org.bflow.toolbox.hive.interchange.events;

/**
 * Defines an interface for import process listener.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 13/07/11
 * @version 14/12/13
 * 
 */
public interface IXSLTInterchangeImportListener {

	/**
	 * Notices the listener that an import event occurred.
	 * @param event event
	 */
	public void noticeImportEvent(ImportEvent event);
	
}

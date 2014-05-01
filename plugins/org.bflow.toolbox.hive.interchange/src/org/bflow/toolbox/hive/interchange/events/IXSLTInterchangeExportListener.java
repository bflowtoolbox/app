package org.bflow.toolbox.hive.interchange.events;

/**
 * Defines an interface for export process listener.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 23/06/11
 * @version 14/12/13
 * 
 */
public interface IXSLTInterchangeExportListener {
	
	/**
	 * Notices the listener that an export event occurred.
	 * @param event event
	 */
	public void noticeExportEvent(ExportEvent event);

}

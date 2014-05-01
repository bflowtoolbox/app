package org.bflow.toolbox.hive.addons.events;

/**
 * Defines a tool store listener interface.<p/>
 * If something happens to the tool store <code>storeUpdate()</code> is invoked.
 * @author Arian Storch
 * @since 02/10/10
 */
public interface ToolStoreListener {
	
	/**
	 * Is invoked when some changes to the tool store has been done.
	 */
	public void storeUpdate();
}

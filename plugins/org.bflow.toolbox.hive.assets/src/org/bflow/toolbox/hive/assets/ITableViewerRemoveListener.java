package org.bflow.toolbox.hive.assets;

/**
 * Defines the signature of a method that handles the remove event of a table
 * viewer.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-04-25
 *
 */
@FunctionalInterface
public interface ITableViewerRemoveListener {
	/** Is invoked when the currently selected item has to be removed. */
	void onRemoveItem();
}

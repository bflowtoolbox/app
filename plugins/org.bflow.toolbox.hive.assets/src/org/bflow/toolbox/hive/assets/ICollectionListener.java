package org.bflow.toolbox.hive.assets;

/**
 * Defines the signature of a collection listener.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-04-13
 *
 */
@FunctionalInterface
public interface ICollectionListener {
	/** Is invoked when the collection has been changed. */
	void onCollectionChanged();
}

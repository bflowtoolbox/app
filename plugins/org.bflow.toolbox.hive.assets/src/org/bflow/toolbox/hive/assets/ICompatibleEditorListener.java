package org.bflow.toolbox.hive.assets;

/**
 * Defines the signature of compatible editor listener to get notified when a
 * editor that is compatible with the asset view has been opened or closed.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-04-23
 *
 */
@FunctionalInterface
public interface ICompatibleEditorListener {
	/**
	 * Is invoked when an editor has been opened or closed. The argument
	 * {@code isCompatible} specifies if the the editor is compatible with the asset
	 * view.
	 */
	void onEditorChanged(boolean isCompatible);
}

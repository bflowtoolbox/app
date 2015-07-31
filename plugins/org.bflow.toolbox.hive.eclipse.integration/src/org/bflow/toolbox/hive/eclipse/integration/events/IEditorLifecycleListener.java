package org.bflow.toolbox.hive.eclipse.integration.events;

/**
 * Defines an listener for events which are raised during the lifecycle of an
 * editor.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 31.07.2015
 *
 */
public interface IEditorLifecycleListener {

	/**
	 * Notifies the listener that the editor is going to be initiated.
	 * 
	 * @param args
	 *            Event arguments
	 */
	void beforeInit(EditorLifecycleEventArgs args);

	/**
	 * Notifies the listener that the editor is going to finish its initiation.
	 * 
	 * @param args
	 *            Event arguments
	 */
	void afterInit(EditorLifecycleEventArgs args);

	/**
	 * Notifies the listener that the editor is going to perform a save
	 * operation.
	 * 
	 * @param args
	 *            Event arguments
	 */
	void beforeSave(EditorLifecycleEventArgs args);

	/**
	 * Notifies the listener that the editor is going to finish its save
	 * operation.
	 * 
	 * @param args
	 *            Event arguments
	 */
	void afterSave(EditorLifecycleEventArgs args);

	/**
	 * Notifies the listener that the editor is going to be closed or disposed.
	 * 
	 * @param args
	 *            Event arguments
	 */
	void onDispose(EditorLifecycleEventArgs args);
}
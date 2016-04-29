package org.bflow.toolbox.hive.eclipse.integration.events;

/**
 * Defines editor lifecycle event types.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 31.07.2015
 *
 */
public enum EEditorLifecycleEventType {
	
	/**
	 * Defines the beginning of the initiation of the editor.
	 */
	BeforeInit,
	
	/**
	 * Defines the end of the initiation of the editor.
	 */
	AfterInit,
	
	/**
	 * Defines the beginning of a save operation of the editor.
	 */
	BeforeSave,
	
	/**
	 * Defines the end of a save operation of the editor.
	 */
	AfterSave,
	
	/**
	 * Defines the closing or disposing of the editor instance. 
	 */
	Dispose

}

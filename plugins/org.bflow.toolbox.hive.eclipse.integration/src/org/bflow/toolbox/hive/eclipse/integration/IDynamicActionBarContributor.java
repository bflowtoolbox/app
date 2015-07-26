package org.bflow.toolbox.hive.eclipse.integration;

/**
 * Defines a dynamic action bar contributor.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 24.07.2015
 */
public interface IDynamicActionBarContributor {

	/**
	 * Tells the instance to update the action bar corresponding to the given
	 * editor id and class.
	 * 
	 * @param editorId
	 *            Editor id that is going to be opened
	 * @param editorClass
	 *            Editor class that is going to be opened
	 */
	void updateCurrentEditor(String editorId, Class<?> editorClass);
	
}

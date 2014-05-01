package org.bflow.toolbox.hive.interchange.mif.core;

/**
 * Defines the interface for an interchange descriptor.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 03/10/12
 * @version 18/07/13
 */
public interface IInterchangeDescriptor {

	/**
	 * Checks if the descriptor is public.
	 * 
	 * @return true, if is public
	 */
	public boolean isPublic();

	/**
	 * Returns the name of the descriptor.
	 * 
	 * @return the name
	 */
	public String getName();

	/**
	 * Returns the applicable diagram editor types.
	 * 
	 * @return the applicable diagram editor types
	 */
	public String[] getApplicableDiagramEditorTypes();

	/**
	 * Returns the file extensions.
	 * 
	 * @return the file extensions
	 */
	public String[] getFileExtensions();

	/**
	 * Returns the description.
	 * 
	 * @return the description
	 */
	public String getDescription();

	/**
	 * Returns the scripts used by the descriptor.
	 * 
	 * @return the scripts
	 */
	public IScriptDescriptor[] getScripts();

	/**
	 * Returns an instance of {@link IInterchangeProcessListener} that has been
	 * registered for this descriptor or null.
	 * 
	 * @return Instance of {@link IInterchangeProcessListener} or null
	 */
	public IInterchangeProcessListener getProcessListener();

}
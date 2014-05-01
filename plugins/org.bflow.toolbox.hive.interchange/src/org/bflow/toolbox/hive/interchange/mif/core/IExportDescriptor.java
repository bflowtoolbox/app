package org.bflow.toolbox.hive.interchange.mif.core;

/**
 * Defines the interface for an Export Descriptor.
 * 
 * @author Arian Storch
 * @since 03/10/12
 */
public interface IExportDescriptor {
	
	
	/**
	 * Checks if the export descriptor is public.
	 * 
	 * @return true, if is public
	 */
	public boolean isPublic();
	
	/**
	 * Returns the name of the export descriptor.
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
	 * Returns the scripts used by the export descriptor.
	 * 
	 * @return the scripts
	 */
	public IScriptDescriptor[] getScripts();

}

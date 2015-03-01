package org.bflow.toolbox.hive.attributes;

/**
 * Defines an interface of a diagram document editor that can be used with the
 * add-on attribute view.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 25.05.11
 */
public interface IAttributableDocumentEditor {
	
	/**
	 * Fires a property change event.
	 */
	public void firePropertyChanged(); // Can be done using reflection too
	
	/**
	 * Returns the file extension of the diagram editor input.
	 * 
	 * @return file extension of the diagram editor input
	 */
	public String getFileExtension(); // Can be done using IEditorInput

}
package org.bflow.toolbox.hive.attributes;

/**
 * Defines an interface for a diagram document editor that can be used with
 * the mitamm attribute view.
 * @author Arian Storch
 * @since 25/05/11
 */
public interface IAttributableDocumentEditor {
	
	/**
	 * Fires a property change event.
	 */
	public void firePropertyChanged();
	
	/**
	 * Returns the file extension of the diagram editor input.
	 * @return file extension of the diagram editor input
	 */
	public String getFileExtension();

}

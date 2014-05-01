package org.bflow.toolbox.hive.interchange.mif.core;

import java.util.Map;

import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;

/**
 * Defines the interface for an Attribute Provider. This provider is used to
 * link the export process with some kind of attribute support.
 * 
 * @author Arian Storch
 * @since 08/10/12
 */
public interface IAttributeProvider {

	/**
	 * Returns the attributes for the given id.
	 * 
	 * @param id
	 *            the id
	 * @return the attributes for
	 */
	public Map<String, String> getAttributesFor(DiagramEditPart diagramEditPart, String id);

	/**
	 * Returns the applicable diagram editor file extensions.
	 * 
	 * @return the applicable diagram editor file extensions
	 */
	public String[] getApplicableDiagramEditorFileExtensions();
}

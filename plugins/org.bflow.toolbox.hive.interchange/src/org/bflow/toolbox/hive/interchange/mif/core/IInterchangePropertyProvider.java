package org.bflow.toolbox.hive.interchange.mif.core;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;

/**
 * Defines an interface for a value provider that can be used to serve the
 * interchange framework with additional values that aren't available from the
 * standard model digger and its meta model.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2013-05-02
 * 
 */
public interface IInterchangePropertyProvider {

	/**
	 * Return the value of the given eObject or its graphical edit part for the
	 * given property name. If no value exists so return null.
	 * 
	 * @param propertyName
	 *            the property name
	 * @param eObj
	 *            the eobject
	 * @param graphicalEditPart
	 *            the graphical edit part
	 * @param id
	 *            the id
	 * @return the property value
	 */
	public Object getPropertyValue(String propertyName, EObject eObj, IGraphicalEditPart graphicalEditPart, String id);

	/**
	 * Returns the applicable diagram editor file extensions.
	 * 
	 * @return the applicable diagram editor file extensions
	 */
	public String[] getApplicableDiagramEditorFileExtensions();
}

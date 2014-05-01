package org.bflow.toolbox.contributions.addons;

import oepc.BusinessObject;

import org.bflow.toolbox.hive.interchange.mif.core.IInterchangePropertyProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;

/**
 * Implements {@link IInterchangePropertyProvider} to provide values for oEPC model elements.
 * 
 * @author Arian Storch
 * @since 03/05/13
 *
 */
public class OEPCPropertyValueProvider implements IInterchangePropertyProvider {
	
	/** The Constant ApplicableDiagramEditorFileExtensions. */
	private static final String ApplicableDiagramEditorFileExtensions[] = { "oepc" };

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IPropertyProvider#getPropertyValue(java.lang.String, org.eclipse.emf.ecore.EObject, org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart, java.lang.String)
	 */
	@Override
	public Object getPropertyValue(String propertyName, EObject eObj, IGraphicalEditPart graphicalEditPart, String id) {
		// Can only be part of BusinessObjects
		if(propertyName.equalsIgnoreCase("BusinessAttributes") && (eObj instanceof BusinessObject)) {
			BusinessObject businessObject = (BusinessObject)eObj;
			return businessObject.getAttributes();
		}
		// Can only be part of BusinessObjects
		if(propertyName.equalsIgnoreCase("BusinessMethods") && (eObj instanceof BusinessObject)) {
			BusinessObject businessObject = (BusinessObject)eObj;
			return businessObject.getMethods();
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IPropertyProvider#getApplicableDiagramEditorFileExtensions()
	 */
	@Override
	public String[] getApplicableDiagramEditorFileExtensions() {
		return ApplicableDiagramEditorFileExtensions;
	}

}

package org.bflow.toolbox.hive.interchange.utils;

import org.apache.commons.lang.NotImplementedException;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangePropertyProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;

/**
 * Defines a hub for a collection of {@link IInterchangePropertyProvider} which
 * is used to handle multiple providers at once.
 * 
 * @author Arian Storch
 * @since 02/05/13
 * 
 */
public class PropertyProviderHub implements IInterchangePropertyProvider {

	/** The instance. */
	private static PropertyProviderHub instance;

	/**
	 * Returns an instance of PropertyProviderHub which operates on the given
	 * providers.
	 * 
	 * @param propertyProvider
	 *            the property provider
	 * @return single instance of PropertyProviderHub
	 */
	public static PropertyProviderHub getInstance(IInterchangePropertyProvider[] propertyProvider) {
		if (instance == null) {
			instance = new PropertyProviderHub(propertyProvider);
		} else {
			instance.propertyProvider = propertyProvider;
		}

		return instance;
	}

	/** The property provider. */
	private IInterchangePropertyProvider[] propertyProvider;

	/**
	 * Instantiates a new property provider hub.
	 * 
	 * @param propertyProvider
	 *            the property provider
	 */
	public PropertyProviderHub(IInterchangePropertyProvider[] propertyProvider) {
		super();
		this.propertyProvider = propertyProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.interchange.mif.core.IInterchangePropertyProvider#
	 * getPropertyValue(java.lang.String, org.eclipse.emf.ecore.EObject,
	 * org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart,
	 * java.lang.String)
	 */
	@Override
	public Object getPropertyValue(String propertyName, EObject eObj,
			IGraphicalEditPart graphicalEditPart, String id) {
		if (propertyProvider == null || propertyProvider.length == 0)
			return null;

		for (int i = -1; ++i < propertyProvider.length;) {
			Object value = propertyProvider[i].getPropertyValue(propertyName,
					eObj, graphicalEditPart, id);
			if (value != null)
				return value;
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.interchange.mif.core.IInterchangePropertyProvider#
	 * getApplicableDiagramEditorFileExtensions()
	 */
	@Override
	public String[] getApplicableDiagramEditorFileExtensions() {
		throw new NotImplementedException("This should never happen!");
	}

}

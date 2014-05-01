package org.bflow.toolbox.hive.interchange.mif.impl;

import java.util.HashMap;
import java.util.Map;

import org.bflow.toolbox.hive.interchange.mif.core.IEdge;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangePropertyProvider;
import org.bflow.toolbox.hive.interchange.mif.core.IShape;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;

/**
 * Implements {@link IEdge}.
 * 
 * @author Arian Storch
 * @since 01/10/12
 * @version 02/05/13
 */
public class EdgeAdapter implements IEdge {
	
	/** The connection edit part. */
	private ConnectionEditPart connectionEditPart;	
	
	/** The eobject. */
	private EObject eObject;
	
	/** The figure. */
	@SuppressWarnings("unused")
	private IFigure figure;
	
	/** The attribute map. */
	private Map<String, String> attributeMap;
	
	/** The source shape. */
	private IShape sourceShape;
	
	/** The target shape. */
	private IShape targetShape;
	
	private IInterchangePropertyProvider propertyProvider;

	/**
	 * Instantiates a new edge adapter.
	 * 
	 * @param connectionEditPart
	 *            the connection edit part
	 * @param source
	 *            the source
	 * @param target
	 *            the target
	 * @param attributes
	 *            the attributes
	 */
	public EdgeAdapter(ConnectionEditPart connectionEditPart, IShape source, IShape target,
			Map<String, String> attributes, IInterchangePropertyProvider propertyProvider) {
		super();
		this.connectionEditPart = connectionEditPart;
		this.eObject = connectionEditPart.resolveSemanticElement();
		this.figure = connectionEditPart.getFigure();
		this.attributeMap = (attributes == null ? new HashMap<String, String>() : attributes);
		this.sourceShape = source;
		this.targetShape = target;
		this.propertyProvider = propertyProvider;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IAttributeContainer#getAttributes()
	 */
	@Override
	public Map<String, String> getAttributes() {
		return attributeMap;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IIdentifiable#getId()
	 */
	@Override
	public String getId() {
		String proxyId = EMFCoreUtil.getProxyID(eObject);
		return proxyId;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IIdentifiable#getType()
	 */
	@Override
	public Object getType() {
		// this is a string at the moment but can change it the future
		Object obj = eObject.eClass().getInstanceTypeName(); 
		return obj;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String result = String.format(TOSTRINGFORMAT, getType().toString(), getId());
		return result;
	}
	
	/** The Constant TOSTRINGFORMAT. */
	private static final String TOSTRINGFORMAT = "%s %s";

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IEdge#getSource()
	 */
	@Override
	public IShape getSource() {
		return sourceShape;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IEdge#getTarget()
	 */
	@Override
	public IShape getTarget() {
		return targetShape;
	}
	
	@Override
	public Object get(String propertyName) {
		if(propertyProvider == null) {
			return null;
		}
		
		Object value = propertyProvider.getPropertyValue(propertyName, eObject, connectionEditPart, null);
		return value;
	}
}

package org.bflow.toolbox.hive.interchange.mif.impl;

import java.util.HashMap;
import java.util.Map;

import org.bflow.toolbox.hive.interchange.mif.core.IEdge;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangePropertyProvider;
import org.bflow.toolbox.hive.interchange.mif.core.IShape;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;

/**
 * Implements {@link IEdge}.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2012-10-01
 * @version 2013-05-02
 * 			2018-10-31 Id and semantic element are now passed via ctor
 */
public class EdgeAdapter implements IEdge {
	
	/** Element id */
	private String _id;
	
	/** Semantic element */
	private EObject _semanticElement;

	/** Connection edit part. */
	private ConnectionEditPart _connectionEditPart;	
	
	/** Figure */
	@SuppressWarnings("unused")
	private IFigure _figure;
	
	/** Attribute map */
	private Map<String, String> _attributeMap;
	
	/** Source shape */
	private IShape _sourceShape;
	
	/** Target shape */
	private IShape _targetShape;
	
	/** Property provider */
	private IInterchangePropertyProvider _propertyProvider;

	/**
	 * Instantiates a new edge adapter.
	 * 
	 * @param id                 Id
	 * 
	 * @param semanticElement    Semantic element
	 * 
	 * @param connectionEditPart Connection edit part
	 * @param source             Source shape
	 * @param target             Target shape
	 * @param attributes         Attributes
	 */
	public EdgeAdapter(String id, EObject semanticElement, 
			ConnectionEditPart connectionEditPart, 
			IShape source, IShape target,
			Map<String, String> attributes, IInterchangePropertyProvider propertyProvider) {
		super();
		_id = id;
		_semanticElement = semanticElement;
		_connectionEditPart = connectionEditPart;
		_figure = connectionEditPart.getFigure();
		_attributeMap = (attributes == null ? new HashMap<String, String>() : attributes);
		_sourceShape = source;
		_targetShape = target;
		_propertyProvider = propertyProvider;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IAttributeContainer#getAttributes()
	 */
	@Override
	public Map<String, String> getAttributes() {
		return _attributeMap;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IIdentifiable#getId()
	 */
	@Override
	public String getId() {
		return _id;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IIdentifiable#getType()
	 */
	@Override
	public Object getType() {
		// this is a string at the moment but can change it the future
		Object obj = _semanticElement.eClass().getInstanceTypeName(); 
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
		return _sourceShape;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IEdge#getTarget()
	 */
	@Override
	public IShape getTarget() {
		return _targetShape;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.hive.interchange.mif.core.IVelocityValueProvider#get(java.lang.String)
	 */
	@Override
	public Object get(String propertyName) {
		if (_propertyProvider == null) {
			return null;
		}
		
		Object value = _propertyProvider.getPropertyValue(propertyName, _semanticElement, _connectionEditPart, null);
		return value;
	}
}

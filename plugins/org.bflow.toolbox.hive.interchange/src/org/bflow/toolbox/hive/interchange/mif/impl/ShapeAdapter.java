package org.bflow.toolbox.hive.interchange.mif.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bflow.toolbox.hive.interchange.mif.core.IInterchangePropertyProvider;
import org.bflow.toolbox.hive.interchange.mif.core.IShape;
import org.bflow.toolbox.hive.libs.aprogu.lang.Cast;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.ShapeStyle;
import org.eclipse.swt.graphics.Image;

/**
 * Implements {@link IShape}.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2012-10-01
 * @version 2014-09-02
 * 			2018-10-31 Id and semantic element are now passed via ctor
 */
public class ShapeAdapter implements IShape {
	
	/** Element id */
	private String _id;
	
	/** Semantic element */
	private EObject _semanticElement;
	
	/** Graphical edit part. */
	private IGraphicalEditPart _graphicalEditPart;	
	
	/** Figure */
	private IFigure _figure;
	
	/** Attribute map */
	private Map<String, String> _attributeMap;
	
	/** The property provider. */
	private IInterchangePropertyProvider _propertyProvider;
	
	/**
	 * Instantiates a new shape adapter.
	 *
	 * @param id               Element id
	 * @param semanticElement  Semantic element
	 * @param editPart         Edit part
	 * @param attributes       Attributes
	 * @param propertyProvider Property provider
	 */
	public ShapeAdapter(String id, EObject semanticElement, 
			IGraphicalEditPart editPart, 
			Map<String, String> attributes, IInterchangePropertyProvider propertyProvider) {
		super();
		_id = id;
		_semanticElement = semanticElement;
		_graphicalEditPart = editPart;		
		_figure = _graphicalEditPart.getFigure();
		_attributeMap = (attributes == null ? new HashMap<String, String>() : attributes);
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
	 * @see org.bflow.toolbox.interchange.mif.core.IShape#getHeight()
	 */
	@Override
	public int getHeight() {
		return _figure.getBounds().height;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IShape#getImage()
	 */
	@Override
	public Image getImage() {
		// TODO add image support
		throw new RuntimeException("Not implemented yet!");
		//return null;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IShape#getName()
	 */
	@Override
	public String getName() {
		String name = EMFCoreUtil.getName(_semanticElement);
		
		// If the eObject is a raw GMF shape then we use the description 
		if (_semanticElement instanceof Shape) {
			Shape shape = (Shape) _semanticElement;
			name = shape.getDescription();
		}
		
		// Absolute(?) fallback, try to get the name from the internal shape style
		if (_semanticElement instanceof Node) {
			Node node = (Node) _semanticElement;
			List<?> styles = node.getStyles();
			for (int i = -1; ++i != styles.size();) {
				Object style = styles.get(i);
				ShapeStyle shapeStyle = Cast.as(ShapeStyle.class, style);
				if (shapeStyle != null) {
					name = shapeStyle.getDescription();
					break;
				}
			}
		}
		
		return name;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IShape#getWidth()
	 */
	@Override
	public int getWidth() {
		return _figure.getBounds().width;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IShape#getX()
	 */
	@Override
	public int getX() {
		return _figure.getBounds().x;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IShape#getY()
	 */
	@Override
	public int getY() {
		return _figure.getBounds().y;
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
		
		// If the eObject is a raw GMF shape then we add the concrete type
		if (_semanticElement instanceof Shape) {
			obj = ((String)obj).concat(String.format("+%s", ((Shape) _semanticElement).getType()));
		}
		
		return obj;
	}
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IVelocityValueProvider#get(java.lang.String)
	 */
	@Override
	public Object get(String propertyName) {
		if (_propertyProvider == null) {
			return null;
		}
		
		Object value = _propertyProvider.getPropertyValue(propertyName, _semanticElement, _graphicalEditPart, _id);
		return value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String result = String.format(TOSTRINGFORMAT, 
				getType().toString(), getName(), getId(),
				getX(), getY(), getWidth(), getHeight());
		return result;
	}
	
	/** The Constant TOSTRINGFORMAT. */
	private static final String TOSTRINGFORMAT = "%s %s %s (x: %d y: %d width: %d, height: %d)";
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ShapeAdapter)) {
			return false;
		}
		ShapeAdapter other = (ShapeAdapter) obj;
		return other._semanticElement.equals(_semanticElement);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return _semanticElement.hashCode();
	}
}

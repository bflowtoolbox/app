package org.bflow.toolbox.hive.interchange.mif.impl;

import java.util.HashMap;
import java.util.Map;

import org.bflow.toolbox.hive.interchange.mif.core.IInterchangePropertyProvider;
import org.bflow.toolbox.hive.interchange.mif.core.IShape;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.swt.graphics.Image;

/**
 * Implements {@link IShape}.
 * 
 * @author Arian Storch
 * @since 01/10/12
 * @version 02/09/14
 */
public class ShapeAdapter implements IShape {
	
	/** The graphical edit part. */
	private IGraphicalEditPart graphicalEditPart;
	
	/** The e object. */
	private EObject eObject;
	
	/** The figure. */
	private IFigure figure;
	
	/** The attribute map. */
	private Map<String, String> attributeMap;

	/** The id. */
	private String id;
	
	/** The property provider. */
	private IInterchangePropertyProvider propertyProvider;
	
	/**
	 * Instantiates a new shape adapter.
	 *
	 * @param editPart the edit part
	 * @param attributes the attributes
	 * @param propertyProvider the property provider
	 */
	public ShapeAdapter(IGraphicalEditPart editPart, Map<String, String> attributes, IInterchangePropertyProvider propertyProvider) {
		super();
		this.graphicalEditPart = editPart;
		this.eObject = graphicalEditPart.resolveSemanticElement();
		
		// EditParts provided by GMF don't ever have semantic elements
		if (this.eObject == null) {
			this.eObject = (EObject) editPart.getModel();
		}
		
		this.figure = graphicalEditPart.getFigure();
		this.attributeMap = (attributes == null ? new HashMap<String, String>() : attributes);
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
	 * @see org.bflow.toolbox.interchange.mif.core.IShape#getHeight()
	 */
	@Override
	public int getHeight() {
		return figure.getBounds().height;
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
		String name = EMFCoreUtil.getName(eObject);
		
		// If the eObject is a raw GMF shape then we use the description 
		if (eObject instanceof Shape) {
			Shape shape = (Shape) eObject;
			name = shape.getDescription();
		}
		
		return name;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IShape#getWidth()
	 */
	@Override
	public int getWidth() {
		return figure.getBounds().width;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IShape#getX()
	 */
	@Override
	public int getX() {
		return figure.getBounds().x;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IShape#getY()
	 */
	@Override
	public int getY() {
		return figure.getBounds().y;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IIdentifiable#getId()
	 */
	@Override
	public String getId() {
		if(id == null) {
			id = EMFCoreUtil.getProxyID(eObject);
		}
		
		return id;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IIdentifiable#getType()
	 */
	@Override
	public Object getType() {
		// this is a string at the moment but can change it the future
		Object obj = eObject.eClass().getInstanceTypeName(); 
		
		// If the eObject is a raw GMF shape then we add the concrete type
		if (eObject instanceof Shape) {
			obj = ((String)obj).concat(String.format("+%s", ((Shape) eObject).getType()));
		}
		
		return obj;
	}
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IVelocityValueProvider#get(java.lang.String)
	 */
	@Override
	public Object get(String propertyName) {
		if(propertyProvider == null) {
			return null;
		}
		
		Object value = propertyProvider.getPropertyValue(propertyName, eObject, graphicalEditPart, id);
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
		if(!(obj instanceof ShapeAdapter)) {
			return false;
		}
		ShapeAdapter other = (ShapeAdapter)obj;
		return other.eObject.equals(eObject);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return eObject.hashCode();
	}
}

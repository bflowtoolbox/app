package org.bflow.toolbox.hive.modelnavigator.model;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.swt.graphics.Image;

/**
 * Defines an item (flow) of a {@link FlowGraph}. Every item has image of an edge and shape,
 * a name for the shape and the associated {@link IGraphicalEditPart} that is mapped by this item.
 * Each item can start a new Flow Graph. If there is one <code>getNext()</code> will return it.
 * 
 * @author Arian Storch
 * @since 03/08/12
 *
 */
public class FlowGraphItem {
	
	private Image edgeImage;
	private Image shapeImage;
	private String name;
	private IGraphicalEditPart graphicalEditPart;
	
	private FlowGraph next = null;
	
	/**
	 * Creates a new instance.
	 * 
	 * @param edgeImage Image of the edge
	 * @param shapeImage Image of the shape
	 * @param name Name of the shape
	 * @param graphicalEditPart Associated graphical edit part 
	 */
	public FlowGraphItem(Image edgeImage, Image shapeImage, String name,
			IGraphicalEditPart graphicalEditPart) {
		super();
		this.edgeImage = edgeImage;
		this.shapeImage = shapeImage;
		this.name = name;
		this.graphicalEditPart = graphicalEditPart;
	}

	/**
	 * Returns the image of the edge.
	 * @return The image of the edge
	 */
	public Image getEdgeImage() {
		return edgeImage;
	}

	/**
	 * Sets the image of the edge.
	 * @param edgeImage Image of the edge
	 */
	public void setEdgeImage(Image edgeImage) {
		this.edgeImage = edgeImage;
	}

	/**
	 * Returns the image of the shape.
	 * @return Image of the shape
	 */
	public Image getShapeImage() {
		return shapeImage;
	}

	/**
	 * Sets the image of the shape.
	 * @param shapeImage Image of the shape
	 */
	public void setShapeImage(Image shapeImage) {
		this.shapeImage = shapeImage;
	}

	/**
	 * Returns the name of the shape.
	 * @return The name of the shape
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the shape.
	 * @param name Name of the shape
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the associated graphical edit part.
	 * @return The associated graphical edit part
	 */
	public IGraphicalEditPart getGraphicalEditPart() {
		return graphicalEditPart;
	}

	/**
	 * Sets the associated graphical edit part.
	 * @param graphicalEditPart Associated graphical edit part
	 */
	public void setGraphicalEditPart(IGraphicalEditPart graphicalEditPart) {
		this.graphicalEditPart = graphicalEditPart;
	}
	
	/**
	 * Returns the next Flow Graph or null if none exists.
	 * @return Next Flow Graph or null
	 */
	public FlowGraph getNext() {
		return next;
	}
	
	/**
	 * Sets the next Flow Graph.
	 * @param next Next Flow Graph
	 */
	public void setNext(FlowGraph next) {
		this.next = next;
	}
	
	@Override
	public String toString() {
		String text = String.format("%s %s --> %s", 
				(name != null ? name : ""),
				(graphicalEditPart != null ? graphicalEditPart.toString() : ""),
				(next != null ? next.toString() : "NULL"));
		
		return text;
	}

}

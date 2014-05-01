package org.bflow.toolbox.hive.modelnavigator.model;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.swt.graphics.Image;

/**
 * Provides a table row data structure that can be used to map a
 * {@link FlowGraph} into a table. Currently the data structure is not generic.
 * That means only 4 columns are supported. In other words, only Flow Graphs
 * with maximal one following Flow Graph can be modeled with this class.
 * <p/>
 * 
 * The structure of one row is like that:<br/>
 * Image of 1st edge | Image of the 1st shape + Name of the 2nd shape |<br/>
 * Image of 2nd edge | Image of the 2nd shape + Name of the 2nd shape
 * 
 * @author Arian Storch
 * @since 03/08/12
 * 
 */
public class FlowGraphTableRow {

	private Image edgeImage1;
	private Image nodeImage1;
	private String name1;
	private IGraphicalEditPart editPart1;

	private Image edgeImage2;
	private Image nodeImage2;
	private String name2;
	private IGraphicalEditPart editPart2;

	/**
	 * Returns the image of the 1st edge.
	 * 
	 * @return the image of the 1st edge
	 */
	public Image getEdgeImage1() {
		return edgeImage1;
	}

	/**
	 * Sets the image of the 1st edge.
	 * 
	 * @param edgeImage1
	 *            Image of the 1st edge
	 */
	public void setEdgeImage1(Image edgeImage1) {
		this.edgeImage1 = edgeImage1;
	}

	/**
	 * Returns the image of the 1st node.
	 * 
	 * @return the image of the 1st node
	 */
	public Image getNodeImage1() {
		return nodeImage1;
	}

	/**
	 * Sets the image of the 1st node.
	 * 
	 * @param nodeImage1
	 *            Image of the 1st node
	 */
	public void setNodeImage1(Image nodeImage1) {
		this.nodeImage1 = nodeImage1;
	}

	/**
	 * Returns the name of the 1st graphical edit part.
	 * @return The name of the 1st graphical edit part
	 */
	public String getName1() {
		return name1;
	}

	/**
	 * Sets the name of the 1st graphical edit part.
	 * @param name2 Name of the 1st graphical edit part
	 */
	public void setName1(String name1) {
		this.name1 = name1;
	}

	/**
	 * Returns the 1st graphical edit part.
	 * @return The 1st graphical edit part
	 */
	public IGraphicalEditPart getEditPart1() {
		return editPart1;
	}

	/**
	 * Sets the 1st graphical edit part.
	 * @param editPart1 1st graphical edit part
	 */
	public void setEditPart1(IGraphicalEditPart editPart1) {
		this.editPart1 = editPart1;
	}

	/**
	 * Returns the image of the 2nd edge.
	 * 
	 * @return the image of the 2nd edge
	 */
	public Image getEdgeImage2() {
		return edgeImage2;
	}

	/**
	 * Sets the image of the 2st edge.
	 * 
	 * @param edgeImage2
	 *            Image of the 2nd edge
	 */
	public void setEdgeImage2(Image edgeImage2) {
		this.edgeImage2 = edgeImage2;
	}

	/**
	 * Returns the image of the 2nd node.
	 * 
	 * @return the image of the 2nd node
	 */
	public Image getNodeImage2() {
		return nodeImage2;
	}

	/**
	 * Sets the image of the 2nd node.
	 * 
	 * @param nodeImage2
	 *            Image of the 2nd node
	 */
	public void setNodeImage2(Image nodeImage2) {
		this.nodeImage2 = nodeImage2;
	}

	/**
	 * Returns the name of the 2nd graphical edit part.
	 * @return The name of the 2nd graphical edit part
	 */
	public String getName2() {
		return name2;
	}

	/**
	 * Sets the name of the 2nd graphical edit part.
	 * @param name2 Name of the 2nd graphical edit part
	 */
	public void setName2(String name2) {
		this.name2 = name2;
	}

	/**
	 * Returns the 2nd graphical edit part.
	 * @return The 2nd graphical edit part
	 */
	public IGraphicalEditPart getEditPart2() {
		return editPart2;
	}

	/**
	 * Sets the 2nd graphical edit part.
	 * @param editPart2 2nd graphical edit part
	 */
	public void setEditPart2(IGraphicalEditPart editPart2) {
		this.editPart2 = editPart2;
	}

	/**
	 * Creates and returns a full copy of this row.
	 * 
	 * @return Newly created row with copied data
	 */
	public FlowGraphTableRow clone() {
		FlowGraphTableRow clone = new FlowGraphTableRow();
		clone.edgeImage1 = edgeImage1;
		clone.edgeImage2 = edgeImage2;
		clone.editPart1 = editPart1;
		clone.editPart2 = editPart2;
		clone.name1 = name1;
		clone.name2 = name2;
		clone.nodeImage1 = nodeImage1;
		clone.nodeImage2 = nodeImage2;

		return clone;
	}

	/**
	 * Overrides the 3rd and 4th column informations (image, name, edit part) of
	 * this row with these from the given row.
	 * 
	 * @param row
	 *            Informations will be copied from this row.
	 */
	public void merge(FlowGraphTableRow row) {
		edgeImage2 = row.edgeImage2;
		editPart2 = row.editPart2;
		name2 = row.name2;
		nodeImage2 = row.nodeImage2;
	}

	/**
	 * Copies the informations (image, name, edit part) from the 1st and 2nd
	 * column to 3rd and 4th. Afterwards the original informations will be set
	 * to null.
	 */
	public void forward() {
		edgeImage2 = edgeImage1;
		edgeImage1 = null;

		editPart2 = editPart1;
		editPart1 = null;

		name2 = name1;
		name1 = null;

		nodeImage2 = nodeImage1;
		nodeImage1 = null;
	}
}

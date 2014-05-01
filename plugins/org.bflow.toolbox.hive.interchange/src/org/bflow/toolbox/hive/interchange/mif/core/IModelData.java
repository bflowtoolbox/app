package org.bflow.toolbox.hive.interchange.mif.core;


/**
 * Defines some model data.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 03/10/12
 */
public interface IModelData {

	/**
	 * Returns the model.
	 * 
	 * @return the model
	 */
	public IModel getModel();

	/**
	 * Returns the edges.
	 * 
	 * @return the edges
	 */
	public IEdge[] getEdges();

	/**
	 * Returns the shapes.
	 * 
	 * @return the shapes
	 */
	public IShape[] getShapes();
}
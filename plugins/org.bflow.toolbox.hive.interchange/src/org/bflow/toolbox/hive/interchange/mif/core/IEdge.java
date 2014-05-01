package org.bflow.toolbox.hive.interchange.mif.core;

/**
 * Defines an edge.
 * 
 * @author Arian Storch
 * @since 01/10/12
 */
public interface IEdge extends IAttributeContainer, IIdentifiable, IVelocityValueProvider {
	
	/**
	 * Returns the source shape.
	 * 
	 * @return the source shape
	 */
	public IShape getSource();
	
	/**
	 * Returns the target shape.
	 * 
	 * @return the target shape
	 */
	public IShape getTarget();

}

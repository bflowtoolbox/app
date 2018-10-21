package org.bflow.toolbox.hive.interchange.mif.core;

/**
 * Describes an edge.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2012-10-01
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

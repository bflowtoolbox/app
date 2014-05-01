package org.bflow.toolbox.hive.interchange.mif.core;

import org.eclipse.swt.graphics.Image;


/**
 * Defines a shape.
 * 
 * @author Arian Storch
 * @since 08/10/12
 */
public interface IShape extends IAttributeContainer, IIdentifiable, IVelocityValueProvider {
	
	/**
	 * Returns the name.
	 * 
	 * @return the name
	 */
	public String getName();
	
	/**
	 * Returns the width.
	 * 
	 * @return the width
	 */
	public int getWidth();
	
	/**
	 * Returns the height.
	 * 
	 * @return the height
	 */
	public int getHeight();
	
	/**
	 * Returns the x.
	 * 
	 * @return the x
	 */
	public int getX();
	
	/**
	 * Returns the y.
	 * 
	 * @return the y
	 */
	public int getY();
	
	/**
	 * Returns the image.
	 * 
	 * @return the image
	 */
	public Image getImage();	
}

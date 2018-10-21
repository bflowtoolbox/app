package org.bflow.toolbox.hive.interchange.mif.core;

import org.eclipse.swt.graphics.Image;

/**
 * Describes a shape.
 * 
 * @author Arian Storch
 * @since 2012-10-08
 * @version 2018-10-21 Added INameable inheritance
 */
public interface IShape extends IAttributeContainer, IIdentifiable, INameable, IVelocityValueProvider {	
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

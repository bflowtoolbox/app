package org.bflow.toolbox.extensions.edit.parts;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;


/**
 * Calculates the mid of the source/target side from the nodes, which will be
 * related by an connection.
 * The methods return this centered point, so the connection has an point to 
 * anchor.
 *
 */
public class CenteredAnchor extends AbstractConnectionAnchor {

	
	/**
	 * Create this anchor.
	 * @param source
	 */
	public CenteredAnchor(IFigure source) {
		super(source);
	}

	
	/**
	 * Calculates the location of the anchor.
	 * @param reference
	 */
	public Point getLocation(Point reference) {
		
		Rectangle r = Rectangle.SINGLETON;
		r.setBounds(getOwner().getBounds());
		getOwner().translateToAbsolute(r);
		
		float centerX = r.x + 0.5f * r.width;
		float centerY = r.y + 0.5f * r.height;
		
		//This avoids divide-by-zero
		if (r.isEmpty() || (reference.x == 
			(int)centerX && reference.y == (int)centerY))
			return new Point((int)centerX, (int)centerY);  


		float dx = reference.x - centerX;
		float dy = reference.y - centerY;
		
		//r.width, r.height, dx, and dy are guaranteed to be non-zero. 
		float scale = 0.5f / Math.max(Math.abs(dx) / r.width, Math.abs(dy) 
				/ r.height);

		dx *= scale;
		dy *= scale;
		centerX += dx;
		centerY += dy;
		
		centerX = Math.round(centerX); 
		centerY = Math.round(centerY);
			
		if (r.getTopLeft().y == centerY) { // up
			centerX = r.getTop().x;
		} else if (r.getBottomLeft().y == centerY) { // down
			centerX = r.getBottom().x;
		} else if (r.getLeft().x == centerX) { // left
			centerY = r.getLeft().y;
		} else { // right
			centerY = r.getRight().y;
		}
		
		return new Point(Math.round(centerX), Math.round(centerY));
	}	
}

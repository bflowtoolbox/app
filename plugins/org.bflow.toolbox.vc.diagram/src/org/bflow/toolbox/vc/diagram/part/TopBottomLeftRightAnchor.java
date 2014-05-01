package org.bflow.toolbox.vc.diagram.part;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class TopBottomLeftRightAnchor extends AbstractConnectionAnchor {

	public TopBottomLeftRightAnchor(IFigure source) {
		super(source);
	}

	public Point getLocation(Point reference) {
		
		Rectangle r = Rectangle.SINGLETON;
		r.setBounds(getOwner().getBounds());
		getOwner().translateToAbsolute(r);
		
		float centerX = r.x + 0.5f * r.width;
		float centerY = r.y + 0.5f * r.height;
		
		if (r.isEmpty() || (reference.x == (int)centerX && reference.y == (int)centerY))
			return new Point((int)centerX, (int)centerY);  //This avoids divide-by-zero

		float dx = reference.x - centerX;
		float dy = reference.y - centerY;
		
		//r.width, r.height, dx, and dy are guaranteed to be non-zero. 
		float scale = 0.5f / Math.max(Math.abs(dx) / r.width, Math.abs(dy) / r.height);

		dx *= scale;
		dy *= scale;
		centerX += dx;
		centerY += dy;
		
		centerX = Math.round(centerX); 
		centerY = Math.round(centerY);
			
		if (r.getTopLeft().y == centerY) { // oben
			centerX = r.getTop().x;
		} else if (r.getBottomLeft().y == centerY) { // unten
			centerX = r.getBottom().x;
		} else if (r.getLeft().x == centerX) { // links
			centerY = r.getLeft().y;
		} else { // rechts
			centerY = r.getRight().y;
		}
		
		return new Point(Math.round(centerX), Math.round(centerY));
	}	
}

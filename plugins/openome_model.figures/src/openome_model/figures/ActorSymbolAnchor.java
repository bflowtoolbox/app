package openome_model.figures;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;


/**
 * This anchor class is used for collapsed actors.
 * When there is a link/connecting connected to an intention inside
 * of an actor/agent/position/role, and it collapses, the link must
 * be redirected to point to the actor symbol.. which is simply
 * a perfect circle, but when we use the EllipseAnchor, the anchor
 * point is too far into the circle, so this class is just a modified
 * version of the EllipseAnchor so that it points more 'out' a little.
 * @author Kelvin Ng
 *
 */
public class ActorSymbolAnchor extends AbstractConnectionAnchor {

	/**
	 * @see org.eclipse.draw2d.AbstractConnectionAnchor#AbstractConnectionAnchor()
	 */
	public ActorSymbolAnchor() { }

	/**
	 * @see org.eclipse.draw2d.AbstractConnectionAnchor#AbstractConnectionAnchor(IFigure)
	 */
	public ActorSymbolAnchor(IFigure owner) {
		super(owner);
	}

	/**
	 * Returns a point on the ellipse (defined by the owner's bounding box) where the
	 * connection should be anchored.
	 * @see org.eclipse.draw2d.ConnectionAnchor#getLocation(Point)
	 */
	public Point getLocation(Point reference) {
		Rectangle r = Rectangle.SINGLETON;
		r.setBounds(getOwner().getBounds());
		r.translate(-1, -1);
		r.resize(1, 1);
		getOwner().translateToAbsolute(r);
		
		Point ref = r.getCenter().negate().translate(reference);
		
		if (ref.x == 0)
			return new Point(reference.x, (ref.y > 0) ? r.bottom() : r.y);
		if (ref.y == 0)
			return new Point((ref.x > 0) ? r.right() : r.x, reference.y);
		
		float dx = (ref.x > 0) ? 0.5f : -0.5f;
		float dy = (ref.y > 0) ? 0.5f : -0.5f;
		
		// THE MODIFICATION TO ELLIPSEANCHOR !!
		// push the points out just a tad bit
		dx = dx * (float)1.1;
		dy = dy * (float)1.1;
		  
		// ref.x, ref.y, r.width, r.height != 0 => safe to proceed
		  
		float k = (float)(ref.y * r.width) / (ref.x * r.height);
		k = k * k;
		  
		return r.getCenter().translate((int)(r.width * dx / Math.sqrt(1 + k)),
		                                (int)(r.height * dy / Math.sqrt(1 + 1 / k)));
	}
}


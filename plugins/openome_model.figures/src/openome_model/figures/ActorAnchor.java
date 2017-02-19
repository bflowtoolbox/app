package openome_model.figures;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class ActorAnchor extends AbstractConnectionAnchor {

/**
 * @see org.eclipse.draw2d.AbstractConnectionAnchor#AbstractConnectionAnchor()
 */
public ActorAnchor() { }

/**
 * The current zoom level we are using to calculate anchor coordinates
 */
private double zoom;

/**
 * updates zoom value
 */
public void updateZoom(){
	// Nothing here, it's up to subclass to implement
}

public void setZoom(double zoom) {
	this.zoom = zoom;
}

public double getZoom() {
	return zoom;
}


/**
 * @see org.eclipse.draw2d.AbstractConnectionAnchor#AbstractConnectionAnchor(IFigure)
 */
public ActorAnchor(IFigure owner) {
	super(owner);
}

/**
 * Returns a point on the ellipse (defined by the owner's bounding box) where the
 * connection should be anchored.
 * @see org.eclipse.draw2d.ConnectionAnchor#getLocation(Point)
 */
public Point getLocation(Point reference) {
	updateZoom();
	Rectangle r = Rectangle.SINGLETON;
	r.setBounds(getOwner().getBounds());
	r.translate(-1, -1);
	r.resize(1, 1);
	getOwner().translateToAbsolute(r);
	
	Point ref = r.getCenter().negate().translate(reference);
	
	double dx = (ref.preciseX() > 0) ? 0.5d : -0.5d;
	double dy = (ref.preciseY() > 0) ? 0.5d : -0.5d;
	  
	// ref.x, ref.y, r.width, r.height != 0 => safe to proceed
	  
	double k = (ref.preciseY() * r.preciseWidth()) / (ref.preciseX() * r.preciseHeight());
	k = k * k;
	//////////////////////////////////////////////////////////////////////
	
	/*
	 * The way the Actor anchor works is:
	 * we know that the 'entire' actor figure contains
	 * the actor figure, and the actor bubble, so first, we find the centre
	 * point of the actor FIGURE, then, we use the ellipse anchor code to simply
	 * translate outwards from the that centre point. 
	 * 
	 * So in the end, the outline of the anchors
	 * is the same thing as the ellipe anchor, but the smaller and
	 * the centre point is located at the centre of the actor figure 
	 */
	
	// we will keep the x and y translation equally proportional, ie, even
	// if the 'entire' actor figure (figure + bubble) is resized to a non-perfect
	// square, the anchor outline will remain a perfect circle. To do this,
	// we calculate the min of the 'entire' figure's width of height, and
	// we use whichever one is smaller
	
	int actorSymbolSize = (int) (zoom * ContainerSVGFigure.SIZE_OF_ACTOR_SYMBOL);
	
	double translateX = (actorSymbolSize * dx / Math.sqrt(1 + k));
	double translateY = (actorSymbolSize * dy / Math.sqrt(1 + 1 / k));
	
	// From the top left corner of the figure, we're going to shift right, and down,
	// by a certain amount, so that we can pinpoint the centre of the actor figure
	int shiftRightAmount;
	int shiftDownAmount;

	// if the entire actor figure (symbol + bubble) is small enough, then the actor symbol is
	// simply placed at the top left corner, rather than calculating the exact point (done by
	// ContainerSVGFigure) and so we should calculate the center of the actor symbol as:
	// top left corner + half of size of the actor symbol to the right + half of the actor symbol
	// size down
	int actorSymbolThreshold = (int) (zoom * ContainerSVGFigure.SIZE_OF_ACTOR_FOR_FIXED_SYMBOL);
	
	if ((r.preciseWidth() <= actorSymbolThreshold) || (r.preciseHeight() <= actorSymbolThreshold)) {
		shiftRightAmount = actorSymbolSize/2;
		shiftDownAmount = actorSymbolSize/2;
	} else {
		// else, we have to calculate the exact point of the actor symbol based
		// on the height and width of the figure
		shiftRightAmount = ((int)(r.preciseWidth()/6.75));
		shiftDownAmount = ((int)(r.preciseHeight()/6.75));	
	}
	
	// this is the centre point of the actor figure!
	Point centreOfActor = r.getTopLeft().translate(shiftRightAmount, shiftDownAmount);

	// now that we've located the centre point of the actor,
	// we will translate outwards from that point, to form a circle.
	// how much we translate by is defined by the variable 'shrinkAmount'
	int xTranslateFromActor = (int)(translateX);
	int yTranslateFromActor = (int)(translateY);
		
	return centreOfActor.translate(xTranslateFromActor, yTranslateFromActor);
	
	}
}

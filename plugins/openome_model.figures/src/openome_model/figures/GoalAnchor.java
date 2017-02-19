package openome_model.figures;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class GoalAnchor extends AbstractConnectionAnchor {

	private IFigure originalOwner;
	private boolean isInsideCollapsedCompartment = false;
	private ConnectionAnchor actorBubbleAnchor;
	
/**
 * @see org.eclipse.draw2d.AbstractConnectionAnchor#AbstractConnectionAnchor()
 */
public GoalAnchor() { }

/**
 * @see org.eclipse.draw2d.AbstractConnectionAnchor#AbstractConnectionAnchor(IFigure)
 */
public GoalAnchor(IFigure owner) {
	super(owner);
	originalOwner = owner;
	
	// create an actor ellipse anchor, so that we can switch to it and use it instead
	// when we want anchor points to be redirected to it instead..
	
	// owner is the figure of the intention, and you must get it's grandparent
	// to get the figure of the actor
	actorBubbleAnchor = new ActorSymbolAnchor(owner.getParent().getParent());
}

/**
 * Tells anchor points to point to the collapsed actor symbol instead (if the
 * intention is within an actor), rather than having them point to the intention as usual.
 * 
 * @param isCollapsed whether the actor that harbours this intention is now collapsed
 * or not.
 */
public void setIsCollapsed(boolean isCollapsed) {
	this.isInsideCollapsedCompartment = isCollapsed;
}

/**
 * Returns a point on the ellipse (defined by the owner's bounding box) where the
 * connection should be anchored.
 * @see org.eclipse.draw2d.ConnectionAnchor#getLocation(Point)
 */
public Point getLocation(Point reference) {
	
	// if the goal is inside of a collapsed actor, we want all of it's
	// anchor points to point to the actor instead now, rather than the goal
	if (isInsideCollapsedCompartment) {
		return actorBubbleAnchor.getLocation(reference);
	}
	
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
	  
	// ref.x, ref.y, r.width, r.height != 0 => safe to proceed
	  
	float k = (float)(ref.y * r.width) / (ref.x * r.height);
	k = k * k;
	//////////////////////////////////////////////////////////////////////
	int translateX = (int)(r.width  * dx / Math.sqrt(1 + k));
	int translateY = (int)(r.height * dy / Math.sqrt(1 + 1 / k));
	
	
	int rWidth = r.width;
	int rHeight = r.height;

	/*
	 * The way the Goal anchor is done is it's simply a copy of the EllipseAnchor 
	 * but with a little modification.. since the Goal figure looks very similar to
	 * an ellipse, we simply do the same thing as the ellipse anchor, but we increase
	 * the amount of translations for x and y by certain amounts
	 */
	
	// from the default ellipse translate,
	// increase the x translation by an amount
	if (translateX < 0) {
		translateX = translateX - (rWidth/30);
	} else if (translateX > 0) {
		translateX = translateX + (rWidth/30);
	}
	
	// from the default ellipse translate,
	// increase the y translation by an amount
	if (translateY < 0) {
		translateY = translateY - (rHeight/50);
	} else if (translateY > 0) {
		translateY = translateY + (rHeight/50);
	}

	/////////////////////////////////////////////////////////////
	
	return r.getCenter().translate(translateX, translateY);
}

public boolean isCollapsed() {
	return this.isInsideCollapsedCompartment;
}

/**
 * Whether or not both intentions are collapsed within the same container
 */
public boolean collapsedInSameContainerAs(Object o) {
	Object thisContainer = this.getOwner().getParent().getParent();
	
	boolean haveSameActor = false;
	boolean areBothCollapsed = false;
	
	if (o instanceof GoalAnchor) {
		haveSameActor = (((GoalAnchor)o).getOwner().getParent().getParent()).equals(thisContainer);
		areBothCollapsed = ((((GoalAnchor)o).isCollapsed() && this.isCollapsed()));
	} else if (o instanceof SoftgoalAnchor) {
		haveSameActor = (((SoftgoalAnchor)o).getOwner().getParent().getParent()).equals(thisContainer);
		areBothCollapsed = ((((SoftgoalAnchor)o).isCollapsed() && this.isCollapsed()));
	} else if (o instanceof ResourceAnchor) {
		haveSameActor = (((ResourceAnchor)o).getOwner().getParent().getParent()).equals(thisContainer);
		areBothCollapsed = ((((ResourceAnchor)o).isCollapsed() && this.isCollapsed()));
	} else if (o instanceof TaskAnchor) {
		haveSameActor = (((TaskAnchor)o).getOwner().getParent().getParent()).equals(thisContainer);
		areBothCollapsed = ((((TaskAnchor)o).isCollapsed() && this.isCollapsed()));
	} 
	
	// both goals are collapsed in the same actor
	return haveSameActor && areBothCollapsed;
}
}

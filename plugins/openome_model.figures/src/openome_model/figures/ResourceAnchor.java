package openome_model.figures;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * The ResourceAnchor is used, similar to the other Anchors - TaskAnchor,
 * SoftGoalAnchor and GoalAnchor - for directing anchor points that outline
 * the shape of the different intentions. The Task intention is simply a rectangle
 * box, of which we could simply use a ChopboxAnchor, but since we needed
 * some custom methods within the Anchor class (like telling it to 'collapse'
 * and redirect anchor points to the actor symbol), then this class is created
 */


/**
 * The ChopboxAnchor's location is found by calculating the intersection of a line drawn
 * from the center point of its owner's box to a reference point on that box. Thus 
 * {@link Connection Connections} using the ChopBoxAnchor will be oriented such that they
 * point to their owner's center.
 */
public class ResourceAnchor
	extends ChopboxAnchor
{
	
	private IFigure resourceAnchorOwner;
	private boolean isInsideCollapsedCompartment = false;
	private ConnectionAnchor actorBubbleAnchor;

/**
 * Constructs a new ChopboxAnchor.
 */
protected ResourceAnchor() { }

/**
 * Constructs a ChopboxAnchor with the given <i>owner</i> figure.
 * 
 * @param owner The owner figure
 * @since 2.0
 */
public ResourceAnchor(IFigure owner) {
	super(owner);
	
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
 * Gets a Rectangle from {@link #getBox()} and returns the Point where a line from the
 * center of the Rectangle to the Point <i>reference</i> intersects the Rectangle.
 * 
 * @param reference The reference point
 * @return The anchor location
 */
public Point getLocation(Point reference) {
	
	// if the goal is inside of a collapsed actor, we want all of it's
	// anchor points to point to the actor instead now, rather than the goal
	if (isInsideCollapsedCompartment) {
		return actorBubbleAnchor.getLocation(reference);
	} else {
		return super.getLocation(reference);
	}
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

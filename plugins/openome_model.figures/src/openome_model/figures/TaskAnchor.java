package openome_model.figures;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class TaskAnchor extends AbstractConnectionAnchor {

/**
 * @see org.eclipse.draw2d.AbstractConnectionAnchor#AbstractConnectionAnchor()
 */
public TaskAnchor() { }

private IFigure taskAnchorOwner;
private boolean isInsideCollapsedCompartment = false;
private ConnectionAnchor actorBubbleAnchor;

/**
 * @see org.eclipse.draw2d.AbstractConnectionAnchor#AbstractConnectionAnchor(IFigure)
 */
public TaskAnchor(IFigure owner) {
	super(owner);
	taskAnchorOwner = owner;
	
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
	
	double rt_x = ((double)translateX)/((double)rWidth);
	double rt_y = ((double)translateY)/((double)rHeight);

	/*
	 * Suppose the Task SVG image looks like this, where
	 * points A, B, C, D are labeled as such:
	 * 
	 *      A_________________________B
	 *      /                         \
	 *     /                           \
	 *   C/                             \D
	 *    \                             /
	 *     \                           /
	 *     E\_________________________/F
	 *   
	 */
	
		
	//System.out.println("translate: " + translateX + " " + translateY);
	//System.out.println("r: " + rWidth + " " + rHeight);
	//System.out.println("rt: " + rt_x + " " + rt_y);
	
	// The relative x positions of each point.
	// It is relative in the sense that these numbers
	// are true and apply properly no matter the size of the figure
	// so these hard coded numbers will still work even if the
	// figure was resized.
	
	//These harcoded numbers correspond to the SVG figure only
	double xPositionOfA = -0.30;
	double xPositionOfB = 0.28;
	double xPositionOfC = -0.5;
	double xPositionOfD = 0.5;
	
	// determine how far in we are on the corner areas.
	// the more 'in' we are, as in, the more closer we are to the left and right
	// edges of the figure, the *linearly* more we will be shifting up or down
	double rightXDiff = (rt_x - xPositionOfB) / (xPositionOfD - xPositionOfB);
	double leftXDiff =  (rt_x - xPositionOfA) / (xPositionOfA - xPositionOfC);
	
	// when we height shift the anchor point from a regular rectangle/square
	// this number is basically how 'steep' the height shift slope should be
	double heightShiftFactor = 1.6;
	
	// if we height shift too far, the anchor point is totally
	// screwed up.. this can happen because the svg figure we're using
	// has white space padding around the image, so if we height shift
	// too far, then we'll just return the anchor placement to be
	// at points C or D
	double rightHeightShiftThreshhold = 0.46;
	double leftHeightShiftThreshhold = -0.44;
	
	// points C and D. Point D needed a little adjustment
	// because that's the way the SVG image was drawn
	Point pointC = r.getLeft();
	Point pointD = r.getRight().translate(-r.width/60, 0);
	
	// a default rectangle/square anchor, that we'll be using for the
	// top and bottom parts, the left (C) and right (D) points,
	// and we'll be using it's points and shifting them up or down,
	// when we are on the corner areas
	ChopboxAnchor cbAnchor = new ChopboxAnchor(taskAnchorOwner);
	Point closestPoint = new Point();
	
	if ((rt_y < 0) && ((rt_x > xPositionOfA) && (rt_x < xPositionOfB))) {
		// top (points A - B)
		closestPoint = cbAnchor.getLocation(reference);
	} else if ((rt_y > 0) && ((rt_x > xPositionOfA) && (rt_x < xPositionOfB))) {
		// bottom (points E - F)
		closestPoint = cbAnchor.getLocation(reference);
	} else if ((rt_y > 0) && (rt_x > xPositionOfB) && (rt_x < xPositionOfD)) {
		// bottom right (points F - D)
		
		double heightShiftAmount = (rightXDiff * heightShiftFactor);
		
		// if we height shift too far, we'll simply just say 
		// the anchor should be at point D
		if (heightShiftAmount > rightHeightShiftThreshhold) {
			closestPoint = pointD;
		} else {
			int heightShift = (int)(rightXDiff * heightShiftFactor * r.height);
			closestPoint = cbAnchor.getLocation(reference).translate(0, -heightShift);
		}
	} else if ((rt_y > 0) && (rt_x < xPositionOfA) && (rt_x > xPositionOfC)) {
		// bottom left (points C - E)
		
		double heightShiftAmount = (leftXDiff * heightShiftFactor);
		
		// if we height shift too far, we'll simply just say 
		// the anchor should be at point C
		if (heightShiftAmount < leftHeightShiftThreshhold) {
			closestPoint = pointC;
		} else {
			int heightShift = (int)(leftXDiff * heightShiftFactor * r.height);
			closestPoint = cbAnchor.getLocation(reference).translate(0, heightShift);
		}
	} else if ((rt_y < 0) && (rt_x > xPositionOfB) && (rt_x < xPositionOfD)) {
		// top right (points B - D)

		double heightShiftAmount = (rightXDiff * heightShiftFactor);
		
		// if we height shift too far, we'll simply just say 
		// the anchor should be at point D
		if (heightShiftAmount > rightHeightShiftThreshhold) {
			closestPoint = pointD;
		} else {
			int heightShift = (int)(rightXDiff * heightShiftFactor * r.height);
			closestPoint = cbAnchor.getLocation(reference).translate(0, heightShift);
		}
	} else if ((rt_y < 0) && (rt_x < xPositionOfA) && (rt_x > xPositionOfC)) {
		// top left (points C - A)

		double heightShiftAmount = (leftXDiff * heightShiftFactor);

		// if we height shift too far, we'll simply just say 
		// the anchor should be at point C
		if (heightShiftAmount < leftHeightShiftThreshhold) {
			closestPoint = pointC;
		} else {
			int heightShift = (int)(leftXDiff * heightShiftFactor * r.height);
			closestPoint = cbAnchor.getLocation(reference).translate(0, -heightShift);
		}
	} else {
		closestPoint = cbAnchor.getLocation(reference);
	}
	
	/////////////////////////////////////////////////////////////
	//System.out.println("task result: " + closestPoint); //kn
	return closestPoint;
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

package openome_model.figures;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Polygon;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.ChopboxAnchor;

public class SoftgoalAnchor extends AbstractConnectionAnchor {

/**
 * @see org.eclipse.draw2d.AbstractConnectionAnchor#AbstractConnectionAnchor()
 */
public SoftgoalAnchor() { }

private IFigure softgoalAnchorOwner;
private boolean isInsideCollapsedCompartment = false;
private ConnectionAnchor actorBubbleAnchor;

private ChopboxAnchor outlineAnchor;

/**
 * @see org.eclipse.draw2d.AbstractConnectionAnchor#AbstractConnectionAnchor(IFigure)
 */
public SoftgoalAnchor(IFigure owner) {
	super(owner);
	softgoalAnchorOwner = owner;
	
	// create an actor ellipse anchor, so that we can switch to it and use it instead
	// when we want anchor points to be redirected to it instead..
	
	// owner is the figure of the intention, and you must get it's grandparent
	// to get the figure of the actor
	actorBubbleAnchor = new ActorSymbolAnchor(owner.getParent().getParent());
	
	// this is temporarily used as the softgoal anchor instead.. since the
	// softgoal anchor doesn't work properly at the moment
	outlineAnchor = new ChopboxAnchor(owner);
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
	} else {
		return outlineAnchor.getLocation(reference);
	}
	
	
//	Rectangle r = Rectangle.SINGLETON;
//	r.setBounds(getOwner().getBounds());
//	r.translate(-1, -1);
//	r.resize(1, 1);
//	getOwner().translateToAbsolute(r);
//	
//	Point ref = r.getCenter().negate().translate(reference);
//	
//	float dx = (ref.x > 0) ? 0.5f : -0.5f;
//	float dy = (ref.y > 0) ? 0.5f : -0.5f;
//	  
//	// ref.x, ref.y, r.width, r.height != 0 => safe to proceed
//	  
//	double k = (float)(ref.y * r.preciseWidth()) / (ref.x * r.preciseHeight());
//	k = k * k;
//
//	int translateX = (int)(r.preciseWidth()  * dx / Math.sqrt(1 + k));
//	int translateY = (int)(r.preciseHeight() * dy / Math.sqrt(1 + 1 / k));
//	
//	
//	double rWidth = r.preciseWidth();
//	double rHeight = r.preciseHeight();
//	
//	double rt_x = ((double)translateX)/((double)rWidth);
//	double rt_y = ((double)translateY)/((double)rHeight);
//	
//	//////////////////////////////////////////////////////////
//	// obtain the list of points that would draw the softgoal figure
//	// and then determine the point in that list that is closest to
//	// the reference point.. that point will be our anchor point
//	
//	PointList pl = this.softgoalOutline();
//	Point closestPointToReference = pl.getFirstPoint();
//	double minDistanceRecord = reference.getDistance(closestPointToReference);
//	
//	for (int i = 0; i < pl.size(); i++) {
//		Point pl_Point = pl.getPoint(i);
//		double examinedDistance = reference.getDistance(pl_Point);
//		if (examinedDistance < minDistanceRecord) {
//			closestPointToReference = pl_Point;
//			minDistanceRecord = examinedDistance;
//		}
//	}
//	
//	//System.out.println("soft result: " + closestPointToReference);//kn
//	
//	// figure out which of the 4 'corners' are are in:
//	// top left, top right, bottom left, or bottom right
//	int xDir = 0;
//	int yDir = 0;
//	
//	if (rt_x > 0) {
//		xDir = 1;
//	}
//	else if (rt_x < 0) {
//		xDir = -1;
//	}
//	
//	if (rt_y > 0) {
//		yDir = 1;
//	}
//	else if (rt_y < 0) {
//		yDir = -1;
//	}
//	
//	// these hardcoded numbers represent how much we whould be translating
//	// the x and y points by
//	double xTranslateFactor = (double)1/23;
//	double yTranslateFactor = (double)1/7;
//	
//	// the actual number of pixels we should be translating by x and by y
//	int xTranslateAmount = (int) (xDir * (r.preciseWidth()  * xTranslateFactor));
//	int yTranslateAmount = (int) (yDir * (r.preciseHeight() * yTranslateFactor));
//	//System.out.println("trans: " + xTranslateAmount + " " + yTranslateAmount);
//	// tighten the points by translating
//	closestPointToReference = closestPointToReference.translate(xTranslateAmount, yTranslateAmount);
//	
//	// shift up a little bit
//	//closestPointToReference = closestPointToReference.translate(0, (int)(-r.preciseHeight()/40));
//	
//	// shift right a little bit
//	closestPointToReference = closestPointToReference.translate((int)(r.preciseWidth()/30), 0);
//	
//	
//	
//	return closestPointToReference;
}


final double skew = 10; 
final double border = 4; 

/**
 * Calculate the SoftGoal shape boundary to draw
 * @param g Graphics2D object
 * @param ge GraphicsViewElement to calculate
 * @return
 */
private Polygon calculateSoftBoundary(Rectangle rec) {
	double x[] = new double[6];
	double y[] = new double[6];
	x[0] = rec.preciseX() + rec.preciseWidth()/skew;
	y[0] = rec.preciseY() + border;
	x[1] = rec.preciseX() + rec.preciseWidth()/2 - border;
	y[1] = rec.preciseY() + rec.preciseHeight()/skew + border;
	x[2] = rec.preciseX() + rec.preciseWidth() - border;
	y[2] = y[0];
	x[3] = rec.preciseX() + rec.preciseWidth()*(1-1/skew) - border;
	y[3] = rec.preciseY() + rec.preciseHeight() - border;
	x[4] = x[1];
	y[4] = rec.preciseY() + rec.preciseHeight()*(1-1/skew) - border;
	x[5] = rec.preciseX();
	y[5] = rec.preciseY() + rec.preciseHeight() - border;
	Polygon p = calcCubicPolygon(x, y);
	return p;
}

/**
 * @see http://www.cse.unsw.edu.au/~lambert/splines/NatCubicClosed.java
 */
public class Cubic {
	double a,b,c,d;         /* a + b*u + c*u^2 +d*u^3 */
  public Cubic(double a, double b, double c, double d){
    this.a = a;
    this.b = b;
    this.c = c;
    this.d = d;
  }
  /** evaluate cubic */
  public double eval(double u) {
    return (((d*u) + c)*u + b)*u + a;
  }
}
Cubic[] calcNaturalCubic(int n, double[] x) {
	double[] w = new double[n+1];
	    double[] v = new double[n+1];
	    double[] y = new double[n+1];
	    double[] D = new double[n+1];
	    double z, F, G, H;
	    int k;
	    /* We solve the equation
	       [4 1      1] [D[0]]   [3(x[1] - x[n])  ]
	       |1 4 1     | |D[1]|   |3(x[2] - x[0])  |
	       |  1 4 1   | | .  | = |      .         |
	       |    ..... | | .  |   |      .         |
	       |     1 4 1| | .  |   |3(x[n] - x[n-2])|
	       [1      1 4] [D[n]]   [3(x[0] - x[n-1])]
	       
	       by decomposing the matrix into upper triangular and lower matrices
	       and then back sustitution.  See Spath "Spline Algorithms for Curves
	       and Surfaces" pp 19--21. The D[i] are the derivatives at the knots.
	       */
	    w[1] = v[1] = z = 1.0f/4.0f;
	    y[0] = z * 3 * (x[1] - x[n]);
	    H = 4;
	    F = 3 * (x[0] - x[n-1]);
	    G = 1;
	    for ( k = 1; k < n; k++) {
	      v[k+1] = z = 1/(4 - v[k]);
	      w[k+1] = -z * w[k];
	      y[k] = z * (3*(x[k+1]-x[k-1]) - y[k-1]);
	      H = H - G * w[k];
	      F = F - G * y[k-1];
	      G = -v[k] * G;
	    }
	    H = H - (G+1)*(v[n]+w[n]);
	    y[n] = F - (G+1)*y[n-1];
	    
	    D[n] = y[n]/H;
	    D[n-1] = y[n-1] - (v[n]+w[n])*D[n]; /* This equation is WRONG! in my copy of Spath */
	    for ( k = n-2; k >= 0; k--) {
	      D[k] = y[k] - v[k+1]*D[k+1] - w[k+1]*D[n];
	    }
	    /* now compute the coefficients of the cubics */
	    Cubic[] C = new Cubic[n+1];
	    for ( k = 0; k < n; k++) {
	      C[k] = new Cubic((float)x[k], D[k], 3*(x[k+1] - x[k]) - 2*D[k] - D[k+1],
			       2*(x[k] - x[k+1]) + D[k] + D[k+1]);
	    }
	    C[n] = new Cubic((float)x[n], D[n], 3*(x[0] - x[n]) - 2*D[n] - D[0],
			     2*(x[n] - x[0]) + D[n] + D[0]);
	    return C;
}	
static int STEPS = 20;
private Polygon calcCubicPolygon(double[] x, double[] y) {
	int n = x.length;
	if (n<2) return new Polygon();
    Cubic[] X = calcNaturalCubic(n-1, x);
    Cubic[] Y = calcNaturalCubic(n-1, y);
    Polygon p = new Polygon();
    p.addPoint(new org.eclipse.draw2d.geometry.Point((int) Math.round(X[0].eval(0)),(int) Math.round(Y[0].eval(0))));
    for (int i = 0; i < X.length; i++) {
	  for (int j = 1; j <= STEPS; j++) {
	  float u = j / (float) STEPS;
	  p.addPoint(new org.eclipse.draw2d.geometry.Point(Math.round(X[i].eval(u)),
	             Math.round(Y[i].eval(u))));
	  }
    }
    return p;
}

//protected void fillShape(Graphics graphics) {
//	Polygon p = calculateSoftBoundary(new Rectangle(softgoalAnchorOwner.getBounds()));		
//	PointList points2 = p.getPoints();
//	int n = points2.size();
//	int [] points = new int[n*2];
//	for (int i=0; i<n; i++) {
//		org.eclipse.draw2d.geometry.Point point = points2.getPoint(i);
//		points[2*i] = point.x;
//		points[2*i+1] = point.y;
//	}
//	graphics.fillPolygon(points);
//}


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


private PointList softgoalOutline() {
	
	
	//graphics.setLineWidth(2);
	//graphics.setForegroundColor(ColorConstants.red);
	Rectangle b = softgoalAnchorOwner.getBounds();//softgoalAnchorOwner..getBounds();
	//System.out.println("b: " + b);//kn
	Polygon p = calculateSoftBoundary(new Rectangle( 
			(int) (1+b.preciseX() + (b.preciseWidth()  - border) * 0.05f),
			(int) (1+b.preciseY() + (b.preciseHeight() - border) * 0.05f), 
			                 (int) ((b.preciseWidth()  - border) * 0.9f), 
			                 (int) ((b.preciseHeight() - border) * 0.9f)));		
	PointList points2 = p.getPoints();
	int n = points2.size();
	//int [] points = new int[n*2];
	PointList pl = new PointList();
	for (int i=0; i<n; i++) {
		org.eclipse.draw2d.geometry.Point point = points2.getPoint(i);
		pl.addPoint(point);
		//points[2*i] = point.x;
		//points[2*i+1] = point.y;
	}
	//System.out.println(pl.getLastPoint());//kn
	
	return pl;
	//graphics.drawPolygon(pl);
}

}

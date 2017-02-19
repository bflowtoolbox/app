package openome_model.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Polygon;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;

/**
 * @generated
 */
public class SoftGoalFigure extends Shape {
	/**
	 * @generated NOT
	 */
	public SoftGoalFigure() {
		this.setLayoutManager(new StackLayout());
		this.setFill(true);
		this.setFillXOR(false);
		this.setOutline(true);
		this.setOutlineXOR(false);
		this.setLineWidth(0);
		this.setLineStyle(Graphics.LINE_SOLID);
		createContents();
	}

	/**
	 * @generated NOT
	 */
	private void createContents() {
//
//		WrapLabel softgoalNameFigure0 = new WrapLabel();
//		softgoalNameFigure0.setForegroundColor(ColorConstants.black);
//		softgoalNameFigure0.setText("<...>");
//		softgoalNameFigure0.setTextWrap(false);
//		this.add(softgoalNameFigure0);
//		setFigureSoftgoalNameFigure(softgoalNameFigure0);
//
	}

	/**
	 * @generated
	 */
	private WrappingLabel fSoftgoalNameFigure;

	/**
	 * @generated
	 */
	public WrappingLabel getFigureSoftgoalNameFigure() {
		return fSoftgoalNameFigure;
	}

	/**
	 * @generated
	 */
	private void setFigureSoftgoalNameFigure(WrappingLabel fig) {
		fSoftgoalNameFigure = fig;
	}

	/**
	 * @generated
	 */
	private boolean myUseLocalCoordinates = false;

	/**
	 * @generated
	 */
	protected boolean useLocalCoordinates() {
		return myUseLocalCoordinates;
	}

	/**
	 * @generated
	 */
	protected void setUseLocalCoordinates(boolean useLocalCoordinates) {
		myUseLocalCoordinates = useLocalCoordinates;
	}
	final int skew = 10; 
	final int border = 4; 
	
	/**
	 * Calculate the SoftGoal shape boundary to draw
	 * @param g Graphics2D object
	 * @param ge GraphicsViewElement to calculate
	 * @return
	 */
	private Polygon calculateSoftBoundary(Rectangle rec) {
		int x[] = new int[6];
		int y[] = new int[6];
		x[0] = rec.x + rec.width/skew;
		y[0] = rec.y + border;
		x[1] = rec.x + rec.width/2 - border;
		y[1] = rec.y + rec.height/skew + border;
		x[2] = rec.x + rec.width - border;
		y[2] = y[0];
		x[3] = rec.x + rec.width*(1-1/skew) - border;
		y[3] = rec.y + rec.height - border;
		x[4] = x[1];
		y[4] = rec.y + rec.height*(1-1/skew) - border;
		x[5] = rec.x;
		y[5] = rec.y + rec.height - border;
		Polygon p = calcCubicPolygon(x, y);
		return p;
	}
	
	/**
	 * @see http://www.cse.unsw.edu.au/~lambert/splines/NatCubicClosed.java
	 */
	public class Cubic {
	  float a,b,c,d;         /* a + b*u + c*u^2 +d*u^3 */
	  public Cubic(float a, float b, float c, float d){
	    this.a = a;
	    this.b = b;
	    this.c = c;
	    this.d = d;
	  }
	  /** evaluate cubic */
	  public float eval(float u) {
	    return (((d*u) + c)*u + b)*u + a;
	  }
	}
	Cubic[] calcNaturalCubic(int n, int[] x) {
		    float[] w = new float[n+1];
		    float[] v = new float[n+1];
		    float[] y = new float[n+1];
		    float[] D = new float[n+1];
		    float z, F, G, H;
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
	private Polygon calcCubicPolygon(int[] x, int[] y) {
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

	@Override
	protected void fillShape(Graphics graphics) {
		Polygon p = calculateSoftBoundary(new Rectangle(getBounds()));		
		PointList points2 = p.getPoints();
		int n = points2.size();
		int [] points = new int[n*2];
		for (int i=0; i<n; i++) {
			org.eclipse.draw2d.geometry.Point point = points2.getPoint(i);
			points[2*i] = point.x;
			points[2*i+1] = point.y;
		}
		graphics.fillPolygon(points);
	}

	@Override
	protected void outlineShape(Graphics graphics) {
		graphics.setLineWidth(2);
		graphics.setForegroundColor(ColorConstants.black);
		Rectangle b = getBounds();
		Polygon p = calculateSoftBoundary(new Rectangle( 
				1+b.x + (int)((b.width-border) * 0.05f),
				1+b.y + (int) ((b.height-border)*0.05f), 
				(int)((b.width - border)*0.9f), 
				(int) ((b.height - border)*0.9f)));		
		PointList points2 = p.getPoints();
		int n = points2.size();
		int [] points = new int[n*2];
		for (int i=0; i<n; i++) {
			org.eclipse.draw2d.geometry.Point point = points2.getPoint(i);
			points[2*i] = point.x;
			points[2*i+1] = point.y;
		}
		graphics.drawPolygon(points);
	}

}

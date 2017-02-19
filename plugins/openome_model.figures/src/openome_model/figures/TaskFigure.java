package openome_model.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel;

/**
 * @generated
 */
public class TaskFigure extends Shape {
	/**
	 * @generated NOT
	 */
	public TaskFigure() {
		setForegroundColor(ColorConstants.black);
		this.setLayoutManager(new StackLayout());
		this.setFill(true);
		this.setFillXOR(false);
		this.setOutline(true);
		this.setOutlineXOR(false);
		this.setLineWidth(2);
		this.setLineStyle(Graphics.LINE_SOLID);
//		createContents();
	}

	/**
	 * @generated NOT
	 */
//	private void createContents() {
//
//		WrapLabel taskNameFigure0 = new WrapLabel();
//		taskNameFigure0.setText("<...>");
//		taskNameFigure0.setTextWrap(false);
//		this.add(taskNameFigure0);
//		setFigureTaskNameFigure(taskNameFigure0);
//
//	}

	/**
	 * @generated
	 */
	private WrapLabel fTaskNameFigure;

	/**
	 * @generated
	 */
	public WrapLabel getFigureTaskNameFigure() {
		return fTaskNameFigure;
	}

	/**
	 * @generated
	 */
	private void setFigureTaskNameFigure(WrapLabel fig) {
		fTaskNameFigure = fig;
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

	@Override
	protected void fillShape(Graphics graphics) {
		Rectangle b = getBounds();
		int lw = getLineWidth();
		int w = b.width- 2*lw, h = b.height-2*lw;
		int x[] = new int[6];
		int y[] = new int[6];
		x[0] = b.x + lw;
		y[0] = b.y + h/2 + lw;
		x[1] = b.x + lw + h/4;
		y[1] = b.y + lw;
		x[3] = b.x + w;
		y[3] = y[0];
		x[2] = x[3] - (x[1] -x[0]);
		y[2] = y[1];
		x[4] = x[2];
		y[4] = b.y + h - lw;
		x[5] = x[1];
		y[5] = y[4];
		PointList pts = new PointList();
		for (int i=0; i<6; i++)
			pts.addPoint(new Point(x[i], y[i]));
		graphics.fillPolygon(pts);		
	}

	@Override
	protected void outlineShape(Graphics graphics) {
		graphics.setForegroundColor(ColorConstants.black);
		Rectangle b = getBounds();
		int lw = getLineWidth();
		int w = b.width- 2*lw, h = b.height-2*lw;
		int x[] = new int[6];
		int y[] = new int[6];
		x[0] = b.x + lw;
		y[0] = b.y + h/2 + lw;
		x[1] = b.x + lw + h/4;
		y[1] = b.y + lw;
		x[3] = b.x + w;
		y[3] = y[0];
		x[2] = x[3] - (x[1] -x[0]);
		y[2] = y[1];
		x[4] = x[2];
		y[4] = b.y + h - lw;
		x[5] = x[1];
		y[5] = y[4];
		PointList pts = new PointList();
		for (int i=0; i<6; i++)
			pts.addPoint(new Point(x[i], y[i]));
		graphics.drawPolygon(pts);		
	}
}

package openome_model.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel;

/**
 * @generated
 */
public class GoalFigure extends RoundedRectangle {
	/**
	 * @generated NOT
	 */
	public GoalFigure() {
//		setLayoutManager(new XYLayout());		
		setLayoutManager(new StackLayout());		
		this.setCornerDimensions(new Dimension(32, 32));
		this.setBackgroundColor(ColorConstants.white);
		this.setForegroundColor(ColorConstants.black);
		this.setFill(true);
		this.setFillXOR(false);
		this.setOutline(true);
		this.setOutlineXOR(false);
		this.setLineWidth(2);
		this.setLineStyle(Graphics.LINE_SOLID);
		createContents();
	}

	/**
	 * @generated
	 */
	private void createContents() {
		WrapLabel goalNameFigure0 = new WrapLabel();
		goalNameFigure0.setText("");
		this.add(goalNameFigure0);
		goalNameFigure0.setTextWrap(false);
		goalNameFigure0.setLocation(goalNameFigure0.getLocation().getTranslated(0, -40));
		setFigureGoalNameFigure(goalNameFigure0);

	}

	/**
	 * @generated
	 */
	private WrapLabel fGoalNameFigure;

	/**
	 * @generated
	 */
	public WrapLabel getFigureGoalNameFigure() {
		return fGoalNameFigure;
	}

	/**
	 * @generated
	 */
	private void setFigureGoalNameFigure(WrapLabel fig) {
		fGoalNameFigure = fig;
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

}
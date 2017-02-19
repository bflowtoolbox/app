package openome_model.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel;

/**
 * @generated
 */
public class ResourceFigure extends RectangleFigure {
	/**
	 * @generated
	 */
	public ResourceFigure() {
		setForegroundColor(ColorConstants.black);
		this.setLayoutManager(new StackLayout());
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

//		WrapLabel resourceNameFigure0 = new WrapLabel();
//		resourceNameFigure0.setText("<...>");
//		resourceNameFigure0.setTextWrap(false);
//		this.add(resourceNameFigure0);
//		setFigureResourceNameFigure(resourceNameFigure0);

	}

	/**
	 * @generated
	 */
	private WrapLabel fResourceNameFigure;

	/**
	 * @generated
	 */
	public WrapLabel getFigureResourceNameFigure() {
		return fResourceNameFigure;
	}

	/**
	 * @generated
	 */
	private void setFigureResourceNameFigure(WrapLabel fig) {
		fResourceNameFigure = fig;
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

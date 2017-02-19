package openome_model.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PolygonDecoration;

public class OrDecoration extends PolygonDecoration {

	public OrDecoration() {
		setFill(true);
		setFillXOR(false);
		setOutline(true);
		setOutlineXOR(false);
		setLineWidth(3);
		setLineStyle(Graphics.LINE_SOLID);
		setScale(10, 4);
	}
}

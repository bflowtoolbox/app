package org.bflow.toolbox.epc.extensions.edit.parts;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.swt.SWT;

public class PersonTypeBorder extends LineBorder{

	public void paint(IFigure figure, Graphics graphics, Insets insets) {
		tempRect.setBounds(getPaintRectangle(figure, insets));
		if (getWidth() % 2 == 1) {
			tempRect.width--;
			tempRect.height--;
		}
		tempRect.shrink(getWidth() / 2, getWidth() / 2);
		graphics.setLineWidth(getWidth() / 2);
		if (getColor() != null)
			graphics.setForegroundColor(getColor());
		graphics.setLineStyle(SWT.LINE_DOT);
		
		graphics.drawRectangle(tempRect);
	}
}

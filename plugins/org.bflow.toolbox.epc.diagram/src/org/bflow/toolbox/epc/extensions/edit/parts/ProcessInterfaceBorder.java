package org.bflow.toolbox.epc.extensions.edit.parts;

import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

public class ProcessInterfaceBorder implements Border{
	
	/**
	 * @param figure
	 * @return (0, 0, 0, 0)
	 */
	public Insets getInsets(IFigure figure) {
		return new Insets(0, 0, 0, 0);
	}

	
	/**
	 * @param figure
	 * @return (0, 0)
	 */
	public Dimension getPreferredSize(IFigure figure) {
		return new Dimension(0, 0);
	}

	
	/**
	 * @return false
	 */
	public boolean isOpaque() {
		return false;
	}

	
	/**
	 * Paints the border.
	 * @param figure
	 * @param graphics
	 * @param insets 
	 */
	public void paint(IFigure figure, Graphics graphics, Insets insets) {
		Rectangle bounds = figure.getBounds();
		
		graphics.pushState();
		graphics.translate(bounds.x, bounds.y);
		
		graphics.setBackgroundColor(figure.getBackgroundColor());
		graphics.setForegroundColor(figure.getForegroundColor());
		
		Rectangle r = new Rectangle(bounds.width - 14, bounds.height - 14, 4, 4);
		graphics.fillRectangle(r);
		
		PointList polygon = new PointList();
		polygon.addPoint(20, bounds.height - 10 - 1);
		polygon.addPoint(30, bounds.height - 1);
		polygon.addPoint(bounds.width - 10, bounds.height - 1);
		polygon.addPoint(bounds.width - 1, bounds.height - 10);
		polygon.addPoint(bounds.width - 1, 30);
		polygon.addPoint(bounds.width - 11, 20);
		polygon.addPoint(bounds.width - 11, bounds.height - 10 - 1);
		
		graphics.fillPolygon(polygon);
		graphics.drawPolygon(polygon);

		graphics.popState();
	}
}

package org.bflow.toolbox.epc.extensions.edit.parts;

import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;


/**
 * The border for the OrganisationUnit.
 * Draws a vertical line and scales it, if the size changes.
 * @author Joerg Hartmann
 *
 */
public class OrganisationUnitBorder implements Border{
	
	
	/**
	 * Default size width. 
	 */
	private final int sw = 101;
	
	
	/**
	 * Default size height.
	 */
	private final int sh = 51;
	
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
		
		int width = bounds.width;
		int height = bounds.height;
		
		float xScale = ((float) width) / sw;
		float yScale = ((float) height) / sh;
		
		graphics.pushState();
		graphics.translate(bounds.x, bounds.y);
		graphics.drawLine((int) (10 * xScale), (int) (11 * yScale), 
				(int) (10 * xScale), (int) (40 * yScale));
		graphics.popState();
	}
}

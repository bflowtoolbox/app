package org.bflow.toolbox.epc.extensions.edit.parts;



import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

public class FileBorder implements Border{
	
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
		
		Rectangle ovalShape = new Rectangle(0, 10 - 1, bounds.width, 15);
		graphics.fillRectangle(ovalShape);
		
		graphics.drawLine(0, 10 - 1, 0, bounds.height - 10);
		graphics.drawLine(bounds.width - 1, 10 - 1, bounds.width - 1, bounds.height - 10);
		
		graphics.fillOval(new Rectangle(0, 0, bounds.width - 1, 20));
		graphics.drawOval(new Rectangle(0, 0, bounds.width - 1, 20));
		graphics.fillArc(new Rectangle(0, bounds.height - 21, bounds.width - 1, 20), 180, 180);
		graphics.drawArc(new Rectangle(0, bounds.height - 21, bounds.width - 1, 20), 180, 180);
		
		graphics.popState();
	}

}

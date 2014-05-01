package org.bflow.toolbox.epc.extensions.edit.parts;

import org.bflow.toolbox.epc.diagram.edit.parts.CardFileEditPart.CardFileFigure;
import org.bflow.toolbox.epc.diagram.edit.parts.CardFileEditPart.CardFileFigure.Polyline0Class;
import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Polygon;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

public class CardFileBorder implements Border{

	
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
		return true;
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
		
		if(figure instanceof CardFileFigure){
			CardFileFigure s = (CardFileFigure) figure;
			
			Polyline0Class p = (Polyline0Class) s.getCardFilePolygonFigure();
			int[] points = p.scalePointList();
			if(points.length % 2 == 0){
				Polygon figureBounds = new Polygon();
				for(int i = 0; i < points.length; i++){
					if(i % 2 == 1){
						points[i] += 20;
					}
				}
				for(int i = 0; i < points.length - 1; i = i + 2){
					figureBounds.addPoint(new Point(points[i], points[i + 1]));
				}
				Rectangle newBounds = figureBounds.getBounds();
				newBounds.width--;
				newBounds.height--;
				graphics.drawRectangle(newBounds);
				
				PointList pl2 = new PointList();
				pl2.addPoint(10, newBounds.y);
				pl2.addPoint(10, newBounds.y - 10);
				pl2.addPoint(newBounds.width + 10, newBounds.y - 10);
				pl2.addPoint(newBounds.width + 10, newBounds.height + 10);
				pl2.addPoint(newBounds.width, newBounds.height + 10);
				pl2.addPoint(newBounds.width, newBounds.y);
				
				graphics.fillPolygon(pl2);
				graphics.drawPolygon(pl2);
				
				PointList pl3 = new PointList();
				pl3.addPoint(20, newBounds.y - 10);
				pl3.addPoint(20, newBounds.y - 20);
				pl3.addPoint(newBounds.width + 20, newBounds.y - 20);
				pl3.addPoint(newBounds.width + 20, newBounds.height);
				pl3.addPoint(newBounds.width + 10, newBounds.height);
				pl3.addPoint(newBounds.width + 10, newBounds.y - 10);
				
				graphics.fillPolygon(pl3);
				graphics.drawPolygon(pl3);
			}
			
			
		}
		
		graphics.popState();
	}
}
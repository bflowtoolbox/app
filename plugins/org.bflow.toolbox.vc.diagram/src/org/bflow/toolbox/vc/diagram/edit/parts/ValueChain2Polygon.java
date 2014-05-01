package org.bflow.toolbox.vc.diagram.edit.parts;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Polygon;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

public class ValueChain2Polygon extends Polygon {
	
	private PointList points;
	
	protected void fillShape(Graphics g) {
	
		Rectangle r = getBounds();
	
		Point one = new Point(r.getTopLeft().x, r.getTopLeft().y);
		Point two = new Point(r.getLeft().x + 10, r.getLeft().y);
		Point three = new Point(r.getBottomLeft().x, r.getBottomLeft().y - 1);
		Point four = new Point(r.getBottomRight().x - 10, r.getBottomRight().y - 1);
		Point five = new Point(r.getRight().x - 1, r.getRight().y);
		Point six = new Point(r.getTopRight().x - 10, r.getTopRight().y);
	
		points = new PointList();
		points.addPoint(one);
		points.addPoint(two);
		points.addPoint(three);
		points.addPoint(four);
		points.addPoint(five);
		points.addPoint(six);
		
		g.fillPolygon(points);
	}
	
	protected void outlineShape(Graphics graphics) {
		graphics.drawPolygon(points);
	}
}

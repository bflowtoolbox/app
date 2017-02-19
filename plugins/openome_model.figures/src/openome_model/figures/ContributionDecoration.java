package openome_model.figures;


import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.swt.graphics.Image;

public class ContributionDecoration extends PolygonDecoration {

	// if you want a thicker, less sharp arrow, increase this value by 5 or 10
	// if you want a thinner, more sharp arrow, decrease this value by 5 or 10
	// A right arrow head (90 degrees) is '35'
	private int arrowThickness = 45;
	
	/** The length of the arrow head. */
	private int arrowLength = 4;
	
	public ContributionDecoration() {
		setBackgroundColor(this.getBackgroundColor());
		
		// line options
		setFill(true);
		setFillXOR(false);
		setOutlineXOR(false);
		setLineWidth(2);
		
		double line_Angle_Radians = this.degrees2Radians(-arrowThickness);
		double line_Angle_Radians2 = this.degrees2Radians(arrowThickness);
		Point sourcePoint = this.getPoints().getFirstPoint();
		
		//         arrowLinePoint_One
		//                 .
		//                  \
		//                   \
		// -------------------. sourcepoint
		//                   /
		//                 ./
		//         arrowLinePoint_Two
		
		Point arrowLinePoint_One = new Point((int)(sourcePoint.x+(Math.sin(line_Angle_Radians)*arrowLength)), 
										(int)(sourcePoint.y+(Math.cos(line_Angle_Radians)*arrowLength)));
		
		Point arrowLinePoint_Two = new Point((int)(sourcePoint.x-(Math.sin(line_Angle_Radians2)*arrowLength)), 
										(int)(sourcePoint.y-(Math.cos(line_Angle_Radians2)*arrowLength)));
		
		PointList template = new PointList();

		// draw the first line
		template.addPoint(sourcePoint);
		template.addPoint(arrowLinePoint_One);
		
		// draw the second line
		template.addPoint(sourcePoint);
		template.addPoint(arrowLinePoint_Two);
		
		setTemplate(template);
	}
	
	/**
	 * Convert an angle from degrees to radians.
	 * @param degrees the angle in degrees
	 * @return the angle in radians
	 */
	public double degrees2Radians (double degrees)
    {
      double radians = (Math.PI / 180) * degrees;
      return (radians);
    }  
	

}
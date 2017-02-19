package openome_model.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.geometry.PointList;

public class AndDecoration extends PolylineDecoration {

	public AndDecoration() {
		setFill(true);
		setFillXOR(false);
		setOutline(true);
		setOutlineXOR(false);
		setLineWidth(3);
		setLineStyle(Graphics.LINE_SOLID);
		PointList template = new PointList();

		// for some unexplainable reason, the midpoint 
		// seems to be at: -3 rather than 0
		// so you would branch outwards from -3 if you wanted
		// a longer line:
		// (-5,-1), (-6,0), (-7,1), etc.
		
		/*
		                           top point
		                           line width 
		                AND             ||             |-4
		 ===============================||=====        |-3 (rather than 0)
		                                ||             |-2
		 ______________________________________________|-1
		                   0 <----------->
		                     x-displacement              
		*/
		int midpoint = -3;
		int lengthOfCrossLine = 3;
		int topPoint    = midpoint - lengthOfCrossLine;
		int bottomPoint = midpoint + lengthOfCrossLine;
		
		int xDisplacement = 4;
		
		template.addPoint(getStart().getTranslated(xDisplacement, topPoint));
		template.addPoint(getStart().getTranslated(xDisplacement, bottomPoint));
		
		setTemplate(template);
	}
}

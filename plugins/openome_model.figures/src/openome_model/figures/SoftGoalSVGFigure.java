package openome_model.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.StackLayout;

public class SoftGoalSVGFigure extends NodeSVGFigure {
	
	public String col = "";
	
	public SoftGoalSVGFigure() {
		super("softgoal");
		setLayoutManager(new OpenOmeElementLayoutManager());
		setBackgroundColor(ColorConstants.red);
	}
	
	public SoftGoalSVGFigure(String color) {
		super("softgoal", color);
		col = color;
		setLayoutManager(new OpenOmeElementLayoutManager());
		setBackgroundColor(ColorConstants.red);
	}

}

package openome_model.figures;

import org.eclipse.draw2d.ColorConstants;

public class GoalSVGFigure extends NodeSVGFigure {
	public String col = "";
	
	public GoalSVGFigure() {
		super("goal");
		setLayoutManager(new OpenOmeElementLayoutManager());
	}
	public GoalSVGFigure(String color) {
		super("goal", color);
		col = color;
		setLayoutManager(new OpenOmeElementLayoutManager());
	}
}

package openome_model.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.StackLayout;

public class TaskSVGFigure extends NodeSVGFigure {
	
	public String col = "";
	
	public TaskSVGFigure() {
		super("task");
		setLayoutManager(new OpenOmeElementLayoutManager());
		setBackgroundColor(ColorConstants.red);
	}
	public TaskSVGFigure(String color) {
		super("task", color);
		col = color;
		setLayoutManager(new OpenOmeElementLayoutManager());
		setBackgroundColor(ColorConstants.red);
	}
}

package openome_model.figures;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.StackLayout;

public class TaskSVG2Figure extends NodeSVGFigure {

	public String col = "";
	
	public TaskSVG2Figure() {
		super("task");
		setLayoutManager(new StackLayout());
	}
	
	public TaskSVG2Figure(String color) {
		super("task", color);
		col = color;
		setLayoutManager(new OpenOmeElementLayoutManager());
		setBackgroundColor(ColorConstants.red);
	}
}

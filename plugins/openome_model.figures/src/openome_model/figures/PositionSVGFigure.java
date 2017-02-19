package openome_model.figures;

import org.eclipse.draw2d.StackLayout;

public class PositionSVGFigure extends ContainerSVGFigure {

	public PositionSVGFigure() {
		super("position");
		setLayoutManager(new OpenOmeActorNameLayoutManager());
		
//		StackLayout slo = new StackLayout();
//		setLayoutManager(slo);
	}

}

package openome_model.figures;

import org.eclipse.draw2d.StackLayout;

public class RoleSVGFigure extends ContainerSVGFigure {

	public RoleSVGFigure() {
		super("role");
		setLayoutManager(new OpenOmeActorNameLayoutManager());
		
//		StackLayout slo = new StackLayout();
//		setLayoutManager(slo);
	}

}

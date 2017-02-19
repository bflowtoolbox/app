package openome_model.figures;

import org.eclipse.draw2d.StackLayout;

public class AgentSVGFigure extends ContainerSVGFigure {

	public AgentSVGFigure() {
		super("agent");
		
		setLayoutManager(new OpenOmeActorNameLayoutManager());
		
//		StackLayout slo = new StackLayout();
//		setLayoutManager(slo);
	}

}

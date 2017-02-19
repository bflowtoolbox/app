package openome_model.figures;

import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.StackLayout;

public class ActorSVGFigure extends ContainerSVGFigure {

	public ActorSVGFigure() {
		super("actor");
		setLayoutManager(new OpenOmeActorNameLayoutManager());
		
//		StackLayout slo = new StackLayout();
//		setLayoutManager(slo);
		
		//FlowLayout flo = new FlowLayout();
		//setLayoutManager(flo);
	}
}

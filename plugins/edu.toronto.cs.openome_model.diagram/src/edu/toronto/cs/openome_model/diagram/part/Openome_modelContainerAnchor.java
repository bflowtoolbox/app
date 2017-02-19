package edu.toronto.cs.openome_model.diagram.part;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gmf.runtime.diagram.ui.render.editparts.RenderedDiagramRootEditPart;

import edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart.ActorFigure;
import edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart.AgentFigure;
import edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart.PositionFigure;
import edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart.RoleFigure;

import openome_model.figures.ActorAnchor;

/**
 * a ConnectionAnchor for containers that take into account the zoom level of the diagram
 * @author johan
 *
 */
public class Openome_modelContainerAnchor extends ActorAnchor {

	public Openome_modelContainerAnchor(IFigure owner) {
		super(owner);
	}
	
	/**
	 * Updates zoom value
	 */
	public void updateZoom(){
		IFigure figure = (IFigure) this.getOwner().getChildren().get(0);
		
		if (figure instanceof ActorFigure){
			RenderedDiagramRootEditPart root = (RenderedDiagramRootEditPart) ((ActorFigure) figure).getEditPart().getParent().getParent();
			setZoom(root.getZoomManager().getZoom());
		}
		else if (figure instanceof AgentFigure){
			RenderedDiagramRootEditPart root = (RenderedDiagramRootEditPart) ((AgentFigure) figure).getEditPart().getParent().getParent();
			setZoom(root.getZoomManager().getZoom());
		}
		else if (figure instanceof RoleFigure){
			RenderedDiagramRootEditPart root = (RenderedDiagramRootEditPart) ((RoleFigure) figure).getEditPart().getParent().getParent();
			setZoom(root.getZoomManager().getZoom());
		}
		else if (figure instanceof PositionFigure){
			RenderedDiagramRootEditPart root = (RenderedDiagramRootEditPart) ((PositionFigure) figure).getEditPart().getParent().getParent();
			setZoom(root.getZoomManager().getZoom());
		}
		else {
			System.out.println(figure);
		}
	}
}
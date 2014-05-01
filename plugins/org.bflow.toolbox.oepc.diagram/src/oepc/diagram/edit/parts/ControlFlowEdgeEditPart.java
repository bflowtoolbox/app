package oepc.diagram.edit.parts;

import oepc.diagram.edit.policies.ControlFlowEdgeItemSemanticEditPolicy;

import org.bflow.toolbox.extensions.edit.parts.BflowDiagramEditPart;
import org.bflow.toolbox.extensions.edit.parts.ColoredConnectionEditPart;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class ControlFlowEdgeEditPart extends ColoredConnectionEditPart implements
		ITreeBranchEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 4001;

	/**
	 * @generated
	 */
	public ControlFlowEdgeEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new ControlFlowEdgeItemSemanticEditPolicy());
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */

	protected Connection createConnectionFigure() {
		return new ControlFlowEdgeFigure();
	}

	/**
	 * @generated
	 */
	public ControlFlowEdgeFigure getPrimaryShape() {
		return (ControlFlowEdgeFigure) getFigure();
	}

	/**
	 * @generated
	 */
	public class ControlFlowEdgeFigure extends PolylineConnectionEx {

		/**
		 * @generated
		 */
		public ControlFlowEdgeFigure() {
			BflowDiagramEditPart diagramEditPart = 
				BflowDiagramEditPart.getCurrentViewer();
			if(diagramEditPart != null){
				this.setForegroundColor(
						diagramEditPart.getColorSchema().getForeground(
								ControlFlowEdgeEditPart.class));
			}
			else{
				this.setForegroundColor(
						ColorConstants.black);
			}
			
			this.setLineStyle(Graphics.LINE_DASH);
			setTargetDecoration(createTargetDecoration());
		}

		/**
		 * @generated
		 */
		private RotatableDecoration createTargetDecoration() {
			PolylineDecoration df = new PolylineDecoration();
			return df;
		}

	}

	@Override
	public IFigure getPrimaryFigure() {
		return getPrimaryShape();
	}
}

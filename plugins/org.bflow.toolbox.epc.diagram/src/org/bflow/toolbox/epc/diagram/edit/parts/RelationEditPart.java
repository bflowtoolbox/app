package org.bflow.toolbox.epc.diagram.edit.parts;

import org.bflow.toolbox.epc.diagram.edit.policies.RelationItemSemanticEditPolicy;
import org.bflow.toolbox.extensions.edit.parts.BflowDiagramEditPart;
import org.bflow.toolbox.extensions.edit.parts.ColoredConnectionEditPart;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class RelationEditPart extends ColoredConnectionEditPart implements
		ITreeBranchEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 4002;

	/**
	 * @generated
	 */
	public RelationEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new RelationItemSemanticEditPolicy());
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
		return new RelationFigure();
	}

	/**
	 * @generated
	 */
	public RelationFigure getPrimaryShape() {
		return (RelationFigure) getFigure();
	}

	/**
	 * @generated
	 */
	public class RelationFigure extends PolylineConnectionEx {

		/**
		 * @generated
		 */
		public RelationFigure() {
			BflowDiagramEditPart diagramEditPart = 
				BflowDiagramEditPart.getCurrentViewer();
			if(diagramEditPart != null){
				this.setForegroundColor(
						diagramEditPart.getColorSchema().getForeground(
								RelationEditPart.class));
			}
			else{
				this.setForegroundColor(
						ColorConstants.black);
			}
		}

	}

	public IFigure getPrimaryFigure() {
		return getPrimaryShape();
	}
}

package edu.toronto.cs.openome_model.diagram.edit.parts;

import java.util.ArrayList;

import openome_model.figures.GoalAnchor;
import openome_model.figures.ResourceAnchor;
import openome_model.figures.SoftgoalAnchor;
import openome_model.figures.TaskAnchor;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class AndDecompositionEditPart extends ConnectionNodeEditPart implements
		ITreeBranchEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 4002;

	/**
	 * @generated
	 */
	public AndDecompositionEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(
				EditPolicyRoles.SEMANTIC_ROLE,
				new edu.toronto.cs.openome_model.diagram.edit.policies.AndDecompositionItemSemanticEditPolicy());
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
		return new AndDecompositionFigure();
	}

	/**
	 * @generated
	 */
	public AndDecompositionFigure getPrimaryShape() {
		return (AndDecompositionFigure) getFigure();
	}

	/**
	 * @generated NOT
	 */
	public ConnectionAnchor getSourceConnectionAnchor(){
		return super.getSourceConnectionAnchor();
	}
	
	/**
	 * @generated NOT
	 */
	public ConnectionAnchor getTargetConnectionAnchor(){
		return super.getTargetConnectionAnchor();
	}
	
	/**
	 * @generated
	 */
	public class AndDecompositionFigure extends PolylineConnectionEx {

		/**
		 * @generated
		 */
		public AndDecompositionFigure() {
			this.setLineWidth(2);

			setTargetDecoration(createTargetDecoration());
		}

		/**
		 * Straighten the connector so that there are no bends or curves
		 */
		public void straightenLine() {

			// straighten the line by simply redefining a brand new routing
			// constraint, that contains only the source and target points

			Point sourcePoint = this.getPoints().getFirstPoint();
			Point targetPoint = this.getPoints().getLastPoint();

			ArrayList<AbsoluteBendpoint> list = new ArrayList<AbsoluteBendpoint>();

			list.add(new AbsoluteBendpoint(sourcePoint));
			list.add(new AbsoluteBendpoint(targetPoint));
			this.setRoutingConstraint(list);

		}

		//		NOTE: Not sure why links are always set to visible, for now will set it to normal (last revision: changeset 1275)
		//		/**
		//		 * *** NOTE: This method has been overridden to prevent
		//		 * the link to be hidden. This method will always call super.setVisible(true) ***
		//		 * @generated NOT
		//		 */
		//		public void setVisible(boolean newbool) {
		//			super.setVisible(true);
		//			this.refreshLine();
		//		}

		/**
		 * @generated
		 */
		private RotatableDecoration createTargetDecoration() {
			openome_model.figures.AndDecoration df = new openome_model.figures.AndDecoration();

			return df;
		}

		/**
		 * @generated NOT
		 */
		public void outlineShape(Graphics g) {

			// ensures that the link/connectors have smooth curvature,
			// even if the view settings say otherwise
			this.setSmoothness(SMOOTH_NORMAL);

			// determine whether or not we should draw the line (and decoration) or not..
			// in the case where the dependency link connects 2 elements within the same
			// container and the container is collapsed, we DO NOT draw the link

			ConnectionAnchor sourceAnchor = this.getSourceAnchor();
			ConnectionAnchor targetAnchor = this.getTargetAnchor();

			boolean goalAnchorInSameContainerAsTargetAnchor = ((sourceAnchor instanceof GoalAnchor) && ((GoalAnchor) sourceAnchor)
					.collapsedInSameContainerAs(targetAnchor));

			boolean softGoalAnchorInSameContainerAsTargetAnchor = ((sourceAnchor instanceof SoftgoalAnchor) && ((SoftgoalAnchor) sourceAnchor)
					.collapsedInSameContainerAs(targetAnchor));

			boolean TaskAnchorInSameContainerAsTargetAnchor = ((sourceAnchor instanceof TaskAnchor) && ((TaskAnchor) sourceAnchor)
					.collapsedInSameContainerAs(targetAnchor));

			boolean ResourceAnchorInSameContainerAsTargetAnchor = ((sourceAnchor instanceof ResourceAnchor) && ((ResourceAnchor) sourceAnchor)
					.collapsedInSameContainerAs(targetAnchor));

			if (goalAnchorInSameContainerAsTargetAnchor
					|| softGoalAnchorInSameContainerAsTargetAnchor
					|| TaskAnchorInSameContainerAsTargetAnchor
					|| ResourceAnchorInSameContainerAsTargetAnchor) {
				// if any of the checks were triggered (true), then that means that this link/connector
				// is connecting (any) 2 intentions within the same container, and the container
				// is collapsed.. so don't do anything (ie, dont' draw the link/connector)
				this.setVisible(false);
				this.getTargetDecoration().setVisible(false);
			} else {
				// else, draw the line/connector and the decoration
				super.outlineShape(g);
				this.setVisible(true);
				this.getTargetDecoration().setVisible(true);
			}

		}

	}

}

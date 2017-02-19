package edu.toronto.cs.openome_model.diagram.edit.parts;

import java.util.ArrayList;
import java.util.List;

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
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class MakeContributionEditPart extends ConnectionNodeEditPart implements
		ITreeBranchEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 4007;

	/**
	 * @generated
	 */
	public MakeContributionEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(
				EditPolicyRoles.SEMANTIC_ROLE,
				new edu.toronto.cs.openome_model.diagram.edit.policies.MakeContributionItemSemanticEditPolicy());
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionContributionTypeEditPart) {
			((edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionContributionTypeEditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getFigureMakeContributionLabel());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionContributionTypeEditPart) {
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected void removeChildVisual(EditPart childEditPart) {
		if (removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
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
		return new MakeContributionFigure();
	}

	/**
	 * @generated
	 */
	public MakeContributionFigure getPrimaryShape() {
		return (MakeContributionFigure) getFigure();
	}

	/**
	 * Make this line straight
	 */
	public void straightenLine() {
		// Straighten the connector figure
		getPrimaryShape().straightenLine();

		// Now update the Bendpoints list to 0, since a  straight line has no bendpoint
		// otherwise next time the line get refreshed the straight effect would be nullified
		RelativeBendpoints bendpoints = (RelativeBendpoints) getEdge()
				.getBendpoints();
		ArrayList<RelativeBendpoints> emptyList = new ArrayList<RelativeBendpoints>();

		// Update the Bendpoint collection in such a way that no odd notification is given
		bendpoints.eSetDeliver(false);
		bendpoints.setPoints(emptyList);
		bendpoints.eSetDeliver(true);
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
	public class MakeContributionFigure extends PolylineConnectionEx {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureMakeContributionLabel;

		/**
		 * @generated
		 */
		public MakeContributionFigure() {
			this.setLineWidth(2);

			createContents();
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

		/**
		 * @generated
		 */
		private void createContents() {

			fFigureMakeContributionLabel = new WrappingLabel();
			fFigureMakeContributionLabel.setText("++");

			this.add(fFigureMakeContributionLabel);

		}

		/**
		 * @generated
		 */
		private RotatableDecoration createTargetDecoration() {
			openome_model.figures.ContributionDecoration df = new openome_model.figures.ContributionDecoration();

			return df;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureMakeContributionLabel() {
			return fFigureMakeContributionLabel;
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
				// if any of the checks were triggered (true), then that means
				// that this link/connector
				// is connecting (any) 2 intentions within the same container,
				// and the container
				// is collapsed.. so don't do anything (ie, dont' draw the
				// link/connector)

				this.setVisible(false);
				this.getTargetDecoration().setVisible(false);

				// search for the contribution text (wrapping label), and hide
				// it
				List listOfChildren = this.getChildren();
				for (int i = 0; i < listOfChildren.size(); i++) {
					Object currentChild = listOfChildren.get(i);
					if (currentChild instanceof WrappingLabel) {
						((WrappingLabel) currentChild).setVisible(false);
					}
				}

			} else {
				// else, draw the line/connector and the decoration
				super.outlineShape(g);
				this.setVisible(true);
				this.getTargetDecoration().setVisible(true);

				// search for the contribution text (wrapping label), and make
				// it visible
				List listOfChildren = this.getChildren();
				for (int i = 0; i < listOfChildren.size(); i++) {
					Object currentChild = listOfChildren.get(i);
					if (currentChild instanceof WrappingLabel) {
						((WrappingLabel) currentChild).setVisible(true);
					}
				}
			}
		}
	}
}

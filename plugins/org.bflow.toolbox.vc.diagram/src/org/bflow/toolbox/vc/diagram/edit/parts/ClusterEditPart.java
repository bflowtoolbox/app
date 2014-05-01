package org.bflow.toolbox.vc.diagram.edit.parts;

import java.util.List;

import org.bflow.toolbox.vc.diagram.edit.policies.ClusterItemSemanticEditPolicy;
import org.bflow.toolbox.vc.diagram.part.TopBottomLeftRightAnchor;
import org.bflow.toolbox.vc.diagram.part.VcVisualIDRegistry;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

/**
 * @generated
 */
public class ClusterEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2004;

	/**
	 * @generated
	 */
	protected IFigure contentPane;

	/**
	 * @generated
	 */
	protected IFigure primaryShape;

	/**
	 * @generated
	 */
	public ClusterEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new ClusterItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {
		LayoutEditPolicy lep = new LayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {
				EditPolicy result = child
						.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
				if (result == null) {
					result = new NonResizableEditPolicy();
				}
				return result;
			}

			protected Command getMoveChildrenCommand(Request request) {
				return null;
			}

			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}
		};
		return lep;
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape() {
		ClusterFigure figure = new ClusterFigure();
		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public ClusterFigure getPrimaryShape() {
		return (ClusterFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof ClusterNameEditPart) {
			((ClusterNameEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureClusterNameFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {

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
	protected void removeChildVisual(EditPart childEditPart) {
		if (removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	 * @generated
	 */
	protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {

		return super.getContentPaneFor(editPart);
	}

	/**
	 * @generated
	 */
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(getMapMode()
				.DPtoLP(100), getMapMode().DPtoLP(50));
		return result;
	}

	/**
	 * @generated
	 */
	public EditPolicy getPrimaryDragEditPolicy() {
		EditPolicy result = super.getPrimaryDragEditPolicy();
		if (result instanceof ResizableEditPolicy) {
			ResizableEditPolicy ep = (ResizableEditPolicy) result;
			ep.setResizeDirections(PositionConstants.NONE);
		}
		return result;
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	protected NodeFigure createNodeFigure() {
		NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new StackLayout());
		IFigure shape = createNodeShape();
		figure.add(shape);
		contentPane = setupContentPane(shape);
		return figure;
	}

	/**
	 * Default implementation treats passed figure as content pane.
	 * Respects layout one may have set for generated figure.
	 * @param nodeShape instance of generated figure class
	 * @generated
	 */
	protected IFigure setupContentPane(IFigure nodeShape) {
		if (nodeShape.getLayoutManager() == null) {
			ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
			layout.setSpacing(getMapMode().DPtoLP(5));
			nodeShape.setLayoutManager(layout);
		}
		return nodeShape; // use nodeShape itself as contentPane
	}

	/**
	 * @generated
	 */
	public IFigure getContentPane() {
		if (contentPane != null) {
			return contentPane;
		}
		return super.getContentPane();
	}

	/**
	 * @generated
	 */
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(VcVisualIDRegistry
				.getType(ClusterNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	protected void handleNotificationEvent(Notification event) {
		if (event.getNotifier() == getModel()
				&& EcorePackage.eINSTANCE.getEModelElement_EAnnotations()
						.equals(event.getFeature())) {
			handleMajorSemanticChange();
		} else {
			super.handleNotificationEvent(event);
		}
	}

	/**
	 * @generated
	 */
	public class ClusterFigure extends RectangleFigure {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureClusterNameFigure;

		/**
		 * @generated NOT
		 */
		public ClusterFigure() {

			this.setLayoutManager(new StackLayout());
			this.setForegroundColor(ColorConstants.black);
			this.setBackgroundColor(THIS_BACK);
			this.setPreferredSize(new Dimension(getMapMode().DPtoLP(100),
					getMapMode().DPtoLP(50)));
			createContents();
		}

		/**
		 * @generated NOT
		 */
		private void createContents() {

			Polyline line10 = new Polyline();
			line10.addPoint(new Point(getMapMode().DPtoLP(5), getMapMode()
					.DPtoLP(0)));
			line10.addPoint(new Point(getMapMode().DPtoLP(5), getMapMode()
					.DPtoLP(50)));

			this.add(line10);

			Polyline line20 = new Polyline();
			line20.addPoint(new Point(getMapMode().DPtoLP(10), getMapMode()
					.DPtoLP(0)));
			line20.addPoint(new Point(getMapMode().DPtoLP(10), getMapMode()
					.DPtoLP(50)));

			this.add(line20);

			Polyline line30 = new Polyline();
			line30.addPoint(new Point(getMapMode().DPtoLP(89), getMapMode()
					.DPtoLP(0)));
			line30.addPoint(new Point(getMapMode().DPtoLP(89), getMapMode()
					.DPtoLP(50)));

			this.add(line30);

			Polyline line40 = new Polyline();
			line40.addPoint(new Point(getMapMode().DPtoLP(94), getMapMode()
					.DPtoLP(0)));
			line40.addPoint(new Point(getMapMode().DPtoLP(94), getMapMode()
					.DPtoLP(50)));

			this.add(line40);

			RectangleFigure clusterLabelContainer0 = new RectangleFigure();
			clusterLabelContainer0.setFill(false);
			clusterLabelContainer0.setOutline(false);

			this.add(clusterLabelContainer0, new Rectangle(getMapMode().DPtoLP(
					0), getMapMode().DPtoLP(0), getMapMode().DPtoLP(100),
					getMapMode().DPtoLP(50)));

			StackLayout layoutClusterLabelContainer0 = new StackLayout() {
				@Override
				public void layout(IFigure figure) {
					// TODO Auto-generated method stub
					Rectangle r = new Rectangle(figure.getBounds().x + 15,
							figure.getBounds().y,
							figure.getBounds().width - 30,
							figure.getBounds().height);
					List children = figure.getChildren();
					IFigure child;
					for (int i = 0; i < children.size(); i++) {
						child = (IFigure) children.get(i);
						child.setBounds(r);
					}
				}
			};

			clusterLabelContainer0
					.setLayoutManager(layoutClusterLabelContainer0);

			fFigureClusterNameFigure = new WrappingLabel();
			fFigureClusterNameFigure.setAlignment(PositionConstants.CENTER);
			fFigureClusterNameFigure.setTextWrap(true);
			fFigureClusterNameFigure.setText("");

			clusterLabelContainer0.add(fFigureClusterNameFigure);

		}

		/**
		 * @generated
		 */
		private boolean myUseLocalCoordinates = true;

		/**
		 * @generated
		 */
		protected boolean useLocalCoordinates() {
			return myUseLocalCoordinates;
		}

		/**
		 * @generated
		 */
		protected void setUseLocalCoordinates(boolean useLocalCoordinates) {
			myUseLocalCoordinates = useLocalCoordinates;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureClusterNameFigure() {
			return fFigureClusterNameFigure;
		}

	}

	/**
	 * @generated
	 */
	static final Color THIS_BACK = new Color(null, 223, 22, 22);

	/**
	 * @generated NOT
	 */
	@Override
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart arg0) {

		TopBottomLeftRightAnchor fixedAnchor = new TopBottomLeftRightAnchor(
				getFigure());
		return fixedAnchor;
	}

	/**
	 * @generated NOT
	 */
	@Override
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {

		TopBottomLeftRightAnchor fixedAnchor = new TopBottomLeftRightAnchor(
				getFigure());
		return fixedAnchor;
	}

	/**
	 * @generated NOT
	 */
	@Override
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart arg0) {

		TopBottomLeftRightAnchor fixedAnchor = new TopBottomLeftRightAnchor(
				getFigure());
		return fixedAnchor;
	}

	/**
	 * @generated NOT
	 */
	@Override
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {

		TopBottomLeftRightAnchor fixedAnchor = new TopBottomLeftRightAnchor(
				getFigure());
		return fixedAnchor;
	}
}

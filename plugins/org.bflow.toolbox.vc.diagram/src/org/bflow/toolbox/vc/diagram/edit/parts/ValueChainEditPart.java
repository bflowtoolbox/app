package org.bflow.toolbox.vc.diagram.edit.parts;

import java.util.List;

import org.bflow.toolbox.vc.diagram.edit.policies.OpenEditorEditPolicy;
import org.bflow.toolbox.vc.diagram.edit.policies.ValueChainItemSemanticEditPolicy;
import org.bflow.toolbox.vc.diagram.part.TopBottomLeftRightAnchor;
import org.bflow.toolbox.vc.diagram.part.VcVisualIDRegistry;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Polygon;
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
public class ValueChainEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2001;

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
	public ValueChainEditPart(View view) {
		super(view);
	}

	/**
	 * @generated NOT
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new ValueChainItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		installEditPolicy(EditPolicyRoles.OPEN_ROLE, new OpenEditorEditPolicy());
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
		ValueChainFigure figure = new ValueChainFigure();
		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public ValueChainFigure getPrimaryShape() {
		return (ValueChainFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof ValueChainNameEditPart) {
			((ValueChainNameEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureValueChainNameFigure());
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
				.getType(ValueChainNameEditPart.VISUAL_ID));
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

	/**
	 * @generated
	 */
	public class ValueChainFigure extends RectangleFigure {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureValueChainNameFigure;

		/**
		 * @generated NOT
		 */
		public ValueChainFigure() {
			this.setLayoutManager(new StackLayout());
			this.setFill(false);
			this.setOutline(false);
			this.setPreferredSize(new Dimension(getMapMode().DPtoLP(101),
					getMapMode().DPtoLP(51)));
			createContents();
		}

		/**
		 * @generated NOT
		 */
		private void createContents() {

			Polygon valueChainPolygonFigure0 = new ValueChainPolygon();
			
			/* [Arian Storch]
			 * Insertion needed by the newer version of gmf since 2.2
			 */
			valueChainPolygonFigure0.addPoint(new Point(getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0)));
			valueChainPolygonFigure0.addPoint(new Point(getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(50)));
			valueChainPolygonFigure0.addPoint(new Point(
					getMapMode().DPtoLP(90), getMapMode().DPtoLP(50)));
			valueChainPolygonFigure0.addPoint(new Point(getMapMode()
					.DPtoLP(100), getMapMode().DPtoLP(25)));
			valueChainPolygonFigure0.addPoint(new Point(
					getMapMode().DPtoLP(90), getMapMode().DPtoLP(0)));
			/* Insertion END */
			
			valueChainPolygonFigure0.setFill(true);
			valueChainPolygonFigure0.setForegroundColor(ColorConstants.black);
			valueChainPolygonFigure0
					.setBackgroundColor(VALUECHAINPOLYGONFIGURE0_BACK);

			this.add(valueChainPolygonFigure0);

			StackLayout layoutValueChainPolygonFigure0 = new StackLayout() {
				@Override
				public void layout(IFigure figure) {
					Rectangle r = new Rectangle(figure.getBounds().x + 5,
							figure.getBounds().y,
							figure.getBounds().width - 15,
							figure.getBounds().height);
					List<?> children = figure.getChildren();
					IFigure child;
					for (int i = 0; i < children.size(); i++) {
						child = (IFigure) children.get(i);
						child.setBounds(r);
					}
				}
			};

			valueChainPolygonFigure0
					.setLayoutManager(layoutValueChainPolygonFigure0);

			fFigureValueChainNameFigure = new WrappingLabel();
			fFigureValueChainNameFigure.setAlignment(PositionConstants.CENTER);
			fFigureValueChainNameFigure.setTextWrap(true);

			valueChainPolygonFigure0.add(fFigureValueChainNameFigure);

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
		public WrappingLabel getFigureValueChainNameFigure() {
			return fFigureValueChainNameFigure;
		}

	}

	/**
	 * @generated
	 */
	static final Color VALUECHAINPOLYGONFIGURE0_BACK = new Color(null, 0, 248,
			0);

}

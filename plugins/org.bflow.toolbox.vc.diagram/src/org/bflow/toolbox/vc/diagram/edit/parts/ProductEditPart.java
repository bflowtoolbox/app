package org.bflow.toolbox.vc.diagram.edit.parts;

import java.util.List;

import org.bflow.toolbox.vc.diagram.edit.policies.ProductItemSemanticEditPolicy;
import org.bflow.toolbox.vc.diagram.part.TopBottomLeftRightAnchor;
import org.bflow.toolbox.vc.diagram.part.VcVisualIDRegistry;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Polygon;
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
public class ProductEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2006;

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
	public ProductEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new ProductItemSemanticEditPolicy());
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
		ProductFigure figure = new ProductFigure();
		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public ProductFigure getPrimaryShape() {
		return (ProductFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof ProductNameEditPart) {
			((ProductNameEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureProductNameFigure());
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
				.DPtoLP(101), getMapMode().DPtoLP(51));
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
				.getType(ProductNameEditPart.VISUAL_ID));
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
	public class ProductFigure extends RectangleFigure {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureProductNameFigure;

		/**
		 * @generated
		 */
		public ProductFigure() {
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

			Polygon productPolygonFigure0 = new Polygon();
			productPolygonFigure0.addPoint(new Point(getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(10)));
			productPolygonFigure0.addPoint(new Point(getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(50)));
			productPolygonFigure0.addPoint(new Point(getMapMode().DPtoLP(100),
					getMapMode().DPtoLP(50)));
			productPolygonFigure0.addPoint(new Point(getMapMode().DPtoLP(100),
					getMapMode().DPtoLP(10)));
			productPolygonFigure0.addPoint(new Point(getMapMode().DPtoLP(50),
					getMapMode().DPtoLP(0)));
			productPolygonFigure0.setFill(true);
			productPolygonFigure0.setForegroundColor(ColorConstants.black);
			productPolygonFigure0
					.setBackgroundColor(PRODUCTPOLYGONFIGURE0_BACK);

			this.add(productPolygonFigure0);

			StackLayout layoutProductPolygonFigure0 = new StackLayout() {
				@Override
				public void layout(IFigure figure) {
					Rectangle r = new Rectangle(figure.getBounds().x + 5,
							figure.getBounds().y + 10,
							figure.getBounds().width - 10,
							figure.getBounds().height - 20);
					List<?> children = figure.getChildren();
					IFigure child;
					for (int i = 0; i < children.size(); i++) {
						child = (IFigure) children.get(i);
						child.setBounds(r);
					}
				}
			};

			productPolygonFigure0.setLayoutManager(layoutProductPolygonFigure0);

			fFigureProductNameFigure = new WrappingLabel();
			fFigureProductNameFigure.setAlignment(PositionConstants.CENTER);
			fFigureProductNameFigure.setTextWrap(true);
			fFigureProductNameFigure.setText("");

			productPolygonFigure0.add(fFigureProductNameFigure);

			Polyline productPolyline1 = new Polyline();
			productPolyline1.addPoint(new Point(getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(40)));
			productPolyline1.addPoint(new Point(getMapMode().DPtoLP(100),
					getMapMode().DPtoLP(40)));

			productPolygonFigure0.add(productPolyline1);

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
		public WrappingLabel getFigureProductNameFigure() {
			return fFigureProductNameFigure;
		}

	}

	/**
	 * @generated
	 */
	static final Color PRODUCTPOLYGONFIGURE0_BACK = new Color(null, 43, 238,
			135);

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

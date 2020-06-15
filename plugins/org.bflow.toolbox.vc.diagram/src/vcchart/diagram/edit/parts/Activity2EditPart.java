package vcchart.diagram.edit.parts;

import org.bflow.toolbox.extensions.LinkContext;
import org.bflow.toolbox.extensions.edit.parts.BflowDiagramEditPart;
import org.bflow.toolbox.extensions.edit.parts.BflowNodeEditPart;
import org.bflow.toolbox.extensions.figures.LinkImageFigure;
import org.bflow.toolbox.extensions.layouts.CentralizingLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.ScalablePolygonShape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

import vcchart.Activity2;
import vcchart.diagram.edit.policies.Activity2ItemSemanticEditPolicy;
import vcchart.diagram.part.VcVisualIDRegistry;

/**
 * @generated
 */
public class Activity2EditPart extends BflowNodeEditPart {

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
	public Activity2EditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new Activity2ItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {
		org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy lep = new org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {
				EditPolicy result = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
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
		return primaryShape = new Activity2Figure();
	}

	/**
	 * @generated
	 */
	public Activity2Figure getPrimaryShape() {
		return (Activity2Figure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof Activity2NameEditPart) {
			((Activity2NameEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureActivity2LabelFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof Activity2NameEditPart) {
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
		return getContentPane();
	}

	/**
	 * @generated
	 */
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(100, 50);
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
			layout.setSpacing(5);
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
		return getChildBySemanticHint(VcVisualIDRegistry.getType(Activity2NameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	protected void handleNotificationEvent(Notification event) {
		if (event.getNotifier() == getModel()
		&& EcorePackage.eINSTANCE.getEModelElement_EAnnotations().equals(event.getFeature())) {
			handleMajorSemanticChange();
		} else {
			super.handleNotificationEvent(event);
		}
	}

	/**
	 * @generated
	 */
	public class Activity2Figure extends ScalablePolygonShape {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureActivity2LabelFigure;

		/**
		 * @generated NOT
		 */
		public Activity2Figure() {
			this.addPoint(new Point(getMapMode().DPtoLP(0), getMapMode().DPtoLP(0)));
			this.addPoint(new Point(getMapMode().DPtoLP(15), getMapMode().DPtoLP(25)));
			this.addPoint(new Point(getMapMode().DPtoLP(0), getMapMode().DPtoLP(50)));
			this.addPoint(new Point(getMapMode().DPtoLP(85), getMapMode().DPtoLP(50)));
			this.addPoint(new Point(getMapMode().DPtoLP(100), getMapMode().DPtoLP(25)));
			this.addPoint(new Point(getMapMode().DPtoLP(85), getMapMode().DPtoLP(0)));
			this.addPoint(new Point(getMapMode().DPtoLP(0), getMapMode().DPtoLP(0)));
			this.setFill(true);
			this.setPreferredSize(new Dimension(getMapMode().DPtoLP(100), getMapMode().DPtoLP(50)));
			this.setBorder(new MarginBorder(getMapMode().DPtoLP(1),
					getMapMode().DPtoLP(5), getMapMode().DPtoLP(5),
					getMapMode().DPtoLP(5)));
			this.setLayoutManager(new StackLayout());
			createContents();
			
			// 2019-02-17 AST Required by the link image figure
			setLayoutManager(new CentralizingLayout());
		}

		/**
		 * @generated NOT
		 */
		private void createContents() {
			fFigureActivity2LabelFigure = new WrappingLabel();
			fFigureActivity2LabelFigure.setAlignment(PositionConstants.CENTER);
			fFigureActivity2LabelFigure.setTextJustification(PositionConstants.CENTER);
			fFigureActivity2LabelFigure.setTextWrap(true);
			fFigureActivity2LabelFigure.setText("");
			fFigureActivity2LabelFigure.setBorder(new MarginBorder(getMapMode().DPtoLP(4), getMapMode().DPtoLP(10), getMapMode().DPtoLP(4), getMapMode()
					.DPtoLP(15)));
			
			this.add(fFigureActivity2LabelFigure);
			
			Image playImage = new Image(null, this.getClass().getResourceAsStream("/icons/link-16.png"));
			LinkContext linkContext = new LinkContext(() -> ((Activity2) Activity2EditPart.this.getPrimaryView().getElement()).getSubdiagram());
			LinkImageFigure linkImage = new LinkImageFigure(playImage, linkContext);			
			this.add(linkImage);
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureActivity2LabelFigure() {
			return fFigureActivity2LabelFigure;
		}
	}

	@Override
	public WrappingLabel[] getLabels() {
		return new WrappingLabel[] { ((Activity2Figure) primaryShape).getFigureActivity2LabelFigure()};
	}

	@Override
	public IFigure getPrimaryFigure() {
		return getPrimaryShape();
	}
	
	public Color getCurentColorShemaBackgroundColor() {
		BflowDiagramEditPart diagramEditPart = BflowDiagramEditPart.getCurrentViewer();
		if(diagramEditPart != null){
			return diagramEditPart.getColorSchema().getBackground(Activity2EditPart.class);
		}else {
			return new Color(null, 0, 248, 0);
		}
	}
}

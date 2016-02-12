/*
 * 
 */
package vcchart.diagram.edit.parts;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bflow.toolbox.extensions.edit.parts.BflowDiagramEditPart;
import org.bflow.toolbox.extensions.edit.parts.BflowNodeEditPart;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.ScalablePolygonShape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
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
import org.eclipse.gmf.runtime.emf.core.resources.GMFResource;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PartInitException;

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
	
	/** The log instance for this class */
	private static final Log logger = LogFactory.getLog(Activity2.class);


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
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new Activity2ItemSemanticEditPolicy());
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
		return getChildBySemanticHint(VcVisualIDRegistry
				.getType(Activity2NameEditPart.VISUAL_ID));
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
	public class Activity2Figure extends ScalablePolygonShape {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureActivity2LabelFigure;
		private ScalablePolygonShape subdiagram_icon;

		/**
		 * @generated
		 */
		public Activity2Figure() {
			this.addPoint(new Point(getMapMode().DPtoLP(0), getMapMode()
					.DPtoLP(0)));
			this.addPoint(new Point(getMapMode().DPtoLP(15), getMapMode()
					.DPtoLP(25)));
			this.addPoint(new Point(getMapMode().DPtoLP(0), getMapMode()
					.DPtoLP(50)));
			this.addPoint(new Point(getMapMode().DPtoLP(85), getMapMode()
					.DPtoLP(50)));
			this.addPoint(new Point(getMapMode().DPtoLP(100), getMapMode()
					.DPtoLP(25)));
			this.addPoint(new Point(getMapMode().DPtoLP(85), getMapMode()
					.DPtoLP(0)));
			this.addPoint(new Point(getMapMode().DPtoLP(0), getMapMode()
					.DPtoLP(0)));
			this.setFill(true);
			this.setPreferredSize(new Dimension(getMapMode().DPtoLP(100),
					getMapMode().DPtoLP(50)));
			this.setBorder(new MarginBorder(getMapMode().DPtoLP(5),
					getMapMode().DPtoLP(5), getMapMode().DPtoLP(5),
					getMapMode().DPtoLP(5)));
			this.setLayoutManager(new StackLayout());
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {
			subdiagram_icon = new ScalablePolygonShape(){
				@Override
				protected void fillShape(Graphics graphics) {
					//Somit is dieses Shape nicht sichtbar
					graphics.setForegroundColor(getCurentColorShemaBackgroundColor());
					super.fillShape(graphics);
				}
			};
			
			subdiagram_icon.addPoint(new Point(getMapMode().DPtoLP(80), getMapMode().DPtoLP(0)));
			subdiagram_icon.addPoint(new Point(getMapMode().DPtoLP(95), getMapMode().DPtoLP(15)));
			subdiagram_icon.addPoint(new Point(getMapMode().DPtoLP(80), getMapMode().DPtoLP(30)));
			subdiagram_icon.setPreferredSize(new Dimension(getMapMode().DPtoLP(6),getMapMode().DPtoLP(12)));
			subdiagram_icon.addMouseListener(new MouseListener.Stub() {
				
				@Override
				public void mouseDoubleClicked(final MouseEvent me) {
					openSubdiagram();
				}
				
				@Override
				public void mousePressed(final MouseEvent me){
					me.consume();
				}
			});

			fFigureActivity2LabelFigure = new WrappingLabel();
			fFigureActivity2LabelFigure.setAlignment(PositionConstants.CENTER);
			fFigureActivity2LabelFigure.setTextJustification(PositionConstants.CENTER);
			fFigureActivity2LabelFigure.setTextWrap(true);
			fFigureActivity2LabelFigure.setText("");
			fFigureActivity2LabelFigure.setBorder(new MarginBorder(getMapMode().DPtoLP(4), getMapMode().DPtoLP(10), getMapMode().DPtoLP(4), getMapMode()
					.DPtoLP(15)));
			
			this.add(fFigureActivity2LabelFigure);
			this.add(subdiagram_icon);

		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureActivity2LabelFigure() {
			return fFigureActivity2LabelFigure;
		}
		
		@Override
		protected void paintClientArea(Graphics graphics) {

			super.paintClientArea(graphics);
			Activity2 a2 = (Activity2) Activity2EditPart.this.getPrimaryView().getElement();

			if (a2.getSubdiagram() != null && !a2.getSubdiagram().isEmpty()) {
				subdiagram_icon.setEnabled(true);
				subdiagram_icon.setVisible(true);
				Point p = fFigureActivity2LabelFigure.getLocation();
				Dimension d = fFigureActivity2LabelFigure.getSize();
				Image img = new Image(null, this.getClass().getResourceAsStream("/icons/play10.png"));
				Point nPoint = new Point(p.x + d.width - 16, p.y + d.height/2-16);
				graphics.drawImage(img, nPoint);
			}else {
				subdiagram_icon.setEnabled(false);
				subdiagram_icon.setVisible(false);
			}
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
	
	/**
	 * Opens the subdiagram if this edit part is connected with one.
	 */
	private void openSubdiagram() {
		Activity2 a2 = (Activity2) Activity2EditPart.this.getPrimaryView().getElement();

		if (a2.getSubdiagram() != null && !a2.getSubdiagram().isEmpty()) {
			URI fileURI = URI.createPlatformResourceURI(a2.getSubdiagram(), true);
			Resource res = new GMFResource(fileURI);

			try {
				org.bflow.toolbox.epc.diagram.part.EpcDiagramEditorUtil.openDiagram(res);
			} catch (PartInitException e) {
				logger.error("Could not open linked subdiagram", e);
			}

		}
	}

}

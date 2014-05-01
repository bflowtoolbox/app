package org.bflow.toolbox.epc.diagram.edit.parts;


import org.bflow.toolbox.epc.diagram.edit.policies.CardFileItemSemanticEditPolicy;
import org.bflow.toolbox.epc.diagram.part.EpcVisualIDRegistry;
import org.bflow.toolbox.epc.extensions.edit.parts.CardFileBorder;
import org.bflow.toolbox.extensions.edit.parts.BflowDiagramEditPart;
import org.bflow.toolbox.extensions.edit.parts.BflowNodeEditPart;
import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
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

/**
 * @generated
 */
public class CardFileEditPart extends BflowNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2017;

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
	public CardFileEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new CardFileItemSemanticEditPolicy());
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
		CardFileFigure figure = new CardFileFigure();
		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public CardFileFigure getPrimaryShape() {
		return (CardFileFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof CardFileNameEditPart) {
			((CardFileNameEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureCardFileNameFigure());
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
		return getChildBySemanticHint(EpcVisualIDRegistry
				.getType(CardFileNameEditPart.VISUAL_ID));
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
	public class CardFileFigure extends RectangleFigure {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureCardFileNameFigure;
		
		private IFigure cardFilePolygonFigure;

		/**
		 * @generated NOT
		 */
		public CardFileFigure() {
			this.setLayoutManager(new StackLayout());
			this.setFill(false);
			this.setOutline(false);
			
			BflowDiagramEditPart diagramEditPart = 
				BflowDiagramEditPart.getCurrentViewer();
			if(diagramEditPart != null){
				this.setBackgroundColor(
						diagramEditPart.getColorSchema().getBackground(
								CardFileEditPart.class));
				this.setForegroundColor(
						diagramEditPart.getColorSchema().getForeground(
								CardFileEditPart.class));
			}
			else{
				this.setBackgroundColor(ColorConstants.white);
				this.setForegroundColor(
						ColorConstants.black);
			}
			
			this.setPreferredSize(new Dimension(getMapMode().DPtoLP(100),
					getMapMode().DPtoLP(50)));
			this.setBorder(new CompoundBorder(createBorder0(),
					new MarginBorder(getMapMode().DPtoLP(20), getMapMode()
							.DPtoLP(0), getMapMode().DPtoLP(0), getMapMode()
							.DPtoLP(20))));
			createContents();
			
		}

		public class Polyline0Class extends Shape {
			/**
			 * @generated
			 */
			private final PointList myTemplate = new PointList();
			/**
			 * @generated
			 */
			private Rectangle myTemplateBounds;

			/**
			 * @generated
			 */
			public void addPoint(Point point) {
				myTemplate.addPoint(point);
				myTemplateBounds = null;
			}

			/**
			 * @generated
			 */
			protected void fillShape(Graphics graphics) {
				Rectangle bounds = getBounds();
				graphics.pushState();
				graphics.translate(bounds.x, bounds.y);
				graphics.fillPolygon(scalePointList());
				graphics.popState();
			}

			/**
			 * @generated
			 */
			public void outlineShape(Graphics graphics) {
				Rectangle bounds = getBounds();
				graphics.pushState();
				graphics.translate(bounds.x, bounds.y);
				graphics.drawPolygon(scalePointList());
				graphics.popState();
			}

			/**
			 * @generated
			 */
			private Rectangle getTemplateBounds() {
				if (myTemplateBounds == null) {
					myTemplateBounds = myTemplate.getBounds().getCopy()
							.union(0, 0);
					//just safety -- we are going to use this as divider 
					if (myTemplateBounds.width < 1) {
						myTemplateBounds.width = 1;
					}
					if (myTemplateBounds.height < 1) {
						myTemplateBounds.height = 1;
					}
				}
				return myTemplateBounds;
			}

			/**
			 * @generated
			 */
			public int[] scalePointList() {
				Rectangle pointsBounds = getTemplateBounds();
				Rectangle actualBounds = getBounds();

				float xScale = ((float) actualBounds.width)
						/ pointsBounds.width;
				float yScale = ((float) actualBounds.height)
						/ pointsBounds.height;

				if (xScale == 1 && yScale == 1) {
					return myTemplate.toIntArray();
				}
				int[] scaled = (int[]) myTemplate.toIntArray().clone();
				for (int i = 0; i < scaled.length; i += 2) {
					scaled[i] = (int) Math.floor(scaled[i] * xScale);
					scaled[i + 1] = (int) Math
							.floor(scaled[i + 1] * yScale);
				}
				return scaled;
			}

			public Rectangle getMyTemplateBounds() {
				return myTemplateBounds;
			}
		}
		
		/**
		 * @generated NOT
		 */
		private void createContents() {

			
			;
			Polyline0Class polyline0 = new Polyline0Class();
			polyline0.setMinimumSize(new Dimension(getMapMode().DPtoLP(100),
					getMapMode().DPtoLP(50)));
			

			polyline0.addPoint(new Point(getMapMode().DPtoLP(0), getMapMode()
					.DPtoLP(0)));
			polyline0.addPoint(new Point(getMapMode().DPtoLP(100), getMapMode()
					.DPtoLP(0)));
			polyline0.addPoint(new Point(getMapMode().DPtoLP(100), getMapMode()
					.DPtoLP(50)));
			polyline0.addPoint(new Point(getMapMode().DPtoLP(0), getMapMode()
					.DPtoLP(50)));
			polyline0.setFill(true);
			polyline0.setOutline(false);
			polyline0.setOpaque(false);

			this.add(polyline0);
			cardFilePolygonFigure = polyline0;
			polyline0.setLayoutManager(new StackLayout());

			fFigureCardFileNameFigure = new WrappingLabel();
			fFigureCardFileNameFigure.setText("");
			fFigureCardFileNameFigure.setAlignment(PositionConstants.CENTER);
			fFigureCardFileNameFigure
					.setTextJustification(PositionConstants.CENTER);
			fFigureCardFileNameFigure.setTextWrap(true);
			fFigureCardFileNameFigure.setBorder(new MarginBorder(getMapMode()
					.DPtoLP(4), getMapMode().DPtoLP(4), getMapMode().DPtoLP(4),
					getMapMode().DPtoLP(4)));

			polyline0.add(fFigureCardFileNameFigure);

			
		}

		/**
		 * @generated
		 */
		private Border createBorder0() {
			CardFileBorder result = new CardFileBorder();

			return result;
		}
		
		public IFigure getCardFilePolygonFigure(){
			return cardFilePolygonFigure;
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
		public WrappingLabel getFigureCardFileNameFigure() {
			return fFigureCardFileNameFigure;
		}
		

	}

	/**
	 * @generated
	 */
	static final Color THIS_BACK = new Color(null, 255, 255, 255);

	@Override
	public IFigure getPrimaryFigure() {
		return getPrimaryShape().getCardFilePolygonFigure();
	}

	public WrappingLabel[] getLabels() {
		return new WrappingLabel[] { ((CardFileFigure) primaryShape)
				.getFigureCardFileNameFigure() };
	}
	
	public void setBackgroundColor(Color background){
		getPrimaryShape().setBackgroundColor(background);
		super.setBackgroundColor(background);
	}
	
	
	public void setForegroundColor(Color foreground){
		getPrimaryShape().setForegroundColor(foreground);
		super.setForegroundColor(foreground);
	}
}

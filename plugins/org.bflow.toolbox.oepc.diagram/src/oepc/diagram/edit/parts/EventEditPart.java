package oepc.diagram.edit.parts;

import java.util.List;

import oepc.diagram.edit.policies.EventItemSemanticEditPolicy;
import oepc.diagram.part.OepcVisualIDRegistry;


import org.bflow.toolbox.extensions.edit.parts.BflowDiagramEditPart;
import org.bflow.toolbox.extensions.edit.parts.BflowNodeEditPart;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
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

/**
 * @generated NOT
 */
public class EventEditPart extends BflowNodeEditPart {

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
	public EventEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new EventItemSemanticEditPolicy());
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
		EventFigure figure = new EventFigure();
		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public EventFigure getPrimaryShape() {
		return (EventFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof EventNameEditPart) {
			((EventNameEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureEventNameLabelFigure());
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
		return getChildBySemanticHint(OepcVisualIDRegistry
				.getType(EventNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated NOT 
	 */
	public class EventFigure extends RectangleFigure {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureEventNameLabelFigure;

		/**
		 * @generated
		 */
		public EventFigure() {
			this.setLayoutManager(new StackLayout());
			this.setOutline(false);
			this.setPreferredSize(new Dimension(getMapMode().DPtoLP(101),
					getMapMode().DPtoLP(51)));
			
			createContents();
		}

		/**
		 * @generated 
		 */
		private void createContents() {

			class EventPolygonFigure0Class extends Shape {
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
				protected void outlineShape(Graphics graphics) {
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
				 * @generated NOT
				 */
				private int[] scalePointList() {
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

					layoutLabelContainer(this, (int) Math.floor(diff * xScale));
					return scaled;
				}
			}
			;
			EventPolygonFigure0Class eventPolygonFigure0 = new EventPolygonFigure0Class();

			eventPolygonFigure0.addPoint(new Point(getMapMode().DPtoLP(10),
					getMapMode().DPtoLP(0)));
			eventPolygonFigure0.addPoint(new Point(getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(25)));
			eventPolygonFigure0.addPoint(new Point(getMapMode().DPtoLP(10),
					getMapMode().DPtoLP(51)));
			eventPolygonFigure0.addPoint(new Point(getMapMode().DPtoLP(91),
					getMapMode().DPtoLP(51)));
			eventPolygonFigure0.addPoint(new Point(getMapMode().DPtoLP(101),
					getMapMode().DPtoLP(25)));
			eventPolygonFigure0.addPoint(new Point(getMapMode().DPtoLP(91),
					getMapMode().DPtoLP(0)));
			eventPolygonFigure0.addPoint(new Point(getMapMode().DPtoLP(10),
					getMapMode().DPtoLP(0)));
			eventPolygonFigure0.setFill(true);
			
			BflowDiagramEditPart diagramEditPart = 
				BflowDiagramEditPart.getCurrentViewer();
			if(diagramEditPart != null){
				eventPolygonFigure0.setBackgroundColor(
						diagramEditPart.getColorSchema().getBackground(
								EventEditPart.class));
				eventPolygonFigure0.setForegroundColor(
						diagramEditPart.getColorSchema().getForeground(
								EventEditPart.class));
			}
			else{
				eventPolygonFigure0.setBackgroundColor(ColorConstants.white);
				eventPolygonFigure0.setForegroundColor(
						ColorConstants.black);
			}

			eventPolygonFigure = eventPolygonFigure0;
			this.add(eventPolygonFigure0);
			layoutLabelContainer(eventPolygonFigure0, diff);

			fFigureEventNameLabelFigure = new WrappingLabel();
			fFigureEventNameLabelFigure.setText("");
			fFigureEventNameLabelFigure.setAlignment(PositionConstants.CENTER);
			fFigureEventNameLabelFigure
					.setTextJustification(PositionConstants.CENTER);
			fFigureEventNameLabelFigure.setTextWrap(true);

			eventPolygonFigure0.add(fFigureEventNameLabelFigure);

		}

		private final int diff = 15;

		private void layoutLabelContainer(Shape figure, final int offset) {
			final int insets = 4;
			StackLayout layoutEventPolygonFigure0 = new StackLayout() {
				@Override
				public void layout(IFigure figure) {
					// TODO Auto-generated method stub
					Rectangle r = new Rectangle(
							figure.getBounds().x + offset,
							figure.getBounds().y + insets, 
							figure.getBounds().width - 2 * offset, 
							figure.getBounds().height - 2 * insets);
					List children = figure.getChildren();
					IFigure child;
					for (int i = 0; i < children.size(); i++) {
						child = (IFigure) children.get(i);
						child.setBounds(r);
					}
				}
			};
			figure.setLayoutManager(layoutEventPolygonFigure0);
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
		public WrappingLabel getFigureEventNameLabelFigure() {
			return fFigureEventNameLabelFigure;
		}

		private IFigure eventPolygonFigure;

		public IFigure getEventPolygonFigure() {
			return eventPolygonFigure;
		}

	}

	public WrappingLabel[] getLabels() {
		return new WrappingLabel[] { ((EventFigure) primaryShape)
				.getFigureEventNameLabelFigure() };
	}

	public IFigure getPrimaryFigure() {
		return getPrimaryShape().getEventPolygonFigure();
	}
}

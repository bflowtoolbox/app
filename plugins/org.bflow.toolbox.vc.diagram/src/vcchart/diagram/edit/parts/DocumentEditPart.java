/*
 * 
 */
package vcchart.diagram.edit.parts;

import java.util.List;

import org.bflow.toolbox.extensions.edit.parts.BflowDiagramEditPart;
import org.bflow.toolbox.extensions.edit.parts.BflowNodeEditPart;
import org.eclipse.draw2d.ColorConstants;
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
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

import vcchart.diagram.edit.policies.DocumentItemSemanticEditPolicy;
import vcchart.diagram.part.VcVisualIDRegistry;

/**
 * @generated NOT
 */
public class DocumentEditPart extends BflowNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2009;

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
	public DocumentEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new DocumentItemSemanticEditPolicy());
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
		return primaryShape = new DocumentFigure();
	}

	/**
	 * @generated
	 */
	public DocumentFigure getPrimaryShape() {
		return (DocumentFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof DocumentNameEditPart) {
			((DocumentNameEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureDocumentLabelFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof DocumentNameEditPart) {
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
				.getType(DocumentNameEditPart.VISUAL_ID));
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
	public class DocumentFigure extends RectangleFigure {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureDocumentLabelFigure;
		
		/**
		 * @generated NOT
		 */
		private IFigure documentPolygonFigure;
		private final int diff = 25;

		/**
		 * @generated NOT
		 */
		public DocumentFigure() {
			this.setLayoutManager(new StackLayout());
			this.setFill(false);
			this.setOutline(false);
			this.setPreferredSize(new Dimension(getMapMode().DPtoLP(101),getMapMode().DPtoLP(61)));
			createContents();
		}

		/**
		 * @generated NOT
		 */
		private void createContents() {
			
			class DocumentPolygonFigure0Class extends Shape {
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
					layoutLabelContainer(this, (int) (diff * yScale));
					return scaled;
				}
			};

			DocumentPolygonFigure0Class documentPolygonFigure0 = new DocumentPolygonFigure0Class();

			documentPolygonFigure0.addPoint(new Point(getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0)));
			documentPolygonFigure0.addPoint(new Point(getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(50)));
			documentPolygonFigure0.addPoint(new Point(getMapMode().DPtoLP(20),
					getMapMode().DPtoLP(60)));
			documentPolygonFigure0.addPoint(new Point(getMapMode().DPtoLP(80),
					getMapMode().DPtoLP(40)));
			documentPolygonFigure0.addPoint(new Point(getMapMode().DPtoLP(100),
					getMapMode().DPtoLP(50)));
			documentPolygonFigure0.addPoint(new Point(getMapMode().DPtoLP(100),
					getMapMode().DPtoLP(0)));
			documentPolygonFigure0.setFill(true);

			BflowDiagramEditPart diagramEditPart = 
				BflowDiagramEditPart.getCurrentViewer();
			if(diagramEditPart != null){
				documentPolygonFigure0.setBackgroundColor(
						diagramEditPart.getColorSchema().getBackground(
								DocumentEditPart.class));
				documentPolygonFigure0.setForegroundColor(
						diagramEditPart.getColorSchema().getForeground(
								DocumentEditPart.class));
			}
			else{
				documentPolygonFigure0.setBackgroundColor(ColorConstants.white);
				documentPolygonFigure0.setForegroundColor(
						ColorConstants.black);
			}

			this.add(documentPolygonFigure0);
			documentPolygonFigure = documentPolygonFigure0;
			layoutLabelContainer(documentPolygonFigure0, diff);

			fFigureDocumentLabelFigure = new WrappingLabel();
			fFigureDocumentLabelFigure.setText("");
			fFigureDocumentLabelFigure.setAlignment(PositionConstants.CENTER);
			fFigureDocumentLabelFigure
					.setTextJustification(PositionConstants.CENTER);
			fFigureDocumentLabelFigure.setTextWrap(true);
			fFigureDocumentLabelFigure.setBorder(new MarginBorder(getMapMode()
					.DPtoLP(4), getMapMode().DPtoLP(4), getMapMode().DPtoLP(4),
					getMapMode().DPtoLP(4)));

			documentPolygonFigure0.add(fFigureDocumentLabelFigure);
		}
		
		private void layoutLabelContainer(Shape figure, final int height) {
			final int insets = 4;
			StackLayout layoutDocumentPolygonFigure0 = new StackLayout() {
				@SuppressWarnings("unchecked")
				@Override
				public void layout(IFigure figure) {
					// TODO Auto-generated method stub
					Rectangle r = new Rectangle(figure.getBounds().x + insets,
							figure.getBounds().y + insets,
							figure.getBounds().width - 2 * insets, figure
									.getBounds().height
									- height - 2 * insets);
					List children = figure.getChildren();
					IFigure child;
					for (int i = 0; i < children.size(); i++) {
						child = (IFigure) children.get(i);
						child.setBounds(r);
					}
				}
			};
			figure.setLayoutManager(layoutDocumentPolygonFigure0);
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureDocumentLabelFigure() {
			return fFigureDocumentLabelFigure;
		}
		
		public IFigure getDocumentPolygonFigure() {
			return documentPolygonFigure;
		}

	}

	@Override
	public WrappingLabel[] getLabels() {
		return new WrappingLabel[] { ((DocumentFigure) primaryShape).getFigureDocumentLabelFigure() };
	}

	@Override
	public IFigure getPrimaryFigure() {
		return getPrimaryShape().getDocumentPolygonFigure();
	}

}

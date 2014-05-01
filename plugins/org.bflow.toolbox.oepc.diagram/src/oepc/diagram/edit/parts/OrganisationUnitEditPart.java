package oepc.diagram.edit.parts;

import java.util.List;

import oepc.diagram.extensions.edit.parts.OrganisationUnitBorder;
import oepc.diagram.edit.policies.OrganisationUnitItemSemanticEditPolicy;
import oepc.diagram.part.OepcVisualIDRegistry;

import org.bflow.toolbox.extensions.edit.parts.BflowDiagramEditPart;
import org.bflow.toolbox.extensions.edit.parts.BflowNodeEditPart;
import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
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
public class OrganisationUnitEditPart extends BflowNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2003;

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
	public OrganisationUnitEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new OrganisationUnitItemSemanticEditPolicy());
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
		OrganisationUnitFigure figure = new OrganisationUnitFigure();
		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public OrganisationUnitFigure getPrimaryShape() {
		return (OrganisationUnitFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof OrganisationUnitNameEditPart) {
			((OrganisationUnitNameEditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getFigureOrganisationUnitNameLabel());
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
				.getType(OrganisationUnitNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public class OrganisationUnitFigure extends Ellipse {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureOrganisationUnitNameLabel;

		/**
		 * @generated
		 */
		public OrganisationUnitFigure() {
			this.setLayoutManager(new StackLayout());
			
			BflowDiagramEditPart diagramEditPart = 
				BflowDiagramEditPart.getCurrentViewer();
			if(diagramEditPart != null){
				this.setBackgroundColor(
						diagramEditPart.getColorSchema().getBackground(
								OrganisationUnitEditPart.class));
				this.setForegroundColor(
						diagramEditPart.getColorSchema().getForeground(
								OrganisationUnitEditPart.class));
			}
			else{
				this.setBackgroundColor(ColorConstants.white);
				this.setForegroundColor(
						ColorConstants.black);
			}
			
			this.setPreferredSize(new Dimension(getMapMode().DPtoLP(101),
					getMapMode().DPtoLP(51)));
			this.tWidth = this.getPreferredSize().width;
			this.tHeight = this.getPreferredSize().height;
			this.setBorder(createBorder0());
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			RectangleFigure nameContainer0 = new RectangleFigure();
			nameContainer0.setFill(false);
			nameContainer0.setOutline(false);

			this.add(nameContainer0);
			layoutLabelContainer(nameContainer0, xInsets, yInsets);

			fFigureOrganisationUnitNameLabel = new WrappingLabel();
			fFigureOrganisationUnitNameLabel.setText("");
			fFigureOrganisationUnitNameLabel
					.setAlignment(PositionConstants.CENTER);
			fFigureOrganisationUnitNameLabel
					.setTextJustification(PositionConstants.CENTER);
			fFigureOrganisationUnitNameLabel.setTextWrap(true);

			nameContainer = nameContainer0;
			nameContainer0.add(fFigureOrganisationUnitNameLabel);

		}

		RectangleFigure nameContainer = null;
		private int tWidth = 1;
		private int tHeight = 1;
		private final int xInsets = 15;
		private final int yInsets = 10;

		private void layoutLabelContainer(Shape figure, final int xOffset,
				final int yOffset) {
			StackLayout layoutEventPolygonFigure0 = new StackLayout() {
				@Override
				public void layout(IFigure figure) {
					
					Rectangle r = new Rectangle(figure.getBounds().x + xOffset,
							figure.getBounds().y + yOffset,
							figure.getBounds().width - 2 * xOffset, figure
									.getBounds().height
									- 2 * yOffset);
					List<?> children = figure.getChildren();
					IFigure child;
					for (int i = 0; i < children.size(); i++) {
						child = (IFigure) children.get(i);
						child.setBounds(r);
					}
				}
			};
			figure.setLayoutManager(layoutEventPolygonFigure0);
		}

		protected void fillShape(Graphics graphics) {
			super.fillShape(graphics);

			Rectangle actualBounds = getBounds();

			float xScale = (float) actualBounds.width / tWidth;
			float yScale = (float) actualBounds.height / tHeight;

			if (nameContainer != null) {
				layoutLabelContainer(nameContainer, (int) (xScale * xInsets),
						(int) (yScale * yInsets));
			}
		}

		/**
		 * @generated
		 */
		private Border createBorder0() {
			OrganisationUnitBorder result = new OrganisationUnitBorder();

			return result;
		}

		/**
		 * @generated
		 */
		private boolean myUseLocalCoordinates = false;

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
		public WrappingLabel getFigureOrganisationUnitNameLabel() {
			return fFigureOrganisationUnitNameLabel;
		}

	}

	public WrappingLabel[] getLabels() {
		return new WrappingLabel[] { ((OrganisationUnitFigure) primaryShape)
				.getFigureOrganisationUnitNameLabel() };
	}

	public IFigure getPrimaryFigure() {
		return getPrimaryShape();
	}
}

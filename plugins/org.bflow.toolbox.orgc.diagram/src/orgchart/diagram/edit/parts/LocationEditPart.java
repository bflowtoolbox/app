/*
 * 
 */
<<<<<<< HEAD:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/edit/parts/ParticipantEditPart.java
package vcchart.diagram.edit.parts;

import java.util.List;

import org.bflow.toolbox.extensions.edit.parts.BflowNodeEditPart;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
=======
package orgchart.diagram.edit.parts;

import java.util.List;

import org.bflow.toolbox.epc.extensions.edit.parts.LocationBorder;
import org.bflow.toolbox.extensions.LinkContext;
import org.bflow.toolbox.extensions.edit.parts.BflowNodeEditPart;
import org.bflow.toolbox.extensions.figures.LinkImageFigure;
import org.bflow.toolbox.extensions.layouts.CentralizingLayout;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
>>>>>>> development:plugins/org.bflow.toolbox.orgc.diagram/src/orgchart/diagram/edit/parts/LocationEditPart.java
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
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
<<<<<<< HEAD:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/edit/parts/ParticipantEditPart.java
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
=======
>>>>>>> development:plugins/org.bflow.toolbox.orgc.diagram/src/orgchart/diagram/edit/parts/LocationEditPart.java
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
<<<<<<< HEAD:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/edit/parts/ParticipantEditPart.java
import org.eclipse.swt.graphics.Color;

import vcchart.diagram.edit.policies.ParticipantItemSemanticEditPolicy;
import vcchart.diagram.part.VcVisualIDRegistry;

/**
 * @generated NOT
 */
public class ParticipantEditPart extends BflowNodeEditPart {
=======
import org.eclipse.swt.graphics.Image;

import orgchart.Location;
import orgchart.diagram.edit.policies.LocationItemSemanticEditPolicy;
import orgchart.diagram.part.OrgcVisualIDRegistry;

/**
 * @generated
 */
public class LocationEditPart extends BflowNodeEditPart {
>>>>>>> development:plugins/org.bflow.toolbox.orgc.diagram/src/orgchart/diagram/edit/parts/LocationEditPart.java

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2007;

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
<<<<<<< HEAD:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/edit/parts/ParticipantEditPart.java
	public ParticipantEditPart(View view) {
=======
	public LocationEditPart(View view) {
>>>>>>> development:plugins/org.bflow.toolbox.orgc.diagram/src/orgchart/diagram/edit/parts/LocationEditPart.java
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
<<<<<<< HEAD:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/edit/parts/ParticipantEditPart.java
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new ParticipantItemSemanticEditPolicy());
=======
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new LocationItemSemanticEditPolicy());
>>>>>>> development:plugins/org.bflow.toolbox.orgc.diagram/src/orgchart/diagram/edit/parts/LocationEditPart.java
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
<<<<<<< HEAD:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/edit/parts/ParticipantEditPart.java
				EditPolicy result = child
						.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
=======
				EditPolicy result = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
>>>>>>> development:plugins/org.bflow.toolbox.orgc.diagram/src/orgchart/diagram/edit/parts/LocationEditPart.java
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
<<<<<<< HEAD:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/edit/parts/ParticipantEditPart.java
		return primaryShape = new ParticipantFigure();
=======
		return primaryShape = new LocationFigure();
>>>>>>> development:plugins/org.bflow.toolbox.orgc.diagram/src/orgchart/diagram/edit/parts/LocationEditPart.java
	}

	/**
	 * @generated
	 */
<<<<<<< HEAD:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/edit/parts/ParticipantEditPart.java
	public ParticipantFigure getPrimaryShape() {
		return (ParticipantFigure) primaryShape;
=======
	public LocationFigure getPrimaryShape() {
		return (LocationFigure) primaryShape;
>>>>>>> development:plugins/org.bflow.toolbox.orgc.diagram/src/orgchart/diagram/edit/parts/LocationEditPart.java
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
<<<<<<< HEAD:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/edit/parts/ParticipantEditPart.java
		if (childEditPart instanceof ParticipantNameEditPart) {
			((ParticipantNameEditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getFigureParticipantLabelFigure());
=======
		if (childEditPart instanceof LocationNameEditPart) {
			((LocationNameEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureLocationLabelFigure());
>>>>>>> development:plugins/org.bflow.toolbox.orgc.diagram/src/orgchart/diagram/edit/parts/LocationEditPart.java
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
<<<<<<< HEAD:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/edit/parts/ParticipantEditPart.java
		if (childEditPart instanceof ParticipantNameEditPart) {
=======
		if (childEditPart instanceof LocationNameEditPart) {
>>>>>>> development:plugins/org.bflow.toolbox.orgc.diagram/src/orgchart/diagram/edit/parts/LocationEditPart.java
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

<<<<<<< HEAD:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/edit/parts/ParticipantEditPart.java

=======
>>>>>>> development:plugins/org.bflow.toolbox.orgc.diagram/src/orgchart/diagram/edit/parts/LocationEditPart.java
	/**
	 * @generated
	 */
	public EditPart getPrimaryChildEditPart() {
<<<<<<< HEAD:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/edit/parts/ParticipantEditPart.java
		return getChildBySemanticHint(VcVisualIDRegistry
				.getType(ParticipantNameEditPart.VISUAL_ID));
=======
		return getChildBySemanticHint(OrgcVisualIDRegistry
				.getType(LocationNameEditPart.VISUAL_ID));
>>>>>>> development:plugins/org.bflow.toolbox.orgc.diagram/src/orgchart/diagram/edit/parts/LocationEditPart.java
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
<<<<<<< HEAD:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/edit/parts/ParticipantEditPart.java
	public class ParticipantFigure extends Ellipse {
=======
	public class LocationFigure extends Ellipse {
>>>>>>> development:plugins/org.bflow.toolbox.orgc.diagram/src/orgchart/diagram/edit/parts/LocationEditPart.java

		/**
		 * @generated
		 */
<<<<<<< HEAD:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/edit/parts/ParticipantEditPart.java
		private WrappingLabel fFigureParticipantLabelFigure;
=======
		private WrappingLabel fFigureLocationLabelFigure;
>>>>>>> development:plugins/org.bflow.toolbox.orgc.diagram/src/orgchart/diagram/edit/parts/LocationEditPart.java
		
		/**
		 * @generated NOT
		 */
		RectangleFigure nameContainer = null;
		private int tWidth = 1;
		private int tHeight = 1;
		private final int xInsets = 15;
		private final int yInsets = 10;

		/**
		 * @generated NOT
		 */
<<<<<<< HEAD:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/edit/parts/ParticipantEditPart.java
		public ParticipantFigure() {
			this.setLayoutManager(new StackLayout());

			this.setPreferredSize(new Dimension(getMapMode().DPtoLP(100),getMapMode().DPtoLP(50)));
			this.setBorder(new OrganisationUnitBorder());
			this.tWidth = this.getPreferredSize().width;
			this.tHeight = this.getPreferredSize().height;
			createContents();
=======
		public LocationFigure() {
			this.setLayoutManager(new StackLayout());

			this.setPreferredSize(new Dimension(getMapMode().DPtoLP(100),getMapMode().DPtoLP(50)));
			this.setBorder(new LocationBorder());
			this.tWidth = this.getPreferredSize().width;
			this.tHeight = this.getPreferredSize().height;
			createContents();
			
			// 2019-02-17 AST Required by the link image figure
			setLayoutManager(new CentralizingLayout());
>>>>>>> development:plugins/org.bflow.toolbox.orgc.diagram/src/orgchart/diagram/edit/parts/LocationEditPart.java
		}

		/**
		 * @generated NOT
		 */
		private void createContents() {
			RectangleFigure nameContainer0 = new RectangleFigure();
			nameContainer0.setFill(false);
			nameContainer0.setOutline(false);

			this.add(nameContainer0);
			layoutLabelContainer(nameContainer0, xInsets, yInsets);
<<<<<<< HEAD:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/edit/parts/ParticipantEditPart.java
			fFigureParticipantLabelFigure = new WrappingLabel();
			fFigureParticipantLabelFigure.setText("");
			fFigureParticipantLabelFigure.setAlignment(PositionConstants.CENTER);
			fFigureParticipantLabelFigure.setTextJustification(PositionConstants.CENTER);
			fFigureParticipantLabelFigure.setTextWrap(true);
=======

			fFigureLocationLabelFigure = new WrappingLabel();
			fFigureLocationLabelFigure.setText("");
			fFigureLocationLabelFigure.setAlignment(PositionConstants.CENTER);
			fFigureLocationLabelFigure
					.setTextJustification(PositionConstants.CENTER);
			fFigureLocationLabelFigure.setTextWrap(true);
>>>>>>> development:plugins/org.bflow.toolbox.orgc.diagram/src/orgchart/diagram/edit/parts/LocationEditPart.java

			this.add(nameContainer0);

			nameContainer = nameContainer0;
<<<<<<< HEAD:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/edit/parts/ParticipantEditPart.java
			nameContainer0.add(fFigureParticipantLabelFigure);
		}

		/**
		 * @generated NOT
		 */
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
=======
			nameContainer0.add(fFigureLocationLabelFigure);
			
			Image playImage = new Image(null, this.getClass().getResourceAsStream("/icons/link-16.png"));
			LinkContext linkContext = new LinkContext(() -> ((Location) LocationEditPart.this.getPrimaryView().getElement()).getSubdiagram());
			LinkImageFigure linkImage = new LinkImageFigure(playImage, linkContext);			
			this.add(linkImage);
		}
		
		private void layoutLabelContainer(Shape figure, final int xOffset, final int yOffset) {
			StackLayout layoutEventPolygonFigure0 = new StackLayout() {
				@Override
				public void layout(IFigure figure) {
					Rectangle r = new Rectangle(figure.getBounds().x + xOffset,
							figure.getBounds().y + yOffset,
							figure.getBounds().width - 2 * xOffset,
							figure.getBounds().height - 2 * yOffset - 2);
>>>>>>> development:plugins/org.bflow.toolbox.orgc.diagram/src/orgchart/diagram/edit/parts/LocationEditPart.java
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
		
		/**
		 * @generated NOT
		 */
		@Override
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
<<<<<<< HEAD:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/edit/parts/ParticipantEditPart.java
=======
		
		/**
		 * @generated
		 */
		private boolean myUseLocalCoordinates = false;
>>>>>>> development:plugins/org.bflow.toolbox.orgc.diagram/src/orgchart/diagram/edit/parts/LocationEditPart.java

		/**
		 * @generated
		 */
<<<<<<< HEAD:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/edit/parts/ParticipantEditPart.java
		public WrappingLabel getFigureParticipantLabelFigure() {
			return fFigureParticipantLabelFigure;
		}
	}

=======
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
		public WrappingLabel getFigureLocationLabelFigure() {
			return fFigureLocationLabelFigure;
		}
		
		

	}
	
>>>>>>> development:plugins/org.bflow.toolbox.orgc.diagram/src/orgchart/diagram/edit/parts/LocationEditPart.java
	public IFigure getPrimaryFigure() {
		return getPrimaryShape();
	}

	public WrappingLabel[] getLabels() {
<<<<<<< HEAD:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/edit/parts/ParticipantEditPart.java
		return new WrappingLabel[] { ((ParticipantFigure) primaryShape).getFigureParticipantLabelFigure()};
	}

}
=======
		return new WrappingLabel[] { ((LocationFigure) primaryShape).getFigureLocationLabelFigure() };
	}
}
>>>>>>> development:plugins/org.bflow.toolbox.orgc.diagram/src/orgchart/diagram/edit/parts/LocationEditPart.java

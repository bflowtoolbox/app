package edu.toronto.cs.openome_model.diagram.edit.parts;

import java.util.ArrayList;
import java.util.List;
import openome_model.figures.ActorAnchor;
import openome_model.figures.ConstrainedResizeShapeEditPolicy;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.render.editparts.RenderedDiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

import edu.toronto.cs.openome_model.diagram.edit.policies.DoubleClickNameEditPolicy;
import edu.toronto.cs.openome_model.diagram.part.Openome_modelContainerAnchor;

/**
 * @generated
 */
public class PositionEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2003;

	/**
	 * @generated NOT
	 */
	private ConnectionAnchor anchor;

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
	public PositionEditPart(View view) {
		super(view);
	}

	public double getZoomLevel() {
		double zoom = ((RenderedDiagramRootEditPart) this.getParent()
				.getParent()).getZoomManager().getZoom();
		return zoom;
	}

	/**
	 * @generated NOT
	 */
	protected ConnectionAnchor getConnectionAnchor() {
		if (anchor == null) {
			anchor = new Openome_modelContainerAnchor(getFigure());
		}
		double zoom = getZoomLevel();
		((Openome_modelContainerAnchor) anchor).setZoom(zoom);
		return anchor;
	}

	/**
	 * @generated NOT
	 */
	public ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connection) {
		//@see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
		return getConnectionAnchor();
	}

	/**
	 * @generated NOT
	 */
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		//@see org.eclipse.gef.NodeEditPart#getSourceConnectionAnchor(org.eclipse.gef.Request)
		return getConnectionAnchor();
	}

	/**
	 * @generated NOT
	 */
	public ConnectionAnchor getTargetConnectionAnchor(
			ConnectionEditPart connection) {
		//@see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.ConnectionEditPart)
		return getConnectionAnchor();
	}

	/**
	 * @generated NOT
	 */
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		//@see org.eclipse.gef.NodeEditPart#getTargetConnectionAnchor(org.eclipse.gef.Request)
		return getConnectionAnchor();
	}

	/**
	 * @generated NOT 
	 */
	public EditPolicy getPrimaryDragEditPolicy() {
		// use the constrained resize shape edit policy
		// to ensure that aspect ratio is maintained
		// when the figure is being resized
		ConstrainedResizeShapeEditPolicy ep = new ConstrainedResizeShapeEditPolicy(
				this);
		ep.setIsUsedForActor(true);
		return ep;
	}

	/**
	 * @generated NOT
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(
				EditPolicyRoles.SEMANTIC_ROLE,
				new edu.toronto.cs.openome_model.diagram.edit.policies.PositionItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
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
	 * @generated NOT
	 */
	protected IFigure createNodeShape() {
		PositionFigure figure = new PositionFigure();
		figure.setEditPart(this);
		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public PositionFigure getPrimaryShape() {
		return (PositionFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.PositionNameEditPart) {
			((edu.toronto.cs.openome_model.diagram.edit.parts.PositionNameEditPart) childEditPart)
					.setLabel(getPrimaryShape().getFigurePositionNameFigure());
			return true;
		}
		if (childEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.PositionPositionCompartmentEditPart) {
			IFigure pane = getPrimaryShape().getFigurePositionBoundaryFigure();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way 
			pane
					.add(((edu.toronto.cs.openome_model.diagram.edit.parts.PositionPositionCompartmentEditPart) childEditPart)
							.getFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.PositionNameEditPart) {
			return true;
		}
		if (childEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.PositionPositionCompartmentEditPart) {
			IFigure pane = getPrimaryShape().getFigurePositionBoundaryFigure();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way 
			pane
					.remove(((edu.toronto.cs.openome_model.diagram.edit.parts.PositionPositionCompartmentEditPart) childEditPart)
							.getFigure());
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
		if (editPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.PositionPositionCompartmentEditPart) {
			return getPrimaryShape().getFigurePositionBoundaryFigure();
		}
		return getContentPane();
	}

	/**
	 * @generated
	 */
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(40, 40);
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
	protected void setForegroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setForegroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setBackgroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setBackgroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineWidth(int width) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineWidth(width);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineType(int style) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineStyle(style);
		}
	}

	/**
	 * @generated
	 */
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
				.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PositionNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMARelTypesOnSource() {
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		types
				.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001);
		types
				.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsAAssociation_4014);
		types
				.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.CoversAssociation_4015);
		types
				.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.OccupiesAssociation_4016);
		types
				.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsPartOfAssociation_4017);
		types
				.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.PlaysAssociation_4018);
		types
				.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.INSAssociation_4019);
		return types;
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMARelTypesOnSourceAndTarget(
			IGraphicalEditPart targetEditPart) {
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsAAssociation_4014);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsAAssociation_4014);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsAAssociation_4014);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsAAssociation_4014);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.CoversAssociation_4015);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.CoversAssociation_4015);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.CoversAssociation_4015);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.CoversAssociation_4015);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.OccupiesAssociation_4016);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.OccupiesAssociation_4016);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.OccupiesAssociation_4016);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.OccupiesAssociation_4016);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsPartOfAssociation_4017);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsPartOfAssociation_4017);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsPartOfAssociation_4017);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsPartOfAssociation_4017);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.PlaysAssociation_4018);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.PlaysAssociation_4018);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.PlaysAssociation_4018);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.PlaysAssociation_4018);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.INSAssociation_4019);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.INSAssociation_4019);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.INSAssociation_4019);
		}
		if (targetEditPart instanceof edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.INSAssociation_4019);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMATypesForTarget(
			IElementType relationshipType) {
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Actor_2001);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Agent_2002);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Position_2003);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Role_2004);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Goal_2005);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Softgoal_2006);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Task_2007);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Resource_2008);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Goal_3001);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Softgoal_3002);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Resource_3003);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Task_3004);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Goal_3005);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Softgoal_3006);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Resource_3007);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Task_3008);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Goal_3009);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Softgoal_3010);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Resource_3011);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Task_3012);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Goal_3013);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Softgoal_3014);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Resource_3015);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Task_3016);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsAAssociation_4014) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Actor_2001);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsAAssociation_4014) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Agent_2002);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsAAssociation_4014) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Position_2003);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsAAssociation_4014) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Role_2004);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.CoversAssociation_4015) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Actor_2001);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.CoversAssociation_4015) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Agent_2002);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.CoversAssociation_4015) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Position_2003);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.CoversAssociation_4015) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Role_2004);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.OccupiesAssociation_4016) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Actor_2001);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.OccupiesAssociation_4016) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Agent_2002);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.OccupiesAssociation_4016) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Position_2003);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.OccupiesAssociation_4016) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Role_2004);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsPartOfAssociation_4017) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Actor_2001);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsPartOfAssociation_4017) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Agent_2002);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsPartOfAssociation_4017) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Position_2003);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsPartOfAssociation_4017) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Role_2004);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.PlaysAssociation_4018) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Actor_2001);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.PlaysAssociation_4018) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Agent_2002);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.PlaysAssociation_4018) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Position_2003);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.PlaysAssociation_4018) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Role_2004);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.INSAssociation_4019) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Actor_2001);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.INSAssociation_4019) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Agent_2002);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.INSAssociation_4019) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Position_2003);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.INSAssociation_4019) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Role_2004);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMARelTypesOnTarget() {
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		types
				.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001);
		types
				.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsAAssociation_4014);
		types
				.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.CoversAssociation_4015);
		types
				.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.OccupiesAssociation_4016);
		types
				.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsPartOfAssociation_4017);
		types
				.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.PlaysAssociation_4018);
		types
				.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.INSAssociation_4019);
		return types;
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMATypesForSource(
			IElementType relationshipType) {
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Actor_2001);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Agent_2002);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Position_2003);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Role_2004);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Goal_2005);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Softgoal_2006);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Task_2007);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Resource_2008);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Goal_3001);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Softgoal_3002);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Resource_3003);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Task_3004);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Goal_3005);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Softgoal_3006);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Resource_3007);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Task_3008);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Goal_3009);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Softgoal_3010);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Resource_3011);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Task_3012);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Goal_3013);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Softgoal_3014);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Resource_3015);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Task_3016);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsAAssociation_4014) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Actor_2001);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsAAssociation_4014) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Agent_2002);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsAAssociation_4014) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Position_2003);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsAAssociation_4014) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Role_2004);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.CoversAssociation_4015) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Actor_2001);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.CoversAssociation_4015) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Agent_2002);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.CoversAssociation_4015) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Position_2003);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.CoversAssociation_4015) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Role_2004);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.OccupiesAssociation_4016) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Actor_2001);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.OccupiesAssociation_4016) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Agent_2002);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.OccupiesAssociation_4016) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Position_2003);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.OccupiesAssociation_4016) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Role_2004);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsPartOfAssociation_4017) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Actor_2001);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsPartOfAssociation_4017) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Agent_2002);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsPartOfAssociation_4017) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Position_2003);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsPartOfAssociation_4017) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Role_2004);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.PlaysAssociation_4018) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Actor_2001);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.PlaysAssociation_4018) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Agent_2002);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.PlaysAssociation_4018) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Position_2003);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.PlaysAssociation_4018) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Role_2004);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.INSAssociation_4019) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Actor_2001);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.INSAssociation_4019) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Agent_2002);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.INSAssociation_4019) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Position_2003);
		}
		if (relationshipType == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.INSAssociation_4019) {
			types
					.add(edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Role_2004);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public EditPart getTargetEditPart(Request request) {
		if (request instanceof CreateViewAndElementRequest) {
			CreateElementRequestAdapter adapter = ((CreateViewAndElementRequest) request)
					.getViewAndElementDescriptor()
					.getCreateElementRequestAdapter();
			IElementType type = (IElementType) adapter
					.getAdapter(IElementType.class);
			if (type == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Goal_3009) {
				return getChildBySemanticHint(edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
						.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PositionPositionCompartmentEditPart.VISUAL_ID));
			}
			if (type == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Softgoal_3010) {
				return getChildBySemanticHint(edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
						.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PositionPositionCompartmentEditPart.VISUAL_ID));
			}
			if (type == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Resource_3011) {
				return getChildBySemanticHint(edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
						.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PositionPositionCompartmentEditPart.VISUAL_ID));
			}
			if (type == edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Task_3012) {
				return getChildBySemanticHint(edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
						.getType(edu.toronto.cs.openome_model.diagram.edit.parts.PositionPositionCompartmentEditPart.VISUAL_ID));
			}
		}
		return super.getTargetEditPart(request);
	}

	/**
	 * @generated
	 */
	public class PositionFigure extends Ellipse {

		private PositionEditPart myEditPart;

		/**
		 * @generated
		 */
		private WrappingLabel fFigurePositionNameFigure;
		/**
		 * @generated
		 */
		private Ellipse fFigurePositionBoundaryFigure;

		/**
		 * This Container's minimum contraction
		 * default 100x100
		 */
		private Dimension contraction = new Dimension(100, 100);

		/**
		 * @generated
		 */
		public PositionFigure() {

			BorderLayout layoutThis = new BorderLayout();
			this.setLayoutManager(layoutThis);

			this.setFill(false);
			this.setOutline(false);
			this.setLineWidth(0);
			createContents();
		}

		public void setEditPart(PositionEditPart ep) {
			myEditPart = ep;
		}

		public PositionEditPart getEditPart() {
			return myEditPart;
		}

		/**
		 * @generated
		 */
		private void createContents() {

			fFigurePositionBoundaryFigure = new Ellipse();
			fFigurePositionBoundaryFigure.setLineWidth(3);
			fFigurePositionBoundaryFigure
					.setLineStyle(Graphics.LINE_DASHDOTDOT);
			fFigurePositionBoundaryFigure
					.setBackgroundColor(FFIGUREPOSITIONBOUNDARYFIGURE_BACK);
			fFigurePositionBoundaryFigure.setPreferredSize(new Dimension(
					getMapMode().DPtoLP(450), getMapMode().DPtoLP(450)));
			fFigurePositionBoundaryFigure.setMinimumSize(new Dimension(
					getMapMode().DPtoLP(100), getMapMode().DPtoLP(100)));

			this.add(fFigurePositionBoundaryFigure, BorderLayout.CENTER);
			fFigurePositionBoundaryFigure.setLayoutManager(new StackLayout());

			openome_model.figures.PositionSVGFigure positionSVGFigure1 = new openome_model.figures.PositionSVGFigure();

			fFigurePositionBoundaryFigure.add(positionSVGFigure1);

			fFigurePositionNameFigure = new WrappingLabel();
			fFigurePositionNameFigure.setText("");

			fFigurePositionNameFigure.setFont(FFIGUREPOSITIONNAMEFIGURE_FONT);

			positionSVGFigure1.add(fFigurePositionNameFigure);

		}

		/*
		 * Sets the minimum size that the container may contract to
		 */
		public void setMinimumContraction(Dimension d) {
			contraction = d;

			// get the zoom level

			// set the minimum size
			fFigurePositionBoundaryFigure.setMinimumSize(d);
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
		public WrappingLabel getFigurePositionNameFigure() {
			return fFigurePositionNameFigure;
		}

		/**
		 * @generated
		 */
		public Ellipse getFigurePositionBoundaryFigure() {
			return fFigurePositionBoundaryFigure;
		}

	}

	/**
	 * @generated
	 */
	static final Font FFIGUREPOSITIONNAMEFIGURE_FONT = new Font(Display
			.getCurrent(), "Arial", 12, SWT.BOLD);

	/**
	 * @generated
	 */
	static final Color FFIGUREPOSITIONBOUNDARYFIGURE_BACK = new Color(null,
			236, 236, 236);

}

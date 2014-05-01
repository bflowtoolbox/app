package org.bflow.toolbox.epc.diagram.edit.parts;


import org.bflow.toolbox.epc.diagram.edit.policies.ProcessInterfaceItemSemanticEditPolicy;
import org.bflow.toolbox.epc.diagram.part.EpcVisualIDRegistry;
import org.bflow.toolbox.epc.extensions.edit.parts.ProcessInterfaceBorder;
import org.bflow.toolbox.extensions.edit.parts.BflowDiagramEditPart;
import org.bflow.toolbox.extensions.edit.parts.BflowNodeEditPart;
import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
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
public class ProcessInterfaceEditPart extends BflowNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2005;

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
	public ProcessInterfaceEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new ProcessInterfaceItemSemanticEditPolicy());
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
		ProcessInterfaceFigure figure = new ProcessInterfaceFigure();
		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public ProcessInterfaceFigure getPrimaryShape() {
		return (ProcessInterfaceFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof ProcessInterfaceNameEditPart) {
			((ProcessInterfaceNameEditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getFigureProcessInterfaceNameFigure());
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
				.DPtoLP(111), getMapMode().DPtoLP(61));
		return result;
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model so
	 * you may safely remove <i>generated</i> tag and modify it.
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
				.getType(ProcessInterfaceNameEditPart.VISUAL_ID));
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
	public class ProcessInterfaceFigure extends RectangleFigure {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureProcessInterfaceNameFigure;
		
		private IFigure processInterfaceFigure;

		/**
		 * @generated NOT
		 */
		public ProcessInterfaceFigure() {
			this.setLayoutManager(new StackLayout());
			this.setFill(false);
			this.setOutline(false);
			
			BflowDiagramEditPart diagramEditPart = 
				BflowDiagramEditPart.getCurrentViewer();
			if(diagramEditPart != null){
				this.setBackgroundColor(
						diagramEditPart.getColorSchema().getBackground(
								ProcessInterfaceEditPart.class));
				this.setForegroundColor(
						diagramEditPart.getColorSchema().getForeground(
								ProcessInterfaceEditPart.class));
			}
			else{
				this.setBackgroundColor(ColorConstants.white);
				this.setForegroundColor(
						ColorConstants.black);
			}
			
			this.setPreferredSize(new Dimension(getMapMode().DPtoLP(111),
					getMapMode().DPtoLP(61)));
			this.setBorder(new CompoundBorder(createBorder0(),
					new MarginBorder(getMapMode().DPtoLP(0), getMapMode()
							.DPtoLP(0), getMapMode().DPtoLP(10), getMapMode()
							.DPtoLP(10))));
			createContents();
		}

		/**
		 * @generated NOT
		 */
		private void createContents() {

			RoundedRectangle rrect0 = new RoundedRectangle();
			rrect0.setCornerDimensions(new Dimension(getMapMode().DPtoLP(8),
					getMapMode().DPtoLP(8)));

			this.add(rrect0);
			processInterfaceFigure = rrect0;
			rrect0.setLayoutManager(new StackLayout());

			fFigureProcessInterfaceNameFigure = new WrappingLabel();
			fFigureProcessInterfaceNameFigure.setText("");
			fFigureProcessInterfaceNameFigure.setBorder(new MarginBorder(
					getMapMode().DPtoLP(4), getMapMode().DPtoLP(4),
					getMapMode().DPtoLP(4), getMapMode().DPtoLP(4)));

			fFigureProcessInterfaceNameFigure
					.setAlignment(PositionConstants.CENTER);
			fFigureProcessInterfaceNameFigure
					.setTextJustification(PositionConstants.CENTER);
			fFigureProcessInterfaceNameFigure.setTextWrap(true);

			rrect0.add(fFigureProcessInterfaceNameFigure);

		}

		/**
		 * @generated
		 */
		private Border createBorder0() {
			ProcessInterfaceBorder result = new ProcessInterfaceBorder();

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
		public WrappingLabel getFigureProcessInterfaceNameFigure() {
			return fFigureProcessInterfaceNameFigure;
		}

		public IFigure getProcessInterfaceFigure() {
			return processInterfaceFigure;
		}

	}
	
	public IFigure getPrimaryFigure() {
		return getPrimaryShape().getProcessInterfaceFigure();
	}

	public WrappingLabel[] getLabels() {
		return new WrappingLabel[] { ((ProcessInterfaceFigure) primaryShape)
				.getFigureProcessInterfaceNameFigure() };
	}
	
	public View getPrimaryView2(){
		return super.getPrimaryView();
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

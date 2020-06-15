package org.bflow.toolbox.epc.diagram.edit.parts;

import org.bflow.toolbox.epc.Function;
import org.bflow.toolbox.epc.diagram.edit.policies.EpcTextSelectionEditPolicy;
import org.bflow.toolbox.epc.diagram.edit.policies.FunctionItemSemanticEditPolicy;
import org.bflow.toolbox.epc.diagram.edit.policies.OpenEditorEditPolicy;
import org.bflow.toolbox.epc.diagram.part.EpcVisualIDRegistry;
import org.bflow.toolbox.extensions.LinkContext;
import org.bflow.toolbox.extensions.edit.parts.BflowDiagramEditPart;
import org.bflow.toolbox.extensions.edit.parts.BflowNodeEditPart;
import org.bflow.toolbox.extensions.figures.LinkImageFigure;
import org.bflow.toolbox.extensions.layouts.CentralizingLayout;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ConstrainedToolbarLayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

/**
 * @generated NOT
 * @version 2010-03-17 modified by Arian Storch
 * 			2019-01-31 AST Changed link icon
 * 			2019-02-17 Moved to new link image figure
 */
public class FunctionEditPart extends BflowNodeEditPart {

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
	 * Instance of the FunctionEditPart
	 */
	protected FunctionEditPart instance;

	/**
	 * @generated
	 */
	public FunctionEditPart(View view) {
		super(view);
		instance = this;
	}
	
	@Override
	public Command getCommand(Request _request) {
		Command command = super.getCommand(_request);
		
		return command;
	}

	/**
	 * @generated NOT
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new FunctionItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		installEditPolicy(EditPolicyRoles.OPEN_ROLE, new OpenEditorEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {

		ConstrainedToolbarLayoutEditPolicy lep = new ConstrainedToolbarLayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {
				if (child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE) == null) {
					if (child instanceof ITextAwareEditPart) {
						return new EpcTextSelectionEditPolicy();
					}
				}
				return super.createChildEditPolicy(child);
			}
		};
		return lep;
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape() {
		FunctionFigure figure = new FunctionFigure();	
		
		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public FunctionFigure getPrimaryShape() {
		return (FunctionFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof FunctionNameEditPart) {
			((FunctionNameEditPart) childEditPart).setLabel(getPrimaryShape().getFigureFunctionNameFigure());
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
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(getMapMode().DPtoLP(100), getMapMode().DPtoLP(50));
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
		return getChildBySemanticHint(EpcVisualIDRegistry.getType(FunctionNameEditPart.VISUAL_ID));
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
	public class FunctionFigure extends RoundedRectangle {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureFunctionNameFigure;

		/**
		 * @generated NOT
		 */
		public FunctionFigure() {
			this.setCornerDimensions(new Dimension(getMapMode().DPtoLP(12),	getMapMode().DPtoLP(12)));			
			this.setBackgroundColor(getCurrentColorSchemaBackgroundColor());
			this.setForegroundColor(getCurentColorShemaForegroundColor());			
			this.setPreferredSize(new Dimension(getMapMode().DPtoLP(100), getMapMode().DPtoLP(50)));
			this.setBorder(new MarginBorder(getMapMode().DPtoLP(5),
					getMapMode().DPtoLP(5), getMapMode().DPtoLP(5),
					getMapMode().DPtoLP(5)));	
			createContents();
			
			// 2019-02-17 AST Required by the link image figure
			setLayoutManager(new CentralizingLayout());
		}

		/**
		 * @generated NOT
		 */
		private void createContents() {			
			fFigureFunctionNameFigure = new WrappingLabel();
			fFigureFunctionNameFigure.setText("");
			fFigureFunctionNameFigure.setTextWrap(true);
			fFigureFunctionNameFigure.setAlignment(PositionConstants.CENTER);
			fFigureFunctionNameFigure.setTextJustification(PositionConstants.CENTER);
			fFigureFunctionNameFigure.setBorder(new MarginBorder(getMapMode()
					.DPtoLP(4), getMapMode().DPtoLP(3), getMapMode().DPtoLP(4),
					getMapMode().DPtoLP(12)));
			
			this.add(fFigureFunctionNameFigure);

			Image playImage = new Image(null, this.getClass().getResourceAsStream("/icons/link-16.png"));
			LinkContext linkContext = new LinkContext(() -> {
				EList<String> sdSet = ((Function) FunctionEditPart.this.getPrimaryView().getElement()).getSubdiagram();
				if (sdSet.isEmpty()) return null;
				return sdSet.get(0);
			});
			LinkImageFigure linkImage = new LinkImageFigure(playImage, linkContext);			
			this.add(linkImage);	
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
		public WrappingLabel getFigureFunctionNameFigure() {
			return fFigureFunctionNameFigure;
		}

	}

	public IFigure getPrimaryFigure() {
		return getPrimaryShape();
	}

	
	public WrappingLabel[] getLabels() {
		return new WrappingLabel[] { ((FunctionFigure) primaryShape).getFigureFunctionNameFigure() };
	}
	
	
	public View getPrimaryView2(){
		return super.getPrimaryView();
	}
	
	public Color getCurrentColorSchemaBackgroundColor() {
		BflowDiagramEditPart diagramEditPart = BflowDiagramEditPart.getCurrentViewer();
		if (diagramEditPart != null) {
			return diagramEditPart.getColorSchema().getBackground(FunctionEditPart.class);
		} else {
			return ColorConstants.white;
		}
	}
	
	public Color getCurentColorShemaForegroundColor() {
		BflowDiagramEditPart diagramEditPart = BflowDiagramEditPart.getCurrentViewer();
		if (diagramEditPart != null) {
			return diagramEditPart.getColorSchema().getForeground(FunctionEditPart.class);
		} else {
			return ColorConstants.black;
		}
	}	
}

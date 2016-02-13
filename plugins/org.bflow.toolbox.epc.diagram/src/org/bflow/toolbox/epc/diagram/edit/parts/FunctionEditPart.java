package org.bflow.toolbox.epc.diagram.edit.parts;

import org.bflow.toolbox.epc.Function;
import org.bflow.toolbox.epc.diagram.edit.policies.EpcTextSelectionEditPolicy;
import org.bflow.toolbox.epc.diagram.edit.policies.FunctionItemSemanticEditPolicy;
import org.bflow.toolbox.epc.diagram.edit.policies.OpenEditorEditPolicy;
import org.bflow.toolbox.epc.diagram.part.EpcVisualIDRegistry;
import org.bflow.toolbox.extensions.edit.parts.BflowDiagramEditPart;
import org.bflow.toolbox.extensions.edit.parts.BflowNodeEditPart;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.ScalablePolygonShape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
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
import org.eclipse.gmf.runtime.emf.core.resources.GMFResource;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PartInitException;

/**
 * @generated
 * @version 17/03/10 modified by Arian Storch
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
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new FunctionItemSemanticEditPolicy());
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
			((FunctionNameEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureFunctionNameFigure());
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
				.getType(FunctionNameEditPart.VISUAL_ID));
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
	public class FunctionFigure extends RoundedRectangle {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureFunctionNameFigure;
		private ScalablePolygonShape fSubdiagramFigure;

		/**
		 * @generated NOT
		 */
		public FunctionFigure() {

			StackLayout layoutThis = new StackLayout();

			this.setLayoutManager(layoutThis);

			this.setCornerDimensions(new Dimension(getMapMode().DPtoLP(12),
					getMapMode().DPtoLP(12)));
			
			this.setBackgroundColor(getCurrentColorSchemaBackgroundColor());
			this.setForegroundColor(getCurentColorShemaForegroundColor());
			
			this.setPreferredSize(new Dimension(getMapMode().DPtoLP(100),
					getMapMode().DPtoLP(50)));
			this.setBorder(new MarginBorder(getMapMode().DPtoLP(5),
					getMapMode().DPtoLP(5), getMapMode().DPtoLP(5),
					getMapMode().DPtoLP(5)));	
			this.setLayoutManager(new StackLayout());
			createContents();
		}

		/**
		 * @generated NOT
		 */
		private void createContents() {
			
			fFigureFunctionNameFigure = new WrappingLabel();
			fFigureFunctionNameFigure.setText("");
			fFigureFunctionNameFigure.setTextWrap(true);
			fFigureFunctionNameFigure.setAlignment(PositionConstants.CENTER);
			fFigureFunctionNameFigure
					.setTextJustification(PositionConstants.CENTER);
			fFigureFunctionNameFigure.setBorder(new MarginBorder(getMapMode()
					.DPtoLP(4), getMapMode().DPtoLP(3), getMapMode().DPtoLP(4),
					getMapMode().DPtoLP(12)));
			
			this.add(fFigureFunctionNameFigure);

			fSubdiagramFigure = new ScalablePolygonShape(){
				@Override
				protected void fillShape(Graphics graphics) {
					graphics.setForegroundColor(getCurrentColorSchemaBackgroundColor());
					super.fillShape(graphics);
				}
			};
			
			fSubdiagramFigure.setLayoutManager(new StackLayout());
			fSubdiagramFigure.addPoint(new Point(getMapMode().DPtoLP(80), getMapMode().DPtoLP(0)));
			fSubdiagramFigure.addPoint(new Point(getMapMode().DPtoLP(95), getMapMode().DPtoLP(0)));
			fSubdiagramFigure.addPoint(new Point(getMapMode().DPtoLP(95), getMapMode().DPtoLP(30)));
			fSubdiagramFigure.addPoint(new Point(getMapMode().DPtoLP(80), getMapMode().DPtoLP(30)));
			fSubdiagramFigure.setPreferredSize(new Dimension(getMapMode().DPtoLP(6),getMapMode().DPtoLP(12)));
			fSubdiagramFigure.addMouseListener(new MouseListener.Stub() {
				
				@Override
				public void mouseDoubleClicked(final MouseEvent me) {
					openSubdiagram();
				}
				
				@Override
				public void mousePressed(final MouseEvent me){
					me.consume();
				}
			});
			
			this.add(fSubdiagramFigure);
		}
		
		@Override
		protected void paintClientArea(Graphics graphics) {
		
			super.paintClientArea(graphics);
			
			try
			{
				Function f = (Function)instance.getPrimaryView().getElement();
				
				if(f.getSubdiagram().size() > 0)
				{
					fSubdiagramFigure.setEnabled(true);
					fSubdiagramFigure.setVisible(true);
					Point p = fFigureFunctionNameFigure.getLocation();
					Dimension d = fFigureFunctionNameFigure.getSize();
								
					Image img = new Image(null, this.getClass().getResourceAsStream("/icons/Play-10i.png"));
								
					Point nPoint = new Point(p.x+d.width-16, p.y+3);
					
					graphics.drawImage(img, nPoint);					
				}else {
					fSubdiagramFigure.setEnabled(false);
					fSubdiagramFigure.setVisible(false);
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
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
		return new WrappingLabel[] { ((FunctionFigure) primaryShape)
				.getFigureFunctionNameFigure() };
	}
	
	
	public View getPrimaryView2(){
		return super.getPrimaryView();
	}
	
	public Color getCurrentColorSchemaBackgroundColor() {
		BflowDiagramEditPart diagramEditPart = BflowDiagramEditPart.getCurrentViewer();
		if(diagramEditPart != null){
			return diagramEditPart.getColorSchema().getBackground(FunctionEditPart.class);
		}else {
			return ColorConstants.white;
		}		
	}
	
	public Color getCurentColorShemaForegroundColor() {
		BflowDiagramEditPart diagramEditPart = BflowDiagramEditPart.getCurrentViewer();
		if(diagramEditPart != null){
			return diagramEditPart.getColorSchema().getForeground(FunctionEditPart.class);
		}else {
			return ColorConstants.black;
		}
	}
	
	/**
	 * Opens the subdiagram if this edit part is connected with one.
	 */
	private void openSubdiagram()
	{		
		Function f = (Function)instance.getPrimaryView().getElement();
		
		if(f.getSubdiagram().size() > 0)
		{
			URI fileURI = URI.createPlatformResourceURI(f.getSubdiagram().get(0), true);
			
			Resource res = new GMFResource(fileURI);
			
			try 
			{
				org.bflow.toolbox.epc.diagram.part.EpcDiagramEditorUtil.openDiagram(res);
			} 
			catch (PartInitException e) 
			{
				e.printStackTrace();
			}
		}			
	}
	
}

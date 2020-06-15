package org.bflow.toolbox.hive.gmfbridge.graphiti.providers;

import org.bflow.toolbox.hive.gmfbridge.graphiti.annotations.AnnotationDecorationSupport;
import org.bflow.toolbox.hive.libs.aprogu.collections.HList;
import org.eclipse.graphiti.DiagramScrollingBehavior;
import org.eclipse.graphiti.IExecutionInfo;
import org.eclipse.graphiti.features.IDirectEditingFeature;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.context.IDoubleClickContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.ISingleClickContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.palette.IConnectionCreationToolEntry;
import org.eclipse.graphiti.palette.IPaletteCompartmentEntry;
import org.eclipse.graphiti.tb.IAnchorSelectionInfo;
import org.eclipse.graphiti.tb.IConnectionSelectionInfo;
import org.eclipse.graphiti.tb.IContextButtonPadData;
import org.eclipse.graphiti.tb.IContextMenuEntry;
import org.eclipse.graphiti.tb.IDecorator;
import org.eclipse.graphiti.tb.IShapeSelectionInfo;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;
import org.eclipse.graphiti.util.ILocationInfo;

/**
 * Provides an adapter of {@link IToolBehaviorProvider} to deliver custom
 * decorators.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2015-12-23
 * @version 2018-10-03 Updated to latest API
 * 
 */
public class ToolBehaviorProviderAdapter implements IToolBehaviorProvider {

	private IToolBehaviorProvider _originalToolBehaviorProvider;

	/**
	 * Creates a new instance based on the given original provider.
	 * 
	 * @param originalToolBehaviorProvider
	 *            Original provider
	 */
	public ToolBehaviorProviderAdapter(IToolBehaviorProvider originalToolBehaviorProvider) {
		super();
		_originalToolBehaviorProvider = originalToolBehaviorProvider;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#dispose()
	 */
	@Override
	public void dispose() {
		_originalToolBehaviorProvider.dispose();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#equalsBusinessObjects(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean equalsBusinessObjects(Object arg0, Object arg1) {
		return _originalToolBehaviorProvider.equalsBusinessObjects(arg0, arg1);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#getAdapter(java.lang.Class)
	 */
	@Override
	public Object getAdapter(Class<?> arg0) {
		return _originalToolBehaviorProvider.getAdapter(arg0);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#getChopboxAnchorArea(org.eclipse.graphiti.mm.pictograms.PictogramElement)
	 */
	@Override
	public GraphicsAlgorithm getChopboxAnchorArea(PictogramElement arg0) {
		return _originalToolBehaviorProvider.getChopboxAnchorArea(arg0);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#getClickArea(org.eclipse.graphiti.mm.pictograms.PictogramElement)
	 */
	@Override
	public GraphicsAlgorithm[] getClickArea(PictogramElement arg0) {
		return _originalToolBehaviorProvider.getClickArea(arg0);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#getCommandFeature(org.eclipse.graphiti.features.context.impl.CustomContext, java.lang.String)
	 */
	@Override
	public ICustomFeature getCommandFeature(CustomContext arg0, String arg1) {
		return _originalToolBehaviorProvider.getCommandFeature(arg0, arg1);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#getContentArea(org.eclipse.graphiti.mm.pictograms.ContainerShape)
	 */
	@Override
	public GraphicsAlgorithm getContentArea(ContainerShape arg0) {
		return _originalToolBehaviorProvider.getContentArea(arg0);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#getContextButtonPad(org.eclipse.graphiti.features.context.IPictogramElementContext)
	 */
	@Override
	public IContextButtonPadData getContextButtonPad(
			IPictogramElementContext arg0) {
		return _originalToolBehaviorProvider.getContextButtonPad(arg0);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#getContextMenu(org.eclipse.graphiti.features.context.ICustomContext)
	 */
	@Override
	public IContextMenuEntry[] getContextMenu(ICustomContext arg0) {
		return _originalToolBehaviorProvider.getContextMenu(arg0);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#getContributorId()
	 */
	@Override
	public String getContributorId() {
		return _originalToolBehaviorProvider.getContributorId();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#getDecorators(org.eclipse.graphiti.mm.pictograms.PictogramElement)
	 */
	@Override
	public IDecorator[] getDecorators(PictogramElement arg0) {
		IDecorator[] originalDecorators = _originalToolBehaviorProvider.getDecorators(arg0);
		IDecorator[] additionalDecorators = new IDecorator[0];
		
		AnnotationDecorationSupport annotationDecorationSupport = AnnotationDecorationSupport.Current();
		if (annotationDecorationSupport != null)
			additionalDecorators = annotationDecorationSupport.getDecoratorsForElement(arg0);
		
		HList<IDecorator> decoratorList = new HList<>(IDecorator.class);
		decoratorList.addAll(originalDecorators);
		decoratorList.addAll(additionalDecorators);
		
		IDecorator[] resultSet = decoratorList.toArray();
		return resultSet;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#getDiagramScrollingBehavior()
	 */
	@Override
	public DiagramScrollingBehavior getDiagramScrollingBehavior() {
		return _originalToolBehaviorProvider.getDiagramScrollingBehavior();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#getDoubleClickFeature(org.eclipse.graphiti.features.context.IDoubleClickContext)
	 */
	@Override
	public ICustomFeature getDoubleClickFeature(IDoubleClickContext arg0) {
		return _originalToolBehaviorProvider.getDoubleClickFeature(arg0);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#getLineSelectionWidth(org.eclipse.graphiti.mm.algorithms.Polyline)
	 */
	@Override
	public int getLineSelectionWidth(Polyline arg0) {
		return _originalToolBehaviorProvider.getLineSelectionWidth(arg0);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#getLocationInfo(org.eclipse.graphiti.mm.pictograms.PictogramElement, org.eclipse.graphiti.util.ILocationInfo)
	 */
	@Override
	public ILocationInfo getLocationInfo(PictogramElement arg0,
			ILocationInfo arg1) {
		return _originalToolBehaviorProvider.getLocationInfo(arg0, arg1);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#getPalette()
	 */
	@Override
	public IPaletteCompartmentEntry[] getPalette() {
		return _originalToolBehaviorProvider.getPalette();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#getSelection(org.eclipse.graphiti.mm.pictograms.PictogramElement, org.eclipse.graphiti.mm.pictograms.PictogramElement[])
	 */
	@Override
	public PictogramElement getSelection(PictogramElement arg0,
			PictogramElement[] arg1) {
		return _originalToolBehaviorProvider.getSelection(arg0, arg1);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#getSelectionBorder(org.eclipse.graphiti.mm.pictograms.PictogramElement)
	 */
	@Override
	public GraphicsAlgorithm getSelectionBorder(PictogramElement arg0) {
		return _originalToolBehaviorProvider.getSelectionBorder(arg0);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#getSelectionInfoForAnchor(org.eclipse.graphiti.mm.pictograms.Anchor)
	 */
	@Override
	public IAnchorSelectionInfo getSelectionInfoForAnchor(Anchor arg0) {
		return _originalToolBehaviorProvider.getSelectionInfoForAnchor(arg0);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#getSelectionInfoForConnection(org.eclipse.graphiti.mm.pictograms.Connection)
	 */
	@Override
	public IConnectionSelectionInfo getSelectionInfoForConnection(Connection arg0) {
		return _originalToolBehaviorProvider.getSelectionInfoForConnection(arg0);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#getSelectionInfoForShape(org.eclipse.graphiti.mm.pictograms.Shape)
	 */
	@Override
	public IShapeSelectionInfo getSelectionInfoForShape(Shape arg0) {
		return _originalToolBehaviorProvider.getSelectionInfoForShape(arg0);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#getSingleClickFeature(org.eclipse.graphiti.features.context.ISingleClickContext)
	 */
	@Override
	public ICustomFeature getSingleClickFeature(ISingleClickContext arg0) {
		return _originalToolBehaviorProvider.getSingleClickFeature(arg0);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#getTitleToolTip()
	 */
	@Override
	public String getTitleToolTip() {
		return _originalToolBehaviorProvider.getTitleToolTip();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#getToolTip(org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm)
	 */
	@Override
	public Object getToolTip(GraphicsAlgorithm arg0) {
		return _originalToolBehaviorProvider.getToolTip(arg0);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#getZoomLevels()
	 */
	@Override
	public double[] getZoomLevels() {
		return _originalToolBehaviorProvider.getZoomLevels();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#isConnectionSelectionEnabled()
	 */
	@Override
	public boolean isConnectionSelectionEnabled() {
		return _originalToolBehaviorProvider.isConnectionSelectionEnabled();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#isMultiSelectionEnabled()
	 */
	@Override
	public boolean isMultiSelectionEnabled() {
		return _originalToolBehaviorProvider.isMultiSelectionEnabled();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#isShowFlyoutPalette()
	 */
	@Override
	public boolean isShowFlyoutPalette() {
		return _originalToolBehaviorProvider.isShowFlyoutPalette();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#isShowGuides()
	 */
	@Override
	public boolean isShowGuides() {
		return _originalToolBehaviorProvider.isShowGuides();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#isShowMarqueeTool()
	 */
	@Override
	public boolean isShowMarqueeTool() {
		return _originalToolBehaviorProvider.isShowMarqueeTool();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#isShowSelectionTool()
	 */
	@Override
	public boolean isShowSelectionTool() {
		return _originalToolBehaviorProvider.isShowSelectionTool();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#postExecute(org.eclipse.graphiti.IExecutionInfo)
	 */
	@Override
	public void postExecute(IExecutionInfo arg0) {
		_originalToolBehaviorProvider.postExecute(arg0);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#preExecute(org.eclipse.graphiti.IExecutionInfo)
	 */
	@Override
	public void preExecute(IExecutionInfo arg0) {
		_originalToolBehaviorProvider.preExecute(arg0);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#getDirectEditingInvalidNotificationTitle(org.eclipse.graphiti.features.IDirectEditingFeature, org.eclipse.graphiti.features.context.IDirectEditingContext)
	 */
	@Override
	public String getDirectEditingInvalidNotificationTitle(IDirectEditingFeature var1, IDirectEditingContext var2) {
		return _originalToolBehaviorProvider.getDirectEditingInvalidNotificationTitle(var1, var2);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.tb.IToolBehaviorProvider#isStayActiveAfterExecution(org.eclipse.graphiti.palette.IConnectionCreationToolEntry)
	 */
	@Override
	public boolean isStayActiveAfterExecution(IConnectionCreationToolEntry var1) {
		return _originalToolBehaviorProvider.isStayActiveAfterExecution(var1);
	}
}

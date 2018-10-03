package org.bflow.toolbox.hive.gmfbridge.graphiti.providers;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.notification.INotificationService;
import org.eclipse.graphiti.platform.IDiagramBehavior;
import org.eclipse.graphiti.platform.ga.IGraphicsAlgorithmRendererFactory;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;

/**
 * Provides an adapter of {@link IDiagramTypeProvider} that injects a custom
 * implementation of {@link IToolBehaviorProvider}.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2015-12-23
 * @version 2018-10-03 Updated to latest API
 * 
 */
public class DiagramTypeProviderAdapter implements IDiagramTypeProvider {
	
	private IDiagramTypeProvider _originalDiagramTypeProvider;	

	/**
	 * Creates a new instance based on the given original provider.
	 * 
	 * @param originalDiagramTypeProvider
	 *            Original provider
	 */
	public DiagramTypeProviderAdapter(IDiagramTypeProvider originalDiagramTypeProvider) {
		super();
		_originalDiagramTypeProvider = originalDiagramTypeProvider;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.platform.IExtension#getProviderId()
	 */
	@Override
	public String getProviderId() {
		return _originalDiagramTypeProvider.getProviderId();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.platform.IExtension#setProviderId(java.lang.String)
	 */
	@Override
	public void setProviderId(String arg0) {
		_originalDiagramTypeProvider.setProviderId(arg0);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.features.IFeatureProviderHolder#getFeatureProvider()
	 */
	@Override
	public IFeatureProvider getFeatureProvider() {
		return _originalDiagramTypeProvider.getFeatureProvider();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#dispose()
	 */
	@Override
	public void dispose() {
		_originalDiagramTypeProvider.dispose();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#getAvailableToolBehaviorProviders()
	 */
	@Override
	public IToolBehaviorProvider[] getAvailableToolBehaviorProviders() {
		IToolBehaviorProvider[] originalBehaviorProvider = _originalDiagramTypeProvider.getAvailableToolBehaviorProviders();
		return originalBehaviorProvider;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#getContextId()
	 */
	@Override
	public String getContextId() {
		return _originalDiagramTypeProvider.getContextId();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#getCurrentToolBehaviorIndex()
	 */
	@Override
	public int getCurrentToolBehaviorIndex() {
		return _originalDiagramTypeProvider.getCurrentToolBehaviorIndex();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#getCurrentToolBehaviorProvider()
	 */
	@Override
	public IToolBehaviorProvider getCurrentToolBehaviorProvider() {
		IToolBehaviorProvider toolBehaviorProvider = _originalDiagramTypeProvider.getCurrentToolBehaviorProvider();
		return new ToolBehaviorProviderAdapter(toolBehaviorProvider); // TODO check if there is a memory leak
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#getDiagram()
	 */
	@Override
	public Diagram getDiagram() {
		return _originalDiagramTypeProvider.getDiagram();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#getDiagramBehavior()
	 */
	@Override
	public IDiagramBehavior getDiagramBehavior() {
		return _originalDiagramTypeProvider.getDiagramBehavior();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#getDiagramTitle()
	 */
	@Override
	public String getDiagramTitle() {
		return _originalDiagramTypeProvider.getDiagramTitle();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#getGraphicsAlgorithmRendererFactory()
	 */
	@Override
	public IGraphicsAlgorithmRendererFactory getGraphicsAlgorithmRendererFactory() {
		return _originalDiagramTypeProvider.getGraphicsAlgorithmRendererFactory();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#getNotificationService()
	 */
	@Override
	public INotificationService getNotificationService() {
		return _originalDiagramTypeProvider.getNotificationService();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#getRelatedBusinessObjects(java.lang.Object[])
	 */
	@Override
	public Object[] getRelatedBusinessObjects(Object[] arg0) {
		return _originalDiagramTypeProvider.getRelatedBusinessObjects(arg0);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#init(org.eclipse.graphiti.mm.pictograms.Diagram, org.eclipse.graphiti.platform.IDiagramBehavior)
	 */
	@Override
	public void init(Diagram arg0, IDiagramBehavior arg1) {
		_originalDiagramTypeProvider.init(arg0, arg1);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#isAutoUpdateAtReset()
	 */
	@Override
	public boolean isAutoUpdateAtReset() {
		return _originalDiagramTypeProvider.isAutoUpdateAtReset();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#isAutoUpdateAtRuntime()
	 */
	@Override
	public boolean isAutoUpdateAtRuntime() {
		return _originalDiagramTypeProvider.isAutoUpdateAtRuntime();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#isAutoUpdateAtRuntimeWhenEditorIsSaved()
	 */
	@Override
	public boolean isAutoUpdateAtRuntimeWhenEditorIsSaved() {
		return _originalDiagramTypeProvider.isAutoUpdateAtRuntimeWhenEditorIsSaved();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#isAutoUpdateAtStartup()
	 */
	@Override
	public boolean isAutoUpdateAtStartup() {
		return _originalDiagramTypeProvider.isAutoUpdateAtStartup();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#postInit()
	 */
	@Override
	public void postInit() {
		_originalDiagramTypeProvider.postInit();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#resourceReloaded(org.eclipse.graphiti.mm.pictograms.Diagram)
	 */
	@Override
	public void resourceReloaded(Diagram arg0) {
		_originalDiagramTypeProvider.resourceReloaded(arg0);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#resourcesSaved(org.eclipse.graphiti.mm.pictograms.Diagram, org.eclipse.emf.ecore.resource.Resource[])
	 */
	@Override
	public void resourcesSaved(Diagram arg0, Resource[] arg1) {
		_originalDiagramTypeProvider.resourcesSaved(arg0, arg1);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#setContextId(java.lang.String)
	 */
	@Override
	public void setContextId(String arg0) {
		_originalDiagramTypeProvider.setContextId(arg0);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#setCurrentToolBehaviorIndex(int)
	 */
	@Override
	public void setCurrentToolBehaviorIndex(int arg0) {
		_originalDiagramTypeProvider.setCurrentToolBehaviorIndex(arg0);
	}

}

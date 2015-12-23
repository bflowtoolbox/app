package org.bflow.toolbox.hive.gmfbridge.graphiti.providers;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.notification.INotificationService;
import org.eclipse.graphiti.platform.IDiagramBehavior;
import org.eclipse.graphiti.platform.IDiagramEditor;
import org.eclipse.graphiti.platform.ga.IGraphicsAlgorithmRendererFactory;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;

/**
 * Provides an adapter of {@link IDiagramTypeProvider} that injects a custom
 * implementation of {@link IToolBehaviorProvider}.
 * 
 * @author Arian Storch
 * @since 2015-12-23
 * 
 */
public class DiagramTypeProviderAdapter implements IDiagramTypeProvider {
	
	private IDiagramTypeProvider fOriginalDiagramTypeProvider;	

	/**
	 * Creates a new instance based on the given original provider.
	 * 
	 * @param originalDiagramTypeProvider
	 *            Original provider
	 */
	public DiagramTypeProviderAdapter(IDiagramTypeProvider originalDiagramTypeProvider) {
		super();
		fOriginalDiagramTypeProvider = originalDiagramTypeProvider;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.platform.IExtension#getProviderId()
	 */
	@Override
	public String getProviderId() {
		return fOriginalDiagramTypeProvider.getProviderId();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.platform.IExtension#setProviderId(java.lang.String)
	 */
	@Override
	public void setProviderId(String arg0) {
		fOriginalDiagramTypeProvider.setProviderId(arg0);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.features.IFeatureProviderHolder#getFeatureProvider()
	 */
	@Override
	public IFeatureProvider getFeatureProvider() {
		return fOriginalDiagramTypeProvider.getFeatureProvider();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#dispose()
	 */
	@Override
	public void dispose() {
		fOriginalDiagramTypeProvider.dispose();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#getAvailableToolBehaviorProviders()
	 */
	@Override
	public IToolBehaviorProvider[] getAvailableToolBehaviorProviders() {
		IToolBehaviorProvider[] originalBehaviorProvider = fOriginalDiagramTypeProvider.getAvailableToolBehaviorProviders();
		return originalBehaviorProvider;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#getContextId()
	 */
	@Override
	public String getContextId() {
		return fOriginalDiagramTypeProvider.getContextId();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#getCurrentToolBehaviorIndex()
	 */
	@Override
	public int getCurrentToolBehaviorIndex() {
		return fOriginalDiagramTypeProvider.getCurrentToolBehaviorIndex();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#getCurrentToolBehaviorProvider()
	 */
	@Override
	public IToolBehaviorProvider getCurrentToolBehaviorProvider() {
		IToolBehaviorProvider toolBehaviorProvider = fOriginalDiagramTypeProvider.getCurrentToolBehaviorProvider();
		return new ToolBehaviorProviderAdapter(toolBehaviorProvider); // TODO check if there is a memory leak
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#getDiagram()
	 */
	@Override
	public Diagram getDiagram() {
		return fOriginalDiagramTypeProvider.getDiagram();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#getDiagramBehavior()
	 */
	@Override
	public IDiagramBehavior getDiagramBehavior() {
		return fOriginalDiagramTypeProvider.getDiagramBehavior();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#getDiagramEditor()
	 */
	@Override
	public IDiagramEditor getDiagramEditor() {
		return fOriginalDiagramTypeProvider.getDiagramEditor();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#getDiagramTitle()
	 */
	@Override
	public String getDiagramTitle() {
		return fOriginalDiagramTypeProvider.getDiagramTitle();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#getGraphicsAlgorithmRendererFactory()
	 */
	@Override
	public IGraphicsAlgorithmRendererFactory getGraphicsAlgorithmRendererFactory() {
		return fOriginalDiagramTypeProvider.getGraphicsAlgorithmRendererFactory();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#getNotificationService()
	 */
	@Override
	public INotificationService getNotificationService() {
		return fOriginalDiagramTypeProvider.getNotificationService();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#getRelatedBusinessObjects(java.lang.Object[])
	 */
	@Override
	public Object[] getRelatedBusinessObjects(Object[] arg0) {
		return fOriginalDiagramTypeProvider.getRelatedBusinessObjects(arg0);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#init(org.eclipse.graphiti.mm.pictograms.Diagram, org.eclipse.graphiti.platform.IDiagramEditor)
	 */
	@Override
	public void init(Diagram arg0, IDiagramEditor arg1) {
		fOriginalDiagramTypeProvider.init(arg0, arg1);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#init(org.eclipse.graphiti.mm.pictograms.Diagram, org.eclipse.graphiti.platform.IDiagramBehavior)
	 */
	@Override
	public void init(Diagram arg0, IDiagramBehavior arg1) {
		fOriginalDiagramTypeProvider.init(arg0, arg1);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#isAutoUpdateAtReset()
	 */
	@Override
	public boolean isAutoUpdateAtReset() {
		return fOriginalDiagramTypeProvider.isAutoUpdateAtReset();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#isAutoUpdateAtRuntime()
	 */
	@Override
	public boolean isAutoUpdateAtRuntime() {
		return fOriginalDiagramTypeProvider.isAutoUpdateAtRuntime();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#isAutoUpdateAtRuntimeWhenEditorIsSaved()
	 */
	@Override
	public boolean isAutoUpdateAtRuntimeWhenEditorIsSaved() {
		return fOriginalDiagramTypeProvider.isAutoUpdateAtRuntimeWhenEditorIsSaved();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#isAutoUpdateAtStartup()
	 */
	@Override
	public boolean isAutoUpdateAtStartup() {
		return fOriginalDiagramTypeProvider.isAutoUpdateAtStartup();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#postInit()
	 */
	@Override
	public void postInit() {
		fOriginalDiagramTypeProvider.postInit();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#resourceReloaded(org.eclipse.graphiti.mm.pictograms.Diagram)
	 */
	@Override
	public void resourceReloaded(Diagram arg0) {
		fOriginalDiagramTypeProvider.resourceReloaded(arg0);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#resourcesSaved(org.eclipse.graphiti.mm.pictograms.Diagram, org.eclipse.emf.ecore.resource.Resource[])
	 */
	@Override
	public void resourcesSaved(Diagram arg0, Resource[] arg1) {
		fOriginalDiagramTypeProvider.resourcesSaved(arg0, arg1);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#setContextId(java.lang.String)
	 */
	@Override
	public void setContextId(String arg0) {
		fOriginalDiagramTypeProvider.setContextId(arg0);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider#setCurrentToolBehaviorIndex(int)
	 */
	@Override
	public void setCurrentToolBehaviorIndex(int arg0) {
		fOriginalDiagramTypeProvider.setCurrentToolBehaviorIndex(arg0);
	}

}

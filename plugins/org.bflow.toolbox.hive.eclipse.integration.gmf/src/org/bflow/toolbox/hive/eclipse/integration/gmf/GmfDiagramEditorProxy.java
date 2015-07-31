package org.bflow.toolbox.hive.eclipse.integration.gmf;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.bflow.toolbox.hive.eclipse.integration.DiagramProxyUtil;
import org.bflow.toolbox.hive.eclipse.integration.DiagramProxyUtil.Ref;
import org.bflow.toolbox.hive.eclipse.integration.events.EEditorInputType;
import org.bflow.toolbox.hive.eclipse.integration.events.EEditorLifecycleEventType;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.gmf.runtime.common.ui.action.ActionManager;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocumentEditor;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocumentProvider;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.WorkbenchPart;

/**
 * Implements {@link DiagramDocumentEditor} to proxy an origin GMF based diagram editor. This offers
 * the possibility to hook the Eclipse API.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 25.07.2015
 *
 */
public class GmfDiagramEditorProxy extends DiagramDocumentEditor {
	
	static public final String EditorId = "org.bflow.toolbox.hive.eclipse.integration.internal.editor.diagramEditorProxy.GMF";
	
	private final DiagramProxyUtil fAdapterUtil = new DiagramProxyUtil();
	
	/**
	 * Default constructor.
	 */
	public GmfDiagramEditorProxy() {
		super(true);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		// Dispatch ante event
		fAdapterUtil.dispatchEditorLifecycleEvent(EEditorLifecycleEventType.BeforeInit, EEditorInputType.Xmi, input, null, null);
		
		setSite(site);
		
		// Support origin lifecycle
		IEditorPart originEditorPart = fAdapterUtil.initialize(site, input);
		
		// Initialize the graphical editor
		try {
			fAdapterUtil.initializeGraphicalEditor(originEditorPart);
		} catch (Exception ex) {
			throw new PartInitException(ex.getMessage(), ex);
		}
		
		// Support action bar contribution
		fAdapterUtil.applyCustomDiagramActionBarContributor();
		
		// Delegate property change listener
		originEditorPart.addPropertyListener(new IPropertyListener() {
			@Override
			public void propertyChanged(Object source, int propId) {
//				firePropertyChange(propId);
				
				// Properties may change later again
				setPartName(fAdapterUtil.OriginEditorPart().getTitle());
				setTitleToolTip(fAdapterUtil.OriginEditorPart().getTitleToolTip());
			}
		});
		
		// Set up "outer" view
		setPartName(originEditorPart.getTitle());
		setTitleToolTip(originEditorPart.getTitleToolTip());
		setTitleImage(fAdapterUtil.TitleImage());
		
		// Dispatch post event
		fAdapterUtil.dispatchEditorLifecycleEvent(EEditorLifecycleEventType.AfterInit, EEditorInputType.Xmi, input, null, null);
	}
		
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		// Support origin editor part lifecycle
		fAdapterUtil.OriginEditorPart().createPartControl(parent);
		
		// Add support for custom key handler
		// see http://www.systemoutprintln.de/2011/06/gmf-editor-bind-delete-key-to-delete-from-model-action/
		KeyHandler keyHandler = getDiagramGraphicalViewer().getKeyHandler();	
		
		// Support for DEL to delete an element from the model
		keyHandler.put(
				KeyStroke.getPressed(SWT.DEL, 127, 0),
				getActionRegistry().getAction(ActionIds.ACTION_DELETE_FROM_MODEL)
		);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		// Dispatch ante event
		fAdapterUtil.dispatchEditorLifecycleEvent(EEditorLifecycleEventType.BeforeSave, EEditorInputType.Xmi, 
				fAdapterUtil.OriginEditorPart().getEditorInput(), fAdapterUtil.OriginGraphicalEditor(), getEditingDomain());
				
		fAdapterUtil.OriginEditorPart().doSave(monitor);
		
		// Dispatch post event
		fAdapterUtil.dispatchEditorLifecycleEvent(EEditorLifecycleEventType.AfterSave, EEditorInputType.Xmi, 
				fAdapterUtil.OriginEditorPart().getEditorInput(), fAdapterUtil.OriginGraphicalEditor(), getEditingDomain());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSaveAs()
	 */
	@Override
	public void doSaveAs() {
		fAdapterUtil.OriginEditorPart().doSaveAs();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#getEditorInput()
	 */
	@Override
	public IEditorInput getEditorInput() {
		return fAdapterUtil.OriginEditorPart().getEditorInput();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#isDirty()
	 */
	@Override
	public boolean isDirty() {
		return fAdapterUtil.OriginEditorPart().isDirty();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#isSaveAsAllowed()
	 */
	@Override
	public boolean isSaveAsAllowed() {
		return fAdapterUtil.OriginEditorPart().isSaveAsAllowed();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#dispose()
	 */
	@Override
	public void dispose() {
		fAdapterUtil.OriginEditorPart().dispose();
		// super.dispose(); May lead to exceptions
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		fAdapterUtil.OriginEditorPart().setFocus();
	}
	
	private ArrayList<IPropertyListener> fLateBoundListener;
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#addPropertyListener(org.eclipse.ui.IPropertyListener)
	 */
	@Override
	public void addPropertyListener(IPropertyListener l) {		
		if (fAdapterUtil == null || fAdapterUtil.OriginEditorPart() == null) {
			if (fLateBoundListener == null) fLateBoundListener = new ArrayList<>(); // Due to super ctor call it may not be initialized yet
			fLateBoundListener.add(l); // Save the reference to bind it later
			return;
		}
		
		IEditorPart originEditorPart = fAdapterUtil.OriginEditorPart();
		
		// Now that there is an instance we apply the previous unbound references
		for (IPropertyListener fo : fLateBoundListener) {
			originEditorPart.addPropertyListener(fo);
		}
		fLateBoundListener.clear(); // clean up
		
		originEditorPart.addPropertyListener(l);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#addPartPropertyListener(org.eclipse.jface.util.IPropertyChangeListener)
	 */
	@Override
	public void addPartPropertyListener(IPropertyChangeListener listener) {
		Ref<WorkbenchPart> outRef = Ref._new();
		if (!fAdapterUtil.tryAdapt(WorkbenchPart.class, outRef)) return;
		WorkbenchPart workbenchPart = outRef.Value;
		workbenchPart.addPartPropertyListener(listener);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#getAdapter(java.lang.Class)
	 */
	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		IAdaptable adapterRoot = fAdapterUtil.AdapterRoot();
		return adapterRoot.getAdapter(adapter);
	}	
	
	// REGION Override
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor#getEditingDomain()
	 */
	@Override
	public TransactionalEditingDomain getEditingDomain() {
		Ref<DiagramDocumentEditor> outRef = Ref._new();
		if (!fAdapterUtil.tryAdapt(DiagramDocumentEditor.class, outRef)) return null;
		
		DiagramDocumentEditor diagramDocumentEditor = outRef.Value;
		return diagramDocumentEditor.getEditingDomain();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor#getActionManager()
	 */
	@Override
	protected ActionManager getActionManager() {
		Ref<GraphicalEditor> outRef = Ref._new();
		if (!fAdapterUtil.tryAdapt(GraphicalEditor.class, outRef)) return null;

		GraphicalEditor graphicalEditor = outRef.Value;
		try {			
			ActionManager actionManager = fAdapterUtil.invokeForced(GraphicalEditor.class, graphicalEditor,
											"getActionManager", new Class<?>[0], new Object[0]);
			
			return actionManager;
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#getActionRegistry()
	 */
	@Override
	protected ActionRegistry getActionRegistry() {
		Ref<GraphicalEditor> outRef = Ref._new();
		if (!fAdapterUtil.tryAdapt(GraphicalEditor.class, outRef)) return null;

		GraphicalEditor graphicalEditor = outRef.Value;
		try {			
			ActionRegistry actionRegistry = fAdapterUtil.invokeForced(GraphicalEditor.class, graphicalEditor, 
												"getActionRegistry", new Class<?>[0], new Object[0]);
			
			return actionRegistry;
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor#setInput(org.eclipse.ui.IEditorInput)
	 */
	@Override
	public void setInput(IEditorInput input) {
		Ref<DiagramDocumentEditor> outRef = Ref._new();
		if (!fAdapterUtil.tryAdapt(DiagramDocumentEditor.class, outRef)) return;
		
		DiagramDocumentEditor diagramEditor = outRef.Value;
		diagramEditor.setInput(input);
	}

	@Override
	protected void initializeGraphicalViewer() {
		System.out.println("HUHU");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor#getDocumentProvider()
	 */
	@Override
	public IDocumentProvider getDocumentProvider() {
		Ref<IDocumentEditor> outRef = Ref._new();
		if (!fAdapterUtil.tryAdapt(IDocumentEditor.class, outRef)) return null;
		
		IDocumentEditor documentEditor = outRef.Value;
		if (documentEditor == null) return null;
		return documentEditor.getDocumentProvider();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#getGraphicalViewer()
	 */
	@Override
	protected GraphicalViewer getGraphicalViewer() {
		Ref<GraphicalEditor> outRef = Ref._new();
		if (!fAdapterUtil.tryAdapt(GraphicalEditor.class, outRef)) return null;
		
		GraphicalEditor graphicalEditor = outRef.Value;
		
		try {
			Object graphicalViewer = FieldUtils.readField(graphicalEditor, "graphicalViewer", true);
			return (GraphicalViewer) graphicalViewer;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor#getDiagramGraphicalViewer()
	 */
	@Override
	public IDiagramGraphicalViewer getDiagramGraphicalViewer() {
		Ref<DiagramDocumentEditor> outRef = Ref._new();
		if (!fAdapterUtil.tryAdapt(DiagramDocumentEditor.class, outRef)) return null;
		
		DiagramDocumentEditor diagramDocumentEditor = outRef.Value;
		return diagramDocumentEditor.getDiagramGraphicalViewer();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor#close(boolean)
	 */
	@Override
	public void close(boolean save) {
		Ref<DiagramDocumentEditor> outRef = Ref._new();
		if (!fAdapterUtil.tryAdapt(DiagramDocumentEditor.class, outRef)) return;
		
		DiagramDocumentEditor diagramDocumentEditor = outRef.Value;
		diagramDocumentEditor.close(save);
	}
	
	// ENDREGION override
}

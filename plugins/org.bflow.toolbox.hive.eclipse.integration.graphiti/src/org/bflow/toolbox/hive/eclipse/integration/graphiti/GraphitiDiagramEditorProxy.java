package org.bflow.toolbox.hive.eclipse.integration.graphiti;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import org.bflow.toolbox.hive.eclipse.integration.DiagramProxyUtil;
import org.bflow.toolbox.hive.eclipse.integration.DiagramProxyUtil.Ref;
import org.bflow.toolbox.hive.eclipse.integration.events.EEditorInputType;
import org.bflow.toolbox.hive.eclipse.integration.events.EEditorLifecycleEventType;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.editor.IDiagramContainerUI;
import org.eclipse.graphiti.ui.editor.IDiagramEditorInput;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.part.WorkbenchPart;

/**
 * Implements {@link DiagramEditor} to proxy an origin Graphiti based diagram editor. This offers
 * the possibility to hook the Eclipse API.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 25.07.2015
 *
 */
public class GraphitiDiagramEditorProxy extends DiagramEditor {
	
	static public final String EditorId = "org.bflow.toolbox.hive.eclipse.integration.internal.editor.diagramEditorProxy.Graphiti";
	
	private final DiagramProxyUtil fAdapterUtil = new DiagramProxyUtil();
	
	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.ui.editor.DiagramEditor#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {		
		// Dispatch ante event
		fAdapterUtil.dispatchEditorLifecycleEvent(EEditorLifecycleEventType.BeforeInit, EEditorInputType.Xml, input, null, null);
		
		setSite(site);
		
		// Support origin lifecycle
		IEditorPart originEditorPart = fAdapterUtil.initialize(site, input);
		
		// Note: The graphical editor can be initialize after createPartControl()
		
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
		fAdapterUtil.dispatchEditorLifecycleEvent(EEditorLifecycleEventType.AfterInit, EEditorInputType.Xml, input, null, null);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.ui.editor.DiagramEditor#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		fAdapterUtil.OriginEditorPart().createPartControl(parent);
		
		// Resolve the underlying editor part 
		IEditorPart originEditorPart = fAdapterUtil.OriginEditorPart();
		IEditorPart underlyingEditorPart = unwrapOriginEditorPart(originEditorPart);
		
		// Initialize the graphical editor
		fAdapterUtil.initializeGraphicalEditor(underlyingEditorPart);
		
		// Switch to new adapter root
		fAdapterUtil.AdapterRoot(underlyingEditorPart);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		// Dispatch ante event
		fAdapterUtil.dispatchEditorLifecycleEvent(EEditorLifecycleEventType.BeforeSave, EEditorInputType.Xml, 
				fAdapterUtil.OriginEditorPart().getEditorInput(), fAdapterUtil.OriginGraphicalEditor(), getEditingDomain());
		
		fAdapterUtil.OriginEditorPart().doSave(monitor);
		
		// Dispatch post event
		fAdapterUtil.dispatchEditorLifecycleEvent(EEditorLifecycleEventType.AfterSave, EEditorInputType.Xml, 
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
		// super.dispose(); // May lead to exceptions
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
	
	/* REGION Override */
	
	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.ui.editor.DiagramEditor#getDiagramBehavior()
	 */
	@Override
	public DiagramBehavior getDiagramBehavior() {
		Ref<IDiagramContainerUI> outRef = Ref._new();
		if (!fAdapterUtil.tryAdapt(IDiagramContainerUI.class, outRef)) return null;
		
		IDiagramContainerUI diagramContainerUI = outRef.Value;
		return diagramContainerUI.getDiagramBehavior();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.ui.editor.DiagramEditor#getDiagramTypeProvider()
	 */
	@Override
	public IDiagramTypeProvider getDiagramTypeProvider() {
		Ref<IDiagramContainerUI> outRef = Ref._new();
		if (!fAdapterUtil.tryAdapt(IDiagramContainerUI.class, outRef)) return null;
		
		IDiagramContainerUI diagramContainerUI = outRef.Value;
		return diagramContainerUI.getDiagramTypeProvider();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.ui.editor.DiagramEditor#getDiagramEditorInput()
	 */
	@Override
	public IDiagramEditorInput getDiagramEditorInput() {
		Ref<IDiagramContainerUI> outRef = Ref._new();
		if (!fAdapterUtil.tryAdapt(IDiagramContainerUI.class, outRef)) return null;
		
		IDiagramContainerUI diagramContainerUI = outRef.Value;
		return diagramContainerUI.getDiagramEditorInput();
	}
	
	@Override
	public void close() {
		// TODO Auto-generated method stub
		super.close();
	}
	
	@Override
	public void commandStackChanged(EventObject event) {
		// TODO Auto-generated method stub
		super.commandStackChanged(event);
	}
	
	@Override
	public ActionRegistry getActionRegistry() {
		Ref<DiagramEditor> outRef = Ref._new();
		if (!fAdapterUtil.tryAdapt(DiagramEditor.class, outRef)) return null;
		
		DiagramEditor diagramEditor = outRef.Value;
		return diagramEditor.getActionRegistry();
	}
	
	@Override
	public String getContributorId() {
		// TODO Auto-generated method stub
		return super.getContributorId();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.ui.editor.DiagramEditor#getEditDomain()
	 */
	@Override
	public DefaultEditDomain getEditDomain() {
		Ref<DiagramEditor> outRef = Ref._new();
		if (!fAdapterUtil.tryAdapt(DiagramEditor.class, outRef)) return null;
		
		DiagramEditor diagramEditor = outRef.Value;
		return diagramEditor.getEditDomain();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.ui.editor.DiagramEditor#getEditingDomain()
	 */
	@Override
	public TransactionalEditingDomain getEditingDomain() {
		Ref<DiagramEditor> outRef = Ref._new();
		if (!fAdapterUtil.tryAdapt(DiagramEditor.class, outRef)) return null;
		
		DiagramEditor diagramEditor = outRef.Value;
		return diagramEditor.getEditingDomain();
	}
	
	@Override
	public GraphicalViewer getGraphicalViewer() {
		// TODO Auto-generated method stub
		return super.getGraphicalViewer();
	}
	
	@Override
	public PictogramElement[] getSelectedPictogramElements() {
		// TODO Auto-generated method stub
		return super.getSelectedPictogramElements();
	}
	
	@Override
	public List<?> getSelectionActions() {
		// TODO Auto-generated method stub
		return super.getSelectionActions();
	}
	
	@Override
	public IWorkbenchPart getWorkbenchPart() {
		// TODO Auto-generated method stub
		return super.getWorkbenchPart();
	}
	
	@Override
	public void hookGraphicalViewer() {
		// TODO Auto-generated method stub
		super.hookGraphicalViewer();
	}
	
	@Override
	public void initializeGraphicalViewer() {
		// TODO Auto-generated method stub
		super.initializeGraphicalViewer();
	}
	
	@Override
	public void refreshTitle() {
		// TODO Auto-generated method stub
		super.refreshTitle();
	}
	
	@Override
	public void refreshTitleToolTip() {
		// TODO Auto-generated method stub
		super.refreshTitleToolTip();
	}
	
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub
		super.selectionChanged(part, selection);
	}
	
	@Override
	public void selectPictogramElements(PictogramElement[] pictogramElements) {
		// TODO Auto-generated method stub
		super.selectPictogramElements(pictogramElements);
	}
	
	@Override
	public void setEditDomain(DefaultEditDomain editDomain) {
		// TODO Auto-generated method stub
		super.setEditDomain(editDomain);
	}
	
	@Override
	public void setGraphicalViewer(GraphicalViewer viewer) {
		// TODO Auto-generated method stub
		super.setGraphicalViewer(viewer);
	}
	
	@Override
	public void setPictogramElementForSelection(PictogramElement pictogramElement) {
		// TODO Auto-generated method stub
		super.setPictogramElementForSelection(pictogramElement);
	}
	
	@Override
	public void setPictogramElementsForSelection(PictogramElement[] pictogramElements) {
		// TODO Auto-generated method stub
		super.setPictogramElementsForSelection(pictogramElements);
	}
	
	@Override
	public void updateDirtyState() {
		// TODO Auto-generated method stub
		super.updateDirtyState();
	}	
	
	/* ENDREGION */
	
	protected IEditorPart unwrapOriginEditorPart(IEditorPart editorPart) {
		// Handling MultiPageEditorPart
		if (editorPart instanceof MultiPageEditorPart) {
			MultiPageEditorPart multiPageEditorPart = (MultiPageEditorPart)editorPart;
			editorPart = (IEditorPart) multiPageEditorPart.getSelectedPage();
		}
		
		return editorPart;
	}
}

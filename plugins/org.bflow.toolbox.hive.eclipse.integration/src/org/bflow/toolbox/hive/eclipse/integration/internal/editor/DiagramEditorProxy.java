package org.bflow.toolbox.hive.eclipse.integration.internal.editor;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.bflow.toolbox.hive.eclipse.integration.EclipseIntegrator;
import org.eclipse.core.runtime.IProgressMonitor;
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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.WorkbenchPart;

/**
 * Implements {@link IEditorPart} to proxy an origin diagram editor. This offers
 * the possibility to hook the Eclipse API.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 12.07.2015
 *
 */
public class DiagramEditorProxy extends DiagramDocumentEditor implements IDocumentEditor {

	/** Eclipse Editor Id */
	static public final String EditorId = "org.bflow.toolbox.hive.eclipse.integration.internal.editor.diagramEditorProxy";

	static private Image fTitleImage;
	
	private IEditorPart fOriginEditorPart; // TODO Change to GraphicalEditor
	
	/**
	 * Default constructor. 
	 */
	public DiagramEditorProxy() {
		super(true);
	}

	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		fOriginEditorPart.doSave(monitor);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#doSaveAs()
	 */
	@Override
	public void doSaveAs() {
		fOriginEditorPart.doSaveAs();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#getEditorInput()
	 */
	@Override
	public IEditorInput getEditorInput() {
		return fOriginEditorPart.getEditorInput();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		setSite(site);
//		setInput(input);
		
		String fileName = input.getName();
		IEditorPart originEditorPart = EclipseIntegrator.getOriginDefaultEditorInstance(fileName);
		if (originEditorPart == null) throw new PartInitException("Could not resolve origin editor part!");
		
		fOriginEditorPart = originEditorPart;
		fOriginEditorPart.init(site, input);
		
		String originEditorId = EclipseIntegrator.getOriginDefaultEditorIdFor(fileName);
		Class<?> originEditorClass = originEditorPart.getClass();
		DynamicDiagramActionBarContributor.Instance().updateCurrentEditor(originEditorId, originEditorClass);
		
		fOriginEditorPart.addPropertyListener(new IPropertyListener() {
			@Override
			public void propertyChanged(Object source, int propId) {
//				firePropertyChange(propId);
				
				// Properties may change later again
				setPartName(fOriginEditorPart.getTitle());
				setTitleToolTip(fOriginEditorPart.getTitleToolTip());
			}
		});
		
		if (fTitleImage == null) {
			try (InputStream inputStream = getClass().getResourceAsStream("/images/Beejive-Im-flat-circle-16.png")) {
				Image image = new Image(Display.getCurrent(), inputStream);
				fTitleImage = image;
			} catch (IOException ex) {
				ex.printStackTrace();
			}	
		}
		
		setPartName(fOriginEditorPart.getTitle());
		setTitleToolTip(fOriginEditorPart.getTitleToolTip());
		setTitleImage(fTitleImage);
		
//		try {
//			GraphicalEditor graphicalEditor = (GraphicalEditor) fOriginEditorPart.getAdapter(GraphicalEditor.class);
//			DefaultEditDomain editDomain = (DefaultEditDomain) MethodUtils.invokeMethod(graphicalEditor, "getDiagramEditDomain", new Object[0]);
//			setEditDomain(editDomain);
//			super.init(site, input);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#isDirty()
	 */
	@Override
	public boolean isDirty() {
		boolean baseIsDirty = fOriginEditorPart.isDirty();
		return baseIsDirty;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.EditorPart#isSaveAsAllowed()
	 */
	@Override
	public boolean isSaveAsAllowed() {
		return fOriginEditorPart.isSaveAsAllowed();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#dispose()
	 */
	@Override
	public void dispose() {
		fOriginEditorPart.dispose();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#getAdapter(java.lang.Class)
	 */
	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		return fOriginEditorPart.getAdapter(adapter);
	}
	
	private ArrayList<IPropertyListener> fLateBoundListener;
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#addPropertyListener(org.eclipse.ui.IPropertyListener)
	 */
	@Override
	public void addPropertyListener(IPropertyListener l) {
		if (fOriginEditorPart == null) {
			if (fLateBoundListener == null) fLateBoundListener = new ArrayList<>(); // Due to super ctor call it may not be initialized yet
			fLateBoundListener.add(l); // Save the reference to bind it later
			return;
		}
		
		// Now that there is an instance we apply the previous unbound references
		for (IPropertyListener fo : fLateBoundListener) {
			fOriginEditorPart.addPropertyListener(fo);
		}
		fLateBoundListener.clear(); // clean up
		
		fOriginEditorPart.addPropertyListener(l);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#addPartPropertyListener(org.eclipse.jface.util.IPropertyChangeListener)
	 */
	@Override
	public void addPartPropertyListener(IPropertyChangeListener listener) {
		Ref<WorkbenchPart> outRef = Ref._new();
		if (!tryAdapt(WorkbenchPart.class, outRef)) return;
		WorkbenchPart workbenchPart = outRef.Value;
		workbenchPart.addPartPropertyListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		fOriginEditorPart.createPartControl(parent);
		
		// Apply custom key handler
		applyKeyHandler(); 
	}

	/**
	 * Adds custom key handler / feature
	 */
	private void applyKeyHandler() {
		// see http://www.systemoutprintln.de/2011/06/gmf-editor-bind-delete-key-to-delete-from-model-action/
		KeyHandler keyHandler = getDiagramGraphicalViewer().getKeyHandler();	
		
		// Support for DEL to delete an element from the model
		keyHandler.put(
				KeyStroke.getPressed(SWT.DEL, 127, 0),
				getActionRegistry().getAction(ActionIds.ACTION_DELETE_FROM_MODEL)
		);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor#getActionManager()
	 */
	@Override
	protected ActionManager getActionManager() {
		Ref<GraphicalEditor> outRef = Ref._new();
		if (!tryAdapt(GraphicalEditor.class, outRef)) return null;

		// XXX
		GraphicalEditor graphicalEditor = outRef.Value;
		try {			
			ActionManager actionManager = invokeForced(GraphicalEditor.class, graphicalEditor, 
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
		if (!tryAdapt(GraphicalEditor.class, outRef)) return null;

		// XXX
		GraphicalEditor graphicalEditor = outRef.Value;
		try {			
			ActionRegistry actionRegistry = invokeForced(GraphicalEditor.class, graphicalEditor, 
					"getActionRegistry", new Class<?>[0], new Object[0]);
			
			return actionRegistry;
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}	
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		fOriginEditorPart.setFocus();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor#setInput(org.eclipse.ui.IEditorInput)
	 */
	@Override
	public void setInput(IEditorInput input) {
		Ref<DiagramDocumentEditor> outRef = Ref._new();
		if (!tryAdapt(DiagramDocumentEditor.class, outRef)) return;
		
		DiagramDocumentEditor diagramEditor = outRef.Value;
		diagramEditor.setInput(input);
	}

	@Override
	protected void initializeGraphicalViewer() {
		System.out.println();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor#getDocumentProvider()
	 */
	@Override
	public IDocumentProvider getDocumentProvider() {
		Ref<IDocumentEditor> outRef = Ref._new();
		if (!tryAdapt(IDocumentEditor.class, outRef)) return null;
		
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
		if (!tryAdapt(GraphicalEditor.class, outRef)) return null;
		
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
		if (!tryAdapt(DiagramDocumentEditor.class, outRef)) return null;
		
		DiagramDocumentEditor diagramDocumentEditor = outRef.Value;
		return diagramDocumentEditor.getDiagramGraphicalViewer();
	}
	
	// ====

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor#close(boolean)
	 */
	@Override
	public void close(boolean save) {
		Ref<DiagramDocumentEditor> outRef = Ref._new();
		if (!tryAdapt(DiagramDocumentEditor.class, outRef)) return;
		
		DiagramDocumentEditor diagramDocumentEditor = outRef.Value;
		diagramDocumentEditor.close(save);
	}
	
	/**
	 * Invokes the method with the given name on the given object. The method is
	 * resolved using the given class. This avoids problems with inheritance. If
	 * the method is protected it will be invoked anyhow.
	 * 
	 * @param cls
	 *            Class to resolve the method
	 * @param obj
	 *            Object to apply the method invocation
	 * @param methodName
	 *            Name of the method to invoke
	 * @param parameterTypes
	 *            Types of parameters of the method to invoke
	 * @param args
	 *            Invocation arguments
	 * @return Result of the method invocation
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	protected <TClass, TResult> TResult invokeForced(Class<TClass> cls, Object obj, String methodName, Class<?>[] parameterTypes, Object[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method = cls.getDeclaredMethod(methodName, parameterTypes);
		boolean isAccessible = method.isAccessible();
		method.setAccessible(true);
		@SuppressWarnings("unchecked")
		TResult result = (TResult) method.invoke(obj, args);
		method.setAccessible(isAccessible);
		return result;
	}
	
	protected <T> T adapt(Class<T> cls) {
		if (fOriginEditorPart == null) return null;
		@SuppressWarnings("unchecked")
		T castedInstance = (T) getAdapter(cls);
		return castedInstance;
	}
	
	protected <T> boolean tryAdapt(Class<T> cls, Ref<T> ref) {
		ref.Value = null;
		T castedInstance = adapt(cls);
		if (castedInstance == null) return false;
		ref.Value = castedInstance;
		return true;
	}
	
	static class Ref<T> {
		public T Value;
		
		public static <K> Ref<K> _new() {
			return new Ref<K>();
		}
	}
}

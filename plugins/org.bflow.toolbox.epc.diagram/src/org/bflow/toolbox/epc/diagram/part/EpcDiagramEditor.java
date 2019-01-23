package org.bflow.toolbox.epc.diagram.part;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.bflow.toolbox.check.CheckPlugin;
import org.bflow.toolbox.epc.diagram.listener.EpcDiagramEditorMouseListener;
import org.bflow.toolbox.epc.diagram.views.DragHelper;
import org.bflow.toolbox.epc.extensions.idelete.IntelligentDeleter;
import org.bflow.toolbox.extensions.BflowDiagramEditor;
import org.bflow.toolbox.extensions.helpers.EFSUtil;
import org.bflow.toolbox.hive.attributes.AttributeFile;
import org.bflow.toolbox.hive.attributes.AttributeViewPart;
import org.bflow.toolbox.hive.attributes.IAttributableDocumentEditor;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gmf.runtime.common.ui.services.marker.MarkerNavigationService;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.WorkspaceViewerProperties;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramDropTargetListener;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocumentProvider;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ActionHandler;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ShowInContext;

/**
 * @generated
 * @version 2013-07-31 Edited by Arian Storch<arian.storch@bflow.org>
 * 			2018-10-07 Changed superclass to BflowDiagramEditor
 */
public class EpcDiagramEditor extends BflowDiagramEditor implements
		IGotoMarker, IAttributableDocumentEditor {

	/**
	 * @generated
	 */
	public static final String ID = "epc.diagram.part.BflowEpcDiagramEditorID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final String CONTEXT_ID = "org.bflow.toolbox.epc.diagram.ui.diagramContext"; //$NON-NLS-1$

	/** mouse listener */
	private EpcDiagramEditorMouseListener _mouseListener;

	/**
	 * @generated
	 */
	public EpcDiagramEditor() {
		super(true);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		// Handle files that are not within the workspace
		if (getDocumentProvider() == null) {
			if (input instanceof FileStoreEditorInput) {
				FileStoreEditorInput fileStoreEditorInput = (FileStoreEditorInput)input;
				try {
					IFile file = EFSUtil.importExternalFile(fileStoreEditorInput);
					FileEditorInput fileEditorInput = new FileEditorInput(file);
					input = fileEditorInput;
					setDocumentProvider(input);
				} catch (Exception e) {
					throw new PartInitException("Error on adding external file to workspace!", e); //$NON-NLS-1$
				}
			}
		}
		super.init(site, input);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor#setInput(org.eclipse.ui.IEditorInput)
	 */
	@Override
	public void setInput(IEditorInput input) {
		// Code copied from super class
		try {
			doSetInput(input, true);
		} catch (CoreException x) {
			String fileName = ""; //$NON-NLS-1$
			
			if (input != null)
				fileName = input.getName();
			
			String title = org.bflow.toolbox.epc.diagram.Messages.EpcDiagramEditor_ErrorOnOpening;// EditorMessages.Editor_error_setinput_title;
			String msg = String.format(org.bflow.toolbox.epc.diagram.Messages.EpcDiagramEditor_FileDoesNotExistAnymore, fileName);  // EditorMessages.Editor_error_setinput_message;
			Shell shell= getSite().getShell();
			ErrorDialog.openError(shell, title, null, new Status(IStatus.ERROR, "org.bflow.toolbox.epc.diagram", msg, x)); //$NON-NLS-1$
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditorWithFlyOutPalette#setFocus()
	 */
	@Override
	public void setFocus() {
		super.setFocus();
		IntelligentDeleter.setEpcDiagramEditor(this);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor#isDirty()
	 */
	@Override
	public boolean isDirty() {
		boolean dirtyAttributes = false;
		boolean dirtyEditor = super.isDirty();
		
		AttributeViewPart attributeView = AttributeViewPart.getInstance();
		
		if (attributeView != null) {
			AttributeFile attrFile = attributeView.getAttributeFile();
			
			if (attrFile != null) {
				dirtyAttributes = attrFile.isDirty();
			}
		}		
		return dirtyEditor || dirtyAttributes;
	}

	/**
	 * @generated
	 */
	protected String getContextID() {
		return CONTEXT_ID;
	}

	/**
	 * @generated
	 */
	protected PaletteRoot createPaletteRoot(PaletteRoot existingPaletteRoot) {
		PaletteRoot root = super.createPaletteRoot(existingPaletteRoot);
		new EpcPaletteFactory().fillPalette(root);
		return root;
	}

	/**
	 * @generated
	 */
	protected PreferencesHint getPreferencesHint() {
		return EpcDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
	}

	/**
	 * @generated
	 */
	public String getContributorId() {
		return EpcDiagramEditorPlugin.ID;
	}

	/**
	 * @generated
	 */
	protected IDocumentProvider getDocumentProvider(IEditorInput input) {
		if (input instanceof IFileEditorInput
				|| input instanceof URIEditorInput) {
			return EpcDiagramEditorPlugin.getInstance().getDocumentProvider();
		}
		return super.getDocumentProvider(input);
	}

	/**
	 * @generated
	 */
	public TransactionalEditingDomain getEditingDomain() {

		CheckPlugin.setActiveDiagramType("epc"); //$NON-NLS-1$

		IDocument document = getEditorInput() != null ? getDocumentProvider()
				.getDocument(getEditorInput()) : null;
		if (document instanceof IDiagramDocument) {
			return ((IDiagramDocument) document).getEditingDomain();
		}
		return super.getEditingDomain();
	}

	/**
	 * @generated
	 */
	protected void setDocumentProvider(IEditorInput input) {
		if (input instanceof IFileEditorInput
				|| input instanceof URIEditorInput) {
			setDocumentProvider(EpcDiagramEditorPlugin.getInstance()
					.getDocumentProvider());
		} else {
			super.setDocumentProvider(input);
		}
	}

	/**
	 * @generated
	 */
	public void gotoMarker(IMarker marker) {
		MarkerNavigationService.getInstance().gotoMarker(this, marker);
	}

	/**
	 * @generated
	 */
	public boolean isSaveAsAllowed() {
		return true;
	}

	/**
	 * @generated
	 */
	public void doSaveAs() {
		performSaveAs(new NullProgressMonitor());
	}

	/**
	 * Returns the mouse location registered by the last click within the
	 * diagram editor.
	 * 
	 * @return mouse location registered by the last click.
	 */
	public Point getMouseLocation() {
		return _mouseListener.getMouseLocation();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor#performSave(boolean, org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected void performSave(boolean overwrite, IProgressMonitor progressMonitor) {
		if (AttributeViewPart.getInstance() != null)
			if (AttributeViewPart.getInstance().getAttributeFile() != null)
				if (AttributeViewPart.getInstance().getAttributeFile().isDirty())
					AttributeViewPart.getInstance().getAttributeFile().save();

		super.performSave(overwrite, progressMonitor);
	}

	/**
	 * @generated
	 */
	protected void performSaveAs(IProgressMonitor progressMonitor) {
		if (AttributeViewPart.getInstance() != null)
			if (AttributeViewPart.getInstance().getAttributeFile() != null)
				if (AttributeViewPart.getInstance().getAttributeFile().isDirty())
					AttributeViewPart.getInstance().getAttributeFile().save();

		Shell shell = getSite().getShell();
		IEditorInput input = getEditorInput();
		SaveAsDialog dialog = new SaveAsDialog(shell);
		IFile original = input instanceof IFileEditorInput ? ((IFileEditorInput) input).getFile() : null;
		if (original != null) {
			dialog.setOriginalFile(original);
		}
		dialog.create();
		IDocumentProvider provider = getDocumentProvider();
		if (provider == null) {
			// editor has been programmatically closed while the dialog was open
			return;
		}
		if (provider.isDeleted(input) && original != null) {
			String message = NLS.bind(
					Messages.EpcDiagramEditor_SavingDeletedFile, original
							.getName());
			dialog.setErrorMessage(null);
			dialog.setMessage(message, IMessageProvider.WARNING);
		}
		if (dialog.open() == Window.CANCEL) {
			if (progressMonitor != null) {
				progressMonitor.setCanceled(true);
			}
			return;
		}
		IPath filePath = dialog.getResult();
		if (filePath == null) {
			if (progressMonitor != null) {
				progressMonitor.setCanceled(true);
			}
			return;
		}
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IFile file = workspaceRoot.getFile(filePath);
		final IEditorInput newInput = new FileEditorInput(file);
		// Check if the editor is already open
		IEditorMatchingStrategy matchingStrategy = getEditorDescriptor()
				.getEditorMatchingStrategy();
		IEditorReference[] editorRefs = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage()
				.getEditorReferences();
		for (int i = 0; i < editorRefs.length; i++) {
			if (matchingStrategy.matches(editorRefs[i], newInput)) {
				MessageDialog.openWarning(shell,
						Messages.EpcDiagramEditor_SaveAsErrorTitle,
						Messages.EpcDiagramEditor_SaveAsErrorMessage);
				return;
			}
		}
		boolean success = false;
		try {
			provider.aboutToChange(newInput);
			getDocumentProvider(newInput).saveDocument(progressMonitor,
					newInput,
					getDocumentProvider().getDocument(getEditorInput()), true);
			success = true;
		} catch (CoreException x) {
			IStatus status = x.getStatus();
			if (status == null || status.getSeverity() != IStatus.CANCEL) {
				ErrorDialog.openError(shell,
						Messages.EpcDiagramEditor_SaveErrorTitle,
						Messages.EpcDiagramEditor_SaveErrorMessage, x
								.getStatus());
			}
		} finally {
			provider.changed(newInput);
			if (success) {
				setInput(newInput);
			}
		}

		if (progressMonitor != null) {
			progressMonitor.setCanceled(!success);
		}
	}

	/** Sets the editor dirty. */
	public void firePropertyChanged() {
		this.firePropertyChange(EpcDiagramEditor.PROP_DIRTY);
	}

	/**
	 * @generated
	 */
	public ShowInContext getShowInContext() {
		return new ShowInContext(
				getEditorInput(), getGraphicalViewer().getSelection());
	}

	/**
	 * @generated NOT
	 */
	@SuppressWarnings("restriction")
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		DiagramEditorContextMenuProvider provider = new DiagramEditorContextMenuProvider(
				this, getDiagramGraphicalViewer());
		getDiagramGraphicalViewer().setContextMenu(provider);
		getSite().registerContextMenu(ActionIds.DIAGRAM_EDITOR_CONTEXT_MENU,
				provider, getDiagramGraphicalViewer());

		getWorkspaceViewerPreferenceStore().setValue(WorkspaceViewerProperties.SNAPTOGRID, false);
		getWorkspaceViewerPreferenceStore().setValue(WorkspaceViewerProperties.SNAPTOGEOMETRY, true);
		getWorkspaceViewerPreferenceStore().setValue(WorkspaceViewerProperties.VIEWGRID, false);

		// Add key binding support for zooming
		registerZoomHandler();
	}
	
	private IAction iZoomInAction;
	private IAction iZoomOutAction;
	private IHandler iActionHandlerZoomIn;
	private IHandler iActionHandlerZoomOut;
	private IHandlerActivation iHandlerActivationZoomIn;
	private IHandlerActivation iHandlerActivationZoomOut;
	
	/** Registers custom zoom handlers for this editor. */
	private void registerZoomHandler() {
		if (iZoomInAction == null)
			iZoomInAction = new ZoomInAction(getZoomManager());
		if (iZoomOutAction == null)
			iZoomOutAction = new ZoomOutAction(getZoomManager());
		
		getActionRegistry().registerAction(iZoomInAction);
		getActionRegistry().registerAction(iZoomOutAction);

		IWorkbenchWindow window = getSite().getWorkbenchWindow();
		IHandlerService handlerService = (IHandlerService) window.getService(IHandlerService.class);

		if (iActionHandlerZoomIn == null)
			iActionHandlerZoomIn = new ActionHandler(iZoomInAction);
		
		if (iActionHandlerZoomOut == null)
			iActionHandlerZoomOut = new ActionHandler(iZoomOutAction);
		
		iHandlerActivationZoomIn = handlerService.activateHandler(
				iZoomInAction.getActionDefinitionId(), iActionHandlerZoomIn);
		iHandlerActivationZoomOut = handlerService.activateHandler(
				iZoomOutAction.getActionDefinitionId(), iActionHandlerZoomOut);
	}
	
	/** Un-registers the custom zoom handlers for this editor. */
	private void unregisterZoomHandler() {
		IWorkbenchWindow window = getSite().getWorkbenchWindow();
		IHandlerService handlerService = (IHandlerService) window.getService(IHandlerService.class);
		
		if (iZoomInAction != null) {
			getActionRegistry().removeAction(iZoomInAction);
			handlerService.deactivateHandler(iHandlerActivationZoomIn);
		}
		
		if (iZoomOutAction != null) {
			getActionRegistry().removeAction(iZoomOutAction);
			handlerService.deactivateHandler(iHandlerActivationZoomOut);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor#dispose()
	 */
	@Override
	public void dispose() {
		// Remove key binding support for zooming
		unregisterZoomHandler();
		super.dispose();
	}

	/**
	 * @generated
	 */
	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		getDiagramGraphicalViewer().addDropTargetListener(
				new DropTargetListener(getDiagramGraphicalViewer(),
						LocalSelectionTransfer.getTransfer()) {

					protected Object getJavaObject(TransferData data) {
						return LocalSelectionTransfer.getTransfer()
								.nativeToJava(data);
					}

				});

		getDiagramGraphicalViewer().addDropTargetListener(
				new DropTargetListener(getDiagramGraphicalViewer(),
						LocalTransfer.getInstance()) {

					protected Object getJavaObject(TransferData data) {
						return LocalTransfer.getInstance().nativeToJava(data);
					}

				});

		_mouseListener = new EpcDiagramEditorMouseListener();
		getGraphicalViewer().getControl().addMouseListener(_mouseListener);
		getGraphicalViewer().getControl().addMouseMoveListener(_mouseListener);
	}

	/**
	 * @generated NOT
	 */
	private abstract class DropTargetListener extends DiagramDropTargetListener {

		/**
		 * @generated NOT
		 */

		public EditPartViewer view = null;

		public DropTargetListener(EditPartViewer viewer, Transfer xfer) {
			super(viewer, xfer);
			view = viewer;
		}

		/**
		 * @generated NOT
		 */
		@SuppressWarnings("unchecked")
		protected List getObjectsBeingDropped() {
			if (!DragHelper.sameObject) {
				if (view
						.findObjectAt(this.getDropLocation())
						.getClass()
						.toString()
						.equals("class org.bflow.toolbox.epc.diagram.edit.parts.FunctionNameEditPart") //$NON-NLS-1$
						|| view
								.findObjectAt(this.getDropLocation())
								.getClass()
								.toString()
								.equals("class org.bflow.toolbox.epc.diagram.edit.parts.ProcessInterfaceNameEditPart")) { //$NON-NLS-1$
				} else
					DragHelper.dropTarget = view.findObjectAt(this.getDropLocation());
			}

			TransferData data = getCurrentEvent().currentDataType;
			Collection uris = new HashSet();

			Object transferedObject = getJavaObject(data);
			if (transferedObject instanceof IStructuredSelection) {
				IStructuredSelection selection = (IStructuredSelection) transferedObject;
				for (Iterator it = selection.iterator(); it.hasNext();) {
					Object nextSelectedObject = it.next();
					if (nextSelectedObject instanceof IAdaptable) {
						IAdaptable adaptable = (IAdaptable) nextSelectedObject;
						nextSelectedObject = adaptable
								.getAdapter(EObject.class);
					}

					if (nextSelectedObject instanceof EObject) {
						EObject modelElement = (EObject) nextSelectedObject;
						Resource modelElementResource = modelElement
								.eResource();
						uris.add(modelElementResource.getURI().appendFragment(
								modelElementResource
										.getURIFragment(modelElement)));
					}
				}
			}

			List result = new ArrayList();
			for (Iterator it = uris.iterator(); it.hasNext();) {
				URI nextURI = (URI) it.next();
				EObject modelObject = getEditingDomain().getResourceSet()
						.getEObject(nextURI, true);
				result.add(modelObject);
			}
			return result;
		}

		/**
		 * @generated
		 */
		protected abstract Object getJavaObject(TransferData data);

	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.hive.attributes.IAttributableDocumentEditor#getFileExtension()
	 */
	@Override
	public String getFileExtension() {
		return this.getEditorInput().getName().split("\\.")[1]; //$NON-NLS-1$
	}
}

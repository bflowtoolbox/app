package edu.toronto.cs.openome_model.diagram.part;

import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gmf.runtime.common.ui.services.marker.MarkerNavigationService;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.actions.PromptingDeleteFromModelAction;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocument;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocumentProvider;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.navigator.resources.ProjectExplorer;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.IShowInTargetList;
import org.eclipse.ui.part.ShowInContext;

import edu.toronto.cs.openome_model.diagram.edit.parts.ActorActorCompartmentEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.AgentAgentCompartmentEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.PositionPositionCompartmentEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.RoleRoleCompartmentEditPart;

/**
 * @generated
 */
public class Openome_modelDiagramEditor extends DiagramDocumentEditor implements
		IGotoMarker {

	/**
	 * @generated
	 */
	public static final String ID = "edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final String CONTEXT_ID = "edu.toronto.cs.openome_model.diagram.ui.diagramContext"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public Openome_modelDiagramEditor() {
		super(true);
	}

	/**
	 * Initialize the content of the diagram
	 */
	protected void initializeGraphicalViewerContents() {
		super.initializeGraphicalViewerContents();
		List children = getDiagramEditPart().getChildren();

		//Make sure that collapsed actors are drawn properly
		// fix for ticket #187
		for (Object o : children) {
			if (o instanceof ActorEditPart) {
				IGraphicalEditPart actorCompartment = ((ActorEditPart) o)
						.getChildBySemanticHint(Integer
								.toString(ActorActorCompartmentEditPart.VISUAL_ID));
				((ActorActorCompartmentEditPart) actorCompartment)
						.forceRedirect();
			} else if (o instanceof AgentEditPart) {
				IGraphicalEditPart agentCompartment = ((AgentEditPart) o)
						.getChildBySemanticHint(Integer
								.toString(AgentAgentCompartmentEditPart.VISUAL_ID));

				((AgentAgentCompartmentEditPart) agentCompartment)
						.forceRedirect();
			} else if (o instanceof RoleEditPart) {
				IGraphicalEditPart roleCompartment = ((RoleEditPart) o)
						.getChildBySemanticHint(Integer
								.toString(RoleRoleCompartmentEditPart.VISUAL_ID));

				((RoleRoleCompartmentEditPart) roleCompartment).forceRedirect();
			} else if (o instanceof PositionEditPart) {
				IGraphicalEditPart posCompartment = ((PositionEditPart) o)
						.getChildBySemanticHint(Integer
								.toString(PositionPositionCompartmentEditPart.VISUAL_ID));

				((PositionPositionCompartmentEditPart) posCompartment)
						.forceRedirect();
			}
		}

		Openome_modelDiagramUpdateCommand up = new Openome_modelDiagramUpdateCommand();

		try {
			up.execute(null);
		} catch (ExecutionException e) {
			System.err.println(e.getLocalizedMessage());
		}
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
		new edu.toronto.cs.openome_model.diagram.part.Openome_modelPaletteFactory()
				.fillPalette(root);
		return root;
	}

	/**
	 * @generated
	 */
	protected PreferencesHint getPreferencesHint() {
		return edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT;
	}

	/**
	 * @generated
	 */
	public String getContributorId() {
		return edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin.ID;
	}

	/**
	 * @generated
	 */
	public Object getAdapter(Class type) {
		if (type == IShowInTargetList.class) {
			return new IShowInTargetList() {
				public String[] getShowInTargetIds() {
					return new String[] { ProjectExplorer.VIEW_ID };
				}
			};
		}
		return super.getAdapter(type);
	}

	/**
	 * @generated
	 */
	protected IDocumentProvider getDocumentProvider(IEditorInput input) {
		if (input instanceof IFileEditorInput
				|| input instanceof URIEditorInput) {
			return edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().getDocumentProvider();
		}
		return super.getDocumentProvider(input);
	}

	/**
	 * @generated
	 */
	public TransactionalEditingDomain getEditingDomain() {
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
			setDocumentProvider(edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin
					.getInstance().getDocumentProvider());
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
	 * @generated NOT
	 */
	public boolean isSaveAsAllowed() {
		return !isDirty();
	}

	/**
	 * @generated NOT
	 */
	public void doSaveAs() {
		performMySaveAs(new NullProgressMonitor());
	}

	protected void performMySaveAs(IProgressMonitor progressMonitor) {

		Shell shell = getSite().getShell();
		IEditorInput input = getEditorInput();
		SaveAsDialog dialog = new SaveAsDialog(shell);
		IFile original = input instanceof IFileEditorInput ? ((IFileEditorInput) input)
				.getFile()
				: null;
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
			String message = NLS
					.bind(
							edu.toronto.cs.openome_model.diagram.part.Messages.Openome_modelDiagramEditor_SavingDeletedFile,
							original.getName());
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
				MessageDialog
						.openWarning(
								shell,
								edu.toronto.cs.openome_model.diagram.part.Messages.Openome_modelDiagramEditor_SaveAsErrorTitle,
								edu.toronto.cs.openome_model.diagram.part.Messages.Openome_modelDiagramEditor_SaveAsErrorMessage);
				return;
			}
		}
		boolean success = false;
		try {
			provider.aboutToChange(newInput);

			//The save below leaves the new diagram in sync with the old one			

			//			getDocumentProvider(newInput).saveDocument(progressMonitor,
			//					newInput,
			//					getDocumentProvider().getDocument(getEditorInput()), true);

			//Instead, simply make a new copy of the file to solve the synchronization issue.
			original.copy(filePath, true, progressMonitor);
			success = true;
		} catch (CoreException x) {
			IStatus status = x.getStatus();
			if (status == null || status.getSeverity() != IStatus.CANCEL) {
				ErrorDialog
						.openError(
								shell,
								edu.toronto.cs.openome_model.diagram.part.Messages.Openome_modelDiagramEditor_SaveErrorTitle,
								edu.toronto.cs.openome_model.diagram.part.Messages.Openome_modelDiagramEditor_SaveErrorMessage,
								x.getStatus());
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

	/**
	 * @generated
	 */
	protected void performSaveAs(IProgressMonitor progressMonitor) {
		Shell shell = getSite().getShell();
		IEditorInput input = getEditorInput();
		SaveAsDialog dialog = new SaveAsDialog(shell);
		IFile original = input instanceof IFileEditorInput ? ((IFileEditorInput) input)
				.getFile()
				: null;
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
			String message = NLS
					.bind(
							edu.toronto.cs.openome_model.diagram.part.Messages.Openome_modelDiagramEditor_SavingDeletedFile,
							original.getName());
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
				MessageDialog
						.openWarning(
								shell,
								edu.toronto.cs.openome_model.diagram.part.Messages.Openome_modelDiagramEditor_SaveAsErrorTitle,
								edu.toronto.cs.openome_model.diagram.part.Messages.Openome_modelDiagramEditor_SaveAsErrorMessage);
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
				ErrorDialog
						.openError(
								shell,
								edu.toronto.cs.openome_model.diagram.part.Messages.Openome_modelDiagramEditor_SaveErrorTitle,
								edu.toronto.cs.openome_model.diagram.part.Messages.Openome_modelDiagramEditor_SaveErrorMessage,
								x.getStatus());
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

	/**
	 * @generated
	 */
	public ShowInContext getShowInContext() {
		return new ShowInContext(getEditorInput(), getNavigatorSelection());
	}

	/**
	 * @generated
	 */
	private ISelection getNavigatorSelection() {
		IDiagramDocument document = getDiagramDocument();
		if (document == null) {
			return StructuredSelection.EMPTY;
		}
		Diagram diagram = document.getDiagram();
		IFile file = WorkspaceSynchronizer.getFile(diagram.eResource());
		if (file != null) {
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorItem item = new edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorItem(
					diagram, file, false);
			return new StructuredSelection(item);
		}
		return StructuredSelection.EMPTY;
	}

	/**
	 * @generated NOT
	 */
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		edu.toronto.cs.openome_model.diagram.part.DiagramEditorContextMenuProvider provider = new edu.toronto.cs.openome_model.diagram.part.DiagramEditorContextMenuProvider(
				this, getDiagramGraphicalViewer());
		getDiagramGraphicalViewer().setContextMenu(provider);
		//getSite().registerContextMenu(ActionIds.DIAGRAM_EDITOR_CONTEXT_MENU,provider, getDiagramGraphicalViewer());
	}

	/**
	 * @generated NOT
	 */
	@SuppressWarnings("restriction")
    @Override
	protected KeyHandler getKeyHandler() {
		KeyHandler h = super.getKeyHandler();
		
		// Pair the delete and backspace keys to call the delete from model action, to make sure 
		// that the respective model representation is being deleted.
		PromptingDeleteFromModelAction action = new PromptingDeleteFromModelAction(this);
		action.init();
		this.getActionRegistry().registerAction(action);
				
		h.put(KeyStroke.getPressed(SWT.DEL, 127, 0), getActionRegistry()
				.getAction(ActionIds.ACTION_DELETE_FROM_MODEL));

		h.put(KeyStroke.getPressed(SWT.BS, 8, 0), getActionRegistry()
				.getAction(ActionIds.ACTION_DELETE_FROM_MODEL));

		return h;
	}
}

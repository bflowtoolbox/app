package org.bflow.toolbox.bpmn.interop;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeDescriptor;
import org.bflow.toolbox.hive.interchange.mif.core.InterchangeProcessService;
import org.bflow.toolbox.hive.interchange.mif.core.InterchangeProcessingException;
import org.bflow.toolbox.hive.interchange.store.ExportDescriptorStore;
import org.bflow.toolbox.hive.libs.aprogu.lang.Cast;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.FileEditorInput;

/**
 * Extends {@link DiagramAction} to provide an abstraction implementation for a
 * diagram conversion.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-02-16
 *
 */
public abstract class AbstractConvertDiagramAction extends DiagramAction {
	private final Log _log;

	/**
	 * Initializes the new instance with the given arguments.
	 * 
	 * @param partDescriptor Workbench part descriptor
	 * @param actionId       Action id
	 */
	public AbstractConvertDiagramAction(IWorkbenchPartDescriptor partDescriptor, String actionId) {
		super(partDescriptor.getPartPage());
		setId(actionId);
		_log = LogFactory.getLog(getClass());
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#refresh()
	 */
	@Override
	public void refresh() {
		// Note: 
		// This method is invoked each time the selection changes due to 
		// the isSelectionListener() result
				
		// Triggers calculateEnabled()
		super.refresh();	
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#createTargetRequest()
	 */
	@Override
	protected Request createTargetRequest() {
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#isSelectionListener()
	 */
	@Override
	protected boolean isSelectionListener() {
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#calculateEnabled()
	 */
	@Override
	protected boolean calculateEnabled() {
		IStructuredSelection selection = getStructuredSelection();
		Object selectedObject = selection.getFirstElement();
		return onCalculateEnabled(selectedObject);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#doRun(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected void doRun(IProgressMonitor progressMonitor) {
		// First, select source and target file / folder
		IDiagramWorkbenchPart workbenchPart = getDiagramWorkbenchPart();
		DiagramEditor diagramEditor = Cast.as(DiagramEditor.class, workbenchPart);
		if (diagramEditor == null) return;
		
		IEditorInput editorInput = diagramEditor.getEditorInput();
		IPathEditorInput pathEditorInput = Cast.as(IPathEditorInput.class, editorInput);
		if (pathEditorInput == null) return;
		
		File sourceFile = pathEditorInput.getPath().toFile();
		File targetFile = getTargetFile(sourceFile);
		File targetFolder = targetFile.getParentFile();
		Shell workbenchShell = getWorkbenchPage().getWorkbenchWindow().getShell();
		boolean targetWasOpen = false;
		
		// Check if the target file exists and ask the user how to proceed
		if (targetFile.exists()) {
			// Ask the user how to proceed
			if (!MessageDialog.openQuestion(workbenchShell, 
					Messages.AbstractConvertDiagramAction_DialogTitle,
					Messages.AbstractConvertDiagramAction_ExistingModelDialog_Text)) return;
			
			// Close the editor when the target file is currently open
			targetWasOpen = closeEditorIfRequired(targetFile);
		}
				
		try {
			onConvert(sourceFile, targetFile, targetFolder);
		} catch(Exception ex) {
			_log.error("Error on performing model conversion", ex); //$NON-NLS-1$
			return;
		}
		
		// Refresh the workspace		
		IFile editorInputFile = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(pathEditorInput.getPath());
		IProject project = editorInputFile.getProject();
			
		try {
			project.refreshLocal(IResource.DEPTH_INFINITE, progressMonitor);
		} catch (CoreException ex) {
			throw new RuntimeException(Messages.AbstractConvertDiagramAction_Error_RefreshWorkspace, ex);
		}
		
		// Ask the user to open new model file
		if (!MessageDialog.openQuestion(workbenchShell, 
				Messages.AbstractConvertDiagramAction_DialogTitle, 
				Messages.AbstractConvertDiagramAction_OpenModelDialog_Text)) return;
		
		IWorkbench workbench = getWorkbenchPage().getWorkbenchWindow().getWorkbench();
		IEditorDescriptor desc = workbench.getEditorRegistry().getDefaultEditor(targetFile.getName());
		IFile file = project.getFile(targetFile.getName());
		
		try {
			workbench.getActiveWorkbenchWindow()
			.getActivePage()
			.openEditor(new FileEditorInput(file), desc.getId());
		} catch (PartInitException ex) {
			throw new RuntimeException("Error on opening diagram editor", ex); //$NON-NLS-1$
		}	
	}
	
	/**
	 * Performs the model conversion from the given {@code sourceFile} to the {@code targetFile} 
	 * in the specified {@code targetFolder}.
	 */
	protected void onConvert(File sourceFile, File targetFile, File targetFolder) {
		String edn = getExportDescriptionName();
		IInterchangeDescriptor exportDescriptor = ExportDescriptorStore.getExportDescription(edn);
		if (exportDescriptor == null) return;
		
		try {
			InterchangeProcessService.processExport(sourceFile, targetFolder, exportDescriptor);
		} catch (InterchangeProcessingException ex) {
			throw new RuntimeException(Messages.AbstractConvertDiagramAction_Error_ModelConversion, ex);
		}
	}
	
	/**
	 * Returns TRUE if the action is enabled for the given {@code selectedObject}.
	 */
	protected abstract boolean onCalculateEnabled(Object selectedObject);
	
	/**
	 * Returns the pointer to the target file the converted model is written to.
	 */
	protected abstract File getTargetFile(File sourceFile);
	
	/**
	 * Returns the name of the interchange export description that is used for the
	 * conversion/interchange process.
	 */
	protected abstract String getExportDescriptionName();
	
	/**
	 * Closes the workbench editor that owns the given {@code targetFile} as editor
	 * input. Returns TRUE if an editor has been closed.
	 */
	protected boolean closeEditorIfRequired(File targetFile) {
		IEditorReference[] editors = getWorkbenchPage().getEditorReferences();
		for (int i = -1; ++i != editors.length;) {
			IEditorReference editor = editors[i];
			IEditorInput editorInput;
			try {
				editorInput = editor.getEditorInput();
			} catch (PartInitException ex) {
				_log.error("Error on getting input from editor", ex); //$NON-NLS-1$
				continue;
			}
			IPathEditorInput pathEditorInput = Cast.as(IPathEditorInput.class, editorInput);
			if (pathEditorInput == null) continue;
			
			File editorFile = pathEditorInput.getPath().toFile();
			if (!editorFile.equals(targetFile)) continue;
			
			getWorkbenchPage().closeEditors(new IEditorReference[] { editor }, false);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Returns the file pointer to the target file that is derived from the given
	 * {@code sourceFile}.
	 * 
	 * @param sourceFile Source file
	 * @param fileExtensions File extension
	 * @return File pointer to the derived target file
	 */
	protected File getTargetFile(File sourceFile, String fileExtensions) {
		String sourceFilePath = sourceFile.getAbsolutePath();
		String sourceFileName = FilenameUtils.getBaseName(sourceFilePath);
		String sourceFileDirPath = FilenameUtils.getFullPath(sourceFilePath);
		String targetFilePath = FilenameUtils.concat(sourceFileDirPath, sourceFileName + fileExtensions); //$NON-NLS-1$
		return new File(targetFilePath);
	}
	
	/** Current class log */
	protected Log Log() {
		return _log;
	}
}

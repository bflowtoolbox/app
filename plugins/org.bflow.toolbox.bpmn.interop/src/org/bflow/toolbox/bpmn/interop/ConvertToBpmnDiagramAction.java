package org.bflow.toolbox.bpmn.interop;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.bflow.toolbox.epc.diagram.edit.parts.EpcEditPart;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeDescriptor;
import org.bflow.toolbox.hive.interchange.mif.core.InterchangeProcessService;
import org.bflow.toolbox.hive.interchange.mif.core.InterchangeProcessingException;
import org.bflow.toolbox.hive.interchange.store.ExportDescriptorStore;
import org.bflow.toolbox.hive.libs.aprogu.lang.Cast;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.PlatformUI;

import oepc.diagram.edit.parts.OEPCEditPart;

/**
 * Implements {@link IAction} to provide the BPMN model conversion action.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2018-10-07
 *
 */
public class ConvertToBpmnDiagramAction extends DiagramAction {

	/** Action id */
	public static final String Id = "org.bflow.toolbox.bpmn.interop.actions.convertToBpmn"; //$NON-NLS-1$
	
	/**
	 * Initializes the new instance.
	 * 
	 * @param partDescriptor Part descriptor
	 */
	public ConvertToBpmnDiagramAction(IWorkbenchPartDescriptor partDescriptor) {
		super(partDescriptor.getPartPage());
		setId(Id);
		setText(Messages.ConvertToBpmnDiagramAction_Text);
		setToolTipText(Messages.ConvertToBpmnDiagramAction_ToolTipText);
		setImageDescriptor(BpmnInteropPlugin.getDefault().imageDescriptorFromPlugin("icons/convert16.png")); //$NON-NLS-1$
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
		
		// Check if the target file exists and ask the user how to proceed
		if (targetFile.exists()) {
			if (!MessageDialog.openQuestion(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), 
					Messages.ConvertToBpmnDiagramAction_MessageDialog_Title, 
					Messages.ConvertToBpmnDiagramAction_MessageDialog_Hint)) return;
		}
		
		// Perform the conversion
		File targetFolder = targetFile.getParentFile();		
		IInterchangeDescriptor descriptor = ExportDescriptorStore.getExportDescription("BPMN"); //$NON-NLS-1$
		if (descriptor == null) return;
		
		try {
			InterchangeProcessService.processExport(sourceFile, targetFolder, descriptor);
		} catch (InterchangeProcessingException ex) {
			throw new RuntimeException(
					Messages.ConvertToBpmnDiagramAction_Error_ModelConversion, 
					ex);
		}
		
		// Refresh the workspace
		IFile eclipseFile = ResourcesPlugin.getWorkspace().getRoot().getFile(pathEditorInput.getPath());
		IContainer eclipseFolder = eclipseFile.getParent();
		
		try {
			// 2018-10-07 AST
			// Unfortunately this approach is not working
			eclipseFolder.refreshLocal(IResource.DEPTH_INFINITE, progressMonitor);
			
			// This is working, but may have a poor performance
			ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, progressMonitor);
		} catch (CoreException ex) {
			throw new RuntimeException(
					Messages.ConvertToBpmnDiagramAction_Error_RefreshWorkspace, 
					ex);
		}
	}
	
	/**
	 * Returns the file pointer to the target file that is derived from the given
	 * {@code sourceFile}.
	 * 
	 * @param sourceFile Source file
	 * @return File pointer to the derived target file
	 */
	private File getTargetFile(File sourceFile) {
		String sourceFilePath = sourceFile.getAbsolutePath();
		String sourceFileName = FilenameUtils.getBaseName(sourceFilePath);
		String sourceFileDirPath = FilenameUtils.getFullPath(sourceFilePath);
		String targetFilePath = FilenameUtils.concat(sourceFileDirPath, sourceFileName + ".bpmn"); //$NON-NLS-1$
		return new File(targetFilePath);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#calculateEnabled()
	 */
	@Override
	protected boolean calculateEnabled() {
		IStructuredSelection selection = getStructuredSelection();
		Object firstElement = selection.getFirstElement();
		return (firstElement instanceof EpcEditPart || firstElement instanceof OEPCEditPart);
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
}

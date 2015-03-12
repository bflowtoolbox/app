package org.bflow.toolbox.hive.interchange.wizard;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.bflow.toolbox.hive.interchange.AddonsInterchangePlugin;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeDescriptor;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeProcessor;
import org.bflow.toolbox.hive.interchange.mif.core.InterchangeProcessService;
import org.bflow.toolbox.hive.interchange.mif.core.InterchangeProcessorStore;
import org.bflow.toolbox.hive.interchange.store.ExportDescriptorStore;
import org.bflow.toolbox.hive.interchange.utils.EclipseUtil;
import org.bflow.toolbox.hive.interchange.wizard.pages.MIFExportWizardPage;
import org.bflow.toolbox.hive.nls.NLSupport;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.ResourceUtil;

/**
 * Implements a wizard for exporting diagrams using the MIF.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 14.08.09
 * @version 28.12.13
 * 			27.02.15 Re-worked dirty check
 * 			12.03.15 Fixed bug of handling multiple selection
 * 
 */
public class MIFExportWizard extends Wizard implements IExportWizard {
	private MIFExportWizardPage exportWizardPage;

	private IStructuredSelection selection;
	
	/**
	 * Creates a new instance.
	 */
	public MIFExportWizard() {
		IDialogSettings dialogSettings = AddonsInterchangePlugin.getInstance().getDialogSettings();
		setDialogSettings(dialogSettings);
		setNeedsProgressMonitor(true);
	}

	@Override
	public boolean performFinish() {
		String targetFile = exportWizardPage.getTextFieldTargetFile().getText();
		// String sourceFile =
		// exportWizardPage.getTextFieldSourceFile().getText();

		IInterchangeDescriptor exportDescription = exportWizardPage.getSelectedExportDescription();

		if (exportDescription == null) {
			exportWizardPage.setMessage(NLSupport.MIFExportWizard_ErrorMessage1, WizardPage.ERROR);
			return false;
		}

		if (targetFile.isEmpty()) {
			exportWizardPage.setMessage(NLSupport.MIFExportWizard_ErrorMessage2, WizardPage.ERROR);
			return false;
		}

		// Tell the wizard page that the will wizard will now perform its finish method
		exportWizardPage.onWizardPerformsFinish();
		
		Object selFiles[] = selection.toArray();

		try {
			performExport(exportDescription, selFiles, targetFile);
		} catch(Exception ex) {
			return false;
		}

		return true;
	}
	
	/**
	 * Performs the export within a progress monitor dialog.
	 * 
	 * @param exportDescription
	 *            Export description to use
	 * @param selFiles
	 *            Array of files to use
	 * @param targetFile
	 *            Path to the target directory
	 * @throws InvocationTargetException
	 * @throws InterruptedException
	 */
	private void performExport(final IInterchangeDescriptor exportDescription, final Object[] selFiles, final String targetFile) throws InvocationTargetException, InterruptedException {
		getContainer().run(false, false, new IRunnableWithProgress() {
			
			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				monitor.beginTask(NLSupport.MIFExportWizard_ProgressDialogText, IProgressMonitor.UNKNOWN);
				
				for (int i = 0; i < selFiles.length; i++) {
					// Process event queue
					while (Display.getCurrent().readAndDispatch());
					
					IFile sFile = (IFile) selFiles[i];
					
					IInterchangeProcessor processor = InterchangeProcessorStore.getExportProcessorFor(exportDescription);
					
					File srcFile = sFile.getLocation().toFile();
					File tgtFile = new File(targetFile);
					
					try {					
						InterchangeProcessService.processExport(srcFile, tgtFile, processor, exportDescription);
					} catch(Exception ex) {
						AddonsInterchangePlugin.logError(ex.getMessage(), ex);
						EclipseUtil.createErrorDetailsDialog(NLSupport.MIFExportWizard_ErrorDetailsDialogText, ex, true);
					}
				}
				
				monitor.done();
			}
		});
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle(NLSupport.MIFExportWizard_WindowTitleText);
		if (selection == null || selection.isEmpty()) return;
		
		this.selection = selection;
		Object[] selectedItems = this.selection.toArray();
		
		for (int i = -1; ++i != selectedItems.length;) {
			Object selectedObject = selectedItems[i];
			
			// If an object within the editor is selected, resolve the underlying file
			if (selectedObject instanceof EditPart) {
				IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
				selectedObject = activeEditor.getEditorInput();
			}
			
			// Look up the editor that is used to edit this file
			// If there is any and it's dirty, ask the user how to proceed
			IFile resourceFile = ResourceUtil.getFile(selectedObject);
			IEditorPart editorPart = ResourceUtil.findEditor(workbench.getActiveWorkbenchWindow().getActivePage(), resourceFile);
			if (editorPart != null) {
				if (editorPart.isDirty()) {
					if (MessageDialog.openQuestion(editorPart.getSite().getShell(), 
							NLSupport.MIFExportWizard_QuestionDialogTitle,
							NLSupport.MIFExportWizard_QuestionDialogText))
						editorPart.getSite().getPage().saveEditor(editorPart, false);
					else
						return;
				}
			}
			
			// Replace the object by the resolved resource file
			selectedItems[i] = resourceFile;
		}
		
		this.selection = new StructuredSelection(selectedItems);

		// If there is at least one dirty editor ask the user 
		// Problem: Dialog will appear even if the selected model to export isn't opened 
		// if (!workbench.saveAllEditors(true)) return;

		exportWizardPage = new MIFExportWizardPage(this.selection);
		String diagramEditorFileExtension = resolveDiagramEditorFileExtension(this.selection);
		List<IInterchangeDescriptor> matchingExportDescriptions = resolveAvailableExportDescriptors(diagramEditorFileExtension);
		
		exportWizardPage.setExportDescriptions(matchingExportDescriptions);

		this.addPage(exportWizardPage);
	}

	/**
	 * Resolves all available export descriptors.
	 * 
	 * @return the list of available export descriptions
	 */
	private List<IInterchangeDescriptor> resolveAvailableExportDescriptors(String diagramEditorFileExtension) {
		return ExportDescriptorStore.getDepository(diagramEditorFileExtension, false);
	}

	/**
	 * Resolves the diagram editor file extension.
	 * 
	 * @param selection
	 *            the selection
	 * @return the diagram editor file extension or null
	 */
	private String resolveDiagramEditorFileExtension(IStructuredSelection selection) {
		if (selection.isEmpty()) return null;
		
		IFile file = (IFile)selection.getFirstElement();
		String fExtension = file.getFileExtension();
		return fExtension;
	}
}

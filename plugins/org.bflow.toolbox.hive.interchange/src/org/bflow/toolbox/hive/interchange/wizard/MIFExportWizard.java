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
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

/**
 * Implements a wizard for exporting diagrams using the MIF.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 14/08/09
 * @version 28/12/13
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
			public void run(IProgressMonitor monitor) throws InvocationTargetException,
					InterruptedException {
				monitor.beginTask(NLSupport.MIFExportWizard_ProgressDialogText, IProgressMonitor.UNKNOWN);
				
				for (int i = 0; i < selFiles.length; i++) {
					// Process event queue
					while(Display.getCurrent().readAndDispatch());
					
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

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle(NLSupport.MIFExportWizard_WindowTitleText);

		if (selection != null && !selection.isEmpty()) {
			this.selection = selection;

			Object obj = selection.getFirstElement();

			if (!(obj instanceof IFile)) {
				if (obj instanceof EditPart) {
					IEditorPart activeEditor = PlatformUI.getWorkbench()
							.getActiveWorkbenchWindow().getActivePage().getActiveEditor();

					if (activeEditor != null) {
						if (activeEditor.isDirty())
							if (MessageDialog.openQuestion(activeEditor.getSite().getShell(), 
									NLSupport.MIFExportWizard_QuestionDialogTitle,
									NLSupport.MIFExportWizard_QuestionDialogText))
								activeEditor.getSite().getPage().saveEditor(activeEditor, false);
							else
								return;

						IEditorInput input = activeEditor.getEditorInput();
						if (input instanceof IFileEditorInput) {
							final IFile resource = ((IFileEditorInput) input).getFile();
							this.selection = new StructuredSelection(resource);
						}
					}

				} else {
					MessageDialog.openInformation(workbench.getDisplay().getActiveShell(), 
							NLSupport.MIFExportWizard_InformationDialogTitle, 
							NLSupport.MIFExportWizard_InformationDialogText);

					return;
				}

			}

			// if(!workbench.saveAllEditors(true))
			// return ;

			exportWizardPage = new MIFExportWizardPage(this.selection);
			String diagramEditorFileExtension = resolveDiagramEditorFileExtension(this.selection);
			List<IInterchangeDescriptor> matchingExportDescriptions = 
				resolveAvailableExportDescriptors(diagramEditorFileExtension);
			
			exportWizardPage.setExportDescriptions(matchingExportDescriptions);

			this.addPage(exportWizardPage);
		}
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
		if(selection.isEmpty()) {
			return null;
		}
		
		IFile file = (IFile)selection.getFirstElement();
		String fExtension = file.getFileExtension();
		return fExtension;
	}
}

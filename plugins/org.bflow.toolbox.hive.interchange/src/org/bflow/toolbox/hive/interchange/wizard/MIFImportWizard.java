package org.bflow.toolbox.hive.interchange.wizard;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.EnumSet;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.bflow.toolbox.hive.actions.BestSizeAction;
import org.bflow.toolbox.hive.actions.PackPageAction;
import org.bflow.toolbox.hive.interchange.AddonsInterchangePlugin;
import org.bflow.toolbox.hive.interchange.commons.CommonInterchangeUtil;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeDescriptor;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeProcessor;
import org.bflow.toolbox.hive.interchange.mif.core.InterchangeProcessService;
import org.bflow.toolbox.hive.interchange.mif.core.InterchangeProcessorStore;
import org.bflow.toolbox.hive.interchange.store.ImportDescriptorStore;
import org.bflow.toolbox.hive.interchange.utils.EclipseUtil;
import org.bflow.toolbox.hive.interchange.wizard.pages.MIFImportWizardPage;
import org.bflow.toolbox.hive.nls.NLSupport;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

/**
 * Implements a wizard for importing diagrams using the MIF.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 17/08/09
 * @version 22/08/13
 * 
 */
public class MIFImportWizard extends Wizard implements IImportWizard {
	
	/** The import wizard page. */
	private MIFImportWizardPage importWizardPage;
	
	/**
	 * Creates a new instance.
	 */
	public MIFImportWizard() {
		IDialogSettings dialogSettings = AddonsInterchangePlugin.getInstance().getDialogSettings();
		setDialogSettings(dialogSettings);
		setNeedsProgressMonitor(true);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		String sourceFile = importWizardPage.getTextFieldFile().getText();
		String targetFile = importWizardPage.getTextFieldTarget().getText();
		IInterchangeDescriptor importDescriptor = importWizardPage.getSelectedImportDescription();

		if (importDescriptor == null) {
			importWizardPage.setErrorMessage(NLSupport.MIFImportWizard_ErrorNoImportFormat);
			return false;
		}

		if (sourceFile.isEmpty()) {
			importWizardPage.setErrorMessage(NLSupport.MIFImportWizard_ErrorNoSources);
			return false;
		}

		if (targetFile.isEmpty()) {
			importWizardPage.setErrorMessage(NLSupport.MIFImportWizard_ErrorNoProject);
			return false;
		}
		
		// Tell the wizard page that the will wizard will now perform its finish method
		importWizardPage.onWizardPerformsFinish();

		String fileNames[] = sourceFile.split("; "); //$NON-NLS-1$
		String bPath = importWizardPage.getBasicPath();
		
		// Create import process info
		ImportProcessInfo processInfo = new ImportProcessInfo();
		processInfo.ImportDescriptor = importDescriptor;
		processInfo.BasicPath = bPath;
		processInfo.FileNames = fileNames;
		processInfo.SourceFile = sourceFile;
		processInfo.TargetFile = targetFile;
		processInfo.ImportOptions = importWizardPage.getImportOptions();
		
		
		try {
			performImport(processInfo);
		} catch(Exception ex) {
			return false;
		}

		return true;
	}
	
	/**
	 * Performs the import within an progress monitor dialog.
	 *
	 * @param importDescriptor The import descriptor
	 * @param bPath The basic file path
	 * @param fileNames The collection of file names
	 * @param sourceFile The source file
	 * @param targetFile The target file
	 * @throws InvocationTargetException the invocation target exception
	 * @throws InterruptedException the interrupted exception
	 */
	private void performImport(final ImportProcessInfo processInfo) 
			throws InvocationTargetException, InterruptedException {
		
		getContainer().run(false, false, new IRunnableWithProgress() {
			
			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {		
				monitor.beginTask(NLSupport.MIFImportWizard_BeginTask, IProgressMonitor.UNKNOWN);
				
				IInterchangeProcessor interchangeProcessor = InterchangeProcessorStore.getImportProcessorFor(processInfo.ImportDescriptor);

				for (String fileName : processInfo.FileNames)		
					try {
						// Process the import
						InterchangeProcessService.processImport(
								new File(processInfo.BasicPath + "/" + fileName), new File(processInfo.TargetFile),  //$NON-NLS-1$
								interchangeProcessor, processInfo.ImportDescriptor);
						
						// Free memory
						System.gc();
						
						// Process event queue
						while(Display.getCurrent().readAndDispatch());
						
						// Replacing the file extension
						String fileExtension = FilenameUtils.getExtension(fileName);
						String targetFileExtension = processInfo.ImportDescriptor.getApplicableDiagramEditorTypes()[0];
						fileName = fileName.replace(fileExtension, targetFileExtension);
						
						// Process import options
						processImportOptions(new File(processInfo.TargetFile + "/" + fileName), processInfo.ImportOptions); //$NON-NLS-1$
					} catch(Exception ex) {
						AddonsInterchangePlugin.logError(ex.getMessage(), ex);
						EclipseUtil.createErrorDetailsDialog(NLSupport.MIFImportWizard_ExceptionNoFinish, ex, true);
						throw new InterruptedException();
					}

				try {
					ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);
				} catch (CoreException ex) {
					AddonsInterchangePlugin.logError(ex.getMessage(), ex);
					EclipseUtil.createErrorDetailsDialog(NLSupport.MIFImportWizard_ErrorOnRefresh, ex, false);
				}
				
				monitor.done();
			}
		});	
	}
	
	/**
	 * Process the import options.
	 * 
	 * @param diagramFile
	 *            File that is being processed
	 * @param importOptions
	 *            Set of import options.
	 */
	private void processImportOptions(File diagramFile, EnumSet<EImportOption> importOptions) {
		if(importOptions == null || importOptions.isEmpty())
			return;
		
		Shell shell = new Shell();
		IFile file = CommonInterchangeUtil.toIFile(diagramFile);
		
		DiagramEditPart diagramEditPart = null;
		Diagram diagram = null;
		
		try {
			diagramEditPart = CommonInterchangeUtil.getOffscreenDiagramEditPart(shell, file);
			diagram = (Diagram) diagramEditPart.getDiagramView();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		if(diagramEditPart == null || diagram == null)
			return;
		
		if(importOptions.contains(EImportOption.AutoSize)) {
				BestSizeAction action = new BestSizeAction();
				action.selectionChanged(null, new StructuredSelection(diagramEditPart));
				action.run((IAction)null);		
			try {
				diagramEditPart = CommonInterchangeUtil.saveAndReload(diagramEditPart, file, shell);
				diagram = (Diagram) diagramEditPart.getDiagramView();
			} catch (CoreException e) {
				e.printStackTrace();
			}	
		}
		
		if(importOptions.contains(EImportOption.ArrangeAll)) {
			ArrangeRequest request = new ArrangeRequest(ActionIds.ACTION_ARRANGE_ALL);
			diagramEditPart.performRequest(request);
			
			try {
				diagramEditPart = CommonInterchangeUtil.saveAndReload(diagramEditPart, file, shell);
				diagram = (Diagram) diagramEditPart.getDiagramView();
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
		if(importOptions.contains(EImportOption.PackPage)) {	
			PackPageAction action = new PackPageAction();
			action.setDiagramEditPart(diagramEditPart);
			action.doRun();
			
			try {
				diagramEditPart = CommonInterchangeUtil.saveAndReload(diagramEditPart, file, shell);
				diagram = (Diagram) diagramEditPart.getDiagramView();
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
		shell.dispose();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle(NLSupport.MIFImportWizard_Title);
		
		importWizardPage = new MIFImportWizardPage();
		importWizardPage.onInitialize(workbench, selection);
		importWizardPage.setImportDescriptions(resolveAvailableImportDescriptions());
		
		addPage(importWizardPage);
	}

	/**
	 * Resolves all available import descriptors.
	 * 
	 * @return Collection of all available import descriptors
	 */
	private List<IInterchangeDescriptor> resolveAvailableImportDescriptions() {
		return ImportDescriptorStore.getDepository();
	}
	
	/**
	 * Defines properties of an import process.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 11/08/13
	 */
	class ImportProcessInfo {
		public IInterchangeDescriptor ImportDescriptor;
		public String BasicPath;
		public String[] FileNames;
		public String SourceFile;
		public String TargetFile;
		public EnumSet<EImportOption> ImportOptions;
	}
}

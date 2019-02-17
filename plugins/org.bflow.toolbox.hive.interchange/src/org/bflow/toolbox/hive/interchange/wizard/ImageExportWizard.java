package org.bflow.toolbox.hive.interchange.wizard;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bflow.toolbox.hive.interchange.wizard.pages.ImageExportWizardPage;
import org.eclipse.core.resources.IFile;
import org.eclipse.gmf.runtime.diagram.ui.render.actions.CopyToImageAction;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

/**
 * Implements a wizard that is used for saving one or more selected diagram files 
 * as images.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2013-07-19
 */
public class ImageExportWizard extends Wizard implements IExportWizard {
	private Log _log = LogFactory.getLog(ImageExportWizard.class);
	private IWorkbenchPage _workbenchPage;
	private IStructuredSelection _selection;
	
	/** Initializes the new instance. */
	public ImageExportWizard() {
		setWindowTitle("Toolbox Image Export");
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#canFinish()
	 */
	@Override
	public boolean canFinish() {
		boolean canFinish = super.canFinish();
		boolean someSelection = !_selection.isEmpty();
		return someSelection && canFinish;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		_workbenchPage = workbench.getActiveWorkbenchWindow().getActivePage();
		_selection = selection;
		addPage(new ImageExportWizardPage());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		Object[] selectedElements = _selection.toArray();
		
		for(int i = 0; i < selectedElements.length; i++) {
			Object selectedElement = selectedElements[i];
			
			// Should be impossible
			if (selectedElement instanceof IEditorPart) {
				throw new NotImplementedException();
			}
			
			if (selectedElement instanceof IFile) {
				IFile file = (IFile)selectedElement;
				IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(file.getName());
				try {
					_workbenchPage.openEditor(new FileEditorInput(file), desc.getId());
					CopyToImageAction copyToImageAction = new CopyToImageAction(_workbenchPage);
					copyToImageAction.init();
					copyToImageAction.run();
				} catch (Exception ex) {
					_log.error("Error on executing image export", ex);
				}
			}
		}
		
		return true;
	}
}

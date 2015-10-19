package org.bflow.toolbox.hive.interchange.wizard;

import org.bflow.toolbox.hive.interchange.wizard.pages.ImageExportWizardPage;
import org.eclipse.core.resources.IFile;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gmf.runtime.diagram.ui.render.actions.CopyToImageAction;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

/**
 * Implements a wizard that is used for saving one or more selected diagram files 
 * as images.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 19/07/13
 */
public class ImageExportWizard extends Wizard implements IExportWizard {

	/** The workbench page. */
	private IWorkbenchPage iWorkbenchPage;
	
	/** The selection. */
	private IStructuredSelection iSelection;
	
	/**
	 * Instantiates a new image export wizard.
	 */
	public ImageExportWizard() {
		setWindowTitle("Toolbox Image Export");
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#canFinish()
	 */
	@Override
	public boolean canFinish() {
		boolean canFinish = super.canFinish();
		boolean someSelection = !iSelection.isEmpty();
		return someSelection && canFinish;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		iWorkbenchPage = workbench.getActiveWorkbenchWindow().getActivePage();
		iSelection = selection;
		addPage(new ImageExportWizardPage());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		Object[] selectedElements = iSelection.toArray();
		
		for(int i = 0; i < selectedElements.length; i++) {
			Object selectedElement = selectedElements[i];
			
			if (selectedElement instanceof AbstractEditPart) {
				copyToImage();
			}
			
			if(selectedElement instanceof IFile) {
				IFile file = (IFile)selectedElement;
				IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(file.getName());
				try {
					iWorkbenchPage.openEditor(new FileEditorInput(file), desc.getId());
				} catch (PartInitException e) {
					throw new RuntimeException(e);
				}
				copyToImage();
			}
		}
		return true;
	}

	private void copyToImage() {
		CopyToImageAction copyToImageAction = new CopyToImageAction(iWorkbenchPage);
		copyToImageAction.init();
		copyToImageAction.run();
	}
}

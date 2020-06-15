package org.bflow.toolbox.epc.diagram.part;

import java.lang.reflect.InvocationTargetException;

import org.bflow.toolbox.extensions.IDiagramCreationWizard;
import org.bflow.toolbox.extensions.wizards.DiagramPageSetupWizardPage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

/**
 * @generated
 * @version 2013-07-22 AST
 * 			2019-01-27 AST Added IDiagramCreationWizard implementation
 * 			
 */
public class EpcCreationWizard extends Wizard implements INewWizard, IDiagramCreationWizard {

	/**
	 * @generated
	 */
	private IWorkbench workbench;

	/**
	 * @generated
	 */
	protected IStructuredSelection selection;

	/**
	 * @generated
	 */
	protected EpcCreationWizardPage diagramModelFilePage;
	
	/**
	 * The <code>DiagramPageSetupWizardPage</code> to set the diagram size.
	 * @generated NOT
	 */
	private DiagramPageSetupWizardPage pageSetupWizardPage;

	/**
	 * @generated
	 */
	protected Resource diagram;

	/**
	 * @generated
	 */
	private boolean openNewlyCreatedDiagramEditor = true;

	/**
	 * @generated
	 */
	public IWorkbench getWorkbench() {
		return workbench;
	}

	/**
	 * @generated
	 */
	public IStructuredSelection getSelection() {
		return selection;
	}

	/**
	 * @generated
	 */
	public Resource getDiagram() {
		return diagram;
	}

	/**
	 * @generated
	 */
	public boolean isOpenNewlyCreatedDiagramEditor() {
		return openNewlyCreatedDiagramEditor;
	}

	/**
	 * @generated
	 */
	public void setOpenNewlyCreatedDiagramEditor(boolean openNewlyCreatedDiagramEditor) {
		this.openNewlyCreatedDiagramEditor = openNewlyCreatedDiagramEditor;
	}

	/**
	 * @generated
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;
		setWindowTitle(Messages.EpcCreationWizardTitle);
		setDefaultPageImageDescriptor(EpcDiagramEditorPlugin
				.getBundledImageDescriptor("icons/wizban/NewEpcWizard.gif")); //$NON-NLS-1$
		setNeedsProgressMonitor(true);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.IDiagramCreationWizard#getShortHint()
	 */
	@Override
	public String getShortHint() {
		return Messages.EpcCreationWizardTitle;
	}

	/**
	 * @generated NOT
	 */
	public void addPages() {
		diagramModelFilePage = new EpcCreationWizardPage(
				"DiagramModelFile", getWorkbench(), getSelection(), "epc"); //$NON-NLS-1$ //$NON-NLS-2$
		diagramModelFilePage.setTitle(Messages.EpcCreationWizard_DiagramModelFilePageTitle);
		diagramModelFilePage.setDescription(Messages.EpcCreationWizard_DiagramModelFilePageDescription);
		addPage(diagramModelFilePage);
		
		pageSetupWizardPage = new DiagramPageSetupWizardPage();
		addPage(pageSetupWizardPage);
	}

	/**
	 * @generated
	 */
	public boolean performFinish() {
		IRunnableWithProgress op = new WorkspaceModifyOperation(null) {

			protected void execute(IProgressMonitor monitor)
					throws CoreException, InterruptedException {
				diagram = EpcDiagramEditorUtil.createDiagram(
						diagramModelFilePage.getURI(), monitor);
				
				if (isOpenNewlyCreatedDiagramEditor() && diagram != null) {
					try {
						EpcDiagramEditorUtil.openDiagram(diagram);
					} catch (PartInitException e) {
						ErrorDialog.openError(getContainer().getShell(),
								Messages.EpcCreationWizardOpenEditorError,
								null, e.getStatus());
					}
				}
			}
		};
		try {
			getContainer().run(false, true, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			if (e.getTargetException() instanceof CoreException) {
				ErrorDialog.openError(getContainer().getShell(),
						Messages.EpcCreationWizardCreationError, null,
						((CoreException) e.getTargetException()).getStatus());
			} else {
				EpcDiagramEditorPlugin.getInstance().logError(
						"Error creating diagram", e.getTargetException()); //$NON-NLS-1$
			}
			return false;
		}
		
		pageSetupWizardPage.applySetup(3, 5);		
		
		return diagram != null;
	}
}

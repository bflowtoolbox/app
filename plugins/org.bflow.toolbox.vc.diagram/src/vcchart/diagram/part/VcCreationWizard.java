package vcchart.diagram.part;

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

import vcchart.diagram.Messages;

/**
 * @generated
 * @version 2019-01-27 AST Added IDiagramCreationWizard implementation
 */
public class VcCreationWizard extends Wizard implements INewWizard, IDiagramCreationWizard {

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
	protected VcCreationWizardPage diagramModelFilePage;

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
	 * The <code>DiagramPageSetupWizardPage</code> to set the diagram size.
	 * @generated NOT
	 */
	private DiagramPageSetupWizardPage pageSetupWizardPage;

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
		setWindowTitle(Messages.VcCreationWizardTitle);
		setDefaultPageImageDescriptor(VcDiagramEditorPlugin
				.getBundledImageDescriptor("icons/wizban/NewVcchartWizard.gif")); //$NON-NLS-1$
		setNeedsProgressMonitor(true);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.IDiagramCreationWizard#getShortHint()
	 */
	@Override
	public String getShortHint() {
		return Messages.VcCreationWizardTitle;
	}

	/**
	 * @generated NOT
	 */
	public void addPages() {
		diagramModelFilePage = new VcCreationWizardPage(
				"DiagramModelFile", getWorkbench(), getSelection(), "vc"); //$NON-NLS-1$ //$NON-NLS-2$
		diagramModelFilePage
				.setTitle(Messages.VcCreationWizard_DiagramModelFilePageTitle);
		diagramModelFilePage
				.setDescription(Messages.VcCreationWizard_DiagramModelFilePageDescription);
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
				diagram = VcDiagramEditorUtil.createDiagram(
						diagramModelFilePage.getURI(), monitor);
				if (isOpenNewlyCreatedDiagramEditor() && diagram != null) {
					try {
						VcDiagramEditorUtil.openDiagram(diagram);
					} catch (PartInitException e) {
						ErrorDialog.openError(getContainer().getShell(),
								Messages.VcCreationWizardOpenEditorError, null,
								e.getStatus());
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
						Messages.VcCreationWizardCreationError, null,
						((CoreException) e.getTargetException()).getStatus());
			} else {
				VcDiagramEditorPlugin.getInstance().logError(
						"Error creating diagram", e.getTargetException()); //$NON-NLS-1$
			}
			return false;
		}
		
		pageSetupWizardPage.applySetup(3, 5);
		
		return diagram != null;
	}
}

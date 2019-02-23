package orgchart.diagram.part;

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
 * @version 2019-02-23 AST Added IDiagramCreationWizard implementation
 */
public class OrgcCreationWizard extends Wizard implements INewWizard, IDiagramCreationWizard {

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
	protected OrgcCreationWizardPage diagramModelFilePage;

	/**
	 * @generated
	 */
	protected Resource diagram;

	/**
	 * @generated
	 */
	private boolean openNewlyCreatedDiagramEditor = true;
	
	/**
	 * The <code>DiagramPageSetupWizardPage</code> to set the diagram size.
	 * @generated NOT
	 */
	private DiagramPageSetupWizardPage pageSetupWizardPage;

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
	public final Resource getDiagram() {
		return diagram;
	}

	/**
	 * @generated
	 */
	public final boolean isOpenNewlyCreatedDiagramEditor() {
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
		setWindowTitle(Messages.OrgcCreationWizardTitle);
		setDefaultPageImageDescriptor(OrgcDiagramEditorPlugin
				.getBundledImageDescriptor("icons/wizban/NewOrgchartWizard.gif")); //$NON-NLS-1$
		setNeedsProgressMonitor(true);
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.IDiagramCreationWizard#getShortHint()
	 */
	@Override
	public String getShortHint() {
		return Messages.OrgcCreationWizardTitle;
	}
	
	/**
	 * @generated NOT
	 */
	public void addPages() {
		diagramModelFilePage = new OrgcCreationWizardPage(
				"DiagramModelFile", getWorkbench() , getSelection(), "orgc"); //$NON-NLS-1$ //$NON-NLS-2$
		diagramModelFilePage.setTitle(Messages.OrgcCreationWizard_DiagramModelFilePageTitle);
		diagramModelFilePage.setDescription(Messages.OrgcCreationWizard_DiagramModelFilePageDescription);
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
				diagram = OrgcDiagramEditorUtil.createDiagram(
						diagramModelFilePage.getURI(), monitor);
				if (isOpenNewlyCreatedDiagramEditor() && diagram != null) {
					try {
						OrgcDiagramEditorUtil.openDiagram(diagram);
					} catch (PartInitException e) {
						ErrorDialog.openError(getContainer().getShell(),
								Messages.OrgcCreationWizardOpenEditorError,
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
						Messages.OrgcCreationWizardCreationError, null,
						((CoreException) e.getTargetException()).getStatus());
			} else {
				OrgcDiagramEditorPlugin.getInstance().logError(
						"Error creating diagram", e.getTargetException()); //$NON-NLS-1$
			}
			return false;
		}
		
		pageSetupWizardPage.applySetup(3, 5);
		
		return diagram != null;
	}
}

/*
 * 
 */
package vcchart.diagram.part;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

/**
 * @generated
 */
public class VcCreationWizardPage extends WizardNewFileCreationPage {

	/**
	 * @generated NOT
	 */
	private final String fileExtension;
	private IWorkbench iWorkbench;
	private IStructuredSelection iSelection;

	/**
	 * @generated
	 */
	public VcCreationWizardPage(String pageName,
			IWorkbench workbench, IStructuredSelection selection, String fileExtension) {
		super(pageName, selection);
		this.fileExtension = fileExtension;
		iWorkbench = workbench;
		iSelection = selection;
	}

	/**
	 * Override to create files with this extension.
	 * 
	 * @generated
	 */
	protected String getExtension() {
		return fileExtension;
	}

	/**
	 * @generated
	 */
	public URI getURI() {
		return URI.createPlatformResourceURI(getFilePath().toString(), false);
	}

	/**
	 * @generated
	 */
	protected IPath getFilePath() {
		IPath path = getContainerFullPath();
		if (path == null) {
			path = new Path(""); //$NON-NLS-1$
		}
		String fileName = getFileName();
		if (fileName != null) {
			path = path.append(fileName);
		}
		return path;
	}

	/**
	 * @generated NOT
	 */
	public void createControl(Composite parent) {
		// Preselect a project
		IPath defaultProject = getDefaultProject();
		setContainerFullPath(defaultProject);
		
		super.createControl(parent);
		setFileName(VcDiagramEditorUtil.getUniqueFileName(
				getContainerFullPath(), getFileName(), getExtension()));
		setPageComplete(validatePage());
	}

	/**
	 * @generated
	 */
	protected boolean validatePage() {
		if (!super.validatePage()) {
			return false;
		}
		String extension = getExtension();
		if (extension != null
				&& !getFilePath().toString().endsWith("." + extension)) {
			setErrorMessage(NLS.bind(
					Messages.VcCreationWizardPageExtensionError, extension));
			return false;
		}
		return true;
	}
	
	@Override
	public String getFileName() {
		String fileName = super.getFileName();
		
		if(fileName == null || fileName.isEmpty())
			fileName = "untitled";
				
		if(!fileName.endsWith(".vc"))
			fileName += ".vc";
		
		return fileName;
	}
	
	/**
	 * Returns the full path of a project that can be used as default selection. 
	 * This method selects an existing project if there is only one. If there isn't 
	 * any a project creation wizard will be shown first. If there are multiple projects 
	 * nothing will happen and null will be returned.
	 * 
	 * @return The full path of a project or null
	 */
	private IPath getDefaultProject() {
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IProject[] projects = workspaceRoot.getProjects();
		IPath defaultProject = null;
		
		// Open create project dialog
		if(projects.length == 0) {
			IWorkbenchWizard wizard = new BasicNewProjectResourceWizard();
			wizard.init(iWorkbench, iSelection);
			WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(), wizard);
			dialog.open();
			projects = workspaceRoot.getProjects();
		}
		
		// Select the one per default
		if(projects.length == 1) {
			defaultProject = projects[0].getFullPath();
		}

		return defaultProject;
	}
}

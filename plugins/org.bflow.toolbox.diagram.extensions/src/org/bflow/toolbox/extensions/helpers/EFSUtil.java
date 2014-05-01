package org.bflow.toolbox.extensions.helpers;

import java.io.FileInputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.ui.ide.FileStoreEditorInput;

/**
 * Provides some methods to handle restrictions that are made by files 
 * referenced outside of the workspace.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 20/07/13
 */
public class EFSUtil {
	
	/** The Constant FOLDER_NAME_EXTERNAL. */
	private static final String FOLDER_NAME_EXTERNAL = ".external";
	
	/**
	 * Creates an instance of {@link IFile} which is linked to the resource
	 * referenced by the given path. All created resource links were stored
	 * within a non-visible project called '.external'.
	 * 
	 * @param path
	 *            The path to the external resource
	 * @return Newly created instance of {@link IFile} linked to the external
	 *         resource
	 * @throws CoreException
	 *             the core exception
	 */
	public static IFile createAndLink(IPath path) throws CoreException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IProject project = workspace.getRoot().getProject(FOLDER_NAME_EXTERNAL);
		if (!project.exists())
		    project.create(null);
		if (!project.isOpen())
		    project.open(null);
		IFile file = project.getFile(path.lastSegment());
		file.createLink(path, IResource.NONE, null);
		return file;
	}
	
	/**
	 * Shows a 'Select Project' dialog for the given file which is located
	 * outside of the user workspace. After the user has been one chosen the
	 * external file will copied to the selected project. This method returns
	 * the created instance of {@link IFile} for the newly created resource or
	 * null if the user has canceled the selection dialog.
	 * 
	 * @param fileStoreEditorInput
	 *            File pointer for the external resource
	 * @return Instance of {@link IFile} for the created resource or null
	 * @throws CoreException
	 */
	public static IFile importExternalFile(FileStoreEditorInput fileStoreEditorInput) throws CoreException {
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		String filePath;
		try {
			filePath = fileStoreEditorInput.getURI().toURL().getFile();
			IPath path = Path.fromOSString(filePath);
			
			ContainerSelectionDialog containerSelectionDialog = 
					new ContainerSelectionDialog(Display.getCurrent().getActiveShell(), workspaceRoot, true, 
							"Select a project where the selected file will be added to.");
			
			if(containerSelectionDialog.open() == ContainerSelectionDialog.CANCEL)
				return null;
			
			Object selections[] = containerSelectionDialog.getResult();
			
			if(selections.length == 0)
				return null;
			
			IPath selPath = (IPath) selections[0];
			IPath selectedProject = workspaceRoot.getLocation().append(selPath);
			IProject project = workspaceRoot.getProject(selectedProject.lastSegment());
			
			if (!project.exists())
			    project.create(null);
			if (!project.isOpen())
			    project.open(null);
			
			IFile file = project.getFile(path.lastSegment());
			if(!file.exists())
				file.create(new FileInputStream(filePath), IFile.NONE, null);
			
			return file;
		} catch (Exception e) {
			throw new CoreException(new Status(IStatus.ERROR, "org.bflow.toolbox.diamgram.externsions", 
					"Error on importing external file", e));
		}
	}

}

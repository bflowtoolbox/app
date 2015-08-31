package org.bflow.toolbox.hive.templating.commands;

import org.bflow.toolbox.hive.templating.dialogs.TemplateFileService;
import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PlatformUI;

public class TemplateExistsPropertyTester extends PropertyTester {

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		
		IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		IEditorInput input = editor.getEditorInput();
		IFile currentLocation = ((IFileEditorInput) input).getFile();
		IPath workspaceRoot = ResourcesPlugin.getWorkspace().getRoot().getLocation();

		IPath globalPath = workspaceRoot.append(TemplateFileService.TEMPLATE_PATH_NAME);
		boolean global = containsTemplateFiles(globalPath);
		
		IPath localPath = TemplateFileService.getLocalTemplatePath(currentLocation, workspaceRoot);
		boolean local = containsTemplateFiles(localPath);
		
		return local || global;
	}

	private boolean containsTemplateFiles(IPath path) {
		if (path != null && path.toFile().isDirectory()) {
			for (String filename : path.toFile().list()) {
				if (filename.endsWith(".epc")) {
					return true;
				}
			}
		}
		return false;
	}

}

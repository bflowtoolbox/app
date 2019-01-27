package org.bflow.toolbox.extensions.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.FileEditorInput;

/**
 * Diagram link action base class to open a diagram link from a model element.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-01-27
 *
 */
public abstract class AbstractOpenDiagramLinkAction extends AbstractDiagramLinkAction<String, Void> {
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#getModificationValue(java.lang.Object)
	 */
	@Override
	protected Void getModificationValue(String selectionData) {
		// We don't need additional values
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#performModification(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected void performModification(String selectionData, Void modificationValue) throws Exception {
		URI fileURI = URI.createPlatformResourceURI(selectionData, true);
		
		try {
			IPath path = new Path(fileURI.toPlatformString(true));
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
			IEditorDescriptor desc = Workbench().getEditorRegistry().getDefaultEditor(file.getName());
			Workbench().getActiveWorkbenchWindow()
					.getActivePage()
					.openEditor(new FileEditorInput(file), desc.getId());			
		} catch (PartInitException ex) {
			Log().error("Error on opening diagram", ex);
		}		
	}
}

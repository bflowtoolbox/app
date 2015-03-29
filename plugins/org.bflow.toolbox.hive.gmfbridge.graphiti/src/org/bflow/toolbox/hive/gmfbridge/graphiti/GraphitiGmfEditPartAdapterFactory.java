package org.bflow.toolbox.hive.gmfbridge.graphiti;

import org.bflow.toolbox.hive.gmfbridge.IGmfEditPartAdapterFactory;
import org.bflow.toolbox.hive.gmfbridge.graphiti.adapters.DiagramEditorAdapter;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.ui.IEditorPart;

/**
 * Provides an implementation of {@link IGmfEditPartAdapterFactory} to adapt
 * graphiti edit parts.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 27.03.2015
 * 
 */
public class GraphitiGmfEditPartAdapterFactory implements IGmfEditPartAdapterFactory {

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.gmfbridge.IGmfEditPartAdapterFactory#canAdapt(org.eclipse.ui.IEditorPart)
	 */
	@Override
	public boolean canAdapt(IEditorPart editorPart) {
		boolean canAdapt = (org.eclipse.graphiti.ui.editor.DiagramEditor.class.isAssignableFrom(editorPart.getClass()));
		return canAdapt;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.gmfbridge.IGmfEditPartAdapterFactory#getAdapter(org.eclipse.ui.IEditorPart)
	 */
	@Override
	public DiagramEditor getAdapter(IEditorPart editorPart) {
		return new DiagramEditorAdapter((org.eclipse.graphiti.ui.editor.DiagramEditor) editorPart);
	}
}

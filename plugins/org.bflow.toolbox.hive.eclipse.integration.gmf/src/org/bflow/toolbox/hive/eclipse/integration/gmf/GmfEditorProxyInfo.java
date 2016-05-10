package org.bflow.toolbox.hive.eclipse.integration.gmf;

import org.bflow.toolbox.hive.eclipse.integration.IEditorProxyInfo;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.ui.IEditorPart;

/**
 * Implements {@link IEditorProxyInfo} to provide the editor proxy for an
 * graphiti build editor.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 26.07.2015
 */
public class GmfEditorProxyInfo implements IEditorProxyInfo {

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.eclipse.integration.IEditorProxyInfo#canProvideProxy(java.lang.String, org.eclipse.ui.IEditorPart)
	 */
	@Override
	public boolean canProvideProxy(String fileName, IEditorPart editorPart) {
		DiagramDocumentEditor diagramDocumentEditor = (DiagramDocumentEditor) editorPart.getAdapter(DiagramDocumentEditor.class);
		if (diagramDocumentEditor == null) return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.eclipse.integration.IEditorProxyInfo#EditorProxyId()
	 */
	@Override
	public String EditorProxyId() {
		return GmfDiagramEditorProxy.EditorId;
	}

}

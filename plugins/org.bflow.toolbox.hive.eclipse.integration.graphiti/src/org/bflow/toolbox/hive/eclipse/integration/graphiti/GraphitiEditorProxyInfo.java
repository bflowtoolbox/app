package org.bflow.toolbox.hive.eclipse.integration.graphiti;

import org.bflow.toolbox.hive.eclipse.integration.IEditorProxyInfo;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.MultiPageEditorPart;

/**
 * Implements {@link IEditorProxyInfo} to provide the editor proxy for an
 * graphiti build editor.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 26.07.2015
 */
public class GraphitiEditorProxyInfo implements IEditorProxyInfo {

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.eclipse.integration.IEditorProxyInfo#canProvideProxy(java.lang.String, org.eclipse.ui.IEditorPart)
	 */
	@Override
	public boolean canProvideProxy(String fileName, IEditorPart editorInstance) {
		MultiPageEditorPart multiPageEditorPart = (MultiPageEditorPart) editorInstance.getAdapter(MultiPageEditorPart.class);
		if (multiPageEditorPart == null) return false;
		
		// TODO Using realiable hint
		String className = multiPageEditorPart.getClass().getName();
		if (className.equalsIgnoreCase("org.eclipse.bpmn2.modeler.ui.editor.BPMN2MultiPageEditor")) return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.eclipse.integration.IEditorProxyInfo#EditorProxyId()
	 */
	@Override
	public String EditorProxyId() {
		return GraphitiDiagramEditorProxy.EditorId;
	}

}

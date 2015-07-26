package org.bflow.toolbox.hive.eclipse.integration;

/**
 * Provides methods to decide which editor proxy is applicable to a specific diagram editor.
 * @author Arian Storch<arian.storch@bflow.org>
 *
 */
public class DiagramEditorProxyRelay {

	/**
	 * Returns the id of the editor proxy to use for origin editor.
	 * 
	 * @param originEditorId
	 *            Editor id of the origin editor
	 * @param fileName
	 *            File name of a file that is typically handled by the origin
	 *            editor
	 * @return Id of the editor proxy
	 */
	public static String getEditorProxyId(String originEditorId, String fileName) {
		if (originEditorId.equalsIgnoreCase("org.eclipse.bpmn2.modeler.ui.bpmn2editor")) 
			return "org.bflow.toolbox.hive.eclipse.integration.internal.editor.diagramEditorProxy.Graphiti"; // GraphitiDiagramEditorProxy.EditorId; // TODO		
		
		return "org.bflow.toolbox.hive.eclipse.integration.internal.editor.diagramEditorProxy.GMF"; // GmfDiagramEditorProxy.EditorId; // TODO
	}
	
}

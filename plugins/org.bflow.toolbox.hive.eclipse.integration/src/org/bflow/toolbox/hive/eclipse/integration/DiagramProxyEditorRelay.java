package org.bflow.toolbox.hive.eclipse.integration;

import org.bflow.toolbox.hive.eclipse.integration.internal.editor.gmf.GmfDiagramEditorProxy;
import org.bflow.toolbox.hive.eclipse.integration.internal.editor.graphiti.GraphitiDiagramEditorProxy;

public class DiagramProxyEditorRelay {

	public static String getEditorId(String originEditorId, String fileName) {
		if (originEditorId.equalsIgnoreCase("org.eclipse.bpmn2.modeler.ui.bpmn2editor")) return GraphitiDiagramEditorProxy.EditorId; // TODO		
		return GmfDiagramEditorProxy.EditorId; // TODO
	}
	
}

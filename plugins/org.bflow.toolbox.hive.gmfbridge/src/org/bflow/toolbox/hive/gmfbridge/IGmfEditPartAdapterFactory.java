package org.bflow.toolbox.hive.gmfbridge;

import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.ui.IEditorPart;

public interface IGmfEditPartAdapterFactory {
	
	boolean canAdapt(IEditorPart editorPart);
	
	DiagramEditor getAdapter(IEditorPart editorPart);

}

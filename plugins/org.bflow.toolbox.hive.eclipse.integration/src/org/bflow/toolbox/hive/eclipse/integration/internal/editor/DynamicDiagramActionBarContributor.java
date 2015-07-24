package org.bflow.toolbox.hive.eclipse.integration.internal.editor;

import org.eclipse.gmf.runtime.common.ui.action.global.GlobalActionId;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramActionBarContributor;
import org.eclipse.ui.IActionBars;

public class DynamicDiagramActionBarContributor extends DiagramActionBarContributor {
	
	// TODO Graphiti - DiagramEditorActionBarContributor
	
	private static DynamicDiagramActionBarContributor fInstance;
	
	/**
	 * Returns the currently used instance.
	 * 
	 * @return Currently used instance
	 */
	public static DynamicDiagramActionBarContributor Instance() {
		return fInstance;
	}
	
	private String fCurrentEditorId;
	private Class<?> fCurrentEditorClass;
	
	/**
	 * Creates a new instance.
	 */
	public DynamicDiagramActionBarContributor() {
		fInstance = this;
	}
	
	public void updateCurrentEditor(String editorId, Class<?> editorClass) {
		fCurrentEditorId = editorId;
		fCurrentEditorClass = editorClass;
		
		try {
			dispose();
			init(getActionBars(), getPage());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.parts.DiagramActionBarContributor#init(org.eclipse.ui.IActionBars)
	 */
	@Override
	public void init(IActionBars bars) {
		super.init(bars);
		
		// ISSUE: Patch due to https://bugs.eclipse.org/bugs/show_bug.cgi?id=346648
		bars.setGlobalActionHandler(GlobalActionId.SAVE, null);
	}

	@Override
	protected String getEditorId() {
		return fCurrentEditorId;
//		return "epc.diagram.part.BflowEpcDiagramEditorID"; // TODO
//		return DiagramEditorProxy.EditorId;
	}

	@Override
	protected Class<?> getEditorClass() {
		return fCurrentEditorClass;
//		try {
//			return Class.forName("org.bflow.toolbox.epc.diagram.part.EpcDiagramEditor");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//		return DiagramEditorProxy.class;
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}

}

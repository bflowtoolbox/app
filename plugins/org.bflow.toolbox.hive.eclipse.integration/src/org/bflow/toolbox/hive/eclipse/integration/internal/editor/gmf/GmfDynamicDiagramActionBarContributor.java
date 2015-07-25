package org.bflow.toolbox.hive.eclipse.integration.internal.editor.gmf;

import org.bflow.toolbox.hive.eclipse.integration.internal.editor.DynamicDiagramActionBarContributor;
import org.bflow.toolbox.hive.eclipse.integration.internal.editor.IDynamicActionBarContributor;
import org.eclipse.gmf.runtime.common.ui.action.global.GlobalActionId;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramActionBarContributor;
import org.eclipse.ui.IActionBars;

/**
 * Extends {@link DiagramActionBarContributor} to provide the standard action
 * set that is applied to the origin diagram editor.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 24.07.2015
 *
 */
public class GmfDynamicDiagramActionBarContributor extends DiagramActionBarContributor implements IDynamicActionBarContributor {
	
	private String fCurrentEditorId;
	private Class<?> fCurrentEditorClass;
	
	/**
	 * Default constructor.
	 */
	public GmfDynamicDiagramActionBarContributor() {
		DynamicDiagramActionBarContributor.Instance(this);
	}
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.eclipse.integration.internal.editor.IDynamicActionBarContributor#updateCurrentEditor(java.lang.String, java.lang.Class)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.parts.DiagramActionBarContributor#getEditorId()
	 */
	@Override
	protected String getEditorId() {
		return fCurrentEditorId;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.parts.DiagramActionBarContributor#getEditorClass()
	 */
	@Override
	protected Class<?> getEditorClass() {
		return fCurrentEditorClass;
	}
}

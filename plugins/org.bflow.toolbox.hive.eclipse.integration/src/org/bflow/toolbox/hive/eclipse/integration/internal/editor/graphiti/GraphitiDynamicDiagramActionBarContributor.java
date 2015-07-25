package org.bflow.toolbox.hive.eclipse.integration.internal.editor.graphiti;

import org.bflow.toolbox.hive.eclipse.integration.internal.editor.DynamicDiagramActionBarContributor;
import org.bflow.toolbox.hive.eclipse.integration.internal.editor.IDynamicActionBarContributor;
import org.eclipse.graphiti.ui.editor.DiagramEditorActionBarContributor;

/**
 * Extends {@link DiagramEditorActionBarContributor} to provide the standard action
 * set that is applied to the origin diagram editor.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 24.07.2015
 *
 */
public class GraphitiDynamicDiagramActionBarContributor extends DiagramEditorActionBarContributor implements IDynamicActionBarContributor {
	
	/**
	 * Default constructor.
	 */
	public GraphitiDynamicDiagramActionBarContributor() {
		DynamicDiagramActionBarContributor.Instance(this);
	}
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.eclipse.integration.internal.editor.IDynamicActionBarContributor#updateCurrentEditor(java.lang.String, java.lang.Class)
	 */
	@Override
	public void updateCurrentEditor(String editorId, Class<?> editorClass) {
//		try {
//			dispose();
//			init(getActionBars(), getPage());
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
	}
}

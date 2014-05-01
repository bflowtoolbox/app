package org.bflow.toolbox.extensions.edit.parts;

import org.eclipse.gmf.runtime.gef.ui.internal.parts.WrapTextCellEditor;

@SuppressWarnings({ "restriction" })
public class BflowTextCellEditorFactory {

	/**
	 * Returns the text cell editor class for using to edit labels.
	 * @see WrapTextCellEditor
	 * @return
	 */
	public static Class getTextCellEditorClass() {
		return WrapTextCellEditor.class;
	}
}

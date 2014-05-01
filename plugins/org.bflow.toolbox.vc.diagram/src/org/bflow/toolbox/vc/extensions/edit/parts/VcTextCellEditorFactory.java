package org.bflow.toolbox.vc.extensions.edit.parts;

import org.eclipse.gmf.runtime.gef.ui.internal.parts.WrapTextCellEditor;

@SuppressWarnings({ "unchecked", "restriction" })
public class VcTextCellEditorFactory {

	
	public static Class getTextCellEditorClass() {
		return WrapTextCellEditor.class;
	}
}

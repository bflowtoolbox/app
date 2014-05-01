package org.bflow.toolbox.epc.extensions.edit.parts;

import org.eclipse.gmf.runtime.gef.ui.internal.parts.WrapTextCellEditor;

@SuppressWarnings({ "unchecked", "restriction" })
public class EpcTextCellEditorFactory {

	
	public static Class getTextCellEditorClass() {
		return WrapTextCellEditor.class;
	}
}

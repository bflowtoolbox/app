package org.bflow.toolbox.hive.attributes.utils;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.MultiPageEditorPart;

/**
 * Provides useful methods when operating with workbench parts.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 01.04.2015
 * 
 */
public class WorkbenchUtil {
	
	/**
	 * Returns the active editor part of the given workbench part. If there is
	 * none, NULL is returned.
	 * 
	 * @param part
	 *            Workbench part to process
	 * @return Active editor part or NULL
	 */
	static public IEditorPart getActiveEditorPart(IWorkbenchPart part) {
		if (part instanceof IEditorPart) return getActiveEditorPart((IEditorPart)part);
		return null;
	}

	/**
	 * Returns the active single editor part within the given editor part. If
	 * the given one is a multi page editor the active one will be returned.
	 * Otherwise the given part itself will be returned.
	 * 
	 * @param part
	 *            Editor part to process
	 * @return Editor part
	 */
	static public IEditorPart getActiveEditorPart(IEditorPart part) {
		if (part instanceof MultiPageEditorPart) {
			MultiPageEditorPart multiPageEditorPart = (MultiPageEditorPart)part;
			part = (IEditorPart) multiPageEditorPart.getSelectedPage();
		}
		return part;
	}
}

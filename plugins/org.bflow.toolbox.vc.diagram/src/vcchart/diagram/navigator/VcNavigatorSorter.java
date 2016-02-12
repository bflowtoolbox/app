/*
 * 
 */
package vcchart.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;

import vcchart.diagram.part.VcVisualIDRegistry;

/**
 * @generated
 */
public class VcNavigatorSorter extends ViewerSorter {

	/**
	 * @generated
	 */
	private static final int GROUP_CATEGORY = 4005;

	/**
	 * @generated
	 */
	private static final int SHORTCUTS_CATEGORY = 4004;

	/**
	 * @generated
	 */
	public int category(Object element) {
		if (element instanceof VcNavigatorItem) {
			VcNavigatorItem item = (VcNavigatorItem) element;
			if (item.getView().getEAnnotation("Shortcut") != null) { //$NON-NLS-1$
				return SHORTCUTS_CATEGORY;
			}
			return VcVisualIDRegistry.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}

}

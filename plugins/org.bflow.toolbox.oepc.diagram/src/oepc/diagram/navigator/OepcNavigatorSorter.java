package oepc.diagram.navigator;

import oepc.diagram.part.OepcVisualIDRegistry;

import org.eclipse.jface.viewers.ViewerSorter;

/**
 * @generated
 */
public class OepcNavigatorSorter extends ViewerSorter {

	/**
	 * @generated
	 */
	private static final int GROUP_CATEGORY = 5004;

	/**
	 * @generated
	 */
	public int category(Object element) {
		if (element instanceof OepcNavigatorItem) {
			OepcNavigatorItem item = (OepcNavigatorItem) element;
			return OepcVisualIDRegistry.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}

}

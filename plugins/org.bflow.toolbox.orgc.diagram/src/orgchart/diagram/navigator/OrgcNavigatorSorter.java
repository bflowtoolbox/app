/*
 * 
 */
package orgchart.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;
import orgchart.diagram.part.OrgcVisualIDRegistry;

/**
 * @generated
 */
public class OrgcNavigatorSorter extends ViewerSorter {

	/**
	 * @generated
	 */
	private static final int GROUP_CATEGORY = 4004;

	/**
	 * @generated
	 */
	private static final int SHORTCUTS_CATEGORY = 4003;

	/**
	 * @generated
	 */
	public int category(Object element) {
		if (element instanceof OrgcNavigatorItem) {
			OrgcNavigatorItem item = (OrgcNavigatorItem) element;
			if (item.getView().getEAnnotation("Shortcut") != null) { //$NON-NLS-1$
				return SHORTCUTS_CATEGORY;
			}
			return OrgcVisualIDRegistry.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}

}

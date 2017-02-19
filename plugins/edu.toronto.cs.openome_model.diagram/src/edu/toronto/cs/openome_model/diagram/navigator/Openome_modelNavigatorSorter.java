package edu.toronto.cs.openome_model.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;

/**
 * @generated
 */
public class Openome_modelNavigatorSorter extends ViewerSorter {

	/**
	 * @generated
	 */
	private static final int GROUP_CATEGORY = 7006;

	/**
	 * @generated
	 */
	public int category(Object element) {
		if (element instanceof edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorItem) {
			edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorItem item = (edu.toronto.cs.openome_model.diagram.navigator.Openome_modelNavigatorItem) element;
			return edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
					.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}

}

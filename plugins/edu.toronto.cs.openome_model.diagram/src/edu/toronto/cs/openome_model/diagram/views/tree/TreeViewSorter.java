package edu.toronto.cs.openome_model.diagram.views.tree;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

public class TreeViewSorter extends ViewerSorter {
	
	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		if (e1 instanceof ActorNode && !(e2 instanceof ActorNode)) {
			return -1;
		} else if (!(e1 instanceof ActorNode) && e2 instanceof ActorNode) {
			return 1;
		} else if (e1 instanceof DependumNode) {
			return 1;
		} else if (e2 instanceof DependumNode) {
			return -1;
		} else {
			return super.compare(viewer, e1, e2);
		}
	}

}

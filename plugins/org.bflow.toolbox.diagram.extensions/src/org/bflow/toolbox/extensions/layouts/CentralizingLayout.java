package org.bflow.toolbox.extensions.layouts;

import java.util.List;

import org.eclipse.draw2d.AbstractHintLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Implements a layout that applies the client area of the container to all
 * children, so that all children are typically located in the center of the
 * container.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-02-17
 *
 */
public class CentralizingLayout extends AbstractHintLayout {
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.draw2d.LayoutManager#layout(org.eclipse.draw2d.IFigure)
	 */
	@Override
	public void layout(IFigure container) {
		Rectangle clientArea = container.getClientArea();
		List<?> children = container.getChildren();
		for (int i = -1; ++i != children.size();) {
			IFigure figure = (IFigure) children.get(i);				
			Rectangle bounds = clientArea.getCopy();
			figure.setBounds(bounds);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.draw2d.AbstractLayout#calculatePreferredSize(org.eclipse.draw2d.IFigure, int, int)
	 */
	@Override
	protected Dimension calculatePreferredSize(IFigure container, int wHint, int hHint) {	
		List<?> children = container.getChildren();
		Dimension dim = new Dimension();
		for (int i = -1; ++i != children.size();) {
			IFigure child = (IFigure) children.get(i);
			Dimension childDim = child.getPreferredSize(wHint, hHint);
			dim.union(childDim);
		}
		
		return dim;
	}		
}

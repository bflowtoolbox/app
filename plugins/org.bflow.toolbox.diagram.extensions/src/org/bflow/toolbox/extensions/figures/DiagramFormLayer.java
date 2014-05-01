package org.bflow.toolbox.extensions.figures;

import java.util.Iterator;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemsAwareFreeFormLayer;
import org.eclipse.gmf.runtime.diagram.ui.internal.figures.IExpandableFigure;


/**
 * An <code>DiagramFormLayer</code> is an figure represents the diagram
 * which calculates the required size by each modification of the notational
 * diagram.
 * 
 * @author Joerg Hartmann
 * @since 0.0.7
 *
 */
@SuppressWarnings({ "restriction" })
public class DiagramFormLayer extends BorderItemsAwareFreeFormLayer {

	
	/**
	 * The <code>DiagramFormLayerHelper</code> calculates the size,
	 */
	private DiagramFormLayerHelper diagramFormHelper = 
		new DiagramFormLayerHelper(this);


	private Rectangle extendedBounds = null;

	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemsAwareFreeFormLayer#getBounds()
	 */
	public Rectangle getBounds() {
		if (extendedBounds == null) {
			Iterator<?> figuresIter = getChildren().iterator();
			Rectangle bounds = super.getBounds().getCopy();
			while (figuresIter.hasNext()) {
				Figure element = (Figure) figuresIter.next();
				Rectangle rect = null;
				if (element instanceof IExpandableFigure) {
					rect = ((IExpandableFigure) element).getExtendedBounds();
				} else {
					rect = element.getBounds();
				}
				bounds.union(rect);
			}
			extendedBounds = bounds;
		}
		return extendedBounds;
	}

	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemsAwareFreeFormLayer#invalidate()
	 */
	public void invalidate() {
		extendedBounds = null;
		super.invalidate();
	}

	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemsAwareFreeFormLayer#validate()
	 */
	public void validate() {
		extendedBounds = null;
		super.validate();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemsAwareFreeFormLayer#add(org.eclipse.draw2d.IFigure, java.lang.Object, int)
	 */
	public void add(IFigure child, Object constraint, int index) {
		super.add(child, constraint, index);
		diagramFormHelper.hookChild(child);
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemsAwareFreeFormLayer#getFreeformExtent()
	 */
	public Rectangle getFreeformExtent() {
		return diagramFormHelper.getFreeformExtent();
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemsAwareFreeFormLayer#remove(org.eclipse.draw2d.IFigure)
	 */
	public void remove(IFigure child) {
		diagramFormHelper.unhookChild(child);
		super.remove(child);
	}

	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemsAwareFreeFormLayer#setFreeformBounds(org.eclipse.draw2d.geometry.Rectangle)
	 */
	public void setFreeformBounds(Rectangle bounds) {
		diagramFormHelper.setFreeformBounds(bounds);
	}


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemsAwareFreeFormLayer#borderFigureMoved()
	 */
	public void borderFigureMoved() {
		diagramFormHelper.invalidate();
	}

	
	/**
	 * Returns the <code>DiagramFormLayerHelper</code>.
	 * @return
	 */
	public DiagramFormLayerHelper getFormHelper() {
		return diagramFormHelper;
	}
}

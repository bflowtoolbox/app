package openome_model.figures;

import java.util.List;

import org.eclipse.draw2d.AbstractHintLayout;
import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;

public class OpenOmeElementLayoutManager extends AbstractHintLayout
{
	
	// *********************************
	// Please note, the 2 methods - calculateMinimumSize and calculatePreferredSize
	// were taken directly from draw2d's StackLayout. Only the 'layout'
	// method was modified (from StackLayout).
	
	// the two methods were taken rather than inherited as they were final methods
	// and thus did not allow inheritance
	
	/**
	 * Returns the minimum size required by the input container. This is the size of the 
	 * largest child of the container, as all other children fit into this size.
	 * 
	 * @see AbstractHintLayout#calculateMinimumSize(IFigure, int, int)
	 */
	protected Dimension calculateMinimumSize(IFigure figure, int wHint, int hHint) {
		if (wHint > -1)
			wHint = Math.max(0, wHint - figure.getInsets().getWidth());
		if (hHint > -1)
			hHint = Math.max(0, hHint - figure.getInsets().getHeight());
		Dimension d = new Dimension();
		List children = figure.getChildren();
		IFigure child;
		for (int i = 0; i < children.size(); i++) {
			child = (IFigure)children.get(i);
			if (!isObservingVisibility() || child.isVisible())
				d.union(child.getMinimumSize(wHint, hHint));
		}
		
		d.expand(figure.getInsets().getWidth(),
		         figure.getInsets().getHeight());
		d.union(getBorderPreferredSize(figure));
		layout(figure);
		return d;

	}

	/**
	 * Calculates and returns the preferred size of the given figure.  This is the union of 
	 * the preferred sizes of the widest and the tallest of all its children. 
	 * 
	 * @see AbstractLayout#calculatePreferredSize(IFigure, int, int)
	 */
	protected Dimension calculatePreferredSize(IFigure figure, int wHint, int hHint) {
		if (wHint > -1)
			wHint = Math.max(0, wHint - figure.getInsets().getWidth());
		if (hHint > -1)
			hHint = Math.max(0, hHint - figure.getInsets().getHeight());
		Dimension d = new Dimension();
		List children = figure.getChildren();
		IFigure child;
		for (int i = 0; i < children.size(); i++) {
			child = (IFigure)children.get(i);
			if (!isObservingVisibility() || child.isVisible())
				d.union(child.getPreferredSize(wHint, hHint));
		}
		
		d.expand(figure.getInsets().getWidth(),
		         figure.getInsets().getHeight());
		d.union(getBorderPreferredSize(figure));
		return d;
	}

	/**
	 * @see org.eclipse.draw2d.LayoutManager#layout(IFigure)
	 */
	public void layout(IFigure figure) {		
		
		Rectangle r = figure.getClientArea();
		List<IFigure> children = figure.getChildren();
		IFigure child;
		for (int i = 0; i < children.size(); i++) {
			child = (IFigure)children.get(i);

			// ensure that the wrapped text is center aligned when			
			// they are pushed onto subsequent rows (see ticket #120)
			// For some strange reason, this crashes under Mac OSX Leopard (10.5), we should
			// only call this method only if we're not on Leopard
			if (!(System.getProperty("os.name").equals("Mac OS X") && System.getProperty("os.version").startsWith("10.5"))) { 
				((WrappingLabel) child).setTextWrap(true);
			}
			((WrappingLabel)child).setTextJustification(PositionConstants.CENTER);
			((WrappingLabel)child).setAlignment(PositionConstants.CENTER);
			
			// shrink the horizontal text bound by 20 units, so that
			// the text doesn't get a chance to 'bleed' onto the edge..
			// Instead, it gets word wrapped to a new row if it gets too
			// close to the edge
			child.setBounds(r.getCopy().shrink(15, 0));
			
		}
	}
	
	
	/**
	 * Count the number of rows in a string, by counting the number 
	 * of new line characters there are in the string.
	 * @param inputString the string the count the number of rows/new lines.
	 * @return the number of rows in the string.
	 */
	private int numRowsInTextWrappedString(String inputString) {
		String[] splitRows = inputString.split("\n");
		return splitRows.length;
	}
	
}

package openome_model.figures;

import java.util.List;

import org.eclipse.draw2d.AbstractHintLayout;
import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;

public class OpenOmeActorNameLayoutManager extends AbstractHintLayout
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
	
	public void layout(IFigure figure) {
		
		Rectangle rec = figure.getClientArea();
		
		// when the actor is collapsed
		if ((rec.width <= ContainerSVGFigure.ACTOR_COLLAPSED_WIDTH_AND_HEIGHT_THRESHOLD) 
		 || (rec.height <= ContainerSVGFigure.ACTOR_COLLAPSED_WIDTH_AND_HEIGHT_THRESHOLD)) {
			
			OpenOmeElementLayoutManager a = new OpenOmeElementLayoutManager();
			a.layout(figure);
		} else {
		
			List children = figure.getChildren();
			IFigure child;
			for (int i = 0; i < children.size(); i++) {
				child = (IFigure)children.get(i);
				
				// radius of the actor symbol
				int r = ContainerSVGFigure.SIZE_OF_ACTOR_SYMBOL;
				
				// the height (and the width, assuming that the actor figure stays a perfect circle)
				float figureHeight = (float)(rec.preciseHeight());
				float figureWidth = (float)(rec.preciseWidth());
				
				// determine the offset, used for placing the actor name at a certain spot
				// within the actor figure
				float yOffsetConstant = .700f + (((figureHeight - 250.0f)/100.0f) * 0.025f);
				float xOffsetConstant = .700f + (((figureWidth - 250.0f)/100.0f) * 0.025f);
				
				//default
				//float offsetConstant = 0.707f;
				
				// determine the exact location of where the symbol should be placed
				int xoffset = (int) (rec.width * (1 - xOffsetConstant) / 2);
				int yoffset = (int) (rec.height * (1 - yOffsetConstant) / 2);
				
				// finally, determine the exact position of where the actor symbol should be placed
				int x = rec.x + xoffset - r / 2;
				int y = rec.y + yoffset - r / 2;
				
				// if the entire actor figure (symbol + bubble) is small enough, then the actor symbol is simply 
				// placed at the top left corner (done by ContainerSVGFigure), so we will place
				// the actor name snuggly into this area
				int actorSymbolThreshold = ContainerSVGFigure.SIZE_OF_ACTOR_FOR_FIXED_SYMBOL;
				
				if ((figureHeight <= actorSymbolThreshold) || (figureWidth <= actorSymbolThreshold)) {
					x = rec.getTopLeft().x + 8;
					y = rec.getTopLeft().y + 8;
				}
				
				///////////////////////////////
				// make the text wrap.. for some strange reason, this crashes under Mac OSX Leopard (10.5), we should
				// only call this method only if we're not on Leopard
				if (!(System.getProperty("os.name").equals("Mac OS X") && System.getProperty("os.version").startsWith("10.5"))) { 
					((WrappingLabel) child).setTextWrap(true);
				}
				
				// vertically and horizontally centre align the text
				((WrappingLabel)child).setTextJustification(PositionConstants.CENTER);
				((WrappingLabel)child).setAlignment(PositionConstants.CENTER);
				/////////////////////////////////////				
				
				child.setBounds(new Rectangle(x, y, r-15, r-15));
			}
		}	
	}
}

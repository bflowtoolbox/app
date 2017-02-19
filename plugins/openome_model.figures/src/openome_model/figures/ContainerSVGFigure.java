package openome_model.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

public class ContainerSVGFigure extends NodeSVGFigure {
	
	/** 
	 * The width (and height, assuming we're using perfect circles) of a collapsed
	 * actor (in number of pixels) 
	 */
	public static final int SIZE_OF_ACTOR_SYMBOL = 100;
	
	/**
	 * If a figure goes below this threshold, we can assume that the figure is collapsed
	 */
	public static final int ACTOR_COLLAPSED_WIDTH_AND_HEIGHT_THRESHOLD = 5 + SIZE_OF_ACTOR_SYMBOL;
	
	/**
	 * The width and height of the actor, when the actor symbol can simply
	 * be placed at the top left of the figure, rather than calculating
	 * where it should be placed on the edge of the actor bubble
	 */
	public static final int SIZE_OF_ACTOR_FOR_FIXED_SYMBOL = 350;
	
	public ContainerSVGFigure(String name) {
		super(name);
		setMaintainAspectRatio(true);
	}	
	
	protected void paintFigure(Graphics graphics) {
		
		// when the figure is collapsed, we want the actor symbol to take up the
		// entire client area
		if (getParent().getBounds().width <= ACTOR_COLLAPSED_WIDTH_AND_HEIGHT_THRESHOLD && 
			getParent().getBounds().height <= ACTOR_COLLAPSED_WIDTH_AND_HEIGHT_THRESHOLD) {
			setBounds(getParent().getBounds());
			super.paintFigure(graphics);
			
		} else {
		
			// but if the figure isn't collapsed (is expanded):
			
			// We want to draw the actor symbol on the top left edge of the actor bubble.
			// It will also need to stay a constant size (100) unless the actor
			// bubble is too small.
			
			// radius of the actor symbol. We set this to a constant because we want 
			// the actor symbol to be a fixed size (continued explanation at (see_here))
			int r = SIZE_OF_ACTOR_SYMBOL;
			
			float offsetConstant = 0.707f;
	
			// determine the exact location of where the symbol should be placed
			int xoffset = (int) (getParent().getBounds().width * (1 - offsetConstant) / 2);
			int yoffset = (int) (getParent().getBounds().height * (1 - offsetConstant) / 2);
					
			// finally, determine the exact position of where the actor symbol should be placed
			int x = getParent().getBounds().x + xoffset - r / 2;
			int y = getParent().getBounds().y + yoffset - r / 2;
			
			// if the entire actor figure (symbol + bubble) is small enough, then we simply
			// place the actor symbol at the top left corner, rather than calculating the exact point
			int actorSymbolThreshold = ContainerSVGFigure.SIZE_OF_ACTOR_FOR_FIXED_SYMBOL;
			
			// if the entire actor figure is too small to fit the actor symbol (which is fixed)
			// then ensure that we don't adjust the location
			if ((getParent().getBounds().preciseHeight() <= actorSymbolThreshold) 
			 || (getParent().getBounds().preciseWidth()) <= actorSymbolThreshold) {
				x = getParent().getBounds().x;
				y = getParent().getBounds().y;
				
			}
			
			setBounds(new Rectangle(x, y, r, r));
			super.paintFigure(graphics);
		}
	}
}
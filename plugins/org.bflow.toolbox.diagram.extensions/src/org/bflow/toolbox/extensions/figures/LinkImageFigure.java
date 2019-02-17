package org.bflow.toolbox.extensions.figures;

import org.bflow.toolbox.extensions.ILinkContext;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;

/**
 * Extends {@link ImageFigure} to add link symbol at the top-right corner of a
 * figure. The style and the behavior of the link is controlled via
 * {@link ILinkContext}.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-02-17
 *
 */
public class LinkImageFigure extends ImageFigure {
	private final ILinkContext _linkContext;
	
	/**
	 * Initializes the new instance with the given arguments.
	 * 
	 * @param image       Image to display as link symbol
	 * @param linkContext Context that describes the behavior of the link
	 */
	public LinkImageFigure(Image image, ILinkContext linkContext) {
		super(image);
		_linkContext = linkContext;
		
		this.addMouseListener(new MouseListener.Stub() {
			private long _lastClick;
			
			@Override
			public void mousePressed(MouseEvent me) {
				if (_linkContext == null) return;
				
				// Workaround because double click event/hook is not working
				long currentClick = System.currentTimeMillis();
				long delta = currentClick - _lastClick;
				_lastClick = currentClick;
				if (delta > 500) return; // 0.5s
				_linkContext.onLinkDoubleClick();
			}
		});
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.draw2d.Figure#isVisible()
	 */
	@Override
	public boolean isVisible() {
		if (_linkContext == null) return false;
		return _linkContext.showLink();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.draw2d.Figure#getToolTip()
	 */
	@Override
	public IFigure getToolTip() {
		if (!isVisible()) return null;
		return new Label(_linkContext.getToolTipText());
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.draw2d.Figure#setBounds(org.eclipse.draw2d.geometry.Rectangle)
	 */
	@Override
	public void setBounds(Rectangle rect) {
		// The given rect is the total area of the primary figure. 
		// Because this figure must not overlay the primary figure (due to event handling), 
		// the bounds must be restricted.
		// Furthermore, we move the area to the upper right location of the primary figure.
		
		int areaX = rect.x;
		int areaY = rect.y;
		int areaWidth = rect.width;
		// int areaHeight = rect.height;
		
		Dimension d = getPreferredSize();
		
		int x = areaX + areaWidth - d.width;
		int y = areaY + 5;
		int width = d.width;
		int height = d.height;
		
		super.setBounds(new Rectangle(x, y, width, height));
	}
}

package org.bflow.toolbox.hive.gmfbridge.graphiti.modelnavigation;

import java.util.HashMap;

import org.bflow.toolbox.hive.modelnavigator.IExtendedIconProvider;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * Implements {@link IExtendedIconProvider} to provide images of all figures
 * that are created by graphiti.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 12.04.2015
 * 
 */
public class GraphitiIconProvider implements IExtendedIconProvider {
	
	private Image fAnnouncingImage;
	private static final int fDefaultImageWidth = 16;
	private static final int fDefaultImageHeight = 16;
	
	private final HashMap<IGraphicalEditPart, Image> fEditPartImageCache = new HashMap<>();

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.common.core.service.IProvider#addProviderChangeListener(org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener)
	 */
	@Override
	public void addProviderChangeListener(IProviderChangeListener listener) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.common.core.service.IProvider#provides(org.eclipse.gmf.runtime.common.core.service.IOperation)
	 */
	@Override
	public boolean provides(IOperation operation) {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.common.core.service.IProvider#removeProviderChangeListener(org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener)
	 */
	@Override
	public void removeProviderChangeListener(IProviderChangeListener listener) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.common.ui.services.icon.IIconProvider#getIcon(org.eclipse.core.runtime.IAdaptable, int)
	 */
	@Override
	public Image getIcon(IAdaptable hint, int flags) {
		// We can always return an image, so we return a default one to indicate that we can handle it
		if (fAnnouncingImage != null) return fAnnouncingImage;
		return (fAnnouncingImage = new Image(Display.getDefault(), fDefaultImageWidth, fDefaultImageHeight));
	}
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.modelnavigator.IExtendedIconProvider#getIcon(org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart)
	 */
	@Override
	public Image getIcon(IGraphicalEditPart graphicalEditPart) {
		if (graphicalEditPart == null) return fAnnouncingImage;
		
		// Check the cache first
		Image cachedImage = fEditPartImageCache.get(graphicalEditPart);
		if (cachedImage != null) return cachedImage;
		
		// Create a new one
		IFigure figure = graphicalEditPart.getFigure();
		Rectangle boundingBox = figure.getBounds();		
		
		if (boundingBox.width == 0 || boundingBox.height == 0) return fAnnouncingImage;
		
		Image image = new Image(Display.getDefault(), boundingBox.width, boundingBox.height);
		GC gc = new GC(image);
		SWTGraphics swtGraphics = new SWTGraphics(gc);
		swtGraphics.setAdvanced(true);
		swtGraphics.translate(-boundingBox.x, -boundingBox.y);   
		
		figure.paint(swtGraphics);
		
		gc.dispose();
		swtGraphics.dispose();
		
		// Resize image
		Image scaledImage = resize(image, fDefaultImageWidth, fDefaultImageHeight);
		image.dispose();
		
		// Put it into the cache
		fEditPartImageCache.put(graphicalEditPart, scaledImage);
		
		return scaledImage;
	}
	
	/**
	 * Resizes the given image to the given bounds.
	 * 
	 * @param image
	 *            Image to resize
	 * @param width
	 *            Width after size
	 * @param height
	 *            Height after resize
	 * @return Resized image
	 */
	private Image resize(Image image, int width, int height) {
		Image scaled = new Image(Display.getDefault(), width, height);
		GC gc = new GC(scaled);
		gc.setAntialias(SWT.ON);
		gc.setInterpolation(SWT.HIGH);
		gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, 0, 0, width, height);
		gc.dispose();
		return scaled;
	}

}

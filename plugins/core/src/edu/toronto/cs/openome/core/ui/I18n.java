package edu.toronto.cs.openome.core.ui;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * Provides lookup services for internationalized text and images.
 *
 * @author <A HREF="mailto:johnson.thomas@mayo.edu">Thomas M Johnson</A>
 */
public class I18n {
	private static Map<ImageDescriptor, Image> fgImageRegistry;

	private I18n() {
		super();
	}

	/**
	 * Returns an internationalized string from plug-in properties, based on key.
	 * @param key
	 * @return String
	 */
	public static String getI18nString(String key) {
		try {
			return Plugin.getResourceString(key);		
		} catch (RuntimeException e) {
		}
		return key;
	}
	
	/**
	 * Returns the image with the given path (current display),
	 * relative to the plugin icon directory.
	 */
	public static Image getImage(String relativePath) {
		return getImage(Display.getCurrent(), getImageDescriptor(relativePath));
	}
	
	/**
	 * Returns an image for the given display as specified by the given image descriptor.
	 * @param display the display
	 * @param descriptor the image descriptor
	 * @return an image for the display as specified by the descriptor
	 */
	public static Image getImage(Display display, ImageDescriptor descriptor) {
		Map<ImageDescriptor, Image> map= getImageRegistry(display);
		Image image= (Image) map.get(descriptor);
		if (image == null) {
			image= descriptor.createImage();
			map.put(descriptor, image);
		}
		return image;
	}

	/**
	 * Returns the image descriptor with the given path, relative to the
	 * plugin icon directory.
	 */
	public static ImageDescriptor getImageDescriptor(String relativePath) {
		final String iconPath = "icons/";
		try {
			URL installURL = Plugin.getPlugin().getBundle().getEntry("/");
			URL url = new URL(installURL, iconPath + relativePath);
			return ImageDescriptor.createFromURL(url);
		}
		catch (MalformedURLException e) {
			return ImageDescriptor.getMissingImageDescriptor();
		}
	}

	/**
	 * Returns an image registry for the given display. If no such registry exists
	 * the resgitry is created.
	 * @param display the display
	 * @return the image registry for the given display
	 */
	protected static Map<ImageDescriptor, Image> getImageRegistry(Display display) {
		if (fgImageRegistry == null) {
			fgImageRegistry= new HashMap<ImageDescriptor, Image>();
			display.disposeExec(new Runnable() {
				public void run() {
					if (fgImageRegistry != null) {
						Map map= fgImageRegistry;
						fgImageRegistry= null;
						Iterator e= map.values().iterator();
						while (e.hasNext()) {
							Image image= (Image) e.next();
							if (!image.isDisposed())
								image.dispose();
						}
					}
				}
			});
		}
		return fgImageRegistry;
	}		
}
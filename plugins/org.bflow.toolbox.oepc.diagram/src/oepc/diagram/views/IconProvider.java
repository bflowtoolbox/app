package oepc.diagram.views;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Display;

public class IconProvider {
	public  static final String BROWSER_EXTENSION = "html";
	
	private static final Image defaultImage;
	
	private static ImageRegistry imageRegistry;
	
	static {
		defaultImage = new Image(Display.getCurrent(), IconProvider.class.getResourceAsStream("/icons/File-16.png"));
		imageRegistry = new ImageRegistry();
	}
	
	public static Image getIcon(String extension) {
		Image image = imageRegistry.get(extension);

		if (image != null) return image;

		Program program = Program.findProgram(extension);
		if (program == null) return defaultImage;
		
		ImageData imageData = program.getImageData();
		if (imageData == null) return defaultImage;
		
		image = new Image(Display.getCurrent(), imageData);
		imageRegistry.put(extension, image);

		return image;
	}
	
	public static String getExtension(String path) {
		String[] parts = path.split("\\.");
		String extension = parts[parts.length - 1];
		
		return extension;
	}
}

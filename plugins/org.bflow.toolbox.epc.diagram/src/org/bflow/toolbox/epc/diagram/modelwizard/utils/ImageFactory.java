package org.bflow.toolbox.epc.diagram.modelwizard.utils;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class ImageFactory 
{
	public static Image getImage(Display display, String name)
	{
		return new Image(display, new ImageFactory().getClass().getResourceAsStream(Constants.IMGPACKAGE+name));
	}
}

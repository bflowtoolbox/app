package org.bflow.toolbox.epc.diagram.modelwizard.utils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

/**
 * 
 * @author Arian Storch
 * @since 02/02/10
 * 
 */
public class ConnectionManager 
{
	private static int lastColor = 0;
	private static int colors[] = { SWT.COLOR_CYAN, 
									SWT.COLOR_YELLOW, 
									SWT.COLOR_DARK_MAGENTA, 
									SWT.COLOR_DARK_GREEN };
	
	/**
	 * Returns the next color to highlight connections.<p/>
	 * Note: There are only 4 colors at the moment which are iterated. When you create a 5th connection,
	 * it will get the same color as the first connection.
	 * @return next usable color
	 */
	public static Color getNextConnectionColor()
	{
		if(lastColor > colors.length)
			lastColor = 0;
		
		return Display.getCurrent().getSystemColor(colors[lastColor++]);
	}
}

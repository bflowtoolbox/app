package org.bflow.toolbox.extensions.commands;


import org.bflow.toolbox.extensions.edit.parts.ColorChangeable;
import org.eclipse.swt.graphics.Color;


/**
 * An <code>ColorNotice</code> is used to store an foreground and background
 * color for a specified <code>ColoredNodeEditPart</code>.
 * @author Joerg Hartmann
 *
 */
public class ColorNotice{
	
	
	/**
	 * The edit part self.
	 */
	private ColorChangeable editPart;
	
	
	/**
	 * The foreground.
	 */
	private Color defaultForeground;
	
	
	/**
	 * The background.
	 */
	private Color defaultBackground;
	
	
	/**
	 * Create me.
	 * @param editPart
	 * @param defaultForeground
	 * @param defaultBackground
	 */
	public ColorNotice(ColorChangeable editPart, Color defaultForeground, 
			Color defaultBackground){
		this.editPart = editPart;
		this.defaultForeground = defaultForeground;
		this.defaultBackground = defaultBackground;
	}

	
	/**
	 * Access the edit part.
	 * @return
	 */
	public ColorChangeable getEditPart() {
		return editPart;
	}

	
	/**
	 * Access the foreground.
	 * @return
	 */
	public Color getDefaultForeground() {
		return defaultForeground;
	}

	
	/**
	 * Access the background.
	 * @return
	 */
	public Color getDefaultBackground() {
		return defaultBackground;
	}
}
package org.bflow.toolbox.extensions.colorschemes;

import java.util.List;

import org.bflow.toolbox.extensions.colorschemes.commands.ApplyColorCommand;
import org.bflow.toolbox.extensions.edit.parts.ColorChangeable;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.graphics.Color;


/**
 * An implementation of an <code>IGlobalColorSchema</code> which paints 
 * all elements with black in foreground and white in background.
 * @author Joerg Hartmann
 * @since 0.0.7
 *
 */
public class BlackWhiteColorSchema extends GlobalColorSchema {
	
	/**
	 * Returns the background.
	 * White.
	 * @param type
	 * @return
	 */
	public Color getBackground(Class<?> type) {
		return ColorConstants.white;
	}

	
	/**
	 * Returns the foreground.
	 * Black.
	 * @param type
	 * @return
	 */
	public Color getForeground(Class<?> type) {
		return ColorConstants.black;
	}
	
	
	/**
	 * Returns an GEF to apply this schema to all delivered edit parts.
	 * @param editParts
	 * @return
	 */
	public ApplyColorCommand getCommand(List<ColorChangeable> editParts){
		ApplyColorCommand cmd = new ApplyColorCommand(this);
		cmd.setElements(editParts);
		return cmd;
	}
	
	/**
	 * Returns an description which is used to store the schema in the 
	 * <code>DiagramStyle</code>.
	 * @return
	 */
	public String toString(){
		return "IGlobalColorSchema=bw";
	}
	
	/**
	 * Returning the title.
	 * @return
	 */
	public String getTitle(){
		return "Black-White";
	}
}

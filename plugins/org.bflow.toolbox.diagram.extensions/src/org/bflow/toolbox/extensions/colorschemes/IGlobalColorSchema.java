package org.bflow.toolbox.extensions.colorschemes;

import java.util.List;
import java.util.Vector;

import org.bflow.toolbox.extensions.colorschemes.commands.ApplyColorCommand;
import org.bflow.toolbox.extensions.edit.parts.BflowDiagramEditPart;
import org.bflow.toolbox.extensions.edit.parts.ColorChangeable;
import org.eclipse.gef.commands.Command;
import org.eclipse.swt.graphics.Color;


/**
 * Each <code>BflowDiagramEditPart</code> provides an color schema.
 * Classes matches this schema must implement me to provide colors.
 * 
 * @author Joerg Hartmann
 * @since 0.0.7
 * @version 01/11/13
 */
public interface IGlobalColorSchema {
	
	/**
	 * Returns the foreground color specified by the delivered element class.
	 * @param type
	 * @return
	 */
	public Color getForeground(Class<?> type);
	
	/**
	 * Returns the background color specified by the delivered element class.
	 * @param type
	 * @return
	 */
	public Color getBackground(Class<?> type);
	
	/**
	 * Returns an GEF command to apply this schema to all delivered edit parts.
	 * @param editParts Edit parts that will be affected by the color schema.
	 * @return Instance of ApplyColorCommand
	 */
	public ApplyColorCommand getCommand(List<ColorChangeable> editParts);
	
	public Command getGlobalCommand(IGlobalColorSchema oldColorSchema, 
			IGlobalColorSchema nextColorSchema, 
			BflowDiagramEditPart bflowDiagramEditPart, Vector<ColorChangeable> children);
	
	/**
	 * Returns an description which is used to store the schema in the 
	 * <code>DiagramStyle</code>.
	 * @return
	 */
	public String toString();
	
	/**
	 * Returning the title.
	 * @return
	 */
	public String getTitle();
}

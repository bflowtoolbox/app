package org.bflow.toolbox.extensions.colorschemes;

import java.util.Vector;

import org.bflow.toolbox.extensions.colorschemes.commands.GlobalColorCommand;
import org.bflow.toolbox.extensions.edit.parts.BflowDiagramEditPart;
import org.bflow.toolbox.extensions.edit.parts.ColorChangeable;
import org.eclipse.gef.commands.Command;


/**
 * Represents an global schema to provide an <code>GlobalColorCommand</code>
 * 
 * @author Joerg Hartmann
 * @since 1.0.0
 * @version 01/11/13
 */
public abstract class GlobalColorSchema implements IGlobalColorSchema {

	/**
	 * Returning an <code>GlobalColorCommand</code>.
	 * @param oldColorSchema
	 * @param nextColorSchema
	 * @param bflowDiagramEditPart
	 * @param children
	 * @see GlobalColorCommand
	 * @return
	 */
	public Command getGlobalCommand(IGlobalColorSchema oldColorSchema, IGlobalColorSchema nextColorSchema, 
			BflowDiagramEditPart bflowDiagramEditPart, Vector<ColorChangeable> children) {
		return new GlobalColorCommand(oldColorSchema, nextColorSchema, bflowDiagramEditPart, children);
	}
}

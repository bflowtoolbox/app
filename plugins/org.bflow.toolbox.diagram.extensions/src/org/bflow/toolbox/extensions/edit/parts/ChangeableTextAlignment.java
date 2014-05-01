package org.bflow.toolbox.extensions.edit.parts;

import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.TextAlignment;


/**
 * If an edit part wants to change the text alignment of specified labels, it
 * must implement me.
 * 
 * @author Joerg Hartmann
 *
 */
public interface ChangeableTextAlignment {

	
	/**
	 * Returns all labels, for which the alignment should applied.
	 * @return
	 */
	public WrappingLabel[] getLabels();
	
	
	/**
	 * Returns the current alignment.
	 * @return
	 */
	public TextAlignment getCurrentAlignment();
}

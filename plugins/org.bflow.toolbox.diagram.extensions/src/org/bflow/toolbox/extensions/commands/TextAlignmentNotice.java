package org.bflow.toolbox.extensions.commands;


import org.bflow.toolbox.extensions.edit.parts.BflowNodeEditPart;
import org.eclipse.gmf.runtime.notation.TextAlignment;


/**
 * An <code>TextAlignmentNotice</code> is used to save an setted text alignment
 * and the edit part, which should use it.
 * @author Joerg Hartmann
 *
 */
public class TextAlignmentNotice {

	
	/**
	 * The edit part self.
	 */
	private BflowNodeEditPart editPart;
	
	
	/**
	 * The alignment.
	 */
	private TextAlignment oldAlignment;
	
	
	/**
	 * Create this notice.
	 * @param editPart
	 * @param oldAlignment
	 */
	public TextAlignmentNotice(BflowNodeEditPart editPart, 
			TextAlignment oldAlignment){
		this.editPart = editPart;
		this.oldAlignment = oldAlignment;
	}

	
	/**
	 * Access the edit part.
	 * @return
	 */
	public BflowNodeEditPart getEditPart() {
		return editPart;
	}

	
	/**
	 * Access the text alignment.
	 * @return
	 */
	public TextAlignment getOldAlignment() {
		return oldAlignment;
	}
}

package org.bflow.toolbox.extensions.commands;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.bflow.toolbox.extensions.edit.parts.BflowNodeEditPart;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.TextAlignment;
import org.eclipse.gmf.runtime.notation.TextStyle;


/**
 * An implementation of an undo- redoable command, to change the text 
 * alignment.
 * @author Joerg Hartmann
 *
 */
public class TextAlignmentCommand extends Command{

	
	/**
	 * Stores the edit parts and the former alignment.
	 */
	private Vector<TextAlignmentNotice> notices;
	
	
	/**
	 * The new alignment.
	 */
	private TextAlignment newAlignment;
	
	
	/**
	 * Create this command by delivering the selected edit parts and the new
	 * text alignment.
	 * @param elements
	 * @param newAlignment
	 */
	public TextAlignmentCommand(Vector<BflowNodeEditPart> elements, 
			TextAlignment newAlignment){
		notices = new Vector<TextAlignmentNotice>();
		for(Iterator<BflowNodeEditPart> i = elements.iterator(); i.hasNext();){
			BflowNodeEditPart part = i.next();
			notices.add(new TextAlignmentNotice(part, 
					part.getCurrentAlignment()));
		}
		this.newAlignment = newAlignment;
	}

	
	/**
	 * True.
	 */
	public boolean canExecute() {
		return true;
	}

	
	/**
	 * True.
	 */
	public boolean canUndo() {	
		return true;
	}

	
	/**
	 * Clears the notices.
	 */
	public void dispose() {
		notices = null;
	}
	
	
	/**
	 * Executes the command by applying the new alignment.
	 */
	public void execute() {
		for(Iterator<TextAlignmentNotice> elements = 
			notices.iterator(); elements.hasNext();){
			TextAlignmentNotice notice = elements.next();
			
			execute(notice.getEditPart(), newAlignment);
			/* Not required, because after saving the text alignment,
			 * an notification will be thrown. After catching, 
			 * the edit part will be refresh his text alignment.
			 * @see BflowNodeEditPart#notificationChanged(Notification)
			 * 
			if(execute(notice.getEditPart(), newAlignment)){
				notice.getEditPart().refreshTextAlignment();
			}
			*/
		}
	}
	
	
	/**
	 * Stores the delivered alignment in the TextStyle of the node.
	 * If the style is not created yet, it will be added.
	 * @param editPart
	 * @param alignment
	 * @return
	 */
	public boolean execute(final BflowNodeEditPart editPart, 
			final TextAlignment alignment){
		boolean success = true;
		
		AbstractTransactionalCommand command = new AbstractTransactionalCommand(
				editPart.getEditingDomain(), null, null) {

			@SuppressWarnings("unchecked")
			protected CommandResult doExecuteWithResult(
					IProgressMonitor monitor, IAdaptable info)
					throws ExecutionException {
				TextStyle style = (TextStyle) editPart.getPrimaryView().getStyle(
						NotationPackage.Literals.TEXT_STYLE);
				if(style == null){
					List<Style> styles = editPart.getPrimaryView().getStyles();
					styles.add(NotationFactory.eINSTANCE.createTextStyle());
				}
				style = (TextStyle) editPart.getPrimaryView().getStyle(
						NotationPackage.Literals.TEXT_STYLE);
				if (style != null) {
					style.setTextAlignment(alignment);
				}
				return CommandResult.newOKCommandResult();
			}
			
		};
		try {
			command.execute(null, null);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		return success;
	}

	
	/**
	 * Returns an description.
	 * @return
	 */
	public String getDescription() {
		return "text alignment";
	}

	
	/**
	 * Returns an printable label. 
	 */
	public String getLabel() {
		return "text alignment";
	}

	
	/**
	 * Redo.
	 */
	public void redo() {
		execute();
	}

	
	/**
	 * Undo.
	 */
	public void undo() {
		for(Iterator<TextAlignmentNotice> elements = notices.iterator(); 
		elements.hasNext();){
			TextAlignmentNotice notice = elements.next();
			if(execute(notice.getEditPart(), notice.getOldAlignment())){
				notice.getEditPart().refreshTextAlignment();
			}
		}
	}
}

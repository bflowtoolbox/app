package org.bflow.toolbox.extensions.colorschemes.commands;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.bflow.toolbox.extensions.colorschemes.IGlobalColorSchema;
import org.bflow.toolbox.extensions.commands.ColorNotice;
import org.bflow.toolbox.extensions.edit.parts.ColorChangeable;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.ShapeStyle;
import org.eclipse.swt.graphics.Color;

/**
 * Implements a command that applies a given color schema to a diagram 
 * or single parts of it.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 01/11/13
 */
public class ApplyColorCommand extends Command {
	
	/** The color schema that will be applied. */
	private IGlobalColorSchema colorSchema;
	
	/**
	 * Stores all elements and the information about previous colors.
	 */
	private Vector<ColorNotice> notices = new Vector<ColorNotice>();
	
	/**
	 * Creates a new instance for the given color schema.
	 * 
	 * @param colorSchema Color schema that is applied to the diagram
	 */
	public ApplyColorCommand(IGlobalColorSchema colorSchema) {
		this.colorSchema = colorSchema;
	}

	/**
	 * Sets the elements.
	 *
	 * @param elements the new elements
	 */
	public void setElements(List<ColorChangeable> elements) {
		notices.clear();
		for(Iterator<ColorChangeable> it = elements.iterator(); it.hasNext();) {
				ColorChangeable part = it.next();
				notices.add(new ColorNotice(part, 
						part.getPrimaryFigure().getForegroundColor(), 
						part.getPrimaryFigure().getBackgroundColor()));
			}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	@Override
	public boolean canExecute() {
		return true;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		return true;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#dispose()
	 */
	@Override
	public void dispose() {
		notices = null;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		Iterator<ColorNotice> elements = notices.iterator();
		while(elements.hasNext()) {
			ColorNotice notice = elements.next();
			ColorChangeable editPart = notice.getEditPart();
			
			IGlobalColorSchema schema = colorSchema;
			
			Color foreground = schema.getForeground(editPart.getClass());
			Color background = schema.getBackground(editPart.getClass());
			
			if(execute(notice.getEditPart(), foreground, background)) {
				notice.getEditPart().applyColor(foreground, background);
			}
		}
	}
	
	/**
	 * Writes the delivered fore- and background to the ShapeStyle.
	 * @param editPart
	 * @param foreground
	 * @param background
	 * @return
	 */
	public boolean execute(final ColorChangeable editPart, 
			final Color foreground, final Color background){
		boolean success = true;
		
		AbstractTransactionalCommand command = 
			new AbstractTransactionalCommand(editPart.getEditingDomain(), 
					null, null){

			protected CommandResult doExecuteWithResult(
					IProgressMonitor monitor, IAdaptable info)
					throws ExecutionException {
				
				ShapeStyle style = 
					(ShapeStyle) editPart.getPrimaryView().getStyle(
						NotationPackage.Literals.SHAPE_STYLE);
				if (style != null) {
					style.setLineColor(foreground.hashCode());
					style.setFillColor(background.hashCode());
				}
				
				return CommandResult.newOKCommandResult();
			}
			
		};
		
		try {
			command.execute(null, null);
		} catch (ExecutionException e) {
			success = false;
			e.printStackTrace();
		}
		
		return success;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		execute();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		Iterator<ColorNotice> elements = notices.iterator();
		while(elements.hasNext()) {
			ColorNotice notice = elements.next();
			Color foreground = notice.getDefaultForeground();
			Color background = notice.getDefaultBackground();
			if(execute(notice.getEditPart(), foreground, background)) {
				notice.getEditPart().applyColor(foreground, background);
			}
		}
	}
}

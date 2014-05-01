package org.bflow.toolbox.extensions.colorschemes.commands;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.bflow.toolbox.extensions.colorschemes.IGlobalColorSchema;
import org.bflow.toolbox.extensions.commands.ColorNotice;
import org.bflow.toolbox.extensions.edit.parts.BflowDiagramEditPart;
import org.bflow.toolbox.extensions.edit.parts.ColorChangeable;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.DiagramStyle;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.ShapeStyle;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.swt.graphics.Color;

/**
 * An <code>GlobalColorCommand</code> is an command, to store specified 
 * <code>IGlobalColorSchema's</code> by switching the colors of delivered 
 * elements.
 * This command is also redoable.
 * 
 * @author Joerg Hartmann
 * @since 1.0.0
 *
 */
public class GlobalColorCommand extends Command {

	private IGlobalColorSchema oldColorSchema;
	private IGlobalColorSchema nextColorSchema;
	private BflowDiagramEditPart bflowDiagramEditPart;
	
	/**
	 * Stores all elements and the information about previous colors.
	 */
	private Vector<ColorNotice> notices;
	
	
	/**
	 * Create me.
	 * @param oldColorSchema
	 * @param nextColorSchema
	 * @param bflowDiagramEditPart
	 * @param elements
	 */
	public GlobalColorCommand(IGlobalColorSchema oldColorSchema, 
			IGlobalColorSchema nextColorSchema,  
			BflowDiagramEditPart bflowDiagramEditPart, 
			Vector<ColorChangeable> elements){
		this.oldColorSchema = oldColorSchema;
		this.nextColorSchema = nextColorSchema;
		this.bflowDiagramEditPart = bflowDiagramEditPart;
		
		notices = new Vector<ColorNotice>();
		for(Iterator<ColorChangeable> i = elements.iterator(); 
			i.hasNext();){
			ColorChangeable part = i.next();
			notices.add(new ColorNotice(part, 
					part.getPrimaryFigure().getForegroundColor(), 
					part.getPrimaryFigure().getBackgroundColor()));
		}
	}
	
	
	/**
	 * Switches to the delivered color schema.
	 * @param nextSchema
	 * @return
	 */
	public boolean switchColorSchema(IGlobalColorSchema nextSchema){
		boolean success = saveColorSchema(nextSchema);
		if(success){
			bflowDiagramEditPart.setColorSchema(nextSchema);
		}
		
		return success;
	}
	
	
	/**
	 * Saves the delivered color schema in the diagram style.
	 * @param schema
	 * @return
	 */
	private boolean saveColorSchema(final IGlobalColorSchema schema){
		boolean success = true;
		
		AbstractTransactionalCommand cmd = new AbstractTransactionalCommand(
				bflowDiagramEditPart.getEditingDomain(), "add schema", null){

			@SuppressWarnings("unchecked")
			protected CommandResult doExecuteWithResult(
					IProgressMonitor monitor, IAdaptable info)
					throws ExecutionException {
				
				
				
				DiagramStyle diagramStyle = (DiagramStyle) bflowDiagramEditPart
						.getDiagramView().getStyle(
						NotationPackage.Literals.DIAGRAM_STYLE);
			
				
				if(diagramStyle != null){
					diagramStyle.setDescription(schema.toString());
				}
				else{
					List<Style> styles = bflowDiagramEditPart
						.getDiagramView().getStyles();
					diagramStyle = 
						NotationFactory.eINSTANCE.createDiagramStyle();
					styles.add(diagramStyle);
					diagramStyle.setDescription(schema.toString());
				};
				
				return CommandResult.newOKCommandResult();
			}
			
		};
		
		try {
			cmd.execute(null, null);
		} catch (ExecutionException e) {
			success = false;
			e.printStackTrace();
		}
		
		return success;
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
	 * Execute me.
	 */
	public void execute() {
		if(switchColorSchema(nextColorSchema)){
			setColorSchema(nextColorSchema);
		}
	}
	
	
	/**
	 * Applies the delivered color schema to all elements.
	 * @param schema
	 */
	public void setColorSchema(IGlobalColorSchema schema){
		Iterator<ColorNotice> elements = notices.iterator();
		while(elements.hasNext()){
			ColorNotice notice = elements.next();
			ColorChangeable editPart = notice.getEditPart();
			
			Color foreground = schema.getForeground(editPart.getClass());
			Color background = schema.getBackground(editPart.getClass());
			if(execute(notice.getEditPart(), foreground, background)){
				notice.getEditPart().applyColor(foreground, background);
			}
		}
	}
	
	
	/**
	 * Persists the delivered colors in the correspond style.
	 * @param editPart
	 * @param foreground
	 * @param background
	 * @return the success
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
	
	
	/**
	 * Returns an description.
	 * @return
	 */
	public String getDescription() {
		return nextColorSchema.getTitle();
	}

	
	/**
	 * Returns the label which is printed.
	 */
	public String getLabel() {
		return nextColorSchema.getTitle();
	}

	
	/**
	 * Redo.
	 * @see execute
	 */
	public void redo() {
		execute();
	}

	
	/**
	 * Undo.
	 * Applies the stored colors in the notices.
	 */
	public void undo() {
		if(switchColorSchema(oldColorSchema)){
			setColorSchema(oldColorSchema);
		}
	}
}

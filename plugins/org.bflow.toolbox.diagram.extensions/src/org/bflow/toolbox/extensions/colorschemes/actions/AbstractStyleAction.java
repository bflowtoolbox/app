package org.bflow.toolbox.extensions.colorschemes.actions;

import java.util.Iterator;
import java.util.Vector;

import org.bflow.toolbox.extensions.colorschemes.IGlobalColorSchema;
import org.bflow.toolbox.extensions.colorschemes.commands.ApplyColorCommand;
import org.bflow.toolbox.extensions.edit.parts.ColorChangeable;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;

/**
 * This class is used as a abstract super class for actions to switch into
 * a specified color style.
 * To access the selected elements use the delegate method called 
 * <code>getSelectedElements()</code>. So subclasses must only implement the 
 * run method.
 * 
 * @author Joerg Hartmann
 * @since 0.0.7
 * @version 01/11/13
 *
 */
public abstract class AbstractStyleAction implements IActionDelegate {
	
	/**
	 * Stores all elements off the last selection.
	 */
	private Vector<ColorChangeable> selectedElements;
	
	/** The associated color schema */
	private IGlobalColorSchema colorSchema;
	
	/**
	 * You've selected elements. This method find and store it.
	 * @param action
	 * @param selection
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		selectedElements = new Vector<ColorChangeable>();
		IStructuredSelection structuredSelection = (IStructuredSelection) selection;
		
		Iterator<?> it = structuredSelection.iterator();
		while(it.hasNext()) {
			EditPart element = (EditPart) it.next();
			if(element instanceof ColorChangeable && element instanceof EditPart) {
				selectedElements.add((ColorChangeable) element);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		if(colorSchema == null)
			colorSchema = getColorSchema();
		
		if(colorSchema == null)
			throw new NullPointerException("There is no color schema definded!");
		
		if(selectedElements.isEmpty())
			return;
		
		ApplyColorCommand cmd = colorSchema.getCommand(selectedElements);
		if(cmd == null)
			return;
		
		cmd.setElements(selectedElements);
		CommandStack cmdStack = selectedElements.get(0).getEditDomain().getCommandStack();
		cmdStack.execute(cmd);
	}
	
	/**
	 * Return the color schema that is used for this action.
	 * 
	 * @return Color schema to use
	 */
	protected abstract IGlobalColorSchema getColorSchema();
	
	/**
	 * Access all selected elements.
	 * @return
	 */
	protected Vector<ColorChangeable> getSelectedElements() {
		return selectedElements;
	}
}

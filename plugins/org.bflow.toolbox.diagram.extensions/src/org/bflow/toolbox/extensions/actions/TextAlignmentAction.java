package org.bflow.toolbox.extensions.actions;

import java.util.Iterator;
import java.util.Vector;


import org.bflow.toolbox.extensions.commands.TextAlignmentCommand;
import org.bflow.toolbox.extensions.edit.parts.BflowNodeEditPart;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.TextAlignment;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;


/**
 * Creates and executes an <code>TextAlignmentAction</code> to apply the
 * selected alignment.
 * @author Joerg Hartmann
 *
 */
public class TextAlignmentAction implements IActionDelegate{

	
	/**
	 * All selected elements.
	 */
	private Vector<BflowNodeEditPart> selectedElements;
	
	
	/**
	 * Creates a new <code>TextAlignmentAction</code> and executes it.
	 * @param action
	 */
	public void run(IAction action) {
		if(selectedElements != null){
			if(selectedElements.size() > 0){
				BflowNodeEditPart singlePart = selectedElements.get(0);
				EditDomain domain = singlePart.getEditDomain();
				TextAlignment newAlignment = null;
				if(action.getId().equals("textAlignment.left")){
					newAlignment = TextAlignment.LEFT_LITERAL;
				}
				else if(action.getId().equals("textAlignment.center")){
					newAlignment = TextAlignment.CENTER_LITERAL;
				}
				else{ // right
					newAlignment = TextAlignment.RIGHT_LITERAL;
				}
				domain.getCommandStack().execute(new TextAlignmentCommand(
						selectedElements, newAlignment));
			} 
		}
	}

	
	/**
	 * You've selected elements, store it.
	 * @param action
	 * @param selection
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		selectedElements = new Vector<BflowNodeEditPart>();
		IStructuredSelection structuredSelection = (IStructuredSelection) selection;
		
		Iterator<?> i = structuredSelection.iterator();
		while(i.hasNext()){
			EditPart element = (EditPart) i.next();
			if(element instanceof BflowNodeEditPart){
				selectedElements.add((BflowNodeEditPart) element);
			}
		}
	}
}

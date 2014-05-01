package org.bflow.toolbox.extensions.colorschemes.actions;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;


import org.bflow.toolbox.extensions.colorschemes.IGlobalColorSchema;
import org.bflow.toolbox.extensions.edit.parts.BflowDiagramEditPart;
import org.bflow.toolbox.extensions.edit.parts.ColorChangeable;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * An action to provide support to switch the colors of all children
 * of the selected diagram. Although sets the global color schema.
 * 
 * @author Joerg Hartmann, Arian Storch<arian.storch@bflow.org>
 * @since 0.0.7
 * @version 01/11/13
 */
public class StyleDiagramAction implements IObjectActionDelegate {
	
	/**
	 * The <code>BflowDiagramEditPart</code>
	 */
	private BflowDiagramEditPart bflowDiagramEditPart;
	
	/**
	 * Apply the action by collecting all children.
	 */
	@SuppressWarnings("unchecked")
	public void run(IAction action) {
		bflowDiagramEditPart = BflowDiagramEditPart.getCurrentViewer();
		
		if(bflowDiagramEditPart != null) {
			IGlobalColorSchema nextSchema = bflowDiagramEditPart.getSchema(action.getId());
			IGlobalColorSchema currentSchema = bflowDiagramEditPart.getColorSchema();
			
			List<EditPart> children = bflowDiagramEditPart.getChildren();
			Vector<ColorChangeable> editParts = new Vector<ColorChangeable>();
			List<EditPart> connections = bflowDiagramEditPart.getConnections();
			for(Iterator<EditPart> i = children.iterator(); i.hasNext();) {
				EditPart part = i.next();
				if(part instanceof ColorChangeable) {
					editParts.add((ColorChangeable) part);
				}
			}
			for(Iterator<EditPart> it = connections.iterator(); it.hasNext();) {
				EditPart part = it.next();
				if(part instanceof ColorChangeable) {
					editParts.add((ColorChangeable) part);
				}
			}
			
			bflowDiagramEditPart.getEditDomain().getCommandStack()
				.execute(nextSchema.getGlobalCommand(currentSchema, nextSchema, 
						bflowDiagramEditPart, editParts));	
		}
	}

	
	/**
	 * Stores the <code>DiagramEditPart</code> because it was selected.
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		bflowDiagramEditPart = BflowDiagramEditPart.getCurrentViewer();
	}

	/**
	 * Sets the editability of the action.
	 * @param action
	 * @param targetPart
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		bflowDiagramEditPart = BflowDiagramEditPart.getCurrentViewer();
		if(bflowDiagramEditPart != null) {
			IGlobalColorSchema schema = bflowDiagramEditPart.getColorSchema();
			if(schema != null) {
				boolean state = schema.toString().equals(action.getId());
				action.setChecked(state);
			}
		}
	}
}

package org.bflow.toolbox.extensions.actions;

import org.bflow.toolbox.extensions.edit.parts.BflowDiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IActionDelegate;


/**
 * Represents an non undoable action which packs the diagram to the minimal size
 * surrounding all elements.
 * 
 * @author Joerg Hartmann
 * @since 0.0.7
 * @version 17/08/13 by Arian Storch<arian.storch@bflow.org>
 *
 */
public class DiagramPagePackAction implements IActionDelegate {
	
	private DiagramEditPart iDiagramEditPart;
	
	/**
	 * Creates a new instance.
	 */
	public DiagramPagePackAction() {
		// Needed by Eclipse Action contribution service
	}

	/**
	 * Creates a new instance which operates on the given edit part.
	 * 
	 * @param diagramEditPart
	 *            The diagram edit part to operate on
	 */
	public DiagramPagePackAction(DiagramEditPart diagramEditPart) {
		iDiagramEditPart = diagramEditPart;
	}
	
	/**
	 * Runs this action by clicking.
	 * @param action
	 */
	public void run(IAction action) {
		BflowDiagramEditPart diagramEditPart = (BflowDiagramEditPart) iDiagramEditPart;
		
		if(diagramEditPart == null)
			diagramEditPart = BflowDiagramEditPart.getCurrentViewer();
		
		if(diagramEditPart != null){
			diagramEditPart.getDiagramFormLayer().getFormHelper().pack();
		}
	}

	
	/**
	 * Called when selection changed.
	 * @param action
	 * @param selection
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		
	}
}

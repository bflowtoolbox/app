package org.bflow.toolbox.hive.actions;

import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;

/**
 * Defines the interface of a common diagram action that is provided by 
 * the eclipse modeling toolbox. 
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 17/08/13
 */
public interface IToolboxDiagramAction {
	
	/**
	 * Sets the diagram edit part to operate on.
	 * 
	 * @param diagramEditPart Diagram edit part to operate on
	 */
	void setDiagramEditPart(DiagramEditPart diagramEditPart);
	
	/**
	 * Returns the currently used diagram edit part.
	 * 
	 * @return Currently used diagram edit part
	 */
	DiagramEditPart getDiagramEditPart();
	
	/**
	 * Invokes the action.
	 */
	void doRun();

}

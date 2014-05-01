package org.bflow.toolbox.hive.modelnavigator.utils;

import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;

/**
 * Provides some common methods to handle GMF based edit parts.
 * 
 * @author Arian Storch
 * @since 27/07/12
 *
 */
public class GMFUtility {
	
	/**
	 * Tries to select the given graphical edit part within the given diagram edit part. If the selected edit
	 * part is not visible within the view it will scrolled until it is.
	 * @param diagramEditPart diagram edit part which contains the graphical edit part
	 * @param graphicalEditPart element which will be selected
	 */
	public static void selectEditPart(DiagramEditPart diagramEditPart, IGraphicalEditPart graphicalEditPart) {
		selectEditPart(diagramEditPart, graphicalEditPart, true);
	}
	
	/**
	 * Tries to select the given graphical edit part within the given diagram edit part.
	 * @param diagramEditPart diagram edit part which contains the graphical edit part
	 * @param graphicalEditPart element which will be selected
	 * @param reveal if true, the selected element will be scrolled into the view 
	 */
	public static void selectEditPart(DiagramEditPart diagramEditPart, IGraphicalEditPart graphicalEditPart, boolean reveal) {
		// HINT http://www.eclipse.org/forums/index.php/t/44944/
		graphicalEditPart.getViewer().select(graphicalEditPart);
		graphicalEditPart.getViewer().reveal(graphicalEditPart);
	}

}

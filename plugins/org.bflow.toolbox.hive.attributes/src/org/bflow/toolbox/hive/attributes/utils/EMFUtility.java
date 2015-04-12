package org.bflow.toolbox.hive.attributes.utils;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;

/**
 * Provides some methods for an easier handling of EMF objects.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 17.07.12
 * 
 */
public class EMFUtility {

	/**
	 * Returns the EObject defined by the given graphical edit part or null if
	 * none could be found.
	 * 
	 * @param graphicalEditPart
	 *            edit part whose EObject shall be found
	 * @return associated EObject or null
	 */
	public static EObject getEObject(IGraphicalEditPart graphicalEditPart) {
		if (graphicalEditPart instanceof DiagramEditPart) {
			DiagramEditPart diagramEditPart = (DiagramEditPart) graphicalEditPart;
			return diagramEditPart.getNotationView().getElement();
		}

		if (graphicalEditPart instanceof ShapeNodeEditPart || graphicalEditPart instanceof ConnectionNodeEditPart) {
			return graphicalEditPart.getPrimaryView().getElement();
		}

		return null;
	}

}

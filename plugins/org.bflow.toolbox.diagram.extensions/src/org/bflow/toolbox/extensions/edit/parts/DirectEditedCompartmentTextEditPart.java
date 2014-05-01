package org.bflow.toolbox.extensions.edit.parts;

import org.bflow.toolbox.extensions.trackers.DirectEditedEditPartTracker;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart;
import org.eclipse.gmf.runtime.notation.View;


/**
 * An implementation of an <code>CompartmentEditPart</code> that delivers
 * an <code>DirectEditedEditPartTracker</code> for simple direct edit.
 * 
 * @author Joerg Harmann
 * @since 0.0.7
 *
 */
public abstract class DirectEditedCompartmentTextEditPart extends 
	CompartmentEditPart{

	
	/**
	 * Creates the edit part.
	 * @param view
	 */
	public DirectEditedCompartmentTextEditPart(View view) {
		super(view);
	}

	
	/**
	 * Returns the drag tracker.
	 * @param request
	 * @see DirectEditedEditPartTracker
	 */
	public DragTracker getDragTracker(Request request) {
		if (request instanceof SelectionRequest
			&& ((SelectionRequest) request).getLastButtonPressed() == 3)
			return null;
		
		return new DirectEditedEditPartTracker(this, getTopGraphicEditPart());
	}
}

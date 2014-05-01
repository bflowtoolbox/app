package org.bflow.toolbox.extensions.trackers;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.tools.SelectEditPartTracker;
import org.eclipse.gef.tools.TargetingTool;


/**
 * Allows you to edit the containing compartment of the owner by 2 simple
 * mouse clicks.
 * @author Joerg Hartmann
 * @since 0.0.7
 *
 */
public class DirectEditedCompartmentTextTracker extends SelectEditPartTracker {

	
	/**
	 * Creates this tracker.
	 * @param owner
	 */
	public DirectEditedCompartmentTextTracker(EditPart owner) {
		super(owner);
	}


	/**
	 * Sets an flag, that direct edit is now allowed.
	 */
	protected void performConditionalSelection() {
		//performSelection();
		setFlag(TargetingTool.MAX_FLAG << 2, true);
	}

	
	/**
	 * Is called after the click is completed.
	 * Performs direct edit, if the correspond edit flag was set.
	 * @param button
	 */
	protected boolean handleButtonUp(int button) {
		if (isInState(STATE_DRAG)) {
			//performSelection();
			if (getFlag(TargetingTool.MAX_FLAG << 2)) {
				performDirectEdit();
			}

			if (button == 1
					&& getSourceEditPart().getSelected() != 
						EditPart.SELECTED_NONE) {
				getCurrentViewer().reveal(getSourceEditPart());
			}

			setState(STATE_TERMINAL);
			return true;
		}
		return false;
	}
	
	
	/**
	 * Double clicks are not supported directly.
	 * @param button
	 */
	protected boolean handleDoubleClick(int button) {
		return false;
	}
}

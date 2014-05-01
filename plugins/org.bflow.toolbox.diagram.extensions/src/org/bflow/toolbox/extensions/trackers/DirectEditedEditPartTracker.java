package org.bflow.toolbox.extensions.trackers;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.gef.ui.internal.tools.DelegatingDragEditPartsTracker;
import org.eclipse.swt.events.MouseEvent;


/**
 * An <code>DirectEditedEditPartTracker</code> is an representation of an
 * <code>DelegatingDragEditPartsTracker</code> that allows you to simple edit
 * compartments, containing an label, with only two clicks.
 * @author Joerg Hartmann
 * @since 0.0.7
 *
 */
@SuppressWarnings("restriction")
public class DirectEditedEditPartTracker extends DelegatingDragEditPartsTracker{

	
	/**
	 * The <code>EditPart</code> that requests this tracker.
	 */
	private EditPart delegatingEditPart;
	
	
	/**
	 * Create this tracker.
	 * @param delegatingEditPart requestor
	 * @param delegateEditPart parent
	 */
	public DirectEditedEditPartTracker(EditPart delegatingEditPart,
			EditPart delegateEditPart) {
		super(delegatingEditPart, delegateEditPart);
		this.delegatingEditPart = delegatingEditPart;
	}
	
	
	/**
	 * You've clicked on the <code>EditPart</code>. This method will be called
	 * when the click is finished.
	 * @param buttin represents the mouse button
	 */
	protected boolean handleButtonDown(int button) {
		setDragTracker(new DirectEditedCompartmentTextTracker(
				delegatingEditPart));
		lockTargetEditPart(getTargetEditPart());
		return true;
	}
	
	
	/**
	 * Transfers the <code>MouseEvent</code> to the drag tracker.
	 * @param me
	 * @param viewer
	 */
	public void mouseDoubleClick(MouseEvent me, EditPartViewer viewer) {
		if(getDragTracker() != null){
			getDragTracker().mouseDoubleClick(me, viewer);
		}
	}
}

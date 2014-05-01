package org.bflow.toolbox.extensions.trackers;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.tools.DragEditPartsTrackerEx;


public class BusinessElementDragEditPartsTracker extends DragEditPartsTrackerEx{

	public BusinessElementDragEditPartsTracker(EditPart sourceEditPart) {
		super(sourceEditPart);
		
	}
	
	protected void setTargetEditPart(EditPart editpart) {
		
		if(editpart == getSourceEditPart().getParent()){
			super.setTargetEditPart(editpart);
		}
		else if(editpart.getClass() == getSourceEditPart().getParent().getClass()){
			super.setTargetEditPart(editpart);
		}
		else{
			super.setTargetEditPart(null);
		}
	}
	
	protected boolean handleDragInProgress() {
		updateTargetRequest();
		if (updateTargetUnderMouse())
			updateTargetRequest();
		showTargetFeedback();
		showSourceFeedback();
		setCurrentCommand(getCommand());
		return true;
	}
	
	
	protected boolean updateTargetUnderMouse() {
		if (!isTargetLocked()) {
			
			EditPart editPart = getCurrentViewer().findObjectAtExcluding(
				getLocation(),
				getExclusionSet(),
				getTargetingConditional());
			if (editPart != null)
				editPart = editPart.getTargetEditPart(getTargetRequest());
			boolean changed = getTargetEditPart() != editPart;
			
			setTargetEditPart(editPart);
			return changed;
		} else
			return false;
		
	}
}

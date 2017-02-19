package edu.toronto.cs.openome_model.diagram.edit.policies;

import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.LabelDirectEditPolicy;

public class NameLabelDirectEditPolicy extends LabelDirectEditPolicy {
		protected void showCurrentEditValue(DirectEditRequest request) {			
			((ITextAwareEditPart) this.getHost()).setLabelText("");
		}
	}
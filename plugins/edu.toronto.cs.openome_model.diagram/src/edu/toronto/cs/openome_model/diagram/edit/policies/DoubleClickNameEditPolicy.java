package edu.toronto.cs.openome_model.diagram.edit.policies;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.OpenEditPolicy;

/**
 * This policy is responsible for handling the action of double clicking on the
 * intentions and actors
 * 
 * @author showzeb
 * 
 */
public class DoubleClickNameEditPolicy extends OpenEditPolicy {

	@Override
	protected Command getOpenCommand(Request request) {
		if (understandsRequest(request)) {
			GraphicalEditPart gp = (GraphicalEditPart) getHost();
			if (!gp.toString().contains("Compartment")) {
				(gp).performRequest(new Request(
						org.eclipse.gef.RequestConstants.REQ_DIRECT_EDIT));
			} else {
				GraphicalEditPart parent = (GraphicalEditPart) gp.getParent();
				(parent).performRequest(new Request(
						org.eclipse.gef.RequestConstants.REQ_DIRECT_EDIT));

			}
		}
		return null;
	}

}

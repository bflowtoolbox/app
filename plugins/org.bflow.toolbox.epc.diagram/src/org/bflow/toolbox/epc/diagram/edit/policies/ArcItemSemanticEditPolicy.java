package org.bflow.toolbox.epc.diagram.edit.policies;

import org.bflow.toolbox.epc.extensions.idelete.IntelligentDeleteCommand;
import org.bflow.toolbox.epc.extensions.idelete.IntelligentDeleter;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

/**
 * @generated
 */
public class ArcItemSemanticEditPolicy extends EpcBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		
		CompoundCommand cc = new CompoundCommand();
		
		if (IntelligentDeleter.isEnabled()) {
			IntelligentDeleteCommand idc = new IntelligentDeleteCommand(req);

			if (!IntelligentDeleter.isInProgress() && IntelligentDeleter.isAvailable(req.getElementToDestroy(), this))
				cc.add(getGEFWrapper(idc));
		}
				
		cc.add(getGEFWrapper(new DestroyElementCommand(req)));
						
		return cc.unwrap();
	}	
}

/*
 * 
 */
package orgchart.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import orgchart.diagram.providers.OrgcElementTypes;

/**
 * @generated
 */
public class Relation2ItemSemanticEditPolicy extends
		OrgcBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public Relation2ItemSemanticEditPolicy() {
		super(OrgcElementTypes.Relation2_4002);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new DestroyElementCommand(req));
	}

}

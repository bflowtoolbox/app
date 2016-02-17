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
public class Relation1ItemSemanticEditPolicy extends
		OrgcBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public Relation1ItemSemanticEditPolicy() {
		super(OrgcElementTypes.Relation1_4001);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new DestroyElementCommand(req));
	}

}

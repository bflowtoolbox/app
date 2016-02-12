/*
 * 
 */
package vcchart.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

import vcchart.diagram.providers.VcElementTypes;

/**
 * @generated
 */
public class Relation3ItemSemanticEditPolicy extends
		VcBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public Relation3ItemSemanticEditPolicy() {
		super(VcElementTypes.Relation3_4003);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new DestroyElementCommand(req));
	}

}

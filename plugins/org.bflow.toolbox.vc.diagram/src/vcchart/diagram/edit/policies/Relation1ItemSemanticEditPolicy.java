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
public class Relation1ItemSemanticEditPolicy extends
		VcBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public Relation1ItemSemanticEditPolicy() {
		super(VcElementTypes.Relation1_4001);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new DestroyElementCommand(req));
	}

}

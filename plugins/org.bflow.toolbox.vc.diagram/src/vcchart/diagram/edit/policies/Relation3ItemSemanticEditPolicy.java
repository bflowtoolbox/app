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
<<<<<<< HEAD:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/edit/policies/Relation2ItemSemanticEditPolicy.java
public class Relation2ItemSemanticEditPolicy extends
=======
public class Relation3ItemSemanticEditPolicy extends
>>>>>>> development:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/edit/policies/Relation3ItemSemanticEditPolicy.java
		VcBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
<<<<<<< HEAD:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/edit/policies/Relation2ItemSemanticEditPolicy.java
	public Relation2ItemSemanticEditPolicy() {
		super(VcElementTypes.Relation2_4002);
=======
	public Relation3ItemSemanticEditPolicy() {
		super(VcElementTypes.Relation3_4003);
>>>>>>> development:plugins/org.bflow.toolbox.vc.diagram/src/vcchart/diagram/edit/policies/Relation3ItemSemanticEditPolicy.java
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new DestroyElementCommand(req));
	}

}

package org.bflow.toolbox.epc.diagram.edit.parts;

import org.bflow.toolbox.epc.diagram.edit.policies.EpcCanonicalEditPolicy;
import org.bflow.toolbox.epc.diagram.edit.policies.EpcItemSemanticEditPolicy;
import org.bflow.toolbox.epc.diagram.edit.policies.MyXYLayoutEditPolicy;
import org.bflow.toolbox.epc.extensions.edit.parts.LiveValidatedDiagramEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableShapeEditPolicy;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated NOT
 */
public class EpcEditPart extends LiveValidatedDiagramEditPart {

	/**
	 * @generated
	 */
	public final static String MODEL_ID = "Epc"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 1000;

	/**
	 * @generated
	 */
	public EpcEditPart(View view) {
		super(view);
	}

	/**
	 * @generated NOT
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new EpcItemSemanticEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE, new EpcCanonicalEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.POPUPBAR_ROLE);
	}

	/**
	 * @generated NOT
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {
		MyXYLayoutEditPolicy lep = new MyXYLayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {
				EditPolicy result = super.createChildEditPolicy(child);
				if (result == null) {
					return new ResizableShapeEditPolicy();
				}
				return result;
			}
		};
		return lep;
	}
}

package oepc.diagram.edit.parts;

import oepc.diagram.edit.policies.OEPCCanonicalEditPolicy;
import oepc.diagram.edit.policies.OEPCItemSemanticEditPolicy;
import oepc.diagram.extensions.edit.parts.LiveValidatedDiagramEditPart;
import oepc.diagram.extensions.edit.parts.legend.LegendEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated NOT
 */
public class OEPCEditPart extends LiveValidatedDiagramEditPart{

	/**
	 * @generated
	 */
	public final static String MODEL_ID = "Oepc"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 79;
	
	private LegendEditPart legendEditPart;

	/**
	 * @generated
	 */
	public OEPCEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new OEPCItemSemanticEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE, new OEPCCanonicalEditPolicy());
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.POPUPBAR_ROLE);
	}

	public LegendEditPart getLegendEditPart() {
		return legendEditPart;
	}

	public void setLegendEditPart(LegendEditPart legendEditPart) {
		this.legendEditPart = legendEditPart;
	}
	
	public boolean useLegend(){
		return getLegendEditPart() != null;
	}
}

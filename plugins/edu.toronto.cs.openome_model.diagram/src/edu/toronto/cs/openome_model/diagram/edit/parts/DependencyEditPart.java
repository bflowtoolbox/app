package edu.toronto.cs.openome_model.diagram.edit.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class DependencyEditPart extends ConnectionNodeEditPart implements
		ITreeBranchEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 4001;

	/**
	 * @generated
	 */
	public DependencyEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(
				EditPolicyRoles.SEMANTIC_ROLE,
				new edu.toronto.cs.openome_model.diagram.edit.policies.DependencyItemSemanticEditPolicy());
	}

	/**
	 * @generated NOT
	 */
	public ConnectionAnchor getSourceConnectionAnchor(){
		return super.getSourceConnectionAnchor();
	}
	
	/**
	 * @generated NOT
	 */
	public ConnectionAnchor getTargetConnectionAnchor(){
		return super.getTargetConnectionAnchor();
	}
	
	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	protected Connection createConnectionFigure() {
		return new openome_model.figures.DependencyLineConnection();
	}

	/**
	 * @generated
	 */
	public openome_model.figures.DependencyLineConnection getPrimaryShape() {
		return (openome_model.figures.DependencyLineConnection) getFigure();
	}

	/**
	 * Make this line straight
	 */
	public void straightenLine() {
		// Straighten the connector figure
		getPrimaryShape().straightenLine();

		// Now update the Bendpoints list to 0, since a  straight line has no bendpoint
		// otherwise next time the line get refreshed the straight effect would be nullified
		RelativeBendpoints bendpoints = (RelativeBendpoints) getEdge()
				.getBendpoints();
		ArrayList<RelativeBendpoints> emptyList = new ArrayList<RelativeBendpoints>();

		// Update the Bendpoint collection in such a way that no odd notification is given
		bendpoints.eSetDeliver(false);
		bendpoints.setPoints(emptyList);
		bendpoints.eSetDeliver(true);
	}

}

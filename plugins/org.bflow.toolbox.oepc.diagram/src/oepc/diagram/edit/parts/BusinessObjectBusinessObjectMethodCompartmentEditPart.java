package oepc.diagram.edit.parts;

import java.util.Iterator;
import java.util.List;

import oepc.diagram.Messages;
import oepc.diagram.edit.policies.BusinessObjectBusinessObjectMethodCompartmentCanonicalEditPolicy;
import oepc.diagram.edit.policies.BusinessObjectBusinessObjectMethodCompartmentItemSemanticEditPolicy;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ListCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableCompartmentEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class BusinessObjectBusinessObjectMethodCompartmentEditPart extends
		ListCompartmentEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 7002;

	/**
	 * @generated
	 */
	public BusinessObjectBusinessObjectMethodCompartmentEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected boolean hasModelChildrenChanged(Notification evt) {
		return false;
	}

	/**
	 * @generated
	 */
	public String getCompartmentName() {
		return Messages.BusinessObjectBusinessObjectMethodCompartmentEditPart_title;
	}

	/**
	 * @generated
	 */
	public IFigure createFigure() {
		ResizableCompartmentFigure result = (ResizableCompartmentFigure) super
				.createFigure();
		result.setTitleVisibility(false);
		return result;
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE,
				new ResizableCompartmentEditPolicy());
		installEditPolicy(
				EditPolicyRoles.SEMANTIC_ROLE,
				new BusinessObjectBusinessObjectMethodCompartmentItemSemanticEditPolicy());
		installEditPolicy(EditPolicyRoles.CREATION_ROLE,
				new CreationEditPolicy());
		installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE,
				new DragDropEditPolicy());
		installEditPolicy(
				EditPolicyRoles.CANONICAL_ROLE,
				new BusinessObjectBusinessObjectMethodCompartmentCanonicalEditPolicy());
	}

	/**
	 * @generated
	 */
	protected void setRatio(Double ratio) {
		// nothing to do -- parent layout does not accept Double constraints as ratio
		// super.setRatio(ratio); 
	}

	/**
	 * @generated NOT
	 */
	@Override
	public void refreshChildren() {
		super.refreshChildren();

		List children = getChildren();

		for (Iterator i = children.iterator(); i.hasNext();) {
			GraphicalEditPart child = (GraphicalEditPart) i.next();
			IFigure childFigure = child.getFigure();
			int childIndex = children.indexOf(child);

			if (childIndex == 0 && childIndex == children.size() - 1) {
				childFigure.setBorder(new MarginBorder(getMapMode().DPtoLP(10),
						getMapMode().DPtoLP(1), getMapMode().DPtoLP(10),
						getMapMode().DPtoLP(1)));
			} else if (childIndex == 0) {
				childFigure.setBorder(new MarginBorder(getMapMode().DPtoLP(10),
						getMapMode().DPtoLP(1), getMapMode().DPtoLP(1),
						getMapMode().DPtoLP(1)));
			} else if (childIndex == children.size() - 1) {
				childFigure.setBorder(new MarginBorder(getMapMode().DPtoLP(1),
						getMapMode().DPtoLP(1), getMapMode().DPtoLP(10),
						getMapMode().DPtoLP(1)));
			} else {
				childFigure.setBorder(new MarginBorder(getMapMode().DPtoLP(1),
						getMapMode().DPtoLP(1), getMapMode().DPtoLP(1),
						getMapMode().DPtoLP(1)));
			}
			childFigure.repaint();
		}

	}

}

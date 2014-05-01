package oepc.diagram.edit.policies;

import oepc.diagram.edit.commands.ControlFlowEdgeCreateCommand;
import oepc.diagram.edit.commands.ControlFlowEdgeReorientCommand;
import oepc.diagram.edit.commands.InformationEdgeCreateCommand;
import oepc.diagram.edit.commands.InformationEdgeReorientCommand;
import oepc.diagram.edit.parts.ControlFlowEdgeEditPart;
import oepc.diagram.edit.parts.InformationEdgeEditPart;
import oepc.diagram.providers.OepcElementTypes;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class ORConnectorItemSemanticEditPolicy extends
		OepcBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		CompoundCommand cc = getDestroyEdgesCommand();
		addDestroyShortcutsCommand(cc);
		View view = (View) getHost().getModel();
		if (view.getEAnnotation("Shortcut") != null) { //$NON-NLS-1$
			req.setElementToDestroy(view);
		}
		cc.add(getGEFWrapper(new DestroyElementCommand(req)));
		return cc.unwrap();
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req)
				: getCompleteCreateRelationshipCommand(req);
		return command != null ? command : super
				.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getStartCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (OepcElementTypes.ControlFlowEdge_4001 == req.getElementType()) {
			return getGEFWrapper(new ControlFlowEdgeCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
		if (OepcElementTypes.InformationEdge_4002 == req.getElementType()) {
			return getGEFWrapper(new InformationEdgeCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (OepcElementTypes.ControlFlowEdge_4001 == req.getElementType()) {
			return getGEFWrapper(new ControlFlowEdgeCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
		if (OepcElementTypes.InformationEdge_4002 == req.getElementType()) {
			return getGEFWrapper(new InformationEdgeCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientRelationshipCommand(
			ReorientRelationshipRequest req) {
		switch (getVisualID(req)) {
		case ControlFlowEdgeEditPart.VISUAL_ID:
			return getGEFWrapper(new ControlFlowEdgeReorientCommand(req));
		case InformationEdgeEditPart.VISUAL_ID:
			return getGEFWrapper(new InformationEdgeReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}

}

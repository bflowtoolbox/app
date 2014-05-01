package org.bflow.toolbox.epc.diagram.edit.policies;

import org.bflow.toolbox.epc.diagram.edit.commands.ArcCreateCommand;
import org.bflow.toolbox.epc.diagram.edit.commands.ArcReorientCommand;
import org.bflow.toolbox.epc.diagram.edit.commands.InformationArcCreateCommand;
import org.bflow.toolbox.epc.diagram.edit.commands.InformationArcReorientCommand;
import org.bflow.toolbox.epc.diagram.edit.commands.RelationCreateCommand;
import org.bflow.toolbox.epc.diagram.edit.commands.RelationReorientCommand;
import org.bflow.toolbox.epc.diagram.edit.parts.ArcEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.InformationArcEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.RelationEditPart;
import org.bflow.toolbox.epc.diagram.providers.EpcElementTypes;
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
public class ParticipantItemSemanticEditPolicy extends
		EpcBaseItemSemanticEditPolicy {

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
		if (EpcElementTypes.Arc_4001 == req.getElementType()) {
			return getGEFWrapper(new ArcCreateCommand(req, req.getSource(), req
					.getTarget()));
		}
		if (EpcElementTypes.Relation_4002 == req.getElementType()) {
			return getGEFWrapper(new RelationCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (EpcElementTypes.InformationArc_4003 == req.getElementType()) {
			return getGEFWrapper(new InformationArcCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (EpcElementTypes.Arc_4001 == req.getElementType()) {
			return getGEFWrapper(new ArcCreateCommand(req, req.getSource(), req
					.getTarget()));
		}
		if (EpcElementTypes.Relation_4002 == req.getElementType()) {
			return getGEFWrapper(new RelationCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (EpcElementTypes.InformationArc_4003 == req.getElementType()) {
			return getGEFWrapper(new InformationArcCreateCommand(req, req
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
		case ArcEditPart.VISUAL_ID:
			return getGEFWrapper(new ArcReorientCommand(req));
		case RelationEditPart.VISUAL_ID:
			return getGEFWrapper(new RelationReorientCommand(req));
		case InformationArcEditPart.VISUAL_ID:
			return getGEFWrapper(new InformationArcReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}

}

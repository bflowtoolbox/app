package org.bflow.toolbox.vc.diagram.edit.policies;

import org.bflow.toolbox.vc.diagram.edit.commands.PredecessorConnectionCreateCommand;
import org.bflow.toolbox.vc.diagram.edit.commands.PredecessorConnectionReorientCommand;
import org.bflow.toolbox.vc.diagram.edit.commands.ProcessSuperiorityCreateCommand;
import org.bflow.toolbox.vc.diagram.edit.commands.ProcessSuperiorityReorientCommand;
import org.bflow.toolbox.vc.diagram.edit.commands.RelationCreateCommand;
import org.bflow.toolbox.vc.diagram.edit.commands.RelationReorientCommand;
import org.bflow.toolbox.vc.diagram.edit.parts.PredecessorConnectionEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.ProcessSuperiorityEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.RelationEditPart;
import org.bflow.toolbox.vc.diagram.providers.VcElementTypes;
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
public class ValueChainItemSemanticEditPolicy extends
		VcBaseItemSemanticEditPolicy {

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
		if (VcElementTypes.PredecessorConnection_4001 == req.getElementType()) {
			return getGEFWrapper(new PredecessorConnectionCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (VcElementTypes.ProcessSuperiority_4002 == req.getElementType()) {
			return getGEFWrapper(new ProcessSuperiorityCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
		if (VcElementTypes.Relation_4003 == req.getElementType()) {
			return getGEFWrapper(new RelationCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (VcElementTypes.PredecessorConnection_4001 == req.getElementType()) {
			return getGEFWrapper(new PredecessorConnectionCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (VcElementTypes.ProcessSuperiority_4002 == req.getElementType()) {
			return getGEFWrapper(new ProcessSuperiorityCreateCommand(req, req
					.getSource(), req.getTarget()));
		}
		if (VcElementTypes.Relation_4003 == req.getElementType()) {
			return getGEFWrapper(new RelationCreateCommand(req,
					req.getSource(), req.getTarget()));
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
		case PredecessorConnectionEditPart.VISUAL_ID:
			return getGEFWrapper(new PredecessorConnectionReorientCommand(req));
		case ProcessSuperiorityEditPart.VISUAL_ID:
			return getGEFWrapper(new ProcessSuperiorityReorientCommand(req));
		case RelationEditPart.VISUAL_ID:
			return getGEFWrapper(new RelationReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}

}

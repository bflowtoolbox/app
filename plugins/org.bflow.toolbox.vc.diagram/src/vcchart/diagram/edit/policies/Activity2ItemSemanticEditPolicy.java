/*
 * 
 */
package vcchart.diagram.edit.policies;

import java.util.Iterator;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

import vcchart.diagram.edit.commands.Relation1CreateCommand;
import vcchart.diagram.edit.commands.Relation1ReorientCommand;
import vcchart.diagram.edit.commands.Relation2CreateCommand;
import vcchart.diagram.edit.commands.Relation2ReorientCommand;
import vcchart.diagram.edit.commands.Relation3CreateCommand;
import vcchart.diagram.edit.commands.Relation3ReorientCommand;
import vcchart.diagram.edit.parts.Relation1EditPart;
import vcchart.diagram.edit.parts.Relation2EditPart;
import vcchart.diagram.edit.parts.Relation3EditPart;
import vcchart.diagram.part.VcVisualIDRegistry;
import vcchart.diagram.providers.VcElementTypes;

/**
 * @generated
 */
public class Activity2ItemSemanticEditPolicy extends
		VcBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public Activity2ItemSemanticEditPolicy() {
		super(VcElementTypes.Activity2_2004);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		View view = (View) getHost().getModel();
		CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(
				getEditingDomain(), null);
		cmd.setTransactionNestingEnabled(false);
		for (Iterator<?> it = view.getTargetEdges().iterator(); it.hasNext();) {
			Edge incomingLink = (Edge) it.next();
			if (VcVisualIDRegistry.getVisualID(incomingLink) == Relation1EditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (VcVisualIDRegistry.getVisualID(incomingLink) == Relation2EditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (VcVisualIDRegistry.getVisualID(incomingLink) == Relation3EditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
		}
		for (Iterator<?> it = view.getSourceEdges().iterator(); it.hasNext();) {
			Edge outgoingLink = (Edge) it.next();
			if (VcVisualIDRegistry.getVisualID(outgoingLink) == Relation1EditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (VcVisualIDRegistry.getVisualID(outgoingLink) == Relation2EditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (VcVisualIDRegistry.getVisualID(outgoingLink) == Relation3EditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
		}
		EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if (annotation == null) {
			// there are indirectly referenced children, need extra commands: false
			addDestroyShortcutsCommand(cmd, view);
			// delete host element
			cmd.add(new DestroyElementCommand(req));
		} else {
			cmd.add(new DeleteCommand(getEditingDomain(), view));
		}
		return getGEFWrapper(cmd.reduce());
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
		if (VcElementTypes.Relation1_4001 == req.getElementType()) {
			return getGEFWrapper(new Relation1CreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (VcElementTypes.Relation2_4002 == req.getElementType()) {
			return getGEFWrapper(new Relation2CreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (VcElementTypes.Relation3_4003 == req.getElementType()) {
			return getGEFWrapper(new Relation3CreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (VcElementTypes.Relation1_4001 == req.getElementType()) {
			return getGEFWrapper(new Relation1CreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (VcElementTypes.Relation2_4002 == req.getElementType()) {
			return getGEFWrapper(new Relation2CreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (VcElementTypes.Relation3_4003 == req.getElementType()) {
			return getGEFWrapper(new Relation3CreateCommand(req,
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
		case Relation1EditPart.VISUAL_ID:
			return getGEFWrapper(new Relation1ReorientCommand(req));
		case Relation2EditPart.VISUAL_ID:
			return getGEFWrapper(new Relation2ReorientCommand(req));
		case Relation3EditPart.VISUAL_ID:
			return getGEFWrapper(new Relation3ReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}

}

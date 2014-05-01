package oepc.diagram.edit.policies;

import java.util.Iterator;

import oepc.diagram.edit.commands.ControlFlowEdgeCreateCommand;
import oepc.diagram.edit.commands.ControlFlowEdgeReorientCommand;
import oepc.diagram.edit.commands.InformationEdgeCreateCommand;
import oepc.diagram.edit.commands.InformationEdgeReorientCommand;
import oepc.diagram.edit.parts.BusinessAttributeEditPart;
import oepc.diagram.edit.parts.BusinessMethodEditPart;
import oepc.diagram.edit.parts.BusinessObjectBusinessObjectAttributeCompartmentEditPart;
import oepc.diagram.edit.parts.BusinessObjectBusinessObjectMethodCompartmentEditPart;
import oepc.diagram.edit.parts.ControlFlowEdgeEditPart;
import oepc.diagram.edit.parts.InformationEdgeEditPart;
import oepc.diagram.part.OepcVisualIDRegistry;
import oepc.diagram.providers.OepcElementTypes;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class BusinessObjectItemSemanticEditPolicy extends
		OepcBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		CompoundCommand cc = getDestroyEdgesCommand();
		addDestroyChildNodesCommand(cc);
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
	protected void addDestroyChildNodesCommand(CompoundCommand cmd) {
		View view = (View) getHost().getModel();
		EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if (annotation != null) {
			return;
		}
		for (Iterator it = view.getChildren().iterator(); it.hasNext();) {
			Node node = (Node) it.next();
			switch (OepcVisualIDRegistry.getVisualID(node)) {
			case BusinessObjectBusinessObjectAttributeCompartmentEditPart.VISUAL_ID:
				for (Iterator cit = node.getChildren().iterator(); cit
						.hasNext();) {
					Node cnode = (Node) cit.next();
					switch (OepcVisualIDRegistry.getVisualID(cnode)) {
					case BusinessAttributeEditPart.VISUAL_ID:
						cmd.add(getDestroyElementCommand(cnode));
						break;
					}
				}
				break;
			case BusinessObjectBusinessObjectMethodCompartmentEditPart.VISUAL_ID:
				for (Iterator cit = node.getChildren().iterator(); cit
						.hasNext();) {
					Node cnode = (Node) cit.next();
					switch (OepcVisualIDRegistry.getVisualID(cnode)) {
					case BusinessMethodEditPart.VISUAL_ID:
						cmd.add(getDestroyElementCommand(cnode));
						break;
					}
				}
				break;
			}
		}
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

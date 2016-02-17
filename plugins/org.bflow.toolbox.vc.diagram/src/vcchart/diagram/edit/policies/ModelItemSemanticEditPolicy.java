/*
 * 
 */
package vcchart.diagram.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

import vcchart.diagram.edit.commands.Activity1CreateCommand;
import vcchart.diagram.edit.commands.Activity2CreateCommand;
import vcchart.diagram.edit.commands.ApplicationCreateCommand;
import vcchart.diagram.edit.commands.ClusterCreateCommand;
import vcchart.diagram.edit.commands.DocumentCreateCommand;
import vcchart.diagram.edit.commands.ObjectiveCreateCommand;
import vcchart.diagram.edit.commands.ParticipantCreateCommand;
import vcchart.diagram.edit.commands.ProductCreateCommand;
import vcchart.diagram.edit.commands.TechnicalTermCreateCommand;
import vcchart.diagram.providers.VcElementTypes;

/**
 * @generated
 */
public class ModelItemSemanticEditPolicy extends VcBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public ModelItemSemanticEditPolicy() {
		super(VcElementTypes.Model_1000);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (VcElementTypes.Product_2001 == req.getElementType()) {
			return getGEFWrapper(new ProductCreateCommand(req));
		}
		if (VcElementTypes.Objective_2002 == req.getElementType()) {
			return getGEFWrapper(new ObjectiveCreateCommand(req));
		}
		if (VcElementTypes.Activity1_2003 == req.getElementType()) {
			return getGEFWrapper(new Activity1CreateCommand(req));
		}
		if (VcElementTypes.Activity2_2004 == req.getElementType()) {
			return getGEFWrapper(new Activity2CreateCommand(req));
		}
		if (VcElementTypes.Cluster_2005 == req.getElementType()) {
			return getGEFWrapper(new ClusterCreateCommand(req));
		}
		if (VcElementTypes.TechnicalTerm_2006 == req.getElementType()) {
			return getGEFWrapper(new TechnicalTermCreateCommand(req));
		}
		if (VcElementTypes.Participant_2007 == req.getElementType()) {
			return getGEFWrapper(new ParticipantCreateCommand(req));
		}
		if (VcElementTypes.Application_2008 == req.getElementType()) {
			return getGEFWrapper(new ApplicationCreateCommand(req));
		}
		if (VcElementTypes.Document_2009 == req.getElementType()) {
			return getGEFWrapper(new DocumentCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
				.getEditingDomain();
		return getGEFWrapper(new DuplicateAnythingCommand(editingDomain, req));
	}

	/**
	 * @generated
	 */
	private static class DuplicateAnythingCommand extends
			DuplicateEObjectsCommand {

		/**
		 * @generated
		 */
		public DuplicateAnythingCommand(
				TransactionalEditingDomain editingDomain,
				DuplicateElementsRequest req) {
			super(editingDomain, req.getLabel(), req
					.getElementsToBeDuplicated(), req
					.getAllDuplicatedElementsMap());
		}

	}

}

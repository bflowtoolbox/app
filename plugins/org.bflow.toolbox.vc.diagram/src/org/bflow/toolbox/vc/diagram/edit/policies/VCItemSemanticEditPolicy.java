package org.bflow.toolbox.vc.diagram.edit.policies;

import org.bflow.toolbox.vc.VcPackage;
import org.bflow.toolbox.vc.diagram.edit.commands.ClusterCreateCommand;
import org.bflow.toolbox.vc.diagram.edit.commands.ObjectiveCreateCommand;
import org.bflow.toolbox.vc.diagram.edit.commands.ProductCreateCommand;
import org.bflow.toolbox.vc.diagram.edit.commands.TechnicalTermCreateCommand;
import org.bflow.toolbox.vc.diagram.edit.commands.ValueChain2CreateCommand;
import org.bflow.toolbox.vc.diagram.edit.commands.ValueChainCreateCommand;
import org.bflow.toolbox.vc.diagram.providers.VcElementTypes;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

/**
 * @generated
 */
public class VCItemSemanticEditPolicy extends VcBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (VcElementTypes.ValueChain_2001 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(VcPackage.eINSTANCE.getVc_Elements());
			}
			return getGEFWrapper(new ValueChainCreateCommand(req));
		}
		if (VcElementTypes.ValueChain2_2002 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(VcPackage.eINSTANCE.getVc_Elements());
			}
			return getGEFWrapper(new ValueChain2CreateCommand(req));
		}
		if (VcElementTypes.TechnicalTerm_2003 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(VcPackage.eINSTANCE.getVc_Elements());
			}
			return getGEFWrapper(new TechnicalTermCreateCommand(req));
		}
		if (VcElementTypes.Cluster_2004 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(VcPackage.eINSTANCE.getVc_Elements());
			}
			return getGEFWrapper(new ClusterCreateCommand(req));
		}
		if (VcElementTypes.Objective_2005 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(VcPackage.eINSTANCE.getVc_Elements());
			}
			return getGEFWrapper(new ObjectiveCreateCommand(req));
		}
		if (VcElementTypes.Product_2006 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(VcPackage.eINSTANCE.getVc_Elements());
			}
			return getGEFWrapper(new ProductCreateCommand(req));
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

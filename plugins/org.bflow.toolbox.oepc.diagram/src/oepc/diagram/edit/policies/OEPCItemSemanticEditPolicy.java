package oepc.diagram.edit.policies;

import oepc.OepcPackage;
import oepc.diagram.edit.commands.ANDConnectorCreateCommand;
import oepc.diagram.edit.commands.BusinessObjectCreateCommand;
import oepc.diagram.edit.commands.DocumentCreateCommand;
import oepc.diagram.edit.commands.EventCreateCommand;
import oepc.diagram.edit.commands.ITSystemCreateCommand;
import oepc.diagram.edit.commands.ORConnectorCreateCommand;
import oepc.diagram.edit.commands.OrganisationUnitCreateCommand;
import oepc.diagram.edit.commands.XORConnectorCreateCommand;
import oepc.diagram.providers.OepcElementTypes;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

/**
 * @generated
 */
public class OEPCItemSemanticEditPolicy extends OepcBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (OepcElementTypes.Event_2001 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(OepcPackage.eINSTANCE
						.getOEPC_Elements());
			}
			return getGEFWrapper(new EventCreateCommand(req));
		}
		if (OepcElementTypes.ITSystem_2002 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(OepcPackage.eINSTANCE
						.getOEPC_Elements());
			}
			return getGEFWrapper(new ITSystemCreateCommand(req));
		}
		if (OepcElementTypes.OrganisationUnit_2003 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(OepcPackage.eINSTANCE
						.getOEPC_Elements());
			}
			return getGEFWrapper(new OrganisationUnitCreateCommand(req));
		}
		if (OepcElementTypes.XORConnector_2004 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(OepcPackage.eINSTANCE
						.getOEPC_Elements());
			}
			return getGEFWrapper(new XORConnectorCreateCommand(req));
		}
		if (OepcElementTypes.BusinessObject_2005 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(OepcPackage.eINSTANCE
						.getOEPC_Elements());
			}
			return getGEFWrapper(new BusinessObjectCreateCommand(req));
		}
		if (OepcElementTypes.ANDConnector_2006 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(OepcPackage.eINSTANCE
						.getOEPC_Elements());
			}
			return getGEFWrapper(new ANDConnectorCreateCommand(req));
		}
		if (OepcElementTypes.ORConnector_2007 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(OepcPackage.eINSTANCE
						.getOEPC_Elements());
			}
			return getGEFWrapper(new ORConnectorCreateCommand(req));
		}
		if (OepcElementTypes.Document_2008 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(OepcPackage.eINSTANCE
						.getOEPC_Elements());
			}
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

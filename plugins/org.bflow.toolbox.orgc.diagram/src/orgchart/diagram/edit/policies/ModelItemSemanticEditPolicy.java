/*
 * 
 */
package orgchart.diagram.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;
import orgchart.diagram.edit.commands.ExternalPersonCreateCommand;
import orgchart.diagram.edit.commands.GroupCreateCommand;
import orgchart.diagram.edit.commands.InternalPersonCreateCommand;
import orgchart.diagram.edit.commands.LocationCreateCommand;
import orgchart.diagram.edit.commands.ParticipantCreateCommand;
import orgchart.diagram.edit.commands.PersonTypeCreateCommand;
import orgchart.diagram.edit.commands.PositionCreateCommand;
import orgchart.diagram.providers.OrgcElementTypes;

/**
 * @generated
 */
public class ModelItemSemanticEditPolicy extends OrgcBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public ModelItemSemanticEditPolicy() {
		super(OrgcElementTypes.Model_1000);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (OrgcElementTypes.Position_2001 == req.getElementType()) {
			return getGEFWrapper(new PositionCreateCommand(req));
		}
		if (OrgcElementTypes.InternalPerson_2002 == req.getElementType()) {
			return getGEFWrapper(new InternalPersonCreateCommand(req));
		}
		if (OrgcElementTypes.ExternalPerson_2003 == req.getElementType()) {
			return getGEFWrapper(new ExternalPersonCreateCommand(req));
		}
		if (OrgcElementTypes.Group_2004 == req.getElementType()) {
			return getGEFWrapper(new GroupCreateCommand(req));
		}
		if (OrgcElementTypes.Participant_2005 == req.getElementType()) {
			return getGEFWrapper(new ParticipantCreateCommand(req));
		}
		if (OrgcElementTypes.PersonType_2006 == req.getElementType()) {
			return getGEFWrapper(new PersonTypeCreateCommand(req));
		}
		if (OrgcElementTypes.Location_2007 == req.getElementType()) {
			return getGEFWrapper(new LocationCreateCommand(req));
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

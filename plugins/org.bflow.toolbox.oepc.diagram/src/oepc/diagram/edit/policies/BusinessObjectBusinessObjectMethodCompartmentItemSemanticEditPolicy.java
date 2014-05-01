package oepc.diagram.edit.policies;

import oepc.OepcPackage;
import oepc.diagram.edit.commands.BusinessMethodCreateCommand;
import oepc.diagram.providers.OepcElementTypes;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

/**
 * @generated
 */
public class BusinessObjectBusinessObjectMethodCompartmentItemSemanticEditPolicy
		extends OepcBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (OepcElementTypes.BusinessMethod_3002 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(OepcPackage.eINSTANCE
						.getBusinessObject_Methods());
			}
			return getGEFWrapper(new BusinessMethodCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}

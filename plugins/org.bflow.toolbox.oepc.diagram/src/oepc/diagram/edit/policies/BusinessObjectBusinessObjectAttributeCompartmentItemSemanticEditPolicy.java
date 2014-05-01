package oepc.diagram.edit.policies;

import oepc.OepcPackage;
import oepc.diagram.edit.commands.BusinessAttributeCreateCommand;
import oepc.diagram.providers.OepcElementTypes;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

/**
 * @generated
 */
public class BusinessObjectBusinessObjectAttributeCompartmentItemSemanticEditPolicy
		extends OepcBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (OepcElementTypes.BusinessAttribute_3001 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(OepcPackage.eINSTANCE
						.getBusinessObject_Attributes());
			}
			return getGEFWrapper(new BusinessAttributeCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}

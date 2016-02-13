/*
 * 
 */
package orgchart.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import orgchart.diagram.providers.OrgcElementTypes;
import orgchart.diagram.providers.OrgcModelingAssistantProvider;

/**
 * @generated
 */
public class OrgcModelingAssistantProviderOfModelEditPart extends
		OrgcModelingAssistantProvider {

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getTypesForPopupBar(IAdaptable host) {
		List<IElementType> types = new ArrayList<IElementType>(7);
		types.add(OrgcElementTypes.Position_2001);
		types.add(OrgcElementTypes.InternalPerson_2002);
		types.add(OrgcElementTypes.ExternalPerson_2003);
		types.add(OrgcElementTypes.Group_2004);
		types.add(OrgcElementTypes.Participant_2005);
		types.add(OrgcElementTypes.PersonType_2006);
		types.add(OrgcElementTypes.Location_2007);
		return types;
	}

}

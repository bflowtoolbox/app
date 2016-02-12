/*
 * 
 */
package vcchart.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import vcchart.diagram.providers.VcElementTypes;
import vcchart.diagram.providers.VcModelingAssistantProvider;

/**
 * @generated
 */
public class VcModelingAssistantProviderOfModelEditPart extends
		VcModelingAssistantProvider {

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getTypesForPopupBar(IAdaptable host) {
		List<IElementType> types = new ArrayList<IElementType>(9);
		types.add(VcElementTypes.Product_2001);
		types.add(VcElementTypes.Objective_2002);
		types.add(VcElementTypes.Activity1_2003);
		types.add(VcElementTypes.Activity2_2004);
		types.add(VcElementTypes.Cluster_2005);
		types.add(VcElementTypes.TechnicalTerm_2006);
		types.add(VcElementTypes.Participant_2007);
		types.add(VcElementTypes.Application_2008);
		types.add(VcElementTypes.Document_2009);
		return types;
	}

}

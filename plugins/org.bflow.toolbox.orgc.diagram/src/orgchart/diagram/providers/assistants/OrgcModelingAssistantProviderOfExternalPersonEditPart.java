/*
 * 
 */
package orgchart.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import orgchart.diagram.edit.parts.ExternalPersonEditPart;
import orgchart.diagram.edit.parts.GroupEditPart;
import orgchart.diagram.edit.parts.InternalPersonEditPart;
import orgchart.diagram.edit.parts.PersonTypeEditPart;
import orgchart.diagram.edit.parts.PositionEditPart;
import orgchart.diagram.providers.OrgcElementTypes;
import orgchart.diagram.providers.OrgcModelingAssistantProvider;

/**
 * @generated
 */
public class OrgcModelingAssistantProviderOfExternalPersonEditPart extends
		OrgcModelingAssistantProvider {

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getRelTypesOnSource(IAdaptable source) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnSource((ExternalPersonEditPart) sourceEditPart);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetRelTypesOnSource(
			ExternalPersonEditPart source) {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(OrgcElementTypes.Relation2_4002);
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getRelTypesOnSourceAndTarget(IAdaptable source,
			IAdaptable target) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnSourceAndTarget(
				(ExternalPersonEditPart) sourceEditPart, targetEditPart);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetRelTypesOnSourceAndTarget(
			ExternalPersonEditPart source, IGraphicalEditPart targetEditPart) {
		List<IElementType> types = new LinkedList<IElementType>();
		if (targetEditPart instanceof PositionEditPart) {
			types.add(OrgcElementTypes.Relation2_4002);
		}
		if (targetEditPart instanceof InternalPersonEditPart) {
			types.add(OrgcElementTypes.Relation2_4002);
		}
		if (targetEditPart instanceof ExternalPersonEditPart) {
			types.add(OrgcElementTypes.Relation2_4002);
		}
		if (targetEditPart instanceof GroupEditPart) {
			types.add(OrgcElementTypes.Relation2_4002);
		}
		if (targetEditPart instanceof PersonTypeEditPart) {
			types.add(OrgcElementTypes.Relation2_4002);
		}
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getTypesForTarget(IAdaptable source,
			IElementType relationshipType) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		return doGetTypesForTarget((ExternalPersonEditPart) sourceEditPart,
				relationshipType);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetTypesForTarget(
			ExternalPersonEditPart source, IElementType relationshipType) {
		List<IElementType> types = new ArrayList<IElementType>();
		if (relationshipType == OrgcElementTypes.Relation2_4002) {
			types.add(OrgcElementTypes.Position_2001);
			types.add(OrgcElementTypes.InternalPerson_2002);
			types.add(OrgcElementTypes.ExternalPerson_2003);
			types.add(OrgcElementTypes.Group_2004);
			types.add(OrgcElementTypes.PersonType_2006);
		}
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getRelTypesOnTarget(IAdaptable target) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnTarget((ExternalPersonEditPart) targetEditPart);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetRelTypesOnTarget(
			ExternalPersonEditPart target) {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(OrgcElementTypes.Relation2_4002);
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getTypesForSource(IAdaptable target,
			IElementType relationshipType) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target
				.getAdapter(IGraphicalEditPart.class);
		return doGetTypesForSource((ExternalPersonEditPart) targetEditPart,
				relationshipType);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetTypesForSource(
			ExternalPersonEditPart target, IElementType relationshipType) {
		List<IElementType> types = new ArrayList<IElementType>();
		if (relationshipType == OrgcElementTypes.Relation2_4002) {
			types.add(OrgcElementTypes.Position_2001);
			types.add(OrgcElementTypes.InternalPerson_2002);
			types.add(OrgcElementTypes.ExternalPerson_2003);
			types.add(OrgcElementTypes.Group_2004);
			types.add(OrgcElementTypes.PersonType_2006);
		}
		return types;
	}

}

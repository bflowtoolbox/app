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
import orgchart.diagram.edit.parts.GroupEditPart;
import orgchart.diagram.edit.parts.LocationEditPart;
import orgchart.diagram.edit.parts.ParticipantEditPart;
import orgchart.diagram.edit.parts.PositionEditPart;
import orgchart.diagram.providers.OrgcElementTypes;
import orgchart.diagram.providers.OrgcModelingAssistantProvider;

/**
 * @generated
 */
public class OrgcModelingAssistantProviderOfParticipantEditPart extends
		OrgcModelingAssistantProvider {

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getRelTypesOnSource(IAdaptable source) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source
				.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnSource((ParticipantEditPart) sourceEditPart);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetRelTypesOnSource(ParticipantEditPart source) {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(OrgcElementTypes.Relation1_4001);
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
				(ParticipantEditPart) sourceEditPart, targetEditPart);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetRelTypesOnSourceAndTarget(
			ParticipantEditPart source, IGraphicalEditPart targetEditPart) {
		List<IElementType> types = new LinkedList<IElementType>();
		if (targetEditPart instanceof PositionEditPart) {
			types.add(OrgcElementTypes.Relation1_4001);
		}
		if (targetEditPart instanceof GroupEditPart) {
			types.add(OrgcElementTypes.Relation1_4001);
		}
		if (targetEditPart instanceof ParticipantEditPart) {
			types.add(OrgcElementTypes.Relation1_4001);
		}
		if (targetEditPart instanceof LocationEditPart) {
			types.add(OrgcElementTypes.Relation1_4001);
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
		return doGetTypesForTarget((ParticipantEditPart) sourceEditPart,
				relationshipType);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetTypesForTarget(ParticipantEditPart source,
			IElementType relationshipType) {
		List<IElementType> types = new ArrayList<IElementType>();
		if (relationshipType == OrgcElementTypes.Relation1_4001) {
			types.add(OrgcElementTypes.Position_2001);
			types.add(OrgcElementTypes.Group_2004);
			types.add(OrgcElementTypes.Participant_2005);
			types.add(OrgcElementTypes.Location_2007);
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
		return doGetRelTypesOnTarget((ParticipantEditPart) targetEditPart);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetRelTypesOnTarget(ParticipantEditPart target) {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(OrgcElementTypes.Relation1_4001);
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
		return doGetTypesForSource((ParticipantEditPart) targetEditPart,
				relationshipType);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetTypesForSource(ParticipantEditPart target,
			IElementType relationshipType) {
		List<IElementType> types = new ArrayList<IElementType>();
		if (relationshipType == OrgcElementTypes.Relation1_4001) {
			types.add(OrgcElementTypes.Position_2001);
			types.add(OrgcElementTypes.Group_2004);
			types.add(OrgcElementTypes.Participant_2005);
			types.add(OrgcElementTypes.Location_2007);
		}
		return types;
	}

}

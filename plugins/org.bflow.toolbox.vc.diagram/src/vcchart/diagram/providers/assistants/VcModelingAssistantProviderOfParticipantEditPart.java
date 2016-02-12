/*
 * 
 */
package vcchart.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import vcchart.diagram.edit.parts.Activity1EditPart;
import vcchart.diagram.edit.parts.Activity2EditPart;
import vcchart.diagram.edit.parts.ApplicationEditPart;
import vcchart.diagram.edit.parts.ClusterEditPart;
import vcchart.diagram.edit.parts.DocumentEditPart;
import vcchart.diagram.edit.parts.ObjectiveEditPart;
import vcchart.diagram.edit.parts.ParticipantEditPart;
import vcchart.diagram.edit.parts.ProductEditPart;
import vcchart.diagram.edit.parts.TechnicalTermEditPart;
import vcchart.diagram.providers.VcElementTypes;
import vcchart.diagram.providers.VcModelingAssistantProvider;

/**
 * @generated
 */
public class VcModelingAssistantProviderOfParticipantEditPart extends
		VcModelingAssistantProvider {

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
		types.add(VcElementTypes.Relation3_4003);
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
		if (targetEditPart instanceof ProductEditPart) {
			types.add(VcElementTypes.Relation3_4003);
		}
		if (targetEditPart instanceof ObjectiveEditPart) {
			types.add(VcElementTypes.Relation3_4003);
		}
		if (targetEditPart instanceof Activity1EditPart) {
			types.add(VcElementTypes.Relation3_4003);
		}
		if (targetEditPart instanceof Activity2EditPart) {
			types.add(VcElementTypes.Relation3_4003);
		}
		if (targetEditPart instanceof ClusterEditPart) {
			types.add(VcElementTypes.Relation3_4003);
		}
		if (targetEditPart instanceof TechnicalTermEditPart) {
			types.add(VcElementTypes.Relation3_4003);
		}
		if (targetEditPart instanceof ParticipantEditPart) {
			types.add(VcElementTypes.Relation3_4003);
		}
		if (targetEditPart instanceof ApplicationEditPart) {
			types.add(VcElementTypes.Relation3_4003);
		}
		if (targetEditPart instanceof DocumentEditPart) {
			types.add(VcElementTypes.Relation3_4003);
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
		if (relationshipType == VcElementTypes.Relation3_4003) {
			types.add(VcElementTypes.Product_2001);
			types.add(VcElementTypes.Objective_2002);
			types.add(VcElementTypes.Activity1_2003);
			types.add(VcElementTypes.Activity2_2004);
			types.add(VcElementTypes.Cluster_2005);
			types.add(VcElementTypes.TechnicalTerm_2006);
			types.add(VcElementTypes.Participant_2007);
			types.add(VcElementTypes.Application_2008);
			types.add(VcElementTypes.Document_2009);
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
		types.add(VcElementTypes.Relation3_4003);
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
		if (relationshipType == VcElementTypes.Relation3_4003) {
			types.add(VcElementTypes.Product_2001);
			types.add(VcElementTypes.Objective_2002);
			types.add(VcElementTypes.Activity1_2003);
			types.add(VcElementTypes.Activity2_2004);
			types.add(VcElementTypes.Cluster_2005);
			types.add(VcElementTypes.TechnicalTerm_2006);
			types.add(VcElementTypes.Participant_2007);
			types.add(VcElementTypes.Application_2008);
			types.add(VcElementTypes.Document_2009);
		}
		return types;
	}

}

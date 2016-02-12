/*
 * 
 */
package orgchart.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.update.DiagramUpdater;
import orgchart.All_Rel_1;
import orgchart.All_Rel_2;
import orgchart.ExternalPerson;
import orgchart.Group;
import orgchart.InternalPerson;
import orgchart.Location;
import orgchart.Model;
import orgchart.OrgchartPackage;
import orgchart.Participant;
import orgchart.PersonType;
import orgchart.Position;
import orgchart.Relation1;
import orgchart.Relation2;
import orgchart.diagram.edit.parts.ExternalPersonEditPart;
import orgchart.diagram.edit.parts.GroupEditPart;
import orgchart.diagram.edit.parts.InternalPersonEditPart;
import orgchart.diagram.edit.parts.LocationEditPart;
import orgchart.diagram.edit.parts.ModelEditPart;
import orgchart.diagram.edit.parts.ParticipantEditPart;
import orgchart.diagram.edit.parts.PersonTypeEditPart;
import orgchart.diagram.edit.parts.PositionEditPart;
import orgchart.diagram.edit.parts.Relation1EditPart;
import orgchart.diagram.edit.parts.Relation2EditPart;
import orgchart.diagram.providers.OrgcElementTypes;

/**
 * @generated
 */
public class OrgcDiagramUpdater {

	/**
	 * @generated
	 */
	public static boolean isShortcutOrphaned(View view) {
		return !view.isSetElement() || view.getElement() == null
				|| view.getElement().eIsProxy();
	}

	/**
	 * @generated
	 */
	public static List<OrgcNodeDescriptor> getSemanticChildren(View view) {
		switch (OrgcVisualIDRegistry.getVisualID(view)) {
		case ModelEditPart.VISUAL_ID:
			return getModel_1000SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OrgcNodeDescriptor> getModel_1000SemanticChildren(
			View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		Model modelElement = (Model) view.getElement();
		LinkedList<OrgcNodeDescriptor> result = new LinkedList<OrgcNodeDescriptor>();
		for (Iterator<?> it = modelElement.getPositions().iterator(); it
				.hasNext();) {
			Position childElement = (Position) it.next();
			int visualID = OrgcVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == PositionEditPart.VISUAL_ID) {
				result.add(new OrgcNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getInternalPersons().iterator(); it
				.hasNext();) {
			InternalPerson childElement = (InternalPerson) it.next();
			int visualID = OrgcVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == InternalPersonEditPart.VISUAL_ID) {
				result.add(new OrgcNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getExternalPersons().iterator(); it
				.hasNext();) {
			ExternalPerson childElement = (ExternalPerson) it.next();
			int visualID = OrgcVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ExternalPersonEditPart.VISUAL_ID) {
				result.add(new OrgcNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getGroups().iterator(); it.hasNext();) {
			Group childElement = (Group) it.next();
			int visualID = OrgcVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == GroupEditPart.VISUAL_ID) {
				result.add(new OrgcNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getParticipants().iterator(); it
				.hasNext();) {
			Participant childElement = (Participant) it.next();
			int visualID = OrgcVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ParticipantEditPart.VISUAL_ID) {
				result.add(new OrgcNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getPersonTypes().iterator(); it
				.hasNext();) {
			PersonType childElement = (PersonType) it.next();
			int visualID = OrgcVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == PersonTypeEditPart.VISUAL_ID) {
				result.add(new OrgcNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getLocations().iterator(); it
				.hasNext();) {
			Location childElement = (Location) it.next();
			int visualID = OrgcVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == LocationEditPart.VISUAL_ID) {
				result.add(new OrgcNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getContainedLinks(View view) {
		switch (OrgcVisualIDRegistry.getVisualID(view)) {
		case ModelEditPart.VISUAL_ID:
			return getModel_1000ContainedLinks(view);
		case PositionEditPart.VISUAL_ID:
			return getPosition_2001ContainedLinks(view);
		case InternalPersonEditPart.VISUAL_ID:
			return getInternalPerson_2002ContainedLinks(view);
		case ExternalPersonEditPart.VISUAL_ID:
			return getExternalPerson_2003ContainedLinks(view);
		case GroupEditPart.VISUAL_ID:
			return getGroup_2004ContainedLinks(view);
		case ParticipantEditPart.VISUAL_ID:
			return getParticipant_2005ContainedLinks(view);
		case PersonTypeEditPart.VISUAL_ID:
			return getPersonType_2006ContainedLinks(view);
		case LocationEditPart.VISUAL_ID:
			return getLocation_2007ContainedLinks(view);
		case Relation1EditPart.VISUAL_ID:
			return getRelation1_4001ContainedLinks(view);
		case Relation2EditPart.VISUAL_ID:
			return getRelation2_4002ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getIncomingLinks(View view) {
		switch (OrgcVisualIDRegistry.getVisualID(view)) {
		case PositionEditPart.VISUAL_ID:
			return getPosition_2001IncomingLinks(view);
		case InternalPersonEditPart.VISUAL_ID:
			return getInternalPerson_2002IncomingLinks(view);
		case ExternalPersonEditPart.VISUAL_ID:
			return getExternalPerson_2003IncomingLinks(view);
		case GroupEditPart.VISUAL_ID:
			return getGroup_2004IncomingLinks(view);
		case ParticipantEditPart.VISUAL_ID:
			return getParticipant_2005IncomingLinks(view);
		case PersonTypeEditPart.VISUAL_ID:
			return getPersonType_2006IncomingLinks(view);
		case LocationEditPart.VISUAL_ID:
			return getLocation_2007IncomingLinks(view);
		case Relation1EditPart.VISUAL_ID:
			return getRelation1_4001IncomingLinks(view);
		case Relation2EditPart.VISUAL_ID:
			return getRelation2_4002IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getOutgoingLinks(View view) {
		switch (OrgcVisualIDRegistry.getVisualID(view)) {
		case PositionEditPart.VISUAL_ID:
			return getPosition_2001OutgoingLinks(view);
		case InternalPersonEditPart.VISUAL_ID:
			return getInternalPerson_2002OutgoingLinks(view);
		case ExternalPersonEditPart.VISUAL_ID:
			return getExternalPerson_2003OutgoingLinks(view);
		case GroupEditPart.VISUAL_ID:
			return getGroup_2004OutgoingLinks(view);
		case ParticipantEditPart.VISUAL_ID:
			return getParticipant_2005OutgoingLinks(view);
		case PersonTypeEditPart.VISUAL_ID:
			return getPersonType_2006OutgoingLinks(view);
		case LocationEditPart.VISUAL_ID:
			return getLocation_2007OutgoingLinks(view);
		case Relation1EditPart.VISUAL_ID:
			return getRelation1_4001OutgoingLinks(view);
		case Relation2EditPart.VISUAL_ID:
			return getRelation2_4002OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getModel_1000ContainedLinks(View view) {
		Model modelElement = (Model) view.getElement();
		LinkedList<OrgcLinkDescriptor> result = new LinkedList<OrgcLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Relation1_4001(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_Relation2_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getPosition_2001ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getInternalPerson_2002ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getExternalPerson_2003ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getGroup_2004ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getParticipant_2005ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getPersonType_2006ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getLocation_2007ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getRelation1_4001ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getRelation2_4002ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getPosition_2001IncomingLinks(
			View view) {
		Position modelElement = (Position) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<OrgcLinkDescriptor> result = new LinkedList<OrgcLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Relation1_4001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation2_4002(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getInternalPerson_2002IncomingLinks(
			View view) {
		InternalPerson modelElement = (InternalPerson) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<OrgcLinkDescriptor> result = new LinkedList<OrgcLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Relation2_4002(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getExternalPerson_2003IncomingLinks(
			View view) {
		ExternalPerson modelElement = (ExternalPerson) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<OrgcLinkDescriptor> result = new LinkedList<OrgcLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Relation2_4002(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getGroup_2004IncomingLinks(View view) {
		Group modelElement = (Group) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<OrgcLinkDescriptor> result = new LinkedList<OrgcLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Relation1_4001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation2_4002(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getParticipant_2005IncomingLinks(
			View view) {
		Participant modelElement = (Participant) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<OrgcLinkDescriptor> result = new LinkedList<OrgcLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Relation1_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getPersonType_2006IncomingLinks(
			View view) {
		PersonType modelElement = (PersonType) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<OrgcLinkDescriptor> result = new LinkedList<OrgcLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Relation2_4002(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getLocation_2007IncomingLinks(
			View view) {
		Location modelElement = (Location) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<OrgcLinkDescriptor> result = new LinkedList<OrgcLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Relation1_4001(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getRelation1_4001IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getRelation2_4002IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getPosition_2001OutgoingLinks(
			View view) {
		Position modelElement = (Position) view.getElement();
		LinkedList<OrgcLinkDescriptor> result = new LinkedList<OrgcLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Relation1_4001(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_Relation2_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getInternalPerson_2002OutgoingLinks(
			View view) {
		InternalPerson modelElement = (InternalPerson) view.getElement();
		LinkedList<OrgcLinkDescriptor> result = new LinkedList<OrgcLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Relation2_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getExternalPerson_2003OutgoingLinks(
			View view) {
		ExternalPerson modelElement = (ExternalPerson) view.getElement();
		LinkedList<OrgcLinkDescriptor> result = new LinkedList<OrgcLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Relation2_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getGroup_2004OutgoingLinks(View view) {
		Group modelElement = (Group) view.getElement();
		LinkedList<OrgcLinkDescriptor> result = new LinkedList<OrgcLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Relation1_4001(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_Relation2_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getParticipant_2005OutgoingLinks(
			View view) {
		Participant modelElement = (Participant) view.getElement();
		LinkedList<OrgcLinkDescriptor> result = new LinkedList<OrgcLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Relation1_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getPersonType_2006OutgoingLinks(
			View view) {
		PersonType modelElement = (PersonType) view.getElement();
		LinkedList<OrgcLinkDescriptor> result = new LinkedList<OrgcLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Relation2_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getLocation_2007OutgoingLinks(
			View view) {
		Location modelElement = (Location) view.getElement();
		LinkedList<OrgcLinkDescriptor> result = new LinkedList<OrgcLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Relation1_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getRelation1_4001OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<OrgcLinkDescriptor> getRelation2_4002OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	private static Collection<OrgcLinkDescriptor> getContainedTypeModelFacetLinks_Relation1_4001(
			Model container) {
		LinkedList<OrgcLinkDescriptor> result = new LinkedList<OrgcLinkDescriptor>();
		for (Iterator<?> links = container.getRelations1().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Relation1) {
				continue;
			}
			Relation1 link = (Relation1) linkObject;
			if (Relation1EditPart.VISUAL_ID != OrgcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			All_Rel_1 dst = link.getTarget();
			All_Rel_1 src = link.getSource();
			result.add(new OrgcLinkDescriptor(src, dst, link,
					OrgcElementTypes.Relation1_4001,
					Relation1EditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<OrgcLinkDescriptor> getContainedTypeModelFacetLinks_Relation2_4002(
			Model container) {
		LinkedList<OrgcLinkDescriptor> result = new LinkedList<OrgcLinkDescriptor>();
		for (Iterator<?> links = container.getRelations2().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Relation2) {
				continue;
			}
			Relation2 link = (Relation2) linkObject;
			if (Relation2EditPart.VISUAL_ID != OrgcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			All_Rel_2 dst = link.getTarget();
			All_Rel_2 src = link.getSource();
			result.add(new OrgcLinkDescriptor(src, dst, link,
					OrgcElementTypes.Relation2_4002,
					Relation2EditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<OrgcLinkDescriptor> getIncomingTypeModelFacetLinks_Relation1_4001(
			All_Rel_1 target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<OrgcLinkDescriptor> result = new LinkedList<OrgcLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != OrgchartPackage.eINSTANCE
					.getRelation1_Target()
					|| false == setting.getEObject() instanceof Relation1) {
				continue;
			}
			Relation1 link = (Relation1) setting.getEObject();
			if (Relation1EditPart.VISUAL_ID != OrgcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			All_Rel_1 src = link.getSource();
			result.add(new OrgcLinkDescriptor(src, target, link,
					OrgcElementTypes.Relation1_4001,
					Relation1EditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<OrgcLinkDescriptor> getIncomingTypeModelFacetLinks_Relation2_4002(
			All_Rel_2 target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<OrgcLinkDescriptor> result = new LinkedList<OrgcLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != OrgchartPackage.eINSTANCE
					.getRelation2_Target()
					|| false == setting.getEObject() instanceof Relation2) {
				continue;
			}
			Relation2 link = (Relation2) setting.getEObject();
			if (Relation2EditPart.VISUAL_ID != OrgcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			All_Rel_2 src = link.getSource();
			result.add(new OrgcLinkDescriptor(src, target, link,
					OrgcElementTypes.Relation2_4002,
					Relation2EditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<OrgcLinkDescriptor> getOutgoingTypeModelFacetLinks_Relation1_4001(
			All_Rel_1 source) {
		Model container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Model) {
				container = (Model) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<OrgcLinkDescriptor> result = new LinkedList<OrgcLinkDescriptor>();
		for (Iterator<?> links = container.getRelations1().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Relation1) {
				continue;
			}
			Relation1 link = (Relation1) linkObject;
			if (Relation1EditPart.VISUAL_ID != OrgcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			All_Rel_1 dst = link.getTarget();
			All_Rel_1 src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new OrgcLinkDescriptor(src, dst, link,
					OrgcElementTypes.Relation1_4001,
					Relation1EditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<OrgcLinkDescriptor> getOutgoingTypeModelFacetLinks_Relation2_4002(
			All_Rel_2 source) {
		Model container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Model) {
				container = (Model) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<OrgcLinkDescriptor> result = new LinkedList<OrgcLinkDescriptor>();
		for (Iterator<?> links = container.getRelations2().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Relation2) {
				continue;
			}
			Relation2 link = (Relation2) linkObject;
			if (Relation2EditPart.VISUAL_ID != OrgcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			All_Rel_2 dst = link.getTarget();
			All_Rel_2 src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new OrgcLinkDescriptor(src, dst, link,
					OrgcElementTypes.Relation2_4002,
					Relation2EditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static final DiagramUpdater TYPED_INSTANCE = new DiagramUpdater() {
		/**
		 * @generated
		 */
		@Override
		public List<OrgcNodeDescriptor> getSemanticChildren(View view) {
			return OrgcDiagramUpdater.getSemanticChildren(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<OrgcLinkDescriptor> getContainedLinks(View view) {
			return OrgcDiagramUpdater.getContainedLinks(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<OrgcLinkDescriptor> getIncomingLinks(View view) {
			return OrgcDiagramUpdater.getIncomingLinks(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<OrgcLinkDescriptor> getOutgoingLinks(View view) {
			return OrgcDiagramUpdater.getOutgoingLinks(view);
		}
	};

}

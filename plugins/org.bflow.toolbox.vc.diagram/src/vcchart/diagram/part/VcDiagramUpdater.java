/*
 * 
 */
package vcchart.diagram.part;

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

import vcchart.Activity1;
import vcchart.Activity2;
import vcchart.All_Rel_1;
import vcchart.All_Rel_3;
import vcchart.Application;
import vcchart.Cluster;
import vcchart.Document;
import vcchart.Model;
import vcchart.Objective;
import vcchart.Participant;
import vcchart.Product;
import vcchart.Relation1;
import vcchart.Relation2;
import vcchart.Relation3;
import vcchart.TechnicalTerm;
import vcchart.VcchartPackage;
import vcchart.diagram.edit.parts.Activity1EditPart;
import vcchart.diagram.edit.parts.Activity2EditPart;
import vcchart.diagram.edit.parts.ApplicationEditPart;
import vcchart.diagram.edit.parts.ClusterEditPart;
import vcchart.diagram.edit.parts.DocumentEditPart;
import vcchart.diagram.edit.parts.ModelEditPart;
import vcchart.diagram.edit.parts.ObjectiveEditPart;
import vcchart.diagram.edit.parts.ParticipantEditPart;
import vcchart.diagram.edit.parts.ProductEditPart;
import vcchart.diagram.edit.parts.Relation1EditPart;
import vcchart.diagram.edit.parts.Relation2EditPart;
import vcchart.diagram.edit.parts.Relation3EditPart;
import vcchart.diagram.edit.parts.TechnicalTermEditPart;
import vcchart.diagram.providers.VcElementTypes;

/**
 * @generated
 */
public class VcDiagramUpdater {

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
	public static List<VcNodeDescriptor> getSemanticChildren(View view) {
		switch (VcVisualIDRegistry.getVisualID(view)) {
		case ModelEditPart.VISUAL_ID:
			return getModel_1000SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<VcNodeDescriptor> getModel_1000SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		Model modelElement = (Model) view.getElement();
		LinkedList<VcNodeDescriptor> result = new LinkedList<VcNodeDescriptor>();
		for (Iterator<?> it = modelElement.getProducts().iterator(); it
				.hasNext();) {
			Product childElement = (Product) it.next();
			int visualID = VcVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ProductEditPart.VISUAL_ID) {
				result.add(new VcNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getObjectives().iterator(); it
				.hasNext();) {
			Objective childElement = (Objective) it.next();
			int visualID = VcVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ObjectiveEditPart.VISUAL_ID) {
				result.add(new VcNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getActivitys1().iterator(); it
				.hasNext();) {
			Activity1 childElement = (Activity1) it.next();
			int visualID = VcVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == Activity1EditPart.VISUAL_ID) {
				result.add(new VcNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getActivitys2().iterator(); it
				.hasNext();) {
			Activity2 childElement = (Activity2) it.next();
			int visualID = VcVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == Activity2EditPart.VISUAL_ID) {
				result.add(new VcNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getClusters().iterator(); it
				.hasNext();) {
			Cluster childElement = (Cluster) it.next();
			int visualID = VcVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ClusterEditPart.VISUAL_ID) {
				result.add(new VcNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getTechnicalTerms().iterator(); it
				.hasNext();) {
			TechnicalTerm childElement = (TechnicalTerm) it.next();
			int visualID = VcVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == TechnicalTermEditPart.VISUAL_ID) {
				result.add(new VcNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getParticipants().iterator(); it
				.hasNext();) {
			Participant childElement = (Participant) it.next();
			int visualID = VcVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ParticipantEditPart.VISUAL_ID) {
				result.add(new VcNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getApplications().iterator(); it
				.hasNext();) {
			Application childElement = (Application) it.next();
			int visualID = VcVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ApplicationEditPart.VISUAL_ID) {
				result.add(new VcNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getDocuments().iterator(); it
				.hasNext();) {
			Document childElement = (Document) it.next();
			int visualID = VcVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == DocumentEditPart.VISUAL_ID) {
				result.add(new VcNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getContainedLinks(View view) {
		switch (VcVisualIDRegistry.getVisualID(view)) {
		case ModelEditPart.VISUAL_ID:
			return getModel_1000ContainedLinks(view);
		case ProductEditPart.VISUAL_ID:
			return getProduct_2001ContainedLinks(view);
		case ObjectiveEditPart.VISUAL_ID:
			return getObjective_2002ContainedLinks(view);
		case Activity1EditPart.VISUAL_ID:
			return getActivity1_2003ContainedLinks(view);
		case Activity2EditPart.VISUAL_ID:
			return getActivity2_2004ContainedLinks(view);
		case ClusterEditPart.VISUAL_ID:
			return getCluster_2005ContainedLinks(view);
		case TechnicalTermEditPart.VISUAL_ID:
			return getTechnicalTerm_2006ContainedLinks(view);
		case ParticipantEditPart.VISUAL_ID:
			return getParticipant_2007ContainedLinks(view);
		case ApplicationEditPart.VISUAL_ID:
			return getApplication_2008ContainedLinks(view);
		case DocumentEditPart.VISUAL_ID:
			return getDocument_2009ContainedLinks(view);
		case Relation1EditPart.VISUAL_ID:
			return getRelation1_4001ContainedLinks(view);
		case Relation2EditPart.VISUAL_ID:
			return getRelation2_4002ContainedLinks(view);
		case Relation3EditPart.VISUAL_ID:
			return getRelation3_4003ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getIncomingLinks(View view) {
		switch (VcVisualIDRegistry.getVisualID(view)) {
		case ProductEditPart.VISUAL_ID:
			return getProduct_2001IncomingLinks(view);
		case ObjectiveEditPart.VISUAL_ID:
			return getObjective_2002IncomingLinks(view);
		case Activity1EditPart.VISUAL_ID:
			return getActivity1_2003IncomingLinks(view);
		case Activity2EditPart.VISUAL_ID:
			return getActivity2_2004IncomingLinks(view);
		case ClusterEditPart.VISUAL_ID:
			return getCluster_2005IncomingLinks(view);
		case TechnicalTermEditPart.VISUAL_ID:
			return getTechnicalTerm_2006IncomingLinks(view);
		case ParticipantEditPart.VISUAL_ID:
			return getParticipant_2007IncomingLinks(view);
		case ApplicationEditPart.VISUAL_ID:
			return getApplication_2008IncomingLinks(view);
		case DocumentEditPart.VISUAL_ID:
			return getDocument_2009IncomingLinks(view);
		case Relation1EditPart.VISUAL_ID:
			return getRelation1_4001IncomingLinks(view);
		case Relation2EditPart.VISUAL_ID:
			return getRelation2_4002IncomingLinks(view);
		case Relation3EditPart.VISUAL_ID:
			return getRelation3_4003IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getOutgoingLinks(View view) {
		switch (VcVisualIDRegistry.getVisualID(view)) {
		case ProductEditPart.VISUAL_ID:
			return getProduct_2001OutgoingLinks(view);
		case ObjectiveEditPart.VISUAL_ID:
			return getObjective_2002OutgoingLinks(view);
		case Activity1EditPart.VISUAL_ID:
			return getActivity1_2003OutgoingLinks(view);
		case Activity2EditPart.VISUAL_ID:
			return getActivity2_2004OutgoingLinks(view);
		case ClusterEditPart.VISUAL_ID:
			return getCluster_2005OutgoingLinks(view);
		case TechnicalTermEditPart.VISUAL_ID:
			return getTechnicalTerm_2006OutgoingLinks(view);
		case ParticipantEditPart.VISUAL_ID:
			return getParticipant_2007OutgoingLinks(view);
		case ApplicationEditPart.VISUAL_ID:
			return getApplication_2008OutgoingLinks(view);
		case DocumentEditPart.VISUAL_ID:
			return getDocument_2009OutgoingLinks(view);
		case Relation1EditPart.VISUAL_ID:
			return getRelation1_4001OutgoingLinks(view);
		case Relation2EditPart.VISUAL_ID:
			return getRelation2_4002OutgoingLinks(view);
		case Relation3EditPart.VISUAL_ID:
			return getRelation3_4003OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getModel_1000ContainedLinks(View view) {
		Model modelElement = (Model) view.getElement();
		LinkedList<VcLinkDescriptor> result = new LinkedList<VcLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Relation1_4001(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_Relation2_4002(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_Relation3_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getProduct_2001ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getObjective_2002ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getActivity1_2003ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getActivity2_2004ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getCluster_2005ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getTechnicalTerm_2006ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getParticipant_2007ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getApplication_2008ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getDocument_2009ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getRelation1_4001ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getRelation2_4002ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getRelation3_4003ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getProduct_2001IncomingLinks(View view) {
		Product modelElement = (Product) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<VcLinkDescriptor> result = new LinkedList<VcLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Relation1_4001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation2_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation3_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getObjective_2002IncomingLinks(
			View view) {
		Objective modelElement = (Objective) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<VcLinkDescriptor> result = new LinkedList<VcLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Relation1_4001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation2_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation3_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getActivity1_2003IncomingLinks(
			View view) {
		Activity1 modelElement = (Activity1) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<VcLinkDescriptor> result = new LinkedList<VcLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Relation1_4001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation2_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation3_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getActivity2_2004IncomingLinks(
			View view) {
		Activity2 modelElement = (Activity2) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<VcLinkDescriptor> result = new LinkedList<VcLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Relation1_4001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation2_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation3_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getCluster_2005IncomingLinks(View view) {
		Cluster modelElement = (Cluster) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<VcLinkDescriptor> result = new LinkedList<VcLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Relation3_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getTechnicalTerm_2006IncomingLinks(
			View view) {
		TechnicalTerm modelElement = (TechnicalTerm) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<VcLinkDescriptor> result = new LinkedList<VcLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Relation3_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getParticipant_2007IncomingLinks(
			View view) {
		Participant modelElement = (Participant) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<VcLinkDescriptor> result = new LinkedList<VcLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Relation3_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getApplication_2008IncomingLinks(
			View view) {
		Application modelElement = (Application) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<VcLinkDescriptor> result = new LinkedList<VcLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Relation3_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getDocument_2009IncomingLinks(View view) {
		Document modelElement = (Document) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<VcLinkDescriptor> result = new LinkedList<VcLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Relation3_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getRelation1_4001IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getRelation2_4002IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getRelation3_4003IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getProduct_2001OutgoingLinks(View view) {
		Product modelElement = (Product) view.getElement();
		LinkedList<VcLinkDescriptor> result = new LinkedList<VcLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Relation1_4001(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_Relation2_4002(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_Relation3_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getObjective_2002OutgoingLinks(
			View view) {
		Objective modelElement = (Objective) view.getElement();
		LinkedList<VcLinkDescriptor> result = new LinkedList<VcLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Relation1_4001(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_Relation2_4002(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_Relation3_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getActivity1_2003OutgoingLinks(
			View view) {
		Activity1 modelElement = (Activity1) view.getElement();
		LinkedList<VcLinkDescriptor> result = new LinkedList<VcLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Relation1_4001(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_Relation2_4002(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_Relation3_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getActivity2_2004OutgoingLinks(
			View view) {
		Activity2 modelElement = (Activity2) view.getElement();
		LinkedList<VcLinkDescriptor> result = new LinkedList<VcLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Relation1_4001(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_Relation2_4002(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_Relation3_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getCluster_2005OutgoingLinks(View view) {
		Cluster modelElement = (Cluster) view.getElement();
		LinkedList<VcLinkDescriptor> result = new LinkedList<VcLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Relation3_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getTechnicalTerm_2006OutgoingLinks(
			View view) {
		TechnicalTerm modelElement = (TechnicalTerm) view.getElement();
		LinkedList<VcLinkDescriptor> result = new LinkedList<VcLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Relation3_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getParticipant_2007OutgoingLinks(
			View view) {
		Participant modelElement = (Participant) view.getElement();
		LinkedList<VcLinkDescriptor> result = new LinkedList<VcLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Relation3_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getApplication_2008OutgoingLinks(
			View view) {
		Application modelElement = (Application) view.getElement();
		LinkedList<VcLinkDescriptor> result = new LinkedList<VcLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Relation3_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getDocument_2009OutgoingLinks(View view) {
		Document modelElement = (Document) view.getElement();
		LinkedList<VcLinkDescriptor> result = new LinkedList<VcLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Relation3_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getRelation1_4001OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getRelation2_4002OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<VcLinkDescriptor> getRelation3_4003OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	private static Collection<VcLinkDescriptor> getContainedTypeModelFacetLinks_Relation1_4001(
			Model container) {
		LinkedList<VcLinkDescriptor> result = new LinkedList<VcLinkDescriptor>();
		for (Iterator<?> links = container.getRelations1().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Relation1) {
				continue;
			}
			Relation1 link = (Relation1) linkObject;
			if (Relation1EditPart.VISUAL_ID != VcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			All_Rel_1 dst = link.getTarget();
			All_Rel_1 src = link.getSource();
			result.add(new VcLinkDescriptor(src, dst, link,
					VcElementTypes.Relation1_4001, Relation1EditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<VcLinkDescriptor> getContainedTypeModelFacetLinks_Relation2_4002(
			Model container) {
		LinkedList<VcLinkDescriptor> result = new LinkedList<VcLinkDescriptor>();
		for (Iterator<?> links = container.getRelations2().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Relation2) {
				continue;
			}
			Relation2 link = (Relation2) linkObject;
			if (Relation2EditPart.VISUAL_ID != VcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			All_Rel_1 dst = link.getTarget();
			All_Rel_1 src = link.getSource();
			result.add(new VcLinkDescriptor(src, dst, link,
					VcElementTypes.Relation2_4002, Relation2EditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<VcLinkDescriptor> getContainedTypeModelFacetLinks_Relation3_4003(
			Model container) {
		LinkedList<VcLinkDescriptor> result = new LinkedList<VcLinkDescriptor>();
		for (Iterator<?> links = container.getRelations3().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Relation3) {
				continue;
			}
			Relation3 link = (Relation3) linkObject;
			if (Relation3EditPart.VISUAL_ID != VcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			All_Rel_3 dst = link.getTarget();
			All_Rel_3 src = link.getSource();
			result.add(new VcLinkDescriptor(src, dst, link,
					VcElementTypes.Relation3_4003, Relation3EditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<VcLinkDescriptor> getIncomingTypeModelFacetLinks_Relation1_4001(
			All_Rel_1 target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<VcLinkDescriptor> result = new LinkedList<VcLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != VcchartPackage.eINSTANCE
					.getRelation1_Target()
					|| false == setting.getEObject() instanceof Relation1) {
				continue;
			}
			Relation1 link = (Relation1) setting.getEObject();
			if (Relation1EditPart.VISUAL_ID != VcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			All_Rel_1 src = link.getSource();
			result.add(new VcLinkDescriptor(src, target, link,
					VcElementTypes.Relation1_4001, Relation1EditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<VcLinkDescriptor> getIncomingTypeModelFacetLinks_Relation2_4002(
			All_Rel_1 target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<VcLinkDescriptor> result = new LinkedList<VcLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != VcchartPackage.eINSTANCE
					.getRelation2_Target()
					|| false == setting.getEObject() instanceof Relation2) {
				continue;
			}
			Relation2 link = (Relation2) setting.getEObject();
			if (Relation2EditPart.VISUAL_ID != VcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			All_Rel_1 src = link.getSource();
			result.add(new VcLinkDescriptor(src, target, link,
					VcElementTypes.Relation2_4002, Relation2EditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<VcLinkDescriptor> getIncomingTypeModelFacetLinks_Relation3_4003(
			All_Rel_3 target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<VcLinkDescriptor> result = new LinkedList<VcLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != VcchartPackage.eINSTANCE
					.getRelation3_Target()
					|| false == setting.getEObject() instanceof Relation3) {
				continue;
			}
			Relation3 link = (Relation3) setting.getEObject();
			if (Relation3EditPart.VISUAL_ID != VcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			All_Rel_3 src = link.getSource();
			result.add(new VcLinkDescriptor(src, target, link,
					VcElementTypes.Relation3_4003, Relation3EditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<VcLinkDescriptor> getOutgoingTypeModelFacetLinks_Relation1_4001(
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
		LinkedList<VcLinkDescriptor> result = new LinkedList<VcLinkDescriptor>();
		for (Iterator<?> links = container.getRelations1().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Relation1) {
				continue;
			}
			Relation1 link = (Relation1) linkObject;
			if (Relation1EditPart.VISUAL_ID != VcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			All_Rel_1 dst = link.getTarget();
			All_Rel_1 src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new VcLinkDescriptor(src, dst, link,
					VcElementTypes.Relation1_4001, Relation1EditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<VcLinkDescriptor> getOutgoingTypeModelFacetLinks_Relation2_4002(
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
		LinkedList<VcLinkDescriptor> result = new LinkedList<VcLinkDescriptor>();
		for (Iterator<?> links = container.getRelations2().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Relation2) {
				continue;
			}
			Relation2 link = (Relation2) linkObject;
			if (Relation2EditPart.VISUAL_ID != VcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			All_Rel_1 dst = link.getTarget();
			All_Rel_1 src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new VcLinkDescriptor(src, dst, link,
					VcElementTypes.Relation2_4002, Relation2EditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<VcLinkDescriptor> getOutgoingTypeModelFacetLinks_Relation3_4003(
			All_Rel_3 source) {
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
		LinkedList<VcLinkDescriptor> result = new LinkedList<VcLinkDescriptor>();
		for (Iterator<?> links = container.getRelations3().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Relation3) {
				continue;
			}
			Relation3 link = (Relation3) linkObject;
			if (Relation3EditPart.VISUAL_ID != VcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			All_Rel_3 dst = link.getTarget();
			All_Rel_3 src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new VcLinkDescriptor(src, dst, link,
					VcElementTypes.Relation3_4003, Relation3EditPart.VISUAL_ID));
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
		public List<VcNodeDescriptor> getSemanticChildren(View view) {
			return VcDiagramUpdater.getSemanticChildren(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<VcLinkDescriptor> getContainedLinks(View view) {
			return VcDiagramUpdater.getContainedLinks(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<VcLinkDescriptor> getIncomingLinks(View view) {
			return VcDiagramUpdater.getIncomingLinks(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<VcLinkDescriptor> getOutgoingLinks(View view) {
			return VcDiagramUpdater.getOutgoingLinks(view);
		}
	};

}

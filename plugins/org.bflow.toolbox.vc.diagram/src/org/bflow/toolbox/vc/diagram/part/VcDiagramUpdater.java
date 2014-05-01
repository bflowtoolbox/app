package org.bflow.toolbox.vc.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.bflow.toolbox.bflow.BflowPackage;
import org.bflow.toolbox.bflow.Element;
import org.bflow.toolbox.vc.Cluster;
import org.bflow.toolbox.vc.Objective;
import org.bflow.toolbox.vc.PredecessorConnection;
import org.bflow.toolbox.vc.ProcessSuperiority;
import org.bflow.toolbox.vc.Product;
import org.bflow.toolbox.vc.Relation;
import org.bflow.toolbox.vc.TechnicalTerm;
import org.bflow.toolbox.vc.ValueChain;
import org.bflow.toolbox.vc.ValueChain2;
import org.bflow.toolbox.vc.Vc;
import org.bflow.toolbox.vc.diagram.edit.parts.ClusterEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.ObjectiveEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.PredecessorConnectionEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.ProcessSuperiorityEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.ProductEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.RelationEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.TechnicalTermEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.VCEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.ValueChain2EditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.ValueChainEditPart;
import org.bflow.toolbox.vc.diagram.providers.VcElementTypes;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

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
	public static List getSemanticChildren(View view) {
		switch (VcVisualIDRegistry.getVisualID(view)) {
		case VCEditPart.VISUAL_ID:
			return getVc_1000SemanticChildren(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getVc_1000SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		Vc modelElement = (Vc) view.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getElements().iterator(); it.hasNext();) {
			Element childElement = (Element) it.next();
			int visualID = VcVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ValueChainEditPart.VISUAL_ID) {
				result.add(new VcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ValueChain2EditPart.VISUAL_ID) {
				result.add(new VcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == TechnicalTermEditPart.VISUAL_ID) {
				result.add(new VcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ClusterEditPart.VISUAL_ID) {
				result.add(new VcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ObjectiveEditPart.VISUAL_ID) {
				result.add(new VcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ProductEditPart.VISUAL_ID) {
				result.add(new VcNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getContainedLinks(View view) {
		switch (VcVisualIDRegistry.getVisualID(view)) {
		case VCEditPart.VISUAL_ID:
			return getVc_1000ContainedLinks(view);
		case ValueChainEditPart.VISUAL_ID:
			return getValueChain_2001ContainedLinks(view);
		case ValueChain2EditPart.VISUAL_ID:
			return getValueChain2_2002ContainedLinks(view);
		case TechnicalTermEditPart.VISUAL_ID:
			return getTechnicalTerm_2003ContainedLinks(view);
		case ClusterEditPart.VISUAL_ID:
			return getCluster_2004ContainedLinks(view);
		case ObjectiveEditPart.VISUAL_ID:
			return getObjective_2005ContainedLinks(view);
		case ProductEditPart.VISUAL_ID:
			return getProduct_2006ContainedLinks(view);
		case PredecessorConnectionEditPart.VISUAL_ID:
			return getPredecessorConnection_4001ContainedLinks(view);
		case ProcessSuperiorityEditPart.VISUAL_ID:
			return getProcessSuperiority_4002ContainedLinks(view);
		case RelationEditPart.VISUAL_ID:
			return getRelation_4003ContainedLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getIncomingLinks(View view) {
		switch (VcVisualIDRegistry.getVisualID(view)) {
		case ValueChainEditPart.VISUAL_ID:
			return getValueChain_2001IncomingLinks(view);
		case ValueChain2EditPart.VISUAL_ID:
			return getValueChain2_2002IncomingLinks(view);
		case TechnicalTermEditPart.VISUAL_ID:
			return getTechnicalTerm_2003IncomingLinks(view);
		case ClusterEditPart.VISUAL_ID:
			return getCluster_2004IncomingLinks(view);
		case ObjectiveEditPart.VISUAL_ID:
			return getObjective_2005IncomingLinks(view);
		case ProductEditPart.VISUAL_ID:
			return getProduct_2006IncomingLinks(view);
		case PredecessorConnectionEditPart.VISUAL_ID:
			return getPredecessorConnection_4001IncomingLinks(view);
		case ProcessSuperiorityEditPart.VISUAL_ID:
			return getProcessSuperiority_4002IncomingLinks(view);
		case RelationEditPart.VISUAL_ID:
			return getRelation_4003IncomingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOutgoingLinks(View view) {
		switch (VcVisualIDRegistry.getVisualID(view)) {
		case ValueChainEditPart.VISUAL_ID:
			return getValueChain_2001OutgoingLinks(view);
		case ValueChain2EditPart.VISUAL_ID:
			return getValueChain2_2002OutgoingLinks(view);
		case TechnicalTermEditPart.VISUAL_ID:
			return getTechnicalTerm_2003OutgoingLinks(view);
		case ClusterEditPart.VISUAL_ID:
			return getCluster_2004OutgoingLinks(view);
		case ObjectiveEditPart.VISUAL_ID:
			return getObjective_2005OutgoingLinks(view);
		case ProductEditPart.VISUAL_ID:
			return getProduct_2006OutgoingLinks(view);
		case PredecessorConnectionEditPart.VISUAL_ID:
			return getPredecessorConnection_4001OutgoingLinks(view);
		case ProcessSuperiorityEditPart.VISUAL_ID:
			return getProcessSuperiority_4002OutgoingLinks(view);
		case RelationEditPart.VISUAL_ID:
			return getRelation_4003OutgoingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getVc_1000ContainedLinks(View view) {
		Vc modelElement = (Vc) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_PredecessorConnection_4001(modelElement));
		result
				.addAll(getContainedTypeModelFacetLinks_ProcessSuperiority_4002(modelElement));
		result
				.addAll(getContainedTypeModelFacetLinks_Relation_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getValueChain_2001ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getValueChain2_2002ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTechnicalTerm_2003ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getCluster_2004ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getObjective_2005ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getProduct_2006ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getPredecessorConnection_4001ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getProcessSuperiority_4002ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getRelation_4003ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getValueChain_2001IncomingLinks(View view) {
		ValueChain modelElement = (ValueChain) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result
				.addAll(getIncomingTypeModelFacetLinks_PredecessorConnection_4001(
						modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_ProcessSuperiority_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getValueChain2_2002IncomingLinks(View view) {
		ValueChain2 modelElement = (ValueChain2) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result
				.addAll(getIncomingTypeModelFacetLinks_PredecessorConnection_4001(
						modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_ProcessSuperiority_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getTechnicalTerm_2003IncomingLinks(View view) {
		TechnicalTerm modelElement = (TechnicalTerm) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result
				.addAll(getIncomingTypeModelFacetLinks_PredecessorConnection_4001(
						modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_ProcessSuperiority_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getCluster_2004IncomingLinks(View view) {
		Cluster modelElement = (Cluster) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result
				.addAll(getIncomingTypeModelFacetLinks_PredecessorConnection_4001(
						modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_ProcessSuperiority_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getObjective_2005IncomingLinks(View view) {
		Objective modelElement = (Objective) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result
				.addAll(getIncomingTypeModelFacetLinks_PredecessorConnection_4001(
						modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_ProcessSuperiority_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getProduct_2006IncomingLinks(View view) {
		Product modelElement = (Product) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result
				.addAll(getIncomingTypeModelFacetLinks_PredecessorConnection_4001(
						modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_ProcessSuperiority_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getPredecessorConnection_4001IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getProcessSuperiority_4002IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getRelation_4003IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getValueChain_2001OutgoingLinks(View view) {
		ValueChain modelElement = (ValueChain) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_PredecessorConnection_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_ProcessSuperiority_4002(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Relation_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getValueChain2_2002OutgoingLinks(View view) {
		ValueChain2 modelElement = (ValueChain2) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_PredecessorConnection_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_ProcessSuperiority_4002(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Relation_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getTechnicalTerm_2003OutgoingLinks(View view) {
		TechnicalTerm modelElement = (TechnicalTerm) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_PredecessorConnection_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_ProcessSuperiority_4002(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Relation_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getCluster_2004OutgoingLinks(View view) {
		Cluster modelElement = (Cluster) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_PredecessorConnection_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_ProcessSuperiority_4002(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Relation_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getObjective_2005OutgoingLinks(View view) {
		Objective modelElement = (Objective) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_PredecessorConnection_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_ProcessSuperiority_4002(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Relation_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getProduct_2006OutgoingLinks(View view) {
		Product modelElement = (Product) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_PredecessorConnection_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_ProcessSuperiority_4002(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Relation_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getPredecessorConnection_4001OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getProcessSuperiority_4002OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getRelation_4003OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_PredecessorConnection_4001(
			Vc container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getConnections().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof PredecessorConnection) {
				continue;
			}
			PredecessorConnection link = (PredecessorConnection) linkObject;
			if (PredecessorConnectionEditPart.VISUAL_ID != VcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element dst = link.getTo();
			Element src = link.getFrom();
			result.add(new VcLinkDescriptor(src, dst, link,
					VcElementTypes.PredecessorConnection_4001,
					PredecessorConnectionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_ProcessSuperiority_4002(
			Vc container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getConnections().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof ProcessSuperiority) {
				continue;
			}
			ProcessSuperiority link = (ProcessSuperiority) linkObject;
			if (ProcessSuperiorityEditPart.VISUAL_ID != VcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element dst = link.getTo();
			Element src = link.getFrom();
			result.add(new VcLinkDescriptor(src, dst, link,
					VcElementTypes.ProcessSuperiority_4002,
					ProcessSuperiorityEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_Relation_4003(
			Vc container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getConnections().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Relation) {
				continue;
			}
			Relation link = (Relation) linkObject;
			if (RelationEditPart.VISUAL_ID != VcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element dst = link.getTo();
			Element src = link.getFrom();
			result.add(new VcLinkDescriptor(src, dst, link,
					VcElementTypes.Relation_4003, RelationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_PredecessorConnection_4001(
			Element target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != BflowPackage.eINSTANCE
					.getConnection_To()
					|| false == setting.getEObject() instanceof PredecessorConnection) {
				continue;
			}
			PredecessorConnection link = (PredecessorConnection) setting
					.getEObject();
			if (PredecessorConnectionEditPart.VISUAL_ID != VcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element src = link.getFrom();
			result.add(new VcLinkDescriptor(src, target, link,
					VcElementTypes.PredecessorConnection_4001,
					PredecessorConnectionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_ProcessSuperiority_4002(
			Element target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != BflowPackage.eINSTANCE
					.getConnection_To()
					|| false == setting.getEObject() instanceof ProcessSuperiority) {
				continue;
			}
			ProcessSuperiority link = (ProcessSuperiority) setting.getEObject();
			if (ProcessSuperiorityEditPart.VISUAL_ID != VcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element src = link.getFrom();
			result.add(new VcLinkDescriptor(src, target, link,
					VcElementTypes.ProcessSuperiority_4002,
					ProcessSuperiorityEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_Relation_4003(
			Element target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != BflowPackage.eINSTANCE
					.getConnection_To()
					|| false == setting.getEObject() instanceof Relation) {
				continue;
			}
			Relation link = (Relation) setting.getEObject();
			if (RelationEditPart.VISUAL_ID != VcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element src = link.getFrom();
			result.add(new VcLinkDescriptor(src, target, link,
					VcElementTypes.Relation_4003, RelationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_PredecessorConnection_4001(
			Element source) {
		Vc container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Vc) {
				container = (Vc) element;
			}
		}
		if (container == null) {
			return Collections.EMPTY_LIST;
		}
		Collection result = new LinkedList();
		for (Iterator links = container.getConnections().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof PredecessorConnection) {
				continue;
			}
			PredecessorConnection link = (PredecessorConnection) linkObject;
			if (PredecessorConnectionEditPart.VISUAL_ID != VcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element dst = link.getTo();
			Element src = link.getFrom();
			if (src != source) {
				continue;
			}
			result.add(new VcLinkDescriptor(src, dst, link,
					VcElementTypes.PredecessorConnection_4001,
					PredecessorConnectionEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_ProcessSuperiority_4002(
			Element source) {
		Vc container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Vc) {
				container = (Vc) element;
			}
		}
		if (container == null) {
			return Collections.EMPTY_LIST;
		}
		Collection result = new LinkedList();
		for (Iterator links = container.getConnections().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof ProcessSuperiority) {
				continue;
			}
			ProcessSuperiority link = (ProcessSuperiority) linkObject;
			if (ProcessSuperiorityEditPart.VISUAL_ID != VcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element dst = link.getTo();
			Element src = link.getFrom();
			if (src != source) {
				continue;
			}
			result.add(new VcLinkDescriptor(src, dst, link,
					VcElementTypes.ProcessSuperiority_4002,
					ProcessSuperiorityEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_Relation_4003(
			Element source) {
		Vc container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Vc) {
				container = (Vc) element;
			}
		}
		if (container == null) {
			return Collections.EMPTY_LIST;
		}
		Collection result = new LinkedList();
		for (Iterator links = container.getConnections().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Relation) {
				continue;
			}
			Relation link = (Relation) linkObject;
			if (RelationEditPart.VISUAL_ID != VcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element dst = link.getTo();
			Element src = link.getFrom();
			if (src != source) {
				continue;
			}
			result.add(new VcLinkDescriptor(src, dst, link,
					VcElementTypes.Relation_4003, RelationEditPart.VISUAL_ID));
		}
		return result;
	}

}

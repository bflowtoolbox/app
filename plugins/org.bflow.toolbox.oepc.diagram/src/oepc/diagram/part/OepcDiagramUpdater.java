package oepc.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import oepc.ANDConnector;
import oepc.BusinessAttribute;
import oepc.BusinessMethod;
import oepc.BusinessObject;
import oepc.ControlFlowEdge;
import oepc.Document;
import oepc.Event;
import oepc.ITSystem;
import oepc.InformationEdge;
import oepc.ORConnector;
import oepc.OrganisationUnit;
import oepc.XORConnector;
import oepc.diagram.edit.parts.ANDConnectorEditPart;
import oepc.diagram.edit.parts.BusinessAttributeEditPart;
import oepc.diagram.edit.parts.BusinessMethodEditPart;
import oepc.diagram.edit.parts.BusinessObjectBusinessObjectAttributeCompartmentEditPart;
import oepc.diagram.edit.parts.BusinessObjectBusinessObjectMethodCompartmentEditPart;
import oepc.diagram.edit.parts.BusinessObjectEditPart;
import oepc.diagram.edit.parts.ControlFlowEdgeEditPart;
import oepc.diagram.edit.parts.DocumentEditPart;
import oepc.diagram.edit.parts.EventEditPart;
import oepc.diagram.edit.parts.ITSystemEditPart;
import oepc.diagram.edit.parts.InformationEdgeEditPart;
import oepc.diagram.edit.parts.OEPCEditPart;
import oepc.diagram.edit.parts.ORConnectorEditPart;
import oepc.diagram.edit.parts.OrganisationUnitEditPart;
import oepc.diagram.edit.parts.XORConnectorEditPart;
import oepc.diagram.providers.OepcElementTypes;

import org.bflow.toolbox.bflow.BflowPackage;
import org.bflow.toolbox.bflow.Element;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class OepcDiagramUpdater {

	/**
	 * @generated
	 */
	public static List getSemanticChildren(View view) {
		switch (OepcVisualIDRegistry.getVisualID(view)) {
		case BusinessObjectBusinessObjectAttributeCompartmentEditPart.VISUAL_ID:
			return getBusinessObjectBusinessObjectAttributeCompartment_7001SemanticChildren(view);
		case BusinessObjectBusinessObjectMethodCompartmentEditPart.VISUAL_ID:
			return getBusinessObjectBusinessObjectMethodCompartment_7002SemanticChildren(view);
		case OEPCEditPart.VISUAL_ID:
			return getOEPC_79SemanticChildren(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getBusinessObjectBusinessObjectAttributeCompartment_7001SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		BusinessObject modelElement = (BusinessObject) containerView
				.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getAttributes().iterator(); it
				.hasNext();) {
			BusinessAttribute childElement = (BusinessAttribute) it.next();
			int visualID = OepcVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == BusinessAttributeEditPart.VISUAL_ID) {
				result.add(new OepcNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getBusinessObjectBusinessObjectMethodCompartment_7002SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.EMPTY_LIST;
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		BusinessObject modelElement = (BusinessObject) containerView
				.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getMethods().iterator(); it.hasNext();) {
			BusinessMethod childElement = (BusinessMethod) it.next();
			int visualID = OepcVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == BusinessMethodEditPart.VISUAL_ID) {
				result.add(new OepcNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getOEPC_79SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		oepc.OEPC modelElement = (oepc.OEPC) view.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getElements().iterator(); it.hasNext();) {
			Element childElement = (Element) it.next();
			int visualID = OepcVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == EventEditPart.VISUAL_ID) {
				result.add(new OepcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ITSystemEditPart.VISUAL_ID) {
				result.add(new OepcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == OrganisationUnitEditPart.VISUAL_ID) {
				result.add(new OepcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == XORConnectorEditPart.VISUAL_ID) {
				result.add(new OepcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == BusinessObjectEditPart.VISUAL_ID) {
				result.add(new OepcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ANDConnectorEditPart.VISUAL_ID) {
				result.add(new OepcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ORConnectorEditPart.VISUAL_ID) {
				result.add(new OepcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == DocumentEditPart.VISUAL_ID) {
				result.add(new OepcNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getContainedLinks(View view) {
		switch (OepcVisualIDRegistry.getVisualID(view)) {
		case OEPCEditPart.VISUAL_ID:
			return getOEPC_79ContainedLinks(view);
		case EventEditPart.VISUAL_ID:
			return getEvent_2001ContainedLinks(view);
		case ITSystemEditPart.VISUAL_ID:
			return getITSystem_2002ContainedLinks(view);
		case OrganisationUnitEditPart.VISUAL_ID:
			return getOrganisationUnit_2003ContainedLinks(view);
		case XORConnectorEditPart.VISUAL_ID:
			return getXORConnector_2004ContainedLinks(view);
		case BusinessObjectEditPart.VISUAL_ID:
			return getBusinessObject_2005ContainedLinks(view);
		case ANDConnectorEditPart.VISUAL_ID:
			return getANDConnector_2006ContainedLinks(view);
		case ORConnectorEditPart.VISUAL_ID:
			return getORConnector_2007ContainedLinks(view);
		case DocumentEditPart.VISUAL_ID:
			return getDocument_2008ContainedLinks(view);
		case BusinessAttributeEditPart.VISUAL_ID:
			return getBusinessAttribute_3001ContainedLinks(view);
		case BusinessMethodEditPart.VISUAL_ID:
			return getBusinessMethod_3002ContainedLinks(view);
		case ControlFlowEdgeEditPart.VISUAL_ID:
			return getControlFlowEdge_4001ContainedLinks(view);
		case InformationEdgeEditPart.VISUAL_ID:
			return getInformationEdge_4002ContainedLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getIncomingLinks(View view) {
		switch (OepcVisualIDRegistry.getVisualID(view)) {
		case EventEditPart.VISUAL_ID:
			return getEvent_2001IncomingLinks(view);
		case ITSystemEditPart.VISUAL_ID:
			return getITSystem_2002IncomingLinks(view);
		case OrganisationUnitEditPart.VISUAL_ID:
			return getOrganisationUnit_2003IncomingLinks(view);
		case XORConnectorEditPart.VISUAL_ID:
			return getXORConnector_2004IncomingLinks(view);
		case BusinessObjectEditPart.VISUAL_ID:
			return getBusinessObject_2005IncomingLinks(view);
		case ANDConnectorEditPart.VISUAL_ID:
			return getANDConnector_2006IncomingLinks(view);
		case ORConnectorEditPart.VISUAL_ID:
			return getORConnector_2007IncomingLinks(view);
		case DocumentEditPart.VISUAL_ID:
			return getDocument_2008IncomingLinks(view);
		case BusinessAttributeEditPart.VISUAL_ID:
			return getBusinessAttribute_3001IncomingLinks(view);
		case BusinessMethodEditPart.VISUAL_ID:
			return getBusinessMethod_3002IncomingLinks(view);
		case ControlFlowEdgeEditPart.VISUAL_ID:
			return getControlFlowEdge_4001IncomingLinks(view);
		case InformationEdgeEditPart.VISUAL_ID:
			return getInformationEdge_4002IncomingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOutgoingLinks(View view) {
		switch (OepcVisualIDRegistry.getVisualID(view)) {
		case EventEditPart.VISUAL_ID:
			return getEvent_2001OutgoingLinks(view);
		case ITSystemEditPart.VISUAL_ID:
			return getITSystem_2002OutgoingLinks(view);
		case OrganisationUnitEditPart.VISUAL_ID:
			return getOrganisationUnit_2003OutgoingLinks(view);
		case XORConnectorEditPart.VISUAL_ID:
			return getXORConnector_2004OutgoingLinks(view);
		case BusinessObjectEditPart.VISUAL_ID:
			return getBusinessObject_2005OutgoingLinks(view);
		case ANDConnectorEditPart.VISUAL_ID:
			return getANDConnector_2006OutgoingLinks(view);
		case ORConnectorEditPart.VISUAL_ID:
			return getORConnector_2007OutgoingLinks(view);
		case DocumentEditPart.VISUAL_ID:
			return getDocument_2008OutgoingLinks(view);
		case BusinessAttributeEditPart.VISUAL_ID:
			return getBusinessAttribute_3001OutgoingLinks(view);
		case BusinessMethodEditPart.VISUAL_ID:
			return getBusinessMethod_3002OutgoingLinks(view);
		case ControlFlowEdgeEditPart.VISUAL_ID:
			return getControlFlowEdge_4001OutgoingLinks(view);
		case InformationEdgeEditPart.VISUAL_ID:
			return getInformationEdge_4002OutgoingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOEPC_79ContainedLinks(View view) {
		oepc.OEPC modelElement = (oepc.OEPC) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getContainedTypeModelFacetLinks_ControlFlowEdge_4001(modelElement));
		result
				.addAll(getContainedTypeModelFacetLinks_InformationEdge_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getEvent_2001ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getITSystem_2002ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOrganisationUnit_2003ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getXORConnector_2004ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getBusinessObject_2005ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getANDConnector_2006ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getORConnector_2007ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getDocument_2008ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getBusinessAttribute_3001ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getBusinessMethod_3002ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getControlFlowEdge_4001ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getInformationEdge_4002ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getEvent_2001IncomingLinks(View view) {
		Event modelElement = (Event) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_ControlFlowEdge_4001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationEdge_4002(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getITSystem_2002IncomingLinks(View view) {
		ITSystem modelElement = (ITSystem) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_ControlFlowEdge_4001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationEdge_4002(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getOrganisationUnit_2003IncomingLinks(View view) {
		OrganisationUnit modelElement = (OrganisationUnit) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_ControlFlowEdge_4001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationEdge_4002(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getXORConnector_2004IncomingLinks(View view) {
		XORConnector modelElement = (XORConnector) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_ControlFlowEdge_4001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationEdge_4002(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getBusinessObject_2005IncomingLinks(View view) {
		BusinessObject modelElement = (BusinessObject) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_ControlFlowEdge_4001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationEdge_4002(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getANDConnector_2006IncomingLinks(View view) {
		ANDConnector modelElement = (ANDConnector) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_ControlFlowEdge_4001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationEdge_4002(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getORConnector_2007IncomingLinks(View view) {
		ORConnector modelElement = (ORConnector) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_ControlFlowEdge_4001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationEdge_4002(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getDocument_2008IncomingLinks(View view) {
		Document modelElement = (Document) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_ControlFlowEdge_4001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationEdge_4002(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getBusinessAttribute_3001IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getBusinessMethod_3002IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getControlFlowEdge_4001IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getInformationEdge_4002IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getEvent_2001OutgoingLinks(View view) {
		Event modelElement = (Event) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_ControlFlowEdge_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationEdge_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getITSystem_2002OutgoingLinks(View view) {
		ITSystem modelElement = (ITSystem) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_ControlFlowEdge_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationEdge_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getOrganisationUnit_2003OutgoingLinks(View view) {
		OrganisationUnit modelElement = (OrganisationUnit) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_ControlFlowEdge_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationEdge_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getXORConnector_2004OutgoingLinks(View view) {
		XORConnector modelElement = (XORConnector) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_ControlFlowEdge_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationEdge_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getBusinessObject_2005OutgoingLinks(View view) {
		BusinessObject modelElement = (BusinessObject) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_ControlFlowEdge_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationEdge_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getANDConnector_2006OutgoingLinks(View view) {
		ANDConnector modelElement = (ANDConnector) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_ControlFlowEdge_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationEdge_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getORConnector_2007OutgoingLinks(View view) {
		ORConnector modelElement = (ORConnector) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_ControlFlowEdge_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationEdge_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getDocument_2008OutgoingLinks(View view) {
		Document modelElement = (Document) view.getElement();
		List result = new LinkedList();
		result
				.addAll(getOutgoingTypeModelFacetLinks_ControlFlowEdge_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationEdge_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getBusinessAttribute_3001OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getBusinessMethod_3002OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getControlFlowEdge_4001OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getInformationEdge_4002OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_ControlFlowEdge_4001(
			oepc.OEPC container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getConnections().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof ControlFlowEdge) {
				continue;
			}
			ControlFlowEdge link = (ControlFlowEdge) linkObject;
			if (ControlFlowEdgeEditPart.VISUAL_ID != OepcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element dst = link.getTo();
			Element src = link.getFrom();
			result.add(new OepcLinkDescriptor(src, dst, link,
					OepcElementTypes.ControlFlowEdge_4001,
					ControlFlowEdgeEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_InformationEdge_4002(
			oepc.OEPC container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getConnections().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof InformationEdge) {
				continue;
			}
			InformationEdge link = (InformationEdge) linkObject;
			if (InformationEdgeEditPart.VISUAL_ID != OepcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element dst = link.getTo();
			Element src = link.getFrom();
			result.add(new OepcLinkDescriptor(src, dst, link,
					OepcElementTypes.InformationEdge_4002,
					InformationEdgeEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_ControlFlowEdge_4001(
			Element target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != BflowPackage.eINSTANCE
					.getConnection_To()
					|| false == setting.getEObject() instanceof ControlFlowEdge) {
				continue;
			}
			ControlFlowEdge link = (ControlFlowEdge) setting.getEObject();
			if (ControlFlowEdgeEditPart.VISUAL_ID != OepcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element src = link.getFrom();
			result.add(new OepcLinkDescriptor(src, target, link,
					OepcElementTypes.ControlFlowEdge_4001,
					ControlFlowEdgeEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_InformationEdge_4002(
			Element target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != BflowPackage.eINSTANCE
					.getConnection_To()
					|| false == setting.getEObject() instanceof InformationEdge) {
				continue;
			}
			InformationEdge link = (InformationEdge) setting.getEObject();
			if (InformationEdgeEditPart.VISUAL_ID != OepcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element src = link.getFrom();
			result.add(new OepcLinkDescriptor(src, target, link,
					OepcElementTypes.InformationEdge_4002,
					InformationEdgeEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_ControlFlowEdge_4001(
			Element source) {
		oepc.OEPC container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof oepc.OEPC) {
				container = (oepc.OEPC) element;
			}
		}
		if (container == null) {
			return Collections.EMPTY_LIST;
		}
		Collection result = new LinkedList();
		for (Iterator links = container.getConnections().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof ControlFlowEdge) {
				continue;
			}
			ControlFlowEdge link = (ControlFlowEdge) linkObject;
			if (ControlFlowEdgeEditPart.VISUAL_ID != OepcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element dst = link.getTo();
			Element src = link.getFrom();
			if (src != source) {
				continue;
			}
			result.add(new OepcLinkDescriptor(src, dst, link,
					OepcElementTypes.ControlFlowEdge_4001,
					ControlFlowEdgeEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_InformationEdge_4002(
			Element source) {
		oepc.OEPC container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof oepc.OEPC) {
				container = (oepc.OEPC) element;
			}
		}
		if (container == null) {
			return Collections.EMPTY_LIST;
		}
		Collection result = new LinkedList();
		for (Iterator links = container.getConnections().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof InformationEdge) {
				continue;
			}
			InformationEdge link = (InformationEdge) linkObject;
			if (InformationEdgeEditPart.VISUAL_ID != OepcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element dst = link.getTo();
			Element src = link.getFrom();
			if (src != source) {
				continue;
			}
			result.add(new OepcLinkDescriptor(src, dst, link,
					OepcElementTypes.InformationEdge_4002,
					InformationEdgeEditPart.VISUAL_ID));
		}
		return result;
	}

}

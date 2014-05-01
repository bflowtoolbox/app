package org.bflow.toolbox.epc.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.bflow.toolbox.bflow.BflowPackage;
import org.bflow.toolbox.bflow.Element;
import org.bflow.toolbox.epc.Application;
import org.bflow.toolbox.epc.Arc;
import org.bflow.toolbox.epc.CardFile;
import org.bflow.toolbox.epc.Cluster;
import org.bflow.toolbox.epc.Document;
import org.bflow.toolbox.epc.Epc;
import org.bflow.toolbox.epc.Event;
import org.bflow.toolbox.epc.ExternalPerson;
import org.bflow.toolbox.epc.File;
import org.bflow.toolbox.epc.Function;
import org.bflow.toolbox.epc.Group;
import org.bflow.toolbox.epc.InformationArc;
import org.bflow.toolbox.epc.InternalPerson;
import org.bflow.toolbox.epc.Location;
import org.bflow.toolbox.epc.Objective;
import org.bflow.toolbox.epc.Participant;
import org.bflow.toolbox.epc.PersonType;
import org.bflow.toolbox.epc.Position;
import org.bflow.toolbox.epc.ProcessInterface;
import org.bflow.toolbox.epc.Product;
import org.bflow.toolbox.epc.Relation;
import org.bflow.toolbox.epc.TechnicalTerm;
import org.bflow.toolbox.epc.diagram.edit.parts.ANDEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ApplicationEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ArcEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.CardFileEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ClusterEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.DocumentEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.EpcEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.EventEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ExternalPersonEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.FileEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.FunctionEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.GroupEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.InformationArcEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.InternalPersonEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.LocationEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.OREditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ObjectiveEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ParticipantEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.PersonTypeEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.PositionEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ProcessInterfaceEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ProductEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.RelationEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.TechnicalTermEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.XOREditPart;
import org.bflow.toolbox.epc.diagram.providers.EpcElementTypes;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class EpcDiagramUpdater {

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
		switch (EpcVisualIDRegistry.getVisualID(view)) {
		case EpcEditPart.VISUAL_ID:
			return getEpc_79SemanticChildren(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getEpc_79SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.EMPTY_LIST;
		}
		Epc modelElement = (Epc) view.getElement();
		List result = new LinkedList();
		for (Iterator it = modelElement.getElements().iterator(); it.hasNext();) {
			Element childElement = (Element) it.next();
			int visualID = EpcVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == OREditPart.VISUAL_ID) {
				result.add(new EpcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ParticipantEditPart.VISUAL_ID) {
				result.add(new EpcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ANDEditPart.VISUAL_ID) {
				result.add(new EpcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ApplicationEditPart.VISUAL_ID) {
				result.add(new EpcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ProcessInterfaceEditPart.VISUAL_ID) {
				result.add(new EpcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EventEditPart.VISUAL_ID) {
				result.add(new EpcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == FunctionEditPart.VISUAL_ID) {
				result.add(new EpcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == XOREditPart.VISUAL_ID) {
				result.add(new EpcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == GroupEditPart.VISUAL_ID) {
				result.add(new EpcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ClusterEditPart.VISUAL_ID) {
				result.add(new EpcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ExternalPersonEditPart.VISUAL_ID) {
				result.add(new EpcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == InternalPersonEditPart.VISUAL_ID) {
				result.add(new EpcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == PositionEditPart.VISUAL_ID) {
				result.add(new EpcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == LocationEditPart.VISUAL_ID) {
				result.add(new EpcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == PersonTypeEditPart.VISUAL_ID) {
				result.add(new EpcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == TechnicalTermEditPart.VISUAL_ID) {
				result.add(new EpcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == CardFileEditPart.VISUAL_ID) {
				result.add(new EpcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == DocumentEditPart.VISUAL_ID) {
				result.add(new EpcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == FileEditPart.VISUAL_ID) {
				result.add(new EpcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ObjectiveEditPart.VISUAL_ID) {
				result.add(new EpcNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ProductEditPart.VISUAL_ID) {
				result.add(new EpcNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List getContainedLinks(View view) {
		switch (EpcVisualIDRegistry.getVisualID(view)) {
		case EpcEditPart.VISUAL_ID:
			return getEpc_79ContainedLinks(view);
		case OREditPart.VISUAL_ID:
			return getOR_2001ContainedLinks(view);
		case ParticipantEditPart.VISUAL_ID:
			return getParticipant_2002ContainedLinks(view);
		case ANDEditPart.VISUAL_ID:
			return getAND_2003ContainedLinks(view);
		case ApplicationEditPart.VISUAL_ID:
			return getApplication_2004ContainedLinks(view);
		case ProcessInterfaceEditPart.VISUAL_ID:
			return getProcessInterface_2005ContainedLinks(view);
		case EventEditPart.VISUAL_ID:
			return getEvent_2006ContainedLinks(view);
		case FunctionEditPart.VISUAL_ID:
			return getFunction_2007ContainedLinks(view);
		case XOREditPart.VISUAL_ID:
			return getXOR_2008ContainedLinks(view);
		case GroupEditPart.VISUAL_ID:
			return getGroup_2009ContainedLinks(view);
		case ClusterEditPart.VISUAL_ID:
			return getCluster_2010ContainedLinks(view);
		case ExternalPersonEditPart.VISUAL_ID:
			return getExternalPerson_2011ContainedLinks(view);
		case InternalPersonEditPart.VISUAL_ID:
			return getInternalPerson_2012ContainedLinks(view);
		case PositionEditPart.VISUAL_ID:
			return getPosition_2013ContainedLinks(view);
		case LocationEditPart.VISUAL_ID:
			return getLocation_2014ContainedLinks(view);
		case PersonTypeEditPart.VISUAL_ID:
			return getPersonType_2015ContainedLinks(view);
		case TechnicalTermEditPart.VISUAL_ID:
			return getTechnicalTerm_2016ContainedLinks(view);
		case CardFileEditPart.VISUAL_ID:
			return getCardFile_2017ContainedLinks(view);
		case DocumentEditPart.VISUAL_ID:
			return getDocument_2018ContainedLinks(view);
		case FileEditPart.VISUAL_ID:
			return getFile_2019ContainedLinks(view);
		case ObjectiveEditPart.VISUAL_ID:
			return getObjective_2020ContainedLinks(view);
		case ProductEditPart.VISUAL_ID:
			return getProduct_2021ContainedLinks(view);
		case ArcEditPart.VISUAL_ID:
			return getArc_4001ContainedLinks(view);
		case RelationEditPart.VISUAL_ID:
			return getRelation_4002ContainedLinks(view);
		case InformationArcEditPart.VISUAL_ID:
			return getInformationArc_4003ContainedLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getIncomingLinks(View view) {
		switch (EpcVisualIDRegistry.getVisualID(view)) {
		case OREditPart.VISUAL_ID:
			return getOR_2001IncomingLinks(view);
		case ParticipantEditPart.VISUAL_ID:
			return getParticipant_2002IncomingLinks(view);
		case ANDEditPart.VISUAL_ID:
			return getAND_2003IncomingLinks(view);
		case ApplicationEditPart.VISUAL_ID:
			return getApplication_2004IncomingLinks(view);
		case ProcessInterfaceEditPart.VISUAL_ID:
			return getProcessInterface_2005IncomingLinks(view);
		case EventEditPart.VISUAL_ID:
			return getEvent_2006IncomingLinks(view);
		case FunctionEditPart.VISUAL_ID:
			return getFunction_2007IncomingLinks(view);
		case XOREditPart.VISUAL_ID:
			return getXOR_2008IncomingLinks(view);
		case GroupEditPart.VISUAL_ID:
			return getGroup_2009IncomingLinks(view);
		case ClusterEditPart.VISUAL_ID:
			return getCluster_2010IncomingLinks(view);
		case ExternalPersonEditPart.VISUAL_ID:
			return getExternalPerson_2011IncomingLinks(view);
		case InternalPersonEditPart.VISUAL_ID:
			return getInternalPerson_2012IncomingLinks(view);
		case PositionEditPart.VISUAL_ID:
			return getPosition_2013IncomingLinks(view);
		case LocationEditPart.VISUAL_ID:
			return getLocation_2014IncomingLinks(view);
		case PersonTypeEditPart.VISUAL_ID:
			return getPersonType_2015IncomingLinks(view);
		case TechnicalTermEditPart.VISUAL_ID:
			return getTechnicalTerm_2016IncomingLinks(view);
		case CardFileEditPart.VISUAL_ID:
			return getCardFile_2017IncomingLinks(view);
		case DocumentEditPart.VISUAL_ID:
			return getDocument_2018IncomingLinks(view);
		case FileEditPart.VISUAL_ID:
			return getFile_2019IncomingLinks(view);
		case ObjectiveEditPart.VISUAL_ID:
			return getObjective_2020IncomingLinks(view);
		case ProductEditPart.VISUAL_ID:
			return getProduct_2021IncomingLinks(view);
		case ArcEditPart.VISUAL_ID:
			return getArc_4001IncomingLinks(view);
		case RelationEditPart.VISUAL_ID:
			return getRelation_4002IncomingLinks(view);
		case InformationArcEditPart.VISUAL_ID:
			return getInformationArc_4003IncomingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOutgoingLinks(View view) {
		switch (EpcVisualIDRegistry.getVisualID(view)) {
		case OREditPart.VISUAL_ID:
			return getOR_2001OutgoingLinks(view);
		case ParticipantEditPart.VISUAL_ID:
			return getParticipant_2002OutgoingLinks(view);
		case ANDEditPart.VISUAL_ID:
			return getAND_2003OutgoingLinks(view);
		case ApplicationEditPart.VISUAL_ID:
			return getApplication_2004OutgoingLinks(view);
		case ProcessInterfaceEditPart.VISUAL_ID:
			return getProcessInterface_2005OutgoingLinks(view);
		case EventEditPart.VISUAL_ID:
			return getEvent_2006OutgoingLinks(view);
		case FunctionEditPart.VISUAL_ID:
			return getFunction_2007OutgoingLinks(view);
		case XOREditPart.VISUAL_ID:
			return getXOR_2008OutgoingLinks(view);
		case GroupEditPart.VISUAL_ID:
			return getGroup_2009OutgoingLinks(view);
		case ClusterEditPart.VISUAL_ID:
			return getCluster_2010OutgoingLinks(view);
		case ExternalPersonEditPart.VISUAL_ID:
			return getExternalPerson_2011OutgoingLinks(view);
		case InternalPersonEditPart.VISUAL_ID:
			return getInternalPerson_2012OutgoingLinks(view);
		case PositionEditPart.VISUAL_ID:
			return getPosition_2013OutgoingLinks(view);
		case LocationEditPart.VISUAL_ID:
			return getLocation_2014OutgoingLinks(view);
		case PersonTypeEditPart.VISUAL_ID:
			return getPersonType_2015OutgoingLinks(view);
		case TechnicalTermEditPart.VISUAL_ID:
			return getTechnicalTerm_2016OutgoingLinks(view);
		case CardFileEditPart.VISUAL_ID:
			return getCardFile_2017OutgoingLinks(view);
		case DocumentEditPart.VISUAL_ID:
			return getDocument_2018OutgoingLinks(view);
		case FileEditPart.VISUAL_ID:
			return getFile_2019OutgoingLinks(view);
		case ObjectiveEditPart.VISUAL_ID:
			return getObjective_2020OutgoingLinks(view);
		case ProductEditPart.VISUAL_ID:
			return getProduct_2021OutgoingLinks(view);
		case ArcEditPart.VISUAL_ID:
			return getArc_4001OutgoingLinks(view);
		case RelationEditPart.VISUAL_ID:
			return getRelation_4002OutgoingLinks(view);
		case InformationArcEditPart.VISUAL_ID:
			return getInformationArc_4003OutgoingLinks(view);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getEpc_79ContainedLinks(View view) {
		Epc modelElement = (Epc) view.getElement();
		List result = new LinkedList();
		result.addAll(getContainedTypeModelFacetLinks_Arc_4001(modelElement));
		result
				.addAll(getContainedTypeModelFacetLinks_Relation_4002(modelElement));
		result
				.addAll(getContainedTypeModelFacetLinks_InformationArc_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getOR_2001ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getParticipant_2002ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getAND_2003ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getApplication_2004ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getProcessInterface_2005ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getEvent_2006ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getFunction_2007ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getXOR_2008ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getGroup_2009ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getCluster_2010ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getExternalPerson_2011ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getInternalPerson_2012ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getPosition_2013ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getLocation_2014ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getPersonType_2015ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getTechnicalTerm_2016ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getCardFile_2017ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getDocument_2018ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getFile_2019ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getObjective_2020ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getProduct_2021ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getArc_4001ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getRelation_4002ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getInformationArc_4003ContainedLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOR_2001IncomingLinks(View view) {
		org.bflow.toolbox.epc.OR modelElement = (org.bflow.toolbox.epc.OR) view
				.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Arc_4001(modelElement,
				crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationArc_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getParticipant_2002IncomingLinks(View view) {
		Participant modelElement = (Participant) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Arc_4001(modelElement,
				crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationArc_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getAND_2003IncomingLinks(View view) {
		org.bflow.toolbox.epc.AND modelElement = (org.bflow.toolbox.epc.AND) view
				.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Arc_4001(modelElement,
				crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationArc_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getApplication_2004IncomingLinks(View view) {
		Application modelElement = (Application) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Arc_4001(modelElement,
				crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationArc_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getProcessInterface_2005IncomingLinks(View view) {
		ProcessInterface modelElement = (ProcessInterface) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Arc_4001(modelElement,
				crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationArc_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getEvent_2006IncomingLinks(View view) {
		Event modelElement = (Event) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Arc_4001(modelElement,
				crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationArc_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getFunction_2007IncomingLinks(View view) {
		Function modelElement = (Function) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Arc_4001(modelElement,
				crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationArc_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getXOR_2008IncomingLinks(View view) {
		org.bflow.toolbox.epc.XOR modelElement = (org.bflow.toolbox.epc.XOR) view
				.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Arc_4001(modelElement,
				crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationArc_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getGroup_2009IncomingLinks(View view) {
		Group modelElement = (Group) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Arc_4001(modelElement,
				crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationArc_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getCluster_2010IncomingLinks(View view) {
		Cluster modelElement = (Cluster) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Arc_4001(modelElement,
				crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationArc_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getExternalPerson_2011IncomingLinks(View view) {
		ExternalPerson modelElement = (ExternalPerson) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Arc_4001(modelElement,
				crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationArc_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getInternalPerson_2012IncomingLinks(View view) {
		InternalPerson modelElement = (InternalPerson) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Arc_4001(modelElement,
				crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationArc_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getPosition_2013IncomingLinks(View view) {
		Position modelElement = (Position) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Arc_4001(modelElement,
				crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationArc_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getLocation_2014IncomingLinks(View view) {
		Location modelElement = (Location) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Arc_4001(modelElement,
				crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationArc_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getPersonType_2015IncomingLinks(View view) {
		PersonType modelElement = (PersonType) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Arc_4001(modelElement,
				crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationArc_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getTechnicalTerm_2016IncomingLinks(View view) {
		TechnicalTerm modelElement = (TechnicalTerm) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Arc_4001(modelElement,
				crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationArc_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getCardFile_2017IncomingLinks(View view) {
		CardFile modelElement = (CardFile) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Arc_4001(modelElement,
				crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationArc_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getDocument_2018IncomingLinks(View view) {
		Document modelElement = (Document) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Arc_4001(modelElement,
				crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationArc_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getFile_2019IncomingLinks(View view) {
		File modelElement = (File) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Arc_4001(modelElement,
				crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationArc_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getObjective_2020IncomingLinks(View view) {
		Objective modelElement = (Objective) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Arc_4001(modelElement,
				crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationArc_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getProduct_2021IncomingLinks(View view) {
		Product modelElement = (Product) view.getElement();
		Map crossReferences = EcoreUtil.CrossReferencer.find(view.eResource()
				.getResourceSet().getResources());
		List result = new LinkedList();
		result.addAll(getIncomingTypeModelFacetLinks_Arc_4001(modelElement,
				crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Relation_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_InformationArc_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getArc_4001IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getRelation_4002IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getInformationArc_4003IncomingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getOR_2001OutgoingLinks(View view) {
		org.bflow.toolbox.epc.OR modelElement = (org.bflow.toolbox.epc.OR) view
				.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Arc_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Relation_4002(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationArc_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getParticipant_2002OutgoingLinks(View view) {
		Participant modelElement = (Participant) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Arc_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Relation_4002(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationArc_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getAND_2003OutgoingLinks(View view) {
		org.bflow.toolbox.epc.AND modelElement = (org.bflow.toolbox.epc.AND) view
				.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Arc_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Relation_4002(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationArc_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getApplication_2004OutgoingLinks(View view) {
		Application modelElement = (Application) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Arc_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Relation_4002(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationArc_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getProcessInterface_2005OutgoingLinks(View view) {
		ProcessInterface modelElement = (ProcessInterface) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Arc_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Relation_4002(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationArc_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getEvent_2006OutgoingLinks(View view) {
		Event modelElement = (Event) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Arc_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Relation_4002(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationArc_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getFunction_2007OutgoingLinks(View view) {
		Function modelElement = (Function) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Arc_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Relation_4002(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationArc_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getXOR_2008OutgoingLinks(View view) {
		org.bflow.toolbox.epc.XOR modelElement = (org.bflow.toolbox.epc.XOR) view
				.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Arc_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Relation_4002(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationArc_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getGroup_2009OutgoingLinks(View view) {
		Group modelElement = (Group) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Arc_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Relation_4002(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationArc_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getCluster_2010OutgoingLinks(View view) {
		Cluster modelElement = (Cluster) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Arc_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Relation_4002(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationArc_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getExternalPerson_2011OutgoingLinks(View view) {
		ExternalPerson modelElement = (ExternalPerson) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Arc_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Relation_4002(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationArc_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getInternalPerson_2012OutgoingLinks(View view) {
		InternalPerson modelElement = (InternalPerson) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Arc_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Relation_4002(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationArc_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getPosition_2013OutgoingLinks(View view) {
		Position modelElement = (Position) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Arc_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Relation_4002(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationArc_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getLocation_2014OutgoingLinks(View view) {
		Location modelElement = (Location) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Arc_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Relation_4002(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationArc_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getPersonType_2015OutgoingLinks(View view) {
		PersonType modelElement = (PersonType) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Arc_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Relation_4002(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationArc_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getTechnicalTerm_2016OutgoingLinks(View view) {
		TechnicalTerm modelElement = (TechnicalTerm) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Arc_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Relation_4002(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationArc_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getCardFile_2017OutgoingLinks(View view) {
		CardFile modelElement = (CardFile) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Arc_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Relation_4002(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationArc_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getDocument_2018OutgoingLinks(View view) {
		Document modelElement = (Document) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Arc_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Relation_4002(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationArc_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getFile_2019OutgoingLinks(View view) {
		File modelElement = (File) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Arc_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Relation_4002(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationArc_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getObjective_2020OutgoingLinks(View view) {
		Objective modelElement = (Objective) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Arc_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Relation_4002(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationArc_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getProduct_2021OutgoingLinks(View view) {
		Product modelElement = (Product) view.getElement();
		List result = new LinkedList();
		result.addAll(getOutgoingTypeModelFacetLinks_Arc_4001(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_Relation_4002(modelElement));
		result
				.addAll(getOutgoingTypeModelFacetLinks_InformationArc_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List getArc_4001OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getRelation_4002OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public static List getInformationArc_4003OutgoingLinks(View view) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_Arc_4001(
			Epc container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getConnections().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Arc) {
				continue;
			}
			Arc link = (Arc) linkObject;
			if (ArcEditPart.VISUAL_ID != EpcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element dst = link.getTo();
			Element src = link.getFrom();
			result.add(new EpcLinkDescriptor(src, dst, link,
					EpcElementTypes.Arc_4001, ArcEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_Relation_4002(
			Epc container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getConnections().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Relation) {
				continue;
			}
			Relation link = (Relation) linkObject;
			if (RelationEditPart.VISUAL_ID != EpcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element dst = link.getTo();
			Element src = link.getFrom();
			result.add(new EpcLinkDescriptor(src, dst, link,
					EpcElementTypes.Relation_4002, RelationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getContainedTypeModelFacetLinks_InformationArc_4003(
			Epc container) {
		Collection result = new LinkedList();
		for (Iterator links = container.getConnections().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof InformationArc) {
				continue;
			}
			InformationArc link = (InformationArc) linkObject;
			if (InformationArcEditPart.VISUAL_ID != EpcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element dst = link.getTo();
			Element src = link.getFrom();
			result.add(new EpcLinkDescriptor(src, dst, link,
					EpcElementTypes.InformationArc_4003,
					InformationArcEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_Arc_4001(
			Element target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != BflowPackage.eINSTANCE
					.getConnection_To()
					|| false == setting.getEObject() instanceof Arc) {
				continue;
			}
			Arc link = (Arc) setting.getEObject();
			if (ArcEditPart.VISUAL_ID != EpcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element src = link.getFrom();
			result.add(new EpcLinkDescriptor(src, target, link,
					EpcElementTypes.Arc_4001, ArcEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_Relation_4002(
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
			if (RelationEditPart.VISUAL_ID != EpcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element src = link.getFrom();
			result.add(new EpcLinkDescriptor(src, target, link,
					EpcElementTypes.Relation_4002, RelationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getIncomingTypeModelFacetLinks_InformationArc_4003(
			Element target, Map crossReferences) {
		Collection result = new LinkedList();
		Collection settings = (Collection) crossReferences.get(target);
		for (Iterator it = settings.iterator(); it.hasNext();) {
			EStructuralFeature.Setting setting = (EStructuralFeature.Setting) it
					.next();
			if (setting.getEStructuralFeature() != BflowPackage.eINSTANCE
					.getConnection_To()
					|| false == setting.getEObject() instanceof InformationArc) {
				continue;
			}
			InformationArc link = (InformationArc) setting.getEObject();
			if (InformationArcEditPart.VISUAL_ID != EpcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element src = link.getFrom();
			result.add(new EpcLinkDescriptor(src, target, link,
					EpcElementTypes.InformationArc_4003,
					InformationArcEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_Arc_4001(
			Element source) {
		Epc container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Epc) {
				container = (Epc) element;
			}
		}
		if (container == null) {
			return Collections.EMPTY_LIST;
		}
		Collection result = new LinkedList();
		for (Iterator links = container.getConnections().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Arc) {
				continue;
			}
			Arc link = (Arc) linkObject;
			if (ArcEditPart.VISUAL_ID != EpcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element dst = link.getTo();
			Element src = link.getFrom();
			if (src != source) {
				continue;
			}
			result.add(new EpcLinkDescriptor(src, dst, link,
					EpcElementTypes.Arc_4001, ArcEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_Relation_4002(
			Element source) {
		Epc container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Epc) {
				container = (Epc) element;
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
			if (RelationEditPart.VISUAL_ID != EpcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element dst = link.getTo();
			Element src = link.getFrom();
			if (src != source) {
				continue;
			}
			result.add(new EpcLinkDescriptor(src, dst, link,
					EpcElementTypes.Relation_4002, RelationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection getOutgoingTypeModelFacetLinks_InformationArc_4003(
			Element source) {
		Epc container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof Epc) {
				container = (Epc) element;
			}
		}
		if (container == null) {
			return Collections.EMPTY_LIST;
		}
		Collection result = new LinkedList();
		for (Iterator links = container.getConnections().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof InformationArc) {
				continue;
			}
			InformationArc link = (InformationArc) linkObject;
			if (InformationArcEditPart.VISUAL_ID != EpcVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element dst = link.getTo();
			Element src = link.getFrom();
			if (src != source) {
				continue;
			}
			result.add(new EpcLinkDescriptor(src, dst, link,
					EpcElementTypes.InformationArc_4003,
					InformationArcEditPart.VISUAL_ID));
		}
		return result;
	}

}

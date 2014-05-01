package oepc.diagram.part;

import oepc.OepcPackage;
import oepc.diagram.edit.parts.ANDConnectorEditPart;
import oepc.diagram.edit.parts.BusinessAttributeEditPart;
import oepc.diagram.edit.parts.BusinessMethodEditPart;
import oepc.diagram.edit.parts.BusinessObjectBusinessObjectAttributeCompartmentEditPart;
import oepc.diagram.edit.parts.BusinessObjectBusinessObjectMethodCompartmentEditPart;
import oepc.diagram.edit.parts.BusinessObjectEditPart;
import oepc.diagram.edit.parts.BusinessObjectNameEditPart;
import oepc.diagram.edit.parts.ControlFlowEdgeEditPart;
import oepc.diagram.edit.parts.DocumentEditPart;
import oepc.diagram.edit.parts.DocumentNameEditPart;
import oepc.diagram.edit.parts.EventEditPart;
import oepc.diagram.edit.parts.EventNameEditPart;
import oepc.diagram.edit.parts.ITSystemEditPart;
import oepc.diagram.edit.parts.ITSystemNameEditPart;
import oepc.diagram.edit.parts.InformationEdgeEditPart;
import oepc.diagram.edit.parts.OEPCEditPart;
import oepc.diagram.edit.parts.ORConnectorEditPart;
import oepc.diagram.edit.parts.OrganisationUnitEditPart;
import oepc.diagram.edit.parts.OrganisationUnitNameEditPart;
import oepc.diagram.edit.parts.XORConnectorEditPart;
import oepc.diagram.extensions.edit.parts.legend.LegendEditPart;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class OepcVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "org.bflow.toolbox.oepc.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (OEPCEditPart.MODEL_ID.equals(view.getType())) {
				return OEPCEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return oepc.diagram.part.OepcVisualIDRegistry.getVisualID(view
				.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(
					Platform.getDebugOption(DEBUG_KEY))) {
				OepcDiagramEditorPlugin.getInstance().logError(
						"Unable to parse view type as a visualID number: "
								+ type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID) {
		return String.valueOf(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (OepcPackage.eINSTANCE.getOEPC().isSuperTypeOf(
				domainElement.eClass())
				&& isDiagram((oepc.OEPC) domainElement)) {
			return OEPCEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = oepc.diagram.part.OepcVisualIDRegistry
				.getModelID(containerView);
		if (!OEPCEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (OEPCEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = oepc.diagram.part.OepcVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = OEPCEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case BusinessObjectBusinessObjectAttributeCompartmentEditPart.VISUAL_ID:
			if (OepcPackage.eINSTANCE.getBusinessAttribute().isSuperTypeOf(
					domainElement.eClass())) {
				return BusinessAttributeEditPart.VISUAL_ID;
			}
			break;
		case BusinessObjectBusinessObjectMethodCompartmentEditPart.VISUAL_ID:
			if (OepcPackage.eINSTANCE.getBusinessMethod().isSuperTypeOf(
					domainElement.eClass())) {
				return BusinessMethodEditPart.VISUAL_ID;
			}
			break;
		case OEPCEditPart.VISUAL_ID:
			if (OepcPackage.eINSTANCE.getEvent().isSuperTypeOf(
					domainElement.eClass())) {
				return EventEditPart.VISUAL_ID;
			}
			if (OepcPackage.eINSTANCE.getITSystem().isSuperTypeOf(
					domainElement.eClass())) {
				return ITSystemEditPart.VISUAL_ID;
			}
			if (OepcPackage.eINSTANCE.getOrganisationUnit().isSuperTypeOf(
					domainElement.eClass())) {
				return OrganisationUnitEditPart.VISUAL_ID;
			}
			if (OepcPackage.eINSTANCE.getXORConnector().isSuperTypeOf(
					domainElement.eClass())) {
				return XORConnectorEditPart.VISUAL_ID;
			}
			if (OepcPackage.eINSTANCE.getBusinessObject().isSuperTypeOf(
					domainElement.eClass())) {
				return BusinessObjectEditPart.VISUAL_ID;
			}
			if (OepcPackage.eINSTANCE.getANDConnector().isSuperTypeOf(
					domainElement.eClass())) {
				return ANDConnectorEditPart.VISUAL_ID;
			}
			if (OepcPackage.eINSTANCE.getORConnector().isSuperTypeOf(
					domainElement.eClass())) {
				return ORConnectorEditPart.VISUAL_ID;
			}
			if (OepcPackage.eINSTANCE.getDocument().isSuperTypeOf(
					domainElement.eClass())) {
				return DocumentEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated NOT
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		if(nodeVisualID == LegendEditPart.VISUAL_ID){
			return true;
		}
		
		String containerModelID = oepc.diagram.part.OepcVisualIDRegistry
				.getModelID(containerView);
		if (!OEPCEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (OEPCEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = oepc.diagram.part.OepcVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = OEPCEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case EventEditPart.VISUAL_ID:
			if (EventNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ITSystemEditPart.VISUAL_ID:
			if (ITSystemNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case OrganisationUnitEditPart.VISUAL_ID:
			if (OrganisationUnitNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BusinessObjectEditPart.VISUAL_ID:
			if (BusinessObjectNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BusinessObjectBusinessObjectAttributeCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BusinessObjectBusinessObjectMethodCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DocumentEditPart.VISUAL_ID:
			if (DocumentNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BusinessObjectBusinessObjectAttributeCompartmentEditPart.VISUAL_ID:
			if (BusinessAttributeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BusinessObjectBusinessObjectMethodCompartmentEditPart.VISUAL_ID:
			if (BusinessMethodEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case OEPCEditPart.VISUAL_ID:
			if (EventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ITSystemEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (OrganisationUnitEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (XORConnectorEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BusinessObjectEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ANDConnectorEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ORConnectorEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DocumentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (OepcPackage.eINSTANCE.getControlFlowEdge().isSuperTypeOf(
				domainElement.eClass())) {
			return ControlFlowEdgeEditPart.VISUAL_ID;
		}
		if (OepcPackage.eINSTANCE.getInformationEdge().isSuperTypeOf(
				domainElement.eClass())) {
			return InformationEdgeEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(oepc.OEPC element) {
		return true;
	}

}

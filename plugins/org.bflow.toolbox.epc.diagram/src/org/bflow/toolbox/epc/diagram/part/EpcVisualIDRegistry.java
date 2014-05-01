package org.bflow.toolbox.epc.diagram.part;

import org.bflow.toolbox.epc.Epc;
import org.bflow.toolbox.epc.EpcPackage;
import org.bflow.toolbox.epc.diagram.edit.parts.ANDEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ApplicationEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ApplicationNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ArcEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.CardFileEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.CardFileNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ClusterEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ClusterNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.DocumentEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.DocumentNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.EpcEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.EventEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.EventNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ExternalPersonEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ExternalPersonNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.FileEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.FileNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.FunctionEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.FunctionNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.GroupEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.GroupNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.InformationArcEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.InternalPersonEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.InternalPersonNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.LocationEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.LocationNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.OREditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ObjectiveEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ObjectiveNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ParticipantEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ParticipantNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.PersonTypeEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.PersonTypeNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.PositionEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.PositionNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ProcessInterfaceEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ProcessInterfaceNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ProductEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ProductNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.RelationEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.TechnicalTermEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.TechnicalTermNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.XOREditPart;
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
public class EpcVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "org.bflow.toolbox.epc.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (EpcEditPart.MODEL_ID.equals(view.getType())) {
				return EpcEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return org.bflow.toolbox.epc.diagram.part.EpcVisualIDRegistry
				.getVisualID(view.getType());
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
				EpcDiagramEditorPlugin.getInstance().logError(
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
		if (EpcPackage.eINSTANCE.getEpc().isSuperTypeOf(domainElement.eClass())
				&& isDiagram((Epc) domainElement)) {
			return EpcEditPart.VISUAL_ID;
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
		String containerModelID = org.bflow.toolbox.epc.diagram.part.EpcVisualIDRegistry
				.getModelID(containerView);
		if (!EpcEditPart.MODEL_ID.equals(containerModelID)
				&& !"epc".equals(containerModelID)) { //$NON-NLS-1$
			return -1;
		}
		int containerVisualID;
		if (EpcEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = org.bflow.toolbox.epc.diagram.part.EpcVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = EpcEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case EpcEditPart.VISUAL_ID:
			if (EpcPackage.eINSTANCE.getOR().isSuperTypeOf(
					domainElement.eClass())) {
				return OREditPart.VISUAL_ID;
			}
			if (EpcPackage.eINSTANCE.getParticipant().isSuperTypeOf(
					domainElement.eClass())) {
				return ParticipantEditPart.VISUAL_ID;
			}
			if (EpcPackage.eINSTANCE.getAND().isSuperTypeOf(
					domainElement.eClass())) {
				return ANDEditPart.VISUAL_ID;
			}
			if (EpcPackage.eINSTANCE.getApplication().isSuperTypeOf(
					domainElement.eClass())) {
				return ApplicationEditPart.VISUAL_ID;
			}
			if (EpcPackage.eINSTANCE.getProcessInterface().isSuperTypeOf(
					domainElement.eClass())) {
				return ProcessInterfaceEditPart.VISUAL_ID;
			}
			if (EpcPackage.eINSTANCE.getEvent().isSuperTypeOf(
					domainElement.eClass())) {
				return EventEditPart.VISUAL_ID;
			}
			if (EpcPackage.eINSTANCE.getFunction().isSuperTypeOf(
					domainElement.eClass())) {
				return FunctionEditPart.VISUAL_ID;
			}
			if (EpcPackage.eINSTANCE.getXOR().isSuperTypeOf(
					domainElement.eClass())) {
				return XOREditPart.VISUAL_ID;
			}
			if (EpcPackage.eINSTANCE.getGroup().isSuperTypeOf(
					domainElement.eClass())) {
				return GroupEditPart.VISUAL_ID;
			}
			if (EpcPackage.eINSTANCE.getCluster().isSuperTypeOf(
					domainElement.eClass())) {
				return ClusterEditPart.VISUAL_ID;
			}
			if (EpcPackage.eINSTANCE.getExternalPerson().isSuperTypeOf(
					domainElement.eClass())) {
				return ExternalPersonEditPart.VISUAL_ID;
			}
			if (EpcPackage.eINSTANCE.getInternalPerson().isSuperTypeOf(
					domainElement.eClass())) {
				return InternalPersonEditPart.VISUAL_ID;
			}
			if (EpcPackage.eINSTANCE.getPosition().isSuperTypeOf(
					domainElement.eClass())) {
				return PositionEditPart.VISUAL_ID;
			}
			if (EpcPackage.eINSTANCE.getLocation().isSuperTypeOf(
					domainElement.eClass())) {
				return LocationEditPart.VISUAL_ID;
			}
			if (EpcPackage.eINSTANCE.getPersonType().isSuperTypeOf(
					domainElement.eClass())) {
				return PersonTypeEditPart.VISUAL_ID;
			}
			if (EpcPackage.eINSTANCE.getTechnicalTerm().isSuperTypeOf(
					domainElement.eClass())) {
				return TechnicalTermEditPart.VISUAL_ID;
			}
			if (EpcPackage.eINSTANCE.getCardFile().isSuperTypeOf(
					domainElement.eClass())) {
				return CardFileEditPart.VISUAL_ID;
			}
			if (EpcPackage.eINSTANCE.getDocument().isSuperTypeOf(
					domainElement.eClass())) {
				return DocumentEditPart.VISUAL_ID;
			}
			if (EpcPackage.eINSTANCE.getFile().isSuperTypeOf(
					domainElement.eClass())) {
				return FileEditPart.VISUAL_ID;
			}
			if (EpcPackage.eINSTANCE.getObjective().isSuperTypeOf(
					domainElement.eClass())) {
				return ObjectiveEditPart.VISUAL_ID;
			}
			if (EpcPackage.eINSTANCE.getProduct().isSuperTypeOf(
					domainElement.eClass())) {
				return ProductEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = org.bflow.toolbox.epc.diagram.part.EpcVisualIDRegistry
				.getModelID(containerView);
		if (!EpcEditPart.MODEL_ID.equals(containerModelID)
				&& !"epc".equals(containerModelID)) { //$NON-NLS-1$
			return false;
		}
		int containerVisualID;
		if (EpcEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = org.bflow.toolbox.epc.diagram.part.EpcVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = EpcEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case ParticipantEditPart.VISUAL_ID:
			if (ParticipantNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ApplicationEditPart.VISUAL_ID:
			if (ApplicationNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ProcessInterfaceEditPart.VISUAL_ID:
			if (ProcessInterfaceNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EventEditPart.VISUAL_ID:
			if (EventNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case FunctionEditPart.VISUAL_ID:
			if (FunctionNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case GroupEditPart.VISUAL_ID:
			if (GroupNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ClusterEditPart.VISUAL_ID:
			if (ClusterNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ExternalPersonEditPart.VISUAL_ID:
			if (ExternalPersonNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case InternalPersonEditPart.VISUAL_ID:
			if (InternalPersonNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case PositionEditPart.VISUAL_ID:
			if (PositionNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case LocationEditPart.VISUAL_ID:
			if (LocationNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case PersonTypeEditPart.VISUAL_ID:
			if (PersonTypeNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TechnicalTermEditPart.VISUAL_ID:
			if (TechnicalTermNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case CardFileEditPart.VISUAL_ID:
			if (CardFileNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DocumentEditPart.VISUAL_ID:
			if (DocumentNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case FileEditPart.VISUAL_ID:
			if (FileNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ObjectiveEditPart.VISUAL_ID:
			if (ObjectiveNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ProductEditPart.VISUAL_ID:
			if (ProductNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EpcEditPart.VISUAL_ID:
			if (OREditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ParticipantEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ANDEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ApplicationEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ProcessInterfaceEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (FunctionEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (XOREditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (GroupEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ClusterEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ExternalPersonEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (InternalPersonEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (PositionEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (LocationEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (PersonTypeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TechnicalTermEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (CardFileEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DocumentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (FileEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ObjectiveEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ProductEditPart.VISUAL_ID == nodeVisualID) {
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
		if (EpcPackage.eINSTANCE.getArc().isSuperTypeOf(domainElement.eClass())) {
			return ArcEditPart.VISUAL_ID;
		}
		if (EpcPackage.eINSTANCE.getRelation().isSuperTypeOf(
				domainElement.eClass())) {
			return RelationEditPart.VISUAL_ID;
		}
		if (EpcPackage.eINSTANCE.getInformationArc().isSuperTypeOf(
				domainElement.eClass())) {
			return InformationArcEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(Epc element) {
		return true;
	}

}

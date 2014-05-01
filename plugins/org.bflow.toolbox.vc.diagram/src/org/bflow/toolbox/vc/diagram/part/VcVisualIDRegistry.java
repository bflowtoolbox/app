package org.bflow.toolbox.vc.diagram.part;

import org.bflow.toolbox.vc.Vc;
import org.bflow.toolbox.vc.VcPackage;
import org.bflow.toolbox.vc.diagram.edit.parts.ClusterEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.ClusterNameEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.ObjectiveEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.ObjectiveNameEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.PredecessorConnectionEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.ProcessSuperiorityEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.ProductEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.ProductNameEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.RelationEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.TechnicalTermEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.TechnicalTermNameEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.VCEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.ValueChain2EditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.ValueChain2NameEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.ValueChainEditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.ValueChainNameEditPart;
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
public class VcVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "org.bflow.toolbox.vc.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (VCEditPart.MODEL_ID.equals(view.getType())) {
				return VCEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return org.bflow.toolbox.vc.diagram.part.VcVisualIDRegistry
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
				VcDiagramEditorPlugin.getInstance().logError(
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
		if (VcPackage.eINSTANCE.getVc().isSuperTypeOf(domainElement.eClass())
				&& isDiagram((Vc) domainElement)) {
			return VCEditPart.VISUAL_ID;
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
		String containerModelID = org.bflow.toolbox.vc.diagram.part.VcVisualIDRegistry
				.getModelID(containerView);
		if (!VCEditPart.MODEL_ID.equals(containerModelID)
				&& !"vc".equals(containerModelID)) { //$NON-NLS-1$
			return -1;
		}
		int containerVisualID;
		if (VCEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = org.bflow.toolbox.vc.diagram.part.VcVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = VCEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case VCEditPart.VISUAL_ID:
			if (VcPackage.eINSTANCE.getValueChain().isSuperTypeOf(
					domainElement.eClass())) {
				return ValueChainEditPart.VISUAL_ID;
			}
			if (VcPackage.eINSTANCE.getValueChain2().isSuperTypeOf(
					domainElement.eClass())) {
				return ValueChain2EditPart.VISUAL_ID;
			}
			if (VcPackage.eINSTANCE.getTechnicalTerm().isSuperTypeOf(
					domainElement.eClass())) {
				return TechnicalTermEditPart.VISUAL_ID;
			}
			if (VcPackage.eINSTANCE.getCluster().isSuperTypeOf(
					domainElement.eClass())) {
				return ClusterEditPart.VISUAL_ID;
			}
			if (VcPackage.eINSTANCE.getObjective().isSuperTypeOf(
					domainElement.eClass())) {
				return ObjectiveEditPart.VISUAL_ID;
			}
			if (VcPackage.eINSTANCE.getProduct().isSuperTypeOf(
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
		String containerModelID = org.bflow.toolbox.vc.diagram.part.VcVisualIDRegistry
				.getModelID(containerView);
		if (!VCEditPart.MODEL_ID.equals(containerModelID)
				&& !"vc".equals(containerModelID)) { //$NON-NLS-1$
			return false;
		}
		int containerVisualID;
		if (VCEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = org.bflow.toolbox.vc.diagram.part.VcVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = VCEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case ValueChainEditPart.VISUAL_ID:
			if (ValueChainNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ValueChain2EditPart.VISUAL_ID:
			if (ValueChain2NameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TechnicalTermEditPart.VISUAL_ID:
			if (TechnicalTermNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ClusterEditPart.VISUAL_ID:
			if (ClusterNameEditPart.VISUAL_ID == nodeVisualID) {
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
		case VCEditPart.VISUAL_ID:
			if (ValueChainEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ValueChain2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TechnicalTermEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ClusterEditPart.VISUAL_ID == nodeVisualID) {
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
		if (VcPackage.eINSTANCE.getPredecessorConnection().isSuperTypeOf(
				domainElement.eClass())) {
			return PredecessorConnectionEditPart.VISUAL_ID;
		}
		if (VcPackage.eINSTANCE.getProcessSuperiority().isSuperTypeOf(
				domainElement.eClass())) {
			return ProcessSuperiorityEditPart.VISUAL_ID;
		}
		if (VcPackage.eINSTANCE.getRelation().isSuperTypeOf(
				domainElement.eClass())) {
			return RelationEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(Vc element) {
		return true;
	}

}

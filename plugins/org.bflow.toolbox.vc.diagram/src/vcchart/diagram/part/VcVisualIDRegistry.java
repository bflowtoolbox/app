/*
 * 
 */
package vcchart.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.structure.DiagramStructure;

import vcchart.Model;
import vcchart.VcchartPackage;
import vcchart.diagram.edit.parts.Activity1EditPart;
import vcchart.diagram.edit.parts.Activity1NameEditPart;
import vcchart.diagram.edit.parts.Activity2EditPart;
import vcchart.diagram.edit.parts.Activity2NameEditPart;
import vcchart.diagram.edit.parts.ApplicationEditPart;
import vcchart.diagram.edit.parts.ApplicationNameEditPart;
import vcchart.diagram.edit.parts.ClusterEditPart;
import vcchart.diagram.edit.parts.ClusterNameEditPart;
import vcchart.diagram.edit.parts.DocumentEditPart;
import vcchart.diagram.edit.parts.DocumentNameEditPart;
import vcchart.diagram.edit.parts.ModelEditPart;
import vcchart.diagram.edit.parts.ObjectiveEditPart;
import vcchart.diagram.edit.parts.ObjectiveNameEditPart;
import vcchart.diagram.edit.parts.ParticipantEditPart;
import vcchart.diagram.edit.parts.ParticipantNameEditPart;
import vcchart.diagram.edit.parts.ProductEditPart;
import vcchart.diagram.edit.parts.ProductNameEditPart;
import vcchart.diagram.edit.parts.Relation1EditPart;
import vcchart.diagram.edit.parts.Relation2EditPart;
import vcchart.diagram.edit.parts.Relation3EditPart;
import vcchart.diagram.edit.parts.TechnicalTermEditPart;
import vcchart.diagram.edit.parts.TechnicalTermNameEditPart;

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
			if (ModelEditPart.MODEL_ID.equals(view.getType())) {
				return ModelEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return vcchart.diagram.part.VcVisualIDRegistry.getVisualID(view
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
		return Integer.toString(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (VcchartPackage.eINSTANCE.getModel().isSuperTypeOf(
				domainElement.eClass())
				&& isDiagram((Model) domainElement)) {
			return ModelEditPart.VISUAL_ID;
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
		String containerModelID = vcchart.diagram.part.VcVisualIDRegistry
				.getModelID(containerView);
		if (!ModelEditPart.MODEL_ID.equals(containerModelID)
				&& !"vcchart".equals(containerModelID)) { //$NON-NLS-1$
			return -1;
		}
		int containerVisualID;
		if (ModelEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = vcchart.diagram.part.VcVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = ModelEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case ModelEditPart.VISUAL_ID:
			if (VcchartPackage.eINSTANCE.getProduct().isSuperTypeOf(
					domainElement.eClass())) {
				return ProductEditPart.VISUAL_ID;
			}
			if (VcchartPackage.eINSTANCE.getObjective().isSuperTypeOf(
					domainElement.eClass())) {
				return ObjectiveEditPart.VISUAL_ID;
			}
			if (VcchartPackage.eINSTANCE.getActivity1().isSuperTypeOf(
					domainElement.eClass())) {
				return Activity1EditPart.VISUAL_ID;
			}
			if (VcchartPackage.eINSTANCE.getActivity2().isSuperTypeOf(
					domainElement.eClass())) {
				return Activity2EditPart.VISUAL_ID;
			}
			if (VcchartPackage.eINSTANCE.getCluster().isSuperTypeOf(
					domainElement.eClass())) {
				return ClusterEditPart.VISUAL_ID;
			}
			if (VcchartPackage.eINSTANCE.getTechnicalTerm().isSuperTypeOf(
					domainElement.eClass())) {
				return TechnicalTermEditPart.VISUAL_ID;
			}
			if (VcchartPackage.eINSTANCE.getParticipant().isSuperTypeOf(
					domainElement.eClass())) {
				return ParticipantEditPart.VISUAL_ID;
			}
			if (VcchartPackage.eINSTANCE.getApplication().isSuperTypeOf(
					domainElement.eClass())) {
				return ApplicationEditPart.VISUAL_ID;
			}
			if (VcchartPackage.eINSTANCE.getDocument().isSuperTypeOf(
					domainElement.eClass())) {
				return DocumentEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = vcchart.diagram.part.VcVisualIDRegistry
				.getModelID(containerView);
		if (!ModelEditPart.MODEL_ID.equals(containerModelID)
				&& !"vcchart".equals(containerModelID)) { //$NON-NLS-1$
			return false;
		}
		int containerVisualID;
		if (ModelEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = vcchart.diagram.part.VcVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = ModelEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case ModelEditPart.VISUAL_ID:
			if (ProductEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ObjectiveEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Activity1EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Activity2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ClusterEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TechnicalTermEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ParticipantEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ApplicationEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DocumentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ProductEditPart.VISUAL_ID:
			if (ProductNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ObjectiveEditPart.VISUAL_ID:
			if (ObjectiveNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Activity1EditPart.VISUAL_ID:
			if (Activity1NameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Activity2EditPart.VISUAL_ID:
			if (Activity2NameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ClusterEditPart.VISUAL_ID:
			if (ClusterNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TechnicalTermEditPart.VISUAL_ID:
			if (TechnicalTermNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
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
		case DocumentEditPart.VISUAL_ID:
			if (DocumentNameEditPart.VISUAL_ID == nodeVisualID) {
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
		if (VcchartPackage.eINSTANCE.getRelation1().isSuperTypeOf(
				domainElement.eClass())) {
			return Relation1EditPart.VISUAL_ID;
		}
		if (VcchartPackage.eINSTANCE.getRelation2().isSuperTypeOf(
				domainElement.eClass())) {
			return Relation2EditPart.VISUAL_ID;
		}
		if (VcchartPackage.eINSTANCE.getRelation3().isSuperTypeOf(
				domainElement.eClass())) {
			return Relation3EditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(Model element) {
		return true;
	}

	/**
	 * @generated
	 */
	public static boolean checkNodeVisualID(View containerView,
			EObject domainElement, int candidate) {
		if (candidate == -1) {
			//unrecognized id is always bad
			return false;
		}
		int basic = getNodeVisualID(containerView, domainElement);
		return basic == candidate;
	}

	/**
	 * @generated
	 */
	public static boolean isCompartmentVisualID(int visualID) {
		return false;
	}

	/**
	 * @generated
	 */
	public static boolean isSemanticLeafVisualID(int visualID) {
		switch (visualID) {
		case ModelEditPart.VISUAL_ID:
			return false;
		case ProductEditPart.VISUAL_ID:
		case ObjectiveEditPart.VISUAL_ID:
		case Activity1EditPart.VISUAL_ID:
		case Activity2EditPart.VISUAL_ID:
		case ClusterEditPart.VISUAL_ID:
		case TechnicalTermEditPart.VISUAL_ID:
		case ParticipantEditPart.VISUAL_ID:
		case ApplicationEditPart.VISUAL_ID:
		case DocumentEditPart.VISUAL_ID:
			return true;
		default:
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static final DiagramStructure TYPED_INSTANCE = new DiagramStructure() {
		/**
		 * @generated
		 */
		@Override
		public int getVisualID(View view) {
			return vcchart.diagram.part.VcVisualIDRegistry.getVisualID(view);
		}

		/**
		 * @generated
		 */
		@Override
		public String getModelID(View view) {
			return vcchart.diagram.part.VcVisualIDRegistry.getModelID(view);
		}

		/**
		 * @generated
		 */
		@Override
		public int getNodeVisualID(View containerView, EObject domainElement) {
			return vcchart.diagram.part.VcVisualIDRegistry.getNodeVisualID(
					containerView, domainElement);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean checkNodeVisualID(View containerView,
				EObject domainElement, int candidate) {
			return vcchart.diagram.part.VcVisualIDRegistry.checkNodeVisualID(
					containerView, domainElement, candidate);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean isCompartmentVisualID(int visualID) {
			return vcchart.diagram.part.VcVisualIDRegistry
					.isCompartmentVisualID(visualID);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean isSemanticLeafVisualID(int visualID) {
			return vcchart.diagram.part.VcVisualIDRegistry
					.isSemanticLeafVisualID(visualID);
		}
	};

}

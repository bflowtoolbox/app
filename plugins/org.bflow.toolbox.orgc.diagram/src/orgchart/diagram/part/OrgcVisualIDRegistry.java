/*
 * 
 */
package orgchart.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.structure.DiagramStructure;
import orgchart.Model;
import orgchart.OrgchartPackage;
import orgchart.diagram.edit.parts.ExternalPersonEditPart;
import orgchart.diagram.edit.parts.ExternalPersonNameEditPart;
import orgchart.diagram.edit.parts.GroupEditPart;
import orgchart.diagram.edit.parts.GroupNameEditPart;
import orgchart.diagram.edit.parts.InternalPersonEditPart;
import orgchart.diagram.edit.parts.InternalPersonNameEditPart;
import orgchart.diagram.edit.parts.LocationEditPart;
import orgchart.diagram.edit.parts.LocationNameEditPart;
import orgchart.diagram.edit.parts.ModelEditPart;
import orgchart.diagram.edit.parts.ParticipantEditPart;
import orgchart.diagram.edit.parts.ParticipantNameEditPart;
import orgchart.diagram.edit.parts.PersonTypeEditPart;
import orgchart.diagram.edit.parts.PersonTypeNameEditPart;
import orgchart.diagram.edit.parts.PositionEditPart;
import orgchart.diagram.edit.parts.PositionNameEditPart;
import orgchart.diagram.edit.parts.Relation1EditPart;
import orgchart.diagram.edit.parts.Relation2EditPart;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class OrgcVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "org.bflow.toolbox.orgc.diagram/debug/visualID"; //$NON-NLS-1$

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
		return orgchart.diagram.part.OrgcVisualIDRegistry.getVisualID(view
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
				OrgcDiagramEditorPlugin.getInstance().logError(
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
		if (OrgchartPackage.eINSTANCE.getModel().isSuperTypeOf(
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
		String containerModelID = orgchart.diagram.part.OrgcVisualIDRegistry
				.getModelID(containerView);
		if (!ModelEditPart.MODEL_ID.equals(containerModelID)
				&& !"orgchart".equals(containerModelID)) { //$NON-NLS-1$
			return -1;
		}
		int containerVisualID;
		if (ModelEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = orgchart.diagram.part.OrgcVisualIDRegistry
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
			if (OrgchartPackage.eINSTANCE.getPosition().isSuperTypeOf(
					domainElement.eClass())) {
				return PositionEditPart.VISUAL_ID;
			}
			if (OrgchartPackage.eINSTANCE.getInternalPerson().isSuperTypeOf(
					domainElement.eClass())) {
				return InternalPersonEditPart.VISUAL_ID;
			}
			if (OrgchartPackage.eINSTANCE.getExternalPerson().isSuperTypeOf(
					domainElement.eClass())) {
				return ExternalPersonEditPart.VISUAL_ID;
			}
			if (OrgchartPackage.eINSTANCE.getGroup().isSuperTypeOf(
					domainElement.eClass())) {
				return GroupEditPart.VISUAL_ID;
			}
			if (OrgchartPackage.eINSTANCE.getParticipant().isSuperTypeOf(
					domainElement.eClass())) {
				return ParticipantEditPart.VISUAL_ID;
			}
			if (OrgchartPackage.eINSTANCE.getPersonType().isSuperTypeOf(
					domainElement.eClass())) {
				return PersonTypeEditPart.VISUAL_ID;
			}
			if (OrgchartPackage.eINSTANCE.getLocation().isSuperTypeOf(
					domainElement.eClass())) {
				return LocationEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = orgchart.diagram.part.OrgcVisualIDRegistry
				.getModelID(containerView);
		if (!ModelEditPart.MODEL_ID.equals(containerModelID)
				&& !"orgchart".equals(containerModelID)) { //$NON-NLS-1$
			return false;
		}
		int containerVisualID;
		if (ModelEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = orgchart.diagram.part.OrgcVisualIDRegistry
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
			if (PositionEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (InternalPersonEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ExternalPersonEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (GroupEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ParticipantEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (PersonTypeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (LocationEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case PositionEditPart.VISUAL_ID:
			if (PositionNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case InternalPersonEditPart.VISUAL_ID:
			if (InternalPersonNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ExternalPersonEditPart.VISUAL_ID:
			if (ExternalPersonNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case GroupEditPart.VISUAL_ID:
			if (GroupNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ParticipantEditPart.VISUAL_ID:
			if (ParticipantNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case PersonTypeEditPart.VISUAL_ID:
			if (PersonTypeNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case LocationEditPart.VISUAL_ID:
			if (LocationNameEditPart.VISUAL_ID == nodeVisualID) {
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
		if (OrgchartPackage.eINSTANCE.getRelation1().isSuperTypeOf(
				domainElement.eClass())) {
			return Relation1EditPart.VISUAL_ID;
		}
		if (OrgchartPackage.eINSTANCE.getRelation2().isSuperTypeOf(
				domainElement.eClass())) {
			return Relation2EditPart.VISUAL_ID;
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
		case PositionEditPart.VISUAL_ID:
		case InternalPersonEditPart.VISUAL_ID:
		case ExternalPersonEditPart.VISUAL_ID:
		case GroupEditPart.VISUAL_ID:
		case ParticipantEditPart.VISUAL_ID:
		case PersonTypeEditPart.VISUAL_ID:
		case LocationEditPart.VISUAL_ID:
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
			return orgchart.diagram.part.OrgcVisualIDRegistry.getVisualID(view);
		}

		/**
		 * @generated
		 */
		@Override
		public String getModelID(View view) {
			return orgchart.diagram.part.OrgcVisualIDRegistry.getModelID(view);
		}

		/**
		 * @generated
		 */
		@Override
		public int getNodeVisualID(View containerView, EObject domainElement) {
			return orgchart.diagram.part.OrgcVisualIDRegistry.getNodeVisualID(
					containerView, domainElement);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean checkNodeVisualID(View containerView,
				EObject domainElement, int candidate) {
			return orgchart.diagram.part.OrgcVisualIDRegistry
					.checkNodeVisualID(containerView, domainElement, candidate);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean isCompartmentVisualID(int visualID) {
			return orgchart.diagram.part.OrgcVisualIDRegistry
					.isCompartmentVisualID(visualID);
		}

		/**
		 * @generated
		 */
		@Override
		public boolean isSemanticLeafVisualID(int visualID) {
			return orgchart.diagram.part.OrgcVisualIDRegistry
					.isSemanticLeafVisualID(visualID);
		}
	};

}

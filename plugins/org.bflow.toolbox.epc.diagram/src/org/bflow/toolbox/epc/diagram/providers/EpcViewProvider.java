package org.bflow.toolbox.epc.diagram.providers;

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
import org.bflow.toolbox.epc.diagram.part.EpcVisualIDRegistry;
import org.bflow.toolbox.epc.diagram.view.factories.ANDViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.ApplicationNameViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.ApplicationViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.ArcViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.CardFileNameViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.CardFileViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.ClusterNameViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.ClusterViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.DocumentNameViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.DocumentViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.EpcViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.EventNameViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.EventViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.ExternalPersonNameViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.ExternalPersonViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.FileNameViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.FileViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.FunctionNameViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.FunctionViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.GroupNameViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.GroupViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.InformationArcViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.InternalPersonNameViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.InternalPersonViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.LocationNameViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.LocationViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.ORViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.ObjectiveNameViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.ObjectiveViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.ParticipantNameViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.ParticipantViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.PersonTypeNameViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.PersonTypeViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.PositionNameViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.PositionViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.ProcessInterfaceNameViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.ProcessInterfaceViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.ProductNameViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.ProductViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.RelationViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.TechnicalTermNameViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.TechnicalTermViewFactory;
import org.bflow.toolbox.epc.diagram.view.factories.XORViewFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.providers.AbstractViewProvider;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class EpcViewProvider extends AbstractViewProvider {

	/**
	 * @generated
	 */
	protected Class getDiagramViewClass(IAdaptable semanticAdapter,
			String diagramKind) {
		EObject semanticElement = getSemanticElement(semanticAdapter);
		if (EpcEditPart.MODEL_ID.equals(diagramKind)
				&& EpcVisualIDRegistry.getDiagramVisualID(semanticElement) != -1) {
			return EpcViewFactory.class;
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Class getNodeViewClass(IAdaptable semanticAdapter,
			View containerView, String semanticHint) {
		if (containerView == null) {
			return null;
		}
		IElementType elementType = getSemanticElementType(semanticAdapter);
		EObject domainElement = getSemanticElement(semanticAdapter);
		int visualID;
		if (semanticHint == null) {
			// Semantic hint is not specified. Can be a result of call from CanonicalEditPolicy.
			// In this situation there should be NO elementType, visualID will be determined
			// by VisualIDRegistry.getNodeVisualID() for domainElement.
			if (elementType != null || domainElement == null) {
				return null;
			}
			visualID = EpcVisualIDRegistry.getNodeVisualID(containerView,
					domainElement);
		} else {
			visualID = EpcVisualIDRegistry.getVisualID(semanticHint);
			if (elementType != null) {
				// Semantic hint is specified together with element type.
				// Both parameters should describe exactly the same diagram element.
				// In addition we check that visualID returned by VisualIDRegistry.getNodeVisualID() for
				// domainElement (if specified) is the same as in element type.
				if (!EpcElementTypes.isKnownElementType(elementType)
						|| (!(elementType instanceof IHintedType))) {
					return null; // foreign element type
				}
				String elementTypeHint = ((IHintedType) elementType)
						.getSemanticHint();
				if (!semanticHint.equals(elementTypeHint)) {
					return null; // if semantic hint is specified it should be the same as in element type
				}
				if (domainElement != null
						&& visualID != EpcVisualIDRegistry.getNodeVisualID(
								containerView, domainElement)) {
					return null; // visual id for node EClass should match visual id from element type
				}
			} else {
				// Element type is not specified. Domain element should be present (except pure design elements).
				// This method is called with EObjectAdapter as parameter from:
				//   - ViewService.createNode(View container, EObject eObject, String type, PreferencesHint preferencesHint) 
				//   - generated ViewFactory.decorateView() for parent element
				if (!EpcEditPart.MODEL_ID.equals(EpcVisualIDRegistry
						.getModelID(containerView))) {
					return null; // foreign diagram
				}
				switch (visualID) {
				case OREditPart.VISUAL_ID:
				case ParticipantEditPart.VISUAL_ID:
				case ANDEditPart.VISUAL_ID:
				case ApplicationEditPart.VISUAL_ID:
				case ProcessInterfaceEditPart.VISUAL_ID:
				case EventEditPart.VISUAL_ID:
				case FunctionEditPart.VISUAL_ID:
				case XOREditPart.VISUAL_ID:
				case GroupEditPart.VISUAL_ID:
				case ClusterEditPart.VISUAL_ID:
				case ExternalPersonEditPart.VISUAL_ID:
				case InternalPersonEditPart.VISUAL_ID:
				case PositionEditPart.VISUAL_ID:
				case LocationEditPart.VISUAL_ID:
				case PersonTypeEditPart.VISUAL_ID:
				case TechnicalTermEditPart.VISUAL_ID:
				case CardFileEditPart.VISUAL_ID:
				case DocumentEditPart.VISUAL_ID:
				case FileEditPart.VISUAL_ID:
				case ObjectiveEditPart.VISUAL_ID:
				case ProductEditPart.VISUAL_ID:
					if (domainElement == null
							|| visualID != EpcVisualIDRegistry.getNodeVisualID(
									containerView, domainElement)) {
						return null; // visual id in semantic hint should match visual id for domain element
					}
					break;
				case ParticipantNameEditPart.VISUAL_ID:
					if (ParticipantEditPart.VISUAL_ID != EpcVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case ApplicationNameEditPart.VISUAL_ID:
					if (ApplicationEditPart.VISUAL_ID != EpcVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case ProcessInterfaceNameEditPart.VISUAL_ID:
					if (ProcessInterfaceEditPart.VISUAL_ID != EpcVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case EventNameEditPart.VISUAL_ID:
					if (EventEditPart.VISUAL_ID != EpcVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case FunctionNameEditPart.VISUAL_ID:
					if (FunctionEditPart.VISUAL_ID != EpcVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case GroupNameEditPart.VISUAL_ID:
					if (GroupEditPart.VISUAL_ID != EpcVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case ClusterNameEditPart.VISUAL_ID:
					if (ClusterEditPart.VISUAL_ID != EpcVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case ExternalPersonNameEditPart.VISUAL_ID:
					if (ExternalPersonEditPart.VISUAL_ID != EpcVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case InternalPersonNameEditPart.VISUAL_ID:
					if (InternalPersonEditPart.VISUAL_ID != EpcVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case PositionNameEditPart.VISUAL_ID:
					if (PositionEditPart.VISUAL_ID != EpcVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case LocationNameEditPart.VISUAL_ID:
					if (LocationEditPart.VISUAL_ID != EpcVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case PersonTypeNameEditPart.VISUAL_ID:
					if (PersonTypeEditPart.VISUAL_ID != EpcVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case TechnicalTermNameEditPart.VISUAL_ID:
					if (TechnicalTermEditPart.VISUAL_ID != EpcVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case CardFileNameEditPart.VISUAL_ID:
					if (CardFileEditPart.VISUAL_ID != EpcVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case DocumentNameEditPart.VISUAL_ID:
					if (DocumentEditPart.VISUAL_ID != EpcVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case FileNameEditPart.VISUAL_ID:
					if (FileEditPart.VISUAL_ID != EpcVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case ObjectiveNameEditPart.VISUAL_ID:
					if (ObjectiveEditPart.VISUAL_ID != EpcVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case ProductNameEditPart.VISUAL_ID:
					if (ProductEditPart.VISUAL_ID != EpcVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				default:
					return null;
				}
			}
		}
		return getNodeViewClass(containerView, visualID);
	}

	/**
	 * @generated
	 */
	protected Class getNodeViewClass(View containerView, int visualID) {
		if (containerView == null
				|| !EpcVisualIDRegistry.canCreateNode(containerView, visualID)) {
			return null;
		}
		switch (visualID) {
		case OREditPart.VISUAL_ID:
			return ORViewFactory.class;
		case ParticipantEditPart.VISUAL_ID:
			return ParticipantViewFactory.class;
		case ParticipantNameEditPart.VISUAL_ID:
			return ParticipantNameViewFactory.class;
		case ANDEditPart.VISUAL_ID:
			return ANDViewFactory.class;
		case ApplicationEditPart.VISUAL_ID:
			return ApplicationViewFactory.class;
		case ApplicationNameEditPart.VISUAL_ID:
			return ApplicationNameViewFactory.class;
		case ProcessInterfaceEditPart.VISUAL_ID:
			return ProcessInterfaceViewFactory.class;
		case ProcessInterfaceNameEditPart.VISUAL_ID:
			return ProcessInterfaceNameViewFactory.class;
		case EventEditPart.VISUAL_ID:
			return EventViewFactory.class;
		case EventNameEditPart.VISUAL_ID:
			return EventNameViewFactory.class;
		case FunctionEditPart.VISUAL_ID:
			return FunctionViewFactory.class;
		case FunctionNameEditPart.VISUAL_ID:
			return FunctionNameViewFactory.class;
		case XOREditPart.VISUAL_ID:
			return XORViewFactory.class;
		case GroupEditPart.VISUAL_ID:
			return GroupViewFactory.class;
		case GroupNameEditPart.VISUAL_ID:
			return GroupNameViewFactory.class;
		case ClusterEditPart.VISUAL_ID:
			return ClusterViewFactory.class;
		case ClusterNameEditPart.VISUAL_ID:
			return ClusterNameViewFactory.class;
		case ExternalPersonEditPart.VISUAL_ID:
			return ExternalPersonViewFactory.class;
		case ExternalPersonNameEditPart.VISUAL_ID:
			return ExternalPersonNameViewFactory.class;
		case InternalPersonEditPart.VISUAL_ID:
			return InternalPersonViewFactory.class;
		case InternalPersonNameEditPart.VISUAL_ID:
			return InternalPersonNameViewFactory.class;
		case PositionEditPart.VISUAL_ID:
			return PositionViewFactory.class;
		case PositionNameEditPart.VISUAL_ID:
			return PositionNameViewFactory.class;
		case LocationEditPart.VISUAL_ID:
			return LocationViewFactory.class;
		case LocationNameEditPart.VISUAL_ID:
			return LocationNameViewFactory.class;
		case PersonTypeEditPart.VISUAL_ID:
			return PersonTypeViewFactory.class;
		case PersonTypeNameEditPart.VISUAL_ID:
			return PersonTypeNameViewFactory.class;
		case TechnicalTermEditPart.VISUAL_ID:
			return TechnicalTermViewFactory.class;
		case TechnicalTermNameEditPart.VISUAL_ID:
			return TechnicalTermNameViewFactory.class;
		case CardFileEditPart.VISUAL_ID:
			return CardFileViewFactory.class;
		case CardFileNameEditPart.VISUAL_ID:
			return CardFileNameViewFactory.class;
		case DocumentEditPart.VISUAL_ID:
			return DocumentViewFactory.class;
		case DocumentNameEditPart.VISUAL_ID:
			return DocumentNameViewFactory.class;
		case FileEditPart.VISUAL_ID:
			return FileViewFactory.class;
		case FileNameEditPart.VISUAL_ID:
			return FileNameViewFactory.class;
		case ObjectiveEditPart.VISUAL_ID:
			return ObjectiveViewFactory.class;
		case ObjectiveNameEditPart.VISUAL_ID:
			return ObjectiveNameViewFactory.class;
		case ProductEditPart.VISUAL_ID:
			return ProductViewFactory.class;
		case ProductNameEditPart.VISUAL_ID:
			return ProductNameViewFactory.class;
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Class getEdgeViewClass(IAdaptable semanticAdapter,
			View containerView, String semanticHint) {
		IElementType elementType = getSemanticElementType(semanticAdapter);
		if (!EpcElementTypes.isKnownElementType(elementType)
				|| (!(elementType instanceof IHintedType))) {
			return null; // foreign element type
		}
		String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
		if (elementTypeHint == null) {
			return null; // our hint is visual id and must be specified
		}
		if (semanticHint != null && !semanticHint.equals(elementTypeHint)) {
			return null; // if semantic hint is specified it should be the same as in element type
		}
		int visualID = EpcVisualIDRegistry.getVisualID(elementTypeHint);
		EObject domainElement = getSemanticElement(semanticAdapter);
		if (domainElement != null
				&& visualID != EpcVisualIDRegistry
						.getLinkWithClassVisualID(domainElement)) {
			return null; // visual id for link EClass should match visual id from element type
		}
		return getEdgeViewClass(visualID);
	}

	/**
	 * @generated
	 */
	protected Class getEdgeViewClass(int visualID) {
		switch (visualID) {
		case ArcEditPart.VISUAL_ID:
			return ArcViewFactory.class;
		case RelationEditPart.VISUAL_ID:
			return RelationViewFactory.class;
		case InformationArcEditPart.VISUAL_ID:
			return InformationArcViewFactory.class;
		}
		return null;
	}

	/**
	 * @generated
	 */
	private IElementType getSemanticElementType(IAdaptable semanticAdapter) {
		if (semanticAdapter == null) {
			return null;
		}
		return (IElementType) semanticAdapter.getAdapter(IElementType.class);
	}
}

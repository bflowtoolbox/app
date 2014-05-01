package org.bflow.toolbox.vc.diagram.providers;

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
import org.bflow.toolbox.vc.diagram.part.VcVisualIDRegistry;
import org.bflow.toolbox.vc.diagram.view.factories.ClusterNameViewFactory;
import org.bflow.toolbox.vc.diagram.view.factories.ClusterViewFactory;
import org.bflow.toolbox.vc.diagram.view.factories.ObjectiveNameViewFactory;
import org.bflow.toolbox.vc.diagram.view.factories.ObjectiveViewFactory;
import org.bflow.toolbox.vc.diagram.view.factories.PredecessorConnectionViewFactory;
import org.bflow.toolbox.vc.diagram.view.factories.ProcessSuperiorityViewFactory;
import org.bflow.toolbox.vc.diagram.view.factories.ProductNameViewFactory;
import org.bflow.toolbox.vc.diagram.view.factories.ProductViewFactory;
import org.bflow.toolbox.vc.diagram.view.factories.RelationViewFactory;
import org.bflow.toolbox.vc.diagram.view.factories.TechnicalTermNameViewFactory;
import org.bflow.toolbox.vc.diagram.view.factories.TechnicalTermViewFactory;
import org.bflow.toolbox.vc.diagram.view.factories.VCViewFactory;
import org.bflow.toolbox.vc.diagram.view.factories.ValueChain2NameViewFactory;
import org.bflow.toolbox.vc.diagram.view.factories.ValueChain2ViewFactory;
import org.bflow.toolbox.vc.diagram.view.factories.ValueChainNameViewFactory;
import org.bflow.toolbox.vc.diagram.view.factories.ValueChainViewFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.providers.AbstractViewProvider;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class VcViewProvider extends AbstractViewProvider {

	/**
	 * @generated
	 */
	protected Class getDiagramViewClass(IAdaptable semanticAdapter,
			String diagramKind) {
		EObject semanticElement = getSemanticElement(semanticAdapter);
		if (VCEditPart.MODEL_ID.equals(diagramKind)
				&& VcVisualIDRegistry.getDiagramVisualID(semanticElement) != -1) {
			return VCViewFactory.class;
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
			visualID = VcVisualIDRegistry.getNodeVisualID(containerView,
					domainElement);
		} else {
			visualID = VcVisualIDRegistry.getVisualID(semanticHint);
			if (elementType != null) {
				// Semantic hint is specified together with element type.
				// Both parameters should describe exactly the same diagram element.
				// In addition we check that visualID returned by VisualIDRegistry.getNodeVisualID() for
				// domainElement (if specified) is the same as in element type.
				if (!VcElementTypes.isKnownElementType(elementType)
						|| (!(elementType instanceof IHintedType))) {
					return null; // foreign element type
				}
				String elementTypeHint = ((IHintedType) elementType)
						.getSemanticHint();
				if (!semanticHint.equals(elementTypeHint)) {
					return null; // if semantic hint is specified it should be the same as in element type
				}
				if (domainElement != null
						&& visualID != VcVisualIDRegistry.getNodeVisualID(
								containerView, domainElement)) {
					return null; // visual id for node EClass should match visual id from element type
				}
			} else {
				// Element type is not specified. Domain element should be present (except pure design elements).
				// This method is called with EObjectAdapter as parameter from:
				//   - ViewService.createNode(View container, EObject eObject, String type, PreferencesHint preferencesHint) 
				//   - generated ViewFactory.decorateView() for parent element
				if (!VCEditPart.MODEL_ID.equals(VcVisualIDRegistry
						.getModelID(containerView))) {
					return null; // foreign diagram
				}
				switch (visualID) {
				case ValueChainEditPart.VISUAL_ID:
				case ValueChain2EditPart.VISUAL_ID:
				case TechnicalTermEditPart.VISUAL_ID:
				case ClusterEditPart.VISUAL_ID:
				case ObjectiveEditPart.VISUAL_ID:
				case ProductEditPart.VISUAL_ID:
					if (domainElement == null
							|| visualID != VcVisualIDRegistry.getNodeVisualID(
									containerView, domainElement)) {
						return null; // visual id in semantic hint should match visual id for domain element
					}
					break;
				case ValueChainNameEditPart.VISUAL_ID:
					if (ValueChainEditPart.VISUAL_ID != VcVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case ValueChain2NameEditPart.VISUAL_ID:
					if (ValueChain2EditPart.VISUAL_ID != VcVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case TechnicalTermNameEditPart.VISUAL_ID:
					if (TechnicalTermEditPart.VISUAL_ID != VcVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case ClusterNameEditPart.VISUAL_ID:
					if (ClusterEditPart.VISUAL_ID != VcVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case ObjectiveNameEditPart.VISUAL_ID:
					if (ObjectiveEditPart.VISUAL_ID != VcVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case ProductNameEditPart.VISUAL_ID:
					if (ProductEditPart.VISUAL_ID != VcVisualIDRegistry
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
				|| !VcVisualIDRegistry.canCreateNode(containerView, visualID)) {
			return null;
		}
		switch (visualID) {
		case ValueChainEditPart.VISUAL_ID:
			return ValueChainViewFactory.class;
		case ValueChainNameEditPart.VISUAL_ID:
			return ValueChainNameViewFactory.class;
		case ValueChain2EditPart.VISUAL_ID:
			return ValueChain2ViewFactory.class;
		case ValueChain2NameEditPart.VISUAL_ID:
			return ValueChain2NameViewFactory.class;
		case TechnicalTermEditPart.VISUAL_ID:
			return TechnicalTermViewFactory.class;
		case TechnicalTermNameEditPart.VISUAL_ID:
			return TechnicalTermNameViewFactory.class;
		case ClusterEditPart.VISUAL_ID:
			return ClusterViewFactory.class;
		case ClusterNameEditPart.VISUAL_ID:
			return ClusterNameViewFactory.class;
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
		if (!VcElementTypes.isKnownElementType(elementType)
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
		int visualID = VcVisualIDRegistry.getVisualID(elementTypeHint);
		EObject domainElement = getSemanticElement(semanticAdapter);
		if (domainElement != null
				&& visualID != VcVisualIDRegistry
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
		case PredecessorConnectionEditPart.VISUAL_ID:
			return PredecessorConnectionViewFactory.class;
		case ProcessSuperiorityEditPart.VISUAL_ID:
			return ProcessSuperiorityViewFactory.class;
		case RelationEditPart.VISUAL_ID:
			return RelationViewFactory.class;
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

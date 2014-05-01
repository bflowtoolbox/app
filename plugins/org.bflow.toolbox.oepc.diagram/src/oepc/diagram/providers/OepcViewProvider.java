package oepc.diagram.providers;

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
import oepc.diagram.extensions.edit.parts.legend.LegendViewFactory;
import oepc.diagram.part.OepcVisualIDRegistry;
import oepc.diagram.view.factories.ANDConnectorViewFactory;
import oepc.diagram.view.factories.BusinessAttributeNameViewFactory;
import oepc.diagram.view.factories.BusinessAttributeViewFactory;
import oepc.diagram.view.factories.BusinessMethodViewFactory;
import oepc.diagram.view.factories.BusinessObjectBusinessObjectAttributeCompartmentViewFactory;
import oepc.diagram.view.factories.BusinessObjectBusinessObjectMethodCompartmentViewFactory;
import oepc.diagram.view.factories.BusinessObjectNameViewFactory;
import oepc.diagram.view.factories.BusinessObjectViewFactory;
import oepc.diagram.view.factories.ControlFlowEdgeViewFactory;
import oepc.diagram.view.factories.DocumentNameViewFactory;
import oepc.diagram.view.factories.DocumentViewFactory;
import oepc.diagram.view.factories.EventNameViewFactory;
import oepc.diagram.view.factories.EventViewFactory;
import oepc.diagram.view.factories.ITSystemNameViewFactory;
import oepc.diagram.view.factories.ITSystemViewFactory;
import oepc.diagram.view.factories.InformationEdgeViewFactory;
import oepc.diagram.view.factories.OEPCViewFactory;
import oepc.diagram.view.factories.ORConnectorViewFactory;
import oepc.diagram.view.factories.OrganisationUnitNameViewFactory;
import oepc.diagram.view.factories.OrganisationUnitViewFactory;
import oepc.diagram.view.factories.XORConnectorViewFactory;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.providers.AbstractViewProvider;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class OepcViewProvider extends AbstractViewProvider {

	/**
	 * @generated
	 */
	protected Class getDiagramViewClass(IAdaptable semanticAdapter,
			String diagramKind) {
		EObject semanticElement = getSemanticElement(semanticAdapter);
		if (OEPCEditPart.MODEL_ID.equals(diagramKind)
				&& OepcVisualIDRegistry.getDiagramVisualID(semanticElement) != -1) {
			return OEPCViewFactory.class;
		}
		return null;
	}

	/**
	 * @generated NOT
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
			visualID = OepcVisualIDRegistry.getNodeVisualID(containerView,
					domainElement);
		} else {
			visualID = OepcVisualIDRegistry.getVisualID(semanticHint);
			if (elementType != null) {
				// Semantic hint is specified together with element type.
				// Both parameters should describe exactly the same diagram element.
				// In addition we check that visualID returned by VisualIDRegistry.getNodeVisualID() for
				// domainElement (if specified) is the same as in element type.
				if (!OepcElementTypes.isKnownElementType(elementType)
						|| (!(elementType instanceof IHintedType))) {
					return null; // foreign element type
				}
				String elementTypeHint = ((IHintedType) elementType)
						.getSemanticHint();
				if (!semanticHint.equals(elementTypeHint)) {
					return null; // if semantic hint is specified it should be the same as in element type
				}
				if (domainElement != null
						&& visualID != OepcVisualIDRegistry.getNodeVisualID(
								containerView, domainElement)) {
					return null; // visual id for node EClass should match visual id from element type
				}
			} else {
				
				// Element type is not specified. Domain element should be present (except pure design elements).
				// This method is called with EObjectAdapter as parameter from:
				//   - ViewService.createNode(View container, EObject eObject, String type, PreferencesHint preferencesHint) 
				//   - generated ViewFactory.decorateView() for parent element
				if (!OEPCEditPart.MODEL_ID.equals(OepcVisualIDRegistry
						.getModelID(containerView))) {
					return null; // foreign diagram
				}
				
				switch (visualID) {
				case LegendEditPart.VISUAL_ID:
					return getNodeViewClass(containerView, visualID);
				case EventEditPart.VISUAL_ID:
				case ITSystemEditPart.VISUAL_ID:
				case OrganisationUnitEditPart.VISUAL_ID:
				case XORConnectorEditPart.VISUAL_ID:
				case BusinessObjectEditPart.VISUAL_ID:
				case ANDConnectorEditPart.VISUAL_ID:
				case ORConnectorEditPart.VISUAL_ID:
				case DocumentEditPart.VISUAL_ID:
				case BusinessAttributeEditPart.VISUAL_ID:
				case BusinessMethodEditPart.VISUAL_ID:
					if (domainElement == null
							|| visualID != OepcVisualIDRegistry
									.getNodeVisualID(containerView,
											domainElement)) {
						return null; // visual id in semantic hint should match visual id for domain element
					}
					break;
				case EventNameEditPart.VISUAL_ID:
					if (EventEditPart.VISUAL_ID != OepcVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case ITSystemNameEditPart.VISUAL_ID:
					if (ITSystemEditPart.VISUAL_ID != OepcVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case OrganisationUnitNameEditPart.VISUAL_ID:
					if (OrganisationUnitEditPart.VISUAL_ID != OepcVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case BusinessObjectNameEditPart.VISUAL_ID:
				case BusinessObjectBusinessObjectAttributeCompartmentEditPart.VISUAL_ID:
				case BusinessObjectBusinessObjectMethodCompartmentEditPart.VISUAL_ID:
					if (BusinessObjectEditPart.VISUAL_ID != OepcVisualIDRegistry
							.getVisualID(containerView)
							|| containerView.getElement() != domainElement) {
						return null; // wrong container
					}
					break;
				case DocumentNameEditPart.VISUAL_ID:
					if (DocumentEditPart.VISUAL_ID != OepcVisualIDRegistry
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
	 * @generated NOT
	 */
	protected Class getNodeViewClass(View containerView, int visualID) {
		if (containerView == null
				|| !OepcVisualIDRegistry.canCreateNode(containerView, visualID)) {
			return null;
		}
		switch (visualID) {
		case LegendEditPart.VISUAL_ID:
			return LegendViewFactory.class;
		case EventEditPart.VISUAL_ID:
			return EventViewFactory.class;
		case EventNameEditPart.VISUAL_ID:
			return EventNameViewFactory.class;
		case ITSystemEditPart.VISUAL_ID:
			return ITSystemViewFactory.class;
		case ITSystemNameEditPart.VISUAL_ID:
			return ITSystemNameViewFactory.class;
		case OrganisationUnitEditPart.VISUAL_ID:
			return OrganisationUnitViewFactory.class;
		case OrganisationUnitNameEditPart.VISUAL_ID:
			return OrganisationUnitNameViewFactory.class;
		case XORConnectorEditPart.VISUAL_ID:
			return XORConnectorViewFactory.class;
		case BusinessObjectEditPart.VISUAL_ID:
			return BusinessObjectViewFactory.class;
		case BusinessObjectNameEditPart.VISUAL_ID:
			return BusinessObjectNameViewFactory.class;
		case ANDConnectorEditPart.VISUAL_ID:
			return ANDConnectorViewFactory.class;
		case ORConnectorEditPart.VISUAL_ID:
			return ORConnectorViewFactory.class;
		case DocumentEditPart.VISUAL_ID:
			return DocumentViewFactory.class;
		case DocumentNameEditPart.VISUAL_ID:
			return DocumentNameViewFactory.class;
		case BusinessAttributeEditPart.VISUAL_ID:
			return BusinessAttributeViewFactory.class;
		case BusinessMethodEditPart.VISUAL_ID:
			return BusinessMethodViewFactory.class;
		case BusinessObjectBusinessObjectAttributeCompartmentEditPart.VISUAL_ID:
			return BusinessObjectBusinessObjectAttributeCompartmentViewFactory.class;
		case BusinessObjectBusinessObjectMethodCompartmentEditPart.VISUAL_ID:
			return BusinessObjectBusinessObjectMethodCompartmentViewFactory.class;
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Class getEdgeViewClass(IAdaptable semanticAdapter,
			View containerView, String semanticHint) {
		IElementType elementType = getSemanticElementType(semanticAdapter);
		if (!OepcElementTypes.isKnownElementType(elementType)
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
		int visualID = OepcVisualIDRegistry.getVisualID(elementTypeHint);
		EObject domainElement = getSemanticElement(semanticAdapter);
		if (domainElement != null
				&& visualID != OepcVisualIDRegistry
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
		case ControlFlowEdgeEditPart.VISUAL_ID:
			return ControlFlowEdgeViewFactory.class;
		case InformationEdgeEditPart.VISUAL_ID:
			return InformationEdgeViewFactory.class;
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

/*
 * 
 */
package vcchart.diagram.providers;

import java.util.ArrayList;

import org.bflow.toolbox.extensions.edit.parts.BflowDiagramEditPart;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.providers.IViewProvider;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateDiagramViewOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateEdgeViewOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateNodeViewOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateViewForKindOperation;
import org.eclipse.gmf.runtime.diagram.core.services.view.CreateViewOperation;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.preferences.IPreferenceConstants;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.DecorationNode;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.FontStyle;
import org.eclipse.gmf.runtime.notation.MeasurementUnit;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontData;

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
import vcchart.diagram.part.VcVisualIDRegistry;

/**
 * @generated
 */
public class VcViewProvider extends AbstractProvider implements IViewProvider {

	/**
	 * @generated
	 */
	public final boolean provides(IOperation operation) {
		if (operation instanceof CreateViewForKindOperation) {
			return provides((CreateViewForKindOperation) operation);
		}
		assert operation instanceof CreateViewOperation;
		if (operation instanceof CreateDiagramViewOperation) {
			return provides((CreateDiagramViewOperation) operation);
		} else if (operation instanceof CreateEdgeViewOperation) {
			return provides((CreateEdgeViewOperation) operation);
		} else if (operation instanceof CreateNodeViewOperation) {
			return provides((CreateNodeViewOperation) operation);
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean provides(CreateViewForKindOperation op) {
		/*
		 if (op.getViewKind() == Node.class)
		 return getNodeViewClass(op.getSemanticAdapter(), op.getContainerView(), op.getSemanticHint()) != null;
		 if (op.getViewKind() == Edge.class)
		 return getEdgeViewClass(op.getSemanticAdapter(), op.getContainerView(), op.getSemanticHint()) != null;
		 */
		return true;
	}

	/**
	 * @generated
	 */
	protected boolean provides(CreateDiagramViewOperation op) {
		return ModelEditPart.MODEL_ID.equals(op.getSemanticHint())
				&& VcVisualIDRegistry.getDiagramVisualID(getSemanticElement(op
						.getSemanticAdapter())) != -1;
	}

	/**
	 * @generated
	 */
	protected boolean provides(CreateNodeViewOperation op) {
		if (op.getContainerView() == null) {
			return false;
		}
		IElementType elementType = getSemanticElementType(op
				.getSemanticAdapter());
		EObject domainElement = getSemanticElement(op.getSemanticAdapter());
		int visualID;
		if (op.getSemanticHint() == null) {
			// Semantic hint is not specified. Can be a result of call from CanonicalEditPolicy.
			// In this situation there should be NO elementType, visualID will be determined
			// by VisualIDRegistry.getNodeVisualID() for domainElement.
			if (elementType != null || domainElement == null) {
				return false;
			}
			visualID = VcVisualIDRegistry.getNodeVisualID(
					op.getContainerView(), domainElement);
		} else {
			visualID = VcVisualIDRegistry.getVisualID(op.getSemanticHint());
			if (elementType != null) {
				if (!VcElementTypes.isKnownElementType(elementType)
						|| (!(elementType instanceof IHintedType))) {
					return false; // foreign element type
				}
				String elementTypeHint = ((IHintedType) elementType)
						.getSemanticHint();
				if (!op.getSemanticHint().equals(elementTypeHint)) {
					return false; // if semantic hint is specified it should be the same as in element type
				}
				if (domainElement != null
						&& visualID != VcVisualIDRegistry.getNodeVisualID(
								op.getContainerView(), domainElement)) {
					return false; // visual id for node EClass should match visual id from element type
				}
			} else {
				if (!ModelEditPart.MODEL_ID.equals(VcVisualIDRegistry
						.getModelID(op.getContainerView()))) {
					return false; // foreign diagram
				}
				switch (visualID) {
				case ProductEditPart.VISUAL_ID:
				case ObjectiveEditPart.VISUAL_ID:
				case Activity1EditPart.VISUAL_ID:
				case Activity2EditPart.VISUAL_ID:
				case ClusterEditPart.VISUAL_ID:
				case TechnicalTermEditPart.VISUAL_ID:
				case ParticipantEditPart.VISUAL_ID:
				case ApplicationEditPart.VISUAL_ID:
				case DocumentEditPart.VISUAL_ID:
					if (domainElement == null
							|| visualID != VcVisualIDRegistry.getNodeVisualID(
									op.getContainerView(), domainElement)) {
						return false; // visual id in semantic hint should match visual id for domain element
					}
					break;
				default:
					return false;
				}
			}
		}
		return ProductEditPart.VISUAL_ID == visualID
				|| ObjectiveEditPart.VISUAL_ID == visualID
				|| Activity1EditPart.VISUAL_ID == visualID
				|| Activity2EditPart.VISUAL_ID == visualID
				|| ClusterEditPart.VISUAL_ID == visualID
				|| TechnicalTermEditPart.VISUAL_ID == visualID
				|| ParticipantEditPart.VISUAL_ID == visualID
				|| ApplicationEditPart.VISUAL_ID == visualID
				|| DocumentEditPart.VISUAL_ID == visualID;
	}

	/**
	 * @generated
	 */
	protected boolean provides(CreateEdgeViewOperation op) {
		IElementType elementType = getSemanticElementType(op
				.getSemanticAdapter());
		if (!VcElementTypes.isKnownElementType(elementType)
				|| (!(elementType instanceof IHintedType))) {
			return false; // foreign element type
		}
		String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
		if (elementTypeHint == null
				|| (op.getSemanticHint() != null && !elementTypeHint.equals(op
						.getSemanticHint()))) {
			return false; // our hint is visual id and must be specified, and it should be the same as in element type
		}
		int visualID = VcVisualIDRegistry.getVisualID(elementTypeHint);
		EObject domainElement = getSemanticElement(op.getSemanticAdapter());
		if (domainElement != null
				&& visualID != VcVisualIDRegistry
						.getLinkWithClassVisualID(domainElement)) {
			return false; // visual id for link EClass should match visual id from element type
		}
		return true;
	}

	/**
	 * @generated
	 */
	public Diagram createDiagram(IAdaptable semanticAdapter,
			String diagramKind, PreferencesHint preferencesHint) {
		Diagram diagram = NotationFactory.eINSTANCE.createDiagram();
		diagram.getStyles().add(NotationFactory.eINSTANCE.createDiagramStyle());
		diagram.setType(ModelEditPart.MODEL_ID);
		diagram.setElement(getSemanticElement(semanticAdapter));
		diagram.setMeasurementUnit(MeasurementUnit.PIXEL_LITERAL);
		return diagram;
	}

	/**
	 * @generated
	 */
	public Node createNode(IAdaptable semanticAdapter, View containerView,
			String semanticHint, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		final EObject domainElement = getSemanticElement(semanticAdapter);
		final int visualID;
		if (semanticHint == null) {
			visualID = VcVisualIDRegistry.getNodeVisualID(containerView,
					domainElement);
		} else {
			visualID = VcVisualIDRegistry.getVisualID(semanticHint);
		}
		switch (visualID) {
		case ProductEditPart.VISUAL_ID:
			return createProduct_2001(domainElement, containerView, index,
					persisted, preferencesHint);
		case ObjectiveEditPart.VISUAL_ID:
			return createObjective_2002(domainElement, containerView, index,
					persisted, preferencesHint);
		case Activity1EditPart.VISUAL_ID:
			return createActivity1_2003(domainElement, containerView, index,
					persisted, preferencesHint);
		case Activity2EditPart.VISUAL_ID:
			return createActivity2_2004(domainElement, containerView, index,
					persisted, preferencesHint);
		case ClusterEditPart.VISUAL_ID:
			return createCluster_2005(domainElement, containerView, index,
					persisted, preferencesHint);
		case TechnicalTermEditPart.VISUAL_ID:
			return createTechnicalTerm_2006(domainElement, containerView,
					index, persisted, preferencesHint);
		case ParticipantEditPart.VISUAL_ID:
			return createParticipant_2007(domainElement, containerView, index,
					persisted, preferencesHint);
		case ApplicationEditPart.VISUAL_ID:
			return createApplication_2008(domainElement, containerView, index,
					persisted, preferencesHint);
		case DocumentEditPart.VISUAL_ID:
			return createDocument_2009(domainElement, containerView, index,
					persisted, preferencesHint);
		}
		// can't happen, provided #provides(CreateNodeViewOperation) is correct
		return null;
	}

	/**
	 * @generated
	 */
	public Edge createEdge(IAdaptable semanticAdapter, View containerView,
			String semanticHint, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		IElementType elementType = getSemanticElementType(semanticAdapter);
		String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
		switch (VcVisualIDRegistry.getVisualID(elementTypeHint)) {
		case Relation1EditPart.VISUAL_ID:
			return createRelation1_4001(getSemanticElement(semanticAdapter),
					containerView, index, persisted, preferencesHint);
		case Relation2EditPart.VISUAL_ID:
			return createRelation2_4002(getSemanticElement(semanticAdapter),
					containerView, index, persisted, preferencesHint);
		case Relation3EditPart.VISUAL_ID:
			return createRelation3_4003(getSemanticElement(semanticAdapter),
					containerView, index, persisted, preferencesHint);
		}
		// can never happen, provided #provides(CreateEdgeViewOperation) is correct
		return null;
	}

	/**
	 * @generated NOT
	 */
	public Node createProduct_2001(EObject domainElement, View containerView,
			int index, boolean persisted, PreferencesHint preferencesHint) {
		Node node = NotationFactory.eINSTANCE.createNode();
		node.getStyles().add(NotationFactory.eINSTANCE.createShapeStyle());
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(VcVisualIDRegistry
				.getType(ProductEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);

		BflowDiagramEditPart diagramEditPart = BflowDiagramEditPart.getCurrentViewer();
		Color fillcolor;
		Color foregroundcolor;
		if (diagramEditPart != null) {
			fillcolor = diagramEditPart.getColorSchema().getBackground(ProductEditPart.class);
			foregroundcolor = diagramEditPart.getColorSchema().getForeground(ProductEditPart.class);
		} else {
			fillcolor = ColorConstants.white;
			foregroundcolor = ColorConstants.black;
		}
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillcolor.getRGB()));
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(foregroundcolor.getRGB()));
		Node label5001 = createLabel(node,
				VcVisualIDRegistry.getType(ProductNameEditPart.VISUAL_ID));
		return node;
	}

	/**
	 * @generated NOT
	 */
	public Node createObjective_2002(EObject domainElement, View containerView,
			int index, boolean persisted, PreferencesHint preferencesHint) {
		Node node = NotationFactory.eINSTANCE.createNode();
		node.getStyles().add(NotationFactory.eINSTANCE.createShapeStyle());
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(VcVisualIDRegistry
				.getType(ObjectiveEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);

		BflowDiagramEditPart diagramEditPart = BflowDiagramEditPart.getCurrentViewer();
		Color fillcolor;
		Color foregroundcolor;
		if (diagramEditPart != null) {
			fillcolor = diagramEditPart.getColorSchema().getBackground(ObjectiveEditPart.class);
			foregroundcolor = diagramEditPart.getColorSchema().getForeground(ObjectiveEditPart.class);
		} else {
			fillcolor = ColorConstants.white;
			foregroundcolor = ColorConstants.black;
		}
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillcolor.getRGB()));
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(foregroundcolor.getRGB()));
		Node label5002 = createLabel(node,
				VcVisualIDRegistry.getType(ObjectiveNameEditPart.VISUAL_ID));
		return node;
	}

	/**
	 * @generated NOT
	 */
	public Node createActivity1_2003(EObject domainElement, View containerView,
			int index, boolean persisted, PreferencesHint preferencesHint) {
		Node node = NotationFactory.eINSTANCE.createNode();
		node.getStyles().add(NotationFactory.eINSTANCE.createShapeStyle());
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(VcVisualIDRegistry
				.getType(Activity1EditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);

		BflowDiagramEditPart diagramEditPart = BflowDiagramEditPart.getCurrentViewer();
		Color fillcolor;
		Color foregroundcolor;
		if (diagramEditPart != null) {
			fillcolor = diagramEditPart.getColorSchema().getBackground(Activity1EditPart.class);
			foregroundcolor = diagramEditPart.getColorSchema().getForeground(Activity1EditPart.class);
		} else {
			fillcolor = ColorConstants.white;
			foregroundcolor = ColorConstants.black;
		}
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillcolor.getRGB()));
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(foregroundcolor.getRGB()));
		Node label5003 = createLabel(node,
				VcVisualIDRegistry.getType(Activity1NameEditPart.VISUAL_ID));
		return node;
	}

	/**
	 * @generated NOT
	 */
	public Node createActivity2_2004(EObject domainElement, View containerView,
			int index, boolean persisted, PreferencesHint preferencesHint) {
		Node node = NotationFactory.eINSTANCE.createNode();
		node.getStyles().add(NotationFactory.eINSTANCE.createShapeStyle());
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(VcVisualIDRegistry
				.getType(Activity2EditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);

		BflowDiagramEditPart diagramEditPart = BflowDiagramEditPart.getCurrentViewer();
		Color fillcolor;
		Color foregroundcolor;
		if (diagramEditPart != null) {
			fillcolor = diagramEditPart.getColorSchema().getBackground(Activity2EditPart.class);
			foregroundcolor = diagramEditPart.getColorSchema().getForeground(Activity2EditPart.class);
		} else {
			fillcolor = ColorConstants.white;
			foregroundcolor = ColorConstants.black;
		}
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillcolor.getRGB()));
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(foregroundcolor.getRGB()));
		Node label5004 = createLabel(node,
				VcVisualIDRegistry.getType(Activity2NameEditPart.VISUAL_ID));
		return node;
	}

	/**
	 * @generated NOT
	 */
	public Node createCluster_2005(EObject domainElement, View containerView,
			int index, boolean persisted, PreferencesHint preferencesHint) {
		Node node = NotationFactory.eINSTANCE.createNode();
		node.getStyles().add(NotationFactory.eINSTANCE.createShapeStyle());
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(VcVisualIDRegistry
				.getType(ClusterEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);

		BflowDiagramEditPart diagramEditPart = BflowDiagramEditPart.getCurrentViewer();
		Color fillcolor;
		Color foregroundcolor;
		if (diagramEditPart != null) {
			fillcolor = diagramEditPart.getColorSchema().getBackground(ClusterEditPart.class);
			foregroundcolor = diagramEditPart.getColorSchema().getForeground(ClusterEditPart.class);
		} else {
			fillcolor = ColorConstants.white;
			foregroundcolor = ColorConstants.black;
		}
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillcolor.getRGB()));
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(foregroundcolor.getRGB()));
		Node label5005 = createLabel(node,
				VcVisualIDRegistry.getType(ClusterNameEditPart.VISUAL_ID));
	return node;
	}

	/**
	 * @generated NOT
	 */
	public Node createTechnicalTerm_2006(EObject domainElement,
			View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Node node = NotationFactory.eINSTANCE.createNode();
		node.getStyles().add(NotationFactory.eINSTANCE.createShapeStyle());
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(VcVisualIDRegistry
				.getType(TechnicalTermEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);

		BflowDiagramEditPart diagramEditPart = BflowDiagramEditPart.getCurrentViewer();
		Color fillcolor;
		Color foregroundcolor;
		if (diagramEditPart != null) {
			fillcolor = diagramEditPart.getColorSchema().getBackground(TechnicalTermEditPart.class);
			foregroundcolor = diagramEditPart.getColorSchema().getForeground(TechnicalTermEditPart.class);
		} else {
			fillcolor = ColorConstants.white;
			foregroundcolor = ColorConstants.black;
		}
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillcolor.getRGB()));
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(foregroundcolor.getRGB()));
		Node label5006 = createLabel(node,
				VcVisualIDRegistry.getType(TechnicalTermNameEditPart.VISUAL_ID));
		return node;
	}

	/**
	 * @generated NOT
	 */
	public Node createParticipant_2007(EObject domainElement,
			View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Node node = NotationFactory.eINSTANCE.createNode();
		node.getStyles().add(NotationFactory.eINSTANCE.createShapeStyle());
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(VcVisualIDRegistry
				.getType(ParticipantEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);

		BflowDiagramEditPart diagramEditPart = BflowDiagramEditPart.getCurrentViewer();
		Color fillcolor;
		Color foregroundcolor;
		if (diagramEditPart != null) {
			fillcolor = diagramEditPart.getColorSchema().getBackground(ParticipantEditPart.class);
			foregroundcolor = diagramEditPart.getColorSchema().getForeground(ParticipantEditPart.class);
		} else {
			fillcolor = ColorConstants.white;
			foregroundcolor = ColorConstants.black;
		}
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillcolor.getRGB()));
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(foregroundcolor.getRGB()));
		
		Node label5007 = createLabel(node,
				VcVisualIDRegistry.getType(ParticipantNameEditPart.VISUAL_ID));
		return node;
	}

	/**
	 * @generated NOT
	 */
	public Node createApplication_2008(EObject domainElement,
			View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Node node = NotationFactory.eINSTANCE.createNode();
		node.getStyles().add(NotationFactory.eINSTANCE.createShapeStyle());
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(VcVisualIDRegistry
				.getType(ApplicationEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);

		BflowDiagramEditPart diagramEditPart = BflowDiagramEditPart.getCurrentViewer();
		Color fillcolor;
		Color foregroundcolor;
		if (diagramEditPart != null) {
			// Nutze das Farbschema der Funktionen aus den EPC
			fillcolor = diagramEditPart.getColorSchema().getBackground(ApplicationEditPart.class);
			foregroundcolor = diagramEditPart.getColorSchema().getForeground(ApplicationEditPart.class);
		} else {
			fillcolor = ColorConstants.white;
			foregroundcolor = ColorConstants.black;
		}
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillcolor.getRGB()));
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(foregroundcolor.getRGB()));
		Node label5008 = createLabel(node,
				VcVisualIDRegistry.getType(ApplicationNameEditPart.VISUAL_ID));
		return node;
	}

	/**
	 * @generated NOT
	 */
	public Node createDocument_2009(EObject domainElement, View containerView,
			int index, boolean persisted, PreferencesHint preferencesHint) {
		Node node = NotationFactory.eINSTANCE.createNode();
		node.getStyles().add(NotationFactory.eINSTANCE.createShapeStyle());
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(VcVisualIDRegistry
				.getType(DocumentEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);

		BflowDiagramEditPart diagramEditPart = BflowDiagramEditPart.getCurrentViewer();
		Color fillcolor;
		Color foregroundcolor;
		if (diagramEditPart != null) {
			fillcolor = diagramEditPart.getColorSchema().getBackground(DocumentEditPart.class);
			foregroundcolor = diagramEditPart.getColorSchema().getForeground(DocumentEditPart.class);
		} else {
			fillcolor = ColorConstants.white;
			foregroundcolor = ColorConstants.black;
		}
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillcolor.getRGB()));
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(foregroundcolor.getRGB()));
		Node label5009 = createLabel(node,
				VcVisualIDRegistry.getType(DocumentNameEditPart.VISUAL_ID));
		return node;
	}

	/**
	 * @generated
	 */
	public Edge createRelation1_4001(EObject domainElement, View containerView,
			int index, boolean persisted, PreferencesHint preferencesHint) {
		Edge edge = NotationFactory.eINSTANCE.createEdge();
		edge.getStyles().add(NotationFactory.eINSTANCE.createRoutingStyle());
		edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
		RelativeBendpoints bendpoints = NotationFactory.eINSTANCE
				.createRelativeBendpoints();
		ArrayList<RelativeBendpoint> points = new ArrayList<RelativeBendpoint>(
				2);
		points.add(new RelativeBendpoint());
		points.add(new RelativeBendpoint());
		bendpoints.setPoints(points);
		edge.setBendpoints(bendpoints);
		ViewUtil.insertChildView(containerView, edge, index, persisted);
		edge.setType(VcVisualIDRegistry.getType(Relation1EditPart.VISUAL_ID));
		edge.setElement(domainElement);
		// initializePreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint
				.getPreferenceStore();
		FontStyle edgeFontStyle = (FontStyle) edge
				.getStyle(NotationPackage.Literals.FONT_STYLE);
		if (edgeFontStyle != null) {
			FontData fontData = PreferenceConverter.getFontData(prefStore,
					IPreferenceConstants.PREF_DEFAULT_FONT);
			edgeFontStyle.setFontName(fontData.getName());
			edgeFontStyle.setFontHeight(fontData.getHeight());
			edgeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
			edgeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
			org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter
					.getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
			edgeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB)
					.intValue());
		}
		Routing routing = Routing.get(prefStore
				.getInt(IPreferenceConstants.PREF_LINE_STYLE));
		if (routing != null) {
			ViewUtil.setStructuralFeatureValue(edge,
					NotationPackage.eINSTANCE.getRoutingStyle_Routing(),
					routing);
		}
		return edge;
	}

	/**
	 * @generated
	 */
	public Edge createRelation2_4002(EObject domainElement, View containerView,
			int index, boolean persisted, PreferencesHint preferencesHint) {
		Edge edge = NotationFactory.eINSTANCE.createEdge();
		edge.getStyles().add(NotationFactory.eINSTANCE.createRoutingStyle());
		edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
		RelativeBendpoints bendpoints = NotationFactory.eINSTANCE
				.createRelativeBendpoints();
		ArrayList<RelativeBendpoint> points = new ArrayList<RelativeBendpoint>(
				2);
		points.add(new RelativeBendpoint());
		points.add(new RelativeBendpoint());
		bendpoints.setPoints(points);
		edge.setBendpoints(bendpoints);
		ViewUtil.insertChildView(containerView, edge, index, persisted);
		edge.setType(VcVisualIDRegistry.getType(Relation2EditPart.VISUAL_ID));
		edge.setElement(domainElement);
		// initializePreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint
				.getPreferenceStore();
		FontStyle edgeFontStyle = (FontStyle) edge
				.getStyle(NotationPackage.Literals.FONT_STYLE);
		if (edgeFontStyle != null) {
			FontData fontData = PreferenceConverter.getFontData(prefStore,
					IPreferenceConstants.PREF_DEFAULT_FONT);
			edgeFontStyle.setFontName(fontData.getName());
			edgeFontStyle.setFontHeight(fontData.getHeight());
			edgeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
			edgeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
			org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter
					.getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
			edgeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB)
					.intValue());
		}
		Routing routing = Routing.get(prefStore
				.getInt(IPreferenceConstants.PREF_LINE_STYLE));
		if (routing != null) {
			ViewUtil.setStructuralFeatureValue(edge,
					NotationPackage.eINSTANCE.getRoutingStyle_Routing(),
					routing);
		}
		return edge;
	}

	/**
	 * @generated
	 */
	public Edge createRelation3_4003(EObject domainElement, View containerView,
			int index, boolean persisted, PreferencesHint preferencesHint) {
		Edge edge = NotationFactory.eINSTANCE.createEdge();
		edge.getStyles().add(NotationFactory.eINSTANCE.createRoutingStyle());
		edge.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
		RelativeBendpoints bendpoints = NotationFactory.eINSTANCE
				.createRelativeBendpoints();
		ArrayList<RelativeBendpoint> points = new ArrayList<RelativeBendpoint>(
				2);
		points.add(new RelativeBendpoint());
		points.add(new RelativeBendpoint());
		bendpoints.setPoints(points);
		edge.setBendpoints(bendpoints);
		ViewUtil.insertChildView(containerView, edge, index, persisted);
		edge.setType(VcVisualIDRegistry.getType(Relation3EditPart.VISUAL_ID));
		edge.setElement(domainElement);
		// initializePreferences
		final IPreferenceStore prefStore = (IPreferenceStore) preferencesHint
				.getPreferenceStore();
		FontStyle edgeFontStyle = (FontStyle) edge
				.getStyle(NotationPackage.Literals.FONT_STYLE);
		if (edgeFontStyle != null) {
			FontData fontData = PreferenceConverter.getFontData(prefStore,
					IPreferenceConstants.PREF_DEFAULT_FONT);
			edgeFontStyle.setFontName(fontData.getName());
			edgeFontStyle.setFontHeight(fontData.getHeight());
			edgeFontStyle.setBold((fontData.getStyle() & SWT.BOLD) != 0);
			edgeFontStyle.setItalic((fontData.getStyle() & SWT.ITALIC) != 0);
			org.eclipse.swt.graphics.RGB fontRGB = PreferenceConverter
					.getColor(prefStore, IPreferenceConstants.PREF_FONT_COLOR);
			edgeFontStyle.setFontColor(FigureUtilities.RGBToInteger(fontRGB)
					.intValue());
		}
		Routing routing = Routing.get(prefStore
				.getInt(IPreferenceConstants.PREF_LINE_STYLE));
		if (routing != null) {
			ViewUtil.setStructuralFeatureValue(edge,
					NotationPackage.eINSTANCE.getRoutingStyle_Routing(),
					routing);
		}
		return edge;
	}

	/**
	 * @generated
	 */
	private void stampShortcut(View containerView, Node target) {
		if (!ModelEditPart.MODEL_ID.equals(VcVisualIDRegistry
				.getModelID(containerView))) {
			EAnnotation shortcutAnnotation = EcoreFactory.eINSTANCE
					.createEAnnotation();
			shortcutAnnotation.setSource("Shortcut"); //$NON-NLS-1$
			shortcutAnnotation.getDetails().put(
					"modelID", ModelEditPart.MODEL_ID); //$NON-NLS-1$
			target.getEAnnotations().add(shortcutAnnotation);
		}
	}

	/**
	 * @generated
	 */
	private Node createLabel(View owner, String hint) {
		DecorationNode rv = NotationFactory.eINSTANCE.createDecorationNode();
		rv.setType(hint);
		ViewUtil.insertChildView(owner, rv, ViewUtil.APPEND, true);
		return rv;
	}

	/**
	 * @generated
	 */
	private EObject getSemanticElement(IAdaptable semanticAdapter) {
		if (semanticAdapter == null) {
			return null;
		}
		EObject eObject = (EObject) semanticAdapter.getAdapter(EObject.class);
		if (eObject != null) {
			return EMFCoreUtil.resolve(
					TransactionUtil.getEditingDomain(eObject), eObject);
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

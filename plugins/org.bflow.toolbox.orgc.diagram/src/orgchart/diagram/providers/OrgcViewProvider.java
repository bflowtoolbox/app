/*
 * 
 */
package orgchart.diagram.providers;

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
import orgchart.diagram.part.OrgcVisualIDRegistry;

/**
 * @generated
 */
public class OrgcViewProvider extends AbstractProvider implements IViewProvider {

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
				&& OrgcVisualIDRegistry
						.getDiagramVisualID(getSemanticElement(op
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
			visualID = OrgcVisualIDRegistry.getNodeVisualID(
					op.getContainerView(), domainElement);
		} else {
			visualID = OrgcVisualIDRegistry.getVisualID(op.getSemanticHint());
			if (elementType != null) {
				if (!OrgcElementTypes.isKnownElementType(elementType)
						|| (!(elementType instanceof IHintedType))) {
					return false; // foreign element type
				}
				String elementTypeHint = ((IHintedType) elementType)
						.getSemanticHint();
				if (!op.getSemanticHint().equals(elementTypeHint)) {
					return false; // if semantic hint is specified it should be the same as in element type
				}
				if (domainElement != null
						&& visualID != OrgcVisualIDRegistry.getNodeVisualID(
								op.getContainerView(), domainElement)) {
					return false; // visual id for node EClass should match visual id from element type
				}
			} else {
				if (!ModelEditPart.MODEL_ID.equals(OrgcVisualIDRegistry
						.getModelID(op.getContainerView()))) {
					return false; // foreign diagram
				}
				switch (visualID) {
				case PositionEditPart.VISUAL_ID:
				case InternalPersonEditPart.VISUAL_ID:
				case ExternalPersonEditPart.VISUAL_ID:
				case GroupEditPart.VISUAL_ID:
				case ParticipantEditPart.VISUAL_ID:
				case PersonTypeEditPart.VISUAL_ID:
				case LocationEditPart.VISUAL_ID:
					if (domainElement == null
							|| visualID != OrgcVisualIDRegistry
									.getNodeVisualID(op.getContainerView(),
											domainElement)) {
						return false; // visual id in semantic hint should match visual id for domain element
					}
					break;
				default:
					return false;
				}
			}
		}
		return PositionEditPart.VISUAL_ID == visualID
				|| InternalPersonEditPart.VISUAL_ID == visualID
				|| ExternalPersonEditPart.VISUAL_ID == visualID
				|| GroupEditPart.VISUAL_ID == visualID
				|| ParticipantEditPart.VISUAL_ID == visualID
				|| PersonTypeEditPart.VISUAL_ID == visualID
				|| LocationEditPart.VISUAL_ID == visualID;
	}

	/**
	 * @generated
	 */
	protected boolean provides(CreateEdgeViewOperation op) {
		IElementType elementType = getSemanticElementType(op
				.getSemanticAdapter());
		if (!OrgcElementTypes.isKnownElementType(elementType)
				|| (!(elementType instanceof IHintedType))) {
			return false; // foreign element type
		}
		String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
		if (elementTypeHint == null
				|| (op.getSemanticHint() != null && !elementTypeHint.equals(op
						.getSemanticHint()))) {
			return false; // our hint is visual id and must be specified, and it should be the same as in element type
		}
		int visualID = OrgcVisualIDRegistry.getVisualID(elementTypeHint);
		EObject domainElement = getSemanticElement(op.getSemanticAdapter());
		if (domainElement != null
				&& visualID != OrgcVisualIDRegistry
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
			visualID = OrgcVisualIDRegistry.getNodeVisualID(containerView,
					domainElement);
		} else {
			visualID = OrgcVisualIDRegistry.getVisualID(semanticHint);
		}
		switch (visualID) {
		case PositionEditPart.VISUAL_ID:
			return createPosition_2001(domainElement, containerView, index,
					persisted, preferencesHint);
		case InternalPersonEditPart.VISUAL_ID:
			return createInternalPerson_2002(domainElement, containerView,
					index, persisted, preferencesHint);
		case ExternalPersonEditPart.VISUAL_ID:
			return createExternalPerson_2003(domainElement, containerView,
					index, persisted, preferencesHint);
		case GroupEditPart.VISUAL_ID:
			return createGroup_2004(domainElement, containerView, index,
					persisted, preferencesHint);
		case ParticipantEditPart.VISUAL_ID:
			return createParticipant_2005(domainElement, containerView, index,
					persisted, preferencesHint);
		case PersonTypeEditPart.VISUAL_ID:
			return createPersonType_2006(domainElement, containerView, index,
					persisted, preferencesHint);
		case LocationEditPart.VISUAL_ID:
			return createLocation_2007(domainElement, containerView, index,
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
		switch (OrgcVisualIDRegistry.getVisualID(elementTypeHint)) {
		case Relation1EditPart.VISUAL_ID:
			return createRelation1_4001(getSemanticElement(semanticAdapter),
					containerView, index, persisted, preferencesHint);
		case Relation2EditPart.VISUAL_ID:
			return createRelation2_4002(getSemanticElement(semanticAdapter),
					containerView, index, persisted, preferencesHint);
		}
		// can never happen, provided #provides(CreateEdgeViewOperation) is correct
		return null;
	}

	/**
	 * @generated
	 */
	public Node createPosition_2001(EObject domainElement, View containerView,
			int index, boolean persisted, PreferencesHint preferencesHint) {
		Node node = NotationFactory.eINSTANCE.createNode();
		node.getStyles().add(NotationFactory.eINSTANCE.createShapeStyle());
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(OrgcVisualIDRegistry.getType(PositionEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);
		
		BflowDiagramEditPart diagramEditPart = BflowDiagramEditPart.getCurrentViewer();
		Color fillcolor;
		Color foregroundcolor;
		if (diagramEditPart != null) {
			fillcolor = diagramEditPart.getColorSchema().getBackground(PositionEditPart.class);
			foregroundcolor = diagramEditPart.getColorSchema().getForeground(PositionEditPart.class);
		} else {
			fillcolor = ColorConstants.white;
			foregroundcolor = ColorConstants.black;
		}
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillcolor.getRGB()));
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(foregroundcolor.getRGB()));
		
		Node label5001 = createLabel(node,
				OrgcVisualIDRegistry.getType(PositionNameEditPart.VISUAL_ID));
		return node;
	}

	/**
	 * @generated
	 */
	public Node createInternalPerson_2002(EObject domainElement, View containerView, int index, boolean persisted, PreferencesHint preferencesHint) {
		Node node = NotationFactory.eINSTANCE.createNode();
		node.getStyles().add(NotationFactory.eINSTANCE.createShapeStyle());
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(OrgcVisualIDRegistry.getType(InternalPersonEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);

		BflowDiagramEditPart diagramEditPart = BflowDiagramEditPart.getCurrentViewer();
		Color fillcolor;
		Color foregroundcolor;
		if (diagramEditPart != null) {
			fillcolor = diagramEditPart.getColorSchema().getBackground(InternalPersonEditPart.class);
			foregroundcolor = diagramEditPart.getColorSchema().getForeground(InternalPersonEditPart.class);
		} else {
			fillcolor = ColorConstants.white;
			foregroundcolor = ColorConstants.black;
		}
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillcolor.getRGB()));
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(foregroundcolor.getRGB()));
		
		Node label5002 = createLabel(node, OrgcVisualIDRegistry.getType(InternalPersonNameEditPart.VISUAL_ID));
		label5002.getStyles().add(NotationFactory.eINSTANCE.createTextStyle());
		return node;
	}

	/**
	 * @generated
	 */
	public Node createExternalPerson_2003(EObject domainElement,
			View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Node node = NotationFactory.eINSTANCE.createNode();
		node.getStyles().add(NotationFactory.eINSTANCE.createShapeStyle());
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(OrgcVisualIDRegistry
				.getType(ExternalPersonEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);

		BflowDiagramEditPart diagramEditPart = BflowDiagramEditPart.getCurrentViewer();
		Color fillcolor;
		Color foregroundcolor;
		if (diagramEditPart != null) {
			fillcolor = diagramEditPart.getColorSchema().getBackground(ExternalPersonEditPart.class);
			foregroundcolor = diagramEditPart.getColorSchema().getForeground(ExternalPersonEditPart.class);
		} else {
			fillcolor = ColorConstants.white;
			foregroundcolor = ColorConstants.black;
		}
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillcolor.getRGB()));
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(foregroundcolor.getRGB()));
		
		Node label5003 = createLabel(node,
				OrgcVisualIDRegistry
						.getType(ExternalPersonNameEditPart.VISUAL_ID));
		return node;
	}

	/**
	 * @generated
	 */
	public Node createGroup_2004(EObject domainElement, View containerView,
			int index, boolean persisted, PreferencesHint preferencesHint) {
		Node node = NotationFactory.eINSTANCE.createNode();
		node.getStyles().add(NotationFactory.eINSTANCE.createShapeStyle());
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(OrgcVisualIDRegistry.getType(GroupEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);

		BflowDiagramEditPart diagramEditPart = BflowDiagramEditPart.getCurrentViewer();
		Color fillcolor;
		Color foregroundcolor;
		if (diagramEditPart != null) {
			fillcolor = diagramEditPart.getColorSchema().getBackground(GroupEditPart.class);
			foregroundcolor = diagramEditPart.getColorSchema().getForeground(GroupEditPart.class);
		} else {
			fillcolor = ColorConstants.white;
			foregroundcolor = ColorConstants.black;
		}
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillcolor.getRGB()));
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(foregroundcolor.getRGB()));
		
		Node label5004 = createLabel(node,
				OrgcVisualIDRegistry.getType(GroupNameEditPart.VISUAL_ID));
		return node;
	}

	/**
	 * @generated
	 */
	public Node createParticipant_2005(EObject domainElement,
			View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Node node = NotationFactory.eINSTANCE.createNode();
		node.getStyles().add(NotationFactory.eINSTANCE.createShapeStyle());
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(OrgcVisualIDRegistry
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
		
		Node label5005 = createLabel(node,
				OrgcVisualIDRegistry.getType(ParticipantNameEditPart.VISUAL_ID));
		return node;
	}

	/**
	 * @generated
	 */
	public Node createPersonType_2006(EObject domainElement,
			View containerView, int index, boolean persisted,
			PreferencesHint preferencesHint) {
		Node node = NotationFactory.eINSTANCE.createNode();
		node.getStyles().add(NotationFactory.eINSTANCE.createShapeStyle());
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(OrgcVisualIDRegistry.getType(PersonTypeEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);

		BflowDiagramEditPart diagramEditPart = BflowDiagramEditPart.getCurrentViewer();
		Color fillcolor;
		Color foregroundcolor;
		if (diagramEditPart != null) {
			fillcolor = diagramEditPart.getColorSchema().getBackground(PersonTypeEditPart.class);
			foregroundcolor = diagramEditPart.getColorSchema().getForeground(PersonTypeEditPart.class);
		} else {
			fillcolor = ColorConstants.white;
			foregroundcolor = ColorConstants.black;
		}
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillcolor.getRGB()));
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(foregroundcolor.getRGB()));
		
		Node label5006 = createLabel(node,
				OrgcVisualIDRegistry.getType(PersonTypeNameEditPart.VISUAL_ID));
		return node;
	}

	/**
	 * @generated
	 */
	public Node createLocation_2007(EObject domainElement, View containerView,
			int index, boolean persisted, PreferencesHint preferencesHint) {
		Node node = NotationFactory.eINSTANCE.createNode();
		node.getStyles().add(NotationFactory.eINSTANCE.createShapeStyle());
		node.setLayoutConstraint(NotationFactory.eINSTANCE.createBounds());
		node.setType(OrgcVisualIDRegistry.getType(LocationEditPart.VISUAL_ID));
		ViewUtil.insertChildView(containerView, node, index, persisted);
		node.setElement(domainElement);
		stampShortcut(containerView, node);

		BflowDiagramEditPart diagramEditPart = BflowDiagramEditPart.getCurrentViewer();
		Color fillcolor;
		Color foregroundcolor;
		if (diagramEditPart != null) {
			fillcolor = diagramEditPart.getColorSchema().getBackground(LocationEditPart.class);
			foregroundcolor = diagramEditPart.getColorSchema().getForeground(LocationEditPart.class);
		} else {
			fillcolor = ColorConstants.white;
			foregroundcolor = ColorConstants.black;
		}
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getFillStyle_FillColor(), FigureUtilities.RGBToInteger(fillcolor.getRGB()));
		ViewUtil.setStructuralFeatureValue(node, NotationPackage.eINSTANCE.getLineStyle_LineColor(), FigureUtilities.RGBToInteger(foregroundcolor.getRGB()));
		
		Node label5007 = createLabel(node,
				OrgcVisualIDRegistry.getType(LocationNameEditPart.VISUAL_ID));
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
		edge.setType(OrgcVisualIDRegistry.getType(Relation1EditPart.VISUAL_ID));
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
		edge.setType(OrgcVisualIDRegistry.getType(Relation2EditPart.VISUAL_ID));
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
		if (!ModelEditPart.MODEL_ID.equals(OrgcVisualIDRegistry
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

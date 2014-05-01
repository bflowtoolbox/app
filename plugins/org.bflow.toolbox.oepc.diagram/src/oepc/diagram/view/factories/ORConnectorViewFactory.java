package oepc.diagram.view.factories;

import java.util.ArrayList;
import java.util.List;

import oepc.diagram.edit.parts.OEPCEditPart;
import oepc.diagram.edit.parts.ORConnectorEditPart;
import oepc.diagram.part.OepcVisualIDRegistry;

import org.bflow.toolbox.extensions.edit.parts.BflowDiagramEditPart;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.AbstractShapeViewFactory;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.ShapeStyle;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class ORConnectorViewFactory extends AbstractShapeViewFactory {

	/**
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	protected List createStyles(View view) {
		List styles = new ArrayList();
		styles.add(NotationFactory.eINSTANCE.createShapeStyle());
		return styles;
	}

	/**
	 * @generated NOT
	 */
	protected void decorateView(View containerView, View view,
			IAdaptable semanticAdapter, String semanticHint, int index,
			boolean persisted) {
		if (semanticHint == null) {
			semanticHint = OepcVisualIDRegistry
					.getType(ORConnectorEditPart.VISUAL_ID);
			view.setType(semanticHint);
		}
		super.decorateView(containerView, view, semanticAdapter, semanticHint,
				index, persisted);
		if (!OEPCEditPart.MODEL_ID.equals(OepcVisualIDRegistry
				.getModelID(containerView))) {
			EAnnotation shortcutAnnotation = EcoreFactory.eINSTANCE
					.createEAnnotation();
			shortcutAnnotation.setSource("Shortcut"); //$NON-NLS-1$
			shortcutAnnotation.getDetails().put(
					"modelID", OEPCEditPart.MODEL_ID); //$NON-NLS-1$
			view.getEAnnotations().add(shortcutAnnotation);
		}

		// setting up default color background to view style
		NotationPackage NOTATION = NotationPackage.eINSTANCE;
		EClass shapeStyle = NOTATION.getShapeStyle();
		ShapeStyle style = (ShapeStyle) view.getStyle(shapeStyle);
		if (style == null) {
			style = (ShapeStyle) view.createStyle(shapeStyle);
		}
		BflowDiagramEditPart.apply(ORConnectorEditPart.class, style);
	}
}

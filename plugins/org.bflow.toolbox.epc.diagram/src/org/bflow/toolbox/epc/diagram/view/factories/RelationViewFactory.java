package org.bflow.toolbox.epc.diagram.view.factories;

import java.util.ArrayList;
import java.util.List;

import org.bflow.toolbox.epc.diagram.edit.parts.RelationEditPart;
import org.bflow.toolbox.epc.diagram.part.EpcVisualIDRegistry;
import org.bflow.toolbox.extensions.edit.parts.BflowDiagramEditPart;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.ConnectionViewFactory;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.RoutingStyle;
import org.eclipse.gmf.runtime.notation.ShapeStyle;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class RelationViewFactory extends ConnectionViewFactory {

	/**
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	protected List createStyles(View view) {
		List styles = new ArrayList();
		styles.add(NotationFactory.eINSTANCE.createRoutingStyle());
		styles.add(NotationFactory.eINSTANCE.createFontStyle());
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
			semanticHint = EpcVisualIDRegistry
					.getType(RelationEditPart.VISUAL_ID);
			view.setType(semanticHint);
		}
		super.decorateView(containerView, view, semanticAdapter, semanticHint,
				index, persisted);

		NotationPackage NOTATION = NotationPackage.eINSTANCE;
		EClass routingStyle = NOTATION.getRoutingStyle();
		RoutingStyle routing = (RoutingStyle) view.getStyle(routingStyle);
		if (routing == null) {
			routing = (RoutingStyle) view.createStyle(routingStyle);
		}
		routing.setRouting(Routing.RECTILINEAR_LITERAL);
		
		EClass shapeStyle = NOTATION.getShapeStyle();
		ShapeStyle style = (ShapeStyle) view.getStyle(shapeStyle);
		if (style == null) {
			style = (ShapeStyle) view.createStyle(shapeStyle);
		}
		BflowDiagramEditPart.apply(RelationEditPart.class, style);

	}
}

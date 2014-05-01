package oepc.diagram.view.factories;

import java.util.ArrayList;
import java.util.List;

import oepc.diagram.edit.parts.ITSystemEditPart;
import oepc.diagram.edit.parts.ITSystemNameEditPart;
import oepc.diagram.edit.parts.OEPCEditPart;
import oepc.diagram.part.OepcVisualIDRegistry;

import org.bflow.toolbox.extensions.edit.parts.BflowDiagramEditPart;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.AbstractShapeViewFactory;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.ShapeStyle;
import org.eclipse.gmf.runtime.notation.TextAlignment;
import org.eclipse.gmf.runtime.notation.TextStyle;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class ITSystemViewFactory extends AbstractShapeViewFactory {

	/**
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	protected List createStyles(View view) {
		List styles = new ArrayList();
		styles.add(NotationFactory.eINSTANCE.createShapeStyle());
		styles.add(NotationFactory.eINSTANCE.createTextStyle());
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
					.getType(ITSystemEditPart.VISUAL_ID);
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
		IAdaptable eObjectAdapter = null;
		EObject eObject = (EObject) semanticAdapter.getAdapter(EObject.class);
		if (eObject != null) {
			eObjectAdapter = new EObjectAdapter(eObject);
		}
		getViewService().createNode(eObjectAdapter, view,
				OepcVisualIDRegistry.getType(ITSystemNameEditPart.VISUAL_ID),
				ViewUtil.APPEND, true, getPreferencesHint());

		// setting up default color background to view style
		NotationPackage NOTATION = NotationPackage.eINSTANCE;
		EClass shapeStyle = NOTATION.getShapeStyle();
		ShapeStyle style = (ShapeStyle) view.getStyle(shapeStyle);
		if (style == null) {
			style = (ShapeStyle) view.createStyle(shapeStyle);
		}
		BflowDiagramEditPart.apply(ITSystemEditPart.class, style);
		
		EClass textStyleEclass = NOTATION.getTextStyle();
		TextStyle textStyle = (TextStyle) view.getStyle(textStyleEclass);
		if (textStyle == null) {
			textStyle = (TextStyle) view.createStyle(textStyleEclass);
		}
		textStyle.setTextAlignment(TextAlignment.CENTER_LITERAL);
	}
}

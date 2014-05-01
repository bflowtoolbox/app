package org.bflow.toolbox.extensions.view.factories;

import java.util.ArrayList;
import java.util.List;

import org.bflow.toolbox.extensions.colorschemes.OriginalColorSchema;
import org.bflow.toolbox.extensions.edit.parts.BflowDiagramEditPart;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.gmf.runtime.diagram.ui.view.factories.DiagramViewFactory;
import org.eclipse.gmf.runtime.notation.DiagramStyle;
import org.eclipse.gmf.runtime.notation.MeasurementUnit;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;


/**
 * An view factory providing same styles for each 
 * <code>BflowDiagramEditPart</code>.
 * @author Joerg Hartmann
 * @since 0.0.7
 *
 */
public class BflowDiagramViewFactory extends DiagramViewFactory{


	/**
	 * Create all styles.
	 * @param view
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List createStyles(View view) {
		List styles = new ArrayList();
		styles.add(NotationFactory.eINSTANCE.createDiagramStyle());
		return styles;
	}

	
	/**
	 * Returns the <code>MeasurementUnit</code>.
	 * @return
	 */
	protected MeasurementUnit getMeasurementUnit() {
		return MeasurementUnit.PIXEL_LITERAL;
	}
	
	/**
	 * Decorates views.
	 * @param view
	 * @param semanticAdapter
	 * @param diagramKind
	 * 
	 * @generated NOT
	 */
	protected void decorateView(View view, IAdaptable semanticAdapter, 
			String diagramKind) {
		super.decorateView(view, semanticAdapter, diagramKind);
		
		
		NotationPackage NOTATION = NotationPackage.eINSTANCE;
		EClass diagramStyleClass = NOTATION.getDiagramStyle();
		DiagramStyle diagramStyle = 
			(DiagramStyle) view.getStyle(diagramStyleClass);
		if(diagramStyle == null){
			diagramStyle = 
				(DiagramStyle) view.createStyle(diagramStyleClass);
		}
		BflowDiagramEditPart diagramEditPart = 
			BflowDiagramEditPart.getCurrentViewer();

		OriginalColorSchema schema = new OriginalColorSchema();
		diagramStyle.setDescription(schema.toString());
		
		if(diagramEditPart != null){
			diagramEditPart.setColorSchema(schema);
		}
	}
}

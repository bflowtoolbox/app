package org.bflow.toolbox.hive.annotations;

import java.util.HashSet;

import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.CreateDecoratorsOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

/**
 * This class is used by the plug-in to initialize the AnnotationDecorators to
 * the model elements. Also holds a list of AnnotationToggleListeners, which
 * should react an the annotation toggle button {@link AnnotationViewPart}
 * 
 * @author Felix Hoess
 * @since 05.05.2015
 *
 */
public class AnnotationDecoratorProvider implements IDecoratorProvider {

	private static final String KEY = "IconAnnotation";
	static private HashSet<IAnnotationToggleListener> showAnnotationToggleButtonlistener = new HashSet<IAnnotationToggleListener>();
	
	
	@Override
	public void addProviderChangeListener(IProviderChangeListener listener) {
	}

	@Override
	public boolean provides(IOperation operation) {
		if (!(operation instanceof CreateDecoratorsOperation)) {
			return false;
		}

		IDecoratorTarget decoratorTarget = ((CreateDecoratorsOperation) operation).getDecoratorTarget();
		View view = (View) decoratorTarget.getAdapter(View.class);
		return view != null;
	}

	@Override
	public void createDecorators(IDecoratorTarget decoratorTarget) {
		EditPart editPart = (EditPart) decoratorTarget.getAdapter(EditPart.class);
		
		if (editPart instanceof DiagramEditPart)
			return;
		if (editPart instanceof AbstractEditPart) {
			Object model = editPart.getModel();
			if ((model instanceof View)) {
				View view = (View) model;
				if (!(view instanceof Edge) && !view.isSetElement()) {
					return;
				}
			}
			EditDomain ed = editPart.getViewer().getEditDomain();
			if (!(ed instanceof DiagramEditDomain)) {
				return;
			}
			
			// Use the custom AnnotationDecorator
			AnnotationDecorator decorator =new AnnotationDecorator(decoratorTarget);
			decoratorTarget.installDecorator(KEY  ,decorator );
		}
	}

	@Override
	public void removeProviderChangeListener(IProviderChangeListener listener) {
	}

	public static HashSet<IAnnotationToggleListener> getListener() {
		return showAnnotationToggleButtonlistener;
	}

	public static void addListener(IAnnotationToggleListener listener) {
		AnnotationDecoratorProvider.showAnnotationToggleButtonlistener.add(listener);
	}

	public static void noticeToggleChange(boolean showAnnotations) {
		for (IAnnotationToggleListener lis: showAnnotationToggleButtonlistener){
			lis.noticeToggleChange(showAnnotations);}
	}

	public static void removeListener(AnnotationDecorator annotationDecorator) {
		showAnnotationToggleButtonlistener.remove(annotationDecorator);
		
	}
}

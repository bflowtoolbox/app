package org.bflow.toolbox.hive.annotations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Set;

import org.bflow.toolbox.hive.attributes.AttributeFileRegistry;
import org.bflow.toolbox.hive.attributes.AttributeFileRegistryEvent;
import org.bflow.toolbox.hive.attributes.IAttributeFileRegistryListener;
import org.bflow.toolbox.hive.attributes.utils.EMFUtility;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget.Direction;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Image;

/**
 * This AnnotationDecorator is used to show annotation symbols.
 * 
 * @author Felix Hoess
 * @since 05.05.2015
 *
 */
public class AnnotationDecorator extends MultipleAbstractDecorator implements
		IAttributeFileRegistryListener, IAnnotationToggleListener{
	
	/**
	 * True if annotation symbols should be visible
	 */
	private boolean isVisible = true;

	public AnnotationDecorator(IDecoratorTarget decoratorTarget) {
		super(decoratorTarget);

	}
	
/**
 * Adds this Object to a couple of listeners at activation
 */
	@Override
	public void activate() {
		//register this as a listener to handle changes if the registry changes
		AttributeFileRegistry.getInstance().addRegistryListener(this); 
		// and to changes of the view toggle button
		AnnotationDecoratorProvider.addListener(this);
	}

	/**
	 * refreshes all annotations of this decorator, (checks the attributes of an
	 * element for certain keywords given in the .xml files)
	 */
	@Override
	public void refresh() {
		View view = (View) getDecoratorTarget().getAdapter(View.class);
		if (view == null || view.eResource() == null) return;
		EditPart editPart = (EditPart) getDecoratorTarget().getAdapter(EditPart.class);
		if (editPart == null || editPart.getViewer() == null) return;

		EObject eObj = EMFUtility.getEObject((IGraphicalEditPart) editPart);
		String id = EMFCoreUtil.getProxyID(eObj);

		// get decorations for annotation
		Set<ShapeDecorationInfo> shapeDecorations = AnnotationRuleController.getInstance().getAnnotationsForRules(id);

		deactivate();
		
		if (!isVisible) return;
		
		for (ShapeDecorationInfo shapeDecoration : shapeDecorations) {
			// add decoration if keyword was found
			if (editPart instanceof org.eclipse.gef.GraphicalEditPart) {
					int margin = -1;
					if (editPart instanceof org.eclipse.gef.GraphicalEditPart) {
						margin = MapModeUtil.getMapMode(((org.eclipse.gef.GraphicalEditPart) editPart).getFigure()).DPtoLP(margin);
					}
					
					Direction direction = shapeDecoration.getPosition();
					if (view instanceof Edge) {
						direction = Direction.CENTER;
					}
					addDecoration(this.getDecoratorTarget().addShapeDecoration(getImage(shapeDecoration.getIconResourceName()),
							direction, margin, false));
				}
		}
		
		if (shapeDecorations.isEmpty()) {
			// deactivate decorations if no keyword was found
			this.deactivate();
		}
	}

	private Image getImage(String imgFileName) {

		InputStream is = null;
		try {
			is = new FileInputStream(AnnotationLauncherConfigurator.getANNOTATIONLOGIC_FOLDER_PATH() + imgFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Image img = new Image(null, is);
		return img;
	}

	@Override
	public void noticeAttributeFileChange(AttributeFileRegistryEvent event) {
		refresh();

	}
	
	@Override
	public void noticeToggleChange(boolean show) {
		isVisible = show;
		refresh();
	}
}

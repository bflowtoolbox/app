package org.bflow.toolbox.hive.gmfbridge.graphiti.annotations;

import java.util.Iterator;
import java.util.Set;

import org.bflow.toolbox.hive.annotations.AnnotationDecorator;
import org.bflow.toolbox.hive.annotations.AnnotationRuleController;
import org.bflow.toolbox.hive.annotations.ShapeDecorationInfo;
import org.bflow.toolbox.hive.gmfbridge.graphiti.adapters.IDisposable;
import org.bflow.toolbox.hive.libs.aprogu.collections.HList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget.Direction;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.tb.IDecorator;
import org.eclipse.graphiti.tb.ImageDecorator;
import org.eclipse.graphiti.ui.internal.GraphitiUIPlugin;
import org.eclipse.jface.resource.ImageRegistry;

/**
 * Provides methods to create decorations that can be applied to Graphiti based
 * pictogram elements.
 * 
 * @author Arian Storch
 * @since 2015-12-23
 * 
 */
@SuppressWarnings("restriction")
public class AnnotationDecorationSupport implements IDisposable {	
	private static AnnotationDecorationSupport fCurrentInstance;
	
	/**
	 * Returns the current active instance. May be NULL
	 * 
	 * @return Instance or NULL
	 */
	public static AnnotationDecorationSupport Current() {
		return fCurrentInstance;
	}
	
	private String fDiagramTypeProviderId;
	
	/**
	 * Creates a new instance that operates with the given diagram type provider
	 * id.
	 * 
	 * @param diagramTypeProviderId
	 *            Diagram type provider id
	 */
	public AnnotationDecorationSupport(String diagramTypeProviderId) {
		fCurrentInstance = this;
		fDiagramTypeProviderId = diagramTypeProviderId;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.hive.gmfbridge.graphiti.adapters.IDisposable#dispose()
	 */
	@Override
	public void dispose() {
		if (fCurrentInstance == this)
			fCurrentInstance = null;
	}
	
	/**
	 * Returns all decorators of the given element which represent the current
	 * applied annotations.
	 * 
	 * @param element
	 *            Element to process
	 * @return All decorators derived from the applied annotations
	 */
	public IDecorator[] getDecoratorsForElement(PictogramElement element) {
		if (element == null) return new IDecorator[0];		
		
		EObject businessObject = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(element);
		String id = EMFCoreUtil.getProxyID(businessObject);
		
		HList<IDecorator> decoratorList = new HList<>(IDecorator.class);
		
		Set<ShapeDecorationInfo> annotationDecoratorSet = AnnotationRuleController.getInstance().getAnnotationsForRules(id);
		for (Iterator<ShapeDecorationInfo> itr = annotationDecoratorSet.iterator(); itr.hasNext();) {
			ShapeDecorationInfo info = itr.next();
			String imageId = checkAndInstallImage(info);
			if (imageId == null) continue;
			
			ImageDecorator imageDecorator = new ImageDecorator(imageId);
			
			Direction direction = info.getPosition();
			if (direction == Direction.EAST) {
				// TODO
			} else if (direction == Direction.SOUTH) {
				// TODO
			}
			
			decoratorList.add(imageDecorator);
		}
		
		IDecorator[] resultSet = decoratorList.toArray();
		return resultSet;
	}
	
	/**
	 * Checks if the Graphiti image registry already contains the image that is
	 * associated with the given decoration info. If not, the image is created
	 * and added. Else, nothing happens. This method returns the name of the
	 * image that MUST be used to identify the image within the image registry.
	 * 
	 * @param info
	 *            Shape decoration info
	 * @return Id of the image within the Graphiti image registry
	 */
	private String checkAndInstallImage(ShapeDecorationInfo info) {
		String iconResourceName = info.getIconResourceName();
		String graphitiImageRegistryId = buildGraphitiImageRegistryId(iconResourceName);
		
		ImageRegistry imageRegistry = GraphitiUIPlugin.getDefault().getImageRegistry();
		if (imageRegistry.get(graphitiImageRegistryId) != null) return iconResourceName;
		
		org.eclipse.swt.graphics.Image image = AnnotationDecorator.getImage(info);
		if (image == null) return null;
		
		imageRegistry.put(graphitiImageRegistryId, image);
		return iconResourceName;
	}
	
	/**
	 * Creates an image id that can be evaluated by the Graphiti image registry.
	 * 
	 * @param imageId
	 *            Id of the image
	 * @return Special image id that can be evaluated by the Graphiti image
	 *         registry
	 */
	private String buildGraphitiImageRegistryId(String imageId) {
		String id = String.format("%s||%s", fDiagramTypeProviderId, imageId);
		return id;
	}

}

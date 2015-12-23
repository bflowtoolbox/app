package org.bflow.toolbox.hive.gmfbridge.graphiti.annotations;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bflow.toolbox.hive.annotations.AnnotationDecorator;
import org.bflow.toolbox.hive.annotations.AnnotationRuleController;
import org.bflow.toolbox.hive.annotations.ShapeDecorationInfo;
import org.bflow.toolbox.hive.gmfbridge.graphiti.adapters.IDisposable;
import org.bflow.toolbox.hive.libs.aprogu.collections.HList;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.workspace.AbstractEMFOperation;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget.Direction;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.tb.IDecorator;
import org.eclipse.graphiti.tb.ImageDecorator;
import org.eclipse.graphiti.ui.editor.DiagramEditor;
import org.eclipse.graphiti.ui.internal.GraphitiUIPlugin;
import org.eclipse.jface.resource.ImageRegistry;

public class AnnotationDecorationSupport implements IDisposable {
	private static final String DiagramTypeProviderId = "org.eclipse.bpmn2.modeler.ui.diagram.MainBPMNDiagramType";
	
	private static AnnotationDecorationSupport fCurrentInstance;
	
	public static AnnotationDecorationSupport Current() {
		return fCurrentInstance;
	}
	
	public AnnotationDecorationSupport() {
		fCurrentInstance = this;
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
	
	public IDecorator[] getDecoratorsForElement(PictogramElement element) {
		if (element == null) return new IDecorator[0];		
		EObject businessObject = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(element);
		String id = EMFCoreUtil.getProxyID(businessObject);
		
		Set<ShapeDecorationInfo> annotationDecoratorSet = AnnotationRuleController.getInstance().getAnnotationsForRules(id);
		HList<IDecorator> decoratorList = new HList<>(IDecorator.class);
		for (Iterator<ShapeDecorationInfo> itr = annotationDecoratorSet.iterator(); itr.hasNext();) {
			ShapeDecorationInfo info = itr.next();
			String imageId = checkAndInstallImage(info);
			if (imageId == null) continue;
			
			ImageDecorator imageDecorator = new ImageDecorator(imageId);
			
			Direction direction = info.getPosition();
			if (direction == Direction.EAST) {
				// TODO
			}
			
			decoratorList.add(imageDecorator);
		}
		
		IDecorator[] resultSet = decoratorList.toArray();
		return resultSet;
	}
	
	private String checkAndInstallImage(ShapeDecorationInfo info) {
		String iconResourceName = info.getIconResourceName();
		String diagramImageId = buildImageId(iconResourceName);
		
		ImageRegistry imageRegistry = GraphitiUIPlugin.getDefault().getImageRegistry();
		if (imageRegistry.get(diagramImageId) != null) return iconResourceName;
		
		org.eclipse.swt.graphics.Image image = AnnotationDecorator.getImage(info);
		if (image == null) return null;
		
		imageRegistry.put(diagramImageId, image);
		return iconResourceName;
	}
	
	private String buildImageId(String imageId) {
		String id = String.format("%s||%s", DiagramTypeProviderId, imageId);
		return id;
	}
	
	private void addAnnotationDecorator(Diagram diagram, EObject obj) {	
		DiagramEditor diagramEditor = ModelUtil.getDiagramEditor(obj);
		@SuppressWarnings("unchecked")
		List<EditPart> selectedEditParts = diagramEditor.getGraphicalViewer().getSelectedEditParts();
		PictogramElement selectedPictogramElement = (PictogramElement) ((EditPart)selectedEditParts.get(0)).getModel();		
		applyImageDecoration(diagramEditor, selectedPictogramElement);
	}
	
	private void applyImageDecoration(DiagramEditor diagramEditor, PictogramElement pictogramElement) {		
		final PictogramElement shapeModel = (PictogramElement) pictogramElement;
		ContainerShape selfContainerShape = (ContainerShape) shapeModel;
		final ContainerShape diagramContainerShape = selfContainerShape.getContainer();
		
		AbstractEMFOperation op = new AbstractEMFOperation(diagramEditor.getEditingDomain(), "foobar") {
			
			@Override
			protected IStatus doExecute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				
				Shape imageContainerShape = Graphiti.getPeCreateService().createShape(diagramContainerShape, true);
				Image img = Graphiti.getCreateService().createImage(imageContainerShape, "foo");
				
				int x = shapeModel.getGraphicsAlgorithm().getX();
				int y = shapeModel.getGraphicsAlgorithm().getY();
				int width = shapeModel.getGraphicsAlgorithm().getWidth();
				int height = shapeModel.getGraphicsAlgorithm().getHeight();
				
				int defaultWidth = 32;
				int defaultHeight = 32;
				int paddingWidth = defaultWidth / 2;
				int paddingHeight = defaultHeight / 2;
				
				img.setX(x + width + paddingWidth);
				img.setY(y - paddingHeight);
				
				img.setWidth(defaultWidth);
				img.setHeight(defaultHeight);
				
				img.setStretchH(true);
				img.setStretchV(true);
				img.setProportional(true);
				
				return Status.OK_STATUS;
			}
		};
		
		boolean dooIt = true;
		try {
			if (!diagramEditor.getEditingDomain().isReadOnly(shapeModel.eResource()) && dooIt)
				op.execute(null, null);
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}

}

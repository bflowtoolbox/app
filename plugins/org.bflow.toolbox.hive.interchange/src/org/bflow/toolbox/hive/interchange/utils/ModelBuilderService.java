package org.bflow.toolbox.hive.interchange.utils;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bflow.toolbox.hive.interchange.mif.core.IEdge;
import org.bflow.toolbox.hive.interchange.mif.core.IModelBuilderAttendant;
import org.bflow.toolbox.hive.interchange.mif.core.IModelData;
import org.bflow.toolbox.hive.interchange.mif.core.IShape;
import org.bflow.toolbox.hive.interchange.mif.core.InterchangeProcessingException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory;
import org.eclipse.gmf.runtime.diagram.ui.commands.DeferredCreateConnectionViewAndElementCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest.ConnectionViewDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateUnspecifiedTypeRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest.ViewAndElementDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequestFactory;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.swt.widgets.Shell;

/**
 * Provides a service that builds models based on given model meta data.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 12/07/13
 * @version 15/07/13
 */
public class ModelBuilderService {
	
	/**
	 * Builds a model based on the given model meta data.
	 * 
	 * @param modelData Model meta data
	 * @param diagramURI URI where the model is created and stored
	 * @param modelBuilderAttendant Instance of model builder attendant
	 * @return Instance of IFile which points to the created model file.
	 * @throws InterchangeProcessingException
	 */
	public static IFile build(IModelData modelData, URI diagramURI, final IModelBuilderAttendant modelBuilderAttendant)
					throws InterchangeProcessingException {
		// Creating the diagram
		Resource diagramResource = modelBuilderAttendant.createDiagramInstance(diagramURI, new NullProgressMonitor());
		Diagram diagram = (Diagram) diagramResource.getContents().get(1);
		
		CompoundCommand shapesCreationCommand = new CompoundCommand();
		final CompoundCommand shapesEditCommand = new CompoundCommand();
		
		final Map<IShape, EObject> shapeToElementMap = new HashMap<IShape, EObject>();
		final Map<IEdge, EObject> edgeToElementMap = new HashMap<IEdge, EObject>();
		
		Shell myShell = new Shell();
		DiagramEditPart diagramEditPart = OffscreenEditPartFactory.getInstance().createDiagramEditPart(diagram, myShell);
		
		// Creating the shapes
		IShape[] shapes = modelData.getShapes();
		for(int i = 0; i < shapes.length; i++) {
			final IShape shape = shapes[i];
			String shapeTypeId = (String) shape.getType();
			final String shapeName = shape.getName();
			
			// Code copied from EpcElementTypes
			IElementType elementType = ElementTypeRegistry.getInstance().getType(shapeTypeId);
			final CreateUnspecifiedTypeRequest request = new CreateUnspecifiedTypeRequest(
					Collections.singletonList(elementType), diagramEditPart.getDiagramPreferencesHint());
			// Takes more time??? Really?
//			CreateViewRequest request = CreateViewRequestFactory.getCreateShapeRequest(elementType, diagramEditPart.getDiagramPreferencesHint());
			request.setLocation(new Point(shape.getX(), shape.getY()));
			request.setSize(new Dimension(shape.getWidth(), shape.getHeight()));
			
			Command requestCommand = diagramEditPart.getCommand(request);
			shapesCreationCommand.add(requestCommand);
			
			Command createShapeNameCommand = new Command() {
				@Override
				public void execute() {
					@SuppressWarnings("unchecked")
					List<IAdaptable> listAdaptable = (List<IAdaptable>) request.getNewObject();
					IAdaptable adaptableInstance = listAdaptable.get(0);
					ViewAndElementDescriptor descriptor = (ViewAndElementDescriptor) adaptableInstance;
//					EObject eObject = descriptor.getCreateElementRequestAdapter().resolve();
					final EObject element = (EObject) descriptor.getElementAdapter().getAdapter(EObject.class);
					
					if(element == null)
						return;
					
					// Mapping shape to element
					shapeToElementMap.put(shape, element);
					
					EStructuralFeature structuralFeature = modelBuilderAttendant.getEStructuralFeatureFor(null, "name");
					
					SetRequest setRequest = new SetRequest(element, structuralFeature, shapeName);
					SetValueCommand svc = new SetValueCommand(setRequest);
					shapesEditCommand.add(new ICommandProxy(svc));
					// Needs write transaction
//					if(element != null)
//						element.setName(shapeName);
				}
			};
			shapesCreationCommand.add(createShapeNameCommand);
		}
		
		// Commit compound commands
		try {
			diagramEditPart.getDiagramEditDomain().getDiagramCommandStack().execute(shapesCreationCommand);
			diagramEditPart.getDiagramEditDomain().getDiagramCommandStack().execute(shapesEditCommand);
		} catch(Exception ex) {
			throw new InterchangeProcessingException("Error on created shapes!", ex);
		}
		
		CompoundCommand edgesCreationCommand = new CompoundCommand();
		
		// Creating the edges
		IEdge[] edges = modelData.getEdges();
		for(int i = 0; i < edges.length; i++) {
			final IEdge edge = edges[i];
			IShape srcShape = edge.getSource();
			IShape tgtShape = edge.getTarget();
			String edgeTypeId = (String) edge.getType();
			// Code copied from EpcElementTypes
			IElementType elementType = ElementTypeRegistry.getInstance().getType(edgeTypeId);
			
			EObject srcElement = shapeToElementMap.get(srcShape);
			EObject tgtElement = shapeToElementMap.get(tgtShape);
			
			EditPart srcEditPart = findEditPart(diagramEditPart, srcElement);
			EditPart tgtEditPart = findEditPart(diagramEditPart, tgtElement);
			
			final CreateConnectionViewRequest request = 
					CreateViewRequestFactory.getCreateConnectionRequest(
							elementType, diagramEditPart.getDiagramPreferencesHint());
//			CreateConnectionRequest request = new CreateUnspecifiedTypeConnectionRequest(
//					Collections.singletonList(elementType), true, diagramEditPart.getDiagramPreferencesHint());
			
			DeferredCreateConnectionViewAndElementCommand createCommand = new DeferredCreateConnectionViewAndElementCommand(
					(CreateConnectionViewAndElementRequest) request, srcEditPart, tgtEditPart, diagramEditPart.getViewer());
			
			request.setSourceEditPart(srcEditPart);
			request.setTargetEditPart(tgtEditPart);
			
			edgesCreationCommand.add(new ICommandProxy(createCommand));
			
			Command edgeMappingCommand = new Command() {
				@Override
				public void execute() {
					ConnectionViewDescriptor descriptor = request.getConnectionViewDescriptor();
					EObject element = (EObject) descriptor.getElementAdapter().getAdapter(EObject.class);
					edgeToElementMap.put(edge, element);
				}
			};
			edgesCreationCommand.add(edgeMappingCommand);
		}
		
		// Commit compound commands
		try {
			diagramEditPart.getDiagramEditDomain().getDiagramCommandStack().execute(edgesCreationCommand);
//					diagramEditPart.getDiagramEditDomain().getDiagramCommandStack().execute(edgesEditCommand);
		} catch(Exception ex) {
			throw new InterchangeProcessingException("Error on creating edges!", ex);
		}
		
//		final Epc epc = (Epc) diagramResource.getContents().get(0);
		
//		TransactionalEditingDomain domain = 
//				TransactionalEditingDomain.Factory.INSTANCE.getEditingDomain(epc.eResource().getResourceSet());
		
//		CreateUnspecifiedTypeRequest req = new CreateUnspecifiedTypeRequest(
//				Collections.singletonList(EpcElementTypes.AND_2003), EpcDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
//				
//				
//		Command command = diagramEditPart.getCommand(req);
//		req.setLocation(new Point(20, 20));
//		req.setSize(new Dimension(32, 32));
//			
//		diagramEditPart.getDiagramEditDomain().getDiagramCommandStack().execute(command);
		
//		List<?> createdTypes = (List<?>) request.getNewObject();
//		ViewAndElementDescriptor r1 = (ViewAndElementDescriptor) createdTypes.get(0);
//		Object adapter = r1.getElementAdapter();
//		Object eObject = r1.getCreateElementRequestAdapter().resolve();
		
		// Creating the attributes
		try {
			modelBuilderAttendant.createAttributeFile(diagramEditPart);
			modelBuilderAttendant.setAttributes(diagramEditPart, modelData.getModel().getAttributes());
			
			for(IShape shape : shapeToElementMap.keySet()) {
				EObject eObj = shapeToElementMap.get(shape);
				EditPart editPart = findEditPart(diagramEditPart, eObj);
				Map<String, String> attributes = shape.getAttributes();
				modelBuilderAttendant.setAttributes(editPart, attributes);
			}
			
			for(IEdge edge : edgeToElementMap.keySet()) {
				EObject eObj = edgeToElementMap.get(edge);
				EditPart editPart = findEditPart(diagramEditPart, eObj);
				Map<String, String> attributes = edge.getAttributes();
				modelBuilderAttendant.setAttributes(editPart, attributes);
			}
		} catch(Exception ex) {
			throw new InterchangeProcessingException("Could not create the attribute file!", ex);
		}
		
		// Setup styling
		try {
			modelBuilderAttendant.onBeforeFinish(diagramEditPart);
		} catch(Exception ex) {
			throw new InterchangeProcessingException("Error while performing 'onBeforeFinish'!", ex);
		}
		
		try {
			diagramResource.save(modelBuilderAttendant.getSaveOptions());
		} catch (IOException e) {
			throw new InterchangeProcessingException("Could not save the newly created resource!", e);
		}
		
		myShell.dispose();
		
		IFile ifile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(diagramURI.toPlatformString(true)));
		return ifile;
	}
	
	/**
	 * Finds the instance of EditPart which is defined by the given semantic element. 
	 * 
	 * @param diagramEditPart The diagram edit part
	 * @param element Semantic element for the wanted edit part
	 * @return The wanted edit part or null
	 */
	private static EditPart findEditPart(DiagramEditPart diagramEditPart, EObject element) {
		EditPart resolvedEditPart = diagramEditPart.findEditPart(diagramEditPart, element);
		if(resolvedEditPart != null)
			return resolvedEditPart;
		
		// Looking for connections
		for(int i = 0 ; i < diagramEditPart.getConnections().size(); i++) {
			EditPart editPart = (EditPart) diagramEditPart.getConnections().get(i);
			EObject eObject = (EObject) editPart.getAdapter(EObject.class);
			if(eObject == element)
				return editPart;
		}
		
		return null;
		
		// Fallback strategy 
//		for(int i = 0; i < diagramEditPart.getChildren().size(); i++) {
//			EditPart editPart = (EditPart) diagramEditPart.getChildren().get(i);
//			EObject editPartElement = (EObject) editPart.getAdapter(EObject.class);
//			if(element == editPartElement)
//				return editPart;
//		}
//		return null;
	}

}

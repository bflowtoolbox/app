package org.bflow.toolbox.hive.gmfbridge.graphiti.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.bflow.toolbox.hive.attributes.AttributeFile;
import org.bflow.toolbox.hive.attributes.AttributeFileRegistry;
import org.bflow.toolbox.hive.attributes.AttributeFileRegistryEvent;
import org.bflow.toolbox.hive.attributes.IAttributeFileListener;
import org.bflow.toolbox.hive.attributes.IAttributeFilePersister;
import org.bflow.toolbox.hive.attributes.IAttributeFileRegistryListener;
import org.eclipse.bpmn2.BaseElement;
import org.eclipse.bpmn2.Definitions;
import org.eclipse.bpmn2.RootElement;
import org.eclipse.bpmn2.modeler.core.adapters.ExtendedPropertiesProvider;
import org.eclipse.bpmn2.modeler.core.model.ModelDecorator;
import org.eclipse.bpmn2.modeler.core.utils.ModelUtil;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.workspace.EMFCommandOperation;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.impl.ViewImpl;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;

/**
 * Provides an adapter for
 * {@link org.eclipse.graphiti.ui.internal.parts.DiagramEditPart}.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 27.03.2015
 * 
 */
@SuppressWarnings("restriction")
public class DiagramEditPartAdapter extends DiagramEditPart implements IAttributeFilePersister, IDisposable {
	
	private static final String DynamicAddonAttributePrefix = "customAddonAttribute"; // "dynamicAddonAttribute";

	private org.eclipse.graphiti.ui.internal.parts.DiagramEditPart fGraphitiDiagramEditPart;
	@SuppressWarnings("unused")
	private GraphicalViewer fDiagramGraphicalViewer;
	
	@SuppressWarnings("unused")
	private EObject fDiagramModel;
	
	private EObject fProcessModel;
	private View fNotationView = new ViewImpl() {};
	
	private IAttributeFileListener fAttributeFileListener = new _AttributeFileListener();
	private IAttributeFileRegistryListener fAttributeFileRegistryListener = new _AttributeFileRegistryListener();
	private AttributeFile fCurrentAttributeFile;
	
	/**
	 * Creates a new instance based on the given instances.
	 * 
	 * @param graphitiDiagramEditPart
	 */
	public DiagramEditPartAdapter(org.eclipse.graphiti.ui.internal.parts.DiagramEditPart graphitiDiagramEditPart) {
		super(null);
		fGraphitiDiagramEditPart = graphitiDiagramEditPart;
		fDiagramGraphicalViewer = (GraphicalViewer) fGraphitiDiagramEditPart.getViewer();
		fDiagramModel = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(fGraphitiDiagramEditPart.getPictogramElement());
		
		fProcessModel = getProcessModel();
		fNotationView.setElement(fProcessModel);
		
		// Subscribe to attribute file
		AttributeFile attributeFile = AttributeFileRegistry.getInstance().getActiveAttributeFile();
		if (attributeFile != null) {
			attributeFile.removeListener(fAttributeFileListener);
			attributeFile.addListener(fAttributeFileListener);
			fCurrentAttributeFile = attributeFile;
		}
		
		AttributeFileRegistry.getInstance().addRegistryListener(fAttributeFileRegistryListener);
	}
	
	/**
	 * Tells this instance that it is going to be disposed and it can release
	 * all its bound resources.
	 */
	@Override
	public void dispose() {
		if (fCurrentAttributeFile != null) {
			fCurrentAttributeFile.removeListener(fAttributeFileListener);
			fCurrentAttributeFile = null;
		}
		
		AttributeFileRegistry.getInstance().removeRegistryListener(fAttributeFileRegistryListener);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart#getConnections()
	 */
	@Override
	public List<?> getConnections() {
		EObject model = (EObject)fGraphitiDiagramEditPart.getModel();
		Diagram diagram = (Diagram) model;
		
		List<Connection> connectionModels = diagram.getConnections();
		List<EditPart> connectionEditParts = new ArrayList<>(connectionModels.size());
		for (Connection connectionModel : connectionModels) {
			ConnectionEditPartAdapter connectionEditPart = AdapterFactory.getConnectionEditPartAdapter(connectionModel);
			connectionEditParts.add(connectionEditPart);
		}
		return connectionEditParts;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractEditPart#getChildren()
	 */
	@Override
	public List<?> getChildren() {
		EObject model = (EObject)fGraphitiDiagramEditPart.getModel();
		Diagram diagram = (Diagram) model;
		
		List<Shape> shapeModels = diagram.getChildren();
		List<EditPart> shapeEditParts = new ArrayList<>(shapeModels.size());
		for (Shape shapeModel : shapeModels) {
			ShapeEditPartAdapter shapeEditPart = AdapterFactory.getShapeEditPartAdapter(shapeModel);
			shapeEditParts.add(shapeEditPart);
		}
		return shapeEditParts;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#resolveSemanticElement()
	 */
	@Override
	public EObject resolveSemanticElement() {		
		return fProcessModel; // XXX fDiagramModel
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#getNotationView()
	 */
	@Override
	public View getNotationView() {
		return fNotationView;
	}
	
	/**
	 * Returns the underlying process model element.
	 * @return Process model element
	 */
	private BaseElement getProcessModel() {
		EObject model = (EObject)fGraphitiDiagramEditPart.getModel();
		Diagram diagram = (Diagram) model;
		
		// TODO Check references / dependencies
//		org.eclipse.dd.di.Diagram diagramModel = (org.eclipse.dd.di.Diagram) Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(diagram);
//		DiagramElement diagramModelRootElement = diagramModel.getRootElement();
//		BPMNPlane bpmnPlane = (BPMNPlane) diagramModelRootElement;
//		BaseElement baseElement = bpmnPlane.getBpmnElement();
		
		EObject diagramModel = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(diagram);
		EObject diagramContainer = diagramModel.eContainer();
		Definitions definitions = (Definitions) diagramContainer;
		RootElement baseElement = definitions.getRootElements().get(0);
		
		return baseElement;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#getModel()
	 */
	@Override
	public Object getModel() {
		return resolveSemanticElement();
	}
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.attributes.IAttributeFilePersister#save(java.util.HashMap)
	 */
	@Override
	public void save(HashMap<String, HashMap<String, String>> attributes) throws Exception {
		// Nothing to do because we add/remove attributes at runtime
	}
	
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.hive.attributes.IAttributeFilePersister#load(java.util.HashMap)
	 */
	@Override
	public void load(HashMap<String, HashMap<String, String>> attributes) throws Exception {
		EObject model = (EObject)fGraphitiDiagramEditPart.getModel();
		Diagram diagram = (Diagram) model;
		
		// Lookup shapes
		List<Shape> shapes = diagram.getChildren();
		for (int i = -1; ++i != shapes.size();) {
			Shape shape = shapes.get(i);
			EObject shapeModel = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(shape);
			lookupAndApplyAttributes(shapeModel, attributes);
		}
		
		// Lookup connections
		List<Connection> connections = diagram.getConnections();
		for (int i = -1; ++i != connections.size();) {
			Connection connection = connections.get(i);
			EObject connectionModel = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(connection);
			lookupAndApplyAttributes(connectionModel, attributes);
		}
		
		// Lookup plane / diagram model
		lookupAndApplyAttributes(fProcessModel, attributes);
	}
	
	/**
	 * Checks if the given model object owns any attribute and adds it to the
	 * given hash map.
	 * 
	 * @param modelObject
	 *            Object to check and process
	 * @param attributes
	 *            HashMap can stores every found attribute
	 */
	private void lookupAndApplyAttributes(EObject modelObject, HashMap<String, HashMap<String, String>> attributes) {
		List<EStructuralFeature> features = ModelDecorator.getAnyAttributes(modelObject);
		
		for (EStructuralFeature structuralFeature : features) {
			if (!(structuralFeature instanceof EAttribute)) continue;
			EAttribute attribute = (EAttribute) structuralFeature;
			EClass attributeClass = (EClass) attribute.eContainer();
			EPackage attributePackage = (EPackage) attributeClass.eContainer();
			String nsprefix = attributePackage.getNsPrefix();
			if (!nsprefix.equalsIgnoreCase(DynamicAddonAttributePrefix)) continue;
			String attributeName = attribute.getName();
			String attributeValue = getAttributeValue(modelObject, attributeName);
			if (attributeValue == null) continue;
			
			attributeName = unescapeAttributeName(attributeName);
			String modelElementId = ModelUtil.getID(modelObject);
			HashMap<String, String> elementAttributeSet = attributes.get(modelElementId);
			if (elementAttributeSet == null) {
				attributes.put(modelElementId, (elementAttributeSet = new HashMap<>()));
			}
			elementAttributeSet.put(attributeName, attributeValue);
		}
	}
	
	private static final String WsEscapteToken = "_ws_";
	
	/**
	 * Replaces all whitespaces within the given string.
	 * 
	 * @param attributeName
	 *            Name to escape
	 * @return Escaped name
	 */
	private String escapeAttributeName(String attributeName) {
		return attributeName.replaceAll(" ", WsEscapteToken);
	}
	
	/**
	 * Restores all whitespaces within the given string.
	 * 
	 * @param attributeName
	 *            Name to unescape
	 * @return Unescaped name
	 */
	private String unescapeAttributeName(String attributeName) {
		return attributeName.replaceAll(WsEscapteToken, " ");
	}
	
	/**
	 * Looks up the model object within the current diagram edit part that has
	 * the given model element id.
	 * 
	 * @param modelElementId
	 *            Id of the element to look up
	 * @return Model object or NULL
	 */
	private EObject lookupModelObject(String modelElementId) {
		EObject model = (EObject)fGraphitiDiagramEditPart.getModel();
		Diagram diagram = (Diagram) model;
		
		// Lookup shapes
		List<Shape> shapes = diagram.getChildren();
		for (int i = -1; ++i != shapes.size();) {
			Shape shape = shapes.get(i);			
			EObject shapeModel = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(shape);
			String modelId = ModelUtil.getID(shapeModel);
			if (modelId.equalsIgnoreCase(modelElementId)) return shapeModel;
		}
		
		// Lookup connections
		List<Connection> connections = diagram.getConnections();
		for (int i = -1; ++i != connections.size();) {
			Connection connection = connections.get(i);			
			EObject connectionModel = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(connection);
			String modelId = ModelUtil.getID(connectionModel);
			if (modelId.equalsIgnoreCase(modelElementId)) return connectionModel;
		}
		
		// Lookup plane / diagram model
		String diagramModelId = ModelUtil.getID(fProcessModel);
		if (diagramModelId.equalsIgnoreCase(modelElementId)) return fProcessModel;
		
		return null;
	}
	
	/**
	 * Applies the given attribute name and value to the given model object.
	 * 
	 * @param objectModel
	 *            Model object that will be attributed
	 * @param attributeName
	 *            Name of the attribute
	 * @param attributeValue
	 *            Value of the attribute
	 */
	private void setAttributeValue(final EObject objectModel, final String attributeName, final String attributeValue) {
		AbstractCommand command = new AbstractCommand() {
			@Override
			public void redo() { }
			
			@Override
			public void execute() {				
				EStructuralFeature attribute = ModelDecorator.getAnyAttribute(objectModel, attributeName);
				if (attribute == null) {
					if (attributeValue == null) return;
					ModelDecorator modelDecorator = new ModelDecorator(objectModel.eClass().getEPackage());
					attribute = modelDecorator.addAnyAttribute(objectModel, DynamicAddonAttributePrefix, attributeName, attributeValue);
					return;
				}
				ExtendedPropertiesProvider.setValue(objectModel, attribute, attributeValue);				
			}
		};
		
		EMFCommandOperation commandOperation = new EMFCommandOperation(getEditingDomain(), command);
		try {
			commandOperation.execute(null, null);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the value of the attribute that may be owned by the given model
	 * object. Otherwise NULL is returned
	 * 
	 * @param modelObject
	 *            Model object that may own the attribute
	 * @param attributeName
	 *            Name of the attribute
	 * @return Attribute value or NULL
	 */
	private String getAttributeValue(final EObject modelObject, final String attributeName) {
		EStructuralFeature attribute = ModelDecorator.getAnyAttribute(modelObject, attributeName);
		if (attribute == null) return null;
		Object value = ExtendedPropertiesProvider.getValue(modelObject, attribute);
		if (value == null) return null;
		String str = value.toString();
		return str;
	}
	
	/**
	 * Implements {@link IAttributeFileListener}.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 11.04.2015
	 * 
	 */
	class _AttributeFileListener implements IAttributeFileListener {

		/* (non-Javadoc)
		 * @see org.bflow.toolbox.hive.attributes.IAttributeFileListener#onAttributeAdded(java.lang.String, java.lang.String, java.lang.String)
		 */
		@Override
		public void onAttributeAdded(String modelElementId, String attributeName, String attributeValue) {
			EObject modelObject = lookupModelObject(modelElementId);
			if (modelObject == null) return;
			
			attributeName = escapeAttributeName(attributeName);
			setAttributeValue(modelObject, attributeName, attributeValue);
		}

		/* (non-Javadoc)
		 * @see org.bflow.toolbox.hive.attributes.IAttributeFileListener#onAttributeRemoved(java.lang.String, java.lang.String)
		 */
		@Override
		public void onAttributeRemoved(String modelElementId, String attributeName) {
			EObject modelObject = lookupModelObject(modelElementId);
			if (modelObject == null) return;
			
			attributeName = escapeAttributeName(attributeName);
			setAttributeValue(modelObject, attributeName, null);
		}
		
		/* (non-Javadoc)
		 * @see org.bflow.toolbox.hive.attributes.IAttributeFileListener#onAttributesRemoved(java.lang.String)
		 */
		@Override
		public void onAttributesRemoved(String modelElementId) {
			EObject modelObject = lookupModelObject(modelElementId);
			if (modelObject == null) return;
			
			HashMap<String, HashMap<String, String>> attributeMap = new HashMap<>();
			lookupAndApplyAttributes(modelObject, attributeMap);
			HashMap<String, String> attributeValueMap = attributeMap.get(modelElementId);
			if (attributeValueMap == null) return;
			
			for (Iterator<Entry<String, String>> it = attributeValueMap.entrySet().iterator(); it.hasNext();) {
				Entry<String, String> entry = it.next();
				String attributeName = entry.getKey();
				onAttributeRemoved(modelElementId, attributeName);
			}
		}
	}
	
	/**
	 * Implements {@link IAttributeFileRegistryListener}.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 11.04.2015
	 * 
	 */
	class _AttributeFileRegistryListener implements IAttributeFileRegistryListener {
		@Override
		public void noticeAttributeFileChange(AttributeFileRegistryEvent event) {
			if (fCurrentAttributeFile != null) {
				fCurrentAttributeFile.removeListener(fAttributeFileListener);
				fCurrentAttributeFile = null;
			}
			
			fCurrentAttributeFile = event.attributeFile;
			
			if (fCurrentAttributeFile != null) {
				fCurrentAttributeFile.addListener(fAttributeFileListener);
			}
		}
	}
}
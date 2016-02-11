/**
 * 
 */
package org.bflow.toolbox.epc.templating.dialogs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.bflow.toolbox.epc.templating.dialogs.BflowTemplate.NamingVariable;
import org.bflow.toolbox.hive.interchange.mif.core.IEdge;
import org.bflow.toolbox.hive.interchange.mif.core.IShape;
import org.bflow.toolbox.hive.interchange.mif.core.InterchangeProcessingException;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.ui.commands.DeferredCreateConnectionViewAndElementCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest.ConnectionViewDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequestFactory;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest.ViewAndElementDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * Helps to embed a templatefile in a diagram-editpart
 * 
 * @author Markus Schnädelbach
 */
public abstract class TemplateEmbedService {
	
	private Map<IEdge, EObject> edgeToElementMap = new HashMap<IEdge, EObject>();
	private Map<IShape, EObject> shapeToElementMap = new HashMap<IShape, EObject>();

	protected DiagramDocumentEditor editor;
	protected BflowTemplate template;
	private org.eclipse.swt.graphics.Point location;

	
	public TemplateEmbedService(DiagramDocumentEditor editor, BflowTemplate template, org.eclipse.swt.graphics.Point location) {
		this.editor = editor;
		this.template = template;
		this.location = location;
	}
	
	
	/**
	 * Connects the template and the base model.
	 * @param diagramEditPart
	 * @param selection
	 * @param id 
	 */
	protected abstract void connectDiagrams(DiagramEditPart diagramEditPart,  IStructuredSelection selection, String id);

	/**
	 * Creates the committed shapes in the given diagramEditPart.
	 * 
	 * @param modelBuilderAttendant
	 * @param diagramEditPart
	 * @param id 
	 * @param iShapes
	 * @return Map<IShape, EObject> contains the inserted shapes
	 * @throws InterchangeProcessingException
	 */
	protected void insertShapesInDiagram(DiagramEditPart diagramEditPart, String id) throws InterchangeProcessingException {
		CompoundCommand shapesCreationCommand = new CompoundCommand(id);
		final CompoundCommand shapesEditCommand = new CompoundCommand(id);
		
		for (final IShape shape : template.getShapes()) {
			addShapeCreationCommands(shapesCreationCommand, shapesEditCommand, shape);
		} try {
			diagramEditPart.getDiagramEditDomain().getDiagramCommandStack().execute(shapesCreationCommand);
			diagramEditPart.getDiagramEditDomain().getDiagramCommandStack().execute(shapesEditCommand);
		} catch (Exception ex) {
			throw new InterchangeProcessingException("Error on created shapes!", ex); //$NON-NLS-1$
		}
	}
	
	/**
	 * Creates and adds a ShapeCreation-command in shapesCreationCommand and
	 * shapesEditCommand-List.
	 * And creates/adds the EObject reference of a shape to shapeToElementMap
	 * @param shapesCreationCommand
	 * @param shapesEditCommand
	 * @param shapeToElementMap
	 * @param shape
	 */
	private void addShapeCreationCommands(CompoundCommand shapesCreationCommand, final CompoundCommand shapesEditCommand, final IShape shape) {
		IElementType elementType = getElementType((String) shape.getType());
		final CreateViewRequest createRequest = CreateViewRequestFactory.getCreateShapeRequest(elementType, editor.getDiagramEditPart()
				.getDiagramPreferencesHint());

		int dy = location.y;
		int dx = location.x;
		if (template.getAction() != TemplateAction.insert) {
			dx = dx + 250;
		}else {
			dx = dx+100;
		}

		if (isConnector(shape.getType().toString())) {
			dx = (int) (dx - shape.getWidth() * 1.5 + 2);
			dy = (int) (dy - shape.getHeight() *0.5);
			
		}
		createRequest.setLocation(new Point(shape.getX()+dx,shape.getY()+dy));
		
		createRequest.setSize(new Dimension(shape.getWidth(), shape.getHeight()));		
		CompoundCommand command = (CompoundCommand) editor.getDiagramEditPart().getCommand(createRequest);
		shapesCreationCommand.add(command);
		Command createShapeNameCommand = new Command() {
			
			@Override
			public void execute() {
				ViewAndElementDescriptor desc = (ViewAndElementDescriptor) createRequest.getViewDescriptors().get(0);
				CreateElementRequestAdapter adapter = (CreateElementRequestAdapter) desc.getElementAdapter();
				EObject element = adapter.resolve();
				if (element == null) {
					return;
				}
				shapeToElementMap.put(shape,element);

				EStructuralFeature structuralFeature = template.getModelBuilderAttendant().getEStructuralFeatureFor(null, "name"); //$NON-NLS-1$
				String shapeName = shape.getName();
				shapeName = replaceNamingVariables(shapeName);
				SetRequest setRequest = new SetRequest(element, structuralFeature, shapeName);
				SetValueCommand svc = new SetValueCommand(setRequest);
				shapesEditCommand.add(new ICommandProxy(svc));
			}
		};
		shapesCreationCommand.add(createShapeNameCommand);
	}
	
	private boolean isConnector(String type) {
		return type.equals("org.bflow.toolbox.epc.XOR") || type.equals("org.bflow.toolbox.epc.AND") || type.equals("org.bflow.toolbox.epc.OR");
	}

	/**
	 * Returns the EObject of the newly created edit part.
	 * @param request create view request
	 * @return EObject
	 */
	public static EObject resolveCreatedEObject(CreateViewRequest request) {
		ViewAndElementDescriptor desc = (ViewAndElementDescriptor) request.getViewDescriptors().get(0);
		CreateElementRequestAdapter adapter = (CreateElementRequestAdapter) desc.getElementAdapter();
		return adapter.resolve();
	}
	
	private String replaceNamingVariables(String shapeName) {
		if (shapeName.indexOf(" $")== -1 && !shapeName.startsWith("$")) { //$NON-NLS-1$ //$NON-NLS-2$
			return shapeName; //weil dann keine VAriablen enthalten
		}
		
		
		Collection<NamingVariable> ids = template.getNamingVariables();
		for ( NamingVariable nvar : ids) {
			String namingvarid = nvar.getId();
			String value = nvar.getValue();
			if (shapeName.contains(namingvarid) && !value.isEmpty()) {
					shapeName = shapeName + " "; //$NON-NLS-1$
					shapeName = shapeName.replaceAll(Pattern.quote(namingvarid+" "), value+ " "); //$NON-NLS-1$ //$NON-NLS-2$
					shapeName = shapeName.substring(0, shapeName.length()-1);
			}
		}
		
		return shapeName;
	}
	
	protected CreateViewRequest createConnector(DiagramEditPart diagramEP, ConnectorType connector, String id) {
		
			final CreateViewRequest createRequest;
			IElementType elementType = connector.getIElementType();
			createRequest = CreateViewRequestFactory.getCreateShapeRequest(elementType, editor.getDiagramEditPart().getDiagramPreferencesHint());

			createRequest.setLocation(new Point(location.x + 150, location.y));
			CompoundCommand command = (CompoundCommand) editor.getDiagramEditPart().getCommand(createRequest);
			command.setLabel(id);
			diagramEP.getDiagramEditDomain().getDiagramCommandStack().execute(command);
			return createRequest;
	}
	
	/**
	 * Creates the edges of modelData in the committed diagramEditPart.
	 * @param diagramEP - the relevant editpart
	 * @param id 
	 * @param shapeToElementMap must contains the connecting shapes of template and epk side 
	 * @throws InterchangeProcessingException
	 */
	protected void insertEdgesToDiagram(DiagramEditPart diagramEP, String id) throws InterchangeProcessingException {
		CompoundCommand edgesCreationCommand = new CompoundCommand(id);

		// Creating the edges
		for (final IEdge edge : template.getEdges()) {
			IShape srcShape = edge.getSource();
			IShape tgtShape = edge.getTarget();

			// Code copied from EpcElementTypes
			IElementType elementType = getElementType((String) edge.getType());
			EObject srcElement = shapeToElementMap.get(srcShape);
			EObject tgtElement = shapeToElementMap.get(tgtShape);

			EditPart src = findEditPart(diagramEP, srcElement);
			EditPart trg = findEditPart(diagramEP, tgtElement);
			
			final CreateConnectionViewRequest request = CreateViewRequestFactory.getCreateConnectionRequest(elementType, diagramEP.getDiagramPreferencesHint());
			DeferredCreateConnectionViewAndElementCommand createCommand = new DeferredCreateConnectionViewAndElementCommand(
					(CreateConnectionViewAndElementRequest) request, src, trg, diagramEP.getViewer());
			request.setSourceEditPart(src);
			request.setTargetEditPart(trg);
			
			Command edgeMappingCommand = new Command() {

				@Override
				public void execute() {
					ConnectionViewDescriptor descriptor = request.getConnectionViewDescriptor();
					EObject element = (EObject) descriptor.getElementAdapter().getAdapter(EObject.class);

					edgeToElementMap.put(edge, element);
				}
			};
			edgesCreationCommand.add(new ICommandProxy(createCommand));
			edgesCreationCommand.add(edgeMappingCommand);
		} try {
			diagramEP.getDiagramEditDomain().getDiagramCommandStack().execute(edgesCreationCommand);
		} catch (Exception ex) {
			throw new InterchangeProcessingException("Error on creating edges!", ex); //$NON-NLS-1$
		}
	}
	
	protected Command getDeleteCommand(DiagramEditPart diagramEditPart, IGraphicalEditPart ep) {
		Command command;
		DestroyElementRequest deleteRequest = new DestroyElementRequest(diagramEditPart.getEditingDomain(),ep.resolveSemanticElement() ,false);
		command = ep.getCommand(new EditCommandRequestWrapper(deleteRequest,Collections.EMPTY_MAP));
		return command;
	}
	
	/**
	 * Creates a new create command.
	 * @param diagramEP - the relevant editpart
	 * @param elementType - type of edge
	 * @param src - source of edge
	 * @param trg - target of edge
	 * @return ICommandProxy
	 */
	public ICommandProxy getEdgeCreateCommand(DiagramEditPart diagramEP, IElementType elementType, EditPart src, EditPart trg, String id) {
		CreateConnectionViewRequest request = CreateViewRequestFactory.getCreateConnectionRequest(elementType, diagramEP.getDiagramPreferencesHint());
		DeferredCreateConnectionViewAndElementCommand createCommand = new CreateEdgeCommand(
				(CreateConnectionViewAndElementRequest) request, src, trg, diagramEP.getViewer());
		request.setSourceEditPart(src);
		request.setTargetEditPart(trg);
		createCommand.setLabel(id);
		return new ICommandProxy(createCommand);
	}


	/**
	 * Returns the IElementType of the given typeid-String or null
	 * 
	 * @param typeId
	 * @return IElementType
	 */
	private IElementType getElementType(String typeId) {
		switch (typeId) {
		case "org.bflow.toolbox.epc.Arc": //$NON-NLS-1$
			typeId = "org.bflow.toolbox.epc.diagram.Arc_4001"; //$NON-NLS-1$
			break;
		case "org.bflow.toolbox.epc.Relation": //$NON-NLS-1$
			typeId = "org.bflow.toolbox.epc.diagram.Relation_4002"; //$NON-NLS-1$
			break;
		case "org.bflow.toolbox.epc.Function": //$NON-NLS-1$
			typeId = "org.bflow.toolbox.epc.diagram.Function_2007"; //$NON-NLS-1$
			break;
		case "org.bflow.toolbox.epc.Event": //$NON-NLS-1$
			typeId = "org.bflow.toolbox.epc.diagram.Event_2006"; //$NON-NLS-1$
			break;
		case "org.bflow.toolbox.epc.Participant": //$NON-NLS-1$
			typeId = "org.bflow.toolbox.epc.diagram.Participant_2002"; //$NON-NLS-1$
			break;
		case "org.bflow.toolbox.epc.XOR": //$NON-NLS-1$
			typeId = "org.bflow.toolbox.epc.diagram.XOR_2008"; //$NON-NLS-1$
			break;
		case "org.bflow.toolbox.epc.AND": //$NON-NLS-1$
			typeId = "org.bflow.toolbox.epc.diagram.AND_2003"; //$NON-NLS-1$
			break;
		case "org.bflow.toolbox.epc.OR": //$NON-NLS-1$
			typeId = "org.bflow.toolbox.epc.diagram.OR_2001"; //$NON-NLS-1$
			break;
		case "org.bflow.toolbox.epc.Product": //$NON-NLS-1$
			typeId = "org.bflow.toolbox.epc.diagram.Product_2021"; //$NON-NLS-1$
			break;
		case "org.bflow.toolbox.epc.Objective": //$NON-NLS-1$
			typeId = "org.bflow.toolbox.epc.diagram.Objective_2020"; //$NON-NLS-1$
			break;
		case "org.bflow.toolbox.epc.File": //$NON-NLS-1$
			typeId = "org.bflow.toolbox.epc.diagram.File_2019"; //$NON-NLS-1$
			break;
		case "org.bflow.toolbox.epc.Document": //$NON-NLS-1$
			typeId = "org.bflow.toolbox.epc.diagram.Document_2018"; //$NON-NLS-1$
			break;
		case "org.bflow.toolbox.epc.CardFile": //$NON-NLS-1$
			typeId = "org.bflow.toolbox.epc.diagram.CardFile_2017"; //$NON-NLS-1$
			break;
		case "org.bflow.toolbox.epc.TechnicalTerm": //$NON-NLS-1$
			typeId = "org.bflow.toolbox.epc.diagram.TechnicalTerm_2016"; //$NON-NLS-1$
			break;
		case "org.bflow.toolbox.epc.PersonType": //$NON-NLS-1$
			typeId = "org.bflow.toolbox.epc.diagram.PersonType_2015"; //$NON-NLS-1$
			break;
		case "org.bflow.toolbox.epc.Location": //$NON-NLS-1$
			typeId = "org.bflow.toolbox.epc.diagram.Location_2014"; //$NON-NLS-1$
			break;
		case "org.bflow.toolbox.epc.Position": //$NON-NLS-1$
			typeId = "org.bflow.toolbox.epc.diagram.Position_2013"; //$NON-NLS-1$
			break;
		case "org.bflow.toolbox.epc.InternalPerson": //$NON-NLS-1$
			typeId = "org.bflow.toolbox.epc.diagram.InternalPerson_2012"; //$NON-NLS-1$
			break;
		case "org.bflow.toolbox.epc.ExternalPerson": //$NON-NLS-1$
			typeId = "org.bflow.toolbox.epc.diagram.ExternalPerson_2011"; //$NON-NLS-1$
			break;
		case "org.bflow.toolbox.epc.Cluster": //$NON-NLS-1$
			typeId = "org.bflow.toolbox.epc.diagram.Cluster_2010"; //$NON-NLS-1$
			break;
		case "org.bflow.toolbox.epc.Group": //$NON-NLS-1$
			typeId = "org.bflow.toolbox.epc.diagram.Group_2009"; //$NON-NLS-1$
			break;
		case "org.bflow.toolbox.epc.ProcessInterface": //$NON-NLS-1$
			typeId = "org.bflow.toolbox.epc.diagram.ProcessInterface_2005"; //$NON-NLS-1$
			break;
		case "org.bflow.toolbox.epc.Application": //$NON-NLS-1$
			typeId = "org.bflow.toolbox.epc.diagram.Application_2004"; //$NON-NLS-1$
			break;
		case "org.bflow.toolbox.epc.InformationArc": //$NON-NLS-1$
			typeId = "org.bflow.toolbox.epc.diagram.InformationArc_4003"; //$NON-NLS-1$
			break;
		case "org.eclipse.gmf.runtime.notation.Shape+Note": //$NON-NLS-1$
			typeId = "org.eclipse.gmf.runtime.diagram.ui.presentation.note"; //$NON-NLS-1$
			break;
		default:
			break;
		}
		return ElementTypeRegistry.getInstance().getType(typeId);
	}

	/**
	 * Finds the instance of EditPart which is defined by the given semantic element.
	 * @param diagramEditPart
	 * @param element
	 * @return EditPart
	 */
	protected static EditPart findEditPart(DiagramEditPart diagramEditPart, EObject element) {
		EditPart resolvedEditPart = diagramEditPart.findEditPart(diagramEditPart, element);
		if (resolvedEditPart != null)
			return resolvedEditPart;

		// Looking for connections
		for (int i = 0; i < diagramEditPart.getConnections().size(); i++) {
			EditPart editPart = (EditPart) diagramEditPart.getConnections().get(i);
			EObject eObject = (EObject) editPart.getAdapter(EObject.class);
			if (eObject == element)
				return editPart;
		}
		return null;
	}
	
	protected EditPart findIShapeEditPartById(DiagramEditPart diagramEditPart, String firstEndPointId) {
		for (IShape shape : shapeToElementMap.keySet()) {
			if (shape.getId().equals(firstEndPointId)) {
				return findEditPart(diagramEditPart, shapeToElementMap.get(shape));
			}
		}
		return null;
	}
	
	protected void setEditorInFocus() {
		editor.getDiagramGraphicalViewer().deselectAll();
		editor.setFocus();
	}


	protected ArrayList<EditPart> getInsertedEditParts(DiagramEditPart diagramEditPart) {
		ArrayList<EObject> eObjectList = new ArrayList<EObject>();
		eObjectList.addAll(this.shapeToElementMap.values());
		ArrayList<EditPart> editParts = new ArrayList<EditPart>();
		for (EObject eObject : eObjectList) {
			EditPart ep = findEditPart(diagramEditPart, eObject);
			if (ep != null) {
				editParts.add(ep);
			}
		}
		
		return editParts;
	}
}

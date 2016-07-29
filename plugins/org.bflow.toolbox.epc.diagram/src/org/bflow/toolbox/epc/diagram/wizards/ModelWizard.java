package org.bflow.toolbox.epc.diagram.wizards;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.UUID;
import java.util.Vector;

import org.bflow.toolbox.epc.diagram.edit.parts.ArcEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.EpcEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.EventEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.FunctionEditPart;
import org.bflow.toolbox.epc.diagram.modelwizard.pages.ElementGeneratorWizardPage;
import org.bflow.toolbox.epc.diagram.modelwizard.utils.Connector;
import org.bflow.toolbox.epc.diagram.modelwizard.utils.Constants;
import org.bflow.toolbox.epc.diagram.modelwizard.utils.Element;
import org.bflow.toolbox.epc.diagram.modelwizard.utils.ProcessStep;
import org.bflow.toolbox.epc.diagram.modelwizard.utils.Connector.ConnectorType;
import org.bflow.toolbox.epc.diagram.part.EpcDiagramEditor;
import org.bflow.toolbox.epc.diagram.part.EpcDiagramEditorPlugin;
import org.bflow.toolbox.epc.diagram.providers.EpcElementTypes;
import org.bflow.toolbox.epc.extensions.actions.DiagramLiveValidator;
import org.bflow.toolbox.epc.extensions.utils.EpcDiagramEditUtil;
import org.bflow.toolbox.extensions.edit.parts.BflowDiagramEditPart;
import org.bflow.toolbox.extensions.edit.parts.BflowNodeEditPart;
import org.bflow.toolbox.extensions.edit.parts.ColoredNodeEditPart;
import org.bflow.toolbox.hive.interchange.mif.core.IModelBuilderAttendant;
import org.bflow.toolbox.hive.interchange.mif.core.ModelBuilderAttendantRegistry;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.commands.operations.DefaultOperationHistory;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.ResourceUndoContext;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest.ViewAndElementDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequestFactory;
import org.eclipse.gmf.runtime.emf.commands.core.command.EditingDomainUndoContext;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.graphics.Point;

/**
 * Defines a wizard to generate and add numerous elements to the diagram.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 24/11/09
 * @version 22/08/13
 * 
 */
public class ModelWizard extends Wizard {
	/**
	 * wizard page
	 */
	private ElementGeneratorWizardPage egwp;

	/**
	 * editor instance
	 */
	private EpcDiagramEditor editor;

	/**
	 * mouse location before the editor was called
	 */
	private Point location;

	/**
	 * selection within the editor
	 */
	private IStructuredSelection selection;

	/**
	 * anchor for element generation
	 */
	private BflowNodeEditPart anchor = null;

	private ColoredNodeEditPart connectorTop = null;
	@SuppressWarnings("unused")
	private ColoredNodeEditPart connectorBottom = null;

	/**
	 * last drawn connector
	 */
	private Connector lastDrawnConnector = null;

	/**
	 * last drawn ColoredNodeEditParts
	 */
	private Stack<ColoredNodeEditPart> lastDrawnCNEditParts = new Stack<ColoredNodeEditPart>();

	/**
	 * last drawn BflowNodeEditParts
	 */
	private Stack<ColoredNodeEditPart> lastDrawnEditParts = new Stack<ColoredNodeEditPart>();
	
	/**
	 * additional BflowNodeEditParts like File, OU, Document ...
	 */
	private ArrayList<ColoredNodeEditPart> additionalEditParts = new ArrayList<ColoredNodeEditPart>();

	/**
	 * array of edit parts for layouting
	 */
	private Object editParts[] = new Object[10];

	/**
	 * holds the connections to create
	 */
	private Vector<Connection> connectionStack = new Vector<Connection>();

	/**
	 * flag that indicates if the live validation is enabled
	 */
	private boolean validationEnabled;
	
	/**
	 * the unique id of that insert operation (for redo/undo)
	 */
	private String id;

	/**
	 * Default constructor.
	 * 
	 * @param editor
	 *            editor instance
	 */
	public ModelWizard(EpcDiagramEditor editor) {
		this.setWindowTitle("Model processing: model wizard");
		this.editor = editor;
		this.location = editor.getMouseLocation();
		this.selection = (IStructuredSelection) editor.getSite()
				.getSelectionProvider().getSelection();

		if (selection.getFirstElement().getClass() != EpcEditPart.class)
			anchor = (BflowNodeEditPart) selection.getFirstElement();

		this.egwp = new ElementGeneratorWizardPage((BflowNodeEditPart) anchor);

		this.addPage(egwp);
	}

	@Override
	public boolean performFinish() {

		/*
		 * Deactivate validation
		 */
		DiagramLiveValidator validator = EpcDiagramEditorPlugin.getInstance()
				.getDiagramLiveValidator();
		validationEnabled = validator.isEnabled();
		validator.setEnabled(false);

		try {
			//id for identify all commands of this insertion
			id = UUID.randomUUID().toString();
			//add an command for undo/redo all insertion commands with one
			DefaultOperationHistory history=(DefaultOperationHistory) OperationHistoryFactory.getOperationHistory();
			//Erhöhen des Undo-Operation-Buffers, da der Modelwizard für jedes Event/ Function genau 2 Operationen
			//in der History registriert und das Default_Limit der History liegt bei 20 -> größere Modelle können
			//nicht mehr vollständig rückgängig gemacht werden.
			//2000 reicht für 100 vollausgefüllte Zeilen (mit maximaler Elementanzahl von 10 pro Zeile)  
			TransactionalEditingDomain editingDomain = editor.getEditingDomain();
			history.setLimit(new EditingDomainUndoContext(editingDomain), 2000);
			Resource res = editingDomain.getResourceSet().getResources().get(0);
			history.setLimit(new ResourceUndoContext(editingDomain, res), 2000);
			history.setLimit(IOperationHistory.GLOBAL_UNDO_CONTEXT, 2000);
			
			
			/*
			 * generating elements
			 */
			createElements();
			createConnections();
			
			
					
			AbstractOperation aoend = EpcDiagramEditUtil.getCollectedUndoRedoCommand(id, "Model Wizard");
			aoend.addContext(IOperationHistory.GLOBAL_UNDO_CONTEXT);
			history.add(aoend);
			/*
			 * do update
			 */
			EpcDiagramEditUtil.updateDiagram(editor, true);

			/*
			 * flush command stack
			 */
			EpcDiagramEditUtil.flushCommandCollection();

			/*
			 * layout update
			 */
			BflowDiagramEditPart bfEditPart = (BflowDiagramEditPart) editor
					.getDiagramEditPart();
			bfEditPart.getDiagramFormLayer().getFormHelper().pack();

			/*
			 * check bendpoints
			 */
			checkBendpoints();
			
			//Pre-selection
			//Add all new nodes
			ArrayList<ColoredNodeEditPart> insertedNodes = new ArrayList<ColoredNodeEditPart>();
			insertedNodes.addAll(lastDrawnCNEditParts);
			insertedNodes.addAll(lastDrawnEditParts);
			insertedNodes.addAll(additionalEditParts);
			// add all new connections
			ArrayList<EditPart> insertedEdges = new ArrayList<EditPart>();
			for (ColoredNodeEditPart editPart : insertedNodes) {
				List<EditPart> targetConnections = editPart.getTargetConnections();
				insertedEdges.addAll(targetConnections);
			}
			ArrayList<EditPart> allEditParts = new ArrayList<EditPart>();
			allEditParts.addAll(insertedNodes);
			allEditParts.addAll(insertedEdges);
						
			StructuredSelection newSelection = new StructuredSelection(allEditParts);
			
			editor.getSite().getSelectionProvider().setSelection(newSelection);

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;

		} finally {
			/*
			 * activate (if it was before) and run validation
			 */
			editor.getDiagramEditPart().refresh();

			validator.setEnabled(validationEnabled);
			validator.runValidation();
		}

		return true;
	}

	private void createElements() {
		int y = location.y;
		int x = location.x;

		if (anchor != null)
			x += 150;

		boolean split = false; // connector split
		ColoredNodeEditPart splitPart = null; // 

		for (int k = 0; k < egwp.getProcessSteps().size(); k++) {

			ProcessStep processStep = egwp.getProcessSteps().get(k);

			if (processStep.isReducable())
				continue;

			boolean newConnector = false;

			/*
			 * create split
			 */
			Connector connector = processStep.getConnector();

			if (connector != lastDrawnConnector)
				newConnector = true;

			if (connector.getConnectorType() != ConnectorType.NONE
					&& newConnector) {
				split = true;
				createConnector(connector.getConnectorType(), new Point(x, y), false);
				splitPart = lastDrawnCNEditParts.peek();

				y += Constants.DRAW_CONNECTOR_Y_ADDITION;
				lastDrawnConnector = connector;

				if (!lastDrawnEditParts.empty())
					connectionStack.add(new Connection(lastDrawnEditParts.peek(),
							lastDrawnCNEditParts.peek()));
			}

			/*
			 * maybe single connector?
			 */
			if (connector.getConnectorType() == ConnectorType.AND_SINGLE
					|| connector.getConnectorType() == ConnectorType.OR_SINGLE
					|| connector.getConnectorType() == ConnectorType.XOR_SINGLE) {
				split = false;
				y -= Constants.DRAW_CONNECTOR_Y_ADDITION / 2;
			}

			/*
			 * create process steps
			 */
			CompoundCommand shapesNamingCommand = new CompoundCommand(id);
			// int align = processStep.getSpreading();
			for (int i = 0; i < processStep.size(); i++) {
				final Element el = processStep.get(i);

				if (!el.isReducable()) {
					CreateViewRequest createRequest;


					DiagramEditPart diagramEditPart = editor.getDiagramEditPart();
					if (el.getKind() == Element.Kind.Function) {
						createRequest = CreateViewRequestFactory.getCreateShapeRequest(EpcElementTypes.Function_2007, diagramEditPart
								.getDiagramPreferencesHint());
					} else if (el.getKind() == Element.Kind.AND_Single) {
						createRequest = CreateViewRequestFactory.getCreateShapeRequest(EpcElementTypes.AND_2003, diagramEditPart
								.getDiagramPreferencesHint());
					} else if (el.getKind() == Element.Kind.OR_Single) {
						createRequest = CreateViewRequestFactory.getCreateShapeRequest(EpcElementTypes.OR_2001, diagramEditPart
								.getDiagramPreferencesHint());
					} else if (el.getKind() == Element.Kind.XOR_Single) {
						createRequest = CreateViewRequestFactory.getCreateShapeRequest(EpcElementTypes.XOR_2008, diagramEditPart
								.getDiagramPreferencesHint());
					}else {
						createRequest = CreateViewRequestFactory.getCreateShapeRequest(EpcElementTypes.Event_2006, diagramEditPart
								.getDiagramPreferencesHint());
					}

					int dX = x;
					// int middle = Math.round((float)(align/2));

					dX += i * Constants.DRAW_ELEMENT_X_ADDITION;

					createRequest
							.setLocation(new org.eclipse.draw2d.geometry.Point(
									dX, y));

					CompoundCommand command = (CompoundCommand) diagramEditPart.getCommand(createRequest);
					
					//internal ID for redo/ undo functionallity
					command.setLabel(id);
					
					editor.getDiagramEditDomain().getDiagramCommandStack()
							.execute(command);

					List<?> listChildren = diagramEditPart.getChildren();
					
					final ColoredNodeEditPart editPart = (ColoredNodeEditPart) listChildren.get(listChildren.size() - 1);
					
					String shapeName = el.getName();
					
					//Extrahiere eventuell vorhande Bedingungen für dieses Shape
					AdditionalFunctionConditions additionalFunctionConditions = null;
					if(el.getKind() == Element.Kind.Function && shapeName.matches(".*[//].*[=].*")){
						//nur für Funktionen
						additionalFunctionConditions = new AdditionalFunctionConditions(shapeName.substring(shapeName.indexOf("//")));
						shapeName = shapeName.substring(0,shapeName.indexOf("//"));	 
					}
					
					if (!el.getKind().isSingleConnector()) {
						SetValueCommand svc = createSetValueCommandForShapeNaming(createRequest, shapeName);
						shapesNamingCommand.add(new ICommandProxy(svc));
					}
					
					//INSERT Additional NODES TO FUNCTIONS IF THERE IS A DECLARATION IN SHAPENAME
					if (additionalFunctionConditions != null) {
						
						int locationIncrement = 0; 
						for (AdditionalFunctionCondition condition : additionalFunctionConditions) {
							createRequest = CreateViewRequestFactory.getCreateShapeRequest(condition.getShapeType(),
									diagramEditPart.getDiagramPreferencesHint());
							
							if (condition.isIncoming()) {
								createRequest.setLocation(new org.eclipse.draw2d.geometry.Point(dX+150+locationIncrement, y-locationIncrement));
							}else {
								createRequest.setLocation(new org.eclipse.draw2d.geometry.Point(dX-150-locationIncrement, y-locationIncrement));
							}
							locationIncrement =locationIncrement + 8;
							CompoundCommand commandAdditionalShape = (CompoundCommand) diagramEditPart.getCommand(createRequest);
							commandAdditionalShape.setLabel(id);
							editor.getDiagramEditDomain().getDiagramCommandStack().execute(commandAdditionalShape);
							final ColoredNodeEditPart additionalShape = (ColoredNodeEditPart) listChildren.get(listChildren.size() - 1);
							//Set names
							SetValueCommand svc = createSetValueCommandForShapeNaming(createRequest, condition.getShapeName());
							shapesNamingCommand.add(new ICommandProxy(svc));
							//create Connections
							if (!condition.isIncoming()) {
								connectionStack.add(new Connection(additionalShape,editPart, condition.getArcType()));
							}else {
								connectionStack.add(new Connection(editPart,additionalShape, condition.getArcType()));
							}
							additionalEditParts.add(additionalShape);
						}
					}
					
					/*
					 * collect connections
					 */
					if (split || newConnector) {
						if (isFirstConnectorStep(processStep, i)
								&& !lastDrawnCNEditParts.empty())
							if (connector.getConnectorType() == ConnectorType.XOR_IT
									&& i == 1) // xor it
								connectionStack.add(new Connection(editPart,
										lastDrawnCNEditParts.peek()));
							else
								connectionStack.add(new Connection(
										lastDrawnCNEditParts.peek(), editPart));
						else if (editParts[i] != null)
							if (connector.getConnectorType() == ConnectorType.XOR_IT
									&& i == 1) // xor it
								connectionStack.add(new Connection(editPart,
										editParts[i]));
							else
								connectionStack.add(new Connection(
										editParts[i], editPart));
					} else if (editParts[i] != null){
						connectionStack.add(new Connection(editParts[i],editPart));
					}


					editParts[i] = editPart;
					lastDrawnEditParts.push(editPart);

				}
			}
			editor.getDiagramEditDomain().getDiagramCommandStack().execute(shapesNamingCommand);
			y += Constants.DRAW_ELEMENT_Y_ADDITION;

			/*
			 * create join
			 */
			if (split) {
				// last process step of connector?
				if (isLastConnectorStep(processStep)) {
					createConnector(connector.getConnectorType(), new Point(x,y),true);
					y += Constants.DRAW_CONNECTOR_Y_ADDITION;

					boolean none = true;

					for (int i = 0; i < 10; i++)
						if (editParts[i] != null) {
							if (connector.getConnectorType() == ConnectorType.XOR_IT
									&& i == 1) // xor it
								connectionStack.add(new Connection(
										lastDrawnCNEditParts.peek(), editParts[i]));
							else
								connectionStack.add(new Connection(
										editParts[i], lastDrawnCNEditParts.peek()));

							if (i == 1)
								none = false;
						}

					if (none)
						if (connector.getConnectorType() == ConnectorType.XOR_IT)
							connectionStack.add(new Connection(
									lastDrawnCNEditParts.peek(), splitPart));
						else if (connector.getConnectorType() != ConnectorType.AND_DOUBLE
								&& connector.getConnectorType() != ConnectorType.AND_N)
							connectionStack.add(new Connection(splitPart,
									lastDrawnCNEditParts.peek()));

					split = false;
					editParts = new Object[10];
					lastDrawnConnector = processStep.getConnector();
				}
			}

		}

		/*
		 * anchor input
		 */
		if (anchor != null) {

			boolean quickFix = false;

			// dirty quick fix
			if (connectionStack.size() == 0) {// only one element created
				connectionStack.add(new Connection(anchor,
						(lastDrawnEditParts.empty() ? lastDrawnCNEditParts.peek()
								: lastDrawnEditParts.peek())));
				quickFix = true;
			}

			/*
			 * has anchor ouput?
			 */
			if (countArcConnections(anchor.getSourceConnections()) > 0) {

				List cons = anchor.getSourceConnections();
				for (Object con : cons) {
					if (con instanceof ArcEditPart) {
						ArcEditPart arc = (ArcEditPart) con;
						connectionStack.add(new Connection(connectionStack.lastElement().getTarget(), arc.getTarget()));
						deleteConnection(arc);
						break;
					}
				}

				if (!quickFix){
					Object target = getFirstDrawnControlFlowEP();
					if (target != null) {
						connectionStack.add(new Connection(anchor, getFirstDrawnControlFlowEP()));
					}
				}
				
			}

			if (connectionStack.size() != 0 && countArcConnections(anchor.getSourceConnections()) == 0 && !quickFix) {
				Object target = getFirstDrawnControlFlowEP();
				if (target != null) {
					connectionStack.add(new Connection(anchor, getFirstDrawnControlFlowEP()));
				}
			}
		}
	}
	
	/**
	 * Counts the ArcEditParts-Connections in the given list
	 * @param list with connections for a GraphicalEditPart
	 * @return number of arc editparts
	 */
	private int countArcConnections(List list){
		int count = 0;
		for (Object con : list) {
			if (con instanceof ArcEditPart) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Returns the first drawn controlflow-node editpart
	 * 
	 * @return EventEditPart, FunctionEditPart or null 
	 */
	private Object getFirstDrawnControlFlowEP(){
		for (Connection con : connectionStack) {
			Object ep = con.getSource();
			if (ep instanceof FunctionEditPart || ep instanceof EventEditPart) {
				return con.getSource();
			}
		}
		return null;
	}

	private SetValueCommand createSetValueCommandForShapeNaming(
			CreateViewRequest createRequest, String shapeName) {
		ViewAndElementDescriptor desc = (ViewAndElementDescriptor) createRequest.getViewDescriptors().get(0);
		CreateElementRequestAdapter adapter = (CreateElementRequestAdapter) desc.getElementAdapter();
		EObject element = adapter.resolve();
		
		IModelBuilderAttendant att = ModelBuilderAttendantRegistry.getModelBuilderFor("epc");
		EStructuralFeature structuralFeature = att.getEStructuralFeatureFor(null, "name");
		
		SetRequest setRequest = new SetRequest(element, structuralFeature, shapeName);
		SetValueCommand svc = new SetValueCommand(setRequest);
		return svc;
	}

	/**
	 * Creates the connections.
	 */
	private void createConnections() {
		Vector<Connection> alreadyConnectedStack = new Vector<Connection>();

		try {
			for (Connection c : connectionStack)
				if (!isDuplicated(alreadyConnectedStack, c)) {
					c.create();
					alreadyConnectedStack.add(c);
				}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Returns true if the connection is already on the stack.
	 * 
	 * @param stack
	 *            stack to proof
	 * @param conn
	 *            connection
	 * @return true or false
	 */
	private boolean isDuplicated(Vector<Connection> stack, Connection conn) {
		for (Connection c : stack)
			if (c.getTarget().equals(conn.getTarget()))
				if (c.getSource().equals(conn.getSource()))
					return true;

		return false;
	}

	/**
	 * Creates a single orientated connection between two edit parts.
	 * 
	 * @param source
	 *            source edit part
	 * @param target
	 *            target edit part
	 * @param arcType 
	 */
	private void createConnection(EditPart source, EditPart target, IElementType arcType) {
		if (target == null)
			return;

		/*
		 * CreateConnectionViewRequest r = CreateViewRequestFactory
		 * .getCreateConnectionRequest(EpcElementTypes.Arc_4001, editor
		 * .getDiagramEditPart().getDiagramPreferencesHint());
		 */

		// CreateConnectionViewRequest.getCreateCommand(r, source, target)
		// .execute();
		EpcDiagramEditUtil.createConnection(editor, source, target, arcType, id);
	}

	/**
	 * 
	 * @param editPart
	 */
	private void deleteConnection(final ArcEditPart editPart) {

		/*
		 * editor.getDiagramEditDomain().getDiagramCommandStack().execute( new
		 * Command() {
		 * 
		 * @Override public void execute() { super.execute();
		 * 
		 * Arc arc = (Arc) editPart.resolveSemanticElement();
		 * 
		 * // kritisch!!! arc.getTo().getIn().clear(); } });
		 * 
		 * editor.getDiagramEditDomain().getDiagramCommandStack().execute( new
		 * Command() {
		 * 
		 * @Override public void execute() { super.execute();
		 * 
		 * Arc arc = (Arc) editPart.resolveSemanticElement();
		 * 
		 * arc.setFrom(null); arc.setTo(null);
		 * 
		 * }
		 * 
		 * });
		 * 
		 * DeleteCommand cd = new DeleteCommand(editor.getEditingDomain(),
		 * editPart.getPrimaryView()); try { cd.execute(null, null); } catch
		 * (ExecutionException e) { e.printStackTrace(); }
		 */

		EpcDiagramEditUtil.deleteConnection(editor, editPart);
	}

	private void checkBendpoints() {
	}

	private void createConnector(ConnectorType type, Point location, boolean isJoin) {
		if (type == null)
			return;
		
		CreateViewRequest createRequest;
		IElementType elType = type.getEMFIElementType();

		createRequest = CreateViewRequestFactory.getCreateShapeRequest(elType,
				editor.getDiagramEditPart().getDiagramPreferencesHint());

		createRequest.setLocation(new org.eclipse.draw2d.geometry.Point(
				location.x, location.y));

		CompoundCommand command = (CompoundCommand) editor.getDiagramEditPart()
				.getCommand(createRequest);
		command.setLabel(id);
		editor.getDiagramEditDomain().getDiagramCommandStack().execute(command);

		List<?> listChildren = editor.getDiagramEditPart().getChildren();

		ColoredNodeEditPart editPart = (ColoredNodeEditPart) listChildren
				.get(listChildren.size() - 1);

		lastDrawnCNEditParts.push(editPart);
		if (isJoin) {
			lastDrawnEditParts.push(editPart);
		}
		
		

		if (connectorTop == null)
			connectorTop = editPart;
		else
			connectorBottom = editPart;

		// this.location = new Point(location.x, location.y+80);
	}

	/**
	 * Returns true if the step is the first one of a new connector.
	 * 
	 * @param step
	 *            step to check
	 * @return true or false
	 */
	private boolean isFirstConnectorStep(ProcessStep step, int i) {
		int pos = egwp.getProcessSteps().indexOf(step);

		if (pos == 0 && !lastDrawnCNEditParts.empty())
			return true;

		if (pos > 0) {
			if (egwp.getProcessSteps().get(pos - 1).getConnector() != step
					.getConnector())
				return true;

			if (egwp.getProcessSteps().get(pos - 1).get(i).getName().isEmpty()) // kritisch
				return true;
		}

		return false;
	}

	/**
	 * Returns true if the step is the last one of a connector.
	 * 
	 * @param step
	 *            step to check
	 * @return true or false
	 */
	private boolean isLastConnectorStep(ProcessStep step) {
		int pos = egwp.getProcessSteps().indexOf(step);

		if (pos != egwp.getProcessSteps().size() - 1)
			if (egwp.getProcessSteps().get(pos + 1).getConnector() != step
					.getConnector())
				return true;

		return false;
	}

	/**
	 * Inner class to hold up and define orientated connections between two
	 * elements.
	 * 
	 * @author Arian Storch
	 * @since 08/02/10
	 * @version 12/07/10
	 * 
	 */
	private class Connection {
		private Object source;
		private Object target;
		private IElementType arcType;

		/**
		 * Default constructor.
		 * 
		 * @param source
		 *            source EditPart
		 * @param target
		 *            target EditPart
		 */
		public Connection(Object source, Object target) {
			this.source = source;
			this.target = target;
			this.arcType = EpcElementTypes.Arc_4001;
		}

		public Connection(Object source, Object target, IElementType arcType) {
			this.source = source;
			this.target = target;
			this.arcType = arcType;
		}

		/**
		 * Creates the defined connection.
		 */
		public void create() {
			createConnection((EditPart) source, (EditPart) target, arcType);
		}

//		public void delete() {
//			BflowNodeEditPart src = (BflowNodeEditPart) source;
//			for (Object o : src.getSourceConnections()) {
//				ArcEditPart arc = (ArcEditPart) o;
//				if (arc.getTarget() == target)
//					deleteConnection(arc);
//			}
//		}

		/**
		 * Returns the source object of the connection.
		 * 
		 * @return source object
		 */
		public Object getSource() {
			return source;
		}

		/**
		 * Returns the target object of the connection.
		 * 
		 * @return target object
		 */
		public Object getTarget() {
			return target;
		}
	}

}

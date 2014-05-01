package org.bflow.toolbox.epc.diagram.wizards;

import java.util.List;
import java.util.Vector;

import org.bflow.toolbox.epc.diagram.edit.parts.ArcEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.EpcEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.EventNameEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.FunctionNameEditPart;
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
import org.bflow.toolbox.epc.impl.EventImpl;
import org.bflow.toolbox.epc.impl.FunctionImpl;
import org.bflow.toolbox.extensions.edit.parts.BflowDiagramEditPart;
import org.bflow.toolbox.extensions.edit.parts.BflowNodeEditPart;
import org.bflow.toolbox.extensions.edit.parts.ColoredNodeEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequestFactory;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.viewers.IStructuredSelection;
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
	 * last drawn ColoredNodeEditPart
	 */
	private ColoredNodeEditPart lastDrawnCNEditPart;

	/**
	 * last drawn BflowNodeEditPart
	 */
	private BflowNodeEditPart lastDrawnEditPart;

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

			/*
			 * generating elements
			 */
			createElements();
			createConnections();

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
				createConnector(connector.getConnectorType(), new Point(x, y));
				splitPart = lastDrawnCNEditPart;

				y += Constants.DRAW_CONNECTOR_Y_ADDITION;
				lastDrawnConnector = connector;

				if (lastDrawnEditPart != null)
					connectionStack.add(new Connection(lastDrawnEditPart,
							lastDrawnCNEditPart));
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

			// int align = processStep.getSpreading();
			for (int i = 0; i < processStep.size(); i++) {
				final Element el = processStep.get(i);

				if (!el.isReducable()) {
					CreateViewRequest createRequest;

					boolean createFunction = (el.getKind() == Element.Kind.Function ? true
							: false);

					if (el.getKind() == Element.Kind.Function)
						createRequest = CreateViewRequestFactory
								.getCreateShapeRequest(
										EpcElementTypes.Function_2007, editor
												.getDiagramEditPart()
												.getDiagramPreferencesHint());
					else
						createRequest = CreateViewRequestFactory
								.getCreateShapeRequest(
										EpcElementTypes.Event_2006, editor
												.getDiagramEditPart()
												.getDiagramPreferencesHint());

					int dX = x;
					// int middle = Math.round((float)(align/2));

					dX += i * Constants.DRAW_ELEMENT_X_ADDITION;

					createRequest
							.setLocation(new org.eclipse.draw2d.geometry.Point(
									dX, y));

					CompoundCommand command = (CompoundCommand) editor
							.getDiagramEditPart().getCommand(createRequest);

					editor.getDiagramEditDomain().getDiagramCommandStack()
							.execute(command);

					List<?> listChildren = editor.getDiagramEditPart()
							.getChildren();

					final BflowNodeEditPart editPart = (BflowNodeEditPart) listChildren
							.get(listChildren.size() - 1);

					if (createFunction) {
						FunctionNameEditPart txtEditPart = (FunctionNameEditPart) editPart
								.getPrimaryChildEditPart();
						txtEditPart.setLabelText(el.getName());

						editor.getDiagramEditDomain().getDiagramCommandStack()
								.execute(new Command() {
									@Override
									public void execute() {
										super.execute();
										FunctionImpl f = (FunctionImpl) editPart
												.resolveSemanticElement();
										f.setName(el.getName());
									}
								});
					} else {
						EventNameEditPart txtEditPart = (EventNameEditPart) editPart
								.getPrimaryChildEditPart();
						txtEditPart.setLabelText(el.getName());

						editor.getDiagramEditDomain().getDiagramCommandStack()
								.execute(new Command() {
									@Override
									public void execute() {
										super.execute();
										EventImpl e = (EventImpl) editPart
												.resolveSemanticElement();
										e.setName(el.getName());
									}
								});
					}

					/*
					 * collect connections
					 */
					if (split || newConnector) {
						if (isFirstConnectorStep(processStep, i)
								&& lastDrawnCNEditPart != null)
							if (connector.getConnectorType() == ConnectorType.XOR_IT
									&& i == 1) // xor it
								connectionStack.add(new Connection(editPart,
										lastDrawnCNEditPart));
							else
								connectionStack.add(new Connection(
										lastDrawnCNEditPart, editPart));
						else if (editParts[i] != null)
							if (connector.getConnectorType() == ConnectorType.XOR_IT
									&& i == 1) // xor it
								connectionStack.add(new Connection(editPart,
										editParts[i]));
							else
								connectionStack.add(new Connection(
										editParts[i], editPart));
					} else if (editParts[i] != null)
						connectionStack.add(new Connection(editParts[i],
								editPart));

					editParts[i] = editPart;
					lastDrawnEditPart = editPart;

				}
			}
			y += Constants.DRAW_ELEMENT_Y_ADDITION;

			/*
			 * create join
			 */
			if (split) {
				// last process step of connector?
				if (isLastConnectorStep(processStep)) {
					createConnector(connector.getConnectorType(), new Point(x,
							y));
					y += Constants.DRAW_CONNECTOR_Y_ADDITION;

					boolean none = true;

					for (int i = 0; i < 10; i++)
						if (editParts[i] != null) {
							if (connector.getConnectorType() == ConnectorType.XOR_IT
									&& i == 1) // xor it
								connectionStack.add(new Connection(
										lastDrawnCNEditPart, editParts[i]));
							else
								connectionStack.add(new Connection(
										editParts[i], lastDrawnCNEditPart));

							if (i == 1)
								none = false;
						}

					if (none)
						if (connector.getConnectorType() == ConnectorType.XOR_IT)
							connectionStack.add(new Connection(
									lastDrawnCNEditPart, splitPart));
						else if (connector.getConnectorType() != ConnectorType.AND_DOUBLE
								&& connector.getConnectorType() != ConnectorType.AND_N)
							connectionStack.add(new Connection(splitPart,
									lastDrawnCNEditPart));

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
						(lastDrawnEditPart == null ? lastDrawnCNEditPart
								: lastDrawnEditPart)));
				quickFix = true;
			}

			/*
			 * has anchor ouput?
			 */
			if (anchor.getSourceConnections().size() > 0) {

				ArcEditPart arc = (ArcEditPart) anchor.getSourceConnections()
						.get(0);

				connectionStack.add(new Connection(connectionStack
						.lastElement().getTarget(), arc.getTarget()));

				if (!quickFix)
					connectionStack.add(new Connection(anchor, connectionStack
							.get(0).getSource()));

				deleteConnection(arc);
			}

			if (connectionStack.size() != 0
					&& anchor.getSourceConnections().size() == 0 && !quickFix) {
				connectionStack.add(new Connection(anchor, connectionStack.get(
						0).getSource()));
			}
		}
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
	 */
	private void createConnection(EditPart source, EditPart target) {
		if (target == null)
			return;

		/*
		 * CreateConnectionViewRequest r = CreateViewRequestFactory
		 * .getCreateConnectionRequest(EpcElementTypes.Arc_4001, editor
		 * .getDiagramEditPart().getDiagramPreferencesHint());
		 */

		// CreateConnectionViewRequest.getCreateCommand(r, source, target)
		// .execute();
		EpcDiagramEditUtil.createConnection(editor, source, target);
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

	private void createConnector(ConnectorType type, Point location) {
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

		editor.getDiagramEditDomain().getDiagramCommandStack().execute(command);

		List<?> listChildren = editor.getDiagramEditPart().getChildren();

		ColoredNodeEditPart editPart = (ColoredNodeEditPart) listChildren
				.get(listChildren.size() - 1);

		lastDrawnCNEditPart = editPart;

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

		if (pos == 0 && lastDrawnCNEditPart != null)
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
		}

		/**
		 * Creates the defined connection.
		 */
		public void create() {
			createConnection((EditPart) source, (EditPart) target);
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

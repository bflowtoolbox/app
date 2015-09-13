package org.bflow.toolbox.epc.extensions.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.bflow.toolbox.bflow.BflowPackage;
import org.bflow.toolbox.epc.diagram.edit.parts.ArcEditPart;
import org.bflow.toolbox.epc.diagram.part.EpcDiagramEditor;
import org.bflow.toolbox.epc.diagram.part.EpcDiagramEditorPlugin;
import org.bflow.toolbox.epc.diagram.part.EpcDiagramEditorUtil;
import org.bflow.toolbox.epc.diagram.providers.EpcElementTypes;
import org.bflow.toolbox.epc.extensions.idelete.IntelligentDeleter;
import org.bflow.toolbox.epc.extensions.actions.DiagramLiveValidator;
import org.bflow.toolbox.extensions.edit.parts.BflowDiagramEditPart;
import org.bflow.toolbox.extensions.edit.parts.ColoredNodeEditPart;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.commands.operations.DefaultOperationHistory;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequestFactory;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest.ViewAndElementDescriptor;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * Defines an utility to make simple model and views editings within an epc
 * diagram.<br/>
 * You can always undo the last changes until make new ones.
 * 
 * @author Arian Storch
 * @since 16/07/10
 * @version 27/11/10
 * 
 */
public class EpcDiagramEditUtil {

	private static Vector<CommandCollection> commandCollection = new Vector<CommandCollection>();
	private static CommandCollection currentCommandCollection = new CommandCollection();
	
	/**
	 * Creates a new connection between two model elements.
	 * 
	 * @param editor
	 *            editor instance
	 * @param source
	 *            source edit part
	 * @param target
	 *            target edit part
	 */
	public static void createConnection(EpcDiagramEditor editor,
			EditPart source, EditPart target) {
		createConnection(editor, source, target, null);
	}

	/**
	 * Creates a new connection between two model elements.
	 * 
	 * @param editor
	 *            editor instance
	 * @param source
	 *            source edit part
	 * @param target
	 *            target edit part
	 * @param commandLabel
	 *            command label
	 */
	public static void createConnection(EpcDiagramEditor editor,
			EditPart source, EditPart target, String commandLabel) {
		if (target == null || source == null)
			return;

		BflowDiagramEditPart diagramEditPart = (BflowDiagramEditPart) editor
				.getDiagramEditPart();

		CreateRelationshipRequest request = new CreateRelationshipRequest(
				diagramEditPart.getEditingDomain(), diagramEditPart
						.resolveSemanticElement(),
				((ColoredNodeEditPart) source).resolveSemanticElement(),
				((ColoredNodeEditPart) target).resolveSemanticElement(),
				EpcElementTypes.Arc_4001);

		Command command = ((ColoredNodeEditPart) source)
				.getCommand(new EditCommandRequestWrapper(request,
						Collections.EMPTY_MAP));

		if (commandLabel != null) {
			command.setLabel(commandLabel);
		}else {
			command.setLabel("intelligent arc creation");
		}
		command.execute();
		currentCommandCollection.getStack().add(command);
	}

	/**
	 * Deletes a connector.
	 * 
	 * @param editor
	 *            editor instance
	 * @param connector
	 *            connector
	 */
	public static void deleteConnector(EpcDiagramEditor editor,
			ColoredNodeEditPart connector) {

		org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest request = new org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest(
				editor.getDiagramEditPart().getEditingDomain(), connector
						.resolveSemanticElement(), false);

		Command command = connector.getCommand(new EditCommandRequestWrapper(
				request, Collections.EMPTY_MAP));

		command.setLabel("intelligent connector delete");
		command.execute();
		currentCommandCollection.getStack().add(command);
	}

	/**
	 * Deletes direct connections between two connectors.
	 * 
	 * @param editor
	 *            editor instance
	 * @param split
	 *            split connector
	 * @param join
	 *            join connector
	 */
	public static void deleteDirectConnection(EpcDiagramEditor editor,
			ColoredNodeEditPart split, ColoredNodeEditPart join) {
		for (Object o : split.getSourceConnections()) {
			ArcEditPart arcOut = (ArcEditPart) o;

			if (arcOut.getTarget().equals(join)) {
				deleteConnection(editor, arcOut);
				return;
			}
		}
	}

	/**
	 * Deletes a connection.
	 * 
	 * @param editor
	 *            editor instance
	 * @param arc
	 *            arc edit part
	 */
	public static void deleteConnection(EpcDiagramEditor editor, ArcEditPart arc) {

		org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest request = new org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest(
				editor.getDiagramEditPart().getEditingDomain(), arc
						.resolveSemanticElement(), false);

		Command command = arc.getCommand(new EditCommandRequestWrapper(request,
				Collections.EMPTY_MAP));

		command.setLabel("intelligent arc delete");
		command.execute();

		currentCommandCollection.getStack().add(command);
	}

	/**
	 * Creates a new connector defined by the elemType. At the moment only xor
	 * connectors are created.
	 * 
	 * @param editor
	 *            editor
	 * @param elemType
	 *            element tyoe
	 * @param location
	 *            location of the new connector
	 * @return created editpart or null
	 */
	public static ColoredNodeEditPart createConnector(EpcDiagramEditor editor,
			IElementType elemType, Point location) {
		CreateViewRequest request = CreateViewRequestFactory
				.getCreateShapeRequest(elemType, editor.getDiagramEditPart()
						.getDiagramPreferencesHint());
		request.setLocation(location);

		Command command = editor.getDiagramEditPart().getCommand(request);

		command.setLabel("intelligent connector create");
		ColoredNodeEditPart createdEditPart = null;

		command.execute();
		currentCommandCollection.getStack().add(command);

		editor.getDiagramEditPart().refresh();

		int count = editor.getDiagramEditPart().getChildren().size();

		createdEditPart = (ColoredNodeEditPart) editor.getDiagramEditPart()
				.getChildren().get(count - 1);
		return createdEditPart;
	}

	/**
	 * Replaces a connector with a new one.
	 * <p/>
	 * <b>Note:</b><br/>
	 * This is only working and tested for connector replacing.
	 * 
	 * @param editor
	 *            epc diagram editor
	 * @param oldEditPart
	 *            edit part to replace
	 * @param newEditPartClazz
	 *            class of the new edit part
	 */
	public static ColoredNodeEditPart replace(final EpcDiagramEditor editor,
			ColoredNodeEditPart oldEditPart, Class<?> newEditPartClazz,
			boolean delete) {
		Point point = oldEditPart.getLocation().getTranslated(13, 13);

		IElementType elemType = null;

		if (newEditPartClazz.getSimpleName().equalsIgnoreCase("XOREditPart")) {
			elemType = EpcElementTypes.XOR_2008;
		}

		if (newEditPartClazz.getSimpleName().equalsIgnoreCase("ANDEditPart")) {
			elemType = EpcElementTypes.AND_2003;
		}

		if (newEditPartClazz.getSimpleName().equalsIgnoreCase("OREditPart")) {
			elemType = EpcElementTypes.OR_2001;
		}

		if (elemType == null)
			return null;

		ColoredNodeEditPart part = (ColoredNodeEditPart) createConnector(
				editor, elemType, point);

		// ausgehende arcs
		for (Object o : oldEditPart.getSourceConnections()) {
			ArcEditPart arc = (ArcEditPart) o;
			EditPart prt = arc.getTarget();

			ColoredNodeEditPart tgt = (ColoredNodeEditPart) (IntelligentDeleter
					.isReplaced(prt) ? IntelligentDeleter
					.getReplacedEditParts().get(prt) : prt);

			createConnection(editor, part, tgt);
		}

		// eingehende arcs
		for (Object o : oldEditPart.getTargetConnections()) {
			ArcEditPart arc = (ArcEditPart) o;
			EditPart prt = arc.getSource();

			if (prt == null)
				continue;

			ColoredNodeEditPart src = (ColoredNodeEditPart) (IntelligentDeleter
					.isReplaced(prt) ? IntelligentDeleter
					.getReplacedEditParts().get(prt) : prt);

			createConnection(editor, src, part);
		}

		if (delete)
			EpcDiagramEditUtil.deleteConnector(editor, oldEditPart);

		IntelligentDeleter.getReplacedEditParts().put(oldEditPart, part);

		return (ColoredNodeEditPart) part;

	}


	/**
	 * Returns the EObject of the newly created edit part.
	 * 
	 * @param editor
	 *            editor instance
	 * @param request
	 *            create view request
	 * @return EObject
	 */
	public static EObject resolveCreatedEObject(EpcDiagramEditor editor,
			CreateViewRequest request) {
		ViewAndElementDescriptor desc = (ViewAndElementDescriptor) request
				.getViewDescriptors().get(0);
		CreateElementRequestAdapter adapter = (CreateElementRequestAdapter) desc
				.getElementAdapter();
		return adapter.resolve();
	}

	/**
	 * Returns the newly created edit part.
	 * 
	 * @param editor
	 *            editor instance
	 * @param request
	 *            create view request
	 * @return EditPart or null
	 */
	public static EditPart resolveCreatedEditPart(EpcDiagramEditor editor,
			CreateViewRequest request) {
		EObject created = resolveCreatedEObject(editor, request);

		editor.getDiagramEditPart().refresh();

		for (Object o : editor.getDiagramEditPart().getChildren()) {
			ColoredNodeEditPart p = (ColoredNodeEditPart) o;

			if (p.resolveSemanticElement().equals(created))
				return p;
		}

		return null;
	}

	/**
	 * Disposes the current command collection.
	 * <p/>
	 * You don't need this normally.
	 */
	public static void flushCommandCollection() {
		commandCollection.add(currentCommandCollection);
		currentCommandCollection = new CommandCollection();
	}

	/**
	 * Undoes the last model changes.
	 */
	public static void undo() {
		if (commandCollection.size() == 0)
			return;

		int last = commandCollection.size() - 1;

		CommandCollection lastCC = commandCollection.remove(last);
		lastCC.undoAll();
	}

	/**
	 * Invokes an update mechanism that updates the diagram edit part.
	 * 
	 * @param editPart
	 *            edit part
	 * @param setSelected
	 */
	public static void updateDiagram(EpcDiagramEditor editor,
			boolean setSelected) {

		BflowDiagramEditPart editPart = (BflowDiagramEditPart) editor
				.getDiagramEditPart();

		editPart.refresh();

		EObject modelElement = ((View) editPart.getModel()).getElement();
		List<?> editPolicies = CanonicalEditPolicy
				.getRegisteredEditPolicies(modelElement);
		for (Iterator<?> it = editPolicies.iterator(); it.hasNext();) {
			CanonicalEditPolicy nextEditPolicy = (CanonicalEditPolicy) it
					.next();
			nextEditPolicy.refresh();
		}

		if (setSelected)
			EpcDiagramEditorUtil.selectElementsInDiagram(editor,
					new ArrayList<EditPart>());
	}

	/**
	 * Renames an edit part.
	 * 
	 * @param editpart
	 *            edit part to rename
	 * @param name
	 *            new name
	 */
	public static void RenameElement(ColoredNodeEditPart editpart, String name) {
		// look at:
		// http://wiki.eclipse.org/GMF_Tips#Change_Names_Of_Newly_Created_Elements
		SetRequest req = new SetRequest(editpart.getEditingDomain(), editpart
				.resolveSemanticElement(), BflowPackage.eINSTANCE
				.getBflowSymbol_Name(), name);

		SetValueCommand com = new SetValueCommand(req);

		editpart.getDiagramEditDomain().getDiagramCommandStack().execute(
				new ICommandProxy(com));
		
	}

	/**
	 * Sets the properties of the font used by the edit part.<p/>
	 * If fontname is null, the previous font is used.<br/>
	 * If size is -1, the previous size is used.<br/>
	 * If style is -1, the previous style is used. Style parameter uses SWT constants like SWT.NORMAL, SWT.BOLD, SWT.ITALIC
	 * @param editpart edit part to edit
	 * @param fontName system name of the font
	 * @param size size of the font
	 * @param style style; look at SWT styles
	 */
	public static void SetElementFont(ColoredNodeEditPart editpart,
			String fontName, int size, int style) {
		EditPart child = editpart.getPrimaryChildEditPart();

		if (child instanceof ITextAwareEditPart) {

			ITextAwareEditPart g = (ITextAwareEditPart) child;
			
			Display display = PlatformUI.getWorkbench().getDisplay();
			
			if(size == -1)
				size = g.getFigure().getFont().getFontData()[0].getHeight();
			
			if(style == -1)
				style = g.getFigure().getFont().getFontData()[0].getStyle();
			
			if(fontName == null)
				fontName = g.getFigure().getFont().getFontData()[0].getName();
			
			Font f = new Font(display, fontName, size, style);

			g.getFigure().setFont(f);
		}
	}
	
	/**
	 * Sets the location of the element.
	 * @param editpart edit part to set
	 * @param location new location
	 */
	public static void SetElementLocation(ColoredNodeEditPart editpart, Point location) {
		SetBoundsCommand command = new SetBoundsCommand(editpart.getEditingDomain(), "Element relocate", 
				new EObjectAdapter((View)editpart.getModel()), location);
				
		editpart.getDiagramEditDomain().getDiagramCommandStack().execute(
				new ICommandProxy(command));
	}
	
	/**
	 * Moves the element along the x and y values.
	 * @param editpart edit part to move
	 * @param x value to move the element along the abscissae
	 * @param y value to move the element along the ordinate
	 */
	public static void MoveElementLocation(ColoredNodeEditPart editpart, int x, int y) {		
		RoundedRectangle rr = (RoundedRectangle) editpart.getPrimaryFigure();
		
		Point old = rr.getLocation();
		
		Point location = new Point(old.x+x, old.y+y);
		
		EpcDiagramEditUtil.SetElementLocation(editpart, location);
	}
	
	
	/**
	 * Returns an AbstractOperation. This can added in the OperationHistory and does 
	 * redo/undo all commands in the history with the same unique ID in the commandlabel
	 * @param id - the unique id for identify all related commands for redo/undo 
	 * @param label - the name shown in the redo/undo context menu
	 * @return
	 */
	public static AbstractOperation getCollectedUndoRedoCommand(final String id, String label) {
		AbstractOperation aoend = new AbstractOperation(label) {
			
			@Override
			public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				DiagramLiveValidator diagramLiveValidator = EpcDiagramEditorPlugin.getInstance().getDiagramLiveValidator();
				boolean validationStatusBefore = diagramLiveValidator.isEnabled();
				diagramLiveValidator.setEnabled(false);
				DefaultOperationHistory history = (DefaultOperationHistory) OperationHistoryFactory.getOperationHistory();
				IUndoableOperation[] s = history.getUndoHistory(IOperationHistory.GLOBAL_UNDO_CONTEXT);
				for (int i = s.length-1; i >= 0; i--) {
					IUndoableOperation iUndoableOperation = s[i];
					if (iUndoableOperation.getLabel().contains(id)) {
							history.undoOperation(iUndoableOperation, null, null);
					}
				}
				if (validationStatusBefore) {
					diagramLiveValidator.setEnabled(true);
				}
				return Status.OK_STATUS;
			}

			@Override
			public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				DiagramLiveValidator diagramLiveValidator = EpcDiagramEditorPlugin.getInstance().getDiagramLiveValidator();
				boolean validationStatusBefore = diagramLiveValidator.isEnabled();
				diagramLiveValidator.setEnabled(false);
				DefaultOperationHistory history = (DefaultOperationHistory) OperationHistoryFactory.getOperationHistory();
				IUndoableOperation[] s = history.getRedoHistory(IOperationHistory.GLOBAL_UNDO_CONTEXT);
				
				for (int i = s.length-1; i >= 0; i--) {
					IUndoableOperation iUndoableOperation = s[i];
					if (iUndoableOperation.getLabel().contains(id)) {
							history.redoOperation(iUndoableOperation, null, null);
					}
				}
				if (validationStatusBefore) {
					diagramLiveValidator.setEnabled(true);
				}
				return Status.OK_STATUS;
			}

			@Override
			public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				return Status.OK_STATUS;
			}
		};
		return aoend;
	}
}

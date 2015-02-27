package org.bflow.toolbox.hive.actions.commands;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

/**
 * Implements an undoable command to set an automatically calculated size for
 * elements.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 16.08.13
 * @version 27.02.15
 */
public class BestSizeCommand extends Command {

	/** The edit part dim map. */
	private Map<EditPart, Dimension> iEditPartDimMap;
	
	/** The edit part dim history map. */
	private Map<EditPart, Dimension> iEditPartDimHistoryMap;
	
	/** The diagram edit part */
	private DiagramEditPart iDiagramEditPart;
	
	/**
	 * Creates a new instance.
	 * @param map Map which contains the edit part and its dimension
	 */
	public BestSizeCommand(Map<EditPart, Dimension> map, DiagramEditPart diagramEditPart) {
		iEditPartDimMap = map;
		iEditPartDimHistoryMap = new HashMap<EditPart, Dimension>(iEditPartDimMap.size());
		iDiagramEditPart = diagramEditPart;
	}

	/**
	 * Dispose the command.
	 */
	public void dispose() {
		iEditPartDimMap.clear();
		iEditPartDimMap = null;
		
		iEditPartDimHistoryMap.clear();
		iEditPartDimHistoryMap = null;
		
		iDiagramEditPart = null;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		return true;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	public boolean canUndo() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		DiagramEditPart diagramEditPart = iDiagramEditPart;
		if (diagramEditPart == null) {
			IEditorPart editorPart = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			if (editorPart != null) {
				diagramEditPart = (DiagramEditPart) ((IDiagramWorkbenchPart) editorPart)
						.getDiagramEditPart();
			}
		}

		if (diagramEditPart != null) {
			AbstractTransactionalCommand cmd = new AbstractTransactionalCommand(
					diagramEditPart.getEditingDomain(), null, null) {

				@Override
				protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info)
						throws ExecutionException {
					for (Iterator<Entry<EditPart, Dimension>> it = iEditPartDimMap.entrySet().iterator(); it.hasNext(); ) {
						Entry<EditPart,Dimension> keyValuePair = it.next();

						ShapeNodeEditPart shape = (ShapeNodeEditPart) keyValuePair.getKey();
						Dimension dim = keyValuePair.getValue();
						Node node = (Node) shape.getNotationView();
						Bounds bounds = (Bounds) node.getLayoutConstraint();
						
						// Fill history entry
						iEditPartDimHistoryMap.put(shape, new Dimension(bounds.getWidth(), bounds.getHeight()));
						
						// Set new values
						// Note that there are additional settings made by BestSizeAction
						bounds.setWidth(dim.width);
						bounds.setHeight(dim.height);
						
						// Calculate the delta - Alternative approach						
//						Dimension currentDimension = new Dimension(bounds.getWidth(), bounds.getHeight());
//						Dimension deltaDimension = dim.getShrinked(currentDimension);
//						
//						ChangeBoundsRequest cbr = new ChangeBoundsRequest("resize");
//						cbr.setEditParts(shape);
//						cbr.setSizeDelta(deltaDimension);
//						cbr.setCenteredResize(true);
//						cbr.setConstrainedResize(true);
//						shape.getCommand(cbr).execute();
					}
					return CommandResult.newOKCommandResult();
				}

			};

			try {
				cmd.execute(null, null);
				diagramEditPart.refresh();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

	
	/**
	 * Returns an description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return "auto size";
	}

	/**
	 * Returns an label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return "auto size";
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		execute();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		IEditorPart editorPart = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor();

		DiagramEditPart diagramEditPart = null;
		if (editorPart != null) {
			diagramEditPart = (DiagramEditPart) ((IDiagramWorkbenchPart) editorPart)
					.getDiagramEditPart();
		}

		if (diagramEditPart != null) {
			AbstractTransactionalCommand cmd = new AbstractTransactionalCommand(
					diagramEditPart.getEditingDomain(), null, null) {

				@Override
				protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info)
						throws ExecutionException {
					for (Iterator<Entry<EditPart, Dimension>> it = iEditPartDimHistoryMap.entrySet().iterator(); it.hasNext(); ) {
						Entry<EditPart,Dimension> keyValuePair = it.next();

						ShapeNodeEditPart shape = (ShapeNodeEditPart) keyValuePair.getKey();
						Dimension dim = keyValuePair.getValue();
						Node node = (Node) shape.getNotationView();
						Bounds bounds = (Bounds) node.getLayoutConstraint();
						
						// Set new values
						bounds.setWidth(dim.width);
						bounds.setHeight(dim.height);
					}
					
					return CommandResult.newOKCommandResult();
				}

			};

			try {
				cmd.execute(null, null);
				diagramEditPart.refresh();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
}

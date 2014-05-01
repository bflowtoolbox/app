package org.bflow.toolbox.extensions.commands;

import java.util.Iterator;
import java.util.Vector;

import org.bflow.toolbox.extensions.commands.AutoSizeMarker.EditPartMarker;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
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
 * Implements an undoable command to set the same size for elements implement
 * to same type. 
 * 
 * @author Joerg Hartmann
 * @since 0.0.7
 * @version 16/08/13 by Arian Storch<arian.storch@bflow.org>
 */
public class MakeSameSizeCommand extends Command {

	
	/**
	 * Containing all edit parts together with size information.
	 */
	private Vector<AutoSizeMarker> autoSizeMarkers;

	
	/**
	 * Creates the command.
	 * @param autoSizeMarkers
	 */
	public MakeSameSizeCommand(Vector<AutoSizeMarker> autoSizeMarkers) {
		this.autoSizeMarkers = autoSizeMarkers;
	}

	
	/**
	 * Dispose the command.
	 */
	public void dispose() {
		autoSizeMarkers = null;
	}

	/**
	 * True.
	 */
	public boolean canExecute() {
		return true;
	}

	/**
	 * True.
	 */
	public boolean canUndo() {
		return true;
	}

	
	/**
	 * Executes this command.
	 * Sets the element specific maximum size.
	 */
	public void execute() {
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
				protected CommandResult doExecuteWithResult(
						IProgressMonitor monitor, IAdaptable info)
						throws ExecutionException {
					for (Iterator<AutoSizeMarker> iMarkers = autoSizeMarkers
							.iterator(); iMarkers.hasNext();) {
						AutoSizeMarker autoSizeMarker = iMarkers.next();

						Iterator<EditPartMarker> editPartMarkers = autoSizeMarker
								.getMarkers().iterator();
						while (editPartMarkers.hasNext()) {
							EditPartMarker marker = editPartMarkers.next();
							
							ShapeNodeEditPart editPart = marker.getEditPart();
							editPart.getFigure().setSize(
									autoSizeMarker.getMaxSize());
							editPart.getContentPane().setSize(
									autoSizeMarker.getMaxSize());
							if (editPart.getNotationView() instanceof Node) {
								Node node = (Node) editPart.getNotationView();
								if (node.getLayoutConstraint() instanceof Bounds) {
									Bounds bounds = (Bounds) node
											.getLayoutConstraint();


									bounds.setWidth(autoSizeMarker.getMaxSize().width);
									bounds.setHeight(autoSizeMarker.getMaxSize().height);
								}
							}
						}
					}
					return CommandResult.newOKCommandResult();
				}

			};

			try {
				cmd.execute(null, null);
			} catch (ExecutionException e) {
			}
		}
	}

	
	/**
	 * Returns an description.
	 * @return
	 */
	public String getDescription() {
		return "make same size";
	}

	
	/**
	 * Returns an label.
	 * @return 
	 */
	public String getLabel() {
		return "make same size";
	}

	
	/**
	 * Also calls execute.
	 */
	public void redo() {
		execute();
	}

	
	/**
	 * Undo.
	 * Sets the last size before auto-sizing.
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
				protected CommandResult doExecuteWithResult(
						IProgressMonitor monitor, IAdaptable info)
						throws ExecutionException {
					for (Iterator<AutoSizeMarker> iMarkers = autoSizeMarkers
							.iterator(); iMarkers.hasNext();) {
						AutoSizeMarker autoSizeMarker = iMarkers.next();

						Iterator<EditPartMarker> editPartMarkers = autoSizeMarker
								.getMarkers().iterator();
						while (editPartMarkers.hasNext()) {
							EditPartMarker marker = editPartMarkers.next();
							
							ShapeNodeEditPart editPart = marker.getEditPart();
							editPart.getFigure().setSize(
									autoSizeMarker.getMaxSize());
							editPart.getContentPane().setSize(
									autoSizeMarker.getMaxSize());
							if (editPart.getNotationView() instanceof Node) {
								Node node = (Node) editPart.getNotationView();
								if (node.getLayoutConstraint() instanceof Bounds) {
									Bounds bounds = (Bounds) node
											.getLayoutConstraint();

									bounds.setWidth(marker.getSize().width);
									bounds.setHeight(marker.getSize().height);
								}
							}
						}
					}
					return CommandResult.newOKCommandResult();
				}

			};

			try {
				cmd.execute(null, null);
			} catch (ExecutionException e) {
			}
		}
	}
}

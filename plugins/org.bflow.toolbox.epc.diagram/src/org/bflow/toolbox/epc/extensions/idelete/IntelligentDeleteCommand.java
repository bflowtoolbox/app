package org.bflow.toolbox.epc.extensions.idelete;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.AbstractCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

/**
 * Defines an undoable command for the intelligent deleter.
 * @author Arian Storch
 * @since 29/08/10
 *
 */
public class IntelligentDeleteCommand extends AbstractCommand {
	
	private DestroyElementRequest req;
		
	/**
	 * Default constructor.
	 * @param req 
	 */
	public IntelligentDeleteCommand(DestroyElementRequest req) {
		super("Intelligent Delete");
		this.setLabel("Intelligent Delete");
		this.req = req;
	}

	public void execute() {
		IntelligentDeleter.delete(req.getElementToDestroy());
	}
	
	public void undo() {
		IntelligentDeleter.undo();		
	}

	@Override
	protected CommandResult doExecuteWithResult(
			IProgressMonitor progressMonitor, IAdaptable info)
			throws ExecutionException {
		
		execute();
		
		return CommandResult.newOKCommandResult();
	}

	@Override
	protected CommandResult doRedoWithResult(IProgressMonitor progressMonitor,
			IAdaptable info) throws ExecutionException {
		
		execute();
		
		return CommandResult.newOKCommandResult();
	}

	@Override
	protected CommandResult doUndoWithResult(IProgressMonitor progressMonitor,
			IAdaptable info) throws ExecutionException {
		undo();
		
		return CommandResult.newOKCommandResult();
	}
	
	@Override
	public boolean canRedo() {
		return false;
	}
	
}

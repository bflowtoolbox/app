package customsrc;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.ui.action.AbstractActionHandler;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;

import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.diagram.edit.parts.ActorActorCompartmentEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.AgentAgentCompartmentEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.ModelEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.PositionPositionCompartmentEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.RoleRoleCompartmentEditPart;
import edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdateCommand;
import edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes;

public class SetIntentionTypeAction extends AbstractActionHandler {
	
	protected String ID = ""; //$NON-NLS-1$
	protected EvaluationLabel evalField = null;
	protected String evalLabel = "";
	protected String commandName = "";
	
	// What we wish to change into
	protected String changeTo = "";

	protected SetIntentionTypeAction(IWorkbenchPage workbenchPage, String changeTo) {
		super(workbenchPage);
		this.changeTo = changeTo;
	}
	
	public String getID() {
		return ID;
	}
	
	@Override
	protected void doRun(IProgressMonitor progressMonitor) {
		IStructuredSelection selection = getStructuredSelection();
		
		if(selection == null) {
			return;
		}
		
		for(Object intention : selection.toArray()) {
			doTypeSwitch((GraphicalEditPart)intention);
		}
	}
	
	public void doTypeSwitch(GraphicalEditPart part) {
		DiagramCommandStack dcs = part.getDiagramEditDomain().getDiagramCommandStack();
		
		Object container = part.getParent();
		
		// Each intention has 5 possible sub-types:
		// - 1 for being directly on the canvas
		// - 4 for being inside a container: Actor, Agent, Position, or Role.	
		
		// Since types are generated, this function needs to be carefully maintained,
		// in order to avoid using old types that result in NullPointer exceptions.
		
		// The new element's type
		IElementType type = null;
		
		if(container instanceof ModelEditPart) {
			if(changeTo.equals("Hardgoal")) {
				type = Openome_modelElementTypes.Goal_2005;
			} else if (changeTo.equals("Softgoal")) {
				type = Openome_modelElementTypes.Softgoal_2006;
			} else if (changeTo.equals("Task")) {
				type = Openome_modelElementTypes.Task_2007;
			} else if (changeTo.equals("Resource")) {
				type = Openome_modelElementTypes.Resource_2008;
			}
		} else if(container instanceof ActorActorCompartmentEditPart) {
			if(changeTo.equals("Hardgoal")) {
				type = Openome_modelElementTypes.Goal_3001;
			} else if (changeTo.equals("Softgoal")) {
				type = Openome_modelElementTypes.Softgoal_3002;
			} else if (changeTo.equals("Task")) {
				type = Openome_modelElementTypes.Task_3004;
			} else if (changeTo.equals("Resource")) {
				type = Openome_modelElementTypes.Resource_3003;
			}
		} else if(container instanceof AgentAgentCompartmentEditPart) {
			if(changeTo.equals("Hardgoal")) {
				type = Openome_modelElementTypes.Goal_3005;
			} else if (changeTo.equals("Softgoal")) {
				type = Openome_modelElementTypes.Softgoal_3006;
			} else if (changeTo.equals("Task")) {
				type = Openome_modelElementTypes.Task_3008;
			} else if (changeTo.equals("Resource")) {
				type = Openome_modelElementTypes.Resource_3007;
			}
		} else if(container instanceof PositionPositionCompartmentEditPart) {
			if(changeTo.equals("Hardgoal")) {
				type = Openome_modelElementTypes.Goal_3009;
			} else if (changeTo.equals("Softgoal")) {
				type = Openome_modelElementTypes.Softgoal_3010;
			} else if (changeTo.equals("Task")) {
				type = Openome_modelElementTypes.Task_3012;
			} else if (changeTo.equals("Resource")) {
				type = Openome_modelElementTypes.Resource_3011;
			}
		} else if(container instanceof RoleRoleCompartmentEditPart) {
			if(changeTo.equals("Hardgoal")) {
				type = Openome_modelElementTypes.Goal_3013;
			} else if (changeTo.equals("Softgoal")) {
				type = Openome_modelElementTypes.Softgoal_3014;
			} else if (changeTo.equals("Task")) {
				type = Openome_modelElementTypes.Task_3016;
			} else if (changeTo.equals("Resource")) {
				type = Openome_modelElementTypes.Resource_3015;
			}
		}
		
		SetIntentionCommand command = new SetIntentionCommand(part, type);
		
		if(command.canExecute()) {
			dcs.execute(new ICommandProxy(command));
			dcs.flush();
		} else {
			System.err.println("SetIntentionCommand problem!");
		}
		
		// refresh diagram to reflect changes
		refresh();
	}
	
	/*
	 * Command to replace an intention with another of a given type.
	 */
	private class SetIntentionCommand extends AbstractTransactionalCommand
	{
		private GraphicalEditPart part;
		private IElementType type;
		
		public SetIntentionCommand(GraphicalEditPart part, IElementType type)
		{
	        super(part.getEditingDomain(), "Change Intention Type", null);
	        
	        this.part = part;
	        this.type = type;
		}
		
		protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info)
	    	throws ExecutionException
		{    
			ReplaceElement.replace(part, (GraphicalEditPart)part.getParent(), type, monitor, info);
			
			return CommandResult.newOKCommandResult();
		}
	}
	
	public void refresh() {
		Openome_modelDiagramUpdateCommand up = new Openome_modelDiagramUpdateCommand();
		
		try {
			up.execute(null);
		} catch(ExecutionException e) {
			System.err.println(e.getLocalizedMessage());
		}
	}
}

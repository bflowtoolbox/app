package customsrc;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.ui.action.AbstractActionHandler;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;

import edu.toronto.cs.openome_model.Container;
import edu.toronto.cs.openome_model.EvaluationLabel;
import edu.toronto.cs.openome_model.diagram.edit.parts.ActorActorCompartmentEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.AgentAgentCompartmentEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.PositionPositionCompartmentEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.RoleRoleCompartmentEditPart;
import edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdateCommand;
import edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes;
import edu.toronto.cs.openome_model.impl.GoalImpl;
import edu.toronto.cs.openome_model.impl.ResourceImpl;
import edu.toronto.cs.openome_model.impl.SoftgoalImpl;
import edu.toronto.cs.openome_model.impl.TaskImpl;

public class SetActorTypeAction extends AbstractActionHandler {
	
	protected String ID = ""; //$NON-NLS-1$
	protected EvaluationLabel evalField = null;
	protected String evalLabel = "";
	protected String commandName = "";
	
	// What we wish to change into
	protected String changeTo = "";

	protected SetActorTypeAction(IWorkbenchPage workbenchPage, String changeTo) {
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
		
		for(Object actor : selection.toArray()) {
			doTypeSwitch((GraphicalEditPart)actor);
		}
	}
	
	private void doTypeSwitch(GraphicalEditPart part) {
		DiagramCommandStack dcs = part.getDiagramEditDomain().getDiagramCommandStack();
		
		// The new container's type
		IElementType type = null;
		
		// Since types are generated, this function needs to be carefully maintained,
		// in order to avoid using old types that result in NullPointer exceptions.
		
		if(changeTo.equals("Actor")) {
			type = Openome_modelElementTypes.Actor_2001;
		} else if(changeTo.equals("Agent")) {
			type = Openome_modelElementTypes.Agent_2002;
		} else if(changeTo.equals("Position")) {
			type = Openome_modelElementTypes.Position_2003;
		} else if(changeTo.equals("Role")) {
			type = Openome_modelElementTypes.Role_2004;
		}
		
		// We execute this in two parts: SetActorCommand1 and SetActorCommand2
		
		// We can't get the new container's GraphicalEditPart until we dcs.execute() the first command,
		// which means that the user has to Ctrl-Z twice to revert changing a container's type.
		
		// Part 1: create the new container
		
		SetActorCommand1 command1 = new SetActorCommand1(part, type);
		
		if(command1.canExecute()) {
			dcs.execute(new ICommandProxy(command1));
			dcs.flush();
		} else {
			System.err.println("SetActorCommand1 problem!");
		}
		
		// Part 2: replace the contained intentions, and delete the old container
		
		SetActorCommand2 command2 = new SetActorCommand2(command1);
		
		if(command2.canExecute()) {
			dcs.execute(new ICommandProxy(command2));
			dcs.flush();
		} else {
			System.err.println("SetActorCommand2 problem!");
		}

		// refresh diagram to reflect changes
		refresh();
	}
	
	/*
	 * Command to replace a container with another of a given type.
	 */
	private class SetActorCommand1 extends AbstractTransactionalCommand
	{
		public GraphicalEditPart part;
		public IElementType type;
		
		public TransactionalEditingDomain domain;
		
		public View originalView;
		public EObject originalElement;
		
		public View newView;
		
		public SetActorCommand1(GraphicalEditPart part, IElementType type)
		{
	        super(part.getEditingDomain(), "Change Actor Type (Part 1 / 2)", null);
	        
	        this.part = part;
	        this.type = type;
	        
	        this.domain = part.getEditingDomain();
	        
	        this.originalView = part.getNotationView();
	        this.originalElement = originalView.getElement();
		}
		
		protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info)
	    	throws ExecutionException
		{    
			GraphicalEditPart container = (GraphicalEditPart)part.getParent();
			
			// Create a new container
			
			CreateElementCommand commandElement = new CreateElementCommand(
				new CreateElementRequest(domain, originalElement.eContainer(), type)
			);
			
			if(commandElement.canExecute()) {
				commandElement.execute(monitor, info);
			} else {
				System.err.println("commandElement problem!");
			}
			
			// Copy the old container's meta data
			
			EObject element = commandElement.getNewElement();
			
			TransferMetaCommand commandMeta = new TransferMetaCommand(domain, element, originalElement);
			
			if(commandMeta.canExecute()) {
				commandMeta.execute(monitor, info);
			} else {
				System.err.println("commandMeta problem!");
			}
			
			// Create a view for the new container
			
			ViewDescriptor viewDescriptor = new ViewDescriptor(
				new EObjectAdapter(element),
				Node.class,
				((IHintedType)type).getSemanticHint(), true,
				container.getDiagramPreferencesHint()
			);
			
			Command commandView = container.getCommand(
				new CreateViewRequest(viewDescriptor)
			);
			
			if(commandView.canExecute()) {
				commandView.execute();
			} else {
				System.err.println("commandView problem!");
			}	
			
			// Keep the old container's canvas position	
			
			newView = (View)viewDescriptor.getAdapter(View.class);
			
			IFigure fig = part.getContentPane();
			Rectangle coords = fig.getBounds();
			
			SetBoundsCommand commandPosition = new SetBoundsCommand(
				domain,
				"",
				new EObjectAdapter(newView),
				new Point(coords.x, coords.y)
			);
			
			if(commandPosition.canExecute()) {
				commandPosition.execute(monitor, info);
			} else {
				System.err.println("commandPosition problem!");
			}
			
			return CommandResult.newOKCommandResult();
		}
	}
	
	/*
	 * Command that continues SetActorCommand1, and replaces the contained intentions.
	 */
	private class SetActorCommand2 extends AbstractTransactionalCommand
	{
		private SetActorCommand1 setBase;
		
		private GraphicalEditPart part;
		private IElementType type;
		
		public SetActorCommand2(SetActorCommand1 setBase)
		{
	        super(setBase.part.getEditingDomain(), "Change Actor Type (Part 2 / 2)", null);
	        
	        this.setBase = setBase;
	        
	        this.part = setBase.part;
	        this.type = setBase.type;
		}
		
		protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info)
	    	throws ExecutionException
		{
			// This should be the ModelEditPart itself
			GraphicalEditPart container = (GraphicalEditPart)part.getParent();
			
			// The new container that was created in SetActorCommand1
			GraphicalEditPart newContainer = getNewContainer(container, setBase.newView);
			
			// Get the contained intentions
			List<GraphicalEditPart> parts = new LinkedList<GraphicalEditPart>();
			collectIntentions(part, parts);

			for(GraphicalEditPart g : parts) {
				View iView = g.getNotationView();
				EObject iElement = iView.getElement();
				
				// Change the element's type to the one appropriate
				// to the container it now sits in.
				
				// Since types are generated, this function needs to be carefully maintained,
				// in order to avoid using old types that result in NullPointer exceptions.
				
				IElementType iType = null;
				
				if(iElement instanceof GoalImpl) {
					if(type == Openome_modelElementTypes.Actor_2001) {
						iType = Openome_modelElementTypes.Goal_3001;
					} else if(type == Openome_modelElementTypes.Agent_2002) {
						iType = Openome_modelElementTypes.Goal_3005;
					} else if(type == Openome_modelElementTypes.Position_2003) {
						iType = Openome_modelElementTypes.Goal_3009;
					} else if(type == Openome_modelElementTypes.Role_2004) {
						iType = Openome_modelElementTypes.Goal_3013;
					}
				} else if(iElement instanceof SoftgoalImpl) {
					if(type == Openome_modelElementTypes.Actor_2001) {
						iType = Openome_modelElementTypes.Softgoal_3002;
					} else if(type == Openome_modelElementTypes.Agent_2002) {
						iType = Openome_modelElementTypes.Softgoal_3006;
					} else if(type == Openome_modelElementTypes.Position_2003) {
						iType = Openome_modelElementTypes.Softgoal_3010;
					} else if(type == Openome_modelElementTypes.Role_2004) {
						iType = Openome_modelElementTypes.Softgoal_3014;
					}
				} else if(iElement instanceof TaskImpl) {
					if(type == Openome_modelElementTypes.Actor_2001) {
						iType = Openome_modelElementTypes.Task_3004;
					} else if(type == Openome_modelElementTypes.Agent_2002) {
						iType = Openome_modelElementTypes.Task_3008;
					} else if(type == Openome_modelElementTypes.Position_2003) {
						iType = Openome_modelElementTypes.Task_3012;
					} else if(type == Openome_modelElementTypes.Role_2004) {
						iType = Openome_modelElementTypes.Task_3016;
					}
				} else if(iElement instanceof ResourceImpl) {
					if(type == Openome_modelElementTypes.Actor_2001) {
						iType = Openome_modelElementTypes.Resource_3003;
					} else if(type == Openome_modelElementTypes.Agent_2002) {
						iType = Openome_modelElementTypes.Resource_3007;
					} else if(type == Openome_modelElementTypes.Position_2003) {
						iType = Openome_modelElementTypes.Resource_3011;
					} else if(type == Openome_modelElementTypes.Role_2004) {
						iType = Openome_modelElementTypes.Resource_3015;
					}
				}
				
				ReplaceElement.replace(g, newContainer, iType, monitor, info);
			}
			
			// Delete the old container
			
			DestroyElementCommand commandDelete = new DestroyElementCommand(
				new DestroyElementRequest(setBase.domain, setBase.originalElement, false)
			);
			
			if(commandDelete.canExecute()) {
				commandDelete.execute(monitor, info);
			} else {
				System.err.println("commandDelete problem!");
			}
			
			// Delete the old container's view
			
			DeleteCommand commandDeleteView = new DeleteCommand(setBase.domain, setBase.originalView);
			
			if(commandDeleteView.canExecute()) {
				commandDeleteView.execute(monitor, info);
			} else {
				System.err.println("commandDeleteView problem!");
			}
			
			return CommandResult.newOKCommandResult();
		}
	}
	
	/*
	 * Command to copy a container's children and name to another one.
	 */
	private class TransferMetaCommand extends AbstractTransactionalCommand
	{
		private Container dst;
		private Container src;
		
		public TransferMetaCommand(TransactionalEditingDomain domain, EObject newElement, EObject oldElement)
		{
	        super(domain, "", null);
	        
	        this.dst = (Container)newElement;
	        this.src = (Container)oldElement;
		}
		
		protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info)
		    throws ExecutionException
		{  	
			dst.setName(src.getName());
			
			dst.getIntentions().addAll(src.getIntentions());
			
			return CommandResult.newOKCommandResult();
		}
	}
	
	/*
	 * Collect a container's intentions' GraphicalEditParts.
	 */
	private void collectIntentions(GraphicalEditPart part, List<GraphicalEditPart> parts)
	{
		for(Object o : part.getChildren()) {
			GraphicalEditPart g = (GraphicalEditPart)o;
			
			if(o instanceof AbstractBorderedShapeEditPart) {
				parts.add(g);
			} else {
				collectIntentions(g, parts);
			}
		}
	}
	
	/*
	 * Get the new container's GraphicalEditPart.
	 */
	private GraphicalEditPart getNewContainer(GraphicalEditPart p, View v)
	{
		for(Object o : p.getChildren()) {
			if(v == p.getNotationView()) {
				if(o instanceof ActorActorCompartmentEditPart
					|| o instanceof AgentAgentCompartmentEditPart
					|| o instanceof RoleRoleCompartmentEditPart
					|| o instanceof PositionPositionCompartmentEditPart) {
					return (GraphicalEditPart)o;
				}
			} else {
				GraphicalEditPart g = getNewContainer((GraphicalEditPart)o, v);
				
				if(g != null) {
					return g;
				}
			}
		}
		
		return null;
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

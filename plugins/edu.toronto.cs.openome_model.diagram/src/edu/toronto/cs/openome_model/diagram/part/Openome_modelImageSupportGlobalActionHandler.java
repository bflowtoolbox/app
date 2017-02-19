package edu.toronto.cs.openome_model.diagram.part;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.CopyToClipboardCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.ui.action.global.GlobalActionId;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionContext;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ComponentEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.PopupBarEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramEditDomain;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.diagram.ui.render.clipboard.AWTClipboardHelper;
import org.eclipse.gmf.runtime.diagram.ui.render.internal.commands.CopyImageCommand;
import org.eclipse.gmf.runtime.diagram.ui.render.internal.providers.ImageSupportGlobalActionHandler;
import org.eclipse.gmf.runtime.diagram.ui.requests.PasteViewRequest;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;
import org.eclipse.gmf.runtime.emf.ui.properties.actions.PropertyPageViewAction;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import edu.toronto.cs.openome_model.Association;
import edu.toronto.cs.openome_model.Container;
import edu.toronto.cs.openome_model.Contribution;
import edu.toronto.cs.openome_model.Decomposition;
import edu.toronto.cs.openome_model.Dependable;
import edu.toronto.cs.openome_model.Dependency;
import edu.toronto.cs.openome_model.Intention;
import edu.toronto.cs.openome_model.Model;
import edu.toronto.cs.openome_model.diagram.edit.commands.GoalCreateCommand;
import edu.toronto.cs.openome_model.diagram.edit.parts.ActorActorCompartmentEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.ActorEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.AgentAgentCompartmentEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.AgentEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Goal2EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Goal3EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Goal4EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Goal5EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.GoalEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.ModelEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Openome_modelEditPartFactory;
import edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.PositionEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.PositionPositionCompartmentEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Resource2EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Resource3EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Resource4EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Resource5EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.ResourceEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.RoleEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.RoleRoleCompartmentEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal2EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal3EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal4EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Softgoal5EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.SoftgoalEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Task2EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Task3EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Task4EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.Task5EditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.TaskEditPart;
import edu.toronto.cs.openome_model.diagram.providers.Openome_modelEditPartProvider;
import edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes;
import edu.toronto.cs.openome_model.diagram.providers.Openome_modelViewProvider;
import edu.toronto.cs.openome_model.diagram.view.factories.GoalViewFactory;
import edu.toronto.cs.openome_model.impl.ActorImpl;
import edu.toronto.cs.openome_model.impl.AgentImpl;
import edu.toronto.cs.openome_model.impl.AndContributionImpl;
import edu.toronto.cs.openome_model.impl.AndDecompositionImpl;
import edu.toronto.cs.openome_model.impl.AssociationImpl;
import edu.toronto.cs.openome_model.impl.BreakContributionImpl;
import edu.toronto.cs.openome_model.impl.ContainerImpl;
import edu.toronto.cs.openome_model.impl.ContributionImpl;
import edu.toronto.cs.openome_model.impl.CoversAssociationImpl;
import edu.toronto.cs.openome_model.impl.DecompositionImpl;
import edu.toronto.cs.openome_model.impl.DependencyImpl;
import edu.toronto.cs.openome_model.impl.GoalImpl;
import edu.toronto.cs.openome_model.impl.HelpContributionImpl;
import edu.toronto.cs.openome_model.impl.HurtContributionImpl;
import edu.toronto.cs.openome_model.impl.INSAssociationImpl;
import edu.toronto.cs.openome_model.impl.IntentionImpl;
import edu.toronto.cs.openome_model.impl.IsAAssociationImpl;
import edu.toronto.cs.openome_model.impl.IsPartOfAssociationImpl;
import edu.toronto.cs.openome_model.impl.LinkImpl;
import edu.toronto.cs.openome_model.impl.MakeContributionImpl;
import edu.toronto.cs.openome_model.impl.ModelImpl;
import edu.toronto.cs.openome_model.impl.OccupiesAssociationImpl;
import edu.toronto.cs.openome_model.impl.OrContributionImpl;
import edu.toronto.cs.openome_model.impl.OrDecompositionImpl;
import edu.toronto.cs.openome_model.impl.PlaysAssociationImpl;
import edu.toronto.cs.openome_model.impl.PositionImpl;
import edu.toronto.cs.openome_model.impl.ResourceImpl;
import edu.toronto.cs.openome_model.impl.RoleImpl;
import edu.toronto.cs.openome_model.impl.SoftgoalImpl;
import edu.toronto.cs.openome_model.impl.SomeMinusContributionImpl;
import edu.toronto.cs.openome_model.impl.SomePlusContributionImpl;
import edu.toronto.cs.openome_model.impl.TaskImpl;
import edu.toronto.cs.openome_model.impl.UnknownContributionImpl;

public class Openome_modelImageSupportGlobalActionHandler extends
		ImageSupportGlobalActionHandler {

	protected static List<EditPart> editPartClipboard = new ArrayList<EditPart>();
	protected static List<EObject> editPartClipboard2 = new ArrayList<EObject>();
	protected static List<EObject> cutClipboard = new ArrayList<EObject>();

	private boolean cutDeleted = true;

	/**
	 * Maps original elements to duplicated element for use in cross-diagram
	 * paste only
	 */
	protected static HashMap<EObject, EObject> map = new HashMap<EObject, EObject>();

	public static IGraphicalEditPart targetEditPart;
	private static IGraphicalEditPart modelEditPart;
	private static HashMap<EObject, EditPart> modelToEditPart = new HashMap<EObject, EditPart>();
	 // need to keep track of copies correspondence
	private static HashMap<EObject, EObject> newToOld = new HashMap<EObject, EObject>();
	private static HashMap<EObject, EObject> oldToNew = new HashMap<EObject, EObject>();
	/**
	 * Constructor
	 */
	public Openome_modelImageSupportGlobalActionHandler() {
		super();
	}

	/**
	 * Modified version of canCopy that allows copying of an Actor
	 */
	protected boolean canCopy(IGlobalActionContext cntxt) {
		// allows copying of a compartment
		for (Object o : ((IStructuredSelection) cntxt.getSelection()).toArray()) {
			if (o instanceof ShapeCompartmentEditPart) {
				return true;
			}
		}
		return super.canCopy(cntxt);
	}

	protected ICommand getCopyCommand(IGlobalActionContext cntxt,
			IDiagramWorkbenchPart diagramPart, final boolean isUndoable) {
		return super.getCopyCommand(cntxt, diagramPart, isUndoable);
	}

	/**
	 * Provides the commmand We shall modify it a bit to modify behaviour of
	 * paste
	 */
	public ICommand getCommand(IGlobalActionContext cntxt) {
		/* Check if the active part is a IDiagramWorkbenchPart */
		IWorkbenchPart part = cntxt.getActivePart();
		if (!(part instanceof IDiagramWorkbenchPart)) {
			return null;
		}

		/* Get the model operation context */
		IDiagramWorkbenchPart diagramPart = (IDiagramWorkbenchPart) part;

		/* Create a command */
		ICommand command = null;

		/* Check the action id */
		String actionId = cntxt.getActionId();
		if (actionId.equals(GlobalActionId.DELETE)) {
			// visibility problem
			super.getCommand(cntxt);

		} else if (actionId.equals(GlobalActionId.COPY)) {
			command = getCopyCommand(cntxt, diagramPart, false);

			// Fill our own clipboard
			editPartClipboard.clear();
			editPartClipboard2.clear();
			cutClipboard.clear();
			/* Get the selected edit parts */
//			Object[] objects = ((IStructuredSelection) cntxt.getSelection())
//					.toArray();

//			for (Object o : objects) {
//				editPartClipboard.add((EditPart) o);
//			}
			oldToNew.clear();
			newToOld.clear();
			/* Get the selected edit parts */
			Object[] objects2 = ((IStructuredSelection) cntxt.getSelection())
					.toArray();

			for (Object ep : objects2) {
				final EObject o = ((IGraphicalEditPart) ep).getNotationView()
						.getElement();
				addToCutOrCopyClipBoard(o, "copy");
			}
			
			for (EObject o : editPartClipboard2) {
				connectLinks(o);
			}

		} else if (actionId.equals(GlobalActionId.CUT)) {
			command = getCutCommand(cntxt, diagramPart);

		} else if (actionId.equals(GlobalActionId.OPEN)) {
			// Open command: use the previously cached command.
			// visibility problem
			super.getCommand(cntxt);
		} else if (actionId.equals(GlobalActionId.PASTE)) {

			PasteViewRequest pasteReq = createPasteViewRequest();

			/* Get the selected edit parts */
			Object[] objects = ((IStructuredSelection) cntxt.getSelection())
					.toArray();

			if (objects.length == 1) {

				Command paste;

				/*
				 * Send the request to the currently selected part, however if
				 * the selection is a container then we must redirect the
				 * request to its compartment
				 * 
				 * This enables us to just select a container and click paste on
				 * it
				 */
				EditPart ep = (EditPart) objects[0];
				if (objects[0] instanceof ActorEditPart) {
					ep = ((ActorEditPart) objects[0])
							.getChildBySemanticHint(Integer
									.toString(ActorActorCompartmentEditPart.VISUAL_ID));
					paste = ep.getCommand(pasteReq);
				} else if (objects[0] instanceof AgentEditPart) {
					ep = ((AgentEditPart) objects[0])
							.getChildBySemanticHint(Integer
									.toString(AgentAgentCompartmentEditPart.VISUAL_ID));
					paste = ep.getCommand(pasteReq);
				} else if (objects[0] instanceof RoleEditPart) {
					ep = ((RoleEditPart) objects[0])
							.getChildBySemanticHint(Integer
									.toString(RoleRoleCompartmentEditPart.VISUAL_ID));
					paste = ep.getCommand(pasteReq);
				} else if (objects[0] instanceof PositionEditPart) {
					ep = ((PositionEditPart) objects[0])
							.getChildBySemanticHint(Integer
									.toString(PositionPositionCompartmentEditPart.VISUAL_ID));
					paste = ep.getCommand(pasteReq);
				} else {
					paste = ((EditPart) objects[0]).getCommand(pasteReq);
				}
				targetEditPart = (IGraphicalEditPart) ep;
				modelEditPart = (IGraphicalEditPart) targetEditPart.getRoot()
						.getChildren().get(0);
				if (paste != null) {
					/* Set the command */
					CommandStack cs = diagramPart.getDiagramEditDomain()
							.getDiagramCommandStack();

					TransactionalEditingDomain pasteToDomain = ((IGraphicalEditPart) ep)
							.getEditingDomain();

					// So since we cannot duplicate, we should add to the
					// destination diagram
					EObject container = ((IGraphicalEditPart) ep)
							.getNotationView().getElement();
					List<CreateElementCommand> commandList = getCreateCommandList(
							pasteToDomain, container, targetEditPart);

					map.clear();

					// "Duplicate" all the selected elements
					for (CreateElementCommand c : commandList) {
						ICommandProxy create = new ICommandProxy(c);
						cs.execute(create);
						map.put(((CreateDuplicateElementCommand) c)
								.getOriginal(),
								((CreateDuplicateElementCommand) c)
										.getDuplicate());
					}

					// Now delete the invisible elements that had been cut
					if (!cutClipboard.isEmpty() && !cutDeleted) {

						// Now we repoint the paste source to the elements we
						// just pasted
						cutClipboard.clear();
						for (EObject e : map.keySet()) {
							cutClipboard.add(EcoreUtil.copy(map.get(e)));
						}
						cutDeleted = true;
					}

					// Adds to the diagram
					// cs.execute(paste); // we don't want to have double paste
					((Openome_modelDiagramEditor) PlatformUI.getWorkbench()
							.getActiveWorkbenchWindow().getActivePage()
							.getActiveEditor()).getDiagramEditPart().refresh();
					diagramPart.getDiagramEditPart().getFigure().invalidate();
					diagramPart.getDiagramEditPart().getFigure().validate();
					selectAddedObject(diagramPart.getDiagramGraphicalViewer(),
							DiagramCommandStack.getReturnValues(paste));
					return null;
				}
			}
		} else if (actionId.equals(GlobalActionId.SAVE)) {
			part.getSite().getPage().saveEditor((IEditorPart) diagramPart,
					false);
		} else if (actionId.equals(GlobalActionId.PROPERTIES)) {
			new PropertyPageViewAction().run();
		} else {
			System.out.println("actionID is : " + actionId);
		}

		return command;
	}

	/**
	 * Returns a list of CreateElementCommand that can create every element in
	 * the clipboard
	 * 
	 * @param domain
	 * @param container
	 * @return
	 */
	private List<CreateElementCommand> getCreateCommandList(
			TransactionalEditingDomain domain, EObject container,
			IGraphicalEditPart dstEditPart) {
		List<CreateElementCommand> commandList = new ArrayList<CreateElementCommand>();
		CreateElementCommand c;

		List<EObject> tmp = new ArrayList<EObject>();
		List<EObject> tmp2 = new ArrayList<EObject>();

		// First get all the actors and intentions
		if (!cutClipboard.isEmpty()) {
			for (EObject o : cutClipboard) {
				if (o instanceof LinkImpl) {
					continue;
				}
				tmp.add(o);
				tmp2.add(o);
			}
		} else {
//			for (EditPart ep : editPartClipboard) {
//				final EObject o = ((IGraphicalEditPart) ep).getNotationView()
//						.getElement();
			for (EObject o : editPartClipboard2) {
				if (o instanceof LinkImpl) {
					continue;
				}
				tmp.add(o);
				tmp2.add(o);
			}
		}

		// then delete all doubled intentions
		for (EObject o : tmp) {
			if (o instanceof Intention) {
				if (tmp2.contains(o.eContainer())) {
					tmp2.remove(o);
				}
			}
		}

		// Now create the series of commands
		for (EObject o : tmp2) {
			c = getCreateCommand(domain, o, container, dstEditPart);
			if (c != null)
				commandList.add(c);
		}

		List<EObject> done = new ArrayList<EObject>();

		// then append the commands to create links
		// this is because it does not make sense to create links without the
		// sources and targets

		if (!cutClipboard.isEmpty()) {
			for (EObject o : cutClipboard) {
				if (o instanceof LinkImpl && !done.contains(o)) {
					c = getCreateLinkCommand(domain, o, container, dstEditPart);
					if (c != null)
						commandList.add(c);
					done.add(o);
				}
			}
		} else {
//			for (EditPart ep : editPartClipboard) {
//				final EObject o = ((IGraphicalEditPart) ep).getNotationView()
//						.getElement();
			for (EObject o : editPartClipboard2) {
				if (o instanceof LinkImpl && !done.contains(o)) {
					c = getCreateLinkCommand(domain, o, container, dstEditPart);
					if (c != null)
						commandList.add(c);
					done.add(o);
				}
			}
		}

		return commandList;

	}

	private static CreateElementCommand getCreateLinkCommand(
			TransactionalEditingDomain domain, EObject o, EObject container,
			IGraphicalEditPart dstEditPart) {
		CreateElementRequest req = null;
		if (o instanceof AndDecompositionImpl) {
			req = new CreateElementRequest(domain, modelEditPart
					.getNotationView().getElement(),
					Openome_modelElementTypes.AndDecomposition_4002);
		} else if (o instanceof OrDecompositionImpl) {
			req = new CreateElementRequest(domain, modelEditPart
					.getNotationView().getElement(),
					Openome_modelElementTypes.OrDecomposition_4003);
		} else if (o instanceof DependencyImpl) {
			req = new CreateElementRequest(domain, modelEditPart
					.getNotationView().getElement(),
					Openome_modelElementTypes.Dependency_4001);
		} else if (o instanceof MakeContributionImpl) {
			req = new CreateElementRequest(domain, modelEditPart
					.getNotationView().getElement(),
					Openome_modelElementTypes.MakeContribution_4007);
		} else if (o instanceof SomePlusContributionImpl) {
			req = new CreateElementRequest(domain, modelEditPart
					.getNotationView().getElement(),
					Openome_modelElementTypes.SomePlusContribution_4009);
		} else if (o instanceof HelpContributionImpl) {
			req = new CreateElementRequest(domain, modelEditPart
					.getNotationView().getElement(),
					Openome_modelElementTypes.HelpContribution_4005);
		} else if (o instanceof UnknownContributionImpl) {
			req = new CreateElementRequest(domain, modelEditPart
					.getNotationView().getElement(),
					Openome_modelElementTypes.UnknownContribution_4011);
		} else if (o instanceof HurtContributionImpl) {
			req = new CreateElementRequest(domain, modelEditPart
					.getNotationView().getElement(),
					Openome_modelElementTypes.HurtContribution_4006);
		} else if (o instanceof SomeMinusContributionImpl) {
			req = new CreateElementRequest(domain, modelEditPart
					.getNotationView().getElement(),
					Openome_modelElementTypes.SomeMinusContribution_4010);
		} else if (o instanceof BreakContributionImpl) {
			req = new CreateElementRequest(domain, modelEditPart
					.getNotationView().getElement(),
					Openome_modelElementTypes.BreakContribution_4008);
		} else if (o instanceof AndContributionImpl) {
			req = new CreateElementRequest(domain, modelEditPart
					.getNotationView().getElement(),
					Openome_modelElementTypes.AndContribution_4012);
		} else if (o instanceof OrContributionImpl) {
			req = new CreateElementRequest(domain, modelEditPart
					.getNotationView().getElement(),
					Openome_modelElementTypes.OrContribution_4013);
		} else if (o instanceof CoversAssociationImpl) {
			req = new CreateElementRequest(domain, modelEditPart
					.getNotationView().getElement(),
					Openome_modelElementTypes.CoversAssociation_4015);
		} else if (o instanceof INSAssociationImpl) {
			req = new CreateElementRequest(domain, modelEditPart
					.getNotationView().getElement(),
					Openome_modelElementTypes.INSAssociation_4019);
		} else if (o instanceof IsAAssociationImpl) {
			req = new CreateElementRequest(domain, modelEditPart
					.getNotationView().getElement(),
					Openome_modelElementTypes.IsAAssociation_4014);
		} else if (o instanceof IsPartOfAssociationImpl) {
			req = new CreateElementRequest(domain, modelEditPart
					.getNotationView().getElement(),
					Openome_modelElementTypes.IsPartOfAssociation_4017);
		} else if (o instanceof PlaysAssociationImpl) {
			req = new CreateElementRequest(domain, modelEditPart
					.getNotationView().getElement(),
					Openome_modelElementTypes.PlaysAssociation_4018);
		} else if (o instanceof OccupiesAssociationImpl) {
			req = new CreateElementRequest(domain, modelEditPart
					.getNotationView().getElement(),
					Openome_modelElementTypes.OccupiesAssociation_4016);
		} else {
			return null;
		}

		return new CreateDuplicateElementCommand(req, o, dstEditPart);

	}

	private static CreateElementCommand getCreateCommand(
			TransactionalEditingDomain domain, EObject o, EObject container,
			IGraphicalEditPart dstEditPart) {
		CreateElementRequest req = null;
		if (o instanceof GoalImpl) {
			req = new CreateElementRequest(domain, container,
					Openome_modelElementTypes.Goal_3001);
		} else if (o instanceof TaskImpl) {
			req = new CreateElementRequest(domain, container,
					Openome_modelElementTypes.Task_3004);
		} else if (o instanceof SoftgoalImpl) {
			req = new CreateElementRequest(domain, container,
					Openome_modelElementTypes.Softgoal_3002);
		} else if (o instanceof ResourceImpl) {
			req = new CreateElementRequest(domain, container,
					Openome_modelElementTypes.Resource_3003);
		} else if (o instanceof ActorImpl) {
			req = new CreateElementRequest(domain, container,
					Openome_modelElementTypes.Actor_2001);
		} else if (o instanceof AgentImpl) {
			req = new CreateElementRequest(domain, container,
					Openome_modelElementTypes.Agent_2002);
		} else if (o instanceof RoleImpl) {
			req = new CreateElementRequest(domain, container,
					Openome_modelElementTypes.Role_2004);
		} else if (o instanceof PositionImpl) {
			req = new CreateElementRequest(domain, container,
					Openome_modelElementTypes.Position_2003);
		} else {
			return null;
		}

		return new CreateDuplicateElementCommand(req, o, dstEditPart);
	}

	/**
	 * A Command to add a model element to a diagram, based on an existing
	 * element
	 * 
	 * @author johan
	 */
	private static class CreateDuplicateElementCommand extends
			CreateElementCommand {

		/**
		 * The newly created element.
		 */
		private EObject newElement;

		/**
		 * The element the duplicate is based on
		 */
		private EObject oldElement;

		/**
		 * The element type to be created.
		 */
		private final IElementType elementType;

		private IGraphicalEditPart dstEditPart;

		public CreateDuplicateElementCommand(CreateElementRequest request,
				EObject original, IGraphicalEditPart dstEditPart) {
			super(request);
			elementType = request.getElementType();
			oldElement = original;
			this.dstEditPart = dstEditPart;
		}

		public EObject getOriginal() {
			return oldElement;
		}

		public EObject getDuplicate() {
			return getCreateRequest().getNewElement();
		}

		protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
				IAdaptable info) throws ExecutionException {

			// Do the default element creation
			newElement = doDefaultElementCreation();

			if (!getDefaultElementCreationStatus().isOK()) {
				return new CommandResult(getDefaultElementCreationStatus());
			}

			// Configure the new element
			ConfigureRequest configureRequest = createConfigureRequest();

			ICommand configureCommand = elementType
					.getEditCommand(configureRequest);

			IStatus configureStatus = null;

			if (configureCommand != null && configureCommand.canExecute()) {
				configureStatus = configureCommand.execute(monitor, info);
			}

			// Copy the metadata
			if (newElement instanceof IntentionImpl) {
				((IntentionImpl) newElement)
						.setName(((IntentionImpl) oldElement).getName());
				((IntentionImpl) newElement)
						.setQualitativeReasoningCombinedLabel(((IntentionImpl) oldElement)
								.getQualitativeReasoningCombinedLabel());
				((IntentionImpl) newElement)
						.setQualitativeReasoningDenialLabel(((IntentionImpl) oldElement)
								.getQualitativeReasoningDenialLabel());
				((IntentionImpl) newElement)
						.setQualitativeReasoningSatisfiedLabel(((IntentionImpl) oldElement)
								.getQualitativeReasoningSatisfiedLabel());
				((IntentionImpl) newElement)
						.setQuantitativeReasoningCombinedLabel(((IntentionImpl) oldElement)
								.getQuantitativeReasoningCombinedLabel());
				((IntentionImpl) newElement)
						.setQuantitativeReasoningDeniedLabel(((IntentionImpl) oldElement)
								.getQuantitativeReasoningDeniedLabel());
				((IntentionImpl) newElement)
						.setQuantitativeReasoningSatisfiedLabel(((IntentionImpl) oldElement)
								.getQuantitativeReasoningSatisfiedLabel());

				String nodeType = null;
				if (newElement instanceof GoalImpl) {
					if (dstEditPart instanceof ModelEditPart) {
						nodeType = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(GoalEditPart.VISUAL_ID);
					} else if (dstEditPart instanceof ActorActorCompartmentEditPart) {
						nodeType = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(Goal2EditPart.VISUAL_ID);
					} else if (dstEditPart instanceof AgentAgentCompartmentEditPart) {
						nodeType = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(Goal3EditPart.VISUAL_ID);
					} else if (dstEditPart instanceof PositionPositionCompartmentEditPart) {
						nodeType = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(Goal4EditPart.VISUAL_ID);
					} else if (dstEditPart instanceof RoleRoleCompartmentEditPart) {
						nodeType = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(Goal5EditPart.VISUAL_ID);
					}
				} else if (newElement instanceof SoftgoalImpl) {
					if (dstEditPart instanceof ModelEditPart) {
						nodeType = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(SoftgoalEditPart.VISUAL_ID);
					} else if (dstEditPart instanceof ActorActorCompartmentEditPart) {
						nodeType = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(Softgoal2EditPart.VISUAL_ID);
					} else if (dstEditPart instanceof AgentAgentCompartmentEditPart) {
						nodeType = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(Softgoal3EditPart.VISUAL_ID);
					} else if (dstEditPart instanceof PositionPositionCompartmentEditPart) {
						nodeType = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(Softgoal4EditPart.VISUAL_ID);
					} else if (dstEditPart instanceof RoleRoleCompartmentEditPart) {
						nodeType = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(Softgoal5EditPart.VISUAL_ID);
					}
				} else if (newElement instanceof TaskImpl) {
					if (dstEditPart instanceof ModelEditPart) {
						nodeType = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(TaskEditPart.VISUAL_ID);
					} else if (dstEditPart instanceof ActorActorCompartmentEditPart) {
						nodeType = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(Task2EditPart.VISUAL_ID);
					} else if (dstEditPart instanceof AgentAgentCompartmentEditPart) {
						nodeType = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(Task3EditPart.VISUAL_ID);
					} else if (dstEditPart instanceof PositionPositionCompartmentEditPart) {
						nodeType = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(Task4EditPart.VISUAL_ID);
					} else if (dstEditPart instanceof RoleRoleCompartmentEditPart) {
						nodeType = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(Task5EditPart.VISUAL_ID);
					}
				} else if (newElement instanceof ResourceImpl) {
					if (dstEditPart instanceof ModelEditPart) {
						nodeType = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(ResourceEditPart.VISUAL_ID);
					} else if (dstEditPart instanceof ActorActorCompartmentEditPart) {
						nodeType = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(Resource2EditPart.VISUAL_ID);
					} else if (dstEditPart instanceof AgentAgentCompartmentEditPart) {
						nodeType = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(Resource3EditPart.VISUAL_ID);
					} else if (dstEditPart instanceof PositionPositionCompartmentEditPart) {
						nodeType = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(Resource4EditPart.VISUAL_ID);
					} else if (dstEditPart instanceof RoleRoleCompartmentEditPart) {
						nodeType = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
								.getType(Resource5EditPart.VISUAL_ID);
					}
				}

				Node node = ViewService
						.createNode(
								dstEditPart.getNotationView(),
								newElement,
								nodeType,
								edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);

				Openome_modelEditPartFactory fact = new Openome_modelEditPartFactory();
				EditPart n = (EditPart) fact.createEditPart(dstEditPart, node);
				modelToEditPart.put(newElement, n);
			} else if (newElement instanceof ContainerImpl) {
				((ContainerImpl) newElement)
						.setName(((ContainerImpl) oldElement).getName());
				String nodeType = null;
				if (newElement instanceof ActorImpl) {
					nodeType = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(ActorEditPart.VISUAL_ID);
				} else if (newElement instanceof AgentImpl) {
					nodeType = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(AgentEditPart.VISUAL_ID);
				} else if (newElement instanceof PositionImpl) {
					nodeType = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(PositionEditPart.VISUAL_ID);
				} else if (newElement instanceof RoleImpl) {
					nodeType = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
							.getType(RoleEditPart.VISUAL_ID);
				}

				Node node = ViewService
						.createNode(
								dstEditPart.getNotationView(),
								newElement,
								nodeType,
								edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);

				Openome_modelEditPartFactory fact = new Openome_modelEditPartFactory();
				IGraphicalEditPart dstEditPart2 = (IGraphicalEditPart) fact
						.createEditPart(dstEditPart, node);

				View comp = null;
				if (dstEditPart2 instanceof ActorEditPart) {
					comp = getCompartment(node,
							ActorActorCompartmentEditPart.VISUAL_ID);
				} else if (dstEditPart2 instanceof AgentEditPart) {
					comp = getCompartment(node,
							AgentAgentCompartmentEditPart.VISUAL_ID);
				} else if (dstEditPart2 instanceof RoleEditPart) {
					comp = getCompartment(node,
							RoleRoleCompartmentEditPart.VISUAL_ID);
				} else if (dstEditPart2 instanceof PositionEditPart) {
					comp = getCompartment(node,
							PositionPositionCompartmentEditPart.VISUAL_ID);
				}
				IGraphicalEditPart dstEditPart3 = (IGraphicalEditPart) fact
						.createEditPart(dstEditPart2, comp);
				/* THIS COPIES THE CONTENT OF THE ACTOR AS WELL */
				// Create the intentions within this actor
				for (Intention intention : ((ContainerImpl) oldElement)
						.getIntentions()) {
					CreateDuplicateElementCommand createChild = (CreateDuplicateElementCommand) getCreateCommand(
							getCreateRequest().getEditingDomain(), intention,
							newElement, dstEditPart3);
					createChild.execute(monitor, info);
					map.put(createChild.getOriginal(), createChild
							.getDuplicate());
				}

				for (Intention intention : ((ContainerImpl) oldElement)
						.getIntentions()) {

					// Create the links within the actor
					for (Contribution contribution : intention
							.getContributesTo()) {
						CreateElementCommand createContribution = getCreateLinkCommand(
								getCreateRequest().getEditingDomain(),
								contribution, newElement.eContainer(),
								dstEditPart3);
						createContribution.execute(monitor, info);
					}
					for (Decomposition decomposition : intention
							.getDecompositionsTo()) {
						CreateElementCommand createDecomposition = getCreateLinkCommand(
								getCreateRequest().getEditingDomain(),
								decomposition, newElement.eContainer(),
								dstEditPart3);
						createDecomposition.execute(monitor, info);
					}
					for (Dependency dependency : intention.getDependencyFrom()) {
						CreateElementCommand createDependency = getCreateLinkCommand(
								getCreateRequest().getEditingDomain(),
								dependency, newElement.eContainer(),
								dstEditPart3);
						createDependency.execute(monitor, info);
					}
				}
				modelToEditPart.put(newElement, dstEditPart2);

			} else if (newElement instanceof LinkImpl) {

				EObject source = null;
				EObject target = null;
				
				if (oldElement instanceof DecompositionImpl) {
					source = ((DecompositionImpl) oldElement).getSource();
					target = ((DecompositionImpl) oldElement).getTarget();
				} else if (oldElement instanceof ContributionImpl) {
					source = ((ContributionImpl) oldElement).getSource();
					target = ((ContributionImpl) oldElement).getTarget();
				} else if (oldElement instanceof AssociationImpl) {
					source = ((AssociationImpl) oldElement).getSource();
					target = ((AssociationImpl) oldElement).getTarget();
				} else if (oldElement instanceof DependencyImpl) {
					// the order is inverted because of the definition of a
					// Dependency relation
					target = ((DependencyImpl) oldElement).getDependencyFrom();
					source = ((DependencyImpl) oldElement).getDependencyTo();
				}

				// Get the source and target in the destination diagram
				// preconditions: the duplicated source and target already
				// exists in the destination diagram
				source = map.get(source);
				target = map.get(target);

				Openome_modelLinkDescriptor nextLinkDescriptor = null;

				if (newElement instanceof DecompositionImpl) {
					((DecompositionImpl) newElement)
							.setSource((Intention) source);
					((DecompositionImpl) newElement)
							.setTarget((Intention) target);

					if (newElement instanceof OrDecompositionImpl) {
						nextLinkDescriptor = getLinkDescriptor(source, target,
								newElement, "orDecompose");
					} else if (newElement instanceof AndDecompositionImpl) {
						nextLinkDescriptor = getLinkDescriptor(source, target,
								newElement, "andDecompose");
					}
				} else if (newElement instanceof ContributionImpl) {
					((ContributionImpl) newElement)
							.setSource((Intention) source);
					((ContributionImpl) newElement)
							.setTarget((Intention) target);

					if (newElement instanceof MakeContributionImpl) {
						nextLinkDescriptor = getLinkDescriptor(source, target,
								newElement, "make");
					} else if (newElement instanceof SomePlusContributionImpl) {
						nextLinkDescriptor = getLinkDescriptor(source, target,
								newElement, "some+");
					} else if (newElement instanceof HelpContributionImpl) {
						nextLinkDescriptor = getLinkDescriptor(source, target,
								newElement, "help");
					} else if (newElement instanceof UnknownContributionImpl) {
						nextLinkDescriptor = getLinkDescriptor(source, target,
								newElement, "unknown");
					} else if (newElement instanceof HurtContributionImpl) {
						nextLinkDescriptor = getLinkDescriptor(source, target,
								newElement, "hurt");
					} else if (newElement instanceof SomeMinusContributionImpl) {
						nextLinkDescriptor = getLinkDescriptor(source, target,
								newElement, "some-");
					} else if (newElement instanceof BreakContributionImpl) {
						nextLinkDescriptor = getLinkDescriptor(source, target,
								newElement, "break");
					} else if (newElement instanceof AndContributionImpl) {
						nextLinkDescriptor = getLinkDescriptor(source, target,
								newElement, "and");
					} else if (newElement instanceof OrContributionImpl) {
						nextLinkDescriptor = getLinkDescriptor(source, target,
								newElement, "or");
					}
				} else if (newElement instanceof AssociationImpl) {
					((AssociationImpl) newElement)
							.setSource((Container) source);
					((AssociationImpl) newElement)
							.setTarget((Container) target);

					if (newElement instanceof IsAAssociationImpl) {
						nextLinkDescriptor = getLinkDescriptor(source, target,
								newElement, "isa");
					} else if (newElement instanceof CoversAssociationImpl) {
						nextLinkDescriptor = getLinkDescriptor(source, target,
								newElement, "covers");
					} else if (newElement instanceof IsPartOfAssociationImpl) {
						nextLinkDescriptor = getLinkDescriptor(source, target,
								newElement, "ispartof");
					} else if (newElement instanceof OccupiesAssociationImpl) {
						nextLinkDescriptor = getLinkDescriptor(source, target,
								newElement, "occupies");
					} else if (newElement instanceof PlaysAssociationImpl) {
						nextLinkDescriptor = getLinkDescriptor(source, target,
								newElement, "plays");
					} else if (newElement instanceof INSAssociationImpl) {
						nextLinkDescriptor = getLinkDescriptor(source, target,
								newElement, "ins");
					}
				} else if (newElement instanceof DependencyImpl) {
					((DependencyImpl) newElement)
							.setDependencyFrom((Dependable) target);
					((DependencyImpl) newElement)
							.setDependencyTo((Dependable) source);
					nextLinkDescriptor = getLinkDescriptor(source, target,
							newElement, "dependency");
				}

				final String linkType = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
						.getType(nextLinkDescriptor.getVisualID());

				Edge edge = ViewService
						.getInstance()
						.createEdge(
								nextLinkDescriptor.getSemanticAdapter(),
								modelEditPart.getNotationView().getDiagram(),
								linkType,
								ViewUtil.APPEND,
								true,
								edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);

				View srcView = ((IGraphicalEditPart) modelToEditPart
						.get(source)).getNotationView();
				View dstView = ((IGraphicalEditPart) modelToEditPart
						.get(target)).getNotationView();

				if (edge != null) {
					edge.setSource(srcView);
					edge.setTarget(dstView);
				}

			}

			// Put the newly created element in the request so that the
			// 'after' commands have access to it.
			getCreateRequest().setNewElement(newElement);
			return (configureStatus == null) ? CommandResult
					.newOKCommandResult(newElement) : new CommandResult(
					configureStatus, newElement);
		}

	}

	private static Node getCompartment(View node, int visualID) {
		String type = edu.toronto.cs.openome_model.diagram.part.Openome_modelVisualIDRegistry
				.getType(visualID);
		for (Iterator it = node.getChildren().iterator(); it.hasNext();) {
			View nextView = (View) it.next();
			if (nextView instanceof Node && type.equals(nextView.getType())) {
				return (Node) nextView;
			}
		}
		return null;
	}

	protected ICommand getCutCommand(IGlobalActionContext cntxt,
			IDiagramWorkbenchPart diagramPart) {

		TransactionalEditingDomain editingDomain = getEditingDomain(diagramPart);

		if (editingDomain == null) {
			return null;
		}

		CompositeTransactionalCommand cut = new CompositeTransactionalCommand(
				editingDomain, cntxt.getLabel());

		// Add a copy command - the cut must be undoable/redoable
		cut.compose(getCopyCommand(cntxt, diagramPart, true));
		

		// Fill our own clipboard
		editPartClipboard.clear();
		editPartClipboard2.clear();
		cutClipboard.clear();
		oldToNew.clear();
		newToOld.clear();
		/* Get the selected edit parts */
		Object[] objects2 = ((IStructuredSelection) cntxt.getSelection())
				.toArray();

		for (Object ep : objects2) {
			editPartClipboard.add((EditPart) ep);
			final EObject o = ((IGraphicalEditPart) ep).getNotationView()
					.getElement();
			addToCutOrCopyClipBoard(o, "cut");
		}
		
		for (EObject o : cutClipboard) {
			connectLinks(o);
		}
		
		cutDeleted = false;
		TransactionalEditingDomain copyFromDomain = ((IGraphicalEditPart) editPartClipboard
				.get(0)).getEditingDomain();
		
		CompositeTransactionalCommand delete = new CompositeTransactionalCommand(
				copyFromDomain, cntxt.getLabel());
		
		List<EditPart> links = new ArrayList<EditPart>();
		
		for (EditPart editPart : editPartClipboard) {
			/* Create the delete request */
			GroupRequest deleteReq = new GroupRequest(
					RequestConstants.REQ_DELETE);

			/* Send the request to the edit part */
			Command deleteCommand = editPart
					.getCommand(deleteReq);

			/* Add to the compound command */
			if (deleteCommand != null) {
				delete.compose(new CommandProxy(deleteCommand));
			}
			
			DestroyElementRequest req = new DestroyElementRequest(((IGraphicalEditPart)editPart).getNotationView().getElement(), true);
			DestroyElementCommand del = new DestroyElementCommand(req);
			
			delete.compose(del);
			List sourceLinks = ((ShapeNodeEditPart)editPart).getSourceConnections();
			
			for (Object link : sourceLinks) {
				if (link != null && !editPartClipboard.contains(link) && !links.contains(link)) {
					req = new DestroyElementRequest(((IGraphicalEditPart)link).getNotationView().getElement(), true);
					del = new DestroyElementCommand(req);
					delete.compose(del);
					links.add((EditPart) link);
				}
			}
			
			List targetLinks = ((ShapeNodeEditPart)editPart).getTargetConnections();
			for (Object link : targetLinks) {
				if (link != null && !editPartClipboard.contains(link) && !links.contains(link)) {
					req = new DestroyElementRequest(((IGraphicalEditPart)link).getNotationView().getElement(), true);
					del = new DestroyElementCommand(req);
					delete.compose(del);
					links.add((EditPart) link);
				}
			}
		}
		cut.compose(delete);
		
		if (cut.canExecute()) {
			return cut;
		}

		return null;
	}

	private void addToCutOrCopyClipBoard(EObject o, String label)  {
		
		EObject newObject = EcoreUtil.copy(o);
		if (label.equals("cut")) {
			cutClipboard.add(newObject);
		} else {
			editPartClipboard2.add(newObject);
		}
		newToOld.put(newObject, o);
		oldToNew.put(o, newObject);
		if (newObject instanceof ContainerImpl) {
			newObject = (ContainerImpl)newObject;
			o = (ContainerImpl)o;
			for (int i = 0; i < o.eContents().size(); i++) {
				newToOld.put(newObject.eContents().get(i), o.eContents().get(i));
				oldToNew.put(o.eContents().get(i), newObject.eContents().get(i));
			}
		}
	}
	private void connectLinks(EObject o) {
		
		if (o instanceof LinkImpl) {
			EObject oldElement = newToOld.get(o);
			EObject source = null;
			EObject target = null;
			
			if (oldElement instanceof DecompositionImpl) {
				source = ((DecompositionImpl) oldElement).getSource();
				target = ((DecompositionImpl) oldElement).getTarget();
				((DecompositionImpl) o).setSource((Intention) oldToNew.get(source));
				((DecompositionImpl) o).setTarget((Intention) oldToNew.get(target));
			} else if (oldElement instanceof ContributionImpl) {
				source = ((ContributionImpl) oldElement).getSource();
				target = ((ContributionImpl) oldElement).getTarget();
				((ContributionImpl) o).setSource((Intention) oldToNew.get(source));
				((ContributionImpl) o).setTarget((Intention) oldToNew.get(target));
			} else if (oldElement instanceof AssociationImpl) {
				source = ((AssociationImpl) oldElement).getSource();
				target = ((AssociationImpl) oldElement).getTarget();
				((AssociationImpl) o).setSource((Container) oldToNew.get(source));
				((AssociationImpl) o).setTarget((Container) oldToNew.get(target));
			} else if (oldElement instanceof DependencyImpl) {
				// the order is inverted because of the definition of a
				// Dependency relation
				target = ((DependencyImpl) oldElement).getDependencyFrom();
				source = ((DependencyImpl) oldElement).getDependencyTo();
				((DependencyImpl) o).setDependencyFrom((Dependable) oldToNew.get(source));
				((DependencyImpl) o).setDependencyTo((Dependable) oldToNew.get(target));
			}
			
		} else if (o instanceof ContainerImpl) {
			
			for (Intention intention : ((ContainerImpl)o).getIntentions()) {
				
				Intention oldIntention = (Intention) newToOld.get(intention);
				
				// Create the links within the actor
				for (Contribution contribution : oldIntention
						.getContributesTo()) {
					Contribution newCont = (Contribution) EcoreUtil.copy(contribution);
					newToOld.put(newCont, contribution);
					oldToNew.put(contribution, newCont);
					connectLinks(newCont);
				}
				for (Decomposition decomposition : oldIntention
						.getDecompositionsTo()) {
					Decomposition newCont = (Decomposition) EcoreUtil.copy(decomposition);
					newToOld.put(newCont, decomposition);
					oldToNew.put(decomposition, newCont);
					connectLinks(newCont);
				}
				for (Dependency dependency : oldIntention.getDependencyFrom()) {
					Dependency newCont = (Dependency) EcoreUtil.copy(dependency);
					newToOld.put(newCont, dependency);
					oldToNew.put(dependency, newCont);
					connectLinks(newCont);
				}
			}
		}
	}
	private TransactionalEditingDomain getEditingDomain(
			IDiagramWorkbenchPart part) {

		TransactionalEditingDomain result = null;

		IEditingDomainProvider provider = (IEditingDomainProvider) part
				.getAdapter(IEditingDomainProvider.class);

		if (provider != null) {
			EditingDomain domain = provider.getEditingDomain();

			if (domain != null && domain instanceof TransactionalEditingDomain) {
				result = (TransactionalEditingDomain) domain;
			}
		}

		return result;
	}

	protected boolean canCut(IGlobalActionContext cntxt) {
		// String actionId = cntxt.getActionId();
		// if (actionId.equals(GlobalActionId.CUT)) {
		// ICommand command = getCommand(cntxt);
		// if (command != null && command.canExecute()) {
		// return canCopy(cntxt);
		// }
		// }
		// return false;
		return true;
	}

	/**
	 * Return the link descriptor corresponding the type
	 * 
	 * @param type
	 * @return
	 */
	private static Openome_modelLinkDescriptor getLinkDescriptor(
			EObject source, EObject target, EObject newElement, String type) {

		if (type.equals("orDecompose")) {
			return new edu.toronto.cs.openome_model.diagram.part.Openome_modelLinkDescriptor(
					source,
					target,
					newElement,
					edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.OrDecomposition_4003,
					edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart.VISUAL_ID);
		} else if (type.equals("andDecompose")) {
			return new edu.toronto.cs.openome_model.diagram.part.Openome_modelLinkDescriptor(
					source,
					target,
					newElement,
					edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.AndDecomposition_4002,
					edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart.VISUAL_ID);
		} else if (type.equals("make")) {
			return new edu.toronto.cs.openome_model.diagram.part.Openome_modelLinkDescriptor(
					source,
					target,
					newElement,
					edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.MakeContribution_4007,
					edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart.VISUAL_ID);
		} else if (type.equals("some+")) {
			return new edu.toronto.cs.openome_model.diagram.part.Openome_modelLinkDescriptor(
					source,
					target,
					newElement,
					edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.SomePlusContribution_4009,
					edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart.VISUAL_ID);
		} else if (type.equals("help")) {
			return new edu.toronto.cs.openome_model.diagram.part.Openome_modelLinkDescriptor(
					source,
					target,
					newElement,
					edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.HelpContribution_4005,
					edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart.VISUAL_ID);
		} else if (type.equals("unknown")) {
			return new edu.toronto.cs.openome_model.diagram.part.Openome_modelLinkDescriptor(
					source,
					target,
					newElement,
					edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.UnknownContribution_4011,
					edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart.VISUAL_ID);
		} else if (type.equals("hurt")) {
			return new edu.toronto.cs.openome_model.diagram.part.Openome_modelLinkDescriptor(
					source,
					target,
					newElement,
					edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.HurtContribution_4006,
					edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart.VISUAL_ID);
		} else if (type.equals("some-")) {
			return new edu.toronto.cs.openome_model.diagram.part.Openome_modelLinkDescriptor(
					source,
					target,
					newElement,
					edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.SomeMinusContribution_4010,
					edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart.VISUAL_ID);
		} else if (type.equals("break")) {
			return new edu.toronto.cs.openome_model.diagram.part.Openome_modelLinkDescriptor(
					source,
					target,
					newElement,
					edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.BreakContribution_4008,
					edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart.VISUAL_ID);
		} else if (type.equals("and")) {
			return new edu.toronto.cs.openome_model.diagram.part.Openome_modelLinkDescriptor(
					source,
					target,
					newElement,
					edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.AndContribution_4012,
					edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart.VISUAL_ID);
		} else if (type.equals("or")) {
			return new edu.toronto.cs.openome_model.diagram.part.Openome_modelLinkDescriptor(
					source,
					target,
					newElement,
					edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.OrContribution_4013,
					edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart.VISUAL_ID);
		} else if (type.equals("isa")) {
			return new edu.toronto.cs.openome_model.diagram.part.Openome_modelLinkDescriptor(
					source,
					target,
					newElement,
					edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsAAssociation_4014,
					edu.toronto.cs.openome_model.diagram.edit.parts.IsAAssociationEditPart.VISUAL_ID);
		} else if (type.equals("covers")) {
			return new edu.toronto.cs.openome_model.diagram.part.Openome_modelLinkDescriptor(
					source,
					target,
					newElement,
					edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.CoversAssociation_4015,
					edu.toronto.cs.openome_model.diagram.edit.parts.CoversAssociationEditPart.VISUAL_ID);
		} else if (type.equals("ispartof")) {
			return new edu.toronto.cs.openome_model.diagram.part.Openome_modelLinkDescriptor(
					source,
					target,
					newElement,
					edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.IsPartOfAssociation_4017,
					edu.toronto.cs.openome_model.diagram.edit.parts.IsPartOfAssociationEditPart.VISUAL_ID);
		} else if (type.equals("occupies")) {
			return new edu.toronto.cs.openome_model.diagram.part.Openome_modelLinkDescriptor(
					source,
					target,
					newElement,
					edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.OccupiesAssociation_4016,
					edu.toronto.cs.openome_model.diagram.edit.parts.OccupiesAssociationEditPart.VISUAL_ID);
		} else if (type.equals("plays")) {
			return new edu.toronto.cs.openome_model.diagram.part.Openome_modelLinkDescriptor(
					source,
					target,
					newElement,
					edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.PlaysAssociation_4018,
					edu.toronto.cs.openome_model.diagram.edit.parts.PlaysAssociationEditPart.VISUAL_ID);
		} else if (type.equals("ins")) {
			return new edu.toronto.cs.openome_model.diagram.part.Openome_modelLinkDescriptor(
					source,
					target,
					newElement,
					edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.INSAssociation_4019,
					edu.toronto.cs.openome_model.diagram.edit.parts.INSAssociationEditPart.VISUAL_ID);
		} else if (type.equals("dependency")) {
			return new edu.toronto.cs.openome_model.diagram.part.Openome_modelLinkDescriptor(
					source,
					target,
					newElement,
					edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes.Dependency_4001,
					edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart.VISUAL_ID);
		}
		return null;
	}
}
package customsrc;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.ui.action.AbstractActionHandler;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.DeferredCreateConnectionViewAndElementCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;

import edu.toronto.cs.openome_model.diagram.edit.parts.AndContributionEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.AndDecompositionEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.BreakContributionEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.CoversAssociationEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.DependencyEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.HelpContributionEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.HurtContributionEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.INSAssociationEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.IsAAssociationEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.IsPartOfAssociationEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.MakeContributionEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.ModelEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.OccupiesAssociationEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.OrContributionEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.OrDecompositionEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.PlaysAssociationEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.SomeMinusContributionEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.SomePlusContributionEditPart;
import edu.toronto.cs.openome_model.diagram.edit.parts.UnknownContributionEditPart;
import edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramEditorPlugin;
import edu.toronto.cs.openome_model.diagram.part.Openome_modelDiagramUpdateCommand;
import edu.toronto.cs.openome_model.diagram.providers.Openome_modelElementTypes;
import edu.toronto.cs.openome_model.impl.AssociationImpl;
import edu.toronto.cs.openome_model.impl.ContributionImpl;
import edu.toronto.cs.openome_model.impl.DecompositionImpl;
import edu.toronto.cs.openome_model.impl.DependencyImpl;

public class SetLineTypeAction extends AbstractActionHandler {
	
	protected String ID = ""; //$NON-NLS-1$
	protected String commandName = "";
	protected String changeTo = ""; // what we want to change into
	private String imageFile = "contribution.png";

	protected SetLineTypeAction(IWorkbenchPage workbenchPage, String changeTo) {
		super(workbenchPage);
		
		this.changeTo = changeTo;
		
		// default line icon
		setImageDescriptor(Openome_modelDiagramEditorPlugin.getBundledImageDescriptor("../openome_model/icons/" + imageFile));
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
		
		for(Object connection : selection.toArray()) {
						
			// DEPENDENCY and DECOMPOSITIONS
			
			if(connection instanceof DependencyEditPart) {				
				// Straighten line so changing to Decomposition does not crash
				((DependencyEditPart) connection).straightenLine();
			} else if(connection instanceof AndDecompositionEditPart) {				
				//
			} else if(connection instanceof OrDecompositionEditPart) {				
				//
			}
			
			// CONTRIBUTIONS
			
			else if(connection instanceof AndContributionEditPart) {
				// Straighten line so changing to Decomposition does not crash
				((AndContributionEditPart) connection).straightenLine();
			} else if(connection instanceof BreakContributionEditPart) {				
				// Straighten line so changing to Decomposition does not crash
				((BreakContributionEditPart) connection).straightenLine();
			} else if (connection instanceof HelpContributionEditPart) {				
				// Straighten line so changing to Decomposition does not crash
				((HelpContributionEditPart) connection).straightenLine();
			} else if(connection instanceof HurtContributionEditPart) {				
				//
			} else if(connection instanceof MakeContributionEditPart) {				
				// Straighten line so changing to Decomposition does not crash
				((MakeContributionEditPart) connection).straightenLine();
			} else if(connection instanceof OrContributionEditPart) {				
				// Straighten line so changing to Decomposition does not crash
				((OrContributionEditPart) connection).straightenLine();
			} else if(connection instanceof SomePlusContributionEditPart) {				
				// Straighten line so changing to Decomposition does not crash
				((SomePlusContributionEditPart) connection).straightenLine();
			} else if(connection instanceof SomeMinusContributionEditPart) {				
				// Straighten line so changing to Decomposition does not crash
				((SomeMinusContributionEditPart) connection).straightenLine();
			} else if(connection instanceof UnknownContributionEditPart) {				
				// Straighten line so changing to Decomposition does not crash
				((UnknownContributionEditPart) connection).straightenLine();
			}
			
			// ASSOCIATIONS
			else if(connection instanceof CoversAssociationEditPart) {				
				// Straighten line so changing to Decomposition does not crash
				((CoversAssociationEditPart) connection).straightenLine();
			} else if(connection instanceof INSAssociationEditPart) {				
				// Straighten line so changing to Decomposition does not crash
				((INSAssociationEditPart) connection).straightenLine();
			} else if(connection instanceof IsAAssociationEditPart) {				
				// Straighten line so changing to Decomposition does not crash
				((IsAAssociationEditPart) connection).straightenLine();
			} else if(connection instanceof IsPartOfAssociationEditPart) {				
				//
			} else if(connection instanceof OccupiesAssociationEditPart) {				
				// Straighten line so changing to Decomposition does not crash
				((OccupiesAssociationEditPart) connection).straightenLine();
			} else if(connection instanceof PlaysAssociationEditPart) {				
				// Straighten line so changing to Decomposition does not crash
				((PlaysAssociationEditPart) connection).straightenLine();
			}
			
			final EObject object = ((IGraphicalEditPart)connection).getNotationView().getElement();
			ConnectionNodeEditPart part = (ConnectionNodeEditPart)connection;
			
			doTypeSwitch(part, object, progressMonitor);
		}
	}

	public void doTypeSwitch(final ConnectionNodeEditPart oldPart, final EObject object, IProgressMonitor progressMonitor) {
		DiagramCommandStack dcs = oldPart.getDiagramEditDomain().getDiagramCommandStack();
		
		ModelEditPart modelPart = (ModelEditPart)oldPart.getParent().getChildren().get(0);
				
		EObject source = null;
		EObject target = null;
		
		if(object instanceof DecompositionImpl) {
			source = ((DecompositionImpl)object).getSource();
			target = ((DecompositionImpl)object).getTarget();
		} else if(object instanceof ContributionImpl) {
			source = ((ContributionImpl)object).getSource();
			target = ((ContributionImpl)object).getTarget();
		} else if(object instanceof AssociationImpl) {
			source = ((AssociationImpl)object).getSource();
			target = ((AssociationImpl)object).getTarget();
		} else if(object instanceof DependencyImpl) {
			// the order is inverted because of the definition of a Dependency relation
			target = ((DependencyImpl)object).getDependencyFrom();
			source = ((DependencyImpl)object).getDependencyTo();
		}
		
		View sourceView = getElementView(source, modelPart);
		View targetView = getElementView(target, modelPart);
		
		// Since types are generated, this function needs to be carefully maintained,
		// in order to avoid using old types that result in NullPointer exceptions.
		
		IElementType type = null;
				
		if(changeTo.equals("Decomposition")) {
			type = Openome_modelElementTypes.AndDecomposition_4002;	
		} else if (changeTo.equals("Means-ends")) {
			type = Openome_modelElementTypes.OrDecomposition_4003;
		} else if (changeTo.equals("Dependency")) {
			type = Openome_modelElementTypes.Dependency_4001;
		} else if (changeTo.equals("Make")) {
			type = Openome_modelElementTypes.MakeContribution_4007; 	
		} else if (changeTo.equals("Some+")) {
			type = Openome_modelElementTypes.SomePlusContribution_4009;	
		} else if (changeTo.equals("Help")) {
			type = Openome_modelElementTypes.HelpContribution_4005;	
		} else if (changeTo.equals("Unknown")) {
			type = Openome_modelElementTypes.UnknownContribution_4011;
		} else if (changeTo.equals("Hurt")) {
			type = Openome_modelElementTypes.HurtContribution_4006;	
		} else if (changeTo.equals("Some-")) {
			type = Openome_modelElementTypes.SomeMinusContribution_4010;
		} else if (changeTo.equals("Break")) {
			type = Openome_modelElementTypes.BreakContribution_4008;
		} else if (changeTo.equals("AND")) {
			type = Openome_modelElementTypes.AndContribution_4012;
		} else if (changeTo.equals("OR")) {
			type = Openome_modelElementTypes.OrContribution_4013;
		} else if (changeTo.equals("ISA")) { 
			type = Openome_modelElementTypes.IsAAssociation_4014;	
		} else if (changeTo.equals("Covers")) {
			type = Openome_modelElementTypes.CoversAssociation_4015;	
		} else if (changeTo.equals("Is part of")) {
			type = Openome_modelElementTypes.IsPartOfAssociation_4017;	
		} else if (changeTo.equals("Occupies")) {
			type = Openome_modelElementTypes.OccupiesAssociation_4016;
		} else if (changeTo.equals("Plays")) {
			type = Openome_modelElementTypes.PlaysAssociation_4018;	
		} else if (changeTo.equals("INS")) {
			type = Openome_modelElementTypes.INSAssociation_4019;
		}
		
		CompoundCommand command = new CompoundCommand("Change Link Type");
		
		// Create a new link
		
		CreateConnectionViewAndElementRequest requestLink = new CreateConnectionViewAndElementRequest(
			type, ((IHintedType)type).getSemanticHint(), modelPart.getDiagramPreferencesHint()
		);

		ICommand commandLink = new DeferredCreateConnectionViewAndElementCommand(
			requestLink, (IAdaptable)(new EObjectAdapter(sourceView)), (IAdaptable)(new EObjectAdapter(targetView)), oldPart.getViewer()
		);
		
		if(commandLink.canExecute()) {
			command.add(new ICommandProxy(commandLink));
		} else {
			System.err.println("commandLink problem!");
		}
		
		// Destroy the old link
		
		DestroyElementCommand destroy = new DestroyElementCommand(
			new DestroyElementRequest(null, object, false)
		);
		
		if(destroy.canExecute()) {
			command.add(new ICommandProxy(destroy));
		} else {
			System.err.println("destroy problem!");
		}
		
		// Delete the old link's view
		
		DeleteCommand delete = new DeleteCommand(oldPart.getNotationView());
		
		if(delete.canExecute()) {
			command.add(new ICommandProxy(delete));
		} else {
			System.err.println("delete problem!");
		}
		
		if(command.canExecute()) {
			dcs.execute(command, progressMonitor);
			dcs.flush();
		} else {
			System.err.println("SetLineType problem!");
		}
		
		// refresh diagram to reflect changes
		refresh();
	}
	
	/*
	 * Get an EObject's View.
	 */
	private View getElementView(EObject e, GraphicalEditPart p)
	{
		View v = p.getNotationView();
		
		if(v.getElement() == e) {
			return v;
		}
		
		for(Object o : p.getChildren()) {
			v = getElementView(e, (GraphicalEditPart)o);
			
			if(v != null) {
				return v;
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

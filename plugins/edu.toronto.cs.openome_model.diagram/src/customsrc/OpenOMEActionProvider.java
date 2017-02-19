package customsrc;

import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.AbstractContributionItemProvider;
import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.actions.ActionGroup;


public class OpenOMEActionProvider extends AbstractContributionItemProvider{

	
	protected IAction createAction(String actionId,
            IWorkbenchPartDescriptor partDescriptor) {

        IWorkbenchPage workbenchPage = partDescriptor.getPartPage();
        
        if (actionId.equals("SetNoneLabelAction")) {
        	return new SetNoneLabelAction(workbenchPage);
        } else if (actionId.equals("SetSatisfiedLabelAction")) {
        	return new SetSatisfiedLabelAction(workbenchPage);
        } else if (actionId.equals("SetWeaklySatisfiedLabelAction")) {
        	return new SetWeaklySatisfiedLabelAction(workbenchPage);
        } else if (actionId.equals("SetConflictLabelAction")) {
        	return new SetConflictLabelAction(workbenchPage);
        } else if (actionId.equals("SetWeaklyDeniedLabelAction")) {
        	return new SetWeaklyDeniedLabelAction(workbenchPage);
        } else if (actionId.equals("SetDeniedLabelAction")) {
        	return new SetDeniedLabelAction(workbenchPage);
        } else if (actionId.equals("SetUnknownLabelAction")) {
        	return new SetUnknownLabelAction(workbenchPage);
        	
        // Enable right click popmenu for straightening lines
        } else if (actionId.equals("StraightenLinesAction")) {
        	return new StraightenLinesAction(workbenchPage);
        	
        // Enable right click popmenu for changing line types
        } else if (actionId.equals("ChangeToDependencyAction")) {
        	return new ChangeToDependencyAction(workbenchPage);
        } else if (actionId.equals("ChangeToAndDecompositionAction")) {
        	return new ChangeToAndDecompositionAction(workbenchPage);
        } else if (actionId.equals("ChangeToOrDecompositionAction")) {
        	return new ChangeToOrDecompositionAction(workbenchPage);
        } else if (actionId.equals("ChangeToMakeContributionAction")) {
        	return new ChangeToMakeContributionAction(workbenchPage);
        } else if (actionId.equals("ChangeToSomePlusContributionAction")) {
        	return new ChangeToSomePlusContributionAction(workbenchPage);
        } else if (actionId.equals("ChangeToHelpContributionAction")) {
        	return new ChangeToHelpContributionAction(workbenchPage);
        } else if (actionId.equals("ChangeToUnknownContributionAction")) {
        	return new ChangeToUnknownContributionAction(workbenchPage);
        } else if (actionId.equals("ChangeToHurtContributionAction")) {
        	return new ChangeToHurtContributionAction(workbenchPage);
        } else if (actionId.equals("ChangeToSomeMinusContributionAction")) {
        	return new ChangeToSomeMinusContributionAction(workbenchPage);
        } else if (actionId.equals("ChangeToBreakContributionAction")) {
        	return new ChangeToBreakContributionAction(workbenchPage);
        } else if (actionId.equals("ChangeToAndContributionAction")) {
        	return new ChangeToAndContributionAction(workbenchPage);
        } else if (actionId.equals("ChangeToOrContributionAction")) {
        	return new ChangeToOrContributionAction(workbenchPage);
        } else if (actionId.equals("ChangeToAndContributionAction")) {
        	return new ChangeToAndContributionAction(workbenchPage);
        } else if (actionId.equals("ChangeToIsAAssociationAction")) {
        	return new ChangeToIsAAssociationAction(workbenchPage);
        } else if (actionId.equals("ChangeToCoversAssociationAction")) {
        	return new ChangeToCoversAssociationAction(workbenchPage);
        } else if (actionId.equals("ChangeToIsPartOfAssociationAction")) {
        	return new ChangeToIsPartOfAssociationAction(workbenchPage);
        } else if (actionId.equals("ChangeToOccupiesAssociationAction")) {
        	return new ChangeToOccupiesAssociationAction(workbenchPage);
        } else if (actionId.equals("ChangeToPlaysAssociationAction")) {
        	return new ChangeToPlaysAssociationAction(workbenchPage);
        } else if (actionId.equals("ChangeToINSAssociationAction")) {
        	return new ChangeToINSAssociationAction(workbenchPage);
        	// Change intentions
        } else if (actionId.equals("ChangeToHardgoalAction")) {
        	return new ChangeToHardgoalAction(workbenchPage);
        } else if (actionId.equals("ChangeToSoftgoalAction")) {
        	return new ChangeToSoftgoalAction(workbenchPage);
        } else if (actionId.equals("ChangeToTaskAction")) {
        	return new ChangeToTaskAction(workbenchPage);
        } else if (actionId.equals("ChangeToResourceAction")) {
        	return new ChangeToResourceAction(workbenchPage);
        } else if (actionId.equals("ChangeToActorAction")) {
        	return new ChangeToActorAction(workbenchPage);
        } else if (actionId.equals("ChangeToAgentAction")) {
        	return new ChangeToAgentAction(workbenchPage);
        } else if (actionId.equals("ChangeToRoleAction")) {
        	return new ChangeToRoleAction(workbenchPage);
        } else if (actionId.equals("ChangeToPositionAction")) {
        	return new ChangeToPositionAction(workbenchPage);
        } else if (actionId.equals("HighlightAsLeafAction")) {
        	return new HighlightAsLeafAction(workbenchPage);
        } else if (actionId.equals("HighlightAsRootAction")) {
        	return new HighlightAsRootAction(workbenchPage);
        } else if (actionId.equals("UnHighlightAction")) {
            	return new UnHighlightAction(workbenchPage);
        } else {
        	return null;        	
        }
	}
	
	protected IMenuManager createMenuManager(String menuId, IWorkbenchPartDescriptor partDescriptor) {
		
		IWorkbenchPage workbenchPage = partDescriptor.getPartPage();
		
		if (menuId.equals("DecompositionLineTypeMenu")){
			IMenuManager menu =  new ChangeTypeMenuManager();
			
			// Adding the options to change to other decomposition types
			menu.add(createAction("ChangeToDependencyAction", partDescriptor));
			menu.add(createAction("ChangeToAndDecompositionAction", partDescriptor));
			menu.add(createAction("ChangeToOrDecompositionAction", partDescriptor));
			
			// adding a sub-submenu to change to contributions
			IMenuManager submenu =  new ChangeTypeMenuManager("Contributions");
			submenu.add(createAction("ChangeToMakeContributionAction", partDescriptor));
			submenu.add(createAction("ChangeToSomePlusContributionAction", partDescriptor));
			submenu.add(createAction("ChangeToHelpContributionAction", partDescriptor));
			submenu.add(createAction("ChangeToUnknownContributionAction", partDescriptor));
			submenu.add(createAction("ChangeToHurtContributionAction", partDescriptor));
			submenu.add(createAction("ChangeToSomeMinusContributionAction", partDescriptor));
			submenu.add(createAction("ChangeToBreakContributionAction", partDescriptor));
			submenu.add(createAction("ChangeToAndContributionAction", partDescriptor));
			submenu.add(createAction("ChangeToOrContributionAction", partDescriptor));
			
			menu.add(submenu);
			
			return menu;
			
		} else if (menuId.equals("ContributionLineTypeMenu")){
			IMenuManager menu =  new ChangeTypeMenuManager();
			
			// Adding the options to change to other decomposition types
			menu.add(createAction("ChangeToMakeContributionAction", partDescriptor));
			menu.add(createAction("ChangeToSomePlusContributionAction", partDescriptor));
			menu.add(createAction("ChangeToHelpContributionAction", partDescriptor));
			menu.add(createAction("ChangeToUnknownContributionAction", partDescriptor));
			menu.add(createAction("ChangeToHurtContributionAction", partDescriptor));
			menu.add(createAction("ChangeToSomeMinusContributionAction", partDescriptor));
			menu.add(createAction("ChangeToBreakContributionAction", partDescriptor));
			menu.add(createAction("ChangeToAndContributionAction", partDescriptor));
			menu.add(createAction("ChangeToOrContributionAction", partDescriptor));
			
			// adding a sub-submenu to change to decompositions
			IMenuManager submenu =  new ChangeTypeMenuManager("Decompositions");
			submenu.add(createAction("ChangeToDependencyAction", partDescriptor));
			submenu.add(createAction("ChangeToAndDecompositionAction", partDescriptor));
			submenu.add(createAction("ChangeToOrDecompositionAction", partDescriptor));
			
			menu.add(submenu);
			
			return menu;
			
		} else if (menuId.equals("AssociationLineTypeMenu")){
			IMenuManager menu =  new ChangeTypeMenuManager();
			menu.add(createAction("ChangeToIsAAssociationAction", partDescriptor));
			menu.add(createAction("ChangeToCoversAssociationAction", partDescriptor));
			menu.add(createAction("ChangeToIsPartOfAssociationAction", partDescriptor));
			menu.add(createAction("ChangeToOccupiesAssociationAction", partDescriptor));
			menu.add(createAction("ChangeToPlaysAssociationAction", partDescriptor));
			menu.add(createAction("ChangeToINSAssociationAction", partDescriptor));
			return menu;
		}else if (menuId.equals("IntentionGoalLabelTypeMenu")){
			IMenuManager menu =  new ChangeTypeMenuManager("Set Qualitative Goal Label");
			menu.add(createAction("SetNoneLabelAction", partDescriptor));
			menu.add(createAction("SetSatisfiedLabelAction", partDescriptor));
			menu.add(createAction("SetWeaklySatisfiedLabelAction", partDescriptor));
			menu.add(createAction("SetConflictLabelAction", partDescriptor));
			menu.add(createAction("SetWeaklyDeniedLabelAction", partDescriptor));
			menu.add(createAction("SetDeniedLabelAction", partDescriptor));
			menu.add(createAction("SetUnknownLabelAction", partDescriptor));
			
			return menu;
		} else if (menuId.equals("IntentionTypeMenu")){
			IMenuManager menu =  new ChangeTypeMenuManager();
			menu.add(createAction("ChangeToHardgoalAction", partDescriptor));
			menu.add(createAction("ChangeToSoftgoalAction", partDescriptor));
			menu.add(createAction("ChangeToTaskAction", partDescriptor));
			menu.add(createAction("ChangeToResourceAction", partDescriptor));

			return menu;
		} else if (menuId.equals("HighlightAsMenu")){
			IMenuManager menu =  new ChangeTypeMenuManager("Highlight");
			menu.add(createAction("HighlightAsLeafAction", partDescriptor));
			menu.add(createAction("HighlightAsRootAction", partDescriptor));
			menu.add(createAction("UnHighlightAction", partDescriptor));

			return menu;
		} else if (menuId.equals("ActorTypeMenu")){
			IMenuManager menu =  new ChangeTypeMenuManager();
			menu.add(createAction("ChangeToActorAction", partDescriptor));
			menu.add(createAction("ChangeToAgentAction", partDescriptor));
			menu.add(createAction("ChangeToRoleAction", partDescriptor));
			menu.add(createAction("ChangeToPositionAction", partDescriptor));

			return menu;
		} 
		return null;
	}

}

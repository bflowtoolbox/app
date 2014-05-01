package org.bflow.toolbox.hive.actions.providers;

import org.bflow.toolbox.hive.actions.BestSizeAction;
import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.AbstractContributionItemProvider;
import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;
import org.eclipse.jface.action.IAction;

/**
 * Implements {@link AbstractContributionItemProvider} to provide common actions to all 
 * diagram editors.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 16/08/13
 */
public class DiagramContributionProvider extends AbstractContributionItemProvider {
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.AbstractContributionItemProvider#createAction(java.lang.String, org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor)
	 */
	@Override
	protected IAction createAction(String actionId, IWorkbenchPartDescriptor partDescriptor) {
		
		// Adding action for extended auto sizing 
		if(actionId.endsWith(BestSizeAction.Id)) {
			return new BestSizeAction(partDescriptor.getPartPage());
		}
		
		return super.createAction(actionId, partDescriptor);
	}

}

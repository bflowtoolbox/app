package org.bflow.toolbox.bpmn.interop;

import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.AbstractContributionItemProvider;
import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;
import org.eclipse.jface.action.IAction;

/**
 * Implements {@link IContributionItemProvider} to contribute BPMN interop related actions 
 * to the diagram menus and toolbars.
 *  
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2018-10-07
 *
 */
public class BpmnInteropContributionItemProvider extends AbstractContributionItemProvider {
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.AbstractContributionItemProvider#createAction(java.lang.String, org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor)
	 */
	@Override
	protected IAction createAction(String actionId, IWorkbenchPartDescriptor partDescriptor) {
		if (actionId.equals(ConvertToBpmnDiagramAction.Id))
			return new ConvertToBpmnDiagramAction(partDescriptor);
		
		return super.createAction(actionId, partDescriptor);
	}	
}

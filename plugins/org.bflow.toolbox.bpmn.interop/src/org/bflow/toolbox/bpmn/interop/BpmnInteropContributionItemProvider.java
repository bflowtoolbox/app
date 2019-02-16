package org.bflow.toolbox.bpmn.interop;

import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.AbstractContributionItemProvider;
import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;

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

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.AbstractContributionItemProvider#createMenuManager(java.lang.String, org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor)
	 */
	@Override
	protected IMenuManager createMenuManager(String menuId, IWorkbenchPartDescriptor partDescriptor) {		
		if (menuId.equals("conversionMenu")) { //$NON-NLS-1$
			MenuManager man =  new MenuManager(Messages.BpmnInteropContributionItemProvider_MenuName, menuId);
			ImageDescriptor id = BpmnInteropPlugin.getDefault().imageDescriptorFromPlugin("/icons/convert16.png"); //$NON-NLS-1$
			man.setImageDescriptor(id);
			return man;
		}		
		
		return super.createMenuManager(menuId, partDescriptor);
	}	
}

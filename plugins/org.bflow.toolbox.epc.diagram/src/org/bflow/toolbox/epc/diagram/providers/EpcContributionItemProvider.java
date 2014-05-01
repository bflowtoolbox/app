package org.bflow.toolbox.epc.diagram.providers;

import org.bflow.toolbox.epc.extensions.actions.SelectAllShapesWithTextAction;
import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.AbstractContributionItemProvider;
import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.printing.actions.PrintPreviewAction;
import org.eclipse.gmf.runtime.diagram.ui.printing.render.actions.EnhancedPrintActionHelper;
import org.eclipse.gmf.runtime.diagram.ui.printing.render.actions.RenderedPrintPreviewAction;
import org.eclipse.jface.action.IAction;

/**
 * @generated
 * @version 21/07/13 by Arian Storch
 */
public class EpcContributionItemProvider extends
		AbstractContributionItemProvider {

	/**
	 * @generated
	 */
	protected IAction createAction(String actionId,
			IWorkbenchPartDescriptor partDescriptor) {
		if (actionId.equals(PrintPreviewAction.ID)) {
			return new RenderedPrintPreviewAction(
					new EnhancedPrintActionHelper());
		}
		
		// Adding action for Shape selection
		if(actionId.equals(SelectAllShapesWithTextAction.Id)) {
			return new SelectAllShapesWithTextAction(partDescriptor.getPartPage());
		}
		
		return super.createAction(actionId, partDescriptor);
	}
}

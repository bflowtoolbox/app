package org.bflow.toolbox.epc.diagram.actions;

import org.bflow.toolbox.hive.attributes.AttributeViewPart;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;

/**
 * 
 * @author Arian Storch
 * @since 15/07/11
 */
public class ShowAttributeViewAction implements IObjectActionDelegate {
	
	private IWorkbenchPart part;

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		part = targetPart;
	}

	@Override
	public void run(IAction action) {
		try {
			part.getSite().getPage().showView(AttributeViewPart.VIEW_ID);
//			part.getSite().getPage().showView("org.bflow.toolbox.attributes.view");
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}

}

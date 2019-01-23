package org.bflow.toolbox.epc.diagram.actions;

import org.bflow.toolbox.epc.Function;
import org.bflow.toolbox.epc.ProcessInterface;
import org.bflow.toolbox.epc.diagram.edit.parts.FunctionEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ProcessInterfaceEditPart;
import org.bflow.toolbox.extensions.edit.parts.BflowNodeEditPart;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Implements the IObjectActionDelegate to handle the remove subdiagram request.
 * 
 * @author Arian Storch
 * @since 16/03/10
 * @version 05/07/11
 * 
 */
public class EpcRemoveSubdiagramAction implements IObjectActionDelegate {
	private Function function;
	private ProcessInterface pInterface;

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {

	}

	@Override
	public void run(IAction action) {
		if (function != null) {
			function.getSubdiagram().clear();
		}

		if (pInterface != null) {
			pInterface.setSubdiagram(null);
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		action.setEnabled(false);
		function = null;
		pInterface = null;
		
		IStructuredSelection strSel = (IStructuredSelection) selection;

		BflowNodeEditPart part = (BflowNodeEditPart) strSel.getFirstElement();

		if (part instanceof FunctionEditPart) {
			function = (Function) ((FunctionEditPart) part)
					.resolveSemanticElement();
			
			if(!function.getSubdiagram().isEmpty())
				action.setEnabled(true);

			return;
		}

		if (part instanceof ProcessInterfaceEditPart) {
			pInterface = (ProcessInterface) ((ProcessInterfaceEditPart) part)
					.resolveSemanticElement();
			
			if(pInterface.getSubdiagram() != null)
				action.setEnabled(true);

			return;
		}
	}

}

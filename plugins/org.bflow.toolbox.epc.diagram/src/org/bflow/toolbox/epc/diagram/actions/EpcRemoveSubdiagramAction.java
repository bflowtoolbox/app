package org.bflow.toolbox.epc.diagram.actions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bflow.toolbox.epc.Function;
import org.bflow.toolbox.epc.ProcessInterface;
import org.bflow.toolbox.epc.diagram.edit.parts.FunctionEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ProcessInterfaceEditPart;
import org.bflow.toolbox.extensions.BflowDiagramElementEditUtil;
import org.bflow.toolbox.extensions.edit.parts.BflowNodeEditPart;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Implements the IObjectActionDelegate to handle the remove subdiagram request.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2010-03-16
 * @version 2011-07-05 ?
 * 			2019-01-23 AST Fixed error when modifying element without transaction
 * 
 */
public class EpcRemoveSubdiagramAction implements IObjectActionDelegate {
	private Log _log = LogFactory.getLog(EpcRemoveSubdiagramAction.class);
	private Function _function;
	private ProcessInterface _interface;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// Nothing to do here			
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		try {
			BflowDiagramElementEditUtil.modifyWithTransaction(_function, null, (e, v) -> e.getSubdiagram().clear());
			BflowDiagramElementEditUtil.modifyWithTransaction(_interface, null, (e, v) -> e.setSubdiagram(null)); 
		} catch (Exception ex) {
			_log.error("Error on modifying element", ex);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		action.setEnabled(false);
		_function = null;
		_interface = null;
		
		IStructuredSelection strSel = (IStructuredSelection) selection;

		BflowNodeEditPart part = (BflowNodeEditPart) strSel.getFirstElement();

		if (part instanceof FunctionEditPart) {
			_function = (Function) ((FunctionEditPart) part).resolveSemanticElement();
			
			if(!_function.getSubdiagram().isEmpty())
				action.setEnabled(true);

			return;
		}

		if (part instanceof ProcessInterfaceEditPart) {
			_interface = (ProcessInterface) ((ProcessInterfaceEditPart) part).resolveSemanticElement();
			
			if(_interface.getSubdiagram() != null)
				action.setEnabled(true);

			return;
		}
	}

}

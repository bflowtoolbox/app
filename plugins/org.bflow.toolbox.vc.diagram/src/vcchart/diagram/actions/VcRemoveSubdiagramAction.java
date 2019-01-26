package vcchart.diagram.actions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bflow.toolbox.extensions.BflowDiagramElementEditUtil;
import org.bflow.toolbox.extensions.edit.parts.BflowNodeEditPart;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import vcchart.Activity1;
import vcchart.Activity2;
import vcchart.diagram.edit.parts.Activity1EditPart;
import vcchart.diagram.edit.parts.Activity2EditPart;

/**
 * Action for removing a linked EPC.
 * 
 * @author Markus Schnädelbach, Arian Storch<arian.storch@bflow.org>
 * @version 2019-01-26 AST Modify element with transaction
 *
 */
public class VcRemoveSubdiagramAction implements IObjectActionDelegate {
	private Log _log = LogFactory.getLog(VcRemoveSubdiagramAction.class);
	private Activity1 _activity1;
	private Activity2 _activity2;
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {		
		try {
			BflowDiagramElementEditUtil.modifyWithTransaction(_activity1, null, (e, v) -> e.setSubdiagram(null));
			BflowDiagramElementEditUtil.modifyWithTransaction(_activity2, null, (e, v) -> e.setSubdiagram(null)); 
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
		_activity1 = null;
		_activity2 = null;
		
		IStructuredSelection strSel = (IStructuredSelection) selection;

		BflowNodeEditPart part = (BflowNodeEditPart) strSel.getFirstElement();

		if (part instanceof Activity1EditPart) {
			_activity1 = (Activity1) ((Activity1EditPart) part).resolveSemanticElement();
			if (_activity1.getSubdiagram() != null) {
				action.setEnabled(true);
			}
			
		} else if(part instanceof Activity2EditPart) {
			_activity2 = (Activity2) ((Activity2EditPart) part).resolveSemanticElement();
			if (_activity2.getSubdiagram() != null) {
				action.setEnabled(true);
			}
			
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// Nothing to do here		
	}	
}

package vcchart.diagram.actions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bflow.toolbox.epc.diagram.part.EpcCreationWizard;
import org.bflow.toolbox.extensions.BflowDiagramElementEditUtil;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;

import vcchart.Activity1;
import vcchart.Activity2;
import vcchart.diagram.edit.parts.Activity1EditPart;
import vcchart.diagram.edit.parts.Activity2EditPart;

/**
 * Action for creating and linking a new EPC to a VC-Activity.
 * 
 * @author Markus Schnädelbach, Arian Storch<arian.storch@bflow.org>
 * @version 2019-01-23 AST Fixed NPE when the dialog has been cancelled
 *
 */
public class VcCreateSubdiagramAction implements IObjectActionDelegate {	
	private Log _log = LogFactory.getLog(VcCreateSubdiagramAction.class);
	
	private Shell _shell;
	private IWorkbench _workbench;
	private Activity1 _activity1;
	private Activity2 _activity2;	
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		_shell = targetPart.getSite().getShell();
		_workbench = targetPart.getSite().getWorkbenchWindow().getWorkbench();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		EpcCreationWizard wizard = new EpcCreationWizard();
		wizard.init(_workbench, StructuredSelection.EMPTY);
		WizardDialog wizardDialog = new WizardDialog(_shell, wizard);
		
		if (wizardDialog.open() == WizardDialog.CANCEL) return;
			
		String pathName = wizard.getDiagram().getURI().toPlatformString(true);
		
		try {
			BflowDiagramElementEditUtil.modifyWithTransaction(_activity1, pathName, (e, v) -> e.setSubdiagram(v));
			BflowDiagramElementEditUtil.modifyWithTransaction(_activity2, pathName, (e, v) -> e.setSubdiagram(v));
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
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1)
				if (structuredSelection.getFirstElement() instanceof Activity1EditPart) {
					Activity1EditPart a1EP = (Activity1EditPart) structuredSelection.getFirstElement();
					_activity1 = (Activity1) a1EP.getPrimaryView().getElement();
				} else if (structuredSelection.getFirstElement() instanceof Activity2EditPart) {
					Activity2EditPart a2EP = (Activity2EditPart) structuredSelection.getFirstElement();
					_activity2 = (Activity2) a2EP.getPrimaryView().getElement();
				}	
		}
	}	
}

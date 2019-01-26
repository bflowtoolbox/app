package vcchart.diagram.actions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bflow.toolbox.epc.diagram.part.EpcElementChooserDialog;
import org.bflow.toolbox.extensions.BflowDiagramElementEditUtil;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import vcchart.Activity1;
import vcchart.Activity2;
import vcchart.diagram.edit.parts.Activity1EditPart;
import vcchart.diagram.edit.parts.Activity2EditPart;

/**
 * Action for linking an existing EPC to a VC-Activity.
 * 
 * @author Markus Schnädelbach, Arian Storch<arian.storch@bflow.org>
 * @version 2019-01-26 AST Modify element within transaction
 *
 */
public class VcInsertSubdiagramAction implements IObjectActionDelegate {
	private Log _log = LogFactory.getLog(VcInsertSubdiagramAction.class);
	private Activity1 _activity1;
	private Activity2 _activity2;
	private Shell _shell;
	private ShapeNodeEditPart _selectedActivity;
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		final View view = (View) _selectedActivity.getModel();
		EpcElementChooserDialog elementChooser = new EpcElementChooserDialog(_shell, view);
		int result = elementChooser.open();
		if (result != Window.OK) return;
			
		URI selectedModelElementURI = elementChooser.getSelectedModelElementURI();
		String path = selectedModelElementURI.toPlatformString(true);

		try {
			BflowDiagramElementEditUtil.modifyWithTransaction(_activity1, path, (e, v) -> e.setSubdiagram(v));
			BflowDiagramElementEditUtil.modifyWithTransaction(_activity2, path, (e, v) -> e.setSubdiagram(v));
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
		_selectedActivity = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1)
				if (structuredSelection.getFirstElement() instanceof Activity1EditPart) {
					_selectedActivity = (Activity1EditPart) structuredSelection.getFirstElement();
					_activity1 = (Activity1)_selectedActivity.getPrimaryView().getElement();
				} else if (structuredSelection.getFirstElement() instanceof Activity2EditPart) {
					_selectedActivity = (Activity2EditPart) structuredSelection.getFirstElement();		
					_activity2 = (Activity2)_selectedActivity.getPrimaryView().getElement();
				}	
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		_shell = targetPart.getSite().getShell();
	}

}

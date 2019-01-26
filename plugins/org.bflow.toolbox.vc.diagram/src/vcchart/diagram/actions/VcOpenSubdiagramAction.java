package vcchart.diagram.actions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bflow.toolbox.extensions.edit.parts.BflowNodeEditPart;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;

import vcchart.Activity1;
import vcchart.Activity2;
import vcchart.diagram.edit.parts.Activity1EditPart;
import vcchart.diagram.edit.parts.Activity2EditPart;

/**
 * Action for opening a linked EPC file.
 * 
 * @author Markus Schnädelbach, Arian Storch<arian.storch@bflow.org>
 * @version 2019-01-26 AST Added logger
 *
 */
public class VcOpenSubdiagramAction implements IObjectActionDelegate   {
	private Log _log = LogFactory.getLog(VcOpenSubdiagramAction.class);
	private String _subdiagram;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		String sub = _subdiagram;

		if (sub != null && !sub.isEmpty()) {
			URI fileURI = URI.createPlatformResourceURI(sub, true);

			Resource res = new GMFResource(fileURI);

			try {
				org.bflow.toolbox.epc.diagram.part.EpcDiagramEditorUtil.openDiagram(res);
			} catch (PartInitException ex) {
				_log.error("Error on opening diagram", ex);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		action.setEnabled(false);
		_subdiagram = null;
		
		IStructuredSelection strSel = (IStructuredSelection) selection;

		BflowNodeEditPart part = (BflowNodeEditPart) strSel.getFirstElement();

		if (part instanceof Activity1EditPart) {
			Activity1 a1 = (Activity1) ((Activity1EditPart) part).resolveSemanticElement();
			
			if(a1.getSubdiagram() != null) {
				_subdiagram = a1.getSubdiagram();
				action.setEnabled(true);
			}

			return;
		}
		
		if (part instanceof Activity2EditPart) {
			Activity2 a2 = (Activity2) ((Activity2EditPart) part).resolveSemanticElement();
			
			if(a2.getSubdiagram() != null) {
				_subdiagram = a2.getSubdiagram();
				action.setEnabled(true);
			}

			return;
		}		
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// Nothing to do
	}
}

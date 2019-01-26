package org.bflow.toolbox.epc.diagram.actions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bflow.toolbox.epc.Function;
import org.bflow.toolbox.epc.ProcessInterface;
import org.bflow.toolbox.epc.diagram.edit.parts.FunctionEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ProcessInterfaceEditPart;
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

/**
 * Implements the IObjectActionDelegate to handle request for opening a
 * partitioned diagram.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2010-03-15
 * @version 2011-07-05 
 * 			2019-01-26 Added logging
 * 
 */
public class EpcOpenSubdiagramAction implements IObjectActionDelegate {
	private Log _log = LogFactory.getLog(EpcOpenSubdiagramAction.class);
	private String subdiagram;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// Nothing to do 
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {

		String sub = subdiagram;

		if (sub != null && !sub.isEmpty()) {
			URI fileURI = URI.createPlatformResourceURI(sub, true);

			Resource res = new GMFResource(fileURI);

			try {
				org.bflow.toolbox.epc.diagram.part.EpcDiagramEditorUtil.openDiagram(res);
			} catch (PartInitException ex) {
				_log.error("Error on opening linked diagram", ex);
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
		subdiagram = null;
		
		IStructuredSelection strSel = (IStructuredSelection) selection;

		BflowNodeEditPart part = (BflowNodeEditPart) strSel.getFirstElement();

		if (part instanceof FunctionEditPart) {
			Function f = (Function) ((FunctionEditPart) part).resolveSemanticElement();
			
			if(!f.getSubdiagram().isEmpty()) {
				subdiagram = f.getSubdiagram().get(0);
				action.setEnabled(true);
			}

			return;
		}

		if (part instanceof ProcessInterfaceEditPart) {
			ProcessInterface p = (ProcessInterface) ((ProcessInterfaceEditPart) part)
					.resolveSemanticElement();
			subdiagram = p.getSubdiagram();
			
			if (subdiagram != null)
				action.setEnabled(true);

			return;
		}
	}

}

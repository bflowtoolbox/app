package oepc.diagram.actions;

import org.bflow.toolbox.extensions.actions.AbstractOpenDiagramLinkAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import oepc.BusinessMethod;
import oepc.diagram.edit.parts.BusinessMethodEditPart;

/**
 * Action for opening a linked diagram file.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-01-30
 *
 */
public class OepcOpenSubdiagramAction extends AbstractOpenDiagramLinkAction {
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#getSelectionData(org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	protected String getSelectionData(ISelection selection) {
		if (!(selection instanceof IStructuredSelection)) return null;
		
		Object selObj = ((IStructuredSelection) selection).getFirstElement();
		if (!(selObj instanceof BusinessMethodEditPart)) return null;
		
		BusinessMethod bm = (BusinessMethod) ((BusinessMethodEditPart) selObj).resolveSemanticElement();
		return bm.getSubdiagram();
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#isEnabled(java.lang.Object)
	 */
	@Override
	protected boolean isEnabled(String selectionData) {
		return selectionData != null;
	}

}

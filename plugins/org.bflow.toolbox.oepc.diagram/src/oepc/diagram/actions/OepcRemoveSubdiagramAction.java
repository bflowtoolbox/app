package oepc.diagram.actions;

import org.bflow.toolbox.extensions.BflowDiagramElementEditUtil;
import org.bflow.toolbox.extensions.actions.AbstractRemoveDiagramLinkAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import oepc.BusinessMethod;
import oepc.diagram.edit.parts.BusinessMethodEditPart;

/**
 * Action for removing a linked diagram from a OEPC business method.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-01-30
 * 
 */
public class OepcRemoveSubdiagramAction extends AbstractRemoveDiagramLinkAction<BusinessMethod> {
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#getSelectionData(org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	protected BusinessMethod getSelectionData(ISelection selection) {
		if (!(selection instanceof IStructuredSelection)) return null;
		
		Object selObj = ((IStructuredSelection) selection).getFirstElement();
		if (!(selObj instanceof BusinessMethodEditPart)) return null;
		
		BusinessMethod bm = (BusinessMethod) ((BusinessMethodEditPart) selObj).resolveSemanticElement();
		return bm;
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#isEnabled(java.lang.Object)
	 */
	@Override
	protected boolean isEnabled(BusinessMethod selectionData) {
		return selectionData != null && selectionData.getSubdiagram() != null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#performModification(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected void performModification(BusinessMethod selectionData, Void modificationValue) throws Exception {
		BflowDiagramElementEditUtil.modifyWithTransaction(selectionData, null, (e, v) -> e.setSubdiagram(null));		
	}
}

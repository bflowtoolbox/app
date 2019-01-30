package oepc.diagram.actions;

import org.bflow.toolbox.extensions.BflowDiagramElementEditUtil;
import org.bflow.toolbox.extensions.actions.AbstractInsertDiagramLinkAction;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import oepc.BusinessMethod;
import oepc.diagram.edit.parts.BusinessMethodEditPart;
import oepc.diagram.part.OepcDiagramEditorPlugin;

/**
 * Action for linking existing diagrams with an OEPC business object method.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-01-30
 *
 */
public class OepcInsertSubdiagramAction extends AbstractInsertDiagramLinkAction<BusinessMethodEditPart> {
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#getSelectionData(org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	protected BusinessMethodEditPart getSelectionData(ISelection selection) {
		if (!(selection instanceof IStructuredSelection)) return null;
		
		IStructuredSelection strSel = (IStructuredSelection) selection;
		Object selObj = strSel.getFirstElement();
		if (!(selObj instanceof BusinessMethodEditPart)) return null;
		
		return (BusinessMethodEditPart) selObj;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#isEnabled(java.lang.Object)
	 */
	@Override
	protected boolean isEnabled(BusinessMethodEditPart selectionData) {
		return selectionData != null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractInsertDiagramLinkAction#getAdapterFactory()
	 */
	@Override
	protected AdapterFactory getAdapterFactory() {
		return OepcDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory();
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractInsertDiagramLinkAction#getViewForSelection(java.lang.Object)
	 */
	@Override
	protected View getViewForSelection(BusinessMethodEditPart selectionData) {
		return selectionData.getPrimaryView();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractInsertDiagramLinkAction#getFileExtensions()
	 */
	@Override
	protected String[] getFileExtensions() {
		return new String[] { "epc", "oepc", "bpmn" };
	}	

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#performModification(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected void performModification(BusinessMethodEditPart selectionData, String modificationValue) throws Exception {
		BusinessMethod bm = (BusinessMethod) selectionData.resolveSemanticElement();
		BflowDiagramElementEditUtil.modifyWithTransaction(bm, modificationValue, (e, v) -> e.setSubdiagram(v));
	}

}

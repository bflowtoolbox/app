package oepc.diagram.actions;

import org.bflow.toolbox.extensions.BflowDiagramElementEditUtil;
import org.bflow.toolbox.extensions.actions.AbstractCreateDiagramLinkAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import oepc.BusinessMethod;
import oepc.diagram.edit.parts.BusinessMethodEditPart;

/**
 * Action for creating and linking a new diagram to an OEPC business object method.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-01-30
 *
 */
public class OepcCreateSubdiagramAction extends AbstractCreateDiagramLinkAction<BusinessMethod> {
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractCreateDiagramLinkAction#getWizardIds()
	 */
	@Override
	protected String[] getWizardIds() {
		return new String[] {
				"org.bflow.toolbox.epc.diagram.part.EpcCreationWizardID",
				"oepc.diagram.part.OepcCreationWizardID",
				"org.bflow.toolbox.bpmn.diagram.wizards.Bpmn2CreationWizardID"
				};
	}

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
		return selectionData != null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#performModification(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected void performModification(BusinessMethod selectionData, String path) throws Exception {
		BflowDiagramElementEditUtil.modifyWithTransaction(selectionData, path, (e, v) -> e.setSubdiagram(v));
	}

}

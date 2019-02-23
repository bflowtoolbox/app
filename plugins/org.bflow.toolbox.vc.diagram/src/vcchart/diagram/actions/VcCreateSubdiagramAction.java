package vcchart.diagram.actions;

import org.bflow.toolbox.extensions.BflowDiagramElementEditUtil;
import org.bflow.toolbox.extensions.actions.AbstractCreateDiagramLinkAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import vcchart.Activity1;
import vcchart.Activity2;
import vcchart.Objective;
import vcchart.Product;
import vcchart.diagram.edit.parts.Activity1EditPart;
import vcchart.diagram.edit.parts.Activity2EditPart;
import vcchart.diagram.edit.parts.ObjectiveEditPart;
import vcchart.diagram.edit.parts.ProductEditPart;

/**
 * Action for creating and linking a new diagram to a VC activity.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-01-27
 *
 */
public class VcCreateSubdiagramAction extends AbstractCreateDiagramLinkAction<VcCreateSubdiagramAction.SelectionData> {	
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractCreateDiagramLinkAction#getWizardIds()
	 */
	@Override
	protected String[] getWizardIds() {
		return new String[] {
				"org.bflow.toolbox.epc.diagram.part.EpcCreationWizardID",
				"oepc.diagram.part.OepcCreationWizardID",
				"vcchart.diagram.part.VcCreationWizardID",
				"org.bflow.toolbox.bpmn.diagram.wizards.Bpmn2CreationWizardID",
				"orgchart.diagram.part.OrgcCreationWizardID"
				};
	}
	
	class SelectionData {
		public Activity1 _activity1;
		public Activity2 _activity2;
		public Product _product;
		public Objective _objective;
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#getSelectionData(org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	protected SelectionData getSelectionData(ISelection selection) {
		SelectionData sd = new SelectionData();
		
		Activity1 activity1 = null;
		Activity2 activity2 = null;
		Product product = null;
		Objective objective = null;
		
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			Object selObj = structuredSelection.getFirstElement();
			
			if (selObj instanceof Activity1EditPart) {
				activity1 = (Activity1) ((Activity1EditPart) selObj).getPrimaryView().getElement();
			} else if (selObj instanceof Activity2EditPart) {
				activity2 = (Activity2) ((Activity2EditPart) selObj).getPrimaryView().getElement();
			} else if (selObj instanceof ProductEditPart) {
				product = (Product) ((ProductEditPart) selObj).getPrimaryView().getElement();
			} else if (selObj instanceof ObjectiveEditPart) {
				objective = (Objective) ((ObjectiveEditPart) selObj).getPrimaryView().getElement();
			}
		}
		
		sd._activity1 = activity1;
		sd._activity2 = activity2;
		sd._product = product;
		sd._objective = objective;
		
		return sd;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#isEnabled(java.lang.Object)
	 */
	@Override
	protected boolean isEnabled(SelectionData sd) {
		return sd._activity1 != null || sd._activity2 != null 
				|| sd._product != null || sd._objective != null
				;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#performModification(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected void performModification(SelectionData sd, String value) throws Exception {
		BflowDiagramElementEditUtil.modifyWithTransaction(sd._activity1, value, (e, v) -> e.setSubdiagram(v));
		BflowDiagramElementEditUtil.modifyWithTransaction(sd._activity2, value, (e, v) -> e.setSubdiagram(v));	
		BflowDiagramElementEditUtil.modifyWithTransaction(sd._product,   value, (e, v) -> e.setSubdiagram(v));
		BflowDiagramElementEditUtil.modifyWithTransaction(sd._objective, value, (e, v) -> e.setSubdiagram(v));
	}
}

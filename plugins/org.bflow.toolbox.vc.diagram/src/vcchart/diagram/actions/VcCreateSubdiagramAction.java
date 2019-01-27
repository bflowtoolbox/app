package vcchart.diagram.actions;

import org.bflow.toolbox.extensions.BflowDiagramElementEditUtil;
import org.bflow.toolbox.extensions.actions.AbstractCreateDiagramLinkAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import vcchart.Activity1;
import vcchart.Activity2;
import vcchart.diagram.edit.parts.Activity1EditPart;
import vcchart.diagram.edit.parts.Activity2EditPart;

/**
 * Action for creating and linking a new diagram to a VC activity.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-01-27
 *
 */
public class VcCreateSubdiagramAction extends AbstractCreateDiagramLinkAction<VcCreateSubdiagramAction.SelectionData> {	
	
	class SelectionData {
		public Activity1 _activity1;
		public Activity2 _activity2;	
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#getSelectionData(org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	protected SelectionData getSelectionData(ISelection selection) {
		SelectionData sd = new SelectionData();
		
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1)
				if (structuredSelection.getFirstElement() instanceof Activity1EditPart) {
					Activity1EditPart a1EP = (Activity1EditPart) structuredSelection.getFirstElement();
					sd._activity1 = (Activity1) a1EP.getPrimaryView().getElement();
				} else if (structuredSelection.getFirstElement() instanceof Activity2EditPart) {
					Activity2EditPart a2EP = (Activity2EditPart) structuredSelection.getFirstElement();
					sd._activity2 = (Activity2) a2EP.getPrimaryView().getElement();
				}	
		}
		
		return sd;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#isEnabled(java.lang.Object)
	 */
	@Override
	protected boolean isEnabled(SelectionData sd) {
		return sd._activity1 != null || sd._activity2 != null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractCreateDiagramLinkAction#getWizardIds()
	 */
	@Override
	protected String[] getWizardIds() {
		return new String[] {
				"org.bflow.toolbox.epc.diagram.part.EpcCreationWizardID",
				"oepc.diagram.part.OepcCreationWizardID"
				};
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#performModification(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected void performModification(SelectionData sd, String pathName) throws Exception {
		BflowDiagramElementEditUtil.modifyWithTransaction(sd._activity1, pathName, (e, v) -> e.setSubdiagram(v));
		BflowDiagramElementEditUtil.modifyWithTransaction(sd._activity2, pathName, (e, v) -> e.setSubdiagram(v));		
	}
}

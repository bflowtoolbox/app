package vcchart.diagram.actions;

import org.bflow.toolbox.extensions.BflowDiagramElementEditUtil;
import org.bflow.toolbox.extensions.actions.AbstractRemoveDiagramLinkAction;
import org.bflow.toolbox.extensions.edit.parts.BflowNodeEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import vcchart.Activity1;
import vcchart.Activity2;
import vcchart.diagram.edit.parts.Activity1EditPart;
import vcchart.diagram.edit.parts.Activity2EditPart;

/**
 * Action for removing a linked diagram from a VC activity.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-01-27
 * 
 */
public class VcRemoveSubdiagramAction extends AbstractRemoveDiagramLinkAction<VcRemoveSubdiagramAction.SelectionData> {
	
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
		
		IStructuredSelection strSel = (IStructuredSelection) selection;
		BflowNodeEditPart part = (BflowNodeEditPart) strSel.getFirstElement();

		if (part instanceof Activity1EditPart) {
			sd._activity1 = (Activity1) ((Activity1EditPart) part).resolveSemanticElement();			
		} else if (part instanceof Activity2EditPart) {
			sd._activity2 = (Activity2) ((Activity2EditPart) part).resolveSemanticElement();			
		}
		
		return sd;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#isEnabled(java.lang.Object)
	 */
	@Override
	protected boolean isEnabled(SelectionData sd) {
		boolean isEnabled = true;
		if (sd._activity1 != null)
			isEnabled &= sd._activity1.getSubdiagram() != null;
		
		if (sd._activity2 != null)
			isEnabled &= sd._activity2.getSubdiagram() != null;
		
		return isEnabled;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#getModificationValue(java.lang.Object)
	 */
	@Override
	protected Void getModificationValue(SelectionData selectionData) {
		// We don't need additional values
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#performModification(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected void performModification(SelectionData sd, Void modificationValue) throws Exception {
		BflowDiagramElementEditUtil.modifyWithTransaction(sd._activity1, null, (e, v) -> e.setSubdiagram(null));
		BflowDiagramElementEditUtil.modifyWithTransaction(sd._activity2, null, (e, v) -> e.setSubdiagram(null)); 		
	}
}

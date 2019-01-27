package vcchart.diagram.actions;

import org.bflow.toolbox.extensions.BflowDiagramElementEditUtil;
import org.bflow.toolbox.extensions.actions.AbstractInsertDiagramLinkAction;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import vcchart.Activity1;
import vcchart.Activity2;
import vcchart.diagram.edit.parts.Activity1EditPart;
import vcchart.diagram.edit.parts.Activity2EditPart;
import vcchart.diagram.part.VcDiagramEditorPlugin;

/**
 * Action for linking existing diagrams to a VC activity.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-01-27
 *
 */
public class VcInsertSubdiagramAction extends AbstractInsertDiagramLinkAction<VcInsertSubdiagramAction.SelectionData> {
	
	static class SelectionData {
		public Activity1 _activity1;
		public Activity2 _activity2;
		public ShapeNodeEditPart _selectedActivity;
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.InsertDiagramLinkAction#getAdapterFactory()
	 */
	@Override
	protected AdapterFactory getAdapterFactory() {
		return VcDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory();
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.InsertDiagramLinkAction#getFileExtensions()
	 */
	@Override
	protected String[] getFileExtensions() {
		return new String[] {"epc", "oepc", "bpmn", "vc"};
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.InsertDiagramLinkAction#getSelectionData(org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	protected SelectionData getSelectionData(ISelection selection) {
		SelectionData sd = new SelectionData();
		
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1) {
				if (structuredSelection.getFirstElement() instanceof Activity1EditPart) {
					sd._selectedActivity = (Activity1EditPart) structuredSelection.getFirstElement();
					sd._activity1 = (Activity1) sd._selectedActivity.getPrimaryView().getElement();
				} else if (structuredSelection.getFirstElement() instanceof Activity2EditPart) {
					sd._selectedActivity = (Activity2EditPart) structuredSelection.getFirstElement();
					sd._activity2 = (Activity2) sd._selectedActivity.getPrimaryView().getElement();
				}	
			}				
		}
		
		return sd;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractInsertDiagramLinkAction#isEnabled(java.lang.Object)
	 */
	@Override
	protected boolean isEnabled(SelectionData selectionData) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.InsertDiagramLinkAction#getViewForSelection(java.lang.Object)
	 */
	@Override
	protected View getViewForSelection(SelectionData selectionData) {
		ShapeNodeEditPart editPart = selectionData._selectedActivity;
		if (editPart == null) return null;
		return (View) editPart.getModel();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.InsertDiagramLinkAction#performInsert(java.lang.Object, java.lang.String)
	 */
	@Override
	protected void performInsert(SelectionData sd, String path) throws Exception {
		BflowDiagramElementEditUtil.modifyWithTransaction(sd._activity1, path, (e, v) -> e.setSubdiagram(v));
		BflowDiagramElementEditUtil.modifyWithTransaction(sd._activity2, path, (e, v) -> e.setSubdiagram(v));		
	}
}

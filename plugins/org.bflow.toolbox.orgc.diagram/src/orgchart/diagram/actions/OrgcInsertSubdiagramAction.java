package orgchart.diagram.actions;

import org.bflow.toolbox.extensions.BflowDiagramElementEditUtil;
import org.bflow.toolbox.extensions.actions.AbstractInsertDiagramLinkAction;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import orgchart.LinkableElement;
import orgchart.diagram.part.OrgcDiagramEditorPlugin;

/**
 * Action for linking existing diagrams to a ORGC element.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-04-08
 *
 */
public class OrgcInsertSubdiagramAction extends AbstractInsertDiagramLinkAction<OrgcInsertSubdiagramAction.SelectionData> {

	class SelectionData {
		public IGraphicalEditPart GraphicalEditPart;
		public LinkableElement LinkableElement;
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractInsertDiagramLinkAction#getAdapterFactory()
	 */
	@Override
	protected AdapterFactory getAdapterFactory() {
		return OrgcDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory();
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractInsertDiagramLinkAction#getFileExtensions()
	 */
	@Override
	protected String[] getFileExtensions() {
		return new String[] { "epc", "oepc", "bpmn", "vc", "orgc" };
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
			Object selObj = structuredSelection.getFirstElement();
			
			if (selObj instanceof IGraphicalEditPart) {
				IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) selObj;
				EObject modelElement = graphicalEditPart.resolveSemanticElement();
				
				if (modelElement instanceof LinkableElement) {
					sd.LinkableElement = (LinkableElement) modelElement;
					sd.GraphicalEditPart = graphicalEditPart;
				}
			}
		}
		
		return sd;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#isEnabled(java.lang.Object)
	 */
	@Override
	protected boolean isEnabled(SelectionData selectionData) {
		return selectionData.LinkableElement != null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractInsertDiagramLinkAction#getViewForSelection(java.lang.Object)
	 */
	@Override
	protected View getViewForSelection(SelectionData selectionData) {
		return (View) selectionData.GraphicalEditPart.getModel();
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#performModification(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected void performModification(SelectionData sd, String path) throws Exception {
		BflowDiagramElementEditUtil.modifyWithTransaction(sd.LinkableElement, path, (e, v) -> e.setSubdiagram(v));		
	}	
}

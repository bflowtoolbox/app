package orgchart.diagram.actions;

import orgchart.LinkableElement;

import org.bflow.toolbox.extensions.BflowDiagramElementEditUtil;
import org.bflow.toolbox.extensions.actions.AbstractRemoveDiagramLinkAction;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * Action for removing a linked diagram from a ORGC element.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-04-10
 * 
 */
public class OrgcRemoveSubdiagramAction extends AbstractRemoveDiagramLinkAction<OrgcRemoveSubdiagramAction.SelectionData> {

	class SelectionData {
		public LinkableElement LinkableElement;
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#getSelectionData(org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	protected SelectionData getSelectionData(ISelection selection) {
		SelectionData sd = new SelectionData();
		
		if (selection instanceof IStructuredSelection) {
			Object selectedElement = ((IStructuredSelection) selection).getFirstElement();
			
			if (selectedElement instanceof IGraphicalEditPart) {
				EObject modelElement = ((IGraphicalEditPart) selectedElement).resolveSemanticElement();
				
				if (modelElement instanceof LinkableElement) {
					sd.LinkableElement = (LinkableElement) modelElement;
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
	protected boolean isEnabled(SelectionData sd) {
		return sd.LinkableElement != null 
			&& sd.LinkableElement.getSubdiagram() != null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#performModification(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected void performModification(SelectionData sd, Void modificationValue) throws Exception {
		BflowDiagramElementEditUtil.modifyWithTransaction(sd.LinkableElement, null, (e, v) -> e.setSubdiagram(null));
	}	
}

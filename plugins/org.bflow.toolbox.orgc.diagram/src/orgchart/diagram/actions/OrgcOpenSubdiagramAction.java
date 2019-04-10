package orgchart.diagram.actions;

import org.bflow.toolbox.extensions.actions.AbstractOpenDiagramLinkAction;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import orgchart.LinkableElement;

/**
 * Action for opening a linked diagram file.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-04-10
 *
 */
public class OrgcOpenSubdiagramAction extends AbstractOpenDiagramLinkAction {
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#getSelectionData(org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	protected String getSelectionData(ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			Object selectedElement = ((IStructuredSelection) selection).getFirstElement();
			
			if (selectedElement instanceof IGraphicalEditPart) {
				EObject modelElement = ((IGraphicalEditPart) selectedElement).resolveSemanticElement();
				
				if (modelElement instanceof LinkableElement) {
					return ((LinkableElement) modelElement).getSubdiagram();
				}
			}
		}
		
		return null;
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

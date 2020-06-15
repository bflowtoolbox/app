package orgchart.diagram.actions;

import org.bflow.toolbox.extensions.BflowDiagramElementEditUtil;
import org.bflow.toolbox.extensions.actions.AbstractCreateDiagramLinkAction;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import orgchart.LinkableElement;

/**
 * Action for creating and linking a new diagram to a ORGC element.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-04-10
 *
 */
public class OrgcCreateSubdiagramAction extends AbstractCreateDiagramLinkAction<OrgcCreateSubdiagramAction.SelectionData> {

	class SelectionData {
		public LinkableElement LinkableElement;
	}

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
	protected boolean isEnabled(SelectionData selectionData) {
		return selectionData.LinkableElement != null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#performModification(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected void performModification(SelectionData sd, String value) throws Exception {
		BflowDiagramElementEditUtil.modifyWithTransaction(sd.LinkableElement,   value, (e, v) -> e.setSubdiagram(v));		
	}
	
}

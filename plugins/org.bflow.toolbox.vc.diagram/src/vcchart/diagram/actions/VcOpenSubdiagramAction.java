package vcchart.diagram.actions;

import org.bflow.toolbox.extensions.actions.AbstractOpenDiagramLinkAction;
import org.bflow.toolbox.extensions.edit.parts.BflowNodeEditPart;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PartInitException;

import vcchart.Activity1;
import vcchart.Activity2;
import vcchart.diagram.edit.parts.Activity1EditPart;
import vcchart.diagram.edit.parts.Activity2EditPart;

/**
 * Action for opening a linked diagram file.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-01-27
 *
 */
public class VcOpenSubdiagramAction extends AbstractOpenDiagramLinkAction<String> {	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#getSelectionData(org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	protected String getSelectionData(ISelection selection) {
		IStructuredSelection strSel = (IStructuredSelection) selection;
		BflowNodeEditPart part = (BflowNodeEditPart) strSel.getFirstElement();
		
		if (part instanceof Activity1EditPart) {
			Activity1 a1 = (Activity1) ((Activity1EditPart) part).resolveSemanticElement();
			return a1.getSubdiagram();
		}
		
		if (part instanceof Activity2EditPart) {
			Activity2 a2 = (Activity2) ((Activity2EditPart) part).resolveSemanticElement();
			return a2.getSubdiagram();
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
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#getModificationValue(java.lang.Object)
	 */
	@Override
	protected Void getModificationValue(String selectionData) {
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.extensions.actions.AbstractDiagramLinkAction#performModification(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected void performModification(String selectionData, Void modificationValue) throws Exception {
		URI fileURI = URI.createPlatformResourceURI(selectionData, true);
		Resource res = new GMFResource(fileURI);

		try {
			org.bflow.toolbox.epc.diagram.part.EpcDiagramEditorUtil.openDiagram(res);
		} catch (PartInitException ex) {
			Log().error("Error on opening diagram", ex);
		}		
	}
}

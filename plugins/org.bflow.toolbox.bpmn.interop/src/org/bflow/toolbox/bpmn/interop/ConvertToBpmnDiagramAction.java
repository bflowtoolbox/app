package org.bflow.toolbox.bpmn.interop;

import org.bflow.toolbox.epc.diagram.edit.parts.EpcEditPart;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction;
import org.eclipse.jface.viewers.IStructuredSelection;

import oepc.diagram.edit.parts.OEPCEditPart;

public class ConvertToBpmnDiagramAction extends DiagramAction {

	/** Action id */
	public static final String Id = "org.bflow.toolbox.bpmn.interop.actions.convertToBpmn";
	
	/**
	 * Initializes the new instance.
	 * 
	 * @param partDescriptor Part descriptor
	 */
	public ConvertToBpmnDiagramAction(IWorkbenchPartDescriptor partDescriptor) {
		super(partDescriptor.getPartPage());
		setId(Id);
		setText("Zu BPMN umwandeln");
		// setImageDescriptor(newImage);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#refresh()
	 */
	@Override
	public void refresh() {
		// Note: 
		// This method is invoked each time the selection changes due to 
		// the isSelectionListener() result
				
		// Triggers calculateEnabled()
		super.refresh();	
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#doRun(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected void doRun(IProgressMonitor progressMonitor) {
		System.out.println();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#calculateEnabled()
	 */
	@Override
	protected boolean calculateEnabled() {
		IStructuredSelection selection = getStructuredSelection();
		Object firstElement = selection.getFirstElement();
		return (firstElement instanceof EpcEditPart || firstElement instanceof OEPCEditPart);
	}	
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#createTargetRequest()
	 */
	@Override
	protected Request createTargetRequest() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#isSelectionListener()
	 */
	@Override
	protected boolean isSelectionListener() {
		return true;
	}	
}

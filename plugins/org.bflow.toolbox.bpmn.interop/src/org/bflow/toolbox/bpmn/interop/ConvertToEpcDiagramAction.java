package org.bflow.toolbox.bpmn.interop;

import org.bflow.toolbox.epc.diagram.edit.parts.EpcEditPart;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;

import oepc.diagram.edit.parts.OEPCEditPart;

/**
 * Implements {@link IAction} to provide the EPC model conversion action.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-02-16
 *
 */
public class ConvertToEpcDiagramAction extends DiagramAction {

	/** Action id */
	public static final String Id = "org.bflow.toolbox.bpmn.interop.actions.convertToEpc"; //$NON-NLS-1$
	
	/**
	 * Initializes the new instance.
	 * 
	 * @param partDescriptor Part descriptor
	 */
	public ConvertToEpcDiagramAction(IWorkbenchPartDescriptor partDescriptor) {
		super(partDescriptor.getPartPage());
		setId(Id);
		setText(Messages.ConvertToEpcDiagramAction_Text);
		setToolTipText(Messages.ConvertToEpcDiagramAction_ToolTipText);
		
		ImageDescriptor imgDesc = BpmnInteropPlugin.imageDescriptorFromPlugin(
				"org.bflow.toolbox.diagram.extensions", "/icons/EpcIcon.png"); //$NON-NLS-1$ //$NON-NLS-2$
		
		setImageDescriptor(imgDesc);
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
	 * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#doRun(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected void doRun(IProgressMonitor progressMonitor) {
		// TODO Auto-generated method stub
		super.doRun(progressMonitor);
	}
}

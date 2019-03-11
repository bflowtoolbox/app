package org.bflow.toolbox.bpmn.interop;

import java.io.File;

import org.bflow.toolbox.epc.diagram.edit.parts.EpcEditPart;
import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * Implements {@link IAction} to provide the eEPC-BPMN model conversion action.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-03-11
 *
 */
public class ConvertEepcToBpmnDiagramAction extends AbstractConvertDiagramAction {
	
	/** Action id */
	public static final String Id = "org.bflow.toolbox.bpmn.interop.actions.convertEepcToBpmn"; //$NON-NLS-1$
	
	/**
	 * Initializes the new instance.
	 * 
	 * @param partDescriptor Part descriptor
	 */
	public ConvertEepcToBpmnDiagramAction(IWorkbenchPartDescriptor partDescriptor) {
		super(partDescriptor, Id);
		
		setText(Messages.ConvertToBpmnDiagramAction_Text);
		setToolTipText(Messages.ConvertToBpmnDiagramAction_ToolTipText);
		
		ImageDescriptor imgDesc = BpmnInteropPlugin.imageDescriptorFromPlugin(
				"org.bflow.toolbox.bpmn.diagram", "/icons/bpmn2process2.png"); //$NON-NLS-1$ //$NON-NLS-2$
		
		setImageDescriptor(imgDesc);
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.bpmn.interop.AbstractConvertDiagramAction#onCalculateEnabled(java.lang.Object)
	 */
	@Override
	protected boolean onCalculateEnabled(Object selectedObject) {
		return selectedObject instanceof EpcEditPart;
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.bpmn.interop.AbstractConvertDiagramAction#getTargetFile(java.io.File)
	 */
	@Override
	protected File getTargetFile(File sourceFile) {
		return getTargetFile(sourceFile, ".bpmn");
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.bpmn.interop.AbstractConvertDiagramAction#getExportDescriptionName()
	 */
	@Override
	protected String getExportDescriptionName() {
		return "BPMN";
	}
}

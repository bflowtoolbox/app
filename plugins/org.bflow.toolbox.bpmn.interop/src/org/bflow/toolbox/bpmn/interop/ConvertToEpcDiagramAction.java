package org.bflow.toolbox.bpmn.interop;

import java.io.File;

import org.bflow.toolbox.epc.diagram.edit.parts.EpcEditPart;
import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;

import oepc.diagram.edit.parts.OEPCEditPart;

/**
 * Implements {@link IAction} to provide the EPC model conversion action.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-02-16
 *
 */
public class ConvertToEpcDiagramAction extends AbstractConvertDiagramAction {

	/** Action id */
	public static final String Id = "org.bflow.toolbox.bpmn.interop.actions.convertToEpc"; //$NON-NLS-1$
	
	/**
	 * Initializes the new instance.
	 * 
	 * @param partDescriptor Part descriptor
	 */
	public ConvertToEpcDiagramAction(IWorkbenchPartDescriptor partDescriptor) {
		super(partDescriptor, Id);
		
		setText(Messages.ConvertToEpcDiagramAction_Text);
		setToolTipText(Messages.ConvertToEpcDiagramAction_ToolTipText);
		
		ImageDescriptor imgDesc = BpmnInteropPlugin.imageDescriptorFromPlugin(
				"org.bflow.toolbox.diagram.extensions", "/icons/EpcIcon.png"); //$NON-NLS-1$ //$NON-NLS-2$
		
		setImageDescriptor(imgDesc);
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.bpmn.interop.AbstractConvertDiagramAction#onCalculateEnabled(java.lang.Object)
	 */
	@Override
	protected boolean onCalculateEnabled(Object selectedObject) {
		return (selectedObject instanceof EpcEditPart || selectedObject instanceof OEPCEditPart);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.bpmn.interop.AbstractConvertDiagramAction#getTargetFile(java.io.File)
	 */
	@Override
	protected File getTargetFile(File sourceFile) {
		return getTargetFile(sourceFile, ".epc");
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.bpmn.interop.AbstractConvertDiagramAction#getExportDescriptionName()
	 */
	@Override
	protected String getExportDescriptionName() {
		return "EPC"; // Name of the export description
	}
}

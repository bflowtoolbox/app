package org.bflow.toolbox.bpmn.interop;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.bflow.toolbox.epc.diagram.edit.parts.EpcEditPart;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeDescriptor;
import org.bflow.toolbox.hive.interchange.mif.core.InterchangeProcessService;
import org.bflow.toolbox.hive.interchange.mif.core.InterchangeProcessingException;
import org.bflow.toolbox.hive.interchange.store.ExportDescriptorStore;
import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;

import oepc.diagram.edit.parts.OEPCEditPart;

/**
 * Implements {@link IAction} to provide the oEPC-BPMN model conversion action.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2018-10-07
 * @version 2019-02-16 AST Changed action icon
 *
 */
public class ConvertOepcToBpmnDiagramAction extends AbstractConvertDiagramAction {

	/** Action id */
	public static final String Id = "org.bflow.toolbox.bpmn.interop.actions.convertOepcToBpmn"; //$NON-NLS-1$
	
	/**
	 * Initializes the new instance.
	 * 
	 * @param partDescriptor Part descriptor
	 */
	public ConvertOepcToBpmnDiagramAction(IWorkbenchPartDescriptor partDescriptor) {
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
		return (selectedObject instanceof EpcEditPart || selectedObject instanceof OEPCEditPart);
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.bpmn.interop.AbstractConvertDiagramAction#getTargetFile(java.io.File)
	 */
	@Override
	protected File getTargetFile(File sourceFile) {	
		return getTargetFile(sourceFile, ".bpmn"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.bpmn.interop.AbstractConvertDiagramAction#getExportDescriptionName()
	 */
	@Override
	protected String getExportDescriptionName() {
		return "BPMN"; //$NON-NLS-1$
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.bpmn.interop.AbstractConvertDiagramAction#onConvert(java.io.File, java.io.File, java.io.File)
	 */
	@Override
	protected void onConvert(File sourceFile, File targetFile, File targetFolder) {
		// First, convert to EPC in a temporary subfolder
		String tmpFolderPath = FilenameUtils.concat(sourceFile.getParent(), ".tmp/"); //$NON-NLS-1$
		File tmpFolder = new File(tmpFolderPath);
		tmpFolder.mkdir();
		
		try {
			IInterchangeDescriptor epcDescriptor = ExportDescriptorStore.getExportDescription("EPC"); //$NON-NLS-1$
			if (epcDescriptor == null) return;
			
			try {
				InterchangeProcessService.processExport(sourceFile, tmpFolder, epcDescriptor);
			} catch (InterchangeProcessingException ex) {
				throw new RuntimeException(Messages.ConvertToBpmnDiagramAction_EpcConversion_Error_Message, ex);
			}
			
			// Grab created EPC file
			File epcFile = tmpFolder.listFiles()[0];
			
			// Then, convert the EPC to BPMN
			super.onConvert(epcFile, targetFile, targetFolder);			
		} finally {
			FileUtils.deleteQuietly(tmpFolder);
		}
	}
}

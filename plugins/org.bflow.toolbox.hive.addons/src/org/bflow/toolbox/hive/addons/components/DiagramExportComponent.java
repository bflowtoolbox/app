package org.bflow.toolbox.hive.addons.components;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.FilenameUtils;
import org.bflow.toolbox.hive.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.hive.addons.core.model.IComponent;
import org.bflow.toolbox.hive.addons.interfaces.IDiagramExportComponent;
import org.bflow.toolbox.hive.attributes.AttributeFile;
import org.bflow.toolbox.hive.attributes.AttributeViewPart;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeDescriptor;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeProcessor;
import org.bflow.toolbox.hive.interchange.mif.core.InterchangeProcessService;
import org.bflow.toolbox.hive.interchange.mif.core.InterchangeProcessorStore;
import org.bflow.toolbox.hive.interchange.store.ExportDescriptorStore;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;

/**
 * Implements <code>IComponent</code> for exporting a gmf diagram.
 * <p/>
 * The component expects a file object to the diagram as input source. If it
 * isn't a <code>ComponentException</code> is thrown.<br/>
 * The exported file is saved in the local OS temp folder and will be deleted on
 * exit.<br/>
 * The export runs by using the <code>org.bflow.toolbox.interchange</code>
 * plug-in. Look there for further informations.<br/>
 * After the export has been finished a file object of the exported file will
 * returned through <code>transformOutput()</code> method.
 * 
 * @author Arian Storch
 * @since 12/10/09
 * @version 17/09/12
 */
public class DiagramExportComponent implements IDiagramExportComponent {
	
	/** The target. */
	private File target;
	
	/** The source. */
	private File source;
	
	/** The x description. */
	private IInterchangeDescriptor xDescription;

	/** The finished. */
	private boolean finished = false;

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.IComponent#finish()
	 */
	@Override
	public void finish() {
		if (target != null)
			target.deleteOnExit();
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.IComponent#hasFinished()
	 */
	@Override
	public boolean hasFinished() {
		return finished;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.IComponent#init()
	 */
	@Override
	public void init() {
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.IComponent#invoke()
	 */
	@Override
	public void invoke() throws ComponentException {
		try {
			target = File.createTempFile("addontmp", xDescription
					.getFileExtensions()[0]);

			// Handle legacy format
			if(xDescription instanceof org.bflow.toolbox.hive.interchange.model.XMLBasedExportDescriptor) {
				((org.bflow.toolbox.hive.interchange.model.XMLBasedExportDescriptor)xDescription).parse();				
			}			
			
			// Resolve user selected elements
			IStructuredSelection sel = (IStructuredSelection) AttributeViewPart.getInstance().getSelection();
			
			if(sel == null)
				sel = new StructuredSelection();
			
			ArrayList<String> selElements = new ArrayList<String>();
			
			for(Object obj:sel.toArray()) {
				if(obj instanceof ShapeNodeEditPart) {
					selElements.add(EMFCoreUtil.getProxyID(((ShapeNodeEditPart)obj).resolveSemanticElement()));
				}
			}
			
			// Add attributes for the selected elements
			AttributeFile aF = AttributeViewPart.getInstance().getAttributeFile();
			
			aF.load();
			
			for(String id:selElements) {
				aF.add(id, "marked", "true");
			}
			
			// Save the modified attribute file
			if(selElements.size() > 0)
				aF.save();
			
			
			File targetFolder = target.getParentFile();
			// Run the export
			IInterchangeProcessor processor = InterchangeProcessorStore.getExportProcessorFor(xDescription);
			InterchangeProcessService.processExport(source, targetFolder, processor, xDescription);
			
			String targetFileName = FilenameUtils.getBaseName(source.getAbsolutePath());
			String targetFileExtension = xDescription.getFileExtensions()[0]; 
			String targetPathName = String.format("%s%s%s.%s", 
					targetFolder.getAbsolutePath(), File.pathSeparator, targetFileName, targetFileExtension);
			target = new File(targetPathName);

			
			// Legacy code
//			xDescription.run(source, target, false, false);
			
			// Remove the selected elements from the attribute file
			for(String id:selElements) {
				aF.remove(id, "marked");
			}
			
			// Save the attribute file
			if(selElements.size() > 0)
				aF.save();
			
		} catch (Exception ex) {
			throw new ComponentException("Could not create temp file", ex);
		} finally {
			this.finished = true;
		}

	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.IComponent#transformInput(java.lang.Object)
	 */
	@Override
	public void transformInput(Object inputSource) throws ComponentException {
		if(inputSource == null)
			throw new ComponentException("Quelle ist null");
		
		if (inputSource.getClass() != File.class)
			throw new ComponentException(
					"input source has not expected format");

		source = (File) inputSource;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.IComponent#transformOutput()
	 */
	@Override
	public Object transformOutput() throws ComponentException {
	return target;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.IComponent#getDescription(java.lang.String)
	 */
	@Override
	public String getDescription(String abbreviation) {

		if (abbreviation.equalsIgnoreCase("de")) {
			String str = "Exportiert das zu bearbeitende Diagramm in ein angegebenes Format zur weiteren Verarbeitung"
					+ " für das externe Programm";

			return str;
		}

		String str = "Exports the diagram in a specified format which shall be processed by an external program";

		return str;

	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.interfaces.IDiagramExportComponent#setExportDescription(org.bflow.toolbox.interchange.model.ExportDescription)
	 */
	@Override
	public void setExportDescription(IInterchangeDescriptor exd) {
		this.xDescription = exd;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.IComponent#isValid()
	 */
	@Override
	public boolean isValid() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.IComponent#getDisplayName()
	 */
	@Override
	public String getDisplayName() {
		return "Diagram export";
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.IComponent#canLinkWith(org.bflow.toolbox.addons.core.model.IComponent)
	 */
	@Override
	public boolean canLinkWith(IComponent component) {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.IComponent#hasParams()
	 */
	@Override
	public boolean hasParams() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.core.model.IComponent#setParams(java.lang.String)
	 */
	@Override
	public void setParams(String param) {
		this.xDescription = ExportDescriptorStore.getExportDescription(param);
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.interfaces.IDiagramExportComponent#getExportDescription()
	 */
	@Override
	public IInterchangeDescriptor getExportDescription() {
		return this.xDescription;
	}

}

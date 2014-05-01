package org.bflow.toolbox.contributions.addons;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.bflow.toolbox.hive.attributes.AttributeFile;
import org.bflow.toolbox.hive.interchange.events.IXSLTInterchangeImportListener;
import org.bflow.toolbox.hive.interchange.events.ImportEvent;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;
import org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.internal.util.DiagramIOUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;


/**
 * TODO
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 13/07/11
 * @version 08/06/13
 */
@SuppressWarnings("restriction")
public class ImportListener implements IXSLTInterchangeImportListener {

	private File sourceFile;
	private File targetFile;
	private boolean isAML = false;
	private Document amlDocument;

	@Override
	public void noticeImportEvent(ImportEvent event) {
		if (event.type == ImportEvent.START) {
			this.sourceFile = event.sourceFile;
			this.targetFile = event.targetFile;

			if (isAML(this.sourceFile)) {
				this.isAML = true;
			}
		}
		if ((event.type == ImportEvent.STEP_DONE) && (event.step == 1)) {
			SAXReader reader = new SAXReader(false);
			try {
				this.amlDocument = reader.read(event.sourceFile);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}

		if (event.type == ImportEvent.IMPORT_DONE) {
			if (this.sourceFile.getAbsolutePath().endsWith(".epml")) {
				try {
					handleEPMLAttributes(this.sourceFile, this.targetFile);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			if ((!(this.sourceFile.getAbsolutePath().endsWith(".xml")))
					|| (!(this.isAML)))
				return;
			try {
				handleAMLAttributes(this.sourceFile, this.targetFile);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	private void handleAMLAttributes(File sourceFile, File targetFile) {
		if (this.amlDocument == null)
			throw new NullPointerException("ignore this exception");
	}

	private void handleEPMLAttributes(File sourceFile, File targetFile)
			throws DocumentException, CoreException {
		SAXReader saxReader = new SAXReader();

		Document xmlDocument = saxReader.read(sourceFile);

		Element root = xmlDocument.getRootElement();
		Element directory = root.element("directory");
		
		// TODO This should not happen - something wrong with the import
		if(directory == null)
			return;
		
		Element epc = directory.element("epc");

		Path path = new Path(targetFile.getAbsolutePath());

		IFile impFile = ResourcesPlugin.getWorkspace().getRoot()
				.getFileForLocation(path);

		IEditorPart editor = null;
		AttributeFile attrFile = null;

		if (editor == null) {
			TransactionalEditingDomain domain = WorkspaceEditingDomainFactory.INSTANCE
					.createEditingDomain();

			Diagram d = DiagramIOUtil.load(domain, impFile, true, null);

			IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow();

			DiagramEditPart prEditPart = OffscreenEditPartFactory.getInstance()
					.createDiagramEditPart(d, workbenchWindow.getShell());

			attrFile = new AttributeFile(prEditPart);
		}

		attrFile.load();

		for (Iterator<?> it = epc.elementIterator(); it.hasNext();) {
			Element item = (Element) it.next();

			Element attribute = item.element("attribute");

			if (attribute != null) {
				String id = item.attributeValue("IdBflow");
				String name = attribute.attributeValue("typeRef");
				String value = attribute.attributeValue("value");

				attrFile.add(id, name, value);
			}
		}

		String epcID = epc.attributeValue("IdBflow");

		epcID = "_n33b1";

		for (Iterator<?> it = epc.elementIterator("attribute"); it.hasNext();) {
			Element attribute = (Element) it.next();

			String name = attribute.attributeValue("typeRef");
			String value = attribute.attributeValue("value");

			attrFile.add(epcID, name, value);
		}

		attrFile.save();
	}

	private boolean isAML(File file) {
		try {
			String fileContent = FileUtils.readFileToString(file);

			return (fileContent.contains("<AML>"));
		} catch (IOException localIOException) {
		}

		return false;
	}

}

package org.bflow.toolbox.contributions.addons;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bflow.toolbox.hive.attributes.AttributeFile;
import org.bflow.toolbox.hive.attributes.AttributeViewPart;
import org.bflow.toolbox.hive.interchange.events.ExportEvent;
import org.bflow.toolbox.hive.interchange.events.IXSLTInterchangeExportListener;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.eclipse.core.runtime.CoreException;

/**
 * 
 * @author Arian Storch
 * @since 13/07/11
 */
public class ExportListener implements IXSLTInterchangeExportListener {

	private File sourceFile;

	@Override
	public void noticeExportEvent(ExportEvent event) {
		if (event.type == ExportEvent.START) {
			this.sourceFile = event.sourceFile;
		}

		if (event.type == ExportEvent.INSERT_ATTRIBUTES) {
			try {
				insertAttributes(this.sourceFile, event.targetFile);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	private void insertAttributes(File sourceFile, File targetFile)
			throws CoreException, DocumentException, IOException {
		AttributeFile attrFile = AttributeViewPart.getInstance()
				.getAttributeFile();

		List<String> typeDef = attrFile.getAllAttributes();

		SAXReader saxReader = new SAXReader();

		File tgFile = targetFile;

		Document xmlDocument = saxReader.read(tgFile);

		List<String> idCollection = new ArrayList<String>();

		Element root = xmlDocument.getRootElement();

		Element attributeTypes = root.addElement("attributeTypes");

		for (String type : typeDef) {
			attributeTypes.addElement("attributeType").addAttribute("typeId",
					type);
		}

		Element directory = root.element("directory");
		Element epc = directory.element("epc");

		Attribute att = null;

		HashMap<String, String> attrMap = null;
		Element el;
		for (Iterator<?> it = epc.elementIterator(); it.hasNext();) {
			el = (Element) it.next();
			att = el.attribute("IdBflow");

			attrMap = attrFile.get(att.getStringValue());

			if (attrMap != null) {
				idCollection.add(att.getStringValue());

				for (String name : attrMap.keySet()) {
					el.addElement("attribute").addAttribute("typeRef", name)
							.addAttribute("value", (String) attrMap.get(name));
				}
			}
		}

		for (String id : attrFile.getAllIDs()) {
			if (!(idCollection.contains(id))) {
				attrMap = attrFile.get(id);
				break;
			}
		}
		if (attrMap != null) {
			for (String name : attrMap.keySet()) {
				epc.addElement("attribute").addAttribute("typeRef", name)
						.addAttribute("value", (String) attrMap.get(name));
			}
		}
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter xmlWriter = new XMLWriter(new FileWriter(tgFile), format);

		xmlWriter.write(xmlDocument);
		xmlWriter.close();
	}

}

package org.bflow.toolbox.hive.interchange.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import net.sf.saxon.TransformerFactoryImpl;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.bflow.toolbox.hive.interchange.events.ExportEvent;
import org.bflow.toolbox.hive.interchange.events.ExportListenerRegistry;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeDescriptor;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeProcessListener;
import org.bflow.toolbox.hive.interchange.mif.core.IScriptDescriptor;
import org.osgi.framework.Bundle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

/**
 * Defines an export description object that is represented by a XML file.
 * 
 * @author Arian Storch
 * @since 14/08/09
 * @version 03/10/12
 * @deprecated Will be no longer supported
 */
public class XMLBasedExportDescriptor implements IInterchangeDescriptor {

	private InputStream iStream = null;
	private Bundle bundle;

	// class attributes
	private String filename;
	private String name;
	private boolean _public = true;
	private String diagramEditorFileExtension;
	private String description;
	private String fileExtension;
	private Vector<XMLBasedScriptDescriptor> scripts = new Vector<XMLBasedScriptDescriptor>();

	// for subclasses
	private File lastExportedFile = null;

	private boolean parsed = false;

	/**
	 * Constructor
	 * 
	 * @param filename
	 *            name of the file
	 * 
	 * @param bundle
	 *            Bundle which contains this description
	 * 
	 * @param iStream
	 *            Input stream of the XML file
	 * 
	 */
	public XMLBasedExportDescriptor(Bundle bundle, InputStream iStream, String filename) {
		this.iStream = iStream;
		this.filename = filename;
		this.bundle = bundle;
	}

	/**
	 * Parses the file given by the constructor.
	 * <p/>
	 * <b>note:</b> call this first, else getters will return null
	 */
	public void parse() throws InterchangeExportException {
		parse(false);
	}

	/**
	 * Parses only that content of the file that contains the name and
	 * description
	 * <p/>
	 * this is useful when you only want to get some basic informations about
	 * the file and it isn't necessary to parse the whole file which may need
	 * more time
	 * <p/>
	 * <b>note:</b> call this first, else getters will return null
	 */
	public void parseDescription() throws InterchangeExportException {
		parse(true);
	}

	private void parse(boolean breakAfterDescription)
			throws InterchangeExportException {
		if (parsed) {
			return;
		}

		if (iStream == null) {
			throw new InterchangeExportException(
					"Could not parse the export description! The input stream was null!");
		}

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document;

			document = builder.parse(iStream);

			Element docElement = document.getDocumentElement();
			NodeList list = docElement.getElementsByTagName("config");

			for (int i = 0; i < list.getLength(); i++) {
				Element el = (Element) list.item(i);

				resolveHeaderInformation(el);
			}
		} catch (Exception ex) {
			throw new InterchangeExportException(
					"Could not parse the export description!", ex);
		}

		parsed = true;
	}

	/**
	 * Runs the export description and invokes necessary functions to fulfill
	 * the export
	 * 
	 * @param sourceFile
	 *            source file
	 * @param targetFile
	 *            target file
	 * @param ignoreFilename
	 *            set true if the target file must have the same name as the
	 *            source one
	 * @param checkFileExtension
	 *            proofs if the file extension of the target file is set correct
	 *            and corrects if necessary
	 * @return true if the export succeed else false
	 */
	public boolean run(File sourceFile, File targetFile,
			boolean ignoreFilename, boolean checkFileExtension)
			throws InterchangeExportException {
		if (ignoreFilename)
			targetFile = new File(targetFile.getPath() + "/"
					+ FilenameUtils.getBaseName(sourceFile.getAbsolutePath()));

		// checking file extension and correcting if necessary
		if (checkFileExtension)
			if (!FilenameUtils.getExtension(targetFile.getAbsolutePath())
					.equalsIgnoreCase(this.fileExtension))
				targetFile = new File(targetFile.getPath() + "."
						+ this.fileExtension);

		// stores all temporarily created files
		Vector<File> createdFiles = new Vector<File>();

		fireExportEvent(ExportEvent.START, -1, sourceFile, targetFile);

		File newSourceFile = null;

		// removing unmanageable XMI tags
		try {
			String fContent = FileUtils.readFileToString(sourceFile);

			int begin = 0;

			while ((begin = fContent.indexOf("<Subdiagram>")) != -1) {
				int end = fContent.indexOf("</Subdiagram>", begin);
				String rpl = fContent.substring(begin, end + 13);

				fContent = fContent.replaceFirst(rpl, "");
			}

			newSourceFile = File.createTempFile("newSourceFile", ".epctmp");
			createdFiles.add(newSourceFile);

			FileUtils.writeStringToFile(newSourceFile, fContent);
		} catch (Exception ex) {
			throw new InterchangeExportException(
					"Could not run the export description!", ex);
		}

		if (newSourceFile != null) {
			sourceFile = newSourceFile;
		}

		// finished

		IScriptDescriptor[] scripts = this.getScripts();
		
		// count of XSLT scripts
		int countScripts = scripts.length; 

		for (int i = 0; i < countScripts; i++) {
			IScriptDescriptor script = scripts[i];
			Map<String, Object> params = script.getParameters();
			
			/*
			 * copying the script file temporarily on local
			 */
			String scriptFileLocation;
			InputStream scriptStream = null;

			scriptFileLocation = script.getPath();

			try {
				if (bundle != null) {
					scriptStream = bundle.getEntry(scriptFileLocation)
							.openStream();
				} else {
					scriptStream = new FileInputStream(new File(FilenameUtils
							.getFullPath(this.filename)
							+ script.getPath()));
				}

				if (scriptStream == null) {
					throw new NullPointerException(
							"Input stream for XSLT script is null");
				}

			} catch (Exception ex) {
				fireExportEvent(ExportEvent.BROKEN, i, sourceFile, targetFile);
				throw new InterchangeExportException(
						"Could not run the export description!", ex);
			}

			File tempScriptFile;

			try {
				tempScriptFile = File.createTempFile("temp", ".xslt");

				OutputStream os = new FileOutputStream(tempScriptFile);

				IOUtils.copy(scriptStream, os);

			} catch (Exception ex) {
				fireExportEvent(ExportEvent.BROKEN, i, sourceFile, targetFile);
				throw new InterchangeExportException(
						"Could not run the export description!", ex);
			}

			/*
			 * finished
			 */

			/*
			 * setting up source file
			 */
			File source;

			if (createdFiles.size() == 0) // es muss stets die zuletzt
				source = sourceFile; // angelegte Datei zur Weiterverarbeitung
			else
				// genutzt werden
				source = createdFiles.lastElement(); //

			/*
			 * setting up target file
			 */
			File target = null;

			if (i == countScripts - 1) // beim letzten Skript
				target = targetFile; // ist die Zieldatei
			else
				try {
					target = File.createTempFile("temp_" + i, "tmp");
				} catch (IOException ex) {
					fireExportEvent(ExportEvent.BROKEN, i, source, target);
					throw new InterchangeExportException(
							"Could not run the export description!", ex);
				}

			try {
				ArrayList<String> parameterList = new ArrayList<String>();

				TransformerFactoryImpl tFactory = new TransformerFactoryImpl();
				Transformer transformer = tFactory
						.newTransformer(new StreamSource(tempScriptFile));

				for (String key : params.keySet()) {
					if (isControlKey(key))
						continue;

					String value = key + "=" + params.get(key);
					parameterList.add(value);

					String val = (String) params.get(key);
					Object oVal = null;

					try {
						double d = Double.parseDouble(val);
						oVal = d;
					} catch (NumberFormatException ex) {
						oVal = val;
					}

					transformer.setParameter(key, oVal);
				}

				OutputStream os = new FileOutputStream(target);
				StreamResult sr = new StreamResult(os);
				transformer.transform(new StreamSource(source), sr);

				if (params.containsKey("insertAttributes")) {
					fireExportEvent(ExportEvent.INSERT_ATTRIBUTES, i, source,
							target);
				}
				
				// Release all locked resources
				os.close();
				os = null;
				sr = null;

			} catch (Exception ex) {
				fireExportEvent(ExportEvent.BROKEN, i, source, target);
				throw new InterchangeExportException(
						"Could not run the export description!", ex);
			}

			tempScriptFile.delete();
			createdFiles.add(target);

			lastExportedFile = target; // targetFile;

			fireExportEvent(ExportEvent.STEP_DONE, i, source, target);
		}

		for (int i = 0; i < countScripts - 1; i++)
			createdFiles.get(i).delete();

		fireExportEvent(ExportEvent.EXPORT_DONE, -1, sourceFile, targetFile);

		return true;
	}

	/**
	 * runs the export description and invokes necessary functions to fulfill
	 * the export
	 * 
	 * @param exportDescription
	 *            ExportDescription that shall run
	 * @param sourceFile
	 *            source file
	 * @param targetFile
	 *            target file
	 * @param checkFileExtension
	 *            proofs if the file extension of the target file is set correct
	 *            and corrects if necessary
	 * @return true if the export succeed else false
	 * @throws Exception
	 *             if something went wrong
	 */
	public static boolean run(XMLBasedExportDescriptor exportDescription,
			File sourceFile, File targetFile, boolean checkFileExtension)
			throws InterchangeExportException {
		exportDescription.parse();

		return exportDescription.run(sourceFile, targetFile, false,
				checkFileExtension);
	}

	/**
	 * runs the export description and invokes necessary functions to fulfil the
	 * export
	 * 
	 * @param exportDescription
	 *            ExportDescription that shall run
	 * @param sourceFile
	 *            source file
	 * @param targetFile
	 *            target file
	 * @return true if the export succeed else false
	 * @throws Exception
	 *             if something went wrong
	 */
	public static boolean run(XMLBasedExportDescriptor exportDescription,
			File sourceFile, File targetFile) throws InterchangeExportException {
		exportDescription.parse();

		return exportDescription.run(sourceFile, targetFile, false, true);
	}

	/**
	 * This method is invoked when a script includes the "insertAttribute"
	 * parameter. By default this is empty. Subclasses may override.
	 * 
	 * @param attributeFile
	 *            attribute file or null
	 * @param targetFile
	 *            target file
	 */
	protected void insertAttributes(File targetFile) {

	}

	/**
	 * Proofs if the given key is an control key and not an xslt parameter
	 * 
	 * @param key
	 *            key to proof
	 * @return true or false
	 */
	protected boolean isControlKey(String key) {
		if (key.equalsIgnoreCase("insertAttribute"))
			return true;

		return false;
	}

	private void resolveHeaderInformation(Element el) {
		String name = getTextValue(el, "name");
		String description = getTextValue(el, "description");
		String fileextension = getTextValue(el, "fileextension");
		String _public = getTextValue(el, "hide");
		String diagram = getTextValue(el, "diagram");

		this.name = name;
		this.description = description;
		this.fileExtension = fileextension;
		this._public = !Boolean.parseBoolean(_public); // hiding means not public
		this.diagramEditorFileExtension = diagram.isEmpty() ? null : diagram;
		
		NodeList list = el.getElementsByTagName("callstack");

		for (int i = 0; i < list.getLength(); i++) {
			Element element = (Element) list.item(i);

			resolveCallstackInformation(element);
		}

	}

	private void resolveCallstackInformation(Element el) {
		NodeList list = el.getElementsByTagName("script");

		for (int i = 0; i < list.getLength(); i++) {
			Element element = (Element) list.item(i);

			resolveScriptInformation(element);
		}
	}

	private void resolveScriptInformation(Element el) {
		String file = getTextValue(el, "file");

		NodeList list = el.getElementsByTagName("param");

		XMLBasedScriptDescriptor script = new XMLBasedScriptDescriptor();
		script.setFile(file);

		for (int i = 0; i < list.getLength(); i++) {
			Element element = (Element) list.item(i);

			NamedNodeMap map = element.getAttributes();

			for (int j = 0; j < map.getLength(); j++) {
				Object e = map.item(j);

				String attributeString = "" + e;

				String attrArray[] = attributeString.split("=");

				String attrName = attrArray[0];
				String attrValue = attrArray[1].substring(1, attrArray[1]
						.length() - 1);

				script.getParams().put(attrName, attrValue);
			}
		}

		this.scripts.add(script);
	}

	/**
	 * takes a xml element and the tag name, look for the tag and get the text
	 * content
	 * 
	 * @param ele
	 *            element
	 * @param tagName
	 *            tag name
	 * @return value of the tag name
	 */
	private String getTextValue(Element ele, String tagName) {
		String textVal = "";
		NodeList nl = ele.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0) {
			Element el = (Element) nl.item(0);
			if (el.hasChildNodes())
				textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}

	/**
	 * returns the name of the ExportDescription<br/>
	 * <b>note:</b> call <code>parse()</code> first, else null will be returned
	 * 
	 * @return name of the ExportDescription
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns true if the export description is public accessible. 
	 * @return true or false
	 */
	public boolean isPublic() {
		return _public;
	}
	
	/**
	 * Sets if the export description shall be public accessible.
	 * @param _public true or false
	 */
	public void setPublic(boolean _public) {
		this._public = _public;
	}

	/**
	 * Returns the description text<br/>
	 * <b>note:</b> call <code>parse()</code> first, else null will be returned
	 * 
	 * @return description text
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * returns the file extension of the exported file
	 * 
	 * @return file extension of the exported file
	 */
	public String getFileExtension() {
		return fileExtension;
	}
	
	
	/**
	 * Returns the diagram editor file extension.
	 * 
	 * @return the diagram editor file extension
	 */
	public String getDiagramEditorFileExtension() {
		return diagramEditorFileExtension;
	}

	/**
	 * returns the scripts of the export description<br/>
	 * <b>note:</b> call <code>parse()</code> first, else null will be returned
	 * 
	 * @return scripts of the export description
	 */
	public IScriptDescriptor[] getScripts() {
		return scripts.toArray(new IScriptDescriptor[0]);
	}

	/**
	 * Returns the last exported file by this script.
	 * 
	 * @return last exported file
	 */
	protected File getLastExportedFile() {
		return lastExportedFile;
	}

	@Override
	public String toString() {
		String retString = "[ExportDescription] Filename: " + filename
				+ " Name: " + name + " Description: " + description;

		for (XMLBasedScriptDescriptor script : scripts)
			retString += " " + script;

		return retString;
	}

	/**
	 * Fires an export event.
	 * 
	 * @param type
	 *            type of the event
	 * @param source
	 *            source file
	 * @param target
	 *            target file
	 */
	private void fireExportEvent(int type, int step, File source, File target) {
		ExportEvent event = new ExportEvent();
		event.exportDescriptor = this;
		event.sourceFile = source;
		event.targetFile = target;
		event.type = type;
		event.step = step;

		ExportListenerRegistry.dispatchEvent(event);
	}

	@Override
	public String[] getApplicableDiagramEditorTypes() {
		if(diagramEditorFileExtension == null) {
			return new String[] {};
		} else {
			return new String[] { diagramEditorFileExtension };			
		}
	}

	@Override
	public String[] getFileExtensions() {
		return new String[] { fileExtension };
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IInterchangeDescriptor#getProcessListener()
	 */
	@Override
	public IInterchangeProcessListener getProcessListener() {
		return IInterchangeProcessListener.DeafProcessListener;
	}
}

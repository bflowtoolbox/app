package org.bflow.toolbox.hive.interchange.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bflow.toolbox.hive.interchange.events.ImportEvent;
import org.bflow.toolbox.hive.interchange.events.ImportListenerRegistry;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeDescriptor;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeProcessListener;
import org.bflow.toolbox.hive.interchange.mif.core.IScriptDescriptor;
import org.osgi.framework.Bundle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

/**
 * This class provides the import function.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 17/08/09
 * @version 18/07/13
 * @deprecated Will be no longer supported
 * 
 */
public class XMLBasedImportDescriptor implements IInterchangeDescriptor {
	
	/** The log instance for this class. */
	private static final Log logger = LogFactory.getLog(XMLBasedImportDescriptor.class);

	/** The i stream. */
	private InputStream iStream = null;
	
	/** The bundle. */
	private Bundle bundle;

	// class attributes
	/** The filename. */
	private String filename;
	
	/** The name. */
	private String name;
	
	/** The description. */
	private String description;
	
	/** The file extension. */
	private String fileExtension;
	
	/** The scripts. */
	private List<XMLBasedScriptDescriptor> scripts = new LinkedList<XMLBasedScriptDescriptor>();

	// subclasses
	/** The last imported file. */
	private File lastImportedFile;

	/** The parsed. */
	private boolean parsed = false;

	/**
	 * Constructor.
	 *
	 * @param bundle the bundle
	 * @param iStream the i stream
	 * @param filename name of the file
	 */
	public XMLBasedImportDescriptor(Bundle bundle, InputStream iStream, String filename) {
		this.iStream = iStream;
		this.filename = filename;
		this.bundle = bundle;
	}

	/**
	 * Returns the filename of the export description.
	 * 
	 * @return name of the file
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Parses the file given by the constructor.
	 * <p/>
	 * <b>note:</b> call this first, else getters will return null
	 *
	 * @throws InterchangeImportException the interchange import exception
	 */
	public void parse() throws InterchangeImportException {
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
	 * <b>note:</b> call this first, else getters will return null.
	 *
	 * @throws InterchangeImportException the interchange import exception
	 */
	public void parseDescription() throws InterchangeImportException {
		parse(true);
	}

	/**
	 * Parses the.
	 *
	 * @param breakAfterDescription the break after description
	 * @throws InterchangeImportException the interchange import exception
	 */
	private void parse(boolean breakAfterDescription) throws InterchangeImportException {
		if (parsed) {
			return;
		}

		if (iStream == null) {
			throw new InterchangeImportException("Could not parse the import description! The input stream was null!");
		}

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
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
			throw new InterchangeImportException("Could not parse the import description!", ex);
		}

		parsed = true;
	}

	/**
	 * Runs the import description and invokes necessary functions to complete the
	 * import process.
	 *
	 * @param sourceFile source file
	 * @param targetFile target file
	 * @param ignoreFilename set true if the target file must have the same name as the
	 * source one
	 * @return true if the import succeed else false
	 * @throws InterchangeImportException the interchange import exception
	 */
	public boolean run(File sourceFile, File targetFile, boolean ignoreFilename) throws InterchangeImportException {

//		targetFile = new File(targetFile.getAbsolutePath() + "/"
//				+ FilenameUtils.getBaseName(sourceFile.getAbsolutePath())
//				+ ".epc");

		// Contains temporary created files
		Vector<File> createdFiles = new Vector<File>();
		// Contains temporary copied files
		Vector<File> copiedFiles = new Vector<File>();
		
		fireImportEvent(ImportEvent.START, -1, sourceFile, targetFile);

		/*
		 * some imports need transformations in a temporary folder
		 */
		File tempSourceFile;

		// copying the source file in a temporary folder
		try {
			tempSourceFile = File.createTempFile(sourceFile.getName(), "");
			FileUtils.copyFile(sourceFile, tempSourceFile);
		} catch (Exception ex) {
			throw new InterchangeImportException("Could not run the import description!", ex);
		}

		copiedFiles.add(tempSourceFile);

		IScriptDescriptor[] _scripts = this.getScripts();
		int countScripts = _scripts.length;

		for (int i = 0; i < countScripts; i++) {
			XMLBasedScriptDescriptor script = (XMLBasedScriptDescriptor) _scripts[i];

			if (script.getFile().isEmpty() && script.getParams().size() == 0)
				continue;

			/*
			 * handling of the copy parameter
			 */
			boolean onlyCopy = false;

			if (script.getParams().containsKey("copy"))
				onlyCopy = true;

			if (onlyCopy) {
				String file = script.getFile(); 
				String name = script.getParams().get("name");
				URL cpyURL = bundle.getEntry(file);

				String tempDir = System.getProperty("java.io.tmpdir");

				try {
					File f = new File(tempDir + "/" + name);
					FileUtils.copyURLToFile(cpyURL, f);

					copiedFiles.add(f);
					continue;
				} catch (Exception ex) {
					throw new InterchangeImportException("Could not run the import description!", ex);
				}

			}

			/*
			 * copying the script into the temporary folder
			 */
			String scriptFileLocation = script.getFile();
			InputStream scriptStream = null;

			try {
				if (bundle != null) {
					scriptStream = bundle.getEntry(scriptFileLocation).openStream();
				} else {
					scriptStream = new FileInputStream(
							new File(FilenameUtils.getFullPath(this.filename) + script.getFile()));
				}
			} catch (Exception ex) {
				fireImportEvent(ImportEvent.BROKEN, i, sourceFile, targetFile);
				throw new InterchangeImportException("Could not run the import description!", ex);
			}

			File tempScriptFile;

			try {
				tempScriptFile = File.createTempFile("temp", ".xslt");
				OutputStream os = new FileOutputStream(tempScriptFile);
				IOUtils.copy(scriptStream, os);
				IOUtils.closeQuietly(os);
			} catch (Exception ex) {
				fireImportEvent(ImportEvent.BROKEN, i, sourceFile, targetFile);
				throw new InterchangeImportException("Could not run the XML based import!", ex);
			}

			/*
			 * Preparation done
			 */

			/*
			 * Setting up source file
			 */
			File source;

			// Always use the latest file for the next operation
			if (createdFiles.size() == 0) {
				source = tempSourceFile; 
			} else {
				source = createdFiles.lastElement();
			}

			/*
			 * Setting up target file
			 */
			File target = null;

			try {
				// For the last step, the final file is that one that
				// has been defined by user within the dialog
				if (i == countScripts - 1)
					target = targetFile;
				else
					target = File.createTempFile("importfile" + i + "_", "");
			} catch (Exception ex) {
				fireImportEvent(ImportEvent.BROKEN, i, source, target);
				throw new InterchangeImportException("Could not run the import description!", ex);
			}

			/*
			 * Doing XSLT processing
			 */
			try {
				ArrayList<String> parameterList = new ArrayList<String>();

				TransformerFactoryImpl tFactory = new TransformerFactoryImpl();
				Transformer transformer = tFactory.newTransformer(new StreamSource(tempScriptFile));

				for (String key : script.getParams().keySet()) {
					String value = key + "=" + script.getParams().get(key);
					parameterList.add(value);

					String val = script.getParams().get(key);
					Object oVal = null;

					try {
						double d = Double.parseDouble(val);
						oVal = d;
					} catch (NumberFormatException ex) {
						oVal = val;
					}

					transformer.setParameter(key, oVal);
				}
				
				// 08.06.2013
				// Sometimes there are \n characters within the files
				// so they will be removed
				// TODO Check if this is a good solution in common
				replaceUndesiredCharacters(source);
				
				OutputStream os = new FileOutputStream(target);
				StreamResult sr = new StreamResult(os);
				transformer.transform(new StreamSource(source), sr);
				
				/*
				if(script.getParams().containsKey("")) {
					fireImportEvent(ImportEvent.INSERT_ATTRIBUTES, i, source, target);
				}*/
				
				// Release all locked resources
				os.close();
				os = null;
				sr = null;
				
			} catch (Exception ex) {
				fireImportEvent(ImportEvent.BROKEN, i, source, target);
				throw new InterchangeImportException("Could not run the import description!", ex);
			}

			tempScriptFile.delete();
			createdFiles.add(target);
			
			fireImportEvent(ImportEvent.STEP_DONE, i, source, target);
		}

		/*
		 * Cleaning up
		 */
		for (int i = 0; i < createdFiles.size() - 1; i++)
			createdFiles.get(i).delete();

		for (File file : copiedFiles)
			file.delete();

		lastImportedFile = targetFile;
		fireImportEvent(ImportEvent.IMPORT_DONE, -1, sourceFile, targetFile);
		return true;
	}

	/**
	 * Runs the import description and invokes necessary functions to complete the
	 * import process.
	 *
	 * @param importDescription ImportDescription that shall run
	 * @param sourceFile source file
	 * @param targetFile target file
	 * @return true if the import succeed else false
	 * @throws InterchangeImportException the interchange import exception
	 */
	public static boolean run(XMLBasedImportDescriptor importDescription,
			File sourceFile, File targetFile) throws InterchangeImportException {
		importDescription.parse();

		return importDescription.run(sourceFile, targetFile, false);
	}

	/**
	 * Resolve header information.
	 *
	 * @param el the el
	 */
	private void resolveHeaderInformation(Element el) {
		String name = getTextValue(el, "name");
		String description = getTextValue(el, "description");
		String fileextension = getTextValue(el, "fileextension");

		this.name = name;
		this.description = description;
		this.fileExtension = fileextension;

		NodeList list = el.getElementsByTagName("callstack");

		for (int i = 0; i < list.getLength(); i++) {
			Element element = (Element) list.item(i);

			resolveCallstackInformation(element);
		}

	}

	/**
	 * Resolve callstack information.
	 *
	 * @param el the el
	 */
	private void resolveCallstackInformation(Element el) {
		NodeList list = el.getElementsByTagName("script");

		for (int i = 0; i < list.getLength(); i++) {
			Element element = (Element) list.item(i);

			resolveScriptInformation(element);
		}
	}

	/**
	 * Resolve script information.
	 *
	 * @param el the el
	 */
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
	 * Takes a XML element and the tag name, looks for the tag and returns the text
	 * content.
	 * 
	 * @param element
	 *            element
	 * @param tagName
	 *            tag name
	 * @return value of the tag name
	 */
	private String getTextValue(Element element, String tagName) {
		String textVal = "";
		NodeList nl = element.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0) {
			Element el = (Element) nl.item(0);
			if (el.hasChildNodes())
				textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}

	/**
	 * returns the name of the ImportDescription<br/>
	 * <b>note:</b> call <code>parse()</code> first, else null will be returned.
	 *
	 * @return name of the ImportDescription
	 */
	public String getName() {
		return name;
	}

	/**
	 * returns the description text<br/>
	 * <b>note:</b> call <code>parse()</code> first, else null will be returned.
	 *
	 * @return description text
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * returns the file extension of the exported file.
	 *
	 * @return file extension of the exported file
	 */
	public String getFileExtension() {
		return fileExtension;
	}

	/**
	 * returns the scripts of the export description<br/>
	 * <b>note:</b> call <code>parse()</code> first, else null will be returned.
	 *
	 * @return scripts of the export description
	 */
	public IScriptDescriptor[] getScripts() {
		return scripts.toArray(new IScriptDescriptor[0]);
	}

	/**
	 * Returns the last imported file by this script.
	 * 
	 * @return the last imported file
	 */
	protected File getLastImportedFile() {
		return lastImportedFile;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String retString = "[ImportDescription] Filename: " + filename
				+ " Name: " + name + " Description: " + description;

		for (XMLBasedScriptDescriptor script : scripts)
			retString += " " + script;

		return retString;
	}
	
	/**
	 * Fires an import event.
	 *
	 * @param type type of the event
	 * @param step the step
	 * @param source source file
	 * @param target target file
	 */
	private void fireImportEvent(int type, int step, File source, File target) {
		ImportEvent event = new ImportEvent();
		event.importDescription = this;
		event.sourceFile = source;
		event.targetFile = target;
		event.type = type;
		event.step = step;
		
		ImportListenerRegistry.dispatchEvent(event);
	}
	
	/**
	 * Replaces all undesired characters within the given source file.
	 * 
	 * @param source
	 *            File which contains undesired characters
	 */
	private void replaceUndesiredCharacters(File source) {
		try {
			String fileContent = FileUtils.readFileToString(source);
			fileContent = fileContent.replace("\\n", " ");
			FileUtils.writeStringToFile(source, fileContent);
		} catch (Exception ex) {
			logger.error("Error while IO operation", ex);
		}
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IInterchangeDescriptor#isPublic()
	 */
	@Override
	public boolean isPublic() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IInterchangeDescriptor#getApplicableDiagramEditorTypes()
	 */
	@Override
	public String[] getApplicableDiagramEditorTypes() {
		return new String[] { "epc" };
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.interchange.mif.core.IInterchangeDescriptor#getFileExtensions()
	 */
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

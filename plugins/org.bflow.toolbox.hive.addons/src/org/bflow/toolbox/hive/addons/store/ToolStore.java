package org.bflow.toolbox.hive.addons.store;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bflow.toolbox.hive.addons.events.ToolStoreListener;
import org.bflow.toolbox.hive.addons.utils.ToolDescriptor;
import org.bflow.toolbox.hive.interchange.AddonsInterchangePlugin;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * Provides a store to manage installed add-on tools. 
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 17/04/10
 * @version 13/09/13
 */
public class ToolStore {
	/**
	 * Collection for all installed tools.
	 */
	private static List<ToolDescriptor> installedTools = new ArrayList<ToolDescriptor>();

	/**
	 * Default state
	 */
	private static boolean init = false;

	/**
	 * holds all installed listeners
	 */
	private static List<ToolStoreListener> storeListener = new ArrayList<ToolStoreListener>();

	/**
	 * Installs the described tool.
	 * 
	 * @param descriptor
	 *            tool descriptor
	 */
	public static void installTool(ToolDescriptor descriptor) {
		if (!init)
			init();

		if (!installedTools.contains(descriptor))
			installedTools.add(descriptor);
	}

	/**
	 * Removes the tools specified by the name.
	 * 
	 * @param name
	 *            name of the tool to remove.
	 */
	public static void removeTool(String name) {
		if (!init)
			init();

		for (ToolDescriptor tool : installedTools)
			if (tool.getName().equals(name)) {
				installedTools.remove(tool);
				return;
			}
	}

	/**
	 * Returns the ToolDescriptor defined by the name.
	 * 
	 * @param name
	 *            name of the tool
	 * @return ToolDescriptor or null
	 */
	public static ToolDescriptor getTool(String name) {
		if (!init)
			init();

		for (ToolDescriptor tool : installedTools)
			if (tool.getName().equals(name))
				return tool;

		return null;
	}

	/**
	 * Saves the store content.
	 */
	public static void save() {
		if (!init)
			init();

		fireStoreUpdateEvent();

		Document xmlDocument = DocumentHelper.createDocument();
		Element root = xmlDocument.addElement("toolmap");

		for (ToolDescriptor td : installedTools) {
			Element tool = root.addElement("tool");
			tool.addAttribute("name", td.getName());
			tool.addAttribute("path", td.getPath());
			tool.addAttribute("param", td.getParam());
		}

		try {
			OutputFormat format = OutputFormat.createPrettyPrint();

			if (!Key.KEY_TOOLSTORE_FILE.exists()) {
				Key.KEY_TOOLSTORE_FILE.getParentFile().mkdirs();
				Key.KEY_TOOLSTORE_FILE.createNewFile();
			}

			XMLWriter writer = new XMLWriter(new FileWriter(Key.KEY_TOOLSTORE_FILE), format);

			writer.write(xmlDocument);
			writer.close();
		} catch (IOException ex) {
			AddonsInterchangePlugin.logError("Error on saving ToolStore", ex);
		}
	}

	/**
	 * Returns a vector with descriptions of all installed tools.
	 * 
	 * @return vector with descriptions of all installed tools
	 */
	public static List<ToolDescriptor> getInstalledTools() {
		if (!init)
			init();

		return installedTools;
	}

	/**
	 * Initializes the store.
	 * <p/>
	 * If you subclass then you can use this method to do extra stuff.
	 */
	protected static void init() {
		SAXReader reader = new SAXReader();

		try {
			Document xmlDocument = reader.read(Key.KEY_TOOLSTORE_FILE);
			Element root = xmlDocument.getRootElement();

			for (Iterator<?> it = root.elementIterator("tool"); it.hasNext();) {
				Element tool = (Element) it.next();

				String name = tool.attributeValue("name");
				String path = tool.attributeValue("path");
				String param = tool.attributeValue("param");

				installedTools.add(new ToolDescriptor(name, path, param));
			}
		} catch (DocumentException ex) {
			// ex.printStackTrace();
		}

		if (installedTools.size() == 0) {
			ToolDescriptor swiPrologTd = new ToolDescriptor("SWI-Prolog", StringUtils.EMPTY, StringUtils.EMPTY);
			installedTools.add(swiPrologTd);

			try {
				// Check for optional dependency
				Class.forName("org.bflow.toolbox.hive.swiprolog.SWIPrologInstaller");

				// Installation meta info
				org.bflow.toolbox.hive.swiprolog.InstallMetaInfo installMetaInfo;

				// Installer is available
				installMetaInfo = org.bflow.toolbox.hive.swiprolog.SWIPrologInstaller
						.installToWorkspace();

				// Resolve path to the tool
				String installPath = installMetaInfo.getInstallPath();
				String executableName = installMetaInfo.getExecutableName();

				String toolPath = String.format("%s\\pl\\bin\\%s", installPath, executableName);

				// Set it
				swiPrologTd.setPath(toolPath);
			} catch (ClassNotFoundException e) {
				// Optional dependency isn't installed
			}
		}

		init = true;
	}

	/**
	 * Adds the listener to the store.
	 * 
	 * @param listener
	 */
	public static void addStoreListener(ToolStoreListener listener) {
		storeListener.add(listener);
	}

	/**
	 * Removes the listener from the store.
	 * 
	 * @param listener
	 */
	public static void removeStoreListener(ToolStoreListener listener) {
		storeListener.remove(listener);
	}

	/**
	 * Forces the store to fire an update event.
	 */
	private static void fireStoreUpdateEvent() {
		for (ToolStoreListener listener : storeListener)
			listener.storeUpdate();
	}
}

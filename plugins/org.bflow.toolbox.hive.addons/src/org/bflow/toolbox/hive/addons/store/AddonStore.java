package org.bflow.toolbox.hive.addons.store;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bflow.toolbox.hive.addons.events.ProtocolStoreListener;
import org.bflow.toolbox.hive.addons.protocols.Standardprotocol;
import org.bflow.toolbox.hive.addons.utils.ProtocolDescriptor;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * Defines a store that contains all installed add-ons.
 * 
 * @author Arian Storch
 * @since 17/04/10
 * @version 17/11/12
 */
public class AddonStore {

	/**
	 * Collection of all installed add-ons.
	 */
	private static List<ProtocolDescriptor> installedAddons = new ArrayList<ProtocolDescriptor>();

	/**
	 * Initialization flag
	 */
	private static boolean init = false;

	/**
	 * Collection of all store listeners
	 */
	private static List<ProtocolStoreListener> storeListener = new ArrayList<ProtocolStoreListener>();

	/**
	 * Initializes the store.
	 */
	protected static void init() {
		SAXReader reader = new SAXReader();

		try {
			Document xmlDocument = reader.read(Key.KEY_PROTOCOLSTORE_FILE);
			Element root = xmlDocument.getRootElement();

			for (Iterator<?> it = root.elementIterator("protocol"); it
					.hasNext();) {
				Element prot = (Element) it.next();

				String id = prot.attributeValue("id");
				String fName = prot.attributeValue("file");
				File file = new File(Key.KEY_PROTOCOLSTORE_PATH
						.getAbsolutePath()
						+ "/" + fName);

				installedAddons.add(new ProtocolDescriptor(id, file, true));
			}
		} catch (DocumentException ex) {
			// ex.printStackTrace();
		}

		init = true;
	}

	/**
	 * Installs an add-on.
	 * 
	 * @param descriptor
	 *            protocol descriptor
	 */
	public static void installAddon(ProtocolDescriptor descriptor) {
		if (!init)
			init();

		if (installedAddons.contains(descriptor)) {
			String name = descriptor.getName();
			String id = descriptor.getId();

			name += "*";
			id += "X";

			descriptor.setName(name);
			descriptor.setId(id);
		}

		installedAddons.add(descriptor);

		fireAddedEvent(descriptor);
	}

	/**
	 * Removes the add-on specified by the descriptor.
	 * 
	 * @param descriptor
	 *            protocol descriptor
	 */
	public static void removeAddon(ProtocolDescriptor descriptor) {
		if (!init)
			init();

		installedAddons.remove(descriptor);
		fireRemovedEvent(descriptor);
	}

	/**
	 * Saves the add-ons protocols.
	 */
	public static void save() {
		if (!init)
			init();

		Document xmlDocument = DocumentHelper.createDocument();

		Element root = xmlDocument.addElement("protocolstore");

		int id = 1;

		for (ProtocolDescriptor td : installedAddons) {
			if (!td.storable) {
				continue;
			}

			Element protocol = root.addElement("protocol");
			protocol.addAttribute("id", "" + (id++));
			protocol.addAttribute("file", td.getFile().getName());

			td.saveDescriptor();
		}

		try {
			OutputFormat format = OutputFormat.createPrettyPrint();

			if (!Key.KEY_PROTOCOLSTORE_FILE.exists()) {
				Key.KEY_PROTOCOLSTORE_FILE.getParentFile().mkdirs();
				Key.KEY_PROTOCOLSTORE_FILE.createNewFile();
			}

			XMLWriter writer = new XMLWriter(new FileWriter(
					Key.KEY_PROTOCOLSTORE_FILE), format);
			writer.write(xmlDocument);
			writer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Returns a vector containing all installed chains.
	 * 
	 * @return vector containing all installed chains
	 */
	public static List<ProtocolDescriptor> getInstalledAddons() {
		if (!init)
			init();

		return installedAddons;
	}

	/**
	 * Proofs if the store knows an add-on with the given name.
	 * 
	 * @param name
	 *            name of an add-on
	 * @return true or false
	 */
	public static boolean hasAddon(String name) {
		if (!init) {
			init();
		}

		for (int i = 0; i < installedAddons.size(); i++) {
			ProtocolDescriptor descriptor = installedAddons.get(i);

			if (((Standardprotocol)descriptor.getProtocol())
					.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Adds the listener to the store.
	 * 
	 * @param listener
	 */
	public static void addStoreListener(ProtocolStoreListener listener) {
		storeListener.add(listener);
	}

	/**
	 * Removes the listener from the store.
	 * 
	 * @param listener
	 */
	public static void removeStoreListener(ProtocolStoreListener listener) {
		storeListener.remove(listener);
	}

	/**
	 * Fires the protocol added event.
	 * 
	 * @param pd
	 *            protocol descriptor
	 */
	private static void fireAddedEvent(ProtocolDescriptor pd) {
		for (ProtocolStoreListener l : storeListener)
			l.protocolAdded(pd);
	}

	/**
	 * Fires the protocol removed event.
	 * 
	 * @param pd
	 *            protocol descriptor
	 */
	private static void fireRemovedEvent(ProtocolDescriptor pd) {
		for (ProtocolStoreListener l : storeListener)
			l.protocolRemoved(pd);
	}
}

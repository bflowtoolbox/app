package org.bflow.toolbox.hive.nls;

import java.io.InputStream;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;
import org.eclipse.core.runtime.Platform;

/**
 * Provides a basic internationalization feature.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 07/12/09
 * @version 15/12/13
 * 
 */
public class NLUtil {
	/**
	 * Contains all installed language abbreviations.
	 */
	public static String installedAbbreviations[] = { "de_DE", "en_US" };

	/**
	 * Initializes the message provider. Installs all know message maps.
	 */
	public static void init() {
		for (String abbr : installedAbbreviations)
			installMessageMap(abbr, new NLUtil().getClass()
					.getResourceAsStream(
							"/files/" + abbr + ".msg"));

		init = true;
	}

	/**
	 * Returns the message selected by the message id and actually selected
	 * language. Least is selected by the preferences dialog.
	 * 
	 * @param msgId
	 *            id of the language
	 * @return Message
	 */
	@Deprecated
	public static String getMessage(String msgId) {
		if (!isInitialized())
			init();

		String abbr = NLUtil.getActiveLanguageAbbreviation();
		
		if(languageMap.containsKey(abbr))
			return languageMap.get(abbr).get(msgId);
		else
			return languageMap.get("en_US").get(msgId);
	}

	/**
	 * Returns the active platform language abbreviation, e.g. "en_US", "de_DE", ...
	 * 
	 * @return active platform language abbreviation, e.g. "en_US", "de_DE",...
	 */
	public static String getActiveLanguageAbbreviation() {
		return Platform.getNL();		
	}

	/**
	 * Installs a message map.
	 * 
	 * @param abbreviation
	 *            abbreviation of the map, e.g. "en_US"
	 * @param stream
	 *            input stream
	 */
	@Deprecated
	public static void installMessageMap(String abbreviation, InputStream stream) {
		String fileContent = null;

		try {
			fileContent = IOUtils.toString(stream);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (fileContent == null)
			return;

		HashMap<String, String> msgMap = new HashMap<String, String>();

		for (String line : fileContent.split(";")) {
			line = line.replace("\n", "");
			line = line.replace("\r", "");
			line = line.replace("\\n", "\n");

			String msg[] = line.split("=");

			msgMap.put(msg[0], msg[1]);
		}

		languageMap.put(abbreviation, msgMap);
	}

	/**
	 * Returns true if the provider has been initialized.
	 * 
	 * @return true if the provider has been initialized.
	 */
	@Deprecated
	public static boolean isInitialized() {
		return init;
	}

	/**
	 * Adds a HashMap of messages to the message provider.<br/>
	 * If you want to install a message which has the same id as an already
	 * installed one, an exception will be thrown.
	 * 
	 * @param abbreviation
	 *            language abbreviation, e.g. "en_US"
	 * @param msgMap
	 *            HashMap
	 * @throws Exception
	 *             if a message with the same id has been installed earlier.
	 */
	@Deprecated
	public static void addMessages(String abbreviation,
			HashMap<String, String> msgMap) throws Exception {
		if (!isInitialized())
			init();

		HashMap<String, String> map = null;

		boolean nu = false;

		if (languageMap.containsKey(abbreviation))
			map = languageMap.get(abbreviation);

		if (map == null) {
			map = new HashMap<String, String>();
			nu = true;
		}

		for (String key : msgMap.keySet())
			if (!nu && map.containsKey(key))
				throw new Exception("id: " + key + " already exists!");
			else
				map.put(key, msgMap.get(key));

		if (nu)
			languageMap.put(abbreviation, map);
	}

	/**
	 * Proofs, if a message specified by the id is already registered.
	 * @param abbreviation abbreviation
	 * @param id id
	 * @return true or false
	 */
	@Deprecated
	public static boolean isInstalled(String abbreviation, String id) {
		if (!languageMap.containsKey(abbreviation))
			return false;

		if (!languageMap.get(abbreviation).containsKey(id))
			return false;

		return true;
	}

	private static HashMap<String, HashMap<String, String>> languageMap = new HashMap<String, HashMap<String, String>>();

	private static boolean init = false;
}

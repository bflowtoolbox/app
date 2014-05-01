package org.bflow.toolbox.extensions.internationalisation;

import java.io.InputStream;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;
import org.bflow.toolbox.check.CheckPlugin;

/**
 * Provides a basic internationalisation feature.
 * @author Arian Storch
 * @since 07/12/09
 * @version 18/02/10
 * @deprecated Switched to OSGI-NLS
 */
public class MessageProvider 
{		
	/**
	 * Contains all installed language abbreviations.
	 */
	public static String installedAbbreviations[] = { "de", "en" };
	
	/**
	 * Initialise the message provider. Installs all know message maps.
	 */
	public static void init()
	{		
		for(String abbr:installedAbbreviations)
			installMessageMap(abbr, new MessageProvider().getClass().getResourceAsStream(
										"/org/bflow/toolbox/extensions/internationalisation/messages/"+abbr+".msg"));
		
		init = true;
	}
	
	/**
	 * Returns the message selected by the message id and actually selected language. Least is selected by
	 * the preferences dialog.
	 * @param msgId id of the language
	 * @return Message
	 */
	public static String getMessage(String msgId)
	{
		if(!isInitialised())
			init();
		
		return languageMap.get(CheckPlugin.getInstance().getActiveLanguage()).get(msgId);
	}
	
	/**
	 * Returns the active language abbreviation, e.g. "en", "de", ...
	 * @return active language abbreviation, e.g. "en", "de",...
	 */
	public static String getActiveLanguageAbbreviation()
	{
		return CheckPlugin.getInstance().getActiveLanguage();
	}
	
	/**
	 * Installs a message map.
	 * @param abbreviation abbreviation of the map
	 * @param stream input stream
	 */
	public static void installMessageMap(String abbreviation, InputStream stream)
	{
		String fileContent = null;
		
		try 
		{
			fileContent = IOUtils.toString(stream);
		} 
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		if(fileContent == null)
			return ;
		
		HashMap<String, String> msgMap = new HashMap<String, String>();
		
		for(String line:fileContent.split(";"))
		{
			line = line.replace("\n", "");
			line = line.replace("\r", "");
			line = line.replace("\\n", "\n");
			
			String msg[] = line.split("=");
			
			msgMap.put(msg[0], msg[1]);
		}
		
		languageMap.put(abbreviation, msgMap);
	}

	/**
	 * Returns true if the provider has been initialised.
	 * @return true if the provider has been initialised. 
	 */
	public static boolean isInitialised()
	{
		return init;
	}
	
	/**
	 * Adds a HashMap of messages to the message provider.<br/>
	 * If you want to install a message which has the same id as an already installed one, an exception
	 * will be thrown.
	 * @param abbreviation language abbreviation 
	 * @param msgMap HashMap
	 * @throws Exception if a message with the same id has been installed earlier.
	 */
	public static void addMessages(String abbreviation, HashMap<String, String> msgMap) throws Exception
	{
		if(!isInitialised())
			init();
		
		HashMap<String, String> map = null;
		
		boolean nu = false;
		
		if(languageMap.containsKey(abbreviation))
			map = languageMap.get(abbreviation);
		
		if(map == null)
		{
			map = new HashMap<String, String>();
			nu = true;
		}
		
		for(String key:msgMap.keySet())
			if(!nu && map.containsKey(key))
				throw new Exception("id: "+key+" already exists!");
			else
				map.put(key, msgMap.get(key));
		
		if(nu)
			languageMap.put(abbreviation, map);
	}
	
	public static boolean isInstalled(String abbreviation, String id)
	{
		if(!languageMap.containsKey(abbreviation))
			return false;
		
		if(!languageMap.get(abbreviation).containsKey(id))
			return false;
		
		return true;
	}
	
	private static HashMap<String, HashMap<String, String>> languageMap = new HashMap<String, HashMap<String, String>>();
	
	private static boolean init = false;
}

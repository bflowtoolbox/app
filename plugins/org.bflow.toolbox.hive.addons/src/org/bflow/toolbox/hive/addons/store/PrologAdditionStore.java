package org.bflow.toolbox.hive.addons.store;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.bflow.toolbox.hive.addons.utils.PrologAddition;

/**
 * Provides a prolog addition store.
 * @author Arian Storch
 * @since 07/06/11
 */
public class PrologAdditionStore {
	
	/**
	 * static collection
	 */
	private static List<PrologAddition> depository = new ArrayList<PrologAddition>();
	
	/**
	 * Registers the prolog addition to the store.
	 * @param id unique id of the addition
	 * @param name name of the addition
	 * @param url url
	 */
	public static void register(String id, String name, URL url) {
		PrologAddition add = new PrologAddition();
		add.id = id;
		add.url = url;
		add.name = name;
		
		depository.add(add);
	}
	
	/**
	 * Returns the url of the addition file.
	 * @param id id of the addition
	 * @return url or null
	 */
	public static URL getURL(String id) {
		for(PrologAddition add:depository) {
			if(add.id.equalsIgnoreCase(id))
				return add.url;
		}
		
		return null;
	}

}

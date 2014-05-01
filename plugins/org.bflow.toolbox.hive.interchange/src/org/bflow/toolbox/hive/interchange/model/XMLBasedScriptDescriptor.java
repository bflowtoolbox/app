package org.bflow.toolbox.hive.interchange.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bflow.toolbox.hive.interchange.mif.core.IScriptDescriptor;

/**
 * defines a script object used by the ExportDescription Object
 * 
 * @author Arian Storch
 * @since 14/08/09
 * @version 03/10/12
 * 
 */
public class XMLBasedScriptDescriptor implements IScriptDescriptor {
	private String file;
	private HashMap<String, String> params = new HashMap<String, String>();

	/**
	 * constructor
	 */
	public XMLBasedScriptDescriptor() {
	}

	/**
	 * constructor
	 * 
	 * @param file
	 *            name of the file including path
	 */
	public XMLBasedScriptDescriptor(String file) {
		this.file = file;
	}

	/**
	 * constructor
	 * 
	 * @param file
	 *            name of the file incl. path
	 * @param params
	 *            hash map of params
	 */
	public XMLBasedScriptDescriptor(String file, HashMap<String, String> params) {
		this.file = file;
		this.params = params;
	}

	/**
	 * returns the file including path of the file
	 */
	public String getFile() {
		return file;
	}

	/**
	 * sets the file
	 * 
	 * @param file
	 *            name of the file including path
	 */
	public void setFile(String file) {
		this.file = file;
	}

	/**
	 * returns the hash map with the params of the script
	 */
	public HashMap<String, String> getParams() {
		return params;
	}

	@Override
	public String toString() {
		return "[Skript] File: " + file + " Params: " + params;
	}

	@Override
	public Map<String, Object> getParameters() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		for (Entry<String,String> entry : params.entrySet()) {
			map.put(entry.getKey(), entry.getValue());
		}
		
		return map;
	}

	@Override
	public String getPath() {
		return file;
	}

}

package org.bflow.toolbox.hive.addons.utils;

/**
 * Defines a class that describes tools used by the protocol.
 * @author Arian Storch
 * @since 17/04/10
 * @version 28/11/10
 *
 */
public class ToolDescriptor 
{
	private String name;
	private String path;
	private String param;
	
	/**
	 * Constructor.
	 * @param name
	 * @param path
	 * @param param
	 */
	public ToolDescriptor(String name, String path, String param) {
		super();
		this.name = name;
		this.path = path;
		this.param = param;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the param
	 */
	public String getParam() {
		return param;
	}

	/**
	 * @param param the param to set
	 */
	public void setParam(String param) {
		this.param = param;
	}
	
	/**
	 * Returns true if the tool is valid and can be used.
	 * @return true or false
	 */
	public boolean isValid() {
		if(name != null && path != null && !path.isEmpty())
			return true;
		else
			return false;
	}	
}

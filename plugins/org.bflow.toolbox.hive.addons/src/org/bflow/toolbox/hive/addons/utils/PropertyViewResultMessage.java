package org.bflow.toolbox.hive.addons.utils;

import org.bflow.toolbox.hive.addons.interfaces.IPropertyViewResultMessage;

/**
 * This class defines a message which can be handled by the Bflow Hive Property View.
 * 
 * @author Markus Schnädelbach
 */
public class PropertyViewResultMessage implements IPropertyViewResultMessage
{		
	private Boolean result;
	
	/**
	 * Constructor.
	 * @param result
	 */
	public PropertyViewResultMessage(Boolean result) {
		super();
		this.result = result;
	}
	
	/**
	 * @return the type
	 */
	public Boolean getResult() {
		return result;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setResult(Boolean result) {
		this.result = result;
	}
}
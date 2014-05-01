package org.bflow.toolbox.hive.addons.utils;

import org.bflow.toolbox.hive.addons.interfaces.IAttributeAdjustMessage;
import org.bflow.toolbox.hive.attributes.IAttribute;

/**
 * Implements the {@link IAttributeAdjustMessage}.
 * 
 * @author Arian Storch
 * @since 11/10/10
 * @version 28/07/12
 */
public class AttributeAdjustMessage implements IAttributeAdjustMessage {
	private int type;
	private String elementID;
	private String attrName;
	private String value;
	
	/**
	 * Constructor.
	 * @param type
	 * @param elementID
	 * @param value
	 */
	public AttributeAdjustMessage(int type, String elementID, String attrName, String value) {
		super();
		this.type = type;
		this.elementID = elementID;
		this.attrName = attrName;
		this.value = value;
	}
	
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	
	/**
	 * @return the elementID
	 */
	public String getElementID() {
		return elementID;
	}
	
	/**
	 * @param elementID the elementID to set
	 */
	public void setElementID(String elementID) {
		this.elementID = elementID;
	}
	
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the attrName
	 */
	public String getAttrName() {
		return attrName;
	}

	/**
	 * @param attrName the attrName to set
	 */
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	
	/**
	 * Wraps the attribute adjust message into an IAttribute object.
	 * @return IAttribute object
	 */
	public IAttribute getAttribute() {
		return new IAttribute(){

			@Override
			public String getElementID() {
				return elementID;
			}

			@Override
			public String getName() {
				return attrName;
			}

			@Override
			public String getValue() {
				if(type == IAttributeAdjustMessage.TYPE_REMOVE ||
						type == IAttributeAdjustMessage.TYPE_XREMOVE)
					return null;
				else
					return value;
			}

			@Override
			public boolean isLoner() {
				if(type == IAttributeAdjustMessage.TYPE_XADD ||
						type == IAttributeAdjustMessage.TYPE_XREMOVE)
					return false;
				else
					return true;
			}
			
			@Override
			public String toString() {
				String line = String.format("Name: %s, Value: %s, ProxyId: %s", 
						getName(), getValue(), getElementID());
				
				return line;
			}
			
			};
	}
		
}

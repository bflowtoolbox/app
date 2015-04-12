package org.bflow.toolbox.hive.attributes;

/**
 * Defines a listener to changes of an attribute file.
 * 
 * @author Arian Storch<arian.storch@blfow.org>
 * @since 11.04.15
 * 
 */
public interface IAttributeFileListener {

	/**
	 * Tells the listener that an attribute with the given name and value has
	 * been added to the given model element.
	 * 
	 * @param modelElementId
	 *            Id of the model element
	 * @param attributeName
	 *            Name of the newly created attribute
	 * @param attributeValue
	 *            Value of the newly created attribute
	 */
	void onAttributeAdded(String modelElementId, String attributeName, String attributeValue);
	
	/**
	 * Tells the listener that the given attribute has been removed from the
	 * given model element.
	 * 
	 * @param modelElementId
	 *            Id of the model element
	 * @param attributeName
	 *            Name of the removed attribute
	 */
	void onAttributeRemoved(String modelElementId, String attributeName);
	
	/**
	 * Tells the listener that all attributes of the given model element have
	 * been removed.
	 * 
	 * @param modelElementId
	 *            Id of the model element
	 */
	void onAttributesRemoved(String modelElementId);
	
}

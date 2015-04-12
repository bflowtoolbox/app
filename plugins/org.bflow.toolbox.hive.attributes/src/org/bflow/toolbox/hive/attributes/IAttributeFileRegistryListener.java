package org.bflow.toolbox.hive.attributes;

/**
 * Defines the interface for a Attribute File Registry Listener.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 17.07.11
 * 
 */
public interface IAttributeFileRegistryListener {
	
	/**
	 * Notice the listener that the Attribute File has changed.
	 * 
	 * @param event
	 *            event
	 */
	public void noticeAttributeFileChange(AttributeFileRegistryEvent event);

}

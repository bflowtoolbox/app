package org.bflow.toolbox.hive.addons.events;

import org.bflow.toolbox.hive.addons.utils.ProtocolDescriptor;

/**
 * Defines a protocol store listener interface. 
 * @author Arian Storch
 * @since 02/10/10
 *
 */
public interface ProtocolStoreListener {

	/**
	 * Is invoked when a protocol has been added.
	 * @param pd new protocol
	 */
	public void protocolAdded(ProtocolDescriptor pd);
	
	/**
	 * Is invoked when a protocol has been deleted.
	 * @param pd deleted protocol
	 */
	public void protocolRemoved(ProtocolDescriptor pd);
	
}

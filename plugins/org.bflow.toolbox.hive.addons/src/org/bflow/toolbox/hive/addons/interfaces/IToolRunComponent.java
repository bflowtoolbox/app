package org.bflow.toolbox.hive.addons.interfaces;

import org.bflow.toolbox.hive.addons.core.model.IComponent;
import org.bflow.toolbox.hive.addons.core.model.Protocol;
import org.bflow.toolbox.hive.addons.utils.ToolDescriptor;

/**
 * <p>Defines the interface to the ToolRunComponent.</p>
 * <p><b>Important note:</b> You have to set a ToolDescriptor and Protocol else you will get a NullPointerException!</p>
 * @author Arian Storch
 * @since 05/05/10
 *
 */
public interface IToolRunComponent extends IComponent 
{
	/**
	 * Sets the tool descriptor.
	 * @param td tool descriptor
	 */
	public void setToolDescriptor(ToolDescriptor td);
	
	/**
	 * Sets the protocol.
	 * @param protocol protocol
	 */
	public void setProtocol(Protocol protocol);
}

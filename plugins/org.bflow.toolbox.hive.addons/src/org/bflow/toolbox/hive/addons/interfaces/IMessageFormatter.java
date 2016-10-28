package org.bflow.toolbox.hive.addons.interfaces;

import org.bflow.toolbox.hive.addons.core.exceptions.ComponentException;
import org.eclipse.core.resources.IFile;

/**
 * Defines a formatter for Add-on messages. The task of a formatter is to create
 * an instance of {@link IAddonMessage} based on the provided values. Each
 * formatter is associated with a specific message type. When an Add-on analysis
 * component receives a message, the corresponding message formatter is notify
 * to derive an {@link IAddonMessage}.
 * 
 * @author Arian Storch (arian.storch@bflow.org)
 * @since 28.10.16
 * 
 */
public interface IMessageFormatter {
	
	/**
	 * Notifies the instance to create an instance of {@link IAddonMessage}
	 * based on the given data.
	 * 
	 * @param contents
	 *            Received message content parts
	 * @param markerResource
	 *            Associated marker resource
	 * @return Created instance of {@link IAddonMessage}
	 */
	IAddonMessage format(String[] contents, IFile markerResource) throws ComponentException;

}

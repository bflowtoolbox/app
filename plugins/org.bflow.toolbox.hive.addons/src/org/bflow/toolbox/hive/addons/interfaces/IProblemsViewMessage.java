package org.bflow.toolbox.hive.addons.interfaces;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;

/**
 * Defines the interface for an eclipse problems view message.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 06/05/10
 * @version 01/06/13
 * 
 */
public interface IProblemsViewMessage extends IAddonMessage {
	/**
	 * flag that indicates the warning type
	 */
	public static int TYPE_WARNING = IStatus.WARNING;

	/**
	 * flag that indicates the info type
	 */
	public static int TYPE_INFO = IStatus.INFO;

	/**
	 * flag that indicates the error type
	 */
	public static int TYPE_ERROR = IStatus.ERROR;

	/**
	 * Returns the message type. Look at the class constants.
	 * 
	 * @return the messageType
	 */
	public int getMessageType();

	/**
	 * Sets the message type. Look at the class constants.
	 * 
	 * @param messageType
	 *            the messageType to set
	 */
	public void setMessageType(int messageType);

	/**
	 * Returns the Description of the message.
	 * 
	 * @return the description
	 */
	public String getDescription();

	/**
	 * Sets the description of the message.
	 * 
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description);

	/**
	 * Returns the element id.
	 * 
	 * @return the elementID
	 */
	public String getElementID();

	/**
	 * Sets the element id.
	 * 
	 * @param elementID
	 *            the elementID to set
	 */
	public void setElementID(String elementID);

	/**
	 * Returns the resource that is connected with the message.
	 * 
	 * @return the resource
	 */
	public IFile getResource();

	/**
	 * Sets the resource that is connected with the message.
	 * 
	 * @param ressource
	 *            the ressource to set
	 */
	public void setResource(IFile ressource);

	/**
	 * Returns the path.
	 * 
	 * @return the path
	 */
	public String getPath();

	/**
	 * Sets the path.
	 * 
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path);

	/**
	 * Returns the location.
	 * 
	 * @return the location
	 */
	public String getLocation();

	/**
	 * Sets the location.
	 * 
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location);
	
	/**
	 * Returns the miscellaneous informations.
	 * 
	 * @return the miscellaneous informations
	 * 			
	 */
	public String getMiscellaneous();
	
	/**
	 * Sets the miscellaneous informations.
	 * 
	 * @param misc
	 * 			the miscellaneous informations
	 */
	public void setMiscellaneous(String misc);

	@Override
	public boolean equals(Object obj);

}
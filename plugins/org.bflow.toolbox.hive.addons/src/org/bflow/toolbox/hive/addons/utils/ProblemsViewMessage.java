package org.bflow.toolbox.hive.addons.utils;

import org.bflow.toolbox.hive.addons.interfaces.IProblemsViewMessage;
import org.eclipse.core.resources.IFile;

/**
 * This class defines different types of messages which can be handled by the Eclipse Problems View.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 12/10/09
 * @version 04/06/13
 */
public class ProblemsViewMessage implements IProblemsViewMessage
{		
	private int messageType;
	private String elementID;
	private String description;
	private IFile resource;
	private String path;
	private String location;
	private String miscellaneous;
	
	/**
	 * Default constructor.
	 * @param messageType Type of message. Look at the class constants.
	 * @param description Message that is shown in the Problems View.
	 * @param resource Resource that is connected with the message.
	 * @param path Path information
	 * @param location Location information
	 */
	public ProblemsViewMessage(int messageType, String elementID, String description,
			IFile resource, String path, String location) {
		super();
		this.elementID = elementID;
		this.messageType = messageType;
		this.description = description;
		this.resource = resource;
		this.path = path;
		this.location = location;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.mitamm.utils.IProblemsViewMessage#getMessageType()
	 */
	public int getMessageType() {
		return messageType;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.mitamm.utils.IProblemsViewMessage#setMessageType(int)
	 */
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.mitamm.utils.IProblemsViewMessage#getDescription()
	 */
	public String getDescription() {
		return description;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.mitamm.utils.IProblemsViewMessage#setDescription(java.lang.String)
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.mitamm.utils.IProblemsViewMessage#getElementID()
	 */
	public String getElementID() {
		return elementID;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.mitamm.utils.IProblemsViewMessage#setElementID(java.lang.String)
	 */
	public void setElementID(String elementID) {
		this.elementID = elementID;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.mitamm.utils.IProblemsViewMessage#getResource()
	 */
	public IFile getResource() {
		return resource;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.mitamm.utils.IProblemsViewMessage#setResource(org.eclipse.core.resources.IFile)
	 */
	public void setResource(IFile ressource) {
		this.resource = ressource;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.mitamm.utils.IProblemsViewMessage#getPath()
	 */
	public String getPath() {
		return path;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.mitamm.utils.IProblemsViewMessage#setPath(java.lang.String)
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.mitamm.utils.IProblemsViewMessage#getLocation()
	 */
	public String getLocation() {
		return location;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.mitamm.utils.IProblemsViewMessage#setLocation(java.lang.String)
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.interfaces.IProblemsViewMessage#getMiscellaneous()
	 */
	public String getMiscellaneous() {
		return miscellaneous;
	}

	/* (non-Javadoc)
	 * @see org.bflow.toolbox.addons.interfaces.IProblemsViewMessage#setMiscellaneous(java.lang.String)
	 */
	public void setMiscellaneous(String miscellaneous) {
		this.miscellaneous = miscellaneous;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof IProblemsViewMessage))
			return false;
		
		IProblemsViewMessage msg = (IProblemsViewMessage)obj;
		
		if(!msg.getDescription().equalsIgnoreCase(this.description))
			return false;
		
		if(!msg.getElementID().equalsIgnoreCase(this.elementID))
			return false;
		
		if(!msg.getLocation().equalsIgnoreCase(this.location))
			return false;
		
		if(msg.getMessageType() != this.messageType)
			return false;
		
		if(!msg.getPath().equals(this.path))
			return false;
		
		if(msg.getResource() != this.resource)
			return false;
		
		return true;
	}
	
	@Override
	public String toString() 
	{
		return "[ProblemsViewMessage] Type: "+messageType+" elementID: "+elementID+" description: "+description+" resource: "+resource+" path: "+path+" location: "+location;
	}
	
}
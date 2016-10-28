package org.bflow.toolbox.hive.addons.messageformatters;

import org.bflow.toolbox.hive.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.hive.addons.interfaces.IAddonMessage;
import org.bflow.toolbox.hive.addons.interfaces.IAttributeAdjustMessage;
import org.bflow.toolbox.hive.addons.interfaces.IMessageFormatter;
import org.bflow.toolbox.hive.addons.utils.AttributeAdjustMessage;
import org.eclipse.core.resources.IFile;

/**
 * Provides an implementation of {@link IMessageFormatter} for the message type
 * 'attribute'.
 * 
 * @author Arian Storch (arian.storch@bflow.org)
 * @since 28.10.16
 * 
 */
public class AttributeMessageFormatter implements IMessageFormatter {
	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.hive.addons.interfaces.IMessageFormatter#format(java.lang.String[], org.eclipse.core.resources.IFile)
	 */
	@Override
	public IAddonMessage format(String[] contents, IFile markerResource) throws ComponentException {
		if (contents.length != 5) throw new ComponentException("Attribute has unexpected number of records");
		
		String type = contents[1];
		String id = contents[2];
		String name = contents[3];
		String value = contents[4];
		
		int msgType = resolveType(type);
		
		return new AttributeAdjustMessage(msgType, id, name, value);
	}
	
	/**
	 * Resolves the attribute adjust message type.
	 * 
	 * @param str
	 *            string to check
	 * @return message type
	 * @throws ComponentException
	 */
	private int resolveType(String str) throws ComponentException {
		if (str.equalsIgnoreCase("add")) return IAttributeAdjustMessage.TYPE_ADD;
		if (str.equalsIgnoreCase("xadd")) return IAttributeAdjustMessage.TYPE_XADD;
		if (str.equalsIgnoreCase("remove"))return IAttributeAdjustMessage.TYPE_REMOVE;
		if (str.equalsIgnoreCase("xremove")) return IAttributeAdjustMessage.TYPE_XREMOVE;
		if (str.equalsIgnoreCase("set")) return IAttributeAdjustMessage.TYPE_SET;
		
		throw new ComponentException("attribute adjust type "+str+" is not known");
	}

}

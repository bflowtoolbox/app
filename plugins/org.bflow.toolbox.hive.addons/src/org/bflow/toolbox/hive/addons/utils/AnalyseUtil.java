package org.bflow.toolbox.hive.addons.utils;

import org.bflow.toolbox.hive.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.hive.addons.interfaces.IAddonMessage;
import org.bflow.toolbox.hive.addons.interfaces.IAttributeAdjustMessage;
import org.eclipse.core.resources.IFile;

/**
 * Utility class that analyzes data streams.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 14/06/11
 * @version 04/06/13
 */
public class AnalyseUtil {

	/**
	 * Analyzes a stream that begins with [MESSAGE].
	 * 
	 * @param contents string array without []
	 * @param markerResource 
	 * @return {@link ProblemsViewMessage}
	 * @throws ComponentException
	 */
	public static IAddonMessage analyseMessage(String contents[], IFile markerResource) throws ComponentException {
		if(contents.length != 5)
			throw new ComponentException("Message has unexpected number of records");
		
		String type = contents[1];
		
		String id = contents[2];
		String msg = contents[3];
		String misc = null;
		
		if(contents.length == 5)
			misc = contents[4];

		int messageType = (type.equalsIgnoreCase("INFO") ? ProblemsViewMessage.TYPE_INFO
				: (type.equalsIgnoreCase("ERROR") ? ProblemsViewMessage.TYPE_ERROR
						: ProblemsViewMessage.TYPE_WARNING));

		ProblemsViewMessage pvm = new ProblemsViewMessage(messageType,
									id, msg, markerResource, markerResource.getLocation()
											.toFile().getName(), markerResource
											.getLocation().toFile().getParent());
		
		// Add the miscellaneous informations if there are some
		if(misc != null)
			pvm.setMiscellaneous(misc);
		
		return pvm;
	}
	
	/**
	 * Analyzes a stream that begins with [ATTRIBUTE].
	 * @param contents string array without []
	 * @return {@link AttributeAdjustMessage}
	 * @throws ComponentException
	 */
	public static IAddonMessage analyseAttribute(String contents[]) throws ComponentException {
		if(contents.length != 5)
			throw new ComponentException("Attribute has unexpected number of records");
		
		String type = contents[1];
		String id = contents[2];
		String name = contents[3];
		String value = contents[4];
		
		int msgType = resolveType(type);
		
		return new AttributeAdjustMessage(msgType, id, name, value);
	}
	
	/**
	 * Resolves the attribute adjust message type.
	 * @param str string to check
	 * @return message type
	 * @throws ComponentException
	 */
	private static int resolveType(String str) throws ComponentException {
		
		if(str.equalsIgnoreCase("add"))
			return IAttributeAdjustMessage.TYPE_ADD;
		
		if(str.equalsIgnoreCase("xadd")) {
			return IAttributeAdjustMessage.TYPE_XADD;
		}
		
		if(str.equalsIgnoreCase("remove")) {
			return IAttributeAdjustMessage.TYPE_REMOVE;
		}
		
		if(str.equalsIgnoreCase("xremove")) {
			return IAttributeAdjustMessage.TYPE_XREMOVE;
		}
		
		if(str.equalsIgnoreCase("set")) {
			return IAttributeAdjustMessage.TYPE_SET;
		}
		
		throw new ComponentException("attribute adjust type "+str+" is not known");
	}
	
}

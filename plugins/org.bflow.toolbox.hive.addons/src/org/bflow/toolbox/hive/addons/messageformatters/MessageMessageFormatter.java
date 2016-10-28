package org.bflow.toolbox.hive.addons.messageformatters;

import org.bflow.toolbox.hive.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.hive.addons.interfaces.IAddonMessage;
import org.bflow.toolbox.hive.addons.interfaces.IMessageFormatter;
import org.bflow.toolbox.hive.addons.utils.ProblemsViewMessage;
import org.eclipse.core.resources.IFile;

/**
 * Provides an implementation of {@link IMessageFormatter} for the message type
 * 'message'.
 * 
 * @author Arian Storch (arian.storch@bflow.org)
 * @since 28.10.16
 * 
 */
public class MessageMessageFormatter implements IMessageFormatter {

	/*
	 * (non-Javadoc)
	 * @see org.bflow.toolbox.hive.addons.interfaces.IMessageFormatter#format(java.lang.String[], org.eclipse.core.resources.IFile)
	 */
	@Override
	public IAddonMessage format(String[] contents, IFile markerResource) throws ComponentException {
		if (contents.length != 5) throw new ComponentException("Message has unexpected number of records");
		
		String type = contents[1];
		
		String id = contents[2];
		String msg = contents[3];
		String misc = null;
		
		if(contents.length == 5)
			misc = contents[4];

		int messageType = (type.equalsIgnoreCase("INFO") ? ProblemsViewMessage.TYPE_INFO
				: (type.equalsIgnoreCase("ERROR") ? ProblemsViewMessage.TYPE_ERROR
						: ProblemsViewMessage.TYPE_WARNING));

		ProblemsViewMessage problemsViewMessage = new ProblemsViewMessage(messageType,
									id, msg, markerResource, markerResource.getLocation()
											.toFile().getName(), markerResource
											.getLocation().toFile().getParent());
		
		// Add the miscellaneous informations if there are some
		if (misc != null)
			problemsViewMessage.setMiscellaneous(misc);
		
		return problemsViewMessage;
	}

}

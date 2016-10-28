package org.bflow.toolbox.hive.statement.addons.contributions;

import org.bflow.toolbox.hive.addons.core.exceptions.ComponentException;
import org.bflow.toolbox.hive.addons.interfaces.IAddonMessage;
import org.bflow.toolbox.hive.addons.interfaces.IMessageFormatter;
import org.eclipse.core.resources.IFile;

/**
 * Provides an implementation of {@link IMessageFormatter} for the message type
 * 'property'.
 * 
 * @author Markus Schnädelbach
 * @since 28.10.16
 * 
 */
public class PropertyMessageFormatter implements IMessageFormatter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bflow.toolbox.hive.addons.interfaces.IMessageFormatter#format(java
	 * .lang.String[], org.eclipse.core.resources.IFile)
	 */
	@Override
	public IAddonMessage format(String[] contents, IFile markerResource) throws ComponentException {
		if (contents.length != 5) throw new ComponentException("PropertyResult has unexpected number of records");

		String result = contents[2];
		Boolean propertyResult = resolvePropertyResult(result);

		return new PropertyViewResultMessage(propertyResult);
	}

	/**
	 * Resolves the result and returns it as Boolean.
	 * 
	 * @param str
	 *            string to check
	 * @return result as Boolean or null
	 */
	private Boolean resolvePropertyResult(String str) {
		if (str.equals("1")) {
			return true;
		} else if (str.equals("0")) {
			return false;
		} else {
			return null;
		}
	}
}

package org.bflow.toolbox.hive.addons.utils;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.bflow.toolbox.hive.addons.AddonsPlugin;
import org.bflow.toolbox.hive.addons.interfaces.IMessageFormatter;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.Platform;

/**
 * Implements a registry for contributed {@link IMessageFormatter}.
 * 
 * @author Arian Storch (arian.storch@bflow.org)
 * @since 28.10.2016
 * 
 */
public class MessageFormatterRegistry {

	private static final String MessageFormatterExtensionPointId = "org.bflow.toolbox.hive.addons.messageFormatter";
	
	/**
	 * Returns the single instance of this class. 
	 */
	public static final MessageFormatterRegistry Instance = new MessageFormatterRegistry();
	
	private HashMap<String, IMessageFormatter> fFormatterTable;
	
	/**
	 * Tells the instance to initialize itself. Though you can call this method
	 * more than once, the initialization is only done one time.
	 */
	public void initialize() {
		if (fFormatterTable != null) return;
		fFormatterTable = new HashMap<>();
		
		initializeRegistry();
	}
	
	/**
	 * Returns a formatter that is associated with the given message type. If
	 * the message type is NULL or empty, NULL is returned. If there is no
	 * registered formatter for the given message type, NULL is returned, too.
	 * 
	 * @param messageType
	 *            Message type of the formatter
	 * @return A formatter or NULL
	 */
	public IMessageFormatter getMessageFormatter(String messageType) {
		if (StringUtils.isBlank(messageType)) return null;
		messageType = messageType.toLowerCase();
		
		IMessageFormatter formatter = fFormatterTable.get(messageType);
		return formatter;
	}
	
	/**
	 * Resolves the contributed message formatters and adds them to the formatter table.
	 */
	private void initializeRegistry() {
		IConfigurationElement[] contributions = Platform.getExtensionRegistry().getConfigurationElementsFor(MessageFormatterExtensionPointId);
		for (int i = -1; ++i != contributions.length;) {
			IConfigurationElement contribution = contributions[i];
			IContributor contributor = contribution.getContributor();
			
			// Check message type
			String messageType = contribution.getAttribute("messageType");
			if (StringUtils.isBlank(messageType)) {
				String errorMessage = String.format("'%s' provides a message formatter without message type", contributor.getName());
				AddonsPlugin.getInstance().logError(errorMessage);
				continue;
			}
			
			// make invariant
			messageType = messageType.toLowerCase();
			
			// Create instance
			try {
				IMessageFormatter formatter = (IMessageFormatter) contribution.createExecutableExtension("formatter");
				fFormatterTable.put(messageType, formatter);
			} catch(CoreException ex) {
				String errorMessage = String.format("Could not create instance of formatter '%s' provided by '%s'", 
						messageType, contributor.getName());
				AddonsPlugin.getInstance().logError(errorMessage, ex);
				continue;
			}
		}
	}
}

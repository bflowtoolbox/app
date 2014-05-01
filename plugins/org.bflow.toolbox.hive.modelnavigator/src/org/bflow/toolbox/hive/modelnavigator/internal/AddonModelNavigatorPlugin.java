package org.bflow.toolbox.hive.modelnavigator.internal;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bflow.toolbox.hive.modelnavigator.INameProvider;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gmf.runtime.common.ui.services.icon.IIconProvider;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 * 
 * @author Arian Storch
 * @since 01/08/12
 */
public class AddonModelNavigatorPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.bflow.toolbox.modelnavigator";

	private static final String EXTENSION_POINT_ICON_PROVIDER_ID = "org.bflow.toolbox.modelnavigator.iconprovider";
	
	private static final String EXTENSION_POINT_NAME_PROVIDER_ID = "org.bflow.toolbox.modelnavigator.nameprovider";

	// The shared instance
	private static AddonModelNavigatorPlugin plugin;

	// The logging instance
	private static Log logger = LogFactory
			.getLog(AddonModelNavigatorPlugin.class);

	// The icon provider store
	private List<IIconProvider> installedIconProvider = new LinkedList<IIconProvider>();
	
	// The name provider store
	private List<INameProvider> installedNameProvider = new LinkedList<INameProvider>();

	/**
	 * The constructor
	 */
	public AddonModelNavigatorPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;

		registerIconProvider();
		registerNameProvider();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Installs all instances of {@link IIconProvider} that has been registered
	 * by the defined extension point.
	 */
	private void registerIconProvider() {
		IConfigurationElement elements[] = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(EXTENSION_POINT_ICON_PROVIDER_ID);

		for (int i = 0; i < elements.length; i++) {
			IConfigurationElement element = elements[i];
			String clazz = element.getAttribute("class");

			try {
				Class<?> clIconProvider = Class.forName(clazz);
				IIconProvider providerInstance = (IIconProvider) clIconProvider
						.newInstance();

				installedIconProvider.add(providerInstance);
			} catch (Exception ex) {
				String message = String.format(
						"Could not register %s as IIconProvider!", clazz);
				logError(message);
			}
		}

		if (installedIconProvider.isEmpty()) {
			logInfo("No icon providers had been registered.");
		}
	}
	
	/**
	 * Installs all instances of {@link INameProvider} that has been registered
	 * by the defined extension point.
	 */
	private void registerNameProvider() {
		IConfigurationElement elements[] = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(EXTENSION_POINT_NAME_PROVIDER_ID);

		for (int i = 0; i < elements.length; i++) {
			IConfigurationElement element = elements[i];
			String clazz = element.getAttribute("class");

			try {
				Class<?> clIconProvider = Class.forName(clazz);
				INameProvider providerInstance = (INameProvider) clIconProvider
						.newInstance();

				installedNameProvider.add(providerInstance);
			} catch (Exception ex) {
				String message = String.format(
						"Could not register %s as INameProvider!", clazz);
				logError(message);
			}
		}

		if (installedNameProvider.isEmpty()) {
			logInfo("No name providers had been registered.");
		}
		
	}

	/**
	 * Tries to find the IconProvider within the registered ones that supports
	 * the given element type. To register an instance of {@link IIconProvider}
	 * use the extension point.
	 * 
	 * @param elementType
	 *            element type that is supported by an instance of
	 *            {@link IIconProvider}
	 * @return Instance of IIconProvider or null if none could be found
	 */
	public IIconProvider getIconProvider(IElementType elementType) {
		if (elementType == null) {
			return null;
		}

		for (Iterator<IIconProvider> it = installedIconProvider.iterator(); it
				.hasNext();) {
			IIconProvider provider = it.next();

			if (provider.getIcon(elementType, 0) != null) {
				return provider;
			}
		}

		return null;
	}
	
	/**
	 * Tries to find the NameProvider within the registered ones that supports
	 * the given graphical edit part. To register an instance of {@link INameProvider}
	 * use the extension point.
	 * 
	 * @param graphicalEditPart
	 *            graphical edit part that is supported by an instance of
	 *            {@link INameProvider}
	 * @return Instance of INameProvider or null if none could be found
	 */
	public INameProvider getNameProvider(IGraphicalEditPart graphicalEditPart) {
		if (graphicalEditPart == null) {
			return null;
		}

		for (Iterator<INameProvider> it = installedNameProvider.iterator(); it
				.hasNext();) {
			INameProvider provider = it.next();

			if (provider.getName(graphicalEditPart) != null) {
				return provider;
			}
		}

		return null;
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static AddonModelNavigatorPlugin getInstance() {
		return plugin;
	}

	/**
	 * Logs an error message with a stack trace if the error level is enabled.
	 * 
	 * @param message
	 *            message to log
	 * @param ex
	 *            exception that is used for the stack trace
	 */
	public static void logError(String message, Exception ex) {
		if (logger.isErrorEnabled()) {
			logger.error(message, ex);
		}
	}

	/**
	 * Logs a debug message if the debug level is enabled.
	 * 
	 * @param message
	 *            message to log
	 */
	public static void logDebug(String message) {
		if (logger.isDebugEnabled()) {
			logger.debug(message);
		}
	}

	/**
	 * Logs an error message if the error level is enabled.
	 * 
	 * @param message
	 *            message to log
	 */
	public static void logError(String message) {
		logError(message, null);
	}

	/**
	 * Logs an info message if the info level is enabled.
	 * 
	 * @param message
	 *            message to log
	 */
	public static void logInfo(String message) {
		if (logger.isInfoEnabled()) {
			logger.info(message);
		}
	}
}

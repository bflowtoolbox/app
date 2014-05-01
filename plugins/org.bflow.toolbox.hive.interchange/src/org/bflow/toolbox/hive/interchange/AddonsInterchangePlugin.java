package org.bflow.toolbox.hive.interchange;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeDescriptor;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeProcessListener;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangePropertyProvider;
import org.bflow.toolbox.hive.interchange.mif.core.IModelBuilderAttendant;
import org.bflow.toolbox.hive.interchange.mif.core.ModelBuilderAttendantRegistry;
import org.bflow.toolbox.hive.interchange.mif.core.PropertyProviderRegistry;
import org.bflow.toolbox.hive.interchange.mif.impl.InterchangeDescriptorFactory;
import org.bflow.toolbox.hive.interchange.mif.impl.InterchangeDescriptorFactory.InterchangeDescriptorImpl;
import org.bflow.toolbox.hive.interchange.model.XMLBasedExportDescriptor;
import org.bflow.toolbox.hive.interchange.model.XMLBasedImportDescriptor;
import org.bflow.toolbox.hive.interchange.store.ExportDescriptorStore;
import org.bflow.toolbox.hive.interchange.store.ImportDescriptorStore;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @since 2011
 * @version 09/09/13
 */
@SuppressWarnings("deprecation")
public class AddonsInterchangePlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.bflow.toolbox.export.xslt";

	// The shared instance
	private static AddonsInterchangePlugin plugin;

	// logger instance
	private static Log logger = LogFactory.getLog(AddonsInterchangePlugin.class);

	/**
	 * The constructor.
	 */
	public AddonsInterchangePlugin() {
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

		registerExportDescriptors();
		registerImportDescriptors();
		registerPropertyValueProviders();
		registerModelBuilder();
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
	 * Returns the plug-in instance.
	 * 
	 * @return the plug-in instance
	 */
	public static AddonsInterchangePlugin getInstance() {
		return plugin;
	}

	/**
	 * Registers all export scripts defined by using the extension point or
	 * stored on local drive.
	 * 
	 */
	private void registerExportDescriptors() {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(EXTENSION_ID_ADDON_EXPORTSCRIPT);

		registerEPDefinedExportDescriptors(config);
		registerFileDefinedExportDescriptors();
	}

	/**
	 * Registers file defined export descriptors.
	 */
	private void registerFileDefinedExportDescriptors() {
		// Scripts on local drive
		IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();

		IPath wsRootPath = wsRoot.getLocation();

		Path exportPath = (Path) wsRootPath.append(".export/");
		File exportDir = exportPath.toFile();

		// there are manually added scripts
		if (exportDir.exists()) {
			File files[] = exportDir.listFiles(new FileFilter() {

				@Override
				public boolean accept(File arg0) {
					if (arg0.isDirectory())
						return false;

					String filename = arg0.getName();

					if (filename.contains(".exd"))
						return true;

					return false;
				}
			});

			for (File file : files) {
				try {
					IInterchangeDescriptor descriptor = null;

					FileInputStream fis = new FileInputStream(file.getAbsoluteFile());

					// handling legacy XML files
					if (file.getName().contains(".exd.xml")) {
						XMLBasedExportDescriptor xmlExp = new XMLBasedExportDescriptor(
								null, fis, file.getAbsolutePath());
						xmlExp.parseDescription();
						descriptor = xmlExp;
					} else {
						descriptor = InterchangeDescriptorFactory.parse(fis);
					}

					fis.close();

					if (descriptor != null)
						ExportDescriptorStore.register(descriptor, file.getAbsolutePath());
				} catch (Exception ex) {
					String message = String.format(
									"Could not register the following export script: %s",
									file.getAbsoluteFile());
					logError(message, ex);
				}
			}
		}
	}

	/**
	 * Registers an extension point defined export descriptor.
	 * 
	 * @param configurationElements
	 *            array of configuration elements
	 */
	private void registerEPDefinedExportDescriptors(IConfigurationElement configurationElements[]) {
		for (IConfigurationElement element : configurationElements) {
			String file = element.getAttribute("File");
			String strPublicFlag = element.getAttribute("public");
			String clazzName = element.getAttribute("Listener");
			boolean publicFlag = (strPublicFlag == null || strPublicFlag.isEmpty() 
									? true 
									: Boolean.parseBoolean(strPublicFlag));
			IContributor con = element.getContributor();
			Bundle bundle = Platform.getBundle(con.getName());

			IInterchangeDescriptor descriptor = null;
			try {
				InputStream is = bundle.getEntry(file).openStream();

				// XML based descriptor
				if (file.contains(".exd.xml")) {
					XMLBasedExportDescriptor xmlExp = new XMLBasedExportDescriptor(bundle, is, file);
					xmlExp.parseDescription();
					xmlExp.setPublic(publicFlag);

					descriptor = xmlExp;
				} else { // MI language based descriptor
					descriptor = InterchangeDescriptorFactory.parse(is);
					
					if(!StringUtils.isBlank(clazzName)) {
						try {
							InterchangeDescriptorImpl descriptorImpl = (InterchangeDescriptorImpl) descriptor;
							Class<?> clazz = Class.forName(clazzName);
							IInterchangeProcessListener listenerImpl = (IInterchangeProcessListener) clazz.newInstance();
							descriptorImpl.setProcessListener(listenerImpl);
						} catch(Exception ex) {
							logError(String.format("Could not set the registered listener '%s'!", clazzName), ex);
						}
					}
				}

				is.close();
			} catch (Exception ex) {
				String message = String.format(
						"Could not register the following export script: %s",
						file);
				logError(message, ex);
			}

			if (descriptor != null)
				ExportDescriptorStore.register(descriptor, bundle);
		}
	}

	/**
	 * Registers the import descriptors.
	 */
	private void registerImportDescriptors() {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(EXTENSION_ID_ADDON_IMPORTSCRIPT);

		registerEPDefinedImportDescriptors(config);
		registerFileDefinedImportDescriptors();
	}
	
	/**
	 * Registers all import descriptors that has been added by using the local file system.
	 */
	private void registerFileDefinedImportDescriptors() {
		// Scripts on local drive
		IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();

		IPath wsRootPath = wsRoot.getLocation();

		Path importPath = (Path) wsRootPath.append(".import/");
		File importDir = importPath.toFile();

		// There are manually added scripts
		if (importDir.exists())
		{
			File files[] = importDir.listFiles(new FileFilter() {

				@Override
				public boolean accept(File arg0) {
					if (arg0.isDirectory())
						return false;

					String filename = arg0.getName();

					if (filename.contains(".ixd"))
						return true;

					return false;
				}
			});

			for (File file : files) {
				try {
					IInterchangeDescriptor descriptor = null;

					// handling legacy XML files
					FileInputStream fis = new FileInputStream(file.getAbsoluteFile());
					
					if (file.getName().contains(".ixd.xml")) {
						XMLBasedImportDescriptor imp = new XMLBasedImportDescriptor(null, fis, file.getAbsolutePath());
						imp.parseDescription();
						descriptor = imp;
					} else {
						descriptor = InterchangeDescriptorFactory.parse(fis);
					}
					
					fis.close();

					if(descriptor != null)
						ImportDescriptorStore.register(descriptor, file.getAbsolutePath());
				} catch (Exception ex) {
					String message = String.format(
									"Could not register the following import script: %s",
									file.getAbsoluteFile());
					logError(message, ex);
				}
			}
		}
	}
	
	/**
	 * Registers all import descriptors that has been added by using extension
	 * points.
	 * 
	 * @param configurationElements
	 *            Configuration elements for the registered extensions
	 */
	private void registerEPDefinedImportDescriptors(IConfigurationElement[] configurationElements) {
		for (IConfigurationElement element : configurationElements) {
			String file = element.getAttribute("File");
			String clazzName = element.getAttribute("Listener");
			IContributor con = element.getContributor();
			Bundle bundle = Platform.getBundle(con.getName());

			IInterchangeDescriptor descriptor = null;
			
			try {
				InputStream inputStream = bundle.getEntry(file).openStream();
				
				// XML based descriptor
				if (file.contains(".ixd.xml")) {
					XMLBasedImportDescriptor imp = new XMLBasedImportDescriptor(bundle, inputStream, file);
					imp.parseDescription();
					descriptor = imp;
				} else {
					descriptor = InterchangeDescriptorFactory.parse(inputStream);
					
					if(!StringUtils.isBlank(clazzName)) {
						try {
							InterchangeDescriptorImpl descriptorImpl = (InterchangeDescriptorImpl) descriptor;
							Class<?> clazz = Class.forName(clazzName);
							IInterchangeProcessListener listenerImpl = (IInterchangeProcessListener) clazz.newInstance();
							descriptorImpl.setProcessListener(listenerImpl);
						} catch(Exception ex) {
							logError(String.format("Could not set the registered listener '%s'!", clazzName), ex);
						}
					}
				}

				inputStream.close();
			} catch (Exception ex) {
				String message = String.format(
						"Could not register the following import script: %s",
						file);
				logError(message, ex);
			}
			
			if(descriptor != null)
				ImportDescriptorStore.register(descriptor);
		}
	}
	
	/**
	 * Registers all implementations of {@link IInterchangePropertyProvider} that were
	 * provided by extension points.
	 */
	private void registerPropertyValueProviders() {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(EXTENSION_ID_ADDON_PROPERTYVALUEPROVIDER);

		for (IConfigurationElement element : config) {
			String clazzName = element.getAttribute("ProviderImpl");
			try {
				Class<?> clazzImpl = Class.forName(clazzName);
				IInterchangePropertyProvider propertyProvider = (IInterchangePropertyProvider) clazzImpl.newInstance();
				PropertyProviderRegistry.registerProvider(propertyProvider);
			} catch(Exception ex) {
				String message = String.format("Could not create instance of '%s'.", clazzName);
				logger.error(message, ex);
			}
		}
	}
	
	/**
	 * Registers all implementations of {@link IModelBuilderAttendant} that were
	 * provided by extension points.
	 */
	private void registerModelBuilder() {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(EXTENSION_ID_ADDON_MODELBUILDER);

		for (IConfigurationElement element : config) {
			String clazzName = element.getAttribute("ModelBuilderImpl");
			String diagramType = element.getAttribute("DiagramType");
			try {
				Class<?> clazzImpl = Class.forName(clazzName);
				ModelBuilderAttendantRegistry.registerModelBuilder(diagramType, clazzImpl);
			} catch(Exception ex) {
				String message = String.format("Could not create instance of '%s'.", clazzName);
				logger.error(message, ex);
			}
		}
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

	/**
	 * Logs an debug message if the debug level is enabled.
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
	 * @param ex
	 *            exception to log
	 */
	public static void logError(String message, Exception ex) {
		if (logger.isErrorEnabled()) {
			logger.error(message, ex);
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
	 * Extension point id for add-ons model builder attendants.
	 */
	public static final String EXTENSION_ID_ADDON_MODELBUILDER = "org.bflow.toolbox.interchange.modelbuilder";

	/**
	 * Extension point id for add-ons export scripts.
	 */
	public static final String EXTENSION_ID_ADDON_EXPORTSCRIPT = "org.bflow.toolbox.interchange.exportscript";

	/**
	 * Extension point id for add-ons import scripts.
	 */
	public static final String EXTENSION_ID_ADDON_IMPORTSCRIPT = "org.bflow.toolbox.interchange.importscript";
	
	/**
	 * Extension point id for the add-ons property value provider.
	 */
	public static final String EXTENSION_ID_ADDON_PROPERTYVALUEPROVIDER = "org.bflow.toolbox.interchange.propertyProvider";

}

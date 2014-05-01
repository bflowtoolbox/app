package org.bflow.toolbox.hive.attributes.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bflow.toolbox.hive.attributes.AttributeFile;
import org.bflow.toolbox.hive.attributes.AttributeFileRegistry;
import org.bflow.toolbox.hive.attributes.IAttributeAdjustProcessor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 * 
 * @author Arian Storch
 * @version 10/02/13
 */
public class AttributeViewPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.bflow.toolbox.attributeadapter";
	
	/**
	 * Defines the id of the AttributeAdjustProcessor extension point.
	 */
	public static final String EXTENSION_POINT_ID_ATTRIBUTE_ADJUST_PROCESSOR = 
		"org.bflow.toolbox.attributes.attributeadjustprocessor";

	// The shared instance
	private static AttributeViewPlugin plugin;
	
	// Logger instance
	private static Log logger = LogFactory.getLog(AttributeViewPlugin.class);
	
	private InterchangeAttributeProvider interchangeAttributeProvider;
	
	/**
	 * The constructor
	 */
	public AttributeViewPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		registerDynamicEMF();
		
		// Needed to register the workbench page listener as soon as possible
		AttributeFileRegistry.getInstance();
		
		// Register any AttributeAdjustProcessor
		try {
			registerAttributeAdjustProcessors();
		}catch (CoreException ex) {
			ex.printStackTrace();
		}
		
		// If the InterchangePlugin is installed than register an AttributeProvider
		try {
			if (Class.forName("org.bflow.toolbox.interchange.mif.core.AttributeProviderRegistry") != null) {
				interchangeAttributeProvider = new InterchangeAttributeProvider();
			}
		} catch (ClassNotFoundException ex) {
			// this can happen so there is nothing to do
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		
		if(interchangeAttributeProvider != null) {
			interchangeAttributeProvider.dispose();
			interchangeAttributeProvider = null;
		}
		
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static AttributeViewPlugin getInstance() {
		return plugin;
	}
	
	/**
	 * Registers all by extension point defined instances of {@link IAttributeAdjustProcessor}
	 * and makes them available for the attribute adjust processing.
	 * 
	 * @throws CoreException
	 */
	private void registerAttributeAdjustProcessors() throws CoreException {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
		.getConfigurationElementsFor(EXTENSION_POINT_ID_ATTRIBUTE_ADJUST_PROCESSOR);

		for (IConfigurationElement element : config) {
			IAttributeAdjustProcessor processor = (IAttributeAdjustProcessor) element
					.createExecutableExtension("Processor");
			AttributeAdjustProcessorRegistry.AddProcessor(processor);
		}
	}
	
	/**
	 * Registers dynamic emf objects that are used by the add-on plugin.
	 */
	private void registerDynamicEMF() {
		EcoreFactory theCoreFactory = EcoreFactory.eINSTANCE;

		EClass addonEClass = theCoreFactory.createEClass();
		addonEClass.setName("AddonAttributes");
		addonEClass.setInstanceClassName("org.modellingaddons.attributes.eclass");
		
		EClass attrEClass = theCoreFactory.createEClass();
		attrEClass.setName("Attribute");

		EPackage addonEPackage = theCoreFactory.createEPackage();
		addonEPackage.setName("AddonAttributesPackage");
		addonEPackage.setNsPrefix("addon");
		addonEPackage.setNsURI("http://org.bflow.addon");

		EReference addonAttributes_attributes = theCoreFactory
				.createEReference();
		addonAttributes_attributes.setName("attributes");
		addonAttributes_attributes.setEType(attrEClass);
		addonAttributes_attributes
				.setUpperBound(EStructuralFeature.UNBOUNDED_MULTIPLICITY);
		addonAttributes_attributes.setContainment(true);

		// erzeugen der attribute
		EcorePackage theCorePackage = EcorePackage.eINSTANCE;

		EAttribute attrId = theCoreFactory.createEAttribute();
		attrId.setName("id");
		attrId.setEType(theCorePackage.getEString());

		EAttribute attrName = theCoreFactory.createEAttribute();
		attrName.setName("name");
		attrName.setEType(theCorePackage.getEString());

		EAttribute attrValue = theCoreFactory.createEAttribute();
		attrValue.setName("value");
		attrValue.setEType(theCorePackage.getEString());

		// hinzufügen der referenzen und eigenschaften
		addonEClass.getEStructuralFeatures().add(addonAttributes_attributes);

		attrEClass.getEStructuralFeatures().add(attrId);
		attrEClass.getEStructuralFeatures().add(attrName);
		attrEClass.getEStructuralFeatures().add(attrValue);

		// dem Paket hinzufügen
		addonEPackage.getEClassifiers().add(addonEClass);
		addonEPackage.getEClassifiers().add(attrEClass);

		EPackage.Registry.INSTANCE.put("http://org.bflow.addon", addonEPackage);

		AttributeFile.addonEClass = addonEClass;
		AttributeFile.attributeEClass = attrEClass;
		AttributeFile.addonEPackage = addonEPackage;
		AttributeFile.attributeId = attrId;
		AttributeFile.attributeName = attrName;
		AttributeFile.attributeValue = attrValue;
		AttributeFile.addon_attributes = addonAttributes_attributes;
	}
	
	/**
	 * Logs an error message with a stack trace if the error level is enabled.
	 * @param message message to log
	 * @param ex exception that is used for the stack trace
	 */
	public static void logError(String message, Exception ex) {
		if(logger.isErrorEnabled()) {
			logger.error(message, ex);
		}
	}
	
	/**
	 * Logs a debug message if the debug level is enabled.
	 * @param message message to log
	 */
	public static void logDebug(String message) {
		if(logger.isDebugEnabled()) {
			logger.debug(message);
		}
	}
	
	/**
	 * Logs an error message if the error level is enabled.
	 * @param message message to log
	 */
	public static void logError(String message) {
		logError(message, null);
	}
	
	/**
	 * Logs an info message if the info level is enabled.
	 * @param message message to log
	 */
	public static void logInfo(String message) {
			if(logger.isInfoEnabled()) {
				logger.info(message);
			}
	}

}

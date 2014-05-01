package org.bflow.toolbox.hive.interchange.mif.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides a registry for the Attribute Providers.
 * 
 * @author Arian Storch
 * @since 08/10/12
 * @see IAttributeProvider
 */
public class AttributeProviderRegistry {
	
	/** The registered providers. */
	private static List<IAttributeProvider> registeredProviders = new ArrayList<IAttributeProvider>();
	
	/**
	 * Register provider.
	 * 
	 * @param provider
	 *            the provider
	 */
	public static void registerProvider(IAttributeProvider provider) {
		registeredProviders.add(provider);
	}
	
	/**
	 * Unregister provider.
	 * 
	 * @param provider
	 *            the provider
	 */
	public static void unregisterProvider(IAttributeProvider provider) {
		registeredProviders.remove(provider);
	}

	/**
	 * Returns the provider for the given diagram editor file extension.
	 * 
	 * @param diagramEditorFileExtension
	 *            the diagram editor file extension
	 * @return the provider for the given diagram editor file extension
	 */
	public static IAttributeProvider[] getProviderFor(String diagramEditorFileExtension) {
		List<IAttributeProvider> matching = new ArrayList<IAttributeProvider>();
		
		for(int i = 0; i < registeredProviders.size(); i++) {
			IAttributeProvider provider = registeredProviders.get(i);
			
			boolean doesMatch = doesMatch(diagramEditorFileExtension, provider.getApplicableDiagramEditorFileExtensions());
			if(doesMatch) {
				matching.add(provider);
			}
		}
		
		return matching.toArray(new IAttributeProvider[0]);
	}
	
	/**
	 * Checks if the given array of provided diagram editor file extensions does match the given type.
	 * 
	 * @param diagramEditorFileExtension
	 *            the diagram editor file extension
	 * @param providedDiagramEditorFileExtensions
	 *            the provided diagram editor file extensions
	 * @return true, if successful
	 */
	private static boolean doesMatch(String diagramEditorFileExtension, String[] providedDiagramEditorFileExtensions) {
		for(int i = 0; i < providedDiagramEditorFileExtensions.length; i++) {
			if(providedDiagramEditorFileExtensions[i].equalsIgnoreCase(diagramEditorFileExtension)) {
				return true;
			}
		}
		
		return false;
	}
}

package org.bflow.toolbox.hive.interchange.mif.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides a registry for the Property Providers.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 02/05/13
 * @see IInterchangePropertyProvider
 */
public class PropertyProviderRegistry {
	
	/** The registered providers. */
	private static List<IInterchangePropertyProvider> registeredProviders = new ArrayList<IInterchangePropertyProvider>();

	/**
	 * Registers the provider.
	 * 
	 * @param provider
	 *            the provider
	 */
	public static void registerProvider(IInterchangePropertyProvider provider) {
		registeredProviders.add(provider);
	}
	
	/**
	 * Unregisters the provider.
	 * 
	 * @param provider
	 *            the provider
	 */
	public static void unregisterProvider(IInterchangePropertyProvider provider) {
		registeredProviders.remove(provider);
	}
	
	/**
	 * Returns the provider for the given diagram editor file extension.
	 * 
	 * @param diagramEditorFileExtension
	 *            the diagram editor file extension
	 * @return the provider for the given diagram editor file extension
	 */
	public static IInterchangePropertyProvider[] getProviderFor(String diagramEditorFileExtension) {
		List<IInterchangePropertyProvider> matching = new ArrayList<IInterchangePropertyProvider>();
		
		for(int i = 0; i < registeredProviders.size(); i++) {
			IInterchangePropertyProvider provider = registeredProviders.get(i);
			
			boolean doesMatch = doesMatch(diagramEditorFileExtension, provider.getApplicableDiagramEditorFileExtensions());
			if(doesMatch) {
				matching.add(provider);
			}
		}
		
		return matching.toArray(new IInterchangePropertyProvider[0]);
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

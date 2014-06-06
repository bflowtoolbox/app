package org.bflow.toolbox.hive.addons.store;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bflow.toolbox.hive.addons.core.model.IComponent;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

/**
 * Defines a store that lists all available protocol components.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 25/09/10
 * @version 06/06/14
 */
public class ComponentStore {
	
	private static ComponentStore fInstance;
	
	/**
	 * Returns the singleton instance of the Component Store. 
	 * @return The singleton instance.
	 */
	public static ComponentStore getInstance() {
		return fInstance != null ? fInstance : (fInstance = new ComponentStore());
	}
	
	
	/**
	 * static collection instance
	 */
	private List<IComponent> depository = new ArrayList<IComponent>();

	/**
	 * Private constructor. Initializes the component store.
	 */
	private ComponentStore() {
		IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(EXTENSION_ID_ADDON_COMPONENT);

		for (IConfigurationElement element : config) {
			try {
				IComponent component = (IComponent) element.createExecutableExtension("Class");
				register(component);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Returns an alphabetic sorted list of all installed and available protocol components.<p/>
	 * Note: The list only contains the simple class names.
	 * @return list
	 */
	public List<String> getInstalledComponents() {
		ArrayList<String> list = new ArrayList<String>();
		
		for(IComponent comp:depository)
			list.add(comp.getClass().getSimpleName());
		
		return list;
	}
	
	/**
	 * Returns an user friendly alphabetic sorted list of all installed and available protocol components.<p/>
	 * 
	 * @return list
	 */
	public List<String> getUserfriendlyComponentNames() {

		List<IComponent> depositoryCollection = depository;
		String list[] = new String[depositoryCollection.size()];
		
		for (int i = 0; i < list.length; i++) {
			IComponent component = depositoryCollection.get(i);
			list[i] = component.getDisplayName();
		}
		
		Arrays.sort(list);		
		return Arrays.asList(list);
	}
	
	/**
	 * Identifies a name with a IComponent implementation and does it return.
	 * @param name name to find
	 * @param userfriendly set true if the name is user-friendly
	 * @return component implementation or null
	 */
	public IComponent identify(String name, boolean userfriendly) {
		for(IComponent comp:depository)
			if(userfriendly) {
				if(comp.getDisplayName().equalsIgnoreCase(name))
					return comp;
			} else {
				if(comp.getClass().getName().equalsIgnoreCase(name))
					return comp;
			}
		
		return null;
	}
	
	/**
	 * Registers the component in the store.
	 * @param component component to register
	 */
	public void register(IComponent component) {
		depository.add(component);
	}
	
	/**
	 * Extension point id for add-ons components.
	 */
	public static final String EXTENSION_ID_ADDON_COMPONENT = "org.bflow.toolbox.addons.component";
}

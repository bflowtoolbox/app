package org.bflow.toolbox.hive.addons.store;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bflow.toolbox.hive.addons.core.model.IComponent;

/**
 * Defines a store that lists all available protocol components.
 * 
 * @author Arian Storch
 * @since 25/09/10
 * @version 05/03/13
 */
public class ComponentStore {
	
	/**
	 * static collection instance
	 */
	private static List<IComponent> depository = new ArrayList<IComponent>();

	/**
	 * Returns an alphabetic sorted list of all installed and available protocol components.<p/>
	 * Note: The list only contains the simple class names.
	 * @return list
	 */
	public static List<String> getInstalledComponents() {
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
	public static List<String> getUserfriendlyComponentNames() {

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
	 * @param userfriendly set true if the name is userfriedly
	 * @return component implementation or null
	 */
	public static IComponent identify(String name, boolean userfriendly) {
		
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
	public static void register(IComponent component) {
		depository.add(component);
	}
}

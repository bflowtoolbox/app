package oepc.diagram.views;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class Associations {
	private List<Association> associations;
	private Map<String, List<Association>> associationsMap;
	
	public Associations() {
		this.associations = new Vector<>();
		this.associationsMap = new HashMap<>();
	}
	
	/**
	 * Adds the given {@code association} to all private collections.
	 */
	public void add(Association association) {
		associations.add(association);
		addToAssociationMap(association);
	}
	
	/**
	 * Removes the supplied {@code association} from all private collections.
	 * If removing fails, all collections are restored to their previous state.
	 * @return Returns {@code true} on success and {@code false} otherwise.
	 */
	public boolean remove(Association association) {
		int index = associations.indexOf(association);
		
		boolean success = associations.remove(association);
		if (!success) return success;
		
		success = removeFromAssociationMap(association);
		if (!success) associations.add(index, association);
		
		return success;
	}
	
	/**
	 * @return Returns all associations as an array and an empty array if none are present.
	 */
	public Association[] toArray() {
		Association[] array = new Association[associations.size()];
		return associations.toArray(array);
	}
	
	/**
	 * Returns that all associations that are associated with the given {@code elementId}.
	 * @return Returns an array that may be empty.
	 */
	public Association[] getAssociationsForElementId(String elementId) {
		List<Association> associations = associationsMap.get(elementId);
		Association[] array = new Association[associations.size()];
		return associations.toArray(array);
	}
	
	private void addToAssociationMap(Association association) {
		String elementId = association.elementId;
		
		List<Association> associations = associationsMap.get(association.elementId);
		if (associations == null) associations = new Vector<>();
		associations.add(association);
		
		associationsMap.put(elementId, associations);
	}
	
	private boolean removeFromAssociationMap(Association association) {
		String elementId = association.elementId;
		
		List<Association> associations = associationsMap.get(elementId);
		boolean success = associations.remove(association);
		
		return success;
	}
}

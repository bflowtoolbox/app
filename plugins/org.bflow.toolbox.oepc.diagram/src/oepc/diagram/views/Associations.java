package oepc.diagram.views;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.function.BinaryOperator;

public class Associations {
	private Map<String, List<Association>> associationsMap;
	
	public Associations() {
		this.associationsMap = new HashMap<>();
	}
	
	/**
	 * Adds the given {@code association} to all private collections.
	 */
	public void add(Association association) {
		String elementId = association.elementId;
		
		List<Association> associations = associationsMap.get(association.elementId);
		if (associations == null) associations = new Vector<>();
		associations.add(association);
		
		associationsMap.put(elementId, associations);
	}
	
	/**
	 * Removes the supplied {@code association}.
	 * @return Returns {@code true} on success and {@code false} otherwise.
	 */
	public boolean remove(Association association) {
		String elementId = association.elementId;
		
		List<Association> associations = associationsMap.get(elementId);
		boolean success = associations.remove(association);
		
		if (associations.isEmpty()) associationsMap.remove(elementId);
		
		return success;
	}
	
	/**
	 * @return Returns all associations as an array and an empty array if none are present.
	 */
	public Association[] toArray() {
		BinaryOperator<List<Association>> accumulator = (flatList, currentList) -> { flatList.addAll(currentList); return flatList; };		
		List<Association> list = associationsMap.values().stream().reduce(new Vector<Association>(), accumulator);
				
		return list.toArray(new Association[list.size()]);
	}
	
	/**
	 * Returns that all associations that are associated with the given {@code elementId}.
	 * @return Returns an array that may be empty.
	 */
	public Association[] getAssociationsForElementId(String elementId) {
		List<Association> associations = associationsMap.get(elementId);
		if (associations == null) return new Association[0];
		
		Association[] array = new Association[associations.size()];
		return associations.toArray(array);
	}
}

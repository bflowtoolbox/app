package org.bflow.toolbox.hive.libs.aprogu.collections;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * Represents a map value object that has additional methods.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 31/10/13
 */
public class MapValue<T> {
	
	/**
	 * Creates an instance.
	 * @return New instance
	 */
	public static <T> MapValue<T> createInstance() {
		return new MapValue<T>();
	}
	
	/** Value */
	public T Value = null;
	
	/**
	 * Private constructor.
	 */
	private MapValue() {
	}

	/**
	 * Does a save look up for the given key within the given map. If 
	 * a value could be found, this will return true and the value is 
	 * placed within mapValue object. Otherwise this method will return 
	 * false.
	 * 
	 * @param map Map that could contain the value
	 * @param key Key of the value to look up
	 * @param mapValue Object that will contain the value for the key
	 * @return true if the map contains the key
	 */
	public static <K,T> boolean tryGetValue(Map<K,T> map, K key, MapValue<T> mapValue) {
		if(mapValue == null)
			throw new NullPointerException();
		
		if(map == null)
			return false;
		
		if(map.containsKey(key)) {
			T value = map.get(key);
			mapValue.Value = value;
			return true;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		if(Value == null)
			return StringUtils.EMPTY;
		return Value.toString();
	}
}

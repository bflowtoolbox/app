package org.bflow.toolbox.hive.libs.aprogu.collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Provides various methods to handle collections.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 31/10/13
 */
public class ListUtils {

	/**
	 * Returns a collection of the items of the given collection which do 
	 * match the selector.
	 * 
	 * @param list Base collection
	 * @param selector Where selector
	 * @return Collection
	 */
	public static <T> Iterable<T> where(Iterable<T> list, IWhereSelector<T> selector) {
		if(list == null)
			throw new NullPointerException("Parameter 'list' is null!");
		
		if(selector == null)
			throw new NullPointerException("Parameter 'selector' is null!");

		List<T> resultSet = new LinkedList<T>();
		for(Iterator<T> it = list.iterator(); it.hasNext();) {
			T item = it.next();
			if(selector.where(item))
				resultSet.add(item);
		}

		return resultSet;
	}
	
	/**
	 * Builds a list from the given iterable.
	 * 
	 * @param iterable iterable
	 * @return list based on the given iterable
	 */
	public static <T> List<T> toList(Iterable<T> iterable) {
		if(iterable instanceof List<?>)
			return (List<T>) iterable;
		
		List<T> result = new LinkedList<T>();
		for(Iterator<T> it = iterable.iterator(); it.hasNext();)
			result.add(it.next());
		
		return result;
	}
	
}

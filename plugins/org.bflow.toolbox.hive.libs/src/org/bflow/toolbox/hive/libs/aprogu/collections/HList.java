package org.bflow.toolbox.hive.libs.aprogu.collections;

import java.util.ArrayList;
import java.util.Iterator;

public class HList<TClass> extends ArrayList<TClass> { // TODO Introduce composite pattern
	
	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 390545038326025213L;
	
	private Class<TClass> fManagedItemClass;
	
	public HList(Class<TClass> managedItemClass) {
		super();
		fManagedItemClass = managedItemClass;
	}
	
	public void addAll(Iterable<TClass> collection) {
		addAll(collection.iterator());
	}
	
	public void addAll(TClass[] collection) {
		addAll(new HArrayIterator<>(fManagedItemClass, collection));
	}

	protected void addAll(Iterator<TClass> itr) {
		while (itr.hasNext())
			add(itr.next());
	}
	
	public TClass[] toArray() {
		@SuppressWarnings("unchecked")
		TClass[] array = (TClass[]) java.lang.reflect.Array.newInstance(fManagedItemClass, size());
		for (int i = -1; ++i != array.length;)
			array[i] = get(i);
		
		return array;
	}
}

package org.bflow.toolbox.hive.libs.aprogu.collections;

/**
 * Provides command array operations.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-01-27
 *
 */
public class Array {
	/**
	 * Returns a new array of the specified type and length. All items of the given
	 * array are copied into the new one.
	 * 
	 * @param cls  Array class
	 * @param set  Array to copy elements from
	 * @param size New array size
	 * @return New array with desired size and copied elements
	 */
	public static <TItem> TItem[] resize(Class<TItem> cls, TItem[] set, int size) {
		@SuppressWarnings("unchecked")
		TItem[] cpy = (TItem[]) java.lang.reflect.Array.newInstance(cls, size);
		for (int i = -1; ++i != size;) {
			cpy[i] = i < set.length ? set[i] : null;
		}
		
		return cpy;
	}
}

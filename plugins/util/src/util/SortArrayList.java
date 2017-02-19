/*
 * Created on Mar 8, 2005
 */
package util;

import java.util.ArrayList;

/**
 * @author Yijun
 */
public class SortArrayList extends ArrayList<Object> {
	private static final long serialVersionUID = 1L;
	
	public void addComparable(Comparable o) {
		boolean inserted = false;
		for (int i=0; i<size(); i++) {
			Comparable s = (Comparable) get(i);
			if (s.compareTo(o) <= 0)
				continue;
			add(i, o);
			inserted = true;
			break;
		}
		if (!inserted)
			super.add(o);
	}
}

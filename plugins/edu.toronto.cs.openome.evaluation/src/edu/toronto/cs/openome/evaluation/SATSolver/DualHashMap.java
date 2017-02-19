package edu.toronto.cs.openome.evaluation.SATSolver;

import java.util.HashMap;
import java.util.Set;

import edu.toronto.cs.openome_model.Intention;

public class DualHashMap<K, V> {
	
	private HashMap<K, V> forward;
	private HashMap<V, K> inverse;

	public DualHashMap () {
		forward = new HashMap<K, V>();
		inverse = new HashMap<V, K>();
	}
	
	public void clear() {
		forward.clear();
		inverse.clear();
	}
	
	public boolean containsKey(Object key)  {
		return forward.containsKey(key);
	}
	
	public boolean containsValue(Object value)  {
		return forward.containsValue(value);
	}
	
	public Object getForward(Object key) {
		return forward.get(key);
	}
	
	public Object getInverse(Object key) {
		return inverse.get(key);
	}
	
	public boolean isEmpty() {
		//the two hashmaps should always be the same size
		return forward.isEmpty();
	}
	
	public Set keySetForward() {
		return forward.keySet();
	}
	
	public Set keySetInverse() {
		return inverse.keySet();
	}
	
	public Object put(K key, V value) {
		forward.put(key, value);
		inverse.put(value, key);
		
		return null;
	}
	
	public Object remove(Object key) {
		inverse.remove(forward.remove(key));
		
		return null;
	}
	
	public void print() {
		for (Object obj : forward.keySet()) {
			System.out.println(obj.toString() + " : " + forward.get(obj).toString());
		}
	}

	public int size() {
		return forward.size();
	}
}

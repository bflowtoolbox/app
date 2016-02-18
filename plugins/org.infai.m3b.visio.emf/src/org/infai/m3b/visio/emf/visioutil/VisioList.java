/*******************************************************************************
 * Copyright (c) 2009 Heiko Kern.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Heiko Kern - initial API and implementation
 *******************************************************************************/
package org.infai.m3b.visio.emf.visioutil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.jawin.COMException;
import org.jawin.DispatchPtr;

/**
 * Encapsulate a VisioList into a Collection.
 * @author Heiko Kern
 *
 * @param <T> Type of the VisioList
 */
public class VisioList<T extends DispatchPtr> implements Collection<T>{
	
	public static final long serialVersionUID = 9219791950343374081L;
	
	private ArrayList<T> javaList;
	
	public VisioList(DispatchPtr visioList, Class<T> visioClazz) {
	
		try {
			int count;
			
			if(visioList.get("Count") instanceof Short)
				count = ((Short)visioList.get("Count")).shortValue();
			else
				count = ((Integer)visioList.get("Count")).intValue();
			
			javaList = new ArrayList<T>(count);
			
			for(int i=0; i<count; i++) {	
				DispatchPtr gPointer = (DispatchPtr)visioList.get("Item", new Integer(i+1));
				T sPointer;
				try {
					sPointer = (T)visioClazz.newInstance();
					sPointer.stealUnknown(gPointer);
			        javaList.add(sPointer);
				} catch (InstantiationException e) {
					
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					
					e.printStackTrace();
				}
		        
			}
		} catch (COMException e) {
			
			e.printStackTrace();
		}
	}	
	
	public int size() {
		return this.javaList.size();
	}
		
	public T get(int index) {
		return this.javaList.get(index);
	}
	
	public Iterator<T> iterator() {
		return javaList.iterator();
	}
	
	public boolean add(T object) {
		return this.javaList.add(object);
	}

	public void clear() {
		this.javaList.clear();		
	}

	public boolean contains(T object) {
		return this.javaList.contains(object);
	}
	
	public boolean isEmpty() {
		return this.javaList.isEmpty();
	}

	public boolean remove(Object o) {
		return this.javaList.remove(o);
	}

	public Object[] toArray() {		
		return this.javaList.toArray();
	}

	public boolean contains(Object o) {
		return this.javaList.contains(o);
	}

	public boolean addAll(Collection<? extends T> c) {
		return this.javaList.addAll(c);
	}

	public boolean containsAll(Collection<?> c) {		
		return this.javaList.containsAll(c);
	}
	
	public boolean removeAll(Collection<?> c) {
		return this.javaList.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return this.javaList.retainAll(c);
	}
	
	public <E> E[] toArray(E[] a) {	
		return this.javaList.toArray(a);
	}
}

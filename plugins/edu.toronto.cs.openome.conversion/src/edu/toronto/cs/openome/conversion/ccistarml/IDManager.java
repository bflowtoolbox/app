/*
 * IDManager.java
 * it is part of the ccistarml Java Package
 * version 0.6
 * Created on July 4 of 2007, By Carlos Cares
 * Updated to v0.6.1  on September 20 of 2007 By Carlos Cares
 */

package edu.toronto.cs.openome.conversion.ccistarml;

import java.util.*;

public class IDManager extends LinkedList {
	private String lastID = "AA00";

	public IDManager() {
		super();
	}

	public IDManager(Collection C) {
		super(C);
	}

	protected boolean add(String id) {
		if (id.length() == 0)
			return false;
		else if (this.contains(id))
			return false;
		else
			return super.add(id);
	}

	public String newID() {
		String r = this.nextID();
		while (!this.add(r)) {
			r = this.nextID();
		}
		return r;
	}

	private String nextID() {
		char c[] = this.lastID.toCharArray();
		if (c[3] != '9')
			c[3]++;
		else if (c[2] != '9') {
			c[3] = '0';
			c[2]++;
		} else if (c[1] != 'Z') {
			c[3] = c[2] = '0';
			c[1]++;
		} else {
			c[3] = c[2] = '0';
			c[1] = 'A';
			c[0]++;
		}
		this.lastID = "" + c[0] + c[1] + c[2] + c[3];
		return this.lastID;
	}
}

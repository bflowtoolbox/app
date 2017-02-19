/*
 * ERelement.java
 * it is part of the ccistarml Java Package
 * version 0.6.1
 * Created on July 4 of 2007, By Carlos Cares
 * Updated to v0.6.1  on September 20 of 2007 By Carlos Cares
 */

package edu.toronto.cs.openome.conversion.ccistarml;

import java.awt.event.ItemEvent;
import java.util.*;

public class ERelement {
	public String diagram = "";
	public String name = "";
	public String text;
	public String ID;
	public Hashtable attribute = new Hashtable();
	private static String lastID = "AA00";

	public ERelement() {
	}

	public ERelement(String nm, Hashtable att, ERelementList erL) {
		this.diagram = erL.currentDiagram;
		this.name = nm;
		this.set_attributes(att);
		this.set_ID(erL);
		erL.add(this);
	}

	public ERelement(String nm, String str, ERelementList erL) {
		this.diagram = erL.currentDiagram;
		this.name = nm;
		this.text = str;
		this.set_ID(erL);
		erL.add(this);
	}

	public void display() {
		String w;
		if (this.name.equals("string") || this.name.equals("graphicSVG"))
			w = this.text;
		else
			w = this.attribute.toString();
		System.out.println(this.diagram + "-" + this.ID + ":" + this.name
				+ "-->" + w);
	}

	public void set_ID(ERelementList erL) {
		String w;
		w = (String) this.attribute.get("id");
		if (w != null) {
			this.ID = w;
			return;
		}
		if (this.name.equals("actor")) {
			w = (String) this.attribute.get("aref");
			if (w != null) {
				this.ID = w;
				return;
			}
		}
		if (this.name.equals("ielement")) {
			w = (String) this.attribute.get("iref");
			if (w != null) {
				this.ID = w;
				return;
			}
		}
		w = nextID();
		while (erL.containsRef(w))
			w = nextID();
		this.ID = w;
	}

	public void set_attributes(Hashtable att) {
		Iterator it = att.keySet().iterator();
		String key, val;
		while (it.hasNext()) {
			key = (String) it.next();
			val = (String) att.get(key);
			this.attribute.put(key, val);
		}
	}

	public String nextID() {
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

/*
 * ccistarmlContent.java
 * it is part of the ccistarml Java Package
 * version 0.6
 * Created on July 4 of 2007, By Carlos Cares
 * Updated to v0.6.1  on September 20 of 2007 By Carlos Cares
 */

package edu.toronto.cs.openome.conversion.ccistarml;

import java.util.*;
import java.lang.CharSequence;

public class ccistarmlContent {
	public String tagName = "";
	public String endTag = "";
	public boolean root = false;

	public enum tagType {
		auto, open, close, string, comment
	};

	public tagType type;
	public String namespace = "";
	public StringBuffer text = new StringBuffer("");

	// public int attCount;
	// private boolean hasContent;
	public Hashtable attribute = new Hashtable();
	public LinkedList content = new LinkedList();
	public int myLineNumber;
	public String myLinePortion;
	public String error = "";

	private static IDManager idmanager = new IDManager(); // v0.6
	private ccistarmlContent father = null; // v0.6

	private static int CountPoint = 0;
	private static char[] letter_ = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
			'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
			'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
			'V', 'W', 'X', 'Y', 'Z' };
	private static char[] newline_ = { '\n', '\f' };
	private static char[] ignore_ = { '\r' };
	private static char[] space_ = { ' ', '\t' };
	private static char[] initag_ = { '<' };
	private static char[] slash_ = { '/' };
	private static char[] endtag_ = { '>' };
	private static char[] equal_ = { '=' };
	private static char[] special_ = { '_', ':', '-', '+', '$', '%', ';', '&',
			'[', ']', '.', ',', '/', '|', '@', '#', '(', ')', '*', '?', '!',
			'{', '}' };
	private static char[] double_ = { '"' };
	private static char[] single_ = { '\'' };
	private static char[] minus_ = { '-' };
	private static char[] colon_ = { ':' };
	private static char[] quest_ = { '?' };
	private static char[] excla_ = { '!' };
	private static char[] x_ = { 'x' };
	private static char[] m_ = { 'm' };
	private static char[] l_ = { 'l' };
	private static char[] underscore_ = { '_' };
	private static char[] number_ = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9' };
	private static char[][] alphabet = { excla_, initag_, endtag_, quest_, x_,
			m_, l_, slash_, space_, double_, single_, equal_, minus_, colon_,
			underscore_, ignore_, newline_, number_, special_, letter_ };
	// 0 , 1 , 2 ,3 ,4 ,5 , 6 , 7 , 8 , 9 , 10 , 11 , 12 , 13 ,14 ,15 , 16 , 17
	// ,18 , 19
	private static short afd[][] = {
			// 00,01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18,19,20
			{ 40, 01, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40,
					40, 40, 40, 40, 40 }, // state 0
			{ 41, 41, 41, 02, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41,
					41, 41, 41, 41, 41 }, // state 1
			{ 42, 42, 42, 42, 3, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42,
					42, 42, 42, 42, 42 }, // state 2
			{ 42, 42, 42, 42, 42, 4, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42,
					42, 42, 42, 42, 42 }, // state 3
			{ 42, 42, 42, 42, 42, 42, 5, 42, 42, 42, 42, 42, 42, 42, 42, 42,
					42, 42, 42, 42, 42 }, // state 4
			{ 43, 43, 43, 43, 43, 43, 43, 43, 06, 43, 43, 43, 43, 43, 43, 05,
					06, 43, 43, 43, 43 }, // state 5
			{ 44, 44, 44, 44, 07, 07, 07, 44, 6, 44, 44, 44, 44, 44, 07, 06,
					44, 44, 44, 07, 44 }, // state 6
			{ 44, 44, 44, 44, 07, 07, 07, 44, 8, 44, 44, 9, 07, 44, 07, 8, 8,
					07, 44, 07, 44 }, // state 7
			{ 45, 45, 45, 45, 45, 45, 45, 45, 8, 45, 45, 9, 45, 45, 45, 45, 8,
					45, 45, 45, 45 }, // state 8
			{ 46, 46, 46, 46, 46, 46, 46, 46, 9, 11, 10, 46, 46, 46, 46, 46, 9,
					46, 46, 46, 46 }, // state 9
			{ 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 10, 10, 10, 10, 10,
					10, 10, 10, 10, 47 }, // state 10
			{ 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 11, 11, 11, 11, 11, 11,
					11, 11, 11, 11, 47 }, // state 11
			{ 48, 48, 48, 14, 48, 48, 48, 48, 13, 48, 48, 48, 48, 48, 48, 48,
					13, 48, 48, 48, 48 }, // state 12
			{ 48, 48, 48, 14, 07, 07, 07, 48, 13, 48, 48, 48, 48, 48, 07, 48,
					13, 48, 48, 07, 48 }, // state 13
			{ 49, 49, 15, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49,
					49, 49, 49, 49, 49 }, // state 14
			{ 29, 16, 50, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29,
					29, 29, 29, 29, 47 }, // state 15
			{ 34, 51, 51, 51, 17, 17, 17, 30, 51, 51, 51, 51, 51, 51, 17, 51,
					51, 51, 51, 17, 51 }, // state 16
			{ 51, 51, 15, 51, 17, 17, 17, 28, 20, 51, 51, 51, 11, 18, 17, 17,
					20, 51, 51, 17, 51 }, // state 17
			{ 51, 51, 51, 51, 19, 19, 19, 51, 51, 51, 51, 51, 51, 51, 19, 51,
					51, 51, 51, 19, 51 }, // state 18
			{ 51, 51, 15, 51, 19, 19, 19, 28, 20, 51, 51, 51, 51, 51, 19, 19,
					20, 51, 51, 19, 51 }, // state 19
			{ 52, 52, 15, 52, 21, 21, 21, 28, 20, 52, 52, 52, 52, 52, 21, 20,
					20, 52, 52, 21, 52 }, // state 20
			{ 52, 52, 52, 52, 21, 21, 21, 52, 22, 52, 52, 23, 21, 52, 21, 21,
					22, 21, 52, 21, 52 }, // state 21
			{ 45, 45, 45, 45, 45, 45, 45, 45, 22, 45, 45, 23, 45, 45, 45, 22,
					22, 45, 45, 45, 45 }, // state 22
			{ 46, 46, 46, 46, 46, 46, 46, 46, 23, 24, 25, 46, 46, 46, 46, 23,
					23, 46, 46, 46, 46 }, // state 23
			{ 24, 24, 24, 24, 24, 24, 24, 24, 24, 26, 24, 24, 24, 24, 24, 24,
					24, 24, 24, 24, 47 }, // state 24
			{ 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26, 25, 25, 25, 25, 25,
					25, 25, 25, 25, 47 }, // state 25
			{ 53, 53, 15, 53, 53, 53, 53, 28, 27, 53, 53, 53, 53, 53, 53, 27,
					27, 53, 53, 53, 53 }, // state 26
			{ 53, 53, 15, 53, 21, 21, 21, 28, 27, 53, 53, 53, 53, 53, 21, 27,
					27, 53, 53, 21, 53 }, // state 27
			{ 49, 49, 15, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49,
					49, 49, 49, 49, 49 }, // state 28
			{ 29, 16, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29,
					29, 29, 29, 29, 47 }, // state 29
			{ 54, 54, 54, 54, 33, 33, 33, 54, 54, 54, 54, 54, 54, 54, 33, 54,
					54, 54, 54, 33, 54 }, // state 30
			{ 54, 54, 54, 54, 32, 32, 32, 54, 54, 54, 54, 54, 54, 54, 32, 54,
					54, 54, 54, 32, 54 }, // state 31
			{ 54, 54, 15, 54, 32, 32, 32, 54, 54, 54, 54, 54, 54, 54, 32, 54,
					54, 54, 54, 32, 54 }, // state 32
			{ 54, 54, 15, 54, 33, 33, 33, 54, 54, 54, 54, 54, 54, 31, 33, 54,
					54, 54, 54, 33, 54 }, // state 33

			{ 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 35, 55, 55, 55,
					55, 55, 55, 55, 55 }, // state 34
			{ 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 36, 55, 55, 55,
					55, 55, 55, 55, 55 }, // state 35
			{ 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 37, 36, 36, 36,
					36, 36, 36, 36, 36 }, // state 36
			{ 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 38, 36, 36, 36,
					36, 36, 36, 36, 36 }, // state 37
			{ 36, 36, 15, 36, 36, 36, 36, 36, 36, 36, 36, 36, 37, 36, 36, 36,
					36, 36, 36, 36, 36 } }; // state 38

	private static String msg[] = {
	/* msg[40]= */"no starting element",
	/* msg[41]= */"expected ?",
	/* msg[42]= */"expected xml",
	/* msg[43]= */"expected space",
	/* msg[44]= */"expected attribute name",
	/* msg[45]= */"expected =",
	/* msg[46]= */"expected \" or \'",
	/* msg[47]= */"unknow char",
	/* msg[48]= */"expected ?>",
	/* msg[49]= */"expected >",
	/* msg[50]= */"expected <",
	/* msg[51]= */"expected tag name",
	/* msg[52]= */"expected attribute name",
	/* msg[53]= */"expected end of tag or new attribute name",
	/* msg[54]= */"expected end of a previously open tag",
	/* msg[55]= */"expected <-- to start a comment" };

	public ccistarmlContent(ccistarmlFile xmlFile) {
		this.root = true;
		while (!xmlFile.eof()) {
			this.content.add(new ccistarmlContent(xmlFile, this));
		}
	}

	// v0.6
	public ccistarmlContent(String tgName) {
		this.root = false;
		this.tagName = tgName;
	}

	// v0.6
	public void setOpen() {
		this.type = tagType.open;
	}

	// v0.6
	public void setAuto() {
		this.type = tagType.auto;
	}

	// v0.6
	public String id() {
		String rid = this.get_attribute("id");
		if (rid.length() > 0)
			return rid;
		else
			return this.add_automatic_id();
	}

	// v0.6
	public String get_attribute(String att) {
		if (this.attribute.containsKey(att))
			return (String) this.attribute.get(att);
		else
			return "";
	}

	// v0.6
	public String add_automatic_id() {
		this.set_attribute("id", "");
		return (String) this.attribute.get("id");
	}

	// v0.6
	public void set_attribute(String att, String value) {
		String val = value;
		if (att.equals("id"))
			if (!this.idmanager.add(val))
				val = this.idmanager.newID();
		// this.attribute.remove(att);
		this.attribute.put(att, val);
	}

	// v0.6
	public boolean remove_attribute(String att) {
		Object r;
		r = this.attribute.remove(att);
		if (r == null)
			return false;
		else
			return true;
	}

	// v0.6
	private ccistarmlContent getOrCreateBoundary() {
		int has;
		ccistarmlContent b = null;
		if (this.tagName.equals("actor")) {
			if (this.type != tagType.open)
				this.setOpen();
			has = this.hasSubTag("boundary");
			if (has == -1) {
				b = new ccistarmlContent("boundary");
				b.setOpen();
				this.content.addLast(b);
			} else
				b = (ccistarmlContent) this.content.get(has);
		}
		return b;
	}

	// v0.6
	public ccistarmlContent add_role(String actorname) {
		return this.add_actor(actorname, "role");
	}

	// v0.6
	public ccistarmlContent add_position(String actorname) {
		return this.add_actor(actorname, "position");
	}

	// v0.6
	public ccistarmlContent add_agent(String actorname) {
		return this.add_actor(actorname, "agent");
	}

	// v0.6
	public ccistarmlContent add_goal(String name) {
		return this.add_ielement(name, "goal");
	}

	// v0.6
	public ccistarmlContent add_softgoal(String name) {
		return this.add_ielement(name, "softgoal");
	}

	// v0.6
	public ccistarmlContent add_resource(String name) {
		return this.add_ielement(name, "resource");
	}

	// v0.6
	public ccistarmlContent add_task(String name) {
		return this.add_ielement(name, "task");
	}

	// v0.6
	public ccistarmlContent add_belief(String name) {
		return this.add_ielement(name, "belief");
	}

	// v0.6
	private ccistarmlContent getOrCreateGraphic() {
		ccistarmlContent ret = null;
		int gPos;
		if (this.tagName
				.matches("diagram|actor|ielement|actorLink|ielementLink|depender|dependee|boundary")) {
			gPos = this.hasSubTag("graphic");
			if (gPos >= 0)
				ret = (ccistarmlContent) this.content.get(gPos);
			else {
				ret = new ccistarmlContent("graphic");
				this.type = tagType.open;
				ret.type = tagType.auto;
				this.content.addFirst(ret);
			}
		}
		return ret;
	}

	// v0.6
	private ccistarmlContent getOrCreateDependency() {
		ccistarmlContent x = null;
		int depPos;
		if (this.tagName.equals("ielement") && this.father != null)
			if (this.father.tagName.equals("diagram")) {
				depPos = this.hasSubTag("dependency");
				if (depPos == -1) {
					x = new ccistarmlContent("dependency");
					x.father = this;
					this.setOpen();
					if (this.hasSubTag("graphic") == 0)
						this.content.add(1, x);
					else
						this.content.addFirst(x);
				} else
					x = (ccistarmlContent) this.content.get(depPos);
			}
		return x;
	}

	// v0.6
	public ccistarmlContent add_depender(ccistarmlContent depender, String type) {
		ccistarmlContent dep = this.add_depender(depender);
		if (dep != null) {
			dep.set_attribute("value", type);
		}
		return dep;
	}

	// v0.6
	public ccistarmlContent add_depender(ccistarmlContent depender) {
		ccistarmlContent ac = null, d, x = null;
		String aref = "", iref = "";
		if (this.tagName.equals("ielement")) {
			d = this.getOrCreateDependency();
			if (d != null) {
				if (depender.tagName.equals("actor"))
					ac = depender;
				else if (depender.tagName.equals("ielement")) {
					ac = depender.containerActor();// this.father; //?
					if (!depender.attribute.containsKey("id"))
						iref = depender.add_automatic_id();
					else
						iref = (String) depender.attribute.get("id");
				}
				if (ac != null) {
					if (ac.tagName.equals("actor")) {
						if (!ac.attribute.containsKey("id"))
							aref = ac.add_automatic_id(); //
						else
							aref = (String) ac.attribute.get("id"); //
						x = new ccistarmlContent("depender");
						x.father = d;
						x.setAuto();
						x.set_attribute("aref", aref);
						d.content.addFirst(x);
						d.setOpen();
						if (iref.length() > 0)
							x.set_attribute("iref", iref);
					}
				}
			}
		}
		return x;
	}

	// v0.6
	public ccistarmlContent add_dependee(ccistarmlContent dependee) {
		ccistarmlContent ac = null, d, x = null;
		String aref = "", iref = "";
		if (this.tagName.equals("ielement")) {
			d = this.getOrCreateDependency();
			if (d != null) {
				if (dependee.tagName.equals("actor"))
					ac = dependee;
				else if (dependee.tagName.equals("ielement")) {
					ac = dependee.containerActor();// this.father; //?
					if (!dependee.attribute.containsKey("id"))
						iref = dependee.add_automatic_id();
					else
						iref = (String) dependee.attribute.get("id");
				}
				if (ac != null) {
					if (ac.tagName.equals("actor")) {
						if (!ac.attribute.containsKey("id"))
							aref = ac.add_automatic_id(); //
						else
							aref = (String) ac.attribute.get("id"); //
						x = new ccistarmlContent("dependee");
						x.father = d;
						x.setAuto();
						x.set_attribute("aref", aref);
						d.content.addLast(x);
						d.setOpen();
						if (iref.length() > 0)
							x.set_attribute("iref", iref);
					}
				}
			}
		}
		return x;
	}

	// v0.6
	public ccistarmlContent add_dependee2(ccistarmlContent dependee) {
		ccistarmlContent ac = null, d, x = null;
		String aref = "", iref = "";
		int depIdx;
		if (this.tagName.equals("ielement")) {
			depIdx = this.hasSubTag("dependency");
			if (depIdx != -1) {
				d = (ccistarmlContent) this.content.get(depIdx);
				if (dependee.tagName.equals("actor"))
					ac = dependee;
				else if (dependee.tagName.equals("ielement")) {
					ac = dependee.containerActor();// this.father; //wrong
					if (!dependee.attribute.containsKey("id"))
						iref = dependee.add_automatic_id(); //
					else
						iref = (String) dependee.attribute.get("id");
				}
				if (ac != null) {
					if (ac.tagName.equals("actor")) {
						if (!ac.attribute.containsKey("id")) //
							aref = ac.add_automatic_id(); //
						else
							aref = (String) ac.attribute.get("id"); //
						x = new ccistarmlContent("dependee");
						x.father = d;
						x.setAuto();
						x.set_attribute("aref", aref);
						d.content.addLast(x);
						if (iref.length() > 0)
							x.set_attribute("iref", iref);
					}
				}
			}
		}
		return x;
	}

	// v0.6
	public ccistarmlContent myDiagram() {
		if (this.tagName.equals("diagram"))
			return this;
		else if (this.father == null)
			return null;
		else
			return this.father.myDiagram();
	}

	// v0.6
	public ccistarmlContent add_actor(ccistarmlContent actor) {
		ccistarmlContent ret = null;
		if (!this.myDiagram().equals(actor.myDiagram())) {
			ret = this.add_actor("none");
			if (ret != null) {
				ret.attribute = new Hashtable();
				ret.set_attribute("aref", actor.id());
			}
		}
		return ret;
	}

	// v0.6
	public boolean removeSubTag(ccistarmlContent x) {
		boolean ret = false;
		if (this.type == tagType.auto || this.type == tagType.open)
			ret = this.content.remove(x);
		return ret;
	}

	// v0.6
	public boolean remove_graphic() {
		return this.remove_content_by_tagName("graphic");
	}

	// v0.6 boolean remove_content_by_tagName
	public boolean remove_content_by_tagName(String tgName) {
		LinkedList ltg = this.get_content_by_tagName(tgName);
		ccistarmlContent x;
		boolean ret = false;
		if (!ltg.isEmpty()) {
			x = (ccistarmlContent) ltg.getFirst();
			ret = this.removeSubTag(x);
		}
		return ret;
	}

	// v0.6
	public LinkedList get_content_by_tagName(String tgName) {
		Iterator it = this.content.iterator();
		LinkedList ret = new LinkedList();
		ccistarmlContent x;
		while (it.hasNext()) {
			x = (ccistarmlContent) it.next();
			if (x.tagName.equals(tgName)) {
				ret.addFirst(x);
			}
		}
		return ret;
	}

	// v0.6
	public ccistarmlContent add_actor(String actorname) {
		return this.add_actor(actorname, "");
	}

	// v0.6
	public ccistarmlContent add_actor(String actorname, String actortype) {
		ccistarmlContent x = null, b = null;
		int has;
		if (this.tagName.equals("diagram")) {
			b = this;
		} else if (this.tagName.equals("actor") && !this.hasAttribute("aref")) {
			b = this.getOrCreateBoundary();
			b.father = this;
		}

		if (b != null) {
			x = new ccistarmlContent("actor");
			x.set_attribute("name", actorname);
			x.father = b;
			if (actortype.length() > 0)
				x.set_attribute("type", actortype);
			x.setAuto();
			b.content.addLast(x);
		}
		return x;
	}

	// v0.6
	public ccistarmlContent add_ielement(ccistarmlContent iele) {
		ccistarmlContent ret = null;
		if (iele != null)
			if (iele.tagName.equals("ielement")) {
				ret = this.add_ielement("", "");
				ret.attribute = new Hashtable();
				ret.set_attribute("iref", iele.id());
			}
		return ret;
	}

	// v0.6
	public ccistarmlContent add_ielement(String iname, String itype) {
		ccistarmlContent x = null, b = null;
		int has;
		if (this.tagName.equals("diagram")) {
			b = this;
		} else if (this.tagName.equals("actor") && !this.hasAttribute("aref")) {
			b = this.getOrCreateBoundary();
			b.father = this;
		}
		if (b != null) {
			x = new ccistarmlContent("ielement");
			x.set_attribute("name", iname);
			x.set_attribute("type", itype);
			x.setAuto();
			x.father = b;
			b.content.addLast(x);
		}
		return x;
	}

	// v0.6
	public ccistarmlContent add_is_part_of(ccistarmlContent actor) {
		return this.add_actorLink(actor, "is_part_of");
	}

	// v0.6
	public ccistarmlContent add_is_a(ccistarmlContent actor) {
		return this.add_actorLink(actor, "is_a");
	}

	// v0.6
	public ccistarmlContent add_covers(ccistarmlContent actor) {
		ccistarmlContent x = null;
		if (this.hasAttributeValue("type", "position")
				&& actor.hasAttributeValue("type", "role"))
			x = this.add_actorLink(actor, "covers");
		return x;
	}

	// v0.6
	public ccistarmlContent add_plays(ccistarmlContent actor) {
		ccistarmlContent x = null;
		if (this.hasAttributeValue("type", "agent")
				&& actor.hasAttributeValue("type", "role"))
			x = this.add_actorLink(actor, "plays");
		return x;
	}

	// v0.6
	public ccistarmlContent add_instance_of(ccistarmlContent actor) {
		return this.add_actorLink(actor, "instance_of");
	}

	// v0.6
	public ccistarmlContent add_occupies(ccistarmlContent actor) {
		ccistarmlContent x = null;
		if (this.hasAttributeValue("type", "agent")
				&& actor.hasAttributeValue("type", "position"))
			x = this.add_actorLink(actor, "occupies");
		return x;
	}

	// v0.6
	public ccistarmlContent containerActor() {
		ccistarmlContent ret = null;
		if (this.father != null)
			if (this.father.tagName.equals("boundary"))
				if (this.father.father != null)
					if (this.father.father.tagName.equals("actor"))
						ret = this.father.father;
		return ret;
	}

	// v0.6
	public ccistarmlContent add_means_end(ccistarmlContent ielement) {
		return this.add_ielementLink(ielement, "means-end", "");
	}

	// v0.6
	public ccistarmlContent add_contribution(ccistarmlContent ielement,
			String value) {
		return this.add_ielementLink(ielement, "contribution", value);
	}

	// v0.6
	public ccistarmlContent add_decomposition(ccistarmlContent ielement) {
		return this.add_ielementLink(ielement, "decomposition", "");
	}

	// v0.6
	public ccistarmlContent add_decomposition(ccistarmlContent ielement,
			String value) {
		if (value.equals("and") || value.equals("or"))
			return this.add_ielementLink(ielement, "decomposition", value);
		else
			return null;
	}

	// v0.6
	public ccistarmlContent add_why(ccistarmlContent ielement) {
		return this.add_ielementLink(ielement, "why", "");
	}

	// v0.6
	public ccistarmlContent add_ielementLink(ccistarmlContent ielement,
			String type, String value) {
		ccistarmlContent ret = null;
		ccistarmlContent ac = null;
		int pos;
		if (this.tagName.equals("ielement")
				&& ielement.tagName.equals("ielement")
				&& !this.hasAttribute("iref")) {
			ret = new ccistarmlContent("ielementLink");
			ret.set_attribute("type", type);
			if (value.length() > 0)
				ret.set_attribute("value", value);
			ret.set_attribute("iref", ielement.id());
			ac = ielement.containerActor();
			if (ac != null)
				ret.set_attribute("aref", ac.id());
			ret.setAuto();
			this.setOpen();
			this.content.addLast(ret);
		}
		return ret;
	}

	// v0.6
	public ccistarmlContent add_ielementLink(ccistarmlContent ielement,
			String type) {
		return this.add_ielementLink(ielement, type, "");
	}

	// v0.6
	public ccistarmlContent add_actorLink(ccistarmlContent actor, String type) {
		ccistarmlContent ret = null;
		int pos;
		if (this.tagName.equals("actor") && actor.tagName.equals("actor")
				&& !this.hasAttribute("aref")) {
			ret = new ccistarmlContent("actorLink");
			ret.set_attribute("type", type);
			ret.set_attribute("aref", actor.id());
			ret.setAuto();
			this.setOpen();
			pos = this.hasSubTag("graphic");
			if (pos == -1)
				this.content.addFirst(ret);
			else
				this.content.add(1, ret);
		}
		return ret;
	}

	// v0.6
	private boolean set_graphic_basicAtt(String att, String value) {
		ccistarmlContent g = this.getOrCreateGraphic();
		if (g == null)
			return false;
		g.set_attribute(att, value);
		g.set_attribute("content", "basic");
		return true;
	}

	// v0.6
	private boolean set_graphic_basicAtt(String att, int value) {
		return this.set_graphic_basicAtt(att, "" + value);
	}

	// v0.6
	public boolean set_graphic_xpos(int value) {
		return this.set_graphic_basicAtt("xpos", value);
	}

	// v0.6
	public boolean set_graphic_ypos(int value) {
		return this.set_graphic_basicAtt("ypos", value);
	}

	// v0.6
	public boolean set_graphic_width(int value) {
		return this.set_graphic_basicAtt("width", value);
	}

	// v0.6
	public boolean set_graphic_height(int value) {
		return this.set_graphic_basicAtt("height", value);
	}

	// v0.6
	public boolean set_graphic_fontsize(int value) {
		return this.set_graphic_basicAtt("fontsize", value);
	}

	// v0.6
	public boolean set_graphic_fontfamily(String font) {
		return this.set_graphic_basicAtt("fontfamily", font);
	}

	// v0.6
	public boolean set_graphic_unit(String unit) {
		if (unit.matches("cm|in|pt"))
			return this.set_graphic_basicAtt("unit", unit);
		return false;
	}

	// v0.6
	public boolean set_graphic_shape(String shape) {
		if (shape.matches("ellipse|rect|spline|polyline"))
			return this.set_graphic_basicAtt("shape", shape);
		return false;
	}

	// v0.6
	public boolean set_graphic_bgcolor(String color) {
		if (color
				.matches("[a-f;0-9;A-F][a-f;0-9;A-F][a-f;0-9;A-F][a-f;0-9;A-F][a-f;0-9;A-F][a-f;0-9;A-F]"))
			return this.set_graphic_basicAtt("bgcolor", color);
		return false;
	}

	// v0.6
	public boolean set_graphic_fontcolor(String color) {
		if (color
				.matches("[a-f;0-9;A-F][a-f;0-9;A-F][a-f;0-9;A-F][a-f;0-9;A-F][a-f;0-9;A-F][a-f;0-9;A-F]"))
			return this.set_graphic_basicAtt("fontcolor", color);
		return false;
	}

	// v0.6
	public boolean set_graphic_SVG(String svg) {
		ccistarmlContent g;
		ccistarmlFile f;
		if (this.tagName
				.matches("diagram|ielement|actor|ielementLink|actorLink|depender|dependee|boundary")) {

			f = new ccistarmlFile(svg);
			if (f.xmlParser()) {
				g = this.getOrCreateGraphic();
				if (g != null) {
					g.type = tagType.open;
					if (!g.attribute.isEmpty())
						g.attribute = new Hashtable();
					g.set_attribute("content", "SVG");
					if (!g.content.isEmpty())
						g.content = new LinkedList();
					g.content.addLast(f.mainTagStructure());
					return true;
				}
			}
		}
		return false;
	}

	// v0.6
	public int hasSubTag(String tgName) {
		Iterator it = this.content.iterator();
		ccistarmlContent tg;
		int ret = -1;
		boolean found = false;
		while (it.hasNext() && !found) {
			tg = (ccistarmlContent) it.next();
			if (tg.tagName.equals(tgName))
				found = true;
			ret++;
		}
		if (found)
			return ret;
		else
			return -1;
	}

	// v0.6
	public boolean remove_content_by_attribute(String att, String value) {
		boolean found = false;
		ccistarmlContent x;
		String val;
		int idx = -1;
		Iterator it = this.content.iterator();
		while (it.hasNext() && !found) {
			idx++;
			x = (ccistarmlContent) it.next();
			val = (String) x.attribute.get(att);
			if (val != null)
				if (val.equals(value)) {
					found = true;
				}
		}
		if (found) {
			this.content.remove(idx);
		}
		return found;
	}

	public ccistarmlContent(ccistarmlFile xmlFile, ccistarmlContent father) {

		short next;
		char ch = xmlFile.read();
		short alpha;
		this.tagName = "";
		// this.attCount = 0;
		String attr = "";
		String val = "";
		ccistarmlContent x; // v0.6

		while (xmlFile.state <= 38 && ch != -1) {
			alpha = is_in_alphabet(ch);
			if (ch == '\n') {
				xmlFile.currentLineCount++;
				// xmlFile.currentLinePortion="";
			} else {
				xmlFile.currentLinePortion += ch;
			}
			this.myLineNumber = xmlFile.currentLineCount;
			this.myLinePortion = xmlFile.currentLinePortion;
			next = afd[xmlFile.state][alpha];

			// --------- condiciones escritura y control ------------
			if (next == 36) {
				this.text.append(ch);
			} else if (next == 6)
				this.tagName = "xml";
			else if (next == 7 || next == 21)
				attr += ch;
			else if ((next == 10 || next == 11 || next == 24 || next == 25)
					&& next == xmlFile.state)
				val += ch;
			else if (next == 12 || next == 26) {
				this.attribute.put(attr, val);
				attr = val = "";
			} else if ((xmlFile.state == 14 || xmlFile.state == 28)
					&& next == 15) {
				xmlFile.state = next;
				this.type = tagType.auto;
				return;
			} else if (xmlFile.state == 15 && next == 16)
				this.tagName = "";
			else if (next == 17 || next == 19)
				this.tagName += ch;
			else if (next == 18) {
				this.namespace = this.tagName;
				this.tagName = "";
			} else if ((xmlFile.state == 27 || xmlFile.state == 26
					|| xmlFile.state == 17 || xmlFile.state == 19 || xmlFile.state == 20)
					&& next == 15) {
				this.type = tagType.open;
				xmlFile.state = next;
				xmlFile.goUp = false;
				while (!xmlFile.eof() && !xmlFile.goUp) {
					x = new ccistarmlContent(xmlFile, this);
					x.father = father; // v0.6
					this.content.add(x);
				}
				xmlFile.goUp = false;
				return;
			} else if (next == 33 || next == 32)
				this.endTag += ch;
			else if (next == 31) {
				this.namespace = this.endTag;
				this.endTag = "";
			} else if ((xmlFile.state == 32 || xmlFile.state == 33)
					&& next == 15) {
				this.type = tagType.close;
				father.endTag = this.endTag;
				// -- deleteme
				this.type = tagType.string;
				this.text.delete(0, this.text.length());
				// --
				if (father.tagName.length() > 0
						&& !father.tagName.equals(father.endTag)) {
					xmlFile.error.add(new ccfileError("expected </"
							+ father.tagName + ">", xmlFile.currentLineCount,
							xmlFile.currentLinePortion));
				}
				xmlFile.state = next;
				xmlFile.goUp = true;
				return;
			} else if (xmlFile.state == 28 && next == 15) {
				this.type = tagType.auto;
				return;
			} else if (xmlFile.state == 38 && next == 15) {
				xmlFile.state = next;
				this.tagName = "comment";
				this.type = tagType.comment;
				return;
			} else if (next == 29)
				this.text.append(ch);
			else if (xmlFile.state == 29 && next == 16) {
				xmlFile.state = next;
				this.type = tagType.string;
				xmlFile.currentLinePortion = "<";
				return;
			}
			if (next == 15 || (xmlFile.state == 15 && next == 29))
				xmlFile.currentLinePortion = "";
			xmlFile.state = next;
			ch = xmlFile.read();
		}
		if (!xmlFile.eof()) {
			xmlFile.error.add(new ccfileError(this.msg[xmlFile.state - 40],
					xmlFile.currentLineCount, xmlFile.currentLinePortion));
			xmlFile.state = 15;
			while (!xmlFile.eof() && (ch = xmlFile.read()) != '>')
				if (ch == '\n')
					xmlFile.currentLineCount++;
		}
	}

	private short is_in_alphabet(char x) {
		int n = this.alphabet.length - 1;
		short ac = 0;
		boolean stop = false;
		while (ac <= n && !stop) {
			if (this.contains(alphabet[ac], x))
				stop = true;
			ac++;
		}
		if (stop)
			return --ac;
		else
			return ac;
	}

	public void load_istarmlRef(ccistarmlFile father) {
		String id;
		if (this.hasAttribute("id")) {
			id = (String) this.attribute.get("id");
			if (father.iRef.contains(id) || father.aRef.contains(id)
					|| father.oRef.contains(id))
				this.addError("id already defined", father);
			else if (this.tagName.equals("ielement"))
				father.iRef.add(id);
			else if (this.tagName.equals("actor"))
				father.aRef.add(id);
			else
				father.oRef.add(id);
		}
		Iterator it = this.content.iterator();
		ccistarmlContent x;
		while (it.hasNext()) {
			x = (ccistarmlContent) it.next();
			x.load_istarmlRef(father);
		}
	}

	private boolean contains(char[] w, char ch) {
		int n = w.length;
		int i = 0;
		boolean stop = false;
		while (i < n && !stop) {
			if (w[i] == ch)
				stop = true;
			i++;
		}
		return stop;
	}

	public void display(String sp) {
		if (this.type == tagType.open)
			System.out.println(sp + "<" + this.tagName + ">");
		else if (this.type == tagType.auto)
			System.out.println(sp + "<" + this.tagName + "/>");
		else if (this.type == tagType.string)
			System.out.println("text:" + this.text.toString());
		this.displayAttributes(sp);
		this.displayContents(sp + "    ");
		if (this.type == tagType.open)
			System.out.println(sp + "</" + this.tagName + ">");
	}

	public void displayAttributes(String sp) {
		Set att = this.attribute.keySet();
		String at, vl;
		Iterator it = att.iterator();
		while (it.hasNext()) {
			at = it.next().toString();
			vl = this.attribute.get(at).toString();
			System.out.println(sp + at + "=" + vl);
		}
	}

	public void displayContents(String sp) {
		Iterator it = this.content.iterator();
		ccistarmlContent x;
		while (it.hasNext()) {
			x = (ccistarmlContent) it.next();
			if (x.type != tagType.close)
				x.display(sp);
		}
	}

	public void removeEmptyTexts() {
		Iterator it = this.content.iterator();
		ccistarmlContent x;
		String aux;
		Stack del = new Stack();

		while (it.hasNext()) {
			x = (ccistarmlContent) it.next();
			if (x.type == tagType.string
					|| (x.type == null && x.tagName.equals(""))) {
				aux = x.text.toString().trim();
				aux.replaceAll("\n", "");
				aux.replaceAll("\r", "");
				aux.replaceAll("\f", "");
				if (aux.equals(""))
					del.push(x);
			} else
				x.removeEmptyTexts();
		}
		while (!del.empty()) {
			this.content.remove(del.pop());
		}
	}

	public boolean istarmlParsing(ccistarmlFile f) {
		ccistarmlContent xml, istarml;
		if (this.content.size() != 2)
			return this
					.addError(
							"illegal elements on file: only one main istarml tag must follow starting xml tag",
							f);
		xml = (ccistarmlContent) this.content.get(0);
		istarml = (ccistarmlContent) this.content.get(1);
		return xml.check_XmlStartingTag(f) && istarml.check_istarmlTag(f);
	}

	public boolean check_XmlStartingTag(ccistarmlFile f) {
		if (this.type == tagType.auto && this.tagName.equals("xml")
				&& this.content.isEmpty()
				&& this.attribute.get("version").equals("1.0"))
			return true;
		else {
			f.error.add(new ccfileError("Bad formed xml starting tag",
					this.myLineNumber, this.myLinePortion));
			return false;
		}

	}

	public boolean check_istarmlTag(ccistarmlFile f) {
		String err = "";
		boolean allOk = true;
		if (!this.tagName.equals("istarml"))
			err = "expected istarml tag";
		else if (this.type != tagType.open)
			err = "istarml must be an open tag";
		else if (!this.hasAttribute("version"))
			err = "istarml must have the version attribute";
		else if (!this.attribute.get("version").equals("1.0"))
			err = "this parser is only for istarml version 1.0";
		else if (this.content.size() <= 0)
			err = "istarml tag must content at least one diagram tag";
		else {
			Iterator it = this.content.iterator();
			ccistarmlContent x;
			while (it.hasNext()) {
				x = (ccistarmlContent) it.next();
				allOk = allOk && x.check_diagramTag(f);
			}
			// if (!allOk) err="istarml tag contains bad formed diagram tags";
		}
		return allOk && !this.addError(err, f);
	}

	public boolean check_diagramTag(ccistarmlFile f) {
		String err = "";
		boolean allOk = true;
		if (!this.tagName.equals("diagram"))
			err = "expected diagram tag";
		else if (this.type != tagType.open)
			err = "diagram tag must be open";
		else if (!this.hasBasicAtts())
			err = "diagram must have id or name attributes";
		else if (this.content.size() > 0) {
			ccistarmlContent x;
			Iterator it = this.content.iterator();
			boolean firstTime = true;
			while (it.hasNext()) {
				x = (ccistarmlContent) it.next();
				if (firstTime) {
					firstTime = false;
					if (x.tagName.equals("graphic"))
						allOk = allOk && x.check_graphicDiagramTag(f);
					else if (x.tagName.equals("actor"))
						allOk = allOk && x.check_actorTag(f);
					else if (x.tagName.equals("ielement"))
						allOk = allOk && x.check_ielementExTag(f);
					// else
					// err="diagram content is restricted to [<graphic>] {<actor>|<ielement>}";
					else
						allOk = allOk
								&& x
										.addError(
												"diagram content is restricted to [<graphic>] {<actor>|<ielement>}",
												f);
				} else {
					if (x.tagName.equals("actor"))
						allOk = x.check_actorTag(f);
					else if (x.tagName.equals("ielement"))
						allOk = x.check_ielementExTag(f);
					else
						allOk = allOk
								&& x
										.addError(
												"diagram content is restricted to [<graphic>] {<actor>|<ielement>}",
												f);
				}
			}
		}
		return allOk && !this.addError(err, f);
	}

	public boolean check_actorTag(ccistarmlFile f) {
		String err = "";
		boolean allOk = true;
		ccistarmlContent x;
		if (this.tagName.equals("actor")) {
			if (this.hasAttribute("aref")) {
				if (!f.has_aref((String) this.attribute.get("aref")))
					err = "does not exist referred actor by aref";
				else if (this.attribute.size() > 1)
					err = "aref must be the only one attribute if it is defined";
				else if (this.content.size() > 1)
					err = "already defined actor must have at most only one graphic tag";
				else if (this.content.size() == 1) {
					if (!((ccistarmlContent) this.content.getFirst()).tagName
							.equals("graphic"))
						err = "only a graphic a tag can appear in the context of an already defined actor";
				}
			} else if (!this.hasBasicAtts())
				err = "an actor must have id or name attribute";
			else if (this.type == tagType.auto && this.hasBasicAtts()
					&& !this.hasAttribute("aref"))
				return true;
			else if (this.type == tagType.open && this.hasBasicAtts()
					&& !this.hasAttribute("aref")) {
				int n = this.content.size();
				boolean oneBoundary = false;
				for (int i = 0; i < n; i++) {
					x = (ccistarmlContent) this.content.get(i);
					if (i == 0 && x.tagName.equals("graphic"))
						allOk = allOk && x.check_graphicNode(f);
					else if (x.tagName.equals("actorLink") && !oneBoundary)
						allOk = allOk && x.check_actorLinkTag(f);
					else if (x.tagName.equals("boundary") && !oneBoundary) {
						allOk = allOk && x.check_boundaryTag(f);
						oneBoundary = true;
					} else if (x.tagName.equals("graphic")
							|| x.tagName.equals("actorLink")
							|| x.tagName.equals("boundary"))
						err = "unexpected " + x.tagName + " in this position";
					else if (x.type != tagType.string)
						err = "unexpected " + x.tagName + " inside actor";
					else if (x.type == tagType.string)
						err = "unexpected string '" + x.text.toString().trim()
								+ "' inside actor";
					else
						err = "unexpected error on actor";
					allOk = allOk && !x.addError(err, f);
					err = "";
				}
			} else if (this.hasAttribute("aref") && this.attribute.size() > 1)
				err = "if aref exists then it must be the only one attribute";
			else
				err = "bad formed actor";
		} else {
			err = "expected <actor>";
		}
		return allOk && !this.addError(err, f);
	}

	public boolean check_boundaryTag(ccistarmlFile f) {
		String err = "";
		boolean allOk = true;
		ccistarmlContent x;
		if (this.tagName.equals("boundary")) {
			if (this.attribute.size() > 1
					|| (this.attribute.size() == 1 && !this
							.hasAttribute("type")))
				err = "boundary allows only the type attribute";
			else if (this.type != tagType.open)
				err = "boundary must be an open tag";
			else {
				int n = this.content.size();
				for (int i = 0; i < n; i++) {
					x = (ccistarmlContent) this.content.get(i);
					if (i == 0) {
						if (x.tagName.equals("graphic"))
							allOk = allOk && x.check_graphicPathTag(f);
						else if (x.tagName.equals("ielement"))
							allOk = allOk && x.check_ielementTag(f);
						else if (x.tagName.equals("actor"))
							allOk = allOk && x.check_actorTag(f);
						else {
							f.error.add(new ccfileError("unexpected "
									+ x.tagName + " inside boundary",
									x.myLineNumber, x.myLinePortion));
							allOk = false;
						}
					} else {
						if (x.tagName.equals("ielement"))
							allOk = allOk && x.check_ielementTag(f);
						else if (x.tagName.equals("actor"))
							allOk = allOk && x.check_actorTag(f);
						else {
							f.error.add(new ccfileError("unexpected "
									+ x.tagName + " inside boundary",
									x.myLineNumber, x.myLinePortion));
							allOk = false;
						}
					}
				}
			}
		} else
			err = "expected <boundary>";
		return allOk && !this.addError(err, f);
	}

	public boolean check_ielementTag(ccistarmlFile f) {
		String err = "";
		ccistarmlContent x;
		boolean allOk = true;
		if (this.tagName.equals("ielement")) {
			if (this.hasAttribute("iref")) {
				if (this.attribute.size() > 1)
					err = "iref must be the unique attributte";
				else if (this.content.size() > 1)
					err = "already defined ielement must have at most only one graphic tag";
				else if (this.content.size() == 1)
					if (!((ccistarmlContent) this.content.getFirst()).tagName
							.equals("graphic"))
						err = "only a graphic a tag can appear in the context of an already defined ielement";
			} else if (!this.hasBasicAtts())
				err = "expected id or name attribute";
			else if (!this.hasAttribute("type"))
				err = "type is a mandatory attribute";
			else if (this.type == tagType.open) {
				int n = this.content.size();
				for (int i = 0; i < n; i++) {
					x = (ccistarmlContent) this.content.get(i);
					if (i == 0) {
						if (x.tagName.equals("graphic"))
							allOk = allOk && x.check_graphicNode(f);
						else if (x.tagName.equals("ielementLink"))
							allOk = allOk && x.check_ielementLinkTag(f);
					} else if (x.tagName.equals("ielementLink"))
						allOk = allOk && x.check_ielementLinkTag(f);
					else {
						f.error.add(new ccfileError("unexpected " + x.tagName
								+ " inside ielement", x.myLineNumber,
								x.myLinePortion));
						allOk = false;
					}
				}
			}
		} else
			err = "expected <ielement>";
		return allOk && !this.addError(err, f);
	}

	public boolean check_actorLinkTag(ccistarmlFile f) {
		String err = "";
		ccistarmlContent x;
		boolean allOk = true;
		if (this.tagName.equals("actorLink")) {
			if (!this.hasAttribute("type"))
				err = "type is a mandatory actorLink attribute";
			else if (!this.hasAttribute("aref"))
				err = "aref is a mandatory actorLink attribute";
			else if (this.attribute.size() > 2)
				err = "actorLink only admit type and aref attributes";
			else if (!f.has_aref((String) this.attribute.get("aref")))
				err = "aref references a non-existing actor";
			else if (this.content.size() > 1)
				err = "the actorLink's content must be only one graphic tag";
			else if (this.content.size() == 1) {
				x = (ccistarmlContent) this.content.getFirst();
				if (!x.tagName.equals("graphic"))
					err = "the actorLink's content must be only one graphic tag";
				else
					allOk = x.check_graphicPathTag(f);
			}
		} else
			err = "expected <actorLink>";
		return allOk && !this.addError(err, f);
	}

	public boolean check_ielementLinkTag(ccistarmlFile f) {
		String err = "";
		ccistarmlContent x;
		boolean allOk = true;
		if (this.tagName.equals("ielementLink")) {
			if (!this.hasAttribute("type"))
				err = "type is a mandatory ielementLink attribute";
			else if (this.attribute.get("type").equals("decomposition")) {
				if (this.hasAttribute("value")) {
					if (!this.hasAttributeValue("value", "and,or"))
						err = "decomposition could have 'and' or 'or' value";
				}
			} else if (this.attribute.get("type").equals("contribution")) {
				if (this.hasAttribute("value")) {
					if (!this
							.hasAttributeValue("value",
									"+,-,sup,sub,++,--,break,hurt,some-,some+,unknown,equal,help,make,and,or"))
						err = "contribution could have specific values (e.g. some+)";
				}
			}
			int n = this.content.size();
			if (this.content.size() > 0) {
				for (int i = 0; i < n; i++) {
					x = (ccistarmlContent) this.content.get(i);
					if (i == 0) {
						if (x.tagName.equals("graphic"))
							allOk = allOk && x.check_graphicPathTag(f);
						else if (x.tagName.equals("ielement"))
							allOk = allOk && x.check_ielementTag(f);
					} else if (x.tagName.equals("ielement"))
						allOk = allOk && x.check_ielementTag(f);
					else {
						f.error.add(new ccfileError("unexpected " + x.tagName
								+ " inside ielementLink", x.myLineNumber,
								x.myLinePortion));
						allOk = false;
					}
				}
			}
		} else
			err = "expected <ielementLink>";
		return allOk && !this.addError(err, f);
	}

	public boolean check_ielementExTag(ccistarmlFile f) {
		String err = "";
		ccistarmlContent x;
		boolean allOk = true;
		if (this.tagName.equals("ielement")) {
			if (this.hasAttribute("iref")) {
				if (this.attribute.size() > 1)
					err = "iref must be the unique attributte";
				else if (this.content.size() > 1)
					err = "already defined ielement must have at most only one graphic tag";
				else if (this.content.size() == 1)
					if (!((ccistarmlContent) this.content.getFirst()).tagName
							.equals("graphic"))
						err = "only a graphic a tag can appear in the context of an already defined ielement";
			} else if (!this.hasBasicAtts())
				err = "expected id or name attribute";
			else if (!this.hasAttribute("type"))
				err = "type is a mandatory attribute";
			else if (this.type == tagType.open) {
				int n = this.content.size();
				for (int i = 0; i < n; i++) {
					x = (ccistarmlContent) this.content.get(i);
					if (i == 0) {
						if (x.tagName.equals("graphic"))
							allOk = allOk && x.check_graphicNode(f);
						else if (x.tagName.equals("dependency"))
							allOk = allOk && x.check_dependencyTag(f);
						else if (x.tagName.equals("ielementLink"))
							allOk = allOk && x.check_ielementLinkTag(f);
					} else if (i == 1) {
						if (x.tagName.equals("dependency"))
							allOk = allOk && x.check_dependencyTag(f);
						else if (x.tagName.equals("ielementLink"))
							allOk = allOk && x.check_ielementLinkTag(f);
					} else if (x.tagName.equals("ielementLink"))
						allOk = allOk && x.check_ielementLinkTag(f);
					else {
						f.error.add(new ccfileError("unexpected " + x.tagName
								+ " inside ielement", x.myLineNumber,
								x.myLinePortion));
						allOk = false;
					}
				}
			}
		} else
			err = "expected <ielement>";
		return allOk && !this.addError(err, f);
	}

	public boolean check_dependerTag(ccistarmlFile f) {
		return check_dependerdeeTag("depender", f);
	}

	public boolean check_dependeeTag(ccistarmlFile f) {
		return check_dependerdeeTag("dependee", f);
	}

	public boolean check_dependerdeeTag(String tg, ccistarmlFile f) {
		String err = "";
		boolean allOk = true;
		ccistarmlContent x;
		int countAtt = 1;
		if (this.tagName.equals(tg)) {
			if (!this.hasAttribute("aref"))
				err = "attribute aref is mandatory in " + tg;
			else if (!f.has_aref((String) this.attribute.get("aref")))
				err = "does not exist referred actor by aref";
			if (this.hasAttribute("iref")) {
				countAtt++;
				String cod = (String) this.attribute.get("iref");
				if (!f.has_iref(cod))
					err = "does not exist referred ielement by iref";
			}
			if (this.hasAttribute("value"))
				countAtt++;
			if (this.attribute.size() > countAtt)
				err = tg + " does not allow extra attributes";
			if (this.type == tagType.open) {
				if (this.content.size() > 1)
					err = "only one graphic tag is allowed in the context of "
							+ tg;
				else if (this.content.size() == 1) {
					x = (ccistarmlContent) this.content.get(0);
					if (x.tagName.equals("graphic"))
						allOk = allOk && x.check_graphicPathTag(f);
					else {
						f.error.add(new ccfileError("unexpected " + x.tagName
								+ " inside " + tg, x.myLineNumber,
								x.myLinePortion));
						allOk = false;
					}
				}
			}

		} else
			err = "expected <" + tg + ">";
		return allOk && !this.addError(err, f);
	}

	public boolean check_graphicNode(ccistarmlFile f) {
		String err = "";
		int attCount = 0;
		if (!this.tagName.equals("graphic"))
			err = "expected <graphic>";
		else if (!this.check_mandatoryAttribute("content", "SVG|basic"))
			err = "graphic must have content attribute set to 'basic' or 'SVG'";
		else if (this.hasAttributeValue("content", "SVG")
				&& this.attribute.size() > 1)
			err = "if content='SVG' then no extra atributes are allowed";
		else if (this.hasAttributeValue("content", "basic"))
			return this.check_g_options_node(f);
		return !this.addError(err, f);
	}

	public boolean check_graphicPathTag(ccistarmlFile f) {
		String err = "";
		int attCount = 0;
		if (!this.tagName.equals("graphic"))
			err = "expected <graphic>";
		else if (!this.hasAttribute("content"))
			err = "graphic must have content attribute set to 'basic' or 'SVG'";
		else if (this.hasAttributeValue("content", "SVG")
				&& this.attribute.size() > 1)
			err = "if content='SVG' then no extra atributes are allowed";
		else if (this.hasAttributeValue("content", "basic")
				&& this.type == tagType.auto) {
			return this.check_g_options_shape(f);
		} else if (this.hasAttributeValue("content", "basic")
				&& this.type == tagType.open) {
			return this.check_g_options_path(f);
		}
		return (!this.addError(err, f));
	}

	public boolean check_graphicDiagramTag(ccistarmlFile f) {
		String err = "";
		int attCount = 0;
		if (!this.tagName.equals("graphic"))
			err = "expected <graphic>";
		else if (!this.check_mandatoryAttribute("content", "SVG|basic"))
			err = "graphic must have content attribute set to 'basic' or 'SVG'";
		else if (this.hasAttributeValue("content", "SVG")
				&& this.attribute.size() > 1)
			err = "if content='SVG' then no extra atributes are allowed";
		else if (this.hasAttributeValue("content", "basic"))
			return this.check_g_options_diagram(f);
		return !this.addError(err, f);
	}

	public boolean addError(String err, ccistarmlFile f) {
		if (err.length() > 0) {
			f.error.add(new ccfileError(err, this.myLineNumber,
					this.myLinePortion));
			return true;
		}
		return false;
	}

	public boolean check_g_options_node(ccistarmlFile f) {
		String pattNumber = "[0-9]+(\\.[0-9]*)?";
		String pattColor = "[a-fA-F0-9]{6}";
		String err = "";
		List atts;
		LinkedList ot = new LinkedList();
		if (!this.check_g_basic_atts(f))
			return false;
		if (!this.check_optionalAttribute("unit", "cm|in|pt"))
			err = "unit value must be 'cm', 'in' or 'pt'";
		if (!this.check_optionalAttribute("bgcolor", pattColor))
			err = "bgcolor value must be an rgb hexadecimal expression (e.g. A100ee)";
		if (!this.check_optionalAttribute("fontcolor", pattColor))
			err = "fontcolor value must be an rgb hexadecimal expression (e.g. A100ee)";
		if (!this.check_optionalAttribute("fontsize", pattNumber))
			err = "fonsize value must be a number";
		if (err.equals("")) {
			atts = new LinkedList(this.attribute.keySet());
			ot.addAll(Arrays.asList(new String[] { "content", "xpos", "ypos",
					"width", "height", "unit", "bgcolor", "fontcolor",
					"fontfamily", "fontsize" }));
			atts.removeAll(ot);
			if (atts.size() > 0)
				err = "illegal attributes in graphic tag";
		}
		return !this.addError(err, f);
	}

	public boolean check_g_options_diagram(ccistarmlFile f) {
		String pattNumber = "[0-9]+(\\.[0-9]*)?";
		String pattColor = "[a-fA-F0-9]{6}";
		String err = "";
		List atts;
		LinkedList ot = new LinkedList();
		if (!this.check_mandatoryAttribute("width", pattNumber))
			err = "width is mandatory and must have a number value";
		else if (!this.check_mandatoryAttribute("height", pattNumber))
			err = "height is mandatory and must have a number value";
		// v0.6.1 ----------------------------------------------------
		else if (!this.check_mandatoryAttribute("xpos", pattNumber))
			err = "xpos is mandatory and must have a number value";
		else if (!this.check_mandatoryAttribute("ypos", pattNumber))
			err = "ypos is mandatory and must have a number value";
		// -----------------------------------------------------------
		if (!this.check_optionalAttribute("unit", "cm|in|pt"))
			err = "unit value must be 'cm', 'in' or 'pt'";
		if (!this.check_optionalAttribute("bgcolor", pattColor))
			err = "bgcolor value must be an rgb hexadecimal expression (e.g. A100ee)";
		if (err.equals("")) {
			atts = new LinkedList(this.attribute.keySet());
			// ot.addAll(Arrays.asList(new String[]{"content","width",
			// "height","unit","bgcolor"})); // v0.6
			ot.addAll(Arrays.asList(new String[] { "content", "width",
					"height", "unit", "bgcolor", "xpos", "ypos" })); // 0.6.1
			atts.removeAll(ot);
			if (atts.size() > 0)
				err = "illegal attributes in graphic tag";
		}
		return !this.addError(err, f);

	}

	public boolean check_g_options_shape(ccistarmlFile f) {
		String pattNumber = "[0-9]+(\\.[0-9]*)?";
		String pattColor = "[a-fA-F0-9]{6}";
		String err = "";
		List atts;
		LinkedList ot = new LinkedList();
		if (!this.check_g_basic_atts(f))
			return false;
		if (!this.check_mandatoryAttribute("shape", "ellipse|rect"))
			err = "in this context shape is mandatory and must have the values 'ellipse' or 'rect'";
		if (!this.check_optionalAttribute("unit", "cm|in|pt"))
			err = "unit value must be 'cm', 'in' or 'pt'";
		if (!this.check_optionalAttribute("bgcolor", pattColor))
			err = "bgcolor value must be an rgb hexadecimal expression (e.g. A100ee)";
		if (!this.check_optionalAttribute("fontcolor", pattColor))
			err = "fontcolor value must be an rgb hexadecimal expression (e.g. A100ee)";
		if (!this.check_optionalAttribute("fontsize", pattNumber))
			err = "fonsize value must be a number";
		if (err.equals("")) {
			atts = new LinkedList(this.attribute.keySet());
			ot.addAll(Arrays.asList(new String[] { "content", "xpos", "ypos",
					"width", "height", "unit", "shape", "bgcolor", "fontcolor",
					"fontfamily", "fontsize" }));
			atts.removeAll(ot);
			if (atts.size() > 0)
				err = "illegal attributes in graphic tag";
		}
		return !this.addError(err, f);

	}

	public boolean check_g_options_path(ccistarmlFile f) {
		String pattNumber = "[0-9]+(\\.[0-9]*)?";
		String pattColor = "[a-fA-F0-9]{6}";
		String err = "";
		List atts;
		LinkedList ot = new LinkedList();
		if (!this.check_mandatoryAttribute("shape", "polyline|spline"))
			err = "in this context shape is mandatory and must have the values 'polyline' or 'spline'";
		else if (!this.check_optionalAttribute("unit", "cm|in|pt"))
			err = "unit value must be 'cm', 'in' or 'pt'";
		else if (!this.check_optionalAttribute("bgcolor", pattColor))
			err = "bgcolor value must be an rgb hexadecimal expression (e.g. A100ee)";
		else if (!this.check_optionalAttribute("fontcolor", pattColor))
			err = "fontcolor value must be an rgb hexadecimal expression (e.g. A100ee)";
		else if (!this.check_optionalAttribute("fontsize", pattNumber))
			err = "fonsize value must be a number";
		if (err.equals("")) {
			atts = new LinkedList(this.attribute.keySet());
			ot.addAll(Arrays.asList(new String[] { "content", "unit", "shape",
					"bgcolor", "fontcolor", "fontfamily", "fontsize" }));
			atts.removeAll(ot);
			if (atts.size() > 0)
				err = "illegal attributes in graphic tag";
		}
		return !this.addError(err, f);
	}

	public boolean check_g_basic_atts(ccistarmlFile f) {
		String pattNumber = "[0-9]+(\\.[0-9]*)?";
		String err = "";
		if (!this.check_mandatoryAttribute("xpos", pattNumber))
			err = "xpos is mandatory and must have a number value";
		else if (!this.check_mandatoryAttribute("ypos", pattNumber))
			err = "ypos is mandatory and must have a number value";
		else if (!this.check_mandatoryAttribute("width", pattNumber))
			err = "width is mandatory and must have a number value";
		else if (!this.check_mandatoryAttribute("height", pattNumber))
			err = "height is mandatory and must have a number value";
		return !this.addError(err, f);
	}

	public boolean check_mandatoryAttribute(String att) {
		return this.hasAttribute(att);
	}

	public boolean check_mandatoryAttribute(String att, String pattern) {
		if (!this.hasAttribute(att))
			return false;
		String aux = (String) this.attribute.get(att);
		return aux.matches(pattern);
	}

	public boolean check_optionalAttribute(String att, String pattern) {
		if (!this.hasAttribute(att))
			return true;
		String aux = (String) this.attribute.get(att);
		return aux.matches(pattern);
	}

	public boolean check_dependencyTag(ccistarmlFile f) {
		String err = "";
		boolean allOk = true;
		ccistarmlContent x;
		if (this.tagName.equals("dependency")) {
			if (this.attribute.size() > 0)
				err = "dependency does not admit attributes";
			else if (this.content.size() == 0)
				err = "dependency must have at least one depender tag";
			else {
				int n = this.content.size();
				boolean dependerYet = true;
				for (int i = 0; i < n; i++) {
					x = (ccistarmlContent) this.content.get(i);
					if (i == 0) {
						if (x.tagName.equals("depender"))
							allOk = allOk && x.check_dependerTag(f);
						else {
							f.error.add(new ccfileError(
									"unexpected first element " + x.tagName
											+ " inside dependency",
									x.myLineNumber, x.myLinePortion));
							allOk = false;
						}
					} else {
						if (x.tagName.equals("depender") && dependerYet)
							allOk = allOk && x.check_dependerTag(f);
						else if (x.tagName.equals("dependee")) {
							allOk = allOk && x.check_dependeeTag(f);
							dependerYet = false;
						} else if (x.tagName.equals("depender")) {
							f.error.add(new ccfileError(
									"dependers must precede dependees",
									x.myLineNumber, x.myLinePortion));
							allOk = false;
						} else {
							f.error.add(new ccfileError(
									"unexpected " + x.tagName
											+ "  in the dependency context",
									x.myLineNumber, x.myLinePortion));
							allOk = false;
						}
					}
				}// for
			}// else
		}// if
		else
			err = "expected <dependency>";
		return allOk && !this.addError(err, f);
	}

	public boolean hasBasicAtts() {
		return this.hasAttribute("id") || this.hasAttribute("name");
	}

	public boolean hasAttribute(String att) {
		if (this.attribute.get(att) != null)
			if (!this.attribute.get(att).equals(""))
				return true;
		return false;
	}

	public boolean hasAttributeValue(String att, String vals) {
		String v[] = vals.split(",");
		boolean found = false;
		if (this.hasAttribute(att)) {
			int n = v.length;
			int i = 0;
			String value = (String) this.attribute.get(att);
			while (!found && i < n) {
				if (v[i].equals(value))
					found = true;
				i++;
			}
		}
		return found;
	}

	public String loadToERelement(ERelementList erL) {
		ccistarmlContent x;
		String cod;
		ERelement e, ew;
		if (this.root) {
			x = (ccistarmlContent) this.content.get(1);
			if (x != null)
				return x.loadToERelement(erL);
			else
				return "";
		}
		if (this.tagName.equals("graphic")) {
			this.CountPoint = 0;
			if (this.hasAttributeValue("content", "SVG")) {
				e = new ERelement("graphicSVG", this.collectSVG(), erL);
				return e.ID;
			}
		}
		if (this.tagName.equals("point")) {
			this.attribute.put("order", "" + this.CountPoint++);
		}
		if (!this.tagName.equals("")) {
			Hashtable w;
			e = new ERelement(this.tagName, this.attribute, erL);
			if (this.tagName.equals("istarml"))
				erL.istarml_ID = e.diagram = e.ID;
			if (this.tagName.equals("diagram")) {
				erL.currentDiagram = e.ID;
				e.diagram = erL.istarml_ID;
			}
			Iterator it = this.content.iterator();
			while (it.hasNext()) {
				x = (ccistarmlContent) it.next();
				cod = x.loadToERelement(erL);
				w = new Hashtable();
				w.put("child", cod);
				w.put("parent", e.ID);
				ew = new ERelement("is_in", w, erL);
			}
			return e.ID;
		} else if (this.type == tagType.string) {
			e = new ERelement("string", this.text.toString(), erL);
			return e.ID;
		}
		return "";
	}

	public String collectSVG() {
		String ret = "";
		if (this.tagName.equals("graphic")
				&& this.hasAttributeValue("content", "SVG")) {
			Iterator it = this.content.iterator();
			ccistarmlContent x;
			while (it.hasNext()) {
				x = (ccistarmlContent) it.next();
				ret += x.buildXML();
			}
			return ret;
		} else
			return null;
	}

	public String buildXML() {
		String ret = this.buildXML("");
		return ret.trim();
	}

	private String buildXML(String space) {
		// v0.6
		String space2 = space + "    ";
		String ret = "\n" + space;
		String rt = "";
		Iterator it;
		ccistarmlContent x;
		if (this.root) {
			it = this.content.iterator();
			while (it.hasNext()) {
				x = (ccistarmlContent) it.next();
				rt += x.buildXML(space);
			}
			ret = rt;
		}
		// --------
		else if (this.tagName.equals("xml"))
			ret += "<?xml" + this.buildXMLatt() + "?>";
		else if (this.type == tagType.string)
			ret += this.text.toString();
		else if (this.type == tagType.auto)
			ret += "<" + this.tagName + this.buildXMLatt() + "/>";
		else if (this.type == tagType.open) {
			ret += "<" + this.tagName + this.buildXMLatt() + ">";
			it = this.content.iterator();
			while (it.hasNext()) {
				x = (ccistarmlContent) it.next();
				ret += x.buildXML(space2);
			}
			ret += "\n" + space + "</" + this.tagName + ">";
		}
		return ret;
	}

	// v0.6
	public String buildXMLatt() {
		LinkedList t = new LinkedList(Arrays.asList(new String[] { "id",
				"name", "type", "iref", "aref", "value", "content", "xpos",
				"ypos", "width", "height" }));
		Set key = new HashSet(this.attribute.keySet());
		String att = "";
		String val = "";
		String ret = "";
		while (!t.isEmpty()) {
			att = (String) t.poll();
			if (key.contains(att)) {
				val = (String) this.attribute.get(att);
				ret += " " + att + "=\"" + val + "\"";
				key.remove(att);
			}
		}
		Iterator it = key.iterator();
		while (it.hasNext()) {
			att = (String) it.next();
			val = (String) this.attribute.get(att);
			ret += " " + att + "=\"" + val + "\"";
		}
		return ret;
	}

	public void removeXmlComments() {
		Iterator it = this.content.iterator();
		ccistarmlContent x;
		LinkedList toDel = new LinkedList();
		while (it.hasNext()) {
			x = (ccistarmlContent) it.next();
			if (x.type == tagType.comment)
				toDel.add(x);
			else
				x.removeXmlComments();
		}
		this.content.removeAll(toDel);
	}

}

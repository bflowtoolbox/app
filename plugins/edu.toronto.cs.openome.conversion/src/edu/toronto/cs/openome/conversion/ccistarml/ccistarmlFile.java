/*
 * ccistarmlFile.java
 * it is part of the ccistarml Java Package
 * version 0.6
 * Created on July 4 of 2007, By Carlos Cares
 * Updated to v0.6.1  on September 20 of 2007 By Carlos Cares
 */

package edu.toronto.cs.openome.conversion.ccistarml;

import java.io.*;
import java.lang.*;
import java.util.*;

public class ccistarmlFile {

	public StringBuffer text = new StringBuffer();
	public String fileName = "";
	private static FileInputStream ar;
	private ccistarmlContent xml = null;
	private int length;
	private int position = 0;
	public boolean externalFile = false;
	public LinkedList error = new LinkedList();
	public int currentLineCount = 1;
	public String currentLinePortion = "";
	public int state = 0;
	public boolean goUp = false;

	public LinkedList aRef = new LinkedList();
	public LinkedList iRef = new LinkedList();
	public LinkedList oRef = new LinkedList();

	public ccistarmlFile(String txtXml) {
		this.loadStringAddingHeader(txtXml);
		// this.text.append(txtXml);
	}

	public ccistarmlFile() {
		this.text.append("");
	}

	// v0.6
	public ccistarmlContent add_diagram(String name) {
		ccistarmlContent x = null;
		ccistarmlContent d;
		if (this.xml == null) {
			this.text
					.append("<?xml version='1.0'?><istarml version='1.0'></istarml>");
			this.length = this.text.length();
			this.xmlParser();
			x = (ccistarmlContent) this.xml.content.get(1);
		} else if (this.xml.content.size() != 2) {
			this.text.delete(0, this.text.length());
			this.text
					.append("<?xml version='1.0'?><istarml version='1.0'></istarml>");
			this.xmlParser();
			x = (ccistarmlContent) this.xml.content.get(1);
		} else {
			x = (ccistarmlContent) this.xml.content.get(1);
			if (!x.tagName.equals("istarml")) {
				this.text.delete(0, this.text.length());
				this.text
						.append("<?xml version='1.0'?><istarml version='1.0'></istarml>");
				this.xmlParser();
				x = (ccistarmlContent) this.xml.content.get(1);
			}
		}
		d = new ccistarmlContent("diagram");
		d.attribute.put("name", name);
		d.setOpen();
		x.content.addLast(d);
		return d;
	}

	// v0.6 true when removing success.
	public boolean remove_diagramByID(String id) {
		ccistarmlContent x;
		if (this.xml == null)
			return false;
		if (this.xml.content.size() < 2)
			return false;
		x = (ccistarmlContent) this.xml.content.get(1);
		if (!x.tagName.equals("istarml"))
			return false;
		return x.remove_content_by_attribute("id", id);
	}

	// v0.6
	public boolean remove_diagramByName(String name) {
		ccistarmlContent x;
		if (this.xml == null)
			return false;
		if (this.xml.content.size() < 2)
			return false;
		x = (ccistarmlContent) this.xml.content.get(1);
		if (!x.tagName.equals("istarml"))
			return false;
		return x.remove_content_by_attribute("name", name);
	}

	// v0.6
	public boolean remove_diagram(ccistarmlContent d) {
		boolean ret = false;
		LinkedList x;
		ccistarmlContent z;
		if (d.tagName.equals("diagram")) {
			z = (ccistarmlContent) this.xml.content.get(1);
			if (z != null) {
				x = (LinkedList) z.content;
				if (x != null) {
					if (x.contains(d)) {
						x.remove(d);
						ret = true;
					}
				}
			}
		}
		return ret;
	}

	public ccistarmlContent xmlStructure() {
		return this.xml;
	}

	public int errors() {
		return this.error.size();
	}

	public boolean saveFile(String fileName) {
		try {
			FileOutputStream f = new FileOutputStream(fileName);
			f.write(this.xml.buildXML().getBytes());
			f.close();
			return true;
		} catch (IOException e) {
			this.error.addLast(new ccfileError("File input error on "
					+ fileName));
			return false;
		}
	}

	// v0.6
	public boolean loadStringAddingHeader(String xmlText) {
		this.text.delete(0, this.text.length());
		this.text.append("<?xml version=\"1.0\"?>" + xmlText);
		this.externalFile = false;
		this.position = 0;
		this.length = this.text.length();
		return true;
	}

	public boolean loadFile(String fileName) {
		try {
			char ch;
			int rd;
			int er = 0;
			FileInputStream f = new FileInputStream(fileName);
			this.text.delete(0, this.text.length());
			rd = f.read();
			while (rd != -1) {
				if (this.text.capacity() > 0)
					this.text.append((char) rd);
				else
					er++;
				rd = f.read();
			}
			f.close();
			this.fileName = fileName;
			this.externalFile = true;
			if (er > 0) {
				this.error.addLast(new ccfileError("fileName " + fileName
						+ " too long"));
				return false;
			}
			this.length = this.text.length();
			this.position = 0;
			return true;
		} catch (FileNotFoundException e) {
			this.error.addLast(new ccfileError("No input file " + fileName));
			return false;
		} catch (IOException e) {
			this.error.addLast(new ccfileError("File input error on "
					+ fileName));
			return false;
		}
	}

	public boolean xmlParser() {
		this.xml = new ccistarmlContent(this);
		this.xml.removeEmptyTexts();
		return !this.hasErrors();
	}

	public void loadRef() {
		this.xml.load_istarmlRef(this);
	}

	public boolean istarmlParser() {
		this.loadRef();
		this.xml.removeXmlComments();
		this.xml.istarmlParsing(this);
		if (this.hasErrors())
			return false;
		return true;
	}

	public void displayXML() {
		this.xml.display("");
	}

	public void displayErrors() {
		Iterator it = this.error.iterator();
		ccfileError err;
		while (it.hasNext()) {
			err = (ccfileError) it.next();
			err.display();
		}
	}

	public boolean eof() {
		if (this.position >= this.length)
			return true;
		else
			return false;
	}

	public char read() {
		if (!this.eof()) {
			char ch = this.text.charAt(this.position);
			this.position++;
			return ch;
		} else
			return (char) -1;
	}

	public void skip(int step) {
		this.position += step;
		if (this.position < 0)
			this.position = 0;
		if (this.eof())
			this.position = this.length;
	}

	public boolean hasErrors() {
		return this.error.size() > 0;
	}

	public boolean has_aref(String ref) {
		return this.aRef.contains(ref);
	}

	public boolean has_iref(String ref) {
		return this.iRef.contains(ref);
	}

	public LinkedList errorList() {
		LinkedList w = new LinkedList(this.error);
		return w;
	}

	// v0.6
	public String buildXML() {
		String ret = "";
		if (this.xml != null)
			ret = this.xml.buildXML();
		return ret;
	}

	// v0.6
	public ccistarmlContent mainTagStructure() {
		if (this.xml == null)
			return null;
		if (this.xml.content == null)
			return null;
		if (this.xml.content.size() <= 1)
			return null;
		return (ccistarmlContent) this.xml.content.get(1);
	}
}

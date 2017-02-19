/*
 * ccfileError.java
 * it is part of the ccistarml Java Package
 * version 0.6
 * Created on July 4 of 2007, By Carlos Cares
 * Updated to v0.6.1  on September 20 of 2007 By Carlos Cares
 */

package edu.toronto.cs.openome.conversion.ccistarml;

import java.lang.String;
import java.io.*;

public class ccfileError {
	public boolean isXmlError = true;
	public String error;
	public int line;
	public String textLine;

	public ccfileError(String errorDescrip) {
		this.isXmlError = false;
		this.line = 0;
		this.textLine = "";
		this.error = errorDescrip;
	}

	public ccfileError(String err, int Ln, String txLn) {
		this.error = err;
		this.line = Ln;
		this.textLine = txLn;
	}

	public void display() {
		if (this.isXmlError && this.textLine != null)
			System.out.println("Line " + this.line + ": "
					+ this.textLine.trim() + " Error:" + this.error);
		else
			System.out.println(this.error);
	}
}// de la clase ccfileError

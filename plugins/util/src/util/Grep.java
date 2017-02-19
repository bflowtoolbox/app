/*
 * @(#)Grep.java	1.3 01/12/13
 * Search a list of files for lines that match a given regular-expression
 * pattern.  Demonstrates NIO mapped byte buffers, charsets, and regular
 * expressions.
 *
 * Copyright 2001-2002 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or 
 * without modification, are permitted provided that the following 
 * conditions are met:
 * 
 * -Redistributions of source code must retain the above copyright  
 * notice, this  list of conditions and the following disclaimer.
 * 
 * -Redistribution in binary form must reproduct the above copyright 
 * notice, this list of conditions and the following disclaimer in 
 * the documentation and/or other materials provided with the 
 * distribution.
 * 
 * Neither the name of Sun Microsystems, Inc. or the names of 
 * contributors may be used to endorse or promote products derived 
 * from this software without specific prior written permission.
 * 
 * This software is provided "AS IS," without a warranty of any 
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND 
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY 
 * EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY 
 * DAMAGES OR LIABILITIES  SUFFERED BY LICENSEE AS A RESULT OF  OR 
 * RELATING TO USE, MODIFICATION OR DISTRIBUTION OF THE SOFTWARE OR 
 * ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE 
 * FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, 
 * SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER 
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF 
 * THE USE OF OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN 
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * You acknowledge that Software is not designed, licensed or 
 * intended for use in the design, construction, operation or 
 * maintenance of any nuclear facility. 
 */
package util; 

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.util.regex.*;

public class Grep {

	// Charset and decoder for ISO-8859-15
	private  Charset charset = Charset.forName("ISO-8859-15");
	private  CharsetDecoder decoder = charset.newDecoder();

	// Pattern used to parse lines
	private  Pattern linePattern = Pattern.compile(".*\r?\n");

	// The input pattern that we're looking for
	private  Pattern pattern;

	/**
	 * Compile the pattern
	 * 
	 * @param pat
	 * @return
	 * @throws PatternSyntaxException
	 */
	private Pattern compile(String pat) throws PatternSyntaxException {
		return pattern = Pattern.compile(pat);

	}
	
	/**
	 * Create a new file parser that will look for the given pattern.
	 * @param pattern the regular expression to look for.
	 * @throws PatternSyntaxException
	 */
	public Grep (String pattern) throws PatternSyntaxException {
		compile (pattern);
	}

	private boolean grep(File f, CharBuffer cb) {
		Matcher lm = linePattern.matcher(cb); // Line matcher
		Matcher pm = null; // Pattern matcher
		int lines = 0;
		while (lm.find()) {
			lines++;
			CharSequence cs = lm.group(); // The current line
			if (pm == null)
				pm = pattern.matcher(cs);
			else
				pm.reset(cs);
			if (pm.find())
				//System.out.print(f + ":" + lines + ":" + cs);
				return true;
			if (lm.end() == cb.limit())
				break;
		}
		return false;
	}

	/**
	 * Search the file f, for occurrences of the input pattern that was set in
	 * the constructor.
	 * @param f
	 *            the file to be searched
	 * @return true if the pattern was found, false otherwise
	 */
	public boolean patternOccurrsOnce(File f) throws IOException {
		
		// Open the file and then get a channel from the stream
		FileInputStream fis = new FileInputStream(f);
		FileChannel fc = fis.getChannel();

		// Get the file's size and then map it into memory
		int fileSize = (int) fc.size();
		MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fileSize);

		// Decode the file into a char buffer
		CharBuffer cb = decoder.decode(bb);

		// Perform the search
		if (grep(f, cb)) {
			fc.close();
			return true;
		} else {
			// Close the channel and the stream
			fc.close();
			return false;
		}

		
	}

}
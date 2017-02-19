package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

public class Computing {
	
    /** 
     * 
     * @param name
     * @return the filename prepended with the tmp directory name
     */
	public static String getTempFilename(String name) {
		String tmpDir = System.getProperty("tmpDir");		
		if (tmpDir == null)
			tmpDir = name;
		else {
			File d = new File(tmpDir); 
			if (!d.exists()) {
				d.mkdir();
			}
			if (!d.isDirectory())
				return name;
			else
				return tmpDir + File.separator + name;
		}
//		Debugging.o(name);
		return name;
	}
	
	/**
	 * @param name
	 * @return
	 */
	public static String prepend(String prefix, String name) {
		if (name.charAt(0) == '"')
			name = "\"" + prefix + name.substring(1).trim(); 
		else
			if (name.indexOf("[")>=0) {
				name = "\"" + prefix + name.substring(0, name.indexOf("[")).trim() + "\" " + name.substring(name.indexOf("["));
			} else {
				name = "\"" + prefix + name + "\"";
			}
		return name;
	}

	/*
	 * Yijun: Refactored method
	 */
	public static FileWriter createWriterWithExtension(File file, String ext) {
		String completepath = file.getAbsolutePath();
		if (!completepath.endsWith(ext)) {
			completepath = completepath.concat(ext);
		}
		if (file.exists()) {
			file.delete();	//	D.o("file deleted");
		}
		try {
//			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			return writer;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @param f
	 * @param string
	 * @return
	 */
	public static File changeFileExtension(File f, String ext) {
		String completepath = f.getAbsolutePath();
		if (!completepath.endsWith(ext)) {
			if (completepath.indexOf(".")>=0)
				completepath = completepath.substring(0, completepath.lastIndexOf("."));
			completepath = completepath.concat(ext);
		}
		return new File(completepath);
	}

	public static String unique_goal_name(String who, String why, String what)
	{
		String [] types = {"Agent"}; // default
		if (who!=null && !who.equals("") && !who.equals("*")) 
			who = "<" + Computing.quotation(Computing.strip_type_prefixes(types, who.trim())) + 
			   (why == null? ">" : ">::");
		else
			who = "";		
		if (what!=null && !what.equals("*"))
			what = " [" + what.trim() + "]";
		else
			what = "";		
		return who + (why!=null? (why.trim() + what) : "");
	}

	/**
	 * @param token
	 * @return
	 */
	public static String quotation(String token) {
		boolean needIt = false;
		token = token.trim();
		for (int i=0; i<token.length(); i++) {
			if ('A' <= token.charAt(i) && token.charAt(i)<='Z' || 'a' <= token.charAt(i)&& token.charAt(i)<='z'
				|| '0' <= token.charAt(i) && token.charAt(i)<='9' 
				|| '*' == token.charAt(i) 
				|| '_' == token.charAt(i)) continue;
			needIt = true;
		}
		if (needIt)
			token = "\"" + token + "\"";
		return token;
	}

	public static String strip_quote(String s) {
		if (s == null || s.length()==0)
			return "";
		String str = "";
		for (int i=0; i<s.length(); i++) {
			if (s.charAt(i) != '"')
				str += s.charAt(i);
		}
		return str;
	}

	/**
	 * Show the file contents of the directory
	 * @param dir -- the directory
	 * @param suffix -- any file with the suffix will be matched
	 * @return -- the matched files
	 */
	public static ArrayList<String> listContents(File dir, String suffix) {
	   // Assume that dir is a directory.  List its contents.
	   ArrayList<String> listed_files = new ArrayList<String>();
	   if (dir == null) {
		   return listed_files;
	   }
	   File[] files;  // The names of the files in the directory.
	   files = dir.listFiles();
	   if (files ==null)
	   	  return null;
	   for (int i = 0; i < files.length; i++) {
	       File f = files[i]; 
	       	// One of the files in the directory.
	       if ( !f.isDirectory() ) {
	           // For a regular file, just print the name, files[i].
	           if (f.getAbsolutePath().indexOf(suffix)>=0) { 
		       	   listed_files.add(""+files[i]);
	           }
	       }
	   }
	   return listed_files;
	}

	public static String strip_type_prefixes(String prefixes[], String name) {		
		name = strip_quote(name);
		for (int i=0; i<prefixes.length; i++) {
			String prefix = prefixes[i];
			String n = new String(name);
			if (name.indexOf(prefix+ " ")>=0) {
				name = n.substring(0, n.indexOf(prefix+ " ")) + 
					name.substring(n.indexOf(prefix+ " ") + prefix.length()+1, 
								n.length());				
				break;
			}
		}
		return strip_quote(name);
	}

    /**
	 * @param program
	 */
	static void copy_file(String input_file, String output_file) {
		if (input_file.equals(output_file)) 
			return;
		try {
			
			try (BufferedReader i = new BufferedReader(new FileReader(new File(input_file)))) {
				try (BufferedWriter o = new BufferedWriter(new FileWriter(new File(output_file)))) {
					for (String a = i.readLine(); a !=null; a = i.readLine()) {
					    o.write(a);
					}
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static String SDtoLabel(float s, float d) {
		if(s == 1 && d == 0) {
			return "IStarSatisficedElementLabel";		    			
		} else if(s == 0 && d == 1){
			return "IStarDeniedElementLabel";		    			
		} else if(s > d){
			return "IStarWeaklySatisficedElementLabel";		    			
		} else if(s < d){
			return "IStarWeaklyDeniedElementLabel";		    			
		} else if(s == d && s >= 0.5){
			return "IStarConflictElementLabel";
		} else if(s == d && s < 0.5){
			return "IStarUndecidedElementLabel";		    			
		}
		return ""; 
	}

	public static float[] labelToSD(String string) {
		float s = 0, d = 0;
		if(string.equals("IStarConflictElementLabel")) {
			s = 1; 
			d = 1;
		}
		else if(string.equals("IStarWeaklySatisficedElementLabel")) {
			s = 0.5f; 
			d = 0;
		}
		else if(string.equals("IStarSatisficedElementLabel")) {
			s = 1;
			d = 0;
		}
		else if(string.equals("IStarWeaklyDeniedElementLabel")) {
			s = 0;
			d = 0.5f;
		}
		else if(string.equals("IStarUndecidedElementLabel")) {
			s = 0;
			d = 0;
		}
		else if(string.equals("IStarDeniedElementLabel")) {
			s = 0;
			d = 1;
		}
		float sd [] = new float[2];
		sd[0] = s;
		sd[1] = d;
		return sd;
	}

	public static void printSystemProperties() {
		Properties ps = System.getProperties();
		for (Enumeration<Object> i = ps.keys(); i.hasMoreElements();) {
			String key = (String) i.nextElement();
			String val = ps.getProperty(key);
			System.out.println(key  + "=" + val);
		}
	}

	public static String createFileWithExtension(File file, String ext) {
		String completepath = file.getAbsolutePath();
		if (!completepath.endsWith(ext)) {
			completepath = completepath.concat(ext);
		}
		if (file.exists()) {
			file.delete();	//	D.o("file deleted");
		}
//		try {
//			file.createNewFile();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
		return completepath;
	}

	/**
	 * Find whether the property exists and is true
	 * @param string name
	 * @return true if the property name exists and is set to be true
	 */
	public static boolean propertyHolds(String string) {
		String p = System.getProperty(string);
		return p!=null && p.equalsIgnoreCase("true");
	}

	/**
	 *  Get the URL.
	 */
	public static URL fetchURL(String url) {
		try {
			return new URL(url);
		} catch (MalformedURLException e) {
			return null;
		}
	}

	public static String strip_spaces(String name) {
		return name.replaceAll(" ", "");
	}
}


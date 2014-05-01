package org.bflow.toolbox.hive.addons.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Collects and manages all created files during a protocol run.
 * @author Arian Storch
 * @since 19/11/10
 *
 */
public class TemporaryFileServer {
	
	private static List<File> fileList = new ArrayList<File>();
	
	/**
	 * Registers a file to the server.
	 * @param file file
	 */
	public static void registerFile(File file) {
		fileList.add(file);
	}
	
	/**
	 * Returns the file in the list. If there is no file for the index null is returned.
	 * @param index index of the file
	 * @return file or null
	 */
	public static File getFileReference(int index) {
		if(index > fileList.size())
			return null;
		
		return fileList.get(index-1);
	}
	
	/**
	 * Returns the file in the list. If there is no file for the index null is returned. 
	 * @param param parameter string like "file1" or "file2",...
	 * @return file or null
	 */
	public static File getFileReference(String param) {
		int pos = param.indexOf("$file");
		
		if(pos == -1)
			return null;
		
		int i = Integer.parseInt(param.substring(pos+5));
		
		return TemporaryFileServer.getFileReference(i);
	}
	
	/**
	 * Inits the server. Clears the list.
	 */
	public static void init() {
		fileList = new ArrayList<File>();
	}

}

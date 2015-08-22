package org.bflow.toolbox.hive.libs.aprogu.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Provides common methods to operate with ZIP packages.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 22.08.2015
 * 
 */
public class ZipUtil {

	/**
	 * Unzips the given input stream to the given output folder.
	 * 
	 * @param inputStream
	 *            InputStream of the ZIP file
	 * @param outputFolder
	 *            Path to the output folder
	 * @throws IOException
	 */
	static public void unZip(InputStream inputStream, String outputFolder) throws IOException {
		byte[] buffer = new byte[1024];
		
		ZipInputStream zis = new ZipInputStream(inputStream);
		
    	// Get the zipped file list entry
    	ZipEntry ze = zis.getNextEntry();
    	
    	while( ze != null ) {
    		String fileName = ze.getName();
    		
            File newFile = new File(outputFolder + File.separator + fileName);
            
            // If the entry is an directory
            // ensure to create all needed directories
            if (ze.isDirectory()) {
            	newFile.mkdirs();
            	ze = zis.getNextEntry();
            	continue;
            }
  
             FileOutputStream fos = new FileOutputStream(newFile);             
  
             int len;
             while ((len = zis.read(buffer)) > 0) {
        		fos.write(buffer, 0, len);
             }
  
             fos.close();   
             ze = zis.getNextEntry();
     	}
  
        zis.closeEntry();
     	zis.close();
	}
	
}

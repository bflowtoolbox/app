package org.bflow.toolbox.hive.annotations.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.bflow.toolbox.hive.libs.aprogu.io.ZipUtil;

/**
 * Copies and unzips resources provided by this plug-in into a specific folder
 * within platform installation.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 22.08.2015
 * 
 */
public class ResourcesInstaller {
	
	/**
	 * Copies and unzips the content of the resource file 'AnnoationIcons.zip'
	 * into a folder defined by the given path.
	 * 
	 * @param path
	 *            Path to the folder where the content is copied to
	 */
	public void install(String path) {
		if (StringUtils.isBlank(path)) throw new NullPointerException("Install path is null or empty!");
		
			// Check if the content has already been copied by this installation
		File succeedIndexFile = new File(path + ".succeed");
		if (succeedIndexFile.exists()) return;
		
		try {
			String srcPath = "/resources/AnnotationIcons.zip";
			
			// Copy ZIP to local temp folder 
			InputStream is = getClass().getResourceAsStream(srcPath);
			File tmpFile = File.createTempFile("annotationsPluginResources", "zip");
			IOUtils.copy(is, new FileOutputStream(tmpFile));
			is.close();
			
			FileInputStream fis = new FileInputStream(tmpFile);
			
			// Unzip the file
			ZipUtil.unZip(fis, path);
			
			fis.close();
		
			// Delete the temp file
			tmpFile.delete();
			
			// Create index file
			succeedIndexFile.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

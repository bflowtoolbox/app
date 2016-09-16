package org.bflow.toolbox.hive.swiprolog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.bflow.toolbox.hive.libs.aprogu.io.ZipUtil;
import org.eclipse.core.resources.ResourcesPlugin;

/**
 * Provides methods to install the embedded SWI-Prolog engine into the running system.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 06.04.13
 * @version 04.01.14
 * 			22.08.15 Moved unZip method to newly created ZipUtil class
 * 			16.09.16 Fixed NPE when Prolog had been already installed
 *
 */
public class SWIPrologInstaller {
	
	private static final SWIPrologInstaller installer = new SWIPrologInstaller();
	
	public static final String OS_Windows = "windows";
	public static final String OS_Linux = "linux";
	public static final String OS_Mac = "mac";
	
	/**
	 * Installs the SWI-Prolog engine into a muted subfolder of the workspace root named ".swiprolog".
	 * 
	 * @return Installation meta info
	 */
	public static InstallMetaInfo installToWorkspace() {
		File file = ResourcesPlugin.getWorkspace().getRoot().getLocation().toFile();
		String path = file.getAbsolutePath();
		String installDir = String.format("%s\\%s", path, ".swiprolog");
		
		if (new File(installDir).exists()) {
			String osName = getOsName();
			String installPath = getRootDir(installDir);
			String executableName = getOSSpecificExecutableName(osName);
			InstallMetaInfo installMetaInfo = new InstallMetaInfo(installPath, executableName);
			return installMetaInfo;
		}
		
		return install(installDir);
	}

	/**
	 * Installs the SWI-Prolog engine for the current OS into the given install directory.
	 * 
	 * @param installDir Directory to install SWI-Prolog to
	 * @return Installation meta info
	 */
	public static InstallMetaInfo install(String installDir) {
		String osName = getOsName();
		
		String outputFolder = installDir;
		String pathFormat = "/bins/%s.zip";
		String srcPath = null;
		String executableName = null;
		
		if (osName == OS_Windows) {
			srcPath = String.format(pathFormat, "win32");
			executableName = getOSSpecificExecutableName(osName);
		}
		
		if (osName == OS_Linux) {
			srcPath = String.format(pathFormat, "linux32");
			executableName = getOSSpecificExecutableName(osName);
		}
		
		if (osName == OS_Mac) {
			srcPath = String.format(pathFormat, "macosx");
			executableName = getOSSpecificExecutableName(osName);
			// Note: The path varies on mac (look at the zip)
			// The socket connection does not work at the moment so this feature
			// will be unavailable
		}
		
		try {
		
			// Copy ZIP to local temp folder 
			InputStream is = installer.getClass().getResourceAsStream(srcPath);
			File tmpFile = File.createTempFile("win32prologzip", "zip");
			IOUtils.copy(is, new FileOutputStream(tmpFile));
			is.close();
			
			FileInputStream fis = new FileInputStream(tmpFile);
			
			// Unzip the file
			ZipUtil.unZip(fis, outputFolder);
			
			fis.close();
		
			// Delete the temp file
			tmpFile.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Create the path to the root directory
		String installPath = getRootDir(outputFolder);
		
		// Create the install meta info and return it
		InstallMetaInfo installMetaInfo = new InstallMetaInfo(installPath, executableName);
		return installMetaInfo;
	}
	
	/**
	 * Returns the root directory of SWI-Prolog based on
	 * the current install directory.
	 * 
	 * @param installDir Install directory path
	 * @return root directory of SWI-Prolog
	 */
	private static String getRootDir(String installDir) {
		return installDir+"\\SWI-Prolog";
	}
	
	/**
	 * Returns the OS specific executable name.
	 * 
	 * @param osName
	 *            Name of the operating system (see class constants)
	 * @return OS specific executable name
	 */
	private static String getOSSpecificExecutableName(String osName) {
		if (osName == OS_Windows) return "swipl.exe";
		if (osName == OS_Linux) return "pl.sh";
		if (osName == OS_Mac) return "unkown";
		
		return null;
	}
	
	/**
	 * Returns the name of the current operating system.
	 * 
	 * @return Name of the operating system
	 */
	private static String getOsName() {
	  String osName = StringUtils.EMPTY;
	  String osPropertyValue = System.getProperty("os.name").toLowerCase();
	  
	  if (osPropertyValue.indexOf("windows") > -1) {
	    osName = OS_Windows;
	  } else if (osPropertyValue.indexOf("linux") > -1) {
	    osName = OS_Linux;
	  } else if (osPropertyValue.indexOf("mac") > -1) {
	    osName = OS_Mac;
	  }
		 
	  return osName;
	}
}

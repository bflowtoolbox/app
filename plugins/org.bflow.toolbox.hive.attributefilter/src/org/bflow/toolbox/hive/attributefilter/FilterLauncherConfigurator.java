package org.bflow.toolbox.hive.attributefilter;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.service.datalocation.Location;

/**
 * Provides some methods to read the filter folder from the .ini file. Sets some
 * default values and constants.
 * 
 * Code based on {@link LauncherConfigurator} by Arian Storch
 * 
 * @author Felix Hoess
 * @since 15.06.2015
 *
 */
public class FilterLauncherConfigurator {
	
	private static FilterLauncherConfigurator instance = null;
	
	private final String INI_NAME = "bflow.ini";
	private final String INI_ARGUMENT = "-FilterFolder";
	private final String DEFAULT_FILENAME = "Filter";

	private final String DEFAULT_FILE_TYPE = ".csv";

	private boolean iniFound = false;
	private String iniContent = "";
	
	private String defaultFilterFilePath;
	private String defaultFilterFolderPath;

	public String getDefaultFilterFolderPath() {
		if (defaultFilterFolderPath == null)
			new FilterLauncherConfigurator();
		return defaultFilterFolderPath;
	}

	public String getDefaultFilterFilePath() {
		if (defaultFilterFilePath == null)
			new FilterLauncherConfigurator();
		return defaultFilterFilePath;
	}
	
	/**
	 * Private constructor.
	 */
	private FilterLauncherConfigurator() {
		try {
		Location loc = Platform.getInstallLocation();
		String pathLocation = loc.getURL().getPath().substring(1);
			String iniLocation = pathLocation + INI_NAME;
		
		iniContent = FileUtils.readFileToString(new File(iniLocation));
		iniFound = true;

		readIniFile();
		
		} catch(Exception eX) {
			iniFound = false;
			//set default locations
			defaultFilterFolderPath = Platform.getInstallLocation()
					.getURL().getPath()
					+ "Filter/";

			defaultFilterFilePath = defaultFilterFolderPath
					+ DEFAULT_FILENAME + DEFAULT_FILE_TYPE;
		}
	}
	
	/**
	 * Returns true when the configurator could found a launcher ini.
	 * @return true if a launcher ini could be found
	 */
	public boolean isIniFound() {
		return iniFound;
	}
	
	/**
	 * Reads the ini file.
	 */
	private void readIniFile() {
		readPathSetting();
	}
	
	/**
	 * Reads the filter path settings from the launcher ini.
	 */
	private void readPathSetting() {
		int index = iniContent.indexOf(INI_ARGUMENT);
		
		if (index == -1) { // default settings

			defaultFilterFolderPath = Platform.getInstallLocation().getURL()
					.getPath()
					+ "Filter/";
			defaultFilterFilePath = defaultFilterFolderPath
					+ DEFAULT_FILENAME + DEFAULT_FILE_TYPE;
			return;
		}
		
		int start = index + 14;

		defaultFilterFolderPath = iniContent.substring(start,
				(iniContent.indexOf("\n", start) - 1));

		defaultFilterFilePath = defaultFilterFolderPath
				+ DEFAULT_FILENAME + DEFAULT_FILE_TYPE;
		
	
	}
	
	
	

	public String getDefaultFilename() {
		return DEFAULT_FILENAME;
	}

	/**
	 * Returns the instance of the FilterLauncherConfigurator.
	 * 
	 * @return instance
	 */
	public static FilterLauncherConfigurator getInstance() {
		if (instance == null)
			instance = new FilterLauncherConfigurator();
		return instance;
	}

	public String getFileType() {

		return DEFAULT_FILE_TYPE;
	}

}

package org.bflow.toolbox.hive.annotations;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.service.datalocation.Location;

/**
 * Provides some methods to set the annotationlogic folder defined in the .ini
 * file
 * 
 * Code based on {@link LauncherConfigurator} by Arian Storch
 * 
 * @author Felix Hoess
 * @since 09.06.2015
 *
 */
public class AnnotationLauncherConfigurator {

	private static final AnnotationLauncherConfigurator instance = new AnnotationLauncherConfigurator();

	private static final String INI_NAME = "bflow.ini";
	private static final String DEFAULT_FILENAME = "AnnotationRules";
	private static final String DEFAULT_FILE_EXTENSION = ".xml";
	private static final String INI_ARGUMENT = "-AnnotationsFolder";

	private boolean iniFound = false;
	private String iniContent = "";

	private static String ANNOTATIONLOGIC_FOLDER_PATH;
	private static String RULES_XML_PATH;

	/**
	 * Private constructor.
	 */
	private AnnotationLauncherConfigurator() {
		try {
			Location loc = Platform.getInstallLocation();
			String pathLocation = loc.getURL().getPath().substring(1);
			String iniLocation = pathLocation + INI_NAME;

			iniContent = FileUtils.readFileToString(new File(iniLocation));
			iniFound = true;

			readIniFile();

		} catch (Exception eX) {
			iniFound = false;
			//set default locations
			setDefault();
		}
	}

	/**
	 * Returns true when the configurator could found a launcher ini.
	 * 
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
	 * Reads the annotations path settings from the launcher ini.
	 */
	private void readPathSetting() {
		int index = iniContent.indexOf(INI_ARGUMENT);

		if (index == -1) { // default settings
			setDefault();
			return;
		}

		int start = index + 19;
		//overwrite the default with the custom path
		ANNOTATIONLOGIC_FOLDER_PATH = iniContent.substring(start,
				(iniContent.indexOf("\n", start) - 1));
		RULES_XML_PATH = ANNOTATIONLOGIC_FOLDER_PATH + DEFAULT_FILENAME
				+ DEFAULT_FILE_EXTENSION;

	}

	public static String getANNOTATIONLOGIC_FOLDER_PATH() {
		if (ANNOTATIONLOGIC_FOLDER_PATH == null)
			new AnnotationLauncherConfigurator();
			return ANNOTATIONLOGIC_FOLDER_PATH;
	}

	public static String getRULES_XML_PATH() {
		if (RULES_XML_PATH == null)
			new AnnotationLauncherConfigurator();
		return RULES_XML_PATH;
	}

	/**
	 * Returns the instance of the LauncherConfigurator.
	 * 
	 * @return instance
	 */
	public static AnnotationLauncherConfigurator getInstance() {
		return instance;
	}

	/**
	 * set default path values
	 */
	private void setDefault() {
		ANNOTATIONLOGIC_FOLDER_PATH = Platform.getInstallLocation()
				.getURL().getPath()
				+ "AnnotationIcons/";
		RULES_XML_PATH = ANNOTATIONLOGIC_FOLDER_PATH + DEFAULT_FILENAME
				+ DEFAULT_FILE_EXTENSION;
	}

	public static String getDefaultFileExtension() {
		return DEFAULT_FILE_EXTENSION;
	}

}

package org.bflow.toolbox.check;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.service.datalocation.Location;

/**
 * Provides some methods to read from and write to the launcher ini.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 21.07.12
 *
 */
public class LauncherConfigurator {
	
	private static final LauncherConfigurator instance = new LauncherConfigurator();
	
	private static final String INI_NAME = "bflow.ini";
	
	private String iniPath = null;
	private boolean iniFound = false;
	private String iniContent = "";
	
	private String nl = "en_US";
	
	/**
	 * Private constructor.
	 */
	private LauncherConfigurator() {
		try {
			Location loc = Platform.getInstallLocation();
			String pathLocation = loc.getURL().getPath().substring(1);
			String iniLocation = pathLocation+INI_NAME;
			iniPath = iniLocation;
			
			iniContent = FileUtils.readFileToString(new File(iniLocation));
			iniFound = true;
	
			readIniFile();
		} catch(Exception eX) {
			iniFound = false;
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
		readNLSetting();
	}
	
	/**
	 * Reads the nl settings from the launcher ini.
	 */
	private void readNLSetting() {
		int indexNL = iniContent.indexOf("-nl");
		
		if (indexNL == -1) { // default settings
			nl = "en_US";
			return;
		}
		
		int start = indexNL + 3;
		
		int indexNLValue = iniContent.indexOf("_", start);
		
		nl = iniContent.substring(indexNLValue-2, indexNLValue+3);
	}
	
	/**
	 * Returns the NL-Settings as set within the launcher ini or the default
	 * "en_US" if no ini could be found.
	 * 
	 * @return NL-Setting from the launcher ini or "en_US".
	 */
	public String getPlatformNL() {
		return nl;
	}
	
	/**
	 * Sets the NL-Settings used by the launcher ini.
	 * 
	 * @param abbr
	 *            new nl settings
	 */
	public void setPlatformNL(String abbr) {
		if (!iniFound) return;
		
		if (abbr.equalsIgnoreCase("de")) {
			abbr = "de_DE";
		}
		
		if (abbr.equalsIgnoreCase("en")) {
			abbr = "en_US";
		}
		
		if (abbr.length() != 5) return;
		
		String newNL = abbr;
		
		if (iniContent.indexOf("-nl") == -1) {
			iniContent = iniContent.concat("\n-nl en_US");
		}
		
		iniContent = iniContent.replace(nl, newNL);
		nl = newNL;
		
		try {
			FileUtils.writeStringToFile(new File(iniPath), iniContent);
		} catch (IOException e) {
			MessageDialog.openError(null, "Error", 
					"Could not save the new settings.\r\nDetails: "+e.getMessage());
		}
		
		MessageDialog.openInformation(null, "Information", 
				"You have to restart the application now to apply your settings!");
	}
	
	/**
	 * Returns the instance of the LauncherConfigurator.
	 * 
	 * @return instance
	 */
	public static LauncherConfigurator getInstance() {
		return instance;
	}

}

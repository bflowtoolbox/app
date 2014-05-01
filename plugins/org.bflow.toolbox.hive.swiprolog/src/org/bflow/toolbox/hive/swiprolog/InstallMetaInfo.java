package org.bflow.toolbox.hive.swiprolog;

/**
 * Defines informations about the installation.
 * 
 * @author Arian Storch
 * @since 06/04/13
 */
public class InstallMetaInfo {
	
	/** The install path. */
	private String installPath;
	
	/** The executable name. */
	private String executableName;
	
	/**
	 * Instantiates a new install meta info.
	 * 
	 * @param installPath
	 *            the install path
	 * @param executableName
	 *            the executable name
	 */
	public InstallMetaInfo(String installPath, String executableName) {
		super();
		this.installPath = installPath;
		this.executableName = executableName;
	}
	
	/**
	 * Returns the executable name.
	 * 
	 * @return the executable name
	 */
	public String getExecutableName() {
		return executableName;
	}
	
	/**
	 * Returns the install path.
	 * 
	 * @return the install path
	 */
	public String getInstallPath() {
		return installPath;
	}

}

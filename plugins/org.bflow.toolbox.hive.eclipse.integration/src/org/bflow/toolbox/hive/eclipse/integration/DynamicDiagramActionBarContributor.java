package org.bflow.toolbox.hive.eclipse.integration;

/**
 * Provides access to the currently active {@link IDynamicActionBarContributor}.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 25.07.2015
 */
public class DynamicDiagramActionBarContributor {
	
	private static IDynamicActionBarContributor fInstance;
	
	/**
	 * Returns the currently used instance.
	 * 
	 * @return Currently used instance
	 */
	public static IDynamicActionBarContributor Instance() {
		return fInstance;
	}
	
	/**
	 * Sets the currently used instance.
	 * 
	 * @param value
	 *            New value
	 */
	public static void Instance(IDynamicActionBarContributor value) {
		fInstance = value;
	}
	
}

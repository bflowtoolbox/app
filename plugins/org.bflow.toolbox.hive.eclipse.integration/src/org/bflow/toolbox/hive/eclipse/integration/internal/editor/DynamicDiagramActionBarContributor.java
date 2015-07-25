package org.bflow.toolbox.hive.eclipse.integration.internal.editor;

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
	
	public static void Instance(IDynamicActionBarContributor instance) {
		fInstance = instance;
	}
	
}

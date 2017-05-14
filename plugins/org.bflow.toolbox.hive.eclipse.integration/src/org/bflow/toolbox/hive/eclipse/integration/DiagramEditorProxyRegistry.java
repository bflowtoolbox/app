package org.bflow.toolbox.hive.eclipse.integration;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.IEditorPart;

/**
 * Provides access to all registered diagram editor proxy contributions.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 24.07.2015
 */
public class DiagramEditorProxyRegistry {
	
	private static ArrayList<IEditorProxyInfo> fRegisteredEditorProxies;
	private static HashMap<String, String> fEditorProxyIdCache = new HashMap<>();
	
	static {		
		onInitialize();
	}
	
	/**
	 * Will be invoked once to register all contributed editor proxy info provider.
	 */
	private static void onInitialize() {
		fRegisteredEditorProxies = new ArrayList<>();
		
		IConfigurationElement[] extensions = Platform.getExtensionRegistry().getConfigurationElementsFor("org.bflow.toolbox.hive.eclipse.integration.editorproxy");
		for (IConfigurationElement extension : extensions) {
			try {
				IEditorProxyInfo proxyInfo = (IEditorProxyInfo) extension.createExecutableExtension("class");
				fRegisteredEditorProxies.add(proxyInfo);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}	

	/**
	 * Returns the id of the editor proxy to use for origin editor.
	 * 
	 * @param originEditorId
	 *            Editor id of the origin editor
	 * @param fileName
	 *            File name of a file that is typically handled by the origin
	 *            editor
	 * @return Id of the editor proxy
	 */
	public static String getEditorProxyId(String originEditorId, String fileName) {
		IEditorPart editorPart = EclipseIntegrator.getOriginDefaultEditorInstance(fileName); // TODO Fix file name
		
		// Look up cache first
		String cachedId = fEditorProxyIdCache.get(originEditorId);
		if (cachedId != null) return cachedId;

		// Ask all contributions
		for (IEditorProxyInfo proxyInfo : fRegisteredEditorProxies) {
			if (!proxyInfo.canProvideProxy(fileName, editorPart)) continue;
			String editorProxyId = proxyInfo.EditorProxyId();
			
			// Put to cache
			fEditorProxyIdCache.put(originEditorId, editorProxyId);
			
			return editorProxyId; 
		}
		
		return null;
	}
}

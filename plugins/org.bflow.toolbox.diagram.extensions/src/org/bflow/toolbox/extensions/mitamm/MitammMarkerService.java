package org.bflow.toolbox.extensions.mitamm;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;


/**
 * Defines a MarkerService so the Toolchain extension can register markers.
 * @author Arian Storch
 * @since 16/04/10
 */
public class MitammMarkerService 
{
	/**
	 * Currently installed MarkerProvider
	 */
	private static IMitammMarkerProvider markerProvider = null;
	
	/**
	 * Installs a provider to the service.<br/>
	 * You can only use one Provider. If you register a new one, the old will be lost.
	 * @param provider {@link IMitammMarkerProvider}
	 */
	public static void registerProvider(IMitammMarkerProvider provider)
	{
		markerProvider = provider;
	}
	
	/**
	 * Adds a specified marker to the diagram.
	 * @param file file
	 * @param elementId element id
	 * @param location location 
	 * @param message message
	 * @param statusSeverity severity
	 * @return Marker
	 */
	public static IMarker addMarker(IFile file, String elementId,
			String location, String message, int statusSeverity)
	{
		if(markerProvider != null)
			return markerProvider.addMarker(file, elementId, location, message, statusSeverity);
		else
			return null;
	}
}

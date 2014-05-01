package org.bflow.toolbox.hive.addons.services;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;


/**
 * Defines an interface for referring classes to handle markers. 
 * @author Arian Storch
 * @since 16/04/10
 *
 */
public interface IMitammMarkerProvider 
{
	/**
	 * Defines the interface for handling markers.
	 * @param iFile file
	 * @param elementId id of the element
	 * @param location location of the element
	 * @param message message
	 * @param statusSeverity severity
	 * @return Marker
	 */
	public IMarker addMarker(IFile iFile, String elementId,
			String location, String message, int statusSeverity);
}

package edu.toronto.cs.openome.core.action;

import java.util.Vector;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;

public class ExtensionReader implements IExtensionReader {	
	protected void readExtensions(String XPOINT, String XELEMENT, Vector<IConfigurationElement> convertors) {
		try {
	        IExtensionPoint extensionPoint = 
	        	Platform.getExtensionRegistry()
	        		.getExtensionPoint(XPOINT);
		    if (extensionPoint != null)
		      for (IExtension element : 
		    	  extensionPoint.getExtensions())
		          for (IConfigurationElement element0 : 
		        	  element.getConfigurationElements())
			    	  if (element0.getName().equals(XELEMENT))
			        	  convertors.add(element0);
		} catch (Exception e) {e.printStackTrace(); }
	}
}
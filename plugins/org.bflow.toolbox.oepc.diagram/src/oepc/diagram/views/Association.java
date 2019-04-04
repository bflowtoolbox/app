package oepc.diagram.views;

import java.io.File;

public class Association {
	public final String elementId;
	public final String associatedURL;
	
	public Association(String elementId, String url) {
		this.elementId = elementId;
		this.associatedURL = url;
	}
	
	public Association(String elementId, File file) {
		this.elementId = elementId;
		this.associatedURL = file.getAbsolutePath().replaceAll("\\\\", "/");
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Association)) return false;
		Association other = (Association) obj;
		
		if (this == obj) return true;
		if (this.elementId.equals(other.elementId) && this.associatedURL.equals(other.associatedURL)) return true;
		
		return false;
	}
}
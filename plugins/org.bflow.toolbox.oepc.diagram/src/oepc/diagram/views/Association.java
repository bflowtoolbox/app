package oepc.diagram.views;

import java.io.File;

public class Association {
	public final Type type;
	public final String elementId;
	public final String associatedURL;

	public Association(String elementId, String url, Type type) {
		this.type = type;
		this.elementId = elementId;
		this.associatedURL = url;
	}
	
	public Association(String elementId, File file, Type type) {
		this.type = type;
		this.elementId = elementId;
		this.associatedURL = file.getAbsolutePath().replaceAll("\\\\", "/");
	}
	
	public static enum Type {
		URL, FILE, SYMLINK
	}
}
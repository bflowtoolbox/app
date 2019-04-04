package oepc.diagram.views;

import java.io.File;

public class Association {
	public final String elementId;
	public final String filePath;
	
	public Association(String elementId, File file) {
		this.elementId = elementId;
		this.filePath = file.getAbsolutePath().replaceAll("\\\\", "/");
	}
	
//	public Association(String elementId, String filePath) {
//		this.elementId = elementId;
//		this.filePath = filePath;
//	}
}
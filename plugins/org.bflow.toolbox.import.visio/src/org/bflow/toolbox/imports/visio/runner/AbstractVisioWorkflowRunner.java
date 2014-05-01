package org.bflow.toolbox.imports.visio.runner;

import java.io.File;

/**
 * @author Christian Boehme
 * 
 */

public abstract class AbstractVisioWorkflowRunner {

	protected String transformTargetPath;
	
	protected String errorMessage = "Error";

	public AbstractVisioWorkflowRunner() {
		
	}

	public abstract boolean runWorkflow(File visioFile);

	public void setTargetPath(String targetPath) {
		transformTargetPath = targetPath + "/";
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}

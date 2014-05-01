package org.bflow.toolbox.imports.visio.oepc.runner;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bflow.toolbox.imports.visio.oepc.Activator;
import org.bflow.toolbox.imports.visio.runner.AbstractVisioWorkflowRunner;
import org.eclipse.emf.mwe.core.WorkflowRunner;
import org.eclipse.emf.mwe.core.monitor.NullProgressMonitor;

/**
 * @author Christian Boehme
 * 
 */

public class VisioWorkflowRunner extends AbstractVisioWorkflowRunner{

	public VisioWorkflowRunner() {
		
	}

	public boolean runWorkflow(File visioFile) {
		// running the oaw Workflow
		String workflowFile = "workflow/visioOepc2bflowOepc_Components.oaw";

		Map<String, String> properties = new HashMap<String, String>();
		Map<String, Object> slotContents = new HashMap<String, Object>();

		properties.put("targetPath", transformTargetPath.replace("\\", "/"));
		properties.put("visioMetaModelFile", "platform:/plugin/" + Activator.getDefault() + "/src/model/metamodel.ecore" );
		properties.put("visioFilePath", visioFile.getAbsolutePath().replace("\\", "/"));
		properties.put("oepcMetamodelFile", "platform:/plugin/org.bflow.toolbox/model/oepc.ecore");
		properties.put("bflowMetamodelFile", "platform:/plugin/org.bflow.toolbox/model/bflow.ecore");
		
	
		boolean success = new WorkflowRunner().run(workflowFile,
				new NullProgressMonitor(), properties, slotContents);
		return success;

	}

}

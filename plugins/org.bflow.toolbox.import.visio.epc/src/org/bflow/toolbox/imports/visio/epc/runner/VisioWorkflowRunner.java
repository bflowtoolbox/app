package org.bflow.toolbox.imports.visio.epc.runner;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bflow.toolbox.imports.visio.epc.Activator;
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
		String workflowFile = "workflow/visioEpc2bflowEpc_Components.oaw";

		Map<String, String> properties = new HashMap<String, String>();
		Map<String, Object> slotContents = new HashMap<String, Object>();

		properties.put("targetPath", transformTargetPath.replace("\\", "/"));
		properties.put("visioMetaModelFile", "platform:/plugin/" + Activator.getDefault() + "/src/model/visio_epc_2007_metamodel.ecore" );
		properties.put("visioFilePath", visioFile.getAbsolutePath().replace("\\", "/"));
		properties.put("epcMetamodelFile", "platform:/plugin/org.bflow.toolbox/model/epc.ecore");
		properties.put("bflowMetamodelFile", "platform:/plugin/org.bflow.toolbox/model/bflow.ecore");
		
	
		boolean success = new WorkflowRunner().run(workflowFile,
				new NullProgressMonitor(), properties, slotContents);
		return success;

	}


}

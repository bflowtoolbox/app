package workflow.components;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.WorkflowRunner;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent;
import org.eclipse.emf.mwe.core.monitor.NullProgressMonitor;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResourceFactory;

public class BflowCheck extends AbstractWorkflowComponent{
		
	public String getComponentName() {
		return "BflowCheck";		
	}
	
	
	/**
	 * Iterates through the 'src-gen' directory and starts the workflow.
	 * @param args
	 */
	public static void main(String[] args) {
		new BflowCheck().scanDirectory("model");
	}
	
	/**
	 * Scanning an file or directory delivered by it's
	 * absolute path. If an directory is found, this method will be called 
	 * recursive with the absolute path of the directory as parameter. 
	 * If it's an file, a specified workflow will be called.
	 * @see {@link BflowCheck#runGenerator(String)}
	 * @param path
	 */
	private void scanDirectory(String path){
		File file = new File(path);
	
		if(file.isDirectory()){
			File children[] = file.listFiles();
			for(int i = 0; i <= children.length - 1; i++){
				File currentChild = children[i];
				if(currentChild.isFile() && currentChild.isHidden() == false){
					if(isSupportedFile(currentChild.getName())){
						runGenerator(currentChild);
					}
				}
				else if(currentChild.isDirectory() 
						&& currentChild.isHidden() == false){
					// avoid problems with hidden files
					// also they should not considered
					scanDirectory(currentChild.getAbsolutePath());
				}
			}
		}
		else if(file.isFile()){
			if(isSupportedFile(file.getName())){
				runGenerator(file);
			}
		}
	}
	
	
	/**
	 * Starts the workflow for the delivered Bflow file.
	 * @param file
	 */
	@SuppressWarnings("deprecation")
	private void runGenerator(File file){
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("*", new GMFResourceFactory());

		String path = null;
		try {
			path = file.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		int index = path.lastIndexOf("\\");
		String currentPath = path.substring(0, index);
		String fileName = path.substring(index + 1);
		fileName = fileName.replaceFirst(".epc", ".validation_result");
		
		HashMap<String, String> properties = new HashMap<String, String>();
		properties.put("bflowModelFile", path);
		properties.put("currentPath", currentPath);
		properties.put("fileName", fileName);
		
		new WorkflowRunner().run("workflow/bflowCheck.oaw", 
				new NullProgressMonitor(), properties, null);
	
	}
	
	
	/**
	 * Checks if the <code>name</code> ends with '.epc'.
	 * @param name
	 * @return
	 */
	private boolean isSupportedFile(String name){
		return name.endsWith(".epc");
	}


	@Override
	protected void invokeInternal(WorkflowContext ctx, ProgressMonitor monitor,
			Issues issues) {
		new BflowCheck().scanDirectory("model");		
	}


	@Override
	public void checkConfiguration(Issues issues) {		
	}
}

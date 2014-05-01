package workflow.components;

import java.io.IOException;
import java.io.RandomAccessFile;

import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.issues.MWEDiagnostic;
import org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;

public class IssueWriter extends AbstractWorkflowComponent {


	public String getComponentName() {
		return "IssueWriter";		
	}

	private String currentPath;
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCurrentPath() {
		return currentPath;
	}

	public void setCurrentPath(String currentPath) {
		this.currentPath = currentPath;
	}

	@Override
	protected void invokeInternal(WorkflowContext ctx, ProgressMonitor monitor,
			Issues issues) {
		RandomAccessFile file;
		try {
			file = new RandomAccessFile("issues.txt","rw");
					//getCurrentPath() + "/" + getFileName(), "rw");
			String date = new java.util.Date().toString();
			String model = (getCurrentPath() + "\\" + getFileName()).replaceFirst(".validation_result",".epc");
			
			file.seek(file.length());
						
			MWEDiagnostic warnings[] = issues.getWarnings();
			for(int i = 0; i <= warnings.length - 1; i++){
				file.writeBytes(date + " ; " + "w: " + warnings[i].getMessage().replaceAll(";","") + " ; " + model + "\n");
			}
			
			MWEDiagnostic errors[] = issues.getErrors();
			for(int i = 0; i <= errors.length - 1; i++){
				file.writeBytes(date + " ; " + "e: " + errors[i].getMessage().replaceAll(";","") + " ; " + model + "\n");
			}
			
			if(file != null)
				file.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void checkConfiguration(Issues issues) {		
	}

}

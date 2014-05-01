package org.bflow.toolbox.epc.diagram.edit.commands;

import java.util.List;

import org.bflow.toolbox.epc.impl.FunctionImpl;
import org.bflow.toolbox.epc.impl.ProcessInterfaceImpl;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.core.resources.GMFResource;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;


/**
 * @generated NOT
 */
public class OpenEditorCommand implements ICommand {
	public OpenEditorCommand(EObject element, Shell shell) {
		// TODO Auto-generated constructor stub
		
		String pathName = null;
		
		if (element instanceof FunctionImpl) {
			//modified by Christian
			//Doppelklick und öffnen des ersten referenzierten Subdiagrams
			if (((FunctionImpl)element).getSubdiagram().size() > 1){
				MessageBox messageBox = new MessageBox(shell);
				messageBox.setText("Warning: More than one Subdiagram!");
				messageBox.setMessage("There are more than one subdiagrams. The first one will be opened!");
				messageBox.open();
			}
			pathName = ((FunctionImpl)element).getSubdiagram().get(0);
		} else if (element instanceof ProcessInterfaceImpl) {
			pathName = ((ProcessInterfaceImpl)element).getSubdiagram();
		}  
		
		if (pathName != null) {
			
			URI tempURI = URI.createPlatformResourceURI(pathName, true);
			//File file = new File(tempURI.toFileString());
		
			//if (file.exists()) {
				Resource res = new GMFResource(tempURI);
				try {
					org.bflow.toolbox.epc.diagram.part.EpcDiagramEditorUtil.openDiagram(res);
				} catch (PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			/*} else {
				showMessage("Error opening Subdiagram: File not found!: "+tempURI.toFileString(), shell);
			}*/
		}
	}
	
	private void showMessage(String message, Shell shell) {
		MessageBox messageBox = new MessageBox(shell);
		messageBox.setMessage(message);
		messageBox.setText("Error: Open File!");
		messageBox.open();
		//MessageBox.openInformation(viewer.getControl().getShell(),"EpcNavigatorView", message);
	}

	
	public ICommand compose(IUndoableOperation operation) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List getAffectedFiles() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public CommandResult getCommandResult() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ICommand reduce() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void setLabel(String label) {
		// TODO Auto-generated method stub
		
	}

	
	public void addContext(IUndoContext arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public boolean canExecute() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean canRedo() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean canUndo() {
		// TODO Auto-generated method stub
		return false;
	}

	
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	
	public IStatus execute(IProgressMonitor arg0, IAdaptable arg1)
			throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public IUndoContext[] getContexts() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean hasContext(IUndoContext arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public IStatus redo(IProgressMonitor arg0, IAdaptable arg1)
			throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void removeContext(IUndoContext arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public IStatus undo(IProgressMonitor arg0, IAdaptable arg1)
			throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}
}

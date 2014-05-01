package org.bflow.toolbox.vc.diagram.edit.commands;

import java.util.List;

import org.bflow.toolbox.vc.impl.ValueChain2Impl;
import org.bflow.toolbox.vc.impl.ValueChainImpl;
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
		
		String pathName = null;
		
		if (element instanceof ValueChainImpl) {
			pathName = ((ValueChainImpl)element).getSubdiagram();
		} else pathName = ((ValueChain2Impl)element).getSubdiagram();
		
		if (pathName != null) {

			URI tempURI = URI.createPlatformResourceURI(pathName, true);
			//File file = new File(tempURI.toFileString());
		
			//if (file.exists()) {
				Resource res = new GMFResource(tempURI);
				if (pathName.endsWith(".epc")) {
					try {
						org.bflow.toolbox.epc.diagram.part.EpcDiagramEditorUtil.openDiagram(res);
					} catch (PartInitException e) {
						e.printStackTrace();
					}
				}
				else {
					try {
						org.bflow.toolbox.vc.diagram.part.VcDiagramEditorUtil.openDiagram(res);
					} catch (PartInitException e) {
						e.printStackTrace();
					}
				}
			/*} else {
				showMessage("Error opening Subdiagram: File not found!: "+tempURI.toFileString(), shell);
			}*/
		}
	}
	
	@SuppressWarnings("unused")
	@Deprecated
	private void showMessage(String message, Shell shell) {
		MessageBox messageBox = new MessageBox(shell);
		messageBox.setMessage(message);
		messageBox.setText("Error: Open File!");
		messageBox.open();
		//MessageBox.openInformation(viewer.getControl().getShell(),"EpcNavigatorView", message);
	}

	
	public ICommand compose(IUndoableOperation operation) {
		return null;
	}

	
	public List<?> getAffectedFiles() {
		return null;
	}

	
	public CommandResult getCommandResult() {
		return null;
	}

	
	public ICommand reduce() {
		return null;
	}

	
	public void setLabel(String label) {
		
	}

	
	public void addContext(IUndoContext arg0) {
		
	}

	
	public boolean canExecute() {
		return false;
	}

	
	public boolean canRedo() {
		
		return false;
	}

	
	public boolean canUndo() {

		return false;
	}

	
	public void dispose() {

		
	}

	
	public IStatus execute(IProgressMonitor arg0, IAdaptable arg1)
			throws ExecutionException {

		return null;
	}

	
	public IUndoContext[] getContexts() {

		return null;
	}

	
	public String getLabel() {

		return null;
	}

	
	public boolean hasContext(IUndoContext arg0) {

		return false;
	}

	
	public IStatus redo(IProgressMonitor arg0, IAdaptable arg1)
			throws ExecutionException {

		return null;
	}

	
	public void removeContext(IUndoContext arg0) {

		
	}

	
	public IStatus undo(IProgressMonitor arg0, IAdaptable arg1)
			throws ExecutionException {
		return null;
	}
}

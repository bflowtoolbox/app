	package org.bflow.toolbox.vc.diagram.part;

import java.io.File;

import org.bflow.toolbox.epc.Function;
import org.bflow.toolbox.epc.ProcessInterface;
import org.bflow.toolbox.epc.diagram.edit.parts.FunctionEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ProcessInterfaceEditPart;
import org.bflow.toolbox.epc.diagram.part.EpcElementChooserDialog;
import org.bflow.toolbox.vc.ValueChain;
import org.bflow.toolbox.vc.ValueChain2;
import org.bflow.toolbox.vc.diagram.edit.parts.ValueChain2EditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.ValueChainEditPart;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;

/**
 * @generated NOT
 */
public class VCInsertSubdiagramAction implements IObjectActionDelegate {

	private ShapeNodeEditPart mySelectedElement;
	
	private ValueChain vc;
	private ValueChain2 vc2;
	
	private Shell myShell;
	
	private IWorkbench workbench;
	
	private boolean valuechainType;

	
	public void setActivePart(IAction arg0, IWorkbenchPart arg1) {
		// TODO Auto-generated method stub
		
		myShell = arg1.getSite().getShell();
		workbench = arg1.getSite().getWorkbenchWindow().getWorkbench();
	}

	
	public void run(IAction arg0) {
		
		/*FileDialog fileDialog = new FileDialog(myShell, SWT.OPEN);
		fileDialog.open();

		if ((fileDialog.getFilterPath() + File.separator + fileDialog.getFileName()).startsWith(org.bflow.toolbox.epc.diagram.views.Workspace.workspaceDirectory)) {
			
			if (fileDialog.getFileName() != null && fileDialog.getFileName().length() > 0) {
				openEditor(workbench, URI.createFileURI(fileDialog.getFilterPath()
						   + File.separator + fileDialog.getFileName()));
			
				if (valuechainType) vc.setSubdiagram((fileDialog.getFilterPath() + File.separator + fileDialog.getFileName()).substring(org.bflow.toolbox.epc.diagram.views.Workspace.workspaceDirectory.length()));
				else vc2.setSubdiagram((fileDialog.getFilterPath() + File.separator + fileDialog.getFileName()).substring(org.bflow.toolbox.epc.diagram.views.Workspace.workspaceDirectory.length()));
			}
		} else {
			MessageBox messageBox = new MessageBox(this.myShell);
			messageBox.setMessage("Error: File not in Workspace!");
			messageBox.setText("Error: File not in Workspace!");
			messageBox.open();
		}*/
		
		if (mySelectedElement instanceof ValueChainEditPart) {
			vc = (ValueChain)mySelectedElement.getPrimaryView().getElement();
			valuechainType = true;
		} else if (mySelectedElement instanceof ValueChain2EditPart) {	
			vc2 = (ValueChain2)mySelectedElement.getPrimaryView().getElement();
		}	
		
		final View view = (View) mySelectedElement.getModel();
		VcElementChooserDialog elementChooser = new VcElementChooserDialog(
				myShell, view);
		int result = elementChooser.open();
		if (result != Window.OK) {
			return;
		}
		URI selectedModelElementURI = elementChooser
				.getSelectedModelElementURI();
		
		if (valuechainType)
			vc.setSubdiagram(selectedModelElementURI.toPlatformString(true));
		else 
			vc2.setSubdiagram(selectedModelElementURI.toPlatformString(true));	
		
	}

	private static boolean openEditor(IWorkbench workbench, URI fileURI) {
		IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
		IWorkbenchPage page = workbenchWindow.getActivePage();
		IEditorDescriptor editorDescriptor = workbench.getEditorRegistry()
				.getDefaultEditor(fileURI.toFileString());
		if (editorDescriptor == null) {
			MessageDialog
					.openError(
							workbenchWindow.getShell(),
							Messages.VcCreationWizardOpenEditorError,
							NLS
									.bind(
											Messages.VcCreationWizardOpenEditorError,
											fileURI.toFileString()));
			return false;
		} else {
			try {
				page.openEditor(new URIEditorInput(fileURI), editorDescriptor
						.getId());
			} catch (PartInitException exception) {
				MessageDialog
						.openError(
								workbenchWindow.getShell(),
								Messages.VcCreationWizardOpenEditorError,
								exception.getMessage());
				return false;
			}
		}
		return true;
	}


	
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		mySelectedElement = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1)
				if (structuredSelection.getFirstElement() instanceof ValueChainEditPart) {
					mySelectedElement = (ValueChainEditPart) structuredSelection.getFirstElement();
					vc = (ValueChain)mySelectedElement.getPrimaryView().getElement();
					valuechainType = true;
				} 
				else if (structuredSelection.getFirstElement() instanceof ValueChain2EditPart) {
				mySelectedElement = (ValueChain2EditPart) structuredSelection.getFirstElement();
				vc2 = (ValueChain2)mySelectedElement.getPrimaryView().getElement();
			} 
		}

		action.setEnabled(isEnabled());
	}
	
	private boolean isEnabled() {	
		return mySelectedElement != null;
	}
}

package org.bflow.toolbox.epc.diagram.actions;

import org.bflow.toolbox.epc.Function;
import org.bflow.toolbox.epc.ProcessInterface;
import org.bflow.toolbox.epc.diagram.edit.parts.FunctionEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ProcessInterfaceEditPart;
import org.bflow.toolbox.epc.diagram.part.EpcElementChooserDialog;
import org.bflow.toolbox.epc.diagram.part.Messages;
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
 * @version 05/07/11 modified by Arian Storch
 */
public class EpcInsertSubdiagramAction implements IObjectActionDelegate {

	private ShapeNodeEditPart mySelectedElement;
	
	private Function func;
	private ProcessInterface proc;

	private Shell myShell;
	
	//private IWorkbench workbench;
	
	private boolean functionType;
	
	
	public void setActivePart(IAction arg0, IWorkbenchPart arg1) {		
		myShell = arg1.getSite().getShell();
		//workbench = arg1.getSite().getWorkbenchWindow().getWorkbench();
	}

	
	public void run(IAction arg0) {
		
		if (mySelectedElement instanceof FunctionEditPart) {
			func = (Function)mySelectedElement.getPrimaryView().getElement();
			functionType = true;
		} else if (mySelectedElement instanceof ProcessInterfaceEditPart) {	
			proc = (ProcessInterface)mySelectedElement.getPrimaryView().getElement();
		}	
		
		final View view = (View) mySelectedElement.getModel();
		EpcElementChooserDialog elementChooser = new EpcElementChooserDialog(
				myShell, view);
		int result = elementChooser.open();
		if (result != Window.OK) {
			return;
		}
		URI selectedModelElementURI = elementChooser
				.getSelectedModelElementURI();
		
		if (functionType)
			//modified by Christian
			
			func.getSubdiagram().add(selectedModelElementURI.toPlatformString(true));
		else 
			proc.setSubdiagram(selectedModelElementURI.toPlatformString(true));	
		
	}

	
	@SuppressWarnings("unused")
	private static boolean openEditor(IWorkbench workbench, URI fileURI) {
		IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
		IWorkbenchPage page = workbenchWindow.getActivePage();
		IEditorDescriptor editorDescriptor = workbench.getEditorRegistry()
				.getDefaultEditor(fileURI.toFileString());
		if (editorDescriptor == null) {
			MessageDialog
					.openError(
							workbenchWindow.getShell(),
							Messages.EpcCreationWizardOpenEditorError,
							NLS
									.bind(
											Messages.EpcCreationWizardOpenEditorError,
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
								Messages.EpcCreationWizardOpenEditorError,
								exception.getMessage());
				return false;
			}
		}
		return true;
	}
	
	public void selectionChanged(IAction action, ISelection selection) {
		mySelectedElement = null;
		functionType = false;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1)
				if (structuredSelection.getFirstElement() instanceof FunctionEditPart) {
					mySelectedElement = (FunctionEditPart) structuredSelection.getFirstElement();
					func = (Function)mySelectedElement.getPrimaryView().getElement();
					functionType = true;
				} else if (structuredSelection.getFirstElement() instanceof ProcessInterfaceEditPart) {
					mySelectedElement = (ProcessInterfaceEditPart) structuredSelection.getFirstElement();		
					proc = (ProcessInterface)mySelectedElement.getPrimaryView().getElement();
				}	
		}

		action.setEnabled(isEnabled());
	}
	
	private boolean isEnabled() {	
		return mySelectedElement != null;
	}
}

package org.bflow.toolbox.epc.diagram.part;

import java.util.ArrayList;

import org.bflow.toolbox.epc.Function;
import org.bflow.toolbox.epc.ProcessInterface;
import org.bflow.toolbox.epc.diagram.edit.parts.ArcEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.EpcEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.FunctionEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ProcessInterfaceEditPart;
import org.bflow.toolbox.extensions.edit.parts.ColoredNodeEditPart;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.emf.clipboard.core.ClipboardUtil;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;


/**
 * @generated NOT
 * @author 
 * @version 09/08/10 modified by Arian Storch
 */
public class EpcCreateSubdiagramAction implements IObjectActionDelegate {

	private ShapeNodeEditPart mySelectedElement;
	
	private Function func;
	private ProcessInterface proc;

	private Shell myShell;
	
	private IWorkbench workbench;
	
	private boolean functionType;
	
	
	public void setActivePart(IAction arg0, IWorkbenchPart arg1) {
		myShell = arg1.getSite().getShell();
		workbench = arg1.getSite().getWorkbenchWindow().getWorkbench();
	}

	
	public void run(IAction arg0) {
		if (! func.getSubdiagram().isEmpty()) {
			MessageBox messageBox = new MessageBox(this.myShell);
			messageBox.setText("Warning: List of Subdiagrams not null!");
			messageBox.setMessage("Warning: There are already existing subdiagrams. Creating a new one will overwrite these references.");
			messageBox.open();
		}
		EpcCreationWizard wizard = new EpcCreationWizard();
		wizard.init(workbench, StructuredSelection.EMPTY);
		WizardDialog wizardDialog = new WizardDialog(myShell, wizard);
		wizardDialog.open();
		
		final String pathName = wizard.getDiagram().getURI().toPlatformString(true);
		
		//if (pathName.startsWith(Workspace.workspaceDirectory)) {
		
		try
		{
			
			if (functionType) 
			{				
				mySelectedElement.getViewer().getEditDomain().getCommandStack().execute(
				new Command(){
					
					@Override
					public void execute() {
						super.execute();
						func.getSubdiagram().add(pathName);
					}
					
				}		
				
				);
				
				// are there events before and after?
				ColoredNodeEditPart prev = getEventBefore();
				ColoredNodeEditPart next = getEventAfter();
				ArrayList<EObject> list = new ArrayList<EObject>();
				
				if(prev != null)
					list.add(prev.resolveSemanticElement());				
				
				if(next != null) 
					list.add(next.resolveSemanticElement());
				
				/*
				 * do copy
				 */
				if(list.size() > 0) {
					final String s = ClipboardUtil.copyElementsToString(list, null,
							null);
					
					EpcDiagramEditor newEditor = (EpcDiagramEditor) workbench
												.getActiveWorkbenchWindow().getActivePage()
																.getActiveEditor();

					final EpcEditPart newEditPart = (EpcEditPart) newEditor
															.getDiagramEditPart();
					
					newEditPart.getEditingDomain().getCommandStack().execute(
							new RecordingCommand(newEditPart.getEditingDomain()) {

								@Override
								protected void doExecute() {
									try {
										ClipboardUtil.pasteElementsFromString(s,
												newEditPart.resolveSemanticElement(),
												null, null);
									} catch (Exception ex) {
										ex.printStackTrace();
									}

								}
							});
				}
				
				mySelectedElement.refresh();
			}
			else proc.setSubdiagram(wizard.getDiagram().getURI().toPlatformString(true));
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
				
		/*} else {	
			MessageBox messageBox = new MessageBox(this.myShell);
			messageBox.setMessage("Error: File not in Workspace!");
			messageBox.setText("Error: File not in Workspace!");
			messageBox.open();
		}	*/
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
	
	private ColoredNodeEditPart getEventBefore() {
		FunctionEditPart editPart = (FunctionEditPart)mySelectedElement;
		
		if(editPart.getTargetConnections().size() == 0)
			return null;
		
		ColoredNodeEditPart prev = (ColoredNodeEditPart) ((ArcEditPart)editPart.getTargetConnections().get(0)).getSource();
		
		return prev;
	}
	
	private ColoredNodeEditPart getEventAfter() {
		FunctionEditPart editPart = (FunctionEditPart)mySelectedElement;
		
		if(editPart.getSourceConnections().size() == 0)
			return null;
		
		ColoredNodeEditPart next = (ColoredNodeEditPart) ((ArcEditPart)editPart.getSourceConnections().get(0)).getTarget();
		
		return next;
	}
}

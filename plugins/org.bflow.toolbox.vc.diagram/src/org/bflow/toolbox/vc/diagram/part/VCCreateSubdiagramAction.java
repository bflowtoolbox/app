package org.bflow.toolbox.vc.diagram.part;

import org.bflow.toolbox.epc.diagram.views.Workspace;
import org.bflow.toolbox.vc.ValueChain;
import org.bflow.toolbox.vc.ValueChain2;
import org.bflow.toolbox.vc.diagram.edit.parts.ValueChain2EditPart;
import org.bflow.toolbox.vc.diagram.edit.parts.ValueChainEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
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
 */
public class VCCreateSubdiagramAction implements IObjectActionDelegate {

	private ShapeNodeEditPart mySelectedElement;
	
	private ValueChain vc;
	private ValueChain2 vc2;
	
	private boolean valuechainType;
	
	private Shell myShell;
	
	private IWorkbench workbench;	
	
	public void setActivePart(IAction arg0, IWorkbenchPart arg1) {
		// TODO Auto-generated method stub
		myShell = arg1.getSite().getShell();
		workbench = arg1.getSite().getWorkbenchWindow().getWorkbench();
	}

	
	public void run(IAction arg0) {
		// TODO Auto-generated method stub
		VcCreationWizard wizard = new VcCreationWizard();
		wizard.init(workbench, StructuredSelection.EMPTY);
		WizardDialog wizardDialog = new WizardDialog(myShell, wizard);
		wizardDialog.open();
		
		//if (pathName.startsWith(org.bflow.toolbox.epc.diagram.views.Workspace.workspaceDirectory)) {
			
			if (valuechainType) 
				vc.setSubdiagram(wizard.getDiagram().getURI().toPlatformString(true));
			else 
				vc2.setSubdiagram(wizard.getDiagram().getURI().toPlatformString(true));
		/*} else {	
			MessageBox messageBox = new MessageBox(this.myShell);
			messageBox.setMessage("Error: File not in Workspace!");
			messageBox.setText("Error: File not in Workspace!");
			messageBox.open();
		}	*/
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
				} else if (structuredSelection.getFirstElement() instanceof ValueChain2EditPart) {
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

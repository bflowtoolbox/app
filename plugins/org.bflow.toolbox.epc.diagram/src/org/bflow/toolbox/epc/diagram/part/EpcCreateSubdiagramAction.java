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
 * @author ?, Arian Storch<arian.storch@bflow.org>
 * @version 2010-09-08 AST ?
 * 			2019-01-23 AST Fixed NPE when the dialog has been cancelled
 */
public class EpcCreateSubdiagramAction implements IObjectActionDelegate {
	private ShapeNodeEditPart _selectedElement;	
	private Function _func;
	private ProcessInterface _proc;
	private Shell _shell;	
	private IWorkbench _workbench;	
	private boolean _functionType;
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void setActivePart(IAction arg0, IWorkbenchPart arg1) {
		_shell = arg1.getSite().getShell();
		_workbench = arg1.getSite().getWorkbenchWindow().getWorkbench();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction arg0) {
		if (! _func.getSubdiagram().isEmpty()) {
			MessageBox messageBox = new MessageBox(this._shell);
			messageBox.setText("Warning: List of Subdiagrams not null!");
			messageBox.setMessage("Warning: There are already existing subdiagrams. Creating a new one will overwrite these references.");
			messageBox.open();
		}
		
		EpcCreationWizard wizard = new EpcCreationWizard();
		wizard.init(_workbench, StructuredSelection.EMPTY);
		WizardDialog wizardDialog = new WizardDialog(_shell, wizard);
		if (wizardDialog.open() == WizardDialog.CANCEL) return;
		
		final String pathName = wizard.getDiagram().getURI().toPlatformString(true);		
		
		try {			
			if (_functionType) {				
				_selectedElement.getViewer().getEditDomain().getCommandStack().execute(
						new Command() {
							@Override
							public void execute() {
								super.execute();
								_func.getSubdiagram().add(pathName);
							}
						}
				);
				
				// are there events before and after?
				ColoredNodeEditPart prev = getEventBefore();
				ColoredNodeEditPart next = getEventAfter();
				ArrayList<EObject> list = new ArrayList<EObject>();
				
				if (prev != null)
					list.add(prev.resolveSemanticElement());				
				
				if (next != null) 
					list.add(next.resolveSemanticElement());
				
				// Do copy
				if (list.size() > 0) {
					final String s = ClipboardUtil.copyElementsToString(list, null, null);
					
					EpcDiagramEditor newEditor = (EpcDiagramEditor) _workbench
												.getActiveWorkbenchWindow()
												.getActivePage()
												.getActiveEditor();

					final EpcEditPart newEditPart = (EpcEditPart) newEditor.getDiagramEditPart();
					
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
				
				_selectedElement.refresh();
			} else {
				_proc.setSubdiagram(wizard.getDiagram().getURI().toPlatformString(true));
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {	
		_selectedElement = null;
		_functionType = false;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1)
				if (structuredSelection.getFirstElement() instanceof FunctionEditPart) {
					_selectedElement = (FunctionEditPart) structuredSelection.getFirstElement();
					_func = (Function)_selectedElement.getPrimaryView().getElement();
					_functionType = true;
				} else if (structuredSelection.getFirstElement() instanceof ProcessInterfaceEditPart) {
					_selectedElement = (ProcessInterfaceEditPart) structuredSelection.getFirstElement();		
					_proc = (ProcessInterface)_selectedElement.getPrimaryView().getElement();
				}	
		}

		action.setEnabled(isEnabled());
	}
	
	private boolean isEnabled() {
		return _selectedElement != null;
	}
	
	private ColoredNodeEditPart getEventBefore() {
		FunctionEditPart editPart = (FunctionEditPart)_selectedElement;
		
		if (editPart.getTargetConnections().size() == 0)
			return null;
		
		ColoredNodeEditPart prev = (ColoredNodeEditPart) ((ArcEditPart)editPart.getTargetConnections().get(0)).getSource();
		
		return prev;
	}
	
	private ColoredNodeEditPart getEventAfter() {
		FunctionEditPart editPart = (FunctionEditPart)_selectedElement;
		
		if (editPart.getSourceConnections().size() == 0)
			return null;
		
		ColoredNodeEditPart next = (ColoredNodeEditPart) ((ArcEditPart)editPart.getSourceConnections().get(0)).getTarget();
		
		return next;
	}
}

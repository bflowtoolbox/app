package org.bflow.toolbox.epc.diagram.actions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bflow.toolbox.epc.Function;
import org.bflow.toolbox.epc.ProcessInterface;
import org.bflow.toolbox.epc.diagram.edit.parts.FunctionEditPart;
import org.bflow.toolbox.epc.diagram.edit.parts.ProcessInterfaceEditPart;
import org.bflow.toolbox.epc.diagram.part.EpcElementChooserDialog;
import org.bflow.toolbox.epc.diagram.part.Messages;
import org.bflow.toolbox.extensions.BflowDiagramElementEditUtil;
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
 * @author ?, Arian Storch<arian.storch@bflow.org>
 * @generated NOT
 * @version 2011-07-05 modified by Arian Storch
 * @version 2019-01-26 AST Fixed exception when modifying element without
 *          transaction
 */
public class EpcInsertSubdiagramAction implements IObjectActionDelegate {
	private Log _log = LogFactory.getLog(EpcInsertSubdiagramAction.class);
	private ShapeNodeEditPart _selectedElement;
	
	private Function _func;
	private ProcessInterface _proc;
	private Shell _shell;	
	private boolean _functionType;
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void setActivePart(IAction arg0, IWorkbenchPart arg1) {		
		_shell = arg1.getSite().getShell();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction arg0) {		
		if (_selectedElement instanceof FunctionEditPart) {
			_func = (Function)_selectedElement.getPrimaryView().getElement();
			_functionType = true;
		} else if (_selectedElement instanceof ProcessInterfaceEditPart) {	
			_proc = (ProcessInterface)_selectedElement.getPrimaryView().getElement();
		}	
		
		final View view = (View) _selectedElement.getModel();
		EpcElementChooserDialog elementChooser = new EpcElementChooserDialog(_shell, view);
		int result = elementChooser.open();
		if (result != Window.OK) return;
		
		URI selectedModelElementURI = elementChooser.getSelectedModelElementURI();
		String path = selectedModelElementURI.toPlatformString(true);
		
		try {
			if (_functionType) {
				BflowDiagramElementEditUtil.modifyWithTransaction(_func, path, (e, v) -> e.getSubdiagram().add(v));
			} else {
				BflowDiagramElementEditUtil.modifyWithTransaction(_proc, path, (e, v) -> e.setSubdiagram(v)); 
			}
		} catch (Exception ex) {
			_log.error("Error on modifying element", ex);
		}		
	}

	
	@SuppressWarnings("unused")
	private static boolean openEditor(IWorkbench workbench, URI fileURI) {
		IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
		IWorkbenchPage page = workbenchWindow.getActivePage();
		IEditorDescriptor editorDescriptor = workbench.getEditorRegistry().getDefaultEditor(fileURI.toFileString());
		if (editorDescriptor == null) {
			MessageDialog.openError(
							workbenchWindow.getShell(),
							Messages.EpcCreationWizardOpenEditorError,
							NLS.bind(Messages.EpcCreationWizardOpenEditorError,	fileURI.toFileString())
							);
			return false;
		} else {
			try {
				page.openEditor(new URIEditorInput(fileURI), editorDescriptor.getId());
			} catch (PartInitException exception) {
				MessageDialog.openError(
								workbenchWindow.getShell(),
								Messages.EpcCreationWizardOpenEditorError,
								exception.getMessage()
								);
				return false;
			}
		}
		return true;
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
}

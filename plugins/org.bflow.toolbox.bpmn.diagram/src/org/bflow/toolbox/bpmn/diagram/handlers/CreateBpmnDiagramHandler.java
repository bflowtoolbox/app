package org.bflow.toolbox.bpmn.diagram.handlers;

import org.bflow.toolbox.bpmn.diagram.BpmnDiagramPlugin;
import org.bflow.toolbox.bpmn.diagram.wizards.BpmnCreationWizard;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Implements {@link IHandler} to create a new BPMN diagram when button 
 * in the toolbar is pressed.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2018-10-04
 * @version 2019-01-27 AST Changed to new {@link BpmnCreationWizard}
 *
 */
public class CreateBpmnDiagramHandler extends AbstractHandler {
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			// Collection required environment objects
			Shell shell = HandlerUtil.getActiveShell(event);
			IWorkbench workbench = PlatformUI.getWorkbench();
			ISelection selection = HandlerUtil.getCurrentSelection(event);			
			StructuredSelection structSelection = (StructuredSelection) selection;
			
			// Set up and run model wizard
			BpmnCreationWizard modelWizard = new BpmnCreationWizard();
			modelWizard.init(workbench, structSelection);
			
			WizardDialog wizardDialog = new WizardDialog(shell, modelWizard);
			wizardDialog.open();
		} catch(Exception ex) {
			BpmnDiagramPlugin.getInstance().logError("Error on running BPMN diagram wizard", ex);
		}
		
		return null;
	}
}

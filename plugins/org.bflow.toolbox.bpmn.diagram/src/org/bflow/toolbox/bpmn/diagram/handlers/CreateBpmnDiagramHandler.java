package org.bflow.toolbox.bpmn.diagram.handlers;

import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.bflow.toolbox.bpmn.diagram.BpmnDiagramPlugin;
import org.eclipse.bpmn2.modeler.ui.wizards.BPMN2DiagramWizard;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * Implements {@link IHandler} to create a new BPMN diagram when button 
 * in the toolbar is pressed.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2018-10-04
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
			BPMN2DiagramWizard modelWizard = new BPMN2DiagramWizardPatcher();
			modelWizard.init(workbench, structSelection);
			
			WizardDialog wizardDialog = new WizardDialog(shell, modelWizard);
			wizardDialog.open();
		} catch(Exception ex) {
			BpmnDiagramPlugin.getInstance().logError("Error on running BPMN diagram wizard", ex);
		}
		
		return null;
	}
	
	/**
	 * Extends {@link BPMN2DiagramWizard} to patch a size error when the 
	 * dialog is being presented via {@link CreateBpmnDiagramHandler}.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 2018-10-04
	 *
	 */
	class BPMN2DiagramWizardPatcher extends BPMN2DiagramWizard {
		/*
		 * (non-Javadoc)
		 * @see org.eclipse.bpmn2.modeler.ui.wizards.BPMN2DiagramWizard#createPageControls(org.eclipse.swt.widgets.Composite)
		 */
		@Override
		public void createPageControls(Composite pageContainer) {
			Point sz = pageContainer.getSize();
			
			/* 2018-10-04 AST:
			 * The BPMN2DiagramWizardPage1 calculates the width of its labels via the 
			 * pageContainer size. When the page is not being displayed by the Eclipse 
			 * New Wizard, the size is initially 0. So we set up a default size here 
			 * to avoid the error.
			 */			
			if (sz.x == 0) {
				pageContainer.setSize(600, 300);
			}
			
			super.createPageControls(pageContainer);
		}
	}

}

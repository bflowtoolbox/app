package org.bflow.toolbox.epc.diagram.actions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bflow.toolbox.epc.diagram.part.EpcDiagramEditor;
import org.bflow.toolbox.epc.diagram.wizards.ModelWizard;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Implements the IObjectActionDelegate to generate a set of new elements.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2009-11-22
 * @version 2019-01-26 AST Added logging
 */
public class ElementGeneratorAction implements IObjectActionDelegate {
	private IWorkbenchPart _activePart;
	private EpcDiagramEditor _editor;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		_activePart = targetPart;
		_editor = (EpcDiagramEditor) _activePart.getSite().getWorkbenchWindow().getActivePage().getActiveEditor();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	@Override
	public void run(IAction action) {
		WizardDialog wd = new WizardDialog(_activePart.getSite().getShell(), new ModelWizard(_editor));
		try {
			wd.open();
		} catch (Exception ex) {
			Log log = LogFactory.getLog(ElementGeneratorAction.class);
			log.error("Error on opening Model Wizard dialog", ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// Nothing to do
	}
}

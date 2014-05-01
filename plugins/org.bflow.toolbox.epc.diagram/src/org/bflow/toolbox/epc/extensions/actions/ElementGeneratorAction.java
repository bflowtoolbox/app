package org.bflow.toolbox.epc.extensions.actions;

import org.bflow.toolbox.epc.diagram.part.EpcDiagramEditor;
import org.bflow.toolbox.epc.diagram.wizards.ModelWizard;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Implements the IObjectActionDelegate to generate a set of new elements.
 * @author Arian Storch
 * @since 22/11/09
 *
 */
public class ElementGeneratorAction implements IObjectActionDelegate
{
	private IWorkbenchPart activePart;
	private EpcDiagramEditor editor;

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) 
	{
		this.activePart = targetPart;
		editor = (EpcDiagramEditor) activePart.getSite().getWorkbenchWindow().getActivePage().getActiveEditor();
	}

	@Override
	public void run(IAction action) 
	{								
		WizardDialog wd = new WizardDialog(activePart.getSite().getShell(), 
						new ModelWizard(editor));
		
		try
		{
		
			wd.open();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) 
	{
	
	}

}

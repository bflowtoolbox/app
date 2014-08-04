package org.bflow.toolbox.hive.templating.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISources;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Provides an implementation of {@link IHandler} to show the 
 * template selection wizard.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 0.0.1
 *
 */
public class ShowTemplateWizardCommand extends AbstractHandler implements IHandler {

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell activeShell = HandlerUtil.getActiveShell(event);
		MessageDialog.openInformation(activeShell, "Ein kleiner Schritt", "Hier geht es los!");
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.AbstractHandler#setEnabled(java.lang.Object)
	 */
	@Override
	public void setEnabled(Object evaluationContext) {
		Object activePart = HandlerUtil.getVariable(evaluationContext, ISources.ACTIVE_PART_NAME);
		Object activeSelection = HandlerUtil.getVariable(evaluationContext, ISources.ACTIVE_CURRENT_SELECTION_NAME);
		boolean isEnabled = false;
		if (activePart instanceof DiagramEditor)
			isEnabled = true;
		else if (activeSelection instanceof StructuredSelection) {
			StructuredSelection structSel = (StructuredSelection)activeSelection;
			isEnabled = !structSel.isEmpty() && structSel.getFirstElement() instanceof GraphicalEditPart;
		}
		
		super.setBaseEnabled(isEnabled);
	}
}
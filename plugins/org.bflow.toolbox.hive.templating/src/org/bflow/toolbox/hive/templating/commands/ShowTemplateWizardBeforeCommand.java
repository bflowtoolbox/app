package org.bflow.toolbox.hive.templating.commands;

import org.bflow.toolbox.epc.diagram.part.EpcDiagramEditor;
import org.bflow.toolbox.hive.templating.dialogs.TemplateAction;
import org.bflow.toolbox.hive.templating.dialogs.TemplateWizardFactory;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

public class ShowTemplateWizardBeforeCommand extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
        Shell activeShell = HandlerUtil.getActiveShell(event);
        EpcDiagramEditor editor = (EpcDiagramEditor) HandlerUtil.getActiveEditor(event);
        TemplateWizardFactory.openWizard(activeShell, editor,(IStructuredSelection) HandlerUtil.getCurrentSelection(event),TemplateAction.before);
        return null;
	}

	
}

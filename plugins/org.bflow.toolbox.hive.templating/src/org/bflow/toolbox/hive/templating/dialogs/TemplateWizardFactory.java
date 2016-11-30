package org.bflow.toolbox.hive.templating.dialogs;

import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * This class is used to ensure that the TemplateWizard can be only opened once.
 * The common way to open the window as dialog, which forces the focus isn't
 * useful, because while the wizard is open, the user may needs to select
 * diagram elements in eclipse.
 * 
 * @author Christian Soward
 * 
 */
public class TemplateWizardFactory {
	private static WizardDialog dialog;

	/**
	 * This method opens the wizard or restores the focus for the already opened
	 * wizard.
	 * 
	 * @param shell
	 *            The given shell.
	 * @param editor
	 *            The given editor.
	 * @param selection 
	 * @param action 
	 */
	public static void openWizard(Shell shell, DiagramDocumentEditor editor, IStructuredSelection selection, TemplateAction action) {
		if (dialog == null) {
			openWizardWhenDisposed(shell, editor, selection, action);
		} else if (dialog.getShell() == null) {
			openWizardWhenDisposed(shell, editor, selection, action);
		} else if (dialog.getShell().isDisposed() || dialog.getShell().isVisible() == false) {
			openWizard(shell, editor, selection, action);
		} else {
			dialog.getShell().setFocus();
		}
	}

	/**
	 * Initially executed if the wizard isn't opened.
	 * 
	 * @param shell
	 *            The given shell.
	 * @param editor
	 *            The given editor.
	 * @param action 
	 */
	private static void openWizardWhenDisposed(Shell shell, DiagramDocumentEditor editor, IStructuredSelection selection, TemplateAction action) {
		dialog = new TemplateWizardDialog(shell, new TemplateWizard(editor, selection, action));
		dialog.open();
	}
}

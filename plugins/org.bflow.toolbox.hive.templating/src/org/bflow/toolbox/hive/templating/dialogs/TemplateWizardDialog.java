package org.bflow.toolbox.hive.templating.dialogs;

import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

/**
 * A modeless, resizable WizardDialog
 * @author Markus Schnädelbach
 */
public class TemplateWizardDialog extends WizardDialog{
	
//	private TemplateWizard templateWizard;

	public TemplateWizardDialog(Shell parentShell, TemplateWizard newWizard) {
		super(parentShell, newWizard);
		setShellStyle(SWT.CLOSE | SWT.APPLICATION_MODAL | SWT.BORDER | SWT.TITLE | SWT.RESIZE | SWT.MAX);
//		setBlockOnOpen(false);
		setHelpAvailable(false);
	}
}

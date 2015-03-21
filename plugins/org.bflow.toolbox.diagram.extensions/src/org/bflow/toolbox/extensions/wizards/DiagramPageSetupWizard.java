package org.bflow.toolbox.extensions.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

/**
 * Represents an wizard to select or enter the site of the diagram page.
 * 
 * @author Joerg Hartmann
 * @since 0.0.7
 *
 */
public class DiagramPageSetupWizard extends Wizard implements INewWizard {

	/**
	 * An <code>WizardPage</code> containing all editable fields.
	 */
	private DiagramPageSetupWizardPage setupPage;

	/**
	 * Called if the 'OK' button was pressed. Gets the values from the wizard
	 * page, calculates and sets the size.
	 * 
	 * @return
	 */
	public boolean performFinish() {
		setupPage.applySetup();
		return true;
	}

	/**
	 * Initializes this wizard.
	 * 
	 * @param workbench
	 * @param selection
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}

	/**
	 * Adds the <code>DiagramPageSetupWizardPage</code>.
	 */
	public void addPages() {
		setupPage = new DiagramPageSetupWizardPage();
		addPage(setupPage);
	}
}

package org.bflow.toolbox.hive.interchange.wizard.pages;

import org.bflow.toolbox.hive.interchange.wizard.ImageExportWizard;
import org.bflow.toolbox.hive.nls.NLSupport;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Implements a {@link WizardPage} that is used by the {@link ImageExportWizard}.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 19/07/13
 * @version 09/09/13
 */
public class ImageExportWizardPage extends WizardPage {
	
	/**
	 * Instantiates a new image export wizard page.
	 */
	public ImageExportWizardPage() {
		super("Add-ons Image export wizard page"); //$NON-NLS-1$
		setTitle(NLSupport.ImageExportWizardPage_Title);
		setDescription(NLSupport.ImageExportWizardPage_Description);
		setPageComplete(true);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout());
		
		String description = NLSupport.ImageExportWizardPage_LblLine1 +
				NLSupport.ImageExportWizardPage_LblLine2 +
				NLSupport.ImageExportWizardPage_LblLine3;
		
		Label lblDescription = new Label(container, SWT.NONE);
		lblDescription.setText(description);
		lblDescription.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		setControl(container);
	}
}

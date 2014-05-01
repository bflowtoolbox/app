package org.bflow.toolbox.hive.interchange.wizard.pages;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeDescriptor;
import org.bflow.toolbox.hive.interchange.wizard.MIFExportWizard;
import org.bflow.toolbox.hive.nls.NLSupport;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Defines the export wizard page used by the export wizard.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 14/08/09
 * @version 28/12/13
 * @see MIFExportWizard
 * 
 */
public class MIFExportWizardPage extends WizardPage {
	private IStructuredSelection selectedFiles;

	private Combo cbExportTypes;

	private List<IInterchangeDescriptor> exportDescriptions;

	private Text textFieldTargetFile;
	private Text textFieldSourceFile;
	private Text txtDescription;

	private IInterchangeDescriptor selectedExportDescription;

	/**
	 * Constructor.
	 * 
	 * @param selectedFile
	 *            selected file into the workbench
	 */
	public MIFExportWizardPage(IStructuredSelection selectedFiles) {
		super("Add-ons export wizard page"); //$NON-NLS-1$
		setTitle(NLSupport.MIFExportWizardPage_Title);
		setDescription(NLSupport.MIFExportWizardPage_Description);
		setPageComplete(true);

		this.selectedFiles = selectedFiles;
	}

	@Override
	public void createControl(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);

		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL
				| GridData.HORIZONTAL_ALIGN_FILL));

		Label lblSelDiagram = new Label(composite, SWT.NONE);
		lblSelDiagram.setText(NLSupport.MIFExportWizardPage_LabelSelectDiagramText);

		textFieldSourceFile = new Text(composite, SWT.BORDER);

		String selectionString = StringUtils.EMPTY;

		for (Object f : selectedFiles.toArray())
			selectionString += ((IFile) f).getName() + " "; //$NON-NLS-1$

		textFieldSourceFile.setText(selectionString);
		textFieldSourceFile.setEditable(false);

		GridData txtSelFileGridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		txtSelFileGridData.widthHint = 150;
		textFieldSourceFile.setLayoutData(txtSelFileGridData);

		Label lblSelExportType = new Label(composite, SWT.NONE);
		lblSelExportType.setText(NLSupport.MIFExportWizardPage_LabelSelectExportTypeText);

		cbExportTypes = new Combo(composite, SWT.BORDER | SWT.READ_ONLY);
		prepareComboBox();

		Label lblDescription = new Label(composite, SWT.NONE);
		lblDescription.setText(NLSupport.MIFExportWizardPage_LabelDescriptionText);

		txtDescription = new Text(composite, SWT.WRAP | SWT.READ_ONLY);
		txtDescription.setText(StringUtils.EMPTY);

		GridData txtDescriptionGridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		txtDescriptionGridData.widthHint = 200;
		txtDescriptionGridData.heightHint = 50;

		txtDescription.setLayoutData(txtDescriptionGridData);

		Label lblTargetFile = new Label(composite, SWT.NONE);
		lblTargetFile.setText(NLSupport.MIFExportWizardPage_LabelTargetFileText);

		Composite fileSelection = new Composite(composite, SWT.NONE);

		GridLayout fileSelectionLayout = new GridLayout(2, false);
		fileSelectionLayout.marginLeft = 0;
		fileSelectionLayout.horizontalSpacing = 0;

		fileSelection.setLayout(fileSelectionLayout);
		fileSelection.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL));

		textFieldTargetFile = new Text(fileSelection, SWT.BORDER);
		textFieldTargetFile.setText(StringUtils.EMPTY);
		textFieldTargetFile.setEnabled(false);

		GridData txtTargetFileGridData = new GridData(
				GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);

		txtTargetFileGridData.widthHint = 200;
		textFieldTargetFile.setLayoutData(txtTargetFileGridData);

		Button btnBrowse = new Button(fileSelection, SWT.NONE);
		btnBrowse.setText(NLSupport.MIFExportWizardPage_ButtonBrowseText);
		btnBrowse.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dialog = new DirectoryDialog(composite.getShell());

				dialog.setFilterPath(null);
				dialog.setMessage(NLSupport.MIFExportWizardPage_DialogMessage);

				textFieldTargetFile.setText(dialog.open());
			}
		});

		this.setControl(composite);
	}

	/**
	 * Sets the export description files
	 * 
	 * @param exportDescriptions
	 */
	public void setExportDescriptions(List<IInterchangeDescriptor> exportDescriptions) {
		this.exportDescriptions = exportDescriptions;
	}

	private void prepareComboBox() {
		if (exportDescriptions == null)
			return;

		for (IInterchangeDescriptor expDescr : exportDescriptions)
			cbExportTypes.add(expDescr.getName() + " (*." //$NON-NLS-1$
					+ expDescr.getFileExtensions()[0] + ")"); //$NON-NLS-1$

		cbExportTypes.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			@Override
			public void widgetSelected(SelectionEvent e) {
				int selIndex = cbExportTypes.getSelectionIndex();

				if (selIndex == -1)
					return;

				IInterchangeDescriptor exportDescription = exportDescriptions
						.get(selIndex);

				selectedExportDescription = exportDescription;

				txtDescription.setText(exportDescription.getDescription());

			}
		});

		cbExportTypes.setVisibleItemCount(10);
	}

	/**
	 * returns the text field that contains the path for the source file
	 * 
	 * @return text field containing path to the source file
	 */
	public Text getTextFieldSourceFile() {
		return textFieldSourceFile;
	}

	/**
	 * returns the text field that contains the path for the target file
	 * 
	 * @return text field containing path to the target file
	 */
	public Text getTextFieldTargetFile() {
		return textFieldTargetFile;
	}

	/**
	 * returns the export description that has been selected or currently is<br/>
	 * if none is selected null will be returned
	 * 
	 * @return selected export description or null
	 */
	public IInterchangeDescriptor getSelectedExportDescription() {
		return selectedExportDescription;
	}

}

package org.bflow.toolbox.hive.interchange.wizard.pages;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeDescriptor;
import org.bflow.toolbox.hive.interchange.wizard.MIFExportWizard;
import org.bflow.toolbox.hive.nls.NLSupport;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Defines the export wizard page used by the export wizard.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2009-08-14
 * @version 2013-12-28
 * 			2015-02-27 Removed read-only flag from text control
 * 			2018-10-05 Modernized layout
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
		final Composite pageComposite = new Composite(parent, SWT.NONE);

		pageComposite.setLayout(new GridLayout(3, false));
		pageComposite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL));

		//- First row (selected diagrams)
		Label lblSelDiagram = new Label(pageComposite, SWT.NONE);
		lblSelDiagram.setLayoutData(new GridData(SWT.RIGHT, SWT.DEFAULT, false, false));
		lblSelDiagram.setText(NLSupport.MIFExportWizardPage_LabelSelectDiagramText);

		textFieldSourceFile = new Text(pageComposite, SWT.BORDER);

		String selectionString = StringUtils.EMPTY;

		for (Object file : selectedFiles.toArray())
			selectionString += ((IFile) file).getName() + " "; //$NON-NLS-1$

		textFieldSourceFile.setText(selectionString);
		textFieldSourceFile.setEditable(false);

		GridData txtSelFileGridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		txtSelFileGridData.horizontalSpan = 2;
		txtSelFileGridData.widthHint = 150;
		textFieldSourceFile.setLayoutData(txtSelFileGridData);

		//- Second row (export type)
		Label lblSelExportType = new Label(pageComposite, SWT.NONE);
		lblSelExportType.setLayoutData(new GridData(SWT.RIGHT, SWT.DEFAULT, false, false));
		lblSelExportType.setText(NLSupport.MIFExportWizardPage_LabelSelectExportTypeText);

		cbExportTypes = new Combo(pageComposite, SWT.BORDER | SWT.READ_ONLY);
		GridData cbExpTypGridData = new GridData(SWT.DEFAULT, SWT.DEFAULT, false, false);
		cbExpTypGridData.horizontalSpan = 2;
		cbExportTypes.setLayoutData(cbExpTypGridData);
		prepareComboBox();

		//- Third row (export description)
		Label lblDescription = new Label(pageComposite, SWT.NONE);
		lblDescription.setLayoutData(new GridData(SWT.RIGHT, SWT.DEFAULT, false, false));
		lblDescription.setText(NLSupport.MIFExportWizardPage_LabelDescriptionText);

		txtDescription = new Text(pageComposite, SWT.WRAP | SWT.READ_ONLY);
		txtDescription.setText(StringUtils.EMPTY);

		GridData txtDescriptionGridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		txtDescriptionGridData.horizontalSpan = 2;
		txtDescriptionGridData.widthHint = 200;
		txtDescriptionGridData.heightHint = 50;

		txtDescription.setLayoutData(txtDescriptionGridData);

		//- Fourth row (export target)
		Label lblTargetFile = new Label(pageComposite, SWT.NONE);
		lblTargetFile.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
		lblTargetFile.setText(NLSupport.MIFExportWizardPage_LabelTargetFileText);

		textFieldTargetFile = new Text(pageComposite, SWT.BORDER);
		textFieldTargetFile.setText(StringUtils.EMPTY);
		textFieldTargetFile.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				checkExportFolder(textFieldTargetFile.getText());
			}
		});

		GridData txtTargetFileGridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		textFieldTargetFile.setLayoutData(txtTargetFileGridData);
		
		Button btnBrowse = new Button(pageComposite, SWT.NONE);
		btnBrowse.setText(NLSupport.MIFExportWizardPage_ButtonBrowseText);
		
		Point pt = btnBrowse.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
		GridData btnBrowseGrdDt = new GridData(SWT.DEFAULT, SWT.DEFAULT, false, false);
		btnBrowseGrdDt.widthHint = pt.x + 20; // Add some nice margin
		btnBrowse.setLayoutData(btnBrowseGrdDt);
		
		btnBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showDirectoryDialog(((Control)e.widget).getShell(),textFieldTargetFile.getText());
			}
		});

		applyPreferencesSettings();
		setControl(pageComposite);
	}
	
	/**
	 * Checks if the given export folder path is valid. If not the wizard page
	 * will show an error and cannot be completed.
	 * 
	 * @param folderPath
	 *            Folder path to check
	 */
	private void checkExportFolder(String folderPath) {
		IPath path = Path.fromOSString(folderPath);
		File file = path.toFile();
		
		// Check if the path points to a valid location
		if (!file.exists()) {
			setErrorMessage(NLSupport.MIFExportWizardPage_Error_PathDoesntExist);
			setPageComplete(false);
			return;
		}
		
		// Check if the path points to a directory
		if (!file.isDirectory()) {
			setErrorMessage(NLSupport.MIFExportWizardPage_Error_PathIsntFolder);
			setPageComplete(false);
			return;
		}
		
		setErrorMessage(null);
		setPageComplete(true);
	}
	
	/**
	 * Shows the directory selection dialog.
	 * 
	 * @param shell
	 *            Dialog shell
	 */
	private void showDirectoryDialog(Shell shell, String startpath) {
		DirectoryDialog dialog = new DirectoryDialog(shell);

		dialog.setFilterPath(startpath);
		dialog.setMessage(NLSupport.MIFExportWizardPage_DialogMessage);
		String selectedFolder = dialog.open();
		if (selectedFolder == null) return; // User cancelled	
		
		textFieldTargetFile.setText(selectedFolder);
		checkExportFolder(selectedFolder);
	}
	
	/**
	 * Proofs if there are preference settings that can be applied to the
	 * controls.
	 */
	private void applyPreferencesSettings() {
		IDialogSettings dialogSettings = getDialogSettings().getSection(KEY_SECTION_NAME);
		if (dialogSettings == null) return;
		
		String selectedFolder = dialogSettings.get(KEY_SELECTED_FOLDER);
		if (selectedFolder == null) return;
		
		textFieldTargetFile.setText(selectedFolder);
	}
	
	/**
	 * Tells this wizard page that the wizard is now performing his finish
	 * method.
	 */
	public void onWizardPerformsFinish() {
		// Store preferences settings
		IDialogSettings dialogSettings = getDialogSettings().getSection(KEY_SECTION_NAME);
		if (dialogSettings == null)
			dialogSettings = getDialogSettings().addNewSection(KEY_SECTION_NAME);
		
		String valueFolder = textFieldTargetFile.getText();		
		dialogSettings.put(KEY_SELECTED_FOLDER, valueFolder);
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
		if (exportDescriptions == null)	return;

		for (IInterchangeDescriptor expDescr : exportDescriptions)
			cbExportTypes.add(expDescr.getName() + " (*." //$NON-NLS-1$
					+ expDescr.getFileExtensions()[0] + ")"); //$NON-NLS-1$

		cbExportTypes.addSelectionListener(new SelectionAdapter() {		
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selIndex = cbExportTypes.getSelectionIndex();
				if (selIndex == -1) return;

				IInterchangeDescriptor exportDescription = exportDescriptions.get(selIndex);
				selectedExportDescription = exportDescription;
				txtDescription.setText(exportDescription.getDescription());
			}
		});

		cbExportTypes.setVisibleItemCount(20);
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

	private static final String KEY_SECTION_NAME 	= "key.bflow.toolbox.addonsinterchange.exportwizard.section.name"; //$NON-NLS-1$
	private static final String KEY_SELECTED_FOLDER = "key.bflow.toolbox.addonsinterchange.exportwizard.selected.folder"; //$NON-NLS-1$
}

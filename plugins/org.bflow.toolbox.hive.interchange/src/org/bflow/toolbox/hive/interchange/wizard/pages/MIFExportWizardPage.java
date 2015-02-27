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
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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
 * @since 14.08.09
 * @version 28.12.13
 * 			27.02.15 Removed readonly flag from text control
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
		composite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL));

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
		textFieldTargetFile.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				checkExportFolder(textFieldTargetFile.getText());
			}
		});

		GridData txtTargetFileGridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);

		txtTargetFileGridData.widthHint = 200;
		textFieldTargetFile.setLayoutData(txtTargetFileGridData);

		Button btnBrowse = new Button(fileSelection, SWT.NONE);
		btnBrowse.setText(NLSupport.MIFExportWizardPage_ButtonBrowseText);
		btnBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showDirectoryDialog(((Control)e.widget).getShell());
			}
		});

		this.setControl(composite);
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
	private void showDirectoryDialog(Shell shell) {
		DirectoryDialog dialog = new DirectoryDialog(shell);

		dialog.setFilterPath(null);
		dialog.setMessage(NLSupport.MIFExportWizardPage_DialogMessage);
		String selectedFolder = dialog.open();
		if (selectedFolder == null) return; // User cancelled	
		
		textFieldTargetFile.setText(selectedFolder);
		checkExportFolder(selectedFolder);
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

}

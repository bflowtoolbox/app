package org.bflow.toolbox.hive.interchange.wizard.pages;

import java.io.File;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.bflow.toolbox.hive.interchange.mif.core.IInterchangeDescriptor;
import org.bflow.toolbox.hive.interchange.wizard.EImportOption;
import org.bflow.toolbox.hive.nls.NLSupport;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

/**
 * This class implements the import wizard page.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2009-08-17
 * @version 2013-12-14
 * 		    2015-03-12 AST Changed representation of of a multiple file selection
 * 			2015-06-07 AST Fixed missing source file string when just selecting one element
 *
 */
public class MIFImportWizardPage extends WizardPage {
	private List<IInterchangeDescriptor> _importDescriptions = new ArrayList<IInterchangeDescriptor>();
	private String _fileExtensions[];
	private Text _textFieldFile;
	private Combo _cbImportDescriptions;
	private IInterchangeDescriptor _selectedImportDescription = null;
	private Text _textFieldDescription;
	private Text _textFieldTarget;
	private Button _chkArrangeAll, _chkPackPage, _chkAutoSize;
	
	private String _basicPath;
	private String _sourceFileString;
	
	private IWorkbench _workbench = null;
	private IStructuredSelection _selection;
	
	/** Initializes the new instance. */
	public MIFImportWizardPage() {
		super("Add-ons import wizard page"); //$NON-NLS-1$
		setTitle(NLSupport.MIFImportWizardPage_Title); 
		setDescription(NLSupport.MIFImportWizardPage_Description); 
	}

	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		
		composite.setLayout(new GridLayout(2, false));
		
		Label lblFormat = new Label(composite, SWT.NONE);
		lblFormat.setText(NLSupport.MIFImportWizardPage_LblFormat); 
		
		_cbImportDescriptions = new Combo(composite, SWT.READ_ONLY);
		prepareComboBox();
		
		Label lblDescription = new Label(composite, SWT.NONE);
		lblDescription.setText(NLSupport.MIFImportWizardPage_LblDescription); 
		
		_textFieldDescription = new Text(composite, SWT.READ_ONLY);
		
		GridData txtDescriptionGridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		txtDescriptionGridData.widthHint = 200;
		txtDescriptionGridData.heightHint = 50;

		_textFieldDescription.setLayoutData(txtDescriptionGridData);
		
		Label lblSelectFile = new Label(composite, SWT.NONE);
		lblSelectFile.setText(NLSupport.MIFImportWizardPage_LblSelectSrcFile); 
		
		_textFieldFile = new Text(composite, SWT.BORDER);
		
		GridData textFieldFileGridData = new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false);
		textFieldFileGridData.widthHint = 400;
		
		_textFieldFile.setLayoutData(textFieldFileGridData);
		
		Label nullLbl = new Label(composite, SWT.NONE);
		nullLbl.setText(StringUtils.EMPTY); //$NON-NLS-1$
		
		Button btnBrowse = new Button(composite, SWT.NONE);
		
		btnBrowse.setText(NLSupport.MIFImportWizardPage_BtnSelectSrcFile);  //$NON-NLS-2$
		
		btnBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e)  {
				if (_selectedImportDescription == null) return;
								
				FileDialog fd = new FileDialog(composite.getShell(), SWT.OPEN | SWT.MULTI);
				
				Vector<String> ext = new Vector<String>();
				
				for(String s:_selectedImportDescription.getFileExtensions()[0].split(";")) //$NON-NLS-1$
					ext.add("*."+s); //$NON-NLS-1$
				
				ext.add("*.*"); //$NON-NLS-1$
								
				fd.setFilterExtensions(ext.toArray(new String[ext.size()]));
				             
	            String ret = fd.open();
	            if (ret == null) return;
	            
	            String files[] = fd.getFileNames();
	            _basicPath = fd.getFilterPath();
				
	            boolean isEditable = true;
	            
				if (files.length > 1) {					
					_sourceFileString = StringUtils.join(files, "; "); //$NON-NLS-1$
					
					final int Limit = 3000;
					
					String textString = _sourceFileString;
					if (textString.length() > Limit) {
						textString = _sourceFileString.substring(0, Limit);
						textString = String.format("%s...", textString); //$NON-NLS-1$
						isEditable = false;
					}
					
					if (files.length > 10) {
						String textFormat = NLSupport.MIFImportWizardPage_Text_ManyFilesSelected;
						textString = String.format(textFormat, files.length);
						isEditable = false;
					}
					
					_textFieldFile.setText(textString);
				} else {
					_sourceFileString = files[0];
					_textFieldFile.setText(files[0]);
				}
				
				_textFieldFile.setEditable(isEditable);							
			}});
		
		Label lblTargetDir = new Label(composite, SWT.NONE);
		lblTargetDir.setText(NLSupport.MIFImportWizardPage_LblTgtDir); 
		
		_textFieldTarget = new Text(composite, SWT.BORDER);
		
		GridData textFieldTargetGridData = new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false);
		textFieldTargetGridData.widthHint = 400;
		
		_textFieldTarget.setLayoutData(textFieldTargetGridData);
		
		Label lblNull = new Label(composite, SWT.NONE);
		lblNull.setText(StringUtils.EMPTY); //$NON-NLS-1$ 
		
		Button btnBrowseTarget = new Button(composite, SWT.NONE);
		btnBrowseTarget.setText(NLSupport.MIFImportWizardPage_BtnTgtDir);  //$NON-NLS-2$
		
		btnBrowseTarget.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
				
				ContainerSelectionDialog dlg = new ContainerSelectionDialog(getShell(), wsRoot, true, null);
				if (dlg.open() == ContainerSelectionDialog.CANCEL) return ;
				
				Object selections[] = dlg.getResult();				
				if (selections.length == 0) return;
				
				IPath selPath = (IPath) selections[0];
				IPath target = wsRoot.getLocation().append(selPath);
				String osFile = target.toFile().getAbsolutePath();
				
				_textFieldTarget.setText(osFile);
			}});
		
		// Options Area
		Group optionsGroup = new Group(composite, SWT.NONE);
		optionsGroup.setLayout(new GridLayout());
		optionsGroup.setText(NLSupport.MIFImportWizardPage_Options);
		
		GridData optionsCompositeGridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		optionsCompositeGridData.horizontalSpan = 2;
		optionsGroup.setLayoutData(optionsCompositeGridData);
		
		_chkArrangeAll = new Button(optionsGroup, SWT.CHECK);
		_chkArrangeAll.setText(NLSupport.MIFImportWizardPage_ArrangeAll);
		
		_chkPackPage  = new Button(optionsGroup, SWT.CHECK);
		_chkPackPage.setText(NLSupport.MIFImportWizardPage_PackPage);
		
		_chkAutoSize = new Button(optionsGroup, SWT.CHECK);
		_chkAutoSize.setText(NLSupport.MIFImportWizardPage_BestSizeElements);
		
		// Make a pre-selection if possible
		setPreferencesOrDefaultSettings();
		
		setControl(composite);
	}
	
	private void prepareComboBox() {
		for (IInterchangeDescriptor impDesc:_importDescriptions)
			_cbImportDescriptions.add(""+impDesc.getName()+" (*."+impDesc.getFileExtensions()[0]+")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		_cbImportDescriptions.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				_selectedImportDescription = _importDescriptions.get(_cbImportDescriptions.getSelectionIndex());
				_textFieldDescription.setText(_selectedImportDescription.getDescription().replaceAll("//", "\r\n")); //$NON-NLS-1$ //$NON-NLS-2$
			}});
		
		_cbImportDescriptions.setVisibleItemCount(20);
	}
	
	/**
	 * Returns a string that contains all selected file names separated by an
	 * semicolon.
	 * 
	 * @return String with semicolon separated file names
	 */
	public String getSourceFileString() {
		return _sourceFileString;
	}
	
	/**
	 * Returns a string that contains the target path.
	 * 
	 * @return String describing the target path
	 */
	public String getTargetFileString() {
		return _textFieldTarget.getText();
	}
	
	/**
	 * Returns the selected import descriptor.
	 * 
	 * @return Selected import descriptor
	 */
	public IInterchangeDescriptor getSelectedImportDescription() {
		return _selectedImportDescription;
	}
	
	/**
	 * Returns the available import descriptors.
	 * 
	 * @return Collection of import descriptors
	 */
	public List<IInterchangeDescriptor> getImportDescriptions() {
		return _importDescriptions;
	}
	
	/**
	 * Returns a set of enums which contains all selected options 
	 * for the import.
	 * 
	 * @return Set of enums
	 */
	public EnumSet<EImportOption> getImportOptions() {
		EnumSet<EImportOption> importOptions = EnumSet.noneOf(EImportOption.class);
		
		if (_chkArrangeAll.getSelection())
			importOptions.add(EImportOption.ArrangeAll);
		
		if (_chkPackPage.getSelection())
			importOptions.add(EImportOption.PackPage);
		
		if (_chkAutoSize.getSelection())
			importOptions.add(EImportOption.AutoSize);
		
		return importOptions;
	}
	
	/**
	 * Sets the available import descriptors.
	 * 
	 * @param importDescriptions
	 *            collection of import descriptors
	 */
	public void setImportDescriptions(List<IInterchangeDescriptor> importDescriptions) {
		this._importDescriptions = importDescriptions;
		
		/*
		 * Updating the file extensions
		 */
		_fileExtensions = new String[importDescriptions.size()];
		
		for (int i = 0; i < _fileExtensions.length; i++)
			_fileExtensions[i] = "*."+importDescriptions.get(i).getFileExtensions()[0]; //$NON-NLS-1$
	}
	
	/**
	 * Returns the base path of the selected files.
	 * 
	 * @return Base path of the selected files
	 */
	public String getBasicPath() {
		return _basicPath;
	}
	
	/**
	 * Checks if the workspace only contains one project. If it is this project 
	 * will be preselected by default. If there is more than one project preferences 
	 * settings will be used to determine what folder is preselected. If no decision 
	 * can be made it will be empty.
	 * 
	 * @param txtTargetFile The control which shows the target file path
	 */
	private void setPreferencesOrDefaultSettings() {
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IProject[] projects = workspaceRoot.getProjects();
		String pathFile = StringUtils.EMPTY;
		
		// If there are preferences settings they will be preferred
		IDialogSettings dialogSettings = getDialogSettings().getSection(KEY_SECTION_NAME);
		if(dialogSettings != null) {
			int selectedImport = -1; 
			String selectedFolder = dialogSettings.get(KEY_SELECTED_FOLDER);
			
			try {
				selectedImport = dialogSettings.getInt(KEY_SELECTED_IMPORT);
			} catch(NumberFormatException ex) {
				// Can be ignored
			}

			// Update controls that are affected by the pre-selection
			_cbImportDescriptions.select(selectedImport);
			if (selectedImport < _importDescriptions.size())
				_selectedImportDescription = _importDescriptions.get(selectedImport);
			if (_selectedImportDescription != null)
				_textFieldDescription.setText(_selectedImportDescription.getDescription());
			
			if (selectedFolder != null)
				pathFile = selectedFolder;
			
			// Set options
			_chkArrangeAll.setSelection(dialogSettings.getBoolean(KEY_ARRANGE_ALL));
			_chkPackPage.setSelection(dialogSettings.getBoolean(KEY_PACK_PAGE));
			_chkAutoSize.setSelection(dialogSettings.getBoolean(KEY_AUTOSIZE));
		}
		
		// Open create project dialog
		if (projects.length == 0) {
			IWorkbenchWizard wizard = new BasicNewProjectResourceWizard();
			wizard.init(_workbench, _selection);
			WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(), wizard);
			dialog.open();
			projects = workspaceRoot.getProjects();
		}
		
		// Select the one per default
		if (projects.length == 1) {
			pathFile = projects[0].getLocation().toFile().getAbsolutePath();
		}
		
		// If there are more projects try to use the selected one
		if (projects.length > 1) {
			ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getSelectionService().getSelection(IPageLayout.ID_PROJECT_EXPLORER);
			
			if (selection instanceof TreeSelection) {
				ITreeSelection treeSelection = (ITreeSelection) selection;
				Object selectedObject = treeSelection.getFirstElement();
				
				// If a file has been selected resolve the project of it
				if (selectedObject instanceof IFile) {
					IFile file = (IFile) selectedObject;
					selectedObject = file.getProject();
				}
				
				// Resolve the project file path
				if (selectedObject instanceof IProject) {
					IProject proj = (IProject) selectedObject;
					pathFile = proj.getLocation().toFile().getAbsolutePath();
				}
			}
		}
		
		// Check if the path really exists
		if (!new File(pathFile).exists())
			pathFile = StringUtils.EMPTY;
		
		_textFieldTarget.setText(pathFile);
	}
	
	/**
	 * Tells this wizard page that the wizard is now performing his finish method.
	 */
	public void onWizardPerformsFinish() {
		// Store preferences settings
		IDialogSettings dialogSettings = getDialogSettings().getSection(KEY_SECTION_NAME);
		if (dialogSettings == null)
			dialogSettings = getDialogSettings().addNewSection(KEY_SECTION_NAME);
		
		String valueFolder = _textFieldTarget.getText();
		int valueImport = _cbImportDescriptions.getSelectionIndex();
		
		dialogSettings.put(KEY_SELECTED_FOLDER, valueFolder);
		dialogSettings.put(KEY_SELECTED_IMPORT, valueImport);
		
		dialogSettings.put(KEY_ARRANGE_ALL, _chkArrangeAll.getSelection());
		dialogSettings.put(KEY_PACK_PAGE, _chkPackPage.getSelection());
		dialogSettings.put(KEY_AUTOSIZE, _chkAutoSize.getSelection());
	}
	
	/**
	 * Tells the wizard page that it shall perform its initializing.
	 * 
	 * @param workbenchWindow Workbench window
	 * @param selection Workbench selection
	 */
	public void onInitialize(IWorkbench workbench, IStructuredSelection selection) {
		_workbench = workbench;
		_selection = selection;
	}
	
	private static final String KEY_SECTION_NAME    = "key.bflow.toolbox.addonsinterchange.importwizard.section.name"; //$NON-NLS-1$
	private static final String KEY_SELECTED_IMPORT = "key.bflow.toolbox.addonsinterchange.importwizard.selected.import"; //$NON-NLS-1$
	private static final String KEY_SELECTED_FOLDER = "key.bflow.toolbox.addonsinterchange.importwizard.selected.folder"; //$NON-NLS-1$
	private static final String KEY_ARRANGE_ALL     = "key.bflow.toolbox.addonsinterchange.importwizard.options.arrangeall"; //$NON-NLS-1$
	private static final String KEY_PACK_PAGE       = "key.bflow.toolbox.addonsinterchange.importwizard.options.packpage"; //$NON-NLS-1$
	private static final String KEY_AUTOSIZE        = "key.bflow.toolbox.addonsinterchange.importwizard.options.autosize"; //$NON-NLS-1$
}

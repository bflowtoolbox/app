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
import org.eclipse.swt.events.SelectionListener;
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
 * @since 17/08/09
 * @version 14/12/13
 *
 */
public class MIFImportWizardPage extends WizardPage 
{
	private List<IInterchangeDescriptor> importDescriptions = new ArrayList<IInterchangeDescriptor>();
	private String fileExtensions[];
	private Text textFieldFile;
	private Combo cbImportDescriptions;
	private IInterchangeDescriptor selectedImportDescription = null;
	private Text textFieldDescription;
	private Text textFieldTarget;
	private Button chkArrangeAll, chkPackPage, chkAutoSize;
	
	private String basicPath;
	
	private IWorkbench iWorkbench = null;
	private IStructuredSelection iSelection;
	
	/**
	 * Constructor.
	 */
	public MIFImportWizardPage() {
		super("Add-ons import wizard page"); //$NON-NLS-1$
		setTitle(NLSupport.MIFImportWizardPage_Title); 
		setDescription(NLSupport.MIFImportWizardPage_Description); 
	}

	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createControl(Composite parent) 
	{
		final Composite composite = new Composite(parent, SWT.NONE);
		
		composite.setLayout(new GridLayout(2, false));
		
		Label lblFormat = new Label(composite, SWT.NONE);
		lblFormat.setText(NLSupport.MIFImportWizardPage_LblFormat); 
		
		cbImportDescriptions = new Combo(composite, SWT.READ_ONLY);
		prepareComboBox();
		
		Label lblDescription = new Label(composite, SWT.NONE);
		lblDescription.setText(NLSupport.MIFImportWizardPage_LblDescription); 
		
		textFieldDescription = new Text(composite, SWT.READ_ONLY);
		
		GridData txtDescriptionGridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL
																| GridData.GRAB_HORIZONTAL);
		txtDescriptionGridData.widthHint = 200;
		txtDescriptionGridData.heightHint = 50;

		textFieldDescription.setLayoutData(txtDescriptionGridData);
		
		Label lblSelectFile = new Label(composite, SWT.NONE);
		lblSelectFile.setText(NLSupport.MIFImportWizardPage_LblSelectSrcFile); 
		
		textFieldFile = new Text(composite, SWT.BORDER);
		
		GridData textFieldFileGridData = new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false);
		textFieldFileGridData.widthHint = 400;
		
		textFieldFile.setLayoutData(textFieldFileGridData);
		
		Label nullLbl = new Label(composite, SWT.NONE);
		nullLbl.setText(StringUtils.EMPTY); //$NON-NLS-1$
		
		Button btnBrowse = new Button(composite, SWT.NONE);
		
		btnBrowse.setText(NLSupport.MIFImportWizardPage_BtnSelectSrcFile);  //$NON-NLS-2$
		
		btnBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e)  {
				if(selectedImportDescription == null)
					return ;
								
				FileDialog fd = new FileDialog(composite.getShell(), SWT.OPEN | SWT.MULTI);
				
				Vector<String> ext = new Vector<String>();
				
				for(String s:selectedImportDescription.getFileExtensions()[0].split(";")) //$NON-NLS-1$
					ext.add("*."+s); //$NON-NLS-1$
				
				ext.add("*.*"); //$NON-NLS-1$
								
				fd.setFilterExtensions(ext.toArray(new String[ext.size()]));
				             
	            String ret = fd.open();
	            
	            if(ret == null)
	            	return ;
	            
	            String files[] = fd.getFileNames();
	            basicPath = fd.getFilterPath();
				
				if(files.length > 1)
				{
					String filelist = ""; //$NON-NLS-1$
					
					for(int i = 0; i < files.length-1; i++)
						filelist += ""+files[i]+"; "; //$NON-NLS-1$ //$NON-NLS-2$
					
					filelist += files[files.length-1];
					textFieldFile.setText(filelist);
				}
				else
					textFieldFile.setText(files[0]);
				
			}});
		
		Label lblTargetDir = new Label(composite, SWT.NONE);
		lblTargetDir.setText(NLSupport.MIFImportWizardPage_LblTgtDir); 
		
		textFieldTarget = new Text(composite, SWT.BORDER);
		
		GridData textFieldTargetGridData = new GridData(SWT.BEGINNING, SWT.BEGINNING, true, false);
		textFieldTargetGridData.widthHint = 400;
		
		textFieldTarget.setLayoutData(textFieldTargetGridData);
		
		Label lblNull = new Label(composite, SWT.NONE);
		lblNull.setText(StringUtils.EMPTY); //$NON-NLS-1$ 
		
		Button btnBrowseTarget = new Button(composite, SWT.NONE);
		btnBrowseTarget.setText(NLSupport.MIFImportWizardPage_BtnTgtDir);  //$NON-NLS-2$
		
		btnBrowseTarget.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {				
			}

			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
				
				ContainerSelectionDialog dlg = new ContainerSelectionDialog(getShell(), 
						wsRoot, true, null);
				
				if(dlg.open() == ContainerSelectionDialog.CANCEL)
					return ;
				
				Object selections[] = dlg.getResult();
				
				if(selections.length == 0)
					return ;
				
				IPath selPath = (IPath) selections[0];
				IPath target = wsRoot.getLocation().append(selPath);
				String osFile = target.toFile().getAbsolutePath();
				
				textFieldTarget.setText(osFile);
				
			}});
		
		// Options Area
		Group optionsGroup = new Group(composite, SWT.NONE);
		optionsGroup.setLayout(new GridLayout());
		optionsGroup.setText(NLSupport.MIFImportWizardPage_Options);
		
		GridData optionsCompositeGridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		optionsCompositeGridData.horizontalSpan = 2;
		optionsGroup.setLayoutData(optionsCompositeGridData);
		
		chkArrangeAll = new Button(optionsGroup, SWT.CHECK);
		chkArrangeAll.setText(NLSupport.MIFImportWizardPage_ArrangeAll);
		
		chkPackPage  = new Button(optionsGroup, SWT.CHECK);
		chkPackPage.setText(NLSupport.MIFImportWizardPage_PackPage);
		
		chkAutoSize = new Button(optionsGroup, SWT.CHECK);
		chkAutoSize.setText(NLSupport.MIFImportWizardPage_BestSizeElements);
		
		// Make a pre-selection if possible
		setPreferencesOrDefaultSettings();
		
		setControl(composite);
	}
	
	private void prepareComboBox()
	{
		for(IInterchangeDescriptor impDesc:importDescriptions)
			cbImportDescriptions.add(""+impDesc.getName()+" (*."+impDesc.getFileExtensions()[0]+")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		cbImportDescriptions.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {				
			}

			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				selectedImportDescription = importDescriptions.get(cbImportDescriptions.getSelectionIndex());
				textFieldDescription.setText(selectedImportDescription.getDescription().replaceAll("//", "\r\n")); //$NON-NLS-1$ //$NON-NLS-2$
			}});
		
		cbImportDescriptions.setVisibleItemCount(20);
	}
		
	/**
	 * Returns the text field that contains the name of the selected file.
	 * 
	 * @return Text field with name of the selected file
	 */
	public Text getTextFieldFile() {
		return textFieldFile;
	}
	
	/**
	 * Returns the selected import descriptor.
	 * 
	 * @return Selected import descriptor
	 */
	public IInterchangeDescriptor getSelectedImportDescription() {
		return selectedImportDescription;
	}
	
	/**
	 * Returns the text field that contains the target path
	 * 
	 * @return Text field containing target path
	 */
	public Text getTextFieldTarget() {
		return textFieldTarget;
	}
	
	/**
	 * Returns the available import descriptors.
	 * 
	 * @return Collection of import descriptors
	 */
	public List<IInterchangeDescriptor> getImportDescriptions() {
		return importDescriptions;
	}
	
	/**
	 * Returns a set of enums which contains all selected options 
	 * for the import.
	 * 
	 * @return Set of enums
	 */
	public EnumSet<EImportOption> getImportOptions() {
		EnumSet<EImportOption> importOptions = EnumSet.noneOf(EImportOption.class);
		
		if(chkArrangeAll.getSelection())
			importOptions.add(EImportOption.ArrangeAll);
		
		if(chkPackPage.getSelection())
			importOptions.add(EImportOption.PackPage);
		
		if(chkAutoSize.getSelection())
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
		this.importDescriptions = importDescriptions;
		
		/*
		 * Updating the file extensions
		 */
		fileExtensions = new String[importDescriptions.size()];
		
		for(int i = 0; i < fileExtensions.length; i++)
			fileExtensions[i] = "*."+importDescriptions.get(i).getFileExtensions()[0]; //$NON-NLS-1$
	}
	
	/**
	 * Returns the base path of the selected files.
	 * 
	 * @return Base path of the selected files
	 */
	public String getBasicPath() {
		return basicPath;
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
			cbImportDescriptions.select(selectedImport);
			if(selectedImport < importDescriptions.size())
				selectedImportDescription = importDescriptions.get(selectedImport);
			if(selectedImportDescription != null)
				textFieldDescription.setText(selectedImportDescription.getDescription());
			
			if(selectedFolder != null)
				pathFile = selectedFolder;
			
			// Set options
			chkArrangeAll.setSelection(dialogSettings.getBoolean(KEY_ARRANGE_ALL));
			chkPackPage.setSelection(dialogSettings.getBoolean(KEY_PACK_PAGE));
			chkAutoSize.setSelection(dialogSettings.getBoolean(KEY_AUTOSIZE));
		}
		
		// Open create project dialog
		if(projects.length == 0) {
			IWorkbenchWizard wizard = new BasicNewProjectResourceWizard();
			wizard.init(iWorkbench, iSelection);
			WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(), wizard);
			dialog.open();
			projects = workspaceRoot.getProjects();
		}
		
		// Select the one per default
		if(projects.length == 1) {
			pathFile = projects[0].getLocation().toFile().getAbsolutePath();
		}
		
		// If there are more projects try to use the selected one
		if(projects.length > 1) {
			ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getSelectionService().getSelection(IPageLayout.ID_PROJECT_EXPLORER);
			
			if(selection instanceof TreeSelection) {
				ITreeSelection treeSelection = (ITreeSelection) selection;
				Object selectedObject = treeSelection.getFirstElement();
				
				// If a file has been selected resolve the project of it
				if(selectedObject instanceof IFile) {
					IFile file = (IFile) selectedObject;
					selectedObject = file.getProject();
				}
				
				// Resolve the project file path
				if(selectedObject instanceof IProject) {
					IProject proj = (IProject) selectedObject;
					pathFile = proj.getLocation().toFile().getAbsolutePath();
				}
			}
		}
		
		// Check if the path really exists
		if(!new File(pathFile).exists())
			pathFile = StringUtils.EMPTY;
		
		textFieldTarget.setText(pathFile);
	}
	
	/**
	 * Tells this wizard page that the wizard is now performing his finish method.
	 */
	public void onWizardPerformsFinish() {
		// Store preferences settings
		IDialogSettings dialogSettings = getDialogSettings().getSection(KEY_SECTION_NAME);
		if(dialogSettings == null)
			dialogSettings = getDialogSettings().addNewSection(KEY_SECTION_NAME);
		
		String valueFolder = textFieldTarget.getText();
		int valueImport = cbImportDescriptions.getSelectionIndex();
		
		dialogSettings.put(KEY_SELECTED_FOLDER, valueFolder);
		dialogSettings.put(KEY_SELECTED_IMPORT, valueImport);
		
		dialogSettings.put(KEY_ARRANGE_ALL, chkArrangeAll.getSelection());
		dialogSettings.put(KEY_PACK_PAGE, chkPackPage.getSelection());
		dialogSettings.put(KEY_AUTOSIZE, chkAutoSize.getSelection());
	}
	
	/**
	 * Tells the wizard page that it shall perform its initializing.
	 * 
	 * @param workbenchWindow Workbench window
	 * @param selection Workbench selection
	 */
	public void onInitialize(IWorkbench workbench, IStructuredSelection selection) {
		iWorkbench = workbench;
		iSelection = selection;
	}
	
	private static final String KEY_SECTION_NAME =    "key.bflow.toolbox.addonsinterchange.importwizard.section.name"; //$NON-NLS-1$
	private static final String KEY_SELECTED_IMPORT = "key.bflow.toolbox.addonsinterchange.importwizard.selected.import"; //$NON-NLS-1$
	private static final String KEY_SELECTED_FOLDER = "key.bflow.toolbox.addonsinterchange.importwizard.selected.folder"; //$NON-NLS-1$
	private static final String KEY_ARRANGE_ALL = "key.bflow.toolbox.addonsinterchange.importwizard.options.arrangeall"; //$NON-NLS-1$
	private static final String KEY_PACK_PAGE = "key.bflow.toolbox.addonsinterchange.importwizard.options.packpage"; //$NON-NLS-1$
	private static final String KEY_AUTOSIZE = "key.bflow.toolbox.addonsinterchange.importwizard.options.autosize"; //$NON-NLS-1$
}

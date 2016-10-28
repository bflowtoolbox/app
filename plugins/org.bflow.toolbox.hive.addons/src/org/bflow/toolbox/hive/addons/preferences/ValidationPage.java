package org.bflow.toolbox.hive.addons.preferences;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.bflow.toolbox.hive.addons.AddonsPlugin;
import org.bflow.toolbox.hive.addons.utils.RuleTree;
import org.bflow.toolbox.hive.addons.validation.Rule;
import org.bflow.toolbox.hive.addons.validation.ValidationService;
import org.bflow.toolbox.hive.nls.NLSupport;
import org.bflow.toolbox.hive.nls.NLUtil;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;
import org.osgi.service.prefs.Preferences;

/**
 * Defines the validation preferences for the add-on plug-in page used by the
 * eclipse preferences dialog.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 29/03/11
 * @version 14/12/13
 * 
 */
public class ValidationPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	/**
	 * Id for the validation rules set property.
	 */
	private static final String IdValidationRulesSet = "validationRulesSet"; //$NON-NLS-1$
	
	private Text descriptionText;
	private Link link;
	private Label imageLabel;
	private Composite top;
	private Image ruleImg;

	private CheckboxTreeViewer checkboxTreeViewer;
	private Preferences prefStore;
	private boolean validationRulesSet;

	private HashMap<TreeItem, Rule> installedItems = new HashMap<TreeItem, Rule>();

	private Image ruleDefaultImage;
	private List<Rule> installedRules;
	private Rule selectedRule;
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
	 */
	@Override
	protected void createFieldEditors() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {
		String abbr = NLUtil.getActiveLanguageAbbreviation();
		installedRules = ValidationService.getInstance().getRules(abbr);
		Assert.isNotNull(installedRules, "Could not found validation rules"); //$NON-NLS-1$
		this.prefStore = AddonsPlugin.getInstance().getPreferencesStore(); 
		validationRulesSet = prefStore.getBoolean(IdValidationRulesSet, true);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#performOk()
	 */
	@Override
	public boolean performOk() {
		prefStore.putBoolean(IdValidationRulesSet, true);
		// this.runValidation();
		return super.performOk();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		for(Iterator<Rule> it = installedRules.iterator(); it.hasNext();) {
			Rule rule = it.next();
			prefStore.remove(rule.getId());
			
			// Clear user error messages
			prefStore.remove(rule.getId() + "Message"); //$NON-NLS-1$
		}
		// Reset to default
		validationRulesSet = false;
		checkboxTreeViewer.refresh();
		validationRulesSet = true;
		
		super.performDefaults();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		top = new Composite(parent, SWT.NONE);
		top.setLayout(new GridLayout(2, false));

		// Creating checkbox tree viewer
		checkboxTreeViewer = new CheckboxTreeViewer(top);
		checkboxTreeViewer.getTree().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		checkboxTreeViewer.setCheckStateProvider(new CheckStateProvider());
		checkboxTreeViewer.setContentProvider(new RuleTreeContentProvider());
		checkboxTreeViewer.setLabelProvider(new RuleTreeLabelProvider());
		checkboxTreeViewer.setInput(RuleTree.create(installedRules));
		checkboxTreeViewer.addCheckStateListener(new CheckStateListener());
		checkboxTreeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				TreeSelection selection = (TreeSelection) event.getSelection();
				RuleTree ruleTree = (RuleTree) selection.getFirstElement();
				Rule rule = ruleTree.getRule();
				selectedRule = rule;
				if(rule == null)
					return;
				
				fillDescription(rule);
				fillImage(rule);
			}
		});

		ruleImg = new Image(top.getDisplay(), 120, 120);

		// Image
		imageLabel = new Label(top, SWT.BORDER);
		imageLabel.setImage(ruleImg);
		imageLabel.setAlignment(SWT.CENTER);

		// Description
		descriptionText = new Text(top, SWT.WRAP | SWT.MULTI | SWT.BORDER
				| SWT.H_SCROLL | SWT.V_SCROLL);

		GridData dscrTextGridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_FILL);

		dscrTextGridData.horizontalSpan = 1;

		descriptionText.setLayoutData(dscrTextGridData);
		descriptionText.setEditable(false);

		link = new Link(top, SWT.NONE);
		link.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, false, 2, 1));
		link.setText("URL:"); //$NON-NLS-1$
		link.setSize(400, 100);

		link.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					URL url = new URL(e.text);
					PlatformUI.getWorkbench().getBrowserSupport().createBrowser("validation browser").openURL(url); //$NON-NLS-1$
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		// Change description button
		Composite btnPane = new Composite(top, SWT.NONE);
		btnPane.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, false, 2, 1));
		btnPane.setLayout(new GridLayout(3, false));

		GridData grdDataBtns = new GridData(SWT.LEAD, SWT.TOP, true, false);
		grdDataBtns.minimumWidth = 80;

		Button btnEditText = new Button(btnPane, SWT.NONE);

		// btnEditText.setText(MessageProvider.getMessage("AbstractDiagramValidationPreferencesPage#msg2")+"...");
		btnEditText.setText(NLSupport.ValidationPage_ButtonEditText);
		btnEditText.setLayoutData(grdDataBtns);
		btnEditText.setEnabled(true);

		btnEditText.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Rule rule = selectedRule;
				if(rule == null)
					return;

				String prefMsg = StringUtils.EMPTY;

				if (prefStore.get(rule.getId() + "Message", null) != null) //$NON-NLS-1$
					prefMsg = prefStore.get(rule.getId() + "Message", null); //$NON-NLS-1$
				else
					prefMsg = rule.getMessage();

				InputDialog dlg = new InputDialog(
						top.getShell(),
						NLSupport.ValidationPage_InputDialogTitle,
						NLSupport.ValidationPage_InputDialogMessage,
						prefMsg, null);

				if (dlg.open() == InputDialog.OK) {
					prefStore.put(rule.getId() + "Message", dlg.getValue()); //$NON-NLS-1$
					fillDescription(rule);
				}
				return;
			}
		});

		Button btnExport = new Button(btnPane, SWT.NONE);
		btnExport.setText(NLSupport.ValidationPage_ButtonExportText);
		btnExport.setLayoutData(grdDataBtns);
		
		btnExport.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(getShell(), SWT.SAVE);
				
				fd.setOverwrite(true);
				fd.setFilterExtensions(new String[] {"*.ars"}); //$NON-NLS-1$
				fd.setFilterNames(new String[] {"Add-ons rule set (*.ars)"}); //$NON-NLS-1$
				
				String filename = fd.open();
				
				if(filename == null)
					return ;
				
				if(!filename.endsWith(".ars")) //$NON-NLS-1$
					filename += ".ars"; //$NON-NLS-1$
				
				File file = new File(filename);
				List<String> lines = new ArrayList<String>();
				
				for(TreeItem item:installedItems.keySet()) {
					if(item.getChecked()) {
						lines.add(installedItems.get(item).getId());
					}
				}
				
				try {
					FileUtils.writeLines(file, lines);
				} catch (IOException e1) {
					MessageDialog.openError(getShell(), NLSupport.ValidationPage_ErrorDialogTitle, NLSupport.ValidationPage_ExportErrorDialogText
							+e1.getLocalizedMessage());
				}
				
				MessageDialog.openInformation(getShell(), NLSupport.ValidationPage_MessageDialogTitle, NLSupport.ValidationPage_MessageDialogText);
				
			}
		});

		Button btnImport = new Button(btnPane, SWT.NONE);
		btnImport.setText(NLSupport.ValidationPage_ButtonImportText);
		btnImport.setLayoutData(grdDataBtns);
		
		btnImport.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("unchecked")
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(getShell(), SWT.OPEN);
				
				fd.setFilterExtensions(new String[] {"*.ars"}); //$NON-NLS-1$
				fd.setFilterNames(new String[] {"Add-ons rule set (*.ars)"}); //$NON-NLS-1$
				
				String filename = fd.open();
				
				if(filename == null)
					return ;
				
				List<String> lines;
				
				try {
					lines = FileUtils.readLines(new File(filename));
				} catch (IOException e1) {
					MessageDialog.openError(getShell(), "Error", NLSupport.ValidationPage_ImportErrorDialogText
							+e1.getLocalizedMessage());
					return ;
				}
				
				boolean overwrite = MessageDialog.openConfirm(getShell(), NLSupport.ValidationPage_InfoDialogTitle, NLSupport.ValidationPage_InfoDialogText +
						NLSupport.ValidationPage_InfoDialogText2);
				
				for(TreeItem item:installedItems.keySet()) {
					Rule rule = installedItems.get(item);
					
					boolean set = false;
					
					for(String s:lines)
						if(s.equalsIgnoreCase(rule.getId())) {
							set = true;
							lines.remove(s);
							break;
						}
					
					if(set) {
						item.setChecked(true);
					} else {
						if(overwrite) {
							item.setChecked(false);
						}
					}
				}
				
				MessageDialog.openInformation(getShell(), NLSupport.ValidationPage_FinishInfoDialogTitle, NLSupport.ValidationPage_FinishInfoDialogText);
			}
		});

		return top;
	}
	
	/**
	 * Event-Handler; Fills the image field.
	 */
	private void fillImage(Rule rule) {
		if (rule == null)
			return;

		if (rule.getImage().length() < 1) {
			if(ruleDefaultImage == null)
				ruleDefaultImage = new Image(top.getDisplay(), this.getClass().getResourceAsStream("/icons/question.png")); //$NON-NLS-1$
			
			ruleImg = ruleDefaultImage;
		} else {			
			try {
				InputStream is = rule.getBundle().getEntry(rule.getImage()).openStream();
				ruleImg = new Image(top.getDisplay(), is);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		ImageData imgData = ruleImg.getImageData();

		double fH = 120.0 / imgData.height;
		double fW = 120.0 / imgData.width;

		double f = (fH < fW ? fH : fW);

		if (f > 1)
			f = 1;

		int nWidth = (int) (imgData.width * f);
		int nHeight = (int) (imgData.height * f);

		ruleImg = new Image(top.getDisplay(), imgData.scaledTo(nWidth, nHeight));

		imageLabel.setImage(ruleImg);
	}

	/**
	 * Event handler; Fills the description text.
	 */
	private void fillDescription(Rule rule) {
		if (rule == null)
			return;

		String prefMsg = StringUtils.EMPTY;
		
		if (prefStore.get(rule.getId() + "Message", null) != null) //$NON-NLS-1$
			prefMsg = prefStore.get(rule.getId() + "Message", null); //$NON-NLS-1$
		else
			prefMsg = rule.getMessage();

		String urlMsg = "URL: <a>" + rule.getUrl() + "</a>"; //$NON-NLS-1$ //$NON-NLS-2$

		link.setText(urlMsg);
		link.setSize(400, 15);

		descriptionText.setText(rule.getDescription() + "\n\r\n\r" //$NON-NLS-1$
						+ NLSupport.ValidationPage_ButtonDescriptionText
						+ "\n\r" + prefMsg); //$NON-NLS-1$
	}

	/**
	 * Implements {@link ITreeContentProvider} for the Tree Viewer.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 31/10/13
	 */
	class RuleTreeContentProvider implements ITreeContentProvider {

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
		 */
		@Override
		public void dispose() {
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
		 */
		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.Object)
		 */
		@Override
		public Object[] getElements(Object inputElement) {
			RuleTree ruleTree = (RuleTree)inputElement;
			return ruleTree.getChildren();
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
		 */
		@Override
		public Object[] getChildren(Object parentElement) {
			RuleTree ruleTree = (RuleTree)parentElement;
			return ruleTree.getChildren();
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
		 */
		@Override
		public Object getParent(Object element) {
			return null;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
		 */
		@Override
		public boolean hasChildren(Object element) {
			RuleTree ruleTree = (RuleTree)element;			
			return ruleTree.hasChildren();
		}
	}
	
	/**
	 * Implements {@link ILabelProvider} for the Tree Viewer.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 31/10/13
	 */
	class RuleTreeLabelProvider implements ILabelProvider {

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
		 */
		@Override
		public void addListener(ILabelProviderListener listener) {
			
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
		 */
		@Override
		public void dispose() {
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
		 */
		@Override
		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
		 */
		@Override
		public void removeListener(ILabelProviderListener listener) {			
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
		 */
		@Override
		public Image getImage(Object element) {
			return null;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
		 */
		@Override
		public String getText(Object element) {
			RuleTree ruleTree = (RuleTree)element;
			return ruleTree.getLabel();
		}
	}
	
	/**
	 * Implements {@link ICheckStateListener} for the Tree Viewer.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 31/10/13
	 */
	class CheckStateListener implements ICheckStateListener {

		/**
		 * Sets the rule tree checked.
		 *
		 * @param ruleTree the rule tree
		 * @param state the state
		 */
		private void setRuleTreeChecked(RuleTree ruleTree, boolean state) {
			if(!ruleTree.hasChildren()) {
				Rule rule = ruleTree.getRule();
				prefStore.putBoolean(rule.getId(), state);
				return;
			}
			
			RuleTree[] children = ruleTree.getChildren();
			for(int i = -1; ++i < children.length;) {
				RuleTree child = children[i];
				setRuleTreeChecked(child, state);
			}
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ICheckStateListener#checkStateChanged(org.eclipse.jface.viewers.CheckStateChangedEvent)
		 */
		@Override
		public void checkStateChanged(CheckStateChangedEvent event) {
			CheckboxTreeViewer viewer = (CheckboxTreeViewer) event.getSource();
			
			boolean state = event.getChecked();
			
			// Update model
			RuleTree ruleTree = (RuleTree) event.getElement();
			setRuleTreeChecked(ruleTree, state);
			
			// Update view
			viewer.setSubtreeChecked(ruleTree, state);
			//viewer.update(ruleTree, null);
			viewer.refresh();
		}
		
	}
	
	
	/**
	 * Defines kinds of check states of a rule tree.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 31/10/13
	 */
	enum ERuleTreeState {
		/**
		 * Neither the rule tree leaf nor its children are checked.
		 */
		Non, 
		/**
		 * The rule tree has checked and unchecked children.
		 */
		PartialChecked, 
		/**
		 * The rule tree leaf and all of its children are checked.
		 */
		FullChecked;
	}
	
	/**
	 * Implements {@link ICheckStateProvider} for the Tree Viewer.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * @since 31/10/13
	 */
	class CheckStateProvider implements ICheckStateProvider {
		
		/**
		 * Returns the rule tree state. A rule tree with no children (leaf)
		 * is either 'ERuleTreeState.FullChecked' : 'ERuleTreeState.Non'. Rule trees 
		 * with children can additionally have the state 'ERuleTreeState.PartialChecked'.
		 *
		 * @param ruleTree the rule tree
		 * @return the rule tree state
		 */
		public ERuleTreeState getRuleTreeState(RuleTree ruleTree) {
			if(!ruleTree.hasChildren()) {
				Rule rule = ruleTree.getRule();			
				boolean isActive = rule.isDfault();
				if (validationRulesSet)
					isActive = prefStore.getBoolean(rule.getId(), isActive);
				
				return isActive ? ERuleTreeState.FullChecked : ERuleTreeState.Non;
			}
			
			ERuleTreeState result = ERuleTreeState.FullChecked;
			RuleTree[] children = ruleTree.getChildren();
			int uncheckedItemCount = 0;
			for(int i = -1; ++i < children.length;) {
				RuleTree child = children[i];
				ERuleTreeState childState = getRuleTreeState(child);
				
				if(childState != ERuleTreeState.FullChecked) {
					result = ERuleTreeState.PartialChecked;
					uncheckedItemCount++;
				}
			}
			
			if(uncheckedItemCount == children.length)
				result = ERuleTreeState.Non;
			
			return result;
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ICheckStateProvider#isChecked(java.lang.Object)
		 */
		@Override
		public boolean isChecked(Object element) {
			RuleTree ruleTree = (RuleTree)element;			
			ERuleTreeState state = getRuleTreeState(ruleTree);
			boolean checked = state != ERuleTreeState.Non;
			return checked;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.jface.viewers.ICheckStateProvider#isGrayed(java.lang.Object)
		 */
		@Override
		public boolean isGrayed(Object element) {
			RuleTree ruleTree = (RuleTree)element;
			ERuleTreeState state = getRuleTreeState(ruleTree);
			boolean grayed = state == ERuleTreeState.PartialChecked;
			return grayed;
		}	
	}
}

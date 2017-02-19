package edu.toronto.cs.openome.core.ui;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * Provides Eclipse-based preferences related to OpenOME.
 * 
 * @author <A HREF="mailto:johnson.thomas@mayo.edu">Thomas M Johnson </A>
 */
public class PrefPage extends PreferencePage implements IWorkbenchPreferencePage, SelectionListener, ModifyListener {
	public PrefPage() {
		super("OpenOME");
	}
	public void init(IWorkbench workbench) {
		setImageDescriptor(
			AbstractUIPlugin.imageDescriptorFromPlugin("core", "icons/protege.gif"));
	}
	protected void performApply() {
        storeValues();
		super.performApply();
	}
    private Hashtable<String, Button> buttons = new Hashtable<String, Button>();
    private Hashtable<String, Text> fields = new Hashtable<String, Text>();

    /**
     * Creates an new checkbox instance and sets the default
     * layout data.
     *
     * @param group  the composite in which to create the checkbox
     * @param label  the string to set into the checkbox
     * @return the new checkbox
     */
    private Button createCheckBox(Composite group, String label) {
        Button button = new Button(group, SWT.CHECK | SWT.LEFT);
        button.setText(label);
        button.addSelectionListener(this);
        GridData data = new GridData();
        button.setLayoutData(data);
        return button;
    }

    /**
     * Creates composite control and sets the default layout data.
     *
     * @param parent  the parent of the new composite
     * @param numColumns  the number of columns for the new composite
     * @return the newly-created coposite
     */
    private Composite createComposite(Composite parent, int numColumns) {
        Composite composite = new Composite(parent, SWT.NULL);

        //GridLayout
        GridLayout layout = new GridLayout();
        layout.numColumns = numColumns;
        composite.setLayout(layout);

        //GridData
        GridData data = new GridData();
        data.verticalAlignment = GridData.FILL;
        data.horizontalAlignment = GridData.FILL;
        composite.setLayoutData(data);
        return composite;
    }

    /** (non-Javadoc)
     * Method declared on PreferencePage
     */
    protected Control createContents(Composite parent) {
        // The following method is not supported in SDK 3.0.2 !
//    	PlatformUI.getWorkbench().getHelpSystem().setHelp(parent,
//				IConstants.PREFERENCE_PAGE_CONTEXT);
        //composite_textField << parent
        Composite composite_textField = createComposite(parent, 2);
        Composite composite_tab = createComposite(parent, 2);
//        createLabel(composite_tab, "Check_Box_Options");
        tabForward(composite_tab);
        Composite composite_checkBox = createComposite(composite_tab, 1);
        Properties ps = System.getProperties();        
        Button button;
        for (Enumeration i = ps.keys(); i.hasMoreElements(); ) {
        	String key = (String)i.nextElement();
        	if (!key.startsWith(IConstants.PREFIX))
        		continue;
        	String val = ps.getProperty(key);
        	if (val.equalsIgnoreCase("true") || val.equalsIgnoreCase("false")) {
        		button = createCheckBox(composite_checkBox, key.substring(IConstants.PREFIX.length()));
        		buttons.put(key, button);
        	} else {
                createLabel(composite_textField, key.substring(IConstants.PREFIX.length()));
                Text text = createTextField(composite_textField);
                fields.put(key, text);
        	}
        }
//        createPushButton(composite_textField, "Change");
        initializeValues();
        return new Composite(parent, SWT.NULL);
    }

    /**
     * Utility method that creates a label instance
     * and sets the default layout data.
     *
     * @param parent  the parent for the new label
     * @param text  the text for the new label
     * @return the new label
     */
    private Label createLabel(Composite parent, String text) {
        Label label = new Label(parent, SWT.LEFT);
        label.setText(text);
        GridData data = new GridData();
        data.horizontalSpan = 2;
        data.horizontalAlignment = GridData.FILL;
        label.setLayoutData(data);
        return label;
    }

    /**
     * Utility method that creates a push button instance
     * and sets the default layout data.
     *
     * @param parent  the parent for the new button
     * @param label  the label for the new button
     * @return the newly-created button
     */
    @SuppressWarnings("unused")
	private Button createPushButton(Composite parent, String label) {
        Button button = new Button(parent, SWT.PUSH);
        button.setText(label);
        button.addSelectionListener(this);
        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        button.setLayoutData(data);
        return button;
    }

    /**
     * Create a text field specific for this application
     *
     * @param parent  the parent of the new text field
     * @return the new text field
     */
    private Text createTextField(Composite parent) {
        Text text = new Text(parent, SWT.SINGLE | SWT.BORDER);
        text.addModifyListener(this);
        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        data.verticalAlignment = GridData.CENTER;
        data.grabExcessVerticalSpace = false;
        text.setLayoutData(data);
        return text;
    }

    /** 
     * The <code>ReadmePreferencePage</code> implementation of this
     * <code>PreferencePage</code> method 
     * returns preference store that belongs to the our plugin.
     * This is important because we want to store
     * our preferences separately from the desktop.
     */
    protected IPreferenceStore doGetPreferenceStore() {
        return Plugin.getPlugin().getPreferenceStore();
    }

    /**
     * Initializes states of the controls using default values
     * in the preference store.
     */
    private void initializeDefaults() {
    	performAction(0);
    }
    /**
     * Initializes states of the controls from the preference store.
     */
    private void initializeValues() {
    	performAction(1);
    }
    /**
     * Save the states of the controls into the preference store.
     */
    private void storeValues() {
    	performAction(-1);
    	performAction(2); // also save to system properties
    }
    /**
     * 
     * @param action
     *  0  -- default
     *  1  -- get
     *  -1 -- set
     *  2 -- apply
     */
    private void performAction(int action) {
        IPreferenceStore store = getPreferenceStore();
        Properties ps = System.getProperties();        
        Button button;
        Text text;
        for (Enumeration i = ps.keys(); i.hasMoreElements(); ) {
        	String key = (String)i.nextElement();
        	if (!key.startsWith(IConstants.PREFIX))
        		continue;
        	String val = ps.getProperty(key);
        	if (val.equalsIgnoreCase("true") || val.equalsIgnoreCase("false")) {
        		button = (Button) buttons.get(key);
        		store.setDefault(key, val.equalsIgnoreCase("true")?true:false);
        		switch(action) {
	        		case 0: button.setSelection(store.getDefaultBoolean(key)); break;
	        		case 1: button.setSelection(store.getBoolean(key)); break;
	        		case -1: store.setValue(key, button.getSelection()); break;
	        		case 2: System.setProperty(key, store.getBoolean(key)?"true" : "false");
        		}
        	} else {
        		store.setDefault(key, val);
        		text = (Text) fields.get(key);
        		switch(action) {                
	        		case 0: text.setText(store.getDefaultString(key)); break;
	        		case 1: text.setText(store.getString(key)); break;
	        		case -1: store.setValue(IConstants.PRE_TEXT, text.getText()); break;
	        		case 2: System.setProperty(key, store.getString(key));
        		}
        	}
        }    	
    }    

    public void modifyText(ModifyEvent event) {
    }

    protected void performDefaults() {
        super.performDefaults();
        initializeDefaults();
    }

    public boolean performOk() {
        storeValues();
        Plugin.getPlugin().savePluginPreferences();
        return true;
    }

    /**
     * Creates a tab of one horizontal spans.
     *
     * @param parent  the parent in which the tab should be created
     */
    private void tabForward(Composite parent) {
        Label vfiller = new Label(parent, SWT.LEFT);
        GridData gridData = new GridData();
        gridData = new GridData();
        gridData.horizontalAlignment = GridData.BEGINNING;
        gridData.grabExcessHorizontalSpace = false;
        gridData.verticalAlignment = GridData.CENTER;
        gridData.grabExcessVerticalSpace = false;
        vfiller.setLayoutData(gridData);
    }
    public void widgetDefaultSelected(SelectionEvent event) {
        //Handle a default selection. Do nothing in this example
    }
    public void widgetSelected(SelectionEvent event) {
        //Do nothing on selection in this example;
    }
}
package org.bflow.toolbox.hive.addons.preferences;

import org.apache.commons.lang3.StringUtils;
import org.bflow.toolbox.hive.addons.store.ConfigurationStore;
import org.bflow.toolbox.hive.nls.NLSupport;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Defines the add-ons preference main page.
 * 
 * @author Arian Storch
 * @since 13.04.10
 * @version 16.09.16 Removed obsolete NLS support
 */
public class MainPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
	 */
	@Override
	protected void createFieldEditors() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		Group group = new Group(composite, SWT.SHADOW_ETCHED_IN);

		group.setLayout(new GridLayout(1, false));
		group.setText(NLSupport.MainPage_GroupTitle);

		final Button btnSaveAllOpen = new Button(group, SWT.CHECK);
		btnSaveAllOpen.setText(NLSupport.MainPage_CheckBoxLabel);
		btnSaveAllOpen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean b = btnSaveAllOpen.getSelection();
				ConfigurationStore.getInstance().put(ConfigurationStore.ID_SAVE_ALL_OPEN_DIAGRAMS, StringUtils.EMPTY + b);
			}
		});

		String strB = ConfigurationStore.getInstance().get(ConfigurationStore.ID_SAVE_ALL_OPEN_DIAGRAMS);

		boolean b = Boolean.parseBoolean((strB == null ? StringUtils.EMPTY : strB));

		btnSaveAllOpen.setSelection(b);

		return composite;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#performOk()
	 */
	@Override
	public boolean performOk() {
		ConfigurationStore.getInstance().save();
		return super.performOk();
	}

}

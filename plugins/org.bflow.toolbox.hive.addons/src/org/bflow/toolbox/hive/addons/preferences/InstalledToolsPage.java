package org.bflow.toolbox.hive.addons.preferences;

import org.bflow.toolbox.hive.addons.preferences.dialogs.EditToolDialog;
import org.bflow.toolbox.hive.addons.store.ToolStore;
import org.bflow.toolbox.hive.addons.utils.ToolDescriptor;
import org.bflow.toolbox.hive.nls.NLUtil;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Defines the preference page for the mitamm installed tools page.
 * 
 * @author Arian Storch
 * @since 13/04/10
 * @version 24/09/10
 */
public class InstalledToolsPage extends
		FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	
	private static InstalledToolsPage instance;
	
	/**
	 * Constructor.
	 */
	public InstalledToolsPage() {
		instance = this;
	}
	
	

	@Override
	public boolean performOk() {
		performApply();

		return true;
	}

	@Override
	protected void performApply() {
		ToolStore.save();
	}

	@Override
	protected void createFieldEditors() {
	}

	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected Control createContents(Composite parent) {
		final Composite composite = new Composite(parent, SWT.WRAP);

		composite.setLayout(new GridLayout(1, false));

		Label lblText = new Label(composite, SWT.NONE);
		lblText.setText(NLUtil
				.getMessage("MitammPreferencesInstalledToolsPage#msg1"));

		Composite panel = new Composite(composite, SWT.NONE);
		panel.setLayout(new GridLayout(3, false));

		Button btnAdd = new Button(panel, SWT.NONE);
		btnAdd.setText(NLUtil
				.getMessage("MitammPreferencesInstalledToolsPage#msg2"));

		btnAdd.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				EditToolDialog eDlg = new EditToolDialog(composite.getShell());

				String value[] = null;

				if ((value = eDlg.open()) != null) {
					TableItem item = new TableItem(table, SWT.NONE);
					item.setText(new String[] { value[0], value[1], value[2] });

					ToolDescriptor td = new ToolDescriptor(value[0], value[1],
							value[2]);
					ToolStore.installTool(td);
					item.setData(td);
				}

			}
		});

		Button btnRemove = new Button(panel, SWT.NONE);
		btnRemove.setText(NLUtil
				.getMessage("MitammPreferencesInstalledToolsPage#msg3"));

		btnRemove.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (isEditable(table.getSelectionIndex())) {
					TableItem item = table.getItem(table.getSelectionIndex());
					ToolStore.removeTool(item.getText(0));
					item.dispose();
				}

			}
		});

		Button btnEdit = new Button(panel, SWT.NONE);
		btnEdit.setText(NLUtil
				.getMessage("MitammPreferencesInstalledToolsPage#msg4"));
		btnEdit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int sel = table.getSelectionIndex();

				if (sel > -1) {
					EditToolDialog eDlg = new EditToolDialog(composite
							.getShell());

					eDlg.setChangable(isEditable(sel));

					TableItem item = table.getItem(sel);

					String oldName = item.getText(0);

					eDlg.setInput(new String[] { item.getText(0),
							item.getText(1), item.getText(2) });

					String value[] = null;

					if ((value = eDlg.open()) != null) {
						item.setText(value);

						ToolStore.removeTool(oldName);
						ToolStore.installTool(new ToolDescriptor(value[0],
								value[1], value[2]));
					}
				}

			}
		});

		table = new Table(composite, SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		GridData tableData = new GridData(SWT.FILL, SWT.FILL, true, true);
		tableData.heightHint = 200;
		tableData.widthHint = 600;
		table.setLayoutData(tableData);

		TableColumn colToolName = new TableColumn(table, SWT.NONE);
		colToolName.setText("Name");
		colToolName.setWidth(80);

		TableColumn colToolPath = new TableColumn(table, SWT.NONE);
		colToolPath.setText("Install path");
		colToolPath.setWidth(300);

		TableColumn colToolParam = new TableColumn(table, SWT.NONE);
		colToolParam.setText("Parameter");
		colToolParam.setWidth(220);

		for (ToolDescriptor descriptor : ToolStore.getInstalledTools()) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(new String[] { descriptor.getName(),
					descriptor.getPath(), descriptor.getParam() });

			item.setData(descriptor);
		}

		return parent;
	}

	private boolean isEditable(int selIndex) {
		TableItem item = table.getItem(selIndex);

		String name = item.getText(0);

		if (name.equalsIgnoreCase("swi-prolog")
				|| name.equalsIgnoreCase("epctools"))
			return false;

		return true;
	}
	
	/**
	 * Inserts a tool descriptor into the table.
	 * @param td tool descriptor
	 */
	public void insert(ToolDescriptor td) {
		TableItem item = new TableItem(table, SWT.NONE);
		
		item.setText(new String[] {td.getName(), td.getPath(), td.getParam()});
		item.setData(td);
	}
	
	/**
	 * Returns the instance of this page.
	 * @return instance of this page
	 */
	public static InstalledToolsPage getInstance() {
		return instance;
	}

	// layout components
	private Table table;

}

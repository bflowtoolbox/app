package org.bflow.toolbox.hive.statement.dialogs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class StatementDialog extends Dialog {

	private List<String> templates;
	private String selectedTemplate;
	private Combo combo;

	public StatementDialog(Shell parentShell, String selectedTemplate) {
		super(parentShell);
		templates = getStatmentTemplatesFromWorkspace();
		this.selectedTemplate = selectedTemplate;
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {

		Composite container = (Composite) super.createDialogArea(parent);

		Label label = new Label(container, SWT.LEFT);
		label.setText("Wähle das zu verwendende Template:");
		combo = new Combo(container, SWT.DROP_DOWN);
		String[] templatesArray = templates.toArray(new String[templates.size()]);
		combo.setItems(templatesArray);
		combo.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				selectedTemplate = combo.getItem(combo.getSelectionIndex());
				getButton(IDialogConstants.OK_ID).setEnabled(true);
			}
		});
		if (selectedTemplate != null) {
			int i = 0;
			for (String temp : combo.getItems()) {
				if (temp.equals(selectedTemplate)) {
					combo.select(i);
					break;
				}
			}
		}
		return container;
	}

	@Override
	protected Control createContents(Composite parent) {
		super.createContents(parent);
		if (combo.getSelectionIndex() == -1) {
			getButton(IDialogConstants.OK_ID).setEnabled(false);
		}
		return parent;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Statment Creation");
	}

	public String getSelectedTemplate() {
		return selectedTemplate;
	}
	
	private List<String> getStatmentTemplatesFromWorkspace() {
		ArrayList<String> temps = new ArrayList<>();
		
		IPath rootPath = ResourcesPlugin.getWorkspace().getRoot().getRawLocation();
		rootPath = rootPath.append(".properties/templates.txt");
		File templateFile = rootPath.toFile();
		if (templateFile.isFile() && templateFile.canRead()) {
			BufferedReader in = null;
	        try {
	            in = new BufferedReader(new FileReader(templateFile));
	            String temp = null;
	            while ((temp = in.readLine()) != null) {
	            	temps.add(temp);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (in != null)
					try {
						in.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	        } 
		}
		return temps;
	}
}

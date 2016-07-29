package org.bflow.toolbox.export.visio.oepc.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

/**
 * @author Christian Boehme
 * 
 */

public class OutputFileSelectionWizardPage extends WizardPage implements
		Listener {

	private Composite composite;
	private Text textTarget;

	public OutputFileSelectionWizardPage(String pageName) {
		super(pageName);
		setTitle("Visio Export Wizard");
		setDescription("Select your destination file!");
		setPageComplete(false);
	}

	public void createControl(Composite parent) {
		composite = new Composite(parent, SWT.NONE);
		this.setControl(composite);
		composite.setLayout(new FormLayout());

		Label lbFileSystem = new Label(composite, SWT.LEFT);
		lbFileSystem
				.setText("Select your destination folder from above or select a destination in your file system.");
		{
			FormData formData = new FormData();
			formData.left = new FormAttachment(0, 10);
			formData.top = new FormAttachment(0, 10);
			lbFileSystem.setLayoutData(formData);
		}

		textTarget = new Text(composite, SWT.SINGLE | SWT.BORDER);
		{
			FormData formData = new FormData();
			formData.left = new FormAttachment(0, 10);
			formData.top = new FormAttachment(lbFileSystem, 10);
			formData.right = new FormAttachment(100, -80);
			textTarget.setLayoutData(formData);
		}

		textTarget.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				validatePage();
			}
		});

		Button btBrowseFolder = new Button(composite, SWT.PUSH);
		btBrowseFolder.setText("Browse...");
		btBrowseFolder.setEnabled(true);
		{
			FormData formData = new FormData();
			formData.left = new FormAttachment(textTarget, 10);
			formData.top = new FormAttachment(lbFileSystem, 8);
			btBrowseFolder.setLayoutData(formData);
		}

		btBrowseFolder.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(composite.getShell(), SWT.SAVE);
				fd.setFilterExtensions(new String[] { "*.vsd" });
				fd.setText("Select target File!");
				textTarget.setText(fd.open());
			}
		});

	}

	public void handleEvent(Event event) {
		validatePage();
	}

	public void validatePage() {
		if (textTarget.getText().isEmpty()) {
			setPageComplete(false);
		} else
			setPageComplete(true);
	}

	public String getTargetPath(){
		return textTarget.getText();
	}
	
}

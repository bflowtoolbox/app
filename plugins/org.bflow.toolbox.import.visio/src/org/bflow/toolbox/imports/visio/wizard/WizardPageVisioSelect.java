package org.bflow.toolbox.imports.visio.wizard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;

import org.bflow.toolbox.imports.visio.VisioActivator;
import org.bflow.toolbox.imports.visio.runner.AbstractVisioWorkflowRunner;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Christian Boehme, Arian Storch<arian.storch@bflow.org>
 * @version 2019-01-21 AST Fixed URL encoding of whitespaces
 * 
 */

public class WizardPageVisioSelect extends WizardPage {

	private Composite composite;

	private Text visioSourceFileText;
	private Button visioDownloadButton;

	private File visioStencilFile;
	public File visioDocumentFile;

	private  String stencilPath;
	private String stencilFileName;
	
	protected VisioActivator activator;

	public WizardPageVisioSelect(String pageName, AbstractVisioWorkflowRunner wf, String newStencilPath, String description, String visioStencilFileName, VisioActivator ac) {
		super(pageName);
		setTitle("Visio Import Wizard");
		setDescription(description);
		stencilPath = newStencilPath;
		stencilFileName = visioStencilFileName;
		activator = ac;
		setPageComplete(false);
	}

	@Override
	public void createControl(Composite parent) {

		composite = new Composite(parent, SWT.NONE);
		this.setControl(composite);
		composite.setLayout(new FormLayout());

		Label visioSourceFileLabel = new Label(composite, SWT.LEFT);
		visioSourceFileLabel.setText("Select your Visio file:");
		{
			FormData formData = new FormData();
			formData.left = new FormAttachment(0, 10);
			formData.top = new FormAttachment(0, 15);
			visioSourceFileLabel.setLayoutData(formData);
		}

		visioSourceFileText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		{
			FormData formData = new FormData();
			formData.left = new FormAttachment(0, 10);
			formData.top = new FormAttachment(visioSourceFileLabel, 10);
			formData.right = new FormAttachment(100, -80);
			visioSourceFileText.setLayoutData(formData);
		}

		Button browseVisioFileButton = new Button(composite, SWT.PUSH);
		browseVisioFileButton.setEnabled(true);
		browseVisioFileButton.setText("Browse...");
		{
			FormData formData = new FormData();
			formData.top = new FormAttachment(visioSourceFileLabel, 7);
			formData.right = new FormAttachment(100, -5);
			browseVisioFileButton.setLayoutData(formData);
		}

		visioDownloadButton = new Button(composite, SWT.PUSH);
		visioDownloadButton.setText("Save Visio Stencil...");
		{
			FormData formData = new FormData();
			formData.bottom = new FormAttachment(100, 0);
			formData.right = new FormAttachment(100, -5);
			visioDownloadButton.setLayoutData(formData);
		}

		Label visioAnnotationLabel = new Label(composite, SWT.LEFT);
		visioAnnotationLabel.setText("Please model your Visio document with the standard stencil.");
		{
			FormData formData = new FormData();
			formData.left = new FormAttachment(0, 10);
			formData.right = new FormAttachment(100, 0);
			formData.bottom = new FormAttachment(100, 0);
			visioAnnotationLabel.setLayoutData(formData);
		}
		
		try {
			URL url = FileLocator.find(VisioActivator.getDefault().getBundle(), new Path(stencilPath), null);
			URL stencilURL = FileLocator.toFileURL(url);
			
			// Fix whitespaces
			String urlStr = stencilURL.toString();
			String fixedUrlStr = urlStr.replace(" ", "%20");
			URL fixedStencilUrl = new URL(fixedUrlStr);
			
			visioStencilFile = new File(fixedStencilUrl.toURI());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// browse File-Event
		browseVisioFileButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(composite.getShell(), SWT.OPEN);
				dialog.setFilterExtensions(new String[] { "*.vsd" });
				visioSourceFileText.setText(dialog.open());
			}
		});

		// Save Visio Stencil Event
		visioDownloadButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dd = new DirectoryDialog(composite.getShell(), SWT.OPEN);
				dd.setMessage("Select your destination folder:");
				String stencilCopyPath = dd.open();
				if (stencilCopyPath.isEmpty()) return;
				
				try {
					File copyOfVisioStencilFile = new File(stencilCopyPath + "/" + stencilFileName);
					// copy file using filechannels
					
					try (FileInputStream fis = new FileInputStream(visioStencilFile)) {
						try (FileChannel inChannel = fis.getChannel()) {
							
							try(FileOutputStream fos = new FileOutputStream(copyOfVisioStencilFile)) {
								try (FileChannel outChannel = fos.getChannel()) {
									inChannel.transferTo(0, inChannel.size(), outChannel);
								}
							}
							
						}
					}						
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		// listener, ueberwacht das Textfeld
		visioSourceFileText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				checkPageComplete();
			}

		});

	}

	// ueberprueft existenz der eingegebenen files und gibt ggf validate-button
	// frei
	private void checkPageComplete() {
		if (visioSourceFileText.getText().isEmpty()) {
			setMessage("Please select your Visio document!");
			setPageComplete(false);
		} else {
			File visioFile = new File(visioSourceFileText.getText()
					.replace("\\", "/"));
			if (visioFile.exists() && visioFile.isFile()) {
				setPageComplete(true);
				setMessage("Please continue!", SWT.ERROR);
				visioDocumentFile = visioFile;
			} else {
				setMessage("Your Visio document does not exist!", SWT.ERROR);
				setPageComplete(false);
			}
		}
	}

	public File getVisioDocumentFile() {
		return visioDocumentFile;
	}
	
	

}

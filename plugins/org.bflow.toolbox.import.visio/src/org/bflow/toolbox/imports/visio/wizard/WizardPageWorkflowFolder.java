package org.bflow.toolbox.imports.visio.wizard;

import java.util.Iterator;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

/**
 * @author Christian Boehme
 * 
 */

public class WizardPageWorkflowFolder extends WizardPage implements Listener {

	private Composite composite;

	private ContainerSelectionGroup containerGroup;
	private IStructuredSelection selection;

	public WizardPageWorkflowFolder(String pageName,
			IStructuredSelection iSelection) {
		super(pageName);
		selection = iSelection;
		setTitle("Visio Import Wizard");
		setDescription("Select your destination folder.");
		setPageComplete(false);
	}

	public void createControl(Composite parent) {
		composite = new Composite(parent, SWT.NONE);
		this.setControl(composite);
		composite.setLayout(new FormLayout());

		Button btBrowseFolder = new Button(composite, SWT.PUSH);
		btBrowseFolder.setText("Browse folder...");
		btBrowseFolder.setEnabled(true);
		{
			FormData formData = new FormData();
			formData.left = new FormAttachment(0, 10);
			formData.bottom = new FormAttachment(100, -10);
			btBrowseFolder.setLayoutData(formData);
		}

		Label lbFileSystem = new Label(composite, SWT.LEFT);
		lbFileSystem
				.setText("Select your destination folder from above or select a destination in your file system.");
		{
			FormData formData = new FormData();
			formData.left = new FormAttachment(0, 10);
			formData.bottom = new FormAttachment(btBrowseFolder, -10);
			lbFileSystem.setLayoutData(formData);
		}

		containerGroup = new ContainerSelectionGroup(composite, this, true);
		{
			FormData formData = new FormData();
			formData.left = new FormAttachment(0, 10);
			formData.top = new FormAttachment(0, 10);
			formData.right = new FormAttachment(100, -10);
			formData.bottom = new FormAttachment(lbFileSystem, -10);
			containerGroup.setLayoutData(formData);
		}

		setPageComplete(setSelection());

		btBrowseFolder.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dd = new DirectoryDialog(composite.getShell(),
						SWT.OPEN);
				dd.setMessage("Select your destination folder:");
				containerGroup.setContainerPath(dd.open());
				containerGroup.setSelectedContainer(null);
			}

		});

	}

	private boolean setSelection() {
		boolean hasSelection = false;
		Iterator it = selection.iterator();
		if (it.hasNext()) {
			Object object = it.next();
			IResource selectedResource = null;
			if (object instanceof IResource) {
				selectedResource = (IResource) object;
			} else if (object instanceof IAdaptable) {
				selectedResource = (IResource) ((IAdaptable) object)
						.getAdapter(IResource.class);
			}
			if (selectedResource != null) {
				if (selectedResource.getType() == IResource.FILE) {
					selectedResource = selectedResource.getParent();
				}
				if (selectedResource.isAccessible()) {
					containerGroup.setContainerPath(selectedResource
							.getFullPath().toOSString());
					containerGroup
							.setSelectedContainer((IContainer) selectedResource);
					return true;
				}
			}
		}
		return hasSelection;

	}

	public void handleEvent(Event event) {
		setPageComplete(true);
	}

	public String getTargetPath() {
		return containerGroup.getTargetPath();
	}

}

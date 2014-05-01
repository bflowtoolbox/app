package org.bflow.toolbox.export.visio.oepc.wizard;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.osgi.util.TextProcessor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.DrillDownComposite;

/**
 * @author Christian Boehme
 * 
 */

public class OepcModelSelectionWizardPage extends WizardPage implements
		Listener {

	private Composite composite;

	private File oepcModelFile;

	private IStructuredSelection selection;

	private ModelElementsTreeContentProvider modelElementsTreeContentProvider;

	private Text sourceNameField;

	private TreeViewer treeViewer;

	protected OepcModelSelectionWizardPage(String pagename,
			IStructuredSelection currentSelection) {
		super(pagename);
		this.selection = currentSelection;
		setDescription("Select the oepc model file you want to export!");
		this.setTitle(pagename);
	}

	public void createControl(Composite parent) {
		initializeDialogUnits(parent);

		composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		composite.setFont(composite.getFont());

		// resource Group
		// ----------------

		Label sourceLabel = new Label(composite, SWT.WRAP);
		sourceLabel.setText("Select Oepc file:");
		sourceLabel.setFont(this.getFont());

		// -------- TreeViewer
		modelElementsTreeContentProvider = new ModelElementsTreeContentProvider();
		createTreeViewer();

		// -----------
		sourceNameField = new Text(composite, SWT.SINGLE | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 250;
		sourceNameField.setLayoutData(gd);
		sourceNameField.addListener(SWT.Modify, this);
		sourceNameField.setFont(this.getFont());

		// -----------------
		setPageComplete(setSelection());
		setErrorMessage(null);
		setMessage(null);
		this.setControl(composite);

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
				if (selectedResource.isAccessible()) {
					this.setContainerPath(selectedResource.getFullPath()
							.toOSString());
					this.setSelectedContainer(selectedResource);
					return true;
				}
			}
		}
		return hasSelection;

	}

	public void setSelectedContainer(IResource resource) {
		// expand to and select the specified container

		List itemsToExpand = new ArrayList();
		IContainer parent = resource.getParent();
		while (parent != null) {
			itemsToExpand.add(0, parent);
			parent = parent.getParent();
		}

		treeViewer.setExpandedElements(itemsToExpand.toArray());
		treeViewer.setSelection(new StructuredSelection(resource), true);
	}

	public String getContainerPath() {
		return sourceNameField.getText();
	}

	public void setContainerPath(String path) {
		sourceNameField.setText(path);
	}

	public File getOepcModelFile() {
		return oepcModelFile;
	}

	public boolean validatePage() {
		if (!sourceNameField.getText().isEmpty()) {
			IPath rootPath = ResourcesPlugin.getWorkspace().getRoot()
					.getLocation();
			oepcModelFile = new File(rootPath + "/" + sourceNameField.getText());
			if (oepcModelFile.exists()) {
				if (oepcModelFile.isFile()) {
					setMessage("");
					return true;
				}
				setMessage("Please select a file!");
				return false;
			} else {
				setMessage("File does not exist!");
				return false;
			}
		}
		setMessage("Please select a file!");
		return false;

	}

	public void handleEvent(Event event) {
		setPageComplete(validatePage());

	}

	protected void createTreeViewer() {

		// Create drill down.
		DrillDownComposite drillDown = new DrillDownComposite(composite,
				SWT.BORDER);
		GridData spec = new GridData(SWT.FILL, SWT.FILL, true, true);
		spec.widthHint = 320;
		spec.heightHint = 250;
		drillDown.setLayoutData(spec);

		// Create tree viewer inside drill down.
		treeViewer = new TreeViewer(drillDown, SWT.NONE);
		drillDown.setChildTree(treeViewer);

		treeViewer.setContentProvider(modelElementsTreeContentProvider);
		treeViewer.setLabelProvider(WorkbenchLabelProvider
				.getDecoratingWorkbenchLabelProvider());
		treeViewer.setComparator(new ViewerComparator());
		treeViewer.setUseHashlookup(true);

		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event
						.getSelection();
				containerSelectionChanged((IResource) selection
						.getFirstElement());
			}
		});
		// expand on DoubleClick
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				ISelection selection = event.getSelection();
				if (selection instanceof IStructuredSelection) {
					Object item = ((IStructuredSelection) selection)
							.getFirstElement();
					if (item == null) {
						return;
					}
					if (treeViewer.getExpandedState(item)) {
						treeViewer.collapseToLevel(item, 1);
					} else {
						treeViewer.expandToLevel(item, 1);
					}
				}
			}
		});

		// This has to be done after the viewer has been laid out
		treeViewer.setInput(ResourcesPlugin.getWorkspace());
	}

	private class ModelElementsTreeContentProvider implements
			ITreeContentProvider {

		public Object[] getChildren(Object element) {

			// root Element
			if (element instanceof IWorkspace) {

				// only return opened projects
				IProject[] allProjects = ((IWorkspace) element).getRoot()
						.getProjects();
				ArrayList<IProject> accessibleProjects = new ArrayList<IProject>();
				for (int i = 0; i < allProjects.length; i++) {
					if (allProjects[i].isOpen()) {
						accessibleProjects.add(allProjects[i]);
					}
				}
				return accessibleProjects.toArray();
			}

			// Directories
			if (element instanceof IContainer) {
				IContainer container = (IContainer) element;
				if (container.isAccessible()) {
					try {
						List<IResource> children = new ArrayList<IResource>();
						IResource[] members = container.members();
						for (int i = 0; i < members.length; i++) {
							if (members[i].getType() == IResource.FILE) {

								if (isValidModelFile((IFile) (members[i]))) {
									children.add(members[i]);
								}
							} else
								children.add(members[i]);
						}
						return children.toArray();
					} catch (CoreException e) {
					}
				}
			}
			return new Object[0];
		}

		public Object getParent(Object element) {
			if (element instanceof IResource) {
				return ((IResource) element).getParent();
			}
			return null;
		}

		public boolean hasChildren(Object element) {
			return getChildren(element).length > 0;
		}

		private boolean isValidModelFile(IFile file) {
			String fileExtension = file.getFullPath().getFileExtension();
			return "oepc".equals(fileExtension);
		}

		public Object[] getElements(Object element) {
			return getChildren(element);
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

	}

	public void containerSelectionChanged(IResource resource) {

		if (resource == null) {
			sourceNameField.setText("");//$NON-NLS-1$
		} else {
			String text = TextProcessor.process(resource.getFullPath()
					.makeRelative().toString());
			sourceNameField.setText(text);
			sourceNameField.setToolTipText(text);
		}

		// fire an event so the parent can update its controls
		Event changeEvent = new Event();
		changeEvent.type = SWT.Selection;
		changeEvent.widget = composite;
		this.handleEvent(changeEvent);
	}

}

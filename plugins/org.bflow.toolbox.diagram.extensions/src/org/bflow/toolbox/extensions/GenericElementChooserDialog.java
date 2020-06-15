package org.bflow.toolbox.extensions;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.GMFToolingRuntimePlugin;
import org.eclipse.gmf.tooling.runtime.part.DefaultElementChooserDialog;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.model.WorkbenchLabelProvider;

/**
 * Implements a generic element chooser dialog. The implementation is heavily
 * based on the GMF DefaultElementChooserDialog, but is extended to get more
 * control of the OK button enablement.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2019-01-27
 * @see DefaultElementChooserDialog
 */
public class GenericElementChooserDialog extends Dialog {
	
	public interface Context {
		public AdapterFactory getAdapterFactory();
		public PreferencesHint getPreferenceHint();
		public String[] getFileExtesions();
		public String getDialogTitle();
		public ITreeContentProvider getTreeContentProvider();
		public boolean allowMultiSelection();
	}

	private final TransactionalEditingDomain _editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();
	private final Context _context;
	private final View _view;
	private List<URI> mySelectedModelElementURIs;
	private TreeViewer _treeViewer;
	
	/**
	 * Initializes the instance with the specified arguments.
	 * 
	 * @param parentShell Parent shell
	 * @param view        View that is associated with this dialog
	 * @param context     Dialog context
	 */
	public GenericElementChooserDialog(Shell parentShell, View view, Context context) {
		super(parentShell);
		setShellStyle(getShellStyle() | SWT.RESIZE);
		_context = context;
		_view = view;
	}
	
	public URI getSelectedModelElementURI() {
		return getSelectedModelElementURIs().size() > 0 ? getSelectedModelElementURIs().get(0) : null;
	}

	public List<URI> getSelectedModelElementURIs() {
		if (mySelectedModelElementURIs == null) {
			mySelectedModelElementURIs = new LinkedList<URI>();
		}
		return mySelectedModelElementURIs;
	}

	@Override
	public int open() {
		int result = super.open();
		for (Resource resource : _editingDomain.getResourceSet().getResources()) {
			resource.unload();
		}
		_editingDomain.dispose();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.tooling.runtime.part.DefaultElementChooserDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		// Important note: the behavior of the base class is fully overridden at this point!
		
		Composite composite = (Composite) super.createDialogArea(parent);
		getShell().setText(_context.getDialogTitle());
		createModelBrowser(composite); // This method is private in base class
		return composite;
	}

	protected void createModelBrowser(Composite composite) {
		// Copied from https://github.com/eclipse/gmf-tooling/blob/master/plugins/org.eclipse.gmf.tooling.runtime/src/org/eclipse/gmf/tooling/runtime/part/DefaultElementChooserDialog.java
		_treeViewer = new TreeViewer(composite, _context.allowMultiSelection() ? SWT.MULTI : SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		GridData layoutData = new GridData(GridData.FILL_BOTH);
		layoutData.heightHint = 300;
		layoutData.widthHint = 300;
		_treeViewer.getTree().setLayoutData(layoutData);
		_treeViewer.setContentProvider(new ModelElementsTreeContentProvider(_context.getTreeContentProvider(), _context.getAdapterFactory()));
		_treeViewer.setLabelProvider(new ModelElementsTreeLabelProvider(_context.getAdapterFactory()));
		_treeViewer.setInput(ResourcesPlugin.getWorkspace().getRoot());
		_treeViewer.addFilter(new ModelFilesFilter());
		
		// Important modification
		_treeViewer.addSelectionChangedListener(new OkButtonEnabler());
	}
	
	protected void setOkButtonEnabled(boolean enabled) {
		getButton(IDialogConstants.OK_ID).setEnabled(enabled);
	}
	
	protected boolean isValidModelFile(IFile file) {
		String fileExtension = file.getFullPath().getFileExtension();
		for (int i = 0; i < _context.getFileExtesions().length; i++) {
			String currentExtension = _context.getFileExtesions()[i];
			if (currentExtension != null && currentExtension.equals(fileExtension)) {
				return true;
			}
		}
		return false; //$NON-NLS-1$
	}
	
	// See original source https://github.com/eclipse/gmf-tooling/blob/master/plugins/org.eclipse.gmf.tooling.runtime/src/org/eclipse/gmf/tooling/runtime/part/DefaultElementChooserDialog.java
	private class ModelElementsTreeContentProvider implements ITreeContentProvider {

		private final ITreeContentProvider myWorkbenchContentProvider;
		private final AdapterFactoryContentProvider myAdapterFactoryContentProvier;

		private ModelElementsTreeContentProvider(ITreeContentProvider iTreeContentProvider, AdapterFactory adapterFactory) {
			myWorkbenchContentProvider = iTreeContentProvider;
			myAdapterFactoryContentProvier = new AdapterFactoryContentProvider(adapterFactory);
		}

		public Object[] getChildren(Object parentElement) {
			Object[] result = myWorkbenchContentProvider.getChildren(parentElement);
			if (result != null && result.length > 0) {
				return result;
			}
			if (parentElement instanceof IFile) {
				IFile modelFile = (IFile) parentElement;
				IPath resourcePath = modelFile.getFullPath();
				ResourceSet resourceSet = _editingDomain.getResourceSet();
				try {
					Resource modelResource = resourceSet.getResource(URI.createPlatformResourceURI(resourcePath.toString(), true), true);
					return myAdapterFactoryContentProvier.getChildren(modelResource);
				} catch (WrappedException e) {
					GMFToolingRuntimePlugin.getInstance().getLog().log(new Status(IStatus.ERROR, GMFToolingRuntimePlugin.ID, //
							"Unable to load resource: " + resourcePath.toString(), e)); //$NON-NLS-1$
				}
				return Collections.EMPTY_LIST.toArray();
			}
			return myAdapterFactoryContentProvier.getChildren(parentElement);
		}

		public Object getParent(Object element) {
			Object parent = myWorkbenchContentProvider.getParent(element);
			if (parent != null) {
				return parent;
			}
			if (element instanceof EObject) {
				EObject eObject = (EObject) element;
				if (eObject.eContainer() == null && eObject.eResource().getURI().isFile()) {
					String path = eObject.eResource().getURI().path();
					return ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path(path));
				}
				return myAdapterFactoryContentProvier.getParent(eObject);
			}
			return null;
		}

		public boolean hasChildren(Object element) {
			if (element instanceof IFile) {
				return isValidModelFile((IFile) element);
			}
			return myWorkbenchContentProvider.hasChildren(element) || myAdapterFactoryContentProvier.hasChildren(element);
		}

		public Object[] getElements(Object inputElement) {
			Object[] elements = myWorkbenchContentProvider.getElements(inputElement);
			return elements;
		}

		public void dispose() {
			myWorkbenchContentProvider.dispose();
			myAdapterFactoryContentProvier.dispose();
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			myWorkbenchContentProvider.inputChanged(viewer, oldInput, newInput);
			myAdapterFactoryContentProvier.inputChanged(viewer, oldInput, newInput);
		}
	}

	// See original source https://github.com/eclipse/gmf-tooling/blob/master/plugins/org.eclipse.gmf.tooling.runtime/src/org/eclipse/gmf/tooling/runtime/part/DefaultElementChooserDialog.java
	private class ModelElementsTreeLabelProvider implements ILabelProvider {

		private final WorkbenchLabelProvider myWorkbenchLabelProvider;
		private final AdapterFactoryLabelProvider myAdapterFactoryLabelProvider;

		private ModelElementsTreeLabelProvider(AdapterFactory adapterFactory) {
			myWorkbenchLabelProvider = new WorkbenchLabelProvider();
			myAdapterFactoryLabelProvider = new AdapterFactoryLabelProvider(adapterFactory);
		}

		public Image getImage(Object element) {
			Image result = myWorkbenchLabelProvider.getImage(element);
			return result != null ? result : myAdapterFactoryLabelProvider.getImage(element);
		}

		public String getText(Object element) {
			String result = myWorkbenchLabelProvider.getText(element);
			return result != null && result.length() > 0 ? result : myAdapterFactoryLabelProvider.getText(element);
		}

		public void addListener(ILabelProviderListener listener) {
			myWorkbenchLabelProvider.addListener(listener);
			myAdapterFactoryLabelProvider.addListener(listener);
		}

		public void dispose() {
			myWorkbenchLabelProvider.dispose();
			myAdapterFactoryLabelProvider.dispose();
		}

		public boolean isLabelProperty(Object element, String property) {
			return myWorkbenchLabelProvider.isLabelProperty(element, property) || myAdapterFactoryLabelProvider.isLabelProperty(element, property);
		}

		public void removeListener(ILabelProviderListener listener) {
			myWorkbenchLabelProvider.removeListener(listener);
			myAdapterFactoryLabelProvider.removeListener(listener);
		}
	}
	
	// See original source https://github.com/eclipse/gmf-tooling/blob/master/plugins/org.eclipse.gmf.tooling.runtime/src/org/eclipse/gmf/tooling/runtime/part/DefaultElementChooserDialog.java
	private class ModelFilesFilter extends ViewerFilter {

		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (element instanceof IContainer) {
				return true;
			}
			if (element instanceof IFile) {
				IFile file = (IFile) element;
				return isValidModelFile(file);
			}
			return true;
		}
	}
	
	// See original source https://github.com/eclipse/gmf-tooling/blob/master/plugins/org.eclipse.gmf.tooling.runtime/src/org/eclipse/gmf/tooling/runtime/part/DefaultElementChooserDialog.java
	private class OkButtonEnabler implements ISelectionChangedListener {

		private final PreferencesHint myPreferenceHint;

		private OkButtonEnabler() {
			myPreferenceHint = _context.getPreferenceHint();
		}

		public void selectionChanged(SelectionChangedEvent event) {
			if (event.getSelection() instanceof IStructuredSelection) {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				getSelectedModelElementURIs().clear();
				boolean isAllOk = true;
				for (Object selectedElement : selection.toList()) {
					URI elementURI = getElementURI(selectedElement);
					if (elementURI != null) {
						getSelectedModelElementURIs().add(elementURI);
					} else {
						getSelectedModelElementURIs().clear();
						isAllOk = false;
						break;
					}
				}
				setOkButtonEnabled(isAllOk);
			}
		}

		private URI getElementURI(Object selectedElement) {
			URI result = null;
			if (selectedElement instanceof IWrapperItemProvider) {
				selectedElement = ((IWrapperItemProvider) selectedElement).getValue();
			}
			if (selectedElement instanceof FeatureMap.Entry) {
				selectedElement = ((FeatureMap.Entry) selectedElement).getValue();
			}
			if (selectedElement instanceof EObject) {
				EObject selectedModelElement = (EObject) selectedElement;
				if (ViewService.getInstance().provides(Node.class, new EObjectAdapter(selectedModelElement), _view, null, ViewUtil.APPEND, true, myPreferenceHint)) {
					result = EcoreUtil.getURI(selectedModelElement);
				} else {
					result = EcoreUtil.getURI(selectedModelElement); // Graphiti / BPMN2 
				}
			}
			
			// Modification
			if (selectedElement instanceof IFile) {
				Object[] children = ((ITreeContentProvider)_treeViewer.getContentProvider()).getChildren(selectedElement);
				for (int i = -1; ++i != children.length;) {
					Object child = children[i];
					if (child instanceof Diagram) { // GMF
						result = getElementURI(child);
					} else if (child instanceof EObject) { // Graphiti / BPMN2
						result = getElementURI(child);
					}
				}
			}
			return result;
		}
	}
}

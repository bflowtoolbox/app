package org.bflow.toolbox.hive.attributes;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bflow.toolbox.hive.attributes.internal.AttributeAdjustProcessorRegistry;
import org.bflow.toolbox.hive.attributes.internal.AttributeViewPlugin;
import org.bflow.toolbox.hive.attributes.utils.EMFUtility;
import org.bflow.toolbox.hive.nls.NLSupport;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.internal.util.DiagramIOUtil;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.ViewPart;

/**
 * Implements the view part to support the add-ons attribute view.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 20.04.10
 * @version 30.12.13
 * 			28.02.15 Added part listener to fix various (de)activate issues
 */
@SuppressWarnings("restriction")
public class AttributeViewPart extends ViewPart implements ISelectionListener, IAttributeFileRegistryListener {
	
	/**
	 * Extension view id
	 */
	public static final String VIEW_ID = "org.bflow.toolbox.attributes.view"; //$NON-NLS-1$
	
	private AttributeFile attrFile;

	private IGraphicalEditPart selectedEditPart;

	private IStructuredSelection selection;

	private DiagramEditPart diagramEditPart;

	private static AttributeViewPart instance;

	private DiagramDocumentEditor diagramEditor;
	
	private boolean sortByName = true;
	private boolean sortASC = false;

	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		site.getPage().addSelectionListener(this);
		
		instance = this;
		AttributeFileRegistry.getInstance().addRegistryListener(this);
		
		/*
		 * [Arian Storch]
		 * This point is very critical. The returned attribute file is null though a valid file exists.
		 * The next fragment should be swapped out in future releases! Look also at the updateView()-method.
		 * 
		 * TODO Add PartListener and request the file when this part is being activated.
		 */
		attrFile = AttributeFileRegistry.getInstance().getActiveAttributeFile();
	}
	
	@Override
	public void dispose() {
		AttributeFileRegistry.getInstance().removeRegistryListener(this);
		getSite().getPage().removeSelectionListener(this);
		instance = null;
		super.dispose();
	}

	@Override
	public void createPartControl(Composite container) {
		ScrolledComposite sc = new ScrolledComposite(container, SWT.V_SCROLL | SWT.H_SCROLL);
		
		final Composite parent = new Composite(sc, SWT.BORDER);
		GridLayout parLayout = new GridLayout(1, false);
		parent.setLayout(parLayout);

		Composite mainPane = new Composite(parent, SWT.BORDER);

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 9;

		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.heightHint = 35;

		mainPane.setLayout(gridLayout);
		mainPane.setLayoutData(gridData);

		Label lblName = new Label(mainPane, SWT.NONE);
		lblName.setText(NLSupport.AttributeViewPart_LabelNameText);

		txtName = new Text(mainPane, SWT.BORDER);

		gridData = new GridData();
		gridData.widthHint = 120;

		txtName.setLayoutData(gridData);

		Label lblValue = new Label(mainPane, SWT.NONE);
		lblValue.setText(NLSupport.AttributeViewPart_LabelValueText);

		txtValue = new Text(mainPane, SWT.BORDER);
		txtValue.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 13) { // means return
					onButtonAddClick();
				}
			}
		});

		gridData = new GridData();
		gridData.widthHint = 120;

		txtValue.setLayoutData(gridData);

		btnAdd = new Button(mainPane, SWT.NONE);
		btnAdd.setImage(new Image(mainPane.getDisplay(), this.getClass()
				.getResourceAsStream("/icons/add8.png"))); //$NON-NLS-1$
		btnAdd.setToolTipText(NLSupport.AttributeViewPart_ButtonAddTooltip);

		btnAdd.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				onButtonAddClick();
			}
		});

		btnAddAll = new Button(mainPane, SWT.NONE);
		btnAddAll.setImage(new Image(mainPane.getDisplay(), this.getClass()
				.getResourceAsStream("/icons/add8.png"))); //$NON-NLS-1$
		btnAddAll
				.setToolTipText(NLSupport.AttributeViewPart_ButtonAddAllTooltip);
		btnAddAll.setText(NLSupport.AttributeViewPart_ButtonAddAllText);

		btnAddAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (selectedEditPart == null)
					return;
				
				// Looking for edges or nodes
				List<?> children = (selectedEditPart instanceof ConnectionNodeEditPart ?
						diagramEditPart.getConnections() : diagramEditPart.getChildren());

				for (Object child : children)
					if (child.getClass() == selectedEditPart.getClass()) {
						IGraphicalEditPart ePart = (IGraphicalEditPart) child;
						EObject eObj = ePart.resolveSemanticElement();

						String id = EMFCoreUtil.getProxyID(eObj);
						String name = txtName.getText();
						String value = txtValue.getText();

						attrFile.add(id, name, (value.isEmpty() ? " " : value)); //$NON-NLS-1$
					}

				// default attributes
				String diagram = getActiveResource(diagramEditor).getName();

				String type = selectedEditPart.resolveSemanticElement().eClass().getInstanceClassName();

				DefaultAttributeProvider.addAttributeForDiagram(diagram,
						txtName.getText(), txtValue.getText(), type);
				// done
				
				txtName.setText(StringUtils.EMPTY);
				txtValue.setText(StringUtils.EMPTY);
				txtName.setFocus();

				if(diagramEditor instanceof IAttributableDocumentEditor) {
					((IAttributableDocumentEditor) diagramEditor)
					.firePropertyChanged();
				}

				updateView();
			}
		});

		btnDel = new Button(mainPane, SWT.NONE);
		btnDel.setImage(new Image(mainPane.getDisplay(), this.getClass()
				.getResourceAsStream("/icons/del8.png"))); //$NON-NLS-1$
		btnDel.setToolTipText(NLSupport.AttributeViewPart_ButtonDeleteTooltip);

		btnDel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (selectedEditPart == null)
					return;

				int sel = attributeTable.getSelectionIndex();

				EObject eObj = EMFUtility.getEObject(selectedEditPart);
				String id = EMFCoreUtil.getProxyID(eObj);

				if (sel > -1) {
					ValuePair pair = (ValuePair) viewer.getElementAt(sel);

					attrFile.remove(id, pair.getName());
					viewer.remove(pair);

					if(diagramEditor instanceof IAttributableDocumentEditor) {
						((IAttributableDocumentEditor) diagramEditor).firePropertyChanged();
					}

					updateView();
				}
			}
		});

		btnDelAll = new Button(mainPane, SWT.NONE);
		btnDelAll.setImage(new Image(mainPane.getDisplay(), this.getClass()
				.getResourceAsStream("/icons/del8.png"))); //$NON-NLS-1$
		btnDelAll.setToolTipText(NLSupport.AttributeViewPart_ButtonDeleteAllTooltip);
		btnDelAll.setText(NLSupport.AttributeViewPart_ButtonDeleteAllText);

		btnDelAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (selectedEditPart == null)
					return;

				if (!MessageDialog.openConfirm(parent.getShell(),
						NLSupport.AttributeViewPart_ConfirmDialogTitle, 
						NLSupport.AttributeViewPart_ConfirmDialogText))
					return;

				int sel = attributeTable.getSelectionIndex();

				if (sel > -1) {
					ValuePair pair = (ValuePair) viewer.getElementAt(sel);

					// Looking for edges or nodes
					List<?> children = (selectedEditPart instanceof ConnectionNodeEditPart ?
							diagramEditPart.getConnections() : diagramEditPart.getChildren());
					
					for (Object child : children)
						if (child.getClass() == selectedEditPart.getClass()) {
							IGraphicalEditPart ePart = (IGraphicalEditPart) child;
							EObject eObj = ePart.resolveSemanticElement();

							String id = EMFCoreUtil.getProxyID(eObj);

							attrFile.remove(id, pair.getName());
						}

					// default attributes
					String diagram = getActiveResource(diagramEditor).getName();

					String type = selectedEditPart.resolveSemanticElement().eClass().getInstanceClassName();

					DefaultAttributeProvider.removeAttributeForDiagram(diagram, pair.getName(), type);
					// done

				}

				if(diagramEditor instanceof IAttributableDocumentEditor) {
					((IAttributableDocumentEditor) diagramEditor).firePropertyChanged();
				}

				updateView();
			}
		});

		btnAddProject = new Button(mainPane, SWT.NONE);
		btnAddProject.setImage(new Image(mainPane.getDisplay(), this.getClass()
				.getResourceAsStream("/icons/add8.png"))); //$NON-NLS-1$
		btnAddProject
				.setToolTipText(NLSupport.AttributeViewPart_ButtonAddProjectTooltip);
		btnAddProject.setText(NLSupport.AttributeViewPart_ButtonAddProjectText);
		btnAddProject.setEnabled(true);

		btnAddProject.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (selectedEditPart == null)
					return;

				if (txtName.getText().trim().isEmpty())
					return;

				String attrName = txtName.getText();
				String attrValue = (txtValue.getText().isEmpty() ? StringUtils.EMPTY
						: txtValue.getText());

				String proj = null;

				try {
					IProject project = getActiveResource(diagramEditor)
							.getProject();
					proj = project.getName();

					String fExt = ((IAttributableDocumentEditor) diagramEditor)
							.getFileExtension();

					for (IResource resource : project.members())
						if (resource.getFileExtension().equalsIgnoreCase(fExt)) {
							IFile iFile = (IFile) resource;

							String type = selectedEditPart.resolveSemanticElement().eClass().getInstanceClassName();

							if (type == null && selectedEditPart != null)
								return;

							Diagram d = DiagramIOUtil.load(diagramEditPart
									.getEditingDomain(), iFile, true, null);

							DiagramEditPart prEditPart = OffscreenEditPartFactory
									.getInstance().createDiagramEditPart(d,
											getSite().getShell());

							AttributeFile prFile = null;

							// ist das Dokument vlt. schon offen?
							for (DiagramDocumentEditor DDE : AttributeFileRegistry.getInstance().getRegisteredEditors()) {
								DiagramEditPart DEP = DDE.getDiagramEditPart();
								String idMap = EMFCoreUtil.getProxyID(DEP
										.resolveSemanticElement());
								String idLoad = EMFCoreUtil
										.getProxyID(prEditPart
												.resolveSemanticElement());

								if (idMap.equalsIgnoreCase(idLoad)) {
									prFile = AttributeFileRegistry.getInstance().getAttributeFile(DDE);
								}
							}

							// dok ist nicht offen und muss geladen werden
							if (prFile == null) {
								prFile = new AttributeFile(prEditPart);
								prFile.load();
							}

							if (prEditPart.getClass().getSimpleName()
									.equalsIgnoreCase(
											selectedEditPart.getClass()
													.getSimpleName())) {
								String id = EMFCoreUtil.getProxyID(prEditPart
										.resolveSemanticElement());
								prFile.add(id, attrName, attrValue);

								prFile.save();
								continue;
							}

							for (Object obj : prEditPart.getChildren()) {
								EObject eObj = ((ShapeNodeEditPart)obj).resolveSemanticElement();
								String eObjType = eObj.eClass().getInstanceClassName();
								
								
								if (eObjType.equalsIgnoreCase(type)) {
									String id = EMFCoreUtil
											.getProxyID(((ShapeNodeEditPart) obj)
													.resolveSemanticElement());

									prFile.add(id, attrName, attrValue);
								}
							}

							prFile.save();
							continue;

						}
				} catch (Exception ex) {
					AttributeViewPlugin.logError(ex.getMessage(), ex);
				}

				// default attributes
				String type = selectedEditPart.resolveSemanticElement().eClass().getInstanceClassName();

				DefaultAttributeProvider.addAttributeForProject(proj, txtName
						.getText(), txtValue.getText(), type);
				// done
				
				// clean up
				txtName.setText(StringUtils.EMPTY);
				txtValue.setText(StringUtils.EMPTY);
				txtName.setFocus();

				if(diagramEditor instanceof IAttributableDocumentEditor) {
					((IAttributableDocumentEditor) diagramEditor)
					.firePropertyChanged();
				}

				updateView();
			}
		});

		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);

		viewer = new TableViewer(parent, SWT.FULL_SELECTION | SWT.MULTI | SWT.BORDER);
		viewer.setComparator(new AttributeViewerComparator());

		TableViewerColumn viewColAttr = new TableViewerColumn(viewer, SWT.NONE);
		viewColAttr.getColumn().setText(NLSupport.AttributeViewPart_ColumnAttributeText);
		viewColAttr.getColumn().setWidth(120);
		viewColAttr.setLabelProvider(new MyColumnLabelProvider(0));
		viewColAttr.getColumn().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean sortColumnChanged = (sortByName = false);
				
				sortByName = true;
				sortASC = (sortColumnChanged ? sortASC : !sortASC);
				updateView();
			}
		});

		TableViewerColumn viewColVal = new TableViewerColumn(viewer, SWT.NONE);
		viewColVal.getColumn().setText(NLSupport.AttributeViewPart_ColumnValueText);
		viewColVal.getColumn().setWidth(180);
		viewColVal.setEditingSupport(new MyColumnViewerEditor(viewer));
		viewColVal.setLabelProvider(new MyColumnLabelProvider(1));
		viewColVal.getColumn().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean sortColumnChanged = (sortByName == true);
				
				sortByName = false;
				sortASC = (sortColumnChanged ? sortASC : !sortASC);
				updateView();
			}
		});

		attributeTable = viewer.getTable();
		attributeTable.setLinesVisible(true);
		attributeTable.setHeaderVisible(true);
		attributeTable.setLayoutData(gridData);
		attributeTable.addKeyListener(new TableViewerKeyListener());

		setUpPopUpMenu(attributeTable);
		
		// Scaling the ScrolledComposite
		parent.layout();
		parent.pack();
		org.eclipse.swt.graphics.Point parentSize = parent.getSize();
		
		sc.setContent(parent);
		sc.setMinSize(parentSize);
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
	}

	private void setUpPopUpMenu(final Table table) {
		Menu popUpMenu = new Menu(table);

		final MenuItem mItemOpenFile = new MenuItem(popUpMenu, SWT.NONE);
		mItemOpenFile.setText(NLSupport.AttributeViewPart_MenuItemOpenFileText);

		mItemOpenFile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				IStructuredSelection structSel = (IStructuredSelection) viewer
						.getSelection();
				ValuePair pair = (ValuePair) structSel.getFirstElement();

				String strUrl = pair.value;

				try {
					URL url = new URL(strUrl);

					String prot = url.getProtocol();

					if (prot.equalsIgnoreCase("file")) { //$NON-NLS-1$
						File fileToOpen = new File(url.getAuthority()
								+ url.getPath());

						if (fileToOpen.exists() && fileToOpen.isFile()) {
							IFileStore fileStore = EFS.getLocalFileSystem()
									.getStore(fileToOpen.toURI());
							IWorkbenchPage page = PlatformUI.getWorkbench()
									.getActiveWorkbenchWindow().getActivePage();

							try {
								IDE.openEditorOnFileStore(page, fileStore);
							} catch (PartInitException e1) {
								MessageDialog.openError(table.getShell(),
										NLSupport.AttributeViewPart_ErrorDialogTitle, NLSupport.AttributeViewPart_ErrorDialogMessage);
								return;
							}
						} else {
							MessageDialog.openError(table.getShell(), "Error",
									NLSupport.AttributeViewPart_ErrorDialogText2);
							return;
						}

					} else {
						try {
							PlatformUI.getWorkbench().getBrowserSupport()
									.createBrowser(
											"attribute view part browser") //$NON-NLS-1$
									.openURL(url);
						} catch (PartInitException e1) {
							AttributeViewPlugin.logError(e1.getMessage(), e1);
						}
					}

				} catch (MalformedURLException e1) {
					MessageDialog.openError(table.getShell(), "Error",
							NLSupport.AttributeViewPart_ErrorDialogText3);
					return;
				}
			}
		});

		table.setMenu(popUpMenu);
	}

	/**
	 * Updates the view content.
	 */
	private void updateView() {
		viewer.getTable().setRedraw(false);
		viewer.setItemCount(0);
		
		if(selectedEditPart == null) {
			viewer.getTable().setRedraw(true);
			return;
		}
		
		EObject eObj = EMFUtility.getEObject(selectedEditPart);

		String id = EMFCoreUtil.getProxyID(eObj);
		
		/* [Arian Storch]
		 * Look at the comment within the init()-method. Following this the next code line is only a hack.
		 * TODO Remove in in future release.
		 */
		attrFile = AttributeFileRegistry.getInstance().getActiveAttributeFile(); 
 
		HashMap<String, String> map = null;
		if(attrFile != null) {
			map = attrFile.get(id);
		}

		if (map == null) {
			viewer.getTable().setRedraw(true);
			return;
		}
		
		for (String name : map.keySet())
			viewer.add(new ValuePair(name, map.get(name)));
		
		viewer.getTable().setRedraw(true);
	}

	/**
	 * Sets a add-on attribute.
	 * 
	 * @param attribute attribute to set
	 */
	public void setAttribute(IAttribute attribute) {
		
		// First, try to delegate the attribute to a specific processor
		if(AttributeAdjustProcessorRegistry.tryToDelegate(attribute, diagramEditPart)) {
			return;
		}
		
		// Handle standard attributes
		String id = attribute.getElementID();
		String name = attribute.getName();

		if (attribute.isLoner() && attribute.getValue() == null) {
			attrFile.remove(id, name);
			return;
		}

		String value = attribute.getValue();
		
		if(DiagramEditorUtils.isGraphicalAttribute(attribute)) {
			DiagramEditorUtils.handleGraphicalAttribute(attribute, diagramEditPart);
			return;
		}

		if (!attribute.isLoner()) {

			Class<?> baseClass = null;

			for (Object obj : diagramEditPart.getChildren()) {
				ShapeNodeEditPart child = (ShapeNodeEditPart) obj;

				EObject eObj = child.resolveSemanticElement();
				String eObjID = EMFCoreUtil.getProxyID(eObj);

				if (eObjID.equalsIgnoreCase(attribute.getElementID())) {
					baseClass = child.getClass();
					break;
				}
			}

			if (baseClass != null) {

				for (Object obj : diagramEditPart.getChildren()) {
					ShapeNodeEditPart child = (ShapeNodeEditPart) obj;

					if (baseClass.isInstance(child)) {
						EObject eObj = child.resolveSemanticElement();
						String eObjID = EMFCoreUtil.getProxyID(eObj);

						if (attribute.getValue() == null) {
							attrFile.remove(eObjID, name);
							continue;
						}

						attrFile.add(eObjID, name, value);
					}
				}
			}
			return;
		}

		// Save new attribute value
		attrFile.add(id, name, (value.isEmpty() ? " " : value)); //$NON-NLS-1$

		if (diagramEditor instanceof IAttributableDocumentEditor) {
			((IAttributableDocumentEditor) diagramEditor).firePropertyChanged();
		}

		updateView();
	}

	@Override
	public void setFocus() {
	}

	/**
	 * Deactivates the usage of the view by disabling and clearing the controls.
	 */
	private void deactivateView() {
		viewer.setItemCount(0);
		setUpControls(false);
	}
	
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// Check if the active editor is a diagram editor. If not deactivate the view
		IEditorPart editorPart = part.getSite().getPage().getActiveEditor();
		if (!(editorPart instanceof DiagramDocumentEditor)) {
			deactivateView();
			return;
		} 
		
		// Check if the part which is affected by the selection is a diagram editor. If not deactivate the view.
		if (!(part instanceof DiagramDocumentEditor)) {
			// deactivateView();
			return;
		}
		
		// Now we are sure that the selection occurred within the current opened editor
		selectedEditPart = null;
		boolean isAssignable = (selection instanceof IStructuredSelection);
		if (!isAssignable) {
			deactivateView();
			return ;
		}
		
		IStructuredSelection sel = (IStructuredSelection) selection;
		this.selection = sel;
		Object selectedObject = sel.getFirstElement();
		
		isAssignable &= isAttributable(selectedObject);
		
		setUpControls(isAssignable);
		
		if (isAssignable) {
			selectedEditPart = (IGraphicalEditPart) selectedObject;
			if (selectedObject instanceof ShapeNodeEditPart || selectedObject instanceof ConnectionNodeEditPart) {
				btnAddAll.setEnabled(true);
				btnDelAll.setEnabled(true);
			}
			
			updateView();
		} else {
			selectedEditPart = null;
			viewer.setItemCount(0);
		}
	}
	
	/**
	 * Returns true if the given object can have attributes.
	 * @param selectedObject object to proof
	 * @return true if the given object can have attributes
	 */
	protected boolean isAttributable(Object selectedObject) {
		return (selectedObject instanceof ShapeNodeEditPart ||
				selectedObject instanceof ConnectionNodeEditPart ||
				selectedObject instanceof DiagramEditPart);
	}

	/**
	 * Returns the selected elements.
	 * 
	 * @return IStructuredSelection
	 */
	public Object getSelection() {
		return this.selection;
	}

	/**
	 * Sets up the enabled state of the controls.
	 * 
	 * @param value
	 *            true or false
	 */
	private void setUpControls(boolean value) {
		if (txtName == null) return;

		txtName.setEnabled(value);
		txtValue.setEnabled(value);
		attributeTable.setEnabled(value);
		btnAdd.setEnabled(value);
		btnDel.setEnabled(value);
		btnAddProject.setEnabled(value);
		btnAddAll.setEnabled(false);
		btnDelAll.setEnabled(false);
	}

	/**
	 * Returns the associated resource file for the opened edit part.
	 * 
	 * @param part
	 *            edit part
	 * @return associated resource file
	 */
	private IFile getActiveResource(IWorkbenchPart part) {
		if (part instanceof DiagramDocumentEditor) {
			IEditorInput input = ((DiagramDocumentEditor) part).getEditorInput();

			if (input instanceof IFileEditorInput)
				return ((IFileEditorInput) input).getFile();
		}

		return null;
	}

	/**
	 * Returns the actually opened attribute file.
	 * 
	 * @return attribute file or null
	 */
	public AttributeFile getAttributeFile() {
		return attrFile;
	}

	/**
	 * Returns the instance of this view part.
	 * 
	 * @return instance of this view part
	 */
	public static AttributeViewPart getInstance() {
		if (instance == null) {
			IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			IWorkbenchPage workbenchPage = workbenchWindow.getActivePage();

			if (workbenchPage != null) {
				try {
					workbenchPage.showView(VIEW_ID);
				} catch (PartInitException e) {
					AttributeViewPlugin.logError(e.getMessage(), e);
				}
			}
		}

		return instance;
	}

	// Layout components
	private Text txtName;
	private Text txtValue;
	private Table attributeTable;
	private TableViewer viewer;
	private Button btnAdd;
	private Button btnDel;
	private Button btnAddAll;
	private Button btnDelAll;
	private Button btnAddProject;
	
	// SelectionListener implementations
	
	
	/**
	 * Handles the click on the add button.
	 */
	private void onButtonAddClick() {
		if (selectedEditPart == null) return;
		if (txtName.getText().trim().isEmpty()) return;
		
		EObject eObj = EMFUtility.getEObject(selectedEditPart);

		String id = EMFCoreUtil.getProxyID(eObj);
		String name = txtName.getText();
		String value = txtValue.getText();

		attrFile.add(id, name, (value.isEmpty() ? StringUtils.EMPTY : value)); //$NON-NLS-1$

		txtName.setText(StringUtils.EMPTY);
		txtValue.setText(StringUtils.EMPTY);
		txtName.setFocus();

		if (diagramEditor instanceof IAttributableDocumentEditor) {
			((IAttributableDocumentEditor) diagramEditor).firePropertyChanged();
		}

		updateView();
	}

	/**
	 * Implements the {@link EditingSupport} for the attribute view table.
	 * 
	 * @author Arian Storch
	 * @since 23/04/10
	 * 
	 */
	private class MyColumnViewerEditor extends EditingSupport {
		private CellEditor editor;

		/**
		 * Constructor.
		 * 
		 * @param viewer
		 *            TableViewer
		 */
		public MyColumnViewerEditor(ColumnViewer viewer) {
			super(viewer);
			this.editor = new TextCellEditor(((TableViewer) viewer).getTable());
		}

		@Override
		protected boolean canEdit(Object element) {
			return true;
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			return editor;
		}

		@Override
		protected Object getValue(Object element) {
			ValuePair pair = (ValuePair) element;
			return pair.getValue();
		}

		@Override
		protected void setValue(Object element, Object value) {
			ValuePair pair = (ValuePair) element;
			pair.setValue((String) value);

			EObject eObj = EMFUtility.getEObject(selectedEditPart);
			
			String id = EMFCoreUtil.getProxyID(eObj);

			attrFile.set(id, pair.getName(), (String) value);

			((IAttributableDocumentEditor) diagramEditor).firePropertyChanged();

			updateView();
		}

	}

	/**
	 * Implements the {@link ColumnLabelProvider} for the attribute view table.
	 * 
	 * @author Arian Storch
	 * @since 23/04/10
	 * 
	 */
	private class MyColumnLabelProvider extends ColumnLabelProvider {
		private int col;

		/**
		 * Constructor.
		 * 
		 * @param col
		 *            column count of the label provider
		 */
		public MyColumnLabelProvider(int col) {
			this.col = col;
		}

		@Override
		public String getText(Object element) {
			ValuePair pair = (ValuePair) element;

			return (col == 0 ? pair.getName() : pair.getValue());
		}
	}
	
	/**
	 * Implements {@link KeyAdapter} to handle the key down event
	 * for the DEL key. 
	 * 
	 * @author Arian Storch
	 * @since 27/02/13
	 *
	 */
	private class TableViewerKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.keyCode != SWT.DEL) {
				return;
			}
			
//			TableItem[] selectedItems = attributeTable.getSelection();
			int selectionIndex = attributeTable.getSelectionIndex();
			
			btnDel.notifyListeners(SWT.Selection, null);
			
			if(attributeTable.getItemCount() > selectionIndex) {
				attributeTable.setSelection(selectionIndex);
			}
		}
	}

	/**
	 * Help class to store values into the attribute view table.
	 * 
	 * @author Arian Storch
	 * @since 23/04/10
	 * 
	 */
	private class ValuePair {
		private String name;
		private String value;

		/**
		 * Constructor.
		 * 
		 * @param name
		 * @param value
		 */
		public ValuePair(String name, String value) {
			super();
			this.name = name;
			this.value = value;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		@SuppressWarnings("unused")
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/**
		 * @param value
		 *            the value to set
		 */
		public void setValue(String value) {
			this.value = value;
		}

	}

	@Override
	public void noticeAttributeFileChange(AttributeFileRegistryEvent event) {
		if(event.attributeFile == null) {
			
		} else {
			attrFile = event.attributeFile;
			diagramEditor = event.diagramEditor;
			diagramEditPart = diagramEditor.getDiagramEditPart();
			updateView();
		}
		
	}
	
	/**
	 * Provides a sorting and comparator ability for the attribute table.
	 * @author Arian Storch
	 * @since 15/01/12
	 *
	 */
	private class AttributeViewerComparator extends ViewerComparator {
		@Override
		public int compare(Viewer viewer, Object e1, Object e2) {
			ValuePair p1 = (ValuePair)e1;
			ValuePair p2 = (ValuePair)e2;
			
			if (sortByName) {
				if (!sortASC) {
					return p1.name.compareToIgnoreCase(p2.name);
				} else {
					return p2.name.compareToIgnoreCase(p1.name);
				}
			} else {
				if (!sortASC) {
					return p1.value.compareToIgnoreCase(p2.value);
				} else {
					return p2.value.compareToIgnoreCase(p1.value);
				}
			}
		}
	}
}

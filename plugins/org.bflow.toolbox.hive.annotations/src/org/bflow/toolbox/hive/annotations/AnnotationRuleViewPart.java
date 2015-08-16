package org.bflow.toolbox.hive.annotations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bflow.toolbox.hive.attributes.AttributeFileRegistry;
import org.bflow.toolbox.hive.attributes.AttributeFileRegistryEvent;
import org.bflow.toolbox.hive.attributes.AttributeViewPart;
import org.bflow.toolbox.hive.attributes.IAttributeFileRegistryListener;
import org.bflow.toolbox.hive.gmfbridge.HiveGmfBridge;
import org.bflow.toolbox.hive.nls.NLSupport;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.part.ViewPart;

/**
 * Implements the view part to support the annotation rule view. Code based on
 * {@link AttributeViewPart}
 * 
 * @author Felix Hoess <fhoess@users.sf.net>
 * @since 11.06.2015
 *
 */
public class AnnotationRuleViewPart extends ViewPart implements
		ISelectionListener, IAttributeFileRegistryListener {

	/**
	 * Extension view id
	 */
	public static final String VIEW_ID = "org.bflow.toolbox.annotationRule.view"; //$NON-NLS-1$
	
	private static Log log = LogFactory.getLog(AnnotationRuleViewPart.class);

	private AnnotationRuleController annotationRuleController = AnnotationRuleController
			.getInstance();

	/**
	 * Selection of rows in the table
	 */
	private IStructuredSelection selection;

	private static AnnotationRuleViewPart instance;

	/**
	 * The editor in which the annotations should be shown
	 */
	private DiagramEditor diagramEditor;

	/**
	 * Saves which column is used for the sorting
	 */
	private int sortBy = 0;
	/**
	 * saves if {@link #sortBy} has changed (if an other column was clicked)
	 */
	private boolean sortColumnChanged = false;
	/**
	 * saves if it should be sorted as- or descending
	 */
	private boolean sortASC = false;

	// Layout components
	private Table rulesTable;
	private TableViewer viewer;
	private Button btnAdd;
	private Button btnImport;
	private Button btnDel;
	private Button btnEdit;


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.ViewPart#init(org.eclipse.ui.IViewSite)
	 */
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		site.getPage().addSelectionListener(this);
		AttributeFileRegistry.getInstance().addRegistryListener(this);


		instance = this;

	}

	@Override
	public void dispose() {
		getSite().getPage().removeSelectionListener(this);
		AttributeFileRegistry.getInstance().removeRegistryListener(this);
		instance = null;
		super.dispose();
	}

	/**
	 * creates the gui
	 */
	@Override
	public void createPartControl(Composite container) {
		ScrolledComposite sc = new ScrolledComposite(container, SWT.V_SCROLL
				| SWT.H_SCROLL);

		final Composite parent = new Composite(sc, SWT.BORDER);
		GridLayout parLayout = new GridLayout(1, false);
		parent.setLayout(parLayout);

		final Composite mainPane = new Composite(parent, SWT.BORDER);

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 4;

		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;

		mainPane.setLayout(gridLayout);
		mainPane.setLayoutData(gridData);

		btnAdd = new Button(mainPane, SWT.NONE);
		btnAdd.setImage(new Image(mainPane.getDisplay(), this.getClass()
				.getResourceAsStream("/icons/add8.png"))); //$NON-NLS-1$ 
		btnAdd.setText(NLSupport.AnnotationRuleViewPart_Annotation_ButtonAddText);

		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				onButtonAddClick();
			}
		});

		btnDel = new Button(mainPane, SWT.NONE);
		btnDel.setImage(new Image(mainPane.getDisplay(), this.getClass()
				.getResourceAsStream("/icons/del8.png"))); //$NON-NLS-1$ 
		btnDel.setText(NLSupport.AnnotationRuleViewPart_Annotation_ButtonDelete_Text);

		btnDel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				onButtonDelClick();

			}
		});

		

		btnImport = new Button(mainPane, SWT.NONE);
		btnImport.setImage(new Image(mainPane.getDisplay(), this.getClass()
				.getResourceAsStream("/icons/add8.png"))); //$NON-NLS-1$ 
		btnImport
				.setText(NLSupport.AnnotationRuleViewPart_Annotation_ButtonImportText);

		btnImport.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				onButtonImportIconClick(mainPane);
			}

		});

		btnEdit = new Button(mainPane, SWT.NONE);
		btnEdit.setImage(new Image(mainPane.getDisplay(), this.getClass()
				.getResourceAsStream("/icons/add8.png")));
		btnEdit.setText(NLSupport.AnnotationRuleViewPart_Annotation_ButtonEditText);

		btnEdit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				onButtonEditKlick();
			}

		});

		// Read the rules in the xml file and present them onto the table 

		// //Table

		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);

		viewer = new TableViewer(parent, SWT.FULL_SELECTION | SWT.MULTI
				| SWT.BORDER);
		viewer.setComparator(new AttributeFilterViewerComparator());

		TableViewerColumn viewColCategory = new TableViewerColumn(viewer,
				SWT.NONE);
		viewColCategory.getColumn().setText(
				NLSupport.AnnotationRuleViewPart_AnnotationKeyWord_Category);
		viewColCategory.getColumn().setWidth(120);
		viewColCategory.setLabelProvider(new MyColumnLabelProvider(
				RuleEntry.ColumnCATEGORY));
		viewColCategory.setEditingSupport(new MyColumnViewerEditor(viewer,
				RuleEntry.ColumnCATEGORY));
		viewColCategory.getColumn().addSelectionListener(
				new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						sortColumnChanged = (sortBy != RuleEntry.ColumnCATEGORY);
						sortBy = RuleEntry.ColumnCATEGORY;
						sortASC = (sortColumnChanged ? sortASC : !sortASC);
						updateView();
					}
				});

		TableViewerColumn viewColAttr = new TableViewerColumn(viewer, SWT.NONE);
		viewColAttr.getColumn().setText(
				NLSupport.AnnotationRuleViewPart_AnnotationKeyWord_AttributeName);
		viewColAttr.getColumn().setWidth(120);
		viewColAttr.setLabelProvider(new MyColumnLabelProvider(
				RuleEntry.ColumnATTRIBUTE_NAME));
		viewColAttr.setEditingSupport(new MyColumnViewerEditor(viewer,
				RuleEntry.ColumnATTRIBUTE_NAME));
		viewColAttr.getColumn().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sortColumnChanged = (sortBy != RuleEntry.ColumnATTRIBUTE_NAME);
				sortBy = RuleEntry.ColumnATTRIBUTE_NAME;
				sortASC = (sortColumnChanged ? sortASC : !sortASC);
				updateView();
			}
		});

		TableViewerColumn viewColOperator = new TableViewerColumn(viewer,
				SWT.CENTER);
		viewColOperator.getColumn().setText(
				NLSupport.AnnotationRuleViewPart_AnnotationKeyWord_Operator);
		viewColOperator.getColumn().setWidth(60);
		viewColOperator.setEditingSupport(new MyColumnViewerEditor(viewer,
				RuleEntry.ColumnOPERATOR));
		viewColOperator.setLabelProvider(new MyColumnLabelProvider(
				RuleEntry.ColumnOPERATOR));
		viewColOperator.getColumn().setResizable(false);
		viewColOperator.getColumn().addSelectionListener(
				new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						sortColumnChanged = (sortBy != RuleEntry.ColumnOPERATOR);
						sortBy = RuleEntry.ColumnOPERATOR;
						sortASC = (sortColumnChanged ? sortASC : !sortASC);
						updateView();
					}
				});

		TableViewerColumn viewColVal = new TableViewerColumn(viewer, SWT.NONE);
		viewColVal.getColumn().setText(
				NLSupport.AnnotationRuleViewPart_AnnotationKeyWord_Value);
		viewColVal.getColumn().setWidth(180);
		viewColVal.setEditingSupport(new MyColumnViewerEditor(viewer,
				RuleEntry.ColumnVALUE));
		viewColVal.setLabelProvider(new MyColumnLabelProvider(
				RuleEntry.ColumnVALUE));
		viewColVal.getColumn().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sortColumnChanged = (sortBy != RuleEntry.ColumnVALUE);
				sortBy = RuleEntry.ColumnVALUE;
				sortASC = (sortColumnChanged ? sortASC : !sortASC);
				updateView();
			}
		});

		TableViewerColumn viewColDirection = new TableViewerColumn(viewer,
				SWT.NONE);
		viewColDirection.getColumn().setText(
				NLSupport.AnnotationRuleViewPart_AnnotationKeyWord_Direction);
		viewColDirection.getColumn().setWidth(180);
		viewColDirection.setEditingSupport(new MyColumnViewerEditor(viewer,
				RuleEntry.ColumnDIRECTION));
		viewColDirection.setLabelProvider(new MyColumnLabelProvider(
				RuleEntry.ColumnDIRECTION));
		viewColDirection.getColumn().addSelectionListener(
				new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						sortColumnChanged = (sortBy != RuleEntry.ColumnDIRECTION);
						sortBy = RuleEntry.ColumnDIRECTION;
						sortASC = (sortColumnChanged ? sortASC : !sortASC);
						updateView();
					}
				});

		TableViewerColumn viewColFilename = new TableViewerColumn(viewer,
				SWT.NONE);
		viewColFilename.getColumn().setText(
				NLSupport.AnnotationRuleViewPart_AnnotationKeyWord_Filename);
		viewColFilename.getColumn().setWidth(180);
		viewColFilename.setEditingSupport(new MyColumnViewerEditor(viewer,
				RuleEntry.ColumnFILENAME));
		viewColFilename.setLabelProvider(new MyColumnLabelProvider(
				RuleEntry.ColumnFILENAME));
		viewColFilename.getColumn().addSelectionListener(
				new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						sortColumnChanged = (sortBy != RuleEntry.ColumnFILENAME);
						sortBy = RuleEntry.ColumnFILENAME;
						sortASC = (sortColumnChanged ? sortASC : !sortASC);
						updateView();
					}
				});

		TableViewerColumn viewColActive = new TableViewerColumn(viewer,
				SWT.NONE);
		viewColActive.getColumn().setText(
				NLSupport.AnnotationRuleViewPart_AnnotationKeyWord_Active);
		viewColActive.getColumn().setWidth(180);
		viewColActive.setEditingSupport(new MyColumnViewerEditor(viewer,
				RuleEntry.ColumnACTIVE));
		viewColActive.setLabelProvider(new MyColumnLabelProvider(
				RuleEntry.ColumnACTIVE));
		viewColActive.getColumn().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sortColumnChanged = (sortBy != RuleEntry.ColumnACTIVE);
				sortBy = RuleEntry.ColumnACTIVE;
				sortASC = (sortColumnChanged ? sortASC : !sortASC);
				updateView();
			}
		});


		rulesTable = viewer.getTable();
		rulesTable.setLinesVisible(true);
		rulesTable.setHeaderVisible(true);
		rulesTable.setLayoutData(gridData);
		rulesTable.addKeyListener(new TableViewerKeyListener());
		rulesTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				onButtonEditKlick();
			}
		});


		// Scaling the ScrolledComposite
		parent.layout();
		parent.pack();
		org.eclipse.swt.graphics.Point parentSize = parent.getSize();

		sc.setContent(parent);
		sc.setMinSize(parentSize);
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		updateView();
	}

	/**
	 * Updates the view content.
	 */
	private void updateView() {
		viewer.getTable().setRedraw(false);
		viewer.setItemCount(0);
		List<RuleEntry> list = annotationRuleController.getRules(); //?? maybe listener concept too?? look comment above and init()method of AttributeViewPart.java


		if (list == null) {
			viewer.getTable().setRedraw(true);
			return;
		}

		for (RuleEntry entry : list)
			viewer.add(entry);

		viewer.getTable().setRedraw(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
	}

	/**
	 * Deactivates the usage of the view by disabling and clearing the controls.
	 */
	private void disableView() {
		viewer.setItemCount(0);
		setUpControls(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.
	 * IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// Check if the active editor is a diagram editor. If not deactivate the
		// view
		IEditorPart activeEditorPart = part.getSite().getPage()
				.getActiveEditor();

		// Handling MultiPageEditorPart
		if (activeEditorPart instanceof MultiPageEditorPart) {
			MultiPageEditorPart multiPageEditorPart = (MultiPageEditorPart) activeEditorPart;
			activeEditorPart = (IEditorPart) multiPageEditorPart
					.getSelectedPage();
		}

		// Ensure that is a graphical editor
		if (!(activeEditorPart instanceof GraphicalEditor)) {
			disableView();
			return;
		}

		// Handling MultiPageEditorPart
		if (part instanceof MultiPageEditorPart) {
			MultiPageEditorPart multiPageEditorPart = (MultiPageEditorPart) part;
			part = (IEditorPart) multiPageEditorPart.getSelectedPage();
		}

		if (!(part instanceof IEditorPart))
			return;
		part = HiveGmfBridge.adapt((IEditorPart) part);

		// Check if the part which is affected by the selection is a diagram
		// editor. If not deactivate the view.
		if (!(part instanceof DiagramEditor)) {
			// deactivateView();
			return;
		}

		// Now we are sure that the selection occurred within the current opened
		// editor
		// selectedEditPart = null;
		boolean isAssignable = (selection instanceof IStructuredSelection);
		if (!isAssignable) {
			disableView();
			return;
		}

		// Adapt selected objects if necessary
		IStructuredSelection structuredSelection = (IStructuredSelection) selection;
		Object[] selectedObjectsArray = structuredSelection.toArray();
		Object[] adaptedObjectsArray = HiveGmfBridge
				.adaptSelection(selectedObjectsArray);

		this.selection = new StructuredSelection(adaptedObjectsArray);
//		Object selectedObject = ((StructuredSelection) this.selection).getFirstElement();

		// isAssignable &= isAttributable(selectedObject);

		setUpControls(isAssignable);

		if (isAssignable) {
			// selectedEditPart = (IGraphicalEditPart) selectedObject;
			// if (selectedObject instanceof ShapeNodeEditPart
			// || selectedObject instanceof ConnectionNodeEditPart) {
			// btnAddAll.setEnabled(true);
			// btnDelAll.setEnabled(true);
		}

		updateView();
		// } else {
		// selectedEditPart = null;
		// viewer.setItemCount(0);
		// }
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
		rulesTable.setEnabled(value);
		btnAdd.setEnabled(value);
		btnDel.setEnabled(value);
		btnImport.setEnabled(value);
		btnEdit.setEnabled(value);
	}



	/**
	 * Returns the actually used AnnotationRuleController
	 * 
	 * @return the controller or null
	 */
	public AnnotationRuleController getAnnotationRuleController() {
		return annotationRuleController;
	}

	/**
	 * Returns the instance of this view part.
	 * 
	 * @return instance of this view part
	 */
	public static AnnotationRuleViewPart getInstance() {
		if (instance == null) {
			IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow();
			IWorkbenchPage workbenchPage = workbenchWindow.getActivePage();

			if (workbenchPage != null) {
				try {
					workbenchPage.showView(VIEW_ID);
				} catch (PartInitException e) {
					log.error(e.getMessage(), e);
				}
			}
		}

		return instance;
	}


	// SelectionListener implementations

	/**
	 * Handles the click on the add button.
	 */
	private void onButtonAddClick() {

		AnnotationRuleCreateDialog dialog = new AnnotationRuleCreateDialog(
				null, annotationRuleController);
		dialog.create();
		dialog.open();

		//notify the Editor that a new rule is active (uses the attributeFileChangedEvent for that)
		AttributeFileRegistry.getInstance().dispatchAttributeFileChangedEvent();
		AnnotationRuleController.getInstance().notifyListeners(diagramEditor);
		updateView();
	}

	private void onButtonDelClick() {
		int sel = rulesTable.getSelectionIndex();

		if (sel > -1) {
			RuleEntry entry = (RuleEntry) viewer.getElementAt(sel);
			annotationRuleController.remove(entry);
			viewer.remove(entry);

			//notify the Editor that rule is deleted (uses the attributeFileChangedEvent for that)
			AttributeFileRegistry.getInstance().dispatchAttributeFileChangedEvent();
			AnnotationRuleController.getInstance().notifyListeners(diagramEditor);

			updateView();
		}
	}

	/**
	 * Opens a custom file chooser for images. Imports the chosen file to the
	 * default (or custom) annotation folder
	 * 
	 * @param parent
	 *            the parent Shell
	 */
	private void onButtonImportIconClick(Composite parent) {
		FileDialog fileDialog = new FileDialog(parent.getShell(), SWT.OPEN);
		fileDialog.setText(NLSupport.ValidationPage_ButtonImportText);
		fileDialog.setFilterExtensions(ImageFileChooserUtils.WildcardFileExtensions);
		
		String filePath = fileDialog.open();
		if (filePath == null) return;
		
		File file = new File(filePath);
		
		String filename = file.getName();
		
		File newFile = new File(AnnotationLauncherConfigurator.getANNOTATIONLOGIC_FOLDER_PATH()	+ filename);
		try {
			Files.copy(file.toPath(), newFile.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void onButtonEditKlick() {
		int sel = rulesTable.getSelectionIndex();
		if (sel == -1) return;
		
		RuleEntry entry = (RuleEntry) viewer.getElementAt(sel); 
		
		AnnotationRuleCreateDialog dialog = new AnnotationRuleCreateDialog(null, annotationRuleController, entry);
		dialog.create();
		dialog.open();

		//notify the Editor that a new rule is active (uses the attributeFileChangedEvent for that)
		AttributeFileRegistry.getInstance().dispatchAttributeFileChangedEvent();
		AnnotationRuleController.getInstance().notifyListeners(diagramEditor);
		updateView();
	}


	/**
	 * Implements the {@link EditingSupport} for the annotation rule view table.
	 * 
	 * @author Felix Hoess <fhoess@users.sf.net>
	 * @since 02.06.2015
	 *
	 */
	private class MyColumnViewerEditor extends EditingSupport {
		private CellEditor editor;
		private int col;

		/**
		 * Constructor.
		 * 
		 * @param viewer
		 *            TableViewer
		 */
		public MyColumnViewerEditor(ColumnViewer viewer, int col) {
			super(viewer);
			this.col = col;
			this.editor = col == RuleEntry.ColumnACTIVE ? new CheckboxCellEditor(
					((TableViewer) viewer).getTable()) : new TextCellEditor(
					((TableViewer) viewer).getTable());

		}

		@Override
		protected boolean canEdit(Object element) {
			return col == RuleEntry.ColumnACTIVE ? true : false;
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			return editor;
		}

		@Override
		protected Object getValue(Object element) {
			RuleEntry entry = (RuleEntry) element;
			if (entry == null)
				return null;
			else
				return (col == RuleEntry.ColumnACTIVE) ? entry.isActive()
						: entry.getEntryInColumn(col);
		}

		@Override
		protected void setValue(Object element, Object value) {
			RuleEntry entry = (RuleEntry) element;
			//TODO currently only used for isActive column

			AnnotationRuleController.getInstance().updateRule(entry,
					!entry.isActive());
			AttributeFileRegistry.getInstance()
					.dispatchAttributeFileChangedEvent();
			updateView();
		}
		

	}


	/**
	 * Implements the {@link ColumnLabelProvider} for the annotation rule view
	 * table.
	 * 
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
			RuleEntry entry = (RuleEntry) element;
			String result = "";
			switch (col) {
			case RuleEntry.ColumnCATEGORY:
				result = entry.getCategory();
				break;
			case RuleEntry.ColumnATTRIBUTE_NAME:
				result = entry.getAttributeName();
				break;
			case RuleEntry.ColumnOPERATOR:
				result = entry.getOperator();
				break;
			case RuleEntry.ColumnVALUE:
				result = entry.getAttributeValue();
				break;
			case RuleEntry.ColumnDIRECTION:
				result = entry.getDirection();
				break;
			case RuleEntry.ColumnFILENAME:
				result = entry.getFilename();
				break;
			case RuleEntry.ColumnACTIVE:
				result = entry.isActive() ? NLSupport.AttributeFilterViewPart_ColumnActive_Used
						: NLSupport.AttributeFilterViewPart_ColumnActive_Unused;
				break;

			}
			return result;

		}

		/**
		 * Icons of http://eclipse-icons.i24.cc/index.html
		 */
		@Override
		public Image getImage(Object element) {
			if (col != RuleEntry.ColumnACTIVE)
				return null;
			else {
				RuleEntry entry = (RuleEntry) element;
				String imgFileName = entry.isActive() ? "never_translate.gif"
						: "disabled_co.gif";
				return new Image(null, this.getClass().getResourceAsStream(
						"/icons/" + imgFileName));
			}
		}
	}

	/**
	 * Implements {@link KeyAdapter} to handle the key down event for the DEL
	 * key. (deletes just one selected item, even if more are selected)
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

		//	 TableItem[] selectedItems = filterTable.getSelection();
			int selectionIndex = rulesTable.getSelectionIndex();

			btnDel.notifyListeners(SWT.Selection, null);

			if (rulesTable.getItemCount() > selectionIndex) {
				rulesTable.setSelection(selectionIndex);
			}
		}
	}


	/**
	 * Provides a sorting and comparator ability for the rule table.
	 *
	 * @author Felix Hoess <fhoess@users.sf.net>
	 * @since 29.05.2015
	 *
	 */
	private class AttributeFilterViewerComparator extends ViewerComparator {
		@Override
		public int compare(Viewer viewer, Object e1, Object e2) {

			RuleEntry p1 = (RuleEntry) e1;
			RuleEntry p2 = (RuleEntry) e2;

			switch (sortBy) {
			case RuleEntry.ColumnATTRIBUTE_NAME:
				if (!sortASC) {
					return p1.getAttributeName().compareToIgnoreCase(p2.getAttributeName());
				} else {
					return p2.getAttributeName().compareToIgnoreCase(p1.getAttributeName());
				}
			case RuleEntry.ColumnVALUE:
				if (!sortASC) {
					return p1.getAttributeValue().compareToIgnoreCase(p2.getAttributeValue());
				} else {
					return p2.getAttributeValue().compareToIgnoreCase(p1.getAttributeValue());
				}
			case RuleEntry.ColumnOPERATOR:
				if (!sortASC) {
					return p1.getOperator().compareToIgnoreCase(
							p2.getOperator());
			} else {
					return p2.getOperator().compareToIgnoreCase(
							p1.getOperator());
				}
			case RuleEntry.ColumnCATEGORY:
				if (!sortASC) {
					return p1.getCategory().compareToIgnoreCase(
							p2.getCategory());
				} else {
					return p2.getCategory().compareToIgnoreCase(
							p1.getCategory());
				}
			case RuleEntry.ColumnDIRECTION:
				if (!sortASC) {
					return p1.getDirection().compareToIgnoreCase(
							p2.getDirection());
				} else {
					return p2.getDirection().compareToIgnoreCase(
							p1.getDirection());
				}
			case RuleEntry.ColumnFILENAME:
				if (!sortASC) {
					return p1.getFilename().compareToIgnoreCase(
							p2.getFilename());
				} else {
					return p2.getFilename().compareToIgnoreCase(
							p1.getFilename());
				}
			case RuleEntry.ColumnACTIVE:
				if (!sortASC) {
					return String.valueOf(p1.isActive()).compareToIgnoreCase(
							String.valueOf(p2.isActive()));
				} else {
					return String.valueOf(p2.isActive()).compareToIgnoreCase(
							String.valueOf(p1.isActive()));
				}
			default:
				return 0;
			}

		}
	}

	@Override
	public void noticeAttributeFileChange(AttributeFileRegistryEvent event) {
		if (event.attributeFile == null)
			return;

		diagramEditor = event.diagramEditor;
	}

}

package org.bflow.toolbox.hive.attributefilter;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.bflow.toolbox.hive.attributes.AttributeFileRegistry;
import org.bflow.toolbox.hive.attributes.AttributeFileRegistryEvent;
import org.bflow.toolbox.hive.attributes.AttributeViewPart;
import org.bflow.toolbox.hive.attributes.IAttributableDocumentEditor;
import org.bflow.toolbox.hive.attributes.IAttributeFileRegistryListener;
import org.bflow.toolbox.hive.attributes.internal.AttributeViewPlugin;
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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
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
 * Implements the view part to support the add-ons attribute filter view. Code
 * based on {@link AttributeViewPart}
 * 
 * @author Felix Hoess <fhoess@users.sf.net>
 * @since 29.05.2015
 *
 */
public class AttributeFilterViewPart extends ViewPart implements
		ISelectionListener, IAttributeFileRegistryListener {

	/**
	 * Extension view id
	 */
	public static final String VIEW_ID = "org.bflow.toolbox.attributesFilter.view"; //$NON-NLS-1$



	private IStructuredSelection selection; // Selection of rows in the table

	private static AttributeFilterViewPart instance;

	private DiagramEditor diagramEditor;

	private int sortBy = 0;
	private boolean sortColumnChanged = false;
	private boolean sortASC = false;

	// Layout components
	private Text txtKey;

	private Text txtValue;

	private Combo operatorBox;

	private Table filterTable;

	private TableViewer viewer;

	private Button btnAdd;

	private Button btnDel;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.ViewPart#init(org.eclipse.ui.IViewSite)
	 */
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		site.getPage().addSelectionListener(this);
		instance = this;
		AttributeFileRegistry.getInstance().addRegistryListener(this); //used to update View of the editor 

	}

	@Override
	public void dispose() {
		AttributeFileRegistry.getInstance().removeRegistryListener(this);
		getSite().getPage().removeSelectionListener(this);
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

		Composite mainPane = new Composite(parent, SWT.BORDER);

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 8;

		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.heightHint = 35;

		mainPane.setLayout(gridLayout);
		mainPane.setLayoutData(gridData);

		Label lblName = new Label(mainPane, SWT.NONE);
		lblName.setText(NLSupport.AttributeViewPart_LabelNameText);

		txtKey = new Text(mainPane, SWT.BORDER);

		gridData = new GridData();
		gridData.widthHint = 120;

		txtKey.setLayoutData(gridData);

		operatorBox = new Combo(mainPane, SWT.DROP_DOWN | SWT.READ_ONLY);

		String[] operators = { ">", "=", "<", "\u2260", "\u2264", "\u2265" };
		operatorBox
				.setLayoutData(new GridData(SWT.NONE, SWT.NONE, false, false));
		operatorBox.setItems(operators);
		operatorBox.select(1);

		Label lblValue = new Label(mainPane, SWT.NONE);
		lblValue.setText(NLSupport.AttributeViewPart_LabelValueText);

		txtValue = new Text(mainPane, SWT.BORDER);
		txtValue.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == 13) { // means return
					onButtonAddClick();
				}
			}
		});

		btnAdd = new Button(mainPane, SWT.NONE);
		btnAdd.setImage(new Image(mainPane.getDisplay(), this.getClass()
				.getResourceAsStream("/icons/add8.png"))); //$NON-NLS-1$
		btnAdd.setToolTipText(NLSupport.AttributeFilterViewPart_ButtonAddTooltip);
		btnAdd.setText(NLSupport.AttributeFilterViewPart_ButtonAddText);

		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				onButtonAddClick();
			}
		});

		btnDel = new Button(mainPane, SWT.NONE);
		btnDel.setImage(new Image(mainPane.getDisplay(), this.getClass()
				.getResourceAsStream("/icons/del8.png"))); //$NON-NLS-1$
		btnDel.setToolTipText(NLSupport.AttributeFilterViewPart_ButtonDelTooltip);
		btnDel.setText(NLSupport.AttributeFilterViewPart_ButtonDelText);

		btnDel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				int sel = filterTable.getSelectionIndex();

				if (sel > -1) {
					FilterEntry entry = (FilterEntry) viewer.getElementAt(sel);
					AttributeFilterController.getInstance().remove(entry);
					viewer.remove(entry);

					if (diagramEditor instanceof IAttributableDocumentEditor) {
						((IAttributableDocumentEditor) diagramEditor)
								.firePropertyChanged();
					}
					AttributeFileRegistryEvent event = new AttributeFileRegistryEvent();
					event.diagramEditor = diagramEditor;
					FilterController.getInstance().noticeAttributeFileChange(
							event);

					updateView();
				}
			}
		});

		gridData = new GridData();
		gridData.widthHint = 120;

		txtValue.setLayoutData(gridData);

		// //TABEL

		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);

		viewer = new TableViewer(parent, SWT.FULL_SELECTION | SWT.MULTI
				| SWT.BORDER);
		viewer.setComparator(new AttributeFilterViewerComparator());

		TableViewerColumn viewColAttr = new TableViewerColumn(viewer, SWT.NONE);
		viewColAttr.getColumn().setText(
				NLSupport.AttributeFilterViewPart_ColumnAttributeName);
		viewColAttr.getColumn().setWidth(120);
		viewColAttr.setLabelProvider(new MyColumnLabelProvider(
				FilterEntry.ColumnATTRIBUTE_NAME));
		viewColAttr.setEditingSupport(new MyColumnViewerEditor(viewer,
				FilterEntry.ColumnATTRIBUTE_NAME));
		viewColAttr.getColumn().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sortColumnChanged = (sortBy != FilterEntry.ColumnATTRIBUTE_NAME);
				sortBy = FilterEntry.ColumnATTRIBUTE_NAME;
				sortASC = (sortColumnChanged ? sortASC : !sortASC);
				updateView();
			}
		});

		TableViewerColumn viewColOperator = new TableViewerColumn(viewer,
				SWT.CENTER);
		viewColOperator.getColumn().setText(
				NLSupport.AttributeFilterViewPart_ColumnOperator);
		viewColOperator.getColumn().setWidth(60);
		viewColOperator.setEditingSupport(new MyColumnViewerEditor(viewer,
				FilterEntry.ColumnOPERATOR));
		viewColOperator.setLabelProvider(new MyColumnLabelProvider(
				FilterEntry.ColumnOPERATOR));
		viewColOperator.getColumn().setResizable(false);
		viewColOperator.getColumn().addSelectionListener(
				new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {

						sortColumnChanged = (sortBy != FilterEntry.ColumnOPERATOR);
						sortBy = FilterEntry.ColumnOPERATOR;
						sortASC = (sortColumnChanged ? sortASC : !sortASC);
						updateView();

					}
				});

		TableViewerColumn viewColVal = new TableViewerColumn(viewer, SWT.NONE);
		viewColVal.getColumn().setText(
				NLSupport.AttributeFilterViewPart_ColumnAttributeValue);
		viewColVal.getColumn().setWidth(180);
		viewColVal.setEditingSupport(new MyColumnViewerEditor(viewer,
				FilterEntry.ColumnVALUE));
		viewColVal.setLabelProvider(new MyColumnLabelProvider(
				FilterEntry.ColumnVALUE));
		viewColVal.getColumn().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sortColumnChanged = (sortBy != FilterEntry.ColumnVALUE);
				sortBy = FilterEntry.ColumnVALUE;
				sortASC = (sortColumnChanged ? sortASC : !sortASC);
				updateView();

			}
		});

		TableViewerColumn viewColActive = new TableViewerColumn(viewer,
				SWT.CENTER);
		viewColActive.getColumn().setText(
				NLSupport.AttributeFilterViewPart_ColumnActive);
		viewColActive.getColumn().setWidth(80);
		viewColActive
				.setEditingSupport(new MyCheckboxColumnViewerEditor(viewer));
		viewColActive.setLabelProvider(new MyColumnLabelProvider(
				FilterEntry.ColumnACTIVE));
		viewColActive.getColumn().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sortColumnChanged = (sortBy != FilterEntry.ColumnACTIVE);
				sortBy = FilterEntry.ColumnACTIVE;
				sortASC = (sortColumnChanged ? sortASC : !sortASC);
				updateView();
			}
		});

		filterTable = viewer.getTable();
		filterTable.setLinesVisible(true);
		filterTable.setHeaderVisible(true);
		filterTable.setLayoutData(gridData);
		filterTable.addKeyListener(new TableViewerKeyListener());


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
		/*
		 * TODO following comment is legacy: 
		 * 
		 * [Arian Storch] Look at the comment within the init()-method.
		 * Following this the next code line is only a hack. TODO Remove in in
		 * future release.
		 * 
		 */
		// attrFile =
		// AttributeFileRegistry.getInstance().getActiveAttributeFile();

		ArrayList<FilterEntry> list = AttributeFilterController.getInstance()
				.getAllFilters(); //?? maybe listener concept too?? look comment above and init()method of AttributeViewPart.java

		if (list == null) {
			viewer.getTable().setRedraw(true);
			return;
		}

		for (FilterEntry entry : list)
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
			disableView();
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
		setUpControls(isAssignable);

		updateView();
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
		if (txtKey == null)
			return;

		txtKey.setEnabled(value);
		txtValue.setEnabled(value);
		filterTable.setEnabled(value);
		btnAdd.setEnabled(value);
		btnDel.setEnabled(value);
	}



	/**
	 * Returns the instance of this view part.
	 * 
	 * @return instance of this view part
	 */
	public static AttributeFilterViewPart getInstance() {
		if (instance == null) {
			IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow();
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

	

	// SelectionListener implementations

	/**
	 * Handles the click on the add button.
	 */
	private void onButtonAddClick() {
		if (txtKey.getText().trim().isEmpty()
				|| txtValue.getText().trim().isEmpty())
			return;

		String key = txtKey.getText();
		String value = txtValue.getText();
		String filename = FilterLauncherConfigurator.getInstance()
				.getDefaultFilename();//TODO

		AttributeFilterController.getInstance().add(key, operatorBox.getText(),
				value, filename);

		txtKey.setText(StringUtils.EMPTY);
		txtValue.setText(StringUtils.EMPTY);
		txtKey.setFocus();
		AttributeFileRegistryEvent event = new AttributeFileRegistryEvent();
		event.diagramEditor = diagramEditor;
		FilterController.getInstance().noticeAttributeFileChange(event);

		updateView();
	}

	/**
	 * Implements the {@link EditingSupport} for the attribute filter view
	 * table.
	 * 
	 * @author Arian Storch
	 * @since 23/04/10
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
			this.editor = new TextCellEditor(((TableViewer) viewer).getTable());
			this.col = col;
		}

		@Override
		protected boolean canEdit(Object element) {
				return false;
			//TODO if you change to true, implement getValue und setValue etc.
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			return editor;
		}

		@Override
		protected Object getValue(Object element) {
			// To Do if you change canEdit to true
			FilterEntry entry = (FilterEntry) element;
			String str = "";
			switch (col) {
			case 0:
				str = entry.getAttributeName();
				break;
			case 1:
				str = entry.getOperator();
				break;
			case 2:
				str = entry.getValue();
				break;
			}

			return str;
		}

		@Override
		protected void setValue(Object element, Object value) {
			// TO DO if canEdit returns true
			((IAttributableDocumentEditor) diagramEditor).firePropertyChanged();
			AttributeFileRegistryEvent event = new AttributeFileRegistryEvent();
			event.diagramEditor = diagramEditor;
			FilterController.getInstance().noticeAttributeFileChange(event);
			updateView();
		}

	}

	private class MyCheckboxColumnViewerEditor extends EditingSupport {
		private CellEditor editor;

		/**
		 * Constructor.
		 * 
		 * @param viewer
		 *            TableViewer
		 */
		public MyCheckboxColumnViewerEditor(ColumnViewer viewer) {
			super(viewer);
			this.editor = new CheckboxCellEditor(
					((TableViewer) viewer).getTable());
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
			FilterEntry entry = (FilterEntry) element;
			return entry.isActive();
		}

		@Override
		protected void setValue(Object element, Object value) {
			FilterEntry entry = (FilterEntry) element;
			entry.setActive(!entry.isActive());
			AttributeFilterController.getInstance().updateEntry(entry);

			if (diagramEditor instanceof IAttributableDocumentEditor) {
				((IAttributableDocumentEditor) diagramEditor)
						.firePropertyChanged();
			}

			AttributeFileRegistryEvent event = new AttributeFileRegistryEvent();
			event.diagramEditor = diagramEditor;
			FilterController.getInstance().noticeAttributeFileChange(event);
			updateView();
		}

	}

	/**
	 * Implements the {@link ColumnLabelProvider} for the attribute view table.
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
			FilterEntry entry = (FilterEntry) element;
			String result = "";
			switch (col) {
			case FilterEntry.ColumnATTRIBUTE_NAME:
				result = entry.getAttributeName();
				break;
			case FilterEntry.ColumnOPERATOR:
				result = entry.getOperator();
				break;
			case FilterEntry.ColumnVALUE:
				result = entry.getValue();
				break;
			case FilterEntry.ColumnACTIVE:
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
			if (col != FilterEntry.ColumnACTIVE)
				return null;
			else {
				FilterEntry entry = (FilterEntry) element;
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
			int selectionIndex = filterTable.getSelectionIndex(); 

			btnDel.notifyListeners(SWT.Selection, null);

			if (filterTable.getItemCount() > selectionIndex) {
				filterTable.setSelection(selectionIndex);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bflow.toolbox.hive.attributes.IAttributeFileRegistryListener#
	 * noticeAttributeFileChange
	 * (org.bflow.toolbox.hive.attributes.AttributeFileRegistryEvent)
	 */
	@Override
	public void noticeAttributeFileChange(AttributeFileRegistryEvent event) {
		if (event.diagramEditor == null)
			return;

		diagramEditor = event.diagramEditor;
		updateView();
	}

	/**
	 * Provides a sorting and comparator ability for the filter table.
	 *
	 * @author Felix Hoess <fhoess@users.sf.net>
	 * @since 29.05.2015
	 *
	 */
	private class AttributeFilterViewerComparator extends ViewerComparator {
		@Override
		public int compare(Viewer viewer, Object e1, Object e2) {

			FilterEntry p1 = (FilterEntry) e1;
			FilterEntry p2 = (FilterEntry) e2;

			switch (sortBy) {
			case FilterEntry.ColumnATTRIBUTE_NAME:
				if (!sortASC) {
					return p1.getAttributeName().compareToIgnoreCase(p2.getAttributeName());
				} else {
					return p2.getAttributeName().compareToIgnoreCase(p1.getAttributeName());
				}
			case FilterEntry.ColumnOPERATOR:
				if (!sortASC) {
					return p1.getOperator().compareToIgnoreCase(
							p2.getOperator());
			} else {
					return p2.getOperator().compareToIgnoreCase(
							p1.getOperator());
				}
			case 	FilterEntry.ColumnVALUE:
				if (!sortASC) {
					return p1.getValue().compareToIgnoreCase(p2.getValue());
				} else {
					return p2.getValue().compareToIgnoreCase(p1.getValue());
				}
			case FilterEntry.ColumnACTIVE:
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
}

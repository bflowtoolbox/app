package org.bflow.toolbox.hive.statement.views;


import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.part.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;


public class StatementView extends ViewPart implements ISelectionListener{

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.bflow.toolbox.hive.statement.views.StatementView";

	private TableViewer viewer;
	private Action addSatementAction;
	private Action doubleClickAction;
	private ArrayList<String> statements = new ArrayList<String>();

	private String diagramTitle;

	private TableViewerColumn viewCol1;

	class ColumnTextLabelProvider extends ColumnLabelProvider{
		private int column;
		
		public ColumnTextLabelProvider(int column) 
		{
			this.column = column;
		}
		
		@Override
		public String getText(Object element) 
		{
			if (column == 0) {
				return element.toString();
			}
			return null;
		}
		
		public Image getImage(Object obj) {
			
			if (column == 0) {
				if (isLastStatement(obj.toString())) {
					return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ADD);
				}
				return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
			}
			if (column == 1) {
				if (isLastStatement(obj.toString())) {
					return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ADD);
				}
				return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_TOOL_DELETE);
			}
			return null;
		}
	}
	class NameSorter extends ViewerSorter {
	}

	/**
	 * The constructor.
	 */
	public StatementView() {
		
		IEditorPart currentEditorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if (currentEditorPart != null) {
			this.diagramTitle = currentEditorPart.getTitle();
		}else {
			this.diagramTitle = "Kein Diagram ausgewählt";
		}
				
		this.statements.add("Funktion1 kann mehrfach ausgeführt werden");
		this.statements.add("");
	}
	
	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		
		viewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		
		final TableViewerFocusCellManager focusCellManager = new TableViewerFocusCellManager(viewer,new FocusCellOwnerDrawHighlighter(viewer));
		ColumnViewerEditorActivationStrategy actSupport = new ColumnViewerEditorActivationStrategy(viewer) {

			@Override
			protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
				ViewerCell focusCell = (ViewerCell) event.getSource();
				int currentColumn = focusCell.getVisualIndex();
				String currentStatement = (String) focusCell.getElement();
				
				if (currentColumn == 0) {
					if (isLastStatement(currentStatement)) {
						if (event.keyCode == SWT.SPACE ||event.eventType == ColumnViewerEditorActivationEvent.MOUSE_CLICK_SELECTION) {
							addSatementAction.run();
						}
					}
				}
				
				if (currentColumn == 1) {
					if (event.keyCode == SWT.SPACE ||event.eventType == ColumnViewerEditorActivationEvent.MOUSE_CLICK_SELECTION) {
						if (!isLastStatement(currentStatement)) {
							boolean bool = MessageDialog.openQuestion(viewer.getControl().getShell(), "Statement wirklich entfernen?", "Soll "+ currentStatement + " wirklich entfernt werden?");
							if (bool) {
								statements.remove(currentStatement);
								viewer.refresh();
							}
						}else {
							addSatementAction.run();
						}
					}
				}
				return false;
			}};
			
		int features = ColumnViewerEditor.TABBING_HORIZONTAL
				| ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR
				| ColumnViewerEditor.TABBING_VERTICAL
				| ColumnViewerEditor.KEYBOARD_ACTIVATION;

		TableViewerEditor.create(viewer, focusCellManager, actSupport, features);
				
		Table table = viewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		viewCol1 = new TableViewerColumn(viewer, SWT.NONE);
		viewCol1.getColumn().setText("Statements für " + diagramTitle);
		viewCol1.getColumn().setWidth(480);
		viewCol1.setLabelProvider(new ColumnTextLabelProvider(0));
		
		TableViewerColumn viewCol2 = new TableViewerColumn(viewer, SWT.NONE);
		viewCol2.getColumn().setWidth(17);
		viewCol2.setLabelProvider(new ColumnTextLabelProvider(1));
		
		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "org.bflow.toolbox.hive.statement.viewer");
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
		viewer.setInput(statements);
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				StatementView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(addSatementAction);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(addSatementAction);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(addSatementAction);
	}

	private void makeActions() {
		addSatementAction = new Action() {
			public void run() {
				
				InputDialog newStatementDialog = new InputDialog(getSite().getShell(), "Statement hinzufügen", "Neues Statement", null, null);
				int result = newStatementDialog.open();
				if (result == 0) {
					String last = statements.get(statements.size()-1);
					statements.remove(last);
					statements.add(newStatementDialog.getValue());
					statements.add(last);
					viewer.refresh(statements);
				}
			}
		};
		addSatementAction.setText("Statement hinzufügen");
		addSatementAction.setToolTipText("Füge ein neues Statement hinzu");
		addSatementAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ADD));
		
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				String statement = (String) ((IStructuredSelection)selection).getFirstElement();
				if (isLastStatement(statement)) {
					return;
				}
				InputDialog editStatementDialog = new InputDialog(getSite().getShell(), "Statement bearbeiten", "Statement", statement, null);
				int result = editStatementDialog.open();
				if (result == 0) {
					int index = statements.indexOf(statement);
					statements.set(index, editStatementDialog.getValue());
					viewer.refresh(statements);
				}
			}
		};
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	private boolean isLastStatement(String statement) {
		return this.statements.indexOf(statement) == this.statements.size()-1;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.ViewPart#init(org.eclipse.ui.IViewSite)
	 */
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		site.getPage().addSelectionListener(this);
	}
	
	@Override
	public void dispose() {
		getSite().getPage().removeSelectionListener(this);
		super.dispose();
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		// TODO Auto-generated method stub
		IEditorPart activeEditorPart = part.getSite().getPage().getActiveEditor();
		
		if (activeEditorPart != null) {
			diagramTitle = activeEditorPart.getTitle();
			viewCol1.getColumn().setText("Statements für " + diagramTitle);
		}
		
		
	}

}